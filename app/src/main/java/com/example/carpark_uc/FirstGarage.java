package com.example.carpark_uc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirstGarage extends AppCompatActivity implements View.OnClickListener{
    Button backButton;

    //Parking Garage Places:
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    boolean isFilledValue = false;

    //Database:
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_garge);
        backButton = findViewById(R.id.backButton);

        //Set Parking Garage Places:
        button1 = findViewById(R.id.parkingPlace1);
        button2 = findViewById(R.id.parkingPlace2);
        button3 = findViewById(R.id.parkingPlace3);
        button4 = findViewById(R.id.parkingPlace4);

        //Set Listeners:
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        //Set Database:
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("ParkingPlace");

        //Testing Read
        String name = getResources().getResourceEntryName(button1.getId());
      //  System.out.println("ISTAKEN: " + isTaken);
        if(readDatabase(name)){
            System.out.println("Yeahhhhhhhhhhhhhhhhhh");
            findViewById(R.id.parkingPlace1).setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //Updated Back button
        backButton.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {
             Intent intent = new Intent(getApplicationContext(),Home.class);
              startActivity(intent);
               finish();
          }
    });
    }

    //Write to Database
    @Override
    public void onClick(View v) {
        String name = getResources().getResourceEntryName(v.getId());

        reference.child(name).setValue("Filled");

        findViewById(v.getId()).setBackgroundColor(Color.parseColor("#FF0000"));
        Toast.makeText(FirstGarage.this, "Parking Place Reserved",Toast.LENGTH_SHORT).show();
    }

    //Read from Database
    //Change it to realtime later
    public Boolean readDatabase(String name){
        final Boolean[] taken = {false};

    DatabaseReference isFilled = FirebaseDatabase.getInstance().getReference("ParkingPlace").child(name);
        isFilled.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("NAME IS: " + name);
                System.out.println("SNAPSHOT IS: " + snapshot.getValue());

                if(snapshot.exists())
                {
                    String data = snapshot.getValue().toString();
                    System.out.println("DATA IS : " + data);
                    if(data.equalsIgnoreCase("Filled"))
                    {
                        findViewById(R.id.parkingPlace1).setBackgroundColor(Color.parseColor("#FF0000"));
                    }
                    else {
                        taken[0] = false;
                    }
                    //Get value in database
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return taken[0];
    }

}