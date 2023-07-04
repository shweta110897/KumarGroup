package com.example.kumarGroup.SmartCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.VendorDashboard.ViewDocumentMainActivity;

public class AddSmartCardFirstActivity extends AppCompatActivity {

    TextView txt_AddPintValue,txt_ViewAccount;

    TextView txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_smart_card_first);

        getSupportActionBar().setTitle("Add Smart Card");


        txt_AddPintValue=findViewById(R.id.txt_AddPintValue);
        txt_ViewAccount=findViewById(R.id.txt_ViewAccount);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);


        txt_AddPintValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(AddSmartCardFirstActivity.this,AddPointValueFirstActivity.class);
                startActivity(i);
            }
        });



        txt_ViewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(AddSmartCardFirstActivity.this, ViewDocumentMainActivity.class);
                startActivity(i);
            }
        });
    }
}