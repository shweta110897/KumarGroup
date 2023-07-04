package com.example.kumarGroup.Workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WorkShoPhaseModel;
import com.example.kumarGroup.Workshop.Add.AddFirstActivity;
import com.example.kumarGroup.Workshop.Add.FourthWsAddMainActivity;
import com.example.kumarGroup.Workshop.Add.ThirdWsAddActivity;
import com.example.kumarGroup.Workshop.Add.WsAddSecondActivity;
import com.example.kumarGroup.Workshop.Daily.DailyWsOneActivity;
import com.example.kumarGroup.Workshop.FeedBackCall.FeedbcakCallMainActivity;
import com.example.kumarGroup.Workshop.General.ComplainBoxDateFilterActivity;
import com.example.kumarGroup.Workshop.General.WSGeneralVisitActivity;
import com.example.kumarGroup.Workshop.Invoice.InvoiceDateSelectActivity;
import com.example.kumarGroup.Workshop.Month.MonthWsOneActivity;
import com.example.kumarGroup.Workshop.Weekly.WeeklyWsOneActivity;
import com.example.kumarGroup.Workshop.WsManager.WsManagerMainActivity;
import com.example.kumarGroup.Workshop.WsPayPen.PaymentPendingWSActivity;
import com.example.kumarGroup.WorkshopNumberAllModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkshopMainActivity extends AppCompatActivity {

    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;

    TextView tv_WorkShop_add,tv_WorkShop_PaymentPending,tv_WorkShop_Manager,
            tv_WorkShop_Invoice,tv_WorkShop_GeneralVisit,
            tv_WorkShop_MonthlyVisit,tv_WorkShop_WeekVisit,tv_WorkShop_DailyVisit,
            tv_WorkShop_FeedBackCall,tv_Complain_Register;



    TextView txt_Daily_Mechanic,txt_PaymentPending,txt_Invoice,txt_General,txt_Monthly,
            txt_Week,txt_Daily,txt_Feedback,
            txt_complainDisplay;


    SharedPreferences sharedPreferences;
    String NextId_WS;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_main);

        getSupportActionBar().setTitle("Workshop");



        sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
        NextId_WS = sharedPreferences.getString("NextId_WS","");

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);
        txt7=findViewById(R.id.txt7);


        txt_Daily_Mechanic=findViewById(R.id.txt_Daily_Mechanic);
        txt_PaymentPending=findViewById(R.id.txt_PaymentPending);
        txt_Invoice=findViewById(R.id.txt_Invoice);
        txt_General=findViewById(R.id.txt_General);
        txt_Monthly=findViewById(R.id.txt_Monthly);
        txt_Week=findViewById(R.id.txt_Week);
        txt_Daily=findViewById(R.id.txt_Daily);
        txt_Feedback=findViewById(R.id.txt_Feedback);
        tv_Complain_Register=findViewById(R.id.tv_Complain_Register);


        tv_WorkShop_add=findViewById(R.id.tv_WorkShop_add);
        tv_WorkShop_PaymentPending=findViewById(R.id.tv_WorkShop_PaymentPending);
        tv_WorkShop_Manager=findViewById(R.id.tv_WorkShop_Manager);
        tv_WorkShop_Invoice=findViewById(R.id.tv_WorkShop_Invoice);
        tv_WorkShop_GeneralVisit=findViewById(R.id.tv_WorkShop_GeneralVisit);

        tv_WorkShop_MonthlyVisit=findViewById(R.id.tv_WorkShop_MonthlyVisit);
        tv_WorkShop_WeekVisit=findViewById(R.id.tv_WorkShop_WeekVisit);
        tv_WorkShop_DailyVisit=findViewById(R.id.tv_WorkShop_DailyVisit);
        tv_WorkShop_FeedBackCall=findViewById(R.id.tv_WorkShop_FeedBackCall);

        txt_complainDisplay=findViewById(R.id.txt_complainDisplay);


        progressDialog= new ProgressDialog(WorkshopMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getWorkshopDIsplayNumber().enqueue(new Callback<WorkshopNumberAllModel>() {
            @Override
            public void onResponse(@NotNull Call<WorkshopNumberAllModel> call,
                                   @NotNull Response<WorkshopNumberAllModel> response) {
                progressDialog.dismiss();


                assert response.body() != null;

                txt_Daily_Mechanic.setText(response.body().getCat().get(0).getDailymechanicreport());

                txt_PaymentPending.setText(response.body().getCat().get(1).getPaymentpending());

               // txt_Invoice.setText(response.body().getCat().get(2).getIn());

                txt_General.setText(response.body().getCat().get(2).getGenral());

                txt_Monthly.setText(response.body().getCat().get(3).getMonthly());

                txt_Week.setText(response.body().getCat().get(4).getWeekly());

                txt_Daily.setText(response.body().getCat().get(5).getDaliy());


                txt_Feedback.setText(response.body().getCat().get(6).getFeedbackcall());


                txt_complainDisplay.setText(response.body().getCat().get(7).getComplain());
            }

            @Override
            public void onFailure(@NotNull Call<WorkshopNumberAllModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();

            }
        });


        tv_WorkShop_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);

                if(NextId_WS.equals(""))
                {
                    Intent i = new Intent(WorkshopMainActivity.this, AddFirstActivity.class);
                    startActivity(i);
                }
                else {

                    WebService.getClient().getWorkShopPhase(NextId_WS).enqueue(new Callback<WorkShoPhaseModel>() {
                        @Override
                        public void onResponse(@NotNull Call<WorkShoPhaseModel> call, @NotNull Response<WorkShoPhaseModel> response) {
                            assert response.body() != null;
                            if (response.body().getPhase() == null) {
                                Intent i = new Intent(WorkshopMainActivity.this, AddFirstActivity.class);
                                 startActivity(i);

                              //  Intent i = new Intent(WorkshopMainActivity.this, FourthWsAddMainActivity.class);
                              //  startActivity(i);

                            } else if (response.body().getPhase().equals("1")) {
                                Intent i = new Intent(WorkshopMainActivity.this, WsAddSecondActivity.class);
                                startActivity(i);

                               // Intent i = new Intent(WorkshopMainActivity.this, AddFirstActivity.class);
                              //  startActivity(i);

                            } else if (response.body().getPhase().equals("2")) {
                                Intent i = new Intent(WorkshopMainActivity.this, ThirdWsAddActivity.class);
                                startActivity(i);

                               // Intent i = new Intent(WorkshopMainActivity.this, WsAddSecondActivity.class);
                               // startActivity(i);

                            } else if (response.body().getPhase().equals("3")) {
                               Intent i = new Intent(WorkshopMainActivity.this, FourthWsAddMainActivity.class);
                                startActivity(i);

                               // Intent i = new Intent(WorkshopMainActivity.this, ThirdWsAddActivity.class);
                              //  startActivity(i);

                            } else if (response.body().getPhase().equals("4")) {
                                Intent i = new Intent(WorkshopMainActivity.this, AddFirstActivity.class);
                                startActivity(i);

                               // Intent i = new Intent(WorkshopMainActivity.this, FourthWsAddMainActivity.class);
                               // startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<WorkShoPhaseModel> call, @NotNull Throwable t) {

                        }
                    });

                }
            }
        });

        tv_WorkShop_Manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(WorkshopMainActivity.this, WsManagerMainActivity.class);
                startActivity(i);
            }
        });

        tv_WorkShop_PaymentPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(WorkshopMainActivity.this, PaymentPendingWSActivity.class);
                startActivity(i);
            }
        });


        tv_WorkShop_Invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(WorkshopMainActivity.this, InvoiceDateSelectActivity.class);
                startActivity(i);
            }
        });



        tv_WorkShop_GeneralVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.enable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);

                Intent i = new Intent(WorkshopMainActivity.this, WSGeneralVisitActivity.class);
                startActivity(i);
            }
        });


        tv_WorkShop_MonthlyVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.enable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(WorkshopMainActivity.this, MonthWsOneActivity.class);
                startActivity(i);
            }
        });


        tv_WorkShop_WeekVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(WorkshopMainActivity.this, WeeklyWsOneActivity.class);
                startActivity(i);
            }
        });

        tv_WorkShop_DailyVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.enable_page);

                Intent i = new Intent(WorkshopMainActivity.this, DailyWsOneActivity.class);
                startActivity(i);
            }
        });



        tv_WorkShop_FeedBackCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.enable_page);


                Intent i = new Intent(WorkshopMainActivity.this, FeedbcakCallMainActivity.class);
                startActivity(i);
            }
        });


        tv_Complain_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.enable_page);


                Intent i = new Intent(WorkshopMainActivity.this, ComplainBoxDateFilterActivity.class);
                startActivity(i);
            }
        });

    }
}