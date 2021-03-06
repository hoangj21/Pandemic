package com.example.joann.pandemic.pandemic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CityCircle {
    protected int x;
    protected int y;
    protected int size = 20;
    protected Paint PawnPaint;
    protected Paint OpponentPaint;

    public CityCircle(int x,int y, Paint paint){

        PawnPaint = new Paint();
        PawnPaint = paint;
       // PawnPaint.setColor(Color.CYAN);
        this.x = x;
        this.y = y;

        OpponentPaint = new Paint();
        OpponentPaint.setColor(Color.MAGENTA);
        //this.x = x;
        //this.y = y;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x, y, size, PawnPaint);


    }

    public void setMyPaint(int turn) {
        if(turn == 0){
            PawnPaint.setColor(Color.MAGENTA);
        }
        if(turn == 1){
            PawnPaint.setColor(Color.CYAN);
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
