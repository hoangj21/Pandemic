package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

public class ShareAction extends GameAction {
    ShareAction(PandemicHumanPlayer player){
        super(player);

    }
    ShareAction(PandemicComputerPlayer player){
        super(player);
    }


}
