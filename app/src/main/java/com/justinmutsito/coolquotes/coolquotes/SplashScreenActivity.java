package com.justinmutsito.coolquotes.coolquotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.justinmutsito.coolquotes.coolquotes.Settings.SettingsActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//Delay app start for 1.5 seconds while checking the app settings.
            Thread timer = new Thread(){
                public void run(){
                    try {
                        sleep(1500);
                    } catch (InterruptedException e) {
                        //IntentionalLy left blank
                    }
                    finally {
                        Intent intent = new Intent(SplashScreenActivity.this,SettingsActivity.class);
                        intent.putExtra(getString(R.string.intentKey),"leave");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
            };
        timer.start();

    }
}
