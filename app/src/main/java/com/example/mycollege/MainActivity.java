package com.example.mycollege;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mycollege.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private long back;
    private Toast toast;

    FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String selected;
    private int checked_item;
    private final String CHECKED_ITEM = "checked_item";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        bottomNavigationView = findViewById(R.id.bottomNavView);
        navController= Navigation.findNavController(this, R.id.frame_layout);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationDrawer);
        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switch (getChecked_item()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.developer:
                startActivity(new Intent(this, DeveloperInformation.class));
                break;
            case R.id.eBooks:
                startActivity(new Intent(this, EbookActivity.class));
                break;
            case R.id.website:
                gotoUrl();
                break;
            case R.id.review:
                Toast.makeText(this, "Review", Toast.LENGTH_SHORT).show();
                break;
            case R.id.theme:
                showDialog();
                break;
            case R.id.share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                mAuth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void gotoUrl() {
        Uri uri= Uri.parse("https://ritroorkee.in/");
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void showDialog() {
        String[] theme = this.getResources().getStringArray(R.array.themes);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.themes, getChecked_item(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected= theme[i];
                checked_item=i;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(selected==null){
                    selected= theme[i];
                    checked_item=i;
                }
                switch (selected){
                    case "System Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                }
                setChecked_item(checked_item);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public int getChecked_item() {
        return sharedPreferences.getInt(CHECKED_ITEM,0);
    }

    public void setChecked_item(int i) {
        editor.putInt(CHECKED_ITEM,i);
        editor.apply();
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (back+3000 < System.currentTimeMillis()){
            toast= Toast.makeText(this, "Press Back Again to Exit", Toast.LENGTH_LONG);
            toast.show();
        } else{
            super.onBackPressed();
            toast.cancel();
        }
        back= System.currentTimeMillis();
    }
}