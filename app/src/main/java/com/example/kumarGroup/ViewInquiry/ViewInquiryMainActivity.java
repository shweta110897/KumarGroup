package com.example.kumarGroup.ViewInquiry;

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
import com.example.kumarGroup.ViewInqUserGenListCateModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewInquiryMainActivity extends AppCompatActivity {

    Spinner spnBookingSelectEmployee;
    Button btnSubmitViewInquiry;


    List<String> category = new ArrayList<>();
    List<String> categoryId = new ArrayList<>();

    String stateId, state,stateId_Main;

    SharedPreferences sp_stateId;

    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inquiry_main);

        getSupportActionBar().setTitle("View Inquiry");

        spnBookingSelectEmployee=findViewById(R.id.spnBookingSelectEmployee);
        btnSubmitViewInquiry=findViewById(R.id.btnSubmitViewInquiry);


        sp_stateId = getSharedPreferences("StateId",MODE_PRIVATE);
        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");



        WebService.getClient().getViewUserInqGenList(emp).enqueue(new Callback<ViewInqUserGenListCateModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewInqUserGenListCateModel> call,
                                   @NotNull Response<ViewInqUserGenListCateModel> response) {


                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        category.clear();
                        categoryId.clear();

                        category.add("Select Employee");
                        categoryId.add("0");

                        Log.d("Banks", response.body().toString());
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            category.add(response.body().getData().get(i).getEmp_name());
                            categoryId.add(response.body().getData().get(i).getId());

                        }

                        ArrayAdapter adapter2 = new ArrayAdapter(ViewInquiryMainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, category);
                        spnBookingSelectEmployee.setAdapter(adapter2);


                        spnBookingSelectEmployee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                // state = category.get(position);
                                // stateId = categoryId.get(position);

                                if(category.get(position).equals("Select Employee")){
                                    stateId="";
                                    stateId_Main=emp;
//                                    stateId=emp;  /*sp1 = getSharedPreferences("Login",MODE_PRIVATE);
//                                    emp=sp1.getString("emp_id","");*/
                                    sp_stateId.edit().putString("StateId", stateId).apply();
                                    sp_stateId.edit().putString("StateId1", stateId_Main).apply();
                                }
                                else{
                                    stateId = categoryId.get(position);
                                    stateId_Main="";
                                }
                                state = category.get(position);
                                //  stateId = categoryId.get(position);

                                sp_stateId.edit().putString("StateId", stateId).apply();
                                sp_stateId.edit().putString("StateId1", stateId_Main).apply();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ViewInqUserGenListCateModel> call,
                                  @NotNull Throwable t) {

                Toast.makeText(ViewInquiryMainActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

            }
        });




        btnSubmitViewInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Slected_EMPID",stateId);
                editor.putString("Slected_EMPID1",stateId_Main);
                editor.putString("Slected_EMPNAME",state);
                editor.apply();

                //Toast.makeText(ViewInquiryMainActivity.this, "empid "+stateId+" name "+state, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ViewInquiryMainActivity.this,ViewInqAllCatActivity.class);
                i.putExtra("state",state);
                i.putExtra("stateId",stateId);
                startActivity(i);
            }
        });

    }
}