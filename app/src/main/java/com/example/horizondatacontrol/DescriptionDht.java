package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DescriptionDht extends AppCompatActivity {
    TextView textView1;
    ProgressBar pb;
    Button bt;
    ImageButton retmal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_dht);
        textView1=findViewById(R.id.servodescription);
        pb=findViewById(R.id.pb);
        retmal=findViewById(R.id.retlea);
        retmal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),DashbordLearn.class);
                startActivity(i1);
                finish();
            }
        });
        bt=findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),DhtLearn.class);
                startActivity(i);
            }
        });
        pb.setVisibility(View.VISIBLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("EspaceDescription/dht11");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    DataSnapshot snapshot = task.getResult();
                    String servodescription=snapshot.getValue(String.class);
                    textView1.setText(servodescription);
                    textView1.setMovementMethod(new ScrollingMovementMethod());
                    pb.setVisibility(View.GONE);
                }
                else {
                    Log.d("TAG", task.getException().getMessage());
                }
            }
        });
    }
    }

