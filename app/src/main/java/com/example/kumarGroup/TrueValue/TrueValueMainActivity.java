package com.example.kumarGroup.TrueValue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.ReviewHistoryCount;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrueValueMainActivity extends AppCompatActivity {

    EditText edt_TrueValueMobileNo;
    Button btnTrueValueSubmit;

    TextView tv_firstmeetingReview,tv_HotReview,tv_coldReview,tv_WarmReview,tv_SellLost,tv_drop,tv_numberplate,tv_service,tv_delivery,tv_newvisit;
    TextView tv_count_delivery,tv_count_service,tv_count_walking,tv_count_numberplate,tv_count_activity,tv_count_firstmetting,tv_count_cold,
            tv_count_warm,tv_count_hot,tv_count_selllost,tv_count_drop,tv_activity,tv_walking,tv_count_newvisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_value_main);

        getSupportActionBar().setTitle("Feedback/Review");

        tv_newvisit=findViewById(R.id.tv_newvisit);
        tv_firstmeetingReview=findViewById(R.id.tv_firstmeetingReview);
        tv_HotReview=findViewById(R.id.tv_HotReview);
        tv_coldReview=findViewById(R.id.tv_coldReview);
        tv_WarmReview=findViewById(R.id.tv_WarmReview);
        tv_SellLost=findViewById(R.id.tv_SellLost);
        tv_drop=findViewById(R.id.tv_drop);
        tv_numberplate=findViewById(R.id.tv_numberplate);
        tv_service=findViewById(R.id.tv_service);
        tv_delivery=findViewById(R.id.tv_delivery);
        tv_activity=findViewById(R.id.tv_activity);
        tv_walking=findViewById(R.id.tv_walking);

        //count view binding
        tv_count_newvisit=findViewById(R.id.tv_count_newvisit);
        tv_count_delivery=findViewById(R.id.tv_count_delivery);
        tv_count_service=findViewById(R.id.tv_count_service);
        tv_count_walking=findViewById(R.id.tv_count_walking);
        tv_count_numberplate=findViewById(R.id.tv_count_numberplate);
        tv_count_activity=findViewById(R.id.tv_count_activity);
        tv_count_firstmetting=findViewById(R.id.tv_count_firstmetting);
        tv_count_warm=findViewById(R.id.tv_count_warm);
        tv_count_hot=findViewById(R.id.tv_count_hot);
        tv_count_cold=findViewById(R.id.tv_count_cold);
        tv_count_selllost=findViewById(R.id.tv_count_selllost);
        tv_count_drop=findViewById(R.id.tv_count_drop);


        tv_firstmeetingReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ReviewList.firstmeeting_review_flag=true;
                    Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                    startActivity(intent);
            }
        });

        tv_newvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ReviewList.new_visit_falg=true;
                    Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                    startActivity(intent);
            }
        });

        tv_HotReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.hotinq_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });

        tv_coldReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.coldinq_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });

        tv_WarmReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.warminq_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });

        tv_SellLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.selllost_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });

        tv_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.drop_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });

        tv_numberplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.numbrplate_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });


        tv_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.serviceandcomplain_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });



        tv_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.delivery_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });

        tv_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.activity_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });

        tv_walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewList.walking_review_flag=true;
                Intent intent=new Intent(TrueValueMainActivity.this,ReviewList.class);
                startActivity(intent);
            }
        });

        /*getReviewHistoryCount()*/
        WebService.getClient().getReviewHistoryCount().enqueue(new Callback<ReviewHistoryCount>() {
            @Override
            public void onResponse(@NotNull Call<ReviewHistoryCount> call,
                                   @NotNull Response<ReviewHistoryCount> response) {
                assert response.body() != null;
                Log.d("done__", "onResponse: "+response.body().getActivities());
                tv_count_delivery.setText(response.body().getDelivry());
                tv_count_service.setText(response.body().getService_complaint());
                tv_count_walking.setText(response.body().getWalking_visit());
                tv_count_numberplate.setText(response.body().getNumber_plate_fitting());
                tv_count_activity.setText(response.body().getActivities());
                tv_count_firstmetting.setText(response.body().getFirstMeeting());
                tv_count_warm.setText(response.body().getWarm());
                tv_count_hot.setText(response.body().getHot());
                tv_count_cold.setText(response.body().getCold());
                tv_count_selllost.setText(response.body().getSelllost());
                tv_count_drop.setText(response.body().getDropInq());
                tv_count_newvisit.setText(response.body().getNew_visit());


                // Toast.makeText(DeliveryReportButtonMain.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<ReviewHistoryCount> call,
                                  @NotNull Throwable t) {
                Log.d("done__", "onResponse: "+t.getCause());

            }
        });

        /*edt_TrueValueMobileNo=findViewById(R.id.edt_TrueValueMobileNo);
        btnTrueValueSubmit=findViewById(R.id.btnTrueValueSubmit);

        btnTrueValueSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_TrueValueMobileNo.getText().equals("")){
                    edt_TrueValueMobileNo.setError("Please Enter Number");
                }

                Intent i = new Intent(TrueValueMainActivity.this,
                        FormDetailsTvActivity.class);
                i.putExtra("MobileNo",edt_TrueValueMobileNo.getText().toString());
                startActivity(i);

            }
        });*/

    }
}