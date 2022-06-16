package com.example.horizondatacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashbordLearn extends AppCompatActivity {
ImageButton servo,back,lcd,dht;
    NerworkChangeReciever nerworkChangeReciever=new NerworkChangeReciever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord_learn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

        } else {

            Intent ir = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(ir);
            finish();
        }
        servo=findViewById(R.id.servo);
        back=findViewById(R.id.back);
        dht=findViewById(R.id.dht);
        dht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentdht=new Intent(getApplicationContext(),DescriptionDht.class);
                startActivity(intentdht);
            }
        });
lcd=findViewById(R.id.lcd);
lcd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent ilcd=new Intent(getApplicationContext(),DescriptionLcd.class);
        startActivity(ilcd);
    }
});
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),Home.class);
                startActivity(in);
                finish();
            }
        });
        servo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent intoservo =new Intent(getApplicationContext(),DescriptionServo.class);
startActivity(intoservo);
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
