package com.example.cuong.ant.Unity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.cuong.ant.DataModel.Constants;
import com.example.cuong.ant.DataModel.Object;
import com.example.cuong.ant.R;
import com.example.cuong.ant.SignUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.widget.Toast.*;
import static com.example.cuong.ant.DataModel.Network.ip;
import static com.example.cuong.ant.SignIn.username;


/**
 * Created by Cuong on 5/12/2018.
 */

public class PlayGame extends SurfaceView implements SurfaceHolder.Callback {

    private BitmapFactory bitmapFactory=new BitmapFactory();
    private Bitmap bitmap;
    private PlayGameThread playGameThread;
    private PlayGameThread playGameThread2;
    private PlayGameThread playGameThread3;
    public static int scoreGame=0;
    public static int count=0;


    private static int x;
    private static int y;




    public PlayGame(Context context) {
        super(context);
        Constants.CREAT_CONTEXT=context;
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
         x=(int)motionEvent.getX();
         y=(int)motionEvent.getY();
         if(x>playGameThread.getLeft()&&x<playGameThread.getLeft()+150&&y>0&&y<200){
             scoreGame=scoreGame+1;
             Toast.makeText(Constants.CREAT_CONTEXT,"Score: "+scoreGame,LENGTH_SHORT).show();
             try {
                 playGameThread.drawDeath();
                 Thread.sleep(2000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
       /* if(x>playGameThread2.getLeft()&&x<playGameThread2.getLeft()+150&&y>400&&y<600){
            scoreGame=scoreGame+1;
            Toast.makeText(Constants.CREAT_CONTEXT,"Score: "+scoreGame,LENGTH_SHORT).show();
            try {
                playGameThread2.drawDeath();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(x>playGameThread2.getLeft()&&x<playGameThread2.getLeft()+150&&y>600&&y<800){
            scoreGame=scoreGame+1;
            Toast.makeText(Constants.CREAT_CONTEXT,"Score: "+scoreGame,LENGTH_SHORT).show();
            try {
                playGameThread3.drawDeath();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/


        return false;
    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
    }
    public void update(){
        Toast.makeText(Constants.CREAT_CONTEXT," "+ count,LENGTH_SHORT).show();
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        bitmap=bitmapFactory.decodeResource(Constants.CREAT_CONTEXT.getResources(),R.drawable.bug);
        playGameThread=new PlayGameThread(new Object(bitmap),getHolder(),this,0);
     //   playGameThread2=new PlayGameThread(new Object(bitmap),getHolder(),this,400);
     //  playGameThread3=new PlayGameThread(new Object(bitmap),getHolder(),this,800);
     //   playGameThread2.start();
        playGameThread.start();
    //   playGameThread3.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

}
