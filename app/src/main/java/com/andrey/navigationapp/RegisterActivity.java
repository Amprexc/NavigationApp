package com.andrey.navigationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.andrey.navigationapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences mSharedPreferences;

    FirebaseAuth mAuth;
    DatabaseReference mDataBase;

    Button mRegisterButton;

    TextInputEditText inputName;
    TextInputEditText inputEmail;
    TextInputEditText inputPassword;

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        mToolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("Registrarse");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSharedPreferences = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();

        mDataBase = FirebaseDatabase.getInstance().getReference();

        mRegisterButton = findViewById(R.id.register_button);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        inputName = findViewById(R.id.input_register_name);
        inputEmail = findViewById(R.id.input_register_email);
        inputPassword = findViewById(R.id.input_register_password);


        
        

    }

    private void register() {

        String strName = inputName.getText().toString();
        String strEmail = inputEmail.getText().toString();
        String strPassword = inputPassword.getText().toString();
        if(strName.isEmpty()){
            Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if(strEmail.isEmpty()){
            Toast.makeText(this, "Ingrese un correo valido", Toast.LENGTH_SHORT).show();
            return;
        }
        if(strPassword.isEmpty() && strPassword.length() > 6){
            Toast.makeText(this, "Ingrese una contraseña valida", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                    saveUser(id, strName, strEmail);
                    //Toast.makeText(RegisterActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void saveUser(String id, String strName, String strEmail) {
        String strSelectedUser = mSharedPreferences.getString("user", "NA");

        User user = new User(id, strName,strEmail);

        if(strSelectedUser.equals("driver")){
            mDataBase.child("Users").child("Drivers").child(id).push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else if(strSelectedUser.equals("client")){
            mDataBase.child("Users").child("Clients").child(id).push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, ClientMapActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(RegisterActivity.this, "Usuario no valido", Toast.LENGTH_SHORT).show();
        }
    }
}