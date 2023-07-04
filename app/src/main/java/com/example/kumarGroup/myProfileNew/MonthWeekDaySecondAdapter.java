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

import com.example.kumarGroup.CatSecondMyProfile;
import com.example.kumarGroup.R;

import java.util.List;

public class MonthWeekDaySecondAdapter extends RecyclerView.Adapter<MonthWeekDaySecondAdapter.ViewHolder>
{
    Context context;
    List<CatSecondMyProfile> catSecondMyProfiles;

    String sms= "";

    public MonthWeekDaySecondAdapter(Context context, List<CatSecondMyProfile> catSecondMyProfiles) {
        this.context = context;
        this.catSecondMyProfiles = catSecondMyProfiles;
    }

    @NonNull
    @Override
    public MonthWeekDaySecondAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.month_week_day_myprofile_raw, parent, false);
        MonthWeekDaySecondAdapter.ViewHolder viewHolder = new MonthWeekDaySecondAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthWeekDaySecondAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtDisplayName.setText("Name: "+catSecondMyProfiles.get(position).getFname());
        holder.txtDisplayCategory.setText("Category: "+catSecondMyProfiles.get(position).getCat_name());
        holder.txtDisplayEmpName.setText("Employee Name: "+catSecondMyProfiles.get(position).getEmployee_name());
        holder.txtDisplayMobile.setText("Mobile: "+catSecondMyProfiles.get(position).getMoblino());
        holder.txtDisplayWhatsAppNumber.setText("Whats App Number: "+catSecondMyProfiles.get(position).getWhatsappno());
        holder.txtDisplayDescription.setText("Description: "+catSecondMyProfiles.get(position).getDesc());
        holder.txtDisplayVillage.setText("Village: "+catSecondMyProfiles.get(position).getVilage());
        holder.txtDisplayNextDate.setText("Next Date: "+catSecondMyProfiles.get(position).getVdate());
       // holder.txtDisplayTractorName.setText("Tractor Name: "+catSecondMyProfiles.get(position).getTractorname());
        holder.txtDisplayInquiryType.setText("FollowUp Type: "+catSecondMyProfiles.get(position).getInq_type());

        holder.txtDisplayTractorName.setVisibility(View.GONE);

        holder.Lin_monthlyDailyWeeklyMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MWDFormActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("customer_name",catSecondMyProfiles.get(position).getFname());
                i.putExtra("category",catSecondMyProfiles.get(position).getCat_name());
                i.putExtra("catId",catSecondMyProfiles.get(position).getCat_id());
                i.putExtra("sname",catSecondMyProfiles.get(position).getId());
             // i.putExtra("id",catSecondMyProfiles.get(position).getAutoid());
                i.putExtra("Vemp",catSecondMyProfiles.get(position).getVemp());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return catSecondMyProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout Lin_monthlyDailyWeeklyMyProfile;

        TextView txtDisplayName,txtDisplayCategory,txtDisplayEmpName,txtDisplayMobile,
                txtDisplayWhatsAppNumber,txtDisplayDescription,txtDisplayVillage,
                txtDisplayNextDate,txtDisplayTractorName,txtDisplayInquiryType;

        TextView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,lin_MDY_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtDisplayName=itemView.findViewById(R.id.txtDisplayName);
            this.txtDisplayCategory=itemView.findViewById(R.id.txtDisplayCategory);
            this.txtDisplayMobile=itemView.findViewById(R.id.txtDisplayMobile);
            this.txtDisplayWhatsAppNumber=itemView.findViewById(R.id.txtDisplayWhatsAppNumber);
            this.txtDisplayEmpName=itemView.findViewById(R.id.txtDisplayEmpName );

            this.txtDisplayTractorName=itemView.findViewById(R.id.txtDisplayTractorName );

            this.txtDisplayInquiryType=itemView.findViewById(R.id.txtDisplayInquiryType);
            this.txtDisplayDescription=itemView.findViewById(R.id.txtDisplayDescription);
            this.txtDisplayNextDate=itemView.findViewById(R.id.txtDisplayNextDate);
            this.txtDisplayVillage=itemView.findViewById(R.id.txtDisplayVillage);

            this.lin_MDY_call=itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms=itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp=itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share=itemView.findViewById(R.id.lin_MDY_share);

            this.Lin_monthlyDailyWeeklyMyProfile=itemView.findViewById(R.id.Lin_monthlyDailyWeeklyMyProfile);
        }
    }
}
