package com.example.joann.pandemic.pandemic;

import android.graphics.Point;

import com.example.joann.pandemic.game.GameComputerPlayer;
import com.example.joann.pandemic.game.infoMsg.GameInfo;

public class PandemicComputerPlayerSmart extends GameComputerPlayer {

    private City theDesiredCity = null;
    private boolean desiredCityIsNull = true;


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

                try {
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
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
                    int mostDiseaseCubes = 0;

                    for (City c : state.getPlayer().getCurrentLocation().getAdjacentCities()) {
                        if (c.getDiseaseCubes().size() > mostDiseaseCubes) {
                            mostDiseaseCubes = c.getDiseaseCubes().size();
                            theDesiredCity = c;
                            desiredCityIsNull = false;
                        }
                    }

                    if(desiredCityIsNull) {
                        theDesiredCity = state.getPlayer().getCurrentLocation().getAdjacentCities().get(1);
                    }


                }

                    if (state.getPlayer().getActionsLeft() > 0) {
                        try {
                            Thread.sleep(3000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        MoveAction moveInstance = new MoveAction(this, theDesiredCity);
                        game.sendAction(moveInstance);
                    }

                    for (int i = 0; i < state.getPlayer().getActionsLeft(); i++) {
                        if(state.getPlayer().getCurrentLocation().getDiseaseCubes().size() > 0) {
                            try {
                                Thread.sleep(2000);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                            TreatAction treatInstance = new TreatAction(this);
                            game.sendAction(treatInstance);
                        }
                    }

                    for (int i = 0; i < state.getPlayer().getActionsLeft(); i++){
                        try {
                            Thread.sleep(2000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                            PassAction passAction = new PassAction(this);
                            game.sendAction(passAction);
                    }

                }


                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

            }// receiveInfo
        }







