package com.example.kumarGroup.ViewInquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInqMonthlyOneModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyViewInqOneActivity extends AppCompatActivity {

    RecyclerView rclvMonthlyViewInquiry;

    SharedPreferences sp1;
    String emp;
    ProgressDialog progressDialog;

    String state,stateId;
    public static boolean myINQdeliveryFlag = false;
    public static boolean myINQshowDeliveryDataFlag = false;

    public static boolean viewINQdeliveryFlag = false;
    public static boolean viewINQshowDeliveryDataFlag = false;

    String date1,date2,catId_eid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_view_inq_one);


        rclvMonthlyViewInquiry=findViewById(R.id.rclvMonthlyViewInquiry);
        rclvMonthlyViewInquiry.setHasFixedSize(true);
        rclvMonthlyViewInquiry.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences2 = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        catId_eid = sharedPreferences2.getString("catId_eid","");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        state = getIntent().getStringExtra("state");
        stateId =getIntent().getStringExtra("stateId");

        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");


        progressDialog= new ProgressDialog(MonthlyViewInqOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        if (myINQdeliveryFlag){
            myINQdeliveryFlag = false;

            getSupportActionBar().setTitle("My inquiry Date filter Data");
            myINQshowDeliveryDataFlag = true;
            Log.d("TAG", "deliverybuttonapo"+date1+" date2 "+date2+" catid "+catId_eid+" emp "+emp);
            WebService.getClient().getDealstageDeliverybutton(date1,date2,catId_eid,emp).enqueue(new Callback<ViewInqMonthlyOneModel>() {
                @Override
                public void onResponse(Call<ViewInqMonthlyOneModel> call, Response<ViewInqMonthlyOneModel> response) {
                    progressDialog.dismiss();
                    if (response.body().getDetail().size() == 0) {
                        Utils.showErrorToast(MonthlyViewInqOneActivity.this, "Data not Available");
                    } else {
                        Log.d("TAG", "onResponse: MonthlyViewInqOneActivity123-1");
                        MonthlyViewInqOneAdapter adapter = new MonthlyViewInqOneAdapter(MonthlyViewInqOneActivity.this, response.body().getDetail());
                        rclvMonthlyViewInquiry.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(Call<ViewInqMonthlyOneModel> call, Throwable t) {
                    Utils.showErrorToast(MonthlyViewInqOneActivity.this,t.getMessage());
                }
            });

        }

        else if (viewINQdeliveryFlag){

            SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
            String selected_emp = sharedPreferencesS.getString("Slected_EMPID","");

            getSupportActionBar().setTitle("View inquiry Date filter Data");
            viewINQshowDeliveryDataFlag = true;
            Log.d("TAG", "deliverybuttonapo"+date1+" date2 "+date2+" catid "+catId_eid+" emp "+selected_emp);
            WebService.getClient().getDealstageDeliverybutton(date1,date2,catId_eid,selected_emp).enqueue(new Callback<ViewInqMonthlyOneModel>() {
                @Override
                public void onResponse(Call<ViewInqMonthlyOneModel> call, Response<ViewInqMonthlyOneModel> response) {
                    progressDialog.dismiss();
                    if (response.body().getDetail().size() == 0) {
                        Utils.showErrorToast(MonthlyViewInqOneActivity.this, "Data not Available");
                    } else {
                        Log.d("TAG", "onResponse: MonthlyViewInqOneActivity123-2");

                        MonthlyViewInqOneAdapter adapter = new MonthlyViewInqOneAdapter(MonthlyViewInqOneActivity.this, response.body().getDetail());
                        rclvMonthlyViewInquiry.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(Call<ViewInqMonthlyOneModel> call, Throwable t) {
                    Utils.showErrorToast(MonthlyViewInqOneActivity.this,t.getMessage());
                }
            });

        }

        else {
            WebService.getClient().getViewInqMonth(stateId).enqueue(new Callback<ViewInqMonthlyOneModel>() {
                @Override
                public void onResponse(@NotNull Call<ViewInqMonthlyOneModel> call, @NotNull Response<ViewInqMonthlyOneModel> response) {
                    progressDialog.dismiss();

                    assert response.body() != null;

                    if (response.body().getDetail().size() == 0) {
                        Utils.showErrorToast(MonthlyViewInqOneActivity.this, "Data not Available");
                    } else {
                        Log.d("TAG", "onResponse: MonthlyViewInqOneActivity123-3");

                        MonthlyViewInqOneAdapter adapter = new MonthlyViewInqOneAdapter(MonthlyViewInqOneActivity.this,
                                response.body().getDetail());
                        rclvMonthlyViewInquiry.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<ViewInqMonthlyOneModel> call, @NotNull Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(MonthlyViewInqOneActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}