package com.example.kumarGroup.WalletTraveling;

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

public class WalletTravelingDateActivity extends AppCompatActivity {

    EditText EdtWalletTravelingDateOne,EdtWalletTravelingDateTwo;
    Button btnWalletTraveling;

    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_traveling_date);


        EdtWalletTravelingDateOne = findViewById(R.id.EdtWalletTravelingDateOne);
        EdtWalletTravelingDateTwo = findViewById(R.id.EdtWalletTravelingDateTwo);
        btnWalletTraveling = findViewById(R.id.btnWalletTraveling);


        EdtWalletTravelingDateOne.setFocusable(false);
        EdtWalletTravelingDateTwo.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        EdtWalletTravelingDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(WalletTravelingDateActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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

                        //EdtWalletOverTimeDateOne.setText(mt+"/"+dy+"/"+year);
                        EdtWalletTravelingDateOne.setText(year+"-"+mt+"-"+dy);
                        //edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        EdtWalletTravelingDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(WalletTravelingDateActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                        EdtWalletTravelingDateTwo.setText(year+"-"+mt+"-"+dy);
                        //  edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btnWalletTraveling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletTravelingDateActivity.this, TravelingDataDisplayActivity.class);
                i.putExtra("StartingDateOne",EdtWalletTravelingDateOne.getText().toString());
                i.putExtra("EndingDateTwo",EdtWalletTravelingDateTwo.getText().toString());
                startActivity(i);
            }
        });

    }

}