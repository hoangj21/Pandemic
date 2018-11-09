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

import android.app.AlertDialog;

import com.example.joann.pandemic.game.LocalGame;
import com.example.joann.pandemic.game.infoMsg.GameState;

import java.util.ArrayList;

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


    //default constructor
    PandemicGameState() {
        numPlayers = 2;
        infectionRate = 2;
        outbreakNum = 0;
        curedDiseases = new int[]{0, 0, 0, 0}; //1 = cured, 2 = eradicated
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
            /* PSEUDOCODE
                1. Iterate through player's hand
                2. See if card.getCity = desired city
                3. If so, discard that card and player.setCurrentLocation(desiredCity);
                4. player.actionTaken(); and return true
             */

        //Charter Flight Case: Move to a city whose card you have.
            /* PSEUDOCODE
                1. Iterate through player's hand
                2. See if card.getCity = player.getCurrentLocation()
                3. If so, discard that card and player.setCurrentLocation(desiredCity);
                4. player.actionTaken(); and return true
             */

        //Shuttle Flight: Move from a city with a research station to any other city that has a research station.
        if(player.getCurrentLocation().getHasResearchLab() && desiredCity.getHasResearchLab()) {
            player.setCurrentLocation(desiredCity);
            player.actionTaken();
            return true;
        }

        //If all of these fail (meaning a player clicked on a city they  can't go to), pop
        //up a dialog box that tells them so and lets them continue to make actions.

        //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        return false;

    }

    //adds card to player's hand
    public boolean drawPlayerCard(PlayerInfo player, int numCards) {
        if (numCards > MAX_CARDS) {
            return false;
        }
        player.addCardToPlayerHand(playerDeck.get(0));
        playerDeck.remove(0);

        return true;
    }

    //draws card from infection deck
    public boolean drawInfectionCard(PlayerInfo player, int infectionRate) {
        this.infectionDeck.get(0);
        infectionDeck.remove(0);
        return true;
    }

    //puts a player card in the player discard deck
    public boolean discardPlayerCard(PlayerInfo player, int numCards, PlayerCard playerCards) {
        if (playerCards == null) {
            return false;
        }
        return true;
    }

    //removes a card from an infection deck and
    public boolean discardInfectionCard(PlayerInfo player, int infectionRate) {
        this.infectionDeck.remove(0);
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
    public boolean discoverACure(PlayerInfo player, City playerCity, PlayerCard gc) {
        //normal, scientist
        if (player.getActionsLeft() <= 0) {
            return false;
        }
        curedDiseases[0] = 1;
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

    //Adds disease cube(s) to a city
    public boolean infect(PlayerInfo player) {
        //normal, epidemic, outbreak
        if (player.getActionsLeft() <= 0) {
            return false;
        }
        player.getCurrentLocation().addDiseaseCube("blue");
        return true;
    }

    //reshuffles infection discard deck, adds back into infection deck
    public boolean intensify(PlayerInfo player) {
        //reshuffling and adding
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
