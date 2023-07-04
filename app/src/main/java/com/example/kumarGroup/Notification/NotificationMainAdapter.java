package com.example.kumarGroup.Notification;

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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.DataNotification;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SharePref;
import com.example.kumarGroup.Utils;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NotificationMainAdapter extends RecyclerView.Adapter<NotificationMainAdapter.ViewHolder> {

    Context context;
    List<DataNotification> dataNotifications;
    //  SharePref sp_noti;
    SharePref sp;
    SharedPreferences sharedPreferences;
    Utils utils;
    String Mobilecall_Noti;
    Activity activity;
    String emp;

    public NotificationMainAdapter(Activity activity, List<DataNotification> dataNotifications) {
        this.activity = activity;
        this.dataNotifications = dataNotifications;

        utils = new Utils(activity);
        sp = new SharePref(activity);
        context = activity.getApplicationContext();

        sharedPreferences = activity.getSharedPreferences("Noti_Day", MODE_PRIVATE);
        // emp=sp1.getString("emp_id","");
    }

    @NonNull
    @Override
    public NotificationMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.notification_display_row, parent, false);
        NotificationMainAdapter.ViewHolder viewHolder = new NotificationMainAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationMainAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name_notification.setText("Employee Name:  " + dataNotifications.get(position).getFname() + " "
                + dataNotifications.get(position).getLname());
        holder.tv_category_notification.setText("Category:  " + dataNotifications.get(position).getCat_name());
        holder.tv_mobile_no_notification.setText("Mobile No:  " + dataNotifications.get(position).getMoblino());
        holder.tv_wta_Number_notification.setText("WhatsApp No:  " + dataNotifications.get(position).getWhatsappno());
        holder.tv_village_notification.setText("Village:  " + dataNotifications.get(position).getVilage());
        holder.tv_district_notification.setText("Description:  " + dataNotifications.get(position).getDesc());
        holder.tv_EntryDate_notification.setText("Entry Date:  " + dataNotifications.get(position).getCdate() +
                " ( " + dataNotifications.get(position).getDay() + " )");

        sharedPreferences.edit().putInt("notification_day", dataNotifications.get(position).getDay()).apply();

     //   NotificationMainActivity.getdata(dataNotifications.get(position).getWhich(), context);

        if (dataNotifications.get(position).getStatus().equals("1")) {

            holder.txt_notification_call.setVisibility(View.GONE);
            holder.txt_notification_takeSelfie.setVisibility(View.GONE);
            holder.txtStatus_notificationDisplay.setVisibility(View.VISIBLE);

            holder.txtStatus_notificationDisplay.setText("Pending");

           /* holder.txt_notification_call.setVisibility(View.VISIBLE);
            holder.txt_notification_takeSelfie.setVisibility(View.VISIBLE);*/
        } else {
            holder.txt_notification_call.setVisibility(View.VISIBLE);
            holder.txt_notification_takeSelfie.setVisibility(View.VISIBLE);
            holder.txtStatus_notificationDisplay.setVisibility(View.GONE);

           /* holder.txt_notification_call.setVisibility(View.GONE);
            holder.txt_notification_takeSelfie.setVisibility(View.GONE);
            holder.txtStatus_notificationDisplay.setText("Pending");*/
        }

        // sharedPreferences.edit().putString("type", dataNotifications.get(position).getWhich()).apply();

        if (dataNotifications.get(position).getDay() >= 2) {
          //  sharedPreferences.edit().putInt("notification_day", dataNotifications.get(position).getDay()).apply();
            Log.d("DaysCount", "onBindViewHolder: " + dataNotifications.get(position).getDay());
        } else {
          //  sharedPreferences.edit().putInt("notification_day", 0).apply();
            Log.d("Days_Count", "onBindViewHolder: " + dataNotifications.get(position).getDay());
        }

        //  holder.txtStatus_notificationDisplay.setText(dataNotifications.get(position).getStatus());

        holder.txt_notification_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // NotificationMainActivity.getdata(dataNotifications.get(position).getWhich(),context);
                /*Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dataNotifications.get(position).getMoblino()));
                Log.d("call_noti", "onClick: "+dataNotifications.get(position).getMoblino());
                context.startActivity(intent);*/

                sp.putSharedPref(sp.PHONE_NUMBER, "+91" + dataNotifications.get(position).getMoblino());
                sp.putSharedPref(sp.CALL_ID, dataNotifications.get(position).getId());

                sp.putSharedPref(sp.NOTI_TYPE, dataNotifications.get(position).getWhich());

              //  sharedPreferences.edit().putString("notification_type", dataNotifications.get(position).getWhich()).apply();

             //   NotificationMainActivity.getdata(dataNotifications.get(position).getWhich(), context);

                //sp.edit().putString("type", dataNotifications.get(position).getWhich()).apply();

                Log.d("SPCallLog", "onClick: " + dataNotifications.get(position).getMoblino());

                if (!utils.userPermission.checkCallPermission()) {
                    utils.userPermission.requestCallPermission();
                } else if (!utils.userPermission.checkCallLogPermission()) {
                    utils.userPermission.requestCallLogPermission();
                } else {

                    Mobilecall_Noti = dataNotifications.get(position).getMoblino();
                    // Mobilecall1 = reaplace;

                    Log.d("Mobilecall", "onClick: " + Mobilecall_Noti);

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+91" + Mobilecall_Noti));
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

        holder.txt_notification_takeSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, takeSelfieNotificationActivity.class);
                i.putExtra("id_noti", dataNotifications.get(position).getId());
                i.putExtra("which", dataNotifications.get(position).getWhich());
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
       /* if(dataNotifications == null){
            return 0;
        }*/
        return dataNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name_notification, tv_category_notification, tv_mobile_no_notification, tv_wta_Number_notification, tv_village_notification, tv_district_notification,
                tv_EntryDate_notification, txtStatus_notificationDisplay;
        ImageView txt_notification_call, txt_notification_takeSelfie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_name_notification = itemView.findViewById(R.id.tv_name_notification);
            this.tv_category_notification = itemView.findViewById(R.id.tv_category_notification);
            this.tv_mobile_no_notification = itemView.findViewById(R.id.tv_mobile_no_notification);
            this.tv_wta_Number_notification = itemView.findViewById(R.id.tv_wta_Number_notification);
            this.tv_village_notification = itemView.findViewById(R.id.tv_village_notification);
            this.tv_district_notification = itemView.findViewById(R.id.tv_district_notification);
            this.txt_notification_call = itemView.findViewById(R.id.txt_notification_call);
            this.txt_notification_takeSelfie = itemView.findViewById(R.id.txt_notification_takeSelfie);
            this.tv_EntryDate_notification = itemView.findViewById(R.id.tv_EntryDate_notification);
            this.txtStatus_notificationDisplay = itemView.findViewById(R.id.txtStatus_notificationDisplay);
            // this.img_delete_notification = itemView.findViewById(R.id.img_delete_notification);
        }
    }
}
