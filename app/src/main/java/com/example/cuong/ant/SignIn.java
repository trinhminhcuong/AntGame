package com.example.cuong.ant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.cuong.ant.DataModel.Network.ip;

public class SignIn extends AppCompatActivity {

    private EditText edUserName02;
    private EditText edPassword02;
    private Button btnSignIn02;
    public static String username;
    public static String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edUserName02=findViewById(R.id.edUserName02);
        edPassword02=findViewById(R.id.edPassword02);
        btnSignIn02=findViewById(R.id.btnSignIn02);

        btnSignIn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edUserName02.length() == 0 || edPassword02.length() == 0) {
                    Toast.makeText(SignIn.this, "Xin mời nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String url = ip + "?username=" + edUserName02.getText().toString().trim()+"&password="
                            +edPassword02.getText().toString().trim();
                    new Sign().execute(url);
                }
            }
        });
    }

    private class Sign extends AsyncTask<String, Void, String> {
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
            String result=s.charAt(0)+"";
            if(result.equals("t")==true)
            {
                username=edUserName02.getText().toString();
                password=edPassword02.getText().toString();
                Intent intent021=new Intent(SignIn.this,Menu.class);
                startActivity(intent021);
            }
            else {
                Toast.makeText(SignIn.this,"Tài khoản không đúng", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}
