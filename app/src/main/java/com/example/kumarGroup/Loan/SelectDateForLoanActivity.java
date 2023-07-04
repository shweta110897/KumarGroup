package com.example.kumarGroup.Loan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kumarGroup.R;

import java.util.Calendar;

public class SelectDateForLoanActivity extends AppCompatActivity {

    EditText EdtDLoanDateOne,EdtDLoanDateTwo;
    Button btnDLoanNext;

    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_for_loan);
        getSupportActionBar().setTitle("Loan");

        EdtDLoanDateOne=findViewById(R.id.EdtDLoanDateOne);
        EdtDLoanDateTwo=findViewById(R.id.EdtDLoanDateTwo);
        btnDLoanNext=findViewById(R.id.btnDLoanNext);

        EdtDLoanDateOne.setFocusable(false);
        EdtDLoanDateTwo.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        EdtDLoanDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SelectDateForLoanActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        monthofYear = monthofYear+1;
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
                        EdtDLoanDateOne.setText(mt+"/"+dy+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        EdtDLoanDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SelectDateForLoanActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        monthofYear = monthofYear+1;


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
                        EdtDLoanDateTwo.setText(mt+"/"+dy+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btnDLoanNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EdtDLoanDateOne.getText().length()==0){
                    Toast.makeText(SelectDateForLoanActivity.this, "Select Date", Toast.LENGTH_SHORT).show();
                    EdtDLoanDateOne.setError("Select Date");

                }else if (EdtDLoanDateTwo.getText().length()==0){
                    Toast.makeText(SelectDateForLoanActivity.this, "Select Date", Toast.LENGTH_SHORT).show();
                    EdtDLoanDateTwo.setError("Select Date");
                }else {
                    Intent i = new Intent(SelectDateForLoanActivity.this, DisplayLoanActivity.class);
                    i.putExtra("StartingDateOneLoan",EdtDLoanDateOne.getText().toString());
                    i.putExtra("EndingDateTwoLoan",EdtDLoanDateTwo.getText().toString());
                    startActivity(i);
                }

            }
        });

    }
}