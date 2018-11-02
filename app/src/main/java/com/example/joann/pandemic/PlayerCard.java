package com.example.joann.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

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
    protected int diseaseColor;
    protected boolean isEpidemic;

    //Default constructor
    public PlayerCard(City city, int color, boolean isItEpidemic)
    {
        location = city;
        diseaseColor = color;
        isEpidemic = isItEpidemic;

    }
    //copy constructor
    public PlayerCard(PlayerCard playerCard)
    {
        this.location = new City(playerCard.location);
        this.diseaseColor = playerCard.diseaseColor;
        this.isEpidemic = playerCard.isEpidemic;

    }

    //getter for all the variables

    public City getisLocation() {
        return location;
    }

    public int getdiseaseColor() {
        return diseaseColor;
    }

    public boolean getisItEpidemic() {
        return isEpidemic;
    }
}
