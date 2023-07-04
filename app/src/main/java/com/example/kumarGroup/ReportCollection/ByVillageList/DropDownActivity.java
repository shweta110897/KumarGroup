package com.example.kumarGroup.ReportCollection.ByVillageList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.R;

import java.util.Arrays;
import java.util.List;

public class DropDownActivity extends AppCompatActivity {

    Spinner spn_village_type;
    List<String> type = Arrays.asList(new String[]{"Select Option","Booking Delivery", "Spar Part"});
    String village_type;
    Button btn_Next;

    public static boolean Type_Check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down);

        getSupportActionBar().setTitle("Select Category");

        spn_village_type = findViewById(R.id.spn_village_type);
        btn_Next = findViewById(R.id.btn_Next);


        ArrayAdapter adapterStage2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, type);
        spn_village_type.setAdapter(adapterStage2);

        spn_village_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                village_type = type.get(i);

                if (village_type.equals("Booking Delivery")){
                    Type_Check = true;

                }else {
                    Type_Check = false;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (village_type.equals("Select Option")){
                    Toast.makeText(getApplicationContext(),"Select Option ",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(DropDownActivity.this,VillageListActivity.class);
                    intent.putExtra("type",village_type);
                    startActivity(intent);
                }
            }
        });

    }
}