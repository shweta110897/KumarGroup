package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.kumarGroup.CommonSearch;
import com.example.kumarGroup.Common_Search.CommonSearchActivity1;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Village;
import com.example.kumarGroup.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgeingViewInqActivity extends AppCompatActivity {

    Spinner spnSelectAgeingPeriod;
    Button btnSubmitAgeingViewInquiry;
    String[] agingPeriod_list={/*"Select Time period",*/"3 months", "6 Months","9 months", "12 Months","15 months", "18 Months","21 months", "24 Months"};
    String[] agingPeriodDays_list1={/*"Select Time period",*/"90", "180","270", "360","450", "540","630", "720"};
    String stateId, state,stateId_Main,Age,Day;
    List<String> countList=new ArrayList<>();

    SharedPreferences sp_stateId;

    SharedPreferences sp1;
    String emp,emp_id,cat_id;
    List<CommonSearch.commonSearch> commonSearch;
    List<CommonSearch.commonSearch> commonSearchCount3=new ArrayList<CommonSearch.commonSearch>();
    List<CommonSearch.commonSearch> commonSearchCount6=new ArrayList<CommonSearch.commonSearch>();
    List<CommonSearch.commonSearch> commonSearchCount9=new ArrayList<CommonSearch.commonSearch>();
    List<CommonSearch.commonSearch> commonSearchCount12=new ArrayList<CommonSearch.commonSearch>();
    List<CommonSearch.commonSearch> commonSearchCount15=new ArrayList<CommonSearch.commonSearch>();
    List<CommonSearch.commonSearch> commonSearchCount18=new ArrayList<CommonSearch.commonSearch>();
    List<CommonSearch.commonSearch> commonSearchCount21=new ArrayList<CommonSearch.commonSearch>();
    List<CommonSearch.commonSearch> commonSearchCount24=new ArrayList<CommonSearch.commonSearch>();

    ProgressDialog progressDialog;
    private ArrayList<Village> items=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ageing_view_inq);

        getSupportActionBar().setTitle("Ageing Period View Inquiry ");

        spnSelectAgeingPeriod=findViewById(R.id.spnSelectAgeingPeriod);
        btnSubmitAgeingViewInquiry=findViewById(R.id.btnSubmitAgeingViewInquiry);


        sp_stateId = getSharedPreferences("StateId",MODE_PRIVATE);
        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        if (FoDashbord.Common_Search_Check ){
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");
        }else {
            SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
            emp_id = sharedPreferencesS.getString("Slected_EMPID","");
        }

        SharedPreferences sharedPreferences = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        cat_id = sharedPreferences.getString("catId_eid","");
//        Toast.makeText(CommonSearchActivity.this, "OUt side api calllll", Toast.LENGTH_SHORT).show();
        progressDialog= new ProgressDialog(AgeingViewInqActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().CommonSearch(emp_id,cat_id,"","").enqueue(new Callback<CommonSearch>() {
            @Override
            public void onResponse(Call<CommonSearch> call, Response<CommonSearch> response) {

                commonSearch = response.body().getCat();

                for (int i = 0; i < commonSearch.size(); i++) {
                    int added = Integer.parseInt(commonSearch.get(i).getAdded());
                    if (added <= 90 && added > 0) {
                        commonSearchCount3.add(commonSearch.get(i));
                    }
                }
                countList.add(0, String.valueOf(commonSearchCount3.size()));
                Log.d("commonSearchCount3", String.valueOf(commonSearchCount3.size()));

                for (int i = 0; i < commonSearch.size(); i++) {
                    int added = Integer.parseInt(commonSearch.get(i).getAdded());
                    if (added <= 180 && added > 90) {
                        commonSearchCount6.add(commonSearch.get(i));
                    }
                }
                countList.add(1, String.valueOf(commonSearchCount6.size()));
                Log.d("commonSearchCount6", String.valueOf(commonSearchCount6.size()));
                for (int i = 0; i < commonSearch.size(); i++) {
                    int added = Integer.parseInt(commonSearch.get(i).getAdded());
                    if (added <= 270 && added > 180) {
                        commonSearchCount9.add(commonSearch.get(i));
                    }
                }
                countList.add(2, String.valueOf(commonSearchCount9.size()));
                Log.d("commonSearchCount9", String.valueOf(commonSearchCount9.size()));
                for (int i = 0; i < commonSearch.size(); i++) {
                    int added = Integer.parseInt(commonSearch.get(i).getAdded());
                    if (added <= 360 && added > 270) {
                        commonSearchCount12.add(commonSearch.get(i));
                    }
                }
                countList.add(3, String.valueOf(commonSearchCount12.size()));
                Log.d("commonSearchCount12", String.valueOf(commonSearchCount12.size()));
                for (int i = 0; i < commonSearch.size(); i++) {
                    int added = Integer.parseInt(commonSearch.get(i).getAdded());
                    if (added <= 450 && added > 360) {
                        commonSearchCount15.add(commonSearch.get(i));
                    }
                }
                countList.add(4, String.valueOf(commonSearchCount15.size()));
                Log.d("commonSearchCount15", String.valueOf(commonSearchCount15.size()));
                for (int i = 0; i < commonSearch.size(); i++) {
                    int added = Integer.parseInt(commonSearch.get(i).getAdded());
                    if (added <= 540 && added > 450) {
                        commonSearchCount18.add(commonSearch.get(i));
                    }
                }
                countList.add(5, String.valueOf(commonSearchCount18.size()));
                Log.d("commonSearchCount18", String.valueOf(commonSearchCount18.size()));
                for (int i = 0; i < commonSearch.size(); i++) {
                    int added = Integer.parseInt(commonSearch.get(i).getAdded());
                    if (added <= 630 && added > 540) {
                        commonSearchCount21.add(commonSearch.get(i));
                    }
                }
                countList.add(6, String.valueOf(commonSearchCount21.size()));
                Log.d("commonSearchCount21", String.valueOf(commonSearchCount21.size()));
                for (int i = 0; i < commonSearch.size(); i++) {
                    int added = Integer.parseInt(commonSearch.get(i).getAdded());
                    if (added <= 720 && added > 630) {
                        commonSearchCount24.add(commonSearch.get(i));
                    }
                }
                countList.add(7, String.valueOf(commonSearchCount24.size()));
                Log.d("commonSearchCount24", String.valueOf(commonSearchCount24.size()));

                progressDialog.dismiss();

                Log.d("commonSearchSize", String.valueOf(commonSearch.size()));
                Log.d("countListS", String.valueOf(countList));

                for (int i=0;i<countList.size();i++){
                    items.add(new Village(String.valueOf(i),agingPeriod_list[i] ,countList.get(i) ));
                }

                Log.d("items",String.valueOf(items));

                // Setting a Custom Adapter to the Spinner
                spnSelectAgeingPeriod.setAdapter(new AgeingAdapter(AgeingViewInqActivity.this,
                        items));


                spnSelectAgeingPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Handle spinner item selection here
                        Village clickedItem = (Village)
                                parent.getItemAtPosition(position);
                        Age = agingPeriod_list[position];
                        Day = agingPeriodDays_list1[position];

                        Log.e("Age==>>",Age);
                        Log.e("Day==>>",Day);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Handle case when nothing is selected
                    }
                });



            }

            @Override
            public void onFailure(Call<CommonSearch> call, Throwable t) {
                Log.d("TAG", "onFailure: processfail "+t.getMessage());
            }
        });

        btnSubmitAgeingViewInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Intent i = new Intent(AgeingViewInqActivity.this, CommonSearchActivity1.class);
                i.putExtra("Day",Day);
                i.putExtra("stateId",stateId);
                startActivity(i);
            }
        });

    }
}