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

public class RegisterActivity extends AppCompatActivity {

    private EditText registerEmail, registerPassword, confirmPassword;
    private Button registerBtn;
    private TextView loginTv;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        loginTv = findViewById(R.id.loginTV);
        registerBtn = findViewById(R.id.registerButton);

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(view -> {
            createUser();
        });
        loginTv.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

    }

    private void createUser() {
        String email = registerEmail.getText().toString();
        String password = registerPassword.getText().toString();
        String confirm = confirmPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            registerEmail.setError("Please enter Email");
            registerEmail.requestFocus();
        } else if(TextUtils.isEmpty(password)){
            registerPassword.setError("Please enter password");
            registerPassword.requestFocus();
        } else if(password.length()<8){
            registerPassword.setError("please enter at least 8 characters");
            registerPassword.requestFocus();
        } else if(TextUtils.isEmpty(confirm)){
            confirmPassword.setError("Please confirm your password");
            confirmPassword.requestFocus();
        } else if(!password.equals(confirm)){
            confirmPassword.setError("Password didn't matched");
            confirmPassword.requestFocus();
        } else {
           mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                       finish();
                   } else{
                       Toast.makeText(RegisterActivity.this, "Registration Error: "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }
    }
}