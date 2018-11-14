package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

import android.util.EventLog;

import java.util.ArrayList;

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
        resilientPopulation = isResilient; //remove one infection card from infectionDiscardDeck 4eva
        quietNight = isQuietNight;//don't draw infection cards
        forecast = isForecast; //rearrange the top 6 cards of infection deck (shuffle)
        governmentGrant = isGovernment; //add a research station to any city
        airlift = isAirlift; //move yourself to any city

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


    public static void initEventCard() {
        EventCard resilient_pop_card = new EventCard(true, true, false, false, false, false);
        EventCard quiet_night_card = new EventCard(true, false, true, false, false, false);
        EventCard forecast_card = new EventCard(true, false, false, true, false, false);
        EventCard government_grant_card = new EventCard(true, false, false, false, true, false);
        EventCard airlift_card = new EventCard(true, false, false, false, false, true);

        ArrayList<EventCard> EventCard = new ArrayList<EventCard>();
        EventCard.add(resilient_pop_card);
        EventCard.add(quiet_night_card);
        EventCard.add(forecast_card);
        EventCard.add(government_grant_card);
        EventCard.add(airlift_card);
    }
}
