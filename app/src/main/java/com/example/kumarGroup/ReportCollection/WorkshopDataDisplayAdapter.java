package com.example.kumarGroup.ReportCollection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataBorrowTwo;
import com.example.kumarGroup.R;

import java.util.ArrayList;
import java.util.List;

public class WorkshopDataDisplayAdapter extends RecyclerView.Adapter<WorkshopDataDisplayAdapter.ViewHolder>
        implements Filterable
{
    Context context;
    List<DataBorrowTwo> dataWsPayPens;
    List<DataBorrowTwo> dataWsPayPens_one;

    public WorkshopDataDisplayAdapter(Context context, List<DataBorrowTwo> dataWsPayPens) {
        this.context = context;
        this.dataWsPayPens = dataWsPayPens;
        this.dataWsPayPens_one = dataWsPayPens;
    }

    @NonNull
    @Override
    public WorkshopDataDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.ws_payment_pending_row, parent, false);
        WorkshopDataDisplayAdapter.ViewHolder viewHolder = new WorkshopDataDisplayAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopDataDisplayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_WS_Clear.setVisibility(View.GONE);

        holder.txt_WS_Payment.setText("Payment:"+"\n"+dataWsPayPens.get(position).getLeft_amt());

        holder.tv_WS_CustomerName.setText("Customer Name: "+dataWsPayPens.get(position).getCname());
        holder.tv_Ws_MobileNo.setText("Mobile No: "+dataWsPayPens.get(position).getMobileno());
        holder.tv_WS_Village.setText("Village: "+dataWsPayPens.get(position).getVillage());
        holder.tv_WS_LeftAmount.setText("Left Amount: "+dataWsPayPens.get(position).getLeft_amt());
        holder.tv_Description_WS.setText("Description: "+dataWsPayPens.get(position).getV_reason());

        holder.tv_Nextdate_WS.setText("Next Date: "+dataWsPayPens.get(position).getN_date());

       /* if(dataWsPayPens.get(position).getFinal_amt()== null
                ||dataWsPayPens.get(position).getFinal_amt().equals("0")) {

            holder.txt_WS_Clear.setVisibility(View.VISIBLE);
        }
        else {
            holder.txt_WS_Clear.setVisibility(View.GONE);
        }*/


       // TextView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,lin_MDY_share;

        holder.lin_MDY_call.setVisibility(View.GONE);
        holder.lin_MDY_sms.setVisibility(View.GONE);
        holder.lin_MDY_whapp.setVisibility(View.GONE);
        holder.lin_MDY_share.setVisibility(View.GONE);
        holder.lin_wpp_cwss.setVisibility(View.GONE);


        holder.txt_WS_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(context, PaymentWsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id",dataWsPayPens.get(position).getId());
                i.putExtra("mobile",dataWsPayPens.get(position).getMobileno());
                context.startActivity(i);
                */



                Intent i = new Intent(context, BorrowLedgerTwoActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id",dataWsPayPens.get(position).getId());
                i.putExtra("mobile",dataWsPayPens.get(position).getMobileno());
                context.startActivity(i);
            }
        });

        holder.linBookingUploadMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,OpenGeneralVisitFormActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Name",dataWsPayPens.get(position).getCname());
                i.putExtra("Category","Payment Collection");
                i.putExtra("cat_id","Payment Collection");
                // i.putExtra("sname",dataPaymentPendings.get(position).getId());
                i.putExtra("sname",dataWsPayPens.get(position).getCname());
                i.putExtra("id",dataWsPayPens.get(position).getId());

                i.putExtra("MobileNo",dataWsPayPens.get(position).getMobileno());
                i.putExtra("add_id",dataWsPayPens.get(position).getId());
                i.putExtra("add_type","Workshop");
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataWsPayPens.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_WS_CustomerName,tv_Ws_MobileNo,tv_WS_Village,tv_WS_LeftAmount,tv_Description_WS;
        TextView txt_WS_Payment,txt_WS_Clear,tv_Nextdate_WS;

        LinearLayout linBookingUploadMain;

        TextView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,lin_MDY_share;

        LinearLayout lin_wpp_cwss;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_WS_CustomerName = itemView.findViewById(R.id.tv_WS_CustomerName);
            this.tv_Ws_MobileNo = itemView.findViewById(R.id.tv_Ws_MobileNo);
            this.tv_WS_Village = itemView.findViewById(R.id.tv_WS_Village);
            this.tv_WS_LeftAmount = itemView.findViewById(R.id.tv_WS_LeftAmount);

            this.txt_WS_Payment = itemView.findViewById(R.id.txt_WS_Payment);
            this.txt_WS_Clear = itemView.findViewById(R.id.txt_WS_Clear);
            this.linBookingUploadMain = itemView.findViewById(R.id.linBookingUploadMain);

            this.tv_Nextdate_WS = itemView.findViewById(R.id.tv_Nextdate_WS);
            this.tv_Description_WS = itemView.findViewById(R.id.tv_Description_WS);


            this.lin_MDY_call=itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms=itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp=itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share=itemView.findViewById(R.id.lin_MDY_share);
            this.lin_wpp_cwss=itemView.findViewById(R.id.lin_wpp_cwss);
        }
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//
//                String charString = charSequence.toString();
//
//                if (charString.isEmpty()) {
//                    dataWsPayPens_one = dataWsPayPens;
//                } else {
//
//                    List<DataBorrowTwo> filteredList = new ArrayList<>();
//
//                    for (DataBorrowTwo row : dataWsPayPens) {
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        //   row.getVilage().toString();
//
//                        String strFName =row.getCname();
//                        String strMobile =row.getMobileno();
//                        String strVName =row.getVillage();
//                        // String strLName =row.getLname();
//                        Log.d("TAG", "Data: "+row);
//
//                        if(strFName == null)
//                            strFName = " ";
//                        if(strMobile == null)
//                            strMobile = " ";
//                        if(strVName == null)
//                            strVName = " ";
//                       /* if(strLName == null)
//                            strLName = " ";*/
//
//                        if (strFName.toLowerCase().contains(charString.toLowerCase())
//                                || strMobile.toLowerCase().contains(charString.toLowerCase())
//                                || strVName.toLowerCase().contains(charString.toLowerCase())
//                            /* || strLName.toLowerCase().contains(charString.toLowerCase())*/)
//                        {
//                            filteredList.add(row);
//                        }
//                    }
//                    dataWsPayPens_one = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = dataWsPayPens_one;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                dataWsPayPens_one = (ArrayList<DataBorrowTwo>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
@Override
public Filter getFilter() {
    return new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String charString = charSequence.toString();

            if (charString.isEmpty()) {
                dataWsPayPens=dataWsPayPens_one;
            } else {

                List<DataBorrowTwo> filteredList = new ArrayList<>();

                for (DataBorrowTwo row : dataWsPayPens) {
                    // name match condition. this might differ depending on your requirement
                    // here we are looking for name or phone number match
                    //   row.getVilage().toString();

                    String strFName =row.getCname();
                    String strMobile =row.getMobileno();
                    String strVName =row.getVillage();

                    Log.d("TAGTAG", "Data: "+row);

                    if(strFName == null)
                        strFName = " ";
                    if(strMobile == null)
                        strMobile = " ";
                    if(strVName == null)
                        strVName = " ";
                       /* if(strLName == null)
                            strLName = " ";*/

                    if (strFName.toLowerCase().contains(charString.toLowerCase())
                            || strMobile.toLowerCase().contains(charString.toLowerCase())
                            || strVName.toLowerCase().contains(charString.toLowerCase()))
                    {
                        filteredList.add(row);
                    }
                }
                //  dataPaymentPendings_one = filteredList;
                dataWsPayPens = filteredList;
            }

            FilterResults filterResults = new FilterResults();
            //  filterResults.values = dataPaymentPendings_one;
            filterResults.values = dataWsPayPens;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            //  dataPaymentPendings_one = (ArrayList<DataBorrowOne>) filterResults.values;
            dataWsPayPens = (ArrayList<DataBorrowTwo>) filterResults.values;
            Log.d("TAGTAG", "publishResults: "+filterResults.values);
            notifyDataSetChanged();
        }
    };
}
}
