package com.example.kumarGroup.myProfileNew;

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

import com.example.kumarGroup.DetailMtProfileMonth;
import com.example.kumarGroup.R;

import java.util.List;

public class MonthlyFirstAdapter extends RecyclerView.Adapter<MonthlyFirstAdapter.ViewHolder>
{
    Context context;
    List<DetailMtProfileMonth> DetailMtProfileMonth;

    public MonthlyFirstAdapter(Context context, List<DetailMtProfileMonth> detailMtProfileMonth) {
        this.context = context;
        DetailMtProfileMonth = detailMtProfileMonth;
    }

    @NonNull
    @Override
    public MonthlyFirstAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        MonthlyFirstAdapter.ViewHolder viewHolder = new MonthlyFirstAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyFirstAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(DetailMtProfileMonth.get(position).getMonth());
        holder.txtWeekValueFollowUpRow.setText(DetailMtProfileMonth.get(position).getCount()+"");

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MonthWeekDaySecondActivity.class);
                i.putExtra("catId",DetailMtProfileMonth.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DetailMtProfileMonth.size();
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
