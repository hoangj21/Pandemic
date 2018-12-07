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


        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.pandemicpic);
        map = Bitmap.createScaledBitmap(map, 1500, 1000, true);
        canvas.drawBitmap(map, 500, 100, myPaint );

        //DISEASE VIALS

        if(state.getCuredDiseases()[0] == 0) {
            Paint yellowVile = new Paint();
            yellowVile.setColor(Color.YELLOW);
            canvas.drawCircle(1015, 1030, 25, yellowVile);
        }

        if(state.getCuredDiseases()[0] == 1) {
            Paint yellowVile = new Paint();
            yellowVile.setColor(Color.YELLOW);
            canvas.drawCircle(1015, 980, 25, yellowVile);
        }

        if(state.getCuredDiseases()[1] == 0) {
            Paint redVile = new Paint();
            redVile.setColor(Color.RED);
            canvas.drawCircle(1075, 1030, 25, redVile);
        }

        if(state.getCuredDiseases()[1] == 1) {
            Paint redVile = new Paint();
            redVile.setColor(Color.RED);
            canvas.drawCircle(1075, 980, 25, redVile);
        }

        if(state.getCuredDiseases()[2] == 0) {
            Paint blueVile = new Paint();
            blueVile.setColor(Color.BLUE);
            canvas.drawCircle(1140, 1030, 25, blueVile);
        }
        if(state.getCuredDiseases()[2] == 1){
            Paint blueVile = new Paint();
            blueVile.setColor(Color.BLUE);
            canvas.drawCircle(1140, 980, 25, blueVile);
        }

        if(state.getCuredDiseases()[3] == 0) {
            Paint blackVile = new Paint();
            blackVile.setColor(Color.BLACK);
            canvas.drawCircle(1210, 1030, 25, blackVile);
        }

        if(state.getCuredDiseases()[3] == 1) {
            Paint blackVile = new Paint();
            blackVile.setColor(Color.BLACK);
            canvas.drawCircle(1210, 980, 25, blackVile);
        }

        //INFECTION RATE SLIDERS
        Paint infectionRate = new Paint();
        infectionRate.setColor(Color.GREEN);

        if(state.getInfectionRate() == 0){
            canvas.drawCircle(1461, 330, 25, infectionRate);}

        if(state.getInfectionRate() == 1){
            canvas.drawCircle(1515, 330, 25, infectionRate);}

        if(state.getInfectionRate() == 2){
            canvas.drawCircle(1565, 330, 25, infectionRate);}

        if(state.getInfectionRate() == 3){
            canvas.drawCircle(1620, 330, 25, infectionRate);}

        if(state.getInfectionRate() == 4){
            canvas.drawCircle(1670, 330, 25, infectionRate);}

        if(state.getInfectionRate() == 5){
            canvas.drawCircle(1720, 330, 25, infectionRate);}

        if(state.getInfectionRate() == 6){
            canvas.drawCircle(1770, 330, 25, infectionRate);}


        Paint outbreakRate = new Paint();
        outbreakRate.setColor(Color.GREEN);

        if(state.getOutbreakNum() == 0){
        canvas.drawCircle(620, 654, 25, outbreakRate);}

        if(state.getOutbreakNum() == 1){
        canvas.drawCircle(673, 710, 25, outbreakRate);}

        if(state.getOutbreakNum() == 2){
        canvas.drawCircle(620, 742, 25, outbreakRate);}

        if(state.getOutbreakNum() == 3){
        canvas.drawCircle(673, 780, 25, outbreakRate);}

        if(state.getOutbreakNum() == 4){
        canvas.drawCircle(620, 827, 25, outbreakRate);}

        if(state.getOutbreakNum() == 5){
        canvas.drawCircle(673, 870, 25, outbreakRate);}

        if(state.getOutbreakNum() == 6){
        canvas.drawCircle(620, 900, 25, outbreakRate);}

        if(state.getOutbreakNum() == 7){
        canvas.drawCircle(673, 940, 25, outbreakRate);}

        if(state.getOutbreakNum() == 8){
        canvas.drawCircle(620, 978, 25, outbreakRate);}


        //TEXT VIEW

        if(state.getPlayerTurn() == 0)
        {
            myPaint.setTextSize(40);
            canvas.drawText("It is your turn Player One", 2050, 205, myPaint);
            myPaint.setTextSize(37);
            canvas.drawText("(Blue Dot)", 2050, 250, myPaint);

            canvas.drawText("Action made: ", 2050, 80, myPaint);
            canvas.drawText(state.getMessage(), 2030, 120, myPaint);

            myPaint.setTextSize(60);
            canvas.drawText("Player Hand: ", 55, 80, myPaint);

            cityTapped = state.getPlayer().getCurrentLocation().getName();
        }
        else if(state.getPlayerTurn() == 1)
        {
            myPaint.setTextSize(40);
            canvas.drawText("It is the AI's turn", 2050, 205, myPaint);
            myPaint.setTextSize(37);
            canvas.drawText("(Pink Dot)", 2050, 250, myPaint);

            canvas.drawText("Action made: ", 2050, 80, myPaint);
            canvas.drawText(state.getMessage(), 2050, 120, myPaint);

            myPaint.setTextSize(60);
            canvas.drawText("AI Hand : ", 75, 80, myPaint);

            cityTapped = state.getPlayer().getCurrentLocation().getName();
        }

        myPaint.setTextSize(40);
        canvas.drawText( "You have " + state.getPlayer().actionsLeft + " moves left", 2050, 350, myPaint);

        if (state.getPlayer().role == 1)
        {
            canvas.drawText( "Your role is: ", 2050, 490, myPaint);
            canvas.drawText( "The Operations Expert", 2050, 550, myPaint);
        }

        else if (state.getPlayer().role == 2)
        {
            canvas.drawText( "Your role is: ", 2050, 490, myPaint);
            canvas.drawText( "The Scientist", 2050, 550, myPaint);
        }

        else if(state.getPlayer().role == 3)
        {
            canvas.drawText( "Your role is: ", 2050, 490, myPaint);
            canvas.drawText( "The Medic", 2050, 550, myPaint);
        }

        canvas.drawText("You are in: " + cityTapped, 2050, 720, myPaint);


        //Draws text for disease cubes in the city you tapped on

        myPaint.setTextSize(40);
        canvas.drawText("There are " + state.getPlayer().getCurrentLocation().getDiseaseCubes().size() + " Disease cubes", 2050, 900, myPaint);
        canvas.drawText("left in " + cityTapped, 2050, 960, myPaint);

        canvas.drawText("There is a research ", 2050, 1100, myPaint);
        canvas.drawText("station here: " + state.getPlayer().getCurrentLocation().hasResearchLab, 2050, 1160, myPaint);


        //DRAWABLE PLAYER HAND CARDS
        int i;
        int padding = 100;
        int cardh = 80;

            ArrayList<Card> playerHand = state.getPlayer().getPlayerHand();
            for(i = 0; i <  playerHand.size() ; i++){

            PlayerCard c = (PlayerCard) playerHand.get(i);

            Bitmap card = BitmapFactory.decodeResource(getResources(), c.getAndroidId());
            card = Bitmap.createScaledBitmap(card, 200, 250, true);

            canvas.drawBitmap(card, 130, (padding + i*cardh + i*padding), myPaint);
        }

        //DRAWABLE DRAWING PLAYER DECK
        ArrayList<Card> playerDeck = state.getPlayerDeck();

            PlayerCard PD =(PlayerCard)playerDeck.get(0);

            Bitmap PDCard = BitmapFactory.decodeResource(getResources(), PD.getAndroidId());
            PDCard = Bitmap.createScaledBitmap(PDCard, 160, 250, true);

            canvas.drawBitmap(PDCard, 1395, 785, myPaint );


        //DRAWABLE DISCARDING PLAYER DECK YES
        ArrayList<Card> playerDiscardDeck = state.getPlayerDiscardDeck();
        if(playerDiscardDeck.size() > 0){

            PlayerCard PDD = (PlayerCard)playerDiscardDeck.get(0);

            Bitmap PDDCard = BitmapFactory.decodeResource(getResources(), PDD.getAndroidId());
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
            canvas.drawBitmap(IDDCard, 16130, 150, myPaint );

            //canvas.drawBitmap(PDCard, 0, )
        }

        //Redraws the AI's pawn depending on its location
        if(state.getPlayerTurn()==1){
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(0))){
                Opponentcircle.setX((int)ALGIERSx+500);
                Opponentcircle.setY((int)ALGIERSy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(1))){
                Opponentcircle.setX((int)ATLAx+500);
                Opponentcircle.setY((int)ATLAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(2))){
                Opponentcircle.setX((int)ATLAx+500);
                Opponentcircle.setY((int)ATLAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(3))){
                Opponentcircle.setX((int)BANGKOKx+500);
                Opponentcircle.setY((int)BANGKOKy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(4))){
                Opponentcircle.setX((int)BEIJINGx+500);
                Opponentcircle.setY((int)BEIJINGy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(5))){
                Opponentcircle.setX((int)BUENOSAIRESx+500);
                Opponentcircle.setY((int)BUENOSAIRESy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(6))){
                Opponentcircle.setX((int)BOGOTAx+500);
                Opponentcircle.setY((int)BOGOTAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(7))){
                Opponentcircle.setX((int)ISTANBULx+500);
                Opponentcircle.setY((int)ISTANBULy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(8))){
                Opponentcircle.setX((int)KHARTOUMx+500);
                Opponentcircle.setY((int)KHARTOUMy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(9))){
                Opponentcircle.setX((int)HOCHIMINHx+500);
                Opponentcircle.setY((int)HOCHIMINHy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(10))){
                Opponentcircle.setX((int)RIYADHx+500);
                Opponentcircle.setY((int)RIYADHy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(11))){
                Opponentcircle.setX((int)ESSENx+500);
                Opponentcircle.setY((int)ESSENy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(12))){
                Opponentcircle.setX((int)WASHx+500);
                Opponentcircle.setY((int)WASHy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(13))){
                Opponentcircle.setX((int)MOSCOWx+500);
                Opponentcircle.setY((int)MOSCOWy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(14))){
                Opponentcircle.setX((int)NYCx+500);
                Opponentcircle.setY((int)NYCy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(15))){
                Opponentcircle.setX((int)TAIPEIx+500);
                Opponentcircle.setY((int)TAIPEIy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(16))){
                Opponentcircle.setX((int)TOKYOx+500);
                Opponentcircle.setY((int)TOKYOy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(17))){
                Opponentcircle.setX((int)TEHRANx+500);
                Opponentcircle.setY((int)TEHRANy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(18))){
                Opponentcircle.setX((int)JAKARTAx+500);
                Opponentcircle.setY((int)JAKARTAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(19))){
                Opponentcircle.setX((int)CAIROx+500);
                Opponentcircle.setY((int)CAIROy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(20))){
                Opponentcircle.setX((int)CHENNAIx+500);
                Opponentcircle.setY((int)CHENNAIy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(21))){
                Opponentcircle.setX((int)PARISx+500);
                Opponentcircle.setY((int)PARISy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(22))){
                Opponentcircle.setX((int)STPETERx+500);
                Opponentcircle.setY((int)STPETERy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(23))){
                Opponentcircle.setX((int)SAOPAULOx+500);
                Opponentcircle.setY((int)SAOPAULOy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(24))){
                Opponentcircle.setX((int)LAGOSx+500);
                Opponentcircle.setY((int)LAGOSy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(25))){
                Opponentcircle.setX((int)LIMAx+500);
                Opponentcircle.setY((int)LIMAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(26))){
                Opponentcircle.setX((int)LONDONx+500);
                Opponentcircle.setY((int)LONDONy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(27))){
                Opponentcircle.setX((int)LAx+500);
                Opponentcircle.setY((int)LAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(28))){
                Opponentcircle.setX((int)DELHIx+500);
                Opponentcircle.setY((int)DELHIy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(29))){
                Opponentcircle.setX((int)JOHANNESBURGx+500);
                Opponentcircle.setY((int)JOHANNESBURGy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(30))){
                Opponentcircle.setX((int)KARACHIx+500);
                Opponentcircle.setY((int)KARACHIy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(31))){
                Opponentcircle.setX((int)MADRIDx+500);
                Opponentcircle.setY((int)MADRIDy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(32))){
                Opponentcircle.setX((int)MONTx+500);
                Opponentcircle.setY((int)MONTy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(33))){
                Opponentcircle.setX((int)SANFRANx+500);
                Opponentcircle.setY((int)SANFRANy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(34))){
                Opponentcircle.setX((int)KOLKATAx+500);
                Opponentcircle.setY((int)KOLKATAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(35))){
                Opponentcircle.setX((int)MEXICOCITYx+500);
                Opponentcircle.setY((int)MEXICOCITYy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(36))){
                Opponentcircle.setX((int)SANTIAGOx+500);
                Opponentcircle.setY((int)SANTIAGOy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(37))){
                Opponentcircle.setX((int)SYDNEYx+500);
                Opponentcircle.setY((int)SYDNEYy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(38))){
                Opponentcircle.setX((int)MUMBAIx+500);
                Opponentcircle.setY((int)MUMBAIy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(39))){
                Opponentcircle.setX((int)SEOULx+500);
                Opponentcircle.setY((int)SEOULy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(40))){
                Opponentcircle.setX((int)CHICAx+500);
                Opponentcircle.setY((int)CHICAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(41))){
                Opponentcircle.setX((int)KINSHASAx+500);
                Opponentcircle.setY((int)KINsHASAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(42))){
                Opponentcircle.setX((int)MIAMIx+500);
                Opponentcircle.setY((int)MIAMIy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(43))){
                Opponentcircle.setX((int)MILANx+500);
                Opponentcircle.setY((int)MILANy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(44))){
                Opponentcircle.setX((int)MANILAx+500);
                Opponentcircle.setY((int)MANILAy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(45))){
                Opponentcircle.setX((int)SHANGHAIx+500);
                Opponentcircle.setY((int)SHANGHAIy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(46))){
                Opponentcircle.setX((int)HONGKONGx+500);
                Opponentcircle.setY((int)HONGKONGy+100);
            }
            if(state.getPlayer().getCurrentLocation().equals(state.getAllCities().get(47))){
                Opponentcircle.setX((int)OSAKAx+500);
                Opponentcircle.setY((int)OSAKAy+100);
            }
        }
        Opponentcircle.draw(canvas);
        Pawncircle.draw(canvas);

    }
    //coordinates of all blue cities
    private float  NYCx = 421;
    private float NYCy = 280;
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
    private float BUENOSAIRESx = 446;
    private float BUENOSAIRESy = 780;
    private float SAOPAULOx = 522;
    private float SAOPAULOy = 668;
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
                //Log.i("YouTouched", "x:" + (touchX - 500) + " y:" + (touchY - 100));
                Log.i("YouTouched", "x:" + (touchX) + " y:" + (touchY));


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

                    this.player.needToMakeMove(state.getAllCities().get(27));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Los Angeles";
                    }
                }


                int MexicoCityx = (int) ((MEXICOCITYx) + 500);
                int MexicoCityy = (int) ((MEXICOCITYy) + 100);

                if (distance(touchX, touchY, MexicoCityx, MexicoCityy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(35));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Mexico City";
                    }
                }

                int Miamix = (int) ((MIAMIx) + 500);
                int Miamiy  = (int) ((MIAMIy) + 100);

                if (distance(touchX, touchY, Miamix, Miamiy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(42));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Miami";
                    }
                }

                int Bogotax = (int) ((BOGOTAx) + 500);
                int Bogotay  = (int) ((BOGOTAy) + 100);

                if (distance(touchX, touchY, Bogotax, Bogotay) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(6));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Bogota";
                    }
                }

                int Limax = (int) ((LIMAx) + 500);
                int Limay  = (int) ((LIMAy) + 100);

                if (distance(touchX, touchY, Limax, Limay) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(25));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Lima";
                    }
                }

                int Santiagox = (int) ((SANTIAGOx) + 500);
                int Santiagoy  = (int) ((SANTIAGOy) + 100);

                if (distance(touchX, touchY, Santiagox, Santiagoy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(36));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Santiago";
                    }
                }

                int BuenosAiresx = (int) ((BUENOSAIRESx) + 500);
                int BuenosAiresy  = (int) ((BUENOSAIRESy) + 100);

                if (distance(touchX, touchY, BuenosAiresx, BuenosAiresy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(5));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Buenos Aires";
                    }
                }

                int SaoPaulox = (int) ((SAOPAULOx) + 500);
                int SaoPauloy  = (int) ((SAOPAULOy) + 100);

                if (distance(touchX, touchY, SaoPaulox, SaoPauloy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(23));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Sao Paulo";
                    }
                }

                int Lagosx = (int) ((LAGOSx) + 500);
                int Lagosy  = (int) ((LAGOSy) + 100);

                if (distance(touchX, touchY, Lagosx, Lagosy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(24));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Lagos";
                    }
                }

                int Khartoumx = (int) ((KHARTOUMx) + 500);
                int Khartoumy  = (int) ((KHARTOUMy) + 100);

                if (distance(touchX, touchY, Khartoumx, Khartoumy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(8));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Khartoum";
                    }
                }

                int Kinshasax = (int) ((KINSHASAx) + 500);
                int Kinshasay  = (int) ((KINsHASAy) + 100);

                if (distance(touchX, touchY, Kinshasax, Kinshasay) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(41));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Kinshasa";
                    }
                }

                int Johannesburgx = (int) ((JOHANNESBURGx) + 500);
                int Johannesburgy  = (int) ((JOHANNESBURGy) + 100);

                if (distance(touchX, touchY, Johannesburgx, Johannesburgy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(29));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Johannesburg";
                    }
                }

                //BLACK CITY PLACEMENT

                int Algiersx = (int) ((ALGIERSx) + 500);
                int Algiersy  = (int) ((ALGIERSy) + 100);

                if (distance(touchX, touchY, Algiersx, Algiersy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(0));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Algiers";
                    }
                }

                int Istanbulx = (int) ((ISTANBULx) + 500);
                int Istanbuly  = (int) ((ISTANBULy) + 100);

                if (distance(touchX, touchY, Istanbulx, Istanbuly) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(7));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Istanbul";
                    }
                }

                int Moscowx = (int) ((MOSCOWx) + 500);
                int Moscowy  = (int) ((MOSCOWy) + 100);

                if (distance(touchX, touchY, Moscowx, Moscowy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(13));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Moscow";
                    }
                }

                int Tehranx = (int) ((TEHRANx) + 500);
                int Tehrany  = (int) ((TEHRANy) + 100);

                if (distance(touchX, touchY, Tehranx, Tehrany) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(17));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Tehran";
                    }
                }

                int Baghdadx = (int) ((BAGHDADx) + 500);
                int Baghdady  = (int) ((BAGHDADy) + 100);

                if (distance(touchX, touchY, Baghdadx, Baghdady) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(2));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Baghdad";
                    }
                }

                int Cairox = (int) ((CAIROx) + 500);
                int Cairoy  = (int) ((CAIROy) + 100);

                if (distance(touchX, touchY, Cairox, Cairoy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(19));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Cairo";
                    }
                }

                int Riyadhx = (int) ((RIYADHx) + 500);
                int Riyadhy  = (int) ((RIYADHy) + 100);

                if (distance(touchX, touchY, Riyadhx, Riyadhy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(10));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Riyadh";
                    }
                }

                int Karachix = (int) ((KARACHIx) + 500);
                int Karachiy  = (int) ((KARACHIy) + 100);

                if (distance(touchX, touchY, Karachix, Karachiy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(30));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Karachi";
                    }
                }

                int Delhix = (int) ((DELHIx) + 500);
                int Delhiy  = (int) ((DELHIy) + 100);

                if (distance(touchX, touchY, Delhix, Delhiy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(28));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Delhi";
                    }
                }


                int Mumbaix = (int) ((MUMBAIx) + 500);
                int Mumbaiy  = (int) ((MUMBAIy) + 100);

                if (distance(touchX, touchY, Mumbaix, Mumbaiy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(38));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Mumbai";
                    }
                }

                int Chennaix = (int) ((CHENNAIx) + 500);
                int Chennaiy  = (int) ((CHENNAIy) + 100);

                if (distance(touchX, touchY, Chennaix, Chennaiy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(20));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Chennai";
                    }
                }

                //RED CITIES
                int Beijingx = (int) ((BEIJINGx) + 500);
                int Beijingy  = (int) ((BEIJINGy) + 100);

                if (distance(touchX, touchY, Beijingx, Beijingy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(4));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Beijing";
                    }
                }

                int Kolkatax = (int) ((KOLKATAx) + 500);
                int Kolkatay  = (int) ((KOLKATAy) + 100);

                if (distance(touchX, touchY, Kolkatax, Kolkatay) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(34));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "kolkata";
                    }
                }


                int Seoulx = (int) ((SEOULx) + 500);
                int Seouly  = (int) ((SEOULy) + 100);

                if (distance(touchX, touchY, Seoulx, Seouly) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(39));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Seoul";
                    }
                }

                int Tokyox = (int) ((TOKYOx) + 500);
                int Tokyoy  = (int) ((TOKYOy) + 100);

                if (distance(touchX, touchY, Tokyox, Tokyoy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(16));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Tokyo";
                    }
                }

                int Shanghaix = (int) ((SHANGHAIx) + 500);
                int Shanghaiy  = (int) ((SHANGHAIy) + 100);

                if (distance(touchX, touchY, Shanghaix, Shanghaiy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(45));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Shanghai";
                    }
                }

                int Taipeix = (int) ((TAIPEIx) + 500);
                int Taipeiy  = (int) ((TAIPEIy) + 100);

                if (distance(touchX, touchY, Taipeix, Taipeiy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(15));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Taipei";
                    }
                }

                int Osakax = (int) ((OSAKAx) + 500);
                int Osakay  = (int) ((OSAKAy) + 100);

                if (distance(touchX, touchY, Osakax, Osakay) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(47));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Osaka";
                    }
                }

                int HongKongx = (int) ((HONGKONGx) + 500);
                int HongKongy  = (int) ((HONGKONGy) + 100);

                if (distance(touchX, touchY, HongKongx, HongKongy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(46));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Hong Kong";
                    }
                }

                int Bangkokx = (int) ((BANGKOKx) + 500);
                int Bangkoky  = (int) ((BANGKOKy) + 100);

                if (distance(touchX, touchY, Bangkokx, Bangkoky) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(3));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Bangkok";
                    }
                }

                int Jakartax = (int) ((JAKARTAx) + 500);
                int Jakartay  = (int) ((JAKARTAy) + 100);

                if (distance(touchX, touchY, Jakartax, Jakartay) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(18));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Jakarta";
                    }
                }

                int HoChiMinhx = (int) ((HOCHIMINHx) + 500);
                int HoChiminhy  = (int) ((HOCHIMINHy) + 100);

                if (distance(touchX, touchY, HoChiMinhx, HoChiminhy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(9));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Ho Chi Minh";
                    }
                }

                int Manilax = (int) ((MANILAx) + 500);
                int Manilay  = (int) ((MANILAy) + 100);

                if (distance(touchX, touchY, Manilax, Manilay) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(26));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Taipei";
                    }
                }

                int Sydneyx = (int) ((SYDNEYx) + 500);
                int Sydneyy  = (int) ((SYDNEYy) + 100);

                if (distance(touchX, touchY, Sydneyx, Sydneyy) < TOUCHRADIUS) {

                    this.player.needToMakeMove(state.getAllCities().get(37));
                    if (state.isLegal()) {

                        this.Pawncircle = circle;
                        cityTapped = "Sydney";
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

