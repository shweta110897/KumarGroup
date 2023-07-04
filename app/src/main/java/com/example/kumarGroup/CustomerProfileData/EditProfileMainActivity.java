package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kumarGroup.R;

public class EditProfileMainActivity extends AppCompatActivity {

    EditText edt_ViewMobileEdit;

    Button btnViewMobileEditSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_main);



        edt_ViewMobileEdit=findViewById(R.id.edt_ViewMobileEdit);

        btnViewMobileEditSubmit=findViewById(R.id.btnViewMobileEditSubmit);


        btnViewMobileEditSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_ViewMobileEdit.getText().equals("")){
                    edt_ViewMobileEdit.setError("Please Enter Number");
                }
                    Intent i = new Intent(EditProfileMainActivity.this, EditProfileFormActivity.class);
                    i.putExtra("MobileNo",edt_ViewMobileEdit.getText().toString());
                    startActivity(i);
            }
        });
    }
}