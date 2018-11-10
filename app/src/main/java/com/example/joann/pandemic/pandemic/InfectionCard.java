package com.example.joann.pandemic.pandemic;

/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

import java.util.ArrayList;

/************************************
 * Object class that creates the infection card info
 * that determines the player cards info
 * based upon the variables:
 * the location of the city on the player card: location
 * the color of the card for diseases: diseaseColor
 *
 ************************************/

public class InfectionCard extends Card
{

    protected City location;
    protected String diseaseColor;
    protected int androidIdInfect;

    //Constructor
    public InfectionCard(City city, String color, int aId)
    {
        location = city;
        diseaseColor = color;
        androidIdInfect = aId;
    }

    //Copy constructor
    public InfectionCard(InfectionCard otherCard){
        this.location = otherCard.location;
        this.diseaseColor = otherCard.diseaseColor;
        this.androidIdInfect = otherCard.androidIdInfect;

    }

    //getter for all the variables
    public City getLocation() {
        return location;
    }

    public String getDiseaseColor() {
        return diseaseColor;
    }

    public int getAndroidIdInfect(){
        return androidIdInfect;
    }

}
