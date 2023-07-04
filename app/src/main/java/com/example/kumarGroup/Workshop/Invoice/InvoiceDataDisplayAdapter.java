package com.example.kumarGroup.Workshop.Invoice;

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

import com.example.kumarGroup.R;
import com.example.kumarGroup.WorkshopInvoice;

import java.util.List;

public class InvoiceDataDisplayAdapter extends RecyclerView.Adapter<InvoiceDataDisplayAdapter.ViewHolder>
{
    Context context;
    List<WorkshopInvoice> workshopInvoices;

    public InvoiceDataDisplayAdapter(Context context, List<WorkshopInvoice> workshopInvoices) {
        this.context = context;
        this.workshopInvoices = workshopInvoices;
    }

    @NonNull
    @Override
    public InvoiceDataDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.ws_invoice_data, parent, false);
        InvoiceDataDisplayAdapter.ViewHolder viewHolder = new InvoiceDataDisplayAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceDataDisplayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_WSI_Customer_name.setText("Customer Name: " + workshopInvoices.get(position).getCname());
        holder.tv_WSI_Customer_moblino.setText("Mobile No: " + workshopInvoices.get(position).getMobileno());
        holder.tv_WSI_Village.setText("Village: " + workshopInvoices.get(position).getVilage());
        holder.tv_WSI_deal_price.setText("Deal Price: " + workshopInvoices.get(position).getDeal_price());
        holder.tv_WSI_Mechanic_Name.setText("Mechanic Name: " + workshopInvoices.get(position).getMacanic());
        holder.tv_WSI_type.setText("Type: " + workshopInvoices.get(position).getType());


        holder.txt_WSI_ViewInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(context, ViewInvoiceActivity.class);
                Intent i = new Intent(context, ViewInvoiceNewActivity.class);
                i.putExtra("id",workshopInvoices.get(position).getId());
                context.startActivity(i);
            }
        });

        /*holder.txt_WSI_ViewInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(workshopInvoices.get(position).getPath()));
                context.startActivity(browserIntent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return workshopInvoices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout linWSMMain;
        TextView tv_WSI_Customer_name,tv_WSI_Customer_moblino,tv_WSI_Village,
                tv_WSI_deal_price,tv_WSI_Mechanic_Name,tv_WSI_type,txt_WSI_ViewInvoice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            this.linWSMMain = itemView.findViewById(R.id.linWSMMain);
            this.tv_WSI_Customer_name = itemView.findViewById(R.id.tv_WSI_Customer_name);
            this.tv_WSI_Customer_moblino = itemView.findViewById(R.id.tv_WSI_Customer_moblino);
            this.tv_WSI_Village = itemView.findViewById(R.id.tv_WSI_Village);
            this.tv_WSI_deal_price = itemView.findViewById(R.id.tv_WSI_deal_price);
            this.tv_WSI_Mechanic_Name = itemView.findViewById(R.id.tv_WSI_Mechanic_Name);
            this.tv_WSI_type = itemView.findViewById(R.id.tv_WSI_type);
            this.txt_WSI_ViewInvoice = itemView.findViewById(R.id.txt_WSI_ViewInvoice);
        }
    }
}
