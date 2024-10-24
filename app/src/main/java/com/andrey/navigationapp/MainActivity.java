package com.andrey.navigationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button mButtonClient;
    Button mButtomDriver;

    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mButtonClient = findViewById(R.id.userButtom);
        mButtomDriver = findViewById(R.id.otherButtom);

        mButtonClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditor.putString("user", "client");
                mEditor.apply();
                goToSelectAuth();
            }
        });
        mButtomDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditor.putString("user", "driver");
                mEditor.apply();
                goToSelectAuth();
            }
        });
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActitivty.class);
        startActivity(intent);
    }
}