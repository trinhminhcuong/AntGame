package com.example.cuong.ant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSignIn01;
    private Button btnSignUp01;
    private Button btnQuit01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn01=findViewById(R.id.btnSignIn01);
        btnQuit01=findViewById(R.id.btnQuit01);
        btnSignUp01=findViewById(R.id.btnSignUp01);

        btnSignUp01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent013=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent013);
            }
        });

        btnSignIn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent012=new Intent(MainActivity.this,SignIn.class);
                startActivity(intent012);
            }
        });

        btnQuit01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });


    }
}
