package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GameComputerPlayer;
import com.example.joann.pandemic.game.infoMsg.GameInfo;

import java.util.Random;

/**
 * Dumb AI that makes random moves
 *
 * @version 11/10/2018
 */

public class PandemicComputerPlayerDumb extends GameComputerPlayer {
    public PandemicComputerPlayerDumb(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof PandemicGameState) {
                    PandemicGameState state = new PandemicGameState((PandemicGameState) info);

                    if (info instanceof PandemicGameState) {
                        if (state.getPlayerTurn() != this.playerNum) {
                            return;
                        }
                //delay computer player

                Random rand = new Random();

                //variable randomizes AI's action
                //temporarily set to 3, omitting ShareAction
                int action = rand.nextInt(4);
                try {
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                if (action == 0) {
                    int size = state.getPlayer().getCurrentLocation().getAdjacentCities().size();
                    action = rand.nextInt(size);
                    City city = state.getPlayer().getCurrentLocation().getAdjacentCities().get(action);
                    //action = rand.nextInt(4);
                    MoveAction moveInstance = new MoveAction(this, city);
                    game.sendAction(moveInstance);
                }
                else if (action == 1) {
                    TreatAction treatInstance = new TreatAction(this);
                    game.sendAction(treatInstance);
                }
                else if (action == 2) {

                    BuildAction buildInstance = new BuildAction(this);
                    game.sendAction(buildInstance);
                }
                else {

                    PassAction passInstance = new PassAction(this);
                    game.sendAction(passInstance);
                }

                return;
            }
        }

    }
}
