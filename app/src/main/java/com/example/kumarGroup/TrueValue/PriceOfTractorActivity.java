package com.example.kumarGroup.TrueValue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;

public class PriceOfTractorActivity extends AppCompatActivity {

    TextView txtPriceOfTractor;

    String Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_of_tractor2);

        getSupportActionBar().setTitle("True Value of Tractor");


        txtPriceOfTractor = findViewById(R.id.txtPriceOfTractor);

        Price = getIntent().getStringExtra("Price");


        txtPriceOfTractor.setText("Price Of Tractor: \n\n " + Price);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(PriceOfTractorActivity.this, FoDashbord.class);
        startActivity(i);
        super.onBackPressed();
    }
}