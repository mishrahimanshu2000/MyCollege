package com.example.mycollege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class DeveloperInformation extends AppCompatActivity {

    private TextView email, phone , linkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Developer");
        setContentView(R.layout.activity_developer_information);
        email = findViewById(R.id.devMail);
        phone = findViewById(R.id.devPhone);
        linkedIn = findViewById(R.id.devSite);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, "mishrahimanshu2000@gmail.com");
                startActivity(intent);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+919118855553"));
                startActivity(intent);
            }
        });
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("https://www.linkedin.com/in/himanshu-mishra-golu/");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });


    }
}