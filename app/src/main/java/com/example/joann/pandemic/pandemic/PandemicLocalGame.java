package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GamePlayer;
import com.example.joann.pandemic.game.LocalGame;
import com.example.joann.pandemic.game.actionMsg.GameAction;

import java.util.Random;


public class PandemicLocalGame extends LocalGame {

    PandemicGameState state;
    public PandemicLocalGame()
    {
        state = new PandemicGameState();
    }

    @Override
    protected boolean canMove(int playerId){


    }

    @Override
    protected boolean makeMove(GameAction action) {
        //update state here

    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PandemicGameState stateCopy = new PandemicGameState(state);
        p.sendInfo(stateCopy);
        //TODO  You will implement this method

    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
    }

}


