package com.example.kumarGroup.ViewInquiry;

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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatMonthlyDataDisplayVI;
import com.example.kumarGroup.Inquiry.AddImageViewimageActivity;
import com.example.kumarGroup.Inquiry.DealDashboardActivity;
import com.example.kumarGroup.Inquiry.FormGeneralActivity;
import com.example.kumarGroup.Inquiry.VisitInquiryActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MonthlyViewInqTwoAdapter extends RecyclerView.Adapter<MonthlyViewInqTwoAdapter.ViewHolder>
    implements Filterable
{
    Context context;
    List<CatMonthlyDataDisplayVI> catMonthlyDataDisplayVIS;
    List<CatMonthlyDataDisplayVI> catMonthlyDataDisplayVIS_one;

    SharedPreferences sp1;
    String emp;

    private Activity activity;
    private Utils utils;
    private SharePref sp;

    String Mobilecall;

    String sms= "";//The message you want to text to the phone


    public MonthlyViewInqTwoAdapter(Context context, List<CatMonthlyDataDisplayVI> catMonthlyDataDisplayVIS) {
        this.context = context;
        this.catMonthlyDataDisplayVIS = catMonthlyDataDisplayVIS;
        this.catMonthlyDataDisplayVIS_one = catMonthlyDataDisplayVIS;

       // this.activity = activity;
        this.activity = (Activity) context;
        utils = new Utils(activity);
        sp =  new SharePref(activity);
        context = activity.getApplicationContext();

        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }

    @NonNull
    @Override
    public MonthlyViewInqTwoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.design_visit, parent, false);
        MonthlyViewInqTwoAdapter.ViewHolder viewHolder = new MonthlyViewInqTwoAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_name_show_rc_gv.setText("Name: "+catMonthlyDataDisplayVIS.get(position).getFname());//+""+catMonthlyDataDisplayVIS.get(position).getLname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+catMonthlyDataDisplayVIS.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+catMonthlyDataDisplayVIS.get(position).getMoblino());
         holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+catMonthlyDataDisplayVIS.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+catMonthlyDataDisplayVIS.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText(catMonthlyDataDisplayVIS.get(position).getDesc());
        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+catMonthlyDataDisplayVIS.get(position).getVdate());
        holder.added_days.setText("Added :"+catMonthlyDataDisplayVIS.get(position).getAdded()+" Days");
        holder.model_added.setText(catMonthlyDataDisplayVIS.get(position).getModel());
        holder.source_ofinquire.setText("Source of Inquire : "+catMonthlyDataDisplayVIS.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(catMonthlyDataDisplayVIS.get(position).getEmployee_name());
        holder.latactivity.setText("Last Activity : "+catMonthlyDataDisplayVIS.get(position).getFollow_up_type());
        holder.follow_type.setText(catMonthlyDataDisplayVIS.get(position).getFollow_up_type());

        if ("0".equals(catMonthlyDataDisplayVIS.get(position).getInq_new())){
            holder.new_design.setVisibility(View.VISIBLE);
            holder.new_design.setText("NEW");
        }

        if ("0".equals(catMonthlyDataDisplayVIS.get(position).getInq_overdue())){
            holder.new_design.setVisibility(View.VISIBLE);
            holder.new_design.setText("OVERDUE");
        }


        if ("HOT".equals(catMonthlyDataDisplayVIS.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.images_hot);
        }
        if ("WARM".equals(catMonthlyDataDisplayVIS.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.warm);
        }
        if ("COLD".equals(catMonthlyDataDisplayVIS.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.cold);
        }

        holder.remider.setVisibility(View.GONE);
        holder.design_deal.setVisibility(View.GONE);
        holder.txtremider.setVisibility(View.GONE);
        holder.txtdeal.setVisibility(View.GONE);
        holder.file_count.setText("Files (1)");

       /* LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                50,
                50
        );
        params.setMargins(0, 0, 0, 0);
        holder.addfile.setLayoutParams(params);*/


        holder.remider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, FormGeneralActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("vemp",catMonthlyDataDisplayVIS.get(position).getVemp());
                i.putExtra("cat_id",catMonthlyDataDisplayVIS.get(position).getCat_id());
                i.putExtra("sname",catMonthlyDataDisplayVIS.get(position).getId());

                context.startActivity(i);

            }
        });

        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catMonthlyDataDisplayVIS.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catMonthlyDataDisplayVIS.get(position).getMoblino());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = catMonthlyDataDisplayVIS.get(position).getMoblino();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();
                }
            }
        });

        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.fromParts("sms", catMonthlyDataDisplayVIS.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);


            }
        });

        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="
                            +"+91"+catMonthlyDataDisplayVIS.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

        holder.addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddImageViewimageActivity.class)
                        .putExtra("sname",catMonthlyDataDisplayVIS.get(position).getId()));
            }
        });

        holder.design_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DealDashboardActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("vemp",catMonthlyDataDisplayVIS.get(position).getVemp())
                        .putExtra("cat_id",catMonthlyDataDisplayVIS.get(position).getCat_id())
                        .putExtra("sname",catMonthlyDataDisplayVIS.get(position).getId()));
            }
        });


        holder.homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, VisitInquiryActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("sname",catMonthlyDataDisplayVIS.get(position).getId());
                i.putExtra("sname",catMonthlyDataDisplayVIS.get(position).getId());
                context.startActivity(i);

            }
        });
        
       /* holder.tv_fname_genVI.setText("Name: "+catMonthlyDataDisplayVIS.get(position).getFname());
        holder.tv_cat_name_genVI.setText("Category: "+catMonthlyDataDisplayVIS.get(position).getCat_name());
        holder.tv_mobile_no_genVI.setText("Mobile: "+catMonthlyDataDisplayVIS.get(position).getMoblino());
        holder.tv_Whapp_Number_genVI.setText("WhatsApp Number: "+catMonthlyDataDisplayVIS.get(position).getWhatsappno());

        holder.tv_state_genVI.setText("State: "+catMonthlyDataDisplayVIS.get(position).getState());

        holder.tv_city_genVI.setText("City: "+catMonthlyDataDisplayVIS.get(position).getCity());
        holder.tv_district_genVI.setText("District: "+catMonthlyDataDisplayVIS.get(position).getDistric());
        holder.tv_taluko_genVI.setText("Taluko: "+catMonthlyDataDisplayVIS.get(position).getTehsil());

        holder.tv_village_genVI.setText("Village: "+catMonthlyDataDisplayVIS.get(position).getVilage());
        holder.tv_EmpName_genVI.setText("Employee Name: "+catMonthlyDataDisplayVIS.get(position).getEmployee_name());
        holder.tv_Desc_genVI.setText("Description: "+catMonthlyDataDisplayVIS.get(position).getDesc());

        holder.tv_NextDate_genVI.setText("Next Date: "+catMonthlyDataDisplayVIS.get(position).getVdate());
        holder.tv_type_inq_genVI.setText("Type: "+catMonthlyDataDisplayVIS.get(position).getInq_type());

        holder.LinShowDetailGenVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MonthWeekDayFormVIActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Name",catMonthlyDataDisplayVIS.get(position).getFname());
                i.putExtra("Category",catMonthlyDataDisplayVIS.get(position).getCat_name());
                i.putExtra("cat_id",catMonthlyDataDisplayVIS.get(position).getCat_id());
                i.putExtra("sname",catMonthlyDataDisplayVIS.get(position).getId());
                i.putExtra("Vemp",catMonthlyDataDisplayVIS.get(position).getVemp());
                // i.putExtra("id",catInquiryGenDetails.get(position).getCat_id());
                context.startActivity(i);
            }
        });


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               *//* Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+catMonthlyDataDisplayVIS.get(position).getMoblino()));
                Log.d("call", "onClick: "+catMonthlyDataDisplayVIS.get(position).getMoblino());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*//*


                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catMonthlyDataDisplayVIS.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catMonthlyDataDisplayVIS.get(position).getCat_id());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = catMonthlyDataDisplayVIS.get(position).getMoblino();

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
                        catMonthlyDataDisplayVIS.get(position).getAutoid(),
                        catMonthlyDataDisplayVIS.get(position).getMoblino(),
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
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        catMonthlyDataDisplayVIS.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);


                WebService.getClient().getMyInqProfile_CSW(emp,
                        catMonthlyDataDisplayVIS.get(position).getAutoid(),
                        catMonthlyDataDisplayVIS.get(position).getMoblino(),
                        "Sms"
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


        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+
                            catMonthlyDataDisplayVIS.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                    WebService.getClient().getMyInqProfile_CSW(emp,
                            catMonthlyDataDisplayVIS.get(position).getAutoid(),
                            catMonthlyDataDisplayVIS.get(position).getMoblino(),
                            "Whatsapp"
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
                        "Customer Name: "+catMonthlyDataDisplayVIS.get(position).getFname()+"\n" +
                        "Mobile: "+catMonthlyDataDisplayVIS.get(position).getMoblino()+"\n"+
                        "Village: "+catMonthlyDataDisplayVIS.get(position).getVilage()+"\n"+
                        "Category: "+catMonthlyDataDisplayVIS.get(position).getCat_name());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });



        holder.lin_MDY_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VisitVIActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("sname",catMonthlyDataDisplayVIS.get(position).getId());
                context.startActivity(i);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return catMonthlyDataDisplayVIS.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        /*TextView tv_cat_name_genVI,tv_fname_genVI,tv_mobile_no_genVI,
                tv_Whapp_Number_genVI,tv_state_genVI,tv_city_genVI,tv_district_genVI,
                tv_taluko_genVI,tv_village_genVI,tv_EmpName_genVI,tv_Desc_genVI,tv_NextDate_genVI,
                tv_type_inq_genVI;

        LinearLayout LinShowDetailGenVI;

        TextView lin_MDY_call, lin_MDY_sms, lin_MDY_whapp, lin_MDY_share, lin_MDY_visit;
*/
        TextView tv_name_show_rc_gv,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,tv_Whapp_Number_show_rc_gv,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,txtremider,txtdeal,tv_HotCold_show_rc_gv,file_count
                ,added_days,model_added,source_ofinquire,employe_name_ink,latactivity,follow_type,new_design;

        LinearLayout lin_detailrc;
        RelativeLayout LinShowDetailRcGv;
        ImageView lin_MDY_share,lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,remider,hot_cold_harm,addfile,design_deal
                ,homeIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

          /*  this.tv_cat_name_genVI= itemView.findViewById(R.id.tv_cat_name_genVI);
            this.tv_fname_genVI= itemView.findViewById(R.id.tv_fname_genVI);
            this.tv_mobile_no_genVI= itemView.findViewById(R.id.tv_mobile_no_genVI);
            this.tv_Whapp_Number_genVI= itemView.findViewById(R.id.tv_Whapp_Number_genVI);
            this.tv_state_genVI= itemView.findViewById(R.id.tv_state_genVI);
            this.tv_city_genVI= itemView.findViewById(R.id.tv_city_genVI);

            this.tv_district_genVI= itemView.findViewById(R.id.tv_district_genVI);
            this.tv_taluko_genVI= itemView.findViewById(R.id.tv_taluko_genVI);
            this.tv_village_genVI= itemView.findViewById(R.id.tv_village_genVI);
            this.tv_EmpName_genVI= itemView.findViewById(R.id.tv_EmpName_genVI);
            this.tv_Desc_genVI= itemView.findViewById(R.id.tv_Desc_genVI);
            this.tv_NextDate_genVI= itemView.findViewById(R.id.tv_NextDate_genVI);
            this.tv_type_inq_genVI= itemView.findViewById(R.id.tv_type_inq_genVI);
            this.LinShowDetailGenVI= itemView.findViewById(R.id.LinShowDetailGenVI);

            this.lin_MDY_call = itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms = itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp = itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share = itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_visit = itemView.findViewById(R.id.lin_MDY_visit);*/

            this.tv_name_show_rc_gv= itemView.findViewById(R.id.tv_name_show_rc_gv);
            this.tv_category_it_show_rc_gv= itemView.findViewById(R.id.tv_category_it_show_rc_gv);
            this.tv_mobile_no_show_rc_gv= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv);
            this.tv_Whapp_Number_show_rc_gv= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv_other);
            this.tv_village_show_rc_gv= itemView.findViewById(R.id.tv_village_show_rc_gv);
            this.tv_district_show_rc_gv= itemView.findViewById(R.id.tv_district_show_rc_gv);
            this.txtremider= itemView.findViewById(R.id.txtremider);
            this.txtdeal= itemView.findViewById(R.id.txtdeal);
            this.file_count= itemView.findViewById(R.id.file_count);

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
                    catMonthlyDataDisplayVIS = catMonthlyDataDisplayVIS_one;
                } else {

                    List<CatMonthlyDataDisplayVI> filteredList = new ArrayList<>();

                    for (CatMonthlyDataDisplayVI row : catMonthlyDataDisplayVIS_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        String strModel =row.getModel();
                      //  String strType = (String) row.getInq_type();
                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strModel == null)
                            strModel = " ";


                     /*   if(strType == null)
                            strType = " ";*/

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                || strModel.toLowerCase().contains(charString.toLowerCase())
                                /* || row.getDistric().toLowerCase().contains(charString.toLowerCase())*/
                                || strVName.toLowerCase().contains(charString.toLowerCase()))
//                                || strType.toLowerCase().contains(charString.toLowerCase())
                        {
                            filteredList.add(row);
                        }
                    }
                    catMonthlyDataDisplayVIS = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = catMonthlyDataDisplayVIS;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                catMonthlyDataDisplayVIS = (ArrayList<CatMonthlyDataDisplayVI>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }
}
