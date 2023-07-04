package com.example.kumarGroup.BookingDeliveryUpload.DocumentPrint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kumarGroup.DocumentPrintDataModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentPrintMainActivity extends AppCompatActivity {

    RecyclerView rclvDocumentPrint;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doument_print_main);

        getSupportActionBar().setTitle("Document Print");


        rclvDocumentPrint = findViewById(R.id.rclvDocumentPrint);

        rclvDocumentPrint.setHasFixedSize(true);
        rclvDocumentPrint.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        progressDialog= new ProgressDialog(DocumentPrintMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getDocPrint(emp).enqueue(new Callback<DocumentPrintDataModel>() {
            @Override
            public void onResponse(@NotNull Call<DocumentPrintDataModel> call, @NotNull Response<DocumentPrintDataModel> response) {

                progressDialog.dismiss();
                assert response.body() != null;
                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(DocumentPrintMainActivity.this,"No Data Available");
                }
                else {
                    DocumentPrintAdapter adapter = new DocumentPrintAdapter(DocumentPrintMainActivity.this,
                            response.body().getData());
                    rclvDocumentPrint.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DocumentPrintDataModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(DocumentPrintMainActivity.this,"No Data Available");
            }
        });

    }
}