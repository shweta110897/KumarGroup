package com.example.kumarGroup.Loan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.R;

public class Loan_final_Activity extends AppCompatActivity {

    String empname,desc,amt,paymentid,date;
    TextView Empname,txt_dec,txt_amt,txt_paymentid,txt_date;
    Button btn_share;
    public static boolean click_check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_final);

        empname = getIntent().getStringExtra("empname");
        desc = getIntent().getStringExtra("desc");
        amt = getIntent().getStringExtra("amt");
        paymentid = getIntent().getStringExtra("paymentid");
        date = getIntent().getStringExtra("date");

        Empname = findViewById(R.id.Empname);
        txt_dec = findViewById(R.id.txt_dec);
        txt_amt = findViewById(R.id.txt_amt);
        txt_paymentid = findViewById(R.id.txt_paymentid);
        txt_date = findViewById(R.id.txt_date);
        btn_share = findViewById(R.id.btn_share);

        Empname.setText(empname);
        txt_dec.setText(desc);
        txt_amt.setText("INR "+amt);
        txt_paymentid.setText(paymentid);
        txt_date.setText(date);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed){
                    click_check = true;
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "Payment Request From \n"+empname+"\n\n"+"PAYMENT FOR \n"+desc+"\n\n"
                            +"AMOUNT PAYBLE \n"+amt+"\n\n"+"PAYMENT OTP \n"+paymentid+"\n\n"+"DATE \n"
                            +date+"\n\n\n"+"For amy queries, please contact \n Kumar Group \n Mo No: 7500567770\n Email: M.skumarautomobiles@gmail.com";
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(waIntent, "Share with"));
//                    startActivity(intent);


                }else {

                    Toast.makeText(getApplicationContext(), "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                }

            }
            private boolean appInstallOrNot(String url){
                PackageManager packageManager = getPackageManager();
                boolean app_installed;
                try{

                    packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
                    app_installed = true;
                }catch (PackageManager.NameNotFoundException e){
                    app_installed =false;
                }
                return app_installed;
            }


        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (click_check){
            click_check = false;
            finish();
            Intent intent = new Intent(Loan_final_Activity.this,ManageLoanActivity.class);
            startActivity(intent);
        }
    }

}