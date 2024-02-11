package com.example.carpark_uc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class Home extends AppCompatActivity {
    String [] garageNames = {"CCM","Garage 2", "Garage 3"};
    int counter = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List<String> items = new LinkedList<>();
        items.add("Testing");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GarageListAdapter adapter = new GarageListAdapter(items);
        recyclerView.setAdapter(adapter);


    }
}