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

import com.example.kumarGroup.CategoryFollowUpModel;
import com.example.kumarGroup.CustomerFollowUpModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.FollowUpSubmitModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowUpMainActivity extends AppCompatActivity {

    Spinner spnFollowUpBooking,spnFollowUpDelivery,spnFollowUpPaymentCollection,
            spnFollowUpAddgestingCustomer,spnFollowUpSellLost;

    SearchableSpinner  spnFollowUpCategrory,spnFollowUpPerson;

    EditText edtFollowUpVisitReason,edtFollowUpBookingAmount,edtFollowUpModelName,
            edtFollowUpPaymentCollection,edtFollowUpSetVisit,edtFollowUpSellLost;

    Button btnFollowsUpSubmit;

    List<String> category = new ArrayList<>();
    List<String> categoryId = new ArrayList<>();

    List<String> customer = new ArrayList<>();
    List<String> customerId = new ArrayList<>();

    String cid , cust_id, cust_name;
    String data, state;

    String AddgestingCustomer;
    String[] addgestingCustomer1 = {"Addgesting Customer","Yes","No"};

    String SellLost;
    String[] Selllost1 = {"Sell Lost","Yes","No"};

    String BookingType1;
    String[] BookingType = {"Booking","Yes","No"};

    String Delivery1;
    String[] Delivery = {"Delivery","Yes","No"};

    String PaymentCollection;
    String[] PaymentCollection1= {"Payment Collection","Yes","No"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    String Date2;

    int day,month,year;
    Calendar mcurrentdate;
    String dayOfWeek;
    String dayCount,sell_lost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_main);
        getSupportActionBar().setTitle("General Visit");

        /*Allocate Memory*/
        spnFollowUpCategrory=findViewById(R.id.spnFollowUpCategrory);
        spnFollowUpPerson=findViewById(R.id.spnFollowUpPerson);
        spnFollowUpBooking=findViewById(R.id.spnFollowUpBooking);
        spnFollowUpDelivery=findViewById(R.id.spnFollowUpDelivery);
        edtFollowUpVisitReason=findViewById(R.id.edtFollowUpVisitReason);
        edtFollowUpBookingAmount=findViewById(R.id.edtFollowUpBookingAmount);
        edtFollowUpModelName=findViewById(R.id.edtFollowUpModelName);
        btnFollowsUpSubmit=findViewById(R.id.btnFollowsUpSubmit);
        edtFollowUpPaymentCollection=findViewById(R.id.edtFollowUpPaymentCollection);
        spnFollowUpPaymentCollection=findViewById(R.id.spnFollowUpPaymentCollection);
        spnFollowUpAddgestingCustomer=findViewById(R.id.spnFollowUpAddgestingCustomer);

        edtFollowUpSetVisit=findViewById(R.id.edtFollowUpSetVisit);
        spnFollowUpSellLost=findViewById(R.id.spnFollowUpSellLost);
        edtFollowUpSellLost=findViewById(R.id.edtFollowUpSellLost);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        spnFollowUpCategrory.setTitle("Select Category");
        spnFollowUpPerson.setTitle("Select Customer");

        edtFollowUpModelName.setVisibility(View.GONE);
        edtFollowUpModelName.setVisibility(View.GONE);
        edtFollowUpPaymentCollection.setVisibility(View.GONE);

        edtFollowUpSetVisit.setFocusable(false);

        edtFollowUpSetVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FollowUpMainActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
                        edtFollowUpSetVisit.setText(year+"-"+mt+"-"+dy);
                       // Date2 = year+"-"+monthofYear+"-"+dy ;
                        Date2 = year+"-"+mt+"-"+dy ;
                        Log.d("NewDate", "onDateSet: "+Date2);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        WebService.getClient().getCategoryFollowUp().enqueue(new Callback<CategoryFollowUpModel>() {
            @Override
            public void onResponse(Call<CategoryFollowUpModel> call, Response<CategoryFollowUpModel> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        category.clear();
                        categoryId.clear();

                      /*  category.add("Select Category");
                        categoryId.add("0");*/

                        Log.d("category", response.body().toString());
                        for (int i = 0; i < response.body().getData().size(); i++)
                        {
                            category.add(response.body().getData().get(i).getCat_list());
                            categoryId.add(response.body().getData().get(i).getCat_id());
                        }
                        Log.d("category1", category.toString());
                        ArrayAdapter adapter2 = new ArrayAdapter(FollowUpMainActivity.this, android.R.layout.simple_spinner_dropdown_item, category);
                        spnFollowUpCategrory.setAdapter(adapter2);

                        spnFollowUpCategrory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                data = category.get(position);
                                cid = categoryId.get(position);

                               WebService.getClient().getCustomerFollowUp(cid,emp).enqueue(new Callback<CustomerFollowUpModel>() {
                                   @Override
                                   public void onResponse(Call<CustomerFollowUpModel> call, Response<CustomerFollowUpModel> response) {
                                        if(response.isSuccessful()){
                                            if(response.body()!=null){
                                                customer.clear();
                                                customerId.clear();

                                                /*customer.add("Select Customer");
                                                customerId.add("0");*/
                                               for (int i=0;i< response.body().getCat().size();i++){
                                                   customer.add(response.body().getCat().get(i).getFname());
                                                   customerId.add(response.body().getCat().get(i).getCust_id());
                                               }
                                                ArrayAdapter adapter_per = new ArrayAdapter(FollowUpMainActivity.this, android.R.layout.simple_spinner_dropdown_item, customer);
                                                spnFollowUpPerson.setAdapter(adapter_per);

                                                spnFollowUpPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        cust_id = customerId.get(position);
                                                        cust_name = customer.get(position);
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                            }
                                        }
                                   }

                                   @Override
                                   public void onFailure(Call<CustomerFollowUpModel> call, Throwable t) {

                                   }
                               });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryFollowUpModel> call, Throwable t) {

            }
        });

        ArrayAdapter sellLost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Selllost1);
        spnFollowUpSellLost.setAdapter(sellLost);

        spnFollowUpSellLost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SellLost = Selllost1[position];
                if(Selllost1[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtFollowUpSellLost.setVisibility(View.VISIBLE);
                    edtFollowUpSetVisit.setVisibility(View.GONE);
                }
                else{
                    //BookingType1 = "1";
                    edtFollowUpSellLost.setVisibility(View.GONE);
                    edtFollowUpSetVisit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,BookingType);
        spnFollowUpBooking.setAdapter(adapter);

        spnFollowUpBooking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingType1 = BookingType[position];

                if(BookingType[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtFollowUpBookingAmount.setVisibility(View.VISIBLE);
                }
                else{
                    //BookingType1 = "1";
                    edtFollowUpBookingAmount.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,addgestingCustomer1);
        spnFollowUpAddgestingCustomer.setAdapter(arrayAdapter);

        spnFollowUpAddgestingCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddgestingCustomer = addgestingCustomer1[position];
                if(addgestingCustomer1[position]=="Yes"){
                    edtFollowUpSetVisit.setVisibility(View.GONE);
                    spnFollowUpSellLost.setVisibility(View.GONE);
                    edtFollowUpSetVisit.setVisibility(View.GONE);
                }else {
                    edtFollowUpSetVisit.setVisibility(View.VISIBLE);
                    spnFollowUpSellLost.setVisibility(View.VISIBLE);
                    edtFollowUpSetVisit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter deliveryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Delivery);
        spnFollowUpDelivery.setAdapter(deliveryAdapter);

        spnFollowUpDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Delivery1 = Delivery[position];
                if(Delivery[position]=="Yes"){
                    edtFollowUpModelName.setVisibility(View.VISIBLE);
                    edtFollowUpSetVisit.setVisibility(View.GONE);
                    spnFollowUpSellLost.setVisibility(View.GONE);
                    spnFollowUpAddgestingCustomer.setVisibility(View.GONE);
                }
                else{
                    edtFollowUpModelName.setVisibility(View.GONE);
                    edtFollowUpSetVisit.setVisibility(View.VISIBLE);
                    spnFollowUpSellLost.setVisibility(View.VISIBLE);
                    spnFollowUpAddgestingCustomer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter PaymentCollectionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,PaymentCollection1);
        spnFollowUpPaymentCollection.setAdapter(PaymentCollectionAdapter);

        spnFollowUpPaymentCollection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PaymentCollection = PaymentCollection1[position];
                if(PaymentCollection1[position]=="Yes"){
                    edtFollowUpPaymentCollection.setVisibility(View.VISIBLE);
                }
                else{
                    edtFollowUpPaymentCollection.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnFollowsUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= new ProgressDialog(FollowUpMainActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                WebService.getClient().getFollowUpSubmit(emp,cid,cust_id,
                        edtFollowUpVisitReason.getText().toString(),
                        BookingType1,
                        edtFollowUpBookingAmount.getText().toString(),
                        Delivery1,
                        edtFollowUpModelName.getText().toString(),
                        PaymentCollection,
                        edtFollowUpPaymentCollection.getText().toString(),
                        Date2,
                        dayCount,
                        AddgestingCustomer,
                        SellLost,
                        edtFollowUpSellLost.getText().toString()).enqueue(new Callback<FollowUpSubmitModel>() {
                    @Override
                    public void onResponse(Call<FollowUpSubmitModel> call, Response<FollowUpSubmitModel> response) {
                        progressDialog.dismiss();
                        Toast.makeText(FollowUpMainActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(FollowUpMainActivity.this, FoDashbord.class);
                        startActivity(i);
                       /* edtFollowUpVisitReason.setText("");
                        edtFollowUpBookingAmount.setText("");
                        edtFollowUpModelName.setText("");
                        edtFollowUpPaymentCollection.setText("");
                        Delivery1 = Delivery[0];
                        BookingType1 = BookingType[0];
                        PaymentCollection = PaymentCollection1[0];*/
                    }

                    @Override
                    public void onFailure(Call<FollowUpSubmitModel> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }
}