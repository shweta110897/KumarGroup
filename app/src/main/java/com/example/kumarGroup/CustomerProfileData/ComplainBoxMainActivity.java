package com.example.kumarGroup.CustomerProfileData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kumarGroup.R;

public class ComplainBoxMainActivity extends AppCompatActivity {

    EditText edt_mobileCustomerProfile;
    Button btnSubmitCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_box_main);

        getSupportActionBar().setTitle("Complaint Box");


        edt_mobileCustomerProfile=findViewById(R.id.edt_mobileCustomerProfile);
        btnSubmitCB=findViewById(R.id.btnSubmitCB);


        btnSubmitCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_mobileCustomerProfile.getText().equals("")){
                    edt_mobileCustomerProfile.setError("Please Enter Number");
                }
                Intent i = new Intent(ComplainBoxMainActivity.this, ComplainBoxSecondActivity.class);
                i.putExtra("MobileNo",edt_mobileCustomerProfile.getText().toString());
                startActivity(i);
            }
        });
    }
    public Menu option_Menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_form, menu);
        option_Menu = menu;
        menu.findItem(R.id.action_add).setVisible(false);
        return true;
    }

    public void setMenuVisible(boolean visible, int id) {
        if (option_Menu != null) {
            option_Menu.findItem(id).setVisible(visible);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.action_add ){


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}