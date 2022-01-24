package com.example.mcsemesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button btn;
    EditText inputemail, inputpassword;
    Button loginbutton;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.signup);

        inputemail = findViewById(R.id.email);
        inputpassword = findViewById(R.id.password);
        loginbutton = findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btn.setOnClickListener(new View.OnClickListener() {
            Intent inte;

            //findind error
            @Override

            public void onClick(View v) {
                inte = new Intent(Login.this, SignUp.class);
                startActivity(inte);
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }


    private void performLogin() {
        String emial = inputemail.getText().toString();
        String pass = inputpassword.getText().toString();
        //String conf = confirmpass.getText().toString();
        if (emial.matches(emailPattern)) {
            inputemail.setError("enter connext Email");
        } else if (pass.isEmpty()) {
            inputpassword.setError("enter proper password");
        } /*else if (!pass.equals(conf)) {
            confirmpass.setError("Password not matched");
        } */else {
            progressDialog.setMessage("Please wait while login");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
    mAuth.signInWithEmailAndPassword(emial,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful())
            {
                progressDialog.dismiss();
                sendUserToNextActivity();
                Toast.makeText(Login.this, "Login Successfull",Toast.LENGTH_SHORT).show();

            }
            else
            {
                progressDialog.dismiss();
                Toast.makeText(Login.this, ""+task.getException(),Toast.LENGTH_SHORT).show();



            }


                  }
    })      ;

    }
}

    private void sendUserToNextActivity() {
    }
}