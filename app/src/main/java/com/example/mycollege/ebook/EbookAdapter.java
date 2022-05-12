package com.example.mycollege.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycollege.R;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookHolder> {

    private Context context;
    private List<EbookData> list;

    public EbookAdapter(Context context, List<EbookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout, parent, false);
        return new EbookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookHolder holder, int position) {
        EbookData item = list.get(position);
        holder.bookName.setText(item.getPdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenEbook.class);
                intent.putExtra("pdfUrl", item.getPdfUrl());
                context.startActivity(intent);
            }
        });


        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.getPdfUrl()));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class EbookHolder extends RecyclerView.ViewHolder {

        TextView bookName;
        ImageView download;

        public EbookHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.ebookName);
            download = itemView.findViewById(R.id.downloadEbook);
        }
    }
}
