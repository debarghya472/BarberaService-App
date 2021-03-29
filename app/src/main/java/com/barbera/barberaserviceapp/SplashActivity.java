package com.barbera.barberaserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.barbera.barberaserviceapp.ui.login.LoginActivity;
import com.barbera.barberaserviceapp.ui.servicedetails.ServiceDetails;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    Animation grow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = (ImageView) findViewById(R.id.logo);
        grow = AnimationUtils.loadAnimation(this,R.anim.grow);
        logo.setAnimation(grow);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isLoggedIn()){
                    sendToMainActivity();
                }else {
                    sendToLoginActivity();
                }
            }
        },3000);
    }

    private boolean isLoggedIn() {
        return false;
    }

    private void sendToLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void sendToMainActivity(){
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}