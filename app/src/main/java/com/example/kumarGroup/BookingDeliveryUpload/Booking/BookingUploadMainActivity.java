package com.example.kumarGroup.BookingDeliveryUpload.Booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.BookingUploadModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingUploadMainActivity extends AppCompatActivity {

    RecyclerView  rclvBookingUpload;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_upload_main);

        getSupportActionBar().setTitle("Booking Data");

        rclvBookingUpload = findViewById(R.id.rclvBookingUpload);

        rclvBookingUpload.setHasFixedSize(true);
        rclvBookingUpload.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        progressDialog= new ProgressDialog(BookingUploadMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getBookingUploadModule(emp).enqueue(new Callback<BookingUploadModel>() {
            @Override
            public void onResponse(@NotNull Call<BookingUploadModel> call,
                                   @NotNull Response<BookingUploadModel> response) {
                progressDialog.dismiss();
                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(BookingUploadMainActivity.this,"No Data Available");
                }
                else {
                    BookingUploadMainAdapter adapter = new BookingUploadMainAdapter(BookingUploadMainActivity.this,BookingUploadMainActivity.this,
                            response.body().getData());
                    rclvBookingUpload.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<BookingUploadModel> call,
                                  @NotNull Throwable t) {
                progressDialog.dismiss();
               // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(BookingUploadMainActivity.this,"No Data Available");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BookingDeliveryMainActivity.Next_Button_Check = false;
    }
}