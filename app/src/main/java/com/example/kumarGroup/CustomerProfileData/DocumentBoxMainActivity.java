package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kumarGroup.R;

public class DocumentBoxMainActivity extends AppCompatActivity {

    EditText edt_mobileDocumentBox;
    Button btnSubmitDocBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_box_main);

        getSupportActionBar().setTitle("Document Box");

        edt_mobileDocumentBox=findViewById(R.id.edt_mobileDocumentBox);
        btnSubmitDocBox=findViewById(R.id.btnSubmitDocBox);


        btnSubmitDocBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_mobileDocumentBox.getText().equals("")){
                    edt_mobileDocumentBox.setError("Please Enter Number");
                }

                Intent i = new Intent(DocumentBoxMainActivity.this,
                        DocumentBoxSecondActivity.class);
                i.putExtra("MobileNo",edt_mobileDocumentBox.getText().toString());
                startActivity(i);
            }
        });
    }
}