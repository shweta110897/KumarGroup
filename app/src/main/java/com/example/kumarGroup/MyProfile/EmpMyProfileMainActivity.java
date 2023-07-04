package com.example.kumarGroup.MyProfile;

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

import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiryEmployeeListModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpMyProfileMainActivity extends AppCompatActivity {


    Spinner spnSelectEmployeeMyProfile;
    Button btnSubmitMyProfile;

    List<String> category = new ArrayList<>();
    List<String> categoryId = new ArrayList<>();

    String stateId, state,stateId_Main;

    SharedPreferences sp_stateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_my_profile_main);

        getSupportActionBar().setTitle("View Profile");

        spnSelectEmployeeMyProfile= findViewById(R.id.spnSelectEmployeeMyProfile);
        btnSubmitMyProfile= findViewById(R.id.btnSubmitMyProfile);

        SharedPreferences sp1;
        String emp;

        ProgressDialog progressDialog;

        sp_stateId = getSharedPreferences("MyProfileId",MODE_PRIVATE);

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

                        ArrayAdapter adapter2 = new ArrayAdapter(EmpMyProfileMainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, category);
                        spnSelectEmployeeMyProfile.setAdapter(adapter2);

                        spnSelectEmployeeMyProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                                sp_stateId.edit().putString("StateId", stateId).apply();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }


            }

            @Override
            public void onFailure(@NotNull Call<ViewInquiryEmployeeListModel> call, @NotNull Throwable t) {


                Toast.makeText(EmpMyProfileMainActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

            }
        });


        btnSubmitMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(EmpMyProfileMainActivity.this, CategoryMyProfileOneActivity.class);
                Intent i = new Intent(EmpMyProfileMainActivity.this, FourButtonMainActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                startActivity(i);
            }
        });


    }
}