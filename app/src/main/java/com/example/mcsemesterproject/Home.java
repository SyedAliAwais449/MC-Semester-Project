package com.example.mcsemesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Home extends AppCompatActivity {

//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
//        toolbar= findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Rooms");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("T1", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onBasicRooms(View v){

        Intent intent= new Intent(Home.this, BasicPackage.class);
        startActivity(intent);
    }

    public  void onStandardRooms(View v){
        Intent intent= new Intent(Home.this, StandardPackage.class);
        startActivity(intent);
    }

    public  void onPremiumRooms(View v){
        Intent intent= new Intent(Home.this, PremiumPackage.class);
        startActivity(intent);
    }
    public void addToDatabase(View v){
        Order obj = new Order(2,"standard");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("premiumRooms");
//        HashMap hashMap= new HashMap();
//        hashMap.put("roomId","24324");
//        database.child("Room201").updateChildren(hashMap);
//        int id1 = getResources().getIdentifier("image31", "drawable", getPackageName());
//        int id2 = getResources().getIdentifier("image32", "drawable", getPackageName());
//        int id3 = getResources().getIdentifier("image33", "drawable", getPackageName());
//
//        Room f0 = new Room("301", 9.8, "449 Reviews", "Double Room with Terrace", "Price: PK-5900/-", id1, false);
//        Room f1 = new Room("302", 9.5, "458 Reviews", "Single Room with Terrace", "Price: PK-4900/-", id2, false);
//        Room f2 = new Room("303", 9.9, "462 Reviews", "Tripple Room with Terrace", "Price: PK-6900/-", id3, false);
//        database.push().setValue(f2);
        Toast.makeText(Home.this,"data inserted successfully", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent= new Intent(Home.this, About.class);
                startActivity(intent);
                break;
            case R.id.food:
//                Intent intent2= new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent2);
                break;
            default:
// If we got here, the user's action was not recognized.
// Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}