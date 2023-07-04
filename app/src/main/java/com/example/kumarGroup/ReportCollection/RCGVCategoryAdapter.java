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

import com.example.kumarGroup.CatRCCustomer;
import com.example.kumarGroup.R;

import java.util.List;

public class RCGVCategoryAdapter extends RecyclerView.Adapter<RCGVCategoryAdapter.ViewHolder> {

    Context context;
    List<CatRCCustomer> catRCCustomers;

    public RCGVCategoryAdapter(Context context, List<CatRCCustomer> catRCCustomers) {
        this.context = context;
        this.catRCCustomers = catRCCustomers;
    }


    @NonNull
    @Override
    public RCGVCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        RCGVCategoryAdapter.ViewHolder viewHolder = new RCGVCategoryAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RCGVCategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(catRCCustomers.get(position).getCate_name());
        holder.txtWeekValueFollowUpRow.setText(catRCCustomers.get(position).getCount());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ShowRCGVActivity.class);
                i.putExtra("catId_eid",catRCCustomers.get(position).getCatid());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catRCCustomers.size();
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
