package com.example.mcsemesterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        int id1 = getResources().getIdentifier("image1", "drawable", getPackageName());
        int id2 = getResources().getIdentifier("image2", "drawable", getPackageName());
        int id3 = getResources().getIdentifier("image3", "drawable", getPackageName());

        Room f0 = new Room("201", "8.2", "223 Reviews", "Double Room with Balcony", "Price: PK-2900/-", id1, false);
        Room f1 = new Room("202", "8.9", "145 Reviews", "Single Room with Balcony", "Price: PK-1900/-", id2, false);
        Room f2 = new Room("203", "8.5", "250 Reviews", "Tripple Room with Balcony", "Price: PK-4900/-", id3, false);

        roomsList.addAll(Arrays.asList(new Room[]{f0, f1, f2}));
        recyclerView = findViewById(R.id.recyclerView);

        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRecyclerViewAdapter(roomsList, StandardPackage.this) {

        };
        recyclerView.setAdapter(adapter);
    }
}