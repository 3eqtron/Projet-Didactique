package com.example.horizondatacontrol;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfigActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton im,music,stop;
TextView textView3,checkconnexion;
   Button btnlg,btnlogout;
   LottieAnimationView animationView;
ProgressBar bar;

   NerworkChangeReciever nerworkChangeReciever=new NerworkChangeReciever();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        im = findViewById(R.id.im);
        textView3 = findViewById(R.id.textView3);
        btnlogout = findViewById(R.id.btnlogout);
        animationView=findViewById(R.id.animationView);
        bar=findViewById(R.id.progressBar3);
        stop=findViewById(R.id.buttonstop);
        music=findViewById(R.id.buttonmusic);
        music.setOnClickListener(this);
        stop.setOnClickListener(this);

        checkconnexion = findViewById(R.id.checkconnection);

        bar.setVisibility(View.VISIBLE);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = auth.getCurrentUser();
        if (currentuser == null) {
            Intent inlog = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(inlog);
            finish();
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentuser.getUid());


        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {

                    textView3.setText("Bonjour : " + user.nom);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iretdash = new Intent(getApplicationContext(), Home.class);
                startActivity(iretdash);
            }
        });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutuser();
            }
        });
        final DatabaseReference onlineDbStatus = database.getReference("onlinestatut");
        onlineDbStatus.setValue(0);


        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String onlinestatut = dataSnapshot.child("onlinestatut").getValue().toString();

                if (Integer.parseInt(onlinestatut) == 1) {
                    checkconnexion.setText("Connectée");
checkconnexion.setTextColor(Color.GREEN);
                }
                if (Integer.parseInt(onlinestatut) == 0) {


                    checkconnexion.setText("Hors ligne");
                    checkconnexion.setTextColor(Color.RED);
                    bar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void logoutuser() {
        FirebaseAuth.getInstance().signOut();
        Intent logout =new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(logout);
        finish();
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


    @Override
    public void onClick(View view) {
        if (view == music){
startService(new Intent(getApplicationContext(),BackgroundSoundService.class));
        }
        else if (view == stop){
            stopService((new Intent(getApplicationContext(),BackgroundSoundService.class)));
            Toast.makeText(getApplicationContext(),"Music Desactivé",Toast.LENGTH_SHORT).show();
        }
    }
}