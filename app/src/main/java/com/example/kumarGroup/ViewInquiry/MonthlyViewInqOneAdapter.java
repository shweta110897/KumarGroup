package com.example.kumarGroup.ViewInquiry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DealstageAK.DealstageRecyclerActivity;
import com.example.kumarGroup.DetailViewInqMonth;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInquiryDealStage.DealstageRecyclerActivityViewINQ;

import java.util.List;


public class MonthlyViewInqOneAdapter extends RecyclerView.Adapter<MonthlyViewInqOneAdapter.ViewHolder>
{
    Context context;
    List<DetailViewInqMonth>   viewInqMonthlyOneModels;

    public MonthlyViewInqOneAdapter(Context context, List<DetailViewInqMonth> viewInqMonthlyOneModels) {
        this.context = context;
        this.viewInqMonthlyOneModels = viewInqMonthlyOneModels;
    }

    @NonNull
    @Override
    public MonthlyViewInqOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        MonthlyViewInqOneAdapter.ViewHolder viewHolder = new MonthlyViewInqOneAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyViewInqOneAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(viewInqMonthlyOneModels.get(position).getMonth());
        holder.txtWeekValueFollowUpRow.setText(viewInqMonthlyOneModels.get(position).getCount()+"");

        holder.lin_MainFollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (0 == viewInqMonthlyOneModels.get(position).getCount()){
                    Utils.showErrorToast(context,"No Data available");
                }
                else {

                    if (MonthlyViewInqOneActivity.myINQshowDeliveryDataFlag) {
                        Log.d("TAG", "onClick: MonthlyViewInqOneAdapter123-1");

                        DealstageRecyclerActivity.deal_deliveryInquiry_flag = true;
                        Intent i = new Intent(context, DealstageRecyclerActivity.class);
                        i.putExtra("actionbar", "Delivery");
                        i.putExtra("catId_eid", viewInqMonthlyOneModels.get(position).getId());
                        context.startActivity(i);
                    } else if (MonthlyViewInqOneActivity.viewINQshowDeliveryDataFlag) {

                        Log.d("TAG", "onClick: MonthlyViewInqOneAdapter123-2");

                        DealstageRecyclerActivityViewINQ.deal_deliveryInquiry_flag = true;
                        Intent i = new Intent(context, DealstageRecyclerActivityViewINQ.class);
                        i.putExtra("actionbar", "View inquiry Delivery");
                        i.putExtra("Id", viewInqMonthlyOneModels.get(position).getId());
                        context.startActivity(i);
                    } else {
                        Log.d("TAG", "onClick: MonthlyViewInqOneAdapter123-3"+"month");

                        Intent i = new Intent(context, MonthlyViewInqTwoDataActivity.class);
                        i.putExtra("catId_eid", viewInqMonthlyOneModels.get(position).getId());
                        context.startActivity(i);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewInqMonthlyOneModels.size();
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
