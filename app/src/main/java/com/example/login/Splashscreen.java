package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {

    //Variables
    Animation topAnime;
    ImageView logo;

    private static int SPLASH_SCREEN=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        //Animations
        topAnime= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        //Hooks
        logo=findViewById(R.id.logo);
        //setAnimation
        logo.setAnimation(topAnime);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
           Intent intent=new Intent(Splashscreen.this,MainActivity3.class);
            startActivity(intent);
            finish();
            }
        },SPLASH_SCREEN);
    }
}