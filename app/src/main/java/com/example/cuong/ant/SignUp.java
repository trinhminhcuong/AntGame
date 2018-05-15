package com.example.cuong.ant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.cuong.ant.DataModel.Network.ip;

public class SignUp extends AppCompatActivity {

    private EditText edUsername03;
    private EditText edPassword03;
    private EditText edPassword031;
    private Button btnSignUp03;
    private final String name03="?username=";
    private final String pass03="&password=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edUsername03=findViewById(R.id.edUsername03);
        edPassword03=findViewById(R.id.edPassword03);
        edPassword031=findViewById(R.id.edPassword031);
        btnSignUp03=findViewById(R.id.btnSignUp03);

        btnSignUp03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edUsername03.length()==0||edPassword03.length()==0||edPassword031.length()==0){
                    Toast.makeText(SignUp.this,"Xin mời nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else if(edPassword03.getText().toString().equals(edPassword031.getText().toString())==false){
                    Toast.makeText(SignUp.this,"Mật khẩu chưa khớp",Toast.LENGTH_SHORT).show();
                }
                else {
                    String url=ip+name03+edUsername03.getText().toString().trim()
                            +pass03+edPassword03.getText().toString().trim();
                     new Check().execute(url);
                }
            }
        });
    }

    private class Check extends AsyncTask<String, Void, String> {
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

            if(a.equals("t")==true)
            {
                edUsername03.setText("");
                edPassword03.setText("");
                edPassword031.setText("");
                Toast.makeText(SignUp.this,"Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(SignUp.this,"Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}
