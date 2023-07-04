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

import com.example.kumarGroup.DetailDailyViewProfile;
import com.example.kumarGroup.R;
import com.example.kumarGroup.myProfileNew.MonthWeekDaySecondActivity;

import java.util.List;

public class WeekViewProfileAdapter extends RecyclerView.Adapter<WeekViewProfileAdapter.ViewHolder>
{
    Context context;
    List<DetailDailyViewProfile>  DetailDailyViewProfile;

    public WeekViewProfileAdapter(Context context, List<DetailDailyViewProfile> detailDailyViewProfile) {
        this.context = context;
        DetailDailyViewProfile = detailDailyViewProfile;
    }

    @NonNull
    @Override
    public WeekViewProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        WeekViewProfileAdapter.ViewHolder viewHolder = new WeekViewProfileAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewProfileAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(DetailDailyViewProfile.get(position).getDay());
        holder.txtWeekValueFollowUpRow.setText(DetailDailyViewProfile.get(position).getCount()+"");

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MonthWeekDaySecondActivity.class);
                i.putExtra("catId",DetailDailyViewProfile.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DetailDailyViewProfile.size();
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
