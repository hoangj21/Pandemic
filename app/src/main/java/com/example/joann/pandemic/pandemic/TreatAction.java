package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

public class TreatAction extends GameAction {
    TreatAction(PandemicHumanPlayer player){
        super(player);

    }
    TreatAction(PandemicComputerPlayer player){
        super(player);
    }


}
