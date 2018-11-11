package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

public class CureAction extends GameAction {
    CureAction(PandemicHumanPlayer player){
        super(player);

    }
    CureAction(PandemicComputerPlayer player){
        super(player);
    }


}
