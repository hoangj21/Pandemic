package com.example.joann.pandemic;

import com.example.joann.pandemic.pandemic.City;
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

    @Test
    public void testPlayerCardDiscarding()
    {
        City london = new City();
        City paris = new City();
        City stPetersburg = new City();


        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        PlayerCard card3 = new PlayerCard(stPetersburg, "blue", false, R.drawable.stpetersburg);

        PandemicGameState instance = new PandemicGameState();
        PlayerInfo pInfo = instance.getPlayer();

        pInfo.addCardToPlayerHand(card1);
        pInfo.addCardToPlayerHand(card2);
        pInfo.addCardToPlayerHand(card3);

        assertTrue(instance.discardPlayerCard(pInfo, card1));
        assertTrue(instance.discardPlayerCard(pInfo, card2));
        assertTrue(instance.discardPlayerCard(pInfo, card3));
        assertFalse(instance.discardPlayerCard(pInfo, card1));

    }

    @Test
    public void testPlayerInfoCp(){

        City london = new City();
        PlayerInfo pInfo = new PlayerInfo(1, 1, 1, london);
        PlayerInfo pInfo2 = new PlayerInfo(pInfo);

        assertEquals(pInfo.getCurrentLocation(), pInfo2.getCurrentLocation());
        assertEquals(pInfo, pInfo2);


    }

    @Test
    public void testGameStateCp(){
        PandemicGameState instance = new PandemicGameState();
        PandemicGameState instance2 = new PandemicGameState(instance);

        assertEquals(instance, instance2);
    }

    @Test
    public void testPass(){
        PandemicGameState instance = new PandemicGameState();
        PlayerInfo pInfo = instance.getPlayer();

        assertEquals(4, pInfo.getActionsLeft());
        instance.passAction(pInfo);
        assertEquals(3, pInfo.getActionsLeft());
    }

    @Test
    public void testCityStuff(){
        City london = new City();
        City paris = new City();

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

    @Test
    public void testPlayerHand(){
        City london = new City();
        City paris = new City();
        City stPetersburg = new City();
        City washington = new City();
        City chicago = new City();
        City newYork = new City();
        City montreal = new City();
        City essen = new City();

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

    @Test
    public void testTreatDisease() {
        City london = new City();
        PandemicGameState instance = new PandemicGameState();
        PlayerInfo pInfo = instance.getPlayer();
        int diseaseCubeCount = pInfo.getCurrentLocation().getDiseaseCubes().size()-1;
        instance.treatDisease(pInfo, pInfo.getCurrentLocation());
        assertEquals(pInfo.getCurrentLocation().getDiseaseCubes().size(), diseaseCubeCount);

    }

    @Test
    public void testDrawInfection()
    {
        City london = new City();
        City paris = new City();
        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);

        PandemicGameState instance = new PandemicGameState();
        PlayerInfo pInfo = instance.getPlayer();


        instance.drawInfectionCard();

        assertEquals(1, instance.getInfectionDiscardDeck().size());

        instance.drawInfectionCard();

        assertEquals(2, instance.getInfectionDiscardDeck().size());

    }

    @Test
    public void testMovePawn()
    {
        City london = new City();
        City paris = new City();
        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        City desiredCity = london;
        PlayerInfo pInfo = new PlayerInfo(1,2,3,london);
        pInfo.addCardToPlayerHand(card1);
        pInfo.addCardToPlayerHand(card2);

        //desired city !in adjacent city array
        assertEquals(desiredCity, pInfo.getCurrentLocation());
    }

    @Test
    public void testMovePawn2()
    {
        City london = new City();
        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        City desiredCity = london;

        //desired city == playerCard
        assertEquals(desiredCity, card1.getLocation());
    }


    @Test
    public void testMovePawn3()
    {
        City paris = new City();
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        City currentCity = paris;

        //current city == playerCard
        assertEquals(currentCity, card2.getLocation());
    }

    //current city has research center == desired city has researched center
    @Test
    public void testMovePawn4()
    {
        City london = new City();
        City paris = new City();
        City desiredCity = london;
        City currentCity = paris;

        //current city has research center == desired city has researched center
        assertEquals(currentCity.getHasResearchLab(), desiredCity.getHasResearchLab());
    }


}