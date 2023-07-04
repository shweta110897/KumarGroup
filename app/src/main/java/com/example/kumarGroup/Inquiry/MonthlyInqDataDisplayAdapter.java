package com.example.kumarGroup.Inquiry;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatInq;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.myInqCswModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MonthlyInqDataDisplayAdapter extends RecyclerView.Adapter<MonthlyInqDataDisplayAdapter.ViewHolder>
{
    Context context;
    List<CatInq> catAllEntryMWDS;
    SharePref sp;
    SharedPreferences sharedPreferences,sp1;
    Utils utils;
    Activity activity;

    String Mobilecall;
    String emp;

    String sms= "";//The message you want to text to the phone

    public MonthlyInqDataDisplayAdapter(Context context, Activity activity,List<CatInq> catAllEntryMWDS) {
        this.context = context;
        this.activity = activity;
        this.catAllEntryMWDS = catAllEntryMWDS;


        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

        sharedPreferences = activity.getSharedPreferences("DateCurrent2_inq", MODE_PRIVATE);


        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }

    @NonNull
    @Override
    public MonthlyInqDataDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.design_visit, parent, false);
        MonthlyInqDataDisplayAdapter.ViewHolder viewHolder = new MonthlyInqDataDisplayAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyInqDataDisplayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_name_show_rc_gv.setText("Name: "+catAllEntryMWDS.get(position).getFname());//+""+catAllEntryMWDS.get(position).getLname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+catAllEntryMWDS.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+catAllEntryMWDS.get(position).getMoblino());
         holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+catAllEntryMWDS.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+catAllEntryMWDS.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText(catAllEntryMWDS.get(position).getDesc());
        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+catAllEntryMWDS.get(position).getVdate());
        holder.added_days.setText("Added :"+catAllEntryMWDS.get(position).getAdded()+" Days");
        holder.model_added.setText(catAllEntryMWDS.get(position).getModel());
        holder.source_ofinquire.setText("Source of Inquire : "+catAllEntryMWDS.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(catAllEntryMWDS.get(position).getEmployee_name());
        holder.latactivity.setText("Last Activity : "+catAllEntryMWDS.get(position).getFollow_up_type());
        holder.follow_type.setText(catAllEntryMWDS.get(position).getFollow_up_type());

        if ("0".equals(catAllEntryMWDS.get(position).getInq_new())){
            holder.new_design.setVisibility(View.VISIBLE);
            holder.new_design.setText("NEW");
        }

        if ("0".equals(catAllEntryMWDS.get(position).getInq_overdue())){
            holder.new_design.setVisibility(View.VISIBLE);
            holder.new_design.setText("OVERDUE");
        }

        if ("HOT".equals(catAllEntryMWDS.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.images_hot);
        }
        if ("WARM".equals(catAllEntryMWDS.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.warm);
        }
        if ("COLD".equals(catAllEntryMWDS.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.cold);
        }


        holder.remider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, FormGeneralActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("vemp",catAllEntryMWDS.get(position).getVemp());
                i.putExtra("cat_id",catAllEntryMWDS.get(position).getCat_id());
                i.putExtra("sname",catAllEntryMWDS.get(position).getId());

                context.startActivity(i);

            }
        });

        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catAllEntryMWDS.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catAllEntryMWDS.get(position).getMoblino());
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

                    WebService.getClient().getMyInqProfile_CSW(emp,
                            catAllEntryMWDS.get(position).getAutoid(),
                            catAllEntryMWDS.get(position).getMoblino(),
                            "Call"
                    ).enqueue(new Callback<myInqCswModel>() {
                        @Override
                        public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
                            // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("Call", "onResponse: "+response.body().getMsg());
                        }

                        @Override
                        public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {

                        }
                    });
                }
            }
        });

        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.fromParts("sms", catAllEntryMWDS.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);

                WebService.getClient().getMyInqProfile_CSW(emp,
                        catAllEntryMWDS.get(position).getAutoid(),
                        catAllEntryMWDS.get(position).getMoblino(),
                        "Call"
                ).enqueue(new Callback<myInqCswModel>() {
                    @Override
                    public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
                        // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.d("Call", "onResponse: "+response.body().getMsg());
                    }

                    @Override
                    public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {

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
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="
                            +"+91"+catAllEntryMWDS.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    WebService.getClient().getMyInqProfile_CSW(emp,
                            catAllEntryMWDS.get(position).getAutoid(),
                            catAllEntryMWDS.get(position).getMoblino(),
                            "Call"
                    ).enqueue(new Callback<myInqCswModel>() {
                        @Override
                        public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
                            // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("Call", "onResponse: "+response.body().getMsg());
                        }

                        @Override
                        public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {

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

        holder.addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,AddImageViewimageActivity.class)
                        .putExtra("sname",catAllEntryMWDS.get(position).getId()));
            }
        });

        holder.design_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,DealDashboardActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("vemp",catAllEntryMWDS.get(position).getVemp())
                        .putExtra("cat_id",catAllEntryMWDS.get(position).getCat_id())
                        .putExtra("sname",catAllEntryMWDS.get(position).getId()));
            }
        });

        holder.homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, VisitInquiryActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                context.startActivity(i);

            }
        });

        /*holder.txtMonthDisplayName.setText("Name: "+catAllEntryMWDS.get(position).getFname());
        holder.txtMonthDisplayCustomer.setText("Category: "+catAllEntryMWDS.get(position).getCat_name());
        holder.txtMonthDisplayEmpName.setText("Employee Name: "+catAllEntryMWDS.get(position).getEmployee_name());
        holder.txtMonthDisplayMobile.setText("Mobile: "+catAllEntryMWDS.get(position).getMoblino());
        holder.txtMonthDisplayWhatsAppNumber.setText("WhatsApp Number: "+catAllEntryMWDS.get(position).getWhatsappno());
        holder.txtMonthDisplayDescription.setText("Description: "+catAllEntryMWDS.get(position).getReason());
        holder.txtMonthDisplayNextDate.setText("Next Date: "+catAllEntryMWDS.get(position).getVdate());
        holder.txtMonthDisplayVillage.setText("Village: "+catAllEntryMWDS.get(position).getVilage());
        holder.txtMonthDisplayHotCold.setText("Type: "+catAllEntryMWDS.get(position).getInq_type());
       *//* holder.txtMonthDisplayFinalAmount.setText("Final Amount: "+catAllEntryMWDS.get(position).getFinal_amt());*//*

        holder.txtMonthDisplayFinalAmount.setVisibility(View.GONE);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());

        //  Toast.makeText(context, "CurrentDate"+currentDateandTime, Toast.LENGTH_SHORT).show();
        Log.d("cDate", "onBindViewHolder: "+currentDateandTime);


        if(currentDateandTime.equals(catAllEntryMWDS.get(position).getVdate())){
            *//* || catAllEntryMWDS.get(position).getVdate() == null){*//*
            // Toast.makeText(context, "Date Match", Toast.LENGTH_SHORT).show();
            Log.d("DateMatch","DateMatch"+catAllEntryMWDS.get(position).getVdate());
            sharedPreferences.edit().putInt("CurrentDateOrNull1_inq",1).apply();
        }
        else{
            //   Toast.makeText(context, "Date Not Match", Toast.LENGTH_SHORT).show();
            Log.d("DateNotMatch","Date Not Match"+catAllEntryMWDS.get(position).getVdate());
            sharedPreferences.edit().putInt("CurrentDateOrNull1_inq",0).apply();
        }

        holder.Lin_monthlyDailyWeeklyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MonthDayWeekFormActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("customer_name",catAllEntryMWDS.get(position).getFname());
                i.putExtra("category",catAllEntryMWDS.get(position).getCat_name());
                i.putExtra("catId",catAllEntryMWDS.get(position).getCat_id());
                i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                i.putExtra("id",catAllEntryMWDS.get(position).getAutoid());
                i.putExtra("Vemp",catAllEntryMWDS.get(position).getVemp());
                context.startActivity(i);
            }
        });


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               *//* Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+catAllEntryMWDS.get(position).getMoblino()));
                Log.d("call", "onClick: "+catAllEntryMWDS.get(position).getMoblino());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*//*


                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catAllEntryMWDS.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catAllEntryMWDS.get(position).getMoblino());
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



                WebService.getClient().getMyInqProfile_CSW(emp,
                        catAllEntryMWDS.get(position).getAutoid(),
                        catAllEntryMWDS.get(position).getMoblino(),
                        "Call"
                ).enqueue(new Callback<myInqCswModel>() {
                    @Override
                    public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
                        //Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.d("Call", "onResponse: "+response.body().getMsg());

                    }

                    @Override
                    public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {

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

                WebService.getClient().getMyInqProfile_CSW(emp,
                        catAllEntryMWDS.get(position).getAutoid(),
                        catAllEntryMWDS.get(position).getMoblino(),
                        "Sms"
                ).enqueue(new Callback<myInqCswModel>() {
                    @Override
                    public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
                       // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Log.d("Call", "onResponse: "+response.body().getMsg());

                    }

                    @Override
                    public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {

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

                    WebService.getClient().getMyInqProfile_CSW(emp,
                            catAllEntryMWDS.get(position).getAutoid(),
                            catAllEntryMWDS.get(position).getMoblino(),
                            "Whatsapp"
                    ).enqueue(new Callback<myInqCswModel>() {
                        @Override
                        public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
                           // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("Call", "onResponse: "+response.body().getMsg());

                        }

                        @Override
                        public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {

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

        holder.lin_MDY_share.setVisibility(View.GONE);

        holder.lin_MDY_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Name: "+catAllEntryMWDS.get(position).getFname()+"\n" +
                        "Category: "+catAllEntryMWDS.get(position).getCat_name()+"\n"+
                        "Employee Name: "+catAllEntryMWDS.get(position).getEmployee_name()+"\n" +
                        "Mobile: "+catAllEntryMWDS.get(position).getMoblino()+"\n"+
                        "WhatsApp Number: "+catAllEntryMWDS.get(position).getWhatsappno()+"\n"+
                        "Description: "+catAllEntryMWDS.get(position).getReason()+"\n"+
                        "Next Date: "+catAllEntryMWDS.get(position).getVdate());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });

        holder.lin_MDY_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VisitInquiryActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                context.startActivity(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return catAllEntryMWDS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_name_show_rc_gv,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,tv_Whapp_Number_show_rc_gv,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,tv_HotCold_show_rc_gv
                ,added_days,model_added,source_ofinquire,employe_name_ink,latactivity,follow_type,new_design;

        LinearLayout lin_detailrc;
        RelativeLayout LinShowDetailRcGv;
        ImageView lin_MDY_share,lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,remider,hot_cold_harm,addfile,design_deal,homeIcon;

        /*TextView txtMonthDisplayName,txtMonthDisplayCustomer,txtMonthDisplayMobile,txtMonthDisplayEmpName,
                txtMonthDisplayWhatsAppNumber,txtMonthDisplayDescription,txtMonthDisplayNextDate,
                txtMonthDisplayFinalAmount;

        TextView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,lin_MDY_share,lin_MDY_visit,txtMonthDisplayVillage,
                txtMonthDisplayHotCold;

        LinearLayout Lin_monthlyDailyWeeklyDetail;*/

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            this.tv_name_show_rc_gv= itemView.findViewById(R.id.tv_name_show_rc_gv);
            this.tv_category_it_show_rc_gv= itemView.findViewById(R.id.tv_category_it_show_rc_gv);
            this.tv_mobile_no_show_rc_gv= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv);
            this.tv_Whapp_Number_show_rc_gv= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv_other);
            this.tv_village_show_rc_gv= itemView.findViewById(R.id.tv_village_show_rc_gv);
            this.tv_district_show_rc_gv= itemView.findViewById(R.id.tv_district_show_rc_gv);

            this.tv_NextDate_show_rc_gv= itemView.findViewById(R.id.tv_NextDate_show_rc_gv);
            this.tv_HotCold_show_rc_gv= itemView.findViewById(R.id.tv_HotCold_show_rc_gv);

            this.LinShowDetailRcGv= itemView.findViewById(R.id.LinShowDetailRcGv);
            this.lin_detailrc= itemView.findViewById(R.id.lin_detailrc);

            //   this.lin_MDY_share= itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_call= itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms= itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp= itemView.findViewById(R.id.lin_MDY_whapp);
            this.added_days= itemView.findViewById(R.id.added_days);
            this.model_added= itemView.findViewById(R.id.model_added);
            this.source_ofinquire= itemView.findViewById(R.id.source_ofinquire);
            this.employe_name_ink= itemView.findViewById(R.id.employe_name_ink);
            this.remider= itemView.findViewById(R.id.remider);
            this.latactivity= itemView.findViewById(R.id.latactivity);
            this.follow_type= itemView.findViewById(R.id.follow_type);
            this.hot_cold_harm= itemView.findViewById(R.id.hot_cold_harm);
            this.addfile= itemView.findViewById(R.id.addfile);
            this.design_deal= itemView.findViewById(R.id.design_deal);
            this.homeIcon= itemView.findViewById(R.id.homeIcon);
            this.new_design= itemView.findViewById(R.id.new_design);


       /*     this.txtMonthDisplayName=itemView.findViewById(R.id.txtMonthDisplayName);
            this.txtMonthDisplayCustomer=itemView.findViewById(R.id.txtMonthDisplayCustomer);
            this.txtMonthDisplayMobile=itemView.findViewById(R.id.txtMonthDisplayMobile);
            this.txtMonthDisplayWhatsAppNumber=itemView.findViewById(R.id.txtMonthDisplayWhatsAppNumber);
            this.txtMonthDisplayEmpName=itemView.findViewById(R.id.txtMonthDisplayEmpName );

            this.txtMonthDisplayFinalAmount=itemView.findViewById(R.id.txtMonthDisplayFinalAmount );

            this.Lin_monthlyDailyWeeklyDetail=itemView.findViewById(R.id.Lin_monthlyDailyWeeklyDetail);
            this.txtMonthDisplayDescription=itemView.findViewById(R.id.txtMonthDisplayDescription);
            this.txtMonthDisplayNextDate=itemView.findViewById(R.id.txtMonthDisplayNextDate);
            this.txtMonthDisplayVillage=itemView.findViewById(R.id.txtMonthDisplayVillage);
            this.txtMonthDisplayHotCold=itemView.findViewById(R.id.txtMonthDisplayHotCold);

            this.lin_MDY_call=itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms=itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp=itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share=itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_visit=itemView.findViewById(R.id.lin_MDY_visit);
*/
        }
    }
}
