package com.example.kumarGroup.DealstageAK;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;

import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_otpVerify_model;
import com.example.kumarGroup.model_msg;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpDealActivity extends AppCompatActivity {

    EditText edt_EnterOTP;

    Button btn_SubmitOTP,btn_skip;

    String ID,message,Whatsappnumber;

    ProgressDialog progressDialog;

    String dealType;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_deal);

        getSupportActionBar().setTitle("Verify OTP");

        edt_EnterOTP =findViewById(R.id.edt_EnterOTP);
        btn_SubmitOTP=findViewById(R.id.btn_SubmitOTP);
        btn_skip=findViewById(R.id.btn_skip);


        ID= getIntent().getStringExtra("id");
        Whatsappnumber= getIntent().getStringExtra("Whatsappnumber");

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog= new ProgressDialog(OtpDealActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                WebService.getClient().deal_otp_verify_web(ID, "skip").enqueue(new Callback<deal_otpVerify_model>() {
                    @Override
                    public void onResponse(@NotNull Call<deal_otpVerify_model> call,
                                           @NotNull Response<deal_otpVerify_model> response) {
                        progressDialog.dismiss();
                        WhatsappMessage();

                        assert response.body() != null;
                        // Toast.makeText(OtpDealActivityViewINQ.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 2000ms
                                if(response.body().getMsg().equals("Record Update Succesfully")){

                                    Toast.makeText(OtpDealActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    DealstageRecyclerActivity.firstmeeting_otpsend_flag = false;
                                    Intent i = new Intent(OtpDealActivity.this, DealstageMainActivity.class);
                                    startActivity(i);
                                    finish();
                                    // Toast.makeText(OtpDealActivityViewINQ.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);

                    }

                    @Override
                    public void onFailure(@NotNull Call<deal_otpVerify_model> call, @NotNull Throwable t) {
                        Utils.showErrorToast(OtpDealActivity.this,t.getMessage());
                        progressDialog.dismiss();
                    }
                });
            }
        });

        btn_SubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(edt_EnterOTP.getText().toString().equals("")){
                    edt_EnterOTP.setError("Please Enter OTP");
                }
                else{

                    progressDialog= new ProgressDialog(OtpDealActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//                    Toast.makeText(getApplicationContext(), "id "+ID, Toast.LENGTH_SHORT).show();
                    WebService.getClient().deal_otp_verify_web(ID, edt_EnterOTP.getText().toString()).enqueue(new Callback<deal_otpVerify_model>() {
                        @Override
                        public void onResponse(@NotNull Call<deal_otpVerify_model> call,
                                               @NotNull Response<deal_otpVerify_model> response) {
                            progressDialog.dismiss();
                            WhatsappMessage1();
                            assert response.body() != null;
                            // Toast.makeText(OtpDealActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Do something after 5s = 2000ms
                                    if(response.body().getMsg().equals("Invalid")){
                                        Utils.showErrorToast(OtpDealActivity.this,
                                                ""+response.body().getMsg()+" "+"OTP");
                                        // Toast.makeText(OtpDealActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(OtpDealActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                        DealstageRecyclerActivity.firstmeeting_otpsend_flag = false;
                                        Intent i = new Intent(OtpDealActivity.this, DealstageMainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            }, 2000);


                        }

                        @Override
                        public void onFailure(@NotNull Call<deal_otpVerify_model> call, @NotNull Throwable t) {
                            Utils.showErrorToast(OtpDealActivity.this,t.getMessage());
                            progressDialog.dismiss();
                        }
                    });

                }

            }
        });
    }

    private void WhatsappMessage() {

        if (DealstageRecyclerActivity.firstmeeting_otpsend_flag){
            Log.d("TAG", "onResponse: Check_whatsapp_Message-1");
            dealType="First Metting";

            DealstageRecyclerActivity.firstmeeting_otpsend_flag = false;

            WebService.getClient().dealstage_msg(dealType).enqueue(new Callback<model_msg>() {
                @Override
                public void onResponse(Call<model_msg> call, Response<model_msg> response) {

                    Log.e("respose",response.body().toString());
                    if (response.body().getData().size()==0){
                        WhatsappMessage1();
//                        Toast.makeText(OtpDealActivity.this, "No Data Found !", Toast.LENGTH_SHORT).show();
                    }else {

                        String message = "प्रिय किसान मित्र।\n" +
                                "\n" +
                                "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                                "\n" +
                                "नमस्ते...\n" +
                                "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                                "\n" +
                                "अधिक जानकारी के लिए संपर्क करें।\n" +
                                "सेल्स MO:- 7500567770\n" +
                                "सर्विस MO:-7505786792";

                        for (int i=0;i<response.body().getData().size();i++){
                            if (response.body().getData().get(i).getVideo_link1() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link1()+"\n";
                            }
                            if (response.body().getData().get(i).getVideo_link2() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link2()+"\n";
                            }
                            if (response.body().getData().get(i).getVideo_link3() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link3()+"\n";
                            }
                            if (response.body().getData().get(i).getVideo_link4() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link4()+"\n";
                            }
                            if (response.body().getData().get(i).getVideo_link5() != null) {
                                message += "\n" + response.body().getData().get(i).getVideo_link5()+"\n";
                            }
                        }

//                        if (response.body().getData().get(0).getVideo_link1() != null) {
//                            message += "\n" + response.body().getData().get(0).getVideo_link1()+"\n";
//                        }
//                        if (response.body().getData().get(0).getVideo_link2() != null) {
//                            message += "\n" + response.body().getData().get(0).getVideo_link2()+"\n";
//                        }
//                        if (response.body().getData().get(0).getVideo_link3() != null) {
//                            message += "\n" + response.body().getData().get(0).getVideo_link3()+"\n";
//                        }

                        Log.d("TAG", "onResponse: Whatsapp_message" + message);


                        startActivity(
                                new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(
                                                String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + Whatsappnumber, message)
                                        )
                                )
                        );
                    }

                }

                @Override
                public void onFailure(Call<model_msg> call, Throwable t) {
                    Utils.showErrorToast(OtpDealActivity.this,t.getMessage());

                }
            });

        }

    }


    private void WhatsappMessage1() {

        message = "प्रिय किसान मित्र।\n" +
                "\n" +
                "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                "\n" +
                "नमस्ते...\n" +
                "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                "\n" +
                "अधिक जानकारी के लिए संपर्क करें।\n" +
                "सेल्स MO:- 7500567770\n" +
                "सर्विस MO:-7505786792";

        Log.d("TAG", "WhatsappMessage: Check_whatsapp_Message-6"+Whatsappnumber);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+Whatsappnumber + "&text=" + message));
        startActivity(intent);

//        startActivity(
//                new Intent(Intent.ACTION_VIEW,
//                        Uri.parse(
//                                String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + Whatsappnumber, message)
//                        )
//                )
//        );

    }
}