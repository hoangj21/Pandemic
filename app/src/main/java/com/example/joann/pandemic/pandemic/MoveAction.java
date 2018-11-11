package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

public class MoveAction extends GameAction {
    MoveAction(PandemicHumanPlayer player){
        super(player);

    }
    MoveAction(PandemicComputerPlayer player){
        super(player);
    }


}
