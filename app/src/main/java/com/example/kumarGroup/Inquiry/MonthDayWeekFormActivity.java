package com.example.kumarGroup.Inquiry;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.kumarGroup.AddInquiryModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthDayWeekFormActivity extends AppCompatActivity {

    EditText edtCategoryNameMWDform,edtEmployeeNameMWDform,edtDescriptionMWDform,
            edtBookAmountMWDform,edtModelNameMWDform,edtSellLostMWDform,edtNextVisitMWDform;

    Spinner spnBookingMWDform,spnDeliveryMWDform,spnSellLostMWDform,spnBookingTYpeMWDform,
            spnFollowUpTypeMWD,
            spnGenInquiryMWD;

    Button btnSubmitMWDform;

    String sellLostMWDform;
    String[] sellLost1_MWDform={"Sell Lost","Yes","No"};

    String BookingTypeMWDform;
    String[] BookingType1_MWDform = {"Booking","Yes","No"};

    String DeliveryMWDform;
    String[] Delivery1_MWDform = {"Delivery","Yes","No"};


    String FollowUptype;
    String[] FollowUpType_list={"Select FollowUp","Call", "Visit","Visit with Dealer"};


    SharedPreferences sp1;
    String emp;

    int day,month,year;
    Calendar mcurrentdate;


    String Name,Category,cat_id,sname,id,MobileNo,Vemp;

    ProgressDialog progressDialog;

    String TypeInq;
    String[] Type_inq = {"Select Type","Hot","Cold"};

    String Inquiry;
    String[] Inquiry_option = {"Drop Inquiry","Yes","No"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_day_week_form);



        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        Name= getIntent().getStringExtra("customer_name");
        Category=getIntent().getStringExtra("category");
        cat_id=getIntent().getStringExtra("catId");
        sname=getIntent().getStringExtra("sname");
        //  id=getIntent().getStringExtra("id");
        MobileNo=getIntent().getStringExtra("MobileNo");
        Vemp=getIntent().getStringExtra("Vemp");


     //   Toast.makeText(this, ""+Name+" "+Category+" "+cat_id, Toast.LENGTH_SHORT).show();


        edtCategoryNameMWDform=findViewById(R.id.edtCategoryNameMWDform);
        edtEmployeeNameMWDform=findViewById(R.id.edtEmployeeNameMWDform);
        edtDescriptionMWDform=findViewById(R.id.edtDescriptionMWDform);
        edtBookAmountMWDform=findViewById(R.id.edtBookAmountMWDform);
        edtModelNameMWDform=findViewById(R.id.edtModelNameMWDform);
        edtSellLostMWDform=findViewById(R.id.edtSellLostMWDform);
        edtNextVisitMWDform=findViewById(R.id.edtNextVisitMWDform);

        spnBookingMWDform=findViewById(R.id.spnBookingMWDform);
        spnDeliveryMWDform=findViewById(R.id.spnDeliveryMWDform);
        spnSellLostMWDform=findViewById(R.id.spnSellLostMWDform);
        spnBookingTYpeMWDform=findViewById(R.id.spnBookingTYpeMWDform);
        spnFollowUpTypeMWD=findViewById(R.id.spnFollowUpTypeMWD);

        spnGenInquiryMWD=findViewById(R.id.spnGenInquiryMWD);

        btnSubmitMWDform=findViewById(R.id.btnSubmitMWDform);


        edtCategoryNameMWDform.setText(Category);
        edtEmployeeNameMWDform.setText(Name);



        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );



        edtNextVisitMWDform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MonthDayWeekFormActivity.this,
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
                        // edtNextVisitMWDform.setText(mt + "/" + dy + "/" + year);
                        // edtNextVisitMWDform.setText(year + "-" + mt + "-" + dy);
                        edtNextVisitMWDform.setText(year+"-"+mt+"-"+dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });


        edtNextVisitMWDform.setFocusable(false);

         ArrayAdapter DropInquiry = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Inquiry_option);
        spnGenInquiryMWD.setAdapter(DropInquiry);

        spnGenInquiryMWD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Inquiry = Inquiry_option[position];

                if(Inquiry_option[position].equals("Yes")){
                    edtDescriptionMWDform.setVisibility(View.GONE);
                    spnBookingMWDform.setVisibility(View.GONE);
                    spnDeliveryMWDform.setVisibility(View.GONE);
                    spnSellLostMWDform.setVisibility(View.GONE);

                    spnBookingTYpeMWDform.setVisibility(View.GONE);

                    spnFollowUpTypeMWD.setVisibility(View.VISIBLE);

                    edtNextVisitMWDform.setVisibility(View.GONE);

                }else{
                    edtDescriptionMWDform.setVisibility(View.VISIBLE);
                    spnBookingMWDform.setVisibility(View.VISIBLE);
                    spnDeliveryMWDform.setVisibility(View.VISIBLE);
                    spnSellLostMWDform.setVisibility(View.VISIBLE);

                    spnBookingTYpeMWDform.setVisibility(View.VISIBLE);
                    spnFollowUpTypeMWD.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





         ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Type_inq);
        spnBookingTYpeMWDform.setAdapter(arrayAdapter1);

        spnBookingTYpeMWDform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TypeInq = Type_inq[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,
                BookingType1_MWDform);
        spnBookingMWDform.setAdapter(adapter);

        spnBookingMWDform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingTypeMWDform = BookingType1_MWDform[position];
                if(BookingType1_MWDform[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtBookAmountMWDform.setVisibility(View.VISIBLE);
                }
                else{
                    //BookingType1 = "1";
                    edtBookAmountMWDform.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter deliveryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,
                Delivery1_MWDform);
        spnDeliveryMWDform.setAdapter(deliveryAdapter);

        spnDeliveryMWDform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DeliveryMWDform = Delivery1_MWDform[position];
                if(Delivery1_MWDform[position]=="Yes"){
                    edtModelNameMWDform.setVisibility(View.VISIBLE);
                    edtNextVisitMWDform.setVisibility(View.GONE);
                    spnBookingTYpeMWDform.setVisibility(View.GONE);

                }
                else{
                    edtModelNameMWDform.setVisibility(View.GONE);
                    edtNextVisitMWDform.setVisibility(View.VISIBLE);
                    spnBookingTYpeMWDform.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ArrayAdapter adapterSellLost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                sellLost1_MWDform);
        spnSellLostMWDform.setAdapter(adapterSellLost);

        spnSellLostMWDform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sellLostMWDform= sellLost1_MWDform[position];
                if(sellLost1_MWDform[position]=="Yes"){
                    edtNextVisitMWDform.setVisibility(View.GONE);
                    edtSellLostMWDform.setVisibility(View.VISIBLE);
                    spnBookingTYpeMWDform.setVisibility(View.GONE);
                }else {
                    edtNextVisitMWDform.setVisibility(View.VISIBLE);
                    edtSellLostMWDform.setVisibility(View.GONE);
                    spnBookingTYpeMWDform.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


          ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                FollowUpType_list);
        spnFollowUpTypeMWD.setAdapter(adapterFollowUp);

        spnFollowUpTypeMWD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



        btnSubmitMWDform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Inquiry.equals("Yes")) {

                    progressDialog = new ProgressDialog(MonthDayWeekFormActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    WebService.getClient().getAddInquiry(emp,
                            cat_id,
                            sname,
                            edtDescriptionMWDform.getText().toString(),
                            BookingTypeMWDform,
                            edtBookAmountMWDform.getText().toString(),
                            DeliveryMWDform,
                            edtModelNameMWDform.getText().toString(),
                            "",
                            "",
                            edtNextVisitMWDform.getText().toString(),
                            "",
                            "",
                            sellLostMWDform,
                            edtSellLostMWDform.getText().toString(),
                            TypeInq,
                            Vemp,
                            FollowUptype,
                            Inquiry
                    ).enqueue(new Callback<AddInquiryModel>() {
                        @Override
                        public void onResponse(@NotNull Call<AddInquiryModel> call,
                                               @NotNull Response<AddInquiryModel> response) {
                            progressDialog.dismiss();

                            assert response.body() != null;
                            Toast.makeText(MonthDayWeekFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MonthDayWeekFormActivity.this,
                                    InquiryMainActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<AddInquiryModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                        }
                    });
                }
                else {


                    if (edtCategoryNameMWDform.getText().toString().equals("")) {
                        edtCategoryNameMWDform.setError("Please Enter Category Name");
                    }

                    if (edtEmployeeNameMWDform.getText().toString().equals("")) {
                        edtEmployeeNameMWDform.setError("Please Enter Employee Name");
                    }

                    if (edtDescriptionMWDform.getText().toString().equals("")) {
                        edtDescriptionMWDform.setError("Please Enter Visit Reason");
                    }


                    if (BookingTypeMWDform.equals("Booking")) {
                        Utils.showErrorToast(MonthDayWeekFormActivity.this,
                                "Please Select Yes or No ");
                    }

                    if (DeliveryMWDform.equals("Delivery")) {
                        Utils.showErrorToast(MonthDayWeekFormActivity.this,
                                "Please Select Yes or No");
                    }

                    if (sellLostMWDform.equals("Sell Lost")) {
                        Utils.showErrorToast(MonthDayWeekFormActivity.this,
                                "Please Select Yes or No ");
                    }

                    if (FollowUptype.equals("Select FollowUp")) {
                        Utils.showErrorToast(MonthDayWeekFormActivity.this, "Please Enter FollowUp Type");
                    }


                    if (edtBookAmountMWDform.getText().toString().equals("")) {
                        edtBookAmountMWDform.setError("Please Enter Book Amount");
                    }

                    if (edtModelNameMWDform.getText().toString().equals("")) {
                        edtModelNameMWDform.setError("Please Enter Book Amount");
                    }

                    if (edtSellLostMWDform.getText().toString().equals("")) {
                        edtSellLostMWDform.setError("Please Enter Book Amount");
                    }

                    if (edtNextVisitMWDform.getText().toString().equals("")) {
                        edtNextVisitMWDform.setError("Please Enter Visit Date");
                    }


                    if (!edtCategoryNameMWDform.getText().toString().equals("") &&
                            !edtEmployeeNameMWDform.getText().toString().equals("")
                            && !edtDescriptionMWDform.getText().toString().equals("")
                        /* && !FollowUptype.equals("Select FollowUp")*/
                    ) {


                        progressDialog = new ProgressDialog(MonthDayWeekFormActivity.this);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.progress_dialog);
                        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                        WebService.getClient().getAddInquiry(emp,
                                cat_id,
                                sname,
                                edtDescriptionMWDform.getText().toString(),
                                BookingTypeMWDform,
                                edtBookAmountMWDform.getText().toString(),
                                DeliveryMWDform,
                                edtModelNameMWDform.getText().toString(),
                                "",
                                "",
                                edtNextVisitMWDform.getText().toString(),
                                "",
                                "",
                                sellLostMWDform,
                                edtSellLostMWDform.getText().toString(),
                                TypeInq,
                                Vemp,
                                FollowUptype,
                                Inquiry
                        ).enqueue(new Callback<AddInquiryModel>() {
                            @Override
                            public void onResponse(@NotNull Call<AddInquiryModel> call,
                                                   @NotNull Response<AddInquiryModel> response) {
                                progressDialog.dismiss();

                                assert response.body() != null;
                                Toast.makeText(MonthDayWeekFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(MonthDayWeekFormActivity.this,
                                        InquiryMainActivity.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(@NotNull Call<AddInquiryModel> call, @NotNull Throwable t) {
                                progressDialog.dismiss();
                            }
                        });
                    }
                }
            }
        });

    }

}
