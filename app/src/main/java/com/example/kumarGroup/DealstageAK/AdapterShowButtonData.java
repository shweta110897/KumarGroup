package com.example.kumarGroup.DealstageAK;

import static android.content.Context.MODE_PRIVATE;

import static com.example.kumarGroup.DealstageAK.DealstageRecyclerActivity.deal_sell_lost_Inquiry_flag_other;
import static com.example.kumarGroup.Utils.ConvertStringTodate;
import static com.example.kumarGroup.Utils.printDifference;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.Inquiry.AddImageViewimageActivity;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_stage_call_sms_what_model;
import com.example.kumarGroup.myInqCswModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterShowButtonData extends RecyclerView.Adapter<AdapterShowButtonData.viewHolder> implements Filterable
{
    Context context;
    List<Catnotattend> mcatlist;
    List<Catnotattend> mcatlist_one;
    List<Notes_POJO.Cat> notList;

    ProgressDialog pro;
    List<String> modelname_list;
    SharePref sp;
    SharedPreferences sp1;
    Utils utils;
    Activity activity;

    String Mobilecall;

    String sms= "",emp;//The message you want to text to the phone

    private RecyclerViewItemInterface1 viewItemInterface;

    public void setViewItemInterface(RecyclerViewItemInterface1 viewItemInterface) {
        this.viewItemInterface = viewItemInterface;
    }

    public AdapterShowButtonData(Context context, Activity activity ,List<Catnotattend> mcatlist) {
        this.context = context;
        this.mcatlist = mcatlist;
        this.mcatlist_one = mcatlist;

        utils = new Utils(activity);
        sp = new SharePref(context);
        this.activity = activity;

        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        pro = new ProgressDialog(context);
        modelname_list=new ArrayList<>();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_visit,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (DealstageRecyclerActivity.overandnew_negotiaion && "Dealer Meeting".equals(mcatlist.get(position).getInq_new()) || "Dealer Meeting".equals(mcatlist.get(position).getInq_overdue()))
        {

            if (DealstageRecyclerActivity.overandnew_followup || DealstageRecyclerActivity.overandnew_negotiaion){
                holder.new_design.setVisibility(View.VISIBLE);

//            if (mcatlist.get(position).getInq_new().equals("Dealer meeting"))

                //   Toast.makeText(context, "new "+mcatlist.get(position).getInq_new()+" overdue "+mcatlist.get(position).getInq_overdue(), Toast.LENGTH_SHORT).show();
                holder.new_design.setText(mcatlist.get(position).getInq_new());
                holder.new_design.setText(mcatlist.get(position).getInq_overdue());
            }
        }
        else {
            holder.new_design.setVisibility(View.GONE);
        }

        if (DealstageRecyclerActivity.deal_sell_lost_Inquiry_flag  || DealstageRecyclerActivity.deal_dropInquiry_flag){
            holder.iv_edit.setVisibility(View.GONE);

        }

        if (deal_sell_lost_Inquiry_flag_other){
            holder.design_deal.setVisibility(View.GONE);
            holder.txtdeal.setVisibility(View.GONE);
            holder.remider.setVisibility(View.GONE);
            holder.txtremider.setVisibility(View.GONE);
        }

        if (DealstageRecyclerActivity.notattend_flag_sms_call_what) {
            holder.image_button.setVisibility(View.GONE);
        }
        if (DealstageRecyclerActivity.firstmeeting_otpsend_flag){
            holder.design_deal.setVisibility(View.GONE);
            holder.txtdeal.setVisibility(View.GONE);
            holder.ll_paymentType.setVisibility(View.VISIBLE);
            holder.ll_passing.setVisibility(View.VISIBLE);
        }

        if (DealstageRecyclerActivity.deal_deliveryInquiry_flag_showonly_attachbutton) {
            holder.remider.setVisibility(View.GONE);
            holder.design_deal.setVisibility(View.GONE);
            holder.txtremider.setVisibility(View.GONE);
            holder.txtdeal.setVisibility(View.GONE);
            holder.delivery_date.setVisibility(View.VISIBLE);
            holder.ringid.setVisibility(View.GONE);
            holder.ringid1.setVisibility(View.GONE);
            holder.added_days.setVisibility(View.GONE);
            holder.latactivity.setVisibility(View.GONE);
            holder.resentNote.setVisibility(View.GONE);
            holder.hot_cold_harm.setVisibility(View.GONE);
            holder.source_ofinquire.setVisibility(View.GONE);
            holder.model_added.setText(mcatlist.get(position).getModel_name());
        }

        if (DealstageRecyclerActivity.deal_overDue_flag_other){
            holder.remider.setVisibility(View.GONE);
            holder.txtremider.setVisibility(View.GONE);
            holder.design_deal.setVisibility(View.GONE);
            holder.txtdeal.setVisibility(View.GONE);

        } else {
            holder.model_added.setText(mcatlist.get(position).getModel());
        }

        if (/*!mcatlist.get(position).getCurrent_stage_name().equals("") ||*/mcatlist.get(position).getCurrent_stage_name()!=null){
            Log.d("{tag}", "onBindViewHolder: current_dealstage:--"+mcatlist.get(position).getCurrent_stage_name());
            holder.textview_current_stage.setText(mcatlist.get(position).getCurrent_stage_name().toString());

        }else {
            Log.d("{tag}", "onBindViewHolder: current_dealstage:--");
            holder.textview_current_stage.setText("");
        }



//        holder.hot_cold_harm.setVisibility(View.GONE);
        holder.delivery_date.setText("Delivery Date"+mcatlist.get(position).getAdd_date());
//       / holder.tv_name_show_rc_gv.setText("Name: "+mcatlist.get(position).getFname());
        holder.tv_name_show_rc_gv.setText("Name: "+mcatlist.get(position).getFname()+" "+mcatlist.get(position).getLname());

        if (mcatlist.get(position).getEmployee_name()!=null){
            holder.cust_name.setText(mcatlist.get(position).getEmployee_name());
        }else {
            holder.cust_name.setVisibility(View.GONE);
        }
        holder.tv_category_it_show_rc_gv.setText("Category: "+mcatlist.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+mcatlist.get(position).getMoblino());
        holder.tv_mobile_no_show_rc_gv_other.setText("Ohter Mobile: "+mcatlist.get(position).getWhatsappno());
//         holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+mcatlist.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+mcatlist.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText(mcatlist.get(position).getDesc());
        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+mcatlist.get(position).getVdate());
        if (mcatlist.get(position).getVdate()!=null){
            holder.remind_date.setText(mcatlist.get(position).getVdate());
        }else {
            holder.remind_date.setVisibility(View.GONE);
        }
        holder.added_days.setText("Added :"+mcatlist.get(position).getAdded()+" Days");

        holder.source_ofinquire.setText("Source of Inquire : "+mcatlist.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(mcatlist.get(position).getEmployee_name());
        holder.follow_type.setText(mcatlist.get(position).getFollow_up_type());
        holder.latactivity.setText("Last Activity : "+mcatlist.get(position).getFollow_up_type());
        holder.file_count.setText("+ Files ( "+mcatlist.get(position).getFile_count()+" )");

        holder.payment_type.setText("Payment Type: "+mcatlist.get(position).getPayment_type()/*"Loan"*/);
        /*passing_type*/
            holder.passing_type.setText(mcatlist.get(position).getPassing_type()/*"Aggriculture"*/);
            if ( holder.passing_type.getText().toString().equals("Agriculture")){
                holder.passing_type.setTextColor(context.getResources().getColor(R.color.Green));
            }else {
                holder.passing_type.setTextColor(context.getResources().getColor(R.color.Yellow));
            }

        if (DealstageRecyclerActivity.deal_overDue_flag_other){
            holder.remider.setVisibility(View.GONE);
            holder.txtremider.setVisibility(View.GONE);
            holder.design_deal.setVisibility(View.GONE);
            holder.txtdeal.setVisibility(View.GONE);

        }
        holder.newdesign_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DealstageRecyclerActivity.deal_deliveryInquiry_flag_showonly_attachbutton) {
                   context.startActivity(new Intent(context,ScreenDeliveryActivity.class)
                   .putExtra("id",mcatlist.get(position).getAutoid())
                   .putExtra("sname",mcatlist.get(position).getId()));
                }
            }
        });

        if (mcatlist.get(position).getDeal_type()==null  || mcatlist.get(position).getDeal_type().equals("")){
            Log.d("iff==>>","getDeal_type");
        }else{
            Log.d("elsee==>>","getDeal_type");
            holder.Deal_type.setText("DealType : " + mcatlist.get(position).getDeal_type());
        }

//        if (mcatlist.get(position).getDeal_type()!=null || !mcatlist.get(position).getDeal_type().equals("")) {
//            holder.Deal_type.setText("DealType : " + mcatlist.get(position).getDeal_type());
//        }

        if ("Exchange".equals(mcatlist.get(position).getDeal_type())){
            holder.ringid1.setVisibility(View.VISIBLE);
        }else{
            holder.ringid1.setVisibility(View.GONE);
        }

        if (DealstageRecyclerActivity.overandnew_followup || DealstageRecyclerActivity.overandnew_negotiaion){
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

//                if (DealstageRecyclerActivity.deal_NegotiationandfinalInquiry_flag_other){

                if (DealstageRecyclerActivity.deal_NegotiationandfinalInquiry_flag_other  ||DealstageRecyclerActivity.deal_followup_Inquiry_other
                        ||DealstageRecyclerActivity.deal_dealer_Inquiry_other
                        ||DealstageRecyclerActivity.deal_followup_Inquiry2_other
                        ||DealstageRecyclerActivity.deal_followup_Inquiry3_other){

                    Intent i = new Intent(context, NegotiationDealActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("vemp", mcatlist.get(position).getVemp());

                    i.putExtra("cat_id", mcatlist.get(position).getCat_id());
                    i.putExtra("cat_name", mcatlist.get(position).getCat_name());
                    i.putExtra("sname", mcatlist.get(position).getId());
                    i.putExtra("mobo", mcatlist.get(position).getMoblino());
                    i.putExtra("id", mcatlist.get(position).getAutoid());
                    i.putExtra("Whatsappnumber", mcatlist.get(position).getWhatsappno());
                    //Toast.makeText(context, ""+mcatlist.get(position).getAutoid(), Toast.LENGTH_SHORT).show();
                    context.startActivity(i);
                }
                else {


                    Catnotattend catnotattend=mcatlist.get(position);
                    Intent i = new Intent(context, DealFormGeneralActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                    if (mcatlist.get(position).getCat_name()!=null){
                        i.putExtra("cat_name", mcatlist.get(position).getCat_name());
                    }else{
                        i.putExtra("cat_name", "");
                    }
                    i.putExtra("desc", mcatlist.get(position).getDesc());
                    i.putExtra("model_name", mcatlist.get(position).getModel());
                    i.putExtra("cur_stage", mcatlist.get(position).getFollow_up_type().toString());

                    context.startActivity(i);
                    ((Activity)context).finish();
                }
            }
        });


        
        holder.design_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = context.getSharedPreferences("NextId1", MODE_PRIVATE);
                preferences.edit().remove("NextId1").commit();
                preferences.edit().remove("phase").commit();

                Intent i = new Intent(context, DealfirstMeetingActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("vemp",mcatlist.get(position).getVemp());
                i.putExtra("cat_id",mcatlist.get(position).getCat_id());
                i.putExtra("sname",mcatlist.get(position).getId());
                i.putExtra("mobo",mcatlist.get(position).getMoblino());
                i.putExtra("login_emp",mcatlist.get(position).getLogin_emp());

                context.startActivity(i);
            }
        });
        if (DealstageRecyclerActivity.Not_attend_skip_check){
            holder.textview_Skip.setVisibility(View.VISIBLE);
        }
        holder.textview_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebService.getClient().deal_send_call_sms_what_web(mcatlist.get(position).getAutoid(),"Not Attend","SKIP").enqueue(new Callback<deal_stage_call_sms_what_model>() {
                    @Override
                    public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {
                        activity.finish();
//                        context.startActivity(activity.getIntent());
                    }

                    @Override
                    public void onFailure(Call<deal_stage_call_sms_what_model> call, Throwable t) {

                    }
                });
            }
        });


        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewItemInterface != null) {
                    viewItemInterface.onItemClick(holder.getAdapterPosition(),mcatlist);
                }
            }
        });

        holder.iv_editmodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                if (viewItemInterface != null) {
                    viewItemInterface.onModelEditclick(holder.getAdapterPosition(),mcatlist);
                }
            }
        });

        WebService.getClient().recent_notlist(mcatlist.get(position).getAutoid()).enqueue(new Callback<Notes_POJO>() {
            @Override
            public void onResponse(Call<Notes_POJO> call, Response<Notes_POJO> response) {
                pro.dismiss();
                Log.e("response_note",response.body().toString());

                if (response.body()!=null){

                    if (response.body().getCat().size()>0){
                        Log.e("respose",response.body().getCat().toString()+response.body().getCat().size());
                        notList=response.body().getCat();
                        int size=0;
                        for (int i=0;i<response.body().getCat().size();i++){
                            size=response.body().getCat().size();
                        }
                        Log.d("size",String.valueOf(size));
                        holder.tv_countttt.setText(String.valueOf(size));



                    }else
                        holder.tv_countttt.setText("0");
                }




            }

            @Override
            public void onFailure(Call<Notes_POJO> call, Throwable t) {
                pro.dismiss();
                Utils.showErrorToast(context,t.getMessage());

            }
        });


        holder.llresentNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!holder.tv_countttt.getText().equals("0")) {

                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dilog_notes);


                    RecyclerView rv_notes = dialog.findViewById(R.id.rv_notes);

                    WebService.getClient().recent_notlist(mcatlist.get(position).getAutoid()).enqueue(new Callback<Notes_POJO>() {
                        @Override
                        public void onResponse(Call<Notes_POJO> call, Response<Notes_POJO> response) {
                            pro.dismiss();
                            Log.e("respose", response.body().toString());

                            if (response.body() != null) {

                                if (response.body().getCat().size() > 0) {

                                    Log.e("respose", response.body().getCat().toString() + response.body().getCat().size());
                                    AdapterNotesDealStage adapterNotesDealStage = new AdapterNotesDealStage(context, response.body().getCat());
                                    rv_notes.setLayoutManager(new LinearLayoutManager(context));
                                    rv_notes.setAdapter(adapterNotesDealStage);


                                } else
                                    holder.tv_countttt.setText("0");
                            }


                        }

                        @Override
                        public void onFailure(Call<Notes_POJO> call, Throwable t) {
                            pro.dismiss();
                            Utils.showErrorToast(context, t.getMessage());
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }else{
                    Toast.makeText(context, "No List found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.tv_maker.setText(mcatlist.get(position).getMakerold());
        holder.tv_cust_price.setText(mcatlist.get(position).getCustomer_price());
        holder.tv_model.setText(mcatlist.get(position).getModelold());
        holder.tv_marketval.setText(mcatlist.get(position).getMarket_value());
        holder.tv_regno.setText(mcatlist.get(position).getRegi_noold());
        holder.tv_mfgyear.setText(mcatlist.get(position).getModel_yold());
        holder.tv_diff.setText(mcatlist.get(position).getDiffrance());



        if (mcatlist.get(position).getInq_date()!=null && !mcatlist.get(position).getInq_date().equals("") && mcatlist.get(position).getNot_attend_time()!=null && !mcatlist.get(position).getNot_attend_time().equals("")){
            String diff= printDifference(ConvertStringTodate(mcatlist.get(position).getInq_date()),ConvertStringTodate(mcatlist.get(position).getNot_attend_time()));
            Log.d("seconds_diff",diff);
            holder.tv_Notattend_ime.setText(diff);

        }

        if (mcatlist.get(position).getInq_date()!=null && !mcatlist.get(position).getInq_date().equals("") && mcatlist.get(position).getFirst_time_attend()!=null &&  !mcatlist.get(position).getFirst_time_attend().equals("")){
            String first_diff= printDifference(ConvertStringTodate(mcatlist.get(position).getInq_date()),ConvertStringTodate(mcatlist.get(position).getFirst_time_attend()));
            Log.d("first_diff",first_diff);
            holder.tv_followuptime.setText(first_diff);

        }



    }

    @Override
    public int getItemCount() {
        return mcatlist.size();
//        return 3;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tv_name_show_rc_gv,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,cust_name,remind_date,tv_regno,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,tv_HotCold_show_rc_gv,Deal_type,tv_maker,
                tv_cust_price,tv_model,tv_marketval, tv_mfgyear,tv_diff,tv_Notattend_ime,tv_followuptime
                ,added_days,model_added,source_ofinquire,employe_name_ink,follow_type,latactivity,new_design,file_count,tv_countttt,
                txtremider,txtdeal,delivery_date,textview_current_stage,textview_Skip,passing_type,payment_type,tv_mobile_no_show_rc_gv_other;

        LinearLayout lin_detailrc,image_button,ringid,ringid1,resentNote,ll_paymentType,ll_passing,llresentNote;
        RelativeLayout LinShowDetailRcGv;
        ImageView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,remider,addfile,design_deal,hot_cold_harm,
                homeIcon,iv_edit,iv_editmodel,iv_recentmenu1;
        CardView newdesign_color;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            this.cust_name= itemView.findViewById(R.id.cust_name);
            this.remind_date= itemView.findViewById(R.id.remind_date);
            this.Deal_type= itemView.findViewById(R.id.Deal_type);
            this.tv_maker= itemView.findViewById(R.id.tv_maker);
            this.tv_Notattend_ime= itemView.findViewById(R.id.tv_Notattend_ime);
            this.tv_followuptime= itemView.findViewById(R.id.tv_followuptime);
            this.iv_recentmenu1= itemView.findViewById(R.id.iv_recentmenu1);
            this.llresentNote= itemView.findViewById(R.id.resentNote);
            this.resentNote= itemView.findViewById(R.id.resentNote1);
            this.tv_countttt= itemView.findViewById(R.id.tv_countttt);
            this.tv_cust_price= itemView.findViewById(R.id.tv_cust_price);
            this.tv_model= itemView.findViewById(R.id.tv_model);
            this.tv_marketval= itemView.findViewById(R.id.tv_marketval);
            this.tv_mfgyear= itemView.findViewById(R.id.tv_mfgyear);
            this.tv_diff= itemView.findViewById(R.id.tv_diff);
            this.tv_regno= itemView.findViewById(R.id.tv_regno);
            this.tv_name_show_rc_gv= itemView.findViewById(R.id.tv_name_show_rc_gv);
            this.tv_Notattend_ime= itemView.findViewById(R.id.tv_Notattend_ime);
            this.tv_followuptime= itemView.findViewById(R.id.tv_followuptime);
            this.tv_category_it_show_rc_gv= itemView.findViewById(R.id.tv_category_it_show_rc_gv);
            this.tv_mobile_no_show_rc_gv= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv);
            this.tv_mobile_no_show_rc_gv_other= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv_other);
//            this.tv_Whapp_Number_show_rc_gv= itemView.findViewById(R.id.tv_Whapp_Number_show_rc_gv);
            this.tv_village_show_rc_gv= itemView.findViewById(R.id.tv_village_show_rc_gv);
            this.tv_district_show_rc_gv= itemView.findViewById(R.id.tv_district_show_rc_gv);

            this.tv_NextDate_show_rc_gv= itemView.findViewById(R.id.tv_NextDate_show_rc_gv);
            this.tv_HotCold_show_rc_gv= itemView.findViewById(R.id.tv_HotCold_show_rc_gv);

            this.LinShowDetailRcGv= itemView.findViewById(R.id.LinShowDetailRcGv);
            this.lin_detailrc= itemView.findViewById(R.id.lin_detailrc);
            this.ll_paymentType= itemView.findViewById(R.id.ll_paymentType);
            this.ll_passing= itemView.findViewById(R.id.ll_passing);

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
            this.ringid1= itemView.findViewById(R.id.ringid1);
            this.delivery_date= itemView.findViewById(R.id.delivery_date);
            this.textview_current_stage= itemView.findViewById(R.id.textview_current_stage);
            this.textview_Skip= itemView.findViewById(R.id.textview_Skip);
            this.iv_edit= itemView.findViewById(R.id.iv_edit);
            this.iv_editmodel= itemView.findViewById(R.id.iv_editmodel);
            this.passing_type= itemView.findViewById(R.id.passing_type);
            this.payment_type= itemView.findViewById(R.id.payment_type);
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
                        String strVAutoId =row.getAutoid();
                        String strModel =row.getModel();
                        String strPaymentType =row.getPayment_type();
                        String strPasingType =row.getPassing_type();
                        //String strLName =row.getLname();
                        String strWtaNumber =row.getWhatsappno();
                     //   Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strModel == null)
                            strModel = " ";
                        if(strPaymentType == null)
                            strPaymentType = " ";
                        if(strPasingType == null)
                            strPasingType = " ";
                       /* if(strLName == null)
                            strLName = " ";
*/
                        if(strWtaNumber == null)
                            strWtaNumber = " ";


                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                || strWtaNumber.toLowerCase().contains(charString.toLowerCase())
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                                || strModel.toLowerCase().contains(charString.toLowerCase())
                                || strPaymentType.toLowerCase().contains(charString.toLowerCase())
                                || strPasingType.toLowerCase().contains(charString.toLowerCase())
                                || strVAutoId.toLowerCase().contains(charString.toLowerCase())
                               // || strLName.toLowerCase().contains(charString.toLowerCase())
                        )
                        {
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

    public interface RecyclerViewItemInterface1 {

        void onItemClick(int position,List<Catnotattend> mcatlist);
        void onModelEditclick(int position,List<Catnotattend> mcatlist);

    }

}
