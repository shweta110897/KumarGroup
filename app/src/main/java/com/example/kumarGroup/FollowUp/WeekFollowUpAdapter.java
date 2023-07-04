package com.example.kumarGroup.FollowUp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DetailWeeklyFollowUp;
import com.example.kumarGroup.R;

import java.util.List;

public class WeekFollowUpAdapter extends RecyclerView.Adapter<WeekFollowUpAdapter.ViewHolder> {

    Context context;
  //  List<DetailDayFollowUp> detailDayFollowUps;
    List<DetailWeeklyFollowUp> detailDayFollowUps;

    public WeekFollowUpAdapter(Context context,List<DetailWeeklyFollowUp> detailDayFollowUps) {
        this.context = context;
        this.detailDayFollowUps= detailDayFollowUps;
    }

    @NonNull
    @Override
    public WeekFollowUpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        WeekFollowUpAdapter.ViewHolder viewHolder = new WeekFollowUpAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeekFollowUpAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Log.d("fff", "onBindViewHolder: "+detailDayFollowUps.get(position).getDay());

       if(detailDayFollowUps.get(position).getDay()!=null){
           holder.txtWeekNameFollowUpRow.setText(detailDayFollowUps.get(position).getDay());
           holder.txtWeekValueFollowUpRow.setText(String.valueOf(detailDayFollowUps.get(position).getCount()));

           holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i =new  Intent(context,ShowMonthlyFUActivity.class);
                   i.putExtra("id",detailDayFollowUps.get(position).getId());
                   context.startActivity(i);
               }
           });
       }
       else{
           holder.lin_MainFollowUp.setClickable(false);
       }

        /*holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new  Intent(context,ShowMonthlyFUActivity.class);
                i.putExtra("id",detailDayFollowUps.get(position).getId());
                context.startActivity(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {

        return detailDayFollowUps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtWeekNameFollowUpRow,txtWeekValueFollowUpRow;
        LinearLayout lin_MainFollowUp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtWeekNameFollowUpRow=itemView.findViewById(R.id.txtWeekNameFollowUpRow);
            this.txtWeekValueFollowUpRow=itemView.findViewById(R.id.txtWeekValueFollowUpRow);
            this.lin_MainFollowUp=itemView.findViewById(R.id.lin_MainFollowUp);

        }
    }
}
