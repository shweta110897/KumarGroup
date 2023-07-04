package com.example.kumarGroup;

import static com.example.kumarGroup.Utils.RESEND_OTP_TIME_DURATION;
import static com.example.kumarGroup.Utils.RESEND_OTP_TIME_INTERVAL;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerificationScreenActivity extends AppCompatActivity {

    Button btnLoginVerify;

    private EditText otp_edit_text_1,otp_edit_text_2,otp_edit_text_3,otp_edit_text_4;
    private TextView resend_otp_btn;
    private int resendOtpCountDown = 45;

    TextView tv_mobile;


    String txt_Number,otp;
    String number,Email;

    private CountDownTimer countDownTimer;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_screen);

        txt_Number=getIntent().getStringExtra("Number");
        Email=getIntent().getStringExtra("Email");
        otp=getIntent().getStringExtra("otp");

        btnLoginVerify=findViewById(R.id.btnLoginVerify);

        tv_mobile = findViewById(R.id.tv_mobile);
//        tv_mobile.setText("+91"+txt_Number);
        tv_mobile.setText(Email);
        otp_edit_text_1 =  findViewById(R.id.otp_edit_text_1);
        otp_edit_text_2 =  findViewById(R.id.otp_edit_text_2);
        otp_edit_text_3 =  findViewById(R.id.otp_edit_text_3);
        otp_edit_text_4 =  findViewById(R.id.otp_edit_text_4);
        resend_otp_btn = (TextView) findViewById(R.id.resend_otp_btn);
        btnLoginVerify = findViewById(R.id.btnLoginVerify);

        otp_edit_text_1.addTextChangedListener(new GenericTextWatcher(otp_edit_text_2,otp_edit_text_1));
        otp_edit_text_2.addTextChangedListener(new GenericTextWatcher(otp_edit_text_3,otp_edit_text_1));
        otp_edit_text_3.addTextChangedListener(new GenericTextWatcher(otp_edit_text_4,otp_edit_text_2));
        otp_edit_text_4.addTextChangedListener(new GenericTextWatcher(otp_edit_text_4,otp_edit_text_3));

        resentOTPCountDown();
        countDownTimer.start();

        progressDialog = new ProgressDialog(OtpVerificationScreenActivity.this);

        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        resend_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resend_otp_btn.setEnabled(false);
                countDownTimer.cancel();
                resendOtpCountDown = 45;
//                resentOTPCountDown();
                countDownTimer.start();

                progressDialog.show();

                WebService.getClient().forgotPassword(txt_Number).enqueue(new Callback<Forgotpassword>() {
                    @Override
                    public void onResponse(@NotNull Call<Forgotpassword> call, @NotNull Response<Forgotpassword> response) {

                        Log.d("login", "onResponse:forgotpassword" + response.body().toString());
                        Log.d("login", "onResponse:forgotpassword" + response.body().getDetail().get(0).getEmail());

                            progressDialog.dismiss();

                        assert response.body() != null;
                        if (response.body() == null) {
                            Utils.showErrorToast(OtpVerificationScreenActivity.this, "Please Enter valid Number");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Forgotpassword> call, @NotNull Throwable t) {
                        Log.d("fail", "onFailure: " + t);
                        Toast.makeText(OtpVerificationScreenActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                    }
                });

            }
        });

        otp_edit_text_2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_DEL){
                    otp_edit_text_1.requestFocus();
                    otp_edit_text_2.setText("");
                    return true;
                }
                return false;
            }
        });

        otp_edit_text_3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_DEL){
                    otp_edit_text_2.requestFocus();
                    otp_edit_text_3.setText("");
                    return true;
                }
                return false;
            }
        });

        otp_edit_text_4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_DEL){
                    otp_edit_text_3.requestFocus();
                    otp_edit_text_4.setText("");
                    Log.e("et4","et4");

                    return true;
                }


                return false;

            }
        });


        btnLoginVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otp_edit_text_1.getText().toString().equals("")){
                    Toast.makeText(OtpVerificationScreenActivity.this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
                }else if (otp_edit_text_2.getText().toString().equals("")){
                    Toast.makeText(OtpVerificationScreenActivity.this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
                }else if (otp_edit_text_3.getText().toString().equals("")){
                    Toast.makeText(OtpVerificationScreenActivity.this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
                }else if (otp_edit_text_4.getText().toString().equals("")){
                    Toast.makeText(OtpVerificationScreenActivity.this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
                }else {

                    progressDialog.show();

                    WebService.getClient().verifyOtp(txt_Number,otp_edit_text_1.getText().toString()+
                            otp_edit_text_2.getText().toString()+otp_edit_text_3.getText().toString()+otp_edit_text_4.getText().toString()
                    ).enqueue(new Callback<Forgotpassword>() {
                        @Override
                        public void onResponse(@NotNull Call<Forgotpassword> call, @NotNull Response<Forgotpassword> response) {

//                            Log.d("login", "onResponse:forgotpassword" + response.body().toString());

                            progressDialog.dismiss();

                            assert response.body() != null;
                            if (response.body() == null) {
                                Utils.showErrorToast(OtpVerificationScreenActivity.this, "Please Enter valid Number");
                            } else {
                                // finish()
                                if (!response.body().getDetail().get(0).getMsg().equals("OTP Not Match")) {
                                    Intent intent = new Intent(OtpVerificationScreenActivity.this, ResetPasswordActivity.class);
                                    intent.putExtra("Mobile", txt_Number);
                                    startActivity(intent);
                                }else{
                                    Utils.showErrorToast(OtpVerificationScreenActivity.this,response.body().getDetail().get(0).getMsg());
                                }

                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<Forgotpassword> call, @NotNull Throwable t) {
                            Log.d("fail", "onFailure: " + t);
                            Toast.makeText(OtpVerificationScreenActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });



                }
            }
        });
    }

    public void resentOTPCountDown() {
        countDownTimer = new CountDownTimer(RESEND_OTP_TIME_DURATION,RESEND_OTP_TIME_INTERVAL){

            @Override
            public void onTick(long millisUntilFinished) {
                resend_otp_btn.setText("Resend in "+resendOtpCountDown+" sec");
                resendOtpCountDown--;
            }

            @Override
            public void onFinish() {
                resend_otp_btn.setText("Resend OTP");
                resend_otp_btn.setEnabled(true);
            }
        };
    }

    public  class GenericTextWatcher implements TextWatcher {

        private View view1,view2;
        private GenericTextWatcher (View view1,View view2){
            this.view1 = view1;
            this.view2 = view2;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();

            if (!otp_edit_text_4.getText().toString().matches("")) {
                Log.d("afterrrrr","ontextchanfe");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    btnLoginVerify.callOnClick();
                }

            }

            Log.e("text",text);

            if (text.length() == 1) {
                view1.requestFocus();
            } else if (text.length() == 0) {
                view2.requestFocus();
            }
        }
    }


}
