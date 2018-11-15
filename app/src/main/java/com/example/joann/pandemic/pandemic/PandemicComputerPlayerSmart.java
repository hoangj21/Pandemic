package com.example.joann.pandemic.pandemic;

import android.graphics.Point;

import com.example.joann.pandemic.game.GameComputerPlayer;
import com.example.joann.pandemic.game.infoMsg.GameInfo;

public class PandemicComputerPlayerSmart extends GameComputerPlayer {


    public PandemicComputerPlayerSmart(String name) {
        // invoke superclass constructor
        super(name);
    }// constructor


    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof PandemicGameState) {
            PandemicGameState state = new PandemicGameState((PandemicGameState) info);

            if (info instanceof PandemicGameState) {
                if (state.getPlayerTurn() != this.playerNum) {
                    return;
                }
                    //Check if AI is able to cure a disease.
                    if(state.getPlayer().getActionsLeft() >= 2) {
                        if (canCureDisease(state) && canMoveToResearchCenter(state) == state.getPlayer().getCurrentLocation()) {
                            CureAction cure = new CureAction(this);
                            game.sendAction(cure);
                        }

                        else if(canCureDisease(state) && canMoveToResearchCenter(state) != null) {
                            MoveAction moveInstance = new MoveAction(this, canMoveToResearchCenter(state));
                            game.sendAction(moveInstance);
                        }
                    }

                    //Otherwise, move to adjacent city with most disease cubes
                    //and
                    int mostDiseaseCubes = 0;
                    City theDesiredCity = null;

                    for (City c : state.getPlayer().getCurrentLocation().getAdjacentCities()) {
                        if (c.getDiseaseCubes().size() > mostDiseaseCubes) {
                            mostDiseaseCubes = c.getDiseaseCubes().size();
                            theDesiredCity = c;
                        }
                    }

                    if (state.getPlayer().getActionsLeft() > 0) {
                        MoveAction moveInstance = new MoveAction(this, theDesiredCity);
                        game.sendAction(moveInstance);
                    }

                    for (int i = 0; i < state.getPlayer().getActionsLeft(); i++) {
                        TreatAction treatInstance = new TreatAction(this);
                        game.sendAction(treatInstance);
                    }

                }


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }// receiveInfo

    }





