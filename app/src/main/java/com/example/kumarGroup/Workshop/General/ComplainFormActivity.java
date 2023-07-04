package com.example.kumarGroup.Workshop.General;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.CompalainFormModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WsEmpListModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplainFormActivity extends AppCompatActivity {

    EditText edt_NextDateCF,edt_DescriptionCF;

    Spinner spn_ClearPend,spn_SelectMechanic;


    Button Btn_SubmitCF;

    String Remark;
    String[] Remark_val = {"Select Status","Clear","Pending"};

    ProgressDialog progressDialog;


    List<String> EmpName = new ArrayList<>();
    List<String> EmpID = new ArrayList<>();

    String EMP_Name, EMP_Id;

    SharedPreferences sp1;
    String emp;

    Calendar mcurrentdate;
    int day, month, year;

    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_form);

        getSupportActionBar().setTitle("Complaint Box");


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");

        edt_NextDateCF=findViewById(R.id.edt_NextDateCF);
        edt_DescriptionCF=findViewById(R.id.edt_DescriptionCF);
        spn_ClearPend=findViewById(R.id.spn_ClearPend);
        spn_SelectMechanic=findViewById(R.id.spn_SelectMechanic);
        Btn_SubmitCF=findViewById(R.id.Btn_SubmitCF);


        cid= getIntent().getStringExtra("cid");



        WebService.getClient().getWsEmpList().enqueue(new Callback<WsEmpListModel>() {
            @Override
            public void onResponse(@NotNull Call<WsEmpListModel> call, @NotNull Response<WsEmpListModel> response) {
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        EmpName.clear();
                        EmpID.clear();

                        EmpName.add("Select Mechanic");
                        EmpID.add("0");

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            EmpName.add(response.body().getData().get(i).getEname());
                            EmpID.add(response.body().getData().get(i).getId());
                        }

                        ArrayAdapter adapterMake = new ArrayAdapter(ComplainFormActivity.this, android.R.layout.simple_spinner_dropdown_item, EmpName);
                        spn_SelectMechanic.setAdapter(adapterMake);

                        spn_SelectMechanic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                EMP_Name = EmpName.get(position);
                                EMP_Id = EmpID.get(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<WsEmpListModel> call, @NotNull Throwable t) {

            }
        });





        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Remark_val);
        spn_ClearPend.setAdapter(adapter);

        spn_ClearPend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Remark = Remark_val[position];

                /*if(Remark.equals("Select Remark") || Remark.equals("Clear")){
                    edt_NextRemark.setVisibility(View.GONE);
                    edt_NextDate.setVisibility(View.GONE);
                }
                else {
                    Remark ="Yes";
                    edt_NextRemark.setVisibility(View.VISIBLE);
                    edt_NextDate.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);
        // month = month+1;


        edt_NextDateCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ComplainFormActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                        String mt, dy;   //local variable
                        if (monthofYear < 10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        } else {
                            mt = String.valueOf(monthofYear);
                        }

                        if (dayOfMonth < 10) {
                            dy = "0" + dayOfMonth;
                        } else {
                            dy = String.valueOf(dayOfMonth);
                        }
                        //2021-05-29
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        edt_NextDateCF.setText(year + "-" + mt + "-" + dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });

        edt_NextDateCF.setFocusable(false);



        Btn_SubmitCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressDialog= new ProgressDialog(ComplainFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



                WebService.getClient().getComplainFormSubmit(edt_NextDateCF.getText().toString(),
                       edt_DescriptionCF.getText().toString(),
                        Remark,
                        cid,
                        EMP_Id).enqueue(new Callback<CompalainFormModel>() {
                    @Override
                    public void onResponse(@NotNull Call<CompalainFormModel> call,
                                           @NotNull Response<CompalainFormModel> response) {
                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(ComplainFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(ComplainFormActivity.this, FoDashbord.class);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<CompalainFormModel> call,
                                          @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });


    }
}