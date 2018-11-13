package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GameComputerPlayer;
import com.example.joann.pandemic.game.actionMsg.GameAction;

/**
 * TreatAction class extends GameAction, allows
 * player to treat disease and updates state.
 *
 * @version November 2018
 */

public class TreatAction extends GameAction {
    TreatAction(PandemicHumanPlayer player){
        super(player);

    }
    TreatAction(GameComputerPlayer player){
        super(player);
    }


}
