package com.example.kumarGroup.DealstageAK;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ScoreBoard.PerfomanceMarketingActivity;
import com.example.kumarGroup.ScoreBoard.PerfomanceMarketingActivityINQ;
import com.example.kumarGroup.ViewInquiry.MonthlyViewInqOneActivity;
import com.example.kumarGroup.ViewInquiryDealStage.PerfomanceActivityINQ;

import java.util.Calendar;

public class DeliveryButtonActivity extends AppCompatActivity {

    EditText EdtWalletSalarySlipDateOne,EdtWalletSalarySlipDateTwo;
    Button btnWalletSalarySlip;

    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;
    public static boolean perfomanceFlag = false;
    public static boolean perfomanceFlag_INQ = false;
    public static boolean perfomanceMarketFlag = false;
    public static boolean perfomanceMarketFlag_INQ = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_button2);


        getSupportActionBar().setTitle("My Inquiry Date filter");

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(DeliveryButtonActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(DeliveryButtonActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                if (perfomanceFlag){
                    Intent i = new Intent(DeliveryButtonActivity.this, PerfomanceActivity.class);
                    i.putExtra("StartingDateOne", EdtWalletSalarySlipDateOne.getText().toString());
                    i.putExtra("EndingDateTwo", EdtWalletSalarySlipDateTwo.getText().toString());
                    startActivity(i);

                }
                else if (perfomanceFlag_INQ){
                    Intent i = new Intent(DeliveryButtonActivity.this, PerfomanceActivityINQ.class);
                    i.putExtra("StartingDateOne", EdtWalletSalarySlipDateOne.getText().toString());
                    i.putExtra("EndingDateTwo", EdtWalletSalarySlipDateTwo.getText().toString());
                    startActivity(i);
                }else if (perfomanceMarketFlag){
                    Intent i = new Intent(DeliveryButtonActivity.this, PerfomanceMarketingActivity.class);
                    i.putExtra("StartingDateOne", EdtWalletSalarySlipDateOne.getText().toString());
                    i.putExtra("EndingDateTwo", EdtWalletSalarySlipDateTwo.getText().toString());
                    startActivity(i);
                }else if (perfomanceMarketFlag_INQ){
                    Intent i = new Intent(DeliveryButtonActivity.this, PerfomanceMarketingActivityINQ.class);
                    i.putExtra("StartingDateOne", EdtWalletSalarySlipDateOne.getText().toString());
                    i.putExtra("EndingDateTwo", EdtWalletSalarySlipDateTwo.getText().toString());
                    startActivity(i);
                }
                else {
                    MonthlyViewInqOneActivity.myINQdeliveryFlag = true;
                    Intent i = new Intent(DeliveryButtonActivity.this, MonthlyViewInqOneActivity.class);
                    i.putExtra("StartingDateOne", EdtWalletSalarySlipDateOne.getText().toString());
                    i.putExtra("EndingDateTwo", EdtWalletSalarySlipDateTwo.getText().toString());
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DeliveryButtonActivity.perfomanceFlag = false;
        DeliveryButtonActivity.perfomanceFlag_INQ = false;
    }
}