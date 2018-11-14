package com.example.joann.pandemic.pandemic;

import android.support.v4.view.MotionEventCompat;
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
 CityCircle Citycircle;

    public void setState (PandemicGameState state)
    {
        this.state = state;
    }


    public MapView(Context context, AttributeSet set) {
        super(context, set);
        setWillNotDraw(false);
        //OnTouchListener listener = new OnTouchListener()

        setOnTouchListener(this);
       Citycircle = new CityCircle(1000,400);

    }

    public void onDraw(Canvas canvas)
    {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.WHITE);

        if(state == null)
        {
            return;
        }


        Paint pawnPaint = new Paint();
        pawnPaint.setColor(Color.BLUE);
        canvas.drawCircle(20, 20, 20, pawnPaint);


        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.pandemicpic);
        map = Bitmap.createScaledBitmap(map, 1500, 1000, true);
        canvas.drawBitmap(map, 500, 100, myPaint );

        //DISEASE VILES
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
        //DRAWABLE PLAYER HAND CARDS
        int padding = 200;
        int cardh = 30;
        int i;

        ArrayList<PlayerCard> playerHand = state.getPlayer().getPlayerHand();
        for(i = 0; i <  playerHand.size() ; i++){

            PlayerCard c = playerHand.get(i);

            Bitmap card = BitmapFactory.decodeResource(getResources(), c.getAndroidId());
            card = Bitmap.createScaledBitmap(card, 160, 250, true);

            canvas.drawBitmap(card, 0, (padding + i*cardh + i*padding), myPaint);
        }

        //DRAWABLE DRAWING PLAYER DECK
        ArrayList<PlayerCard> playerDeck = state.getPlayerDeck();
        for(i = 0; i < playerDeck.size(); i++){

            PlayerCard PD = playerDeck.get(i);

            Bitmap PDCard = BitmapFactory.decodeResource(getResources(), PD.getAndroidId());
            PDCard = Bitmap.createScaledBitmap(PDCard, 160, 250, true);

            canvas.drawBitmap(PDCard, 1395, 785, myPaint );
        } //1395

        //DRAWABLE DISCARDING PLAYER DECK YES
        ArrayList<PlayerCard> playerDiscardDeck = state.getPlayerDiscardDeck();
        if(playerDiscardDeck.size() > 0){

            PlayerCard PD = playerDiscardDeck.get(i);

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
        Citycircle.draw(canvas);

    }
    private float  NYCx = (float)980.0/(float)2000.0;
    private float NYCy = (float)441.0/(float)2100.0;
    private float  WASHx = (float)950.0/(float)2000.0;
    private float WASHy = (float)500.0/(float)2100.0;
    private float  MONTx = (float)920.0/(float)2000.0;
    private float MONTy = (float)447.0/(float)2100.0;
    private float  ATLAx = (float)850.0/(float)2000.0;
    private float ATLAy = (float)500.0/(float)2100.0;
    private float  CHICAx = (float)890.0/(float)2000.0;
    private float CHICAy = (float)447.0/(float)2100.0;
    private float  SANFRANx = (float)700.0/(float)2000.0;
    private float SANFRANy = (float)460.0/(float)100.0;
    private double distance(int x1, int y1, int x2, int y2)
    {
        double distance =  Math.sqrt((Math.pow(x1 - x2, 2) + Math.pow(y1-y2, 2)));
        return distance;
    }

    public boolean onTouch (View v, MotionEvent ev)
    {

       if(ev.getAction() == MotionEvent.ACTION_DOWN)
       {
           int touchX = (int)ev.getX();
           int touchY = (int)ev.getY();
           CityCircle circle = new CityCircle(touchX, touchY);



           int NYCcityX = (int)((NYCx*1500)+500);
           int NYCcityY = (int)((NYCy*1000)+100);

           if(distance(touchX, touchY, NYCcityX, NYCcityY) < 500)
           {
               this.Citycircle = circle;
               invalidate();
           }

           int WashcityX = (int)((WASHx*1500)+500);
           int WashcityY = (int)((WASHy*1000)+100);

           if(distance(touchX, touchY, WashcityX, WashcityY) < 500)
           {
               this.Citycircle = circle;
               invalidate();
           }

           int MontrealcityX = (int)((MONTx*1500)+500);
           int MontrealcityY = (int)((MONTy*1000)+100);

           if(distance(touchX, touchY, MontrealcityX, MontrealcityY) < 500)
           {
               this.Citycircle = circle;
               invalidate();
           }

           int AtlantacityX = (int)((ATLAx*1500)+500);
           int AtlantacityY = (int)((ATLAy*1000)+100);

           if(distance(touchX, touchY, AtlantacityX, AtlantacityY) < 500)
           {
               this.Citycircle = circle;
               invalidate();
           }

           int ChicagocityX = (int)((CHICAx*1500)+500);
           int ChicagocityY = (int)((CHICAy*1000)+100);

           if(distance(touchX, touchY, ChicagocityX, ChicagocityY) < 500)
           {
               this.Citycircle = circle;
               invalidate();
           }

           int SanFrancityX = (int)((SANFRANx*1500)+500);
           int SanFrancityY = (int)((SANFRANy*1000)+100);

           if(distance(touchX, touchY, SanFrancityX, SanFrancityY) < 500)
           {
               this.Citycircle = circle;
               invalidate();
           }


       }
       return true;
    }

}

