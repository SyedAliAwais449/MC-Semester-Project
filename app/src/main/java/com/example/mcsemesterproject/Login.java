package com.example.mcsemesterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
   Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn=findViewById(R.id.signup);
        btn.setOnClickListener(new View.OnClickListener() {
            Intent inte;
            //findind error
            @Override

            public void onClick(View v) {
                inte =new Intent (Login.this, SignUp.class);
                startActivity(inte);
            }
        });
    }
}