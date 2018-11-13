package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GameComputerPlayer;
import com.example.joann.pandemic.game.actionMsg.GameAction;

public class PassAction extends GameAction{

    PassAction(PandemicHumanPlayer player){
        super(player);

    }

    PassAction(GameComputerPlayer player){
        super(player);
    }



}
