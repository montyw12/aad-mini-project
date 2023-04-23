package com.example.studentvarification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.hide();
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreen.this, Notice.class);
            startActivity(i);
            finish();
        }, 750);
    }
}