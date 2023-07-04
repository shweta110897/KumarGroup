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

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInqFormAddModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewInquiryFormActivity extends AppCompatActivity {


    EditText edtCategoryNameVI,edtEmployeeNameVI,edtDescriptionVI,
            edtBookAmountVI,edtModelNameVI,edtSellLostVI,edtNextVisitVI;

    Spinner spnBookingVI,spnDeliveryVI,spnSellLostVI,spnBookingTYpe,
            spnFollowUpTypeVI,
            spnGenInquiryVI;

    Button btnSubmitVI;

    String sellLostVI;
    String[] sellLost1_VI={"Sell Lost","Yes","No"};

    String BookingTypeVI;
    String[] BookingType1_VI = {"Booking","Yes","No"};

    String DeliveryVI;
    String[] Delivery1_VI = {"Delivery","Yes","No"};


    String TypeInq;
    String[] Type_inq = {"Select Type","Hot","Cold"};

    SharedPreferences sp1;
    String emp;

    int day,month,year;
    Calendar mcurrentdate;


    String Name,Category,cat_id,sname,id,MobileNo,Vemp;

    ProgressDialog progressDialog;


    String FollowUptype;
    String[] FollowUpType_list={"Select FollowUp","Call", "Visit","Visit with Dealer"};

    String Inquiry;
    String[] Inquiry_option = {"Drop Inquiry","Yes","No"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inquiry_form);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        Name= getIntent().getStringExtra("Name");
        Category=getIntent().getStringExtra("Category");
        cat_id=getIntent().getStringExtra("cat_id");
        sname=getIntent().getStringExtra("sname");
        //  id=getIntent().getStringExtra("id");
        MobileNo=getIntent().getStringExtra("MobileNo");
        Vemp=getIntent().getStringExtra("Vemp");


        edtCategoryNameVI=findViewById(R.id.edtCategoryNameVI);
        edtEmployeeNameVI=findViewById(R.id.edtEmployeeNameVI);
        edtDescriptionVI=findViewById(R.id.edtDescriptionVI);
        edtBookAmountVI=findViewById(R.id.edtBookAmountVI);
        edtModelNameVI=findViewById(R.id.edtModelNameVI);
        edtSellLostVI=findViewById(R.id.edtSellLostVI);
        edtNextVisitVI=findViewById(R.id.edtNextVisitVI);

        spnBookingVI=findViewById(R.id.spnBookingVI);
        spnDeliveryVI=findViewById(R.id.spnDeliveryVI);
        spnSellLostVI=findViewById(R.id.spnSellLostVI);
        spnBookingTYpe=findViewById(R.id.spnBookingTYpe);
        spnFollowUpTypeVI=findViewById(R.id.spnFollowUpTypeVI);

        spnGenInquiryVI=findViewById(R.id.spnGenInquiryVI);

        btnSubmitVI=findViewById(R.id.btnSubmitVI);


        edtCategoryNameVI.setText(Category);
        edtEmployeeNameVI.setText(Name);



        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );



        edtNextVisitVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ViewInquiryFormActivity.this,
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
                        // edtNextVisitVI.setText(mt + "/" + dy + "/" + year);
                        // edtNextVisitVI.setText(year + "-" + mt + "-" + dy);
                        edtNextVisitVI.setText(year+"-"+mt+"-"+dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            }
        });


        edtNextVisitVI.setFocusable(false);


         ArrayAdapter DropInquiry = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Inquiry_option);
        spnGenInquiryVI.setAdapter(DropInquiry);

        spnGenInquiryVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Inquiry = Inquiry_option[position];

                if(Inquiry_option[position].equals("Yes")){
                    edtDescriptionVI.setVisibility(View.GONE);
                    spnBookingVI.setVisibility(View.GONE);
                    spnDeliveryVI.setVisibility(View.GONE);
                    spnSellLostVI.setVisibility(View.GONE);

                    spnBookingTYpe.setVisibility(View.GONE);
                    spnFollowUpTypeVI.setVisibility(View.VISIBLE);

                    edtNextVisitVI.setVisibility(View.GONE);

                }else{
                    edtDescriptionVI.setVisibility(View.VISIBLE);
                    spnBookingVI.setVisibility(View.VISIBLE);
                    spnDeliveryVI.setVisibility(View.VISIBLE);
                    spnSellLostVI.setVisibility(View.VISIBLE);

                    spnBookingTYpe.setVisibility(View.VISIBLE);
                    spnFollowUpTypeVI.setVisibility(View.VISIBLE);
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
                BookingType1_VI);
        spnBookingVI.setAdapter(adapter);

        spnBookingVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingTypeVI = BookingType1_VI[position];
                if(BookingType1_VI[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtBookAmountVI.setVisibility(View.VISIBLE);
                }
                else{
                    //BookingType1 = "1";
                    edtBookAmountVI.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter deliveryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,
                Delivery1_VI);
        spnDeliveryVI.setAdapter(deliveryAdapter);

        spnDeliveryVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DeliveryVI = Delivery1_VI[position];
                if(Delivery1_VI[position]=="Yes"){
                    edtModelNameVI.setVisibility(View.VISIBLE);
                    edtNextVisitVI.setVisibility(View.GONE);
                    spnBookingTYpe.setVisibility(View.GONE);
                }
                else{
                    edtModelNameVI.setVisibility(View.GONE);
                    edtNextVisitVI.setVisibility(View.VISIBLE);
                    spnBookingTYpe.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        ArrayAdapter adapterSellLost = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                sellLost1_VI);
        spnSellLostVI.setAdapter(adapterSellLost);

        spnSellLostVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sellLostVI= sellLost1_VI[position];
                if(sellLost1_VI[position]=="Yes"){
                    edtNextVisitVI.setVisibility(View.GONE);
                    edtSellLostVI.setVisibility(View.VISIBLE);
                    spnBookingTYpe.setVisibility(View.VISIBLE);
                }else {
                    edtNextVisitVI.setVisibility(View.VISIBLE);
                    edtSellLostVI.setVisibility(View.GONE);
                    spnBookingTYpe.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


          ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                FollowUpType_list);
        spnFollowUpTypeVI.setAdapter(adapterFollowUp);

        spnFollowUpTypeVI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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




        btnSubmitVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Inquiry.equals("Yes")){
                    progressDialog = new ProgressDialog(ViewInquiryFormActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                    WebService.getClient().getViewInqAdd(
                            emp,
                            cat_id,
                            TypeInq,
                            sname,
                            edtDescriptionVI.getText().toString(),
                            BookingTypeVI,
                            edtBookAmountVI.getText().toString(),
                            DeliveryVI,
                            edtModelNameVI.getText().toString(),
                            edtNextVisitVI.getText().toString(),
                            sellLostVI,
                            edtSellLostVI.getText().toString(),
                            Vemp,
                            FollowUptype,
                            Inquiry
                    ).enqueue(new Callback<ViewInqFormAddModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ViewInqFormAddModel> call,
                                               @NotNull Response<ViewInqFormAddModel> response) {
                            progressDialog.dismiss();

                            assert response.body() != null;
                            Toast.makeText(ViewInquiryFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(ViewInquiryFormActivity.this,
                                    FoDashbord.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<ViewInqFormAddModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(ViewInquiryFormActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else {


                    if (edtCategoryNameVI.getText().toString().equals("")) {
                        edtCategoryNameVI.setError("Please Enter Category Name");
                    }

                    if (edtEmployeeNameVI.getText().toString().equals("")) {
                        edtEmployeeNameVI.setError("Please Enter Employee Name");
                    }

                    if (edtDescriptionVI.getText().toString().equals("")) {
                        edtDescriptionVI.setError("Please Enter Visit Reason");
                    }

                    if (BookingTypeVI.equals("Booking")) {
                        Utils.showErrorToast(ViewInquiryFormActivity.this,
                                "Please Select Yes or No ");
                    }

                    if (DeliveryVI.equals("Delivery")) {
                        Utils.showErrorToast(ViewInquiryFormActivity.this,
                                "Please Select Yes or No");
                    }

                    if (sellLostVI.equals("Sell Lost")) {
                        Utils.showErrorToast(ViewInquiryFormActivity.this,
                                "Please Select Yes or No ");
                    }

                    if (FollowUptype.equals("Select FollowUp")) {
                        Utils.showErrorToast(ViewInquiryFormActivity.this, "Please Enter FollowUp Type");
                    }

                    if (BookingTypeVI.equals("Booking")) {
                        Utils.showErrorToast(ViewInquiryFormActivity.this,
                                "Select Booking");
                    }

                    if (edtNextVisitVI.getText().toString().equals("")) {
                        edtNextVisitVI.setError("Please Enter Visit Date");
                    }


                    if (!edtCategoryNameVI.getText().toString().equals("") &&
                            !edtEmployeeNameVI.getText().toString().equals("") &&
                            !edtDescriptionVI.getText().toString().equals("")
                         && !FollowUptype.equals("Select FollowUp")
                         && !BookingTypeVI.equals("Booking")
                    ) {


                        progressDialog = new ProgressDialog(ViewInquiryFormActivity.this);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.progress_dialog);
                        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                        WebService.getClient().getViewInqAdd(
                                emp,
                                cat_id,
                                TypeInq,
                                sname,
                                edtDescriptionVI.getText().toString(),
                                BookingTypeVI,
                                edtBookAmountVI.getText().toString(),
                                DeliveryVI,
                                edtModelNameVI.getText().toString(),
                                edtNextVisitVI.getText().toString(),
                                sellLostVI,
                                edtSellLostVI.getText().toString(),
                                Vemp,
                                FollowUptype,
                                Inquiry
                        ).enqueue(new Callback<ViewInqFormAddModel>() {
                            @Override
                            public void onResponse(@NotNull Call<ViewInqFormAddModel> call,
                                                   @NotNull Response<ViewInqFormAddModel> response) {
                                progressDialog.dismiss();

                                assert response.body() != null;
                                Toast.makeText(ViewInquiryFormActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(ViewInquiryFormActivity.this,
                                        FoDashbord.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(@NotNull Call<ViewInqFormAddModel> call, @NotNull Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(ViewInquiryFormActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }
            }
        });


    }
}