package com.example.startechnical.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.startechnical.R;

public class SplashScreen extends AppCompatActivity
{
    TextView appName;
    TextView appSlogan;
    TextView poweredBy;
    ImageView appLogo;
    ImageView splashscreen_background;

    //animation variables
    Animation side_anim, bottom_anim;

    //Animation_TimeOut
    public final int SlackScreen_TimeOut = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS);
        setContentView(R.layout.splash_screen);

        Initialization();
        //Animation Variables
        side_anim   = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.buttom_anim);

        //Animation Assign
        appLogo.setAnimation(side_anim);
        appName.setAnimation(side_anim);
        appSlogan.setAnimation(side_anim);
        poweredBy.setAnimation(bottom_anim);
        splashscreen_background.setAnimation(bottom_anim);

        //Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent intent = new Intent(getApplicationContext(), LoginDashboard_C.class);
                startActivity(intent);
                finish();
            }
        },SlackScreen_TimeOut);

    }

    public void Initialization()
    {
        appLogo   = findViewById(R.id.app_logo);
        appName   = findViewById(R.id.app_name);
        appSlogan = findViewById(R.id.app_slogan);
        poweredBy = findViewById(R.id.app_poweredby);
        splashscreen_background = findViewById(R.id.splashscreen_background);


    }
}