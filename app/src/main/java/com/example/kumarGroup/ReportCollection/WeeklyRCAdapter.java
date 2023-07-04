package com.example.kumarGroup.ReportCollection;

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

import com.example.kumarGroup.DetailRcWeekly;
import com.example.kumarGroup.R;

import java.util.List;

public class WeeklyRCAdapter extends RecyclerView.Adapter<WeeklyRCAdapter.ViewHolder> {

    Context context;
    List<DetailRcWeekly> detailRcWeeklies;

    public WeeklyRCAdapter(Context context, List<DetailRcWeekly> detailRcWeeklies) {
        this.context = context;
        this.detailRcWeeklies = detailRcWeeklies;
    }

    @NonNull
    @Override
    public WeeklyRCAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        WeeklyRCAdapter.ViewHolder viewHolder = new WeeklyRCAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyRCAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
      //  holder.txtWeekValueFollowUpRow.setText(String.valueOf(detailRcWeeklies.get(position).getCount()));
      //  holder.txtWeekNameFollowUpRow.setText(detailRcWeeklies.get(position).getDay());

        if(detailRcWeeklies.get(position).getDay()!=null){
            holder.txtWeekNameFollowUpRow.setText(detailRcWeeklies.get(position).getDay());
            holder.txtWeekValueFollowUpRow.setText(String.valueOf(detailRcWeeklies.get(position).getCount()));

            holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new  Intent(context, AllEntryMonthWeekDayActivity.class);
                    i.putExtra("id",detailRcWeeklies.get(position).getId());
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
        return detailRcWeeklies.size();
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
