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
import android.widget.Toast;

import com.example.joann.pandemic.R;
import com.example.joann.pandemic.game.infoMsg.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.example.joann.pandemic.pandemic.EventCard.initEventCard;
//import static com.example.joann.pandemic.pandemic.PlayerCard.initStarterPlayerDecks;

/************************************
 * PandemicGameState Constructor
 * Initializes a 2-player game
 * where neither players have any
 * cards or pawns yet and it is
 * player 1's turn.
 ************************************/
//NOTE TO US: Roles are represented with ints
    //0 =
public class PandemicGameState extends GameState {
    private ArrayList<Card> playerDeck;
    private ArrayList<InfectionCard> infectionDeck;
    private ArrayList<Card> playerDiscardDeck;
    private ArrayList<InfectionCard> infectionDiscardDeck;
    private ArrayList<City> allCities;
    private ArrayList<PlayerInfo> players;
    private int numPlayers;
    private int infectionRate;
    private int outbreakNum;
    private int[] curedDiseases;
    private int playerTurn;
    private int numResearchStations;
    private PlayerInfo player;
    private City tappedCity;
    private String message;
    private boolean isLegal;
    //private PlayerInfo player2;

    //If these go to 0, players have lost the game
    private int numCubesBlue ;
    private int numCubesBlack ;
    private int numCubesRed;
    private int numCubesYellow;
    private int numPlayerCardsInDeck; //(add 5 for action cards when they're implemented)


    private final int MAX_CARDS = 7;
    private final int MAX_INFECTION_RATE = 4;
    private final int MAX_RESEARCH_STATIONS = 6;


    private Random rand = new Random();


    //default constructor
    public PandemicGameState() {
        playerDeck = new ArrayList<>();
        playerDiscardDeck = new ArrayList<>();
        infectionDeck = new ArrayList<>();
        infectionDiscardDeck = new ArrayList<>();
        numPlayers = 2;
        infectionRate = 2;
        outbreakNum = 0;
        //order of curedDisease will be {blue, black, red, yellow}
        curedDiseases = new int[]{0, 0, 0, 0}; //1 = cured, 2 = eradicated
        playerTurn = 0;
        numResearchStations = 0;
        allCities = new ArrayList<>();
        numCubesBlue = 24;
        numCubesBlack = 24;
        numCubesRed = 24;
        numCubesYellow = 24;
        numPlayerCardsInDeck = 48;
        tappedCity = new City();
        init();

        //initializing two player info objects
        PlayerInfo player1 = initPlayer();
        player1.setPlayerNumber(1);
        PlayerInfo player2 = initPlayer();
        player2.setPlayerNumber(2);

        //adding players to array of players
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);


        //default, set first turn to player1
        player = player1;

        message = "";
        isLegal = true;



    }
    private PlayerInfo initPlayer(){

        //starter city Atlanta is located at AllCities.get(3);
        int role = rand.nextInt(4);

        //initializing player
        PlayerInfo aPlayer = new PlayerInfo(0, role, 4, allCities.get(1));

        //draws 4 random cards and adds them to the player hand
        drawPlayerCard(aPlayer);
        drawPlayerCard(aPlayer);
        drawPlayerCard(aPlayer);
        drawPlayerCard(aPlayer);
        return aPlayer;
    }
    private void init(){
        initStarterPlayerDecks(playerDeck, infectionDeck);
    }

    //copy constructor
    public PandemicGameState(PandemicGameState otherState) {

        //copy player deck
        playerDeck = new ArrayList<Card>();
        for (int i = 0; i < otherState.playerDeck.size(); i++) {
            PlayerCard card = new PlayerCard((PlayerCard) otherState.getPlayerDeck().get(i));
            this.playerDeck.add(card);
        }

        //copy infection deck
        infectionDeck = new ArrayList<InfectionCard>();
        for (int i = 0; i < otherState.infectionDeck.size(); i++) {
            InfectionCard card = new InfectionCard(otherState.getInfectionDeck().get(i));
            this.infectionDeck.add(card);
        }

        //copy player discard deck
        playerDiscardDeck = new ArrayList<Card>();
        for (int i = 0; i < otherState.playerDiscardDeck.size(); i++) {
            PlayerCard card = new PlayerCard((PlayerCard)otherState.getPlayerDiscardDeck().get(i));
            this.playerDiscardDeck.add(card);
        }

        //copy infection discard deck
        infectionDiscardDeck = new ArrayList<InfectionCard>();
        for (int i = 0; i < otherState.infectionDiscardDeck.size(); i++) {
            InfectionCard card = new InfectionCard(otherState.getInfectionDeck().get(i));
            this.infectionDiscardDeck.add(card);
        }

        players = new ArrayList<PlayerInfo>();
        for(int i = 0; i< otherState.getPlayers().size(); i++){
            PlayerInfo otherPlayer = new PlayerInfo(otherState.getPlayers().get(i));
            this.players.add(otherPlayer);
        }

        allCities = new ArrayList<City>();
        for(int i = 0; i< otherState.getAllCities().size(); i++){
            City city = new City(otherState.getAllCities().get(i));
            this.allCities.add(city);
        }

        player = new PlayerInfo(otherState.getPlayer());

        //copy simple board variables
        this.infectionRate = otherState.infectionRate;
        this.numPlayers = otherState.numPlayers;
        this.outbreakNum = otherState.outbreakNum;
        curedDiseases = new int[4];
        for (int i = 0; i < otherState.curedDiseases.length; i++) {
            this.curedDiseases[i] = otherState.curedDiseases[i];
        this.playerTurn = otherState.getPlayerTurn();
        this.numResearchStations = otherState.getNumResearchStations();
        this.isLegal = otherState.isLegal();

        this.numCubesBlue = otherState.numCubesBlue;
        this.numCubesBlack = otherState.numCubesBlack;
        this.numCubesRed = otherState.numCubesRed;
        this.numCubesYellow = otherState.numCubesYellow;
        this.numPlayerCardsInDeck = otherState.numPlayerCardsInDeck;
        this.message = otherState.message;


        this.tappedCity = new City(otherState.getTappedCity());

        }
        //copy players
    }

    //Moves player to a different city
    public boolean movePawn(PlayerInfo player, City currentCity, City desiredCity) {

        //Base Case: Player is trying to move when they have no moves left.
        if (player.getActionsLeft() <= 0) {
            isLegal = false;
            return false;
        }

            //player.setCurrentLocation(desiredCity);
           // player.setActionsLeft(player.getActionsLeft()-1);
           // return true;

        //Drive Case: Move to a city you are connected to.

            for (City c : currentCity.adjacentCities) { //Iterate through adjacent cities of desired city
                if (c == desiredCity) {
                    
                    player.setCurrentLocation(desiredCity);
                    player.actionTaken();
                    isLegal = true;
                    player.setActionsLeft(player.getActionsLeft()-1);
                    return true;
                }
            }

        //Shuttle Flight: Move from a city with a research station to any other city that has a research station.

        if (player.getCurrentLocation().getHasResearchLab() && desiredCity.getHasResearchLab()) {
            player.setCurrentLocation(desiredCity);
            player.actionTaken();
            isLegal = true;
            player.setActionsLeft(player.getActionsLeft()-1);
            return true;
        }

        //Direct Flight Case: Move to a city whose card you have.

            for (Card p : player.getPlayerHand()) {
                if (((PlayerCard)p).getLocation() == desiredCity) {
                    player.setCurrentLocation(desiredCity);
                    discardPlayerCard(player, p);
                    player.actionTaken();
                    isLegal = true;
                    player.setActionsLeft(player.getActionsLeft()-1);
                    return true;
                }
            }

        //Charter Flight Case: Discard the card of the city you are in to move to any city .

            for (Card p : player.getPlayerHand()) {
                if (((PlayerCard)p).getLocation() == player.getCurrentLocation()) {
                    player.setCurrentLocation(desiredCity);
                    discardPlayerCard(player, p);
                    player.actionTaken();
                    isLegal = true;
                    player.setActionsLeft(player.getActionsLeft()-1);
                    return true;
                }
            }




        //If all of these fail (meaning a player clicked on a city they  can't go to), pop
        //up a dialog box that tells them so and lets them continue to make actions.

        //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //Log.e("Move ", "Something went wrong in PandemicGameState>Move");
       // isLegal = false;
        return false;

    }

    //Adds card to player's hand
    public boolean drawPlayerCard(PlayerInfo player) {
        if(player.getPlayerHand().size() >= 7){
            //print message?
            return false;
        }

        else{
            int index = rand.nextInt(playerDeck.size());
            Card card = playerDeck.get(index);

            if(((PlayerCard)card).getisItEpidemic())
            {
                playerDeck.remove(index);
                numPlayerCardsInDeck--;
                infectE(player);
                increaseInfectionRate(player);
                intensify(player);
                return true;
            }
            playerDeck.remove(index);
            numPlayerCardsInDeck--;
            player.addCardToPlayerHand(card);
            //reflect in gui?
            return true;
        }
    }

    //draws card from infection deck and infects city
    public boolean drawInfectionCard() {
        for(int i = 0; i < infectionRate; i++) {
            int index = rand.nextInt(infectionDeck.size());

            InfectionCard c = infectionDeck.get(index);

            if(isDiseaseEradicated(c.diseaseColor)){
                return true;
            }

            //OUTBREAK CHECK
            if(c.getLocation().getDiseaseCubes().size() == 3)
            {
                for(int j = 0; j < c.getLocation().adjacentCities.size(); j++)
                {
                    c.getLocation().getAdjacentCities().get(j).addDiseaseCube(c.getDiseaseColor());
                }
                outbreakNum++;
                this.message = "There's been an outbreak!";
            }

            else {
                c.getLocation().addDiseaseCube(c.getDiseaseColor());
                if (c.getDiseaseColor().equals("Blue")) {
                    numCubesBlue--;
                } else if (c.getDiseaseColor().equals("Black")) {
                    numCubesBlack--;
                } else if (c.getDiseaseColor().equals("Yellow")) {
                    numCubesYellow--;
                } else if (c.getDiseaseColor().equals("Red")) {
                    numCubesRed--;
                }
                infectionDiscardDeck.add(c);
                this.message = c.getLocation() + "has been infected!";
                infectionDeck.remove(c);
            }
        }
        return true;
    }

    //puts a player card in the player discard deck
    public boolean discardPlayerCard(PlayerInfo player,Card gc){
        int index = player.getPlayerHand().indexOf(gc);
        if(index < 0)
        {
            return false;
        }
        Card card = player.getPlayerHand().get(index);
       playerDiscardDeck.add(card);
       player.getPlayerHand().remove(index);
        return true;
    }

    //Adds a research station to a specified city
    public boolean buildAResearchStation(PlayerInfo player, City playerCity) {
        //normal, operations expert

        if (player.getActionsLeft() <= 0 || playerCity.hasResearchLab == true || this.numResearchStations >= 6) {
            this.message = "This move is not legal!";

            return false;
        }
        //Special case for if player role is operations expert
        //Player does not need card of city to build a research station
        if (player.getRole() == 1) {
            player.getCurrentLocation().hasResearchLab = true;

            int index = -1;

            //looping through player hand to search for necessary card
            for (int i = 0; i < player.getPlayerHand().size(); i++) {
                if (((PlayerCard)player.getPlayerHand().get(i)).getLocation() == playerCity) {
                    index = i;
                }
            }

            //required card was not found in player's hand
            if (index == -1) {
                return false;
            }
                player.getCurrentLocation().hasResearchLab = true;
                discardPlayerCard(player, player.getPlayerHand().get(index));

            this.numResearchStations++;
            player.setActionsLeft(player.getActionsLeft() - 1);
            this.message = "You've built a research station!";
            return true;
        }
        return true;
    }

    public void passAction(PlayerInfo player){
        this.message = "You are passing";

        player.actionTaken();
    }

    //removes disease cube(s) at a specified city
    public boolean treatDisease(PlayerInfo player, City city) {
        //normal, medic
        if (player.getActionsLeft() <= 0) {
            this.message = "You have no moves left!";
            return false;
        }
        if (city.getDiseaseCubes().isEmpty()) {
            this.message = "There are no disease cubes to treat here!";
            return false; //no disease cubes there
        }
        if(player.getRole()== 2)
        {
            for(int i = 0; i < city.getDiseaseCubes().size(); i++) {
                city.removeDiseaseCube();
            }
        }

        if (city.getDiseaseCubes().get(0).getCubeColor().equals("Blue")) {
            numCubesBlue++;
        } else if (city.getDiseaseCubes().get(0).getCubeColor().equals("Black")) {
            numCubesBlack++;
        } else if (city.getDiseaseCubes().get(0).getCubeColor().equals("Yellow")) {
            numCubesYellow++;
        } else if (city.getDiseaseCubes().get(0).getCubeColor().equals("Red")) {
            numCubesRed++;
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
            String color = ((PlayerCard)player.getPlayerHand().get(i)).getdiseaseColor();
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
                if(numYellow>=5){
                    curedDiseases[0] = 1;
                }
                if(numRed>=5){
                    curedDiseases[1] = 1;
                }
                if(numBlue>=5){
                    curedDiseases[2] = 1;
                }
                if(numBlack>=5){
                    curedDiseases[3] = 1;
                }
            //Special case for if player role is scientist
            //Then player only needs 3 cards to cure a disease
            if(player.role == 3){
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
            }
        }


        player.setActionsLeft(player.getActionsLeft() - 1);
        return true;
    }

    public boolean infectE(PlayerInfo player){
        InfectionCard card = infectionDeck.get(0);
        infectionDeck.remove(0);
        this.message = card.getLocation() + "is now being infected with 3 disease cubes!";
        if(card.getLocation().getDiseaseCubes().size() == 1)
        {
            card.getLocation().addDiseaseCube(card.getDiseaseColor());
            card.getLocation().addDiseaseCube(card.getDiseaseColor());
            for(int j = 0; j < card.getLocation().adjacentCities.size(); j++)
            {
                card.getLocation().getAdjacentCities().get(j).addDiseaseCube(card.getDiseaseColor());
            }
            outbreakNum++;
            this.message = "OUTBREAK";
            return true;
        }
        else if(card.getLocation().getDiseaseCubes().size() == 2)
        {
            card.getLocation().addDiseaseCube(card.getDiseaseColor());
            for(int j = 0; j < card.getLocation().adjacentCities.size(); j++)
            {
                card.getLocation().getAdjacentCities().get(j).addDiseaseCube(card.getDiseaseColor());
            }
            outbreakNum++;
            this.message = "OUTBREAK";
            return true;
        }
        if(card.getLocation().getDiseaseCubes().size() == 3)
        {
            for(int j = 0; j < card.getLocation().adjacentCities.size(); j++)
            {
                card.getLocation().getAdjacentCities().get(j).addDiseaseCube(card.getDiseaseColor());
            }
            outbreakNum++;
            this.message = "OUTBREAK";
            return true;
        }

        return true;
    }

    //increases the infection rate
    public boolean increaseInfectionRate(PlayerInfo player) {
        if (getInfectionRate() < MAX_INFECTION_RATE) {
            setInfectionRate(getInfectionRate() + 1);
            this.message = "The infection rate goes up by one!";
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
        this.message = "The infection discard deck is reshuffled into the infection deck!";
        return true;
    }

    //trades city card with another player
    //TODO: Will not be implemented for Alpha Release
    public boolean shareKnowledge(PlayerInfo player) {
        //
        if (player.getActionsLeft() <= 0) {
            isLegal = false;
            return false;
        }
        //check if players are both in the same city

        if(players.get(0).currentLocation == players.get(1).currentLocation)
        {
            City city = players.get(0).currentLocation;
            //check if either player has the card of the same city
            //loop through array
            for(Card card: players.get(0).playerHand) {
                if (((PlayerCard)card).getLocation() == city) {
                    //take card/give
                    players.get(0).playerHand.remove(card);
                    players.get(1).playerHand.add(card);
                }
            }
            for(Card card: players.get(1).playerHand) {
                if (((PlayerCard)card).getLocation() == city) {
                    //take card/give
                    players.get(1).playerHand.remove(card);
                    players.get(0).playerHand.add(card);
                }
            }
        }
        player.setActionsLeft(player.getActionsLeft() - 1);
        isLegal = true;
        return true;
    }

    //Activates an event card
    //TODO: Will not be implemented for Alpha Release
    //TODO: Add event cards to the playerDeck
    boolean quietNightCardUsed = false;
    public boolean playEventCard(PlayerInfo player) {

        if (player.getActionsLeft() <= 0) {
            return false;
        }
        for(Card c : player.playerHand)
        {
            if(((EventCard)c).getresilientPopulation())
            {
                //remove one infection card from infectionDiscardDeck
                infectionDiscardDeck.remove(0);
                player.playerHand.remove(c);
                return true;
            }
            else if (((EventCard)c).getquietNight())
            {
                //don't draw infection cards
                quietNightCardUsed = true;
                player.playerHand.remove(c);
                return true;
            }
            else if (((EventCard)c).getforecast())
            {
                //rearrange the top 6 cards of infection deck (shuffle)
                int size = infectionDeck.size();
                if(size < 6)
                {
                    return false;
                }

                ArrayList <InfectionCard> cardsToBeShuffled = new ArrayList<>();
                cardsToBeShuffled.add(infectionDeck.get(size));
                infectionDeck.remove(size);
                cardsToBeShuffled.add(infectionDeck.get(size-1));
                infectionDeck.remove(size-1);
                cardsToBeShuffled.add(infectionDeck.get(size-2));
                infectionDeck.remove(size-2);
                cardsToBeShuffled.add(infectionDeck.get(size-3));
                infectionDeck.remove(size-3);
                cardsToBeShuffled.add(infectionDeck.get(size-4));
                infectionDeck.remove(size-4);
                cardsToBeShuffled.add(infectionDeck.get(size-5));
                infectionDeck.remove(size-5);

                Collections.shuffle(cardsToBeShuffled);

                infectionDeck.addAll(cardsToBeShuffled);
            }

            else if (((EventCard)c).getGovernment() )
            {
            //add a research station to current city
                this.message = "Tap a city to build a research center!";
                City city = getTappedCity();
                if(!city.hasResearchLab)
                {
                    city.hasResearchLab = true;
                }
                player.playerHand.remove(c);
            }
            else if (((EventCard)c).getairlift())
            {
            //move any pawn to any city
            //player.currentCity = touchedCity;
            }
        }
        return false;
    }


    public boolean isDiseaseEradicated(String color){
        //check if the cure has been found for a disease
        //order of curedDisease will be {blue, black, red, yellow}

        if(color.equals("Blue"))
        {
            if(getNumCubesBlue() == 24) {
                curedDiseases[0] = 2;
                this.message = "Blue has been eradicated";
                return true;
            }
        }
        else if(color.equals("Black"))
        {
            if(getNumCubesBlack() == 24) {
                curedDiseases[1] = 2;
                this.message = "Black has been eradicated";
                return true;
            }
        }
        else if(color.equals("Red"))
        {
            if(getNumCubesRed() == 24) {
                curedDiseases[2] = 2;
                this.message = "Red has been eradicated";
                return true;
            }
        }
        else if(color.equals("Yellow"))
        {
            if(getNumCubesYellow() == 24) {
                curedDiseases[3] = 2;
                this.message = "Yellow has been eradicated";
                return true;
            }
        }
        return false;
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




    public City getTappedCity() {return tappedCity;}

    public void setTappedCity(City tappedCity) {
        this.tappedCity = tappedCity;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<InfectionCard> getInfectionDeck() {
        return infectionDeck;
    }

    public ArrayList<InfectionCard> getInfectionDiscardDeck() {
        return infectionDiscardDeck;
    }

    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }

    public ArrayList<Card> getPlayerDiscardDeck() {
        return playerDiscardDeck;
    }

    public ArrayList<City> getAllCities() {
        return allCities;
    }

    public int getMAX_CARDS() {
        return MAX_CARDS;
    }

    public int getMAX_INFECTION_RATE() {
        return MAX_INFECTION_RATE;
    }

    public int getMAX_RESEARCH_STATIONS() {
        return MAX_RESEARCH_STATIONS;
    }

    public int getNumPlayers() {
        return numPlayers;
    }


    public int getNumCubesBlue(){
        return numCubesBlue;
    }

    public void setNumCubesBlue(int n){
        numCubesBlue = n;
    }
    public int getNumCubesBlack(){
        return numCubesBlack;
    }

    public int getNumCubesRed() {
        return numCubesRed;
    }

    public int getNumCubesYellow() {
        return numCubesYellow;
    }

    public int getNumPlayerCardsInDeck() {
        return numPlayerCardsInDeck;
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

    public int getNumResearchStations() {
        return numResearchStations;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public PlayerInfo getPlayer() {
        return player;
    }

    public ArrayList<PlayerInfo> getPlayers() {
        return players;
    }

    public void setPlayer(PlayerInfo player) {
        this.player = player;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setInfectionDeck(ArrayList<InfectionCard> infectionDeck) {
        this.infectionDeck = infectionDeck;
    }

    public void setPlayerDeck(ArrayList<Card> playerDeck) {
        this.playerDeck = playerDeck;
    }

    public void setInfectionDiscardDeck(ArrayList<InfectionCard> infectionDiscardDeck) {
        this.infectionDiscardDeck = infectionDiscardDeck;
    }

    public void setPlayerDiscardDeck(ArrayList<Card> playerDiscardDeck) {
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

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void initStarterPlayerDecks(ArrayList<Card> playerDeck, ArrayList<InfectionCard> InfectionCard){
        String red = "Red";
        String blue = "Blue";
        String black = "Black";
        String yellow = "Yellow";
        String none = "None";

        City epidemic = new City("epidemic");
        City algiers = new City ("algiers");
        City atlanta = new City ("atlanta");
        City baghdad = new City ("baghdad");
        City bangkok = new City ("bangkok");
        City bejing = new City ("beijing");
        City beunosaires = new City ("beunosaires");
        City bogota = new City ("bogota");
        City istanbul = new City ("istanbul");
        City khartoum = new City ("khartoum");
        City hochiminhcity = new City ("hochimincity");
        City riyadh = new City ("riyadh");
        City essen = new City ("essen");
        City washington = new City ("washington");
        City moscow = new City ("moscow");
        City newyork = new City ("newyork");
        City taipei = new City ("taipei");
        City tokyo = new City ("tokyo");
        City tehran = new City ("tehran");
        City jakarta = new City ("jakarta");
        City cairo = new City ("cairo");
        City chennai = new City ("chennai");
        City paris = new City ("pairs");
        City petersburg = new City ("petersburg");
        City saopaulo = new City ("saopaulo");
        City lagos = new City ("lagos");
        City lima = new City ("lima");
        City london = new City ("london");
        City losangeles = new City ("losangeles");
        City delhi = new City ("dehli");
        City johannesburg = new City ("johannesburg");
        City karachi = new City ("karachi");
        City madrid = new City ("madrid");
        City montreal = new City ("montreal");
        City sanfrancisco = new City ("sanfrancisco");
        City kolkata = new City ("kolkata");
        City mexicocity = new City ("mexicocity");
        City santiago = new City ("santiago");
        City sydney = new City ("sydney");
        City mumbai = new City ("mumbai");
        City seoul = new City ("seoul");
        City chicago = new City ("chicago");
        City kinshasa = new City ("kinshasa");
        City miami = new City ("miami");
        City milan = new City ("milan");
        City manila = new City ("manila");
        City shanghai = new City ("shanghai");
        City hongkong = new City ("hongkong");
        City osaka = new City ("osaka");

        allCities = new ArrayList<>();

        algiers.setAdjacentCities(cairo);
        algiers.setAdjacentCities(istanbul);
        algiers.setAdjacentCities(paris);
        algiers.setAdjacentCities(madrid);

        atlanta.setAdjacentCities(chicago);
        atlanta.setAdjacentCities(washington);
        atlanta.setAdjacentCities(miami);

        baghdad.setAdjacentCities(tehran);
        baghdad.setAdjacentCities(karachi);
        baghdad.setAdjacentCities(riyadh);
        baghdad.setAdjacentCities(cairo);
        baghdad.setAdjacentCities(istanbul);

        bangkok.setAdjacentCities(kolkata);
        bangkok.setAdjacentCities(hongkong);
        bangkok.setAdjacentCities(hochiminhcity);
        bangkok.setAdjacentCities(jakarta);
        bangkok.setAdjacentCities(chennai);

        bejing.setAdjacentCities(seoul);
        bejing.setAdjacentCities(shanghai);

        beunosaires.setAdjacentCities(saopaulo);
        beunosaires.setAdjacentCities(bogota);

        bogota.setAdjacentCities(miami);
        bogota.setAdjacentCities(mexicocity);
        bogota.setAdjacentCities(lima);
        bogota.setAdjacentCities(beunosaires);
        bogota.setAdjacentCities(saopaulo);

        istanbul.setAdjacentCities(algiers);
        istanbul.setAdjacentCities(milan);
        istanbul.setAdjacentCities(petersburg);
        istanbul.setAdjacentCities(moscow);
        istanbul.setAdjacentCities(baghdad);
        istanbul.setAdjacentCities(cairo);

        khartoum.setAdjacentCities(cairo);
        khartoum.setAdjacentCities(johannesburg);
        khartoum.setAdjacentCities(kinshasa);
        khartoum.setAdjacentCities(lagos);

        hochiminhcity.setAdjacentCities(manila);
        hochiminhcity.setAdjacentCities(hongkong);
        hochiminhcity.setAdjacentCities(bangkok);
        hochiminhcity.setAdjacentCities(jakarta);

        riyadh.setAdjacentCities(karachi);
        riyadh.setAdjacentCities(baghdad);
        riyadh.setAdjacentCities(cairo);

        essen.setAdjacentCities(petersburg);
        essen.setAdjacentCities(milan);
        essen.setAdjacentCities(paris);
        essen.setAdjacentCities(london);

        washington.setAdjacentCities(newyork);
        washington.setAdjacentCities(montreal);
        washington.setAdjacentCities(atlanta);
        washington.setAdjacentCities(miami);

        moscow.setAdjacentCities(tehran);
        moscow.setAdjacentCities(istanbul);
        moscow.setAdjacentCities(petersburg);

        newyork.setAdjacentCities(montreal);
        newyork.setAdjacentCities(washington);
        newyork.setAdjacentCities(london);
        newyork.setAdjacentCities(madrid);

        taipei.setAdjacentCities(osaka);
        taipei.setAdjacentCities(shanghai);
        taipei.setAdjacentCities(hongkong);
        taipei.setAdjacentCities(manila);

        tokyo.setAdjacentCities(osaka);
        tokyo.setAdjacentCities(seoul);
        tokyo.setAdjacentCities(shanghai);
        tokyo.setAdjacentCities(sanfrancisco);

        tehran.setAdjacentCities(moscow);
        tehran.setAdjacentCities(baghdad);
        tehran.setAdjacentCities(karachi);
        tehran.setAdjacentCities(delhi);

        jakarta.setAdjacentCities(sydney);
        jakarta.setAdjacentCities(hochiminhcity);
        jakarta.setAdjacentCities(bangkok);
        jakarta.setAdjacentCities(chennai);

        cairo.setAdjacentCities(khartoum);
        cairo.setAdjacentCities(riyadh);
        cairo.setAdjacentCities(baghdad);
        cairo.setAdjacentCities(istanbul);
        cairo.setAdjacentCities(algiers);

        chennai.setAdjacentCities(jakarta);
        chennai.setAdjacentCities(bangkok);
        chennai.setAdjacentCities(kolkata);
        chennai.setAdjacentCities(delhi);
        chennai.setAdjacentCities(mumbai);

        paris.setAdjacentCities(essen);
        paris.setAdjacentCities(milan);
        paris.setAdjacentCities(algiers);
        paris.setAdjacentCities(madrid);
        paris.setAdjacentCities(london);

        petersburg.setAdjacentCities(essen);
        petersburg.setAdjacentCities(istanbul);
        petersburg.setAdjacentCities(moscow);

        saopaulo.setAdjacentCities(beunosaires);
        saopaulo.setAdjacentCities(bogota);
        saopaulo.setAdjacentCities(lagos);
        saopaulo.setAdjacentCities(madrid);

        lagos.setAdjacentCities(saopaulo);
        lagos.setAdjacentCities(kinshasa);
        lagos.setAdjacentCities(khartoum);

        lima.setAdjacentCities(santiago);
        lima.setAdjacentCities(bogota);
        lima.setAdjacentCities(mexicocity);

        london.setAdjacentCities(newyork);
        london.setAdjacentCities(madrid);
        london.setAdjacentCities(paris);
        london.setAdjacentCities(essen);

        losangeles.setAdjacentCities(mexicocity);
        losangeles.setAdjacentCities(chicago);
        losangeles.setAdjacentCities(sanfrancisco);
        losangeles.setAdjacentCities(sydney);

        delhi.setAdjacentCities(tehran);
        delhi.setAdjacentCities(karachi);
        delhi.setAdjacentCities(mumbai);
        delhi.setAdjacentCities(chennai);
        delhi.setAdjacentCities(kolkata);

        johannesburg.setAdjacentCities(kinshasa);
        johannesburg.setAdjacentCities(khartoum);

        karachi.setAdjacentCities(riyadh);
        karachi.setAdjacentCities(baghdad);
        karachi.setAdjacentCities(tehran);
        karachi.setAdjacentCities(delhi);
        karachi.setAdjacentCities(mumbai);

        madrid.setAdjacentCities(saopaulo);
        madrid.setAdjacentCities(newyork);
        madrid.setAdjacentCities(london);
        madrid.setAdjacentCities(paris);
        madrid.setAdjacentCities(algiers);

        montreal.setAdjacentCities(chicago);
        montreal.setAdjacentCities(washington);
        montreal.setAdjacentCities(newyork);

        sanfrancisco.setAdjacentCities(chicago);
        sanfrancisco.setAdjacentCities(tokyo);
        sanfrancisco.setAdjacentCities(manila);

        kolkata.setAdjacentCities(delhi);
        kolkata.setAdjacentCities(chennai);
        kolkata.setAdjacentCities(bangkok);
        kolkata.setAdjacentCities(hongkong);

        mexicocity.setAdjacentCities(losangeles);
        mexicocity.setAdjacentCities(miami);
        mexicocity.setAdjacentCities(bogota);
        mexicocity.setAdjacentCities(chicago);
        mexicocity.setAdjacentCities(lima);

        santiago.setAdjacentCities(lima);

        sydney.setAdjacentCities(jakarta);
        sydney.setAdjacentCities(manila);
        sydney.setAdjacentCities(losangeles);

        mumbai.setAdjacentCities(karachi);
        mumbai.setAdjacentCities(delhi);
        mumbai.setAdjacentCities(chennai);

        seoul.setAdjacentCities(bejing);
        seoul.setAdjacentCities(shanghai);
        seoul.setAdjacentCities(tokyo);

        chicago.setAdjacentCities(sanfrancisco);
        chicago.setAdjacentCities(losangeles);
        chicago.setAdjacentCities(mexicocity);
        chicago.setAdjacentCities(atlanta);
        chicago.setAdjacentCities(montreal);

        kinshasa.setAdjacentCities(johannesburg);
        kinshasa.setAdjacentCities(khartoum);
        kinshasa.setAdjacentCities(lagos);

        miami.setAdjacentCities(atlanta);
        miami.setAdjacentCities(washington);
        miami.setAdjacentCities(bogota);
        miami.setAdjacentCities(mexicocity);

        milan.setAdjacentCities(paris);
        milan.setAdjacentCities(essen);
        milan.setAdjacentCities(istanbul);

        manila.setAdjacentCities(hochiminhcity);
        manila.setAdjacentCities(hongkong);
        manila.setAdjacentCities(taipei);
        manila.setAdjacentCities(sydney);
        manila.setAdjacentCities(sanfrancisco);

        shanghai.setAdjacentCities(bejing);
        shanghai.setAdjacentCities(seoul);
        shanghai.setAdjacentCities(tokyo);
        shanghai.setAdjacentCities(taipei);
        shanghai.setAdjacentCities(hongkong);

        hongkong.setAdjacentCities(shanghai);
        hongkong.setAdjacentCities(taipei);
        hongkong.setAdjacentCities(manila);
        hongkong.setAdjacentCities(hochiminhcity);
        hongkong.setAdjacentCities(bangkok);
        hongkong.setAdjacentCities(kolkata);

        osaka.setAdjacentCities(tokyo);
        osaka.setAdjacentCities(taipei);



        //NOTE: some cities are spelled wrong here because there were typos in the image file names
        //DO NOT CHANGE THE CITY NAMES HERE UNTIL THE TYPOS ARE FIXED IN THE FILE NAMES
        PlayerCard algiers_card = new PlayerCard(algiers, black, false, R.drawable.algiers);
        PlayerCard atlanta_card = new PlayerCard(atlanta, blue, false, R.drawable.atlanta);
        PlayerCard baghdad_card = new PlayerCard(baghdad, black, false, R.drawable.baghidad);
        PlayerCard bangkok_card = new PlayerCard(bangkok, red, false, R.drawable.bangkok);
        PlayerCard bejing_card = new PlayerCard(bejing, red, false, R.drawable.bejing);
        PlayerCard beunosaires_card = new PlayerCard(beunosaires, yellow, false, R.drawable.beunosaires);
        PlayerCard bogota_card = new PlayerCard(bogota, yellow, false, R.drawable.bogota);
        PlayerCard istanbul_card = new PlayerCard(istanbul, black, false, R.drawable.istanbul);
        PlayerCard khartoum_card = new PlayerCard(khartoum, yellow, false, R.drawable.khartoum);
        PlayerCard hochiminhcity_card = new PlayerCard(hochiminhcity, red, false, R.drawable.hochiminhcity);
        PlayerCard riyadh_card = new PlayerCard(riyadh, black, false, R.drawable.riyadh);
        PlayerCard essen_card = new PlayerCard(essen, blue, false, R.drawable.essen);
        PlayerCard washington_card = new PlayerCard(washington, blue, false, R.drawable.washington);
        PlayerCard moscow_card = new PlayerCard(moscow, black, false, R.drawable.moscow);
        PlayerCard newyork_card = new PlayerCard(newyork, blue, false, R.drawable.newyork);
        PlayerCard taipei_card = new PlayerCard(taipei, red, false, R.drawable.taipei);
        PlayerCard tokyo_card = new PlayerCard(tokyo, red, false, R.drawable.tokyo);
        PlayerCard tehran_card = new PlayerCard(tehran, black, false, R.drawable.tehran);
        PlayerCard jakarta_card = new PlayerCard(jakarta, red, false, R.drawable.jakarta);
        PlayerCard cairo_card = new PlayerCard(cairo, black, false, R.drawable.cairo);
        PlayerCard chennai_card = new PlayerCard(chennai, black, false, R.drawable.chennai);
        PlayerCard paris_card = new PlayerCard(paris, blue, false, R.drawable.paris);
        PlayerCard petersburg_card = new PlayerCard(petersburg, blue, false, R.drawable.stpetersburg);
        PlayerCard saopaulo_card = new PlayerCard(saopaulo, yellow, false, R.drawable.saopaulo);
        PlayerCard lagos_card = new PlayerCard(lagos, yellow, false, R.drawable.lagos);
        PlayerCard lima_card = new PlayerCard(lima, yellow, false, R.drawable.lima);
        PlayerCard london_card = new PlayerCard(london, blue, false, R.drawable.london);
        PlayerCard losangeles_card = new PlayerCard(losangeles, blue, false, R.drawable.losangeles);
        PlayerCard delhi_card = new PlayerCard(delhi, black, false, R.drawable.delhi);
        PlayerCard johannesburg_card = new PlayerCard(johannesburg, yellow, false, R.drawable.johannesburg);
        PlayerCard karachi_card = new PlayerCard(karachi, black, false, R.drawable.karachi);
        PlayerCard madrid_card = new PlayerCard(madrid, yellow, false, R.drawable.madrid);
        PlayerCard montreal_card = new PlayerCard(montreal, blue, false, R.drawable.montreal);
        PlayerCard sanfrancisco_card = new PlayerCard(sanfrancisco, blue, false, R.drawable.sanfrancisco);
        PlayerCard kolkata_card = new PlayerCard(kolkata, black, false, R.drawable.kolkata);
        PlayerCard mexicocity_card = new PlayerCard(mexicocity, yellow, false, R.drawable.mexicocity);
        PlayerCard santiago_card = new PlayerCard(santiago, yellow, false, R.drawable.santiago);
        PlayerCard sydney_card = new PlayerCard(sydney, red, false, R.drawable.sydney);
        PlayerCard mumbai_card = new PlayerCard(mumbai, black, false, R.drawable.mumbai);
        PlayerCard seoul_card = new PlayerCard(seoul, red, false, R.drawable.seoul);
        PlayerCard chicago_card = new PlayerCard(chicago, blue, false, R.drawable.chicago);
        PlayerCard kinshasa_card = new PlayerCard(kinshasa, yellow, false, R.drawable.kinshasa);
        PlayerCard miami_card = new PlayerCard(miami, yellow, false, R.drawable.miami);
        PlayerCard milan_card = new PlayerCard(milan, blue, false, R.drawable.milan);
        PlayerCard manila_card = new PlayerCard(manila, red, false, R.drawable.manila);
        PlayerCard shanghai_card = new PlayerCard(shanghai, red, false, R.drawable.shanghai);
        PlayerCard hongkong_card = new PlayerCard(hongkong, red, false, R.drawable.hongkong);
        PlayerCard osaka_card = new PlayerCard(osaka, red, false, R.drawable.osaka);
        PlayerCard epidemic_1 = new PlayerCard(epidemic, none, true, R.drawable.epidemic);
        PlayerCard epidemic_2 = new PlayerCard(epidemic, none, true, R.drawable.epidemic);
        PlayerCard epidemic_3 = new PlayerCard(epidemic, none, true, R.drawable.epidemic);
        PlayerCard epidemic_4 = new PlayerCard(epidemic, none, true, R.drawable.epidemic);


        playerDeck.add(algiers_card);
        playerDeck.add(atlanta_card);
        playerDeck.add(baghdad_card);
        playerDeck.add(bangkok_card);
        playerDeck.add(bejing_card);
        playerDeck.add(beunosaires_card);
        playerDeck.add(bogota_card);
        playerDeck.add(istanbul_card);
        playerDeck.add(khartoum_card);
        playerDeck.add(hochiminhcity_card);
        playerDeck.add(riyadh_card);
        playerDeck.add(essen_card);
        playerDeck.add(washington_card);
        playerDeck.add(moscow_card);
        playerDeck.add(newyork_card);
        playerDeck.add(taipei_card);
        playerDeck.add(tokyo_card);
        playerDeck.add(tehran_card);
        playerDeck.add(jakarta_card);
        playerDeck.add(cairo_card);
        playerDeck.add(chennai_card);
        playerDeck.add(paris_card);
        playerDeck.add(petersburg_card);
        playerDeck.add(saopaulo_card);
        playerDeck.add(lagos_card);
        playerDeck.add(lima_card);
        playerDeck.add(london_card);
        playerDeck.add(losangeles_card);
        playerDeck.add(delhi_card);
        playerDeck.add(johannesburg_card);
        playerDeck.add(karachi_card);
        playerDeck.add(madrid_card);
        playerDeck.add(montreal_card);
        playerDeck.add(sanfrancisco_card);
        playerDeck.add(kolkata_card);
        playerDeck.add(mexicocity_card);
        playerDeck.add(santiago_card);
        playerDeck.add(sydney_card);
        playerDeck.add(mumbai_card);
        playerDeck.add(seoul_card);
        playerDeck.add(chicago_card);
        playerDeck.add(kinshasa_card);
        playerDeck.add(miami_card);
        playerDeck.add(milan_card);
        playerDeck.add(manila_card);
        playerDeck.add(shanghai_card);
        playerDeck.add(hongkong_card);
        playerDeck.add(osaka_card);
        playerDeck.add(epidemic_1);
        playerDeck.add(epidemic_2);
        playerDeck.add(epidemic_3);
        playerDeck.add(epidemic_4);

/*
        Card resilient_pop_card = new EventCard(true, true, false, false, false, false);
        Card quiet_night_card = new EventCard(true, false, true, false, false, false);
        Card forecast_card = new EventCard(true, false, false, true, false, false);
        Card government_grant_card = new EventCard(true, false, false, false, true, false);
        Card airlift_card = new EventCard(true, false, false, false, false, true);

        playerDeck.add(resilient_pop_card);
        playerDeck.add(quiet_night_card);
        playerDeck.add(forecast_card);
        playerDeck.add(government_grant_card);
        playerDeck.add(airlift_card);



*/
        InfectionCard algiers_infection = new InfectionCard(algiers, black, R.drawable.algiers_i);
        InfectionCard atlanta_infection = new InfectionCard(atlanta, blue, R.drawable.atlanta_i);
        InfectionCard baghdad_infection = new InfectionCard(baghdad, black, R.drawable.baghdad_i);
        InfectionCard bangkok_infection = new InfectionCard(bangkok, red, R.drawable.bangkok_i);
        InfectionCard bejing_infection = new InfectionCard(bejing, red, R.drawable.bejing_i);
        InfectionCard beunosaires_infection = new InfectionCard(beunosaires, yellow, R.drawable.beunosaires);
        InfectionCard bogota_infection = new InfectionCard(bogota, yellow, R.drawable.bogota_i);
        InfectionCard istanbul_infection = new InfectionCard(istanbul, black, R.drawable.istanbul_i);
        InfectionCard khartoum_infection = new InfectionCard(khartoum, yellow, R.drawable.khartoum_i);
        InfectionCard hochiminhcity_infection = new InfectionCard(hochiminhcity, red, R.drawable.hochiminh_i);
        InfectionCard riyadh_infection = new InfectionCard(riyadh, black, R.drawable.riyadhi);
        InfectionCard essen_infection = new InfectionCard(essen, blue, R.drawable.essen_i);
        InfectionCard washington_infection = new InfectionCard(washington, blue, R.drawable.washington_i);
        InfectionCard moscow_infection = new InfectionCard(moscow, black, R.drawable.moscow_i);
        InfectionCard newyork_infection = new InfectionCard(newyork, blue, R.drawable.newyork_i);
        InfectionCard taipei_infection = new InfectionCard(taipei, red, R.drawable.taipei_i);
        InfectionCard tokyo_infection = new InfectionCard(tokyo, red, R.drawable.tokyo_i);
        InfectionCard tehran_infection = new InfectionCard(tehran, black, R.drawable.tehran_i);
        InfectionCard jakarta_infection = new InfectionCard(jakarta, red, R.drawable.jakarta_i);
        InfectionCard cairo_infection = new InfectionCard(cairo, black, R.drawable.cairo_i);
        InfectionCard chennai_infection = new InfectionCard(chennai, black, R.drawable.chennai_i);
        InfectionCard paris_infection = new InfectionCard(paris, blue, R.drawable.paris_i);
        InfectionCard petersburg_infection = new InfectionCard(petersburg, blue, R.drawable.petersburg_i);
        InfectionCard saopaulo_infection = new InfectionCard(saopaulo, yellow, R.drawable.saopaulo_i);
        InfectionCard lagos_infection = new InfectionCard(lagos, yellow, R.drawable.lagos_i);
        InfectionCard lima_infection = new InfectionCard(lima, yellow, R.drawable.lima_i);
        InfectionCard london_infection = new InfectionCard(london, blue, R.drawable.london_i);
        InfectionCard losangeles_infection = new InfectionCard(losangeles, blue, R.drawable.losangeles_i);
        InfectionCard delhi_infection = new InfectionCard(delhi, black, R.drawable.delhi_i);
        InfectionCard johannesburg_infection = new InfectionCard(johannesburg, yellow, R.drawable.johannesburg_i);
        InfectionCard karachi_infection = new InfectionCard(karachi, black, R.drawable.karachi_i);
        InfectionCard madrid_infection = new InfectionCard(madrid, yellow, R.drawable.madrid_i);
        InfectionCard montreal_infection = new InfectionCard(montreal, blue, R.drawable.montreal_i);
        InfectionCard sanfrancisco_infection = new InfectionCard(sanfrancisco, blue, R.drawable.sanfrancisco_i);
        InfectionCard kolkata_infection = new InfectionCard(kolkata, black, R.drawable.kolkata_i);
        InfectionCard mexicocity_infection = new InfectionCard(mexicocity, yellow, R.drawable.mexicocity_i);
        InfectionCard santiago_infection = new InfectionCard(santiago, yellow, R.drawable.santiago_i);
        InfectionCard sydney_infection = new InfectionCard(sydney, red, R.drawable.sydeny_i);
        InfectionCard mumbai_infection = new InfectionCard(mumbai, black, R.drawable.mumbai_i);
        InfectionCard seoul_infection = new InfectionCard(seoul, red, R.drawable.seoul_i);
        InfectionCard chicago_infection = new InfectionCard(chicago, blue, R.drawable.chicago_i);
        InfectionCard kinshasa_infection = new InfectionCard(kinshasa, yellow, R.drawable.kinshasa_i);
        InfectionCard miami_infection = new InfectionCard(miami, yellow, R.drawable.miami);
        InfectionCard milan_infection = new InfectionCard(milan, blue, R.drawable.milan_i);
        InfectionCard manila_infection = new InfectionCard(manila, red, R.drawable.manila_i);
        InfectionCard shanghai_infection = new InfectionCard(shanghai, red, R.drawable.shanghai_i);
        InfectionCard hongkong_infection = new InfectionCard(hongkong, red, R.drawable.hongkong_i);
        InfectionCard osaka_infection = new InfectionCard(osaka, red, R.drawable.osaka_i);

        InfectionCard.add(algiers_infection);
        InfectionCard.add(atlanta_infection);
        InfectionCard.add(baghdad_infection);
        InfectionCard.add(bangkok_infection);
        InfectionCard.add(bejing_infection);
        InfectionCard.add(beunosaires_infection);
        InfectionCard.add(bogota_infection);
        InfectionCard.add(istanbul_infection);
        InfectionCard.add(khartoum_infection);
        InfectionCard.add(hochiminhcity_infection);
        InfectionCard.add(riyadh_infection);
        InfectionCard.add(essen_infection);
        InfectionCard.add(washington_infection);
        InfectionCard.add(moscow_infection);
        InfectionCard.add(newyork_infection);
        InfectionCard.add(taipei_infection);
        InfectionCard.add(tokyo_infection);
        InfectionCard.add(tehran_infection);
        InfectionCard.add(jakarta_infection);
        InfectionCard.add(cairo_infection);
        InfectionCard.add(chennai_infection);
        InfectionCard.add(paris_infection);
        InfectionCard.add(petersburg_infection);
        InfectionCard.add(saopaulo_infection);
        InfectionCard.add(lagos_infection);
        InfectionCard.add(lima_infection);
        InfectionCard.add(london_infection);
        InfectionCard.add(losangeles_infection);
        InfectionCard.add(delhi_infection);
        InfectionCard.add(johannesburg_infection);
        InfectionCard.add(karachi_infection);
        InfectionCard.add(madrid_infection);
        InfectionCard.add(montreal_infection);
        InfectionCard.add(sanfrancisco_infection);
        InfectionCard.add(kolkata_infection);
        InfectionCard.add(mexicocity_infection);
        InfectionCard.add(santiago_infection);
        InfectionCard.add(sydney_infection);
        InfectionCard.add(mumbai_infection);
        InfectionCard.add(seoul_infection);
        InfectionCard.add(chicago_infection);
        InfectionCard.add(kinshasa_infection);
        InfectionCard.add(miami_infection);
        InfectionCard.add(milan_infection);
        InfectionCard.add(manila_infection);
        InfectionCard.add(shanghai_infection);
        InfectionCard.add(hongkong_infection);
        InfectionCard.add(osaka_infection);

        allCities.add(algiers);//0
        allCities.add(atlanta);//1
        allCities.add(baghdad);//2
        allCities.add(bangkok);//3
        allCities.add(bejing);//4
        allCities.add(beunosaires);//5
        allCities.add(bogota);//6
        allCities.add(istanbul);//7
        allCities.add(khartoum);//8
        allCities.add(hochiminhcity);//9
        allCities.add(riyadh);//10
        allCities.add(essen);//11
        allCities.add(washington);//12
        allCities.add(moscow);//13
        allCities.add(newyork);//14
        allCities.add(taipei);//15
        allCities.add(tokyo);//16
        allCities.add(tehran);//17
        allCities.add(jakarta);//18
        allCities.add(cairo);//19
        allCities.add(chennai);//20
        allCities.add(paris);//21
        allCities.add(petersburg);//22
        allCities.add(saopaulo);//23
        allCities.add(lagos);//24
        allCities.add(lima);//25
        allCities.add(london);//26
        allCities.add(losangeles);//27
        allCities.add(delhi);//28
        allCities.add(johannesburg);//29
        allCities.add(karachi);//30
        allCities.add(madrid);//31
        allCities.add(montreal);//32
        allCities.add(sanfrancisco);//33
        allCities.add(kolkata);//34
        allCities.add(mexicocity);//35
        allCities.add(santiago);//36
        allCities.add(sydney);//37
        allCities.add(mumbai);//38
        allCities.add(seoul);//39
        allCities.add(chicago);//40
        allCities.add(kinshasa);//41
        allCities.add(miami);//42
        allCities.add(milan);//43
        allCities.add(manila);//44
        allCities.add(shanghai);//45
        allCities.add(hongkong);//46
        allCities.add(osaka);//47
        allCities.get(1).addDiseaseCube("Blue");
        allCities.get(15).addDiseaseCube("Blue");
        allCities.get(1).addDiseaseCube("Blue");
        allCities.get(15).addDiseaseCube("Blue");
        allCities.get(12).addDiseaseCube("Blue");
        allCities.get(12).addDiseaseCube("Blue");
        allCities.get(40).addDiseaseCube("Blue");
        allCities.get(40).addDiseaseCube("Blue");
        allCities.get(4).addDiseaseCube("Blue");
        allCities.get(14).addDiseaseCube("Blue");
        numCubesBlue = numCubesBlue-10;


    }
}

