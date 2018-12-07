package com.example.joann.pandemic;

import android.util.Log;

import com.example.joann.pandemic.pandemic.Card;
import com.example.joann.pandemic.pandemic.City;
import com.example.joann.pandemic.pandemic.InfectionCard;
import com.example.joann.pandemic.pandemic.PandemicComputerPlayerDumb;
import com.example.joann.pandemic.pandemic.PandemicGameState;
import com.example.joann.pandemic.pandemic.PlayerCard;
import com.example.joann.pandemic.pandemic.PlayerInfo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    //Tests if eradication test works properly
    // -Polina G.
    @Test
    public void testIsDiseaseEradicated(){

        PandemicGameState state = new PandemicGameState();

        //check to make sure disease are not eradicated at the start
        assertFalse(state.isDiseaseEradicated("Black"));

        //check to make sure disease are not eradicated at the start
        assertFalse(state.isDiseaseEradicated("Red"));

        //check to make sure disease are not eradicated at the start
        assertFalse(state.isDiseaseEradicated("Yellow"));

        //make sure its not only based on number of blue cubes
        state.setNumCubesBlue(24);
        assertFalse(state.isDiseaseEradicated("Blue"));

        //cure blue
        int [] cure1 = new int []{0,0,1,0};
        state.setCuredDiseases(cure1);

        //check if eradication works
        assertTrue(state.isDiseaseEradicated("Blue"));

    }

    //Tests if player can properly discard a card from their hand
    // -Polina G.
    @Test
    public void testPlayerCardDiscarding()
    {
        PandemicGameState state = new PandemicGameState();
        state.getPlayer().getPlayerHand().removeAll(state.getPlayer().getPlayerHand());

        City london = state.getAllCities().get(26);
        City paris = state.getAllCities().get(21);
        City stPetersburg = state.getAllCities().get(22);


        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        PlayerCard card3 = new PlayerCard(stPetersburg, "blue", false, R.drawable.stpetersburg);


        state.getPlayer().addCardToPlayerHand(card1);
        state.getPlayer().addCardToPlayerHand(card2);

        assertEquals(state.getPlayer().getPlayerHand().size(), 2);

        state.getPlayer().addCardToPlayerHand(card3);
        assertEquals(state.getPlayer().getPlayerHand().size(), 3);


        assertTrue(state.discardPlayerCard(state.getPlayer(), card1));
        assertEquals(state.getPlayer().getPlayerHand().size(), 2);

        assertTrue(state.discardPlayerCard(state.getPlayer(), card2));
        assertEquals(state.getPlayer().getPlayerHand().size(), 1);

        assertTrue(state.discardPlayerCard(state.getPlayer(), card3));
        assertEquals(state.getPlayer().getPlayerHand().size(), 0);

        assertFalse(state.discardPlayerCard(state.getPlayer(), card1));
    }

    //tests the gamestate copy constructor
    // -Polina G.
    @Test
    public void testGameStateCp(){
        PandemicGameState instance = new PandemicGameState();
        PandemicGameState instance2 = new PandemicGameState(instance);

        assertEquals(instance.getInfectionDiscardDeck(), instance2.getInfectionDiscardDeck());
        assertEquals(instance.getNumResearchStations(), instance2.getNumResearchStations());
        assertEquals(instance.getPlayerDiscardDeck(), instance2.getPlayerDiscardDeck());
    }

    //Tests if pass function works
    // -Sarah S.
    @Test
    public void testPass(){
        PandemicGameState state = new PandemicGameState();

        assertEquals(4, state.getPlayer().getActionsLeft());
        state.passAction(state.getPlayer());
        assertEquals(3, state.getPlayer().getActionsLeft());
    }

    @Test
    public void testCureFunction(){

        PandemicGameState state = new PandemicGameState();

        //player starts with 4 cards so cure should not work
        state.discoverACure(state.getPlayer());
        assertEquals(state.getCuredDiseases()[0], 0);
        assertEquals(state.getCuredDiseases()[1], 0);
        assertEquals(state.getCuredDiseases()[2], 0);
        assertEquals(state.getCuredDiseases()[3], 0);

        //so that player can have 5 blue cards in their deck
        state.getPlayer().getPlayerHand().remove(1);
        state.getPlayer().getPlayerHand().remove(0);

        //inserting the cards needed
        City london = state.getAllCities().get(26);
        City paris = state.getAllCities().get(21);
        City stPetersburg = state.getAllCities().get(22);
        City washington = state.getAllCities().get(12);
        City chicago = state.getAllCities().get(40);

        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        PlayerCard card3 = new PlayerCard(stPetersburg, "blue", false, R.drawable.stpetersburg);
        PlayerCard card4 = new PlayerCard(washington, "blue", false, R.drawable.washington);
        PlayerCard card5 = new PlayerCard(chicago, "blue", false, R.drawable.chicago);

        state.getPlayer().getPlayerHand().add(card1);
        state.getPlayer().getPlayerHand().add(card2);
        state.getPlayer().getPlayerHand().add(card3);
        state.getPlayer().getPlayerHand().add(card4);
        state.getPlayer().getPlayerHand().add(card5);

        //check to that cure was discovered
        assertTrue(state.discoverACure(state.getPlayer()));

    }

    //tests player hand to make sure it hold and handle cards
    // -Kelsi C.
    @Test
    public void testPlayerHand(){

        PandemicGameState state = new PandemicGameState();

        City london = state.getAllCities().get(26);
        City paris = state.getAllCities().get(21);
        City stPetersburg = state.getAllCities().get(22);
        City washington = state.getAllCities().get(12);
        City chicago = state.getAllCities().get(40);
        City newYork = state.getAllCities().get(14);
        City montreal = state.getAllCities().get(32);
        City essen = state.getAllCities().get(11);

        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        PlayerCard card3 = new PlayerCard(stPetersburg, "blue", false, R.drawable.stpetersburg);
        PlayerCard card4 = new PlayerCard(washington, "blue", false, R.drawable.washington);
        PlayerCard card5 = new PlayerCard(chicago, "blue", false, R.drawable.chicago);
        PlayerCard card6 = new PlayerCard(newYork, "blue", false, R.drawable.newyork);
        PlayerCard card7 = new PlayerCard(montreal, "blue", false, R.drawable.montreal);
        PlayerCard card8 = new PlayerCard(essen, "blue", false, R.drawable.essen);

        assertTrue(state.getPlayer().addCardToPlayerHand(card1));
        state.getPlayer().addCardToPlayerHand(card2);
        state.getPlayer().addCardToPlayerHand(card3);
        state.getPlayer().addCardToPlayerHand(card4);
        state.getPlayer().addCardToPlayerHand(card5);
        state.getPlayer().addCardToPlayerHand(card6);
        state.getPlayer().addCardToPlayerHand(card7);
        assertEquals(7, state.getPlayer().getPlayerHand().size());

        state.getPlayer().addCardToPlayerHand(card8);
        assertEquals(7, state.getPlayer().getPlayerHand().size());

    }

    //tests if treat disease function works properly
    // -Sarah S.
    @Test
    public void testTreatDisease() {
        //default location is Atlanta
        PandemicGameState state = new PandemicGameState();
        City atlanta = state.getAllCities().get(1);
        state.getPlayer().setPlayerNumber(3);

        //check to make sure a disease cube is removed from city
        assertTrue(state.treatDisease(state.getPlayer(),state.getPlayer().getCurrentLocation()));
        assertEquals(state.getPlayer().getCurrentLocation().getDiseaseCubes().size(), atlanta.getDiseaseCubes().size());

        //check to make sure if no disease cubes are left, nothing happens
        assertFalse(state.treatDisease(state.getPlayer(),state.getPlayer().getCurrentLocation()));
        assertEquals(state.getPlayer().getCurrentLocation().getDiseaseCubes().size(), atlanta.getDiseaseCubes().size());
        assertEquals(state.getPlayer().getCurrentLocation().getDiseaseCubes().size(), 0);
    }

    //tests movement by discarding a card (aka flight)
    // -Joanna H.
    @Test
    public void testMovePawn1()
    {
        //default location is Atlanta
        PandemicGameState state = new PandemicGameState();

        //checks if player can move to a location by discarding card from player hand (atlanta -> player card in index 1)
        City atlanta = state.getAllCities().get(1);
        City london = state.getAllCities().get(26);
        PlayerCard p = new PlayerCard(london, "blue", false, R.drawable.london);
        state.getPlayer().addCardToPlayerHand(p);

        assertTrue(state.movePawn(state.getPlayer(),atlanta,(p).getLocation()));
        assertEquals(state.getPlayer().getCurrentLocation(),(p).getLocation());

        //check so that player can't move to another city if no card in deck (reset to atlanta)
        state.getPlayer().setCurrentLocation(atlanta);
        state.getPlayer().getPlayerHand().removeAll(state.getPlayer().getPlayerHand());
        assertFalse(state.movePawn(state.getPlayer(),atlanta,(p).getLocation()));
        assertEquals(state.getPlayer().getCurrentLocation(), atlanta);

    }

    // tests research center movement:  current city has research center == desired city has researched center
    // -Kelsi C.
    @Test
    public void testMovePawn2()
    {
        //default location is Atlanta (Only Atlanta begins with a research center)
        PandemicGameState state = new PandemicGameState();
        state.getPlayer().setPlayerNumber(1);
        state.getPlayer().getPlayerHand().removeAll(state.getPlayer().getPlayerHand());

        //check if player can move from atlanta to atlanta
        City atlanta = state.getAllCities().get(1);
        assertFalse(state.movePawn(state.getPlayer(),atlanta, atlanta));

        //check to make sure you can't move from one research center to a city without a research center
        //(from atlanta to baghdad)
        City baghdad = state.getAllCities().get(2);
        assertFalse(state.movePawn(state.getPlayer(),atlanta,baghdad));
        assertEquals(state.getPlayer().getCurrentLocation(),atlanta);

        // check if player can move from one location with research center
        // to another city with a research center (from baghdad to atlanta)
        baghdad.setHasResearchLab(true);
        assertTrue(state.movePawn(state.getPlayer(),atlanta,baghdad));
        assertEquals(state.getPlayer().getCurrentLocation(),baghdad);

    }


    //Sarah Schibel wrote this test
    @Test
    public void testCityCp(){

        PandemicGameState state = new PandemicGameState();
        City pInfo = state.getAllCities().get(1);
        City pInfo2 = pInfo;

        assertEquals(pInfo.getName(), pInfo2.getName());
        assertEquals(pInfo.getAdjacentCities(), pInfo2.getAdjacentCities());
        assertEquals(pInfo.getDiseaseCubes(), pInfo2.getDiseaseCubes());
        assertEquals(pInfo.getHasResearchLab(), pInfo2.getHasResearchLab());
    }

    @Test
    public void testMovePawn3(){
        //default location at start is Atlanta
        PandemicGameState state = new PandemicGameState();

        //Checks to see if you can travel back to Atlanta from the initial point (Atlanta)
        City atlanta = state.getAllCities().get(1);
        assertFalse(state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), atlanta));
        assertEquals(state.getPlayer().getCurrentLocation().getName(), atlanta.getName());

        //Checks adjacent city for Atlanta which is Chicago
        City chicago = state.getAllCities().get(40);
        assertTrue(state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), chicago));//does it believe that it worked
        assertEquals(state.getPlayer().getCurrentLocation().getName(), chicago.getName());//actually test that it worked

        //Checks if sanFran is adjacent city from SF
        City sanfran = state.getAllCities().get(33);
        assertTrue(state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), sanfran));
        assertEquals(state.getPlayer().getCurrentLocation().getName(), sanfran.getName());
        //assertEquals(state.getPlayer().getCurrentLocation().getName(), chicago.getName());

        //Checks to make sure that Osaka is not an adjacent city to San Fran
        City osaka = state.getAllCities().get(47);
        state.getPlayer().getPlayerHand().removeAll(state.getPlayer().getPlayerHand());
        assertFalse(state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), osaka));
        assertEquals(state.getPlayer().getCurrentLocation().getName(), sanfran.getName());

    }

    @Test
    public void testMovePawn4(){
        //default location is Atlanta
        PandemicGameState state = new PandemicGameState();

        //tests if discarding card of current city, lets you go anywhere
        City atlanta = state.getAllCities().get(1);
        City buenosaires = state.getAllCities().get(5);
        City milan = state.getAllCities().get(43);
        PlayerCard card1 = new PlayerCard(atlanta, "blue", false, R.drawable.atlanta);

        state.getPlayer().addCardToPlayerHand(card1);

        assertTrue(state.movePawn(state.getPlayer(),atlanta, buenosaires));

        //tests if you don't have current city's card, can't go anywhere
        state.getPlayer().getPlayerHand().removeAll(state.getPlayer().getPlayerHand());
        assertFalse(state.movePawn(state.getPlayer(), buenosaires, milan));

    }

}