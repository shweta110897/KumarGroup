package com.example.kumarGroup.ReportCollection.GeneralVisit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kumarGroup.PaymentCollectionBorrowListCount;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Type_Count_Activity extends AppCompatActivity {

    String type;
    TextView txtPaymentCollectionCount;
    LinearLayout lin_MainGeneralVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_count);

        type = getIntent().getStringExtra("type");

        txtPaymentCollectionCount = findViewById(R.id.txtPaymentCollectionCount);
        lin_MainGeneralVisit = findViewById(R.id.lin_MainGeneralVisit);


        WebService.getClient().getPCBorrowListCount(type).enqueue(new Callback<PaymentCollectionBorrowListCount>() {
            @Override
            public void onResponse(@NotNull Call<PaymentCollectionBorrowListCount> call,
                                   @NotNull Response<PaymentCollectionBorrowListCount> response)
            {
                assert response.body() != null;
                txtPaymentCollectionCount.setText(String.valueOf(response.body().getCount()));
            }

            @Override
            public void onFailure(@NotNull Call<PaymentCollectionBorrowListCount> call, @NotNull Throwable t) {

            }
        });

        lin_MainGeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Type_Count_Activity.this,ShowGeneralVisitDataActivity.class);
                startActivity(intent);
            }
        });

    }
}