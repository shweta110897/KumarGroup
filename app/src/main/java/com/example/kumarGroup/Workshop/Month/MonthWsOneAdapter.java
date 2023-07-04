package com.example.kumarGroup.Workshop.Month;

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

import com.example.kumarGroup.DetailMotnhWSOne;
import com.example.kumarGroup.R;

import java.util.List;

public class MonthWsOneAdapter extends RecyclerView.Adapter<MonthWsOneAdapter.ViewHolder>
{
    Context context;
    List<DetailMotnhWSOne> detailMotnhWSOnes;

    public MonthWsOneAdapter(Context context, List<DetailMotnhWSOne> detailMotnhWSOnes) {
        this.context = context;
        this.detailMotnhWSOnes = detailMotnhWSOnes;
    }

    @NonNull
    @Override
    public MonthWsOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        MonthWsOneAdapter.ViewHolder viewHolder = new MonthWsOneAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthWsOneAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekValueFollowUpRow.setText(String.valueOf(detailMotnhWSOnes.get(position).getCount()));
        holder.txtWeekNameFollowUpRow.setText(detailMotnhWSOnes.get(position).getMonth());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new  Intent(context, MonthWeekDayWsTwoActivity.class);
                i.putExtra("id",detailMotnhWSOnes.get(position).getId());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return detailMotnhWSOnes.size();
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
