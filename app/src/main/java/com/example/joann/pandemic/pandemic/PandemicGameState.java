package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
************************************/

/************************************
 * GAME STATE VARIABLES
 *  -playerDeck: ArrayList<PlayerCard>
 *  -infectionDeck: ArrayList<InfectionCard>
 *  -playerDiscardDeck: ArrayList<PlayerCard>
 *  -infectionDiscardDeck: ArrayList<InfectionCard>
 *  -numPlayers: int
 *  -actionsLeft: int
 *  -infectionRate: int
 *  -outbreakNum: int
 *  -curedDiseases: int[]
 ************************************/

import android.util.Log;

import com.example.joann.pandemic.game.infoMsg.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/************************************
 * PandemicGameState Constructor
 * Initializes a 2-player game
 * where neither players have any
 * cards or pawns yet and it is
 * player 1's turn.
 ************************************/

public class PandemicGameState extends GameState {
    private ArrayList<PlayerCard> playerDeck;
    private ArrayList<InfectionCard> infectionDeck;
    private ArrayList<PlayerCard> playerDiscardDeck;
    private ArrayList<InfectionCard> infectionDiscardDeck;
    private int numPlayers;
    private int infectionRate;
    private int outbreakNum;
    private int[] curedDiseases;
    private final int MAX_CARDS = 7;
    private final int MAX_INFECTION_RATE = 4;
    private Random rand = new Random();


    //default constructor
    PandemicGameState() {
        playerDeck = new ArrayList<>();
        playerDiscardDeck = new ArrayList<>();
        infectionDeck = new ArrayList<>();
        infectionDiscardDeck = new ArrayList<>();
        numPlayers = 2;
        infectionRate = 2;
        outbreakNum = 0;
        curedDiseases = new int[]{0, 0, 0, 0}; //1 = cured, 2 = eradicated
        init();
    }
    private void init(){
        PlayerCard.initStarterPlayerDecks(playerDeck, infectionDeck);
    }

    //copy constructor
    PandemicGameState(PandemicGameState otherState) {

        //copy player deck
        for (int i = 0; i < otherState.playerDeck.size(); i++) {
            this.playerDeck.add(new PlayerCard(otherState.playerDeck.get(i)));
        }

        //copy infection deck
        for (int i = 0; i < otherState.infectionDeck.size(); i++) {
            this.infectionDeck.add(new InfectionCard(otherState.infectionDeck.get(i)));
        }

        //copy player discard deck
        for (int i = 0; i < otherState.playerDiscardDeck.size(); i++) {
            this.playerDiscardDeck.add(new PlayerCard(otherState.playerDiscardDeck.get(i)));
        }

        //copy infection discard deck
        for (int i = 0; i < otherState.infectionDiscardDeck.size(); i++) {
            this.infectionDiscardDeck.add(new InfectionCard(otherState.infectionDiscardDeck.get(i)));
        }

        //copy simple board variables
        this.infectionRate = otherState.infectionRate;
        this.numPlayers = otherState.numPlayers;
        this.outbreakNum = otherState.outbreakNum;
        for (int i = 0; i < otherState.curedDiseases.length; i++) {
            this.curedDiseases[i] = otherState.curedDiseases[i];
        }

        //copy players


    }

    //Moves player to a different city
    public boolean movePawn(PlayerInfo player, City currentCity, City desiredCity) {

        //Base Case: Player is trying to move when they have no moves left.
        if (player.getActionsLeft() <= 0) {
            return false;
        }

        //Drive Case: Move to a city you are connected to.
        for(City c: desiredCity.adjacentCities) { //Iterate through adjacent cities of desired city
            if (c == currentCity) {
                player.setCurrentLocation(desiredCity);
                player.actionTaken();
                return true;
            }
        }

        //Direct Flight Case: Move to a city whose card you have.
        for(PlayerCard p: player.getPlayerHand()){
            if(p.getLocation() == desiredCity){
                player.setCurrentLocation(desiredCity);
                discardPlayerCard(player, p);
                player.actionTaken();
                return true;
            }
        }

        //Charter Flight Case: Move to a city whose card you have.
        for(PlayerCard p: player.getPlayerHand()){
            if(p.getLocation() == player.getCurrentLocation()){
                player.setCurrentLocation(desiredCity);
                discardPlayerCard(player, p);
                player.actionTaken();
                return true;
            }
        }

        //Shuttle Flight: Move from a city with a research station to any other city that has a research station.
        if(player.getCurrentLocation().getHasResearchLab() && desiredCity.getHasResearchLab()) {
            player.setCurrentLocation(desiredCity);
            player.actionTaken();
            return true;
        }

        //If all of these fail (meaning a player clicked on a city they  can't go to), pop
        //up a dialog box that tells them so and lets them continue to make actions.

        //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        Log.e("Move ", "Something went wrong in PandemicGameState>Move");
        return false;

    }

    //Adds card to player's hand
    public boolean drawPlayerCard(PlayerInfo player, PlayerCard card) {
        if(player.getPlayerHand().size() >= 7){
            //print message?
            return false;
        }

        else{
            int index = rand.nextInt(playerDeck.size());
            card = playerDeck.get(index);
            player.addCardToPlayerHand(card);
            //reflect in gui?
            return true;
        }
    }

    //draws card from infection deck and infects city
    public boolean drawInfectionCard() {
        int index = rand.nextInt(infectionDeck.size());

        InfectionCard c = infectionDeck.get(index);

        for(int i = 0; i < infectionRate; i++){
            c.getLocation().addDiseaseCube(c.getDiseaseColor());
        }
        c.getLocation().addDiseaseCube(c.getDiseaseColor());
        infectionDiscardDeck.add(c);
        infectionDeck.remove(c);
        return true;
    }

    //puts a player card in the player discard deck
    public boolean discardPlayerCard(PlayerInfo player, PlayerCard playerCard) {
        player.getPlayerHand().remove(playerCard);
        playerDiscardDeck.add(playerCard);
        return true;
    }

    //Adds a research station to a specified city
    public boolean buildAResearchStation(PlayerInfo player, City playerCity, PlayerCard gc) {
        //normal, operations expert
        if (player.getActionsLeft() <= 0) {
            return false;
        }
        player.setActionsLeft(player.getActionsLeft() - 1);
        return true;
    }

    //removes disease cube(s) at a specified city
    public boolean treatDisease(PlayerInfo player, City city) {
        //normal, medic
        if (player.getActionsLeft() <= 0) {
            return false;
        }
        if (city.getDiseaseCubes().isEmpty()) {
            return false; //no disease cubes there
        }
        city.removeDiseaseCube();
        player.setActionsLeft(player.getActionsLeft() - 1);
        return true;
    }

    //Sets state of disease to cured
    public boolean discoverACure(PlayerInfo player) {
        //normal, scientist
        if (player.getActionsLeft() <= 0) {
            return false;
        }
        if(player.getCurrentLocation().hasResearchLab == false){
            return false;
        }
        //counters for number of each card color in player hand
        int numYellow = 0;
        int numRed = 0;
        int numBlue = 0;
        int numBlack = 0;

        //Loop through player hand and count colors
        for( int i = 0; i< player.getPlayerHand().size(); i++){
            String color = player.getPlayerHand().get(i).getdiseaseColor();
            if(color.equals("Yellow")){
                numYellow++;
            }
            if(color.equals("Red")){
                numRed++;
            }
            if(color.equals("Blue")){
                numBlue++;
            }
            if(color.equals("Black")){
                numBlack++;
            }

            //sets a disease to cured if player has enough cards of a certain color
                if(numYellow>=4){
                    curedDiseases[0] = 1;
                }
                if(numRed>=4){
                    curedDiseases[1] = 1;
                }
                if(numBlue>=4){
                    curedDiseases[2] = 1;
                }
                if(numBlack>=4){
                    curedDiseases[3] = 1;
                }
            //Special case for if player role is scientist
            //Then player only needs 3 cards to cure a disease
            if(player.role == 2){
                if(numYellow>=3){
                    curedDiseases[0] = 1;
                }
                if(numRed>=3){
                    curedDiseases[1] = 1;
                }
                if(numBlue>=3){
                    curedDiseases[2] = 1;
                }
                if(numBlack>=3){
                    curedDiseases[3] = 1;
                }
            }
        }


        player.setActionsLeft(player.getActionsLeft() - 1);
        return true;
    }

    //increases the infection rate
    public boolean increaseInfectionRate(PlayerInfo player) {
        if (getInfectionRate() < MAX_INFECTION_RATE) {
            setInfectionRate(getInfectionRate() + 1);
            return true;
        }
        return false;
    }

    //reshuffles infection discard deck, adds back into infection deck
    public boolean intensify(PlayerInfo player) {
        //reshuffling and adding
        for(int i = 0; i < infectionDiscardDeck.size(); i++){
            InfectionCard card = infectionDiscardDeck.get(i);
            infectionDeck.add(card);
        }
        Collections.shuffle(infectionDeck);
        return true;
    }

    //trades city card with another player
    public boolean shareKnowledge(PlayerInfo player) {
        //normal, researcher
        if (player.getActionsLeft() <= 0) {
            return false;
        }
        player.setActionsLeft(player.getActionsLeft() - 1);
        return true;
    }

    //Activates an event card
    public boolean playEventCard(PlayerInfo player) {
        if (player.getActionsLeft() <= 0) {
            return false;
        }
        //if(role = contingencyPlanner){
        //do action with different requirements.
        //}
        return true;
    }

    @Override
    public String toString() {
        String fullString;

        int curedCount = 0;
        int eCount = 0;
        int oCount = 0;
        String diseaseInfo = "";
        for (int i = 0; i < 4; i++) {
            if (curedDiseases[i] == 1) {
                curedCount++;
                diseaseInfo = "\nNumber of cured diseases: " + curedCount;
            } else if (curedDiseases[i] == 2) {
                eCount++;
                diseaseInfo = diseaseInfo + "\nNumber of eradicated diseases: " + eCount;
            } else if (curedDiseases[i] == 0) {
                oCount++;
                diseaseInfo = diseaseInfo + "\nNumber of diseases that are still rampaging: " + oCount;
            }
        }

        fullString = diseaseInfo;
        return fullString;
    }

    /****************************************
     * GETTERS & SETTERS
     ***************************************/

    public int getNumPlayers() {
        return numPlayers;
    }


    public int getInfectionRate() {
        return infectionRate;
    }

    public int getOutbreakNum() {
        return outbreakNum;
    }

    public int[] getCuredDiseases() {
        return curedDiseases;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setInfectionDeck(ArrayList<InfectionCard> infectionDeck) {
        this.infectionDeck = infectionDeck;
    }

    public void setPlayerDeck(ArrayList<PlayerCard> playerDeck) {
        this.playerDeck = playerDeck;
    }

    public void setInfectionDiscardDeck(ArrayList<InfectionCard> infectionDiscardDeck) {
        this.infectionDiscardDeck = infectionDiscardDeck;
    }

    public void setPlayerDiscardDeck(ArrayList<PlayerCard> playerDiscardDeck) {
        this.playerDiscardDeck = playerDiscardDeck;
    }


    public void setCuredDiseases(int[] curedDiseases) {
        this.curedDiseases = curedDiseases;
    }

    public void setInfectionRate(int infectionRate) {
        this.infectionRate = infectionRate;
    }

    public void setOutbreakNum(int outbreakNum) {
        this.outbreakNum = outbreakNum;
    }

}

