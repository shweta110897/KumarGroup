package com.example.kumarGroup.cashbookAK;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.transfer_cashtobank_model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferMoneyDesignActivity extends AppCompatActivity {

    Spinner sp1,sp2,InOut;
    List<String> sp1list = new ArrayList<>();
    List<String> sp2list = new ArrayList<>();
    String get1sp,get2sp;
    EditText amounttransfer,note;
    TextView date;
    Button savecash_transfer,histroy;
    SharedPreferences sp;
    ProgressDialog pro;
    List<String> In_Out = Arrays.asList(new String[]{"Select Option","IN", "OUT"});
    String inout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money_design);

        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        amounttransfer = findViewById(R.id.amounttransfer);
        date = findViewById(R.id.date);
        note = findViewById(R.id.note);
        savecash_transfer = findViewById(R.id.savecash_transfer);
        histroy = findViewById(R.id.histroy);
        InOut = findViewById(R.id.InOut);

        pro = new ProgressDialog(TransferMoneyDesignActivity.this);

        String date0 = new SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault()).format(new Date());
        date.setText(date0);

        String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        setspiner();
        ArrayAdapter adapterStage = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, In_Out);
        InOut.setAdapter(adapterStage);

        InOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inout = In_Out.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        savecash_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(amounttransfer.getText().toString().trim())){
                    Utils.showErrorToast(TransferMoneyDesignActivity.this,"Please Enter Amount");
                    amounttransfer.setError("Require Amount");
                    return;
                }
                if (TextUtils.isEmpty(note.getText().toString().trim())){
                    Utils.showErrorToast(TransferMoneyDesignActivity.this,"Please Enter Note");
                    note.setError("Require Note");
                    return;
                }
                if (TextUtils.isEmpty(get1sp) && get1sp.equals("")){
                    Utils.showErrorToast(TransferMoneyDesignActivity.this,"Please Select Mode");
                    return;
                }

                if (TextUtils.isEmpty(get2sp) && get2sp.equals("")){
                    Utils.showErrorToast(TransferMoneyDesignActivity.this,"Please Select Mode");
                    return;
                }

                sp = getSharedPreferences("Login", MODE_PRIVATE);

                String emp_id = sp.getString("emp_id", "");

                pro.show();
                pro.setMessage("Please wait ..");
                pro.setCancelable(false);

                if (inout.equals("Select Option")){
                    Utils.showErrorToast(TransferMoneyDesignActivity.this,"Pls Select Type");

                    TextView errorText = (TextView)InOut.getSelectedView();
                    errorText.setError("Pls Select Type");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Select Option");
                    pro.dismiss();
                }else {
                    WebService.getClient().cashbook_send_transfer_cashtobank(emp_id,get1sp,get2sp,
                            amounttransfer.getText().toString().trim(),
                            note.getText().toString().trim(),
                            date1,inout
                    ).enqueue(new Callback<transfer_cashtobank_model>() {
                        @Override
                        public void onResponse(Call<transfer_cashtobank_model> call, Response<transfer_cashtobank_model> response) {
                            pro.dismiss();
                            Toast.makeText(TransferMoneyDesignActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TransferMoneyDesignActivity.this,CashbookDashboard.class));
                            finish();
                        }

                        @Override
                        public void onFailure(Call<transfer_cashtobank_model> call, Throwable t) {
                            pro.dismiss();
                            Utils.showErrorToast(TransferMoneyDesignActivity.this,t.getMessage());
                        }
                    });
                }


            }
        });

        histroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransferMoneyDesignActivity.this,HistoryTransferMoneyActivity.class));
            }
        });
    }

    private void setspiner() {

        sp1list.add("Select transfer mode");
        sp1list.add("Cash");
        sp1list.add("Bank");

        Log.d("TAG", "setspiner: "+sp1list);

        ArrayAdapter adapter1 = new ArrayAdapter(TransferMoneyDesignActivity.this, android.R.layout.simple_spinner_dropdown_item,sp1list);
        sp1.setAdapter(adapter1);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(sp1list.get(position).equals("Select transfer mode")){
                    get1sp = "";
                }
                else{
                    get1sp = sp1list.get(position);
                    sp2list.clear();
                    if (get1sp.equals("Cash")){

                        sp2list.add("Bank");
                    }
                    else {
                        sp2list.add("Cash");
                        sp2list.add("Bank");
                    }

                    spset2method();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void spset2method() {

        ArrayAdapter adapter2 = new ArrayAdapter(TransferMoneyDesignActivity.this, android.R.layout.simple_spinner_dropdown_item,sp2list);
        sp2.setAdapter(adapter2);

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get2sp = sp2list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}