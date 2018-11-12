package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

public class MoveAction extends GameAction {
    private int moveType;
    private City desiredCity;

    MoveAction(PandemicHumanPlayer player, int moveType,  City desiredCity){
        super(player);
        this.moveType = moveType;

    }

    MoveAction(PandemicComputerPlayer player, int moveType, City desiredCity){
        super(player);
        this.moveType = moveType;
        this.desiredCity = desiredCity;

    }

    public int getMoveType() {
        return moveType;
    }

    public City getDesiredCity() {
        return desiredCity;
    }
}
