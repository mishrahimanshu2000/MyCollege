package com.example.mycollege.UI.Faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mycollege.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

    private RecyclerView csDepartment, meDepartment, eeDepartment, ceDepartment, humDepartment;
    private LinearLayout csNoData, meNoData, eeNoData, ceNoData, humNoData;
    private List<FacultyData> csList, meList, eeList, ceList, humList;
    private FacultyAdapter facultyAdapter;

    private DatabaseReference databaseReference, reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_faculty, container, false);

        csDepartment = view.findViewById(R.id.csDepartment);
        meDepartment = view.findViewById(R.id.meDepartment);
        eeDepartment = view.findViewById(R.id.eeDepartment);
        ceDepartment = view.findViewById(R.id.ceDepartment);
        humDepartment = view.findViewById(R.id.humDepartment);

        csNoData = view.findViewById(R.id.csNoData);
        meNoData = view.findViewById(R.id.meNoData);
        eeNoData = view.findViewById(R.id.eeNoData);
        ceNoData = view.findViewById(R.id.ceNoData);
        humNoData = view.findViewById(R.id.humNoData);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Faculty");

        csDepartment();
        meDepartment();
        eeDepartment();
        ceDepartment();
        humDepartment();
        
        return view;
    }
    private void csDepartment() {
        reference = databaseReference.child("CSE");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                csList = new ArrayList<>();
                if(!snapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                } else {
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        FacultyData data = snapshot1.getValue(FacultyData.class);
                        csList.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyAdapter = new FacultyAdapter(csList, getContext(),"CSE");
                    csDepartment.setAdapter(facultyAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void meDepartment() {
        reference = databaseReference.child("ME");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                meList = new ArrayList<>();
                if(!snapshot.exists()){
                    meNoData.setVisibility(View.VISIBLE);
                    meDepartment.setVisibility(View.GONE);
                } else {
                    meNoData.setVisibility(View.GONE);
                    meDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        FacultyData data = snapshot1.getValue(FacultyData.class);
                        meList.add(data);
                    }
                    meDepartment.setHasFixedSize(true);
                    meDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyAdapter = new FacultyAdapter(meList, getContext(),"ME");
                    meDepartment.setAdapter(facultyAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void eeDepartment() {
        reference = databaseReference.child("ECE/EE");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eeList = new ArrayList<>();
                if(!snapshot.exists()){
                    eeNoData.setVisibility(View.VISIBLE);
                    eeDepartment.setVisibility(View.GONE);
                } else {
                    eeNoData.setVisibility(View.GONE);
                    eeDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        FacultyData data = snapshot1.getValue(FacultyData.class);
                        eeList.add(data);
                    }
                    eeDepartment.setHasFixedSize(true);
                    eeDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyAdapter = new FacultyAdapter(eeList, getContext(),"ECE/EE");
                    eeDepartment.setAdapter(facultyAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ceDepartment() {
        reference = databaseReference.child("CE");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ceList = new ArrayList<>();
                if(!snapshot.exists()){
                    ceNoData.setVisibility(View.VISIBLE);
                    ceDepartment.setVisibility(View.GONE);
                } else {
                    ceNoData.setVisibility(View.GONE);
                    ceDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        FacultyData data = snapshot1.getValue(FacultyData.class);
                        ceList.add(data);
                    }
                    ceDepartment.setHasFixedSize(true);
                    ceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyAdapter = new FacultyAdapter(ceList, getContext(),"CE");
                    ceDepartment.setAdapter(facultyAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void humDepartment() {
        reference = databaseReference.child("Humanities");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                humList = new ArrayList<>();
                if(!snapshot.exists()){
                    humNoData.setVisibility(View.VISIBLE);
                    humDepartment.setVisibility(View.GONE);
                } else {
                    humNoData.setVisibility(View.GONE);
                    humDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        FacultyData data = snapshot1.getValue(FacultyData.class);
                        humList.add(data);
                    }
                    humDepartment.setHasFixedSize(true);
                    humDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyAdapter = new FacultyAdapter(humList, getContext(),"Humanities");
                    humDepartment.setAdapter(facultyAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}