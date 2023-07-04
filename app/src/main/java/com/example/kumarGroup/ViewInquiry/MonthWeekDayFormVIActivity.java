package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.DailyMonthWeekVIModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthWeekDayFormVIActivity extends AppCompatActivity {


    EditText edtCategoryNameMWDVI,edtEmployeeNameMWDVI,edtDescriptionMWDVI,
            edtBookAmountMWDVI,edtModelNameMWDVI,edtSellLostMWDVI,edtNextVisitMWDVI;

    Spinner spnBookingMWDVI,spnDeliveryMWDVI,spnSellLostMWDVI,spnBookingTYpe,
            spnFollowUpTypeMWDVI,
            spnGenInquiryMWDVI;

    Button btnSubmitMWDVI;

    String sellLostMWDVI;
    String[] sellLost1_MWDVI={"Sell Lost","Yes","No"};

    String BookingTypeMWDVI;
    String[] BookingType1_MWDVI = {"Booking","Yes","No"};

    String DeliveryMWDVI;
    String[] Delivery1_MWDVI = {"Delivery","Yes","No"};


    String TypeInq;
    String[] Type_inq = {"Select Type","Hot","Cold"};


    String FollowUptype;
    String[] FollowUpType_list={"Select FollowUp","Call", "Visit","Visit with Dealer"};


    String Inquiry;
    String[] Inquiry_option = {"Drop Inquiry","Yes","No"};



    SharedPreferences sp1;
    String emp;

    int day,month,year;
    Calendar mcurrentdate;


    String Name,Category,cat_id,sname,id,MobileNo,Vemp;

    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_week_day_form_v_i);


        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        Name= getIntent().getStringExtra("Name");
        Category=getIntent().getStringExtra("Category");
        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        //  id=getIntent().getStringExtra("id");
        MobileNo=getIntent().getStringExtra("MobileNo");
        Vemp=getIntent().getStringExtra("Vemp");


        edtCategoryNameMWDVI=findViewById(R.id.edtCategoryNameMWDVI);
        edtEmployeeNameMWDVI=findViewById(R.id.edtEmployeeNameMWDVI);
        edtDescriptionMWDVI=findViewById(R.id.edtDescriptionMWDVI);
        edtBookAmountMWDVI=findViewById(R.id.edtBookAmountMWDVI);
        edtModelNameMWDVI=findViewById(R.id.edtModelNameMWDVI);
        edtSellLostMWDVI=findViewById(R.id.edtSellLostMWDVI);
        edtNextVisitMWDVI=findViewById(R.id.edtNextVisitMWDVI);

        spnBookingMWDVI=findViewById(R.id.spnBookingMWDVI);
        spnDeliveryMWDVI=findViewById(R.id.spnDeliveryMWDVI);
        spnSellLostMWDVI=findViewById(R.id.spnSellLostMWDVI);
        spnBookingTYpe=findViewById(R.id.spnBookingTYpe);
        spnFollowUpTypeMWDVI=findViewById(R.id.spnFollowUpTypeMWDVI);

        spnGenInquiryMWDVI=findViewById(R.id.spnGenInquiryMWDVI);

        btnSubmitMWDVI=findViewById(R.id.btnSubmitMWDVI);


        edtCategoryNameMWDVI.setText(Category);
        edtEmployeeNameMWDVI.setText(Name);



        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );



        edtNextVisitMWDVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MonthWeekDayFormVIActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                        String mt, dy;   //local variable
                        if (monthofYear < 10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        } else {
                            mt = String.valueOf(monthofYear);
                        }

                        if (dayOfMonth < 10) {
                            dy = "0" + dayOfMonth;
                        } else {
                            dy = String.valueOf(dayOfMonth);
                        }
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        // 2021-05-08
                        // edtNextVisitMWDVI.setText(mt + "/" + dy + "/" + year);
                        // edtNextVisitMWDVI.setText(year + "-" + mt + "-" + dy);
                        edtNextVisitMWDVI.setText(year+"-"+mt+"-"+dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            }
        });


        edtNextVisitMWDVI.setFocusable(false);


         ArrayAdapter DropInquiry = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Inquiry_option);
        spnGenInquiryMWDVI.setAdapter(DropInquiry);

        spnGenInquiryMWDVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Inquiry = Inquiry_option[position];

                if(Inquiry_option[position].equals("Yes")){
                    edtDescriptionMWDVI.setVisibility(View.GONE);
                    spnBookingMWDVI.setVisibility(View.GONE);
                    spnDeliveryMWDVI.setVisibility(View.GONE);
                    spnSellLostMWDVI.setVisibility(View.GONE);

                    spnBookingTYpe.setVisibility(View.GONE);
                    spnFollowUpTypeMWDVI.setVisibility(View.VISIBLE);

                    edtNextVisitMWDVI.setVisibility(View.GONE);

                }else{
                    edtDescriptionMWDVI.setVisibility(View.VISIBLE);
                    spnBookingMWDVI.setVisibility(View.VISIBLE);
                    spnDeliveryMWDVI.setVisibility(View.VISIBLE);
                    spnSellLostMWDVI.setVisibility(View.VISIBLE);

                    spnBookingTYpe.setVisibility(View.VISIBLE);
                    spnFollowUpTypeMWDVI.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Type_inq);
        spnBookingTYpe.setAdapter(arrayAdapter1);

        spnBookingTYpe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if( Type_inq[position]=="Select Type"){
                    TypeInq = " ";
                }
                else{
                    TypeInq = Type_inq[position];

                }
                //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,
                BookingType1_MWDVI);
        spnBookingMWDVI.setAdapter(adapter);

        spnBookingMWDVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingTypeMWDVI = BookingType1_MWDVI[position];
                if(BookingType1_MWDVI[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtBookAmountMWDVI.setVisibility(View.VISIBLE);
                }
                else{
                    //BookingType1 = "1";
                    edtBookAmountMWDVI.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter deliveryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,
                Delivery1_MWDVI);
        spnDeliveryMWDVI.setAdapter(deliveryAdapter);

        spnDeliveryMWDVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DeliveryMWDVI = Delivery1_MWDVI[position];
                if(Delivery1_MWDVI[position]=="Yes"){
                    edtModelNameMWDVI.setVisibility(View.VISIBLE);
                    edtNextVisitMWDVI.setVisibility(View.GONE);
                    spnBookingTYpe.setVisibility(View.GONE);
                }
                else{
                    edtModelNameMWDVI.setVisibility(View.GONE);
                    edtNextVisitMWDVI.setVisibility(View.VISIBLE);
                    spnBookingTYpe.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        ArrayAdapter adapterSellLost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                sellLost1_MWDVI);
        spnSellLostMWDVI.setAdapter(adapterSellLost);

        spnSellLostMWDVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sellLostMWDVI= sellLost1_MWDVI[position];
                if(sellLost1_MWDVI[position]=="Yes"){
                    edtNextVisitMWDVI.setVisibility(View.GONE);
                    edtSellLostMWDVI.setVisibility(View.VISIBLE);
                    spnBookingTYpe.setVisibility(View.GONE);
                }else {
                    edtNextVisitMWDVI.setVisibility(View.VISIBLE);
                    edtSellLostMWDVI.setVisibility(View.GONE);
                    spnBookingTYpe.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


         ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                FollowUpType_list);
        spnFollowUpTypeMWDVI.setAdapter(adapterFollowUp);

        spnFollowUpTypeMWDVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FollowUptype = FollowUpType_list[position];

                /*if( FollowUpType_list[position]=="Select FollowUp"){
                    FollowUptype = "";
                }
                else{
                    FollowUptype = FollowUpType_list[position];

                }*/
              //  TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnSubmitMWDVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Inquiry.equals("Yes")){

                    progressDialog = new ProgressDialog(MonthWeekDayFormVIActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                    WebService.getClient().getDailyWeekMonthVI(emp,
                            cat_id,
                            TypeInq,
                            sname,
                            edtDescriptionMWDVI.getText().toString(),
                            BookingTypeMWDVI,
                            edtBookAmountMWDVI.getText().toString(),
                            DeliveryMWDVI,
                            edtModelNameMWDVI.getText().toString(),
                            edtNextVisitMWDVI.getText().toString(),
                            sellLostMWDVI,
                            edtSellLostMWDVI.getText().toString(),
                            Vemp,
                            FollowUptype,
                            Inquiry
                    ).enqueue(new Callback<DailyMonthWeekVIModel>() {
                        @Override
                        public void onResponse(@NotNull Call<DailyMonthWeekVIModel> call,
                                               @NotNull Response<DailyMonthWeekVIModel> response) {
                            progressDialog.dismiss();

                            assert response.body() != null;
                            Toast.makeText(MonthWeekDayFormVIActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MonthWeekDayFormVIActivity.this,
                                    FoDashbord.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<DailyMonthWeekVIModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(MonthWeekDayFormVIActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else {


                    if (edtCategoryNameMWDVI.getText().toString().equals("")) {
                        edtCategoryNameMWDVI.setError("Please Enter Category Name");
                    }


                    if (edtEmployeeNameMWDVI.getText().toString().equals("")) {
                        edtEmployeeNameMWDVI.setError("Please Enter Employee Name");
                    }

                    if (edtDescriptionMWDVI.getText().toString().equals("")) {
                        edtDescriptionMWDVI.setError("Please Enter Visit Reason");
                    }


                    if (BookingTypeMWDVI.equals("Booking")) {
                        Utils.showErrorToast(MonthWeekDayFormVIActivity.this,
                                "Please Select Yes or No ");
                    }

                    if (DeliveryMWDVI.equals("Delivery")) {
                        Utils.showErrorToast(MonthWeekDayFormVIActivity.this,
                                "Please Select Yes or No");
                    }

                    if (sellLostMWDVI.equals("Sell Lost")) {
                        Utils.showErrorToast(MonthWeekDayFormVIActivity.this,
                                "Please Select Yes or No ");
                    }


                    if (FollowUptype.equals("Select FollowUp")) {
                        Utils.showErrorToast(MonthWeekDayFormVIActivity.this, "Please Enter FollowUp Type");
                    }


                    if (edtNextVisitMWDVI.getText().toString().equals("")) {
                        edtNextVisitMWDVI.setError("Please Enter Visit Date");
                    }


                    if (!edtCategoryNameMWDVI.getText().toString().equals("") &&
                            !edtEmployeeNameMWDVI.getText().toString().equals("") &&
                            !edtDescriptionMWDVI.getText().toString().equals("")
                         && !FollowUptype.equals("Select FollowUp")
                    ) {


                        progressDialog = new ProgressDialog(MonthWeekDayFormVIActivity.this);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.progress_dialog);
                        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                        WebService.getClient().getDailyWeekMonthVI(emp,
                                cat_id,
                                TypeInq,
                                sname,
                                edtDescriptionMWDVI.getText().toString(),
                                BookingTypeMWDVI,
                                edtBookAmountMWDVI.getText().toString(),
                                DeliveryMWDVI,
                                edtModelNameMWDVI.getText().toString(),
                                edtNextVisitMWDVI.getText().toString(),
                                sellLostMWDVI,
                                edtSellLostMWDVI.getText().toString(),
                                Vemp,
                                FollowUptype,
                                Inquiry
                        ).enqueue(new Callback<DailyMonthWeekVIModel>() {
                            @Override
                            public void onResponse(@NotNull Call<DailyMonthWeekVIModel> call,
                                                   @NotNull Response<DailyMonthWeekVIModel> response) {
                                progressDialog.dismiss();

                                assert response.body() != null;
                                Toast.makeText(MonthWeekDayFormVIActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(MonthWeekDayFormVIActivity.this,
                                        FoDashbord.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(@NotNull Call<DailyMonthWeekVIModel> call, @NotNull Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(MonthWeekDayFormVIActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
            }
        });



    }
}