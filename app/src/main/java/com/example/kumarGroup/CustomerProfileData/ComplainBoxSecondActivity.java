package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.ComplainBoxSubModel;
import com.example.kumarGroup.DatabankMakeModel;
import com.example.kumarGroup.DatabankModelListModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewCustomerProfileEditModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplainBoxSecondActivity extends AppCompatActivity
{
    EditText edt_categoryName,edt_FnameName,edt_LastName,edt_State,
            edt_City,edt_District,edt_Taluko,edt_Village,edt_mobile_number,
            edt_whatsapp_number,edt_Description,edt_TractorRegisterNo,edt_MfgYear_EP;

    Button btn_Submit;

    String MobileNo, cuid;

    ProgressDialog progressDialog;

    SharedPreferences sp;
    String emp;


    Spinner sp_make_name ,sp_model_name;
    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();

    String  makedata = "", modellistdata = "", strModel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_box_second);


        edt_categoryName= findViewById(R.id.edt_categoryName);
        edt_FnameName= findViewById(R.id.edt_FnameName);
        edt_LastName= findViewById(R.id.edt_LastName);
        edt_State= findViewById(R.id.edt_State);
        edt_City= findViewById(R.id.edt_City);
        edt_District= findViewById(R.id.edt_District);
        edt_Taluko= findViewById(R.id.edt_Taluko);
        edt_Village= findViewById(R.id.edt_Village);
        edt_mobile_number= findViewById(R.id.edt_mobile_number);
        edt_whatsapp_number= findViewById(R.id.edt_whatsapp_number);
        edt_Description= findViewById(R.id.edt_Description);
        edt_TractorRegisterNo= findViewById(R.id.edt_TractorRegisterNo);
        edt_MfgYear_EP= findViewById(R.id.edt_MfgYear_EP);
        sp_make_name = findViewById(R.id.sp_make_name);
        sp_model_name = findViewById(R.id.sp_model_name);

        btn_Submit= findViewById(R.id.btn_Submit);


        MobileNo = getIntent().getStringExtra("MobileNo");

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");


        WebService.getClient().getViewCuProfileEdit(MobileNo).enqueue(new Callback<ViewCustomerProfileEditModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileEditModel> call,
                                   @NotNull Response<ViewCustomerProfileEditModel> response) {

                assert response.body() != null;
                if (response.body().getCustomer_profile().size() == 0) {
                    Utils.showErrorToast(ComplainBoxSecondActivity.this, "No Data Available");
                } else {

                    edt_categoryName.setText(response.body().getCustomer_profile().get(0).getCat_name());
                    edt_FnameName.setText(response.body().getCustomer_profile().get(0).getFname());
                    edt_LastName.setText(response.body().getCustomer_profile().get(0).getLname());
                    edt_mobile_number.setText(response.body().getCustomer_profile().get(0).getMoblino());
                    edt_whatsapp_number.setText(response.body().getCustomer_profile().get(0).getWhatsappno());
                    edt_State.setText(response.body().getCustomer_profile().get(0).getState());
                    edt_City.setText(response.body().getCustomer_profile().get(0).getCity());
                    edt_District.setText(response.body().getCustomer_profile().get(0).getDistric());
                    edt_Taluko.setText(response.body().getCustomer_profile().get(0).getTehsil());
                    edt_Village.setText(response.body().getCustomer_profile().get(0).getVilage());


                    cuid = response.body().getCustomer_profile().get(0).getId();
                   }

                }

            @Override
            public void onFailure(@NotNull Call<ViewCustomerProfileEditModel> call, @NotNull Throwable t) {

            }
        });

        WebService.getClient().getDatabankMake().enqueue(new Callback<DatabankMakeModel>() {
            @Override
            public void onResponse(Call<DatabankMakeModel> call, Response<DatabankMakeModel> response1) {
                Log.d("MakeName", response1.body().toString());
                if (response1.isSuccessful()) {
                    if (response1.body() != null) {
                        l_make_name.clear();
                        l_make_name.add("Select Make");

                        for (int i = 0; i < response1.body().getState().size(); i++)
                        {
                            l_make_name.add(response1.body().getState().get(i).getMake());
                        }

                        ArrayAdapter adapter2 = new ArrayAdapter(ComplainBoxSecondActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, l_make_name);
                        sp_make_name.setAdapter(adapter2);

                        sp_make_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                makedata = l_make_name.get(position);  //
                                Log.d("ModelList", makedata);

                                if(!makedata.equals("Select Make")) {
                                    WebService.getClient().getDatabankModelList(makedata).enqueue(new Callback<DatabankModelListModel>() {
                                        @Override
                                        public void onResponse(Call<DatabankModelListModel> call, Response<DatabankModelListModel> response2) {
                                            l_model_list.clear();

                                            if (response2.isSuccessful()) {
                                                if (response2.body() != null) {
                                                    l_model_list.add("Select Model");
                                                    int xz = response2.body().getModel().size();
                                                    Log.d("ModelList", "" + xz +"");
                                                    for (int j = 0; j < response2.body().getModel().size();j++)
                                                    {
                                                        l_model_list.add(response2.body().getModel().get(j).getModelName());
                                                        Log.d("ModelList", "onResponse: " + response2.body().getModel().get(j).getModelName());
                                                    }

                                                    ArrayAdapter adapter3 = new ArrayAdapter(ComplainBoxSecondActivity.this,
                                                            android.R.layout.simple_spinner_dropdown_item, l_model_list);
                                                    sp_model_name.setAdapter(adapter3);


                                                    if (!(strModel != null ? strModel.equalsIgnoreCase("") : false)){

//                            if (strModel!=null  || !strModel.equals("")){
                                                        int spinnerPosition = adapter3.getPosition(strModel);
                                                        sp_model_name.setSelection(spinnerPosition);
                                                    }

                                                    sp_model_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                            modellistdata = l_model_list.get(i);
                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                        }
                                                    });
                                                }
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<DatabankModelListModel> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    l_model_list.clear();
                                    l_model_list.add("Select Model");
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<DatabankMakeModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isvalidate()){

                progressDialog = new ProgressDialog(ComplainBoxSecondActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                WebService.getClient().getComplainSubmit(cuid,
                        edt_Description.getText().toString(),
                        makedata,
                        modellistdata,
                        edt_TractorRegisterNo.getText().toString(),
                        edt_MfgYear_EP.getText().toString(),
                        emp).enqueue(new Callback<ComplainBoxSubModel>() {
                    @Override
                    public void onResponse(@NotNull Call<ComplainBoxSubModel> call,
                                           @NotNull Response<ComplainBoxSubModel> response) {

                        progressDialog.dismiss();


                        assert response.body() != null;
                        Toast.makeText(ComplainBoxSecondActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                       /* Intent i = new Intent(ComplainBoxSecondActivity.this, FoDashbord.class);
                        startActivity(i);*/


                        try {
                            String text = "** Kumar Group ** \n"
                                    + "Complain Detail:\n" +
                                    "Complaint No: " + response.body().getId() + "\n" +
                                    "FirstName: " + edt_FnameName.getText().toString() + "\n" +
                                    "LastName: " + edt_LastName.getText().toString() + "\n" +
                                    "Category: " + edt_categoryName.getText().toString() + "\n" +
                                    "Mobile: " + edt_mobile_number.getText().toString() + "\n" +
                                    "State: " + edt_State.getText().toString() + "\n" +
                                    "City: " + edt_City.getText().toString() + "\n" +
                                    "Village:" + edt_Village.getText().toString() +
                                    "District:" + edt_District.getText().toString() + "\n" +
                                    "Taluko:" + edt_Taluko.getText().toString() + "\n" +
                                    "Make:" + makedata + "\n" +
                                    "Model Name:" + modellistdata+ "\n" +
                                    "MFG Year:" + edt_MfgYear_EP.getText().toString() + "\n" +
                                    "Registor No.:" + edt_TractorRegisterNo.getText().toString() + "\n" +
                                    "Description: " + edt_Description.getText().toString();// Replace with your message.

                            // String toNumber = "+91" + edt_whatsapp_number.getText().toString();
                            String toNumber = "+917500567770";

                            // Replace with mobile phone number without +Sign or leading zeros, but with country code
                            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                       /* Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                        whatsappIntent.setType("text/plain");
                        whatsappIntent.setPackage("com.whatsapp");
                        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "** Kumar Group ** \n"
                                + "Complain Detail:\n" +
                                "FirstName: "+ edt_FnameName.getText().toString()+ "\n" +
                                "LastName: "+ edt_LastName.getText().toString()+ "\n" +
                                "Category: " + edt_categoryName.getText().toString()+ "\n" +
                                "Mobile: "+edt_mobile_number.getText().toString() + "\n" +
                                "State: "+edt_State.getText().toString() + "\n" +
                                "City: "+edt_City.getText().toString() + "\n" +
                                "Village:" + edt_Village.getText().toString() +
                                "District:" +edt_District.getText().toString()+ "\n" +
                                "Taluko:"+edt_Taluko.getText().toString());
                        try {
                            startActivity(whatsappIntent);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(ComplainBoxSecondActivity.this, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                        }*/


                       /* boolean installed = appInstallOrNot("com.whatsapp");
                        if (installed) {

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" +
                                    "+91" + edt_whatsapp_number.getText().toString()));
                            startActivity(intent);
                        } else {
                            Toast.makeText(ComplainBoxSecondActivity.this, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                        }*/



                    private boolean appInstallOrNot(String s) {

                            PackageManager packageManager = getPackageManager();
                            boolean app_installed;
                            try {

                                packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
                                app_installed = true;
                            } catch (PackageManager.NameNotFoundException e) {
                                app_installed = false;
                            }
                            return app_installed;
                    }

                    @Override
                    public void onFailure(@NotNull Call<ComplainBoxSubModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });

            }

            }
        });

    }

    private boolean isvalidate() {


        if (edt_Description.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show();
            return false;
        }else if (makedata.equals("Select Make")){
            Toast.makeText(this, "Please Select Make", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!makedata.equals("Select Make") ){
            if (modellistdata.equals("Select Model") ){
                Toast.makeText(this, "Enter Model", Toast.LENGTH_SHORT).show();
                return false;
            }
            if ( edt_MfgYear_EP.getText().toString().equals("")){
                Toast.makeText(this, "Enter Model Year", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}