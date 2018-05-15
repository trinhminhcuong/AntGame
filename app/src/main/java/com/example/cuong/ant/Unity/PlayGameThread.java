package com.example.cuong.ant.Unity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.cuong.ant.DataModel.Constants;
import com.example.cuong.ant.DataModel.Object;
import com.example.cuong.ant.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;

import static com.example.cuong.ant.DataModel.Network.ip;
import static com.example.cuong.ant.SignIn.username;
import static com.example.cuong.ant.Unity.PlayGame.count;
import static com.example.cuong.ant.Unity.PlayGame.scoreGame;

/**
 * Created by Cuong on 5/12/2018.
 */

public class PlayGameThread extends Thread {
    private Object object;
    private static Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private PlayGame playGame;
    private  int FPS=30;
    private static boolean running=true;
    private static int left=0;
    public int top;





    public void setRunning(boolean run){
        this.running=run;
    }

    public void setFPS(int FPS){
        this.FPS=FPS;
    }
    public void setLeft(int left){
        this.left=left;
    }
    public int getLeft(){
        return left;
    }
    public void drawDeath() throws InterruptedException {
        canvas = surfaceHolder.lockCanvas();
        BitmapFactory bm=new BitmapFactory();
        Bitmap b=bm.decodeResource(Constants.CREAT_CONTEXT.getResources(), R.drawable.death);
        canvas.drawBitmap(b,new Rect(0,0,b.getWidth(),b.getHeight()),new Rect(left,top,left+150,top+200),new Paint());
        surfaceHolder.unlockCanvasAndPost(canvas);
        this.setLeft(0);


    }
    public PlayGameThread(Object object, SurfaceHolder surfaceHolder, PlayGame playGame,int top){
        this.object=object;
        this.canvas=canvas; this.surfaceHolder=surfaceHolder;
        this.playGame=playGame;
        this.top=top;
    }



    @Override
    public void run(){
        long startTime;
        long endTime;
        long frameTime=1000/FPS;
        long sleepTime;

           for(int i=0;i<2;i++){
               left=0;
               count=count+1;
               Log.d("done"," "+count);
                 while (left<800) {
                     canvas = surfaceHolder.lockCanvas();
                     startTime = SystemClock.currentThreadTimeMillis();
                     canvas.drawColor(Color.parseColor("#FFFFFF"));
                     object.draw(canvas, left, top);
                     surfaceHolder.unlockCanvasAndPost(canvas);
                     this.playGame.draw(canvas);
                     endTime = SystemClock.currentThreadTimeMillis();
                     sleepTime = frameTime - (endTime - startTime);
                     if (sleepTime > 0) {
                         try {
                             sleep(sleepTime);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                     left = left + 5;
                 }

         }
         String url=ip+"?name="+username+"&score="+scoreGame;
         new addScore().execute(url);
    }



    private class addScore extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder=new StringBuilder();
            try {
                URL url=new URL(strings[0]);

                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();


                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                InputStream inputStream=httpURLConnection.getInputStream();

                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                String line="";

                while((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line + "\n");
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String a=s.charAt(0)+"";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}
