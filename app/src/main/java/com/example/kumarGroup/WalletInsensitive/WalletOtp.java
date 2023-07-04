package com.example.kumarGroup.WalletInsensitive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_otpVerify_model;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletOtp extends AppCompatActivity {

    EditText edt_EnterOTP;

    Button btn_SubmitOTP,btn_skip;
    TextView tv_email;

    String ID,name,email;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Verify OTP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_wallet_otp);

        edt_EnterOTP =findViewById(R.id.edt_EnterOTP);
        btn_SubmitOTP=findViewById(R.id.btn_SubmitOTP);
        tv_email=findViewById(R.id.tv_email);
//        btn_skip=findViewById(R.id.btn_skip);


        ID= getIntent().getStringExtra("id");
        name= getIntent().getStringExtra("name");
        email= getIntent().getStringExtra("email");

        tv_email.setText(email);
//

//        btn_skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent;
//                if (name.equals("Add Inquiry")) {
//
//                     intent = new Intent(WalletOtp.this, InsensitiveListDisplay.class);
//                }else{
//                    intent = new Intent(WalletOtp.this, InsensitiveDeliveryListDisplayActivity.class);
//                }
//                startActivity(intent);
//                finish();
//
//            }
//        });

        btn_SubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(edt_EnterOTP.getText().toString().equals("")){
                    edt_EnterOTP.setError("Please Enter OTP");
                }
                else{

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    progressDialog= new ProgressDialog(WalletOtp.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

//
                    WebService.getClient().insentive_otp_verify_web(ID,edt_EnterOTP.getText().toString() ).enqueue(new Callback<deal_otpVerify_model>() {
                        @Override
                        public void onResponse(@NotNull Call<deal_otpVerify_model> call,
                                               @NotNull Response<deal_otpVerify_model> response) {
                            progressDialog.dismiss();


                            assert response.body() != null;

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Do something after 5s = 2000ms
                                    Toast.makeText(WalletOtp.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                    if(response.body().getMsg().equals("otp Update")){

                                        Intent intent;
                                        if (name.equals("Add Inquiry")) {

                                            intent = new Intent(WalletOtp.this, InsensitiveListDisplay.class);
                                        }else{
                                            intent = new Intent(WalletOtp.this, InsensitiveDeliveryListDisplayActivity.class);
                                        }
                                        startActivity(intent);
                                        finish();
                                        // Toast.makeText(OtpDealActivityViewINQ.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, 2000);

                        }

                        @Override
                        public void onFailure(@NotNull Call<deal_otpVerify_model> call, @NotNull Throwable t) {
                            Utils.showErrorToast(WalletOtp.this,t.getMessage());
                            progressDialog.dismiss();
                        }
                    });

                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}