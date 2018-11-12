package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;

import com.example.joann.pandemic.R;

import java.util.ArrayList;

/************************************
 * Object class that creates the player card info
 * that determines the player cards info
 * based upon the variables:
 * the location of the city on the player card: location
 * the color of the card for diseases: diseaseColor
 * determines if the card drawn from the player deck
 * is an epidemic card: isEpidemic
 *
 ************************************/


public class PlayerCard extends Card{

    protected City location;
    protected String diseaseColor;
    protected boolean isEpidemic;
    protected int androidId;

    //Default constructor
    public PlayerCard(City city, String color, boolean isItEpidemic, int aId)
    {
        location = city;
        diseaseColor = color;
        isEpidemic = isItEpidemic;
        androidId = aId;

    }
    //copy constructor
    public PlayerCard(PlayerCard playerCard)
    {
        this.location = new City(playerCard.location);
        this.diseaseColor = playerCard.diseaseColor;
        this.isEpidemic = playerCard.isEpidemic;
        this.androidId = playerCard.androidId;

    }

    //getter for all the variables

    public City getLocation() {
        return location;
    }

    public String getdiseaseColor() {
        return diseaseColor;
    }

    public boolean getisItEpidemic() {
        return isEpidemic;
    }

    public int getAndroidId(){
        return androidId;
    }


}

