package com.example.signin.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.signin.logintabFragment;
import com.example.signin.signuptabFragment;

public class loginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public loginAdapter(FragmentManager fm, Context context, int totalTabs) {

        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;

    }


    @Override
    public int getCount()
    {
        return totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                logintabFragment logintabFragment = new logintabFragment();
                return logintabFragment;

            case 1:
                signuptabFragment signuptabFragment = new signuptabFragment();
                return signuptabFragment;

            default:
                return null;

        }
    }
}