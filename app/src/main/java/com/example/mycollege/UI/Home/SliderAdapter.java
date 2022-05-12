package com.example.mycollege.UI.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mycollege.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private final List<SliderData> list;

    public SliderAdapter(Context context , ArrayList<SliderData> sliderDataArrayList) {
        this.list = sliderDataArrayList;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        SliderData item = list.get(position);
        Glide.with(viewHolder.itemView)
                .load(item.getImgUrl())
                .fitCenter()
                .into(viewHolder.sliderImageView);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView sliderImageView;
        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            sliderImageView = itemView.findViewById(R.id.sliderImageView);
            this.itemView=itemView;
        }
    }
}
