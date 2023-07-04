package com.example.kumarGroup.ViewInquiryDealStage.Add;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kumarGroup.R;
import com.example.kumarGroup.RTODetailNextModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RtoDetailsDealstageActivity extends AppCompatActivity {

    Spinner spn_AddRto_RTO,spn_AddRto_RTOTAX,spn_AddRto_RTOPassing,
            spn_AddRto_Insurance,spn_AddRto_AgentFees,spn_AddRto_NumberPlat,
            spn_AddRto_LoanCharge,spn_AddRto_RTO_yesno;

    Button btn_AddRto_Next;


    String RTO_main,RTO1;
    String[] Rto_Main = {"RTO","Yes","No"};
    String[] Rto_Main1 = {"No"};

    String[] Rto_Main2 = {"RTO","In","Out"};

    String RTO_TAX;
    String[] Rto_Tax = {"RTO Tax","In","Out"};

    String RTO_PASSING;
    String[] Rto_Passing =  {"RTO Passing","In","Out"};

    String INSURANCE;
    String[] Insurance =  {"Insurance","In","Out"};

    String Agent_FEE;
    String[] AGent_Fees =  {"Agent Fee","In","Out"};

    String NUMBER_PLAT;
    String[] Number_Plat =  {"Number Plat","In","Out"};

    String Loan_Charge;
    String[] LoanCharge =  {"Loan Charge","In","Out"};

    SharedPreferences sharedPreferences;
    String NextIDD;

    String Product_Name;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rto_details);
        getSupportActionBar().setTitle("RTO Details");

        spn_AddRto_RTO=findViewById(R.id.spn_AddRto_RTO);
        spn_AddRto_RTOTAX=findViewById(R.id.spn_AddRto_RTOTAX);
        spn_AddRto_RTOPassing=findViewById(R.id.spn_AddRto_RTOPassing);
        spn_AddRto_Insurance=findViewById(R.id.spn_AddRto_Insurance);
        spn_AddRto_AgentFees=findViewById(R.id.spn_AddRto_AgentFees);
        spn_AddRto_NumberPlat=findViewById(R.id.spn_AddRto_NumberPlat);
        spn_AddRto_LoanCharge=findViewById(R.id.spn_AddRto_LoanCharge);
        spn_AddRto_RTO_yesno=findViewById(R.id.spn_AddRto_RTO_yesno);

        btn_AddRto_Next=findViewById(R.id.btn_AddRto_Next);

        sharedPreferences = getSharedPreferences("NextId1", MODE_PRIVATE);
        NextIDD = sharedPreferences.getString("NextId1","");

        Product_Name =sharedPreferences.getString("Product_Name","");

        if(Product_Name.equals("Implement") || Product_Name.equals("Spar part")){
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Rto_Main1);
            spn_AddRto_RTO.setAdapter(adapter);

            spn_AddRto_RTO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    RTO_main = Rto_Main1[position];

                    spn_AddRto_RTOTAX.setVisibility(View.GONE);
                    spn_AddRto_RTOPassing.setVisibility(View.GONE);
                    spn_AddRto_Insurance.setVisibility(View.GONE);
                    spn_AddRto_AgentFees.setVisibility(View.GONE);
                    spn_AddRto_NumberPlat.setVisibility(View.GONE);
                    spn_AddRto_LoanCharge.setVisibility(View.GONE);

                    if(Rto_Main[position].equals("No")){
                        spn_AddRto_RTOTAX.setVisibility(View.GONE);
                        spn_AddRto_RTOPassing.setVisibility(View.GONE);
                        spn_AddRto_Insurance.setVisibility(View.GONE);
                        spn_AddRto_AgentFees.setVisibility(View.GONE);
                        spn_AddRto_NumberPlat.setVisibility(View.GONE);
                        spn_AddRto_LoanCharge.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else{
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Rto_Main);
            spn_AddRto_RTO.setAdapter(adapter);

            spn_AddRto_RTO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    RTO_main = Rto_Main[position];

                    if(Rto_Main[position].equals("Yes")){
                        spn_AddRto_RTOTAX.setVisibility(View.VISIBLE);
                        spn_AddRto_RTOPassing.setVisibility(View.VISIBLE);
                        spn_AddRto_Insurance.setVisibility(View.VISIBLE);
                        spn_AddRto_AgentFees.setVisibility(View.VISIBLE);
                        spn_AddRto_NumberPlat.setVisibility(View.GONE);
                        spn_AddRto_LoanCharge.setVisibility(View.GONE);
                        spn_AddRto_RTO_yesno.setVisibility(View.VISIBLE);
                    }
                    else{
                        spn_AddRto_RTOTAX.setVisibility(View.GONE);
                        spn_AddRto_RTOPassing.setVisibility(View.GONE);
                        spn_AddRto_Insurance.setVisibility(View.GONE);
                        spn_AddRto_AgentFees.setVisibility(View.GONE);
                        spn_AddRto_NumberPlat.setVisibility(View.GONE);
                        spn_AddRto_LoanCharge.setVisibility(View.GONE);
                        spn_AddRto_RTO_yesno.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }



       /* ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Rto_Main);
        spn_AddRto_RTO.setAdapter(adapter);

        spn_AddRto_RTO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RTO_main = Rto_Main[position];

                if(Rto_Main[position].equals("Yes")){
                    spn_AddRto_RTOTAX.setVisibility(View.VISIBLE);
                    spn_AddRto_RTOPassing.setVisibility(View.VISIBLE);
                    spn_AddRto_Insurance.setVisibility(View.VISIBLE);
                    spn_AddRto_AgentFees.setVisibility(View.VISIBLE);
                    spn_AddRto_NumberPlat.setVisibility(View.VISIBLE);
                    spn_AddRto_LoanCharge.setVisibility(View.VISIBLE);
                }
                else{
                    spn_AddRto_RTOTAX.setVisibility(View.GONE);
                    spn_AddRto_RTOPassing.setVisibility(View.GONE);
                    spn_AddRto_Insurance.setVisibility(View.GONE);
                    spn_AddRto_AgentFees.setVisibility(View.GONE);
                    spn_AddRto_NumberPlat.setVisibility(View.GONE);
                    spn_AddRto_LoanCharge.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        ArrayAdapter adapterRTO_TAX1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Rto_Main2);
        spn_AddRto_RTO_yesno.setAdapter(adapterRTO_TAX1);
        spn_AddRto_RTO_yesno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                RTO1 = Rto_Main2[i];
//                Log.d("TAG", "onItemSelected: Checkposition  out");
                if (RTO1.equals("In")){
//                    Log.d("TAG", "onItemSelected: Checkposition if");
                    spn_AddRto_RTOTAX.setSelection(1);
                    spn_AddRto_RTOPassing.setSelection(1);
                    spn_AddRto_Insurance.setSelection(1);
                    spn_AddRto_NumberPlat.setSelection(1);
                    spn_AddRto_LoanCharge.setSelection(1);
                    spn_AddRto_AgentFees.setSelection(1);
                }else if (RTO1.equals("Out")){
//                    Log.d("TAG", "onItemSelected: Checkposition else");
                    spn_AddRto_RTOTAX.setSelection(2);
                    spn_AddRto_RTOPassing.setSelection(2);
                    spn_AddRto_Insurance.setSelection(2);
                    spn_AddRto_NumberPlat.setSelection(2);
                    spn_AddRto_LoanCharge.setSelection(2);
                    spn_AddRto_AgentFees.setSelection(2);
                }else {
                    spn_AddRto_RTOTAX.setSelection(0);
                    spn_AddRto_RTOPassing.setSelection(0);
                    spn_AddRto_Insurance.setSelection(0);
                    spn_AddRto_NumberPlat.setSelection(0);
                    spn_AddRto_LoanCharge.setSelection(0);
                    spn_AddRto_AgentFees.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapterRTO_TAX = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Rto_Tax);
        spn_AddRto_RTOTAX.setAdapter(adapterRTO_TAX);

        spn_AddRto_RTOTAX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RTO_TAX = Rto_Tax[position];
               /* if(RTO_TAX.equals("RTO Tax")){
                    Utils.showErrorToast(RtoDetailsActivity.this,"Please Must be select Yes No");
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterRto_Passing = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Rto_Passing);
        spn_AddRto_RTOPassing.setAdapter(adapterRto_Passing);

        spn_AddRto_RTOPassing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RTO_PASSING = Rto_Passing[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterInsurance = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Insurance);
        spn_AddRto_Insurance.setAdapter(adapterInsurance);

        spn_AddRto_Insurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                INSURANCE = Insurance[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterAGent_Fees = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,AGent_Fees);
        spn_AddRto_AgentFees.setAdapter(adapterAGent_Fees);

        spn_AddRto_AgentFees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Agent_FEE = AGent_Fees[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterNUMBER_PLAT = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Number_Plat);
        spn_AddRto_NumberPlat.setAdapter(adapterNUMBER_PLAT);

        spn_AddRto_NumberPlat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NUMBER_PLAT = Number_Plat[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterLoan = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,LoanCharge);
        spn_AddRto_LoanCharge.setAdapter(adapterLoan);

        spn_AddRto_LoanCharge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Loan_Charge = LoanCharge[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_AddRto_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidate()) {

                    progressDialog = new ProgressDialog(RtoDetailsDealstageActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    WebService.getClient().getRToDetailNext(NextIDD, RTO1,
                            RTO_main,
                            RTO_TAX,
                            RTO_PASSING,
                            INSURANCE,
                            Agent_FEE,
                            NUMBER_PLAT,
                            Loan_Charge,
                            "5").enqueue(new Callback<RTODetailNextModel>() {
                        @Override
                        public void onResponse(Call<RTODetailNextModel> call, Response<RTODetailNextModel> response) {
                            progressDialog.dismiss();

                            Toast.makeText(RtoDetailsDealstageActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            // Intent i = new Intent(RtoDetailsActivity.this,DownPaymentActivity.class);
                            Intent i = new Intent(RtoDetailsDealstageActivity.this, CustomerSkimDealstageActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<RTODetailNextModel> call, Throwable t) {
                            progressDialog.dismiss();

                        }
                    });
                }


            }
        });
    }

    private boolean isValidate() {
        if (spn_AddRto_RTO.getSelectedItem().toString().trim().equals("RTO")) {
            Utils.showErrorToast(RtoDetailsDealstageActivity.this, "Select RTO");
            return false;
        }else if (spn_AddRto_RTO_yesno.getSelectedItem().toString().trim().equals("RTO")) {
            Utils.showErrorToast(RtoDetailsDealstageActivity.this, "Select RTO");
            return false;
        }else{
            return true;
        }
    }
}