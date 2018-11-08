/***********************
 @Kelsi
 @Joanna
 @Polina
 @Sarah
************************/

package com.example.joann.pandemic.pandemic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.joann.pandemic.R;
import com.example.joann.pandemic.game.GamePlayer;
import com.example.joann.pandemic.game.LocalGame;
import com.example.joann.pandemic.game.config.GameConfig;
import com.example.joann.pandemic.game.config.GamePlayerType;
import java.util.ArrayList;

//External citation:
// https://images-cdn.zmangames.com/us-east-1/filer_public/25/12/251252dd-1338-4f78-b90d-afe073c72363/zm7101_pandemic_rules.pdf
//Used for knowing the rules and layout of Pandemic, referenced throughout all code
public class PandemicMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Button testButton = findViewById(R.id.runTestButton);
            ButtonListener testListener = new ButtonListener();
            testButton.setOnClickListener(testListener);*/

    }

    @Override
    //External Citation:
    //https://developer.android.com/guide/topics/ui/menus
    //Referenced android documentation for how to create menus
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        int itemID = menuItem.getItemId();
        if(itemID == R.id.exitGameMenuItem)
        {
            //do action
            //surfaceView.invalidate()
            return true;
        }
        else if(itemID == R.id.restartMenuItem)
        {
            //do action
            //surfaceView.invalidate()
            return true;
        }
        else if(itemID == R.id.settings)
        {
            //do action
            //surfaceView.invalidate()
            return true;
        }
        else if (itemID == R.id.changeAIDifficulty)
        {
            //do action
            //surfaceView.invalidate()
            return true;
        }
        return false;
    }




}

    /*@Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Pig has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new PandemicHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new PandemicComputerPlayer(name);
            }});

        // Create a game configuration class for Pig:
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Pig", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }//createDefaultConfig

    /**
     * create a local game
     *
     * @return
     * 		the local game, a pig game
     *
    @Override
    public LocalGame createLocalGame() {
        return new PandemicLocalGame();
    }*/
