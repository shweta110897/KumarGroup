package com.example.kumarGroup.Inquiry;

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

import com.example.kumarGroup.CatInquiryGenDetail;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.myInqCswModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayGeneralAdapter extends RecyclerView.Adapter<DisplayGeneralAdapter.ViewHolder> implements Filterable
{
    Context context;
    List<CatInquiryGenDetail> catInquiryGenDetails;
    List<CatInquiryGenDetail> catInquiryGenDetails_one;

    SharePref sp;
    SharedPreferences sharedPreferences,sp1;
    Utils utils;
    Activity activity;

    String Mobilecall;

    String sms= "",emp;//The message you want to text to the phone

    public DisplayGeneralAdapter(Context context, Activity activity,List<CatInquiryGenDetail> catInquiryGenDetails) {
        this.context = context;
        this.activity = activity;
        this.catInquiryGenDetails = catInquiryGenDetails;
        this.catInquiryGenDetails_one = catInquiryGenDetails;

        utils = new Utils(activity);
        sp = new SharePref(activity);

        sp1 = activity.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }

    @NonNull
    @Override
    public DisplayGeneralAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View listItem = inflater.inflate(R.layout.show_detail_rc_gv, parent, false);
        View listItem = inflater.inflate(R.layout.design_visit, parent, false);
        DisplayGeneralAdapter.ViewHolder viewHolder = new DisplayGeneralAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayGeneralAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.homeIcon.setVisibility(View.INVISIBLE);
        holder.image_button.setVisibility(View.GONE);
        holder.tv_name_show_rc_gv.setText("Name: "+catInquiryGenDetails.get(position).getFname()+""+catInquiryGenDetails.get(position).getLname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+catInquiryGenDetails.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+catInquiryGenDetails.get(position).getMoblino());
        holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+catInquiryGenDetails.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+catInquiryGenDetails.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText(catInquiryGenDetails.get(position).getDesc());
        holder.tv_NextDate_show_rc_gv.setText("Next Date: "+catInquiryGenDetails.get(position).getNext_date());
        holder.added_days.setText("Added :"+catInquiryGenDetails.get(position).getDays()+" Days");
        holder.model_added.setText(catInquiryGenDetails.get(position).getModel());
        holder.source_ofinquire.setText("Source of Inquire : "+catInquiryGenDetails.get(position).getSor_of_inq());
        holder.employe_name_ink.setText(catInquiryGenDetails.get(position).getEmployee_name());
        holder.follow_type.setText(catInquiryGenDetails.get(position).getFollow_up_type());
        holder.latactivity.setText("Last Activity : "+catInquiryGenDetails.get(position).getFollow_up_type());

        if ("0".equals(catInquiryGenDetails.get(position).getInq_new())){
            holder.new_design.setVisibility(View.VISIBLE);
            holder.new_design.setText("NEW");
        }

        if ("0".equals(catInquiryGenDetails.get(position).getInq_overdue())){
            holder.new_design.setVisibility(View.VISIBLE);
            holder.new_design.setText("OVERDUE");
        }


        if ("HOT".equals(catInquiryGenDetails.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.images_hot);
        }
        if ("WARM".equals(catInquiryGenDetails.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.warm);
        }
        if ("COLD".equals(catInquiryGenDetails.get(position).getType_inq())){
            holder.hot_cold_harm.setBackgroundResource(R.drawable.cold);
        }


        holder.remider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, FormGeneralActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("vemp",catInquiryGenDetails.get(position).getVemp());
                i.putExtra("cat_id",catInquiryGenDetails.get(position).getCat_id());
                i.putExtra("sname",catInquiryGenDetails.get(position).getSid());

                context.startActivity(i);

            }
        });

        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catInquiryGenDetails.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catInquiryGenDetails.get(position).getAutoid());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = catInquiryGenDetails.get(position).getMoblino();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();

                    WebService.getClient().getMyInqProfile_CSW(emp,
                            catInquiryGenDetails.get(position).getAutoid(),
                            catInquiryGenDetails.get(position).getMoblino(),
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
                        Uri.fromParts("sms", catInquiryGenDetails.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);

                WebService.getClient().getMyInqProfile_CSW(emp,
                        catInquiryGenDetails.get(position).getAutoid(),
                        catInquiryGenDetails.get(position).getMoblino(),
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
                            +"+91"+catInquiryGenDetails.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    WebService.getClient().getMyInqProfile_CSW(emp,
                            catInquiryGenDetails.get(position).getAutoid(),
                            catInquiryGenDetails.get(position).getMoblino(),
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
                .putExtra("sname",catInquiryGenDetails.get(position).getSid()));
            }
        });

        holder.design_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,DealDashboardActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("vemp",catInquiryGenDetails.get(position).getVemp())
                .putExtra("cat_id",catInquiryGenDetails.get(position).getCat_id())
                .putExtra("sname",catInquiryGenDetails.get(position).getSid()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return catInquiryGenDetails.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name_show_rc_gv,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,tv_Whapp_Number_show_rc_gv,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,tv_HotCold_show_rc_gv
                ,added_days,model_added,source_ofinquire,employe_name_ink,follow_type,latactivity,new_design;

        LinearLayout lin_detailrc,image_button;
        RelativeLayout LinShowDetailRcGv;
        ImageView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,remider,addfile,design_deal,hot_cold_harm,homeIcon;

        public ViewHolder(@NonNull View itemView)
        {
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
            this.follow_type= itemView.findViewById(R.id.follow_type);
            this.latactivity= itemView.findViewById(R.id.latactivity);
            this.addfile= itemView.findViewById(R.id.addfile);
            this.design_deal= itemView.findViewById(R.id.design_deal);
            this.new_design= itemView.findViewById(R.id.new_design);
            this.hot_cold_harm= itemView.findViewById(R.id.hot_cold_harm);
            this.homeIcon= itemView.findViewById(R.id.homeIcon);
            this.image_button= itemView.findViewById(R.id.image_button);
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
                    catInquiryGenDetails = catInquiryGenDetails_one;
                } else {

                    List<CatInquiryGenDetail> filteredList = new ArrayList<>();

                    for (CatInquiryGenDetail row : catInquiryGenDetails_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        String strLName =row.getLname();
                        String strWtaNumber =row.getWhatsappno();
                        String strModel =row.getModel();
                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strLName == null)
                            strLName = " ";

                        if(strWtaNumber == null)
                            strWtaNumber = " ";

                        if(strModel == null)
                            strModel = " ";


                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                 || strWtaNumber.toLowerCase().contains(charString.toLowerCase())
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                                || strModel.toLowerCase().contains(charString.toLowerCase())
                                || strLName.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    catInquiryGenDetails = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = catInquiryGenDetails;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                catInquiryGenDetails = (ArrayList<CatInquiryGenDetail>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
