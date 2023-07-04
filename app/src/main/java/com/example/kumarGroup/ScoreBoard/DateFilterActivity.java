package com.example.kumarGroup.ScoreBoard;

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

public class DateFilterActivity extends AppCompatActivity {


    EditText EdtScoreBoardDateOne,EdtScoreBoardDateTwo;
    Button btnScoreBoardDateNext;

    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_filter);

        getSupportActionBar().setTitle("Score Board");


        EdtScoreBoardDateOne=findViewById(R.id.EdtScoreBoardDateOne);
        EdtScoreBoardDateTwo=findViewById(R.id.EdtScoreBoardDateTwo);
        btnScoreBoardDateNext=findViewById(R.id.btnScoreBoardDateNext);

        EdtScoreBoardDateOne.setFocusable(false);
        EdtScoreBoardDateTwo.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        EdtScoreBoardDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateFilterActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                        // tdate:2021-05-13
                        //EdtWalletOverTimeDateOne.setText(mt+"/"+dy+"/"+year);
                        EdtScoreBoardDateOne.setText(year+"-"+mt+"-"+dy);
                        //edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        EdtScoreBoardDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateFilterActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                        //fdate:2021-05-01
                        //  EdtWalletOverTimeDateTwo.setText(mt+"/"+dy+"/"+year);
                        EdtScoreBoardDateTwo.setText(year+"-"+mt+"-"+dy);
                        //  edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btnScoreBoardDateNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DateFilterActivity.this,
                        DisplayScoreBoardActivity.class);

                i.putExtra("StartingDateOne",EdtScoreBoardDateOne.getText().toString());
                i.putExtra("EndingDateTwo",EdtScoreBoardDateTwo.getText().toString());

                startActivity(i);
            }
        });


    }
}