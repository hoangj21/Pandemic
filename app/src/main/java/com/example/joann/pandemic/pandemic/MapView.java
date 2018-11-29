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
    String cityTapped = "Atlanta";

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

        //TEXT VIEW
        if(state.getPlayerTurn() == 0)
        {
            myPaint.setTextSize(40);
            canvas.drawText("It is your turn Player One", 2050, 175, myPaint);

            myPaint.setTextSize(60);
            canvas.drawText("Player Hand: ", 55, 175, myPaint);

            cityTapped = state.getPlayer().getCurrentLocation().getName();
        }
        else if(state.getPlayerTurn() == 1)
        {
            myPaint.setTextSize(40);
            canvas.drawText("It is the AI's turn", 2050, 175, myPaint);

            myPaint.setTextSize(60);
            canvas.drawText("AI Hand : ", 75, 175, myPaint);

            cityTapped = state.getPlayer().getCurrentLocation().getName();
        }

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


        //Draws text for disease cubes in the city you tapped on

        myPaint.setTextSize(40);
        canvas.drawText("There are " + state.getPlayer().getCurrentLocation().getDiseaseCubes().size() + " Disease cubes", 2050, 900, myPaint);
        canvas.drawText("left in " + cityTapped, 2050, 960, myPaint);

        canvas.drawText("There is a research ", 2050, 1100, myPaint);
        canvas.drawText("station here: " + state.getPlayer().getCurrentLocation().hasResearchLab, 2050, 1160, myPaint);


        //DRAWABLE PLAYER HAND CARDS
        int i;
        int padding = 200;
        int cardh = 60;

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

            PlayerCard PD =(PlayerCard)playerDeck.get(0);

            Bitmap PDCard = BitmapFactory.decodeResource(getResources(), PD.getAndroidId());
            PDCard = Bitmap.createScaledBitmap(PDCard, 160, 250, true);

            canvas.drawBitmap(PDCard, 1395, 785, myPaint );
        } //1395

        //DRAWABLE DISCARDING PLAYER DECK YES
        ArrayList<Card> playerDiscardDeck = state.getPlayerDiscardDeck();
        if(playerDiscardDeck.size() > 0){

            PlayerCard PD = (PlayerCard)playerDiscardDeck.get(0);

            Bitmap PDDCard = BitmapFactory.decodeResource(getResources(), PD.getAndroidId());
            PDDCard = Bitmap.createScaledBitmap(PDDCard, 160, 250, true);
            //canvas.drawBitmap(PDDCard, 100, 100, myPaint );

            canvas.drawBitmap(PDDCard, 1595, 785, myPaint );

        }

        //DRAWABLE DRAWING INFECTION DECK
        ArrayList<InfectionCard> infectionDeck = state.getInfectionDeck();
        for(i = 0; i < infectionDeck.size(); i++){

            InfectionCard ID = infectionDeck.get(0);

            Bitmap IDCard = BitmapFactory.decodeResource(getResources(), ID.getAndroidIdInfect());
            IDCard = Bitmap.createScaledBitmap(IDCard, 240, 140, true);

            canvas.drawBitmap(IDCard, 1390, 160, myPaint );
        }

        //DRAWABLE DISCARDING INFECTION DECK
        ArrayList<InfectionCard> infectionDiscardDeck = state.getInfectionDiscardDeck();

        if(infectionDiscardDeck.size() > 0) {
            InfectionCard IDD = infectionDiscardDeck.get(0);

            Bitmap IDDCard = BitmapFactory.decodeResource(getResources(), IDD.getAndroidIdInfect());
            IDDCard = Bitmap.createScaledBitmap(IDDCard, 240, 140, true);
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

        //Redraws the AI's pawn depending on its location
        if(state.getPlayerTurn()==1){
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(1))){
                Opponentcircle.setX((int)ATLAx+500);
                Opponentcircle.setY((int)ATLAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(12))){
                Opponentcircle.setX((int)WASHx+500);
                Opponentcircle.setY((int)WASHy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(14))){
                Opponentcircle.setX((int)NYCx+500);
                Opponentcircle.setY((int)NYCy+100);

            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(32))){
                Opponentcircle.setX((int)MONTx+500);
                Opponentcircle.setY((int)MONTy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(33))){
                Opponentcircle.setX((int)SANFRANx+500);
                Opponentcircle.setY((int)SANFRANy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(40))){
                Opponentcircle.setX((int)CHICAx+500);
                Opponentcircle.setY((int)CHICAy+100);
            }
        }
        Opponentcircle.draw(canvas);
        Pawncircle.draw(canvas);

    }
    //coordinates of all blue cities
    private float  NYCx = 421;
    private float NYCy = 298;
    private float  WASHx = 423;
    private float WASHy = 362;
    private float  MONTx = 348;
    private float MONTy = 296;
    private float  ATLAx = 304;
    private float ATLAy = 375;
    private float   CHICAx = 255;
    private float CHICAy = 298;
    private float   SANFRANx = 125;
    private float SANFRANy = 344;
    private float   LONDONx = 653;
    private float LONDONy = 248;
    private float   MADRIDx = 640;
    private float MADRIDy = 344;
    private float   PARISx = 728;
    private float PARISy = 309;
    private float   ESSENx = 744;
    private float ESSENy = 240;
    private float   MILANx = 791;
    private float MILANy = 313;
    private float   STPETERx = 858;
    private float STPETERy = 231;

    //coordinates of all yellow cities
    private float LAx = 135;
    private float LAy = 442;
    private float MEXICOCITYx = 240;
    private float MEXICOCITYy = 491;
    private float MIAMIx = 371;
    private float MIAMIy = 471;
    private float BOGOTAx = 358;
    private float BOGOTAy = 575;
    private float LIMAx = 328;
    private float LIMAy = 678;
    private float SANTIAGOx = 337;
    private float SANTIAGOy = 780;
    private float BUENOSAIRESx = 458;
    private float BUENOSAIRESy = 757;
    private float SAOPAULOx = 499;
    private float SAOPAULOy = 688;
    private float LAGOSx = 735;
    private float LAGOSy = 542;
    private float KHARTOUMx = 834;
    private float KHARTOUMy = 529;
    private float KINSHASAx = 753;
    private float KINsHASAy = 622;
    private float JOHANNESBURGx = 828;
    private float JOHANNESBURGy = 740;

    //coordinates of all black cities
    private float ALGIERSx = 747;
    private float ALGIERSy = 407;
    private float ISTANBULx = 834;
    private float ISTANBULy = 340;
    private float MOSCOWx = 916;
    private float MOSCOWy = 296;
    private float TEHRANx = 982;
    private float TEHRANy = 344;
    private float BAGHDADx = 899;
    private float BAGHDADy = 396;
    private float CAIROx = 828;
    private float CAIROy = 418;
    private float RIYADHx = 918;
    private float RIYADHy = 501;
    private float KARACHIx = 998;
    private float KARACHIy = 424;
    private float DELHIx = 1082;
    private float DELHIy = 405;
    private float KOLKATAx = 1149;
    private float KOLKATAy = 444;
    private float MUMBAIx = 1018;
    private float MUMBAIy = 524;
    private float CHENNAIx = 1085;
    private float CHENNAIy = 571;

    //coordinates of all red cities
    private float BEIJINGx = 1206;
    private float BEIJINGy = 319;
    private float SEOULx = 1303;
    private float SEOULy = 304;
    private float TOKYOx = 1367;
    private float TOKYOy = 362;
    private float SHANGHAIx = 1211;
    private float SHANGHAIy = 387;
    private float TAIPEIx = 1302;
    private float TAIPEIy = 450;
    private float OSAKAx = 1381;
    private float OSAKAy = 432;
    private float HONGKONGx = 1218;
    private float HONGKONGy = 469;
    private float BANGKOKx = 1168;
    private float BANGKOKy = 518;
    private float JAKARTAx = 1161;
    private float JAKARTAy = 635;
    private float HOCHIMINHx = 1226;
    private float HOCHIMINHy = 588;
    private float MANILAx = 1324;
    private float MANILAy = 570;
    private float SYDNEYx = 1389;
    private float SYDNEYy = 777;

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
                //Toast.makeText(v.getContext(), "Your finger is touching the screen! :)", Toast.LENGTH_LONG).show();
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

                int Londonx = (int) ((LONDONx) + 500);
                int Londony = (int) ((LONDONy) + 100);

                if (distance(touchX, touchY, Londonx, Londony) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(26));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "London";
                    }
                }

                int Madridx = (int) ((MADRIDx) + 500);
                int Madridy = (int) ((MADRIDy) + 100);

                if (distance(touchX, touchY, Madridx, Madridy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(31));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Madrid";
                    }
                }

                int Parisx = (int) ((PARISx) + 500);
                int Parisy = (int) ((PARISy) + 100);

                if (distance(touchX, touchY, Parisx, Parisy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(21));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Paris";
                    }
                }

                int Essenx = (int) ((ESSENx) + 500);
                int Esseny = (int) ((ESSENy) + 100);

                if (distance(touchX, touchY, Essenx, Esseny) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(11));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Essen";
                    }
                }

                int Milanx = (int) ((MILANx) + 500);
                int Milany = (int) ((MILANy) + 100);

                if (distance(touchX, touchY, Milanx, Milany) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(43));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Milan";
                    }
                }

                int StPeterx = (int) ((STPETERx) + 500);
                int StPetery = (int) ((STPETERy) + 100);

                if (distance(touchX, touchY, StPeterx, StPetery) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(22));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "St Petersburg";
                    }
                }


                int LosAngelesx = (int) ((LAx) + 500);
                int LosAngelesy = (int) ((LAy) + 100);

                if (distance(touchX, touchY, LosAngelesx, LosAngelesy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(22));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Los Angeles";
                    }
                }


                int MexicoCityx = (int) ((MEXICOCITYx) + 500);
                int MexicoCityy = (int) ((MEXICOCITYy) + 100);

                if (distance(touchX, touchY, MexicoCityx, MexicoCityy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(22));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Mexico City";
                    }
                }

                int Miamix = (int) ((MIAMIx) + 500);
                int Miamiy  = (int) ((MIAMIy) + 100);

                if (distance(touchX, touchY, Miamix, Miamiy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(22));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Miami";
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

