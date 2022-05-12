package com.example.mycollege.UI.Home;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mycollege.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private String url1= "https://firebasestorage.googleapis.com/v0/b/my-college-17a08.appspot.com/o/images%2F23_04_2019_15_35_14_d45aa8f1595748300f04c9cdadb15ed0_ago130hkcn.jpg?alt=media&token=f489938d-9756-4261-89db-c3acf4beca90";
    private String url2 = "https://firebasestorage.googleapis.com/v0/b/my-college-17a08.appspot.com/o/images%2Fimage2.jpg?alt=media&token=7df4e272-b8cf-4535-965d-581576a7d91e";
    private String url3 = "https://firebasestorage.googleapis.com/v0/b/my-college-17a08.appspot.com/o/images%2Fimage3.png?alt=media&token=8bf4b50f-8686-499c-b50c-0d43a7a9290f";
    private String url4 = "https://firebasestorage.googleapis.com/v0/b/my-college-17a08.appspot.com/o/images%2Fimage4.jpg?alt=media&token=2681cc58-4300-409d-b125-2cc752eb9c74";

    private SliderView sliderView;
    private ImageView map;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<SliderData> list =new ArrayList<>();
        sliderView = view.findViewById(R.id.sliderView);
        map= view.findViewById(R.id.map);
        list.add(new SliderData(url1));
        list.add(new SliderData(url2));
        list.add(new SliderData(url3));
        list.add(new SliderData(url4));

        SliderAdapter adapter = new SliderAdapter(getActivity(), list);
        sliderView.setSliderAdapter(adapter);
        sliderView.startAutoCycle();
        sliderView.setAutoCycle(true);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.FILL);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setScrollTimeInSec(3);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });


        return view;
    }

    private void openMap() {
        Uri uri= Uri.parse("geo:0, 0?q=Roorkee Institute of Technology");
        Intent intent= new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}