package com.example.kumarGroup.Workshop.WsManager;

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

import com.example.kumarGroup.DataWSManager;
import com.example.kumarGroup.R;

import java.util.List;

public class WsManagerMainAdapter  extends RecyclerView.Adapter<WsManagerMainAdapter.ViewHolder>
{
    Context context;
    List<DataWSManager>  dataWSManagers;

    public WsManagerMainAdapter(Context context, List<DataWSManager> dataWSManagers) {
        this.context = context;
        this.dataWSManagers = dataWSManagers;
    }

    @NonNull
    @Override
    public WsManagerMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.ws_manager, parent, false);
        WsManagerMainAdapter.ViewHolder viewHolder = new WsManagerMainAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WsManagerMainAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_WSM_Customer_name.setText("Customer Name: " + dataWSManagers.get(position).getCname());
        holder.tv_WSM_Customer_moblino.setText("Mobile No: " + dataWSManagers.get(position).getMobileno());
        holder.tv_WSM_Village.setText("Village: " + dataWSManagers.get(position).getVillage());
        holder.tv_WSM_deal_price.setText("Deal Price: " + dataWSManagers.get(position).getDeal_price());
        holder.tv_WSM_Mechanic_Name.setText("Mechanic Name: " + dataWSManagers.get(position).getMacanic_name());
        holder.tv_WSM_type.setText("Type: " + dataWSManagers.get(position).getType());
        holder.tv_WSMWork_type.setText("Work Type: " + dataWSManagers.get(position).getWorks_ser());


        holder.linWSMMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,WsManagerEditFormActivity.class);
                i.putExtra("MobileNo",dataWSManagers.get(position).getMobileno());
                i.putExtra("PartId",dataWSManagers.get(position).getId());
                i.putExtra("ChasisNo",dataWSManagers.get(position).getChasis_no());
                i.putExtra("EngieerNo",dataWSManagers.get(position).getEngine_no());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataWSManagers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout linWSMMain;
        TextView tv_WSM_Customer_name,tv_WSM_Customer_moblino,tv_WSM_Village,
                tv_WSM_deal_price,tv_WSM_Mechanic_Name,tv_WSM_type,
                tv_WSMWork_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.linWSMMain = itemView.findViewById(R.id.linWSMMain);
            this.tv_WSM_Customer_name = itemView.findViewById(R.id.tv_WSM_Customer_name);
            this.tv_WSM_Customer_moblino = itemView.findViewById(R.id.tv_WSM_Customer_moblino);

            this.tv_WSM_Village = itemView.findViewById(R.id.tv_WSM_Village);
            this.tv_WSM_deal_price = itemView.findViewById(R.id.tv_WSM_deal_price);
            this.tv_WSM_Mechanic_Name = itemView.findViewById(R.id.tv_WSM_Mechanic_Name);
            this.tv_WSM_type = itemView.findViewById(R.id.tv_WSM_type);
            this.tv_WSMWork_type = itemView.findViewById(R.id.tv_WSMWork_type);


        }
    }
}
