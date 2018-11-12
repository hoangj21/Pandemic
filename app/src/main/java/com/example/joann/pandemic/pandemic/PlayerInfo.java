package com.example.joann.pandemic.pandemic;
/************************************
 * @Kelsi
 * @Joanna
 * @Sarah
 * @Polina
 ************************************/

/************************************
 * Object class that creates the player info based upon variables:
 * the cards the player currently has: playerHand
 * the players role in the game: role
 * the players current location on the board: currentLocation
 * actions left in the players turn: actionsLeft
 * the players number in the gameL playerNumber
 *
 ************************************/
import java.util.ArrayList;

public class PlayerInfo {

        protected ArrayList<PlayerCard> playerHand;
        protected int role;
        protected City currentLocation;
        protected int actionsLeft;
        protected int playerNumber;

        //default constructor
        public PlayerInfo(int playerNum, int playerRole, int initActions, City startLoc) {
            playerHand = new ArrayList<>();
            playerNumber = playerNum;
            role = playerRole;
            actionsLeft = initActions;
            currentLocation = startLoc;


        }
        //copy constructor
    public PlayerInfo(PlayerInfo otherPlayerInfo){
        for(int i = 0; i<otherPlayerInfo.playerHand.size(); i++){
            this.playerHand.add(new PlayerCard(otherPlayerInfo.playerHand.get(i)));
        }
        this.role = otherPlayerInfo.role;
        this.currentLocation = otherPlayerInfo.currentLocation;
        this.actionsLeft = otherPlayerInfo.actionsLeft;
        this.playerNumber = this.getPlayerNumber();
    }

    //getters and setters for all variables
    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public ArrayList<PlayerCard> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<PlayerCard> playerHand) {
        this.playerHand = playerHand;
    }

    public int getRole(){
            return role;
        }

        public City getCurrentLocation(){
            return currentLocation;
        }

        public void setCurrentLocation(City newLocation){
            currentLocation = newLocation;
        }

        public int getActionsLeft(){
            return actionsLeft;
        }

        public void actionTaken(){
            actionsLeft--;
        }

    public boolean addCardToPlayerHand(PlayerCard newCard){ //THIS IS DIFFERENT... I FIXED IT
        if(playerHand.size() >= 7){
            return false;
        }
        else{
            playerHand.add(newCard);
            return true;
        }
    }
    //Method set to set the number fo actions left in a players turn
    public void setActionsLeft(int actionsLeft) {
        this.actionsLeft = actionsLeft;
    }
}
