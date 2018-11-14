package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GameComputerPlayer;
import com.example.joann.pandemic.game.actionMsg.GameAction;

/**
 * MoveAction class extends GameAction, allows
 * player to move their pawn. Connects state to xml.
 *
 * @version November 2018
 */

public class MoveAction extends GameAction {
    private City desiredCity;


//What does this constructor do? @Groupmates
    //A constructor is needed for both the human player and the computer player
    MoveAction(PandemicHumanPlayer player,  City desiredCity){
        super(player);
        this.desiredCity = desiredCity;

    }

    MoveAction(GameComputerPlayer player, City desiredCity){
        super(player);
        this.desiredCity = desiredCity;

    }


    public City getDesiredCity() {
        return desiredCity;
    }
}
