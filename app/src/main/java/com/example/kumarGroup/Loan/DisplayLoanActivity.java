package com.example.kumarGroup.Loan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.kumarGroup.DisplayLoanFinalModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayLoanActivity extends AppCompatActivity {

    RecyclerView RclvDisplayLoan;
    Button btnDisplayLoanAdd;

    ProgressDialog progressDialog;
    String date1,date2;
    SharedPreferences sp1;
    String emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_display_loan);
        getSupportActionBar().setTitle("Loan");

        RclvDisplayLoan=findViewById(R.id.RclvDisplayLoan);
      //  btnDisplayLoanAdd=findViewById(R.id.btnDisplayLoanAdd);

        date1 = getIntent().getStringExtra("StartingDateOneLoan");
        date2 = getIntent().getStringExtra("EndingDateTwoLoan");


        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");


        RclvDisplayLoan.setHasFixedSize(true);
        RclvDisplayLoan.setLayoutManager(new LinearLayoutManager(this));



        progressDialog= new ProgressDialog(DisplayLoanActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

       WebService.getClient().getDisplayLoanFinal(emp,date1,date2).enqueue(new Callback<DisplayLoanFinalModel>() {
           @Override
           public void onResponse(Call<DisplayLoanFinalModel> call, Response<DisplayLoanFinalModel> response) {
               progressDialog.dismiss();
               if(response.body().getLoan_display().size()==0){
                   Utils.showErrorToast(DisplayLoanActivity.this,"No data available");
               }
               else
               {
                   DisplayLoanAdapter  displayLoanAdapter = new DisplayLoanAdapter(DisplayLoanActivity.this,response.body().getLoan_display());
                   RclvDisplayLoan.setAdapter(displayLoanAdapter);
               }
           }

           @Override
           public void onFailure(Call<DisplayLoanFinalModel> call, Throwable t) {
               progressDialog.dismiss();
           }
       });

    }
}
