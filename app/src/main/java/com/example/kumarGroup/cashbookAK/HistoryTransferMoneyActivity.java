package com.example.kumarGroup.cashbookAK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.cashbook_history_model;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryTransferMoneyActivity extends AppCompatActivity {
    RecyclerView histroyRecyclerView;
    AdapterHistroyTransaction adapterHistroyTransaction;
    ProgressDialog pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_transfer_money);

        histroyRecyclerView = findViewById(R.id.histroyRecyclerView);
        pro = new ProgressDialog(HistoryTransferMoneyActivity.this);

        pro.show();
        pro.setMessage("Please wait");
        pro.setCancelable(false);

        WebService.getClient().cashbook_getHistory().enqueue(new Callback<cashbook_history_model>() {
            @Override
            public void onResponse(Call<cashbook_history_model> call, Response<cashbook_history_model> response) {
                if (0 != response.body().getData().size()){
                    Collections.reverse(response.body().getData());
                    adapterHistroyTransaction = new AdapterHistroyTransaction(HistoryTransferMoneyActivity.this,response.body().getData());
                    histroyRecyclerView.setAdapter(adapterHistroyTransaction);
                    pro.dismiss();
                }
                else {
                    Utils.showErrorToast(HistoryTransferMoneyActivity.this,"No Data Found");
                    pro.dismiss();
                }
            }

            @Override
            public void onFailure(Call<cashbook_history_model> call, Throwable t) {
                pro.dismiss();
                Utils.showErrorToast(HistoryTransferMoneyActivity.this,t.getMessage());
            }
        });
    }
}