package com.example.cuong.ant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.cuong.ant.DataModel.Network.ip;
import static com.example.cuong.ant.SignIn.username;

public class HighScore extends AppCompatActivity {

    private TextView tvHighScore06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        tvHighScore06=findViewById(R.id.tvHighScore06);
        String url=ip+"?namescore="+username;
        new getScore().execute(url);
    }

    private class getScore extends AsyncTask<String, Void, String> {
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


                httpURLConnection.setRequestMethod("GET");
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
            Toast.makeText(HighScore.this,s.length()+"",Toast.LENGTH_SHORT).show();
            if(s.length()>30) {
                try {
                    JSONArray array=new JSONArray(s) ;
                    for(int i=0;i<1;i++){
                        JSONObject activity=array.getJSONObject(i);
                        String id=activity.getString("id");
                        String username=activity.getString("username");
                        String score=activity.getString("score");
                        tvHighScore06.setText(score);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                tvHighScore06.setText("0");
            }



        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}
