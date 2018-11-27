package com.example.joann.pandemic.pandemic;

import com.example.joann.pandemic.game.GameHumanPlayer;
import com.example.joann.pandemic.game.GameMainActivity;
import com.example.joann.pandemic.R;
import com.example.joann.pandemic.game.infoMsg.GameInfo;
import com.example.joann.pandemic.game.infoMsg.GameState;
import com.example.joann.pandemic.game.infoMsg.NotYourTurnInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

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

    private Button  buildButton = null;
    private Button  treatButton = null;
    private Button  cureButton = null;
    private Button  shareButton = null;
    private Button  moveButton = null;
    private Button  passButton = null;
    private ImageButton PlayerImageButton = null;

    // the android activity that we are running
    private GameMainActivity myActivity;
    private MapView myMapView;
    private PandemicGameState theState;
    private City city = new City("");
    private boolean isClicked = false;
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
            theState= new PandemicGameState();
            theState = (PandemicGameState) info;

            myMapView.setState(theState);
            myMapView.postInvalidate();


    /*
            if (theState.getID() == 0)
            {
                View v = this.getWindow().getDecorView().findViewById(R.id.top_gui_layout);
                v.setBackgroundColor(Color.RED);


                playerScoreTextView.setText(String.valueOf(theState.getScore0()));
                oppScoreTextView.setText(String.valueOf(theState.getScore1()));
                turnTotalTextView.setText(String.valueOf(theState.getRunningTotal()));
            }
            else {
                playerScoreTextView.setText(String.valueOf(theState.getScore1()));
                oppScoreTextView.setText(String.valueOf(theState.getScore0()));
                turnTotalTextView.setText(String.valueOf(theState.getRunningTotal()));
            }
*/




        for(int i = 0; i < theState.getPlayer().getPlayerHand().size(); i++){
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                 PlayerImageButton.setImageResource(R.drawable.algiers);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.atlanta);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.baghidad);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.bangkok);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.bejing);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.beunosaires);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.bogota);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.cairo);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.chennai);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.chicago);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.delhi);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.essen);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.hochiminhcity);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.hongkong);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.istanbul);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.jakarta);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.johannesburg);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.karachi);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.khartoum);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.kinshasa);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.kolkata);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.lagos);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.lima);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.london);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.losangeles);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.madrid);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.manila);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.mexicocity);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.miami);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.milan);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.montreal);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.moscow);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.mumbai);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.newyork);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.osaka);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.paris);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.stpetersburg);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.riyadh);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.sanfrancisco);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.santiago);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.saopaulo);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.seoul);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.shanghai);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.sydney);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.taipei);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.tehran);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.tokyo);
            }
            if(((PlayerCard)theState.getPlayer().getPlayerHand().get(i)).getLocation() == theState.getAllCities().get(i))
            {
                PlayerImageButton.setImageResource(R.drawable.washington);
            }


            }

            for(int i = 0; i < theState.getPlayerDeck().size(); i++){
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(0))
                {
                    PlayerImageButton.setImageResource(R.drawable.algiers);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(1))
                {
                    PlayerImageButton.setImageResource(R.drawable.atlanta);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(2))
                {
                    PlayerImageButton.setImageResource(R.drawable.baghidad);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(3))
                {
                    PlayerImageButton.setImageResource(R.drawable.bangkok);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(4))
                {
                    PlayerImageButton.setImageResource(R.drawable.bejing);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(5))
                {
                    PlayerImageButton.setImageResource(R.drawable.beunosaires);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(6))
                {
                    PlayerImageButton.setImageResource(R.drawable.bogota);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(7))
                {
                    PlayerImageButton.setImageResource(R.drawable.cairo);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(8))
                {
                    PlayerImageButton.setImageResource(R.drawable.chennai);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(9))
                {
                    PlayerImageButton.setImageResource(R.drawable.chicago);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(10))
                {
                    PlayerImageButton.setImageResource(R.drawable.delhi);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(11))
                {
                    PlayerImageButton.setImageResource(R.drawable.essen);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(12))
                {
                    PlayerImageButton.setImageResource(R.drawable.hochiminhcity);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(13))
                {
                    PlayerImageButton.setImageResource(R.drawable.hongkong);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(14))
                {
                    PlayerImageButton.setImageResource(R.drawable.istanbul);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(15))
                {
                    PlayerImageButton.setImageResource(R.drawable.jakarta);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(16))
                {
                    PlayerImageButton.setImageResource(R.drawable.johannesburg);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(17))
                {
                    PlayerImageButton.setImageResource(R.drawable.karachi);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(18))
                {
                    PlayerImageButton.setImageResource(R.drawable.khartoum);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(19))
                {
                    PlayerImageButton.setImageResource(R.drawable.kinshasa);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(20))
                {
                    PlayerImageButton.setImageResource(R.drawable.kolkata);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(21))
                {
                    PlayerImageButton.setImageResource(R.drawable.lagos);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(22))
                {
                    PlayerImageButton.setImageResource(R.drawable.lima);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(23))
                {
                    PlayerImageButton.setImageResource(R.drawable.london);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(24))
                {
                    PlayerImageButton.setImageResource(R.drawable.losangeles);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(25))
                {
                    PlayerImageButton.setImageResource(R.drawable.madrid);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(26))
                {
                    PlayerImageButton.setImageResource(R.drawable.manila);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(27))
                {
                    PlayerImageButton.setImageResource(R.drawable.mexicocity);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(28))
                {
                    PlayerImageButton.setImageResource(R.drawable.miami);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(29))
                {
                    PlayerImageButton.setImageResource(R.drawable.milan);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(30))
                {
                    PlayerImageButton.setImageResource(R.drawable.montreal);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(31))
                {
                    PlayerImageButton.setImageResource(R.drawable.moscow);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(32))
                {
                    PlayerImageButton.setImageResource(R.drawable.mumbai);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(33))
                {
                    PlayerImageButton.setImageResource(R.drawable.newyork);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(34))
                {
                    PlayerImageButton.setImageResource(R.drawable.osaka);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(35))
                {
                    PlayerImageButton.setImageResource(R.drawable.paris);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(36))
                {
                    PlayerImageButton.setImageResource(R.drawable.stpetersburg);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(37))
                {
                    PlayerImageButton.setImageResource(R.drawable.riyadh);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(38))
                {
                    PlayerImageButton.setImageResource(R.drawable.sanfrancisco);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(39))
                {
                    PlayerImageButton.setImageResource(R.drawable.santiago);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(40))
                {
                    PlayerImageButton.setImageResource(R.drawable.saopaulo);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(41))
                {
                    PlayerImageButton.setImageResource(R.drawable.seoul);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(42))
                {
                    PlayerImageButton.setImageResource(R.drawable.shanghai);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(43))
                {
                    PlayerImageButton.setImageResource(R.drawable.sydney);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(44))
                {
                    PlayerImageButton.setImageResource(R.drawable.taipei);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(45))
                {
                    PlayerImageButton.setImageResource(R.drawable.tehran);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(46))
                {
                    PlayerImageButton.setImageResource(R.drawable.tokyo);
                }
                if(((PlayerCard)theState.getPlayerDeck().get(i)).getLocation() == theState.getAllCities().get(47))
                {
                    PlayerImageButton.setImageResource(R.drawable.washington);
                }


            }

            for(int i = 0; i < theState.getPlayerDiscardDeck().size(); i++){
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(0))
                {
                    PlayerImageButton.setImageResource(R.drawable.algiers);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(1))
                {
                    PlayerImageButton.setImageResource(R.drawable.atlanta);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(2))
                {
                    PlayerImageButton.setImageResource(R.drawable.baghidad);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(3))
                {
                    PlayerImageButton.setImageResource(R.drawable.bangkok);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(4))
                {
                    PlayerImageButton.setImageResource(R.drawable.bejing);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(5))
                {
                    PlayerImageButton.setImageResource(R.drawable.beunosaires);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(6))
                {
                    PlayerImageButton.setImageResource(R.drawable.bogota);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(7))
                {
                    PlayerImageButton.setImageResource(R.drawable.cairo);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(8))
                {
                    PlayerImageButton.setImageResource(R.drawable.chennai);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(9))
                {
                    PlayerImageButton.setImageResource(R.drawable.chicago);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(10))
                {
                    PlayerImageButton.setImageResource(R.drawable.delhi);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(11))
                {
                    PlayerImageButton.setImageResource(R.drawable.essen);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(12))
                {
                    PlayerImageButton.setImageResource(R.drawable.hochiminhcity);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(13))
                {
                    PlayerImageButton.setImageResource(R.drawable.hongkong);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(14))
                {
                    PlayerImageButton.setImageResource(R.drawable.istanbul);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(15))
                {
                    PlayerImageButton.setImageResource(R.drawable.jakarta);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(16))
                {
                    PlayerImageButton.setImageResource(R.drawable.johannesburg);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(17))
                {
                    PlayerImageButton.setImageResource(R.drawable.karachi);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(18))
                {
                    PlayerImageButton.setImageResource(R.drawable.khartoum);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(19))
                {
                    PlayerImageButton.setImageResource(R.drawable.kinshasa);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(20))
                {
                    PlayerImageButton.setImageResource(R.drawable.kolkata);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(21))
                {
                    PlayerImageButton.setImageResource(R.drawable.lagos);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(22))
                {
                    PlayerImageButton.setImageResource(R.drawable.lima);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(23))
                {
                    PlayerImageButton.setImageResource(R.drawable.london);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(24))
                {
                    PlayerImageButton.setImageResource(R.drawable.losangeles);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(25))
                {
                    PlayerImageButton.setImageResource(R.drawable.madrid);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(26))
                {
                    PlayerImageButton.setImageResource(R.drawable.manila);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(27))
                {
                    PlayerImageButton.setImageResource(R.drawable.mexicocity);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(28))
                {
                    PlayerImageButton.setImageResource(R.drawable.miami);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(29))
                {
                    PlayerImageButton.setImageResource(R.drawable.milan);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(30))
                {
                    PlayerImageButton.setImageResource(R.drawable.montreal);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(31))
                {
                    PlayerImageButton.setImageResource(R.drawable.moscow);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(32))
                {
                    PlayerImageButton.setImageResource(R.drawable.mumbai);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(33))
                {
                    PlayerImageButton.setImageResource(R.drawable.newyork);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(34))
                {
                    PlayerImageButton.setImageResource(R.drawable.osaka);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(35))
                {
                    PlayerImageButton.setImageResource(R.drawable.paris);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(36))
                {
                    PlayerImageButton.setImageResource(R.drawable.stpetersburg);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(37))
                {
                    PlayerImageButton.setImageResource(R.drawable.riyadh);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(38))
                {
                    PlayerImageButton.setImageResource(R.drawable.sanfrancisco);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(39))
                {
                    PlayerImageButton.setImageResource(R.drawable.santiago);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(40))
                {
                    PlayerImageButton.setImageResource(R.drawable.saopaulo);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(41))
                {
                    PlayerImageButton.setImageResource(R.drawable.seoul);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(42))
                {
                    PlayerImageButton.setImageResource(R.drawable.shanghai);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(43))
                {
                    PlayerImageButton.setImageResource(R.drawable.sydney);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(44))
                {
                    PlayerImageButton.setImageResource(R.drawable.taipei);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(45))
                {
                    PlayerImageButton.setImageResource(R.drawable.tehran);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(46))
                {
                    PlayerImageButton.setImageResource(R.drawable.tokyo);
                }
                if(((PlayerCard)theState.getPlayerDiscardDeck().get(i)).getLocation() == theState.getAllCities().get(47))
                {
                    PlayerImageButton.setImageResource(R.drawable.washington);
                }


            }

            for(int i = 0; i < theState.getInfectionDeck().size(); i++){
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(0))
                {
                    PlayerImageButton.setImageResource(R.drawable.algiers);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(1))
                {
                    PlayerImageButton.setImageResource(R.drawable.atlanta);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(2))
                {
                    PlayerImageButton.setImageResource(R.drawable.baghidad);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(3))
                {
                    PlayerImageButton.setImageResource(R.drawable.bangkok);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(4))
                {
                    PlayerImageButton.setImageResource(R.drawable.bejing);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(5))
                {
                    PlayerImageButton.setImageResource(R.drawable.beunosaires);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(6))
                {
                    PlayerImageButton.setImageResource(R.drawable.bogota);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(7))
                {
                    PlayerImageButton.setImageResource(R.drawable.cairo);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(8))
                {
                    PlayerImageButton.setImageResource(R.drawable.chennai);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(9))
                {
                    PlayerImageButton.setImageResource(R.drawable.chicago);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(10))
                {
                    PlayerImageButton.setImageResource(R.drawable.delhi);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(11))
                {
                    PlayerImageButton.setImageResource(R.drawable.essen);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(12))
                {
                    PlayerImageButton.setImageResource(R.drawable.hochiminhcity);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(13))
                {
                    PlayerImageButton.setImageResource(R.drawable.hongkong);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(14))
                {
                    PlayerImageButton.setImageResource(R.drawable.istanbul);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(15))
                {
                    PlayerImageButton.setImageResource(R.drawable.jakarta);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(16))
                {
                    PlayerImageButton.setImageResource(R.drawable.johannesburg);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(17))
                {
                    PlayerImageButton.setImageResource(R.drawable.karachi);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(18))
                {
                    PlayerImageButton.setImageResource(R.drawable.khartoum);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(19))
                {
                    PlayerImageButton.setImageResource(R.drawable.kinshasa);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(20))
                {
                    PlayerImageButton.setImageResource(R.drawable.kolkata);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(21))
                {
                    PlayerImageButton.setImageResource(R.drawable.lagos);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(22))
                {
                    PlayerImageButton.setImageResource(R.drawable.lima);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(23))
                {
                    PlayerImageButton.setImageResource(R.drawable.london);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(24))
                {
                    PlayerImageButton.setImageResource(R.drawable.losangeles);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(25))
                {
                    PlayerImageButton.setImageResource(R.drawable.madrid);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(26))
                {
                    PlayerImageButton.setImageResource(R.drawable.manila);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(27))
                {
                    PlayerImageButton.setImageResource(R.drawable.mexicocity);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(28))
                {
                    PlayerImageButton.setImageResource(R.drawable.miami);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(29))
                {
                    PlayerImageButton.setImageResource(R.drawable.milan);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(30))
                {
                    PlayerImageButton.setImageResource(R.drawable.montreal);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(31))
                {
                    PlayerImageButton.setImageResource(R.drawable.moscow);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(32))
                {
                    PlayerImageButton.setImageResource(R.drawable.mumbai);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(33))
                {
                    PlayerImageButton.setImageResource(R.drawable.newyork);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(34))
                {
                    PlayerImageButton.setImageResource(R.drawable.osaka);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(35))
                {
                    PlayerImageButton.setImageResource(R.drawable.paris);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(36))
                {
                    PlayerImageButton.setImageResource(R.drawable.stpetersburg);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(37))
                {
                    PlayerImageButton.setImageResource(R.drawable.riyadh);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(38))
                {
                    PlayerImageButton.setImageResource(R.drawable.sanfrancisco);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(39))
                {
                    PlayerImageButton.setImageResource(R.drawable.santiago);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(40))
                {
                    PlayerImageButton.setImageResource(R.drawable.saopaulo);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(41))
                {
                    PlayerImageButton.setImageResource(R.drawable.seoul);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(42))
                {
                    PlayerImageButton.setImageResource(R.drawable.shanghai);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(43))
                {
                    PlayerImageButton.setImageResource(R.drawable.sydney);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(44))
                {
                    PlayerImageButton.setImageResource(R.drawable.taipei);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(45))
                {
                    PlayerImageButton.setImageResource(R.drawable.tehran);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(46))
                {
                    PlayerImageButton.setImageResource(R.drawable.tokyo);
                }
                if(theState.getInfectionDeck().get(i).getLocation() == theState.getAllCities().get(47))
                {
                    PlayerImageButton.setImageResource(R.drawable.washington);
                }

            }
            for(int i = 0; i < theState.getInfectionDiscardDeck().size(); i++){
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(0))
                {
                    PlayerImageButton.setImageResource(R.drawable.algiers);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(1))
                {
                    PlayerImageButton.setImageResource(R.drawable.atlanta);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(2))
                {
                    PlayerImageButton.setImageResource(R.drawable.baghidad);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(3))
                {
                    PlayerImageButton.setImageResource(R.drawable.bangkok);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(4))
                {
                    PlayerImageButton.setImageResource(R.drawable.bejing);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(5))
                {
                    PlayerImageButton.setImageResource(R.drawable.beunosaires);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(6))
                {
                    PlayerImageButton.setImageResource(R.drawable.bogota);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(7))
                {
                    PlayerImageButton.setImageResource(R.drawable.cairo);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(8))
                {
                    PlayerImageButton.setImageResource(R.drawable.chennai);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(9))
                {
                    PlayerImageButton.setImageResource(R.drawable.chicago);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(10))
                {
                    PlayerImageButton.setImageResource(R.drawable.delhi);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(11))
                {
                    PlayerImageButton.setImageResource(R.drawable.essen);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(12))
                {
                    PlayerImageButton.setImageResource(R.drawable.hochiminhcity);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(13))
                {
                    PlayerImageButton.setImageResource(R.drawable.hongkong);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(14))
                {
                    PlayerImageButton.setImageResource(R.drawable.istanbul);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(15))
                {
                    PlayerImageButton.setImageResource(R.drawable.jakarta);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(16))
                {
                    PlayerImageButton.setImageResource(R.drawable.johannesburg);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(17))
                {
                    PlayerImageButton.setImageResource(R.drawable.karachi);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(18))
                {
                    PlayerImageButton.setImageResource(R.drawable.khartoum);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(19))
                {
                    PlayerImageButton.setImageResource(R.drawable.kinshasa);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(20))
                {
                    PlayerImageButton.setImageResource(R.drawable.kolkata);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(21))
                {
                    PlayerImageButton.setImageResource(R.drawable.lagos);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(22))
                {
                    PlayerImageButton.setImageResource(R.drawable.lima);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(23))
                {
                    PlayerImageButton.setImageResource(R.drawable.london);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(24))
                {
                    PlayerImageButton.setImageResource(R.drawable.losangeles);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(25))
                {
                    PlayerImageButton.setImageResource(R.drawable.madrid);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(26))
                {
                    PlayerImageButton.setImageResource(R.drawable.manila);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(27))
                {
                    PlayerImageButton.setImageResource(R.drawable.mexicocity);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(28))
                {
                    PlayerImageButton.setImageResource(R.drawable.miami);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(29))
                {
                    PlayerImageButton.setImageResource(R.drawable.milan);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(30))
                {
                    PlayerImageButton.setImageResource(R.drawable.montreal);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(31))
                {
                    PlayerImageButton.setImageResource(R.drawable.moscow);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(32))
                {
                    PlayerImageButton.setImageResource(R.drawable.mumbai);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(33))
                {
                    PlayerImageButton.setImageResource(R.drawable.newyork);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(34))
                {
                    PlayerImageButton.setImageResource(R.drawable.osaka);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(35))
                {
                    PlayerImageButton.setImageResource(R.drawable.paris);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(36))
                {
                    PlayerImageButton.setImageResource(R.drawable.stpetersburg);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(37))
                {
                    PlayerImageButton.setImageResource(R.drawable.riyadh);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(38))
                {
                    PlayerImageButton.setImageResource(R.drawable.sanfrancisco);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(39))
                {
                    PlayerImageButton.setImageResource(R.drawable.santiago);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(40))
                {
                    PlayerImageButton.setImageResource(R.drawable.saopaulo);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(41))
                {
                    PlayerImageButton.setImageResource(R.drawable.seoul);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(42))
                {
                    PlayerImageButton.setImageResource(R.drawable.shanghai);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(43))
                {
                    PlayerImageButton.setImageResource(R.drawable.sydney);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(44))
                {
                    PlayerImageButton.setImageResource(R.drawable.taipei);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(45))
                {
                    PlayerImageButton.setImageResource(R.drawable.tehran);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(46))
                {
                    PlayerImageButton.setImageResource(R.drawable.tokyo);
                }
                if(theState.getInfectionDiscardDeck().get(i).getLocation() == theState.getAllCities().get(47))
                {
                    PlayerImageButton.setImageResource(R.drawable.washington);
                }


            }
        }
        else{
            flash(Color.WHITE, 5);
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
        CureAction cureAction = new CureAction(this);
        PassAction passAction = new PassAction(this);
        ShareAction shareAction = new ShareAction(this);
        TreatAction treatAction = new TreatAction(this);

        if (button == buildButton) {
            game.sendAction(buildAction);
            //Toast.makeText(button.getContext(), theState.getMessage(), Toast.LENGTH_LONG).show();

        }
        else if(button == cureButton) {
            game.sendAction(cureAction);

        }
        else if(button == passButton){
            game.sendAction(passAction);
            //Toast.makeText(button.getContext(), theState.getMessage(), Toast.LENGTH_LONG).show();

        }
        else if(button == shareButton){
            game.sendAction(shareAction);

        }
        else if(button == treatButton){
            game.sendAction(treatAction);
            //Toast.makeText(button.getContext(), theState.getMessage(), Toast.LENGTH_LONG).show();
            int size = theState.getPlayer().getCurrentLocation().diseaseCubes.size();


        }
        else if(button == moveButton){
            Toast.makeText(button.getContext(), "Please select a city", Toast.LENGTH_SHORT).show();
           // game.sendAction(moveAction);
            isClicked = true;
        }
        else{
            isClicked = false;
        }
    }


    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */


    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);

        //Initialize the widget reference member variables
        this.buildButton = (Button)activity.findViewById(R.id.BuildButton);
        this.treatButton = (Button)activity.findViewById(R.id.TreatButton);
        this.cureButton = (Button)activity.findViewById(R.id.CureButton);
        this.shareButton = (Button)activity.findViewById(R.id.ShareButton);
        this.moveButton = (Button)activity.findViewById(R.id.MoveButton);
        this.passButton = (Button)activity.findViewById(R.id.PassButton);

        buildButton.setOnClickListener(this);
        treatButton.setOnClickListener(this);
        cureButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        moveButton.setOnClickListener(this);
        passButton.setOnClickListener(this);
        myMapView = (MapView) myActivity.findViewById(R.id.MapView);
        myMapView.setPlayer(this);
    }//setAsGui

    public void needToMakeMove(City city){
        //this.city = city;
        MoveAction moveAction = new MoveAction(this, city);
        game.sendAction(moveAction);
        //moveAction.setDesiredCity(city);



    }

    public boolean isClicked() {
        return isClicked;
        //
    }
    public boolean setIsClicked(){
        if(isClicked==true){
            isClicked = false;
        }
        return isClicked;
    }
}// class PigHumanPlayer




