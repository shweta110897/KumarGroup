package com.example.kumarGroup.TrueValue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInquiryDealStage.AdapterShowButtonDataViewINQ1;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Deal_notattend_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstMeetingReviewList extends AppCompatActivity {
    RecyclerView rv_Review;
    SharedPreferences sp;
    String emp_id;

    ProgressDialog pro;

    List<Catnotattend> catShowRCGVS;


    AdapterShowButtonDataViewINQ1  adapterShowButtonData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_meeting_review_list);

        rv_Review=findViewById(R.id.rv_Review);

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id = sp.getString("emp_id", "");
        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        WebService.getClient().getFirstMeetingReviewHistory(emp_id).enqueue(new Callback<Deal_notattend_model>() {
            @Override
            public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                if (0 == response.body().getCat().size()){
                    Utils.showErrorToast(FirstMeetingReviewList.this,"No data found");
                    pro.dismiss();
                }
                else {
                    catShowRCGVS = response.body().getCat();
                    Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                    adapterShowButtonData = new AdapterShowButtonDataViewINQ1(FirstMeetingReviewList.this, FirstMeetingReviewList.this, response.body().getCat());
                    rv_Review.setAdapter(adapterShowButtonData);

                    pro.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                pro.dismiss();
                Utils.showErrorToast(FirstMeetingReviewList.this,t.getMessage());
            }
        });



    }
}