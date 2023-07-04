package com.example.kumarGroup.FollowUp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Detail;
import com.example.kumarGroup.R;

import java.util.ArrayList;
import java.util.List;

public class MonthFollowUpAdpater extends RecyclerView.Adapter<MonthFollowUpAdpater.ViewHolder> {

    Context context;
    ArrayList<String> MonthNames;
    List<Detail> MonthDetail;
    int a=0;

    public MonthFollowUpAdpater(Context context, ArrayList<String> monthNames,List<Detail> MonthDetail) {
        this.context = context;
        MonthNames = monthNames;
        this.MonthDetail= MonthDetail;
    }

    @NonNull
    @Override
    public MonthFollowUpAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        MonthFollowUpAdpater.ViewHolder viewHolder = new MonthFollowUpAdpater.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthFollowUpAdpater.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.txtWeekValueFollowUpRow.setText(String.valueOf(MonthDetail.get(position).getCount()));
       holder.txtWeekNameFollowUpRow.setText(MonthDetail.get(position).getMonth());

       holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Intent i =new  Intent(context,ShowMonthlyFUActivity.class);
              i.putExtra("id",MonthDetail.get(position).getId());
              context.startActivity(i);
           }
       });


    }

    @Override
    public int getItemCount() {
        return MonthDetail.size();
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
