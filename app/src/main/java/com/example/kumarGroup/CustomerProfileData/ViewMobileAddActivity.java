package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kumarGroup.R;

public class ViewMobileAddActivity extends AppCompatActivity {


    EditText edt_ViewMobileAdd;

    Button btnViewMobileAddSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mobile_add);


        edt_ViewMobileAdd=findViewById(R.id.edt_ViewMobileAdd);

        btnViewMobileAddSubmit=findViewById(R.id.btnViewMobileAddSubmit);



        btnViewMobileAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_ViewMobileAdd.getText().equals("")){
                    edt_ViewMobileAdd.setError("Please Enter Number");
                }

                Intent i = new Intent(ViewMobileAddActivity.this,ViewCustomerDatadisplayMainActivity.class);
                i.putExtra("MobileNo",edt_ViewMobileAdd.getText().toString());
                startActivity(i);


            }
        });

    }
}