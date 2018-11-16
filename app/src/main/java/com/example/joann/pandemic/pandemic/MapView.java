package com.example.joann.pandemic.pandemic;

import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.example.joann.pandemic.R;
import com.example.joann.pandemic.pandemic.Card;
import com.example.joann.pandemic.pandemic.InfectionCard;
import com.example.joann.pandemic.pandemic.PandemicGameState;
import com.example.joann.pandemic.pandemic.Pawn;
import com.example.joann.pandemic.pandemic.PlayerCard;

import java.util.ArrayList;

import static android.view.MotionEvent.INVALID_POINTER_ID;

class MapView extends SurfaceView implements View.OnTouchListener
{

    protected PandemicGameState state;
    protected int HumanPlayerNum = 0;
    protected ArrayList<Pawn> thePawns;
    CityCircle Pawncircle;
    CityCircle Opponentcircle;
    private PandemicHumanPlayer player;
    String cityTapped = "";

    public void setState (PandemicGameState state)
    {
        this.state = state;
    }


    public MapView(Context context, AttributeSet set) {
        super(context, set);
        setWillNotDraw(false);
        //OnTouchListener listener = new OnTouchListener()

        setOnTouchListener(this);

        Paint paint = new Paint();

        paint.setColor(Color.CYAN);
        Pawncircle = new CityCircle(800,490, paint) ;
        //Pawncircle.setMyPaint(0);
        paint.setColor(Color.MAGENTA);
        Opponentcircle = new CityCircle(800, 490, paint);
       // Opponentcircle.setMyPaint(1);

    }

    public void onDraw(Canvas canvas)
    {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.WHITE);

        if(state == null)
        {
            return;
        }


        //Paint pawnPaint = new Paint();
      //  pawnPaint.setColor(Color.BLUE);
       // canvas.drawCircle(20, 20, 20, pawnPaint);


        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.pandemicpic);
        map = Bitmap.createScaledBitmap(map, 1500, 1000, true);
        canvas.drawBitmap(map, 500, 100, myPaint );

        //DISEASE VIALS
        Paint blueVile = new Paint();
        blueVile.setColor(Color.BLUE);
        canvas.drawCircle(1140, 1030, 30, blueVile);

        Paint yellowVile = new Paint();
        yellowVile.setColor(Color.YELLOW);
        canvas.drawCircle(1015, 1030, 30, yellowVile);

        Paint redVile = new Paint();
        redVile.setColor(Color.RED);
        canvas.drawCircle(1075, 1030, 30, redVile);

        Paint blackVile = new Paint();
        blackVile.setColor(Color.BLACK);
        canvas.drawCircle(1210, 1030, 30, blackVile);

        Paint infectionRate = new Paint();
        infectionRate.setColor(Color.GREEN);
        canvas.drawCircle(1470, 330, 30, infectionRate);

        Paint outbreakRate = new Paint();
        outbreakRate.setColor(Color.GREEN);
        canvas.drawCircle(620, 650, 30, outbreakRate);

        //INFECTION RATE SLIDERS
        /*
        Paint epidemicSlider = new Paint();
        epidemicSlider.setColor(Color.GREEN);
        canvas.drawCircle(900, 402, 50, epidemicSlider);

       Paint outbreakSlider = new Paint();
       outbreakSlider.setColor(Color.GREEN);
        canvas.drawCircle(950, 402, 50, outbreakSlider);
        */

        //TEXT VIEWS
        myPaint.setTextSize(60);
        canvas.drawText("Player Hand: ", 55, 175, myPaint);

        myPaint.setTextSize(40);
        canvas.drawText( "You have " + state.getPlayer().actionsLeft + " moves left", 2050, 300, myPaint);

        if (state.getPlayer().role == 1)
        {
            canvas.drawText( "Your role is: ", 2050, 450, myPaint);
            canvas.drawText( "The Operations Expert", 2050, 510, myPaint);
        }

        else if (state.getPlayer().role == 2)
        {
            canvas.drawText( "Your role is: ", 2050, 450, myPaint);
            canvas.drawText( "The Scientist", 2050, 510, myPaint);
        }

        else if(state.getPlayer().role == 3)
        {
            canvas.drawText( "Your role is: ", 2050, 450, myPaint);
            canvas.drawText( "The Medic", 2050, 510, myPaint);
        }


        canvas.drawText("You are in: " + cityTapped, 2050, 700, myPaint);

        //DRAWABLE PLAYER HAND CARDS

        int padding = 200;
        int cardh = 60;
        int i;

        for(i = 0; i <  state.getAllCities().size() ; i++) {

            myPaint.setTextSize(40);
            canvas.drawText("There are " + state.getAllCities().get(i).getDiseaseCubes().size() + " Disease cubes", 2050, 900, myPaint);
            canvas.drawText("left in " + cityTapped, 2050, 960, myPaint);
        }

            ArrayList<Card> playerHand = state.getPlayer().getPlayerHand();
            for(i = 0; i <  playerHand.size() ; i++){

            PlayerCard c = (PlayerCard) playerHand.get(i);

            Bitmap card = BitmapFactory.decodeResource(getResources(), c.getAndroidId());
            card = Bitmap.createScaledBitmap(card, 200, 250, true);

            canvas.drawBitmap(card, 130, (padding + i*cardh + i*padding), myPaint);
        }

        //DRAWABLE DRAWING PLAYER DECK
        ArrayList<Card> playerDeck = state.getPlayerDeck();
        for(i = 0; i < playerDeck.size(); i++){

            PlayerCard PD =(PlayerCard)playerDeck.get(i);

            Bitmap PDCard = BitmapFactory.decodeResource(getResources(), PD.getAndroidId());
            PDCard = Bitmap.createScaledBitmap(PDCard, 160, 250, true);

            canvas.drawBitmap(PDCard, 1395, 785, myPaint );
        } //1395

        //DRAWABLE DISCARDING PLAYER DECK YES
        ArrayList<Card> playerDiscardDeck = state.getPlayerDiscardDeck();
        if(playerDiscardDeck.size() > 0){

            PlayerCard PD = (PlayerCard)playerDiscardDeck.get(i);

            Bitmap PDDCard = BitmapFactory.decodeResource(getResources(), PD.getAndroidId());
            PDDCard = Bitmap.createScaledBitmap(PDDCard, 160, 250, true);
            //canvas.drawBitmap(PDDCard, 100, 100, myPaint );

            canvas.drawBitmap(PDDCard, 1595, 785, myPaint );

        }

        //DRAWABLE DRAWING INFECTION DECK
        ArrayList<InfectionCard> infectionDeck = state.getInfectionDeck();
        for(i = 0; i < playerDeck.size(); i++){

            InfectionCard ID = infectionDeck.get(i);

            Bitmap IDCard = BitmapFactory.decodeResource(getResources(), ID.getAndroidIdInfect());
            IDCard = Bitmap.createScaledBitmap(IDCard, 240, 140, true);

            canvas.drawBitmap(IDCard, 1390, 160, myPaint );
        }

        //DRAWABLE DISCARDING INFECTION DECK
        ArrayList<InfectionCard> infectionDiscardDeck = state.getInfectionDiscardDeck();

        if(infectionDiscardDeck.size() > 0) {
            InfectionCard IDD = infectionDiscardDeck.get(i);

            Bitmap IDDCard = BitmapFactory.decodeResource(getResources(), IDD.getAndroidIdInfect());
            IDDCard = Bitmap.createScaledBitmap(IDDCard, 160, 250, true);
            canvas.drawBitmap(IDDCard, 1670, 150, myPaint );

            //canvas.drawBitmap(PDCard, 0, )
        }
        /*
        if(state.getPlayerTurn() == 0){
            Pawncircle.setMyPaint(0);
        }
        if(state.getPlayerTurn() == 1){
            Pawncircle.setMyPaint(1);
        }
        */
        Opponentcircle.draw(canvas);
        Pawncircle.draw(canvas);

    }
    //coordinates of north american cities...yes
    private float  NYCx = 421;
    private float NYCy = 298;
    private float  WASHx = 423;
    private float WASHy = 362;
    private float  MONTx = 348;
    private float MONTy = 296;
    private float  ATLAx = 304;
    private float ATLAy = 375;
    private float  CHICAx = 255;
    private float CHICAy = 298;
    private float  SANFRANx = 125;
    private float SANFRANy = 344;
    private double distance(int x1, int y1, int x2, int y2)
    {
        double distance =  Math.sqrt((Math.pow(x1 - x2, 2) + Math.pow(y1-y2, 2)));
        return distance;
    }

    public boolean onTouch (View v, MotionEvent ev)
    {
        if(player.isClicked() == true) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                //Toast.makeText(v.getContext(), "You are touching the GUI!", Toast.LENGTH_LONG).show();
                Toast.makeText(v.getContext(), "Your finger is touching the screen! :)", Toast.LENGTH_LONG).show();
                int touchX = (int) ev.getX();
                int touchY = (int) ev.getY();
                Paint paint = new Paint();
                paint.setColor(Color.CYAN);
                CityCircle circle = new CityCircle(touchX, touchY,paint);
                final int TOUCHRADIUS = 50;

                //comment remove later
                Log.i("YouTouched", "x:" + (touchX - 500) + " y:" + (touchY - 100));



                int NYCcityX = (int) ((NYCx) + 500);
                int NYCcityY = (int) ((NYCy) + 100);

                if (distance(touchX, touchY, NYCcityX, NYCcityY) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(14));

                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "New York";

                    }

                }

                int WashcityX = (int) ((WASHx) + 500);
                int WashcityY = (int) ((WASHy) + 100);

                if (distance(touchX, touchY, WashcityX, WashcityY) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(12));

                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Washington";
                    }

                }

                int MontrealcityX = (int) ((MONTx) + 500);
                int MontrealcityY = (int) ((MONTy) + 100);

                if (distance(touchX, touchY, MontrealcityX, MontrealcityY) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(32));

                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Montreal";
                    }

                }

                int AtlantacityX = (int) ((ATLAx) + 500);
                int AtlantacityY = (int) ((ATLAy) + 100);

                if (distance(touchX, touchY, AtlantacityX, AtlantacityY) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(1));

                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Atlanta";
                    }

                }

                int ChicagocityX = (int) ((CHICAx) + 500);
                int ChicagocityY = (int) ((CHICAy) + 100);

                if (distance(touchX, touchY, ChicagocityX, ChicagocityY) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(40));

                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Chicago";
                    }

                }

                int SanFrancityX = (int) ((SANFRANx) + 500);
                int SanFrancityY = (int) ((SANFRANy) + 100);

                if (distance(touchX, touchY, SanFrancityX, SanFrancityY) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(33));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "San Francisco";
                    }
                }


            }

        }

        player.setIsClicked();
        return true;
    }

    public void setPlayer(PandemicHumanPlayer player) {
        this.player = player;
    }
}

