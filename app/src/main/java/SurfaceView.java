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

public class MapView extends SurfaceView {

    public MapView(Context context, AttributeSet set) {
        super(context, set);
        setWillNotDraw(false);
    }

    public void onDraw(Canvas canvas)
    {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLUE);

        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );


        Bitmap infect3 = BitmapFactory.decodeResource(getResources(), R.drawable.INFECTION3);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

        Bitmap country11 = BitmapFactory.decodeResource(getResources(), R.drawable.PANDEMICCARDS11);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

        Bitmap country13 = BitmapFactory.decodeResource(getResources(), R.drawable.PANDEMICCARDS13);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

        Bitmap country16 = BitmapFactory.decodeResource(getResources(), R.drawable.PANDEMICCARDS16);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

        Bitmap country29 = BitmapFactory.decodeResource(getResources(), R.drawable.PANDEMICCARDS29);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

        Bitmap country30 = BitmapFactory.decodeResource(getResources(), R.drawable.PANDEMICCARDS30);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

        Bitmap role3 = BitmapFactory.decodeResource(getResources(), R.drawable.ROLE3);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

        Bitmap role5 = BitmapFactory.decodeResource(getResources(), R.drawable.ROLE5);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );


    }
}

