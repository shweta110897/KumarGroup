package com.example.kumarGroup.Workshop.General;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatCBDisplay;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import java.util.ArrayList;
import java.util.List;

public class ComplainBoxDisplayAdapter extends RecyclerView.Adapter<ComplainBoxDisplayAdapter.ViewHolder>
implements Filterable {

    Context context;
    List<CatCBDisplay>  catCBDisplays;
    List<CatCBDisplay>  catCBDisplays_one;

    String sms=" ";

    private Activity activity;
    private Utils utils;
    private SharePref sp;
    SharedPreferences sp1;
    String emp;
    String Mobilecall;

    public ComplainBoxDisplayAdapter(Context context, List<CatCBDisplay> catCBDisplays) {
        this.context = context;
        this.catCBDisplays = catCBDisplays;
        this.catCBDisplays_one = catCBDisplays;

        activity = (Activity) context;

        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

    }

    @NonNull
    @Override
    public ComplainBoxDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.compalin_box_display_raw, parent, false);
        ComplainBoxDisplayAdapter.ViewHolder viewHolder = new ComplainBoxDisplayAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComplainBoxDisplayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWSGenNextDate.setText("Category Name: "+catCBDisplays.get(position).getCat_name());
        holder.txtWSGenRemark.setText("Complain: "+catCBDisplays.get(position).getComplain());
        holder.txtWSGenCuName.setText("Customer Name: "+catCBDisplays.get(position).getFname());
        holder.txtWSGenWOrkSer.setText("District: "+catCBDisplays.get(position).getDistric());
        holder.txtWSGenMobileNo.setText("Mobile Number: "+catCBDisplays.get(position).getMoblino());
        holder.txtWSGenVillage.setText("Village: "+catCBDisplays.get(position).getVilage());

        holder.txtWSGenCStatus.setText("Status: "+catCBDisplays.get(position).getC_status());
        holder.txtWSGenLoginName.setText("Employee Name: "+catCBDisplays.get(position).getLoginname());
        holder.txtWSGenAddDate.setText("Date: "+catCBDisplays.get(position).getAdd_date());
        holder.txtWSGenComplainNum.setText("Complain Number: "+catCBDisplays.get(position).getComplain_number());
        holder.txtWSGenNExtdate.setText("Next Date: "+catCBDisplays.get(position).getNext_date());
        holder.txtWSGenComplainMake.setText("Make Name: "+catCBDisplays.get(position).getMaker());
        holder.txtWSGenComplainModel.setText("Model Name: "+catCBDisplays.get(position).getModel_name());
        holder.txtWSGenComplainyear.setText("MFG Year: "+catCBDisplays.get(position).getManufacture_year());
        holder.txtWSGenComplainRegNo.setText("Registration No: "+catCBDisplays.get(position).getRegistration_no());

        holder.txtWSGenAdddate.setText("Mechanic Name: "+catCBDisplays.get(position).getMecanic_name());
        holder.txtWSGenStatus .setVisibility(View.GONE);


        holder.lin_MDY_visit.setVisibility(View.GONE);


        if(catCBDisplays.get(position).getC_status()== null){
            holder.txtWSGenCStatus.setTextColor(Color.BLACK);
        }

        else if(catCBDisplays.get(position).getC_status().equals("Clear")){
            holder.txtWSGenCStatus.setTextColor(Color.GREEN);
        }
        else{
            holder.txtWSGenCStatus.setTextColor(Color.BLACK);
        }


        if(catCBDisplays.get(position).getStatus().equals("0")){
            holder.lin_MDY_call.setVisibility(View.VISIBLE);
        }
        else{
            holder.lin_MDY_call.setVisibility(View.GONE);

        }


        holder.Lin_monthlyDailyWeeklyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catCBDisplays.get(position).getForm_status().equals("0")){
                    Intent i = new Intent(context, ComplainFormActivity.class);
                    i.putExtra("cid",catCBDisplays.get(position).getTableid());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else{

                }

            }
        });


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+catCBDisplays.get(position).getMoblino()));
                Log.d("call", "onClick: "+catCBDisplays.get(position).getMoblino());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + catCBDisplays.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, catCBDisplays.get(position).getTableid());

                Log.d("SPCallLog", "onClick: " + catCBDisplays.get(position).getMoblino());

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {

                    Mobilecall = catCBDisplays.get(position).getMoblino();
                    // Mobilecall1 = reaplace;

                    Log.d("Mobilecall", "onClick: " + Mobilecall);

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    // intent.setData(Uri.parse("tel:+91" + Mobilecall1));
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
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        catCBDisplays.get(position).getMoblino(), null));
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
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+
                            catCBDisplays.get(position).getWhatsappno()));
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




        holder.lin_MDY_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                        ""+"Customer Detail:\n" +
                        "Complain No.: "+catCBDisplays.get(position).getComplain_number()+"\n" +
                        "Customer Name: "+catCBDisplays.get(position).getFname()+"\n" +
                        "Complain: "+catCBDisplays.get(position).getComplain()+"\n"+
                        "Mobile: "+catCBDisplays.get(position).getMoblino()+"\n"+
                        "Village: "+catCBDisplays.get(position).getVilage()+"\n"+
                        "Category Name: "+catCBDisplays.get(position).getCat_name()+"\n"+
                        "District: "+catCBDisplays.get(position).getDistric());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });

    }

    @Override
    public int getItemCount() {
        return catCBDisplays.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtWSGenNextDate, txtWSGenRemark, txtWSGenCuName, txtWSGenWOrkSer,
                txtWSGenMobileNo, txtWSGenVillage;

        TextView lin_MDY_call, lin_MDY_sms, lin_MDY_whapp, lin_MDY_share, lin_MDY_visit;

        LinearLayout Lin_monthlyDailyWeeklyDetail;


        TextView txtWSGenCStatus,txtWSGenLoginName,txtWSGenAddDate,txtWSGenComplainNum,txtWSGenNExtdate,
                txtWSGenAdddate,txtWSGenStatus,txtWSGenComplainMake,txtWSGenComplainModel,txtWSGenComplainyear,txtWSGenComplainRegNo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtWSGenNextDate = itemView.findViewById(R.id.txtWSGenNextDate);
            this.txtWSGenRemark = itemView.findViewById(R.id.txtWSGenRemark);
            this.txtWSGenCuName = itemView.findViewById(R.id.txtWSGenCuName);
            this.txtWSGenWOrkSer = itemView.findViewById(R.id.txtWSGenWOrkSer);
            this.txtWSGenMobileNo = itemView.findViewById(R.id.txtWSGenMobileNo);
            this.txtWSGenVillage = itemView.findViewById(R.id.txtWSGenVillage);

            this.txtWSGenCStatus = itemView.findViewById(R.id.txtWSGenCStatus);
            this.txtWSGenLoginName = itemView.findViewById(R.id.txtWSGenLoginName);
            this.txtWSGenAddDate = itemView.findViewById(R.id.txtWSGenAddDate);
            this.txtWSGenComplainNum = itemView.findViewById(R.id.txtWSGenComplainNum);
            this.txtWSGenNExtdate = itemView.findViewById(R.id.txtWSGenNExtdate);

            this.txtWSGenAdddate = itemView.findViewById(R.id.txtWSGenAdddate);
            this.txtWSGenStatus = itemView.findViewById(R.id.txtWSGenStatus);

            this.txtWSGenComplainMake = itemView.findViewById(R.id.txtWSGenComplainMake);
            this.txtWSGenComplainModel = itemView.findViewById(R.id.txtWSGenComplainModel);
            this.txtWSGenComplainyear = itemView.findViewById(R.id.txtWSGenComplainyear);
            this.txtWSGenComplainRegNo = itemView.findViewById(R.id.txtWSGenComplainRegNo);

            this.Lin_monthlyDailyWeeklyDetail = itemView.findViewById(R.id.Lin_monthlyDailyWeeklyDetail);

            this.lin_MDY_call = itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms = itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp = itemView.findViewById(R.id.lin_MDY_whapp);
            this.lin_MDY_share = itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_visit = itemView.findViewById(R.id.lin_MDY_visit);

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
                  //  List<CatCBDisplay>  catCBDisplays;
                    catCBDisplays = catCBDisplays_one;
                } else {

                    List<CatCBDisplay> filteredList = new ArrayList<>();

                    for (CatCBDisplay row : catCBDisplays_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName = row.getComplain_number();
                        String strMobile = row.getMoblino();
                        String strVName = row.getFname();
                        String strLName = row.getVilage();
                        Log.d("TAG", "Data: " + row);

                        if (strFName == null)
                            strFName = " ";
                        if (strMobile == null)
                            strMobile = " ";
                        if (strVName == null)
                            strVName = " ";
                        /*if (strLName == null)
                            strLName = " ";*/

                        if (strFName.toLowerCase().contains(charString.toLowerCase())
                                || strMobile.toLowerCase().contains(charString.toLowerCase())
                                 ||strLName.toLowerCase().contains(charString.toLowerCase())
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                        ) {
                            filteredList.add(row);
                        }
                    }
                    catCBDisplays = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = catCBDisplays;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                catCBDisplays = (ArrayList<CatCBDisplay>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
