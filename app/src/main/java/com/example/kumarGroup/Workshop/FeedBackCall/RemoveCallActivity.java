package com.example.kumarGroup.Workshop.FeedBackCall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kumarGroup.DeleteCallWorkshopModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoveCallActivity extends AppCompatActivity {

    String Id,MobileNo,emp;

    SharedPreferences sp1;

    EditText EdtRemoveDescription;
    Button btnSubmit_RemoveRemark;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_call);


        EdtRemoveDescription=findViewById(R.id.EdtRemoveDescription);
        btnSubmit_RemoveRemark=findViewById(R.id.btnSubmit_RemoveRemark);

        Id= getIntent().getStringExtra("Id");
        MobileNo= getIntent().getStringExtra("MobileNo");

        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");





        btnSubmit_RemoveRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(EdtRemoveDescription.getText().toString().equals("")){
                    EdtRemoveDescription.setError("Please Enter Remove Remark");
                }
                else {

                    progressDialog = new ProgressDialog(RemoveCallActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    WebService.getClient().getDeleteCallWs(emp,
                            Id,
                            MobileNo,
                            EdtRemoveDescription.getText().toString()).enqueue(new Callback<DeleteCallWorkshopModel>() {
                        @Override
                        public void onResponse(@NotNull Call<DeleteCallWorkshopModel> call,
                                               @NotNull Response<DeleteCallWorkshopModel> response) {

                            progressDialog.dismiss();
                            assert response.body() != null;
                            Toast.makeText(RemoveCallActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(RemoveCallActivity.this,FeedbcakCallMainActivity.class);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(@NotNull Call<DeleteCallWorkshopModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(RemoveCallActivity.this, "Try Again", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


    }
}