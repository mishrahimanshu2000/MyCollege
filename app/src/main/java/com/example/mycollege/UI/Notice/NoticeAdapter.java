package com.example.mycollege.UI.Notice;

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

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {
    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_feed_layout, parent, false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {

        NoticeData currItem = list.get(position);
        holder.date.setText(currItem.getDate());
        holder.time.setText(currItem.getTime());
        holder.noticeTitle.setText(currItem.getTitle());
        try {
            if(currItem.getImage()!= null){
                Glide.with(context)
                        .load(currItem.getImage())
                        .into(holder.noticeImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.noticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullImage.class);
                intent.putExtra("image", currItem.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder{
        private TextView noticeTitle;
        private ImageView noticeImage;
        private TextView date, time;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            noticeImage = itemView.findViewById(R.id.noticeImage);
            date= itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

        }
    }
}
