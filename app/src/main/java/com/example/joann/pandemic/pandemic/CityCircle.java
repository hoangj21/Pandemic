package com.example.joann.pandemic.pandemic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CityCircle {
    protected int x;
    protected int y;
    protected int size = 20;
    protected Paint myPaint;

    public CityCircle(int x,int y){

        myPaint = new Paint();
        myPaint.setColor(Color.CYAN);
        this.x = x;
        this.y = y;

    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x, y, size, myPaint);


    }

    public void setMyPaint(int turn) {
        if(turn == 0){
            myPaint.setColor(Color.RED);
        }
        if(turn == 1){
            myPaint.setColor(Color.CYAN);
        }
    }
}