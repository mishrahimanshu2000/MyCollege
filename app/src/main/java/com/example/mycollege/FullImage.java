package com.example.mycollege;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.Objects;

public class FullImage extends AppCompatActivity {

    PhotoView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Image");

        String string = getIntent().getStringExtra("image");
        image = findViewById(R.id.photoView);
        Glide.with(this).load(string).into(image);

    }
}