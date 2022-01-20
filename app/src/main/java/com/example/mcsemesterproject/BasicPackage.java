package com.example.mcsemesterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicPackage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    List<Room> roomsList = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_package);

        setListAdapter();
    }

    private void setListAdapter() {

        int id1 = getResources().getIdentifier("image11", "drawable", getPackageName());
        int id2 = getResources().getIdentifier("image12", "drawable", getPackageName());
        int id3 = getResources().getIdentifier("image13", "drawable", getPackageName());

        Room f0 = new Room("Room Number: 101", 9.0, "149 Reviews", "Double Room without Terrace", "Price: PK-3900/-", id1);
        Room f1 = new Room("Room Number: 102", 9.2, "258 Reviews", "Single Room without Terrace", "Price: PK-1900/-", id2);
        Room f2 = new Room("Room Number: 103", 8.9, "162 Reviews", "Tripple Room without Terrace", "Price: PK-5900/-", id3);

        roomsList.addAll(Arrays.asList(new Room[]{f0, f1, f2}));
        recyclerView = findViewById(R.id.recyclerView3);

        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRecyclerViewAdapter(roomsList, BasicPackage.this) {

        };
        recyclerView.setAdapter(adapter);
    }
}