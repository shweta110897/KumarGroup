package com.example.kumarGroup.Alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

import com.example.kumarGroup.FoLogin;
import com.example.kumarGroup.R;

public class DailyReceiverEvening extends BroadcastReceiver {
    // DatabaseHelper databaseHelper;

    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {

        String quote;

        //  mp = MediaPlayer.create(context, R.raw.soft_alarm);
        //  mp.start();

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        MediaPlayer mp = MediaPlayer.create(context, notification);
        mp.start();

        Vibrator v=(Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(20000);

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //  Intent notificationIntent = new Intent(context, DailySpecialActivity.class);
        Intent notificationIntent = new Intent(context, FoLogin.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity
                    (context, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getActivity
                    (context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
       /* PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        /*mp = MediaPlayer.create(context, R.raw.soft_alarm);
        mp.start();*/

        // get your quote here
        //   quote = doSomeMethod();

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.app_icon)
                .setContentTitle("Sonalika")
                .setContentText("Check Out Time").setSound(alarmSound)
                //.setContentText("Check In Time")
                .setAutoCancel(true).setWhen(when)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Check Out Time"))
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});  // Declair VIBRATOR Permission in AndroidManifest.xml
        assert notificationManager != null;
        notificationManager.notify(5, mNotifyBuilder.build());
    }
}

