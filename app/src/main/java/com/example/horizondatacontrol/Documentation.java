package com.example.horizondatacontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Documentation extends AppCompatActivity {
ImageButton ret;



    NerworkChangeReciever nerworkChangeReciever = new NerworkChangeReciever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentation);
        ret = findViewById(R.id.imageButton2);



        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iret = new Intent(getApplicationContext(), Home.class);
                startActivity(iret);
                finish();
            }
        });
        final ListView list = findViewById(R.id.lst);
        ArrayList<Docu> arrayList = new ArrayList<Docu>();
        arrayList.add(new Docu("Application Mobile","cliquez pour plus d'infos",R.drawable.ic_info_outline_black_24dp));
        arrayList.add(new Docu("Developpée par","Horizon Data",R.drawable.avatar));
        arrayList.add(new Docu("Nature ","Android Native (java)",R.drawable.ic_android_black_24dp));
        arrayList.add(new Docu("Taille ","≈ 30 mo (version 1.0)",R.drawable.ic_android_black_24dp));
        arrayList.add(new Docu("Serveur Backend ","Firebase (version 1.0)",R.drawable.firebase));
        arrayList.add(new Docu("Nombre de Composants a controller ","8 composants (version 1.0)",R.drawable.ic_android_black_24dp));
        arrayList.add(new Docu("Carte électronique","cliquez pour plus d'infos",R.drawable.ic_info_outline_black_24dp));
        arrayList.add(new Docu("Nom :","HorizonDataDidactique",R.drawable.ic_memory_black_24dp));
        arrayList.add(new Docu("Alimentation ","12V , 5V , 3.3V",R.drawable.ic_memory_black_24dp));
        arrayList.add(new Docu("Nombre de Composants","12 composants",R.drawable.ic_memory_black_24dp));
        arrayList.add(new Docu(" Dimensions ","150 mm x 250 mm",R.drawable.ic_memory_black_24dp));
        arrayList.add(new Docu(" Core ","NodeMcu esp32s",R.drawable.esp));

        CustomAdapterr customAdapterr = new CustomAdapterr(this, arrayList);
        list.setAdapter(customAdapterr);
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
