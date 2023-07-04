package com.example.kumarGroup.Loan;

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

import com.example.kumarGroup.AddLoanModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLoanDetailActivity extends AppCompatActivity {

    EditText edtAddLoanAmount,edtAddLoanDate,edtAddLoanDescription;
    Button btnAddLoanAdd,btnAddLoanCancel;
    Spinner spnAddLoanType,spnAddCashTYpe;

    Calendar mcurrentdate,mcurrentTime;
    int day,month,year;
    String loanType,cashType;
    String[] LoanType = {"Credit"};
    String[] CashType =  {"Select LoanType","Bank","Cash"};

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan_detail);
        getSupportActionBar().setTitle("Add Loan Details");

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        edtAddLoanAmount=findViewById(R.id.edtAddLoanAmount);
        edtAddLoanDate=findViewById(R.id.edtAddLoanDate);
        edtAddLoanDescription=findViewById(R.id.edtAddLoanDescription);
        btnAddLoanCancel=findViewById(R.id.btnAddLoanCancel);
        btnAddLoanAdd=findViewById(R.id.btnAddLoanAdd);
        spnAddLoanType=findViewById(R.id.spnAddLoanType);
        spnAddCashTYpe=findViewById(R.id.spnAddCashTYpe);

        edtAddLoanDate.setFocusable(false);
        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,LoanType);
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,CashType);
        spnAddLoanType.setAdapter(adapter);
        spnAddCashTYpe.setAdapter(adapter1);

        spnAddLoanType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loanType = LoanType[position];
                if(LoanType[position]=="Credit"){
                    loanType = "2";
                }
                else{
                    loanType = "1";
                }
               // Credit=2;
               // Debit=1;
              //  Toast.makeText(AddLoanDetailActivity.this, ""+loanType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        spnAddCashTYpe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                cashType = CashType[position];
              /*  if(Objects.equals(CashType[position], "Credit")){
                    cashType = "2";
                }
                else{
                    cashType = "1";
                }*/

                if( CashType[position]=="Select LoanType"){
                    cashType = "";
                }
                else{
                    cashType = CashType[position];
                }

//                  Toast.makeText(AddLoanDetailActivity.this, ""+cashType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edtAddLoanDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddLoanDetailActivity.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
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
                      //  edtAddLoanDate.setText(mt+"/"+dy+"/"+year);
                        edtAddLoanDate.setText(year+"-"+mt+"-"+dy);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        btnAddLoanCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAddLoanAmount.setText("");
                edtAddLoanDate.setText("");
                edtAddLoanDescription.setText("");
            }
        });

        btnAddLoanAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtAddLoanAmount.getText().length()==0){
                    Toast.makeText(AddLoanDetailActivity.this, "Enter Loan Amount", Toast.LENGTH_SHORT).show();
                    edtAddLoanAmount.setError("Enter Loan Amount");
                }else if (edtAddLoanDescription.getText().length()==0){
                    Toast.makeText(AddLoanDetailActivity.this, "Enter Loan Description", Toast.LENGTH_SHORT).show();
                    edtAddLoanDescription.setError("Enter Loan Description");
                }else if (edtAddLoanDate.getText().length()==0){
                    Toast.makeText(AddLoanDetailActivity.this, "Enter Loan Date", Toast.LENGTH_SHORT).show();
                    edtAddLoanDate.setError("Enter Loan Date");
                }else if (spnAddCashTYpe==null || spnAddCashTYpe.getSelectedItem()=="Select LoanType" || cashType.equals("")){
                    Toast.makeText(AddLoanDetailActivity.this, "Select LoanType", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog= new ProgressDialog(AddLoanDetailActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent
                    );

//                    Intent i = new Intent(AddLoanDetailActivity.this, loan_otp_verification.class);
//                            startActivity(i);

                    Log.d("TAG", "onClick: check_date "+edtAddLoanDate.getText().toString());

                    WebService.getClient().getAddLoan(emp,cashType,
                            loanType,
                            edtAddLoanAmount.getText().toString(),
                            edtAddLoanDescription.getText().toString(),
                            edtAddLoanDate.getText().toString()).enqueue(new Callback<AddLoanModel>() {
                        @Override
                        public void onResponse(@NotNull Call<AddLoanModel> call, @NotNull Response<AddLoanModel> response) {
                            progressDialog.dismiss();
                            assert response.body() != null;
                            Toast.makeText(AddLoanDetailActivity.this, ""+response.body().getMsg(), Toast.LENGTH_LONG).show();

                            Intent i = new Intent(AddLoanDetailActivity.this, loan_otp_verification.class);
                            i.putExtra("id", response.body().getId());
                            i.putExtra("email", response.body().getEmail());
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(@NotNull Call<AddLoanModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                        }
                    });

                }


            }
        });

    }
}