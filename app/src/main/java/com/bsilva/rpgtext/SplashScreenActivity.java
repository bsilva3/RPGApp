package com.bsilva.rpgtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMainMenu();
            }
        }, 2500);
    }

    private void showMainMenu() {
        Intent intent = new Intent(
                SplashScreenActivity.this,MainActivity.class
        );
        startActivity(intent);
        finish();
    }
}
