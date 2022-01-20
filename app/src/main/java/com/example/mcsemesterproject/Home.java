package com.example.mcsemesterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Home extends AppCompatActivity {

//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
//        toolbar= findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
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
//                Intent intent= new Intent(MainActivity.this, MainActivity3.class);
//                startActivity(intent);
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