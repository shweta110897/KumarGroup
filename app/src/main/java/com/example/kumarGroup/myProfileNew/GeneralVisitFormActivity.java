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
import com.example.kumarGroup.MyProfileAddFormModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralVisitFormActivity extends AppCompatActivity {

    String Customordoit,CustomerName,CustomerName1,Id,cat_id,eid;

    String Customordoit_1,CustomerName_1,CustomerName1_1;

    EditText edtEmployeeNameGVform,edtCategoryNameGVform,
            edtDescriptionGVform,edtDateGVform;
    Button btnSubmitGVform;

    int day,month,year;
    Calendar mcurrentdate;

    Spinner spnFollowUpGVform;

    String FollowUptype;
    String[] FollowUpType_list={"Select FollowUp","Call", "Visit","Visit with Dealer"};

    SharedPreferences sp1;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_visit_form);

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        CustomerName= getIntent().getStringExtra("CustomerName");
        CustomerName1= getIntent().getStringExtra("CustomerName1");
        Customordoit= getIntent().getStringExtra("Customordoit");
        Id= getIntent().getStringExtra("Id");
        cat_id= getIntent().getStringExtra("cat_id");
        eid= getIntent().getStringExtra("eid");

        CustomerName_1=  CustomerName.substring(13);  // ello World
        CustomerName1_1= CustomerName1.substring(11);  // ello World
        Customordoit_1= Customordoit.substring(16);  // ello World

        edtEmployeeNameGVform= findViewById(R.id.edtEmployeeNameGVform);
        edtCategoryNameGVform= findViewById(R.id.edtCategoryNameGVform);
        edtDescriptionGVform= findViewById(R.id.edtDescriptionGVform);
        edtDateGVform= findViewById(R.id.edtDateGVform);
        spnFollowUpGVform= findViewById(R.id.spnFollowUpGVform);

        btnSubmitGVform= findViewById(R.id.btnSubmitGVform);


        edtEmployeeNameGVform.setText(CustomerName_1+" "+CustomerName1_1);
        edtCategoryNameGVform.setText(Customordoit_1);

        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );


        edtDateGVform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(GeneralVisitFormActivity.this,
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

                        edtDateGVform.setText(year+"-"+mt+"-"+dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
              //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });


        edtDateGVform.setFocusable(false);

        ArrayAdapter adapterFollowUp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                FollowUpType_list);
        spnFollowUpGVform.setAdapter(adapterFollowUp);

        spnFollowUpGVform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        btnSubmitGVform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(GeneralVisitFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                WebService.getClient().getMyProfileForm(emp,
                        FollowUptype,
                        cat_id,
                        Id,
                        edtDescriptionGVform.getText().toString(),
                        edtDateGVform.getText().toString(),
                        eid).enqueue(new Callback<MyProfileAddFormModel>() {
                    @Override
                    public void onResponse(@NotNull Call<MyProfileAddFormModel> call,
                                           @NotNull Response<MyProfileAddFormModel> response) {
                        progressDialog.dismiss();
                        Toast.makeText(GeneralVisitFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(GeneralVisitFormActivity.this, FoDashbord.class);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<MyProfileAddFormModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }
}