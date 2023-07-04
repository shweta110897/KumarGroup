package com.example.kumarGroup.BookingDeliveryUpload.Passing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataPassing;
import com.example.kumarGroup.R;

import java.util.List;

public class PassingDataAdapter extends RecyclerView.Adapter<PassingDataAdapter.ViewHolder> {

    Context context;
    List<DataPassing> dataPassings;

    String sms= "";//The message you want to text to the phone

    public PassingDataAdapter(Context context, List<DataPassing> dataPassings) {
        this.context = context;
        this.dataPassings = dataPassings;
    }

    @NonNull
    @Override
    public PassingDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.booking_main_raw, parent, false);
        PassingDataAdapter.ViewHolder viewHolder = new PassingDataAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PassingDataAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name_booking.setText("Name: "+dataPassings.get(position).getFname()+" "+dataPassings.get(position).getLname());
        holder.tv_mobile_no_booking.setText("Mobile No: "+dataPassings.get(position).getMobileno());
        holder.tv_Wta_Number_booking.setText("WhatsApp no: "+dataPassings.get(position).getWhno());
        holder.tv_village_booking.setText("Village: "+dataPassings.get(position).getVillage());
        holder.tv_description_booking.setText("Description: "+dataPassings.get(position).getDescription());
        holder.tv_Type.setText("Type: "+dataPassings.get(position).getB_type());
        holder.tv_Employee_name.setText("Employee Name: "+dataPassings.get(position).getEmp());
        if (dataPassings.get(position).getRto()==null){
            holder.tv_Rto.setText("RTO: ");
        }else {
            holder.tv_Rto.setText("RTO: "+dataPassings.get(position).getRto());
        }
        if (dataPassings.get(position).getCskim()==null){
            holder.tv_consumer_scheme.setText("Consumer Scheme: ");
        }else {
            holder.tv_consumer_scheme.setText("Consumer Scheme: "+dataPassings.get(position).getCskim());
        }
        if (dataPassings.get(position).getDmodelname()==null){
            holder.tv_model_name.setText("Model Name: ");
        }else {
            holder.tv_model_name.setText("Model Name: "+dataPassings.get(position).getDmodelname());
        }
        //  Toast.makeText(context, ""+dataBookings.get(position).getFname(), Toast.LENGTH_SHORT).show();

        String pos = (String.valueOf(position));

        holder.linBookingUploadMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PassingFormActivity.class);
                i.putExtra("idBookingUpload", dataPassings.get(position).getId());
                i.putExtra("position",pos);
                // i.getIntExtra("position", Integer.parseInt(String.valueOf(dataDisplays.get(position))));
                context.startActivity(i);
            }
        });


        holder.lin_BookingMain_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dataPassings.get(position).getMobileno()));
                Log.d("call", "onClick: "+dataPassings.get(position).getMobileno());
                context.startActivity(intent);
            }
        });


        holder.lin_BookingMain_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        dataPassings.get(position).getMobileno(), null));
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
                            dataPassings.get(position).getWhno()));
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


        holder.lin_BookingMain_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Name: "+dataPassings.get(position).getFname()+" "+
                        dataPassings.get(position).getLname()+"\n" +
                        "Employee Name: "+dataPassings.get(position).getEmp()+"\n" +
                        "Mobile: "+dataPassings.get(position).getMobileno()+"\n"+
                        "WhatsApp Number: "+dataPassings.get(position).getWhno()+"\n"+
                        "Description: "+dataPassings.get(position).getDescription()+"\n");
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });

        holder.lin_MDY_visit.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return dataPassings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name_booking, tv_mobile_no_booking, tv_Wta_Number_booking,
                tv_village_booking, tv_description_booking,lin_MDY_visit,
                tv_Type,tv_Employee_name,tv_Rto,tv_consumer_scheme,tv_model_name;
        RelativeLayout linBookingUploadMain;

        TextView lin_BookingMain_call,lin_BookingMain_sms,lin_BookingMain_whapp,lin_BookingMain_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.lin_MDY_visit = itemView.findViewById(R.id.lin_MDY_visit);
            this.tv_name_booking = itemView.findViewById(R.id.tv_name_booking);
            this.tv_mobile_no_booking = itemView.findViewById(R.id.tv_mobile_no_booking);
            this.tv_Wta_Number_booking = itemView.findViewById(R.id.tv_Wta_Number_booking);
            this.tv_village_booking = itemView.findViewById(R.id.tv_village_booking);
            this.tv_description_booking = itemView.findViewById(R.id.tv_description_booking);
            this.linBookingUploadMain = itemView.findViewById(R.id.linBookingUploadMain);

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
}
