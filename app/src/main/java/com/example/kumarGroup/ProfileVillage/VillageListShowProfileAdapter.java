package com.example.kumarGroup.ProfileVillage;

import static android.content.Context.MODE_PRIVATE;

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

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.VillageListShowProfile;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.myInqCswModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageListShowProfileAdapter extends RecyclerView.Adapter<VillageListShowProfileAdapter.ViewHolder>{

    Context context;
    List<VillageListShowProfile.Cat> Cat1;
    Activity activity;
    Utils utils;


    String Mobilecall;
    String emp;
    SharedPreferences sp1;
    String sms= "";


    public VillageListShowProfileAdapter(Activity activity,Context context,List<VillageListShowProfile.Cat> Cat1) {
        this.activity = activity;
        this.context = context;
        this.Cat1 = Cat1;

        utils = new Utils(activity);

        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

    }

    @NonNull
    @Override
    public VillageListShowProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.village_list_show, parent, false);
        Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall");
        VillageListShowProfileAdapter.ViewHolder viewHolder = new VillageListShowProfileAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VillageListShowProfileAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_name_show_rc_gv.setText("Name: "+Cat1.get(position).getFname());//+""+catAllEntryMWDS.get(position).getLname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+Cat1.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+Cat1.get(position).getMoblino());
        // holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+catAllEntryMWDS.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+Cat1.get(position).getVilage());
//        holder.tv_district_show_rc_gv.setText(Cat1.get(position).getDesc());
//        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+Cat1.get(position).getVdate());
//        holder.added_days.setText("Added :"+Cat1.get(position).getAdded()+" Days");
//        holder.model_added.setText(Cat1.get(position).getModel());
//        holder.source_ofinquire.setText("Source of Inquire : "+Cat1.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(Cat1.get(position).getEmployee_name());
//        holder.latactivity.setText("Last Activity : "+Cat1.get(position).getFollow_up_type());
//        holder.follow_type.setText(Cat1.get(position).getFollow_up_type());
//        holder.textview_current_stage.setText(Cat1.get(position).getCurrent_stage_name());

//        if ("HOT".equals(Cat1.get(position).getInq_type())){
//            holder.hot_cold_harm.setBackgroundResource(R.drawable.images_hot);
//        }
//        if ("WARM".equals(Cat1.get(position).getInq_type())){
//            holder.hot_cold_harm.setBackgroundResource(R.drawable.warm);
//        }
//        if ("COLD".equals(Cat1.get(position).getInq_type())){
//            holder.hot_cold_harm.setBackgroundResource(R.drawable.cold);
//        }
        holder.homeIcon.setVisibility(View.GONE);

        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catAllEntryMWDS.get(position).getMoblino());
//                sp.putSharedPref(sp.CALL_ID, catAllEntryMWDS.get(position).getMoblino());
//                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = Cat1.get(position).getMoblino();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();

                    WebService.getClient().getMyInqProfile_CSW(emp,
                            Cat1.get(position).getAutoid(),
                            Cat1.get(position).getMoblino(),
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

        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="
                            +"+91"+Cat1.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    WebService.getClient().getMyInqProfile_CSW(emp,
                            Cat1.get(position).getAutoid(),
                            Cat1.get(position).getMoblino(),
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

        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.fromParts("sms", Cat1.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);

                WebService.getClient().getMyInqProfile_CSW(emp,
                        Cat1.get(position).getAutoid(),
                        Cat1.get(position).getMoblino(),
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

//        holder.homeIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(context, DealstageHistoryActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //   i.putExtra("sname",catAllEntryMWDS.get(position).getId());
//                i.putExtra("sname",Cat1.get(position).getId());
//                context.startActivity(i);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return Cat1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name_show_rc_gv,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,tv_Whapp_Number_show_rc_gv,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,tv_HotCold_show_rc_gv
                ,added_days,model_added,source_ofinquire,employe_name_ink,latactivity,follow_type,new_design,textview_current_stage;

        LinearLayout lin_detailrc;
        RelativeLayout LinShowDetailRcGv;
        ImageView lin_MDY_share,lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,remider,hot_cold_harm,addfile,design_deal
                ,homeIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name_show_rc_gv= itemView.findViewById(R.id.tv_name_show_rc_gv);
            this.tv_category_it_show_rc_gv= itemView.findViewById(R.id.tv_category_it_show_rc_gv);
            this.tv_mobile_no_show_rc_gv= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv);
//            this.tv_Whapp_Number_show_rc_gv= itemView.findViewById(R.id.tv_Whapp_Number_show_rc_gv);
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
            this.textview_current_stage= itemView.findViewById(R.id.textview_current_stage);
        }
    }
}
