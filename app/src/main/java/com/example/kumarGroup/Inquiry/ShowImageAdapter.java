package com.example.kumarGroup.Inquiry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kumarGroup.Datashowimage;
import com.example.kumarGroup.R;

import java.util.List;

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.viewHolder> {
    Context context;
    List<Datashowimage> mimagelist;

    public ShowImageAdapter(Context context, List<Datashowimage> mimagelist) {
        this.context = context;
        this.mimagelist = mimagelist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_image_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Glide.with(context).load(mimagelist.get(position).getImage()).into(holder.image_show);
        holder.date.setText(mimagelist.get(position).getCdate());
    }

    @Override
    public int getItemCount() {
        return mimagelist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView date;
        ImageView image_show;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            image_show = itemView.findViewById(R.id.image_show);
        }
    }
}
