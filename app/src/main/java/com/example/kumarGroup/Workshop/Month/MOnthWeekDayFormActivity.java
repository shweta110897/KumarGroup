package com.example.kumarGroup.Workshop.Month;

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

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WSMonthWeekDailyEditFormModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MOnthWeekDayFormActivity extends AppCompatActivity {


    EditText edt_CustomerNAme,edt_CustomerMobile,edt_NextRemark,edt_NextDate;

    Spinner spn_Remark;

    Button Btn_Submit;

    String Remark;
    String[] Remark_val = {"Select Remark","Clear","Next Remark"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    String MobileNo,CuName, Pid;

    Calendar mcurrentdate;
    int day, month, year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_onth_week_day_form);


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");



        Pid=getIntent().getStringExtra("Pid");
        CuName=getIntent().getStringExtra("CuName");
        MobileNo=getIntent().getStringExtra("MobileNo");


        edt_CustomerNAme=findViewById(R.id.edt_CustomerNAme);
        edt_CustomerMobile=findViewById(R.id.edt_CustomerMobile);
        edt_NextRemark=findViewById(R.id.edt_NextRemark);
        edt_NextDate=findViewById(R.id.edt_NextDate);

        spn_Remark=findViewById(R.id.spn_Remark);
        Btn_Submit=findViewById(R.id.Btn_Submit);


        edt_CustomerNAme.setText(CuName);
        edt_CustomerMobile.setText(MobileNo);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Remark_val);
        spn_Remark.setAdapter(adapter);

        spn_Remark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Remark = Remark_val[position];

                if(Remark.equals("Select Remark") || Remark.equals("Clear")){
                    edt_NextRemark.setVisibility(View.GONE);
                    edt_NextDate.setVisibility(View.GONE);
                }
                else {
                    Remark ="Yes";
                    edt_NextRemark.setVisibility(View.VISIBLE);
                    edt_NextDate.setVisibility(View.VISIBLE);
                }
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


        edt_NextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MOnthWeekDayFormActivity.this,
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
                        edt_NextDate.setText(year + "-" + mt + "-" + dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_NextDate.setFocusable(false);


        Btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MOnthWeekDayFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



                WebService.getClient().getWsMonthWeekDayFormEdit(emp,
                        MobileNo,
                        Remark,
                        edt_NextRemark.getText().toString(),
                        edt_NextDate.getText().toString(),
                        Pid).enqueue(new Callback<WSMonthWeekDailyEditFormModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WSMonthWeekDailyEditFormModel> call,
                                           @NotNull Response<WSMonthWeekDailyEditFormModel> response) {
                        progressDialog.dismiss();

                        Toast.makeText(MOnthWeekDayFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(MOnthWeekDayFormActivity.this, FoDashbord.class);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<WSMonthWeekDailyEditFormModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();

                    }
                });
            }
        });


    }
}