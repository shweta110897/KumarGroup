package com.example.kumarGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.Alarm.DailyReceiverEvening;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Attendance extends AppCompatActivity{
//public class Attendance extends AppCompatActivity  implements LocationListener {


    TextView tv_present, tv_Absent, tv_Halfday, tv_fullsunday, tv_Halfsunday, tv_note;
    Button btn1, btn2, btn3, btn4, btn5;
    //   String module_name;
    String emp_id,id;
    SharedPreferences sp;
    String mono;
    String currentDateTimeString;
    String click_time, currunt_time, click_timeabsent;
    Date startDate, endDate, endabsent;

    LocationManager locationManager;
    String latitude, longitude;
    private static final int REQUEST_LOCATION = 1;

    String address_line;

    List<Address> addresses;

    String lat1,lon1;

    SharedPreferences sharedPreferences;

    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        getSupportActionBar().setTitle(("Attendance"));

       /* if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }*/

     //   getLocation();

      //  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);


       /* locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }*/

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        mono = sp.getString("mobileNo", "");
        Log.d("mobile", "onCreate: " + mono);

        emp_id = sp.getString("emp_id", "");
        id = sp.getString("id", "");
        Log.d("iddsfs", "onCreate: " + id);

        sharedPreferences = getSharedPreferences("Noti_Day",MODE_PRIVATE);
        day = sharedPreferences.getInt("notification_day",0);


     //   Toast.makeText(this, "Day of Notification "+day, Toast.LENGTH_SHORT).show();

        tv_present = findViewById(R.id.tv_present);
        tv_Absent = findViewById(R.id.tv_Absent);
        tv_Halfday = findViewById(R.id.tv_Halfday);
        tv_fullsunday = findViewById(R.id.tv_fullsunday);
        tv_Halfsunday = findViewById(R.id.tv_Halfsunday);
        tv_note = findViewById(R.id.tv_note);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);


//       setAlarmEvening();





        /*if(day<=3)
        {
              tv_present.setClickable(true);
            // Toast.makeText(this, "check in kari shaksho", Toast.LENGTH_SHORT).show();
        }
        else {

            Utils.showErrorToast(Attendance.this,"Please Clear All Notification");

            // tv_present.setClickable(false);
              Intent i  = new Intent(Attendance.this, FoDashbord.class);
              startActivity(i);

           // Toast.makeText(this, "Please Clear Notification All", Toast.LENGTH_SHORT).show();

        }*/



        sp = getSharedPreferences("CheckInPhase", MODE_PRIVATE);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");

        Calendar cal = Calendar.getInstance();
        currunt_time = simpleDateFormat.format(cal.getTime()).toUpperCase();
        Log.d("currunt_time", "onCreate: " + currunt_time);

        click_time = sp.getString("click_time", "").toUpperCase();
        click_timeabsent = sp.getString("click_timeabsent", "").toUpperCase();

        final SharedPreferences sharedPreferences = getSharedPreferences("buttonPref", Context.MODE_PRIVATE);
        boolean buttonTrigger = sharedPreferences.getBoolean("buttonTrigger", true);

        if (buttonTrigger) {
            Toast.makeText(this, "Button Click and start your day", Toast.LENGTH_SHORT).show();

            tv_present.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    click_time = format.format(calendar.getTime()).toLowerCase();
                    tv_present.setVisibility(View.INVISIBLE);
                    tv_Absent.setVisibility(View.INVISIBLE);
                    tv_fullsunday.setVisibility(View.INVISIBLE);
                    tv_Halfsunday.setVisibility(View.INVISIBLE);
                    tv_Halfday.setVisibility(View.INVISIBLE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("buttonTrigger", false);
                    editor.apply();
                    sp.edit().putString("click_time", click_time).apply();
                    Log.d("click_time", "onClick: " + click_time);

                    Phase();
                }
            });

            tv_Absent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    click_time = format.format(calendar.getTime()).toLowerCase();
                    tv_present.setVisibility(View.INVISIBLE);
                    tv_Absent.setVisibility(View.INVISIBLE);
                    tv_Halfday.setVisibility(View.INVISIBLE);
                    tv_fullsunday.setVisibility(View.INVISIBLE);
                    tv_Halfsunday.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("buttonTrigger", false);
                    editor.apply();
                    sp.edit().putString("click_time", click_time).apply();
                    Log.d("click_time", "onClick: " + click_time);
                    Intent i = new Intent(Attendance.this, Absent.class);
                    startActivity(i);
                }
            });

            tv_Halfday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    click_time = format.format(calendar.getTime()).toLowerCase();
                    tv_present.setVisibility(View.INVISIBLE);
                    tv_Absent.setVisibility(View.INVISIBLE);
                    tv_Halfday.setVisibility(View.INVISIBLE);
                    tv_fullsunday.setVisibility(View.INVISIBLE);
                    tv_Halfsunday.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("buttonTrigger", false);
                    editor.apply();
                    sp.edit().putString("click_time", click_time).apply();
                    Log.d("click_time", "onClick: " + click_time);
                    Intent i = new Intent(Attendance.this, HalfDay.class);
                    startActivity(i);
                }
            });

            tv_fullsunday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    click_time = format.format(calendar.getTime()).toLowerCase();
                    tv_present.setVisibility(View.INVISIBLE);
                    tv_Absent.setVisibility(View.INVISIBLE);
                    tv_fullsunday.setVisibility(View.INVISIBLE);
                    tv_Halfsunday.setVisibility(View.INVISIBLE);
                    tv_Halfday.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("buttonTrigger", false);
                    editor.apply();
                    sp.edit().putString("click_time", click_time).apply();
                    Log.d("click_time", "onClick: " + click_time);
                    Intent i = new Intent(Attendance.this, ChackIn.class);
                    startActivity(i);

                }
            });

            tv_Halfsunday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    click_time = format.format(calendar.getTime()).toLowerCase();
                    tv_present.setVisibility(View.INVISIBLE);
                    tv_Absent.setVisibility(View.INVISIBLE);
                    tv_Halfday.setVisibility(View.INVISIBLE);
                    tv_fullsunday.setVisibility(View.INVISIBLE);
                    tv_Halfsunday.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("buttonTrigger", false);
                    editor.apply();
                    sp.edit().putString("click_time", click_time).apply();
                    Log.d("click_time", "onClick: " + click_time);
                    Intent i = new Intent(Attendance.this, HalfSundayChackin.class);
                    startActivity(i);
                }
            });

        } else {

            try {
                Date date1 = simpleDateFormat.parse(currunt_time);
                Date date2 = simpleDateFormat.parse(click_time);
             //        Date date1 = simpleDateFormat.parse("09/08/2020 01:30:10");
              //        Date date2 = simpleDateFormat.parse("08/08/2020 09:05:10");
                printDifference(date1, date2);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void setAlarmEvening()
    {
        // Quote in Morning at 08:32:00 AM
        Toast.makeText(this, "SetAlarm Evening Calling", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 38);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.d("time_evening", "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE);

        Toast.makeText(this, "setAlarm: " + Calendar.HOUR_OF_DAY + " " + Calendar.MINUTE, Toast.LENGTH_SHORT).show();

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(Attendance.this, DailyReceiverEvening.class);
        int ALARM1_ID = 20000;
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getBroadcast
                    (this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
       /* PendingIntent pendingIntent = PendingIntent.getBroadcast(
                Attendance.this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);*/
        //  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

       /* AlarmManager alarmMgr;
        PendingIntent alarmIntent;

        alarmMgr = (AlarmManager)Attendance.this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(Attendance.this, DailyReceiverEvening.class);
        alarmIntent = PendingIntent.getBroadcast(Attendance.this, 0, intent, 0);

        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 30);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        assert alarmMgr != null;
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);*/

    }

   /* private void getLocation() {
        try{
            locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
      //  textView.setText("Latitude:"+location.getLatitude()+"\n Longtitude:"+location.getLongitude());
        Toast.makeText(this, "Latitude:"+location.getLatitude()+
                "\n Longtitude:"+location.getLongitude(), Toast.LENGTH_SHORT).show();

        Log.d("lat_long", "onLocationChanged: "+"Latitude:"+location.getLatitude()+
                "\n Longtitude:"+location.getLongitude());

        try{
            Geocoder geocoder=new Geocoder(this, Locale.getDefault());
           // List<Address> addresses =geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

          //  Geocoder geocoder;
            List<Address> addresses;

            try {

                geocoder = new Geocoder(this, Locale.getDefault());

                addresses = geocoder.getFromLocation(+location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                address_line = address+" "+city+" "+state+" "+country+" "+postalCode+" "+knownName;

                //  Toast.makeText(this, "address"+address+city+state+country+postalCode+knownName, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }


           *//* address_line =addresses.get(0).getAddressLine(0)+","+
                     addresses.get(0).getAddressLine(1)+","+
                     addresses.get(0).getAddressLine(2);*//*

            Toast.makeText(this, "address"+ address_line, Toast.LENGTH_SHORT).show();

           *//* Log.d("address", "onLocationChanged: "+addresses.get(0).getAddressLine(0)+","+
                    addresses.get(0).getAddressLine(1)+","+
                    addresses.get(0).getAddressLine(2));*//*

        }catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }*/


   /* private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(Attendance.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                Attendance.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
           // Location locationGPS = locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,  (LocationListener) this);



            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                Log.d("latitude", "getLocation: "+latitude+" "+longitude);
                //  Log.d("longitude", "getLocation: "+longitude);

                Toast.makeText(this, "Your Location:" + "\n" +
                        "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_LONG).show();

                Geocoder geocoder;
                List<Address> addresses;

                try {

                    geocoder = new Geocoder(this, Locale.getDefault());

                    addresses = geocoder.getFromLocation(lat, longi, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    address_line = address+" "+city+" "+state+" "+country+" "+postalCode+" "+knownName;

                  //  Toast.makeText(this, "address"+address+city+state+country+postalCode+knownName, Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                // showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            }
            else {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }*/


    private void printDifference(Date startDate, Date endDate) {
            long different = startDate.getTime() - endDate.getTime();
            Log.d("jdskfhsdzs", "printDifference: "+different);

            System.out.println("startDate : " + startDate);
            System.out.println("endDate : " + endDate);
            System.out.println("different : " + different);

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long difference = Math.abs(startDate.getTime() - endDate.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);
            //  String dayDifference = Long.toString(differenceDates);
            Log.d("dayDifference", "printDifference: "+differenceDates);


            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            Log.d("elapsedHours", "printDifference: " + elapsedHours);


            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            Log.d("elapsedMinutes", "printDifference: " + elapsedMinutes);

            long elapsedSeconds = different / secondsInMilli;

            System.out.printf(
                    "%d days, %d hours, %d minutes, %d seconds%n",
                    differenceDates, elapsedHours, elapsedMinutes, elapsedSeconds);

            if (differenceDates<1) {
                Toast.makeText(this, "Please Wait Next Day", Toast.LENGTH_SHORT).show();
                Phase();
                tv_present.setVisibility(View.INVISIBLE);
                tv_Absent.setVisibility(View.INVISIBLE);
                tv_Halfday.setVisibility(View.INVISIBLE);
                tv_fullsunday.setVisibility(View.INVISIBLE);
                tv_Halfsunday.setVisibility(View.INVISIBLE);
                Log.d("TAG", "printDifference: checkflag123 1");
                tv_note.setVisibility(View.VISIBLE);

            } else if (differenceDates >= 1) {
                Toast.makeText(this, "Press The above Button Start your day", Toast.LENGTH_SHORT).show();

                tv_present.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // Phase();
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                        click_time = format.format(calendar.getTime()).toLowerCase();

                        tv_present.setVisibility(View.INVISIBLE);
                        tv_Absent.setVisibility(View.INVISIBLE);
                        tv_fullsunday.setVisibility(View.INVISIBLE);
                        tv_Halfsunday.setVisibility(View.INVISIBLE);
                        tv_Halfday.setVisibility(View.INVISIBLE);

                        sp.edit().putString("click_time", click_time).apply();

                        Log.d("click_time", "onClick: " + click_time);

                        Intent i = new Intent(Attendance.this, ChackIn.class);
                            i.putExtra("address_line",address_line);
                       /* i.putExtra("latitude",latitude);
                        i.putExtra("longitude",longitude);*/
                        startActivity(i);
                    }
                });

                tv_Absent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        click_time = format.format(calendar.getTime()).toLowerCase();
                        tv_present.setVisibility(View.INVISIBLE);
                        tv_Absent.setVisibility(View.INVISIBLE);
                        tv_Halfday.setVisibility(View.INVISIBLE);
                        tv_fullsunday.setVisibility(View.INVISIBLE);
                        tv_Halfsunday.setVisibility(View.INVISIBLE);

                        sp.edit().putString("click_time", click_time).apply();
                        Log.d("click_time", "onClick: " + click_time);
                        Intent i = new Intent(Attendance.this, Absent.class);
                        startActivity(i);
                    }
                });

                tv_Halfday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        click_time = format.format(calendar.getTime()).toLowerCase();
                        tv_present.setVisibility(View.INVISIBLE);
                        tv_Absent.setVisibility(View.INVISIBLE);
                        tv_Halfday.setVisibility(View.INVISIBLE);
                        tv_fullsunday.setVisibility(View.INVISIBLE);
                        tv_Halfsunday.setVisibility(View.INVISIBLE);

                        sp.edit().putString("click_time", click_time).apply();
                        Log.d("click_time", "onClick: " + click_time);
                        Intent i = new Intent(Attendance.this, HalfDay.class);
                        startActivity(i);

                    }
                });

                tv_fullsunday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        click_time = format.format(calendar.getTime()).toLowerCase();
                        tv_present.setVisibility(View.INVISIBLE);
                        tv_Absent.setVisibility(View.INVISIBLE);
                        tv_fullsunday.setVisibility(View.INVISIBLE);
                        tv_Halfsunday.setVisibility(View.INVISIBLE);
                        tv_Halfday.setVisibility(View.INVISIBLE);
                        sp.edit().putString("click_time", click_time).apply();
                        Log.d("click_time", "onClick: " + click_time);
                        Intent i = new Intent(Attendance.this, ChackIn.class);
                        startActivity(i);

                    }
                });

                tv_Halfsunday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        click_time = format.format(calendar.getTime()).toLowerCase();
                        tv_present.setVisibility(View.INVISIBLE);
                        tv_Absent.setVisibility(View.INVISIBLE);
                        tv_Halfday.setVisibility(View.INVISIBLE);
                        tv_fullsunday.setVisibility(View.INVISIBLE);
                        tv_Halfsunday.setVisibility(View.INVISIBLE);

                        sp.edit().putString("click_time", click_time).apply();
                        Log.d("click_time", "onClick: " + click_time);
                        Intent i = new Intent(Attendance.this, HalfSundayChackin.class);
                        startActivity(i);
                    }
                });
            }
        }


        public void Phase () {
            Log.d("TAG", "Phase: checkid "+id);
            WebService.getClient().getphase(id).enqueue(new Callback<ChackphaseModel>() {
                @Override
                public void onResponse(@NotNull Call<ChackphaseModel> call, @NotNull Response<ChackphaseModel> response) {
                    assert response.body() != null;
                    Log.d("Phase", "onResponse: " + response.body().getPhase());

                    sp.edit().putString("CheckInPhase", response.body().getPhase()).apply();

                    if (response.body().getPhase() == null) {
                        Intent i = new Intent(Attendance.this, ChackIn1.class);
                        i.putExtra("address_line",address_line);
                      /*  i.putExtra("latitude",latitude);
                        i.putExtra("longitude",longitude);*/
                        startActivity(i);
                    } else if (response.body().getPhase().equals("1")) {
                        Intent i = new Intent(Attendance.this, TaskWoek.class);
                        startActivity(i);
                    } else if (response.body().getPhase().equals("2")) {
                        Intent i = new Intent(Attendance.this, LanchBreak.class);
                        startActivity(i);
                    } else if (response.body().getPhase().equals("3")) {
                        Log.d("TAG", "printDifference: checkflag123 2");

                        tv_note.setVisibility(View.VISIBLE);
                    }
                    else if (response.body().getPhase().equals("13")) {
                        Intent i = new Intent(Attendance.this, ChackOut.class);
                        startActivity(i);
                    }else if (response.body().getPhase().equals("4")) {
                        Intent i = new Intent(Attendance.this, StartOverTime.class);
                        startActivity(i);
                    } else if (response.body().getPhase().equals("5")) {
                        Log.d("TAG", "printDifference: checkflag123 3");

                        tv_note.setVisibility(View.VISIBLE);
                    } else if (response.body().getPhase().equals("6")) {
                        Intent i = new Intent(Attendance.this, EndOverTime.class);
                        startActivity(i);
                    } else if (response.body().getPhase().equals("7")) {
                        Log.d("TAG", "printDifference: checkflag123 4");

                        tv_note.setVisibility(View.VISIBLE);
                    } else if (response.body().getPhase().equals("8")) {
                        Intent i = new Intent(Attendance.this, HalfDayChackOut.class);
                        startActivity(i);
                    } else if (response.body().getPhase().equals("9")) {
                        Log.d("TAG", "printDifference: checkflag123 5");

                        tv_note.setVisibility(View.VISIBLE);
                    } else if (response.body().getPhase().equals("10")) {
                        Intent i = new Intent(Attendance.this, HalfSundayChackout.class);
                        startActivity(i);
                    } else if (response.body().getPhase().equals("11")) {
                        Log.d("TAG", "printDifference: checkflag123 6");

                        tv_note.setVisibility(View.VISIBLE);
                    }
                    else if (response.body().getPhase().equals("12")) {
                        Log.d("TAG", "printDifference: checkflag123 7");

                        tv_note.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<ChackphaseModel> call, @NotNull Throwable t) {

                    Toast.makeText(Attendance.this, "null", Toast.LENGTH_SHORT).show();
                }
            });
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getIntent().getStringExtra("checkIn")!=null){
            Intent intent=new Intent(Attendance.this,FoDashbord.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
       /* if (tv_note.getVisibility() == View.VISIBLE){
            Log.d("TAG", "phasecalling: phase status");
            Phase ();
        }*/
        super.onResume();
    }
}



