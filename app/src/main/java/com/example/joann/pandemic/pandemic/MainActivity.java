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

//External citation:
// https://images-cdn.zmangames.com/us-east-1/filer_public/25/12/251252dd-1338-4f78-b90d-afe073c72363/zm7101_pandemic_rules.pdf
//Used for knowing the rules and layout of Pandemic, referenced throughout all code
public class MainActivity extends AppCompatActivity {

    EditText edTe;
    City london = new City("London", "paris", "madrid", "essen", 3);
    City paris = new City("Paris", "London", "Milan", "Madrid", 2);
    City madrid = new City("Madrid", "Paris", "London", "New York", 1);
    City essen = new City("Essen", "London", "Paris", "Milan", 0);
    City newYork = new City ("New York", "London", "Madrid", "Washington", 2);

    PlayerCard card1 = new PlayerCard(london, 0, false);
    PlayerCard card2 = new PlayerCard(paris, 0, false);
    PlayerCard card3 = new PlayerCard(madrid, 0, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTe = (EditText) findViewById(R.id.editText);

        Button testButton = findViewById(R.id.runTestButton);
            ButtonListener testListener = new ButtonListener();
            testButton.setOnClickListener(testListener);

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


    public class ButtonListener implements View.OnClickListener {
        public void onClick(View Button) {
            //Clears edit text
            edTe.setText(" ");

            //Creates a new instance of the game state class using default constructor
            PandemicGameState firstInstance = new PandemicGameState();


            PlayerInfo player1 = new PlayerInfo(1, 1, 0, newYork, card1, card2);
            PlayerInfo player2 = new PlayerInfo(2, 1, 0, essen, card1, card2);

            firstInstance.setPlayer(player1);

            //Creates a deep copy of firstInstance using copy constructor.
            PandemicGameState secondInstance = new PandemicGameState(firstInstance);

            //Method call and print statements.
            firstInstance.movePawn(player1, player1.getCurrentLocation(), london);
            edTe.append("Player 0 has moved their pawn.\n");

            firstInstance.movePawn(player1, player1.getCurrentLocation(), essen);
            edTe.append("Player 0 has moved their pawn.\n");

            firstInstance.movePawn(player1, player1.getCurrentLocation(), paris);
            edTe.append("Player 0 has moved their pawn.\n");

            firstInstance.treatDisease(player1, player1.getCurrentLocation());
            edTe.append("Player 0 has treated disease in New York.\n");

            firstInstance.drawPlayerCard(player1,2);
            edTe.append("Player 0 has drawn 2 player cards.\n");

            firstInstance.discardPlayerCard(player1,1,card1);
            edTe.append("Player 0 had a full hand and discarded 1 card.\n");

            firstInstance.drawInfectionCard(player1,2);
            edTe.append("Player 0 has drawn 2 infection cards.\n");

            firstInstance.infect(player1);
            edTe.append("Player 0 has infected cities.\n");

            firstInstance.discardInfectionCard(player1,2);
            edTe.append("Player 0 has discarded 2 infection cards.\n");




            edTe.append("It is now player 1's turn.\n");


            secondInstance.movePawn(player2, player2.getCurrentLocation(), paris);
            edTe.append("Player 1 has moved their pawn.\n");

            secondInstance.movePawn(player2, player2.getCurrentLocation(), madrid);
            edTe.append("Player 1 has moved their pawn.\n");

            secondInstance.movePawn(player2, player2.getCurrentLocation(), newYork);
            edTe.append("Player 1 has moved their pawn.\n");

            secondInstance.treatDisease(player2, newYork);
            edTe.append("Player 1 has treated disease in New York.\n");

            secondInstance.drawPlayerCard(player2,2);
            edTe.append("Player 1 has drawn 2 player cards.\n");

            secondInstance.discardPlayerCard(player2,1,card1);
            edTe.append("Player 1 had a full hand and discarded 1 card.\n");

            secondInstance.drawInfectionCard(player2,2);
            edTe.append("Player 1 has drawn 2 infection cards.\n");

            secondInstance.infect(player2);
            edTe.append("Player 1 has infected cities.\n");

            secondInstance.discardInfectionCard(player2,2);
            edTe.append("Player 1 has discarded 2 infection cards.\n");



            //Creates a new instance of the game state class using default constructor.
            PandemicGameState thirdInstance = new PandemicGameState();

            //Creates a deep copy of thirdInstance using copy constructor.
            PandemicGameState fourthInstance = new PandemicGameState(firstInstance);

            //Call toString on second and fourth instance. Should produce identical results.
            edTe.append("First instance to string: " +firstInstance.toString() + "\n");
            edTe.append("Second instance to string: " +secondInstance.toString() + "\n");
            edTe.append("Fourth instance to string: " +fourthInstance.toString() + "\n");


        }
    }

}
