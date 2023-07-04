package com.example.kumarGroup.ReportCollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatBorrowLedgerTwo;
import com.example.kumarGroup.R;

import java.util.List;

public class BorrowLedgerTwoAdapter  extends RecyclerView.Adapter<BorrowLedgerTwoAdapter.ViewHolder>
{
    Context context;
    List<CatBorrowLedgerTwo> catPaymentWs;

    public BorrowLedgerTwoAdapter(Context context, List<CatBorrowLedgerTwo> catPaymentWs) {
        this.context = context;
        this.catPaymentWs = catPaymentWs;
    }

    @NonNull
    @Override
    public BorrowLedgerTwoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       // View listItem = inflater.inflate(R.layout.payment_workshop_row, parent, false);
        View listItem = inflater.inflate(R.layout.borrow_ledger_two_raw, parent, false);
        BorrowLedgerTwoAdapter.ViewHolder viewHolder = new BorrowLedgerTwoAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BorrowLedgerTwoAdapter.ViewHolder holder, int position) {
        holder.tv_fname_PaymentWS.setText("Name: " + catPaymentWs.get(position).getFname());
        holder.tv_mobile_no_PaymentWS.setText("Mobile No: " + catPaymentWs.get(position).getMobileno());
        holder.tv_Village_PaymentWS.setText("Village: " + catPaymentWs.get(position).getVillage());
        holder.tv_Taluko_PaymentWS.setText("Taluko: " + catPaymentWs.get(position).getTehsill());
        holder.tv_District_PaymentWS.setText("District: " + catPaymentWs.get(position).getDistric());


        //Table Row 1


        holder.tv_DealPrice_PaymentWS.setText(""+catPaymentWs.get(position).getDeal_price());
        holder.tv_DeliveryDate_PaymentWS.setText(catPaymentWs.get(position).getS_date());
        holder.tv_Description_PaymentWS.setText(catPaymentWs.get(position).getDesc());


        //**********************************************************************************************


        holder.tv_other_price.setText(""+catPaymentWs.get(position).getWork_deal_price());
        holder.tv_other_date.setText(catPaymentWs.get(position).getWork_date());
        holder.tv_other_desc.setText(catPaymentWs.get(position).getWork_desc());


      /*  if(catPaymentWs.get(position).getWork_deal_price()=="0"||
                catPaymentWs.get(position).getWork_deal_price()=="null"){
            holder.tv_work_deal_price.setText("");
            holder.tv_work_date.setText("");
            holder.tv_work_desc.setText("");
        }
        else{
            holder. tv_work_deal_price.setText(catPaymentWs.get(position).getWork_deal_price());
            holder.tv_work_date.setText(catPaymentWs.get(position).getWork_date());
            holder.tv_work_desc.setText(catPaymentWs.get(position).getWork_desc());

        }*/


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

        holder.tv_ExPrice1_PaymentWS.setText(""+catPaymentWs.get(position).getExprice1());
        holder.tv_ExDate1_PaymentWS.setText(catPaymentWs.get(position).getExdate1());
        holder.tv_ExDesc1_PaymentWS.setText(catPaymentWs.get(position).getExdesc1());


        holder.tv_ExPrice2_PaymentWS.setText(catPaymentWs.get(position).getExprice2());
        holder.tv_ExDate2_PaymentWS.setText(catPaymentWs.get(position).getExdate2());
        holder.tv_ExDesc2_PaymentWS.setText(catPaymentWs.get(position).getExdesc2());


        holder.tv_ExPrice3_PaymentWS.setText(catPaymentWs.get(position).getExprice3());
        holder.tv_ExDate3_PaymentWS.setText(catPaymentWs.get(position).getExdate3());
        holder.tv_ExDesc3_PaymentWS.setText(catPaymentWs.get(position).getExdesc3());



        //********************************************************************************************



        holder.tv_work_com_price.setText(""+catPaymentWs.get(position).getWork_com_price());
        holder.tv_work_com_date.setText(catPaymentWs.get(position).getWork_com_date());
        holder.tv_work_com_desc.setText(catPaymentWs.get(position).getWork_com_desc());


        holder.tv_work_de_price.setText(""+catPaymentWs.get(position).getWork_de_price());
        holder.tv_work_de_date.setText(catPaymentWs.get(position).getWork_de_date());
        holder.tv_work_de_desc.setText(catPaymentWs.get(position).getWork_de_desc());


        holder.tv_work_cu_cash_price.setText(""+catPaymentWs.get(position).getWork_cu_cash_price());
        holder.tv_work_cu_cash_date.setText(catPaymentWs.get(position).getWork_cu_cash_date());
        holder.tv_work_cu_cash_desc.setText(catPaymentWs.get(position).getWork_cu_cash_desc());



        holder.tv_work_cu_che_price.setText(""+catPaymentWs.get(position).getWork_cu_che_price());
        holder.tv_work_cu_che_date.setText(catPaymentWs.get(position).getWork_cu_che_date());
        holder.tv_work_cu_che_desc.setText(catPaymentWs.get(position).getWork_cu_che_desc());

        holder.tv_work_cu_nert_price.setText(""+catPaymentWs.get(position).getWork_cu_nert_price());
        holder.tv_work_cu_nert_date .setText(catPaymentWs.get(position).getWork_cu_nert_date());
        holder.tv_work_cu_nert_desc .setText(catPaymentWs.get(position).getWork_cu_nert_desc());


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



        TextView tv_work_deal_price,tv_work_date,tv_work_desc;

        TextView tv_work_com_price,tv_work_com_date,tv_work_com_desc;

        TextView tv_work_de_price,tv_work_de_date,tv_work_de_desc;

        TextView tv_work_cu_cash_price,tv_work_cu_cash_date,tv_work_cu_cash_desc;

        TextView tv_work_cu_che_price,tv_work_cu_che_date,tv_work_cu_che_desc;

        TextView tv_work_cu_nert_price,tv_work_cu_nert_date,tv_work_cu_nert_desc;



        TextView  tv_other_price, tv_other_date, tv_other_desc;

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


            //*************************************************************************************

          /*  this.tv_work_deal_price = itemView.findViewById(R.id.tv_work_deal_price);
            this.tv_work_date = itemView.findViewById(R.id.tv_work_date);
            this.tv_work_desc = itemView.findViewById(R.id.tv_work_desc);*/



            this.tv_work_com_price = itemView.findViewById(R.id.tv_work_com_price);
            this.tv_work_com_date = itemView.findViewById(R.id.tv_work_com_date);
            this.tv_work_com_desc = itemView.findViewById(R.id.tv_work_com_desc);

            this.tv_work_de_price = itemView.findViewById(R.id.tv_work_de_price);
            this.tv_work_de_date = itemView.findViewById(R.id.tv_work_de_date);
            this.tv_work_de_desc = itemView.findViewById(R.id.tv_work_de_desc);


            this.tv_work_cu_cash_price = itemView.findViewById(R.id.tv_work_cu_cash_price);
            this.tv_work_cu_cash_date = itemView.findViewById(R.id.tv_work_cu_cash_date);
            this.tv_work_cu_cash_desc = itemView.findViewById(R.id.tv_work_cu_cash_desc);


            this.tv_work_cu_che_price = itemView.findViewById(R.id.tv_work_cu_che_price);
            this.tv_work_cu_che_date = itemView.findViewById(R.id.tv_work_cu_che_date);
            this.tv_work_cu_che_desc = itemView.findViewById(R.id.tv_work_cu_che_desc);


            this.tv_work_cu_nert_price = itemView.findViewById(R.id.tv_work_cu_nert_price);
            this.tv_work_cu_nert_date = itemView.findViewById(R.id.tv_work_cu_nert_date);
            this.tv_work_cu_nert_desc = itemView.findViewById(R.id.tv_work_cu_nert_desc);


            this.tv_other_price = itemView.findViewById(R.id.tv_other_price);
            this.tv_other_date= itemView.findViewById(R.id.tv_other_date);
            this.tv_other_desc  = itemView.findViewById(R.id.tv_other_desc);


        }
    }
}
