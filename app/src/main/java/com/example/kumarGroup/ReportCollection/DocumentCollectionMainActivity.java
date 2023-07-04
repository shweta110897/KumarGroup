package com.example.kumarGroup.ReportCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.kumarGroup.DocumentBoxDisplayModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentCollectionMainActivity extends AppCompatActivity {


    RecyclerView rclvDocCollection;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_collection_main);

        getSupportActionBar().setTitle("Document Box");


        rclvDocCollection=findViewById(R.id.rclvDocCollection);

        rclvDocCollection.setHasFixedSize(true);
        rclvDocCollection.setLayoutManager(new LinearLayoutManager(this));


        progressDialog= new ProgressDialog(DocumentCollectionMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );


        WebService.getClient().getDocBoxDisplay().enqueue(new Callback<DocumentBoxDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<DocumentBoxDisplayModel> call,
                                   @NotNull Response<DocumentBoxDisplayModel> response) {
                progressDialog.dismiss();

                progressDialog.dismiss();
                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(DocumentCollectionMainActivity.this,"No Data Available");
                }
                else{

                    DocumentCollectionAdapter adapter = new DocumentCollectionAdapter(DocumentCollectionMainActivity.this,
                            response.body().getCat());
                    rclvDocCollection.setAdapter(adapter);;

                }
            }

            @Override
            public void onFailure(@NotNull Call<DocumentBoxDisplayModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}