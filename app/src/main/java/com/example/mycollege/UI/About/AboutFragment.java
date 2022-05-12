package com.example.mycollege.UI.About;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mycollege.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter branchAdapter;
    private List<BranchModel> list;


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_about, container, false);

        list= new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_cse, "Computer Science","Computer Science and Engineering (CSE) is one of the topmost choices of engineering aspirants. With more than 5 centers of excellence (Apple India, CISCO Systems etc.)\n RIT Roorkee, School of Technology has introduced various courses such as Artificial Intelligence (AI), Machine Learning, Data Science and Analytics, Virtual Reality/Augmented Reality (AR/VR), Cyber Security and Block-chain in the modular format and training capsules."));
        list.add(new BranchModel(R.drawable.ic_electronics, "Electronics/Electrical", "Providing quality power and particularly green power, to cater the today’s need is the most difficult challenge, which scientists and engineers are facing in modern era. RIT Roorkee strives to do research on these sectors.\n Recently RIT signed an MOU with IIT Guwahati (Deshya Technologies) for research on Electric Vehicles."));
        list.add(new BranchModel(R.drawable.ic_mechanical,  "Mechanical Engineering", "RIT’s Department of Mechanical Engineering works closely with the industries to keep pace with the latest advancements in the manufacturing field. RIT has set up an Automation Lab in association with Prolific Technologies. \nIt is on the way to set up a 3D Printing Lab for enhancing the technical knowledge of the students." ));
        list.add(new BranchModel(R.drawable.ic_civil, "Civil Engineering", "Roorkee Institute of Technology, Roorkee aims at producing several eminent engineers, who are skilled, knowledgeable and can provide significant contributions in the planning and execution of civil engineering projects.\nOur Institute is considered to be the best civil engineering college in Uttarakhand. We are the only Institute in Uttarakhand to have an Indian Green Building Council (IGBC) Local Chapter."));

        branchAdapter= new BranchAdapter(getContext(), list);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(branchAdapter);

        ImageView imageView = view.findViewById(R.id.collegeImage);

        try {
            Glide.with(getContext())
                    .load("https://firebasestorage.googleapis.com/v0/b/my-college-17a08.appspot.com/o/maxresdefault.jpg?alt=media&token=caae810b-18c2-4e10-b4ba-695771efb1f9")
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }
}