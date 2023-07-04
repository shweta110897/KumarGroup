package com.example.kumarGroup.Workshop.Weekly;

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

import com.example.kumarGroup.DetailWeekWS;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Workshop.Month.MonthWeekDayWsTwoActivity;

import java.util.List;

public class WeekWsOneAdapter extends RecyclerView.Adapter<WeekWsOneAdapter.ViewHolder>
{
    Context context;
    List<DetailWeekWS> detailWeekWS;

    public WeekWsOneAdapter(Context context, List<DetailWeekWS> detailWeekWS) {
        this.context = context;
        this.detailWeekWS = detailWeekWS;
    }

    @NonNull
    @Override
    public WeekWsOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        WeekWsOneAdapter.ViewHolder viewHolder = new WeekWsOneAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeekWsOneAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {



        if(detailWeekWS.get(position).getDay()!=null){
            holder.txtWeekNameFollowUpRow.setText(detailWeekWS.get(position).getDay());
            holder.txtWeekValueFollowUpRow.setText(String.valueOf(detailWeekWS.get(position).getCount()));

            holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new  Intent(context, MonthWeekDayWsTwoActivity.class);
                    i.putExtra("id",detailWeekWS.get(position).getId());
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
        return detailWeekWS.size();
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
