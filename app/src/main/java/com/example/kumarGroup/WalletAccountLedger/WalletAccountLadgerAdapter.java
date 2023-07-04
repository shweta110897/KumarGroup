package com.example.kumarGroup.WalletAccountLedger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.AccountLadgerData;
import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.R;

import java.util.ArrayList;
import java.util.List;

public class WalletAccountLadgerAdapter extends RecyclerView.Adapter<WalletAccountLadgerAdapter.viewHolder> {

    Context context;
    ArrayList<AccountLadgerData> mcatlist;


    public WalletAccountLadgerAdapter(Context context, ArrayList<AccountLadgerData> mcatlist) {
        this.context = context;
        this.mcatlist = mcatlist;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_ladger, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.tv_product.setText("Product Name:- "+mcatlist.get(position).getProduct());
        holder.tv_Customername.setText("Customer Name:- "+mcatlist.get(position).getCustomerName());
        holder.tv_desc.setText("Description:- "+mcatlist.get(position).getDescription());
        holder.tv_credit.setText(mcatlist.get(position).getCredit());
        holder.tv_debit.setText(mcatlist.get(position).getDebit());

    }


    @Override
    public int getItemCount() {
        return mcatlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView tv_product, tv_Customername,tv_desc,tv_credit,tv_debit;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_product = itemView.findViewById(R.id.tv_product);
            this.tv_Customername = itemView.findViewById(R.id.tv_Customername);
            this.tv_desc = itemView.findViewById(R.id.tv_desc);
            this.tv_credit = itemView.findViewById(R.id.tv_credit);
            this.tv_debit = itemView.findViewById(R.id.tv_debit);


        }
    }




    public interface RecyclerViewItemInterface {

        void onItemClick(int position, List<Catnotattend> mcatlist);

        void onCallclick(int position, List<Catnotattend> mcatlist);

    }
}
