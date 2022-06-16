package com.example.horizondatacontrol;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

import java.util.ArrayList;

public class ServoContolActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
ArrayList<String> serv=new ArrayList<>();
    ArcSeekBar arcSeekBar;
    NerworkChangeReciever nerworkChangeReciever = new NerworkChangeReciever();

    ImageView imageView;
    TextView textView;
    ImageButton rre;
    Spinner spin;
    BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servo_contol);
        spin = findViewById(R.id.spinner);
        arcSeekBar = findViewById(R.id.seekArc);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView4);


        rre = findViewById(R.id.rre);
        serv.add("Choisir un Servo pour Controller");
        serv.add("servo1");
        serv.add("servo2");
        serv.add("servo3");
        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, serv);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(this);
        rre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inservo =new Intent(getApplicationContext(),ManipulationActivity.class);
                startActivity(inservo);
            }   });

    }








    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getItemAtPosition(i).equals("servo1")){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Composantcontrolle/Servos/Servo");
            int[] intArray = getResources().getIntArray(R.array.progress);
            arcSeekBar.setProgressGradient(intArray);
            ProgressListener progressListener = progress -> {
                imageView.setRotation(progress);
                myRef.setValue(progress);
            };

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int value = dataSnapshot.getValue(Integer.class);
                    textView.setText("Position: "+ value +"°");
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });

            arcSeekBar.setOnProgressChangedListener(progressListener);


        }
        else if (adapterView.getItemAtPosition(i).equals("servo2")){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Composantcontrolle/Servos/Servo1");
            int[] intArray = getResources().getIntArray(R.array.progress);
            arcSeekBar.setProgressGradient(intArray);
            ProgressListener progressListener = progress -> {
                imageView.setRotation(progress);
                myRef.setValue(progress);
            };

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int value = dataSnapshot.getValue(Integer.class);
                    textView.setText("Position: "+ value +"°");
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });

            arcSeekBar.setOnProgressChangedListener(progressListener);

        }
        else if (adapterView.getItemAtPosition(i).equals("servo3")){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Composantcontrolle/Servos/Servo2");
            int[] intArray = getResources().getIntArray(R.array.progress);
            arcSeekBar.setProgressGradient(intArray);
            ProgressListener progressListener = progress -> {
                imageView.setRotation(progress);
                myRef.setValue(progress);
            };

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int value = dataSnapshot.getValue(Integer.class);
                    textView.setText("Position: "+ value +"°");
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("TAG", "Erreur", error.toException());
                }
            });

            arcSeekBar.setOnProgressChangedListener(progressListener);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {




    }
    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(nerworkChangeReciever,filter);

        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(nerworkChangeReciever);
        super.onStop();
    }
}


