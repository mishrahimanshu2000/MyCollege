package com.example.mycollege.UI.Faculty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycollege.FullImage;
import com.example.mycollege.R;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewAdapter> {
    private List<FacultyData> list;
    private Context context;
    private String category;

    public FacultyAdapter(List<FacultyData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public FacultyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_data_layout, parent, false);
        return new FacultyViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewAdapter holder, int position) {
        FacultyData item = list.get(position);

        holder.name.setText(item.getName());
        holder.email.setText(item.geteMail());
        holder.post.setText(item.getPost());

        try {
            Glide.with(context).load(item.getImage()).placeholder(R.drawable.avatar).into(holder.facultyImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.facultyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullImage.class);
                intent.putExtra("image", item.getImage());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class FacultyViewAdapter extends RecyclerView.ViewHolder {
        private TextView name, email, post;
        private ImageView facultyImage;

        public FacultyViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.teacherName);
            email = itemView.findViewById(R.id.teacherEmail);
            post = itemView.findViewById(R.id.teacherPost);
            facultyImage = itemView.findViewById(R.id.teacherImage);
        }
    }
}
