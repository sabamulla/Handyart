//package com.example.signin;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.Html;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
//
//public class onboarding extends AppCompatActivity {
//
//    ViewPager msliderviewPager;
//    LinearLayout mDotLayout;
//    Button backbtn, nextbtn, skipbtn;
//
//    TextView[] dots;
//    viewPagerAdaptor viewPagerAdaptor;
//    int i;
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_onboarding);
//        backbtn = findViewById(R.id.backbtn);
//        nextbtn = findViewById(R.id.nextbtn);
//        skipbtn = findViewById(R.id.skipbtn);
//
//        if (restorePrefData()){
//
//            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class );
//            startActivity(mainActivity);
//            finish();
//
//        }
//
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (getitems(0) > 0){
//
//                    msliderviewPager.setCurrentItem(getitems(-1), true);
//                }
//
//            }
//        });
//
//        nextbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (getitems(0) < 3)
//                    msliderviewPager.setCurrentItem(getitems(1),true);
//                else{
//                    Intent i = new Intent(onboarding.this,MainActivity.class);
//                    startActivity(i);
//                    savePrefsData();
//                    finish();
//                }
//
//            }
//        });
//
//        skipbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Intent i = new Intent(onboarding.this,MainActivity.class);
//                startActivity(i);
//                savePrefsData();
//                finish();
//
//            }
//        });
//
//        msliderviewPager = (ViewPager) findViewById(R.id.slideViewPager);
//        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);
//
//        viewPagerAdaptor = new viewPagerAdaptor(this);
//
//        msliderviewPager.setAdapter(viewPagerAdaptor);
//
//        setUpindicator(0);
//        msliderviewPager.addOnPageChangeListener(viewListener);
//
//    }
//
//    private void savePrefsData() {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putBoolean("isIntroOpnend",true);
//        editor.commit();
//    }
//
//    private boolean restorePrefData() {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
//        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
//        return  isIntroActivityOpnendBefore;
//    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void setUpindicator(int position) {
//
//        dots = new TextView[4];
//        mDotLayout.removeAllViews();
//
//        for (int i = 0; i < dots.length; i++) ;
//        {
//
//
//
//            dots[i] = new TextView(this);
//            dots[i].setText(Html.fromHtml("&#8226"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
//            mDotLayout.addView(dots[i]);
//
//        }
//        //dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
//
//    }
//
//    ViewPager.OnPageChangeListener viewListener =  new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.M)
//        @Override
//        public void onPageSelected(int position) {
//            setUpindicator(position);
//            if(position > 0){
//                backbtn.setVisibility(View.VISIBLE);
//
//            }else{
//
//                backbtn.setVisibility(View.INVISIBLE);
//
//            }
//
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    };
//
//    private int getitems(int i){
//
//        return msliderviewPager.getCurrentItem() + i;
//    }
//}