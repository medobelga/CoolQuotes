package com.justinmutsito.coolquotes.coolquotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

            Thread timer = new Thread(){
                public void run(){
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        //IntentionalLy left blank
                    }
                    finally {
                        Intent intent = new Intent(SplashScreenActivity.this,WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
            };
        timer.start();

    }
}
