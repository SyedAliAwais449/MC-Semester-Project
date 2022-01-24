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

public class SignUp extends AppCompatActivity {

    EditText inputemail, inputpassword, confirmpass;
    Button registerbutton;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        inputemail = findViewById(R.id.emaile);
        inputpassword = findViewById(R.id.passworde);
        confirmpass = findViewById(R.id.confirmpassword);
        registerbutton = findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Intent inte = getIntent();
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String emial = inputemail.getText().toString();
        String pass = inputpassword.getText().toString();
        String conf = confirmpass.getText().toString();
        if (emial.matches(emailPattern)) {
            inputemail.setError("enter connext Email");
        } else if (pass.isEmpty()) {
            inputpassword.setError("enter proper password");
        } else if (!pass.equals(conf)) {
            confirmpass.setError("Password not matched");
        } else {
            progressDialog.setMessage("Please wait while registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(emial,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNexrActivity();
                        Toast.makeText(SignUp.this, "Registration Successfull",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, ""+task.getException(),Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }

    private void sendUserToNexrActivity() {
        Toast.makeText(SignUp.this, "Registration Successfull send this to next activitys",Toast.LENGTH_SHORT).show();
    }
}
