package com.example.kumarGroup.ScoreBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ScoreBoardDisplayModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayScoreBoardActivity extends AppCompatActivity {

    RecyclerView rclv_ScoreBoardDisplay;

    String StartingDateOne,EndingDateTwo;

    SharedPreferences sp_stateIdSB;

    String emp_id,emp_name;

    ProgressDialog progressDialog;

    TextView DataBankCount,DataBankPoint;

    SharedPreferences sp1;

    SharedPreferences spDate;

    int jk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_score_board);


        getSupportActionBar().setTitle("Score Board");


        rclv_ScoreBoardDisplay=findViewById(R.id.rclv_ScoreBoardDisplay);
        rclv_ScoreBoardDisplay.setHasFixedSize(true);
        rclv_ScoreBoardDisplay.setLayoutManager(new LinearLayoutManager(this));


        DataBankCount=findViewById(R.id.DataBankCount);
        DataBankPoint=findViewById(R.id.DataBankPoint);

        StartingDateOne=getIntent().getStringExtra("StartingDateOne");
        EndingDateTwo=getIntent().getStringExtra("EndingDateTwo");

        sp_stateIdSB = getSharedPreferences("ScoreBoardId",MODE_PRIVATE);
        spDate = getSharedPreferences("SbDate",MODE_PRIVATE);
        spDate.edit().putString("Sdate",StartingDateOne).apply();
        spDate.edit().putString("Edate",EndingDateTwo).apply();

        if (FoDashbord.Score_Board_Check){
            emp_id = sp_stateIdSB.getString("StateId","");
            emp_name = sp_stateIdSB.getString("StateIdName","");
        }else {
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");
            emp_name=sp1.getString("emp_name","");
        }
        Log.d("TAG", "onCreate: Check_empid_scroboaed "+emp_id);

        progressDialog = new ProgressDialog(DisplayScoreBoardActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getScoreBoardDisplay(emp_id,
                StartingDateOne,EndingDateTwo).enqueue(new Callback<ScoreBoardDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<ScoreBoardDisplayModel> call,
                                   @NotNull Response<ScoreBoardDisplayModel> response) {

                progressDialog.dismiss();
                if (response.body().getCat().size() == 0) {
                    Utils.showErrorToast(DisplayScoreBoardActivity.this,"No Data Found");

                } else {
                    /*for(int i =0; i< response.body().getCat().size();i++){

                    }*/

                    Double s = response.body().getCat().get(response.body().getCat().size()-1).getTotalpoint();

                    DataBankCount.setText(""+response.body().getCat().get(response.body().getCat().size()-1).getTotalcount());
                   // DataBankPoint.setText(""+response.body().getCat().get(response.body().getCat().size()-1).getTotalpoint());
                    DataBankPoint.setText(String.valueOf(s));

                 //   Log.d("res", "onResponse: "+response.body().getCat());

                   // DataBankCount.setText(""+response.body().getCat().get(response.body().getCat().size()));


                    DisplayScoreBoardAdapter adapter= new DisplayScoreBoardAdapter(DisplayScoreBoardActivity.this,
                            response.body().getCat());
                    rclv_ScoreBoardDisplay.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ScoreBoardDisplayModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("onF", "onFailure: "+t.getCause());
                Toast.makeText(DisplayScoreBoardActivity.this,"server issue"+t.getCause(),Toast.LENGTH_LONG).show();

            }
        });

    }
}