package com.example.mycollege.UI.Gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycollege.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    private RecyclerView technomaxRecycler, convocationRecycler, orientationRecycler, sportsTechRecycler, otherEventRecycler;
    private GalleryAdapter adapter;

    private DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_gallery, container, false);

        technomaxRecycler = view.findViewById(R.id.technoMaxRecycler);
        convocationRecycler = view.findViewById(R.id.convocationRecycler);
        orientationRecycler = view.findViewById(R.id.orientationRecycler);
        sportsTechRecycler = view.findViewById(R.id.sportsTechRecycler);
        otherEventRecycler = view.findViewById(R.id.otherEventRecycler);

        reference= FirebaseDatabase.getInstance().getReference().child("gallery");

        getTechnoMax();
        getSportTech();
        getConvocation();
        getOrientation();
        getOtherEvent();

        return view;
    }

    private void getOtherEvent() {
        reference.child("Other Events").addValueEventListener(new ValueEventListener() {
            List<String> images= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                    String data = (String) snapshot1.getValue();
                    images.add(data);
                }
                adapter= new GalleryAdapter(getContext(), images);
                otherEventRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                otherEventRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getOrientation() {
        reference.child("Orientation").addValueEventListener(new ValueEventListener() {
            List<String> images= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                    String data = (String) snapshot1.getValue();
                    images.add(data);
                }
                adapter= new GalleryAdapter(getContext(), images);
                orientationRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                orientationRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getConvocation() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {
            List<String> images= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                    String data = (String) snapshot1.getValue();
                    images.add(data);
                }
                adapter= new GalleryAdapter(getContext(), images);
                convocationRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                convocationRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getSportTech() {
        reference.child("Sports Tech").addValueEventListener(new ValueEventListener() {
            List<String> images= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                    String data = (String) snapshot1.getValue();
                    images.add(data);
                }
                adapter= new GalleryAdapter(getContext(), images);
                sportsTechRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                sportsTechRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getTechnoMax() {
        reference.child("TechnoMax").addValueEventListener(new ValueEventListener() {
            List<String> images= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()) {
                    String data = (String) snapshot1.getValue();
                    images.add(data);
                }
                adapter= new GalleryAdapter(getContext(), images);
                technomaxRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                technomaxRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}