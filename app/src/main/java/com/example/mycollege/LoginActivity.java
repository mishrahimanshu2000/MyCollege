package com.example.mycollege;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button loginBtn;
    private TextView register;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginButton);
        register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(view -> {
            loginUser();
        });

        register.setOnClickListener(view -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void loginUser() {
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            loginEmail.setError("Please enter Email");
            loginEmail.requestFocus();
        } else if(TextUtils.isEmpty(password)) {
            loginPassword.setError("Please enter password");
            loginPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Error: "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}