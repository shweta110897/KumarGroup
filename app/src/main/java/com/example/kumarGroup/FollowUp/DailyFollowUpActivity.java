package com.example.kumarGroup.FollowUp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kumarGroup.DailyFollowUpModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyFollowUpActivity extends AppCompatActivity {

    TextView txtDailyFollowUpCount;
    LinearLayout lin_Main;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_follow_up);
        getSupportActionBar().setTitle("Daily Inquiry");

        lin_Main=findViewById(R.id.lin_Main);
        txtDailyFollowUpCount=findViewById(R.id.txtDailyFollowUpCount);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        Log.d("EMP_ID", "onCreate: "+emp);

        progressDialog= new ProgressDialog(DailyFollowUpActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


       WebService.getClient().getDailyFollowUp(emp).enqueue(new Callback<DailyFollowUpModel>()
       {
           @Override
           public void onResponse(Call<DailyFollowUpModel> call, Response<DailyFollowUpModel> response) {
               progressDialog.dismiss();
               if(response.body().getDetail().size()==0){
                   Utils.showErrorToast(DailyFollowUpActivity.this,"No Visit Available");
               }
               else{
                   txtDailyFollowUpCount.setText(String.valueOf(response.body().getDetail().get(0).getCount()));
                   Log.d("DailyID", "onResponse: "+response.body().getDetail().get(0).getId());

                   lin_Main.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent i = new  Intent(DailyFollowUpActivity.this,ShowMonthlyFUActivity.class);
                       i.putExtra("id",response.body().getDetail().get(0).getId());
                       startActivity(i);
                   }
                });
               }
           }

           @Override
           public void onFailure(Call<DailyFollowUpModel> call, Throwable t) {
               progressDialog.dismiss();
               Toast.makeText(DailyFollowUpActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }
}