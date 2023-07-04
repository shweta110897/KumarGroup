package com.example.kumarGroup.Inquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.show_image_model;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewImageActivity extends AppCompatActivity {
    RecyclerView showImageRecyclerView;
    String isid;
    ProgressDialog pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        showImageRecyclerView = findViewById(R.id.showImageRecyclerView);
        isid  = getIntent().getStringExtra("sname");
        pro = new ProgressDialog(this);


        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ..");

        //Toast.makeText(ViewImageActivity.this, "isid "+isid, Toast.LENGTH_SHORT).show();
        WebService.getClient().show_image_added_web(isid).enqueue(new Callback<show_image_model>() {
            @Override
            public void onResponse(Call<show_image_model> call, Response<show_image_model> response) {
                pro.dismiss();
                Collections.reverse(response.body().getData());
                ShowImageAdapter showImageAdapter = new ShowImageAdapter(ViewImageActivity.this,response.body().getData());
                showImageRecyclerView.setAdapter(showImageAdapter);
            }

            @Override
            public void onFailure(Call<show_image_model> call, Throwable t) {
                pro.dismiss();
                Utils.showErrorToast(ViewImageActivity.this,t.getMessage());
            }
        });
    }
}