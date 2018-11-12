package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.actionMsg.GameAction;

public class BuildAction extends GameAction {
    private PlayerCard chosenCard;
    BuildAction(PandemicHumanPlayer player, PlayerCard chosenCard){
        super(player);

    }
    BuildAction(PandemicComputerPlayer player, PlayerCard chosenCard){

        super(player);
        this.chosenCard = chosenCard;
    }

    public PlayerCard getChosenCard() {
        return chosenCard;
    }
}
