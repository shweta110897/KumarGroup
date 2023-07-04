package com.example.kumarGroup.SmartCard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kumarGroup.AddSmartCardVerfiedOtpModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.VendorDashboard.VendorDashboardActivity;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPointValueFourthActivity extends AppCompatActivity {

    EditText edt_EnterOTP;

    Button btn_SubmitOTP;

    SharedPreferences sharedPreferences;
    Integer ID;
    String CUID;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point_value_fourth);


        getSupportActionBar().setTitle("Verify OTP");

        edt_EnterOTP =findViewById(R.id.edt_EnterOTP);
        btn_SubmitOTP=findViewById(R.id.btn_SubmitOTP);


        sharedPreferences = getSharedPreferences("ID", MODE_PRIVATE);
        ID= sharedPreferences.getInt("id",0);
        CUID = sharedPreferences.getString("cuid", "");



        btn_SubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog= new ProgressDialog(AddPointValueFourthActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );


                if(edt_EnterOTP.getText().toString().equals("")){
                    edt_EnterOTP.setError("Please Enter OTP");
                }
                else{

                    WebService.getClient().getAddSmartOTPVerified(String.valueOf(ID),
                            edt_EnterOTP.getText().toString()).enqueue(new Callback<AddSmartCardVerfiedOtpModel>() {
                        @Override
                        public void onResponse(@NotNull Call<AddSmartCardVerfiedOtpModel> call,
                                               @NotNull Response<AddSmartCardVerfiedOtpModel> response) {
                            progressDialog.dismiss();

                            assert response.body() != null;
                           // Toast.makeText(AddPointValueFourthActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();


                            if(response.body().getMsg().equals("Invalid")){
                                Utils.showErrorToast(AddPointValueFourthActivity.this,
                                        ""+response.body().getMsg()+" "+"OTP");
                               // Toast.makeText(AddPointValueFourthActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(AddPointValueFourthActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(AddPointValueFourthActivity.this, VendorDashboardActivity.class);
                                startActivity(i);
                            }


                        }

                        @Override
                        public void onFailure(@NotNull Call<AddSmartCardVerfiedOtpModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                        }
                    });

                }

            }
        });
    }
}