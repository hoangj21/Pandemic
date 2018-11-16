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
    public City(String name){
        this.name = name;
        diseaseCubes = new ArrayList<DiseaseCube>();
        adjacentCities = new ArrayList<City>();
        hasResearchLab = false;
        isVisited = false;

    }

    //Copy constructor
    public City(City city){
        this.name = city.name;
        this.diseaseCubes = new ArrayList<DiseaseCube>();
        this.adjacentCities = new ArrayList<City>();
        for(int i = 0; i<diseaseCubes.size(); i++){
            this.diseaseCubes.add(i, city.diseaseCubes.get(i));
        }
        for(int i = 0; i<city.adjacentCities.size(); i++){
            this.adjacentCities.add(i, city.adjacentCities.get(i));
        }
        this.hasResearchLab = city.hasResearchLab;
        this.isVisited = city.isVisited;

    }

    //@Override
    public boolean equals(City city) {
        super.equals(city);
        if(this.name.equals(city.getName())){
            return true;

        }
        return false;
    }

    public String getName() {
        return name;
    }

    //Adds new disease cube to array of disease cubes
    public void addDiseaseCube(String cubeColor){
        DiseaseCube cube = new DiseaseCube(cubeColor);
        if(diseaseCubes.size() < 3)
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


    //Following two method is all the city start stuff


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
