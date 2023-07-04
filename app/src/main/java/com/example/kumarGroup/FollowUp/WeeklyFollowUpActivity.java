package com.example.kumarGroup.FollowUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WeeklyFollowUpModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyFollowUpActivity extends AppCompatActivity {

    RecyclerView rclvWeeklyFollowUp;
    ArrayList<String> Weekday = new ArrayList<>();

    TextView txtWeeklyFollow;

    SharedPreferences sp1;
    String emp;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_follow_up);
        getSupportActionBar().setTitle("Weekly Inquiry");

        Weekday.add("Monday");
        Weekday.add("Tuesday");
        Weekday.add("Wednesday");
        Weekday.add("Thursday");
        Weekday.add("Friday");
        Weekday.add("Saturday");
        Weekday.add("Sunday");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        rclvWeeklyFollowUp=findViewById(R.id.rclvWeeklyFollowUp);
        txtWeeklyFollow=findViewById(R.id.txtWeeklyFollow);

        rclvWeeklyFollowUp.setHasFixedSize(true);
        rclvWeeklyFollowUp.setLayoutManager(new LinearLayoutManager(this));

        progressDialog= new ProgressDialog(WeeklyFollowUpActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getWeeklyFollowUp(emp).enqueue(new Callback<WeeklyFollowUpModel>() {
            @Override
            public void onResponse(@NotNull Call<WeeklyFollowUpModel> call, @NotNull Response<WeeklyFollowUpModel> response) {
                progressDialog.dismiss();

                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(WeeklyFollowUpActivity.this,"Data not Available");
                    // txtWeeklyFollow.setText("THIS MONTH DATA NOT FOUND");
                }
                else{
                    // txtWeeklyFollow.setVisibility(View.GONE);
                    WeekFollowUpAdapter adapter = new WeekFollowUpAdapter(WeeklyFollowUpActivity.this,response.body().getDetail());
                    rclvWeeklyFollowUp.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<WeeklyFollowUpModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
             //   Toast.makeText(WeeklyFollowUpActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
            }
        });




       /* WebService.getClient().getDayFollowUp(emp).enqueue(new Callback<DayFollowUpModel>() {
            @Override
            public void onResponse(@NotNull Call<DayFollowUpModel> call, @NotNull Response<DayFollowUpModel> response) {
                progressDialog.dismiss();

                if(response.body().getDetail().size()==0){
                    Utils.showErrorToast(WeeklyFollowUpActivity.this,"Data not Available");
                   // txtWeeklyFollow.setText("THIS MONTH DATA NOT FOUND");
                }
                else{
                   // txtWeeklyFollow.setVisibility(View.GONE);
                    WeekFollowUpAdapter adapter = new WeekFollowUpAdapter(WeeklyFollowUpActivity.this,response.body().getDetail());
                    rclvWeeklyFollowUp.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DayFollowUpModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(WeeklyFollowUpActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
            }
        });*/

      /*  WeekFollowUpAdapter adapter = new WeekFollowUpAdapter(WeeklyFollowUpActivity.this,Weekday);
        rclvWeeklyFollowUp.setAdapter(adapter);*/

    }
}