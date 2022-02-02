package com.example.mcsemesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PremiumPackage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    List<Room> roomsList = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_package);

        setListAdapter();
    }

    private void setListAdapter() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("premiumRooms");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String roomKey;
                String roomInfo;
                String roomNumber;
                String roomRating;
                String roomReviews;
                String roomPrice;
                int roomImageName;
                boolean book;
                boolean vacant;
                String temp;
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    Room room= new Room();
                    roomKey = childSnapshot.getKey();
                    roomInfo= dataSnapshot.child(roomKey).child("roomInfo").getValue().toString();
                    roomNumber= dataSnapshot.child(roomKey).child("roomNumber").getValue().toString();
                    roomRating= dataSnapshot.child(roomKey).child("roomRating").getValue().toString();
                    roomReviews= dataSnapshot.child(roomKey).child("roomReviews").getValue().toString();
                    roomPrice= dataSnapshot.child(roomKey).child("roomPrice").getValue().toString();
                    roomImageName= Integer.parseInt(dataSnapshot.child(roomKey).child("roomImageName").getValue().toString());
                    temp= dataSnapshot.child(roomKey).child("book").getValue().toString();
                    Log.d("T8", roomInfo);
                    if(temp.equals("false")){
                        book= false;
                        vacant= true;
                    }
                    else{
                        book=true;
                        vacant=false;
                    }
                    if(!book) {
                        room.setRoomNumber(roomNumber);
                        room.setRoomRating(roomRating);
                        room.setRoomReviews(roomReviews);
                        room.setRoomInfo(roomInfo);
                        room.setRoomPrice(roomPrice);
                        room.setRoomImageName(roomImageName);
                        room.setBook(book);
                        room.setVacant(vacant);
                        roomsList.add(room);
                    }
                }
                setAdapterFunction();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                Intent intent= new Intent(PremiumPackage.this, About.class);
                startActivity(intent);
                break;
            case R.id.logout:
                Intent intent2= new Intent(PremiumPackage.this, Login.class);
                startActivity(intent2);
                break;
            default:
// If we got here, the user's action was not recognized.
// Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
    public void setAdapterFunction(){
        recyclerView = findViewById(R.id.recyclerView2);

        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRecyclerViewAdapter(roomsList, PremiumPackage.this, this);
        recyclerView.setAdapter(adapter);
    }
}