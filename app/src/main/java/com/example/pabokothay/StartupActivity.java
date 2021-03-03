package com.example.pabokothay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class StartupActivity extends AppCompatActivity {

    private static int splashTime =10000;
    Animation topanime,animbot,fadein;
    ImageView t1,t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        //getSupportActionBar().hide();

//        fadein= AnimationUtils.loadAnimation(this,R.anim.fade_in);
//        t1=findViewById(R.id.toptext);
//        t1.setAnimation(fadein);
//
        animbot= AnimationUtils.loadAnimation(this,R.anim.botomanim);
        t2=findViewById(R.id.srt_imageView);
        t2.setAnimation(animbot);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(StartupActivity.this,LogInPage.class);
                startActivity(intent);
                finish();
            }
        }, splashTime);


    }
    public void goLogInPage(View view){
        Intent intent= new Intent(this,LogInPage.class);
        startActivity(intent);
    }
}