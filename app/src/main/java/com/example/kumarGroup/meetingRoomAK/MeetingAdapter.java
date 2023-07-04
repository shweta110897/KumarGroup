package com.example.kumarGroup.meetingRoomAK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Datameeting;
import com.example.kumarGroup.R;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.viewHolder> {

    Context context;
    List<Datameeting> linklist;

    public MeetingAdapter(Context context, List<Datameeting> linklist) {
        this.context = context;
        this.linklist = linklist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_meeting_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.linkid.setText(linklist.get(position).getLink());

        holder.joinnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent googlemeet = new Intent("android.intent.action.VIEW", Uri.parse(linklist.get(position).getLink()));
                context.startActivity(googlemeet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return linklist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView linkid;
        Button joinnow;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            linkid = itemView.findViewById(R.id.linkid);
            joinnow = itemView.findViewById(R.id.joinnow);
        }
    }
}
