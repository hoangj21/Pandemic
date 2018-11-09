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
    private ImageButton dieImageButton      = null;
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


            /*switch(theState.getValueOnDie()) {
                case 1: dieImageButton.setImageResource(R.drawable.face1);
                    break;
                case 2: dieImageButton.setImageResource(R.drawable.face2);
                    break;
                case 3: dieImageButton.setImageResource(R.drawable.face3);
                    break;
                case 4: dieImageButton.setImageResource(R.drawable.face4);
                    break;
                case 5: dieImageButton.setImageResource(R.drawable.face5);
                    break;
                case 6: dieImageButton.setImageResource(R.drawable.face6);
                    break;
            }
        }
        else{
            flash(R.color.black, 5);
            return;
        }*/
    }//receiveInfo

}

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
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



