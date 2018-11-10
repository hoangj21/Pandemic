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

import com.example.joann.pandemic.R;
import com.example.joann.pandemic.pandemic.Card;
import com.example.joann.pandemic.pandemic.PandemicGameState;
import com.example.joann.pandemic.pandemic.PlayerCard;

import java.util.ArrayList;

class MapView extends SurfaceView
{
 protected PandemicGameState state;
 protected int HumanPlayerNum = 0;
    public MapView(Context context, AttributeSet set) {
        super(context, set);
        setWillNotDraw(false);
    }

    public void onDraw(Canvas canvas)
    {
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
            card = Bitmap.createScaledBitmap(map, 62, 111, true);

            canvas.drawBitmap(card, 0, (padding + i*cardh + i*padding), myPaint);
        }


    }
}

