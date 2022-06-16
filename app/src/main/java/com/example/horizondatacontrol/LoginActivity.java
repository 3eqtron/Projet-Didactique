package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button intregis,login,motdepasseoublie;
    private FirebaseAuth mauth;
    EditText edtemi,edtpss;
    String email;
    NerworkChangeReciever nerworkChangeReciever=new NerworkChangeReciever();
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intregis=findViewById(R.id.intregis);
        login=findViewById(R.id.login);
        edtemi=findViewById(R.id.email);
        edtpss=findViewById(R.id.pssd);
        progressBar=findViewById(R.id.progressBarl);
        motdepasseoublie=findViewById(R.id.motdepasseoublie);

        motdepasseoublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iii=new Intent(getApplicationContext(),Resetpassword.class);
                startActivity(iii);

            }
        });
        progressBar.setVisibility(View.GONE);
        mauth= FirebaseAuth.getInstance();
        intregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intreg=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intreg);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                authenuser();
            }
        });

    }

    private void authenuser() {
         email=edtemi.getText().toString();
        String password=edtpss.getText().toString();
        if (email.isEmpty()||password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Champ Invalid",Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mauth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            showmainactivity();


                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Authentification echoué",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showmainactivity() {
        Intent i=new Intent(getApplicationContext(),ConfigActivity.class);
        startActivity(i);
        finish();
        Toast.makeText(getApplicationContext(),"Authentification Valid",Toast.LENGTH_SHORT).show();
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


