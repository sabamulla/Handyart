package com.example.signin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homepage extends FragmentActivity {

BottomNavigationView navbar;
FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

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
                    SelectedFragment = new homeFragment();
                    break;

                case R.id.fav:
                    SelectedFragment = new favoritesFragment();
                    break;

                case R.id.cart:
                    SelectedFragment = new cartFragment();
                    break;

                case R.id.profile:
                    SelectedFragment = new profileFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Framelayout,SelectedFragment).commit();
            return true;

        }
        };


    @Override
    public void onBackPressed() {
        if(navbar.getSelectedItemId()==R.id.home) {

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
        else{
            navbar.setSelectedItemId(R.id.home);
        }
    }
}


