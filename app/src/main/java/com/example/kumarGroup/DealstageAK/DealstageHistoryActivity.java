package com.example.kumarGroup.DealstageAK;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.deal_stage_history_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealstageHistoryActivity extends AppCompatActivity {

    String sname;
    TextView dl_ht_stage_1,dl_ht_date_1,dl_ht_st_1,addemp_stage_2,addemp_date_2,addemp_st_2
            ,follow_ps_stage_3,follow_ps_dt_3,follow_ps_st_3,notattan_st_4,notattan_dt_4,notattan_ss_4
            ,fistmeeting_st_5,fistmeeting_dt_5,fistmeeting_ss_5,second_st_6,second_dt_6,second_ss_6,
            booking_st_11,booking_dt_11,booking_ss_11,delivery_st_12,delivery_dt_12,delivery_ss_12,
            sellost_st_17,sellost_dt_17,sellost_ss_17,drop_st_18,drop_dt_18,drop_ss_18,
            recentnot,location,nextactivtiyplan_st_7,nextactivtiyplan_dt_7,nextactivtiyplan_ss_7,
            nextactivtiyplan_st_7_2,nextactivtiyplan_dt_7_2,nextactivtiyplan_ss_7_2,
            nextactivtiyplan_st_7_3,nextactivtiyplan_dt_7_3,nextactivtiyplan_ss_7_3,
            makeandoffer_st_8_1,makeandoffer_dt_8_1,makeandoffer_ss_8_1,
            makeandoffer_st_8_2,makeandoffer_dt_8_2,makeandoffer_ss_8_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealstage_history);

        getSupportActionBar().setTitle("Deal Stage History");

        dl_ht_stage_1 = findViewById(R.id.dl_ht_stage_1);
        dl_ht_date_1 = findViewById(R.id.dl_ht_date_1);
        dl_ht_st_1 = findViewById(R.id.dl_ht_st_1);

        addemp_stage_2 = findViewById(R.id.addemp_stage_2);
        addemp_date_2 = findViewById(R.id.addemp_date_2);
        addemp_st_2 = findViewById(R.id.addemp_st_2);

        follow_ps_stage_3 = findViewById(R.id.follow_ps_stage_3);
        follow_ps_dt_3 = findViewById(R.id.follow_ps_dt_3);
        follow_ps_st_3 = findViewById(R.id.follow_ps_st_3);

        notattan_st_4 = findViewById(R.id.notattan_st_4);
        notattan_dt_4 = findViewById(R.id.notattan_dt_4);
        notattan_ss_4 = findViewById(R.id.notattan_ss_4);

        fistmeeting_st_5 = findViewById(R.id.fistmeeting_st_5);
        fistmeeting_dt_5 = findViewById(R.id.fistmeeting_dt_5);
        fistmeeting_ss_5 = findViewById(R.id.fistmeeting_ss_5);

        second_st_6 = findViewById(R.id.second_st_6);
        second_dt_6 = findViewById(R.id.second_dt_6);
        second_ss_6 = findViewById(R.id.second_ss_6);

        nextactivtiyplan_st_7 = findViewById(R.id.nextactivtiyplan_st_7);
        nextactivtiyplan_dt_7 = findViewById(R.id.nextactivtiyplan_dt_7);
        nextactivtiyplan_ss_7 = findViewById(R.id.nextactivtiyplan_ss_7);

        nextactivtiyplan_st_7_2 = findViewById(R.id.nextactivtiyplan_st_7_2);
        nextactivtiyplan_dt_7_2 = findViewById(R.id.nextactivtiyplan_dt_7_2);
        nextactivtiyplan_ss_7_2 = findViewById(R.id.nextactivtiyplan_ss_7_2);

        nextactivtiyplan_st_7_3 = findViewById(R.id.nextactivtiyplan_st_7_3);
        nextactivtiyplan_dt_7_3 = findViewById(R.id.nextactivtiyplan_dt_7_3);
        nextactivtiyplan_ss_7_3 = findViewById(R.id.nextactivtiyplan_ss_7_3);

        makeandoffer_st_8_1 = findViewById(R.id.makeandoffer_st_8_1);
        makeandoffer_dt_8_1 = findViewById(R.id.makeandoffer_dt_8_1);
        makeandoffer_ss_8_1 = findViewById(R.id.makeandoffer_ss_8_1);

        makeandoffer_st_8_2 = findViewById(R.id.makeandoffer_st_8_2);
        makeandoffer_dt_8_2 = findViewById(R.id.makeandoffer_dt_8_2);
        makeandoffer_ss_8_2 = findViewById(R.id.makeandoffer_ss_8_2);

        booking_st_11 = findViewById(R.id.booking_st_11);
        booking_dt_11 = findViewById(R.id.booking_dt_11);
        booking_ss_11 = findViewById(R.id.booking_ss_11);

        delivery_st_12 = findViewById(R.id.delivery_st_12);
        delivery_dt_12 = findViewById(R.id.delivery_dt_12);
        delivery_ss_12 = findViewById(R.id.delivery_ss_12);

        sellost_st_17 = findViewById(R.id.sellost_st_17);
        sellost_dt_17 = findViewById(R.id.sellost_dt_17);
        sellost_ss_17 = findViewById(R.id.sellost_ss_17);

        drop_st_18 = findViewById(R.id.drop_st_18);
        drop_dt_18 = findViewById(R.id.drop_dt_18);
        drop_ss_18 = findViewById(R.id.drop_ss_18);

        recentnot = findViewById(R.id.recentnot);
        location = findViewById(R.id.location);

        sname=getIntent().getStringExtra("sname");

        WebService.getClient().dealstage_history_web(sname).enqueue(new Callback<deal_stage_history_model>() {
            @Override
            public void onResponse(Call<deal_stage_history_model> call, Response<deal_stage_history_model> response) {

                dl_ht_stage_1.setText(response.body().getAdd_date_stage());
                dl_ht_date_1.setText(response.body().getAdd_date_date());
                dl_ht_st_1.setText(response.body().getAdd_date_status());

                addemp_stage_2.setText(response.body().getAdd_Employee_stage());
                addemp_date_2.setText(response.body().getAdd_Employee_date());
                addemp_st_2.setText(response.body().getAdd_Employee_status());

                follow_ps_stage_3.setText(response.body().getFollow_person_stage());
                follow_ps_dt_3.setText(response.body().getFollow_person_date());
                follow_ps_st_3.setText(response.body().getFollow_person_status());

                notattan_st_4.setText(response.body().getNot_attand_stage());
                notattan_dt_4.setText(response.body().getNot_attand_date());
                notattan_ss_4.setText(response.body().getNot_attand_status());

                fistmeeting_st_5.setText(response.body().getFirst_metting_stage());
                fistmeeting_dt_5.setText(response.body().getFirst_metting_date());
                fistmeeting_ss_5.setText(response.body().getFirst_metting_status());

                second_st_6.setText(response.body().getSecond_metting_stage());
                second_dt_6.setText(response.body().getSecond_metting_date());
                second_ss_6.setText(response.body().getSecond_metting_status());

                nextactivtiyplan_st_7.setText(response.body().getNext_Activity_Plan1_stage());
                nextactivtiyplan_dt_7.setText(response.body().getNext_Activity_Plan1_date());
                nextactivtiyplan_ss_7.setText(response.body().getNext_Activity_Plan1_status());

                nextactivtiyplan_st_7_2.setText(response.body().getNext_Activity_Plan2_stage());
                nextactivtiyplan_dt_7_2.setText(response.body().getNext_Activity_Plan2_date());
                nextactivtiyplan_ss_7_2.setText(response.body().getNext_Activity_Plan2_status());

                nextactivtiyplan_st_7_3.setText(response.body().getNext_Activity_Plan3_stage());
                nextactivtiyplan_dt_7_3.setText(response.body().getNext_Activity_Plan3_date());
                nextactivtiyplan_ss_7_3.setText(response.body().getNext_Activity_Plan3_status());

                makeandoffer_st_8_1.setText(response.body().getMake_an_offer1_stage());
                makeandoffer_dt_8_1.setText(response.body().getMake_an_offer1_date());
                makeandoffer_ss_8_1.setText(response.body().getMake_an_offer1_status());


                makeandoffer_st_8_2.setText(response.body().getMake_an_offer2_stage());
                makeandoffer_dt_8_2.setText(response.body().getMake_an_offer2_date());
                makeandoffer_ss_8_2.setText(response.body().getMake_an_offer2_status());

                booking_st_11.setText(response.body().getBooking_stage());
                booking_dt_11.setText(response.body().getBooking_date());
                booking_ss_11.setText(response.body().getBooking_status());

                delivery_st_12.setText(response.body().getDelivery_stage());
                delivery_dt_12.setText(response.body().getDelivery_date());
                delivery_ss_12.setText(response.body().getDelivery_status());

                sellost_st_17.setText(response.body().getSellost_stage());
                sellost_dt_17.setText(response.body().getSellost_date());
                sellost_ss_17.setText(response.body().getSellost_status());

                drop_st_18.setText(response.body().getDrop_stage());
                drop_dt_18.setText(response.body().getDrop_date());
                drop_ss_18.setText(response.body().getDrop_status());

                recentnot.setText("     Description : "+response.body().getRecent_note());
                location.setText("      Location  : "+response.body().getLocation());
            }

            @Override
            public void onFailure(Call<deal_stage_history_model> call, Throwable t) {
                Utils.showErrorToast(DealstageHistoryActivity.this,t.getMessage());
            }
        });

    }
}