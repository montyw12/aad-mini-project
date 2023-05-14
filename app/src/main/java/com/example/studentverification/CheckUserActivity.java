package com.example.studentverification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CheckUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user);
    }

    public void f1(View v) {
        Intent intent = new Intent(CheckUserActivity.this, Notice.class);
        startActivity(intent);
    }

    public void f2(View v) {
        Intent intent = new Intent(CheckUserActivity.this, AdminSignin.class);
        startActivity(intent);
    }
}