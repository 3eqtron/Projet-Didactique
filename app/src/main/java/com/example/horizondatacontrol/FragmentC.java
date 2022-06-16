package com.example.horizondatacontrol;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class FragmentC extends Fragment {

Button conf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v3= inflater.inflate(R.layout.fragment_c, container, false);
        conf=v3.findViewById(R.id.conf);
conf.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent manip =new Intent(getActivity(),ManipulationActivity.class);
        startActivity(manip);
    }
});
    return v3;
    }
}
