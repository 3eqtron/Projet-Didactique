package com.example.horizondatacontrol;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TemperatureFragment extends Fragment {
ImageButton retman;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_temperature, container, false);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Composantdetection/Dht11/temperature");


        final TextView mTemperatureTextView = root.findViewById(R.id.temperatureTextView);
        final ProgressBar mTemperatureProgressBar = root.findViewById(R.id.temperatureProgressBar);
        retman=root.findViewById(R.id.retman);
        retman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retman=new Intent(getContext(),ManipulationActivity.class);
                startActivity(retman);

            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                mTemperatureTextView.setText(value+" C°");

                assert value != null;
                mTemperatureProgressBar.setProgress(Integer.parseInt(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Erreur.", error.toException());
            }
        });

        return root;
    }


}