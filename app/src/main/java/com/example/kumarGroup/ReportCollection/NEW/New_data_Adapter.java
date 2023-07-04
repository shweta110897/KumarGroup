package com.example.kumarGroup.ReportCollection.NEW;

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

import com.example.kumarGroup.Newdata;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ReportCollection.BorrowLadgerOneActivity;
import com.example.kumarGroup.ReportCollection.OpenGeneralVisitFormActivity;

import java.util.ArrayList;
import java.util.List;

public class New_data_Adapter extends RecyclerView.Adapter<New_data_Adapter.ViewHolder> implements Filterable {

    Display_new_data_Activity activity;
    Context context;
    List<Newdata.Data> Cat;
    List<Newdata.Data> Cat1;

    public New_data_Adapter(Display_new_data_Activity activity, Context context, List<Newdata.Data> Cat) {
        this.activity = activity;
        this.context = context;
        this.Cat = Cat;
        this.Cat1 = Cat;
    }

    @NonNull
    @Override
    public New_data_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_pending_raw,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull New_data_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_pendingDoc_pending.setVisibility(View.GONE);
        holder.txt_Clear_pending.setVisibility(View.GONE);

        if (Select_NEW_Type_Activity.Type_NEW_Check){
            holder.tv_name_payPen.setText("Name: "+Cat.get(position).getCustomer_name());
            holder.txt_payment_pending.setText("Payment: "+"\n"+Cat.get(position).getFinal_amt());

        }else {
            holder.tv_name_payPen.setText("Name: "+Cat.get(position).getCname());
            holder.txt_payment_pending.setText("Payment: "+"\n"+Cat.get(position).getLeft_amt());

        }

        holder.tv_mobile_no_payPen.setText("Mobile No: "+Cat.get(position).getMobileno());
        holder.tv_Wta_Number_payPen.setText("WhatsApp no: "+Cat.get(position).getWhno());
        holder.tv_village_payPen.setText("Village: "+Cat.get(position).getVillage());
        holder.tv_description_payPen.setText("Description: "+Cat.get(position).getV_reason());

        holder.tv_Nextdate_payPen.setText("Next Date: "+Cat.get(position).getN_date());

        holder.lin_inqview_CSWS.setVisibility(View.GONE);

        String pos = (String.valueOf(position));


        holder.txt_payment_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick123: BookingUploadDataDisplayAdapter");
                Intent i = new Intent(context, BorrowLadgerOneActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("idBookingUpload", Cat.get(position).getId());
                i.putExtra("position",pos);
                i.putExtra("idPayment",Cat.get(position).getId());
                i.putExtra("mobile",Cat.get(position).getMobileno());
                context.startActivity(i);
            }
        });
        if (Select_NEW_Type_Activity.Type_NEW_Check){
            holder.linBookingUploadMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, OpenGeneralVisitFormActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("Name",Cat.get(position).getFname());
                    i.putExtra("Category","Payment Collection");
                    i.putExtra("cat_id","Payment Collection");
                    // i.putExtra("sname",dataPaymentPendings.get(position).getId());
                    i.putExtra("sname",Cat.get(position).getFname());
                    i.putExtra("id",Cat.get(position).getId());
                    i.putExtra("MobileNo",Cat.get(position).getMobileno());
                    i.putExtra("add_id",Cat.get(position).getId());
                    i.putExtra("add_type","Booking");
                    context.startActivity(i);
                }
            });
        }else {
            holder.linBookingUploadMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,OpenGeneralVisitFormActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("Name",Cat.get(position).getCname());
                    i.putExtra("Category","Payment Collection");
                    i.putExtra("cat_id","Payment Collection");
                    // i.putExtra("sname",dataPaymentPendings.get(position).getId());
                    i.putExtra("sname",Cat.get(position).getCname());
                    i.putExtra("id",Cat.get(position).getId());

                    i.putExtra("MobileNo",Cat.get(position).getMobileno());
                    i.putExtra("add_id",Cat.get(position).getId());
                    i.putExtra("add_type","Workshop");
                    context.startActivity(i);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return Cat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
                    Cat=Cat1;
                } else {

                    List<Newdata.Data> filteredList = new ArrayList<>();

                    for (Newdata.Data row : Cat) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName;
                        Log.d("TAG", "performFiltering: checkdetail "+Select_NEW_Type_Activity.Type_NEW_Check);
                        if (Select_NEW_Type_Activity.Type_NEW_Check){
                            strFName=row.getCustomer_name();
                            Log.d("TAG", "performFiltering: checkdetail 1 ");
                        }else {
                            strFName=row.getCname();
                            Log.d("TAG", "performFiltering: checkdetail 2 ");

                        }
                        String strMobile =row.getMobileno();
                        String strVName =row.getVillage();
                        String strModel = String.valueOf(row.getModel());

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
                    Cat = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                //  filterResults.values = dataPaymentPendings_one;
                filterResults.values = Cat;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                //  dataPaymentPendings_one = (ArrayList<DataBorrowOne>) filterResults.values;
                Cat = (ArrayList<Newdata.Data>) filterResults.values;
                Log.d("TAGTAG", "publishResults: "+filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}
