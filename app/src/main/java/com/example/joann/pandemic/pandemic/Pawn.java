package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

/************************************
 * Object class that creates the pawn info
 * that determines the pawns id and the role that they have
 * based upon variables:
 * the id given to the pawn based upon player info: pawnId
 * the player information that will be linked to the pawn: thePlayer
 * the role the pawn contains: role
 *
 ************************************/

/************************************
 * POSSIBLE ROLES
 * -operations expert: green
 * -medic: orange
 * -scientist: purple
 *
 ************************************/


public class Pawn {

    protected int pawnId;
    protected PlayerInfo thePlayer;
    protected String role;

    //Default constructor
    public Pawn(int aPawnId, PlayerInfo aPlayer, String aRole)
    {
        pawnId = aPawnId;
        thePlayer = aPlayer;
        role = aRole;
    }
    //copy constructor
    public Pawn(Pawn aPawn)
    {
        this.pawnId = aPawn.pawnId;
        this.thePlayer = aPawn.thePlayer;
        this.role = aPawn.role;
    }
    //getters for all the variables
    public int getPawnId() {
        return pawnId;
    }

    public PlayerInfo getThePlayer() {
        return thePlayer;
    }

    public String getrole() {
        return role;
    }

}
