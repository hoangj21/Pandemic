package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

/************************************
 * Object class that creates the city info that helps players determine
 * what actions you can do based upon the city you're in
 * based upon variables:
 * the name for the city given: name
 * how many disease cubes there are in a given city: diseaseCubes
 * what cities are neighbors to the city specified: adjacentCities
 * does the city have a research lab built there: hasResearchLab
 * has the city already been visited: isVisited
 *
 ************************************/

import java.util.ArrayList;

public class City {

    protected String name;
    protected ArrayList<DiseaseCube> diseaseCubes;
    protected ArrayList<City> adjacentCities;
    protected Boolean hasResearchLab;
    protected Boolean isVisited;

    //Default constructor
    public City(){
        //this.name = name;
        diseaseCubes = new ArrayList<DiseaseCube>();
        adjacentCities = new ArrayList<City>();
        hasResearchLab = false;
        isVisited = false;

    }

    //Copy constructor
    public City(City city){
        this.name = city.name;
        for(int i = 0; i<diseaseCubes.size(); i++){
            this.diseaseCubes.add(i, city.diseaseCubes.get(i));
        }
        for(int i = 0; i<city.adjacentCities.size(); i++){
            this.adjacentCities.add(i, city.adjacentCities.get(i));
        }
        this.hasResearchLab = city.hasResearchLab;
        this.isVisited = city.isVisited;

    }


    //Adds new disease cube to array of disease cubes
    public void addDiseaseCube(String cubeColor){
        DiseaseCube cube = new DiseaseCube(cubeColor);
        diseaseCubes.add(cube);

    }
    public void removeDiseaseCube(){
        if(diseaseCubes!=null) {
            diseaseCubes.remove(0);
        }
    }


    //getters and setters for all variables
    public ArrayList<DiseaseCube> getDiseaseCubes() {
        return diseaseCubes;
    }

    public ArrayList<City> getAdjacentCities() {
        return adjacentCities;
    }

    public void setAdjacentCities(City city)
    {
        adjacentCities.add(city);
    }

    public Boolean getHasResearchLab() {
        return hasResearchLab;
    }

    public Boolean getVisited() {
        return isVisited;
    }



    public String getName() {
        return name;
    }
    //Following two method is all the city start stuff

    public void initiation()
    {
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


    }
/*
    public int caseSwitchName (City city) {

        switch (city) {

            case algiers:
                return 1; //@drawable/algiers;
            break;

            case atlanta:
                return 1; //@drawable/atlanta;
            break;

            case baghdad:
                return 1; //@drawable/baghdad;
            break;

            case bangkok:
                return 1; //@drawable/bangkok;
            break;

            case bejing:
                return 1; //@drawable/bejing;
            break;

            case beunosaires:
                return 1; //@drawable/beunosaires;
            break;

            case bogota:
                return 1; //@drawable/bogota;
            break;

            case cairo:
                return 1; //@drawable/cairo;
            break;

            case chennai:
                return 1; //@drawable/chennai;
            break;

            case chicago:
                return 1; //@drawable/chicago;
            break;

            case delhi:
                return 1; //@drawable/delhi
            break;

            case essen:
                return 1; //@drawable/essen
            break;

            case hochiminhcity:
                return 1; //@drawable/hochiminhcity;
            break;

            case hongkong:
                return 1; //@drawable/hongkong;
            break;

            case istanbul:
                return 1; //@drawable/istanbul;
            break;

            case jakarta:
                return 1; //@drawable/jakarta;
            break;

            case johannesburg:
                return 1; //@drawable/johannesburg;
            break;

            case karachi:
                return 1; //@drawable/karachi;
            break;

            case khartoum:
                return 1; //@drawable/khartoum;
            break;

            case kinshasa:
                return 1; //@drawable/kinshasa;
            break;

            case kolkata:
                return 1; //@drawable/kolkata;
            break;

            case lagos:
                return 1; //@drawable/lagos;
            break;

            case lima:
                return 1; //@drawable/lima;
            break;

            case london:
                return 1;// @drawable/london;
            break;

            case losangeles:
                return 1; // @drawable/losangeles
            break;

            case madrid:
                return 1; // @drawable/madrid
            break;

            case manila:
                return 1; // @drawable/manila;
            break;

            case mexicocity:
                return 1; // @drawable/mexicocity;
            break;

            case miami:
                return 1; // @drawable/miami;
            break;

            case milan:
                return 1; // @drawable/milan;
            break;

            case montreal:
                return 1; // @drawable/montreal;
            break;

            case moscow:
                return 1; // @drawable/moscow;
            break;

            case mumbai:
                return 1; // @drawable/mumbai;
            break;

            case newyork:
                return 1; // @drawable/newyork;
            break;

            case osaka:
                return 1; // @drawable/osaka;
            break;

            case paris:
                return 1; // @drawable/paris;
            break;

            case petersburg:
                return 1; // @drawable/petersburg;
            break;

            case riyadh:
                return 1; // @drawable/riyadh
            break;

            case sanfrancisco:
                return 1; // @drawable/sanfrancisco;
            break;

            case santiago:
                return 1; //@drawable/santiago;
            break;

            case saopaulo:
                return 1; //@drawable/saopaulo;
            break;

            case seoul:
                return 1; //@drawable/seoul;
            break;

            case shanghai:
                return 1; //@drawable/shanghai
            break;

            case sydney:
                return 1; //@drawable/sydney;
            break;

            case taipei:
                return 1; //@drawable/taipei;
            break;

            case tehran:
                return 1; //@drawable/tehran;
            break;

            case tokyo:
                return 1; //@drawable/tokyo;
            break;

            case washington:
                return 1; //@drawable/washington;
            break;

        }
    }
*/
}
