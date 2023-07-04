package com.example.kumarGroup.ReportCollection;

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

import com.example.kumarGroup.DocBoxAddModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentCollecationFormActivity extends AppCompatActivity {

    EditText edtCategory,edtEmployeeName,edtVisitReason,edtDocCollection,edtSetVisit;
    Spinner spnPaymentCollection;

    Button btnSubmit;

    String PaymentCollection;
    String[] PaymentCollection1= {"Document Collection","Yes","No"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    int day,month,year;
    Calendar mcurrentdate;
    String dayOfWeek;
    String dayCount, MobileNo,add_type,add_id;

    String catname,customername;
    String catId, SnameId,id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_collecation_form);


        getSupportActionBar().setTitle("Document Box");


        catname = getIntent().getStringExtra("category");
        customername = getIntent().getStringExtra("customer_name");
        catId = getIntent().getStringExtra("catId");
        MobileNo = getIntent().getStringExtra("MobileNo");


        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");



        edtCategory=findViewById(R.id.edtCategory);
        edtEmployeeName=findViewById(R.id.edtEmployeeName);
        edtVisitReason=findViewById(R.id.edtVisitReason);
        edtDocCollection=findViewById(R.id.edtDocCollection);
        edtSetVisit=findViewById(R.id.edtSetVisit);
        spnPaymentCollection=findViewById(R.id.spnPaymentCollection);
        btnSubmit=findViewById(R.id.btnSubmit);


        edtCategory.setText(catname);
        edtEmployeeName.setText(customername);


        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        edtSetVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DocumentCollecationFormActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Date date = new Date(year, monthofYear, dayOfMonth-1);
                        dayOfWeek = simpledateformat.format(date);
                        Log.d("dayOfWeek", "onDateSet: "+dayOfWeek);



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
                        edtSetVisit.setText(year+"-"+mt+"-"+dy);
                        //Date2 = year+"-"+monthofYear+"-"+dy ;

                    }
                },year,month,day);
                datePickerDialog.show();
              //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });

        edtSetVisit.setFocusable(false);


        ArrayAdapter PaymentCollectionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,PaymentCollection1);
        spnPaymentCollection.setAdapter(PaymentCollectionAdapter);

        spnPaymentCollection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PaymentCollection = PaymentCollection1[position];

                if(PaymentCollection1[position]=="Yes"){
                    edtDocCollection.setVisibility(View.GONE);
                }
                else{
                    edtDocCollection.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog= new ProgressDialog(DocumentCollecationFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                WebService.getClient().getDocBoxAdd(catId,
                        emp,
                        edtVisitReason.getText().toString(),
                        edtSetVisit.getText().toString(),
                        PaymentCollection).enqueue(new Callback<DocBoxAddModel>() {
                    @Override
                    public void onResponse(@NotNull Call<DocBoxAddModel> call,
                                           @NotNull Response<DocBoxAddModel> response) {

                        progressDialog.dismiss();

                        assert response.body() != null;
                        Toast.makeText(DocumentCollecationFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(DocumentCollecationFormActivity.this, FoDashbord.class);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<DocBoxAddModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });

    }
}