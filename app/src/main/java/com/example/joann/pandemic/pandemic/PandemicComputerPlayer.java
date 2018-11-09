package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GameComputerPlayer;
import com.example.joann.pandemic.game.infoMsg.GameInfo;

public class PandemicComputerPlayer extends GameComputerPlayer {
    public PandemicComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof PandemicGameState) {
            PandemicGameState state = new PandemicGameState((PandemicGameState) info);
            if () {
                return;
            }
    }

}
