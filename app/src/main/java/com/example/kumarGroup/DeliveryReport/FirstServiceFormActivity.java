package com.example.kumarGroup.DeliveryReport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.FirstServiceAddModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WsEmpListModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstServiceFormActivity extends AppCompatActivity
{

    EditText edt_FollowUpDate_FSForm,edt_ServiceDate_FSForm,edt_ComplianNumber_FSForm;
    Spinner spn_Mechanic_FSForm,spn_SelectType_FSForm;
    Button btn_Submit_FSForm;

    String id,MobileNo;

    String SelectType;
    String[] Select_Type = {"Select Type", "Follow Up Date", "Service Date","Drop Service"};

    Calendar mcurrentdate;
    int day, month, year;

    List<String> EmpName = new ArrayList<>();
    List<String> EmpID = new ArrayList<>();

    String EMP_Name, EMP_Id,cuid;



    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_service_form);


        edt_FollowUpDate_FSForm= findViewById(R.id.edt_FollowUpDate_FSForm);
        edt_ServiceDate_FSForm= findViewById(R.id.edt_ServiceDate_FSForm);
        edt_ComplianNumber_FSForm= findViewById(R.id.edt_ComplianNumber_FSForm);
        spn_Mechanic_FSForm= findViewById(R.id.spn_Mechanic_FSForm);
        spn_SelectType_FSForm= findViewById(R.id.spn_SelectType_FSForm);
        btn_Submit_FSForm= findViewById(R.id.btn_Submit_FSForm);


        id= getIntent().getStringExtra("id");
        MobileNo= getIntent().getStringExtra("MobileNo");
        cuid= getIntent().getStringExtra("cuid");

        ArrayAdapter consumerSkim = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Select_Type);
        spn_SelectType_FSForm.setAdapter(consumerSkim);

        spn_SelectType_FSForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectType = Select_Type[position];
                if (SelectType.equals("Follow Up Date")) {
                    edt_FollowUpDate_FSForm.setVisibility(View.VISIBLE);
                    edt_ServiceDate_FSForm.setVisibility(View.GONE);
                    edt_ComplianNumber_FSForm.setVisibility(View.GONE);
                    spn_Mechanic_FSForm.setVisibility(View.GONE);

                } else if(SelectType.equals("Service Date")) {
                    edt_FollowUpDate_FSForm.setVisibility(View.GONE);

                    edt_ServiceDate_FSForm.setVisibility(View.VISIBLE);
                    edt_ComplianNumber_FSForm.setVisibility(View.VISIBLE);
                    spn_Mechanic_FSForm.setVisibility(View.VISIBLE);
                }else{
                    edt_FollowUpDate_FSForm.setVisibility(View.GONE);

                    edt_ServiceDate_FSForm.setVisibility(View.GONE);
                    edt_ComplianNumber_FSForm.setVisibility(View.GONE);
                    spn_Mechanic_FSForm.setVisibility(View.GONE);
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



        edt_FollowUpDate_FSForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FirstServiceFormActivity.this,
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
                      //  edt_FollowUpDate_FSForm.setText(mt + "/" + dy + "/" + year);
                        edt_FollowUpDate_FSForm.setText(year + "-" + mt + "-" + dy);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_FollowUpDate_FSForm.setFocusable(false);


        edt_ServiceDate_FSForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FirstServiceFormActivity.this,
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
                        //  edt_FollowUpDate_FSForm.setText(mt + "/" + dy + "/" + year);
                        edt_ServiceDate_FSForm.setText(year + "-" + mt + "-" + dy);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        edt_ServiceDate_FSForm.setFocusable(false);



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

                        ArrayAdapter adapterMake = new ArrayAdapter(FirstServiceFormActivity.this, android.R.layout.simple_spinner_dropdown_item, EmpName);
                        spn_Mechanic_FSForm.setAdapter(adapterMake);

                        spn_Mechanic_FSForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



        btn_Submit_FSForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(FirstServiceFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                WebService.getClient().getFirstServiceAdd(id,
                        MobileNo,
                        SelectType,
                        edt_FollowUpDate_FSForm.getText().toString(),
                        edt_ServiceDate_FSForm.getText().toString(),
                        EMP_Id,
                        edt_ComplianNumber_FSForm.getText().toString(),
                        cuid
                        ).enqueue(new Callback<FirstServiceAddModel>() {
                    @Override
                    public void onResponse(@NotNull Call<FirstServiceAddModel> call,
                                           @NotNull Response<FirstServiceAddModel> response) {

                        progressDialog.dismiss();
                        assert response.body() != null;

                        if(response.body().getSuccess()==true){
                            Toast.makeText(FirstServiceFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(FirstServiceFormActivity.this, FoDashbord.class);
                            startActivity(i);
                        }
                        else{
                            Utils.showErrorToast(FirstServiceFormActivity.this,""+response.body().getMsg());
                        }

                      //  Toast.makeText(FirstServiceFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(@NotNull Call<FirstServiceAddModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(FirstServiceFormActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}