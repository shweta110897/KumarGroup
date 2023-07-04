package com.example.kumarGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.CustomerProfileData.CategoryPermissionMainActivity;
import com.example.kumarGroup.Loan.ManageLoanActivity;
import com.example.kumarGroup.Maintenance.MaintananceMainActivity;
import com.example.kumarGroup.WalletAccountLedger.WalletAccountLadgerView;
import com.example.kumarGroup.WalletInsensitive.InsensitiveDeliveryListDisplayActivity;
import com.example.kumarGroup.WalletInsensitive.InsensitiveListDisplay;
import com.example.kumarGroup.WalletOverTime.OverTimeDateSelectActivity;
import com.example.kumarGroup.WalletSlarySlip.WalletSalarySlipActivity;
import com.example.kumarGroup.WalletTraveling.WalletPaymentCollection;
import com.example.kumarGroup.WalletTraveling.WalletTravelingDateActivity;

public class Wallet extends AppCompatActivity {

    TextView tv_Attandance,tv_saleryslip,tv_loan,tv_Insensitive,tv_DelInsensitive,tv_Maintenance,tv_wallet_CategoryPermission,tv_OverTime,tv_Travelling,tv_ac_ledger,tv_pay_collection;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Wallet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_wallet);
        
        tv_Attandance=findViewById(R.id.tv_Attandance);
        tv_saleryslip=findViewById(R.id.tv_saleryslip);
        tv_loan=findViewById(R.id.tv_loan);
        tv_Insensitive=findViewById(R.id.tv_Insensitive);
        tv_DelInsensitive=findViewById(R.id.tv_DelInsensitive);
        tv_Maintenance=findViewById(R.id.tv_Maintenance);
        tv_wallet_CategoryPermission=findViewById(R.id.tv_wallet_CategoryPermission);
        tv_OverTime=findViewById(R.id.tv_OverTime);
        tv_Travelling=findViewById(R.id.tv_Travelling);
        tv_ac_ledger=findViewById(R.id.tv_ac_ledger);
        tv_pay_collection=findViewById(R.id.tv_pay_collection);


        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);
        txt7=findViewById(R.id.txt7);
        txt8=findViewById(R.id.txt8);
        txt9=findViewById(R.id.txt9);
        txt10=findViewById(R.id.txt10);

        tv_Attandance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               txt1.setBackgroundResource(R.drawable.enable_page);
               txt2.setBackgroundResource(R.drawable.disable_page);
               txt3.setBackgroundResource(R.drawable.disable_page);
               txt4.setBackgroundResource(R.drawable.disable_page);
               txt5.setBackgroundResource(R.drawable.disable_page);
               txt6.setBackgroundResource(R.drawable.disable_page);
               txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

              //  Toast.makeText(Wallet.this, "coming soon", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(Wallet.this,WalletAttendance.class);
                Intent i = new Intent(Wallet.this,WalletSelectDateAttendence.class);
                startActivity(i);

            }
        });


        tv_saleryslip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                //  Toast.makeText(Wallet.this, "coming soon", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Wallet.this, WalletSalarySlipActivity.class);
                startActivity(i);

            }
        });


        tv_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(Wallet.this, ManageLoanActivity.class);
                startActivity(i);
            }
        });

        tv_Insensitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(Wallet.this, InsensitiveListDisplay.class);
                startActivity(i);
            }
        });

        tv_DelInsensitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.enable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(Wallet.this, InsensitiveDeliveryListDisplayActivity.class);
                startActivity(i);
            }
        });

        tv_wallet_CategoryPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.enable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

               /* Intent i = new Intent(Wallet.this, MaintananceMainActivity.class);
                startActivity(i); */

                Intent i = new Intent(Wallet.this, CategoryPermissionMainActivity.class);
                startActivity(i);
            }
        });

        tv_Maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.enable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(Wallet.this, MaintananceMainActivity.class);
                startActivity(i);
            }
        });


        tv_OverTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.enable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(Wallet.this, OverTimeDateSelectActivity.class);
                startActivity(i);
            }
        });



        tv_Travelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.enable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(Wallet.this, WalletTravelingDateActivity.class);
                startActivity(i);
            }
        });


        tv_ac_ledger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.enable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(Wallet.this, WalletAccountLadgerView.class);
                startActivity(i);
            }
        });


        tv_pay_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(Wallet.this, WalletPaymentCollection.class);
                startActivity(i);
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