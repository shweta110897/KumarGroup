package com.example.kumarGroup.ViewInquiry;

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

import com.example.kumarGroup.DetailWeeklyViewInq;
import com.example.kumarGroup.R;

import java.util.List;

public class WeeklyViewInqOneAdapter extends RecyclerView.Adapter<WeeklyViewInqOneAdapter.ViewHolder>
{
    Context context;
    List<DetailWeeklyViewInq> detailWeeklyViewInqs;

    public WeeklyViewInqOneAdapter(Context context, List<DetailWeeklyViewInq> detailWeeklyViewInqs) {
        this.context = context;
        this.detailWeeklyViewInqs = detailWeeklyViewInqs;
    }

    @NonNull
    @Override
    public WeeklyViewInqOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        WeeklyViewInqOneAdapter.ViewHolder viewHolder = new WeeklyViewInqOneAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyViewInqOneAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(detailWeeklyViewInqs.get(position).getDay()!=null){
            holder.txtWeekNameFollowUpRow.setText(detailWeeklyViewInqs.get(position).getDay());
            holder.txtWeekValueFollowUpRow.setText(detailWeeklyViewInqs.get(position).getCount()+"");

            holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, MonthlyViewInqTwoDataActivity.class);
                    i.putExtra("catId_eid",detailWeeklyViewInqs.get(position).getId());
                    context.startActivity(i);
                }
            });
        }
        else{
            holder.lin_MainFollowUp.setClickable(false);
        }



       /* holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MonthlyViewInqTwoDataActivity.class);
                i.putExtra("catId_eid",detailWeeklyViewInqs.get(position).getId());
                context.startActivity(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return detailWeeklyViewInqs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtWeekNameFollowUpRow, txtWeekValueFollowUpRow;
        LinearLayout lin_MainFollowUp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtWeekNameFollowUpRow = itemView.findViewById(R.id.txtWeekNameFollowUpRow);
            this.txtWeekValueFollowUpRow = itemView.findViewById(R.id.txtWeekValueFollowUpRow);
            this.lin_MainFollowUp = itemView.findViewById(R.id.lin_MainFollowUp);
        }
    }
}
