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

import com.example.kumarGroup.DataSupBorrowList;
import com.example.kumarGroup.R;

import java.util.ArrayList;
import java.util.List;

public class SupBorrowedListAdapter extends RecyclerView.Adapter<SupBorrowedListAdapter.ViewHolder> implements Filterable {

    Context context;
    List<DataSupBorrowList> dataSupBorrowListList;
    List<DataSupBorrowList> dataSupBorrowListList_one;

    public SupBorrowedListAdapter(Context context, List<DataSupBorrowList> dataSupBorrowListList) {
        this.context = context;
        this.dataSupBorrowListList = dataSupBorrowListList;
        this.dataSupBorrowListList_one = dataSupBorrowListList;
    }

    @NonNull
    @Override
    public SupBorrowedListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.payment_pending_raw, parent, false);
        SupBorrowedListAdapter.ViewHolder viewHolder = new SupBorrowedListAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SupBorrowedListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_pendingDoc_pending.setVisibility(View.GONE);
        holder.txt_Clear_pending.setVisibility(View.GONE);


        holder.tv_name_payPen.setText("Name: "+dataSupBorrowListList.get(position).getName());
        holder.tv_mobile_no_payPen.setText("Mobile No: "+dataSupBorrowListList.get(position).getMobile());
        holder.tv_Wta_Number_payPen.setText("WhatsApp no: "+dataSupBorrowListList.get(position).getMobile());
        holder.tv_village_payPen.setText("Village: ");
        holder.tv_description_payPen.setText("Description: ");

        holder.tv_Nextdate_payPen.setText("Next Date: ");

        holder.lin_inqview_CSWS.setVisibility(View.GONE);

        String pos = (String.valueOf(position));

        holder.txt_payment_pending.setText("Payment: "+"\n"+dataSupBorrowListList.get(position).getLeftamount());


        holder.txt_payment_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BorrowLadgerOneActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("idBookingUpload", dataSupBorrowListList.get(position).getId());
                i.putExtra("position",pos);
                i.putExtra("idPayment",dataSupBorrowListList.get(position).getId());
                i.putExtra("mobile",dataSupBorrowListList.get(position).getMobile());
                context.startActivity(i);
            }
        });


        holder.linBookingUploadMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,OpenGeneralVisitFormActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Name",dataSupBorrowListList.get(position).getName());
                i.putExtra("Category","Payment Collection");
                i.putExtra("cat_id","Payment Collection");
                // i.putExtra("sname",dataPaymentPendings.get(position).getId());
                i.putExtra("sname",dataSupBorrowListList.get(position).getName());
                i.putExtra("id",dataSupBorrowListList.get(position).getId());
                i.putExtra("MobileNo",dataSupBorrowListList.get(position).getMobile());
                i.putExtra("add_id",dataSupBorrowListList.get(position).getId());
                i.putExtra("add_type","Booking");
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSupBorrowListList.size();
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
                    dataSupBorrowListList=dataSupBorrowListList_one;
                } else {

                    List<DataSupBorrowList> filteredList = new ArrayList<>();

                    for (DataSupBorrowList row : dataSupBorrowListList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getName();
                        String strMobile =row.getMobile();
                        //String strVName =row.getVillage();

                        Log.d("TAGTAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";

                       /* if(strLName == null)
                            strLName = " ";*/

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    //  dataPaymentPendings_one = filteredList;
                    dataSupBorrowListList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                //  filterResults.values = dataPaymentPendings_one;
                filterResults.values = dataSupBorrowListList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                //  dataPaymentPendings_one = (ArrayList<DataBorrowOne>) filterResults.values;
                dataSupBorrowListList = (ArrayList<DataSupBorrowList>) filterResults.values;
                Log.d("TAGTAG", "publishResults: "+filterResults.values);
                notifyDataSetChanged();
            }
        };
    }


}
