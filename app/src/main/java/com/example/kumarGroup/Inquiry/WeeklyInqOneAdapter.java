package com.example.kumarGroup.Inquiry;

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

import com.example.kumarGroup.DetailWeeklyInq;
import com.example.kumarGroup.R;

import java.util.List;

public class WeeklyInqOneAdapter  extends RecyclerView.Adapter<WeeklyInqOneAdapter.ViewHolder>
{
    Context context;
    List<DetailWeeklyInq> detailWeeklyInqs;

    public WeeklyInqOneAdapter(Context context, List<DetailWeeklyInq> detailWeeklyInqs) {
        this.context = context;
        this.detailWeeklyInqs = detailWeeklyInqs;
    }

    @NonNull
    @Override
    public WeeklyInqOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        WeeklyInqOneAdapter.ViewHolder viewHolder = new WeeklyInqOneAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyInqOneAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(detailWeeklyInqs.get(position).getDay()!=null){
            holder.txtWeekNameFollowUpRow.setText(detailWeeklyInqs.get(position).getDay());
            holder.txtWeekValueFollowUpRow.setText(String.valueOf(detailWeeklyInqs.get(position).getCount()));

            holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new  Intent(context, MonthlyInqDataActivity.class);
                    i.putExtra("id",detailWeeklyInqs.get(position).getId());
                    context.startActivity(i);
                }
            });
        }
        else{
            holder.lin_MainFollowUp.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return detailWeeklyInqs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
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
