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

        return playerId == state.getPlayerTurn();
    }

    @Override
    protected boolean makeMove(GameAction action) {
        //update state here
        if(action instanceof MoveAction) {
            MoveAction move = (MoveAction) action;
            int moveType = move.getMoveType();
            City desiredCity = move.getDesiredCity();
            state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), desiredCity, moveType);
        }
        if(action instanceof TreatAction) {
            TreatAction treat = (TreatAction) action;
            state.treatDisease(state.getPlayer(), state.getPlayer().getCurrentLocation());
        }
        if(action instanceof BuildAction){
            BuildAction build = (BuildAction) action;
            state.buildAResearchStation(state.getPlayer(), state.getPlayer().currentLocation);
        }

        return true;

    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PandemicGameState stateCopy = new PandemicGameState(state);
        p.sendInfo(stateCopy);


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
        int countCured = 0;
        for(int i = 0; i<state.getCuredDiseases().length; i++){
            if(state.getCuredDiseases()[i] == 1 || state.getCuredDiseases()[i] == 2){
                countCured++;
            }

        }
        if(countCured == 4){
            return("All diseases have been cured, you have won!");
        }
        return null;
    }

}


