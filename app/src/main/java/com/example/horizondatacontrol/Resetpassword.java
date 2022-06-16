package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Resetpassword extends AppCompatActivity {
Button reinstall;
ImageButton ret;
    private FirebaseAuth mauth;
EditText resrt;
ProgressBar progressBar;
    NerworkChangeReciever nerworkChangeReciever = new NerworkChangeReciever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        resrt=findViewById(R.id.edtreset);
        reinstall=findViewById(R.id.reinstall);
        progressBar=findViewById(R.id.progressBar);
        ret=findViewById(R.id.ret);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(is);
            }
        });
        mauth= FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);
        reinstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetpassword();
            }
        });
    }

    private void resetpassword() {
        String email=resrt.getText().toString();
        if (email.isEmpty()){
            resrt.setError("Champ Invalid");

        return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
if (task.isSuccessful()){
    Toast.makeText(getApplicationContext(),"Verifié votre Email pour reinstaller mot de passe",Toast.LENGTH_SHORT).show();
    Intent in=new Intent(getApplicationContext(),LoginActivity.class);
    startActivity(in);
    finish();
}
else {
    Toast.makeText(getApplicationContext(),"Erreur...! Résseayer s'ils vous plait...",Toast.LENGTH_SHORT).show();
}
                progressBar.setVisibility(View.GONE);
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
