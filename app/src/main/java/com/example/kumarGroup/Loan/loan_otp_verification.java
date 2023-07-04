package com.example.kumarGroup.Loan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.Loan_formate;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loan_otp_verification extends AppCompatActivity {

    EditText et_password;
    Button btn_login;
    SharedPreferences sp1;
    String emp,email;
    String id;
    ProgressDialog pro;
    TextView tv_email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_otp_verification);

        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_email = findViewById(R.id.tv_email);

        pro = new ProgressDialog(this);


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");

        id = getIntent().getStringExtra("id");
        email = getIntent().getStringExtra("email");
        tv_email.setText(email);
//        id = "6";


        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.show();

                if (et_password.getText().length()==0){
                    Toast.makeText(loan_otp_verification.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                    et_password.setError("Enter OTP");
                }else {
                    WebService.getClient().Loan_formate(id,et_password.getText().toString()).enqueue(new Callback<Loan_formate>() {
                        @Override
                        public void onResponse(Call<Loan_formate> call, Response<Loan_formate> response) {
                            pro.dismiss();
                            if (response.body().getMsg().equals("Record Update Succesfully")){
                                Intent intent = new Intent(loan_otp_verification.this,Loan_final_Activity.class);
                                intent.putExtra("empname", response.body().getEmpname());
                                intent.putExtra("desc", response.body().getDesc());
                                intent.putExtra("amt", response.body().getAmt());
                                intent.putExtra("paymentid", response.body().getOtp());
                                intent.putExtra("date", response.body().getCdate());

                                startActivity(intent);

                            }else {
                                Toast.makeText(loan_otp_verification.this, "Enter Valid OTP", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Loan_formate> call, Throwable t) {
                            pro.dismiss();
                            Toast.makeText(loan_otp_verification.this, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}