package com.example.kumarGroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    Button btn_reset;
    EditText et_password,et_Confpassword;
    String Mobile;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Mobile=getIntent().getStringExtra("Mobile");

        btn_reset=findViewById(R.id.btn_reset);
        et_password=findViewById(R.id.et_password);
        et_Confpassword=findViewById(R.id.et_Confpassword);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_password.getText().toString().equals(et_Confpassword.getText().toString())){

                    progressDialog = new ProgressDialog(ResetPasswordActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent
                    );

                    WebService.getClient().resetPassword(Mobile,et_password.getText().toString()
                    ).enqueue(new Callback<Forgotpassword>() {
                        @Override
                        public void onResponse(@NotNull Call<Forgotpassword> call, @NotNull Response<Forgotpassword> response) {

                            Log.d("login", "onResponse:forgotpassword" + response.body().toString());

                            progressDialog.dismiss();

                            assert response.body() != null;
                            if (response.body() == null) {
                                Utils.showErrorToast(ResetPasswordActivity.this, "Please Enter valid password");
                            } else {
//                                finish();
                                Intent intent=new Intent(ResetPasswordActivity.this,FoLogin.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<Forgotpassword> call, @NotNull Throwable t) {
                            Log.d("fail", "onFailure: " + t);
                            Toast.makeText(ResetPasswordActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(ResetPasswordActivity.this, "Password does not match!!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
