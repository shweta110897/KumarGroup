package com.example.kumarGroup.OverTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.R;

public class CheckInCheckOutLabelActivity extends AppCompatActivity {

    TextView txtChInChOut_CheckIn,txtChInChOut_CheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_check_out_label);

        txtChInChOut_CheckIn=findViewById(R.id.txtChInChOut_CheckIn);
        txtChInChOut_CheckOut=findViewById(R.id.txtChInChOut_CheckOut);

        txtChInChOut_CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckInCheckOutLabelActivity.this, StartOverTimeOvertimeActivity.class);
                startActivity(i);
            }
        });


       /* txtChInChOut_CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckInCheckOutLabelActivity.this, CheckOutStartOvertimeActivity.class);
                startActivity(i);
            }
        });*/
    }
}