package com.example.joann.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

/******************************************************
* Object class that creates the the pawn info
 * that determines the pawns id and the role that they have
 * based upon variables:
 * knowing if the card drawn is an action card: isAction
 * Remove any 1 card in the Infection Discard Pile from the Game
 * You may play this between the Infect and Intensify steps of an epidemic : resilientPopulation
* Skip the next Infect Cities step: quietNight
* Draw, Look at, and Rearrange the top 6 cards of the Infection deck Put them back on top.: forecast
 * Add 1 research station to any city (no city card needed): governmentGrant
* Move any 1 pawn to any city: airlift
 *
 ****************************************************/

public class EventCard extends Card
{
    protected boolean isAction;
    protected boolean resilientPopulation;
    protected boolean quietNight;
    protected boolean forecast;
    protected boolean governmentGrant;
    protected boolean airlift;

    //Default constructor
    public EventCard(boolean isItAction, boolean isResilient, boolean isQuietNight, boolean isForecast, boolean isGovernment, boolean isAirlift)
    {
        isAction = isItAction;
        resilientPopulation = isResilient;
        quietNight = isQuietNight;
        forecast = isForecast;
        governmentGrant = isGovernment;
        airlift = isAirlift;

    }

    //Copy constructor
    public EventCard(EventCard otherCard)
    {
        this.isAction = otherCard.isAction;
        this.resilientPopulation = otherCard.resilientPopulation;
        this.quietNight = otherCard.quietNight;
        this.forecast= otherCard.forecast;
        this.governmentGrant = otherCard.governmentGrant;
        this.airlift = otherCard.airlift;

    }
    //getter for all the variables
    public Boolean getisAction() {
        return isAction;
    }

    public Boolean getresilientPopulation() {
        return resilientPopulation;
    }

    public Boolean getquietNight() {
        return quietNight;
    }

    public Boolean getforecast() {
        return forecast;
    }

    public Boolean getGovernment() {
        return governmentGrant;
    }

    public Boolean getairlift() {
        return airlift;
    }

}
