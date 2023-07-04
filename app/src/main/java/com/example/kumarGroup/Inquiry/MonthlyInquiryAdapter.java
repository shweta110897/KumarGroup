package com.example.kumarGroup.Inquiry;

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

import com.example.kumarGroup.DetailInqMonth;
import com.example.kumarGroup.R;

import java.util.List;

public class MonthlyInquiryAdapter extends RecyclerView.Adapter<MonthlyInquiryAdapter.ViewHolder>
{
    Context context;
    List<DetailInqMonth>  detailInqMonths;

    public MonthlyInquiryAdapter(Context context, List<DetailInqMonth> detailInqMonths) {
        this.context = context;
        this.detailInqMonths = detailInqMonths;
    }


    @NonNull
    @Override
    public MonthlyInquiryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        MonthlyInquiryAdapter.ViewHolder viewHolder = new MonthlyInquiryAdapter.ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MonthlyInquiryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekValueFollowUpRow.setText(String.valueOf(detailInqMonths.get(position).getCount()));
        holder.txtWeekNameFollowUpRow.setText(detailInqMonths.get(position).getMonth());

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent i =new  Intent(context, MonthlyInqDataActivity.class);
                Intent i =new  Intent(context, MonthlyInqDataFilterActivity.class);
                i.putExtra("id",detailInqMonths.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailInqMonths.size();
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
