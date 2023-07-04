package com.example.kumarGroup.Maintenance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kumarGroup.Maintainance;
import com.example.kumarGroup.R;

import java.util.List;

public class DisplayMaintenanceAdapter extends RecyclerView.Adapter<DisplayMaintenanceAdapter.ViewHolder>
{
    Context context;
    List<Maintainance> maintainances;

    public DisplayMaintenanceAdapter(Context context, List<Maintainance> maintainances) {
        this.context = context;
        this.maintainances = maintainances;
    }

    @NonNull
    @Override
    public DisplayMaintenanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.display_maintenance_row, parent, false);
        DisplayMaintenanceAdapter.ViewHolder viewHolder = new DisplayMaintenanceAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayMaintenanceAdapter.ViewHolder holder, int position) {

       // Glide.with(context).load("http://"+maintainances.get(position).getImage()).into(holder.imgDM);
        Glide.with(context).load(maintainances.get(position).getImage()).into(holder.imgDM);
        holder.txtDMMainTypeName.setText("Maintenance Type: "+"\n"+maintainances.get(position).getMain_type());
        holder.txtDMType.setText("Type: "+maintainances.get(position).getType());
        holder.txtDMName.setText("Name: "+maintainances.get(position).getMain_name());
        holder.txtDMAmount.setText("Amount: "+maintainances.get(position).getAmount());
        holder.txtPaymentType.setText("Payment Type: "+maintainances.get(position).getAmount());
        holder.txtDMDate.setText("Date: "+maintainances.get(position).getMdate());
        holder.txtDMDescription.setText("Description: "+maintainances.get(position).getDesc());

        holder.txtDMStatus.setText(maintainances.get(position).getStatus());

        if(maintainances.get(position).getStatus().equals("Approve")){
            holder.txtDMStatus.setBackgroundResource(R.color.Green);
        }
        else{
            holder.txtDMStatus.setBackgroundResource(R.color.red_btn_bg_color);
        }
    }

    @Override
    public int getItemCount() {
        return maintainances.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtDMMainTypeName,txtDMStatus,txtDMType,txtDMName,txtDMDate,txtDMDescription,txtDMAmount,txtPaymentType;
        ImageView imgDM;
        CardView cardStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imgDM=itemView.findViewById(R.id.imgDM);
            this.txtDMMainTypeName=itemView.findViewById(R.id.txtDMMainTypeName);
            this.txtDMStatus=itemView.findViewById(R.id.txtDMStatus);
            this.txtDMType=itemView.findViewById(R.id.txtDMType);
            this.txtDMName=itemView.findViewById(R.id.txtDMName);
            this.txtDMDate=itemView.findViewById(R.id.txtDMDate);
            this.txtDMDescription=itemView.findViewById(R.id.txtDMDescription);
            this.txtDMAmount=itemView.findViewById(R.id.txtDMAmount);
            this.txtPaymentType=itemView.findViewById(R.id.txtPaymentType);
           // this.cardStatus=itemView.findViewById(R.id.cardStatus);
        }
    }
}
