package com.example.mcsemesterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView cellNumber;
    TextView nightStay;
    String roomNumber;

    String nameField;
    String emailField;

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

//        BookedRoomInfo obj= new BookedRoomInfo();
//        database.push().setValue();
    }
}