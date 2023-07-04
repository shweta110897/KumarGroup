package com.example.kumarGroup.VendorDashboard;

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

public class ViewDocumentMainActivity extends AppCompatActivity {


    EditText Edt_viewDocDateOne,Edt_viewDocDateTwo;
    Button btn_viewDoc;

    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_document_main);


        //AllocateMemory
        Edt_viewDocDateOne = findViewById(R.id.Edt_viewDocDateOne);
        Edt_viewDocDateTwo = findViewById(R.id.Edt_viewDocDateTwo);
        btn_viewDoc = findViewById(R.id.btn_viewDoc);

        Edt_viewDocDateOne.setFocusable(false);
        Edt_viewDocDateTwo.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;


        Edt_viewDocDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ViewDocumentMainActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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

                        Edt_viewDocDateOne.setText(year+"-"+mt+"-"+dy);
                        // EdtWalletSalarySlipDateOne.setText(year+"/"+mt+"/"+dy);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        Edt_viewDocDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ViewDocumentMainActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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

                        Edt_viewDocDateTwo.setText(year+"-"+mt+"-"+dy);
                        // EdtWalletSalarySlipDateOne.setText(year+"/"+mt+"/"+dy);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btn_viewDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewDocumentMainActivity.this, ViewDocDisplayActivity.class);
                i.putExtra("StartingDateOne",Edt_viewDocDateOne.getText().toString());
                i.putExtra("EndingDateTwo",Edt_viewDocDateTwo.getText().toString());
                startActivity(i);
            }
        });


    }
}