package com.example.signin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.signin.adapters.loginAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
//    FloatingActionButton fb,google,twitter;
    float v=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
//        fb=findViewById(R.id.Fab_facebook);
//        google=findViewById(R.id.Fab_google);
//        twitter=findViewById(R.id.Fab_twitter);


        tabLayout.addTab(tabLayout.newTab().setText("login"));
        tabLayout.addTab(tabLayout.newTab().setText("signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

         final loginAdapter adapter= new loginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
         viewPager.setAdapter(adapter);

         viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setTranslationY(300);



      tabLayout.setAlpha(v);

//        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
//        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
//        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:tabLayout.setScrollPosition(0,0f,true);
                        viewPager.setCurrentItem(0);;
                        break;
                    case 1:tabLayout.setScrollPosition(1,0f,true);
                        viewPager.setCurrentItem(1);;
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder= new AlertDialog.Builder(this );
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //    MainActivity.super.onBackPressed();
                        finishActivity(i);
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}