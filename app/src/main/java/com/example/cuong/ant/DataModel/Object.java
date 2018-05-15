package com.example.cuong.ant.DataModel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Cuong on 5/12/2018.
 */

public class Object {
    private Bitmap bitmap;

    private static int left;

    public Object(Bitmap bitmap){
        this.bitmap=bitmap;
    }

    public void draw(Canvas canvas, int left,int top){
        canvas.drawColor(Color.parseColor("#ffffff"));
        canvas.drawBitmap(bitmap, new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()),new Rect(left,top,left+150,top+200),new Paint());


    }
}

