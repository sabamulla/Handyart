package com.example.signin;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class adminpage extends AppCompatActivity {
    BottomNavigationView navbar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        navbar=findViewById(R.id.navbar);
        frameLayout=findViewById(R.id.Framelayout);
        navbar.setOnNavigationItemSelectedListener(navlistener);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Framelayout,new homeFragment()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment SelectedFragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    SelectedFragment = new adminhome();
                    break;

                case R.id.add:
                    SelectedFragment = new add();
                    break;

//                case R.id.admin:
//                    SelectedFragment = new profile();
//                    break;


            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Framelayout,SelectedFragment).commit();
            return true;

        }
    };
    }
