package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GamePlayer;
import com.example.joann.pandemic.game.LocalGame;
import com.example.joann.pandemic.game.actionMsg.GameAction;



public class PandemicLocalGame extends LocalGame {

    PandemicGameState state;
    public PandemicLocalGame()
    {
        state = new PandemicGameState();
    }

    //Makes sure it is the player's turn before their pawn is moved.
    @Override
    protected boolean canMove(int playerId){

        return playerId == state.getPlayerTurn();
    }


    //Moves the pawn where the player wants to go and updates the state.
    @Override
    protected boolean makeMove(GameAction action) {

        //update state here
        if(action instanceof MoveAction) {
            MoveAction move = (MoveAction) action;
            state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), move.getDesiredCity());
        }

        else if(action instanceof TreatAction) {
            state.treatDisease(state.getPlayer(), state.getPlayer().getCurrentLocation());
        }
        else if(action instanceof BuildAction){
            state.buildAResearchStation(state.getPlayer(), state.getPlayer().currentLocation);
        }
        else if(action instanceof CureAction){
            state.discoverACure(state.getPlayer());
        }
        else if(action instanceof PassAction){
            state.passAction(state.getPlayer());
        }

        if(state.getPlayer().actionsLeft <= 0){
            state.setMessage("Switching turns");
            state.drawPlayerCard(state.getPlayer());
            state.drawPlayerCard(state.getPlayer());
            state.drawInfectionCard();

            if(state.getPlayerTurn() == 0) {
                state.setPlayerTurn(1);
                state.getPlayers().get(0).setActionsLeft(4);
                state.setPlayer(state.getPlayers().get(1));
            }else if(state.getPlayerTurn() == 1) {
                state.setPlayerTurn(0);
                state.getPlayers().get(1).setActionsLeft(4);
                state.setPlayer(state.getPlayers().get(0));
            }
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

        //check if game lost
        if(state.getNumCubesBlack() <= 0)
        {
            return("Game Lost");
        }
        else if(state.getNumCubesBlue() <= 0)
        {
            return("Game Lost");
        }
        else if(state.getNumCubesYellow() <= 0)
        {
            return("Game Lost");
        }
        else if(state.getNumCubesRed() <= 0)
        {
            return("Game Lost");
        }
        else if(state.getNumPlayerCardsInDeck() <= 0)
        {
            return("Game Lost");
        }
        else if(state.getOutbreakNum() > 7)
        {
            return("Game Lost");
        }

        return null;
    }

}


