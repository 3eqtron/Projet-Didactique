package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.jem.liquidswipe.LiquidSwipeViewPager;

public class Home extends AppCompatActivity {
LiquidSwipeViewPager viewPager;

Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager=findViewById(R.id.viewpager);
        adapter=new Adapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(adapter);

    }

}
