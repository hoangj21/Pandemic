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

    public MapView(Context context, AttributeSet set) {
        super(context, set);
        setWillNotDraw(false);
        thePawns = new ArrayList<Pawn>();
        setOnTouchListener(this);
    }

    public void onDraw(Canvas canvas)
    {
        Paint pawnPaint = new Paint();
        pawnPaint.setColor(Color.BLUE);
        canvas.drawCircle(20, 20, 20, pawnPaint);
        for(Pawn pawn: thePawns)
                pawn.draw(canvas);

        //COUNTRY DOTS
        Paint circleRedPaint = new Paint();
        circleRedPaint.setColor(Color.RED);
        canvas.drawCircle(775, 402, 30, circleRedPaint);

        Paint circleBluePaint = new Paint();
        circleBluePaint.setColor(Color.BLUE);
        canvas.drawCircle(500, 402, 30, circleBluePaint);

        Paint circleYellowPaint = new Paint();
        circleYellowPaint.setColor(Color.YELLOW);
        canvas.drawCircle(600, 402, 30, circleYellowPaint);

        Paint circleBlackPaint = new Paint();
        circleBlackPaint.setColor(Color.BLACK);
        canvas.drawCircle(600, 402, 30, circleBlackPaint);

        //DISEASE VILES
        Paint blueVile = new Paint();
        blueVile.setColor(Color.BLUE);
        canvas.drawCircle(800, 402, 50, blueVile);

        Paint yellowVile = new Paint();
        yellowVile.setColor(Color.YELLOW);
        canvas.drawCircle(830, 402, 50, yellowVile);

        Paint redVile = new Paint();
        redVile.setColor(Color.RED);
        canvas.drawCircle(860, 402, 50, redVile);

        Paint blackVile = new Paint();
        blackVile.setColor(Color.BLACK);
        canvas.drawCircle(890, 402, 50, blackVile);

        //INFECTION RATE SLIDERS
        Paint epidemicSlider = new Paint();
        epidemicSlider.setColor(Color.GREEN);
        canvas.drawCircle(900, 402, 50, epidemicSlider);

        Paint outbreakSlider = new Paint();
        outbreakSlider.setColor(Color.GREEN);
        canvas.drawCircle(950, 402, 50, outbreakSlider);

        //DRAWABLE PLAYER HAND CARDS
        Paint myPaint = new Paint();
        myPaint.setColor(Color.WHITE);
        int padding = 65;
        int cardh = 111;
        int i;

        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.pandemicpic);
        map = Bitmap.createScaledBitmap(map, 500, 500, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

        ArrayList<PlayerCard> playerHand = state.getPlayerHand(HumanPlayerNum);
        for(i = 0; i <  playerHand.size() ; i++){

            PlayerCard c = playerHand.get(i);

            Bitmap card = BitmapFactory.decodeResource(getResources(), c.getAndroidId());
            card = Bitmap.createScaledBitmap(card, 62, 111, true);

            canvas.drawBitmap(card, 0, (padding + i*cardh + i*padding), myPaint);
        }

        //DRAWABLE DRAWING PLAYER DECK
        ArrayList<PlayerCard> playerDeck = state.getPlayerDeck();
        for(i = 0; i < playerDeck.size(); i++){

            PlayerCard PD = playerDeck.get(i);

            Bitmap PDCard = BitmapFactory.decodeResource(getResources(), PD.getAndroidId());
            PDCard = Bitmap.createScaledBitmap(PDCard, 62, 111, true);

            //canvas.drawBitmap(PDCard, 0, )
        }

        //DRAWABLE DISCARDING PLAYER DECK
        ArrayList<PlayerCard> playerDiscardDeck = state.getPlayerDiscardDeck();
        for(i = 0; i < playerDiscardDeck.size(); i++){

            PlayerCard PD = playerDiscardDeck.get(i);

            Bitmap PDDCard = BitmapFactory.decodeResource(getResources(), PD.getAndroidId());
            PDDCard = Bitmap.createScaledBitmap(PDDCard, 62, 111, true);

            //canvas.drawBitmap(PDCard, 0, )
        }

        //DRAWABLE DRAWING INFECTION DECK
        ArrayList<InfectionCard> infectionDeck = state.getInfectionDeck();
        for(i = 0; i < playerDeck.size(); i++){

            InfectionCard ID = infectionDeck.get(i);

            Bitmap IDCard = BitmapFactory.decodeResource(getResources(), ID.getAndroidIdInfect());
            IDCard = Bitmap.createScaledBitmap(IDCard, 62, 111, true);

            //canvas.drawBitmap(PDCard, 0, )
        }

        //DRAWABLE DISCARDING INFECTION DECK
        ArrayList<InfectionCard> infectionDiscardDeck = state.getInfectionDiscardDeck();
        for(i = 0; i < playerDeck.size(); i++){

            InfectionCard IDD = infectionDiscardDeck.get(i);

            Bitmap IDDCard = BitmapFactory.decodeResource(getResources(), IDD.getAndroidIdInfect());
            IDDCard = Bitmap.createScaledBitmap(IDDCard, 62, 111, true);

            //canvas.drawBitmap(PDCard, 0, )
        }
        invalidate();
    }

    private int mActivePointerId = INVALID_POINTER_ID;

    public boolean onTouchEvent (MotionEvent ev)
    {
       final int action = MotionEventCompat.getActionMasked(ev);

       switch(action)
       {
           case MotionEvent.ACTION_DOWN:
           {
               final int pointerIndex = MotionEventCompat.getActionIndex(ev);
               final float x = MotionEventCompat.getX(ev, pointerIndex);
               final float y = MotionEventCompat.getY(ev, pointerIndex);

               mLastTouchX = x;
               mLastTouchY = y;

               mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
               break;
           }
           case MotionEvent.ACTION_MOVE: {
               // Find the index of the active pointer and fetch its position
               final int pointerIndex =
                       MotionEventCompat.findPointerIndex(ev, mActivePointerId);

               final float x = MotionEventCompat.getX(ev, pointerIndex);
               final float y = MotionEventCompat.getY(ev, pointerIndex);

               // Remember this touch position for the next move event
               mLastTouchX = x;
               mLastTouchY = y;

               break;
           }

           case MotionEvent.ACTION_UP: {
               mActivePointerId = INVALID_POINTER_ID;
               break;
           }

           case MotionEvent.ACTION_CANCEL: {
               mActivePointerId = INVALID_POINTER_ID;
               break;
           }

           case MotionEvent.ACTION_POINTER_UP: {

               final int pointerIndex = MotionEventCompat.getActionIndex(ev);
               final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);

               if (pointerId == mActivePointerId) {
                   // This was our active pointer going up. Choose a new
                   // active pointer and adjust accordingly.
                   final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                   mLastTouchX = MotionEventCompat.getX(ev, newPointerIndex);
                   mLastTouchY = MotionEventCompat.getY(ev, newPointerIndex);
                   mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
               }
               break;
           }
       }
        return true;

    }

}

