package com.example.joann.pandemic;

/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

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
    protected int diseaseColor;

    //Constructor
    public InfectionCard(City city, int color)
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

    public int getDiseaseColor() {
        return diseaseColor;
    }
}
