package com.example.studentverification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Notice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
    }

    public void f1(View v) {
        LinearLayout layout = findViewById(R.id.ll00);
        CheckBox cb00 = findViewById(R.id.cb00);

        if (cb00.isChecked()) {
            Intent i = new Intent(Notice.this, FormFillup.class);
            startActivity(i);
        } else {
            Snackbar snackbar = Snackbar.make(layout, "Select notice checkbox to process next", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
        }
    }
}