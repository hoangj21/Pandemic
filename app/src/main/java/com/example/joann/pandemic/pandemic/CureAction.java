package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

/**
 * CureAction class extends GameAction, allows
 * player to cure a disease.
 *
 * @version November 2018
 */

public class CureAction extends GameAction {
    CureAction(PandemicHumanPlayer player){
        super(player);

    }
    CureAction(PandemicComputerPlayer player){
        super(player);
    }


}
