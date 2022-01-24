package com.example.mcsemesterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        int id1 = getResources().getIdentifier("image31", "drawable", getPackageName());
        int id2 = getResources().getIdentifier("image32", "drawable", getPackageName());
        int id3 = getResources().getIdentifier("image33", "drawable", getPackageName());

        Room f0 = new Room("Room Number: 301", 9.8, "449 Reviews", "Double Room with Terrace", "Price: PK-5900/-", id1);
        Room f1 = new Room("Room Number: 302", 9.5, "458 Reviews", "Single Room with Terrace", "Price: PK-4900/-", id2);
        Room f2 = new Room("Room Number: 303", 9.9, "462 Reviews", "Tripple Room with Terrace", "Price: PK-6900/-", id3);

        roomsList.addAll(Arrays.asList(new Room[]{f0, f1, f2}));
        recyclerView = findViewById(R.id.recyclerView2);

        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRecyclerViewAdapter(roomsList, PremiumPackage.this, PremiumPackage.this) {

        };
        recyclerView.setAdapter(adapter);
    }
}