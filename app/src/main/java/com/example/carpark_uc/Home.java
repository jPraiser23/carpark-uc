package com.example.carpark_uc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.LinkedList;
import java.util.List;

public class Home extends AppCompatActivity {
    String [] garageNames = {"CCM","Garage 2", "Garage 3"};
    int counter = 0 ;
    private FirebaseAuth mAuth;
    private Button BtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //ActionBar

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        List<String> items = new LinkedList<>();
        items.add("CCM");
        items.add("Garage 2");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GarageListAdapter adapter = new GarageListAdapter(items);
        recyclerView.setAdapter(adapter);


        //Logout
        mAuth = FirebaseAuth.getInstance();
        BtnLogout = findViewById(R.id.logoutBtn);
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(Home.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}