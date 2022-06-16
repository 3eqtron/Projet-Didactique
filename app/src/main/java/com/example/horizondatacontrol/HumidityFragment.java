package com.example.horizondatacontrol;


import android.content.Intent;
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

public class HumidityFragment extends Fragment {
ImageButton retman1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_humidity, container, false);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Composantdetection/Dht11/humidity");


        final TextView mHumidityTextView = root.findViewById(R.id.humidityTextView);
        final ProgressBar mHumidityProgressBar = root.findViewById(R.id.humidityProgressBar);

retman1=root.findViewById(R.id.retaman1);
retman1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent retman1=new Intent(getContext(),ManipulationActivity.class);
        startActivity(retman1);
    }
});
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                mHumidityTextView.setText(value+" %");

                assert value != null;
                mHumidityProgressBar.setProgress(Integer.parseInt(value));
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