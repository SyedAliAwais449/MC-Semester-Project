package com.example.mcsemesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StandardPackage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    List<Room> roomsList = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_package);

        setListAdapter();
    }

    private void setListAdapter() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("standardRooms");
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
                    }
                    else{
                        book=true;
                    }
                    if(!book) {
                        room.setRoomNumber(roomNumber);
                        room.setRoomRating(roomRating);
                        room.setRoomReviews(roomReviews);
                        room.setRoomInfo(roomInfo);
                        room.setRoomPrice(roomPrice);
                        room.setRoomImageName(roomImageName);
                        room.setBook(book);
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

    public void setAdapterFunction(){
        recyclerView = findViewById(R.id.recyclerView);

        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRecyclerViewAdapter(roomsList, StandardPackage.this, this);
        recyclerView.setAdapter(adapter);
    }
}