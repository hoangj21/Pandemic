package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

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

    public City getLocation() {
        return location;
    }

    public int getdiseaseColor() {
        return diseaseColor;
    }

    public boolean getisItEpidemic() {
        return isEpidemic;
    }


       public void initiation()
    {
        PlayerCard algiers_card = new PlayerCard(algiers, black, false);
        PlayerCard atlanta_card = new PlayerCard(atlanta, blue, false);
        PlayerCard baghdad_card = new PlayerCard(baghdad, black, false);
        PlayerCard bangkok_card = new PlayerCard(bangkok, red, false);
        PlayerCard bejing_card = new PlayerCard(bejing, red, false);
        PlayerCard beunosaires_card = new PlayerCard(beunosaires, yellow, false);
        PlayerCard bogota_card = new PlayerCard(bogota, yellow, false);
        PlayerCard istanbul_card = new PlayerCard(istanbul, black, false);
        PlayerCard khartoum_card = new PlayerCard(khartoum, yellow, false);
        PlayerCard hochiminhcity_card = new PlayerCard(hochiminhcity, red, false);
        PlayerCard riyadh_card = new PlayerCard(riyadh, black, false);
        PlayerCard essen_card = new PlayerCard(essen, blue, false);
        PlayerCard washington_card = new PlayerCard(washington, blue, false);
        PlayerCard moscow_card = new PlayerCard(moscow, black, false);
        PlayerCard newyork_card = new PlayerCard(newyork, blue, false);
        PlayerCard taipei_card = new PlayerCard(taipei, red, false);
        PlayerCard tokyo_card = new PlayerCard(tokyo, red, false);
        PlayerCard tehran_card = new PlayerCard(tehran, black, false);
        PlayerCard jakarta_card = new PlayerCard(jakarta, red, false);
        PlayerCard cairo_card = new PlayerCard(cairo, black, false);
        PlayerCard chennai_card = new PlayerCard(chennai, black, false);
        PlayerCard paris_card = new PlayerCard(paris, blue, false);
        PlayerCard petersburg_card = new PlayerCard(petersburg, blue, false);
        PlayerCard saopaulo_card = new PlayerCard(saopaulo, yellow, false);
        PlayerCard lagos_card = new PlayerCard(lagos, yellow, false);
        PlayerCard lima_card = new PlayerCard(lima, yellow, false);
        PlayerCard london_card = new PlayerCard(london, blue, false);
        PlayerCard losangeles_card = new PlayerCard(losangeles, blue, false);
        PlayerCard delhi_card = new PlayerCard(delhi, black, false);
        PlayerCard johannesburg_card = new PlayerCard(johannesburg, yellow, false);
        PlayerCard karachi_card = new PlayerCard(karachi, black, false);
        PlayerCard madrid_card = new PlayerCard(madrid, yellow, false);
        PlayerCard montreal_card = new PlayerCard(montreal, blue, false);
        PlayerCard sanfrancisco_card = new PlayerCard(sanfrancisco, blue, false);
        PlayerCard kolkata_card = new PlayerCard(kolkata, black, false);
        PlayerCard mexicocity_card = new PlayerCard(mexicocity, yellow, false);
        PlayerCard santiago_card = new PlayerCard(santiago, yellow, false);
        PlayerCard sydney_card = new PlayerCard(sydney, red, false);
        PlayerCard mumbai_card = new PlayerCard(mumbai, black, false);
        PlayerCard seoul_card = new PlayerCard(seoul, red, false);
        PlayerCard chicago_card = new PlayerCard(chicago, blue, false);
        PlayerCard kinshasa_card = new PlayerCard(kinshasa, yellow, false);
        PlayerCard miami_card = new PlayerCard(miami, yellow, false);
        PlayerCard milan_card = new PlayerCard(milan, blue, false);
        PlayerCard manila_card = new PlayerCard(manila, red, false);
        PlayerCard shanghai_card = new PlayerCard(shanghai, red, false);
        PlayerCard hongkong_card = new PlayerCard(hongkong, red, false);
        PlayerCard osaka_card = new PlayerCard(osaka, red, false);


        ArrayList<PlayerCard> playerDeck = new ArrayList<PlayerCard>;
        playerDeck.add(algiers_card);
        playerDeck.add(atlanta_card);
        playerDeck.add(baghdad_card);
        playerDeck.add(bangkok_card);
        playerDeck.add(bejing_card);
        playerDeck.add(beunosaires_card);
        playerDeck.add(bogota_card);
        playerDeck.add(istanbul_card);
        playerDeck.add(khartoum_card);
        playerDeck.add(hochiminhcity_card);
        playerDeck.add(riyadh_card);
        playerDeck.add(essen_card);
        playerDeck.add(washington_card);
        playerDeck.add(moscow_card);
        playerDeck.add(newyork_card);
        playerDeck.add(taipei_card);
        playerDeck.add(tokyo_card);
        playerDeck.add(tehran_card);
        playerDeck.add(jakarta_card);
        playerDeck.add(cairo_card);
        playerDeck.add(chennai_card);
        playerDeck.add(paris_card);
        playerDeck.add(petersburg_card);
        playerDeck.add(saopaulo_card);
        playerDeck.add(lagos_card);
        playerDeck.add(lima_card);
        playerDeck.add(london_card);
        playerDeck.add(losangeles_card);
        playerDeck.add(delhi_card);
        playerDeck.add(johannesburg_card);
        playerDeck.add(karachi_card);
        playerDeck.add(madrid_card);
        playerDeck.add(montreal_card);
        playerDeck.add(sanfrancisco_card);
        playerDeck.add(kolkata_card);
        playerDeck.add(mexicocity_card);
        playerDeck.add(santiago_card);
        playerDeck.add(sydney_card);
        playerDeck.add(mumbai_card);
        playerDeck.add(seoul_card);
        playerDeck.add(chicago_card);
        playerDeck.add(kinshasa_card);
        playerDeck.add(miami_card);
        playerDeck.add(milan_card);
        playerDeck.add(manila_card);
        playerDeck.add(shanghai_card);
        playerDeck.add(hongkong_card);
        playerDeck.add(osaka_card);
    }

}
