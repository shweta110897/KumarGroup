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

import com.example.kumarGroup.CatMyProfile;
import com.example.kumarGroup.R;

import java.util.List;

public class CategoryMyProfileAdapter extends RecyclerView.Adapter<CategoryMyProfileAdapter.ViewHolder>
{
    Context context;
    List<CatMyProfile> catMyProfiles;

    public CategoryMyProfileAdapter(Context context, List<CatMyProfile> catMyProfiles) {
        this.context = context;
        this.catMyProfiles = catMyProfiles;
    }

    @NonNull
    @Override
    public CategoryMyProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        CategoryMyProfileAdapter.ViewHolder viewHolder = new CategoryMyProfileAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryMyProfileAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(catMyProfiles.get(position).getCat_list());
        holder.txtWeekValueFollowUpRow.setText(catMyProfiles.get(position).getCount());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CategoryDetailsTwoActivity.class);
                i.putExtra("catId_eid",catMyProfiles.get(position).getCat_id());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catMyProfiles.size();
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
