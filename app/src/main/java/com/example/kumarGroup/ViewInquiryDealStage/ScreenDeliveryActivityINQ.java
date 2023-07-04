package com.example.kumarGroup.ViewInquiryDealStage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.delivery_screen_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScreenDeliveryActivityINQ extends AppCompatActivity {
    String id,sname;
    TextView basictxt,basicAmount,addinqtxt,addinqAmount,firstmeettxt,firstmeetAmount,activitytxt,
            activityAmount,offermeetingtxt,offermeetingAmount,bookingtxtt,bookingAmount,loancleartxt,
            loanclearAmouont,dpcleartxt,dpclearAmount,oldvehicalorRcbooktxt,oldvehicalorRcbookAmount,
            totalAmount;
    ProgressDialog pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_delivery_inq);

        getSupportActionBar().setTitle("Delivery Data View Inquiry");

        id = getIntent().getStringExtra("id");
        sname = getIntent().getStringExtra("sname");

        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait");

        basictxt = findViewById(R.id.basictxt);
        basicAmount = findViewById(R.id.basicAmount);
        addinqtxt = findViewById(R.id.addinqtxt);
        addinqAmount = findViewById(R.id.addinqAmount);
        firstmeettxt = findViewById(R.id.firstmeettxt);
        firstmeetAmount = findViewById(R.id.firstmeetAmount);
        activitytxt = findViewById(R.id.activitytxt);
        activityAmount = findViewById(R.id.activityAmount);
        offermeetingtxt = findViewById(R.id.offermeetingtxt);
        offermeetingAmount = findViewById(R.id.offermeetingAmount);
        bookingtxtt = findViewById(R.id.bookingtxtt);
        bookingAmount = findViewById(R.id.bookingAmount);
        loancleartxt = findViewById(R.id.loancleartxt);
        loanclearAmouont = findViewById(R.id.loanclearAmouont);
        dpcleartxt = findViewById(R.id.dpcleartxt);
        dpclearAmount = findViewById(R.id.dpclearAmount);
        oldvehicalorRcbooktxt = findViewById(R.id.oldvehicalorRcbooktxt);
        oldvehicalorRcbookAmount = findViewById(R.id.oldvehicalorRcbookAmount);
        totalAmount = findViewById(R.id.totalAmount);

        //Toast.makeText(ScreenDeliveryActivityINQ.this, "id "+id + " sname "+sname, Toast.LENGTH_SHORT).show();
        WebService.getClient().getdeliveryScreen_web(id,sname).enqueue(new Callback<delivery_screen_model>() {
            @Override
            public void onResponse(Call<delivery_screen_model> call, Response<delivery_screen_model> response) {
                basictxt.setText(response.body().getBasic_stage());
                basicAmount.setText(response.body().getBasic_price());
                addinqtxt.setText(response.body().getAdd_inq_stage());
                addinqAmount.setText(response.body().getAdd_inq_price());
                firstmeettxt.setText(response.body().getFirst_meet_stage());
                firstmeetAmount.setText(response.body().getFirst_meet_price());
                activitytxt.setText(response.body().getNext_act_stage());
                activityAmount.setText(response.body().getNext_act_price());
                offermeetingtxt.setText(response.body().getOffer_meet_stage());
                offermeetingAmount.setText(response.body().getOffer_meet_price());
                bookingtxtt.setText(response.body().getBooking_stage());
                bookingAmount.setText(response.body().getBooking_price());
                loancleartxt.setText(response.body().getLoan_stage());
                loanclearAmouont.setText(response.body().getLoan_price());
                dpcleartxt.setText(response.body().getDp_stage());
                dpclearAmount.setText(response.body().getDp_price());
                oldvehicalorRcbooktxt.setText(response.body().getOld_rc_stage());
                oldvehicalorRcbookAmount.setText(response.body().getOld_rc_price());
                totalAmount.setText(String.valueOf(response.body().getTotal()));
                pro.dismiss();
            }

            @Override
            public void onFailure(Call<delivery_screen_model> call, Throwable t) {
                pro.dismiss();
                Utils.showErrorToast(ScreenDeliveryActivityINQ.this,t.getMessage());
            }
        });
    }
}