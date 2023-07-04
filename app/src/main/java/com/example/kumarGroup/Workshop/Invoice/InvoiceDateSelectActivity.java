package com.example.kumarGroup.Workshop.Invoice;

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

public class InvoiceDateSelectActivity extends AppCompatActivity {


    EditText EdtInvoiceDateOne,EdtInvoiceDateTwo;
    Button btnInvoiceNext;


    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_date_select);

        getSupportActionBar().setTitle("Invoice");

        EdtInvoiceDateOne=findViewById(R.id.EdtInvoiceDateOne);
        EdtInvoiceDateTwo=findViewById(R.id.EdtInvoiceDateTwo);

        btnInvoiceNext=findViewById(R.id.btnInvoiceNext);


        EdtInvoiceDateOne.setFocusable(false);
        EdtInvoiceDateTwo.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;



        EdtInvoiceDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InvoiceDateSelectActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                        EdtInvoiceDateOne.setText(year+"-"+mt+"-"+dy);
                        //edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        EdtInvoiceDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InvoiceDateSelectActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                        EdtInvoiceDateTwo.setText(year+"-"+mt+"-"+dy);
                        //  edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btnInvoiceNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InvoiceDateSelectActivity.this, InvoiceDataDisplayActivity.class);
                i.putExtra("StartingDateOne",EdtInvoiceDateOne.getText().toString());
                i.putExtra("EndingDateTwo",EdtInvoiceDateTwo.getText().toString());
                startActivity(i);
            }
        });

    }



}