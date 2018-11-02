package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
************************************/

/************************************
 * GAME STATE VARIABLES
 *  -p1Cards: ArrayList<PlayerCard>
 *  -p2Cards: ArrayList<PlayerCard>
 *  -playerDeck: ArrayList<PlayerCard>
 *  -infectionDeck: ArrayList<InfectionCard>
 *  -playerDiscardDeck: ArrayList<PlayerCard>
 *  -infectionDiscardDeck: ArrayList<InfectionCard>
 *  -p1Pawn: Pawn
 *  -p2Pawn: Pawn
 *  -numPlayers: int
 *  -playerTurn: String
 *  -actionsLeft: int
 *  -infectionRate: int
 *  -outbreakNum: int
 *  -curedDiseases: int[]
 *  -playerCity: String
 ************************************/

import java.util.ArrayList;

/************************************
 * PandemicGameState Constructor
 * Initializes a 2-player game
 * where neither players have any
 * cards or pawns yet and it is
 * player 1's turn.
 ************************************/
public class PandemicGameState {
    /* NOTE: The cards a player has will be implemented as an array of general cards
                in the actual game implementation, but are single GeneralCard objects for now
                because the object class is empty and we don't have a "getCardName" function
                implemented yet.
     */
    private ArrayList<PlayerCard> p1Cards;
    private ArrayList<PlayerCard> p2Cards;
    private ArrayList<PlayerCard> playerDeck;
    private ArrayList<InfectionCard> infectionDeck;
    private ArrayList<PlayerCard> playerDiscardDeck;
    private ArrayList<InfectionCard> infectionDiscardDeck;
    private Pawn p1Pawn;
    private Pawn p2Pawn;
    private int numPlayers;
    private int infectionRate;
    private int outbreakNum;
    private int playerTurn;
    private int actionsLeft;
    private int[] curedDiseases;
    private String playerCity;
    private final int MAX_CARDS = 7;
    private final int MAX_INFECTION_RATE = 4;
    private PlayerInfo player;


        //default constructor
    PandemicGameState() {
        numPlayers = 2;
        infectionRate = 2;
        outbreakNum = 0;
        curedDiseases = new int[] {0, 0, 0, 0}; //1 = cured, 2 = eradicated



    }

    //copy constructor
    PandemicGameState(PandemicGameState otherState) {
        this.p1Cards = otherState.p1Cards;
            for(int i = 0; i<otherState.p1Cards.size(); i++){
                this.p1Cards.add(new PlayerCard(otherState.p1Cards.get(i)));
            }
        for(int i = 0; i<otherState.p2Cards.size(); i++){
            this.p2Cards.add(new PlayerCard(otherState.p2Cards.get(i)));
        }
        for(int i = 0; i<otherState.playerDeck.size(); i++){
            this.playerDeck.add(new PlayerCard(otherState.playerDeck.get(i)));
        }
        for(int i = 0; i<otherState.infectionDeck.size(); i++){
            this.infectionDeck.add(new InfectionCard(otherState.infectionDeck.get(i)));
        }
        for(int i = 0; i<otherState.playerDiscardDeck.size(); i++){
            this.playerDiscardDeck.add(new PlayerCard(otherState.playerDiscardDeck.get(i)));
        }
        for(int i = 0; i<otherState.infectionDiscardDeck.size(); i++){
            this.infectionDiscardDeck.add(new InfectionCard(otherState.infectionDiscardDeck.get(i)));
        }
        this.p1Pawn = otherState.p1Pawn;
        this.p2Pawn = otherState.p2Pawn;
        this.infectionRate = otherState.infectionRate;
        this.numPlayers = otherState.numPlayers;
        this.outbreakNum = otherState.outbreakNum;
        for(int i = 0; i<otherState.curedDiseases.length; i++){
            this.curedDiseases[i]= otherState.curedDiseases[i];
        }


    }

    //PlayerInfo contains information on id of player who's turn it is
    //Moves player to a different city
    public boolean movePawn(PlayerInfo player, City city, City desiredCity) {
        if(player.getActionsLeft()<=0){
            return false;
        }
        int count = city.getAdjacentCities().size();
        while(count > 0)
        {
            if (desiredCity.equals(city.getAdjacentCities()))
            {
                player.setCurrentLocation(desiredCity);
            }

        }
        return true;
    }

    //adds card to player's hand
    public boolean drawPlayerCard( PlayerInfo player, int numCards) {
        if(numCards>MAX_CARDS){
            return false;
        }
        player.addCardToPlayerHand(playerDeck.get(0));
        playerDeck.remove(0);

        return true;
    }

    //draws card from infection deck
    public boolean drawInfectionCard( PlayerInfo player, int infectionRate) {
        this.infectionDeck.get(0);
        infectionDeck.remove(0);
        return true;
    }

    //puts a player card in the player discard deck
    public boolean discardPlayerCard( PlayerInfo player, int numCards, PlayerCard playerCards){
        if(playerCards == null){
            return false;
        }
        return true;
    }

    //removes a card from an infection deck and
    public boolean discardInfectionCard( PlayerInfo player, int infectionRate) {
        this.infectionDeck.remove(0);
        return true;
    }

    //Adds a research station to a specified city
    public boolean buildAResearchStation( PlayerInfo player, City playerCity, PlayerCard gc) {
        //normal, operations expert
        if(player.getActionsLeft()<=0){
            return false;
        }
        player.setActionsLeft(player.getActionsLeft()-1);
        return true;
    }

    //removes disease cube(s) at a specified city
    public boolean treatDisease(PlayerInfo player, City city) {
        //normal, medic
        if(player.getActionsLeft()<=0){
            return false;
        }
        city.removeDiseaseCube();
        player.setActionsLeft(player.getActionsLeft()-1);
        return true;
    }

    //Sets state of disease to cured
    public boolean discoverACure(PlayerInfo player, City playerCity, PlayerCard gc) {
        //normal, scientist
        if(player.getActionsLeft()<=0){
            return false;
        }
        curedDiseases[0] = 1;
        player.setActionsLeft(player.getActionsLeft()-1);
        return true;
    }

    //increases the infection rate
    public boolean increaseInfectionRate( PlayerInfo player) {
        if(getInfectionRate()<MAX_INFECTION_RATE) {
            setInfectionRate(getInfectionRate() + 1);
            return true;
        }
       return false;
    }

    //Adds disease cube(s) to a city
    public boolean infect(PlayerInfo player) {
        //normal, epidemic, outbreak
        if(player.getActionsLeft()<=0){
            return false;
        }
        player.getCurrentLocation().addDiseaseCube("blue");
        return true;
    }

    //reshuffles infection discard deck, adds back into infection deck
    public boolean intensify( PlayerInfo player) {
        //reshuffling and adding
        return true;
    }

    //trades city card with another player
    public boolean shareKnowledge( PlayerInfo player) {
        //normal, researcher
        if(player.getActionsLeft()<=0){
            return false;
        }
        player.setActionsLeft(player.getActionsLeft()-1);
        return true;
    }

    //Activates an event card
    public boolean playEventCard( PlayerInfo player) {
        if(player.getActionsLeft()<=0){
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

        String p1String = "\nPlayer 1 cards: " + p1Cards + "\n";
        String p2String = "\nPlayer 2 cards: " + p2Cards + "\n";
        String gameInfo = "\nNumber of players: " + numPlayers + "\nWhich player's turn it is: "
                + "\nThe infection rate is: " + infectionRate
                + "\nThe amount of outbreaks that have occurred: " + outbreakNum
                + "\nThe number of actions left: " + player.getActionsLeft()
                + "\nThe city that the current player is in: " + player.getCurrentLocation();

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

        fullString = p1String + p2String + gameInfo + diseaseInfo;
        return fullString;
    }

/****************************************
 * GETTERS & SETTERS
 ***************************************/

public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<PlayerCard> getP1Cards() {
        return p1Cards;
    }

    public ArrayList<PlayerCard> getP2Cards() {
        return p2Cards;
    }

    public int getInfectionRate() {
        return infectionRate;
    }

    public Pawn getP1Pawn() {
        return p1Pawn;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getOutbreakNum() {
        return outbreakNum;
    }

    public Pawn getP2Pawn() {
        return p2Pawn;
    }

    public int[] getCuredDiseases() {
        return curedDiseases;
    }

    public PlayerInfo getPlayer() {
        return player;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setP1Cards(ArrayList<PlayerCard> p1Cards) {
        this.p1Cards = p1Cards;
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

    public void setP1Pawn(Pawn p1Pawn) {
        this.p1Pawn = p1Pawn;
    }

    public void setP2Cards(ArrayList<PlayerCard> p2Cards) {
        this.p2Cards = p2Cards;
    }

    public void setP2Pawn(Pawn p2Pawn) {
        this.p2Pawn = p2Pawn;
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

    public void setPlayer(PlayerInfo player) {
        this.player = player;
    }
}
