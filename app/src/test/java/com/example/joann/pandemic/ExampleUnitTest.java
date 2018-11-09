package com.example.joann.pandemic;

import android.graphics.Color;

import com.example.joann.pandemic.pandemic.Card;
import com.example.joann.pandemic.pandemic.City;
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
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMovePawn()
    {
        City london = new City();
        City paris = new City();
        PlayerCard card1 = new PlayerCard(london, Color.BLUE, false);
        PlayerCard card2 = new PlayerCard(paris, Color.BLUE, false);
        City desiredCity = london;
        PlayerInfo pInfo = new PlayerInfo(1,2,3,london, card1, card2);

        //desired city !in adjacent city array
        assertEquals(desiredCity, pInfo.getCurrentLocation());
    }

    @Test
    public void testMovePawn2()
    {
        City london = new City();
        PlayerCard card1 = new PlayerCard(london, Color.BLUE, false);
        City desiredCity = london;

        //desired city == playerCard
        assertEquals(desiredCity, card1.getisLocation());
    }


    @Test
    public void testMovePawn3()
    {
        City paris = new City();
        PlayerCard card2 = new PlayerCard(paris, Color.BLUE, false);
        City currentCity = paris;

        //current city == playerCard
        assertEquals(currentCity, card2.getisLocation());
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

    @Test
    public void testInfectionRate()
    {

    }
}