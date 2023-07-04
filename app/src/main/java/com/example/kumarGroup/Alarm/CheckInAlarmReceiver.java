package com.example.kumarGroup.Alarm;

import static com.example.kumarGroup.AlarmService.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.kumarGroup.Attendance;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;

public class CheckInAlarmReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {

//        if (Objects.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED")) {

            // Quote in Morning

            Intent notificationIntent = new Intent(context, Attendance.class);//on tap this activity will open

            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(FoDashbord.class);
            stackBuilder.addNextIntent(notificationIntent);

            PendingIntent pendingIntent = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//                pendingIntent = PendingIntent.getActivity
//                        (context, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);
                pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_MUTABLE);
            }
            else
            {
//                pendingIntent = PendingIntent.getActivity
//                        (context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            }

//            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);//getting the pendingIntent

            Notification.Builder builder = new Notification.Builder(context);//building the notification

            Notification notification = builder.setContentTitle("Check IN Notification")
                    .setContentText("Click here to Check IN..")
                    .setTicker("New Message Alert!")
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentIntent(pendingIntent).build();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setChannelId(CHANNEL_ID);
            }

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//below creating notification channel, because of androids latest update, O is Oreo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        CHANNEL_ID,
                        "NotificationDemo",
                        NotificationManager.IMPORTANCE_DEFAULT
                );
                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(0, notification);

        }
//    }
}
