package com.example.kumarGroup.DealstageAK;


import android.annotation.SuppressLint;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;

import java.util.List;

public class AdapterNotesDealStage extends RecyclerView.Adapter<AdapterNotesDealStage.viewHolder>
{
    Context context;
    List<Notes_POJO.Cat> mcatlist;


    public AdapterNotesDealStage(Context context, List<Notes_POJO.Cat> mcatlist) {
        this.context = context;
        this.mcatlist = mcatlist;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {

        Log.d("dateeee",mcatlist.get(position).getDate());
        Log.d("getRecentNote",mcatlist.get(position).getRecentNote());
        holder.tv_date.setText(mcatlist.get(position).getDate());
        holder.tv_dealstage.setText(mcatlist.get(position).getFollowUp());
        holder.tv_location.setText(mcatlist.get(position).getLocation());
        holder.tv_note.setText(mcatlist.get(position).getRecentNote());


    }

    @Override
    public int getItemCount() {
        return mcatlist.size();

    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tv_date,tv_dealstage,tv_location,tv_note;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
                tv_date=itemView.findViewById(R.id.tv_date);
                tv_dealstage=itemView.findViewById(R.id.tv_dealstage);
                tv_location=itemView.findViewById(R.id.tv_location);
                tv_note=itemView.findViewById(R.id.tv_note);
        }
    }



}
