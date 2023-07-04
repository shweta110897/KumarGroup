package com.example.kumarGroup.Workshop.WsPayPen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatPaymentWs;
import com.example.kumarGroup.R;

import java.util.List;

public class PaymentWSAdapter extends RecyclerView.Adapter<PaymentWSAdapter.ViewHolder>
{
    Context context;
    List<CatPaymentWs>  catPaymentWs;

    public PaymentWSAdapter(Context context, List<CatPaymentWs> catPaymentWs) {
        this.context = context;
        this.catPaymentWs = catPaymentWs;
    }

    @NonNull
    @Override
    public PaymentWSAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.payment_workshop_row, parent, false);
        PaymentWSAdapter.ViewHolder viewHolder = new PaymentWSAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentWSAdapter.ViewHolder holder, int position) {

        holder.tv_fname_PaymentWS.setText("Name: " + catPaymentWs.get(position).getFname());
        holder.tv_mobile_no_PaymentWS.setText("Mobile No: " + catPaymentWs.get(position).getMobileno());
        holder.tv_Village_PaymentWS.setText("Village: " + catPaymentWs.get(position).getVillage());
        holder.tv_Taluko_PaymentWS.setText("Taluko: " + catPaymentWs.get(position).getTehsill());
        holder.tv_District_PaymentWS.setText("District: " + catPaymentWs.get(position).getDistric());


        //Table Row 1
        holder.tv_DealPrice_PaymentWS.setText(catPaymentWs.get(position).getDeal_price());
        holder.tv_DeliveryDate_PaymentWS.setText(catPaymentWs.get(position).getS_date());
        holder.tv_Description_PaymentWS.setText(catPaymentWs.get(position).getDesc());


        //Table Row 2
        holder.tv_cashAmount_PaymentWS.setText(catPaymentWs.get(position).getCashamount());
        holder.tv_cashDate_PaymentWSWS.setText(catPaymentWs.get(position).getCashdate());
        holder.tv_CashDesc_PaymentWSWs.setText(catPaymentWs.get(position).getCash_desc());


        holder.tv_Ref1_PaymentWS.setText(catPaymentWs.get(position).getRef1());
        holder.tv_Refdate_PaymentWS.setText(catPaymentWs.get(position).getRefdate());
        holder.tv_Refdesc_PaymentWS.setText(catPaymentWs.get(position).getRefdesc());


        holder.tv_chequeAmount_PaymentWS.setText(catPaymentWs.get(position).getCheck_amt());
        holder.tv_ChequeDate_PaymentWS.setText(catPaymentWs.get(position).getCheck_date());
        holder.tv_ChequeDesc_PaymentWS.setText(catPaymentWs.get(position).getCheck_desc());


        holder.tv_neft_rtgs_amt_PaymentWS.setText(catPaymentWs.get(position).getNeft_rtgs_amt());
        holder.tv_neft_rtgs_date_PaymentWS.setText(catPaymentWs.get(position).getNeft_rtgs_date());
        holder.tv_neft_rtgs_desc_PaymentWS.setText(catPaymentWs.get(position).getNeft_rtgs_desc());

        holder.tv_ExPrice1_PaymentWS.setText(catPaymentWs.get(position).getExprice1());
        holder.tv_ExDate1_PaymentWS.setText(catPaymentWs.get(position).getExdate1());
        holder.tv_ExDesc1_PaymentWS.setText(catPaymentWs.get(position).getExdesc1());


        holder.tv_ExPrice2_PaymentWS.setText(catPaymentWs.get(position).getExprice2());
        holder.tv_ExDate2_PaymentWS.setText(catPaymentWs.get(position).getExdate2());
        holder.tv_ExDesc2_PaymentWS.setText(catPaymentWs.get(position).getExdesc2());


        holder.tv_ExPrice3_PaymentWS.setText(catPaymentWs.get(position).getExprice3());
        holder.tv_ExDate3_PaymentWS.setText(catPaymentWs.get(position).getExdate3());
        holder.tv_ExDesc3_PaymentWS.setText(catPaymentWs.get(position).getExdesc3());


        holder.tv_TP_PaymentWS.setText("Left RS: "+catPaymentWs.get(position).getTp());

    }


    @Override
    public int getItemCount() {
        return catPaymentWs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_fname_PaymentWS,tv_mobile_no_PaymentWS,tv_Village_PaymentWS,
                tv_Taluko_PaymentWS,tv_District_PaymentWS;

        TextView tv_DealPrice_PaymentWS,tv_DeliveryDate_PaymentWS,tv_Description_PaymentWS;

        TextView tv_cashAmount_PaymentWS,tv_cashDate_PaymentWSWS,tv_CashDesc_PaymentWSWs,
                tv_Ref1_PaymentWS,tv_Refdate_PaymentWS,tv_Refdesc_PaymentWS,
                tv_chequeAmount_PaymentWS,tv_ChequeDate_PaymentWS,tv_ChequeDesc_PaymentWS,
                tv_neft_rtgs_amt_PaymentWS,tv_neft_rtgs_date_PaymentWS,tv_neft_rtgs_desc_PaymentWS,
                tv_ExPrice1_PaymentWS,tv_ExDate1_PaymentWS,tv_ExDesc1_PaymentWS,
                tv_ExPrice2_PaymentWS,tv_ExDate2_PaymentWS,tv_ExDesc2_PaymentWS,
                tv_ExPrice3_PaymentWS,tv_ExDate3_PaymentWS,tv_ExDesc3_PaymentWS;

        TextView tv_TP_PaymentWS;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            this.tv_fname_PaymentWS = itemView.findViewById(R.id.tv_fname_PaymentWS);
            this.tv_mobile_no_PaymentWS = itemView.findViewById(R.id.tv_mobile_no_PaymentWS);
            this.tv_Village_PaymentWS = itemView.findViewById(R.id.tv_Village_PaymentWS);
            this.tv_Taluko_PaymentWS = itemView.findViewById(R.id.tv_Taluko_PaymentWS);
            this.tv_District_PaymentWS = itemView.findViewById(R.id.tv_District_PaymentWS);

            this.tv_DealPrice_PaymentWS = itemView.findViewById(R.id.tv_DealPrice_PaymentWS);
            this.tv_DeliveryDate_PaymentWS = itemView.findViewById(R.id.tv_DeliveryDate_PaymentWS);
            this.tv_Description_PaymentWS = itemView.findViewById(R.id.tv_Description_PaymentWS);

            this.tv_cashAmount_PaymentWS = itemView.findViewById(R.id.tv_cashAmount_PaymentWS);
            this.tv_cashDate_PaymentWSWS = itemView.findViewById(R.id.tv_cashDate_PaymentWSWS);
            this.tv_CashDesc_PaymentWSWs = itemView.findViewById(R.id.tv_CashDesc_PaymentWSWs);

            this.tv_Ref1_PaymentWS = itemView.findViewById(R.id.tv_Ref1_PaymentWS);
            this.tv_Refdate_PaymentWS = itemView.findViewById(R.id.tv_Refdate_PaymentWS);
            this.tv_Refdesc_PaymentWS = itemView.findViewById(R.id.tv_Refdesc_PaymentWS);

            this.tv_chequeAmount_PaymentWS = itemView.findViewById(R.id.tv_chequeAmount_PaymentWS);
            this.tv_ChequeDate_PaymentWS = itemView.findViewById(R.id.tv_ChequeDate_PaymentWS);
            this.tv_ChequeDesc_PaymentWS = itemView.findViewById(R.id.tv_ChequeDesc_PaymentWS);

            this.tv_neft_rtgs_amt_PaymentWS = itemView.findViewById(R.id.tv_neft_rtgs_amt_PaymentWS);
            this.tv_neft_rtgs_date_PaymentWS = itemView.findViewById(R.id.tv_neft_rtgs_date_PaymentWS);
            this.tv_neft_rtgs_desc_PaymentWS = itemView.findViewById(R.id.tv_neft_rtgs_desc_PaymentWS);

            this.tv_ExPrice1_PaymentWS = itemView.findViewById(R.id.tv_ExPrice1_PaymentWS);
            this.tv_ExDate1_PaymentWS = itemView.findViewById(R.id.tv_ExDate1_PaymentWS);
            this.tv_ExDesc1_PaymentWS = itemView.findViewById(R.id.tv_ExDesc1_PaymentWS);

            this.tv_ExPrice2_PaymentWS = itemView.findViewById(R.id.tv_ExPrice2_PaymentWS);
            this.tv_ExDate2_PaymentWS = itemView.findViewById(R.id.tv_ExDate2_PaymentWS);
            this.tv_ExDesc2_PaymentWS = itemView.findViewById(R.id.tv_ExDesc2_PaymentWS);

            this.tv_ExPrice3_PaymentWS = itemView.findViewById(R.id.tv_ExPrice3_PaymentWS);
            this.tv_ExDate3_PaymentWS = itemView.findViewById(R.id.tv_ExDate3_PaymentWS);
            this.tv_ExDesc3_PaymentWS = itemView.findViewById(R.id.tv_ExDesc3_PaymentWS);

            this.tv_TP_PaymentWS = itemView.findViewById(R.id.tv_TP_PaymentWS);
        }
    }
}
