package com.example.kumarGroup.TrueValue;

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
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.Village_List.VillageListShowAdapter;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_stage_call_sms_what_model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterReviewList extends RecyclerView.Adapter<AdapterReviewList.viewHolder> implements Filterable {

    Context context;
    List<Catnotattend> mcatlist;
    List<Catnotattend> mcatlist_one;


    SharePref sp;
    Utils utils;
    Activity activity;

    String Mobilecall;
    ProgressDialog pro;

    String sms= "",emp;//The message you want to text to the phone

    private RecyclerViewItemInterface viewItemInterface;

    public void setViewItemInterface(RecyclerViewItemInterface viewItemInterface) {
        this.viewItemInterface = viewItemInterface;
    }


    public AdapterReviewList(Context context, Activity activity , List<Catnotattend> mcatlist) {
        this.context = context;
        this.mcatlist = mcatlist;
        this.mcatlist_one = mcatlist;

        utils = new Utils(activity);
        sp = new SharePref(context);
        this.activity = activity;

        SharedPreferences sharedPreferencesS = context.getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
        emp = sharedPreferencesS.getString("Slected_EMPID","");

        pro = new ProgressDialog(context);
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_review_history,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {



        holder.image_button.setVisibility(View.GONE);
        holder.homeIcon.setVisibility(View.GONE);

//        holder.hot_cold_harm.setVisibility(View.GONE);
        holder.tv_name_show_rc_gv.setText("Name: "+mcatlist.get(position).getFname()+" "+mcatlist.get(position).getLname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+mcatlist.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+mcatlist.get(position).getMoblino());
        holder.tv_mobile_no_show_rc_gv_other.setText("Other Mobile: "+mcatlist.get(position).getOther_mobile());
//         holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+mcatlist.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+mcatlist.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText(mcatlist.get(position).getDesc());
        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+mcatlist.get(position).getVdate());
        holder.added_days.setText("Added :"+mcatlist.get(position).getAdded()+" Days");
        holder.source_ofinquire.setText("Source of Inquire : "+mcatlist.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(mcatlist.get(position).getEmployee_name());
        holder.follow_type.setText(mcatlist.get(position).getFollow_up_type());
//        holder.passing_type.setText(/*mcatlist.get(position).getPassing_type()*/"Aggriculture");
        holder.latactivity.setText("Last Activity : "+mcatlist.get(position).getFollow_up_type());
        if (mcatlist.get(position).getCurrent_stage_name()==null){
            holder.textview_current_stage.setText("");
        }else {
            holder.textview_current_stage.setText(mcatlist.get(position).getCurrent_stage_name().toString());
        }
        holder.payment_type.setText("Payment Type: "+mcatlist.get(position).getPayment_type()/*"Loan"*/);
        /*passing_type*/
//        if (mcatlist.get(position).getPassing_type()!=null || !mcatlist.get(position).getPassing_type().equals("")){
        holder.passing_type.setText(mcatlist.get(position).getPassing_type()/*"Aggriculture"*/);
//            if (mcatlist.get(position).getPassing_type().toString().equals("Aggriculture")){
        if ( holder.passing_type.getText().toString().equals("Agriculture")){
//            holder.passing_type.setBackgroundColor(Color.parseColor(R.color.Green));
            holder.passing_type.setTextColor(context.getResources().getColor(R.color.Green));
        }else {
            holder.passing_type.setTextColor(context.getResources().getColor(R.color.Yellow));
        }
//        }

        holder.iv_edit.setVisibility(View.GONE);

      /*  if (mcatlist.get(position).getWhatsapp_flag().equals("1")){
            holder.lin_MDY_whapp.setVisibility(View.GONE);
        }

        if (mcatlist.get(position).getPhn_flag().equals("1")){
            holder.lin_MDY_call.setVisibility(View.GONE);
            if (holder.lin_MDY_call.getVisibility()==View.VISIBLE){
                holder.LinShowDetailRcGv.setEnabled(false);
            }else{
                holder.LinShowDetailRcGv.setEnabled(true);
            }
        }

        if (mcatlist.get(position).getSms_flag().equals("1")){
            holder.lin_MDY_sms.setVisibility(View.GONE);
        }
*/

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


                    WebService.getClient().getCallReview("1", mcatlist.get(position).getId()).enqueue(new Callback<deal_stage_call_sms_what_model>() {
                        @Override
                        public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {

                        }

                        @Override
                        public void onFailure(Call<deal_stage_call_sms_what_model> call, Throwable t) {
                            if (viewItemInterface != null) {
                                viewItemInterface.onCallclick(holder.getAdapterPosition(),mcatlist);
                            }
                        }
                    });


                 /*   WebService.getClient().getMyInqProfile_CSW(emp,
                            mcatlist.get(position).getAutoid(),
                            mcatlist.get(position).getMoblino(),
                            "Call"
                    ).enqueue(new Callback<myInqCswModel>() {
                        @Override
                        public void onResponse(@NotNull Call<myInqCswModel> call, @NotNull Response<myInqCswModel> response) {
                            // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("Call", "onResponse: notattend"+response.body().getMsg());

                            if (viewItemInterface != null) {
                                viewItemInterface.onCallclick(holder.getAdapterPosition(),mcatlist);
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<myInqCswModel> call, @NotNull Throwable t) {

                        }
                    });*/
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


                WebService.getClient().getSMSReview("1", mcatlist.get(position).getId()).enqueue(new Callback<deal_stage_call_sms_what_model>() {
                    @Override
                    public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {

                    }

                    @Override
                    public void onFailure(Call<deal_stage_call_sms_what_model> call, Throwable t) {
                        if (viewItemInterface != null) {
                            viewItemInterface.onCallclick(holder.getAdapterPosition(),mcatlist);
                        }
                    }
                });


               /* WebService.getClient().getMyInqProfile_CSW(emp,
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
                });*/
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

                    WebService.getClient().getWhatsappReview("1", mcatlist.get(position).getId()).enqueue(new Callback<deal_stage_call_sms_what_model>() {
                        @Override
                        public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {

                        }

                        @Override
                        public void onFailure(Call<deal_stage_call_sms_what_model> call, Throwable t) {
                            if (viewItemInterface != null) {
                                viewItemInterface.onCallclick(holder.getAdapterPosition(),mcatlist);
                            }
                        }
                    });

                    /*WebService.getClient().getMyInqProfile_CSW(emp,
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
                    });*/

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

        holder.textview_Skip.setVisibility(View.VISIBLE);

        holder.textview_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.design_remark);

                    EditText note = dialog.findViewById(R.id.note);
                    Button btn_submit = dialog.findViewById(R.id.btn_submit);


                    btn_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (note.getText().toString().equals("")){
                                note.setError("Enter Remark");
                            }else if (!note.getText().toString().equals("")){
                                    note.setError("");

                                   /* WebService.getClient().deal_send_call_sms_what_web(mcatlist.get(position).getAutoid(),"Not Attend","SKIP").enqueue(new Callback<deal_stage_call_sms_what_model>() {
                                        @Override
                                        public void onResponse(Call<deal_stage_call_sms_what_model> call, Response<deal_stage_call_sms_what_model> response) {
                                            activity.finish();
    //                                          activity.startActivity(activity.getIntent());
                                        }

                                        @Override
                                        public void onFailure(Call<deal_stage_call_sms_what_model> call, Throwable t) {

                                        }
                                });*/

                            }

                        }
                    });


                    dialog.show();



            }
        });



        holder.LinShowDetailRcGv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UserExperienceActivity.class);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mcatlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView tv_name_show_rc_gv,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,tv_Whapp_Number_show_rc_gv,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,tv_HotCold_show_rc_gv
                ,added_days,model_added,source_ofinquire,employe_name_ink,follow_type,latactivity,new_design,file_count,
                txtremider,txtdeal,delivery_date,textview_current_stage,textview_Skip,passing_type,payment_type,tv_mobile_no_show_rc_gv_other;

        LinearLayout lin_detailrc,image_button,ringid,resentNote,ll_paymentType,ll_passing;
        RelativeLayout LinShowDetailRcGv;
        ImageView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,remider,addfile,design_deal,hot_cold_harm,
                homeIcon,iv_edit;
        CardView newdesign_color;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_name_show_rc_gv= itemView.findViewById(R.id.tv_name_show_rc_gv);
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

            this.follow_type= itemView.findViewById(R.id.follow_type);
            this.latactivity= itemView.findViewById(R.id.latactivity);
            this.new_design= itemView.findViewById(R.id.new_design);
            this.iv_edit= itemView.findViewById(R.id.iv_edit);
            this.hot_cold_harm= itemView.findViewById(R.id.hot_cold_harm);
            this.image_button= itemView.findViewById(R.id.image_button);
            this.homeIcon= itemView.findViewById(R.id.homeIcon);
            this.file_count= itemView.findViewById(R.id.file_count);
            this.newdesign_color= itemView.findViewById(R.id.newdesign_color);
            this.ringid= itemView.findViewById(R.id.ringid);
            this.resentNote= itemView.findViewById(R.id.resentNote);
            this.delivery_date= itemView.findViewById(R.id.delivery_date);
            this.textview_current_stage= itemView.findViewById(R.id.textview_current_stage);
            this.textview_Skip= itemView.findViewById(R.id.textview_Skip);
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
                        String strVAutoid =row.getAutoid();
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
                       /* if(strLName == null)
                            strLName = " ";
*/
                        if(strWtaNumber == null)
                            strWtaNumber = " ";

                        if(strModel == null)
                            strModel = " ";
                        if(strPaymentType == null)
                            strPaymentType = " ";
                        if(strPasingType == null)
                            strPasingType = " ";

                        if (VillageListShowAdapter.Village_List_AutoFill_Search_Check){
                            if (strFName.toLowerCase().contains(charString.toLowerCase())
                                    || strVAutoid.toLowerCase().contains(charString.toLowerCase())
                                 || strModel.toLowerCase().contains(charString.toLowerCase())
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
                                    || strPaymentType.toLowerCase().contains(charString.toLowerCase())
                                    || strPasingType.toLowerCase().contains(charString.toLowerCase())
                                    || strModel.toLowerCase().contains(charString.toLowerCase())
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


    public interface RecyclerViewItemInterface {

        void onItemClick(int position,List<Catnotattend> mcatlist);
        void onCallclick(int position,List<Catnotattend> mcatlist);

    }
}
