package com.example.kumarGroup.Village_List;

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


import com.example.kumarGroup.DealstageAK.DealstageHistoryActivity;
import com.example.kumarGroup.DealstageAK.DealstageRecyclerActivity;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.Inquiry.AddImageViewimageActivity;
import com.example.kumarGroup.Inquiry.FormGeneralActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInquiryDealStage.DealstageRecyclerActivityViewINQ;
import com.example.kumarGroup.VillageListShow;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.myInqCswModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillageListShowAdapter extends RecyclerView.Adapter<VillageListShowAdapter.ViewHolder> implements Filterable {

    Activity activity;
    Context context;
    Utils utils;
    List<com.example.kumarGroup.VillageListShow.Cat> Cat1;
    List<com.example.kumarGroup.VillageListShow.Cat> Cat1_one;

    String Mobilecall;
    String sms= "";
    String emp,click_id;
    SharedPreferences sp1;

    public static boolean Village_List_AutoFill_Search_Check = false;



    public VillageListShowAdapter(Activity activity,Context context,List<VillageListShow.Cat> Cat1) {
        this.activity = activity;
        this.context = context;
        this.Cat1 = Cat1;
        this.Cat1_one = Cat1;

        utils = new Utils(activity);

        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }

    @NonNull
    @Override
    public VillageListShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.village_list_show, parent, false);
        Log.d("Jemin", "onCreateViewHolder: GenerallnquiryAdapterCall");
        VillageListShowAdapter.ViewHolder viewHolder = new VillageListShowAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VillageListShowAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name_show_rc_gv.setText("Name: "+Cat1.get(position).getFname());//+""+catAllEntryMWDS.get(position).getLname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+Cat1.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+Cat1.get(position).getMoblino());
        // holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+catAllEntryMWDS.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+Cat1.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText(Cat1.get(position).getDesc());
        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+Cat1.get(position).getVdate());
        holder.added_days.setText("Added :"+Cat1.get(position).getAdded()+" Days");
        holder.model_added.setText(Cat1.get(position).getModel());
        holder.source_ofinquire.setText("Source of Inquire : "+Cat1.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(Cat1.get(position).getEmployee_name());
        holder.latactivity.setText("Last Activity : "+Cat1.get(position).getFollow_up_type());
        holder.follow_type.setText(Cat1.get(position).getFollow_up_type());

        holder.textview_current_stage.setText(Cat1.get(position).getDeal_stage().toString());

        if ("HOT".equals(Cat1.get(position).getInq_type())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.images_hot);
        }
        if ("WARM".equals(Cat1.get(position).getInq_type())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.warm);
        }
        if ("COLD".equals(Cat1.get(position).getInq_type())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.cold);
        }

        holder.remider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, FormGeneralActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("vemp",Cat1.get(position).getVemp());
                i.putExtra("cat_id",Cat1.get(position).getCat_id());
                i.putExtra("sname",Cat1.get(position).getId());

                context.startActivity(i);

            }
        });

        holder.addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddImageViewimageActivity.class)
                        .putExtra("sname",Cat1.get(position).getId()));
            }
        });

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

        holder.homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DealstageHistoryActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                i.putExtra("sname",Cat1.get(position).getId());
                context.startActivity(i);

            }
        });

        holder.LinShowDetailRcGv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Village_List_AutoFill_Search_Check = true;

                click_id = Cat1.get(position).getAutoid();

                if(Cat1.get(position).getCurrent_stage_name()==null && "".equals(Cat1.get(position).getInq_type())){
                    Toast.makeText(context.getApplicationContext(), "Deal Stage Is Null", Toast.LENGTH_SHORT).show();
                    Village_List_AutoFill_Search_Check = false;
                } else if (FoDashbord.Village_List_Show_Check && (Cat1.get(position).getCurrent_stage_name()!=null || !"".equals(Cat1.get(position).getInq_type()))){
                    Log.d("TAG", "onClick: MyInquire "+FoDashbord.Village_List_Show_Check);
                    if (Cat1.get(position).getCurrent_stage_name().equals("Not Attand")){
                        DealstageRecyclerActivity.notattend_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","Not attend").putExtra("click_id",click_id));


                    }else if (Cat1.get(position).getCurrent_stage_name().equals("First Metting")){
                        Log.d("TAG", "onClick: MyInquire FirstMeeting "+FoDashbord.Village_List_Show_Check);
                        DealstageRecyclerActivity.firstmeeting_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","first meeting").putExtra("click_id",click_id));

                    }else if (Cat1.get(position).getCurrent_stage_name().equals("Make An Offer(HOT)") || "HOT".equals(Cat1.get(position).getInq_type())){
                        DealstageRecyclerActivity.hotInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","Make An Offer").putExtra("click_id",click_id));

                    }else if (Cat1.get(position).getCurrent_stage_name().equals("Next Activity Plan(WARM)") || "WARM".equals(Cat1.get(position).getInq_type())){
                        Log.d("TAG", "onClick: Warm_Check "+ click_id);
                        DealstageRecyclerActivity.deal_nextactivityplanInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","Next Activity Plan").putExtra("click_id",click_id));

                    }else if (Cat1.get(position).getCurrent_stage_name().equals("Second Metting(COLD)") || "COLD".equals(Cat1.get(position).getInq_type())){
                        DealstageRecyclerActivity.deal_coldInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivity.class)
                                .putExtra("actionbar","Second meeting").putExtra("click_id",click_id));

                    }else {
                        Toast.makeText(context.getApplicationContext(), "Deal Stage Is Null", Toast.LENGTH_SHORT).show();
                        Village_List_AutoFill_Search_Check = false;
                    }
                } else if(FoDashbord.Village_List_Show_Check == false && (Cat1.get(position).getCurrent_stage_name()!=null || !"".equals(Cat1.get(position).getInq_type()))){
                    if (Cat1.get(position).getCurrent_stage_name().equals("Not Attand")){
                        Log.d("TAG", "onClick: Check_id"+click_id);
                        DealstageRecyclerActivityViewINQ.notattend_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ.class)
                                .putExtra("actionbar","Not attend").putExtra("click_id",click_id));

                    }else if (Cat1.get(position).getCurrent_stage_name().equals("First Metting")){
                        Log.d("TAG", "onClick: MyInquire FirstMeeting "+FoDashbord.Village_List_Show_Check);

                        DealstageRecyclerActivityViewINQ.firstmeeting_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ.class)
                                .putExtra("actionbar","first meeting").putExtra("click_id",click_id));

                    }else if (Cat1.get(position).getCurrent_stage_name().equals("Make An Offer(HOT)") || "HOT".equals(Cat1.get(position).getInq_type())){
                        DealstageRecyclerActivityViewINQ.hotInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ.class)
                                .putExtra("actionbar","Make An Offer").putExtra("click_id",click_id));


                    }else if (Cat1.get(position).getCurrent_stage_name().equals("Next Activity Plan(WARM)") || "WARM".equals(Cat1.get(position).getInq_type())){
                        Log.d("TAG", "onClick: Warm_Check "+ click_id);
                        DealstageRecyclerActivityViewINQ.deal_nextactivityplanInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ.class)
                                .putExtra("actionbar","Next Activity Plan").putExtra("click_id",click_id));

                    }else if (Cat1.get(position).getCurrent_stage_name().equals("Second Metting(COLD)") || "COLD".equals(Cat1.get(position).getInq_type())){
                        DealstageRecyclerActivityViewINQ.deal_coldInquiry_flag = true;
                        context.startActivity(new Intent(context,DealstageRecyclerActivityViewINQ.class)
                                .putExtra("actionbar","Second meeting").putExtra("click_id",click_id));

                    }else {
                        Toast.makeText(context.getApplicationContext(), "Deal Stage Is Null", Toast.LENGTH_SHORT).show();
                        Village_List_AutoFill_Search_Check = false;
                    }
                }else {
                    Toast.makeText(context.getApplicationContext(), "Deal Stage Is Null", Toast.LENGTH_SHORT).show();
                    Village_List_AutoFill_Search_Check = false;
                }
            }

        });


    }

    @Override
    public int getItemCount() {
        return Cat1.size();

    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                Log.d("TAG", "performFiltering: "+charString);

                if (charString.isEmpty()) {
                    //  catShowRCGVS_one = catShowRCGVS;
                    // FilterdlistData = listData;
                    Cat1 = Cat1_one;
                } else {

                    List<VillageListShow.Cat> filteredList = new ArrayList<>();

                    for (VillageListShow.Cat row : Cat1_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        //String strLName =row.getLname();
                        String strWtaNumber =row.getWhatsappno();
                        //   Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                       /* if(strLName == null)
                            strLName = " ";
*/
                        if(strWtaNumber == null)
                            strWtaNumber = " ";


                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                || strWtaNumber.toLowerCase().contains(charString.toLowerCase())
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                            // || strLName.toLowerCase().contains(charString.toLowerCase())
                        )
                        {
                            filteredList.add(row);
                        }
                    }
                    Cat1 = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = Cat1;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Cat1 = (List<VillageListShow.Cat>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
