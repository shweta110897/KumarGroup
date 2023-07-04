package com.example.kumarGroup.EmployeeTracker.ak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.kumarGroup.All_data_show_tracking_model;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterDateLocation extends AppCompatActivity {
    Totallocation_Adapter totallocationAdapter;
    String id;
    RecyclerView filter_recyclerView;


    EditText EdtWalletSalarySlipDateOne,EdtWalletSalarySlipDateTwo;
    Button btnWalletSalarySlip;

    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_date_location);


        filter_recyclerView = findViewById(R.id.filter_recyclerView);
        SharedPreferences sharedPreferences = getSharedPreferences("emp_location_id",MODE_PRIVATE);
        id = sharedPreferences.getString("id","");


        getSupportActionBar().setTitle("Employee Tracking");

        //AllocateMemory
        EdtWalletSalarySlipDateOne = findViewById(R.id.EdtWalletSalarySlipDateOne);
        EdtWalletSalarySlipDateTwo = findViewById(R.id.EdtWalletSalarySlipDateTwo);
        btnWalletSalarySlip = findViewById(R.id.btnWalletSalarySlip);

        EdtWalletSalarySlipDateOne.setFocusable(false);
        EdtWalletSalarySlipDateTwo.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        EdtWalletSalarySlipDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FilterDateLocation.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        monthofYear = monthofYear+1;
                       /* Date date = new Date(year, monthofYear,dayOfMonth);
                       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String cdate = formatter.format(date);*/

                        String mt,dy;   //local variable
                        if(monthofYear<10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        }
                        else{
                            mt=String.valueOf(monthofYear);
                        }

                        if(dayOfMonth<10) {
                            dy = "0" + dayOfMonth;
                        }
                        else{
                            dy = String.valueOf(dayOfMonth);
                        }


                        EdtWalletSalarySlipDateOne.setText(year+"-"+mt+"-"+dy);
                        //2021-07-03
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        EdtWalletSalarySlipDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FilterDateLocation.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        monthofYear = monthofYear+1;
                       /* Date date = new Date(year, monthofYear,dayOfMonth);
                       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String cdate = formatter.format(date);*/

                        String mt,dy;   //local variable
                        if(monthofYear<10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        }
                        else{
                            mt=String.valueOf(monthofYear);
                        }

                        if(dayOfMonth<10) {
                            dy = "0" + dayOfMonth;
                        }
                        else{
                            dy = String.valueOf(dayOfMonth);
                        }
                        //09/05/2020 date format
                        EdtWalletSalarySlipDateTwo.setText(year+"-"+mt+"-"+dy);
                        // EdtWalletSalarySlipDateTwo.setText(year+"/"+mt+"/"+dy);
                        //  edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btnWalletSalarySlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(FilterDateLocation.this,
                        WalletAttendance.class);
                i.putExtra("StartingDateOne",EdtWalletSalarySlipDateOne.getText().toString());
                i.putExtra("EndingDateTwo",EdtWalletSalarySlipDateTwo.getText().toString());
                startActivity(i);*/

                if (EdtWalletSalarySlipDateOne.getText().toString().equals("") && EdtWalletSalarySlipDateTwo.getText().toString().equals("")){
                        Utils.showErrorToast(FilterDateLocation.this,"Please select Date");
                }
                else {
                    WebService.getClient().get_filter_emp_location(id, EdtWalletSalarySlipDateOne.getText().toString(), EdtWalletSalarySlipDateTwo.getText().toString()).enqueue(new Callback<All_data_show_tracking_model>() {
                        @Override
                        public void onResponse(Call<All_data_show_tracking_model> call, Response<All_data_show_tracking_model> response) {
                            if (0 != response.body().getData().size()) {
                                totallocationAdapter = new Totallocation_Adapter(FilterDateLocation.this, response.body().getData());
                                filter_recyclerView.setAdapter(totallocationAdapter);
                            } else {
                                Utils.showErrorToast(FilterDateLocation.this, "No Data Found");
                            }
                        }

                        @Override
                        public void onFailure(Call<All_data_show_tracking_model> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }
}