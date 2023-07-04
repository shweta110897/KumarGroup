package com.example.kumarGroup.Alarm;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Looper;
import android.os.Vibrator;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.example.kumarGroup.FoLogin;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.send_location_model_ak;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyReceiver extends BroadcastReceiver {
    // DatabaseHelper databaseHelper;

    MediaPlayer mp;
    private FusedLocationProviderClient fusedLocationClient;
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
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

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
                .setContentText("Check In Time").setSound(alarmSound)
                //.setContentText("Check In Time")
                .setAutoCancel(true).setWhen(when)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Check In Time"))
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});  // Declair VIBRATOR Permission in AndroidManifest.xml
        notificationManager.notify(5, mNotifyBuilder.build());

       // getlocation(context);
    }
    private void getlocation(Context context)  {

//        ProgressDialog pro = new ProgressDialog(context);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

//        pro.show();
//        pro.setMessage("Please wait get location...");
        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

//                            binding.location.setText(addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
                            //  Toast.makeText(context, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

//                            WebService.getClient().send_location_web_ak(emp_id,
                            WebService.getClient().send_location_web_ak("2",
                                    addresses.get(0).getAddressLine(0) +","+
                                            addresses.get(0).getSubLocality() +","+
                                            addresses.get(0).getLocality() + "," +
                                            addresses.get(0).getAdminArea() + "," +
                                            addresses.get(0).getCountryName()
                                    ,String.valueOf(addresses.get(0).getLatitude())
                                    ,String.valueOf(addresses.get(0).getLongitude())
                                    ,"0"
                            ).enqueue(new Callback<send_location_model_ak>() {
                                @Override
                                public void onResponse(Call<send_location_model_ak> call, Response<send_location_model_ak> response) {
                                    Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                                    pro.dismiss();
                                }

                                @Override
                                public void onFailure(Call<send_location_model_ak> call, Throwable throwable) {
//                                    pro.dismiss();
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
//                            pro.dismiss();
                        }
                    }
                    else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(10000)
                                .setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                //   super.onLocationResult(locationResult);
                                try {

                                    Location location1 = locationResult.getLastLocation();
                                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 0);

//                                    binding.location.setText(addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName());
                                    //  Toast.makeText(context, addresses.get(0).getLocality() + "," + addresses.get(0).getAdminArea() + "," + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();

//                                    WebService.getClient().send_location_web_ak(emp_id,
                                    WebService.getClient().send_location_web_ak("1",
                                            addresses.get(0).getAddressLine(0) +","+
                                                    addresses.get(0).getSubLocality() +","+
                                                    addresses.get(0).getLocality() + "," +
                                                    addresses.get(0).getAdminArea() + "," +
                                                    addresses.get(0).getCountryName()
                                            ,String.valueOf(addresses.get(0).getLatitude())
                                            ,String.valueOf(addresses.get(0).getLongitude()),
                                            "0"
                                    ).enqueue(new Callback<send_location_model_ak>() {
                                        @Override
                                        public void onResponse(Call<send_location_model_ak> call, Response<send_location_model_ak> response) {
                                            Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                                            pro.dismiss();
                                        }

                                        @Override
                                        public void onFailure(Call<send_location_model_ak> call, Throwable throwable) {
//                                            pro.dismiss();
                                        }
                                    });

                                } catch (Exception e) {
//                                    pro.dismiss();
                                }
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else {
//            pro.dismiss();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);


            alertDialog.setTitle("GPS is not Enabled!");

            alertDialog.setMessage("Do you want to turn on GPS?");


            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                }
            });


            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });


            alertDialog.show();

        }
    }
}

