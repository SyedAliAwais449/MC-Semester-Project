package com.example.mcsemesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

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
        nameField= name.getText().toString();
        emailField= email.getText().toString();
        cellNumberField= cellNumber.getText().toString();
        nightStayField= nightStay.getText().toString();
        if(!name.getText().toString().equals("") && !email.getText().toString().equals("") &&
                !cellNumber.getText().toString().equals("") && !nightStay.getText().toString().equals("")){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("BookedRooms");
            Toast.makeText(Payment.this,"Room "+roomNumber+" has been Booked for you", Toast.LENGTH_SHORT).show();
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent intent= new Intent(Payment.this,Home.class);
                            startActivity(intent);
                        }
                    },
                    1000);
            BookedRoomInfo obj= new BookedRoomInfo(nameField,emailField,cellNumberField,nightStayField,roomNumber);
            database.push().setValue(obj);
            String childTableName;
            if(Integer.parseInt(roomNumber)>100 && Integer.parseInt(roomNumber)<200){
                childTableName="basicRooms";
            }
            else if(Integer.parseInt(roomNumber)>200 && Integer.parseInt(roomNumber)<300){
                childTableName="standardRooms";
            }
            else{
                childTableName="premiumRooms";
            }
            DatabaseReference database2 = FirebaseDatabase.getInstance().getReference().child(childTableName);
            database2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String roomKey;
                    String roomNumber2;
                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                        roomKey = childSnapshot.getKey();
                        roomNumber2= snapshot.child(roomKey).child("roomNumber").getValue().toString();
                        if(roomNumber2.equals(roomNumber)){
                            HashMap hashMap= new HashMap();
                            hashMap.put("book",true);
                            hashMap.put("vacant",false);
                            database2.child(roomKey).updateChildren(hashMap);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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