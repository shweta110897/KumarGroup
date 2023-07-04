package com.example.kumarGroup.DeliveryReport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.DisplayReportCountModel;
import com.example.kumarGroup.FirstServiceAddTwoModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SecondServiceAddTwo;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryReportButtonMain extends AppCompatActivity {


    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;

    SharedPreferences sp1;
    String emp;


    TextView tv_add,tv_GenList,tv_First_Service,tv_Second_Service,tv_Third_Service,
            tv_Fourth_Service,tv_Fifth_Service;


    TextView txt_count_GeneralList,txt_count_firstService,txt_count_secondService,
            txt_count_ThirdService,txt_count_fourthService,txt_count_fifthService;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_report_button_main);


        getSupportActionBar().setTitle("Delivery Report");

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);
        txt7=findViewById(R.id.txt7);

        tv_add= findViewById(R.id.tv_add);
        tv_GenList= findViewById(R.id.tv_GenList);
        tv_First_Service= findViewById(R.id.tv_First_Service);
        tv_Second_Service= findViewById(R.id.tv_Second_Service);
        tv_Third_Service= findViewById(R.id.tv_Third_Service);
        tv_Fourth_Service= findViewById(R.id.tv_Fourth_Service);
        tv_Fifth_Service= findViewById(R.id.tv_Fifth_Service);

        txt_count_GeneralList= findViewById(R.id.txt_count_GeneralList);
        txt_count_firstService= findViewById(R.id.txt_count_firstService);
        txt_count_secondService= findViewById(R.id.txt_count_secondService);
        txt_count_ThirdService= findViewById(R.id.txt_count_ThirdService);
        txt_count_fourthService= findViewById(R.id.txt_count_fourthService);
        txt_count_fifthService= findViewById(R.id.txt_count_fifthService);



        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");


        progressDialog= new ProgressDialog(DeliveryReportButtonMain.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);




        WebService.getClient().getDisplayReportCount().enqueue(new Callback<DisplayReportCountModel>() {
            @Override
            public void onResponse(@NotNull Call<DisplayReportCountModel> call,
                                   @NotNull Response<DisplayReportCountModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;


                txt_count_GeneralList.setText(response.body().getCat().get(0).getGenral_list());

                txt_count_firstService.setText(response.body().getCat().get(1).getFirst_ser());

                // txt_Invoice.setText(response.body().getCat().get(2).getIn());

                txt_count_secondService.setText(response.body().getCat().get(2).getSecond_ser());

                txt_count_ThirdService.setText(response.body().getCat().get(3).getThird_ser());

              //  txt_count_fourthService.setText(response.body().getCat().get(4).getWeekly());

              //  txt_count_fifthService.setText(response.body().getCat().get(5).getDaliy());




            }

            @Override
            public void onFailure(@NotNull Call<DisplayReportCountModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });


        WebService.getClient().getFirstServiceAddTwoModel().enqueue(new Callback<FirstServiceAddTwoModel>() {
            @Override
            public void onResponse(@NotNull Call<FirstServiceAddTwoModel> call,
                                   @NotNull Response<FirstServiceAddTwoModel> response) {
                assert response.body() != null;
                Log.d("done__", "onResponse: "+response.body().getMsg());
               // Toast.makeText(DeliveryReportButtonMain.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<FirstServiceAddTwoModel> call,
                                  @NotNull Throwable t) {
                Log.d("done__", "onResponse: "+t.getCause());

            }
        });


        WebService.getClient().getSecondServiceAdd().enqueue(new Callback<SecondServiceAddTwo>() {
            @Override
            public void onResponse(@NotNull Call<SecondServiceAddTwo> call,
                                   @NotNull Response<SecondServiceAddTwo> response) {
//                Log.d("sDone", "onResponse: "+response.body().getMsg());
            }

            @Override
            public void onFailure(@NotNull Call<SecondServiceAddTwo> call, @NotNull Throwable t) {
                Log.d("sDone__", "onResponse: "+t.getCause());
            }
        });


        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(DeliveryReportButtonMain.this,AddDRMainActivity.class);
                startActivity(i);

            }
        });



        tv_GenList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(DeliveryReportButtonMain.this, DelievryReportMainActivity.class);
                startActivity(i);
            }
        });


        tv_First_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(DeliveryReportButtonMain.this, FIrstServiceDRActivity.class);
                startActivity(i);
            }
        });

    }
}