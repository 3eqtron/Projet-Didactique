package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ManipulationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageButton retmn;
    Spinner spinner;

    NerworkChangeReciever nerworkChangeReciever = new NerworkChangeReciever();
    TextView textView13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulation);
        retmn = findViewById(R.id.retmn);
        spinner = findViewById(R.id.spinner);
        textView13 = findViewById(R.id.textView13);

        ArrayList<CustomItems> customList = new ArrayList<>();
        customList.add(new CustomItems("Selectionner un Elément", R.drawable.ic_arrow_drop_down_circle_black_24dp));
        customList.add(new CustomItems("Servo moteur", R.drawable.servo));
        customList.add(new CustomItems("Lcd", R.drawable.lcd));
        customList.add(new CustomItems("Dht11", R.drawable.ic_temperature));
        customList.add(new CustomItems("Mq2", R.drawable.co2));
        customList.add(new CustomItems("Robot", R.drawable.robotic));
        CustomAdapter customAdapter = new CustomAdapter(this, customList);


        if (spinner != null) {
            spinner.setAdapter(customAdapter);
            spinner.setOnItemSelectedListener(this);
        }


        retmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rettofrag = new Intent(getApplicationContext(), Home.class);
                startActivity(rettofrag);
                finish();
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

        } else {

            Intent ir = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(ir);
            finish();
        }
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String onlinestatut = snapshot.child("onlinestatut").getValue().toString();
                if (Integer.parseInt(onlinestatut) == 1) {
                    textView13.setText("Carte Connectée ");
                    textView13.setTextColor(Color.GREEN);
                    spinner.setEnabled(true);
                    spinner.setClickable(true);
                } else if (Integer.parseInt(onlinestatut) == 0) {
                    textView13.setText("Carte Déconnectée");
                    textView13.setTextColor(Color.RED);
                    spinner.setEnabled(false);
                    spinner.setClickable(false);
                    Toasty.error(ManipulationActivity.this,
                            "Verifé la connexion de votre carte s'il vous plait!...", Toast.LENGTH_SHORT,true).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        CustomItems items = (CustomItems) adapterView.getSelectedItem();
        if (items.getSpinnerText().equals("Servo moteur")) {
            Intent intentserv = new Intent(getApplicationContext(), ServoContolActivity.class);
            startActivity(intentserv);

        }
        if (items.getSpinnerText().equals("Lcd")) {
            Intent intentlcd = new Intent(getApplicationContext(), LcdControlActivity.class);
            startActivity(intentlcd);

        }
        if (items.getSpinnerText().equals("Dht11")) {
            Intent intentdht = new Intent(getApplicationContext(), DhtControl.class);
            startActivity(intentdht);

        }

        if (items.getSpinnerText().equals("Mq2")) {
            Intent intentmq2 = new Intent(getApplicationContext(), Mq2_ControlActivity.class);
            startActivity(intentmq2);

        }
        if (items.getSpinnerText().equals("Robot")) {
            Intent robot = new Intent(getApplicationContext(), RobotControl.class);
            startActivity(robot);
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

