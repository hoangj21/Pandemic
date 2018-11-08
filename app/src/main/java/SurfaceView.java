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

class MapView extends SurfaceView {

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


        Bitmap epidemic_card = BitmapFactory.decodeResource(getResources(), R.drawable.PANDEMICCARDS46);
        map = Bitmap.createScaledBitmap(map, 800, 800, true);
        canvas.drawBitmap(map, 100, 100, myPaint );

    }
}

