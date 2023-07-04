package com.example.kumarGroup.DeliveryReport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kumarGroup.R;

public class AddDRMainActivity extends AppCompatActivity {


    EditText edt_ViewMobileAddDR;
    Button btnViewMobileAddSubmitDR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_d_r_main);

        edt_ViewMobileAddDR=findViewById(R.id.edt_ViewMobileAddDR);

        btnViewMobileAddSubmitDR=findViewById(R.id.btnViewMobileAddSubmitDR);



        btnViewMobileAddSubmitDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_ViewMobileAddDR.getText().equals("")){
                    edt_ViewMobileAddDR.setError("Please Enter Number");
                }

                Intent i = new Intent(AddDRMainActivity.this, AddDrViewActivity.class);
                i.putExtra("MobileNo",edt_ViewMobileAddDR.getText().toString());
                startActivity(i);


            }
        });
    }
}