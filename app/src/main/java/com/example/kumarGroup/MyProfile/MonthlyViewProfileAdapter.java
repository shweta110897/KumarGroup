package com.example.kumarGroup.MyProfile;

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

import com.example.kumarGroup.DetailMonthViewProfile;
import com.example.kumarGroup.R;
import com.example.kumarGroup.myProfileNew.MonthWeekDaySecondActivity;

import java.util.List;

public class MonthlyViewProfileAdapter extends RecyclerView.Adapter<MonthlyViewProfileAdapter.ViewHolder>
{
    Context context;
    List<DetailMonthViewProfile> detailMonthViewProfileList;

    public MonthlyViewProfileAdapter(Context context, List<DetailMonthViewProfile> detailMonthViewProfileList) {
        this.context = context;
        this.detailMonthViewProfileList = detailMonthViewProfileList;
    }

    @NonNull
    @Override
    public MonthlyViewProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        MonthlyViewProfileAdapter.ViewHolder viewHolder = new MonthlyViewProfileAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyViewProfileAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(detailMonthViewProfileList.get(position).getMonth());
        holder.txtWeekValueFollowUpRow.setText(detailMonthViewProfileList.get(position).getCount()+"");

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MonthWeekDaySecondActivity.class);
                i.putExtra("catId",detailMonthViewProfileList.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailMonthViewProfileList.size();
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
