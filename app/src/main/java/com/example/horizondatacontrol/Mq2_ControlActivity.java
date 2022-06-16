package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.PriorityQueue;

public class Mq2_ControlActivity extends AppCompatActivity {
    ImageButton retman1;
    private Context mContext;
    private Resources mResources;
    NerworkChangeReciever nerworkChangeReciever = new NerworkChangeReciever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mq2__control);
        retman1=findViewById(R.id.retaman1);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Composantdetection/Mq2/gas");


         TextView gasTextView=findViewById(R.id.gasTextView);
         ProgressBar gasProgressBar=findViewById(R.id.gasProgressBar);
        retman1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),ManipulationActivity.class);
                startActivity(i);
                finish();
            }
        });
        mContext = getApplicationContext();
        mResources = getResources();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String value = snapshot.getValue().toString();
                gasTextView.setText(value+" mw");

                assert value != null;
                gasProgressBar.setProgress(Integer.parseInt(value));
                int v=Integer.parseInt(value);
                if (v > 100){
                 notification();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG", "Erreur .", error.toException());
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
    private void notification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel("n","n",NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"n")
                    .setContentTitle("Notification MQ2")
                    .setSmallIcon(R.drawable.gasnot)
                    .setAutoCancel(true)
                    .setContentText("Danger GAZ,s'il vous plait quitter cette zone");
            NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
            managerCompat.notify(999,builder.build());
        }
    }

}
