package com.example.cuong.ant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button btnLogOut04;
    private Button btnPlay04;
    private Button btnHighScore04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        btnHighScore04=findViewById(R.id.btnHighScore04);
        btnPlay04=findViewById(R.id.btnPlay04);
        btnLogOut04=findViewById(R.id.btnLogOut04);

        btnPlay04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent04=new Intent(Menu.this,SurfaceV.class);
                startActivity(intent04);
            }
        });

        btnHighScore04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent042=new Intent(Menu.this,HighScore.class);
                startActivity(intent042);
            }
        });

        btnLogOut04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent043=new Intent(Menu.this,ModPassword.class);
                startActivity(intent043);
            }
        });
    }
}
