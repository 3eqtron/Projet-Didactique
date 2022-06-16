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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
EditText edtnom,edtemail,edtmotdepasse;
Button register;
ImageButton retlogin;
ProgressBar progressBar;
private FirebaseAuth mauth;
    NerworkChangeReciever nerworkChangeReciever = new NerworkChangeReciever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtnom=findViewById(R.id.editText);
        edtemail=findViewById(R.id.editText2);
        edtmotdepasse=findViewById(R.id.editText3);
        register=findViewById(R.id.btnregister);
        retlogin=findViewById(R.id.retlogin);
        progressBar=findViewById(R.id.progressBarr);
        retlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retlog=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(retlog);
                finish();
            }
        });
        mauth=FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
            }
        });
    }

    private void registeruser() {
        String name=edtnom.getText().toString();
        String email=edtemail.getText().toString();
        String password=edtmotdepasse.getText().toString();
        if (name.isEmpty()||email.isEmpty()||password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Champ Invalid",Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mauth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
User user=new User(name,email,password);
                            FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    Showmainactivity();
                                }
                            });
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Authentification echoué",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void Showmainactivity() {
        Intent in=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(in);
        Toast.makeText(getApplicationContext(),"Registrement Nouveau User",Toast.LENGTH_SHORT).show();
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
}
