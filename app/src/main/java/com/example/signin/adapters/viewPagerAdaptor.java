package com.example.signin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.signin.R;

public class viewPagerAdaptor extends PagerAdapter {

    Context context;

    int images[] ={

            R.drawable.images1,
            R.drawable.images2,
            R.drawable.images3,
            R.drawable.images4

    };

    int heading[] = {

            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth


    };

    int discription [] = {

            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three,
            R.string.desc_fourth,

    };

    public viewPagerAdaptor(Context context){

        this.context = context;
    }

    @Override
    public int getCount() {
        return  heading.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container, false);

        ImageView slidertitleimage = (ImageView) view.findViewById(R.id.titleImages);
        TextView slideHeading = (TextView) view.findViewById(R.id.texttitle);
        TextView slideDescription = (TextView) view.findViewById(R.id.textdiscription);

        slidertitleimage.setImageResource(images[position]);
        slideHeading.setText(heading[position]);
        slideDescription.setText(discription[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout)object);
    }
}
