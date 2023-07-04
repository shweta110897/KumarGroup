package com.example.kumarGroup.ReportCollection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.kumarGroup.DataBorrowOne;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingUploadDataDisplayAdapter extends
        RecyclerView.Adapter<BookingUploadDataDisplayAdapter.ViewHolder> implements Filterable
{
    Context context;
    List<DataBorrowOne> dataPaymentPendings;
    List<DataBorrowOne> dataPaymentPendings_one;


    SharePref sp;
    SharedPreferences sharedPreferences;
    Utils utils;
    Activity activity;

    public BookingUploadDataDisplayAdapter(Activity activity, List<DataBorrowOne> dataPaymentPendings) {
     //   this.context = context;
        this.activity = activity;
        this.dataPaymentPendings = dataPaymentPendings;
        this.dataPaymentPendings_one = dataPaymentPendings;

        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

       // sharedPreferences = activity.getSharedPreferences("DateCurrent", MODE_PRIVATE);

    }

    @NonNull
    @Override
    public BookingUploadDataDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.payment_pending_raw, parent, false);
        BookingUploadDataDisplayAdapter.ViewHolder viewHolder = new BookingUploadDataDisplayAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingUploadDataDisplayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

       holder.txt_pendingDoc_pending.setVisibility(View.GONE);
       holder.txt_Clear_pending.setVisibility(View.GONE);

      /*  DataBorrowOne dataPaymentPendings = dataPaymentPendings_one.get(position);
        for (int i = 0; i <= position; i++) {
            Log.d("kk", "onBindViewHolder: " + i);
            Log.d("cat", "onBindViewHolder: " + dataPaymentPendings_one);

        }*/

        //date Formate:- 2021-04-30 == yyyy-month-date

       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss z");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        //textView.setText(currentDateandTime);
      //  Toast.makeText(context, "CurrentDate"+currentDateandTime, Toast.LENGTH_SHORT).show();
        Log.d("cDate", "onBindViewHolder: "+currentDateandTime);

        Log.d("TAG", "onBindViewHolder: sdf "+sdf);
        Log.d("TAG", "onBindViewHolder: sdf12 "+dataPaymentPendings.get(position).getN_date());


        if(sdf.equals(dataPaymentPendings.get(position).getN_date()) ||
                dataPaymentPendings.get(position).getN_date()== null){
           // Toast.makeText(context, "Date Match", Toast.LENGTH_SHORT).show();
            Log.d("DateMatch","onBindViewHolder DateMatch");
          //  sharedPreferences.edit().putInt("CurrentDateOrNull",1).apply();
        }
        else{
           // Toast.makeText(context, "Date Not Match", Toast.LENGTH_SHORT).show();
            Log.d("DateNotMatch","onBindViewHolder Date Not Match");
          //  sharedPreferences.edit().putInt("CurrentDateOrNull",0).apply();
        }


     //   holder.tv_name_payPen.setText("Name: "+dataPaymentPendings.get(position).getFname()+" "+dataPaymentPendings.get(position).getLname());
        holder.tv_name_payPen.setText("Name: "+dataPaymentPendings.get(position).getCustomer_name());
        holder.tv_mobile_no_payPen.setText("Mobile No: "+dataPaymentPendings.get(position).getMobileno());
        holder.tv_Wta_Number_payPen.setText("WhatsApp no: "+dataPaymentPendings.get(position).getWhno());
        holder.tv_village_payPen.setText("Village: "+dataPaymentPendings.get(position).getVillage());
        holder.tv_description_payPen.setText("Description: "+dataPaymentPendings.get(position).getV_reason());

        holder.tv_Nextdate_payPen.setText("Next Date: "+dataPaymentPendings.get(position).getN_date());

        holder.lin_inqview_CSWS.setVisibility(View.GONE);

        String pos = (String.valueOf(position));

        holder.txt_payment_pending.setText("Payment: "+"\n"+dataPaymentPendings.get(position).getFinal_amt());

        // holder.txt_payment_pending.setText("Payment: "+"\n"+total_price );

       /* if(dataPaymentPendings.get(position).getFinal_amt()== null
                ||dataPaymentPendings.get(position).getFinal_amt().equals("0")) {

            holder.txt_Clear_pending.setVisibility(View.VISIBLE);
        }
        else {
            holder.txt_Clear_pending.setVisibility(View.GONE);
        }*/





            holder.txt_payment_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick123: BookingUploadDataDisplayAdapter");
                Intent i = new Intent(context, BorrowLadgerOneActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("idBookingUpload", dataPaymentPendings.get(position).getId());
                i.putExtra("position",pos);
                i.putExtra("idPayment",dataPaymentPendings.get(position).getId());
                i.putExtra("mobile",dataPaymentPendings.get(position).getMobileno());
                context.startActivity(i);
            }
        });


        holder.linBookingUploadMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,OpenGeneralVisitFormActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Name",dataPaymentPendings.get(position).getFname());
                i.putExtra("Category","Payment Collection");
                i.putExtra("cat_id","Payment Collection");
             // i.putExtra("sname",dataPaymentPendings.get(position).getId());
                i.putExtra("sname",dataPaymentPendings.get(position).getFname());
                i.putExtra("id",dataPaymentPendings.get(position).getId());
                i.putExtra("MobileNo",dataPaymentPendings.get(position).getMobileno());
                i.putExtra("add_id",dataPaymentPendings.get(position).getId());
                i.putExtra("add_type","Booking");
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
       return dataPaymentPendings.size();
    //   return dataPaymentPendings_one.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name_payPen,tv_mobile_no_payPen,tv_Wta_Number_payPen,tv_village_payPen,
                tv_description_payPen,tv_Nextdate_payPen;

        TextView txt_payment_pending,txt_pendingDoc_pending,txt_Clear_pending;

        LinearLayout linBookingUploadMain;


        LinearLayout lin_inqview_CSWS;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_name_payPen = itemView.findViewById(R.id.tv_name_payPen);
            this.tv_mobile_no_payPen = itemView.findViewById(R.id.tv_mobile_no_payPen);
            this.tv_Wta_Number_payPen = itemView.findViewById(R.id.tv_Wta_Number_payPen);
            this.tv_village_payPen = itemView.findViewById(R.id.tv_village_payPen);
            this.tv_description_payPen = itemView.findViewById(R.id.tv_description_payPen);

            this.txt_payment_pending = itemView.findViewById(R.id.txt_payment_pending);
            this.txt_pendingDoc_pending = itemView.findViewById(R.id.txt_pendingDoc_pending);
            this.txt_Clear_pending = itemView.findViewById(R.id.txt_Clear_pending);

            this.linBookingUploadMain = itemView.findViewById(R.id.linBookingUploadMain);
            this.tv_Nextdate_payPen = itemView.findViewById(R.id.tv_Nextdate_payPen);

            this.lin_inqview_CSWS = itemView.findViewById(R.id.lin_inqview_CSWS);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                      dataPaymentPendings=dataPaymentPendings_one;
                } else {

                    List<DataBorrowOne> filteredList = new ArrayList<>();

                    for (DataBorrowOne row : dataPaymentPendings) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getCustomer_name();
                        String strMobile =row.getMobileno();
                        String strVName =row.getVillage();
                        String strModel =row.getModel();

                        Log.d("TAGTAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strModel == null)
                            strModel = " ";
                       /* if(strLName == null)
                            strLName = " ";*/

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                || strModel.toLowerCase().contains(charString.toLowerCase())
                                || strVName.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                  //  dataPaymentPendings_one = filteredList;
                    dataPaymentPendings = filteredList;
                }

                FilterResults filterResults = new FilterResults();
              //  filterResults.values = dataPaymentPendings_one;
                filterResults.values = dataPaymentPendings;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
              //  dataPaymentPendings_one = (ArrayList<DataBorrowOne>) filterResults.values;
                dataPaymentPendings = (ArrayList<DataBorrowOne>) filterResults.values;
                Log.d("TAGTAG", "publishResults: "+filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

}
