package com.example.kumarGroup.Workshop.General;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataGenaralWS;
import com.example.kumarGroup.R;

import java.util.List;

public class WSGeneralAdapter extends RecyclerView.Adapter<WSGeneralAdapter.Viewholder>
{
    Context context;
    List<DataGenaralWS> dataGenaralWS;

    String sms= "";//The message you want to text to the phone


    public WSGeneralAdapter(Context context, List<DataGenaralWS> dataGenaralWS) {
        this.context = context;
        this.dataGenaralWS = dataGenaralWS;
    }

    @NonNull
    @Override
    public WSGeneralAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.general_ws_raw, parent, false);
        WSGeneralAdapter.Viewholder viewHolder = new WSGeneralAdapter.Viewholder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WSGeneralAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.txtWSGenNextDate.setText("Next Date: "+dataGenaralWS.get(position).getNext_date());
        holder.txtWSGenRemark.setText("Remark: "+dataGenaralWS.get(position).getRemark());
        holder.txtWSGenCuName.setText("Customer Name: "+dataGenaralWS.get(position).getCuname());
        holder.txtWSGenWOrkSer.setText("WorkSer: "+dataGenaralWS.get(position).getWorks_ser());
        holder.txtWSGenMobileNo.setText("Mobile Number: "+dataGenaralWS.get(position).getMobileno());
        holder.txtWSGenVillage.setText("Village: "+dataGenaralWS.get(position).getVillage());


        holder.lin_MDY_visit.setVisibility(View.GONE);


       /* holder.Lin_monthlyDailyWeeklyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, MOnthWeekDayFormActivity.class);
                i.putExtra("Pid",dataGenaralWS.get(position).getId());
                i.putExtra("CuName",dataGenaralWS.get(position).getCuname());
                i.putExtra("MobileNo",dataGenaralWS.get(position).getMobileno());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });*/


        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dataGenaralWS.get(position).getMobileno()));
                Log.d("call", "onClick: "+dataGenaralWS.get(position).getMobileno());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                        dataGenaralWS.get(position).getMobileno(), null));
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
                            dataGenaralWS.get(position).getMobileno()));
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
                        "Customer Name: "+dataGenaralWS.get(position).getCuname()+"\n" +
                        "Remark: "+dataGenaralWS.get(position).getRemark()+"\n"+
                        "Mobile: "+dataGenaralWS.get(position).getMobileno()+"\n"+
                        "Village: "+dataGenaralWS.get(position).getVillage()+"\n"+
                        "Work ser: "+dataGenaralWS.get(position).getWorks_ser()+"\n"+
                        "Next Date: "+dataGenaralWS.get(position).getNext_date());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(i,"Share via"));

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataGenaralWS.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        TextView txtWSGenNextDate, txtWSGenRemark, txtWSGenCuName, txtWSGenWOrkSer,
                txtWSGenMobileNo, txtWSGenVillage;

        TextView lin_MDY_call, lin_MDY_sms, lin_MDY_whapp, lin_MDY_share, lin_MDY_visit;

        LinearLayout Lin_monthlyDailyWeeklyDetail;

        public Viewholder(@NonNull View itemView) {
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
}
