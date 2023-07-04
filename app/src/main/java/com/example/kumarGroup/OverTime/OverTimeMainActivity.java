package com.example.kumarGroup.OverTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.ChackphaseModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverTimeMainActivity extends AppCompatActivity {

    TextView txtStartOverTime,txtSunday;

    SharedPreferences sp;

    DataStorage ds;

    int hour,min;
    String AM_PM;

    String idCheckInStartOverTime;
    String CheckInPhase,emp_id,id1;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_time_main);
        getSupportActionBar().setTitle("Over Time");

        txtStartOverTime = findViewById(R.id.txtStartOverTime);
        txtSunday = findViewById(R.id.txtSunday);

        ds = new DataStorage(this);

        int id = Integer.parseInt(ds.read("id",DataStorage.INTEGER).toString());


      /*  sp = getSharedPreferences("CheckInPhase", MODE_PRIVATE);
        CheckInPhase=sp.getString("CheckInPhase","");*/


        sp = getSharedPreferences("Login", MODE_PRIVATE);
       // Log.d("mobile", "onCreate: " + mono);

        emp_id = sp.getString("emp_id", "");
        id1 = sp.getString("id", "");


      //  sp = getSharedPreferences("CheckIn", MODE_PRIVATE);

    /*   idCheckInStartOverTime=sp.getString("idCheckInStartOverTime","");
       Log.d("idCheckInStartOverTime", "onCreate: "+idCheckInStartOverTime);*/

      /*  Calendar calendar = Calendar.getInstance();
       // SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        String strDate = mdformat.format(calendar.getTime());*/

        //  Log.d("CurrentTime", "display: "+strDate);

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        int ds = c.get(Calendar.AM_PM);

    //    Toast.makeText(this, ""+ds, Toast.LENGTH_SHORT).show();

        if (ds == 0)
            AM_PM = "am";
        else
            AM_PM = "pm";

      /*  Toast.makeText(OverTimeMainActivity.this, "" + hour + ":" + min + AM_PM, Toast.LENGTH_SHORT).show();

        if ((hour == 11 && min == 30 && AM_PM.matches("am"))  ||
                (hour == 12 && AM_PM.matches("pm")))
        {
            Toast.makeText(OverTimeMainActivity.this, "Time is between the range", Toast.LENGTH_SHORT).show();

            txtStartOverTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

               *//* if(id>0){
                    Intent i = new Intent(OverTimeMainActivity.this, CheckOutStartOvertimeActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                    startActivity(i);
                }*/
        /*

                    //100>0; -1<0
                    if(id>0){
                        Intent i = new Intent(OverTimeMainActivity.this, CheckOutStartOvertimeActivity.class);
                        startActivity(i);
                    }
                    else if(id<0) {
                        Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                        startActivity(i);
                    }
                }
            });
        } else{
            Toast.makeText(OverTimeMainActivity.this, "Time is not between the range", Toast.LENGTH_SHORT).show();
    }

*/
//Manish Bhai Id "4331"


     //    Toast.makeText(this, " "+id1,Toast.LENGTH_SHORT).show();

        if(id1 == null || id1.equals("")){
            //   Toast.makeText(OverTimeMainActivity.this, "CheckIn", Toast.LENGTH_SHORT).show();
            Toast.makeText(OverTimeMainActivity.this, "You Should Check In", Toast.LENGTH_SHORT).show();

            txtStartOverTime.setClickable(true);

            txtStartOverTime.setOnClickListener(v -> {
                //  Toast.makeText(this, "ghjkjkl"+id, Toast.LENGTH_SHORT).show();
                //100>0; -1<0
                if(id>0){
                    Intent i = new Intent(OverTimeMainActivity.this, CheckOutStartOvertimeActivity.class);
                    startActivity(i);
                }

                else if(id<0) {
                    Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                    startActivity(i);
                }

                else if(id==0){
                    Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                    startActivity(i);
                }

            });
        }
        else {

            WebService.getClient().getphase(id1).enqueue(new Callback<ChackphaseModel>() {
                @Override
                public void onResponse(@NotNull Call<ChackphaseModel> call, @NotNull Response<ChackphaseModel> response) {
                     assert response.body() != null;

                //    Toast.makeText(OverTimeMainActivity.this, "" + response.body().getPhase(), Toast.LENGTH_SHORT).show();


                       // if (response.body().getPhase().equals("null")) {
                        if (response.body().getPhase()== null) {
                            //   Toast.makeText(OverTimeMainActivity.this, "CheckIn", Toast.LENGTH_SHORT).show();
                            Toast.makeText(OverTimeMainActivity.this, "You Should Check In", Toast.LENGTH_SHORT).show();

                            txtStartOverTime.setClickable(true);

                            txtStartOverTime.setOnClickListener(v -> {
                                //  Toast.makeText(this, "ghjkjkl"+id, Toast.LENGTH_SHORT).show();
                                //100>0; -1<0
                                if(id>0){
                                    Intent i = new Intent(OverTimeMainActivity.this, CheckOutStartOvertimeActivity.class);
                                    startActivity(i);
                                }

                                else if(id<0) {
                                    Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                                    startActivity(i);
                                }

                                else if(id==0){
                                    Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                                    startActivity(i);
                                }

                            });

                        } else {
                            response.body().getPhase();
                            if (response.body().getPhase().equals("1")) {
                                //   Toast.makeText(OverTimeMainActivity.this, "CheckIn", Toast.LENGTH_SHORT).show();
                                // Toast.makeText(OverTimeMainActivity.this, "Please Check Out First", Toast.LENGTH_SHORT).show();
                                Utils.showErrorToast(OverTimeMainActivity.this, "Please do  CheckOut First");

                                txtStartOverTime.setClickable(false);

                            } else if (response.body().getPhase().equals("5")) {
                                Toast.makeText(OverTimeMainActivity.this, "You Should Check In", Toast.LENGTH_SHORT).show();
                                txtStartOverTime.setClickable(true);

                                txtStartOverTime.setOnClickListener(v -> {
                                    //  Toast.makeText(this, "ghjkjkl"+id, Toast.LENGTH_SHORT).show();
                                    //100>0; -1<0
                                    if(id>0){
                                        Intent i = new Intent(OverTimeMainActivity.this, CheckOutStartOvertimeActivity.class);
                                        startActivity(i);
                                    }

                                    else if(id<0) {
                                        Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                                        startActivity(i);
                                    }

                                    else if(id==0){
                                        Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                                        startActivity(i);
                                    }

                                });

                            } else {

                                Utils.showErrorToast(OverTimeMainActivity.this, "Please do  CheckOut First");
                                txtStartOverTime.setClickable(false);
                            }
                        }



                }

                @Override
                public void onFailure(@NotNull Call<ChackphaseModel> call, @NotNull Throwable t) {
                    Toast.makeText(OverTimeMainActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                }
            });
        }




        txtStartOverTime.setOnClickListener(v -> {
          //  Toast.makeText(this, "ghjkjkl"+id, Toast.LENGTH_SHORT).show();
            //100>0; -1<0
            if(id>0){
                Intent i = new Intent(OverTimeMainActivity.this, CheckOutStartOvertimeActivity.class);
                startActivity(i);
            }

            else if(id<0) {
                Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                startActivity(i);
            }

            else if(id==0){
                Intent i = new Intent(OverTimeMainActivity.this, StartOverTimeOvertimeActivity.class);
                startActivity(i);
            }

        });


        txtSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Toast.makeText(OverTimeMainActivity.this, "dffdfdf"+id, Toast.LENGTH_SHORT).show();
                if(id>0){
                    Intent i = new Intent(OverTimeMainActivity.this,SundayCheckOutActivity.class);
                    startActivity(i);
                }
                else if(id<0) {

                    Intent i = new Intent(OverTimeMainActivity.this,SundayOverTimeActivity.class);
                    startActivity(i);
                }
                else if(id==0){
                    Intent i = new Intent(OverTimeMainActivity.this,SundayOverTimeActivity.class);
                    startActivity(i);
                }
            }
        });
    }

}