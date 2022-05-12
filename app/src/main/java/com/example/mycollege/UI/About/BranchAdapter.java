package com.example.mycollege.UI.About;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mycollege.R;

import java.util.List;

public class BranchAdapter extends PagerAdapter {

    private Context context;
    private List<BranchModel> list;

    public BranchAdapter(Context context, List<BranchModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.branch_item, container, false);

        ImageView branchImage;
        TextView branchTitle, branchDes;

        branchImage= view.findViewById(R.id.branch_image);
        branchTitle= view.findViewById(R.id.branch_title);
        branchDes= view.findViewById(R.id.branch_des);

        branchImage.setImageResource(list.get(position).getImage());

        branchTitle.setText(list.get(position).getTitle());
        branchDes.setText(list.get(position).getDescription());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
