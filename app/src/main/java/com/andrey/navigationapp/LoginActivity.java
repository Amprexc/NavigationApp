package com.andrey.navigationapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    Button mLoginButton;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //AlertDialog.Builder mDialog = new AlertDialog.Builder(this);

    Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextInputEmail = findViewById(R.id.inputEmailLogin);
        mTextInputPassword = findViewById(R.id.inputPasswordLogin);
        mLoginButton = findViewById(R.id.startSessionButton);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //mDialog.setTitle("Autenticando");
        //mDialog = new AlertDialog().builder().setContext(LoginActivity.this).setMessage("Autenticando").build();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

//        mToolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("Inicia sesión");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void login() {
        String strEmail = mTextInputEmail.getText().toString();
        String strPassword  = mTextInputPassword.getText().toString();

        if(strEmail.isEmpty() || strPassword.isEmpty()){
            Toast.makeText(this, "Ingrese datos", Toast.LENGTH_SHORT);
            return;
        }
        //mDialog.show();
        mAuth.signInWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ClientMapActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}