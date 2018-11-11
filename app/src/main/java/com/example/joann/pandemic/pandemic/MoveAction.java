package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

public class MoveAction extends GameAction {
    private int moveType;

    MoveAction(PandemicHumanPlayer player, int moveType){
        super(player);
        this.moveType = moveType;

    }

    MoveAction(PandemicComputerPlayer player, int moveType){
        super(player);
        this.moveType = moveType;

    }

    public int getMoveType() {
        return moveType;
    }
}
