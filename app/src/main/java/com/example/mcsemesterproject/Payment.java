package com.example.mcsemesterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText cellNumber;
    EditText nightStay;
    String roomNumber;

    String nameField;
    String emailField;
    String cellNumberField;
    String nightStayField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        cellNumber = findViewById(R.id.userCellNumber);
        nightStay = findViewById(R.id.userNightStay);
        Intent intent = getIntent();
        roomNumber= intent.getStringExtra("roomNumber");
    }

    public void confirmReservation(View v){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("BookedRooms");
        nameField= name.getText().toString();
        emailField= email.getText().toString();
        cellNumberField= cellNumber.getText().toString();
        nightStayField= nightStay.getText().toString();

        if(!name.getText().toString().equals("") && !email.getText().toString().equals("") &&
                !cellNumber.getText().toString().equals("") && !nightStay.getText().toString().equals("")){
            Toast.makeText(Payment.this,"Room "+roomNumber+" has been Booked for you", Toast.LENGTH_SHORT).show();
            BookedRoomInfo obj= new BookedRoomInfo(nameField,emailField,cellNumberField,nightStayField,roomNumber);
            database.push().setValue(obj);
        }

        if(name.getText().toString().equals("") && email.getText().toString().equals("") &&
                cellNumber.getText().toString().equals("") && nightStay.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "All fields are empty. Please Enter data in all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            if (name.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Please Enter Your Name in Name field", Toast.LENGTH_SHORT).show();
            if (email.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Please Enter Your Email in Email field", Toast.LENGTH_SHORT).show();
            if (cellNumber.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Please Enter Your Cell Number in Cell Number field", Toast.LENGTH_SHORT).show();
            if (nightStay.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Please Enter Night Stays in NightStay field", Toast.LENGTH_SHORT).show();
        }


    }
}