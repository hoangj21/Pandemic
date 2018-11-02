package com.example.joann.pandemic.pandemic;

/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

/************************************
 * Object class that creates the disease cube info
 * based upon the variables:
 * the disease cube color: cubeColor
 *
 ************************************/

public class DiseaseCube {

    protected String cubeColor;

    //Default constructor
    public DiseaseCube(String cubeColor)
    {
        this.cubeColor = cubeColor;
    }

    //Copy constructor
    public DiseaseCube(DiseaseCube aDiseaseCube)
    {
        this.cubeColor = aDiseaseCube.cubeColor;
    }

    //getter for all the variables
    public String getCubeColor()
    {
        return cubeColor;
    }
}
