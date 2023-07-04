package com.example.kumarGroup.DeliveryReport;

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

import com.example.kumarGroup.DataFirstService;
import com.example.kumarGroup.R;

import java.util.List;

public class FirstServiceAdapter  extends RecyclerView.Adapter<FirstServiceAdapter.ViewHolder>
{
    Context context;
    List<DataFirstService> dataFirstServices;

    public FirstServiceAdapter(Context context, List<DataFirstService> dataFirstServices) {
        this.context = context;
        this.dataFirstServices = dataFirstServices;
    }

    @NonNull
    @Override
    public FirstServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.first_service_raw, parent, false);
        FirstServiceAdapter.ViewHolder viewHolder = new FirstServiceAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FirstServiceAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_cat_name_fs.setText("Category Name:"+dataFirstServices.get(position).getCat_name());
        holder.txt_fname_fs.setText("Name:"+dataFirstServices.get(position).getFname());
        holder.txt_state_fs.setText("State:"+dataFirstServices.get(position).getState());
        holder.txt_city_fs.setText("City:"+dataFirstServices.get(position).getCity());
        holder.txt_district_fs.setText("District:"+dataFirstServices.get(position).getDistric());
        holder.txt_tehsil_fs.setText("Tehsil:"+dataFirstServices.get(position).getTehsil());
        holder.txt_vilage_fs.setText("Village:"+dataFirstServices.get(position).getVilage());
        holder.txt_moblino_fs.setText("Mobile No:"+dataFirstServices.get(position).getMoblino());
        holder.txt_whatsappno_fs.setText("WhatsApp No:"+dataFirstServices.get(position).getWhatsappno());
        holder.txt_employee_name_fs.setText("Employee Name:"+dataFirstServices.get(position).getEmployee_name());
        holder.txt_desc_fs.setText("Description:"+dataFirstServices.get(position).getDesc());
        holder.txt_follow_date_fs.setText("Follow Date:"+dataFirstServices.get(position).getFollow_date());


        holder.Lin_DeliveryReportMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FirstServiceFormActivity.class);
                i.putExtra("id",dataFirstServices.get(position).getAutoid());
                i.putExtra("MobileNo",dataFirstServices.get(position).getMoblino());
                i.putExtra("cuid",dataFirstServices.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataFirstServices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_cat_name_fs,txt_fname_fs,txt_state_fs,txt_city_fs,txt_district_fs,
                txt_tehsil_fs,txt_vilage_fs,txt_moblino_fs,txt_whatsappno_fs,
                txt_employee_name_fs,txt_desc_fs,txt_follow_date_fs;

        LinearLayout Lin_DeliveryReportMain;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txt_cat_name_fs = itemView.findViewById(R.id.txt_cat_name_fs);
            this.txt_fname_fs = itemView.findViewById(R.id.txt_fname_fs);
            this.txt_state_fs = itemView.findViewById(R.id.txt_state_fs);
            this.txt_city_fs = itemView.findViewById(R.id.txt_city_fs);
            this.txt_district_fs = itemView.findViewById(R.id.txt_district_fs);
            this.txt_tehsil_fs = itemView.findViewById(R.id.txt_tehsil_fs);
            this.txt_vilage_fs = itemView.findViewById(R.id.txt_vilage_fs);
            this.txt_moblino_fs = itemView.findViewById(R.id.txt_moblino_fs);
            this.txt_whatsappno_fs = itemView.findViewById(R.id.txt_whatsappno_fs);
            this.txt_employee_name_fs = itemView.findViewById(R.id.txt_employee_name_fs);
            this.txt_desc_fs = itemView.findViewById(R.id.txt_desc_fs);
            this.txt_follow_date_fs = itemView.findViewById(R.id.txt_follow_date_fs);

            this.Lin_DeliveryReportMain = itemView.findViewById(R.id.Lin_DeliveryReportMain);
        }
    }
}
