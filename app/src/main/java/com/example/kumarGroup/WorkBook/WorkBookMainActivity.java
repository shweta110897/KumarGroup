package com.example.kumarGroup.WorkBook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.CustomerListWBModel;
import com.example.kumarGroup.EmployeeListWBModel;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WorkBookSubmitModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkBookMainActivity extends AppCompatActivity {

    Spinner spnWorkBookEmployee,spnWorkBookCustomer,
            spnWorkBookBooking,spnWorkBookDelivery,
            spnWorkBookPaymentCollection;

    EditText EdtWorkBookVisitReason,edtWorkBookAmount,edtWorkBookModelName,edtWorkBookPaymentCollection;
    Button btnWorkBookSubmit;

    String BookingTypeWorkBook;
    String[] BookingType = {"Select Booking","Yes","No"};

    String DeliveryWorkBook;
    String[] Delivery = {"Select Delivery","Yes","No"};

    String PaymentCollectionWorkBook;
    String[] PaymentCollection1= {"Select Payment Collection","Yes","No"};


    List<String> EmpNameWork = new ArrayList<>();
    List<String> EmpIDWork = new ArrayList<>();


    List<String> customerNameWB = new ArrayList<>();
    List<String> customerIdWB = new ArrayList<>();


    String EmpId,EmpName,cust_idWb, cust_nameWb;

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_book_main);
        getSupportActionBar().setTitle("SR Report");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        AllocateMemory();

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

      //  SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);

        Log.d("currentdate", "onCreate: "+formattedDate);


        WebService.getClient().getEmployeeWB().enqueue(new Callback<EmployeeListWBModel>() {
            @Override
            public void onResponse(Call<EmployeeListWBModel> call, Response<EmployeeListWBModel> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        EmpNameWork.clear();
                        EmpIDWork.clear();
                        Log.d("category", response.body().toString());
                        for (int i = 0; i < response.body().getData().size(); i++)
                        {
                            EmpNameWork.add(response.body().getData().get(i).getEname());
                            EmpIDWork.add(response.body().getData().get(i).getId());
                        }
                        Log.d("category1", EmpNameWork.toString());
                        ArrayAdapter adapter2 = new ArrayAdapter(WorkBookMainActivity.this, android.R.layout.simple_spinner_dropdown_item, EmpNameWork);
                        spnWorkBookEmployee.setAdapter(adapter2);


                        spnWorkBookEmployee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                EmpName = EmpNameWork.get(position);
                                EmpId = EmpIDWork.get(position);

                                WebService.getClient().getCustomerListWB(formattedDate,EmpId).enqueue(new Callback<CustomerListWBModel>() {
                                    @Override
                                    public void onResponse(Call<CustomerListWBModel> call, Response<CustomerListWBModel> response) {
                                        if(response.isSuccessful()){
                                            if(response.body()!=null){
                                                customerNameWB.clear();
                                                customerIdWB.clear();

                                                /*customer.add("Select Customer");
                                                customerId.add("0");*/
                                                for (int i=0;i< response.body().getData().size();i++){
                                                    customerNameWB.add(response.body().getData().get(i).getName());
                                                    customerIdWB.add(response.body().getData().get(i).getId());
                                                }
                                                ArrayAdapter adapter_per = new ArrayAdapter(WorkBookMainActivity.this, android.R.layout.simple_spinner_dropdown_item, customerNameWB);
                                                spnWorkBookCustomer.setAdapter(adapter_per);

                                                spnWorkBookCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        cust_idWb = customerIdWB.get(position);
                                                        cust_nameWb = customerNameWB.get(position);
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CustomerListWBModel> call, Throwable t) {

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
            public void onFailure(Call<EmployeeListWBModel> call, Throwable t) {

            }
        });


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,BookingType);
        spnWorkBookBooking.setAdapter(adapter);

        spnWorkBookBooking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingTypeWorkBook = BookingType[position];

                if(BookingType[position]=="Yes"){
                    //BookingType1 = "Yes";
                    edtWorkBookAmount.setVisibility(View.VISIBLE);
                }
                else{
                    //BookingType1 = "1";
                    edtWorkBookAmount.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter deliveryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Delivery);
        spnWorkBookDelivery.setAdapter(deliveryAdapter);

        spnWorkBookDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DeliveryWorkBook = Delivery[position];
                if(Delivery[position]=="Yes"){
                    edtWorkBookModelName.setVisibility(View.VISIBLE);
                }
                else{
                    edtWorkBookModelName.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter PaymentCollectionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,PaymentCollection1);
        spnWorkBookPaymentCollection.setAdapter(PaymentCollectionAdapter);

        spnWorkBookPaymentCollection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PaymentCollectionWorkBook = PaymentCollection1[position];
                if(PaymentCollection1[position]=="Yes"){
                    edtWorkBookPaymentCollection.setVisibility(View.VISIBLE);
                }
                else{
                    edtWorkBookPaymentCollection.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnWorkBookSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= new ProgressDialog(WorkBookMainActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                WebService.getClient().getWorkBookSubmit(
                        emp,
                        EmpId,
                        cust_idWb,
                        EdtWorkBookVisitReason.getText().toString(),
                        BookingTypeWorkBook,
                        edtWorkBookAmount.getText().toString(),
                        DeliveryWorkBook,
                        edtWorkBookModelName.getText().toString(),
                        PaymentCollectionWorkBook,
                        edtWorkBookPaymentCollection.getText().toString()
                ).enqueue(new Callback<WorkBookSubmitModel>() {
                    @Override
                    public void onResponse(Call<WorkBookSubmitModel> call, Response<WorkBookSubmitModel> response) {
                        progressDialog.dismiss();
                        Toast.makeText(WorkBookMainActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(WorkBookMainActivity.this, FoDashbord.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<WorkBookSubmitModel> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });




    }

    private void AllocateMemory()
    {
        spnWorkBookEmployee=findViewById(R.id.spnWorkBookEmployee);
        spnWorkBookCustomer=findViewById(R.id.spnWorkBookCustomer);
        spnWorkBookBooking=findViewById(R.id.spnWorkBookBooking);
        spnWorkBookDelivery=findViewById(R.id.spnWorkBookDelivery);
        spnWorkBookPaymentCollection=findViewById(R.id.spnWorkBookPaymentCollection);

        EdtWorkBookVisitReason=findViewById(R.id.EdtWorkBookVisitReason);
        edtWorkBookAmount=findViewById(R.id.edtWorkBookAmount);
        edtWorkBookModelName=findViewById(R.id.edtWorkBookModelName);
        edtWorkBookPaymentCollection=findViewById(R.id.edtWorkBookPaymentCollection);

        btnWorkBookSubmit=findViewById(R.id.btnWorkBookSubmit);
    }
}