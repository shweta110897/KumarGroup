package com.example.kumarGroup.BookingDeliveryUpload.Loan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.Booking_Next;
import com.example.kumarGroup.DataLoan;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanDataAdapter extends RecyclerView.Adapter<LoanDataAdapter.viewHolder>
{
    Context context;
    LoanDataMainActivity activity;
    List<DataLoan>  dataBookings;

    String sms= "";//The message you want to text to the phone
    ProgressDialog pro;




    public LoanDataAdapter(LoanDataMainActivity activity, Context context, List<DataLoan> dataBookings) {
        this.context = context;
        this.activity = activity;
        this.dataBookings = dataBookings;
    }

    int i;

    @NonNull
    @Override
    public LoanDataAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.booking_main_raw, parent, false);
        LoanDataAdapter.viewHolder viewHolder = new LoanDataAdapter.viewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoanDataAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name_booking.setText("Name: "+dataBookings.get(position).getFname() +" "+dataBookings.get(position).getLname());
        holder.tv_mobile_no_booking.setText("Mobile No: "+dataBookings.get(position).getMobileno());
        holder.tv_Wta_Number_booking.setText("WhatsApp no: "+dataBookings.get(position).getWhno());
        holder.tv_village_booking.setText("Village: "+dataBookings.get(position).getVillage());
        holder.tv_description_booking.setText("Description: "+dataBookings.get(position).getDescription());
        holder.tv_Type.setText("Type: "+dataBookings.get(position).getB_type());
        holder.tv_Employee_name.setText("Employee Name: "+dataBookings.get(position).getEmp());
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
            holder.tv_model_name.setText("Model Name: "+dataBookings.get(position).getDmodelname());
        }

        //  Toast.makeText(context, ""+dataBookings.get(position).getFname(), Toast.LENGTH_SHORT).show();
      // int   ji = holder.getAdapterPosition();

        int ij = position;

        String pos = (String.valueOf(position));

        holder.linBookingUploadMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, LoanFillFormActivity.class);
                i.putExtra("idBookingUpload", dataBookings.get(position).getId());
               // i.getIntExtra("position", Integer.parseInt(String.valueOf(dataBookings.get(position))));
                i.putExtra("position",pos);
                Log.e("possss", "onClick: "+pos);
                context.startActivity(i);
            }
        });


        holder.lin_BookingMain_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dataBookings.get(position).getMobileno()));
               // Log.d("call", "onClick: "+dataBookings.get(position).getMobileno());
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


        holder.lin_BookingMain_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Name: "+dataBookings.get(position).getFname()+" "+
                        dataBookings.get(position).getLname()+"\n" +
                        "Employee Name: "+dataBookings.get(position).getEmp()+"\n" +
                        "Mobile: "+dataBookings.get(position).getMobileno()+"\n"+
                        "WhatsApp Number: "+dataBookings.get(position).getWhno()+"\n"+
                        "Description: "+dataBookings.get(position).getDescription()+"\n");
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });


        holder.lin_BookingMain_Next.setVisibility(View.GONE);
//        Log.d("TAG", "onBindViewHolder: CheckNextButton");
        if (BookingDeliveryMainActivity.Next_Button_Check && dataBookings.get(position).getLoan_next_click().equals("0")){
//            Log.d("TAG", "onBindViewHolder: CheckNextButton in if");

            holder.lin_BookingMain_Next.setVisibility(View.VISIBLE);
        }

        holder.lin_BookingMain_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                pro.show();
                Log.d("TAG", "onClick: CheckNextButton "+dataBookings.get(position).getId());

                WebService.getClient().Booking_Next(dataBookings.get(position).getId(),"Loan").enqueue(new Callback<Booking_Next>() {
                    @Override
                    public void onResponse(Call<Booking_Next> call, Response<Booking_Next> response) {

                        if (response.body().getMsg().equals("Record Update Succesfully")){
                            Toast.makeText(context, "Record Update Succesfully", Toast.LENGTH_SHORT).show();
                            activity.finish();
                            context.startActivity(activity.getIntent());
                        }

                    }

                    @Override
                    public void onFailure(Call<Booking_Next> call, Throwable t) {

                    }
                });
            }
        });


        holder.lin_MDY_visit.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return dataBookings.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name_booking, tv_mobile_no_booking, tv_Wta_Number_booking,
                tv_village_booking, tv_description_booking,lin_BookingMain_Next,lin_MDY_visit,
                tv_Type,tv_Employee_name,tv_Rto,tv_consumer_scheme,tv_model_name;
        RelativeLayout linBookingUploadMain;

        TextView lin_BookingMain_call,lin_BookingMain_sms,lin_BookingMain_whapp,lin_BookingMain_share;

        public viewHolder(@NonNull View itemView) {
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
            this.lin_BookingMain_Next=itemView.findViewById(R.id.lin_BookingMain_Next);
            this.tv_Type=itemView.findViewById(R.id.tv_Type);
            this.tv_Employee_name=itemView.findViewById(R.id.tv_Employee_name);
            this.tv_Rto=itemView.findViewById(R.id.tv_Rto);
            this.tv_consumer_scheme=itemView.findViewById(R.id.tv_consumer_scheme);
            this.tv_model_name=itemView.findViewById(R.id.tv_model_name);
        }
    }
}
