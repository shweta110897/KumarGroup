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

import com.example.kumarGroup.DetailRCMonth;
import com.example.kumarGroup.R;

import java.util.List;

public class MonthlyRCAdapater  extends RecyclerView.Adapter<MonthlyRCAdapater.ViewHolder> {

    Context context;
    List<DetailRCMonth> detailRCMonths;

    public MonthlyRCAdapater(Context context, List<DetailRCMonth> detailRCMonths) {
        this.context = context;
        this.detailRCMonths = detailRCMonths;
    }

    @NonNull
    @Override
    public MonthlyRCAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        MonthlyRCAdapater.ViewHolder viewHolder = new MonthlyRCAdapater.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyRCAdapater.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtWeekValueFollowUpRow.setText(String.valueOf(detailRCMonths.get(position).getCount()));
        holder.txtWeekNameFollowUpRow.setText(detailRCMonths.get(position).getMonth());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i =new  Intent(context, AllEntryMonthWeekDayActivity.class);
                Intent i =new  Intent(context, MonthlySearchActivity.class);
                i.putExtra("id",detailRCMonths.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailRCMonths.size();
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
