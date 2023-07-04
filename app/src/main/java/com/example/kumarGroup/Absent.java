package com.example.kumarGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Absent extends AppCompatActivity {
    EditText et_reason;
    Button btn_reson;
    String id;
    SharedPreferences sp;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent2);
        getSupportActionBar().setTitle( ( "Absemt"));

        et_reason=findViewById(R.id.et_reason);
        btn_reson=findViewById(R.id.btn_reson);
        sp = getSharedPreferences("Login", MODE_PRIVATE);

        id=sp.getString("id","");
        Log.d("uhagdgagsdg", "onCreate: "+id);

        btn_reson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_reason.getText().toString().equals("")){
                    Utils.showErrorToast(Absent.this,"Please Enter Absent Reason");

                }
                if (!et_reason.getText().toString().equals(""))
                {

                    progressDialog= new ProgressDialog(Absent.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent
                    );
                    WebService.getClient().getabsent(et_reason.getText().toString(),id).enqueue(new Callback<AbsentresonModel>() {
                        @Override
                        public void onResponse(Call<AbsentresonModel> call, Response<AbsentresonModel> response) {
                            progressDialog.dismiss();

                            //       AbsentresonModel AbsentresonModel = response.body().getMessage().;
                         //   Log.d("Response", response.body().toString());
                            Toast.makeText(Absent.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();

                            Intent i=new Intent(Absent.this,FoDashbord.class);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(Call<AbsentresonModel> call, Throwable t) {
                            progressDialog.dismiss();

                            Log.d("Response", t.getMessage().toString());
                         //   Toast.makeText(Absent.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }
        });

    }
}