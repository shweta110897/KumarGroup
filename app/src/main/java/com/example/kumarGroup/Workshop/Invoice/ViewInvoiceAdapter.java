package com.example.kumarGroup.Workshop.Invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataViewInvoice;
import com.example.kumarGroup.R;

import java.util.List;

public class ViewInvoiceAdapter extends RecyclerView.Adapter<ViewInvoiceAdapter.ViewHolder>
{

    Context context;
    List<DataViewInvoice> dataViewInvoiceList;

    public ViewInvoiceAdapter(Context context, List<DataViewInvoice> dataViewInvoiceList) {
        this.context = context;
        this.dataViewInvoiceList = dataViewInvoiceList;
    }

    @NonNull
    @Override
    public ViewInvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.view_invoice_raw, parent, false);
        ViewInvoiceAdapter.ViewHolder viewHolder = new ViewInvoiceAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewInvoiceAdapter.ViewHolder holder, int position) {

        holder.tv_Cname_ViewInvoice.setText("Customer Name: "+dataViewInvoiceList.get(position).getCname());
        holder.tv_mobile_no_ViewInvoice.setText("Mobile No: "+dataViewInvoiceList.get(position).getMobileno());
        holder.tv_Village_ViewInvoice.setText("Village: "+dataViewInvoiceList.get(position).getVillage());
        holder.tv_Taluko_ViewInvoice.setText("Taluko: "+dataViewInvoiceList.get(position).getTehsil());
        holder.tv_District_ViewInvoice.setText("District: "+dataViewInvoiceList.get(position).getDistric());
        holder.tv_MechanicName_ViewInvoice.setText("Mechanic Name: "+dataViewInvoiceList.get(position).getMacanic_name());
        holder.tv_EngineNo_ViewInvoice.setText("Engine No: "+dataViewInvoiceList.get(position).getEngine_no());
        holder.tv_ChasisNo_ViewInvoice.setText("Chasis No: "+dataViewInvoiceList.get(position).getChasis_no());
        holder.tv_Date_ViewInvoice.setText("Date: "+dataViewInvoiceList.get(position).getEntry_date());
        holder.tv_WorkType_ViewInvoice.setText("Work Type: "+dataViewInvoiceList.get(position).getWorks_ser());


        String Rate = dataViewInvoiceList.get(position).getRate();
        String Rate1 =  Rate.replaceAll(",", "\n");

        String PartNo = dataViewInvoiceList.get(position).getMno();
        String PartNo1 = PartNo.replaceAll(",","\n");

        String PartDetail = dataViewInvoiceList.get(position).getModel_name();
        String PartDetail1 = PartDetail.replaceAll(",","\n");

        String PartQty = dataViewInvoiceList.get(position).getQty();
        String PartQty1 = PartQty.replaceAll(",","\n");

        String PartTot = dataViewInvoiceList.get(position).getQty();
        String PartTot1 = PartTot.replaceAll(",","\n");


        holder.tv_partNo_IV.setText(""+PartNo1);
        holder.tv_Part_Detail_IV.setText(""+PartDetail1);
        holder.tv_Part_Qty_VI.setText(""+PartQty1);
        holder.tv_Part_Rate_VI.setText(""+Rate1);
        holder.tv_Part_Total.setText(""+PartTot1);


        holder.tv_Petrol_Detail_IV.setText("Petrol Charge");
        holder.tv_Petrol_Total.setText(dataViewInvoiceList.get(position).getPetrol_charge());

        holder.tv_Laber_Detail_IV.setText("Labor Charge");
        holder.tv_Laber_Total.setText(dataViewInvoiceList.get(position).getPetrol_charge());

        holder.tv_DealPrice_ViewInvoice.setText("Total: "+dataViewInvoiceList.get(position).getDeal_price());
        holder.tv_DealPriceWord_ViewInvoice.setText("Total Price In Words: "
                +dataViewInvoiceList.get(position).getDeal_price_word());

    }

    @Override
    public int getItemCount() {
        return dataViewInvoiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_Cname_ViewInvoice,tv_mobile_no_ViewInvoice,tv_Village_ViewInvoice,tv_Taluko_ViewInvoice,
                tv_District_ViewInvoice,tv_MechanicName_ViewInvoice,
                tv_EngineNo_ViewInvoice,tv_ChasisNo_ViewInvoice,tv_Date_ViewInvoice,
                tv_WorkType_ViewInvoice;

        //Row1
        TextView tv_partNo_IV,tv_Part_Detail_IV,tv_Part_Qty_VI,tv_Part_Rate_VI,tv_Part_Total;

        //ROw2
        TextView tv_PetrolNo_IV,tv_Petrol_Detail_IV,tv_Petrol_Qty_VI,tv_Petrol_Rate_VI,tv_Petrol_Total;

        //Row3
        TextView tv_LaberNo_IV,tv_Laber_Detail_IV,tv_Laber_Qty_VI,tv_Laber_Rate_VI,tv_Laber_Total;

        TextView tv_DealPrice_ViewInvoice,tv_DealPriceWord_ViewInvoice;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            this.tv_Cname_ViewInvoice=itemView.findViewById(R.id.tv_Cname_ViewInvoice);
            this.tv_mobile_no_ViewInvoice=itemView.findViewById(R.id.tv_mobile_no_ViewInvoice);
            this.tv_Village_ViewInvoice=itemView.findViewById(R.id.tv_Village_ViewInvoice);
            this.tv_Taluko_ViewInvoice=itemView.findViewById(R.id.tv_Taluko_ViewInvoice);
            this.tv_District_ViewInvoice=itemView.findViewById(R.id.tv_District_ViewInvoice);
            this.tv_MechanicName_ViewInvoice=itemView.findViewById(R.id.tv_MechanicName_ViewInvoice);

            this.tv_EngineNo_ViewInvoice=itemView.findViewById(R.id.tv_EngineNo_ViewInvoice);
            this.tv_ChasisNo_ViewInvoice=itemView.findViewById(R.id.tv_ChasisNo_ViewInvoice);
            this.tv_Date_ViewInvoice=itemView.findViewById(R.id.tv_Date_ViewInvoice);
            this.tv_WorkType_ViewInvoice=itemView.findViewById(R.id.tv_WorkType_ViewInvoice);

            this.tv_partNo_IV=itemView.findViewById(R.id.tv_partNo_IV);
            this.tv_Part_Detail_IV=itemView.findViewById(R.id.tv_Part_Detail_IV);
            this.tv_Part_Qty_VI=itemView.findViewById(R.id.tv_Part_Qty_VI);
            this.tv_Part_Rate_VI=itemView.findViewById(R.id.tv_Part_Rate_VI);
            this.tv_Part_Total=itemView.findViewById(R.id.tv_Part_Total);

            this.tv_PetrolNo_IV=itemView.findViewById(R.id.tv_PetrolNo_IV);
            this.tv_Petrol_Detail_IV=itemView.findViewById(R.id.tv_Petrol_Detail_IV);
            this.tv_Petrol_Qty_VI=itemView.findViewById(R.id.tv_Petrol_Qty_VI);
            this.tv_Petrol_Rate_VI=itemView.findViewById(R.id.tv_Petrol_Rate_VI);
            this.tv_Petrol_Total=itemView.findViewById(R.id.tv_Petrol_Total);

            this.tv_LaberNo_IV=itemView.findViewById(R.id.tv_LaberNo_IV);
            this.tv_Laber_Detail_IV=itemView.findViewById(R.id.tv_Laber_Detail_IV);
            this.tv_Laber_Qty_VI=itemView.findViewById(R.id.tv_Laber_Qty_VI);
            this.tv_Laber_Rate_VI=itemView.findViewById(R.id.tv_Laber_Rate_VI);
            this.tv_Laber_Total=itemView.findViewById(R.id.tv_Laber_Total);

            this.tv_DealPrice_ViewInvoice=itemView.findViewById(R.id.tv_DealPrice_ViewInvoice);
            this.tv_DealPriceWord_ViewInvoice=itemView.findViewById(R.id.tv_DealPriceWord_ViewInvoice);

        }
    }
}
