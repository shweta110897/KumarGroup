package com.example.kumarGroup.ViewInquiryDealStage;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.DealstageAK.DealstageHistoryActivity;
import com.example.kumarGroup.Inquiry.AddImageViewimageActivity;
import com.example.kumarGroup.ModelNameProductModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.Village_List.VillageListShowAdapter;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_stage_call_sms_what_model;
import com.example.kumarGroup.model_msg;
import com.example.kumarGroup.myInqCswModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterShowButtonDataViewINQ extends RecyclerView.Adapter<AdapterShowButtonDataViewINQ.viewHolder> implements Filterable {

    Context context;
    List<Catnotattend> mcatlist;
    List<Catnotattend> mcatlist_one;
    ProgressDialog pro;

    SharePref sp;
    SharedPreferences sharedPreferences,sp1;
    Utils utils;
    Activity activity;

    String Mobilecall;


    String sms= "",emp;//The message you want to text to the phone

    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();

    String  message = "", modellistdata = "", sor_inqData = "";
    int total;

    String[] Products_List = {"Select Product", "New Tractor","Old Tractor","Implement"};
    List<String> ModelName = new ArrayList<>();
    List<String> ModelID = new ArrayList<>();
    String dealType1,ProductName1,model_id="",String_modelget;
    List<String> modelname_list=new ArrayList<>();


    public AdapterShowButtonDataViewINQ(Context context, Activity activity ,List<Catnotattend> mcatlist) {
        this.context = context;
        this.mcatlist = mcatlist;
        this.mcatlist_one = mcatlist;

        utils = new Utils(activity);
        sp = new SharePref(context);
        this.activity = activity;

        pro = new ProgressDialog(context);
        SharedPreferences sharedPreferencesS = context.getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        emp = sharedPreferencesS.getString("Slected_EMPID","");
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_visit,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (DealstageRecyclerActivityViewINQ.overandnew_negotiaion && "Dealer Meeting".equals(mcatlist.get(position).getInq_new()) || "Dealer Meeting".equals(mcatlist.get(position).getInq_overdue()))
        {
            // Log.d("TAG", "po "+position+" colroidnew "+mcatlist.get(position).getInq_new() +" overdue "+mcatlist.get(position).getInq_overdue());
            holder.newdesign_color.setBackgroundColor(Color.parseColor("#EF6C89"));
        }
        else {
            holder.newdesign_color.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        if (DealstageRecyclerActivityViewINQ.notattend_flag_sms_call_what) {
            holder.image_button.setVisibility(View.GONE);
        }

        if (DealstageRecyclerActivityViewINQ.firstmeeting_otpsend_flag){
            holder.design_deal.setVisibility(View.GONE);
            holder.txtdeal.setVisibility(View.GONE);
        }

        if (DealstageRecyclerActivityViewINQ.deal_deliveryInquiry_flag_showonly_attachbutton) {
            holder.remider.setVisibility(View.GONE);
            holder.design_deal.setVisibility(View.GONE);
            holder.txtremider.setVisibility(View.GONE);
            holder.txtdeal.setVisibility(View.GONE);
            holder.delivery_date.setVisibility(View.VISIBLE);
            holder.ringid.setVisibility(View.GONE);
            holder.added_days.setVisibility(View.GONE);
            holder.latactivity.setVisibility(View.GONE);
            holder.resentNote.setVisibility(View.GONE);
            holder.hot_cold_harm.setVisibility(View.GONE);
            holder.source_ofinquire.setVisibility(View.GONE);
            holder.model_added.setText(mcatlist.get(position).getModel_name());
        }
        else {
            holder.model_added.setText(mcatlist.get(position).getModel());
        }

//        holder.hot_cold_harm.setVisibility(View.GONE);
        holder.tv_name_show_rc_gv.setText("Name: "+mcatlist.get(position).getFname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+mcatlist.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+mcatlist.get(position).getMoblino());
         holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+mcatlist.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+mcatlist.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText(mcatlist.get(position).getDesc());
        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+mcatlist.get(position).getVdate());
        holder.added_days.setText("Added :"+mcatlist.get(position).getAdded()+" Days");
        holder.source_ofinquire.setText("Source of Inquire : "+mcatlist.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(mcatlist.get(position).getEmployee_name());
        holder.follow_type.setText(mcatlist.get(position).getFollow_up_type());
        holder.latactivity.setText("Last Activity : "+mcatlist.get(position).getFollow_up_type());
        holder.file_count.setText("+ Files ( "+mcatlist.get(position).getFile_count()+" )");
        if (mcatlist.get(position).getCurrent_stage_name()==null){
            holder.textview_current_stage.setText("");
        }else {
            holder.textview_current_stage.setText(mcatlist.get(position).getCurrent_stage_name().toString());
        }



        holder.newdesign_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DealstageRecyclerActivityViewINQ.deal_deliveryInquiry_flag_showonly_attachbutton) {
                    context.startActivity(new Intent(context,ScreenDeliveryActivityINQ.class)
                            .putExtra("id",mcatlist.get(position).getAutoid())
                            .putExtra("sname",mcatlist.get(position).getId()));
                }
            }
        });

        Log.d("TAG", "chckdskfsdf: "+position+" name is "+mcatlist.get(position).getEmployee_name());

        if (DealstageRecyclerActivityViewINQ.overandnew_followup || DealstageRecyclerActivityViewINQ.overandnew_negotiaion){
            holder.new_design.setVisibility(View.VISIBLE);

            //   Toast.makeText(context, "new "+mcatlist.get(position).getInq_new()+" overdue "+mcatlist.get(position).getInq_overdue(), Toast.LENGTH_SHORT).show();
            holder.new_design.setText(mcatlist.get(position).getInq_new());
            holder.new_design.setText(mcatlist.get(position).getInq_overdue());
        }

        if ("0".equals(mcatlist.get(position).getInq_new())){
            holder.new_design.setVisibility(View.VISIBLE);
            holder.new_design.setText("NEW");
        }

        if ("0".equals(mcatlist.get(position).getInq_overdue())){
            holder.new_design.setVisibility(View.VISIBLE);
            holder.new_design.setText("OVERDUE");
        }

        if ("HOT".equals(mcatlist.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.images_hot);
        }
        if ("WARM".equals(mcatlist.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.warm);
        }
        if ("COLD".equals(mcatlist.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.cold);
        }

        holder.addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddImageViewimageActivity.class)
                        .putExtra("sname",mcatlist.get(position).getId()));
            }
        });

        holder.homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DealstageHistoryActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   i.putExtra("sname",catAllEntryMWDS.get(position).getId());
                i.putExtra("sname",mcatlist.get(position).getId());
                context.startActivity(i);

            }
        });

        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + mcatlist.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, mcatlist.get(position).getAutoid());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = mcatlist.get(position).getMoblino();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();



                    WebService.getClient().getMyInqProfile_CSW(emp,
                            mcatlist.get(position).getAutoid(),
                            mcatlist.get(position).getMoblino(),
                            "Call"
                    ).enqueue(new Callback<myInqCswModel>() {
                        @Override
                        public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
                            // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("Call", "onResponse: notattend"+response.body().getMsg());
//                            WhatsappMessage(mcatlist.get(position).getWhatsappno());

                             message = "प्रिय किसान मित्र।\n" +
                                     "\n" +
                                     "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                                     "\n" +
                                     "नमस्ते...\n" +
                                     "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                                     "\n" +
                                     "अधिक जानकारी के लिए संपर्क करें।\n" +
                                     "सेल्स MO:- 7500567770\n" +
                                     "सर्विस MO:-7505786792";

                            Log.d("TAG", "onResponse: Whatsapp_message" + message);


                            context.startActivity(
                                    new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(
                                                    String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + mcatlist.get(position).getWhatsappno(), message)
                                            )
                                    )
                            );



                        }

                        @Override
                        public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {

                        }
                    });
                }
            }
        });

        if (mcatlist.get(position).getEmployee_name()!=null){
            holder.cust_name.setText(mcatlist.get(position).getEmployee_name());
        }else {
            holder.cust_name.setVisibility(View.GONE);
        }

        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.fromParts("sms", mcatlist.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);


                WebService.getClient().getMyInqProfile_CSW(emp,
                        mcatlist.get(position).getAutoid(),
                        mcatlist.get(position).getMoblino(),
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

        if (mcatlist.get(position).getVdate()!=null){
            holder.remind_date.setText(mcatlist.get(position).getVdate());
        }else {
            holder.remind_date.setVisibility(View.GONE);
        }

        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="
                            +"+91"+mcatlist.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    WebService.getClient().getMyInqProfile_CSW(emp,
                            mcatlist.get(position).getAutoid(),
                            mcatlist.get(position).getMoblino(),
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

        holder.remider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("overandnew_followup", String.valueOf(DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry_other));
                if (DealstageRecyclerActivityViewINQ1.deal_NegotiationandfinalInquiry_flag_other  ||DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry_other
                        ||DealstageRecyclerActivityViewINQ1.deal_dealer_Inquiry_other
                        ||DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry2_other
                        ||DealstageRecyclerActivityViewINQ1.deal_followup_Inquiry3_other){
                    Log.d("Jemin", "onCreateViewHolder: Reminder-1");


                    Intent i = new Intent(context, NegotiationDealActivityViewINQ_test.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("vemp", mcatlist.get(position).getVemp());
                    i.putExtra("cat_id", mcatlist.get(position).getCat_id());
                    i.putExtra("sname", mcatlist.get(position).getId());
                    i.putExtra("mobo", mcatlist.get(position).getMoblino());
                    i.putExtra("id", mcatlist.get(position).getAutoid());
                    i.putExtra("Whatsappnumber",mcatlist.get(position).getWhatsappno());
                    i.putExtra("cur_stage", mcatlist.get(position).getSor_of_inq().toString());
                    context.startActivity(i);
                }
                else {


                    Catnotattend catnotattend=mcatlist.get(position);
                    Intent i = new Intent(context, DealFormGeneralActivityViewINQ1.class);
                    Log.d("Jemin", "onCreateViewHolder: Reminder-2"+mcatlist.get(position).getPayment_type());
                    i.putExtra("Cat", String.valueOf(catnotattend));
                    i.putExtra("vemp", mcatlist.get(position).getVemp());
                    i.putExtra("cat_id", mcatlist.get(position).getCat_id());
                    i.putExtra("sname", mcatlist.get(position).getId());
                    i.putExtra("mobo", mcatlist.get(position).getMoblino());
                    i.putExtra("other_nmbr", mcatlist.get(position).getOther_mobile());
                    i.putExtra("passing_type", mcatlist.get(position).getPassing_type());
                    i.putExtra("payment_type", mcatlist.get(position).getPayment_type());
                    i.putExtra("id", mcatlist.get(position).getAutoid());
                    i.putExtra("login_emp",mcatlist.get(position).getLogin_emp());
                    i.putExtra("Whatsappnumber",mcatlist.get(position).getWhatsappno());
                    i.putExtra("fname",mcatlist.get(position).getFname());
                    i.putExtra("lname",mcatlist.get(position).getLname());
                    i.putExtra("cat_name", mcatlist.get(position).getCat_name());
                    i.putExtra("desc", mcatlist.get(position).getDesc());
                    i.putExtra("model_name", mcatlist.get(position).getModel());
                    i.putExtra("cur_stage", mcatlist.get(position).getSor_of_inq());

                    context.startActivity(i);
                    ((Activity)context).finish();

                }
            }
        });

        holder.design_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: CheckReminder");

                SharedPreferences preferences = context.getSharedPreferences("NextId1", MODE_PRIVATE);
                preferences.edit().remove("NextId1").commit();
                preferences.edit().remove("phase").commit();

                Intent i = new Intent(context, DealfirstMeetingActivityViewINQ.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("vemp",mcatlist.get(position).getVemp());
                i.putExtra("cat_id",mcatlist.get(position).getCat_id());
                i.putExtra("sname",mcatlist.get(position).getId());
                i.putExtra("mobo",mcatlist.get(position).getMoblino());
                i.putExtra("login_emp",mcatlist.get(position).getLogin_emp());
                i.putExtra("Whatsappnumber",mcatlist.get(position).getWhatsappno());

                context.startActivity(i);
            }
        });
        if (DealstageRecyclerActivityViewINQ.Not_attend_skip_check){
            holder.textview_Skip.setVisibility(View.VISIBLE);
        }
        holder.textview_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebService.getClient().deal_send_call_sms_what_web(mcatlist.get(position).getAutoid(),"Not Attend","SKIP").enqueue(new Callback<deal_stage_call_sms_what_model>() {
                    @Override
                    public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {
                        activity.finish();
//                        activity.startActivity(activity.getIntent());
                    }

                    @Override
                    public void onFailure(Call<deal_stage_call_sms_what_model> call, Throwable t) {

                    }
                });
            }
        });

//        holder.

    }

    @Override
    public int getItemCount() {
        return mcatlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView tv_name_show_rc_gv,cust_name,remind_date,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,tv_Whapp_Number_show_rc_gv,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,tv_HotCold_show_rc_gv
                ,added_days,model_added,source_ofinquire,employe_name_ink,follow_type,latactivity,new_design,file_count,
                txtremider,txtdeal,delivery_date,textview_current_stage,textview_Skip;

        LinearLayout lin_detailrc,image_button,ringid,resentNote;
        RelativeLayout LinShowDetailRcGv;
        ImageView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,remider,addfile,design_deal,hot_cold_harm,
                homeIcon;
        CardView newdesign_color;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            this.cust_name= itemView.findViewById(R.id.cust_name);
            this.remind_date= itemView.findViewById(R.id.remind_date);
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
            this.follow_type= itemView.findViewById(R.id.follow_type);
            this.latactivity= itemView.findViewById(R.id.latactivity);
            this.addfile= itemView.findViewById(R.id.addfile);
            this.design_deal= itemView.findViewById(R.id.design_deal);
            this.new_design= itemView.findViewById(R.id.new_design);
            this.hot_cold_harm= itemView.findViewById(R.id.hot_cold_harm);
            this.image_button= itemView.findViewById(R.id.image_button);
            this.homeIcon= itemView.findViewById(R.id.homeIcon);
            this.file_count= itemView.findViewById(R.id.file_count);
            this.newdesign_color= itemView.findViewById(R.id.newdesign_color);
            this.txtremider= itemView.findViewById(R.id.txtremider);
            this.txtdeal= itemView.findViewById(R.id.txtdeal);
            this.ringid= itemView.findViewById(R.id.ringid);
            this.resentNote= itemView.findViewById(R.id.resentNote);
            this.delivery_date= itemView.findViewById(R.id.delivery_date);
            this.textview_current_stage= itemView.findViewById(R.id.textview_current_stage);
            this.textview_Skip= itemView.findViewById(R.id.textview_Skip);
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
                    mcatlist = mcatlist_one;
                } else {

                    List<Catnotattend> filteredList = new ArrayList<>();

                    for (Catnotattend row : mcatlist_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        String strVAutoid =row.getAutoid();
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

                        if (VillageListShowAdapter.Village_List_AutoFill_Search_Check){
                            if (strFName.toLowerCase().contains(charString.toLowerCase())
                                    || strVAutoid.toLowerCase().contains(charString.toLowerCase())
                                // || strLName.toLowerCase().contains(charString.toLowerCase())

                            )
                            {
                                VillageListShowAdapter.Village_List_AutoFill_Search_Check = false;
                                Log.d("TAG", "performFiltering: Check_Search ");
                                filteredList.add(row);
                            }
                        }else {
                            if (strFName.toLowerCase().contains(charString.toLowerCase())
                                    || strMobile.toLowerCase().contains(charString.toLowerCase())
                                    || strWtaNumber.toLowerCase().contains(charString.toLowerCase())
                                    || strVName.toLowerCase().contains(charString.toLowerCase())
                                    || strVAutoid.toLowerCase().contains(charString.toLowerCase())
                                // || strLName.toLowerCase().contains(charString.toLowerCase())
                            )
                            {
                                Log.d("TAG", "performFiltering: Check_Search 123");

                                filteredList.add(row);
                            }
                        }
                    }
                    mcatlist = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mcatlist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mcatlist = (ArrayList<Catnotattend>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public Filter getFilter1() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    //  catShowRCGVS_one = catShowRCGVS;
                    // FilterdlistData = listData;
                    mcatlist = mcatlist_one;
                } else {

                    List<Catnotattend> filteredList = new ArrayList<>();

                    for (Catnotattend row : mcatlist_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        String strVAutoid =row.getAutoid();
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
                                || strVAutoid.toLowerCase().contains(charString.toLowerCase())
                            // || strLName.toLowerCase().contains(charString.toLowerCase())

                        )
                        {
                            Log.d("TAG", "performFiltering: Check_Search seco");
                            filteredList.add(row);
                        }

                    }
                    mcatlist = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mcatlist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mcatlist = (ArrayList<Catnotattend>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private void WhatsappMessage(String moblino) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_msg);


        Button btn_edit = dialog.findViewById(R.id.btn_edit);
        Button btn_msg = dialog.findViewById(R.id.btn_msg);

        btn_msg.setVisibility(View.GONE);

        Spinner sp_model_emp =dialog. findViewById(R.id.sp_model_emp);
        Spinner spn_AddPD_ProductName =dialog. findViewById(R.id.spn_AddPD_ProductName);

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,
                Products_List);
        spn_AddPD_ProductName.setAdapter(adapter);

        spn_AddPD_ProductName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductName1 = Products_List[position];
                Log.d("product", "onItemSelected: "+ProductName1);

                if (ProductName1!=null && !ProductName1.equals("Select Product")){

                    WebService.getClient().getModelName(ProductName1).enqueue(new Callback<ModelNameProductModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ModelNameProductModel> call, @NotNull Response<ModelNameProductModel> response)
                        {
                            if(response.isSuccessful()) {
                                if (response.body() != null) {

                                    if (response.body().getData().size() > 0) {
                                        modelname_list.clear();

                                        Log.d("product", response.body().toString());

                                        for (int i = 0; i < response.body().getData().size(); i++) {
                                            modelname_list.add(response.body().getData().get(i).getModel_name());
                                            ModelID.add(response.body().getData().get(i).getId());

                                        }

                                        Log.d("ProductS", "onResponse: " + response.body().getData().size());


                                        ArrayAdapter adapter2 = new ArrayAdapter(context,
                                                android.R.layout.simple_spinner_dropdown_item, modelname_list);
                                        sp_model_emp.setAdapter(adapter2);


                                        sp_model_emp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                                                model_id = ModelID.get(i);

                                                if ("Select Model ".equals(modelname_list.get(i))) {
                                                    String_modelget = "";
                                                } else {
                                                    String_modelget = modelname_list.get(i);
                                                }
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<ModelNameProductModel> call, @NotNull Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.show();
                pro.setCancelable(false);
                pro.setMessage("Please wait ...");
                if (DealstageRecyclerActivityViewINQ1.firstmeeting_otpsend_flag){
                    dealType1="First Metting";
                }else if (DealstageRecyclerActivityViewINQ1.hotInquiry_flag){
                    dealType1="Make An Offer(HOT)";
                }else if (DealstageRecyclerActivityViewINQ1.deal_warmInquiry_flag){
                    dealType1="Next Activity Plan(WARM)";
                }else if (DealstageRecyclerActivityViewINQ1.deal_coldInquiry_flag){
                    dealType1="Second Metting(COLD)";
                }else if (DealstageRecyclerActivityViewINQ1.deal_bookingInquiry_flag){
                    dealType1="Booking";
                }else if (DealstageRecyclerActivityViewINQ1.deal_deliveryInquiry_flag){
                    dealType1="Delivery";
                }

                WebService.getClient().dealstage_msg(dealType1).enqueue(new Callback<model_msg>() {
                    @Override
                    public void onResponse(Call<model_msg> call, Response<model_msg> response) {
                        pro.dismiss();

                        Log.e("respose",response.body().toString());

                        if (response.body().getData().size()==0){
                            Toast.makeText(context, "No Data Found !", Toast.LENGTH_SHORT).show();
                        }else {

                            message = "प्रिय किसान मित्र।\n" +
                                    "\n" +
                                    "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                                    "\n" +
                                    "नमस्ते...\n" +
                                    "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                                    "\n" +
                                    "अधिक जानकारी के लिए संपर्क करें।\n" +
                                    "सेल्स MO:- 7500567770\n" +
                                    "सर्विस MO:-7505786792\n"+
                                    "\n" +
                                    "\uD83D\uDC47 Useful Links \uD83D\uDC47\n" +
                                    "YouTube\n" +
                                    response.body().getData().get(0).getVideo_link1() +
                                    "\n" +
                                    "\n" +
                                    response.body().getData().get(0).getVideo_link2() +
                                    "\n" +
                                    "\n" +
                                    response.body().getData().get(0).getVideo_link3()
                            ;
                            Log.d("TAG", "onResponse: Whatsapp_message" + message);


                            context.startActivity(
                                    new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(
                                                    String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + moblino, message)
                                            )
                                    )
                            );

                        }


                        dialog.dismiss();




                    }

                    @Override
                    public void onFailure(Call<model_msg> call, Throwable t) {
                        pro.dismiss();
                        Utils.showErrorToast(context,t.getMessage());
                        dialog.dismiss();
                    }
                });

            }
        });
        dialog.show();
        
    }

}
