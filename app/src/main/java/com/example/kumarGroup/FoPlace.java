package com.example.kumarGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoPlace extends AppCompatActivity
{
    ListView listView;
    SharedPreferences sp;
    String emp;
    String[] village;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fo_place);

        sp = getSharedPreferences("Login",MODE_PRIVATE);

        emp=sp.getString("emp_id","");
        listView = findViewById(R.id.listview);
        getFoPlace();
    }

     private void getFoPlace(){

         progressDialog= new ProgressDialog(FoPlace.this);
         progressDialog.show();
         progressDialog.setContentView(R.layout.progress_dialog);
         progressDialog.getWindow().setBackgroundDrawableResource(
                 android.R.color.transparent);

        WebService.getClient().getVillagee(emp).enqueue(new Callback<DataVillageeModel>() {
            @Override
            public void onResponse(Call<DataVillageeModel> call, Response<DataVillageeModel> response) {
                progressDialog.dismiss();

                Log.d("listplaces", "onResponse: "+response.body().getDetail().size() );
                if (response.body().getDetail().size() > 0) {
                    village = new String[response.body().getDetail().size()];
                    int j=1;
                    for (int i = 0; i < response.body().getDetail().size(); i++) {
                        village[i] = j+" ) "+response.body().getDetail().get(i).getVillage_name();
                        j++;
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(FoPlace.this,
                            android.R.layout.simple_spinner_dropdown_item, village);
                    listView.setAdapter(adapter);
                } else {
                  //  utils.dialogFinishActivity("Internal erro try after sometime !!!!");
                }
            }

            @Override
            public void onFailure(Call<DataVillageeModel> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

}
}