package com.example.kumarGroup.Loan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kumarGroup.R;

public class ManageLoanActivity extends AppCompatActivity {

    Button btnManageLoan_EnterLoan,btnManageLoan_ShowLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_loan);
        getSupportActionBar().setTitle("Manage Loans");

        btnManageLoan_EnterLoan = findViewById(R.id.btnManageLoan_EnterLoan);
        btnManageLoan_ShowLoan = findViewById(R.id.btnManageLoan_ShowLoan);

        btnManageLoan_EnterLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageLoanActivity.this, AddLoanDetailActivity.class);
                startActivity(i);
            }
        });


        btnManageLoan_ShowLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageLoanActivity.this, SelectDateForLoanActivity.class);
                startActivity(i);
            }
        });
    }
}