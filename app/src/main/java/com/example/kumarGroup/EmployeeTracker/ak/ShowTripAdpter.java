package com.example.kumarGroup.EmployeeTracker.ak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Datatripmodel;
import com.example.kumarGroup.R;

import java.util.List;

public class ShowTripAdpter extends RecyclerView.Adapter<ShowTripAdpter.viewHolder> {

    Context context;
    List<Datatripmodel> trilist;

    public ShowTripAdpter(Context context, List<Datatripmodel> trilist) {
        this.context = context;
        this.trilist = trilist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_data_row,parent,false);
        return new ShowTripAdpter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.belowlayout.setVisibility(View.GONE);
        holder.count_day_location.setText(String.valueOf(position+1));
        holder.daylocation.setText(trilist.get(position).getLocation());
        holder.day_time_location.setText(trilist.get(position).getDate());
        holder.daytime.setText(trilist.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return trilist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        LinearLayout belowlayout;
        TextView daylocation,count_day_location,day_time_location,daytime;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            belowlayout = itemView.findViewById(R.id.belowlayout);
            daylocation = itemView.findViewById(R.id.daylocation);
            count_day_location = itemView.findViewById(R.id.count_day_location);
            day_time_location = itemView.findViewById(R.id.day_time_location);
            daytime = itemView.findViewById(R.id.daytime);
        }
    }
}
