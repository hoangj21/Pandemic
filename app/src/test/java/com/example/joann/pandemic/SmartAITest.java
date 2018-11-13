package com.example.joann.pandemic;

import com.example.joann.pandemic.pandemic.City;
import com.example.joann.pandemic.pandemic.PandemicComputerPlayerSmart;
import com.example.joann.pandemic.pandemic.PandemicGameState;
import com.example.joann.pandemic.pandemic.PlayerCard;

import org.junit.Test;

public class SmartAITest {


    @Test
    public void testSmartAI()
    {
        City london = new City();
        City paris = new City();
        City stPetersburg = new City();
        City atlanta = new City();
        City essen = new City();

        PandemicComputerPlayerSmart ai = new PandemicComputerPlayerSmart("Dave");
        PandemicComputerPlayerSmart ai2 = new PandemicComputerPlayerSmart("Carl");

        PandemicGameState state = new PandemicGameState();


        PlayerCard card1 = new PlayerCard(london, "blue", false, R.drawable.london);
        PlayerCard card2 = new PlayerCard(paris, "blue", false, R.drawable.paris);
        PlayerCard card3 = new PlayerCard(stPetersburg, "blue", false, R.drawable.stpetersburg);
        PlayerCard card4 = new PlayerCard(atlanta, "blue", false, R.drawable.atlanta);
        PlayerCard card5 = new PlayerCard(essen, "blue", false, R.drawable.essen);


    }
}
