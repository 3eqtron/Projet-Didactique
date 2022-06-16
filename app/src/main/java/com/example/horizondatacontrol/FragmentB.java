package com.example.horizondatacontrol;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class FragmentB extends Fragment {



Button imageButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1= inflater.inflate(R.layout.fragment_b, container, false);
        imageButton=v1.findViewById(R.id.imageButton);
    imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent ii=new Intent(getActivity(),ConfigActivity.class);
            startActivity(ii);
        }
    });
        return v1;
    }
}
