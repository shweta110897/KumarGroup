package com.example.kumarGroup.SSP.ViewAccountDatabank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatSSPViewAcc;
import com.example.kumarGroup.R;

import java.util.List;

public class ViewAccountDatabankAdapter extends RecyclerView.Adapter<ViewAccountDatabankAdapter.ViewHolder> {

    Context context;
    List<CatSSPViewAcc> catSSPViewAccs;

    public ViewAccountDatabankAdapter(Context context, List<CatSSPViewAcc> catSSPViewAccs) {
        this.context = context;
        this.catSSPViewAccs = catSSPViewAccs;
    }

    @NonNull
    @Override
    public ViewAccountDatabankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.ssp_view_account_raw, parent, false);
        ViewAccountDatabankAdapter.ViewHolder viewHolder = new ViewAccountDatabankAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAccountDatabankAdapter.ViewHolder holder, int position) {


        if(catSSPViewAccs.get(position).getStatus().equals("credit")){
            holder.Lin_ViewAccountDetail.setBackgroundResource(R.drawable.cardwhite);


            holder.txt_CuName.setText("Customer Name: "+catSSPViewAccs.get(position).getCu_name());
            holder.txt_Village.setText("Village Name: "+catSSPViewAccs.get(position).getVillage());
          //  holder.txt_WAmt.setText("Withdrawal Amount: "+catSSPViewAccs.get(position).getW_amt());
          //  holder.txt_WDate.setText("Withdrawal Date: "+catSSPViewAccs.get(position).getW_date());
            holder.txt_Status.setText("Status: "+catSSPViewAccs.get(position).getStatus());

          // holder.txt_Status.setTextColor(R.color.snack_red);

            holder.txt_Insensetive.setText("Insentive: "+catSSPViewAccs.get(position).getInsentive());
            holder.txt_Point_Value.setText("Point Value: "+catSSPViewAccs.get(position).getPoint_value());
          //  holder.txt_SmartCard.setText("Smart Card: "+catSSPViewAccs.get(position).getSmart_card());
            holder.txt_SalesDate.setText("Sales Date: "+catSSPViewAccs.get(position).getSales_date());


            holder.txt_WAmt.setVisibility(View.GONE);
            holder.txt_WDate.setVisibility(View.GONE);
            holder.txt_SmartCard.setVisibility(View.GONE);
            holder.txt_Bal.setVisibility(View.GONE);
        }

        if(catSSPViewAccs.get(position).getStatus().equals("debit")){
            holder.Lin_ViewAccountDetail.setBackgroundResource(R.color.colorLayout);


           // holder.txt_CuName.setText("Customer Name: "+catSSPViewAccs.get(position).getCu_name());
           // holder.txt_Village.setText("Village Name: "+catSSPViewAccs.get(position).getVillage());
            holder.txt_WAmt.setText("Withdrawal Amount: "+catSSPViewAccs.get(position).getW_amt());
            holder.txt_WDate.setText("Withdrawal Date: "+catSSPViewAccs.get(position).getW_date());
            holder.txt_Status.setText("Status: "+catSSPViewAccs.get(position).getStatus());
           // holder.txt_Insensetive.setText("Insentive: "+catSSPViewAccs.get(position).getInsentive());
           // holder.txt_Point_Value.setText("Point Value: "+catSSPViewAccs.get(position).getPoint_value());
            holder.txt_SmartCard.setText("Smart Card: "+catSSPViewAccs.get(position).getSmart_card());
            holder.txt_Bal.setText("Balance: "+catSSPViewAccs.get(position).getBal());


            holder.txt_CuName.setVisibility(View.GONE);
            holder.txt_Village.setVisibility(View.GONE);
            holder.txt_Insensetive.setVisibility(View.GONE);
            holder.txt_Point_Value.setVisibility(View.GONE);
            holder.txt_SalesDate.setVisibility(View.GONE);
        }




    }

    @Override
    public int getItemCount() {
        return catSSPViewAccs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout Lin_ViewAccountDetail;
        TextView txt_CuName,txt_Village,txt_WAmt,txt_WDate,
                txt_Status,txt_Insensetive,txt_Point_Value,
                txt_Product_Name,txt_SmartCard,
                txt_SalesDate,txt_Bal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.Lin_ViewAccountDetail = itemView.findViewById(R.id.Lin_ViewAccountDetail);
            this.txt_CuName = itemView.findViewById(R.id.txt_CuName);
            this.txt_Village = itemView.findViewById(R.id.txt_Village);
            this.txt_WAmt = itemView.findViewById(R.id.txt_WAmt);
            this.txt_WDate = itemView.findViewById(R.id.txt_WDate);
            this.txt_Status = itemView.findViewById(R.id.txt_Status);
            this.txt_Insensetive = itemView.findViewById(R.id.txt_Insensetive);
            this.txt_Point_Value = itemView.findViewById(R.id.txt_Point_Value);
            this.txt_SmartCard = itemView.findViewById(R.id.txt_SmartCard);
            this.txt_SalesDate = itemView.findViewById(R.id.txt_SalesDate);
            this.txt_Bal = itemView.findViewById(R.id.txt_Bal);
        }
    }
}
