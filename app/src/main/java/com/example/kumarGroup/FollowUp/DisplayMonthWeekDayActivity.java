package com.example.kumarGroup.FollowUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.kumarGroup.SubmitDetailShowFollowUpModel;
import com.example.kumarGroup.WebService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayMonthWeekDayActivity extends AppCompatActivity {

    EditText edtMWDEmployeeName,edtMWDCustomer,edtMWDVisitReason,
            edtMWDBookAmount,edtMWDModelName,edtMWDPaymentCollection,
            edtMWDSetVisit,edtMWDSellLost;

    Spinner spnMWDBooking,spnMWDDelivery,spnMWDPaymentCollection,spnMWDAddgcust,spnMWDSellLost;

    Button btnMWDSubmit;

    String catname,customername;
    String catId, SnameId,id;

    String sellLost;
    String[] sellLost1={"Sell Lost","Yes","No"};

    String BookingType1;
    String[] BookingType = {"Booking","Yes","No"};

    String Delivery1;
    String[] Delivery = {"Delivery","Yes","No"};

    String PaymentCollection;
    String[] PaymentCollection1= {"Payment Collection","Yes","No"};

    String AddgestingCustomer_mdw;
    String[] addgestingCustomer1_mdw = {"Addgesting Customer","Yes","No"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    String Date2;

    int day,month,year;
    Calendar mcurrentdate;
    String dayOfWeek;
    String dayCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_month_week_day);

        catname = getIntent().getStringExtra("category");
        customername = getIntent().getStringExtra("customer_name");
        catId = getIntent().getStringExtra("catId");
        SnameId = getIntent().getStringExtra("sname");
        id = getIntent().getStringExtra("id");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        /** Memory Allocation*/

        edtMWDEmployeeName= findViewById(R.id.edtMWDEmployeeName);
        edtMWDCustomer= findViewById(R.id.edtMWDCustomer);
        edtMWDVisitReason= findViewById(R.id.edtMWDVisitReason);
        edtMWDBookAmount= findViewById(R.id.edtMWDBookAmount);
        edtMWDModelName= findViewById(R.id.edtMWDModelName);
        edtMWDPaymentCollection= findViewById(R.id.edtMWDPaymentCollection);

        spnMWDBooking= findViewById(R.id.spnMWDBooking);
        spnMWDDelivery= findViewById(R.id.spnMWDDelivery);
        spnMWDPaymentCollection= findViewById(R.id.spnMWDPaymentCollection);
        edtMWDSetVisit= findViewById(R.id.edtMWDSetVisit);

        btnMWDSubmit= findViewById(R.id.btnMWDSubmit);
        spnMWDAddgcust= findViewById(R.id.spnMWDAddgcust);
        edtMWDSellLost= findViewById(R.id.edtMWDSellLost);
        spnMWDSellLost= findViewById(R.id.spnMWDSellLost);

        /**------------------------------------------------------------*/

        edtMWDEmployeeName.setText(customername);
        edtMWDCustomer.setText(catname);

        edtMWDEmployeeName.setFocusable(false);
        edtMWDCustomer.setFocusable(false);
        edtMWDSetVisit.setFocusable(false);

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        edtMWDSetVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DisplayMonthWeekDayActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Date date = new Date(year, monthofYear, dayOfMonth-1);
                        dayOfWeek = simpledateformat.format(date);
                        Log.d("dayOfWeek", "onDateSet: "+dayOfWeek);

                        if(dayOfWeek.equals("Monday"))
                        {
                            dayCount="0";
                        }

                        if(dayOfWeek.equals("Tuesday"))
                        {
                            dayCount="1";
                        }
                        if(dayOfWeek.equals("Wednesday"))
                        {
                            dayCount="2";
                        }
                        if(dayOfWeek.equals("Tuesday"))
                        {
                            dayCount="3";
                        }
                        if(dayOfWeek.equals("Friday"))
                        {
                            dayCount="4";
                        }
                        if(dayOfWeek.equals("Saturday"))
                        {
                            dayCount="5";
                        }
                        if(dayOfWeek.equals("Sunday"))
                        {
                            dayCount="6";
                        }

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

                        //2020-09-22
                        //edtAddLoanDate.setText(mt+"/"+dy+"/"+year);
                        edtMWDSetVisit.setText(year+"-"+mt+"-"+dy);
                       // Date2 = year+"-"+monthofYear+"-"+dy ;
                        Date2 = year+"-"+mt+"-"+dy ;
                        Log.d("NewDate", "onDateSet: "+Date2);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        ArrayAdapter adapterSellLost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,sellLost1);
        spnMWDSellLost.setAdapter(adapterSellLost);

        spnMWDSellLost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sellLost= sellLost1[position];
                if(sellLost1[position]=="Yes"){
                    edtMWDSetVisit.setVisibility(View.GONE);
                    edtMWDSellLost.setVisibility(View.VISIBLE);
                }else {
                    edtMWDSetVisit.setVisibility(View.VISIBLE);
                    edtMWDSellLost.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,addgestingCustomer1_mdw);
        spnMWDAddgcust.setAdapter(arrayAdapter);

        spnMWDAddgcust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddgestingCustomer_mdw = addgestingCustomer1_mdw[position];
                if(addgestingCustomer1_mdw[position]=="Yes"){
                    edtMWDSetVisit.setVisibility(View.GONE);
                }else {
                    edtMWDSetVisit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,BookingType);
        spnMWDBooking.setAdapter(adapter);

        spnMWDBooking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingType1 = BookingType[position];
                if(BookingType[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtMWDBookAmount.setVisibility(View.VISIBLE);
                }
                else{
                    //BookingType1 = "1";
                    edtMWDBookAmount.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter deliveryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Delivery);
        spnMWDDelivery.setAdapter(deliveryAdapter);

        spnMWDDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Delivery1 = Delivery[position];
                if(Delivery[position]=="Yes"){
                    edtMWDModelName.setVisibility(View.VISIBLE);
                    edtMWDSetVisit.setVisibility(View.GONE);
                }
                else{
                    edtMWDModelName.setVisibility(View.GONE);
                    edtMWDSetVisit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter PaymentCollectionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,PaymentCollection1);
        spnMWDPaymentCollection.setAdapter(PaymentCollectionAdapter);

        spnMWDPaymentCollection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PaymentCollection = PaymentCollection1[position];
                if(PaymentCollection1[position]=="Yes"){
                    edtMWDPaymentCollection.setVisibility(View.VISIBLE);
                }
                else{
                    edtMWDPaymentCollection.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

     //   Toast.makeText(this, "id"+id, Toast.LENGTH_SHORT).show();

        btnMWDSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= new ProgressDialog(DisplayMonthWeekDayActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                WebService.getClient().getShowSubmitFollowUp(emp,catId,SnameId,
                        edtMWDVisitReason.getText().toString(),
                        BookingType1,
                        edtMWDBookAmount.getText().toString(),
                        Delivery1,
                        edtMWDModelName.getText().toString(),
                        PaymentCollection,
                        edtMWDPaymentCollection.getText().toString(),
                        Date2,
                        dayCount,
                        id,
                        AddgestingCustomer_mdw,
                        sellLost,
                        edtMWDSellLost.getText().toString()).enqueue(new Callback<SubmitDetailShowFollowUpModel>() {
                    @Override
                    public void onResponse(Call<SubmitDetailShowFollowUpModel> call, Response<SubmitDetailShowFollowUpModel> response) {
                        progressDialog.dismiss();
                        Toast.makeText(DisplayMonthWeekDayActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(DisplayMonthWeekDayActivity.this, FoDashbord.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<SubmitDetailShowFollowUpModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(DisplayMonthWeekDayActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}