package com.example.startechnical.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.startechnical.AssisterClasses.SliderAdapter;
import com.example.startechnical.R;

public class onBoarding extends AppCompatActivity
{
    ViewPager2 viewPager2;
    LinearLayout linearLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS,WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS);
        setContentView(R.layout.activity_on_boarding);


        viewPager2     = findViewById(R.id.onboarding_slider);
        linearLayout  = findViewById(R.id.onboarding_linearlayout);

        sliderAdapter  = new SliderAdapter(this);
        viewPager2.setAdapter(sliderAdapter);

        addDots(0);


    }

    private void addDots(int position)
    {
        dots = new TextView[5];
        linearLayout.removeAllViews();

        for(int i=0; i<dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226") );
            dots[i].setTextSize(35);

            linearLayout.addView(dots[i]);
        }

        if(dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.g_blue_purple));
        }

    }

    ViewPager2.OnPageChangeCallback changeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };
}