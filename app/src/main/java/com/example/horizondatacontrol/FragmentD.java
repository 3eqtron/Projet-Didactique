package com.example.horizondatacontrol;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class FragmentD extends Fragment {
Button comm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vc= inflater.inflate(R.layout.fragment_d, container, false);
    comm=vc.findViewById(R.id.comm);
    comm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent DashLearn=new Intent(getActivity(),DashbordLearn.class);
            startActivity(DashLearn);
        }
    });
        return vc;

    }
}
