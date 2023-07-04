package com.example.kumarGroup.ProfileVillage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.VillageListProfile;

import java.util.List;


public class VillageListProfileAdapter extends RecyclerView.Adapter<VillageListProfileAdapter.ViewHolder>{

    Context context;
    List<VillageListProfile.Name> Cat1;
    Activity activity;

    public VillageListProfileAdapter(Activity activity,Context context,List<VillageListProfile.Name> Cat1 ) {
        this.activity = activity;
        this.context = context;
        this.Cat1 = Cat1;
    }

    @NonNull
    @Override
    public VillageListProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        VillageListProfileAdapter.ViewHolder viewHolder = new VillageListProfileAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VillageListProfileAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(Cat1.get(position).getVname());
        holder.txtWeekValueFollowUpRow.setText(Cat1.get(position).getCount());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,VillageListShowProfileActivity.class);
                intent.putExtra("Vid",Cat1.get(position).getVid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Cat1.size();
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
