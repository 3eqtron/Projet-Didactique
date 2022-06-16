package com.example.horizondatacontrol;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LcdControlActivity extends AppCompatActivity {
    ImageButton imret;
    Button donelcd;
    EditText editTextlcd;

    NerworkChangeReciever nerworkChangeReciever = new NerworkChangeReciever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcd_control);
        imret = findViewById(R.id.imret);
        donelcd = findViewById(R.id.donelcd);
        editTextlcd = findViewById(R.id.edtlcd);

        imret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retmaniplcd = new Intent(getApplicationContext(), ManipulationActivity.class);
                startActivity(retmaniplcd);
                finish();
            }
        });


        donelcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference lc = database.getReference("Composantcontrolle/Lcd/message");
                String message=editTextlcd.getText().toString();
                lc.setValue(message);
                Toast.makeText(getApplicationContext(),"Text sur votre afficheur lcd",Toast.LENGTH_SHORT).show();
                editTextlcd.setText("");
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
