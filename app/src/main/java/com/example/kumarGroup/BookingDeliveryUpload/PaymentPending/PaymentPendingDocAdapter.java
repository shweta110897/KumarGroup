package com.example.kumarGroup.BookingDeliveryUpload.PaymentPending;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.BookingUpload_PaymentPendingCSW_Model;
import com.example.kumarGroup.ClearPayPenData;
import com.example.kumarGroup.DataPaymentPending;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class PaymentPendingDocAdapter extends RecyclerView.Adapter<PaymentPendingDocAdapter.ViewHolder>
    implements Filterable
{
    Context context;
    List<DataPaymentPending>  dataPaymentPendings;
    List<DataPaymentPending>  dataPaymentPendings_one;

    SharedPreferences sharedPreferences,sp1;
    Activity activity;

    Integer tpVal,totalvallllll;
    SharedPreferences prefs;

    String sms= "";//The message you want to text to the phone
    Utils utils;
    SharePref sp;
    String Mobilecall;
    String emp,FinalAmt;

    ProgressDialog progressDialog;

    String finish, finalAmt;

    private RecyclerViewItemInterface1 viewItemInterface;
    String idPayment;

    public  void setViewItemInterface(RecyclerViewItemInterface1 viewItemInterface) {
        this.viewItemInterface = viewItemInterface;
    }

    public PaymentPendingDocAdapter(Context context, List<DataPaymentPending> dataPaymentPendings,String idPayment) {
        this.context = context;
        this.dataPaymentPendings = dataPaymentPendings;
        this.dataPaymentPendings_one = dataPaymentPendings;
        this.idPayment = idPayment;
       // this.total_price = total_price;
       // this.payment_payPenModels_1 = payment_payPenModels_1;

        activity = (Activity) context;
        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }

    @NonNull
    @Override
    public PaymentPendingDocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.payment_pending_raw, parent, false);
        PaymentPendingDocAdapter.ViewHolder viewHolder = new PaymentPendingDocAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentPendingDocAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name_payPen.setText("Name: "+dataPaymentPendings.get(position).getFname()+" "+dataPaymentPendings.get(position).getLname());
        holder.tv_mobile_no_payPen.setText("Mobile No: "+dataPaymentPendings.get(position).getMobileno());
        holder.tv_Wta_Number_payPen.setText("WhatsApp no: "+dataPaymentPendings.get(position).getWhno());
        holder.tv_village_payPen.setText("Village: "+dataPaymentPendings.get(position).getVillage()
                /*""+dataPaymentPendings.get(position).getProduct_name()*/);
        holder.tv_description_payPen.setText("Description: "+dataPaymentPendings.get(position).getDescription());

        String pos = (String.valueOf(position));

       holder.tv_Nextdate_payPen.setVisibility(View.GONE);

       sharedPreferences = context.getSharedPreferences("TP_Payment",MODE_PRIVATE);
       tpVal = sharedPreferences.getInt("TP_Payment1",0);


       holder.txt_payment_pending.setText("Payment: "+"\n"+dataPaymentPendings.get(position).getFinal_amt());

        holder.txt_payment_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,PaymentMainActivity.class);
                i.putExtra("idBookingUpload", dataPaymentPendings.get(position).getId());
                i.putExtra("position",pos);
                i.putExtra("idPayment",dataPaymentPendings.get(position).getId());
                i.putExtra("MobileNo",dataPaymentPendings.get(position).getMobileno());
                context.startActivity(i);
            }
        });


        holder.txt_pendingDoc_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,PendingDocActivity.class);
                i.putExtra("idBookingUpload", dataPaymentPendings.get(position).getId());
                i.putExtra("position",pos);
                context.startActivity(i);
            }
        });

        if(dataPaymentPendings.get(position).getFinal_amt()== null){
            Log.d("TAG", "onBindViewHolder: checkclearbutton 1");

            holder.txt_Clear_pending.setVisibility(View.GONE);
            holder.txt_payment_pending.setVisibility(View.VISIBLE);
            FinalAmt = "";

        }
        else{
            FinalAmt = dataPaymentPendings.get(position).getFinal_amt();
            if(FinalAmt.equals("0"))
            {
                Log.d("TAG", "onBindViewHolder: checkclearbutton 2");
                holder.txt_Clear_pending.setVisibility(View.VISIBLE);
                holder.txt_payment_pending.setVisibility(View.GONE);
            }
            else{

                holder.txt_Clear_pending.setVisibility(View.GONE);
                holder.txt_payment_pending.setVisibility(View.VISIBLE);
            }
        }

        Log.d("TAG", "onResponse: check_click 3 "+dataPaymentPendings.get(position).getProduct_name()+" "
                +dataPaymentPendings.get(position).getMobileno()+" "+dataPaymentPendings.get(position).getDescription());

        if(FinalAmt.equals("0") ){

            holder.txt_Clear_pending.setVisibility(View.VISIBLE);
        }
        else {

            holder.txt_Clear_pending.setVisibility(View.GONE);
        }

        if (dataPaymentPendings.get(position).getProduct_name().equals("New Tractor")){

        }else {
            if (dataPaymentPendings.get(position).getFinal_amt().equals("0")){
                holder.txt_Clear_pending.setVisibility(View.VISIBLE);
            }else {
                holder.txt_Clear_pending.setVisibility(View.GONE);

            }
        }


        holder.txt_Clear_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= new ProgressDialog(context);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                WebService.getClient().getClearPayPenData(dataPaymentPendings.get(position).getId(),
                        dataPaymentPendings.get(position).getModel_name(),
                        dataPaymentPendings.get(position).getProduct_name(),
                        dataPaymentPendings.get(position).getEmpid(),
                        dataPaymentPendings.get(position).getMobileno(),
                        dataPaymentPendings.get(position).getFname(),
                        dataPaymentPendings.get(position).getVillage(),
                        dataPaymentPendings.get(position).getDelivery_date()).enqueue(
                        new Callback<ClearPayPenData>() {
                            @Override
                            public void onResponse(@NotNull Call<ClearPayPenData> call, @NotNull Response<ClearPayPenData> response) {

                                progressDialog.dismiss();
                                assert response.body() != null;
                                Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                if (viewItemInterface != null) {
                                    viewItemInterface.onItemClick(holder.getAdapterPosition(),dataPaymentPendings,idPayment);
                                }
                            }

                            @Override
                            public void onFailure(@NotNull Call<ClearPayPenData> call, @NotNull Throwable t) {
                                progressDialog.dismiss();
                            }
                        }
                );
            }
        });


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dataPaymentPendings.get(position).getMobileno()));
                Log.d("call", "onClick: "+dataPaymentPendings.get(position).getMobileno());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + dataPaymentPendings.get(position).getMobileno());
                sp.putSharedPref(sp.CALL_ID, dataPaymentPendings.get(position).getId());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = dataPaymentPendings.get(position).getMobileno();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();
                }


                WebService.getClient().getPayPenBookingUpload(emp,
                        dataPaymentPendings.get(position).getId() ,
                        dataPaymentPendings.get(position).getMobileno(),
                        "Call").enqueue(new Callback<BookingUpload_PaymentPendingCSW_Model>() {
                    @Override
                    public void onResponse(@NotNull Call<BookingUpload_PaymentPendingCSW_Model> call,
                                           @NotNull Response<BookingUpload_PaymentPendingCSW_Model> response) {
                        Log.d("Call", "onResponse: "+response.body().getMsg());

                        //Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NotNull Call<BookingUpload_PaymentPendingCSW_Model> call,
                                          @NotNull Throwable t) {

                    }
                });

            }
        });


        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        dataPaymentPendings.get(position).getMobileno(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);

                WebService.getClient().getPayPenBookingUpload(emp,
                        dataPaymentPendings.get(position).getId() ,
                        dataPaymentPendings.get(position).getMobileno(),
                        "Sms").enqueue(new Callback<BookingUpload_PaymentPendingCSW_Model>() {
                    @Override
                    public void onResponse(@NotNull Call<BookingUpload_PaymentPendingCSW_Model> call,
                                           @NotNull Response<BookingUpload_PaymentPendingCSW_Model> response) {
                       // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.d("SMS", "onResponse: "+response.body().getMsg());

                    }

                    @Override
                    public void onFailure(@NotNull Call<BookingUpload_PaymentPendingCSW_Model> call,
                                          @NotNull Throwable t) {

                    }
                });
            }
        });


        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+
                            dataPaymentPendings.get(position).getMobileno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                    WebService.getClient().getPayPenBookingUpload(emp,
                            dataPaymentPendings.get(position).getId() ,
                            dataPaymentPendings.get(position).getWhno(),
                            "Whatsapp").enqueue(new Callback<BookingUpload_PaymentPendingCSW_Model>() {
                        @Override
                        public void onResponse(@NotNull Call<BookingUpload_PaymentPendingCSW_Model> call,
                                               @NotNull Response<BookingUpload_PaymentPendingCSW_Model> response) {
                           // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("Whatsapp", "onResponse: "+response.body().getMsg());
                        }


                        @Override
                        public void onFailure(@NotNull Call<BookingUpload_PaymentPendingCSW_Model> call,
                                              @NotNull Throwable t) {

                        }
                    });

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


        holder.lin_MDY_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Customer Name: "+dataPaymentPendings.get(position).getFname()+"\n" +
                        "Mobile: "+dataPaymentPendings.get(position).getMobileno()+"\n"+
                        "Village: "+dataPaymentPendings.get(position).getVillage()+"\n"+
                        "Description: "+dataPaymentPendings.get(position).getDescription());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPaymentPendings.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name_payPen,tv_mobile_no_payPen,tv_Wta_Number_payPen,tv_village_payPen,
                tv_description_payPen,tv_Nextdate_payPen;

        TextView txt_payment_pending,txt_pendingDoc_pending,txt_Clear_pending;


        TextView lin_MDY_call, lin_MDY_sms, lin_MDY_whapp, lin_MDY_share, lin_MDY_visit;


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

            this.tv_Nextdate_payPen = itemView.findViewById(R.id.tv_Nextdate_payPen);


            this.lin_MDY_call = itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms = itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp = itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share = itemView.findViewById(R.id.lin_MDY_share);
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
                    //  catShowRCGVS_one = catShowRCGVS;
                    // FilterdlistData = listData;
                    dataPaymentPendings = dataPaymentPendings_one;
                } else {

                    List<DataPaymentPending> filteredList = new ArrayList<>();

                    for (DataPaymentPending row : dataPaymentPendings_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMobileno();
                        String strVName =row.getVillage();
                        String strModel =row.getModel();
                       // String strType = (String) row.getInq_type();
                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strModel == null)
                            strModel = " ";


                       /* if(strType == null)
                            strType = " ";*/

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                || strModel.toLowerCase().contains(charString.toLowerCase())
                                /* || row.getDistric().toLowerCase().contains(charString.toLowerCase())*/
                                || strVName.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    dataPaymentPendings = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataPaymentPendings;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataPaymentPendings = (ArrayList<DataPaymentPending>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }




    public interface RecyclerViewItemInterface1 {

        void onItemClick(int position,List<DataPaymentPending>  dataPaymentPendings,String idPayment);

    }

}
