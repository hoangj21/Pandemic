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

    //Constructor
    public InfectionCard(City city, String color)
    {
        location = city;
        diseaseColor = color;
    }

    //Copy constructor
    public InfectionCard(InfectionCard otherCard){
        this.location = otherCard.location;
        this.diseaseColor = otherCard.diseaseColor;


    }

    //getter for all the variables
    public City getLocation() {
        return location;
    }

    public String getDiseaseColor() {
        return diseaseColor;
    }



}
