package com.example.kumarGroup.BookingDeliveryUpload.RcBook_Financer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.RcUpdate;

import java.util.ArrayList;
import java.util.List;


public class RC_Financer_Update_Detail_Adapter extends RecyclerView.Adapter<RC_Financer_Update_Detail_Adapter.ViewHolder>  implements Filterable {

    RC_Financer_Detail_Activity activity;
    Context context;
    List<com.example.kumarGroup.RcUpdate.rcUpdate> data;
    List<com.example.kumarGroup.RcUpdate.rcUpdate> data_one;
    String sms= "";

    public RC_Financer_Update_Detail_Adapter(RC_Financer_Detail_Activity activity, Context context, List<com.example.kumarGroup.RcUpdate.rcUpdate> data) {
        this.activity = activity;
        this.context = context;
        this.data = data;
        this.data_one = data;
    }

    @NonNull
    @Override
    public RC_Financer_Update_Detail_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_main_raw,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RC_Financer_Update_Detail_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name_booking.setText("Name: "+data.get(position).getFname()+" "+data.get(position).getLname());
        holder.tv_mobile_no_booking.setText("Mobile No: "+data.get(position).getMobileno());
        holder.tv_Wta_Number_booking.setText("WhatsApp no: "+data.get(position).getWhno());
        holder.tv_village_booking.setText("Village: "+data.get(position).getVillage());
        holder.tv_description_booking.setText("Description: "+data.get(position).getDescription());
        holder.tv_Type.setText("Type: "+data.get(position).getB_type());
        holder.tv_Employee_name.setText("Employee Name: "+data.get(position).getEmp());
        if (data.get(position).getRto()==null){
            holder.tv_Rto.setText("RTO: ");
        }else {
            holder.tv_Rto.setText("RTO: "+data.get(position).getRto());
        }
        if (data.get(position).getCskim()==null){
            holder.tv_consumer_scheme.setText("Consumer Scheme: ");
        }else {
            holder.tv_consumer_scheme.setText("Consumer Scheme: "+data.get(position).getCskim());
        }
        if (data.get(position).getDmodelname()==null){
            holder.tv_model_name.setText("Model Name: ");
        }else {
            holder.tv_model_name.setText("Model Name: "+data.get(position).getDmodelname());
        }
        //  Toast.makeText(context, ""+dataBookings.get(position).getFname(), Toast.LENGTH_SHORT).show();

        String pos = (String.valueOf(position));
        String lname = null;
        if(data.get(position).getLname()!=null){
            lname=data.get(position).getLname().toString();
        }else{
            lname="";
        }

        String finalLname = lname;
        holder.linBookingUploadMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RC_Financer_UpdateActivity.class);
                i.putExtra("idBookingUpload", data.get(position).getId());
                i.putExtra("position",pos);
                i.putExtra("register_no",data.get(position).getRegister_no());
                i.putExtra("emp_name",data.get(position).getFname()+" "+ finalLname);
                i.putExtra("Mobile_No",data.get(position).getMobileno());
                i.putExtra("WA_No",data.get(position).getWhno());
                i.putExtra("Village",data.get(position).getVillage());
                i.putExtra("Financer_Name",data.get(position).getR_e_name());
                i.putExtra("Financer_From",data.get(position).getFinance_from());
                i.putExtra("engineno",data.get(position).getEngineno());
                i.putExtra("Chasisno",data.get(position).getChasisno());
                // i.getIntExtra("position", Integer.parseInt(String.valueOf(dataDisplays.get(position))));
                context.startActivity(i);
            }
        });


        holder.lin_BookingMain_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+data.get(position).getMobileno()));
                Log.d("call", "onClick: "+data.get(position).getMobileno());
                context.startActivity(intent);
            }
        });


        holder.lin_BookingMain_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        data.get(position).getMobileno(), null));
                smsIntent.putExtra("sms_body",sms);
                context.startActivity(smsIntent);
            }
        });


        holder.lin_BookingMain_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+
                            data.get(position).getWhno()));
                    context.startActivity(intent);
                }else {

                    Toast.makeText(context, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean appInstallOrNot(String url)
            {
                PackageManager packageManager = context.getPackageManager();
                boolean app_installed;
                try{

                    packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
                    app_installed = true;
                }catch (PackageManager.NameNotFoundException e){
                    app_installed =false;
                }
                return app_installed;
            }
        });


        holder.lin_MDY_visit.setVisibility(View.GONE);

        holder.lin_BookingMain_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Name: "+data.get(position).getFname()+" "+
                        data.get(position).getLname()+"\n" +
                        "Employee Name: "+data.get(position).getEmp()+"\n" +
                        "Mobile: "+data.get(position).getMobileno()+"\n"+
                        "WhatsApp Number: "+data.get(position).getWhno()+"\n"+
                        "Description: "+data.get(position).getDescription()+"\n");
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_booking, tv_mobile_no_booking, tv_Wta_Number_booking,
                tv_village_booking, tv_description_booking,lin_MDY_visit,
                tv_Type,tv_Employee_name,tv_Rto,tv_consumer_scheme,tv_model_name;
        RelativeLayout linBookingUploadMain;


        TextView lin_BookingMain_call,lin_BookingMain_sms,lin_BookingMain_whapp,lin_BookingMain_share;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name_booking = itemView.findViewById(R.id.tv_name_booking);
            this.tv_mobile_no_booking = itemView.findViewById(R.id.tv_mobile_no_booking);
            this.tv_Wta_Number_booking = itemView.findViewById(R.id.tv_Wta_Number_booking);
            this.tv_village_booking = itemView.findViewById(R.id.tv_village_booking);
            this.tv_description_booking = itemView.findViewById(R.id.tv_description_booking);
            this.linBookingUploadMain = itemView.findViewById(R.id.linBookingUploadMain);
            this.lin_MDY_visit = itemView.findViewById(R.id.lin_MDY_visit);

            this.lin_BookingMain_call=itemView.findViewById(R.id.lin_BookingMain_call);
            this.lin_BookingMain_sms=itemView.findViewById(R.id.lin_BookingMain_sms);
            this.lin_BookingMain_whapp=itemView.findViewById(R.id.lin_BookingMain_whapp);
            this.lin_BookingMain_share=itemView.findViewById(R.id.lin_BookingMain_share);
            this.tv_Type=itemView.findViewById(R.id.tv_Type);
            this.tv_Employee_name=itemView.findViewById(R.id.tv_Employee_name);
            this.tv_Rto=itemView.findViewById(R.id.tv_Rto);
            this.tv_consumer_scheme=itemView.findViewById(R.id.tv_consumer_scheme);
            this.tv_model_name=itemView.findViewById(R.id.tv_model_name);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    //  catShowRCGVS_one = catShowRCGVS;
                    // FilterdlistData = listData;
                    data = data_one;
                } else {

                    List<RcUpdate.rcUpdate> filteredList = new ArrayList<>();

                    for (RcUpdate.rcUpdate row : data_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMobileno();
                        String strVName =row.getVillage();
                        String strLName = (String) row.getLname();
                        String strType =row.getModel_name();
                        String strModel =row.getModel();
                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strLName == null)
                            strLName = " ";

                        if(strType == null)
                            strType = " ";
                        if(strModel == null)
                            strModel = " ";

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                /* || row.getDistric().toLowerCase().contains(charString.toLowerCase())*/
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                                || strModel.toLowerCase().contains(charString.toLowerCase())
                                || strType.toLowerCase().contains(charString.toLowerCase())
                                || strLName.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    data = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = data;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = (ArrayList<RcUpdate.rcUpdate>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }
}
