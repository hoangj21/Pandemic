package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GameComputerPlayer;
import com.example.joann.pandemic.game.actionMsg.GameAction;

/**
 * ShareAction extends GameAction and allows the player
 * to share information with another player.
 *
 * @version November 2018
 */

public class ShareAction extends GameAction {
    ShareAction(PandemicHumanPlayer player){
        super(player);

    }
    ShareAction(GameComputerPlayer player){
        super(player);
    }


}
