package com.example.kumarGroup.Workshop.Add;

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

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SecondPhaseAddWsModel;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WsEmpListModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WsAddSecondActivity extends AppCompatActivity {

    Spinner spn_SecondWS_Employ;

    EditText edt_SecondWS_LaberCharge,edt_SecondWS_PetrolCharge;

    Button Btn_SecondWS_Next,Btn_Ws_SecondWS_Cancel;

    List<String> EmpName = new ArrayList<>();
    List<String> EmpID = new ArrayList<>();

    String EMP_Name, EMP_Id;

    ProgressDialog progressDialog;


    SharedPreferences sharedPreferences;
    String NextIDD_WS;
    Integer sumOfTotalPrice;


    SharedPreferences sp;
    String emp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws_add_second);

        getSupportActionBar().setTitle("Workshop Add");

        sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
        NextIDD_WS = sharedPreferences.getString("NextId_WS","");

        //  sumOfTotalPrice = sharedPreferences.getInt("sumOfTotalPrice",0);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        spn_SecondWS_Employ=findViewById(R.id.spn_SecondWS_Employ);

        edt_SecondWS_LaberCharge=findViewById(R.id.edt_SecondWS_LaberCharge);
        edt_SecondWS_PetrolCharge=findViewById(R.id.edt_SecondWS_PetrolCharge);


        Btn_SecondWS_Next=findViewById(R.id.Btn_SecondWS_Next);
        Btn_Ws_SecondWS_Cancel=findViewById(R.id.Btn_Ws_SecondWS_Cancel);


        WebService.getClient().getWsEmpList().enqueue(new Callback<WsEmpListModel>() {
            @Override
            public void onResponse(@NotNull Call<WsEmpListModel> call, @NotNull Response<WsEmpListModel> response) {
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        EmpName.clear();
                        EmpID.clear();

                        EmpName.add("Select Employee");
                        EmpID.add("0");

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            EmpName.add(response.body().getData().get(i).getEname());
                            EmpID.add(response.body().getData().get(i).getId());
                        }

                        ArrayAdapter adapterMake = new ArrayAdapter(WsAddSecondActivity.this, android.R.layout.simple_spinner_dropdown_item, EmpName);
                        spn_SecondWS_Employ.setAdapter(adapterMake);

                        spn_SecondWS_Employ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                EMP_Name = EmpName.get(position);
                                EMP_Id = EmpID.get(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<WsEmpListModel> call, @NotNull Throwable t) {

            }
        });


        Btn_SecondWS_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_SecondWS_LaberCharge.getText().toString().equals("")){
                    edt_SecondWS_LaberCharge.setError("Please enter Labor Charge");
                }

                if(edt_SecondWS_PetrolCharge.getText().toString().equals("")){
                    edt_SecondWS_PetrolCharge.setError("Please enter Petrol Charge");
                }
//                if(select_data.equals("Select Option")){
//                    Utils.showErrorToast(WsAddSecondActivity.this,"Please select value");
//                   // Toast.makeText(WsAddSecondActivity.this, "Please select value", Toast.LENGTH_SHORT).show();
//                }

                progressDialog = new ProgressDialog(WsAddSecondActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if (!edt_SecondWS_LaberCharge.getText().toString().equals("") &&
//                        !select_data.equals("Select Option") &&
                        !edt_SecondWS_PetrolCharge.getText().toString().equals("")) {

                    WebService.getClient().getSecondPhaseWs(NextIDD_WS,
                            EMP_Id,
                            emp,
                            edt_SecondWS_LaberCharge.getText().toString(),
                            edt_SecondWS_PetrolCharge.getText().toString(),
//                            select_data,
                            "2"
                    ).enqueue(new Callback<SecondPhaseAddWsModel>() {
                        @Override
                        public void onResponse(@NotNull Call<SecondPhaseAddWsModel> call,
                                               @NotNull Response<SecondPhaseAddWsModel> response) {
                            progressDialog.dismiss();

                            sharedPreferences = getSharedPreferences("Price", MODE_PRIVATE);
                            sharedPreferences.edit().putInt("PricePetrol", Integer.parseInt(edt_SecondWS_PetrolCharge.getText().toString())).apply();
                            sharedPreferences.edit().putInt("PriceLabor", Integer.parseInt(edt_SecondWS_LaberCharge.getText().toString())).apply();

                            Log.d("TAG", "onResponse: PricePetrol"+edt_SecondWS_PetrolCharge.getText().toString());
                            Log.d("TAG", "onResponse: PricePetrol"+" p"+edt_SecondWS_PetrolCharge.getText().toString());

                            Intent i = new Intent(WsAddSecondActivity.this, ThirdWsAddActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<SecondPhaseAddWsModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();

                        }
                    });

              /*  Intent i = new Intent(WsAddSecondActivity.this,ThirdWsAddActivity.class);
                startActivity(i);*/

                }
            }
        });



        Btn_Ws_SecondWS_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WsAddSecondActivity.this, FoDashbord.class);
                sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                sharedPreferences.edit().putString("NextId_WS", "").apply();
                startActivity(i);
            }
        });
    }
}