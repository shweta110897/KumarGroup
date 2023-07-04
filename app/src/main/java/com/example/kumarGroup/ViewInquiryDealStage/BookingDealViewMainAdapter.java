package com.example.kumarGroup.ViewInquiryDealStage;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataBooking;
import com.example.kumarGroup.R;

import java.util.List;

public class BookingDealViewMainAdapter extends RecyclerView.Adapter<BookingDealViewMainAdapter.ViewHolder>{

    Context context;
    Context activity;
    List<DataBooking>  dataBookings;
    String sms= "";//The message you want to text to the phone
    ProgressDialog pro;


    public BookingDealViewMainAdapter(Context activity, Context context, List<DataBooking> dataBookings) {
        this.activity = activity;
        this.context = context;
        this.dataBookings = dataBookings;
    }

    @NonNull
    @Override
    public BookingDealViewMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.design_booking, parent, false);
        BookingDealViewMainAdapter.ViewHolder viewHolder = new BookingDealViewMainAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingDealViewMainAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_name_booking.setText("Name: "+dataBookings.get(position).getFname());
        holder.tv_mobile_no_booking.setText("Mobile No: "+dataBookings.get(position).getMobileno());
        holder.tv_Wta_Number_booking.setText("WhatsApp no: "+dataBookings.get(position).getWhno());
        holder.tv_village_booking.setText("Village: "+dataBookings.get(position).getVillage());
        holder.tv_description_booking.setText("Description: "+dataBookings.get(position).getP_desc());
        holder.tv_Type.setText("Type: "+dataBookings.get(position).getB_type());
        holder.tv_Employee_name.setText(dataBookings.get(position).getEmp());
        holder.payment_type.setText("Payment Type: "+dataBookings.get(position).getAtype());


        holder.file_count.setText("Files(0)");
        if (dataBookings.get(position).getRto()==null){
            holder.tv_Rto.setText("RTO: ");
        }else {
            holder.tv_Rto.setText("RTO: "+dataBookings.get(position).getRto());
        }
        if (dataBookings.get(position).getCskim()==null){
            holder.tv_consumer_scheme.setText("Consumer Scheme: ");
        }else {
            holder.tv_consumer_scheme.setText("Consumer Scheme: "+dataBookings.get(position).getCskim());
        }
        if (dataBookings.get(position).getDmodelname()==null){
            holder.tv_model_name.setText("Model Name: ");
        }else {
            holder.tv_model_name.setText("Model Name: "+dataBookings.get(position).getModel_name());
        }


      //  Toast.makeText(context, ""+dataBookings.get(position).getFname(), Toast.LENGTH_SHORT).show();

        String pos = (String.valueOf(position));



        holder.lin_BookingMain_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dataBookings.get(position).getMobileno()));
                Log.d("call", "onClick: "+dataBookings.get(position).getMobileno());
                context.startActivity(intent);
            }
        });


        holder.lin_BookingMain_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        dataBookings.get(position).getMobileno(), null));
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
                            dataBookings.get(position).getWhno()));
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


        holder.design_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = context.getSharedPreferences("NextId1", MODE_PRIVATE);
                preferences.edit().remove("NextId1").commit();
                preferences.edit().remove("phase").commit();

                Intent i = new Intent(context, DealfirstMeetingDeliveryActivityViewINQ.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("vemp",dataBookings.get(position).getEmp_id());
//                i.putExtra("cat_id",dataBookings.get(position).getCat_id());
                i.putExtra("sname",dataBookings.get(position).getSname());
                i.putExtra("mobo",dataBookings.get(position).getMobileno());
                i.putExtra("login_emp",dataBookings.get(position).getEmpid());
                i.putExtra("Whatsappnumber",dataBookings.get(position).getWhno());

                context.startActivity(i);
            }
        });


     /*   holder.lin_BookingMain_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Name: "+dataBookings.get(position).getFname()+" "+
                        dataBookings.get(position).getLname()+"\n" +
                        "Employee Name: "+dataBookings.get(position).getEmp()+"\n" +
                        "Mobile: "+dataBookings.get(position).getMobileno()+"\n"+
                        "WhatsApp Number: "+dataBookings.get(position).getWhno()+"\n"+
                        "Description: "+dataBookings.get(position).getDescription()+"\n");
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return dataBookings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name_booking, tv_mobile_no_booking, tv_Wta_Number_booking,
                tv_village_booking, tv_description_booking,payment_type,
                tv_Type,tv_Employee_name,tv_Rto,tv_consumer_scheme,tv_model_name;
        LinearLayout linBookingUploadMain;

        ImageView lin_BookingMain_call,lin_BookingMain_sms,lin_BookingMain_whapp,
        design_deal,remider,addfile;

        TextView file_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_name_booking = itemView.findViewById(R.id.tv_name_booking);
            this.tv_mobile_no_booking = itemView.findViewById(R.id.tv_mobile_no_booking);
            this.tv_Wta_Number_booking = itemView.findViewById(R.id.tv_Wta_Number_booking);
            this.payment_type = itemView.findViewById(R.id.payment_type);
            this.tv_village_booking = itemView.findViewById(R.id.tv_village_booking);
            this.tv_description_booking = itemView.findViewById(R.id.tv_description_booking);
            this.linBookingUploadMain = itemView.findViewById(R.id.linBookingUploadMain);

            this.lin_BookingMain_call=itemView.findViewById(R.id.lin_BookingMain_call);
            this.lin_BookingMain_sms=itemView.findViewById(R.id.lin_BookingMain_sms);
            this.lin_BookingMain_whapp=itemView.findViewById(R.id.lin_BookingMain_whapp);
//            this.lin_BookingMain_Next=itemView.findViewById(R.id.lin_BookingMain_Next);
            this.tv_Type=itemView.findViewById(R.id.tv_Type);
            this.tv_Employee_name=itemView.findViewById(R.id.tv_Employee_name);
            this.tv_Rto=itemView.findViewById(R.id.tv_Rto);
            this.tv_consumer_scheme=itemView.findViewById(R.id.tv_consumer_scheme);
            this.tv_model_name=itemView.findViewById(R.id.tv_model_name);


            this.design_deal=itemView.findViewById(R.id.design_deal);
            this.remider=itemView.findViewById(R.id.remider);
            this.addfile=itemView.findViewById(R.id.addfile);

            this.file_count=itemView.findViewById(R.id.file_count);
        }
    }
}
