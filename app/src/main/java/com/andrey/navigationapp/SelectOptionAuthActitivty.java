package com.andrey.navigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class SelectOptionAuthActitivty extends AppCompatActivity {

    Button mGoToLoginButton;
    Button mGoToRegisterButton;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth_actitivty);
//        mToolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        Objects.requireNonNull(getSupportActionBar()).setTitle("Seleccionar opción");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mGoToLoginButton = findViewById(R.id.buttonLogin);
        mGoToRegisterButton = findViewById(R.id.buttonRegister);

        mGoToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });

        mGoToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });
    }

    private void goToRegister() {
        Intent intent = new Intent(SelectOptionAuthActitivty.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void goToLogin() {
        Intent intent = new Intent(SelectOptionAuthActitivty.this, LoginActivity.class);
        startActivity(intent);
    }

}