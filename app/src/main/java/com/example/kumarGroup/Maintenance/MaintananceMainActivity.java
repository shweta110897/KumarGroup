package com.example.kumarGroup.Maintenance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kumarGroup.R;

public class MaintananceMainActivity extends AppCompatActivity {

    Button btnMaintenanceAdd,btnMaintenanceShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_maintanance_main);
        getSupportActionBar().setTitle("Maintenance");

        btnMaintenanceAdd=findViewById(R.id.btnMaintenanceAdd);
        btnMaintenanceShow=findViewById(R.id.btnMaintenanceShow);


        btnMaintenanceAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaintananceMainActivity.this, AddMaintenanceActivity.class);
                startActivity(i);
            }
        });


        btnMaintenanceShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaintananceMainActivity.this, SelectMaintenanceDateActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}