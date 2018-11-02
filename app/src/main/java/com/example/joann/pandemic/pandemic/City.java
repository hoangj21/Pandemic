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
    protected ArrayList<String> adjacentCities;
    protected Boolean hasResearchLab;
    protected Boolean isVisited;


    //Default constructor
    public City(String name, String name1, String name2, String name3, int count){
        this.name = name;
        diseaseCubes = new ArrayList<DiseaseCube>();
        adjacentCities = new ArrayList<String>();
            adjacentCities.add(name1);
            adjacentCities.add(name2);
            adjacentCities.add(name3);
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

    public ArrayList<String> getAdjacentCities() {
        return adjacentCities;
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
}
