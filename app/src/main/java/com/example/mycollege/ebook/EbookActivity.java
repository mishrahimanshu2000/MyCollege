package com.example.mycollege.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mycollege.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<EbookData> list;
    private EbookAdapter adapter;
    private ShimmerFrameLayout shimmer;
    private LinearLayout linearLayout;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
        Objects.requireNonNull(getSupportActionBar()).setTitle("E-Books");

        recyclerView= findViewById(R.id.ebookRecycler);
        shimmer = findViewById(R.id.shimmer_view_container);
        linearLayout = findViewById(R.id.shimmerLayout);
        reference= FirebaseDatabase.getInstance().getReference().child("pdf");
        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    EbookData data = dataSnapshot.getValue(EbookData.class);
                    list.add(data);
                }
                adapter= new EbookAdapter(EbookActivity.this, list);
                recyclerView.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                recyclerView.setAdapter(adapter);
                shimmer.stopShimmer();
                linearLayout.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EbookActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        shimmer.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        shimmer.startShimmer();
        super.onResume();
    }
}