package com.example.elshamelapp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.bhargavms.dotloader.DotLoader;
import com.example.elshamelapp.R;

public class SplashCycleActivity extends BaseActivity {
    DotLoader dotLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
//        if(pref.getBoolean("activity_executed", false)){
//            Intent intent = new Intent(this, Regester.class);
//            startActivity(intent);
//            finish();
//        } else {
//            SharedPreferences.Editor ed = pref.edit();
//            ed.putBoolean("activity_executed", true);
//            ed.commit();
//        }
        // SET no title , full-screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // SET activity_splash.xml as layout
        final View viewSplash = View.inflate(this, R.layout.activity_splash, null);
        setContentView(viewSplash);
        dotLoader=(DotLoader)findViewById(R.id.text_dot_loader);
        final ProgressDialog progressDialog = new ProgressDialog(SplashCycleActivity.this);

        // Gradient Animation
        AlphaAnimation anim = new AlphaAnimation(0.5f, 1.0f); // change alpha from 0.5 to 1.0
        anim.setDuration(6000); // animate in 5000ms
        viewSplash.setAnimation(anim);
        anim.setAnimationListener(
                new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation animation) {
                        dotLoader.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dotLoader.setNumberOfDots(3);
                            }
                        }, 500);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // redirect to the other screen, such as HomeCycleActivity


                        Intent intent = new Intent(SplashCycleActivity.this, AboutApp.class);
                        startActivity(intent);

                        // close SplashActivity
                        finish();

                    }
                });

    }

    @Override
    public void onBackPressed() {

        this.finish();
    }
}
