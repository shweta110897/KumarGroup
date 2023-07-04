package com.example.kumarGroup.ReportCollection.ByVillageList;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ShowVilageDetail;
import com.example.kumarGroup.Utils;

import java.util.List;

public class VillageListShow2Adapter extends RecyclerView.Adapter<VillageListShow2Adapter.ViewHolder>{

    Context context;
    List<ShowVilageDetail.Detail> Cat1;
    Activity activity;
    Utils utils;

    String Mobilecall,sms;


    public VillageListShow2Adapter(Context context, Activity activity,List<ShowVilageDetail.Detail> cat1) {
        this.context = context;
        this.Cat1 = cat1;
        this.activity = activity;

        utils = new Utils(activity);

    }

    @NonNull
    @Override
    public VillageListShow2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_village_list_show,parent,false);
        return new VillageListShow2Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VillageListShow2Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (DropDownActivity.Type_Check){
            holder.tv_name_payPen.setText("Name: "+Cat1.get(position).getCustomer_name());
            holder.txt_payment_pending.setText("Payment: "+"\n"+Cat1.get(position).getFinal_amt());

        }else {
            holder.tv_name_payPen.setText("Name: "+Cat1.get(position).getCname());
            holder.txt_payment_pending.setText("Payment: "+"\n"+Cat1.get(position).getLeft_amt());

        }
        holder.tv_mobile_no_payPen.setText("Mobile No: "+Cat1.get(position).getMobileno());
        holder.tv_Wta_Number_payPen.setText("WhatsApp no: "+Cat1.get(position).getWhno());
        holder.tv_village_payPen.setText("Village: "+Cat1.get(position).getVillage());
        holder.tv_description_payPen.setText("Description: "+Cat1.get(position).getV_reason());

        holder.tv_Nextdate_payPen.setText("Next Date: "+Cat1.get(position).getN_date());


        Log.d("TAG", "onBindViewHolder: Checksize "+Cat1.size());

        holder.txt_pendingDoc_pending.setVisibility(View.GONE);
        holder.txt_Clear_pending.setVisibility(View.GONE);
        holder.lin_inqview_CSWS.setVisibility(View.VISIBLE);
        holder.lin_MDY_share.setVisibility(View.GONE);

        holder.lin_MDY_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {
                    Mobilecall = Cat1.get(position).getMobileno();

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    activity.startActivity(intent);
                    activity.finish();
                }
            }
        });

        //sms
        holder.lin_MDY_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.fromParts("sms", Cat1.get(position).getMobileno(), null));
                smsIntent.putExtra("sms_body",sms);
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(smsIntent);
            }
        });

        holder.lin_MDY_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="
                            +"+91"+Cat1.get(position).getWhno()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, "Whatsapp not installed on your device ", Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public int getItemCount() {
        return Cat1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_payPen,tv_mobile_no_payPen,tv_Wta_Number_payPen,tv_village_payPen,
                tv_description_payPen,tv_Nextdate_payPen;

        TextView txt_payment_pending,txt_pendingDoc_pending,txt_Clear_pending,lin_MDY_share,
                lin_MDY_call,lin_MDY_sms,lin_MDY_whapp;

        LinearLayout linBookingUploadMain;

        LinearLayout lin_inqview_CSWS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name_payPen = itemView.findViewById(R.id.tv_name_payPen);
            this.tv_mobile_no_payPen = itemView.findViewById(R.id.tv_mobile_no_payPen);
            this.tv_Wta_Number_payPen = itemView.findViewById(R.id.tv_Wta_Number_payPen);
            this.tv_village_payPen = itemView.findViewById(R.id.tv_village_payPen);
            this.tv_description_payPen = itemView.findViewById(R.id.tv_description_payPen);

            this.txt_payment_pending = itemView.findViewById(R.id.txt_payment_pending);
            this.txt_pendingDoc_pending = itemView.findViewById(R.id.txt_pendingDoc_pending);
            this.txt_Clear_pending = itemView.findViewById(R.id.txt_Clear_pending);

            this.linBookingUploadMain = itemView.findViewById(R.id.linBookingUploadMain);
            this.tv_Nextdate_payPen = itemView.findViewById(R.id.tv_Nextdate_payPen);

            this.lin_inqview_CSWS = itemView.findViewById(R.id.lin_inqview_CSWS);
            this.lin_MDY_share = itemView.findViewById(R.id.lin_MDY_share);
            this.lin_MDY_call = itemView.findViewById(R.id.lin_MDY_call);
            this.lin_MDY_sms = itemView.findViewById(R.id.lin_MDY_sms);
            this.lin_MDY_whapp = itemView.findViewById(R.id.lin_MDY_whapp);
        }
    }
}
