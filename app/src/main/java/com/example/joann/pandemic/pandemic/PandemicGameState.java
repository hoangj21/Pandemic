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

import com.example.joann.pandemic.R;
import com.example.joann.pandemic.game.infoMsg.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.example.joann.pandemic.pandemic.EventCard.initEventCard;
import static com.example.joann.pandemic.pandemic.PlayerCard.initStarterPlayerDecks;

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
    private ArrayList<PlayerCard> playerDeck;
    private ArrayList<InfectionCard> infectionDeck;
    private ArrayList<PlayerCard> playerDiscardDeck;
    private ArrayList<InfectionCard> infectionDiscardDeck;
    private ArrayList<City> allCities;

    private int numPlayers;
    private int infectionRate;
    private int outbreakNum;
    private int[] curedDiseases;
    private int playerTurn;
    private int numResearchStations;
    private PlayerInfo player;

    private final int MAX_CARDS = 7;
    private final int MAX_INFECTION_RATE = 4;
    private final int MAX_RESEARCH_STATIONS = 6;


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
        playerTurn = 0;
        numResearchStations = 0;

        //TODO
        //initializing a player info object with temp values, change later
        City city = new City();
        int index = rand.nextInt(playerDeck.size());
        PlayerCard card1 = playerDeck.get(index);

        index = rand.nextInt(playerDeck.size());
        PlayerCard card2 = playerDeck.get(index);

        index = rand.nextInt(playerDeck.size());
        PlayerCard card3 = playerDeck.get(index);

        index = rand.nextInt(playerDeck.size());
        PlayerCard card4 = playerDeck.get(index);
        player = new PlayerInfo(0, 0, 4, city, card1, card2,card3, card4);

        init();
    }
    private void init(){
        initStarterPlayerDecks(playerDeck, infectionDeck);
        initEventCard();
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
        this.playerTurn = otherState.getPlayerTurn();
        this.numResearchStations = otherState.getNumResearchStations();
        }

        //copy players


    }

    //Moves player to a different city
    public boolean movePawn(PlayerInfo player, City currentCity, City desiredCity, int moveType) {

        //Base Case: Player is trying to move when they have no moves left.
        if (player.getActionsLeft() <= 0) {
            return false;
        }

        //Drive Case: Move to a city you are connected to.
        if (moveType == 0) {
            for (City c : desiredCity.adjacentCities) { //Iterate through adjacent cities of desired city
                if (c == currentCity) {
                    player.setCurrentLocation(desiredCity);
                    player.actionTaken();
                    return true;
                }
            }
        }

        //Direct Flight Case: Move to a city whose card you have.
        if (moveType == 1) {
            for (PlayerCard p : player.getPlayerHand()) {
                if (p.getLocation() == desiredCity) {
                    player.setCurrentLocation(desiredCity);
                    discardPlayerCard(player, p);
                    player.actionTaken();
                    return true;
                }
            }
        }
        //Charter Flight Case: Discard the card of the city you are in to move to any city .
        if (moveType == 2) {
            for (PlayerCard p : player.getPlayerHand()) {
                if (p.getLocation() == player.getCurrentLocation()) {
                    player.setCurrentLocation(desiredCity);
                    discardPlayerCard(player, p);
                    player.actionTaken();
                    return true;
                }
            }
        }

        //Shuttle Flight: Move from a city with a research station to any other city that has a research station.
        if (moveType == 3) {
            if (player.getCurrentLocation().getHasResearchLab() && desiredCity.getHasResearchLab()) {
                player.setCurrentLocation(desiredCity);
                player.actionTaken();
                return true;
            }
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
    public boolean discardPlayerCard(PlayerInfo player,PlayerCard gc){
        int index = player.getPlayerHand().indexOf(gc);
       PlayerCard card = player.getPlayerHand().get(index);
       playerDiscardDeck.add(card);
       player.getPlayerHand().remove(index);
        return true;
    }

    //Adds a research station to a specified city
    public boolean buildAResearchStation(PlayerInfo player, City playerCity, PlayerCard gc) {
        //normal, operations expert

        if (player.getActionsLeft() <= 0 || playerCity.hasResearchLab == true || this.numResearchStations>=6) {
            return false;
        }

        if(player.getRole() == 2){
            player.getCurrentLocation().hasResearchLab = true;
        }else if( player.getCurrentLocation() == gc.getLocation()){
            player.getCurrentLocation().hasResearchLab = true;
            discardPlayerCard(player, gc);
        }
        this.numResearchStations++;
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
            if(player.role == 2){
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
    //TODO: Will not be implemented for Alpha Release
    public boolean shareKnowledge(PlayerInfo player) {
        //normal, researcher
        if (player.getActionsLeft() <= 0) {
            return false;
        }
        player.setActionsLeft(player.getActionsLeft() - 1);
        return true;
    }

    //Activates an event card
    //TODO: Will not be implemented for Alpha Release
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


    public ArrayList<InfectionCard> getInfectionDeck() {
        return infectionDeck;
    }

    public ArrayList<InfectionCard> getInfectionDiscardDeck() {
        return infectionDiscardDeck;
    }

    public ArrayList<PlayerCard> getPlayerDeck() {
        return playerDeck;
    }

    public ArrayList<PlayerCard> getPlayerDiscardDeck() {
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

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void initStarterPlayerDecks(ArrayList<PlayerCard> playerDeck, ArrayList<InfectionCard> InfectionCard){
        String red = "Red";
        String blue = "Blue";
        String black = "Black";
        String yellow = "Yellow";
        String none = "None";

        City epidemic = new City();
        City algiers = new City ();
        City atlanta = new City ();
        City baghdad = new City ();
        City bangkok = new City ();
        City bejing = new City ();
        City beunosaires = new City ();
        City bogota = new City ();
        City istanbul = new City ();
        City khartoum = new City ();
        City hochiminhcity = new City ();
        City riyadh = new City ();
        City essen = new City ();
        City washington = new City ();
        City moscow = new City ();
        City newyork = new City ();
        City taipei = new City ();
        City tokyo = new City ();
        City tehran = new City ();
        City jakarta = new City ();
        City cairo = new City ();
        City chennai = new City ();
        City paris = new City ();
        City petersburg = new City ();
        City saopaulo = new City ();
        City lagos = new City ();
        City lima = new City ();
        City london = new City ();
        City losangeles = new City ();
        City delhi = new City ();
        City johannesburg = new City ();
        City karachi = new City ();
        City madrid = new City ();
        City montreal = new City ();
        City sanfrancisco = new City ();
        City kolkata = new City ();
        City mexicocity = new City ();
        City santiago = new City ();
        City sydney = new City ();
        City mumbai = new City ();
        City seoul = new City ();
        City chicago = new City ();
        City kinshasa = new City ();
        City miami = new City ();
        City milan = new City ();
        City manila = new City ();
        City shanghai = new City ();
        City hongkong = new City ();
        City osaka = new City ();

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




        PlayerCard algiers_card = new PlayerCard(algiers, black, false, R.drawable.algiers);
        PlayerCard atlanta_card = new PlayerCard(atlanta, blue, false, R.drawable.atlanta);
        PlayerCard baghdad_card = new PlayerCard(baghdad, black, false, R.drawable.baghidad);
        PlayerCard bangkok_card = new PlayerCard(bangkok, red, false, R.drawable.bangkok);
        PlayerCard bejing_card = new PlayerCard(bejing, red, false, R.drawable.bejing);
        PlayerCard beunosaires_card = new PlayerCard(beunosaires, yellow, false, R.drawable.beunosaires);
        PlayerCard bogota_card = new PlayerCard(bogota, yellow, false, R.drawable.bobota);
        PlayerCard istanbul_card = new PlayerCard(istanbul, black, false, R.drawable.instanbul);
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
        PlayerCard sanfrancisco_card = new PlayerCard(sanfrancisco, blue, false, R.drawable.sanfranscisco);
        PlayerCard kolkata_card = new PlayerCard(kolkata, black, false, R.drawable.kolkata);
        PlayerCard mexicocity_card = new PlayerCard(mexicocity, yellow, false, R.drawable.mexicocity);
        PlayerCard santiago_card = new PlayerCard(santiago, yellow, false, R.drawable.santiago);
        PlayerCard sydney_card = new PlayerCard(sydney, red, false, R.drawable.sydney);
        PlayerCard mumbai_card = new PlayerCard(mumbai, black, false, R.drawable.mumbai);
        PlayerCard seoul_card = new PlayerCard(seoul, red, false, R.drawable.seoul);
        PlayerCard chicago_card = new PlayerCard(chicago, blue, false, R.drawable.chicago);
        PlayerCard kinshasa_card = new PlayerCard(kinshasa, yellow, false, R.drawable.kinghasa);
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
        InfectionCard montreal_infection = new InfectionCard(montreal, blue, R.drawable.monteal_i);
        InfectionCard sanfrancisco_infection = new InfectionCard(sanfrancisco, blue, R.drawable.sanfrancsicoi_i);
        InfectionCard kolkata_infection = new InfectionCard(kolkata, black, R.drawable.kolkata_i);
        InfectionCard mexicocity_infection = new InfectionCard(mexicocity, yellow, R.drawable.mexicocity_i);
        InfectionCard santiago_infection = new InfectionCard(santiago, yellow, R.drawable.santiago_i);
        InfectionCard sydney_infection = new InfectionCard(sydney, red, R.drawable.sydeny_i);
        InfectionCard mumbai_infection = new InfectionCard(mumbai, black, R.drawable.mumbai_i);
        InfectionCard seoul_infection = new InfectionCard(seoul, red, R.drawable.sequl_i);
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

        allCities.add(algiers);
        allCities.add(atlanta);
        allCities.add(baghdad);
        allCities.add(bangkok);
        allCities.add(bejing);
        allCities.add(beunosaires);
        allCities.add(bogota);
        allCities.add(istanbul);
        allCities.add(khartoum);
        allCities.add(hochiminhcity);
        allCities.add(riyadh);
        allCities.add(essen);
        allCities.add(washington);
        allCities.add(moscow);
        allCities.add(newyork);
        allCities.add(taipei);
        allCities.add(tokyo);
        allCities.add(tehran);
        allCities.add(jakarta);
        allCities.add(cairo);
        allCities.add(chennai);
        allCities.add(paris);
        allCities.add(petersburg);
        allCities.add(saopaulo);
        allCities.add(lagos);
        allCities.add(lima);
        allCities.add(london);
        allCities.add(losangeles);
        allCities.add(delhi);
        allCities.add(johannesburg);
        allCities.add(karachi);
        allCities.add(madrid);
        allCities.add(montreal);
        allCities.add(sanfrancisco);
        allCities.add(kolkata);
        allCities.add(mexicocity);
        allCities.add(santiago);
        allCities.add(sydney);
        allCities.add(mumbai);
        allCities.add(seoul);
        allCities.add(chicago);
        allCities.add(kinshasa);
        allCities.add(miami);
        allCities.add(milan);
        allCities.add(manila);
        allCities.add(shanghai);
        allCities.add(hongkong);
        allCities.add(osaka);

    }
}

