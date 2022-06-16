package com.example.horizondatacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RobotControl extends AppCompatActivity {
Button avant,gauche,droit,arriere;
ImageButton ret;
DatabaseReference reference;
    NerworkChangeReciever nerworkChangeReciever = new NerworkChangeReciever();    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_control);
        ret=findViewById(R.id.retbot);
        avant=findViewById(R.id.avant);
        arriere=findViewById(R.id.arriere);
        gauche=findViewById(R.id.gauche);
        droit=findViewById(R.id.droit);


        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ManipulationActivity.class);
                startActivity(i);
            }
        });
        avant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Composantcontrolle/RobotMove/move");
                myRef.setValue(1);
            }
        });
droit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Composantcontrolle/RobotMove/move");
       myRef.setValue(2);
    }
});
gauche.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Composantcontrolle/RobotMove/move");
        myRef.setValue(3);
    }
});
arriere.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Composantcontrolle/RobotMove/move");
myRef.setValue(4);
    }
});
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
