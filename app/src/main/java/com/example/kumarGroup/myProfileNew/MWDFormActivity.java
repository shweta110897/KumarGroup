package com.example.kumarGroup.myProfileNew;

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
import com.example.kumarGroup.MwdMyProfileModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MWDFormActivity extends AppCompatActivity {

    EditText edtEmployeeNameform,edtCategoryNameform,edtDescriptionform,
            edtDateform;

    Spinner spnFollowUpform;

    Button btnSubmitform;

    int day,month,year;
    Calendar mcurrentdate;


    String FollowUptype;
    String[] FollowUpType_list={"Select FollowUp","Call", "Visit","Visit with Dealer"};

    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;


    String  customer_name,category,catId,sname,Vemp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_w_d_form);


        edtEmployeeNameform= findViewById(R.id.edtEmployeeNameform);
        edtCategoryNameform= findViewById(R.id.edtCategoryNameform);
        edtDescriptionform= findViewById(R.id.edtDescriptionform);
        edtDateform= findViewById(R.id.edtDateform);

        spnFollowUpform= findViewById(R.id.spnFollowUpform);

        btnSubmitform= findViewById(R.id.btnSubmitform);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        customer_name= getIntent().getStringExtra("customer_name");
        category= getIntent().getStringExtra("category");
        catId= getIntent().getStringExtra("catId");
        sname= getIntent().getStringExtra("sname");
        Vemp= getIntent().getStringExtra("Vemp");

        edtEmployeeNameform.setText(customer_name);
        edtCategoryNameform.setText(category);


        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );


        edtDateform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MWDFormActivity.this,
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

                        edtDateform.setText(year+"-"+mt+"-"+dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
                //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });


        edtDateform.setFocusable(false);


        ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                FollowUpType_list);
        spnFollowUpform.setAdapter(adapterFollowUp);

        spnFollowUpform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        btnSubmitform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                progressDialog = new ProgressDialog(MWDFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                WebService.getClient().gwtMwdMyProfile(emp,
                        FollowUptype,
                        catId,
                        sname,
                        edtDescriptionform.getText().toString(),
                        edtDateform.getText().toString(),
                        Vemp
                        ).enqueue(new Callback<MwdMyProfileModel>() {
                    @Override
                    public void onResponse(@NotNull Call<MwdMyProfileModel> call,
                                           @NotNull Response<MwdMyProfileModel> response) {

                        progressDialog.dismiss();
                        Toast.makeText(MWDFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(MWDFormActivity.this, FoDashbord.class);
                        startActivity(i);


                    }

                    @Override
                    public void onFailure(@NotNull Call<MwdMyProfileModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });

            }
        });

    }
}