package com.example.kumarGroup.ReportCollection;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatAllEntryMWD;
import com.example.kumarGroup.PaymentCelClearModel;
import com.example.kumarGroup.PaymentCollection_CSWModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class AllEntryMonthWeekDayAdapter  extends RecyclerView.Adapter<AllEntryMonthWeekDayAdapter.ViewHolder>
       {

    Context context;
    List<CatAllEntryMWD> catAllEntryMWDS;

    SharePref sp;
    SharedPreferences sharedPreferences,sp1;
    Utils utils;
    Activity activity;

    String Mobilecall;
    String emp;

    public AllEntryMonthWeekDayAdapter(Activity activity, List<CatAllEntryMWD> catAllEntryMWDS) {
        this.activity = activity;
        this.catAllEntryMWDS = catAllEntryMWDS;

        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

        sharedPreferences = activity.getSharedPreferences("DateCurrent1", MODE_PRIVATE);

        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }

    String sms= "";//The message you want to text to the phone

    @NonNull
    @Override
    public AllEntryMonthWeekDayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.monthly_display_row, parent, false);
        AllEntryMonthWeekDayAdapter.ViewHolder viewHolder = new AllEntryMonthWeekDayAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllEntryMonthWeekDayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.txtMonthDisplayName.setText("Name: "+catAllEntryMWDS.get(position).getFname()+" "+catAllEntryMWDS.get(position).getLname());
        holder.txtMonthDisplayCustomer.setText("Category: "+catAllEntryMWDS.get(position).getCat_name());
        holder.txtMonthDisplayEmpName.setText("Employee Name: "+catAllEntryMWDS.get(position).getEmployee_name());
        holder.txtMonthDisplayMobile.setText("Mobile: "+catAllEntryMWDS.get(position).getMoblino());
        holder.txtMonthDisplayWhatsAppNumber.setText("WhatsApp Number: "+catAllEntryMWDS.get(position).getWhatsappno());
        holder.txtMonthDisplayDescription.setText("Description: "+catAllEntryMWDS.get(position).getReason());
        holder.txtMonthDisplayNextDate.setText("Next Date: "+catAllEntryMWDS.get(position).getVdate());
        holder.txtMonthDisplayFinalAmount.setText("Final Amount: "+catAllEntryMWDS.get(position).getFinal_amt());
        holder.txtMonthDisplayVillage.setText("Village: "+catAllEntryMWDS.get(position).getVilage());

        holder.txtMonthDisplayHotCold.setVisibility(View.GONE);

        if(catAllEntryMWDS.get(position).getFinal_amt()== null) {

            // Toast.makeText(context, ""+catAllEntryMWDS.get(position).getAutoid(), Toast.LENGTH_SHORT).show();
        }
        else if(catAllEntryMWDS.get(position).getFinal_amt().equals("0")) {

          //  Toast.makeText(context, ""+catAllEntryMWDS.get(position).getAutoid(), Toast.LENGTH_SHORT).show();

            WebService.getClient().getPaymentCelClear(catAllEntryMWDS.get(position).getAutoid()).enqueue(new Callback<PaymentCelClearModel>() {
                @Override
                public void onResponse(@NotNull Call<PaymentCelClearModel> call,
                                       @NotNull Response<PaymentCelClearModel> response) {
                    assert response.body() != null;
                 //   Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(@NotNull Call<PaymentCelClearModel> call, @NotNull Throwable t) {
                 //   Toast.makeText(context, ""+t.getCause(), Toast.LENGTH_SHORT).show();

                }
            });

        }
        else {

        }


        holder.Lin_monthlyDailyWeeklyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(context, ""+catAllEntryMWDS.get(position).getAdd_type(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, RCNewEntryFormActivity.class);
                i.putExtra("customer_name",catAllEntryMWDS.get(position).getFname()+" " +
                        ""+catAllEntryMWDS.get(position).getLname());
                i.putExtra("category",catAllEntryMWDS.get(position).getCat_name());
                i.putExtra("catId",catAllEntryMWDS.get(position).getCat_id());
                i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                i.putExtra("id",catAllEntryMWDS.get(position).getAutoid());
                i.putExtra("MobileNo",catAllEntryMWDS.get(position).getMoblino());
                i.putExtra("add_type",catAllEntryMWDS.get(position).getAdd_type());
                i.putExtra("add_id",catAllEntryMWDS.get(position).getAdd_id());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+catAllEntryMWDS.get(position).getMoblino()));
                Log.d("call", "onClick: "+catAllEntryMWDS.get(position).getMoblino());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catAllEntryMWDS.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catAllEntryMWDS.get(position).getId());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = catAllEntryMWDS.get(position).getMoblino();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();
                }


                WebService.getClient().getPaymentCollectionCSW(emp,
                        catAllEntryMWDS.get(position).getAutoid(),
                        catAllEntryMWDS.get(position).getMoblino(),
                        "Call").enqueue(new Callback<PaymentCollection_CSWModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PaymentCollection_CSWModel> call,
                                           @NotNull Response<PaymentCollection_CSWModel> response) {
                        //  Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.d("Call", "onResponse: "+response.body().getMsg());

                    }

                    @Override
                    public void onFailure(@NotNull Call<PaymentCollection_CSWModel> call, @NotNull Throwable t) {

                    }
                });

            }
        });


        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", catAllEntryMWDS.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);



                WebService.getClient().getPaymentCollectionCSW(emp,
                        catAllEntryMWDS.get(position).getAutoid(),
                        catAllEntryMWDS.get(position).getMoblino(),
                        "Sms").enqueue(new Callback<PaymentCollection_CSWModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PaymentCollection_CSWModel> call,
                                           @NotNull Response<PaymentCollection_CSWModel> response) {
                        //  Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.d("Sms", "onResponse: "+response.body().getMsg());

                    }

                    @Override
                    public void onFailure(@NotNull Call<PaymentCollection_CSWModel> call, @NotNull Throwable t) {

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
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+catAllEntryMWDS.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    WebService.getClient().getPaymentCollectionCSW(emp,
                            catAllEntryMWDS.get(position).getAutoid(),
                            catAllEntryMWDS.get(position).getMoblino(),
                            "Whatsapp").enqueue(new Callback<PaymentCollection_CSWModel>() {
                        @Override
                        public void onResponse(@NotNull Call<PaymentCollection_CSWModel> call,
                                               @NotNull Response<PaymentCollection_CSWModel> response) {
                           //  Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("Whatsapp", "onResponse: "+response.body().getMsg());

                        }

                        @Override
                        public void onFailure(@NotNull Call<PaymentCollection_CSWModel> call, @NotNull Throwable t) {

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
                            "Name: "+catAllEntryMWDS.get(position).getFname()+" "+catAllEntryMWDS.get(position).getLname()+"\n" +
                            "Category: "+catAllEntryMWDS.get(position).getCat_name()+"\n"+
                            "Employee Name: "+catAllEntryMWDS.get(position).getEmployee_name()+"\n" +
                            "Mobile: "+catAllEntryMWDS.get(position).getMoblino()+"\n"+
                            "WhatsApp Number: "+catAllEntryMWDS.get(position).getWhatsappno()+"\n"+
                            "Description: "+catAllEntryMWDS.get(position).getReason()+"\n"+
                            "Next Date: "+catAllEntryMWDS.get(position).getVdate());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(Intent.createChooser(i,"Share via"));

            }
        });


        holder.lin_MDY_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VisitRCActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                context.startActivity(i);
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        //textView.setText(currentDateandTime);
        //  Toast.makeText(context, "CurrentDate"+currentDateandTime, Toast.LENGTH_SHORT).show();
        Log.d("cDate", "onBindViewHolder: "+currentDateandTime);

        if(currentDateandTime.equals(catAllEntryMWDS.get(position).getVdate()) ){
               /* || catAllEntryMWDS.get(position).getVdate() == null){*/
            // Toast.makeText(context, "Date Match", Toast.LENGTH_SHORT).show();
            Log.d("DateMatch","DateMatch");
            sharedPreferences.edit().putInt("CurrentDateOrNull1",1).apply();
            Log.d("TAG", "onCreate: check_log_here 6" +catAllEntryMWDS.get(position).getFname()+" "+catAllEntryMWDS.get(position).getLname());

        }
        else{
            // Toast.makeText(context, "Date Not Match", Toast.LENGTH_SHORT).show();
            Log.d("DateNotMatch","Date Not Match");
            sharedPreferences.edit().putInt("CurrentDateOrNull1",0).apply();
            Log.d("TAG", "onCreate: check_log_here 7");

        }

    }

    @Override
    public int getItemCount() {
        return catAllEntryMWDS.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMonthDisplayName, txtMonthDisplayCustomer, txtMonthDisplayMobile, txtMonthDisplayEmpName,
                txtMonthDisplayWhatsAppNumber, txtMonthDisplayDescription, txtMonthDisplayNextDate,
                txtMonthDisplayFinalAmount;

        TextView lin_MDY_call, lin_MDY_sms, lin_MDY_whapp, lin_MDY_share, lin_MDY_visit,txtMonthDisplayVillage,
                txtMonthDisplayHotCold;

        LinearLayout Lin_monthlyDailyWeeklyDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtMonthDisplayName = itemView.findViewById(R.id.txtMonthDisplayName);
            this.txtMonthDisplayCustomer = itemView.findViewById(R.id.txtMonthDisplayCustomer);
            this.txtMonthDisplayMobile = itemView.findViewById(R.id.txtMonthDisplayMobile);
            this.txtMonthDisplayWhatsAppNumber = itemView.findViewById(R.id.txtMonthDisplayWhatsAppNumber);
            this.txtMonthDisplayEmpName = itemView.findViewById(R.id.txtMonthDisplayEmpName);

            this.txtMonthDisplayFinalAmount = itemView.findViewById(R.id.txtMonthDisplayFinalAmount);

            this.Lin_monthlyDailyWeeklyDetail = itemView.findViewById(R.id.Lin_monthlyDailyWeeklyDetail);
            this.txtMonthDisplayDescription = itemView.findViewById(R.id.txtMonthDisplayDescription);
            this.txtMonthDisplayNextDate = itemView.findViewById(R.id.txtMonthDisplayNextDate);
            this.txtMonthDisplayVillage = itemView.findViewById(R.id.txtMonthDisplayVillage);
            this.txtMonthDisplayHotCold = itemView.findViewById(R.id.txtMonthDisplayHotCold);

            this.lin_MDY_call = itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms = itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp = itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share = itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_visit = itemView.findViewById(R.id.lin_MDY_visit);

        }
    }



}
