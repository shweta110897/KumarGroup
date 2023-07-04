package com.example.kumarGroup.Village_List;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.example.kumarGroup.Inquiry.InquiryMainActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiry.ViewInqAllCatActivity;

import java.util.List;


public class VillageListAdapter extends RecyclerView.Adapter<VillageListAdapter.ViewHolder>{

    Context context;
    List<com.example.kumarGroup.VillageList.Name> Cat1;
    Activity activity;


    public VillageListAdapter(Activity activity, Context context, List<com.example.kumarGroup.VillageList.Name> Cat1) {
        this.activity = activity;
        this.context = context;
        this.Cat1 = Cat1;
    }

    @NonNull
    @Override
    public VillageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_row_follow_up, parent, false);
        Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall");
        VillageListAdapter.ViewHolder viewHolder = new VillageListAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VillageListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWeekNameFollowUpRow.setText(Cat1.get(position).getVname());
        holder.txtWeekValueFollowUpRow.setText(Cat1.get(position).getCount());

        holder.txtWeekNameFollowUpRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ViewInqAllCatActivity.deal_stage_Village_List){
                    Village_List_Activity.Village_List_Check_View_Inquire = true;
                }else if (InquiryMainActivity.deal_stage_village_list){
                    Village_List_Activity.Village_List_Check_My_Inquire = true;
                }
                Intent intent = new Intent(context,VillageListShowActivity.class);
                intent.putExtra("vid",Cat1.get(position).getVid());
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
