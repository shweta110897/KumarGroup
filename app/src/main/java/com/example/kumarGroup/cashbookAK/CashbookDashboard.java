package com.example.kumarGroup.cashbookAK;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.cashbook_dateFilter_model;
import com.example.kumarGroup.cashbook_getDate_model;
import com.example.kumarGroup.cashbook_getdata_model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CashbookDashboard extends AppCompatActivity {
    RecyclerView dataentryRecyclerView,dataFilterRecyclerView;
    DataentryAdapter dataentryAdapter;
    AdapterDateFilter adapterDateFilter;
    Button cash_in,cash_out;

    Spinner cashbook_start_date,cashbook_end_date;
    Button transfer_balance;

    TextView das_out,das_in,das_bank,das_inhand,das_trans,das_fulltotal,das_date;
    List<String> datelist_start = new ArrayList<>();
    List<String> datelist_end = new ArrayList<>();
    String start_date,end_date;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashbook_dashboard);

        dataentryRecyclerView = findViewById(R.id.dataentryRecyclerView);
        dataFilterRecyclerView = findViewById(R.id.dataFilterRecyclerView);
        cash_in = findViewById(R.id.cash_in);
        cash_out = findViewById(R.id.cash_out);
        das_out = findViewById(R.id.das_out);
        das_in = findViewById(R.id.das_in);
        das_trans = findViewById(R.id.das_trans);
        das_bank = findViewById(R.id.das_bank);
        das_inhand = findViewById(R.id.das_inhand);
        das_fulltotal = findViewById(R.id.das_fulltotal);
        das_date = findViewById(R.id.das_date);

//        dataentryRecyclerView.setAdapter(dataentryAdapter);

        //date filter

        //AllocateMemory
        cashbook_start_date = findViewById(R.id.cashbook_start_date);
        cashbook_end_date = findViewById(R.id.cashbook_end_date);
        transfer_balance = findViewById(R.id.transfer_balance);

        cashbook_start_date.setFocusable(false);
        cashbook_end_date.setFocusable(false);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        das_date.setText(date);

        getentryData();

        WebService.getClient().cashbook_getDate().enqueue(new Callback<cashbook_getDate_model>() {
            @Override
            public void onResponse(Call<cashbook_getDate_model> call, Response<cashbook_getDate_model> response) {
                if (0 != response.body().getCat().size()){
                    datelist_start.clear();

                    datelist_start.add("Start Date");
                    datelist_end.add("End Date");

                    for (int i = 0; i < response.body().getCat().size(); i++) {
                        datelist_start.add(response.body().getCat().get(i).getCudate());
                        datelist_end.add(response.body().getCat().get(i).getCudate());

                    }

                    ArrayAdapter adapter1 = new ArrayAdapter(CashbookDashboard.this, android.R.layout.simple_spinner_dropdown_item, datelist_start);
                    cashbook_start_date.setAdapter(adapter1);

                    ArrayAdapter adapter2 = new ArrayAdapter(CashbookDashboard.this, android.R.layout.simple_spinner_dropdown_item, datelist_end);
                    cashbook_end_date.setAdapter(adapter2);


                    cashbook_start_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // state = category.get(position);
                            // stateId = categoryId.get(position);

                            if(datelist_start.get(position).equals("Start Date")){
                                //Utils.showErrorToast(CashbookDashboard.this,"Please select Start Date");
                                start_date = "";
                            }
                            else{
                                start_date = datelist_start.get(position);

                                if (!end_date.equals("") && !"End Date".equals(end_date)) {
                                    callapi_filter_date();
                                }
                            }

                            Log.d("TAG", "startdate "+start_date);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    cashbook_end_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(datelist_end.get(position).equals("End Date")){
                                end_date = "";
                            }
                            else{
                                end_date = datelist_end.get(position);

                                if (!start_date.equals("") && !"Start Date".equals(start_date)) {
                                    callapi_filter_date();
                                }
                                else {
                                    Utils.showErrorToast(CashbookDashboard.this,"Please Select Start Date");
                                }
                            }

                            Log.d("TAG", "enddate "+start_date);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<cashbook_getDate_model> call, Throwable t) {
                Log.d("TAG", "onFailure: check123 message2 "+t.getMessage());

                Utils.showErrorToast(CashbookDashboard.this,t.getMessage());
            }
        });

        transfer_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CashbookDashboard.this,TransferMoneyDesignActivity.class));

            }
        });

        cash_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CashbookDashboard.this,InButtionActivity.class));
            }
        });

        cash_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CashbookDashboard.this,OutButtionActivity.class));
            }
        });

    }

    private void callapi_filter_date() {

        if ("".equals(start_date) && "".equals(end_date)){
            Utils.showErrorToast(CashbookDashboard.this,"Please select date");
        }
        else {


            WebService.getClient().cashbook_das_datefilter(start_date, end_date).enqueue(new Callback<cashbook_dateFilter_model>() {
                @Override
                public void onResponse(Call<cashbook_dateFilter_model> call, Response<cashbook_dateFilter_model> response) {
                    if (0 != response.body().getData().size()) {
                        dataentryRecyclerView.setVisibility(View.GONE);
                        dataFilterRecyclerView.setVisibility(View.VISIBLE);
                        das_out.setText(response.body().getData().get(0).getCuouttotal());
                        das_in.setText(response.body().getData().get(0).getCuintotal());
//                        das_trans.setText(response.body().getData().get(0).getToday_transfer());
                        Collections.reverse(response.body().getData());
                        adapterDateFilter = new AdapterDateFilter(CashbookDashboard.this, response.body().getData());
                        dataFilterRecyclerView.setAdapter(adapterDateFilter);
                    }
                    else {
                        Utils.showErrorToast(CashbookDashboard.this,"No Data Found This Date");
                    }

                }

                @Override
                public void onFailure(Call<cashbook_dateFilter_model> call, Throwable t) {
                    Utils.showErrorToast(CashbookDashboard.this, t.getMessage());
                }
            });
        }
    }

    private void getentryData() {
        WebService.getClient().cashbook_getEntry().enqueue(new Callback<cashbook_getdata_model>() {
            @Override
            public void onResponse(Call<cashbook_getdata_model> call, Response<cashbook_getdata_model> response) {
                if (0 != response.body().getData().size()){
                    Collections.reverse(response.body().getData());
                    dataentryAdapter = new DataentryAdapter(CashbookDashboard.this,response.body().getData());
                    dataentryRecyclerView.setAdapter(dataentryAdapter);
                }
                das_out.setText(String.valueOf(response.body().getCuouttotal()));
                Log.d("TAG", "onResponse: check123 money "+String.valueOf(response.body().getCuouttotal()));
                das_in.setText(String.valueOf(response.body().getCuintotal()));
                Log.d("TAG", "onResponse: check123 money1 "+String.valueOf(response.body().getCuintotal()));
                das_trans.setText(response.body().getToday_transfer());
                Log.d("TAG", "onResponse: check123 moneytrans "+response.body().getToday_transfer());

                das_bank.setText(response.body().getBanktotal());
                Log.d("TAG", "onResponse: check123 money2 "+String.valueOf(response.body().getBanktotal()));

                das_inhand.setText(response.body().getCashtotal());
                Log.d("TAG", "onResponse: check123 money3 "+String.valueOf(response.body().getCashtotal()));

                das_fulltotal.setText(response.body().getFulltotal());
                Log.d("TAG", "onResponse: check123 money4 "+String.valueOf(response.body().getFulltotal()));

                //das_date.setText(String.valueOf(response.body().getData().get(0).getC_date()));
            }

            @Override
            public void onFailure(Call<cashbook_getdata_model> call, Throwable t) {
                Log.d("TAG", "onFailure: check123 message1 "+t.getMessage());
            Utils.showErrorToast(CashbookDashboard.this,t.getMessage());
            }
        });
    }
}