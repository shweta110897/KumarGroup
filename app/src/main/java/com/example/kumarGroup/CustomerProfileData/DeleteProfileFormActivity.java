package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.kumarGroup.ChackinModel;
import com.example.kumarGroup.CustomerProfile;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewCustomerProfileDataModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteProfileFormActivity extends AppCompatActivity {

    String MobileNo;


    RecyclerView rclvViewCustomer;
    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;
    CheckBox checkAll;
    boolean isCheck;
    DeleteCustomerProfileAdapter adapter;
    public static ArrayList<CustomerProfile> modelArrayList;
    ArrayList<CustomerProfile> deleteList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile_form);

        getSupportActionBar().setTitle("Delete View");

        MobileNo = getIntent().getStringExtra("MobileNo");


        rclvViewCustomer=findViewById(R.id.rclvViewCustomer);
        checkAll=findViewById(R.id.checkAll);
        rclvViewCustomer.setHasFixedSize(true);
        rclvViewCustomer.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");


        Log.d("EMP_ID", "onCreate: "+emp);

        progressDialog= new ProgressDialog(DeleteProfileFormActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isCheck=true;
                }else {
                    isCheck=false;
                }
            }
        });

       


        WebService.getClient().getViewProfileDetail(MobileNo).enqueue(new Callback<ViewCustomerProfileDataModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileDataModel> call,
                                   @NotNull Response<ViewCustomerProfileDataModel> response) {

                progressDialog.dismiss();
                modelArrayList= (ArrayList<CustomerProfile>) response.body().getCustomer_profile();

                assert response.body() != null;
                if(modelArrayList.size()==0){
                    Utils.showErrorToast(DeleteProfileFormActivity.this,"No Data Available");
                }
                else{

                    /*to  remove  delete  data*/
                    for (int  i=0;i<modelArrayList.size();i++){
                        if (!modelArrayList.get(i).getApprove_delete().equals("2")){
                            deleteList.add(modelArrayList.get(i));
                        }
                    }
                    Log.d("deleteList",String.valueOf(deleteList));
//                    modelArrayList = getModel(false,modelArrayList);

                    adapter = new DeleteCustomerProfileAdapter(DeleteProfileFormActivity.this,
                            deleteList,isCheck);
                    adapter.setViewItemInterface(new DeleteCustomerProfileAdapter.RecyclerViewItemInterface() {
                        @Override
                        public void onItemClick(int position, List<CustomerProfile> mcatlist, Boolean isCheck) {
                            Log.d("ischeckkk", String.valueOf(isCheck));
                            progressDialog.show();
                            WebService.getClient().deleteRecord(emp,modelArrayList.get(position).getId()
                            ).enqueue(new Callback<ChackinModel>() {
                                @Override
                                public void onResponse(@NotNull Call<ChackinModel> call,
                                                       @NotNull Response<ChackinModel> response) {
                                    progressDialog.dismiss();

                                    Toast.makeText(DeleteProfileFormActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                    finish();
                                    Intent i = new Intent(DeleteProfileFormActivity.this, DeleteProfileFormActivity.class);
                                    i.putExtra("MobileNo",MobileNo);
                                    startActivity(i);

                                }

                                @Override
                                public void onFailure(@NotNull Call<ChackinModel> call, @NotNull Throwable t) {
                                     progressDialog.dismiss();

                                }
                            });
                        }
                    });
                    rclvViewCustomer.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(@NotNull Call<ViewCustomerProfileDataModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("Travelling", "onFailure: "+t.getCause());
                Toast.makeText(DeleteProfileFormActivity.this,""+t.getCause(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<CustomerProfile> getModel(boolean b, ArrayList<CustomerProfile> modelArrayList) {
        ArrayList<CustomerProfile> list = new ArrayList<>();
        for(int i = 0; i < modelArrayList.size(); i++){

//            CustomerProfile model = new CustomerProfile(modelArrayList.get(i).getCat_id(),
//                    modelArrayList.get(i).getCat_id(), modelArrayList.get(i).getCityid(), modelArrayList.get(i).getDisid(), modelArrayList.get(i).getDistric(),modelArrayList.get(i).getEmpid(),modelArrayList.get(i).getEmployee_name(),modelArrayList.get(i).getFname(),modelArrayList.get(i).getLname(),modelArrayList.get(i).getMoblino(),
//                    modelArrayList.get(i).getNote(),modelArrayList.get(i).getState(),modelArrayList.get(i).getStateid(),modelArrayList.get(i).getTehsil(),modelArrayList.get(i).getTehsilid(),modelArrayList.get(i).getVilage(),modelArrayList.get(i).getVilageid(),modelArrayList.get(i).getWhatsappno(),modelArrayList.get(i).getId(),modelArrayList.get(i).getAddemp(),
//                    modelArrayList.get(i).getDrop(),b
//                    );
//       /*     model.setSelects(b);
//            model.setName(animallist[i]);*/
//            model.getCat_id();
//            list.add(model);
        }
        return list;
    }

}