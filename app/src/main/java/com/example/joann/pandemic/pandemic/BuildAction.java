package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

/**
 * BuildAction class extends GameAction, allows
 * player to build a research center.
 *
 * @version November 2018
 */



public class BuildAction extends GameAction {
    BuildAction(PandemicHumanPlayer player){
        super(player);

    }
    BuildAction(PandemicComputerPlayer player){

        super(player);

    }


}
