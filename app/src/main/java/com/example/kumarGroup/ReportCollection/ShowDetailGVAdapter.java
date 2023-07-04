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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatShowRCGV;
import com.example.kumarGroup.CustomerProfileCSW_Model;
import com.example.kumarGroup.CustomerProfileData.DisplayCatPermissionActivity;
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

public class ShowDetailGVAdapter extends RecyclerView.Adapter<ShowDetailGVAdapter.ViewHolder>
        implements Filterable {

    Context context;
    List<CatShowRCGV> catShowRCGVS;
    List<CatShowRCGV> catShowRCGVS_one;

    String sms= "";//The message you want to text to the phone

    SharePref sp;
    SharedPreferences sharedPreferences;
    Utils utils;
    Activity activity;

    String Mobilecall;

    SharedPreferences sp1;
    String emp;

    public ShowDetailGVAdapter(Context context, List<CatShowRCGV> catShowRCGVS) {
        this.context = context;
        this.catShowRCGVS = catShowRCGVS;
        this.catShowRCGVS_one = catShowRCGVS;

        this.activity = (Activity) context;
        utils = new Utils(activity);
        sp = new SharePref(activity);
       // context = activity.getApplicationContext();

        sp1 = context.getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
    }

    @NonNull
    @Override
    public ShowDetailGVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.show_detail_rc_gv, parent, false);
        ShowDetailGVAdapter.ViewHolder viewHolder = new ShowDetailGVAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowDetailGVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        CatShowRCGV data = catShowRCGVS.get(position);

        holder.tv_name_show_rc_gv.setText("Name: "+catShowRCGVS.get(position).getFname()+""+catShowRCGVS.get(position).getLname());
        holder.tv_category_it_show_rc_gv.setText("Category: "+catShowRCGVS.get(position).getCat_name());
        holder.tv_mobile_no_show_rc_gv.setText("Mobile: "+catShowRCGVS.get(position).getMoblino());
        holder.tv_Whapp_Number_show_rc_gv.setText("WhatsApp Number: "+catShowRCGVS.get(position).getWhatsappno());
        holder.tv_village_show_rc_gv.setText("Village: "+catShowRCGVS.get(position).getVilage());
        holder.tv_district_show_rc_gv.setText("Description: "+catShowRCGVS.get(position).getDesc());

        holder.tv_NextDate_show_rc_gv.setVisibility(View.GONE);
        holder.tv_HotCold_show_rc_gv.setVisibility(View.GONE);

        holder.LinShowDetailRcGv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DisplayCatPermissionActivity.class);
              /*  i.putExtra("Name",catShowRCGVS.get(position).getFname()+""+catShowRCGVS.get(position).getLname());
                i.putExtra("Category",catShowRCGVS.get(position).getCat_name());
                i.putExtra("cat_id",catShowRCGVS.get(position).getCat_id());
                i.putExtra("sname",catShowRCGVS.get(position).getId());*/
                i.putExtra("id",catShowRCGVS.get(position).getId());
                context.startActivity(i);
            }
        });

        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+catShowRCGVS.get(position).getMoblino()));
                Log.d("call", "onClick: "+catShowRCGVS.get(position).getMoblino());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catShowRCGVS.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catShowRCGVS.get(position).getMoblino());
                sp.putSharedPref(sp.task_type, "");

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = catShowRCGVS.get(position).getMoblino();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();
                }


                WebService.getClient().getCustomerProfile_CSW(emp,
                        catShowRCGVS.get(position).getId(),
                        catShowRCGVS.get(position).getMoblino(),
                        "Call").enqueue(new Callback<CustomerProfileCSW_Model>() {
                    @Override
                    public void onResponse(@NotNull Call<CustomerProfileCSW_Model> call, @NotNull Response<CustomerProfileCSW_Model> response) {
                        Log.d("Call", "onResponse: "+response.body().getMsg());
                    }

                    @Override
                    public void onFailure(@NotNull Call<CustomerProfileCSW_Model> call, @NotNull Throwable t) {

                    }
                });
            }
        });


        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", catShowRCGVS.get(position).getMoblino(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);

                WebService.getClient().getCustomerProfile_CSW(emp,
                        catShowRCGVS.get(position).getId(),
                        catShowRCGVS.get(position).getMoblino(),
                        "Sms").enqueue(new Callback<CustomerProfileCSW_Model>() {
                    @Override
                    public void onResponse(@NotNull Call<CustomerProfileCSW_Model> call, @NotNull Response<CustomerProfileCSW_Model> response) {
                        Log.d("SMs", "onResponse: "+response.body().getMsg());
                    }

                    @Override
                    public void onFailure(@NotNull Call<CustomerProfileCSW_Model> call, @NotNull Throwable t) {

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
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+catShowRCGVS.get(position).getWhatsappno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                    WebService.getClient().getCustomerProfile_CSW(emp,
                            catShowRCGVS.get(position).getId(),
                            catShowRCGVS.get(position).getWhatsappno(),
                            "Whatsapp").enqueue(new Callback<CustomerProfileCSW_Model>() {
                        @Override
                        public void onResponse(@NotNull Call<CustomerProfileCSW_Model> call, @NotNull Response<CustomerProfileCSW_Model> response) {
                           // Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("wta", "onResponse: "+response.body().getMsg());
                        }

                        @Override
                        public void onFailure(@NotNull Call<CustomerProfileCSW_Model> call, @NotNull Throwable t) {

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

                // Intent i=new Intent(Intent.ACTION_SEND);
                Intent shareIntent=new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail\n" +
                        "Name: "+catShowRCGVS.get(position).getFname()+"\n" +
                        "Category: "+catShowRCGVS.get(position).getCat_name()+"\n"+
                        "Employee Name: "+catShowRCGVS.get(position).getEmployee_name()+"\n" +
                        "Mobile: "+catShowRCGVS.get(position).getMoblino()+"\n"+
                        "WhatsApp Number: "+catShowRCGVS.get(position).getWhatsappno());
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(shareIntent,"Share via"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return catShowRCGVS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name_show_rc_gv,tv_category_it_show_rc_gv,tv_mobile_no_show_rc_gv,tv_Whapp_Number_show_rc_gv,
                tv_village_show_rc_gv,tv_district_show_rc_gv,tv_NextDate_show_rc_gv,tv_HotCold_show_rc_gv;

        LinearLayout LinShowDetailRcGv,lin_detailrc;

        TextView lin_MDY_call,lin_MDY_sms,lin_MDY_whapp,lin_MDY_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_name_show_rc_gv= itemView.findViewById(R.id.tv_name_show_rc_gv);
            this.tv_category_it_show_rc_gv= itemView.findViewById(R.id.tv_category_it_show_rc_gv);
            this.tv_mobile_no_show_rc_gv= itemView.findViewById(R.id.tv_mobile_no_show_rc_gv);
            this.tv_Whapp_Number_show_rc_gv= itemView.findViewById(R.id.tv_Whapp_Number_show_rc_gv);
            this.tv_village_show_rc_gv= itemView.findViewById(R.id.tv_village_show_rc_gv);
            this.tv_district_show_rc_gv= itemView.findViewById(R.id.tv_district_show_rc_gv);
            this.tv_NextDate_show_rc_gv= itemView.findViewById(R.id.tv_NextDate_show_rc_gv);
            this.tv_HotCold_show_rc_gv= itemView.findViewById(R.id.tv_HotCold_show_rc_gv);

            this.LinShowDetailRcGv= itemView.findViewById(R.id.LinShowDetailRcGv);
            this.lin_detailrc= itemView.findViewById(R.id.lin_detailrc);

            this.lin_MDY_call= itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms= itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp= itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share= itemView.findViewById(R.id.lin_MDY_share);
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
                    catShowRCGVS = catShowRCGVS_one;
                } else {

                    List<CatShowRCGV> filteredList = new ArrayList<>();

                    for (CatShowRCGV row : catShowRCGVS_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName =row.getFname();
                        String strMobile =row.getMoblino();
                        String strVName =row.getVilage();
                        String strLName =row.getLname();
                        Log.d("TAG", "Data: "+row);

                        if(strFName == null)
                            strFName = " ";
                        if(strMobile == null)
                            strMobile = " ";
                        if(strVName == null)
                            strVName = " ";
                        if(strLName == null)
                            strLName = " ";

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                /* || row.getDistric().toLowerCase().contains(charString.toLowerCase())*/
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                                || strLName.toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(row);
                        }
                    }
                    catShowRCGVS = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = catShowRCGVS;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                catShowRCGVS = (ArrayList<CatShowRCGV>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
