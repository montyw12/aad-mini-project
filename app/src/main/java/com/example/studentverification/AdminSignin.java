package com.example.studentverification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminSignin extends AppCompatActivity {

    Button btnLogin;
    EditText userName, userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signin);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = findViewById(R.id.userName);
                userPassword = findViewById(R.id.userPassword);
                String userNameStr = userName.getText().toString();
                String userPasswordStr = userPassword.getText().toString();
                if(userNameStr.equals("admin")){
                    if(userPasswordStr.equals("admin#123 ")){
                        startActivity(new Intent(AdminSignin.this, AdminHome.class));
                    } else {
                        Toast.makeText(AdminSignin.this, "Invalid Password", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AdminSignin.this, "Invalid Username", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}