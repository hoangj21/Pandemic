package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

import android.graphics.Color;

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

    //Default constructor
    public PlayerCard(City city, String color, boolean isItEpidemic)
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

    public String getdiseaseColor() {
        return diseaseColor;
    }

    public boolean getisItEpidemic() {
        return isEpidemic;
    }

    public static void initStarterPlayerDecks(ArrayList<PlayerCard> playerDeck, ArrayList<InfectionCard> InfectionCard){
        String red = "Red";
        String blue = "Blue";
        String black = "Black";
        String yellow = "Yellow";

            City algiers = new City ();
            City atlanta = new City ();
            City baghdad = new City ();
            City bangkok = new City ();
            City bejing = new City ();
            City beunosaires = new City ();
            City bogota = new City ();
            City istanbul = new City ();
            City khartoum = new City ();
            City hochiminhcity = new City ();
            City riyadh = new City ();
            City essen = new City ();
            City washington = new City ();
            City moscow = new City ();
            City newyork = new City ();
            City taipei = new City ();
            City tokyo = new City ();
            City tehran = new City ();
            City jakarta = new City ();
            City cairo = new City ();
            City chennai = new City ();
            City paris = new City ();
            City petersburg = new City ();
            City saopaulo = new City ();
            City lagos = new City ();
            City lima = new City ();
            City london = new City ();
            City losangeles = new City ();
            City delhi = new City ();
            City johannesburg = new City ();
            City karachi = new City ();
            City madrid = new City ();
            City montreal = new City ();
            City sanfrancisco = new City ();
            City kolkata = new City ();
            City mexicocity = new City ();
            City santiago = new City ();
            City sydney = new City ();
            City mumbai = new City ();
            City seoul = new City ();
            City chicago = new City ();
            City kinshasa = new City ();
            City miami = new City ();
            City milan = new City ();
            City manila = new City ();
            City shanghai = new City ();
            City hongkong = new City ();
            City osaka = new City ();

            algiers.setAdjacentCities(cairo);
            algiers.setAdjacentCities(istanbul);
            algiers.setAdjacentCities(paris);
            algiers.setAdjacentCities(madrid);

            atlanta.setAdjacentCities(chicago);
            atlanta.setAdjacentCities(washington);
            atlanta.setAdjacentCities(miami);

            baghdad.setAdjacentCities(tehran);
            baghdad.setAdjacentCities(karachi);
            baghdad.setAdjacentCities(riyadh);
            baghdad.setAdjacentCities(cairo);
            baghdad.setAdjacentCities(istanbul);

            bangkok.setAdjacentCities(kolkata);
            bangkok.setAdjacentCities(hongkong);
            bangkok.setAdjacentCities(hochiminhcity);
            bangkok.setAdjacentCities(jakarta);
            bangkok.setAdjacentCities(chennai);

            bejing.setAdjacentCities(seoul);
            bejing.setAdjacentCities(shanghai);

            beunosaires.setAdjacentCities(saopaulo);
            beunosaires.setAdjacentCities(bogota);

            bogota.setAdjacentCities(miami);
            bogota.setAdjacentCities(mexicocity);
            bogota.setAdjacentCities(lima);
            bogota.setAdjacentCities(beunosaires);
            bogota.setAdjacentCities(saopaulo);

            istanbul.setAdjacentCities(algiers);
            istanbul.setAdjacentCities(milan);
            istanbul.setAdjacentCities(petersburg);
            istanbul.setAdjacentCities(moscow);
            istanbul.setAdjacentCities(baghdad);
            istanbul.setAdjacentCities(cairo);

            khartoum.setAdjacentCities(cairo);
            khartoum.setAdjacentCities(johannesburg);
            khartoum.setAdjacentCities(kinshasa);
            khartoum.setAdjacentCities(lagos);

            hochiminhcity.setAdjacentCities(manila);
            hochiminhcity.setAdjacentCities(hongkong);
            hochiminhcity.setAdjacentCities(bangkok);
            hochiminhcity.setAdjacentCities(jakarta);

            riyadh.setAdjacentCities(karachi);
            riyadh.setAdjacentCities(baghdad);
            riyadh.setAdjacentCities(cairo);

            essen.setAdjacentCities(petersburg);
            essen.setAdjacentCities(milan);
            essen.setAdjacentCities(paris);
            essen.setAdjacentCities(london);

            washington.setAdjacentCities(newyork);
            washington.setAdjacentCities(montreal);
            washington.setAdjacentCities(atlanta);
            washington.setAdjacentCities(miami);

            moscow.setAdjacentCities(tehran);
            moscow.setAdjacentCities(istanbul);
            moscow.setAdjacentCities(petersburg);

            newyork.setAdjacentCities(montreal);
            newyork.setAdjacentCities(washington);
            newyork.setAdjacentCities(london);
            newyork.setAdjacentCities(madrid);

            taipei.setAdjacentCities(osaka);
            taipei.setAdjacentCities(shanghai);
            taipei.setAdjacentCities(hongkong);
            taipei.setAdjacentCities(manila);

            tokyo.setAdjacentCities(osaka);
            tokyo.setAdjacentCities(seoul);
            tokyo.setAdjacentCities(shanghai);
            tokyo.setAdjacentCities(sanfrancisco);

            tehran.setAdjacentCities(moscow);
            tehran.setAdjacentCities(baghdad);
            tehran.setAdjacentCities(karachi);
            tehran.setAdjacentCities(delhi);

            jakarta.setAdjacentCities(sydney);
            jakarta.setAdjacentCities(hochiminhcity);
            jakarta.setAdjacentCities(bangkok);
            jakarta.setAdjacentCities(chennai);

            cairo.setAdjacentCities(khartoum);
            cairo.setAdjacentCities(riyadh);
            cairo.setAdjacentCities(baghdad);
            cairo.setAdjacentCities(istanbul);
            cairo.setAdjacentCities(algiers);

            chennai.setAdjacentCities(jakarta);
            chennai.setAdjacentCities(bangkok);
            chennai.setAdjacentCities(kolkata);
            chennai.setAdjacentCities(delhi);
            chennai.setAdjacentCities(mumbai);

            paris.setAdjacentCities(essen);
            paris.setAdjacentCities(milan);
            paris.setAdjacentCities(algiers);
            paris.setAdjacentCities(madrid);
            paris.setAdjacentCities(london);

            petersburg.setAdjacentCities(essen);
            petersburg.setAdjacentCities(istanbul);
            petersburg.setAdjacentCities(moscow);

            saopaulo.setAdjacentCities(beunosaires);
            saopaulo.setAdjacentCities(bogota);
            saopaulo.setAdjacentCities(lagos);
            saopaulo.setAdjacentCities(madrid);

            lagos.setAdjacentCities(saopaulo);
            lagos.setAdjacentCities(kinshasa);
            lagos.setAdjacentCities(khartoum);

            lima.setAdjacentCities(santiago);
            lima.setAdjacentCities(bogota);
            lima.setAdjacentCities(mexicocity);

            london.setAdjacentCities(newyork);
            london.setAdjacentCities(madrid);
            london.setAdjacentCities(paris);
            london.setAdjacentCities(essen);

            losangeles.setAdjacentCities(mexicocity);
            losangeles.setAdjacentCities(chicago);
            losangeles.setAdjacentCities(sanfrancisco);
            losangeles.setAdjacentCities(sydney);

            delhi.setAdjacentCities(tehran);
            delhi.setAdjacentCities(karachi);
            delhi.setAdjacentCities(mumbai);
            delhi.setAdjacentCities(chennai);
            delhi.setAdjacentCities(kolkata);

            johannesburg.setAdjacentCities(kinshasa);
            johannesburg.setAdjacentCities(khartoum);

            karachi.setAdjacentCities(riyadh);
            karachi.setAdjacentCities(baghdad);
            karachi.setAdjacentCities(tehran);
            karachi.setAdjacentCities(delhi);
            karachi.setAdjacentCities(mumbai);

            madrid.setAdjacentCities(saopaulo);
            madrid.setAdjacentCities(newyork);
            madrid.setAdjacentCities(london);
            madrid.setAdjacentCities(paris);
            madrid.setAdjacentCities(algiers);

            montreal.setAdjacentCities(chicago);
            montreal.setAdjacentCities(washington);
            montreal.setAdjacentCities(newyork);

            sanfrancisco.setAdjacentCities(chicago);
            sanfrancisco.setAdjacentCities(tokyo);
            sanfrancisco.setAdjacentCities(manila);

            kolkata.setAdjacentCities(delhi);
            kolkata.setAdjacentCities(chennai);
            kolkata.setAdjacentCities(bangkok);
            kolkata.setAdjacentCities(hongkong);

            mexicocity.setAdjacentCities(losangeles);
            mexicocity.setAdjacentCities(miami);
            mexicocity.setAdjacentCities(bogota);
            mexicocity.setAdjacentCities(chicago);
            mexicocity.setAdjacentCities(lima);

            santiago.setAdjacentCities(lima);

            sydney.setAdjacentCities(jakarta);
            sydney.setAdjacentCities(manila);
            sydney.setAdjacentCities(losangeles);

            mumbai.setAdjacentCities(karachi);
            mumbai.setAdjacentCities(delhi);
            mumbai.setAdjacentCities(chennai);

            seoul.setAdjacentCities(bejing);
            seoul.setAdjacentCities(shanghai);
            seoul.setAdjacentCities(tokyo);

            chicago.setAdjacentCities(sanfrancisco);
            chicago.setAdjacentCities(losangeles);
            chicago.setAdjacentCities(mexicocity);
            chicago.setAdjacentCities(atlanta);
            chicago.setAdjacentCities(montreal);

            kinshasa.setAdjacentCities(johannesburg);
            kinshasa.setAdjacentCities(khartoum);
            kinshasa.setAdjacentCities(lagos);

            miami.setAdjacentCities(atlanta);
            miami.setAdjacentCities(washington);
            miami.setAdjacentCities(bogota);
            miami.setAdjacentCities(mexicocity);

            milan.setAdjacentCities(paris);
            milan.setAdjacentCities(essen);
            milan.setAdjacentCities(istanbul);

            manila.setAdjacentCities(hochiminhcity);
            manila.setAdjacentCities(hongkong);
            manila.setAdjacentCities(taipei);
            manila.setAdjacentCities(sydney);
            manila.setAdjacentCities(sanfrancisco);

            shanghai.setAdjacentCities(bejing);
            shanghai.setAdjacentCities(seoul);
            shanghai.setAdjacentCities(tokyo);
            shanghai.setAdjacentCities(taipei);
            shanghai.setAdjacentCities(hongkong);

            hongkong.setAdjacentCities(shanghai);
            hongkong.setAdjacentCities(taipei);
            hongkong.setAdjacentCities(manila);
            hongkong.setAdjacentCities(hochiminhcity);
            hongkong.setAdjacentCities(bangkok);
            hongkong.setAdjacentCities(kolkata);

            osaka.setAdjacentCities(tokyo);
            osaka.setAdjacentCities(taipei);




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
            InfectionCard.add(washington_infection);
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
            InfectionCard.add(kinshasa_infection);
            InfectionCard.add(miami_infection);
            InfectionCard.add(milan_infection);
            InfectionCard.add(manila_infection);
            InfectionCard.add(shanghai_infection);
            InfectionCard.add(hongkong_infection);
            InfectionCard.add(osaka_infection);

}
}

