package com.example.kumarGroup.Workshop.Month;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CatWsMonthWeekDay;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MonthWeekDayWsAdapter extends RecyclerView.Adapter<MonthWeekDayWsAdapter.ViewHolder>
    implements Filterable
{

    Context context;
    List<CatWsMonthWeekDay>  catWsMonthWeekDays;
    List<CatWsMonthWeekDay>  catWsMonthWeekDays_one;

    String sms= "";//The message you want to text to the phone

    SharePref sp;
    SharedPreferences sharedPreferences;
    Utils utils;
    Activity activity;


    public MonthWeekDayWsAdapter(Context context, List<CatWsMonthWeekDay> catWsMonthWeekDays) {
        this.context = context;
        this.catWsMonthWeekDays = catWsMonthWeekDays;
        this.catWsMonthWeekDays_one = catWsMonthWeekDays;

        this.activity = (Activity) context;
        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

        sharedPreferences = activity.getSharedPreferences("DateCurrent1", MODE_PRIVATE);
    }

    @NonNull
    @Override
    public MonthWeekDayWsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.general_ws_raw, parent, false);
        MonthWeekDayWsAdapter.ViewHolder viewHolder = new MonthWeekDayWsAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonthWeekDayWsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtWSGenNextDate.setText("Next Date: "+catWsMonthWeekDays.get(position).getNext_date());
        holder.txtWSGenRemark.setText("Remark: "+catWsMonthWeekDays.get(position).getRemark());
        holder.txtWSGenCuName.setText("Customer Name: "+catWsMonthWeekDays.get(position).getCuname());
        holder.txtWSGenWOrkSer.setText("WorkSer: "+catWsMonthWeekDays.get(position).getWorks_ser());
        holder.txtWSGenMobileNo.setText("Mobile Number: "+catWsMonthWeekDays.get(position).getMobileno());
        holder.txtWSGenVillage.setText("Village: "+catWsMonthWeekDays.get(position).getVillage());


        holder.lin_MDY_visit.setVisibility(View.GONE);


        holder.Lin_monthlyDailyWeeklyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, MOnthWeekDayFormActivity.class);
                i.putExtra("Pid",catWsMonthWeekDays.get(position).getSpart_id());
                i.putExtra("CuName",catWsMonthWeekDays.get(position).getCuname());
                i.putExtra("MobileNo",catWsMonthWeekDays.get(position).getMobileno());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+catWsMonthWeekDays.get(position).getMobileno()));
                Log.d("call", "onClick: "+catWsMonthWeekDays.get(position).getMobileno());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        catWsMonthWeekDays.get(position).getMobileno(), null));
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
                            catWsMonthWeekDays.get(position).getMobileno()));
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
                        "Customer Name: "+catWsMonthWeekDays.get(position).getCuname()+"\n" +
                        "Remark: "+catWsMonthWeekDays.get(position).getRemark()+"\n"+
                        "Mobile: "+catWsMonthWeekDays.get(position).getMobileno()+"\n"+
                        "Village: "+catWsMonthWeekDays.get(position).getVillage()+"\n"+
                        "Work ser: "+catWsMonthWeekDays.get(position).getWorks_ser()+"\n"+
                        "Next Date: "+catWsMonthWeekDays.get(position).getNext_date());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });

    }

    @Override
    public int getItemCount() {
        return catWsMonthWeekDays.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtWSGenNextDate, txtWSGenRemark, txtWSGenCuName, txtWSGenWOrkSer,
                txtWSGenMobileNo, txtWSGenVillage;

        TextView lin_MDY_call, lin_MDY_sms, lin_MDY_whapp, lin_MDY_share, lin_MDY_visit;

        LinearLayout Lin_monthlyDailyWeeklyDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            this.txtWSGenNextDate = itemView.findViewById(R.id.txtWSGenNextDate);
            this.txtWSGenRemark = itemView.findViewById(R.id.txtWSGenRemark);
            this.txtWSGenCuName = itemView.findViewById(R.id.txtWSGenCuName);
            this.txtWSGenWOrkSer = itemView.findViewById(R.id.txtWSGenWOrkSer);
            this.txtWSGenMobileNo = itemView.findViewById(R.id.txtWSGenMobileNo);
            this.txtWSGenVillage = itemView.findViewById(R.id.txtWSGenVillage);

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
                    catWsMonthWeekDays = catWsMonthWeekDays_one;
                } else {

                    List<CatWsMonthWeekDay> filteredList = new ArrayList<>();

                    for (CatWsMonthWeekDay row : catWsMonthWeekDays_one) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //   row.getVilage().toString();

                        String strFName = row.getCuname();
                        String strMobile = row.getMobileno();
                        String strVName = row.getVillage();
                       // String strLName = row.getMobileno();
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
                                /* || row.getDistric().toLowerCase().contains(charString.toLowerCase())*/
                                || strVName.toLowerCase().contains(charString.toLowerCase())
                                ) {
                            filteredList.add(row);
                        }
                    }
                    catWsMonthWeekDays = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = catWsMonthWeekDays;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                catWsMonthWeekDays = (ArrayList<CatWsMonthWeekDay>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
