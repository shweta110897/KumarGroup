package com.example.kumarGroup.Traveling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.PhaseTravelingModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelingMainActivity extends AppCompatActivity {

    TextView txtStartTraveling,tv_note_travelling;

    SharedPreferences sp,sp1,sp2;

    String click_time_Traveling,currunt_time_Traveling,click_timeabsent_Traveling;

    String Phase, id,idCheckInTravelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveling_main);


        txtStartTraveling = findViewById(R.id.txtStartTraveling);
        tv_note_travelling = findViewById(R.id.tv_note_travelling);


        sp1 = getSharedPreferences("StartTravelling", MODE_PRIVATE);

        Phase =  sp1.getString("idPhaseTravelling", "");
        id = sp1.getString("idCheckInTravelling", "");



       /* sp2 = getSharedPreferences("StartTravelling", MODE_PRIVATE);

        idCheckInTravelling = sp2.getString("idCheckInStartTravelling","");*/


      //  Toast.makeText(this, "id"+id+" phase"+Phase, Toast.LENGTH_SHORT).show();

        sp = getSharedPreferences("CheckInPhase_traveling", MODE_PRIVATE);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");

        Calendar cal = Calendar.getInstance();
        currunt_time_Traveling = simpleDateFormat.format(cal.getTime()).toUpperCase();
        Log.d("currunt_time", "onCreate: " + currunt_time_Traveling);

        click_time_Traveling = sp.getString("click_time1", "").toUpperCase();
        click_timeabsent_Traveling = sp.getString("click_timeabsent", "").toUpperCase();

        final SharedPreferences sharedPreferences = getSharedPreferences("buttonPref_Traveling", Context.MODE_PRIVATE);
        boolean buttonTrigger_Traveling = sharedPreferences.getBoolean("buttonTrigger_traveling", true);

      //  Toast.makeText(this, ""+buttonTrigger_Traveling, Toast.LENGTH_SHORT).show();


        if (buttonTrigger_Traveling) {
            Toast.makeText(this, "Button Click and start your day", Toast.LENGTH_SHORT).show();


            txtStartTraveling.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    click_time_Traveling = format.format(calendar.getTime()).toLowerCase();

                    txtStartTraveling.setVisibility(View.INVISIBLE);


                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("buttonTrigger_traveling", false);
                    editor.apply();
                    sp.edit().putString("click_time1", click_time_Traveling).apply();
                    Log.d("click_time1", "onClick: " + click_time_Traveling);

                    Phase_Traveling();
                }
            });
        }else {

            try {
                Date date1 = simpleDateFormat.parse(currunt_time_Traveling);
                Date date2 = simpleDateFormat.parse(click_time_Traveling);
                //        Date date1 = simpleDateFormat.parse("09/08/2020 01:30:10");
                //        Date date2 = simpleDateFormat.parse("08/08/2020 09:05:10");
                printDifference(date1, date2);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }







       /* txtStartTraveling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(id.equals(""))
                {
                    Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                    startActivity(i);
                }
                else {

                    WebService.getClient().getPhaseTraveling(id).enqueue(new Callback<PhaseTravelingModel>() {
                        @Override
                        public void onResponse(@NotNull Call<PhaseTravelingModel> call,
                                               @NotNull Response<PhaseTravelingModel> response) {

                            assert response.body() != null;

                        //    Toast.makeText(TravelingMainActivity.this, ""+response.body().getPhase(), Toast.LENGTH_SHORT).show();

                            if (response.body().getPhase() == null) {
                                sp.edit().putString("idPhaseTravelling", "").apply();
                            }
                            else{
                                sp.edit().putString("idPhaseTravelling", response.body().getPhase().toString()).apply();
                            }


                            if (response.body().getPhase() == null) {
                                //  if (response.body().getPhase().equals("0")) {
                                Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                                startActivity(i);
                            }
                            else if (response.body().getPhase().equals("1")) {
                                Intent i = new Intent(TravelingMainActivity.this, EndTravelingActivity.class);
                                startActivity(i);
                            }
                            else if (response.body().getPhase().equals("2")) {
                                Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                                startActivity(i);
                            }
                            else{
                                Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<PhaseTravelingModel> call, @NotNull Throwable t) {
                            Toast.makeText(TravelingMainActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });*/

    }

    private void printDifference(Date startDate, Date endDate)
    {
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
            Toast.makeText(this, "Please Wait Next Day", Toast.LENGTH_LONG).show();

            Phase_Traveling();

            txtStartTraveling.setVisibility(View.INVISIBLE);
            tv_note_travelling.setVisibility(View.VISIBLE);

        }
        else if (differenceDates >= 1)
        {
            Toast.makeText(this, "Press The above Button Start your day", Toast.LENGTH_SHORT).show();

            txtStartTraveling.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Phase();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    click_time_Traveling = format.format(calendar.getTime()).toLowerCase();

                    sp.edit().putString("click_time1", click_time_Traveling).apply();

                    Log.d("click_time", "onClick: " + click_time_Traveling);

                    Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                    startActivity(i);
                }
            });
        }

        }


    private void Phase_Traveling()
    {
        if(id.equals(""))
        {
           // Toast.makeText(this, "Next day", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
            startActivity(i);
        }
        else {

          /*  if(Phase == null){
              //  sp.edit().putString("idPhaseTravelling", "").apply();

                Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                startActivity(i);
            }

            else if(Phase.equals("1")){
                Intent i = new Intent(TravelingMainActivity.this, EndTravelingActivity.class);
                startActivity(i);
            }
            else if (Phase.equals("2")) {
                tv_note_travelling.setVisibility(View.VISIBLE);
                      *//*  Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                        startActivity(i);*//*
            }
            else{
                Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                startActivity(i);
            }*/


            WebService.getClient().getPhaseTraveling(id).enqueue(new Callback<PhaseTravelingModel>() {
                @Override
                public void onResponse(@NotNull Call<PhaseTravelingModel> call,
                                       @NotNull Response<PhaseTravelingModel> response) {

                    assert response.body() != null;

                  //  Toast.makeText(TravelingMainActivity.this, ".."+response.body().getPhase()+id, Toast.LENGTH_SHORT).show();


                    if (response.body().getPhase() == null) {
                        //  if (response.body().getPhase().equals("0")) {
                        Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                        startActivity(i);
                    }
                    else if (response.body().getPhase().equals("1")) {
                        Intent i = new Intent(TravelingMainActivity.this, EndTravelingActivity.class);
                        startActivity(i);
                    }
                    else if (response.body().getPhase().equals("2")) {
                        tv_note_travelling.setVisibility(View.VISIBLE);
                        /*Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                        startActivity(i);*/
                    }
                    else{
                        Intent i = new Intent(TravelingMainActivity.this, StartTravellingActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<PhaseTravelingModel> call, @NotNull Throwable t) {
                    Toast.makeText(TravelingMainActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}