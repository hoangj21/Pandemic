package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GameHumanPlayer;
import com.example.joann.pandemic.game.GameMainActivity;
import com.example.joann.pandemic.R;
import com.example.joann.pandemic.game.infoMsg.GameInfo;
import com.example.joann.pandemic.game.infoMsg.GameState;
import com.example.joann.pandemic.game.infoMsg.NotYourTurnInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI for a human to play Pandemic.
 *
 *
 * @author Kelsi Cruz, Joanna Hoang, Sarah Schibel, Polina Gannotskaya
 * @version November 2018
 */



public class PandemicHumanPlayer extends GameHumanPlayer implements OnClickListener {

    /* instance variables */

    // These variables will reference widgets that will be modified during play
    //May not need all of these, they are from Pig
    private TextView    playerScoreTextView = null;
    private TextView    oppScoreTextView    = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton PlayerImageButton      = null;
    private Button      holdButton          = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public PandemicHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view hierarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.MapView);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

        if(info instanceof PandemicGameState) {
            info.setGame(game);
            PandemicGameState theState = (PandemicGameState) info;

            /*if (theState.getID() == 0)
            {
                //View v = this.getWindow().getDecorView().findViewById(R.id.top_gui_layout);
                //v.setBackgroundColor(Color.RED);


                playerScoreTextView.setText(String.valueOf(theState.getScore0()));
                oppScoreTextView.setText(String.valueOf(theState.getScore1()));
                turnTotalTextView.setText(String.valueOf(theState.getRunningTotal()));
            }
            /*else {
                playerScoreTextView.setText(String.valueOf(theState.getScore1()));
                oppScoreTextView.setText(String.valueOf(theState.getScore0()));
                turnTotalTextView.setText(String.valueOf(theState.getRunningTotal()));
            }*/


            switch(theState.getPlayerHand(1)) {
                case 1: PlayerImageButton.setImageResource(R.drawable.algiers);
                    break;
                case 2: PlayerImageButton.setImageResource(R.drawable.atlanta);
                    break;
                case 3: PlayerImageButton.setImageResource(R.drawable.baghidad);
                    break;
                case 4: PlayerImageButton.setImageResource(R.drawable.bangkok);
                    break;
                case 5: PlayerImageButton.setImageResource(R.drawable.bejing);
                    break;
                case 6: PlayerImageButton.setImageResource(R.drawable.beunosaires);
                    break;
                case 7: PlayerImageButton.setImageResource(R.drawable.bogota);
                    break;
                case 8: PlayerImageButton.setImageResource(R.drawable.cairo);
                    break;
                case 9: PlayerImageButton.setImageResource(R.drawable.chennai);
                    break;
                case 10: PlayerImageButton.setImageResource(R.drawable.chicago);
                    break;
                case 11: PlayerImageButton.setImageResource(R.drawable.delhi);
                    break;
                case 12: PlayerImageButton.setImageResource(R.drawable.essen);
                    break;
                case 13: PlayerImageButton.setImageResource(R.drawable.hochiminhcity);
                    break;
                case 14: PlayerImageButton.setImageResource(R.drawable.hongkong);
                    break;
                case 15: PlayerImageButton.setImageResource(R.drawable.istanbul);
                    break;
                case 16: PlayerImageButton.setImageResource(R.drawable.jakarta);
                    break;
                case 17: PlayerImageButton.setImageResource(R.drawable.johannesburg);
                    break;
                case 18: PlayerImageButton.setImageResource(R.drawable.karachi);
                    break;
                case 19: PlayerImageButton.setImageResource(R.drawable.khartoum);
                    break;
                case 20: PlayerImageButton.setImageResource(R.drawable.kinshasa);
                    break;
                case 21: PlayerImageButton.setImageResource(R.drawable.kolkata);
                    break;
                case 22: PlayerImageButton.setImageResource(R.drawable.lagos);
                    break;
                case 23: PlayerImageButton.setImageResource(R.drawable.lima);
                    break;
                case 24: PlayerImageButton.setImageResource(R.drawable.london);
                    break;
                case 25: PlayerImageButton.setImageResource(R.drawable.losangeles);
                    break;
                case 26: PlayerImageButton.setImageResource(R.drawable.madrid);
                    break;
                case 27: PlayerImageButton.setImageResource(R.drawable.manila);
                    break;
                case 28: PlayerImageButton.setImageResource(R.drawable.mexicocity);
                    break;
                case 29: PlayerImageButton.setImageResource(R.drawable.miami);
                    break;
                case 30: PlayerImageButton.setImageResource(R.drawable.milan);
                    break;
                case 31: PlayerImageButton.setImageResource(R.drawable.montreal);
                    break;
                case 32: PlayerImageButton.setImageResource(R.drawable.moscow);
                    break;
                case 33: PlayerImageButton.setImageResource(R.drawable.mumbai);
                    break;
                case 34: PlayerImageButton.setImageResource(R.drawable.newyork);
                    break;
                case 35: PlayerImageButton.setImageResource(R.drawable.osaka);
                    break;
                case 36: PlayerImageButton.setImageResource(R.drawable.paris);
                    break;
                case 37: PlayerImageButton.setImageResource(R.drawable.stpetersburg);
                    break;
                case 38: PlayerImageButton.setImageResource(R.drawable.riyadh);
                    break;
                case 39: PlayerImageButton.setImageResource(R.drawable.sanfrancisco);
                    break;
                case 40: PlayerImageButton.setImageResource(R.drawable.santiago);
                    break;
                case 41: PlayerImageButton.setImageResource(R.drawable.saopaulo);
                    break;
                case 42: PlayerImageButton.setImageResource(R.drawable.seoul);
                    break;
                case 43: PlayerImageButton.setImageResource(R.drawable.shanghai);
                    break;
                case 44: PlayerImageButton.setImageResource(R.drawable.sydney);
                    break;
                case 45: PlayerImageButton.setImageResource(R.drawable.taipei);
                    break;
                case 46: PlayerImageButton.setImageResource(R.drawable.tehran);
                    break;
                case 47: PlayerImageButton.setImageResource(R.drawable.tokyo);
                    break;
                case 48: PlayerImageButton.setImageResource(R.drawable.washington);
                    break;
            }
            switch(theState.getPlayerDeck()) {
                case 1: PlayerImageButton.setImageResource(R.drawable.algiers);
                    break;
                case 2: PlayerImageButton.setImageResource(R.drawable.atlanta);
                    break;
                case 3: PlayerImageButton.setImageResource(R.drawable.baghidad);
                    break;
                case 4: PlayerImageButton.setImageResource(R.drawable.bangkok);
                    break;
                case 5: PlayerImageButton.setImageResource(R.drawable.bejing);
                    break;
                case 6: PlayerImageButton.setImageResource(R.drawable.beunosaires);
                    break;
                case 7: PlayerImageButton.setImageResource(R.drawable.bogota);
                    break;
                case 8: PlayerImageButton.setImageResource(R.drawable.cairo);
                    break;
                case 9: PlayerImageButton.setImageResource(R.drawable.chennai);
                    break;
                case 10: PlayerImageButton.setImageResource(R.drawable.chicago);
                    break;
                case 11: PlayerImageButton.setImageResource(R.drawable.delhi);
                    break;
                case 12: PlayerImageButton.setImageResource(R.drawable.essen);
                    break;
                case 13: PlayerImageButton.setImageResource(R.drawable.hochiminhcity);
                    break;
                case 14: PlayerImageButton.setImageResource(R.drawable.hongkong);
                    break;
                case 15: PlayerImageButton.setImageResource(R.drawable.istanbul);
                    break;
                case 16: PlayerImageButton.setImageResource(R.drawable.jakarta);
                    break;
                case 17: PlayerImageButton.setImageResource(R.drawable.johannesburg);
                    break;
                case 18: PlayerImageButton.setImageResource(R.drawable.karachi);
                    break;
                case 19: PlayerImageButton.setImageResource(R.drawable.khartoum);
                    break;
                case 20: PlayerImageButton.setImageResource(R.drawable.kinshasa);
                    break;
                case 21: PlayerImageButton.setImageResource(R.drawable.kolkata);
                    break;
                case 22: PlayerImageButton.setImageResource(R.drawable.lagos);
                    break;
                case 23: PlayerImageButton.setImageResource(R.drawable.lima);
                    break;
                case 24: PlayerImageButton.setImageResource(R.drawable.london);
                    break;
                case 25: PlayerImageButton.setImageResource(R.drawable.losangeles);
                    break;
                case 26: PlayerImageButton.setImageResource(R.drawable.madrid);
                    break;
                case 27: PlayerImageButton.setImageResource(R.drawable.manila);
                    break;
                case 28: PlayerImageButton.setImageResource(R.drawable.mexicocity);
                    break;
                case 29: PlayerImageButton.setImageResource(R.drawable.miami);
                    break;
                case 30: PlayerImageButton.setImageResource(R.drawable.milan);
                    break;
                case 31: PlayerImageButton.setImageResource(R.drawable.montreal);
                    break;
                case 32: PlayerImageButton.setImageResource(R.drawable.moscow);
                    break;
                case 33: PlayerImageButton.setImageResource(R.drawable.mumbai);
                    break;
                case 34: PlayerImageButton.setImageResource(R.drawable.newyork);
                    break;
                case 35: PlayerImageButton.setImageResource(R.drawable.osaka);
                    break;
                case 36: PlayerImageButton.setImageResource(R.drawable.paris);
                    break;
                case 37: PlayerImageButton.setImageResource(R.drawable.stpetersburg);
                    break;
                case 38: PlayerImageButton.setImageResource(R.drawable.riyadh);
                    break;
                case 39: PlayerImageButton.setImageResource(R.drawable.sanfrancisco);
                    break;
                case 40: PlayerImageButton.setImageResource(R.drawable.santiago);
                    break;
                case 41: PlayerImageButton.setImageResource(R.drawable.saopaulo);
                    break;
                case 42: PlayerImageButton.setImageResource(R.drawable.seoul);
                    break;
                case 43: PlayerImageButton.setImageResource(R.drawable.shanghai);
                    break;
                case 44: PlayerImageButton.setImageResource(R.drawable.sydney);
                    break;
                case 45: PlayerImageButton.setImageResource(R.drawable.taipei);
                    break;
                case 46: PlayerImageButton.setImageResource(R.drawable.tehran);
                    break;
                case 47: PlayerImageButton.setImageResource(R.drawable.tokyo);
                    break;
                case 48: PlayerImageButton.setImageResource(R.drawable.washington);
                    break;
        }

            switch(theState.getPlayerDiscardDeck()) {
                case 1: PlayerImageButton.setImageResource(R.drawable.algiers);
                    break;
                case 2: PlayerImageButton.setImageResource(R.drawable.atlanta);
                    break;
                case 3: PlayerImageButton.setImageResource(R.drawable.baghidad);
                    break;
                case 4: PlayerImageButton.setImageResource(R.drawable.bangkok);
                    break;
                case 5: PlayerImageButton.setImageResource(R.drawable.bejing);
                    break;
                case 6: PlayerImageButton.setImageResource(R.drawable.beunosaires);
                    break;
                case 7: PlayerImageButton.setImageResource(R.drawable.bogota);
                    break;
                case 8: PlayerImageButton.setImageResource(R.drawable.cairo);
                    break;
                case 9: PlayerImageButton.setImageResource(R.drawable.chennai);
                    break;
                case 10: PlayerImageButton.setImageResource(R.drawable.chicago);
                    break;
                case 11: PlayerImageButton.setImageResource(R.drawable.delhi);
                    break;
                case 12: PlayerImageButton.setImageResource(R.drawable.essen);
                    break;
                case 13: PlayerImageButton.setImageResource(R.drawable.hochiminhcity);
                    break;
                case 14: PlayerImageButton.setImageResource(R.drawable.hongkong);
                    break;
                case 15: PlayerImageButton.setImageResource(R.drawable.istanbul);
                    break;
                case 16: PlayerImageButton.setImageResource(R.drawable.jakarta);
                    break;
                case 17: PlayerImageButton.setImageResource(R.drawable.johannesburg);
                    break;
                case 18: PlayerImageButton.setImageResource(R.drawable.karachi);
                    break;
                case 19: PlayerImageButton.setImageResource(R.drawable.khartoum);
                    break;
                case 20: PlayerImageButton.setImageResource(R.drawable.kinshasa);
                    break;
                case 21: PlayerImageButton.setImageResource(R.drawable.kolkata);
                    break;
                case 22: PlayerImageButton.setImageResource(R.drawable.lagos);
                    break;
                case 23: PlayerImageButton.setImageResource(R.drawable.lima);
                    break;
                case 24: PlayerImageButton.setImageResource(R.drawable.london);
                    break;
                case 25: PlayerImageButton.setImageResource(R.drawable.losangeles);
                    break;
                case 26: PlayerImageButton.setImageResource(R.drawable.madrid);
                    break;
                case 27: PlayerImageButton.setImageResource(R.drawable.manila);
                    break;
                case 28: PlayerImageButton.setImageResource(R.drawable.mexicocity);
                    break;
                case 29: PlayerImageButton.setImageResource(R.drawable.miami);
                    break;
                case 30: PlayerImageButton.setImageResource(R.drawable.milan);
                    break;
                case 31: PlayerImageButton.setImageResource(R.drawable.montreal);
                    break;
                case 32: PlayerImageButton.setImageResource(R.drawable.moscow);
                    break;
                case 33: PlayerImageButton.setImageResource(R.drawable.mumbai);
                    break;
                case 34: PlayerImageButton.setImageResource(R.drawable.newyork);
                    break;
                case 35: PlayerImageButton.setImageResource(R.drawable.osaka);
                    break;
                case 36: PlayerImageButton.setImageResource(R.drawable.paris);
                    break;
                case 37: PlayerImageButton.setImageResource(R.drawable.stpetersburg);
                    break;
                case 38: PlayerImageButton.setImageResource(R.drawable.riyadh);
                    break;
                case 39: PlayerImageButton.setImageResource(R.drawable.sanfrancisco);
                    break;
                case 40: PlayerImageButton.setImageResource(R.drawable.santiago);
                    break;
                case 41: PlayerImageButton.setImageResource(R.drawable.saopaulo);
                    break;
                case 42: PlayerImageButton.setImageResource(R.drawable.seoul);
                    break;
                case 43: PlayerImageButton.setImageResource(R.drawable.shanghai);
                    break;
                case 44: PlayerImageButton.setImageResource(R.drawable.sydney);
                    break;
                case 45: PlayerImageButton.setImageResource(R.drawable.taipei);
                    break;
                case 46: PlayerImageButton.setImageResource(R.drawable.tehran);
                    break;
                case 47: PlayerImageButton.setImageResource(R.drawable.tokyo);
                    break;
                case 48: PlayerImageButton.setImageResource(R.drawable.washington);
                    break;
            }

        else{
            flash(Color.BLACK, 5);
            return;
        }
    //}receiveInfo

}

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {

        BuildAction buildAction = new BuildAction(this);
        TreatAction treatAction = new TreatAction(this);
        CureAction cureAction = new CureAction(this);
        ShareAction shareAction = new ShareAction(this);
        MoveAction moveAction = new MoveAction(this);

        if(button == buildButton)
        {
            game.sendAction(buildAction);
        }
        if(button == treatButton)
        {
            game.sendAction(treatAction);
        }
        if(button == cureButton)
        {
            game.sendAction(cureAction);
        }
        if(button == shareButton)
        {
            game.sendAction(shareAction);
        }
        if(button == moveButton)
        {
            game.sendAction(moveAction);
        }
        /*PigRollAction rollAction = new PigRollAction(this);
        PigHoldAction holdAction = new PigHoldAction(this);


        if (button == holdButton) {
            game.sendAction(holdAction);
        }
        else
            game.sendAction(rollAction);
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */}


    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        //activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        /*this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);

        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);*/

    }//setAsGui

}// class PigHumanPlayer



