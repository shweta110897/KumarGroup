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

import com.example.kumarGroup.DetailWeeklyMyProfile;
import com.example.kumarGroup.R;

import java.util.List;

public class WeeklyFirstAdapter extends RecyclerView.Adapter<WeeklyFirstAdapter.ViewHolder> {

    Context context;
    List<DetailWeeklyMyProfile> detailWeeklyMyProfiles;

    public WeeklyFirstAdapter(Context context, List<DetailWeeklyMyProfile> detailWeeklyMyProfiles) {
        this.context = context;
        this.detailWeeklyMyProfiles = detailWeeklyMyProfiles;
    }

    @NonNull
    @Override
    public WeeklyFirstAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        WeeklyFirstAdapter.ViewHolder viewHolder = new WeeklyFirstAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyFirstAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(detailWeeklyMyProfiles.get(position).getDay());
        holder.txtWeekValueFollowUpRow.setText(detailWeeklyMyProfiles.get(position).getCount()+"");

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MonthWeekDaySecondActivity.class);
                i.putExtra("catId",detailWeeklyMyProfiles.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailWeeklyMyProfiles.size();
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
