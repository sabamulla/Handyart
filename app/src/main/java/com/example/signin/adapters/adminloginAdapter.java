package com.example.signin.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.signin.Adminlogin;
import com.example.signin.Adminsignup;

public class adminloginAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

public  adminloginAdapter (FragmentManager fm, Context context, int totalTabs){
    super(fm);
    this.context = context;
    this.totalTabs = totalTabs;

}

    @Override
    public int getCount() {
        return totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                Adminlogin Adminlogin=new Adminlogin();
                return Adminlogin;


            case 1:
                Adminsignup Adminsignup=new Adminsignup();
                return Adminsignup;

            default:
                return null;

        }
    }
}