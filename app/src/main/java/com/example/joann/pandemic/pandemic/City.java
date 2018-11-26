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
        //if(diseaseCubes.size() < 3)
            diseaseCubes.add(cube);

    }
    public String removeDiseaseCube(){
        if(diseaseCubes.size()!=0) {
            String color = diseaseCubes.get(0).getCubeColor();
            diseaseCubes.remove(0);
            return color;
        }
        return null;
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


}
