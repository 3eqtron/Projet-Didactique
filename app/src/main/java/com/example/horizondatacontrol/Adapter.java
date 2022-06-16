package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Adapter extends FragmentPagerAdapter {
    public Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new FragmentA();
        }
        switch (position){
            case 1:return new FragmentB();
        }
        switch (position){
            case 2:return new FragmentC();
        }
        switch (position){
            case 3:return new FragmentD();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
