package com.example.kumarGroup.ScoreBoard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiryEmployeeListModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreBoardMainActivity extends AppCompatActivity {

    Spinner spnSelectEmployeeSB;
    Button btnSubmitSB;

    List<String> category = new ArrayList<>();
    List<String> categoryId = new ArrayList<>();

    String stateId, state,stateId_Main;

    SharedPreferences sp_stateIdSB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board_main);

        getSupportActionBar().setTitle("Score Board");


        spnSelectEmployeeSB= findViewById(R.id.spnSelectEmployeeSB);
        btnSubmitSB= findViewById(R.id.btnSubmitSB);

        SharedPreferences sp1;
        String emp;

        ProgressDialog progressDialog;

        sp_stateIdSB = getSharedPreferences("ScoreBoardId",MODE_PRIVATE);


        WebService.getClient().getEmployeeList().enqueue(new Callback<ViewInquiryEmployeeListModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewInquiryEmployeeListModel> call,
                                   @NotNull Response<ViewInquiryEmployeeListModel> response)
            {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        category.clear();
                        categoryId.clear();

                        category.add("Select Employee");
                        categoryId.add("0");

                        Log.d("Banks", response.body().toString());

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            category.add(response.body().getData().get(i).getState());
                            categoryId.add(response.body().getData().get(i).getId());
                        }

                        ArrayAdapter adapter2 = new ArrayAdapter(ScoreBoardMainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, category);
                        spnSelectEmployeeSB.setAdapter(adapter2);

                        spnSelectEmployeeSB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                // state = category.get(position);
                                // stateId = categoryId.get(position);

                                if(category.get(position).equals("Select Employee")){
                                    stateId="";
                                }
                                else{
                                    stateId = categoryId.get(position);
                                }
                                state = category.get(position);
                                //  stateId = categoryId.get(position);

                                sp_stateIdSB.edit().putString("StateId", stateId).apply();
                                sp_stateIdSB.edit().putString("StateIdName", state).apply();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                Log.d("notSelectItem", "onNothingSelected: Not select item");
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ViewInquiryEmployeeListModel> call, @NotNull Throwable t) {

                Toast.makeText(ScoreBoardMainActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

            }
        });


        btnSubmitSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(stateId==""){
                    Utils.showErrorToast(ScoreBoardMainActivity.this,"Please select Employee");
                }else{
                    Intent i = new Intent(ScoreBoardMainActivity.this, DateFilterActivity.class);
                    startActivity(i);
                }*/
                Intent i = new Intent(ScoreBoardMainActivity.this, ScoreBoardCategory.class);
                startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FoDashbord.Score_Board_Check = false;
    }
}