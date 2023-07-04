package com.example.kumarGroup.ReportCollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatBorrowOne;
import com.example.kumarGroup.R;

import java.util.List;

public class BorrowledgerOneAdapter extends RecyclerView.Adapter<BorrowledgerOneAdapter.ViewHolder>
{
    Context context;
    List<CatBorrowOne> payment_payPenModels;

    public BorrowledgerOneAdapter(Context context, List<CatBorrowOne> payment_payPenModels) {
        this.context = context;
        this.payment_payPenModels = payment_payPenModels;
    }

    @NonNull
    @Override
    public BorrowledgerOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.payment_raw_pay_pen, parent, false);
        BorrowledgerOneAdapter.ViewHolder viewHolder = new BorrowledgerOneAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BorrowledgerOneAdapter.ViewHolder holder, int position) {
        holder.tv_BookingNo_Payment.setText(payment_payPenModels.get(position).getBno());
        holder.tv_fname_payment.setText("Name: " + payment_payPenModels.get(position).getFname());
        holder.tv_mobile_no_payment.setText("Mobile No: " + payment_payPenModels.get(position).getMobileno());
        holder.tv_Village_payment.setText("Village: " + payment_payPenModels.get(position).getVillage());
        holder.tv_Taluko_payment.setText("Taluko: " + payment_payPenModels.get(position).getTehsill());
        holder.tv_District_payment.setText("District: " + payment_payPenModels.get(position).getDistric());
        holder.tv_DModelNm_payment.setText("Model Name: " + payment_payPenModels.get(position).getDmodelname());
        holder.tv_EngineNo_payment.setText("Engine No: " + payment_payPenModels.get(position).getEngineno());
        holder.tv_Variants_payment.setText("Variants: " + payment_payPenModels.get(position).getVariants());
        holder.tv_ChasisNo_payment.setText("Chasis No: " + payment_payPenModels.get(position).getChasisno());
        holder.tv_Battery_payment.setText("Battery: " + payment_payPenModels.get(position).getBattery());
        holder.tv_Tyre_payment.setText("Tyre: " + payment_payPenModels.get(position).getTyre());
        holder.tv_FinanceForm_payment.setText("Finance From: " + payment_payPenModels.get(position).getFinance_from());

        //Table 1------------------------------------------


        if(payment_payPenModels.get(position).getDeal_price()=="0"||
                payment_payPenModels.get(position).getDeal_price()==null)
        {
            holder.tv_DealPrice_payment.setText(" ");
            holder.tv_DeliveryDate_payment.setText(" ");
            holder.tv_Description_payment.setText(" ");
        }
        else{
            holder.tv_DealPrice_payment.setText(payment_payPenModels.get(position).getDeal_price());
            holder.tv_DeliveryDate_payment.setText(payment_payPenModels.get(position).getDelivery_date());
            holder.tv_Description_payment.setText(payment_payPenModels.get(position).getDesc());
        }


       /* holder.tv_DealPrice_payment.setText(payment_payPenModels.get(position).getDeal_price());
        holder.tv_DeliveryDate_payment.setText(payment_payPenModels.get(position).getDelivery_date());
        holder.tv_Description_payment.setText(payment_payPenModels.get(position).getDesc());*/

        holder.tv_cmt_payment.setText(payment_payPenModels.get(position).getCamt());
        holder.tv_cpdelivery_date_payment.setText(payment_payPenModels.get(position).getCpdelivery_date());
        holder.tv_cudesc_payment.setText(payment_payPenModels.get(position).getCudesc());


        holder.tv_Ref_payment.setText(payment_payPenModels.get(position).getRef1());
        holder.tv_Ref_date_payment.setText(payment_payPenModels.get(position).getRefdate());
        holder.tv_RefDesc_payment.setText(payment_payPenModels.get(position).getRefdesc());

        holder.tv_other_price.setText(payment_payPenModels.get(position).getOther_amt());
      //  holder.tv_other_date.setText(payment_payPenModels.get(position).getDatee());
      //  holder.tv_other_desc.setText(payment_payPenModels.get(position).getOdesc());




       if(payment_payPenModels.get(position).getOther_amt()==null){
           holder.tv_other_date.setText(" ");
           holder.tv_other_desc.setText(" ");
       }
       else {
           holder.tv_other_date.setText(payment_payPenModels.get(position).getDatee());
           holder.tv_other_desc.setText(payment_payPenModels.get(position).getOdesc());
       }



        if(payment_payPenModels.get(position).getWork_deal_price()=="0"||
                payment_payPenModels.get(position).getWork_deal_price()=="null"){
            holder.tv_work_deal_price.setText("");
            holder.tv_work_date.setText("");
            holder.tv_work_desc.setText("");
        }
        else{
            holder. tv_work_deal_price.setText(payment_payPenModels.get(position).getWork_deal_price());
            holder.tv_work_date.setText(payment_payPenModels.get(position).getWork_date());
            holder.tv_work_desc.setText(payment_payPenModels.get(position).getWork_desc());

        }


        //Table 2------------------------------------------

        holder.tv_cashAmount_payment.setText(payment_payPenModels.get(position).getCashamount());
        if (payment_payPenModels.get(position).getCashamount()== null){
            holder.tv_AddDate_payment.setText(" ");
        }
        else{
            holder.tv_AddDate_payment.setText(payment_payPenModels.get(position).getAdd_date());
        }
        holder.tv_ptypef_payment.setText(payment_payPenModels.get(position).getPtypef());


        holder.tv_OldVehicleAmount_payment.setText(payment_payPenModels.get(position).getOld_Vehicle_amount());
        if (payment_payPenModels.get(position).getOld_Vehicle_amount()== null){
            holder.tv_Adddate1_payment.setText(" ");
        }
        else{
            holder.tv_Adddate1_payment.setText(payment_payPenModels.get(position).getAdd_date());
        }
        holder.tv_ptypeo_payment.setText(payment_payPenModels.get(position).getPtypeo());


        holder.tv_chequeamount_payment.setText(payment_payPenModels.get(position).getChequeamount());
        if(payment_payPenModels.get(position).getChequeamount()== null)
        {
            holder.tv_Adddate2_payment.setText("");
        }
        else{
            holder.tv_Adddate2_payment.setText(payment_payPenModels.get(position).getAdd_date());
        }
        holder.tv_ptypes_payment.setText(payment_payPenModels.get(position).getPtypes());



        holder.tv_NeftAmount_payment.setText(payment_payPenModels.get(position).getNEFT_RTGSamount());
        if(payment_payPenModels.get(position).getNEFT_RTGSamount()== null){
            holder.tv_Adddate3_payment.setText("");
        }
        else{
            holder.tv_Adddate3_payment.setText(payment_payPenModels.get(position).getAdd_date());
        }
        holder.tv_ptypet_payment.setText(payment_payPenModels.get(position).getPtypet());


        holder.tv_cashAmt_payment.setText(payment_payPenModels.get(position).getCashamt());
        holder.tv_cashDate_payment.setText(payment_payPenModels.get(position).getCashdate());
        holder.tv_cashDesc_payment.setText(payment_payPenModels.get(position).getCashdesc());



        holder.tv_LoanAmt_payment.setText(payment_payPenModels.get(position).getLoanamt());
        holder.tv_LoanDate_payment.setText(payment_payPenModels.get(position).getLoandate());
        holder.tv_LoanDesc_payment.setText(payment_payPenModels.get(position).getLoandesc());



        holder.tv_ExPrice1_payment.setText(payment_payPenModels.get(position).getExprice1());
        holder.tv_ExDate1_payment.setText(payment_payPenModels.get(position).getExdate1());
        holder.tv_ExDesc1_payment.setText(payment_payPenModels.get(position).getExdesc1());


        holder.tv_ExPrice2_payment.setText(payment_payPenModels.get(position).getExprice2());
        holder.tv_ExDate2_payment.setText(payment_payPenModels.get(position).getExdate2());
        holder.tv_ExDesc2_payment.setText(payment_payPenModels.get(position).getExdesc2());


        holder.tv_ExPrice3_payment.setText(payment_payPenModels.get(position).getExprice3());
        holder.tv_ExDate3_payment.setText(payment_payPenModels.get(position).getExdate3());
        holder.tv_ExDesc3_payment.setText(payment_payPenModels.get(position).getExdesc3());


        holder.tv_UPrice_payment.setText(payment_payPenModels.get(position).getUprice());
        holder.tv_Udate_payment.setText(payment_payPenModels.get(position).getUdate());
        holder.tv_UDesc_payment.setText(payment_payPenModels.get(position).getUdesc());


        holder.tv_UPrice1_payment.setText(payment_payPenModels.get(position).getUprice1());
        holder.tv_Udate1_payment.setText(payment_payPenModels.get(position).getUdate1());
        holder.tv_UDesc1_payment.setText(payment_payPenModels.get(position).getUdesc1());






        holder.tv_work_com_price.setText(payment_payPenModels.get(position).getWork_com_price());
        holder.tv_work_com_date.setText(payment_payPenModels.get(position).getWork_com_date());
        holder.tv_work_com_desc.setText(payment_payPenModels.get(position).getWork_com_desc());


        holder.tv_work_de_price.setText(payment_payPenModels.get(position).getWork_de_price());
        holder.tv_work_de_date.setText(payment_payPenModels.get(position).getWork_de_date());
        holder.tv_work_de_desc.setText(payment_payPenModels.get(position).getWork_de_desc());


        holder.tv_work_cu_cash_price.setText(payment_payPenModels.get(position).getWork_cu_cash_price());
        holder.tv_work_cu_cash_date.setText(payment_payPenModels.get(position).getWork_cu_cash_date());
        holder.tv_work_cu_cash_desc.setText(payment_payPenModels.get(position).getWork_cu_cash_desc());




        holder.tv_work_cu_che_price.setText(payment_payPenModels.get(position).getWork_cu_che_price());
        holder.tv_work_cu_che_date.setText(payment_payPenModels.get(position).getWork_cu_che_date());
        holder.tv_work_cu_che_desc.setText(payment_payPenModels.get(position).getWork_cu_che_desc());

        holder.tv_work_cu_nert_price.setText(payment_payPenModels.get(position).getWork_cu_nert_price());
        holder.tv_work_cu_nert_date .setText(payment_payPenModels.get(position).getWork_cu_nert_date());
        holder.tv_work_cu_nert_desc .setText(payment_payPenModels.get(position).getWork_cu_nert_desc());



        holder.tv_TP_payment.setText("Left Price: " + payment_payPenModels.get(position).getTp() + "");
//        123
        if (""+payment_payPenModels.get(position).getDexprice1()==null){
            holder.tv_other_price_dexprice1.setText("");
            holder.tv_other_date_dexdate1.setText("");
            holder.tv_other_desc_dexdesc1.setText("");
        }else {
            holder.tv_other_price_dexprice1.setText(""+payment_payPenModels.get(position).getDexprice1());
            holder.tv_other_date_dexdate1.setText(payment_payPenModels.get(position).getDexdate1());
            holder.tv_other_desc_dexdesc1.setText(payment_payPenModels.get(position).getDexdesc1());
        }

        if (""+payment_payPenModels.get(position).getDexprice2()==null){
            holder.tv_other_price_dexprice2.setText("");
            holder.tv_other_date_dexdate2.setText("");
            holder.tv_other_desc_dexdesc2.setText("");
        }else {
            holder.tv_other_price_dexprice2.setText(""+payment_payPenModels.get(position).getDexprice2());
            holder.tv_other_date_dexdate2.setText(payment_payPenModels.get(position).getDexdate2());
            holder.tv_other_desc_dexdesc2.setText(payment_payPenModels.get(position).getDexdesc2());
        }

        if (""+payment_payPenModels.get(position).getDexprice3()==null){
            holder.tv_other_price_dexprice3.setText("");
            holder.tv_other_date_dexdate3.setText("");
            holder.tv_other_desc_dexdesc3.setText("");
        }else {
            holder.tv_other_price_dexprice3.setText(""+payment_payPenModels.get(position).getDexprice3());
            holder.tv_other_date_dexdate3.setText(payment_payPenModels.get(position).getDexdate3());
            holder.tv_other_desc_dexdesc3.setText(payment_payPenModels.get(position).getDexdesc3());
        }
    }

    @Override
    public int getItemCount() {
        return payment_payPenModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_fname_payment, tv_mobile_no_payment, tv_Village_payment, tv_Taluko_payment, tv_District_payment,
                tv_DModelNm_payment, tv_EngineNo_payment, tv_Variants_payment, tv_ChasisNo_payment, tv_Battery_payment,
                tv_Tyre_payment, tv_FinanceForm_payment, tv_Amount_payment, tv_BookingAmt_payment,
                tv_DealPrice_payment, tv_DeliveryDate_payment, tv_Description_payment, tv_UPrice_payment,
                tv_Udate_payment, tv_UDesc_payment, tv_Price_payment, tv_TP_payment, tv_BookingNo_Payment;

        TextView tv_ExPrice1_payment, tv_ExDate1_payment, tv_ExDesc1_payment,
                tv_ExPrice2_payment, tv_ExDate2_payment, tv_ExDesc2_payment,
                tv_ExPrice3_payment, tv_ExDate3_payment, tv_ExDesc3_payment;


        TextView tv_cmt_payment,tv_cpdelivery_date_payment,tv_cudesc_payment;

        TextView tv_UDesc1_payment,tv_Udate1_payment,tv_UPrice1_payment;

        TextView tv_cashAmount_payment,tv_AddDate_payment,tv_ptypef_payment,
                tv_OldVehicleAmount_payment,tv_Adddate1_payment,tv_ptypeo_payment,
                tv_chequeamount_payment,tv_Adddate2_payment,tv_ptypes_payment,
                tv_NeftAmount_payment,tv_Adddate3_payment,tv_ptypet_payment,
                tv_cashAmt_payment,tv_cashDate_payment,tv_cashDesc_payment,
                tv_LoanAmt_payment,tv_LoanDate_payment,tv_LoanDesc_payment;


        TextView  tv_Ref_payment,tv_Ref_date_payment,tv_RefDesc_payment ;

        TextView  tv_other_price,tv_other_date,tv_other_desc ;



        TextView tv_work_deal_price,tv_work_date,tv_work_desc;

        TextView tv_work_com_price,tv_work_com_date,tv_work_com_desc;

        TextView tv_work_de_price,tv_work_de_date,tv_work_de_desc;

        TextView tv_work_cu_cash_price,tv_work_cu_cash_date,tv_work_cu_cash_desc;

        TextView tv_work_cu_che_price,tv_work_cu_che_date,tv_work_cu_che_desc;

        TextView tv_work_cu_nert_price,tv_work_cu_nert_date,tv_work_cu_nert_desc;

        TextView tv_other_price_dexprice1,tv_other_date_dexdate1,tv_other_desc_dexdesc1;
        TextView tv_other_price_dexprice2,tv_other_date_dexdate2,tv_other_desc_dexdesc2;
        TextView tv_other_price_dexprice3,tv_other_date_dexdate3,tv_other_desc_dexdesc3;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //new row first
            this.tv_other_price_dexprice1 = itemView.findViewById(R.id.tv_other_price_dexprice1);
            this.tv_other_date_dexdate1 = itemView.findViewById(R.id.tv_other_date_dexdate1);
            this.tv_other_desc_dexdesc1 = itemView.findViewById(R.id.tv_other_desc_dexdesc1);

            //second
            this.tv_other_price_dexprice2 = itemView.findViewById(R.id.tv_other_price_dexprice2);
            this.tv_other_date_dexdate2 = itemView.findViewById(R.id.tv_other_date_dexdate2);
            this.tv_other_desc_dexdesc2 = itemView.findViewById(R.id.tv_other_desc_dexdesc2);

            //third
            this.tv_other_price_dexprice3 = itemView.findViewById(R.id.tv_other_price_dexprice3);
            this.tv_other_date_dexdate3 = itemView.findViewById(R.id.tv_other_date_dexdate3);
            this.tv_other_desc_dexdesc3 = itemView.findViewById(R.id.tv_other_desc_dexdesc3);



            this.tv_fname_payment = itemView.findViewById(R.id.tv_fname_payment);
            this.tv_mobile_no_payment = itemView.findViewById(R.id.tv_mobile_no_payment);
            this.tv_Village_payment = itemView.findViewById(R.id.tv_Village_payment);
            this.tv_Taluko_payment = itemView.findViewById(R.id.tv_Taluko_payment);
            this.tv_District_payment = itemView.findViewById(R.id.tv_District_payment);
            this.tv_DModelNm_payment = itemView.findViewById(R.id.tv_DModelNm_payment);
            this.tv_EngineNo_payment = itemView.findViewById(R.id.tv_EngineNo_payment);
            this.tv_Variants_payment = itemView.findViewById(R.id.tv_Variants_payment);
            this.tv_ChasisNo_payment = itemView.findViewById(R.id.tv_ChasisNo_payment);

            this.tv_Battery_payment = itemView.findViewById(R.id.tv_Battery_payment);
            this.tv_Tyre_payment = itemView.findViewById(R.id.tv_Tyre_payment);
            this.tv_FinanceForm_payment = itemView.findViewById(R.id.tv_FinanceForm_payment);
            //  this.tv_Amount_payment = itemView.findViewById(R.id.tv_Amount_payment);
            //  this.tv_AddDate_payment = itemView.findViewById(R.id.tv_AddDate_payment);
            //  this.tv_BookingAmt_payment = itemView.findViewById(R.id.tv_BookingAmt_payment);
            this.tv_DealPrice_payment = itemView.findViewById(R.id.tv_DealPrice_payment);
            this.tv_DeliveryDate_payment = itemView.findViewById(R.id.tv_DeliveryDate_payment);

            this.tv_Description_payment = itemView.findViewById(R.id.tv_Description_payment);

            this.tv_UPrice_payment = itemView.findViewById(R.id.tv_UPrice_payment);
            this.tv_Udate_payment = itemView.findViewById(R.id.tv_Udate_payment);
            this.tv_UDesc_payment = itemView.findViewById(R.id.tv_UDesc_payment);

            this.tv_UPrice1_payment = itemView.findViewById(R.id.tv_UPrice1_payment);
            this.tv_Udate1_payment = itemView.findViewById(R.id.tv_Udate1_payment);
            this.tv_UDesc1_payment = itemView.findViewById(R.id.tv_UDesc1_payment);


            // this.tv_Price_payment = itemView.findViewById(R.id.tv_Price_payment);
            this.tv_TP_payment = itemView.findViewById(R.id.tv_TP_payment);

            this.tv_BookingNo_Payment = itemView.findViewById(R.id.tv_BookingNo_Payment);

            this.tv_ExPrice1_payment = itemView.findViewById(R.id.tv_ExPrice1_payment);
            this.tv_ExDate1_payment = itemView.findViewById(R.id.tv_ExDate1_payment);
            this.tv_ExDesc1_payment = itemView.findViewById(R.id.tv_ExDesc1_payment);

            this.tv_ExPrice2_payment = itemView.findViewById(R.id.tv_ExPrice2_payment);
            this.tv_ExDate2_payment = itemView.findViewById(R.id.tv_ExDate2_payment);
            this.tv_ExDesc2_payment = itemView.findViewById(R.id.tv_ExDesc2_payment);

            this.tv_ExPrice3_payment = itemView.findViewById(R.id.tv_ExPrice3_payment);
            this.tv_ExDate3_payment = itemView.findViewById(R.id.tv_ExDate3_payment);
            this.tv_ExDesc3_payment = itemView.findViewById(R.id.tv_ExDesc3_payment);


            this.tv_cmt_payment = itemView.findViewById(R.id.tv_cmt_payment);
            this.tv_cpdelivery_date_payment = itemView.findViewById(R.id.tv_cpdelivery_date_payment);
            this.tv_cudesc_payment = itemView.findViewById(R.id.tv_cudesc_payment);

            this.tv_cashAmount_payment = itemView.findViewById(R.id.tv_cashAmount_payment);
            this.tv_AddDate_payment = itemView.findViewById(R.id.tv_AddDate_payment);
            this.tv_ptypef_payment = itemView.findViewById(R.id.tv_ptypef_payment);

            this.tv_OldVehicleAmount_payment = itemView.findViewById(R.id.tv_OldVehicleAmount_payment);
            this.tv_Adddate1_payment = itemView.findViewById(R.id.tv_Adddate1_payment);
            this.tv_ptypeo_payment = itemView.findViewById(R.id.tv_ptypeo_payment);

            this.tv_chequeamount_payment = itemView.findViewById(R.id.tv_chequeamount_payment);
            this.tv_Adddate2_payment = itemView.findViewById(R.id.tv_Adddate2_payment);
            this.tv_ptypes_payment = itemView.findViewById(R.id.tv_ptypes_payment);

            this.tv_NeftAmount_payment = itemView.findViewById(R.id.tv_NeftAmount_payment);
            this.tv_Adddate3_payment = itemView.findViewById(R.id.tv_Adddate3_payment);
            this.tv_ptypet_payment = itemView.findViewById(R.id.tv_ptypet_payment);

            this.tv_cashAmt_payment = itemView.findViewById(R.id.tv_cashAmt_payment);
            this.tv_cashDate_payment = itemView.findViewById(R.id.tv_cashDate_payment);
            this.tv_cashDesc_payment = itemView.findViewById(R.id.tv_cashDesc_payment);

            this.tv_LoanAmt_payment = itemView.findViewById(R.id.tv_LoanAmt_payment);
            this.tv_LoanDate_payment = itemView.findViewById(R.id.tv_LoanDate_payment);
            this.tv_LoanDesc_payment = itemView.findViewById(R.id.tv_LoanDesc_payment);


            this.tv_Ref_payment = itemView.findViewById(R.id.tv_Ref_payment);
            this.tv_Ref_date_payment = itemView.findViewById(R.id.tv_Ref_date_payment);
            this.tv_RefDesc_payment = itemView.findViewById(R.id.tv_RefDesc_payment);


            this.tv_other_price = itemView.findViewById(R.id.tv_other_price);
            this.tv_other_date = itemView.findViewById(R.id.tv_other_date);
            this.tv_other_desc = itemView.findViewById(R.id.tv_other_desc);





            this.tv_work_deal_price = itemView.findViewById(R.id.tv_work_deal_price);
            this.tv_work_date = itemView.findViewById(R.id.tv_work_date);
            this.tv_work_desc = itemView.findViewById(R.id.tv_work_desc);



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


        }
    }
}
