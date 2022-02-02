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
import java.util.List;

public class AdminPanel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    List<Room> roomsList = new ArrayList<Room>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_panel);

        setListAdapter();
    }

    private void setListAdapter() {
        Log.d("T6", "setListAdapter: ");
        String[] roomsArray={"basicRooms","standardRooms","premiumRooms"};
        for(int i=0; i<roomsArray.length;i++){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference().child(roomsArray[i]);
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String roomKey=null;
                    String roomInfo=null;
                    String roomNumber=null;
                    String roomRating=null;
                    String roomReviews=null;
                    String roomPrice=null;
                    int roomImageName=0;
                    boolean book= false;
                    boolean vacant= true;
                    for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                        Room room= new Room();
                        roomKey = childSnapshot.getKey();
                        if(dataSnapshot.child(roomKey).child("book").getValue().toString().equals("true")) {
                            roomInfo = dataSnapshot.child(roomKey).child("roomInfo").getValue().toString();
                            Log.d("T25", roomInfo);
                            roomNumber = dataSnapshot.child(roomKey).child("roomNumber").getValue().toString();
                            roomRating = dataSnapshot.child(roomKey).child("roomRating").getValue().toString();
                            roomReviews = dataSnapshot.child(roomKey).child("roomReviews").getValue().toString();
                            roomPrice = dataSnapshot.child(roomKey).child("roomPrice").getValue().toString();
                            roomImageName = Integer.parseInt(dataSnapshot.child(roomKey).child("roomImageName").getValue().toString());
                            book = true;
                            vacant= false;
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
                Intent intent= new Intent(AdminPanel.this, About.class);
                startActivity(intent);
                break;
            case R.id.logout:
                Intent intent2= new Intent(AdminPanel.this, Login.class);
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
        recyclerView = findViewById(R.id.recyclerView5);

        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRecyclerViewAdapter(roomsList, AdminPanel.this, this);
        recyclerView.setAdapter(adapter);
    }
}