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

    public void initiation()
    {
        InfectionCard algiers_infection = new InfectionCard(algiers, black);
        InfectionCard atlanta_infection = new InfectionCard(atlanta, blue);
        InfectionCard baghdad_infection = new InfectionCard(baghdad, black);
        InfectionCard bangkok_infection = new InfectionCard(bangkok, red);
        InfectionCard bejing_infection = new InfectionCard(bejing, red);
        InfectionCard beunosaires_infection = new InfectionCard(beunosaires, yellow);
        InfectionCard bogota_infection = new InfectionCard(bogota, yellow);
        InfectionCard istanbul_infection = new InfectionCard(istanbul, black);
        InfectionCard khartoum_infection = new InfectionCard(khartoum, yellow);
        InfectionCard hochiminhcity_infection = new InfectionCard(hochiminhcity, red);
        InfectionCard riyadh_infection = new InfectionCard(riyadh, black);
        InfectionCard essen_infection = new InfectionCard(essen, blue);
        InfectionCard washington_infection = new InfectionCard(washington, blue);
        InfectionCard moscow_infection = new InfectionCard(moscow, black);
        InfectionCard newyork_infection = new InfectionCard(newyork, blue);
        InfectionCard taipei_infection = new InfectionCard(taipei, red);
        InfectionCard tokyo_infection = new InfectionCard(tokyo, red);
        InfectionCard tehran_infection = new InfectionCard(tehran, black);
        InfectionCard jakarta_infection = new InfectionCard(jakarta, red);
        InfectionCard cairo_infection = new InfectionCard(cairo, black);
        InfectionCard chennai_infection = new InfectionCard(chennai, black);
        InfectionCard paris_infection = new InfectionCard(paris, blue);
        InfectionCard petersburg_infection = new InfectionCard(petersburg, blue);
        InfectionCard saopaulo_infection = new InfectionCard(saopaulo, yellow);
        InfectionCard lagos_infection = new InfectionCard(lagos, yellow);
        InfectionCard lima_infection = new InfectionCard(lima, yellow);
        InfectionCard london_infection = new InfectionCard(london, blue);
        InfectionCard losangeles_infection = new InfectionCard(losangeles, blue);
        InfectionCard delhi_infection = new InfectionCard(delhi, black);
        InfectionCard johannesburg_infection = new InfectionCard(johannesburg, yellow);
        InfectionCard karachi_infection = new InfectionCard(karachi, black);
        InfectionCard madrid_infection = new InfectionCard(madrid, yellow);
        InfectionCard montreal_infection = new InfectionCard(montreal, blue);
        InfectionCard sanfrancisco_infection = new InfectionCard(sanfrancisco, blue);
        InfectionCard kolkata_infection = new InfectionCard(kolkata, black);
        InfectionCard mexicocity_infection = new InfectionCard(mexicocity, yellow);
        InfectionCard santiago_infection = new InfectionCard(santiago, yellow);
        InfectionCard sydney_infection = new InfectionCard(sydney, red);
        InfectionCard mumbai_infection = new InfectionCard(mumbai, black);
        InfectionCard seoul_infection = new InfectionCard(seoul, red);
        InfectionCard chicago_infection = new InfectionCard(chicago, blue);
        InfectionCard kinshasa_infection = new InfectionCard(kinshasa, yellow);
        InfectionCard miami_infection = new InfectionCard(miami, yellow);
        InfectionCard milan_infection = new InfectionCard(milan, blue);
        InfectionCard manila_infection = new InfectionCard(manila, red);
        InfectionCard shanghai_infection = new InfectionCard(shanghai, red);
        InfectionCard hongkong_infection = new InfectionCard(hongkong, red);
        InfectionCard osaka_infection = new InfectionCard(osaka, red);

        ArrayList<InfectionCard> InfectionCard = new ArrayList<InfectionCard>;
        InfectionCard.add(algiers_infection);
        InfectionCard.add(atlanta_infection);
        InfectionCard.add(baghdad_infection);
        InfectionCard.add(bangkok_infection);
        InfectionCard.add(bejing_infection);
        InfectionCard.add(beunosaires_infection);
        InfectionCard.add(bogota_infection);
        InfectionCard.add(istanbul_infection);
        InfectionCard.add(khartoum_infection);
        InfectionCard.add(hochiminhcity_infection);
        InfectionCard.add(riyadh_infection);
        InfectionCard.add(essen_infection);
        InfectionCard.add(washington_infection;
        InfectionCard.add(moscow_infection);
        InfectionCard.add(newyork_infection);
        InfectionCard.add(taipei_infection);
        InfectionCard.add(tokyo_infection);
        InfectionCard.add(tehran_infection);
        InfectionCard.add(jakarta_infection);
        InfectionCard.add(cairo_infection);
        InfectionCard.add(chennai_infection);
        InfectionCard.add(paris_infection);
        InfectionCard.add(petersburg_infection);
        InfectionCard.add(saopaulo_infection);
        InfectionCard.add(lagos_infection);
        InfectionCard.add(lima_infection);
        InfectionCard.add(london_infection);
        InfectionCard.add(losangeles_infection);
        InfectionCard.add(delhi_infection);
        InfectionCard.add(johannesburg_infection);
        InfectionCard.add(karachi_infection);
        InfectionCard.add(madrid_infection);
        InfectionCard.add(montreal_infection);
        InfectionCard.add(sanfrancisco_infection);
        InfectionCard.add(kolkata_infection);
        InfectionCard.add(mexicocity_infection);
        InfectionCard.add(santiago_infection);
        InfectionCard.add(sydney_infection);
        InfectionCard.add(mumbai_infection);
        InfectionCard.add(seoul_infection);
        InfectionCard.add(chicago_infection);
        InfectionCard.add(kinshasa_infection;
        InfectionCard.add(miami_infection);
        InfectionCard.add(milan_infection;
        InfectionCard.add(manila_infection);
        InfectionCard.add(shanghai_infection);
        InfectionCard.add(hongkong_infection);
        InfectionCard.add(osaka_infection);
    }

}
