package com.example.kumarGroup.Workshop.General;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.kumarGroup.R;

import java.util.Calendar;

public class ComplainBoxDateFilterActivity extends AppCompatActivity {

    EditText EdtDateOne,EdtDateTwo;
    Button btnNext;

    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_box_date_filter);

        getSupportActionBar().setTitle("Complaint Box");

        //AllocateMemory
        EdtDateOne = findViewById(R.id.EdtDateOne);
        EdtDateTwo = findViewById(R.id.EdtDateTwo);
        btnNext = findViewById(R.id.btnNext);

        EdtDateOne.setFocusable(false);
        EdtDateTwo.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        EdtDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ComplainBoxDateFilterActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        //EdtDateOne.setText(mt+"/"+dy+"/"+year);
                        // EdtWalletSalarySlipDateOne.setText(year+"/"+mt+"/"+dy);
                        //edtSearchBBListReqDate.setText(cdate);
                        EdtDateOne.setText(year +"-" + mt + "-" + dy);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        EdtDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ComplainBoxDateFilterActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                       // EdtDateTwo.setText(mt+"/"+dy+"/"+year);
                        // EdtWalletSalarySlipDateTwo.setText(year+"/"+mt+"/"+dy);
                        //  edtSearchBBListReqDate.setText(cdate);

                        EdtDateTwo.setText(year +"-" + mt + "-" + dy);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplainBoxDateFilterActivity.this, ComplainRegisterActivity.class);
                i.putExtra("StartingDateOne",EdtDateOne.getText().toString());
                i.putExtra("EndingDateTwo",EdtDateTwo.getText().toString());
                startActivity(i);
            }
        });


    }
}