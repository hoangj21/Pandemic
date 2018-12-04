package com.example.joann.pandemic;

import android.util.Log;

import com.example.joann.pandemic.pandemic.City;
import com.example.joann.pandemic.pandemic.InfectionCard;
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
        //order of curedDisease will be {blue, black, red, yellow}

        PandemicGameState instance = new PandemicGameState();


        int[]curedDisease1 = new int[]{0, 0, 0, 1};
        instance.setCuredDiseases(curedDisease1);
        assertTrue(instance.isDiseaseEradicated("Yellow"));

        int[]curedDisease2 = new int[]{0, 0, 1, 0};
        instance.setCuredDiseases(curedDisease2);
        assertTrue(instance.isDiseaseEradicated("Red"));

        int[]curedDisease3 = new int[]{0, 1, 0, 0};
        instance.setCuredDiseases(curedDisease3);
        assertTrue(instance.isDiseaseEradicated("Black"));


        instance.setNumCubesBlue(10);

        int[]curedDisease4 = new int[]{1, 0, 0, 0};
        instance.setCuredDiseases(curedDisease4);
        assertFalse(instance.isDiseaseEradicated("Blue"));

    }

    //Tests if player can properly discard a card from their hand
    // -Polina G.
    @Test
    public void testPlayerCardDiscarding()
    {
        City london = new City("London");
        City paris = new City("Paris");
        City stPetersburg = new City("St Petersburg");


        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        PlayerCard card3 = new PlayerCard(stPetersburg, "blue", false, R.drawable.stpetersburg);

        PandemicGameState instance = new PandemicGameState();
        PlayerInfo pInfo = instance.getPlayer();

        pInfo.addCardToPlayerHand(card1);
        //assertEquals(pInfo.getPlayerHand().get(1), card1);


        pInfo.addCardToPlayerHand(card2);
        assertEquals(pInfo.getPlayerHand().size(), 6);

        pInfo.addCardToPlayerHand(card3);
        assertEquals(pInfo.getPlayerHand().size(), 7);


        assertTrue(instance.discardPlayerCard(pInfo, card1));
        assertEquals(pInfo.getPlayerHand().size(), 6);

        assertTrue(instance.discardPlayerCard(pInfo, card2));
        assertEquals(pInfo.getPlayerHand().size(), 5);

        assertTrue(instance.discardPlayerCard(pInfo, card3));
        assertEquals(pInfo.getPlayerHand().size(), 4);

        assertFalse(instance.discardPlayerCard(pInfo, card1));
    }

    //Tests the Player Info copy constructor
    // -Polina G.
    @Test
    public void testPlayerInfoCp(){

        City london = new City("London");
        PlayerInfo pInfo = new PlayerInfo(1, 1, 1, london);
        PlayerInfo pInfo2 = new PlayerInfo(pInfo);

        assertEquals(pInfo.getCurrentLocation(), pInfo2.getCurrentLocation());
        assertEquals(pInfo.getActionsLeft(), pInfo2.getActionsLeft());
        assertEquals(pInfo.getPlayerHand(), pInfo2.getPlayerHand());
        assertEquals(pInfo.getRole(), pInfo2.getRole());
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
        PandemicGameState instance = new PandemicGameState();
        PlayerInfo pInfo = instance.getPlayer();

        assertEquals(4, pInfo.getActionsLeft());
        instance.passAction(pInfo);
        assertEquals(3, pInfo.getActionsLeft());
    }

    //tests to make sure the disease count in cities works
    // -Joanna H.
    @Test
    public void testCityDiseaseCount(){
        City london = new City("London");
        City paris = new City("Paris");

        london.addDiseaseCube("blue");
        assertEquals(1, london.getDiseaseCubes().size());
        london.addDiseaseCube("blue");
        assertEquals(2, london.getDiseaseCubes().size());
        london.addDiseaseCube("blue");
        assertEquals(3, london.getDiseaseCubes().size());
        london.addDiseaseCube("blue");
        assertEquals(3, london.getDiseaseCubes().size());

        assertFalse(paris.getVisited());

        london.removeDiseaseCube();
        assertEquals(2, london.getDiseaseCubes().size());
    }

    //tests player hand to make sure it hold and handle cards
    // -Kelsi C.
    @Test
    public void testPlayerHand(){
        City london = new City("London");
        City paris = new City("Paris");
        City stPetersburg = new City("St Petersburg");
        City washington = new City("Washington");
        City chicago = new City("Chicago");
        City newYork = new City("New York");
        City montreal = new City("Montreal");
        City essen = new City("Essen");

        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        PlayerCard card3 = new PlayerCard(stPetersburg, "blue", false, R.drawable.stpetersburg);
        PlayerCard card4 = new PlayerCard(washington, "blue", false, R.drawable.washington);
        PlayerCard card5 = new PlayerCard(chicago, "blue", false, R.drawable.chicago);
        PlayerCard card6 = new PlayerCard(newYork, "blue", false, R.drawable.newyork);
        PlayerCard card7 = new PlayerCard(montreal, "blue", false, R.drawable.montreal);
        PlayerCard card8 = new PlayerCard(essen, "blue", false, R.drawable.essen);

        PlayerInfo pInfo = new PlayerInfo(1,2,3,london);

        assertEquals(0, pInfo.getPlayerHand().size());


        assertTrue(pInfo.addCardToPlayerHand(card1));
        pInfo.addCardToPlayerHand(card2);
        pInfo.addCardToPlayerHand(card3);
        pInfo.addCardToPlayerHand(card4);
        pInfo.addCardToPlayerHand(card5);
        pInfo.addCardToPlayerHand(card6);
        pInfo.addCardToPlayerHand(card7);
        assertEquals(7, pInfo.getPlayerHand().size());

        pInfo.addCardToPlayerHand(card8);
        assertEquals(7, pInfo.getPlayerHand().size());


    }

    //tests if treat disease function works properly
    // -Sarah S.
    @Test
    public void testTreatDisease() {
        PandemicGameState instance = new PandemicGameState();
        PlayerInfo pInfo = instance.getPlayer();
        int diseaseCubeCount = pInfo.getCurrentLocation().getDiseaseCubes().size();
        instance.treatDisease(pInfo, pInfo.getCurrentLocation());
        assertEquals(pInfo.getCurrentLocation().getDiseaseCubes().size(), diseaseCubeCount - 1);

    }

    //tests movement to an adjacent city
    // - Johanna H.
    @Test
    public void testMovePawn()
    {
        City london = new City("London");
        City paris = new City("Paris");
        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        City desiredCity = london;
        PlayerInfo pInfo = new PlayerInfo(1,2,3,london);
        pInfo.addCardToPlayerHand(card1);
        pInfo.addCardToPlayerHand(card2);

        //desired city !in adjacent city array
        assertEquals(desiredCity, pInfo.getCurrentLocation());
    }

    //tests movement by discarding a card
    // -Joanna H.
    @Test
    public void testMovePawn2()
    {
        City london = new City("London");
        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        City desiredCity = london;

        //desired city == playerCard
        assertEquals(desiredCity, card1.getLocation());
    }

    //tests movement to any city by discarding card of the current city player is in
    // -Kelsi C.
    @Test
    public void testMovePawn3()
    {
        City paris = new City("Paris");
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        City currentCity = paris;

        //current city == playerCard
        assertEquals(currentCity, card2.getLocation());
    }

    // tests research center movement:  current city has research center == desired city has researched center
    // -Kelsi C.
    @Test
    public void testMovePawn4()
    {
        City london = new City("London");
        City paris = new City("Paris");
        City desiredCity = london;
        City currentCity = paris;

        //current city has research center == desired city has researched center
        assertEquals(currentCity.getHasResearchLab(), desiredCity.getHasResearchLab());
    }


    //Sarah Schibel wrote this test
    @Test
    public void testCityCp(){

        City pInfo = new City("London");
        City pInfo2 = pInfo;

        assertEquals(pInfo.getName(), pInfo2.getName());
        assertEquals(pInfo.getAdjacentCities(), pInfo2.getAdjacentCities());
        assertEquals(pInfo.getDiseaseCubes(), pInfo2.getDiseaseCubes());
        assertEquals(pInfo.getHasResearchLab(), pInfo2.getHasResearchLab());

    }


    @Test
    public void testMovePawn5(){
        //default location at start is Atlanta
        PandemicGameState state = new PandemicGameState();

        //Checks to see if you can travel back to Atlanta from the initial point (Atlanta)
        City atlanta = state.getAllCities().get(1);
        assertTrue(state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), atlanta));
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
        assertFalse(state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), osaka));
        assertEquals(state.getPlayer().getCurrentLocation().getName(), sanfran.getName());

    }

}