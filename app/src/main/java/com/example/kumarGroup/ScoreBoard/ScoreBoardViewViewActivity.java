package com.example.kumarGroup.ScoreBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ScoreBoardView;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreBoardViewViewActivity extends AppCompatActivity {

    RecyclerView recyclerView_Score_Board_View_View;
    SharedPreferences spDate, sp1, sp_stateIdSB;
    String StartDate, EndDate, emp_id, sbname;
    ProgressDialog pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board_view_view);

        sbname = getIntent().getStringExtra("sbname");

        recyclerView_Score_Board_View_View = findViewById(R.id.recyclerView_Score_Board_View_View);

        if (FoDashbord.Score_Board_Check) {
            sp_stateIdSB = getSharedPreferences("ScoreBoardId", MODE_PRIVATE);
            emp_id = sp_stateIdSB.getString("StateId", "");
        } else {
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");

        }


        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

//        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
//        emp_id = sp1.getString("emp_id", "");

        spDate = getSharedPreferences("SbDate", MODE_PRIVATE);
        StartDate = spDate.getString("Sdate", "");
        EndDate = spDate.getString("Edate", "");

        Log.d("TAG", "onCreate: Check_Date " + emp_id + " " + StartDate + " " + EndDate + " " + sbname);

        WebService.getClient().ScoreBoardView(emp_id, StartDate, EndDate, sbname).enqueue(new Callback<ScoreBoardView>() {
            @Override
            public void onResponse(Call<ScoreBoardView> call, Response<ScoreBoardView> response) {

//                Log.d("TAG", "onResponse: Check_Date "+response.body().getCat().get(0).getCategory());
                pro.dismiss();
                if (response.body().getCat().size() == 0) {
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    ScoreBoardViewViewAdapter scoreBoardViewViewAdapter = new ScoreBoardViewViewAdapter(ScoreBoardViewViewActivity.this, ScoreBoardViewViewActivity.this, response.body().getCat());
                    recyclerView_Score_Board_View_View.setLayoutManager(new LinearLayoutManager(ScoreBoardViewViewActivity.this));
                    recyclerView_Score_Board_View_View.setAdapter(scoreBoardViewViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<ScoreBoardView> call, Throwable t) {

            }
        });

    }
}