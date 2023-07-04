package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.DatabankMakeModel;
import com.example.kumarGroup.DatabankModelListModel;
import com.example.kumarGroup.DocumentBoxAddModel;
import com.example.kumarGroup.FoDashbord;
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

public class DocumentBoxSecondActivity extends AppCompatActivity
{
    EditText edt_categoryName_db,edt_FnameName_db,edt_LastName_db,edt_State_db,
            edt_City_db,edt_District_db,edt_Taluko_db,edt_Village_db,edt_mobile_number_db,
            edt_whatsapp_number_db,edt_Description_db,edt_TractorRegisterNo,edt_MfgYear_EP;

    Button btn_Submit_db;

    String MobileNo, cuid;

    ProgressDialog progressDialog;

    SharedPreferences sp;
    String emp;

    CheckBox checkBox,checkBoxBL,checkBoxPC,checkBoxSI,checkBoxBSVF,checkBoxKD,checkBox7_12,
            checkBoxForm,checkBoxBankLoanStatement;

    ArrayList<String> CheckName = new ArrayList<>();

    Spinner sp_make_name ,sp_model_name;
    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();

    String  makedata = "", modellistdata = "", strModel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_document_box_second);

        edt_categoryName_db= findViewById(R.id.edt_categoryName_db);
        edt_FnameName_db= findViewById(R.id.edt_FnameName_db);
        edt_LastName_db= findViewById(R.id.edt_LastName_db);
        edt_State_db= findViewById(R.id.edt_State_db);
        edt_City_db= findViewById(R.id.edt_City_db);
        edt_District_db= findViewById(R.id.edt_District_db);
        edt_Taluko_db= findViewById(R.id.edt_Taluko_db);
        edt_Village_db= findViewById(R.id.edt_Village_db);
        edt_mobile_number_db= findViewById(R.id.edt_mobile_number_db);
        edt_whatsapp_number_db= findViewById(R.id.edt_whatsapp_number_db);
        edt_Description_db= findViewById(R.id.edt_Description_db);
        edt_TractorRegisterNo= findViewById(R.id.edt_TractorRegisterNo);
        edt_MfgYear_EP= findViewById(R.id.edt_MfgYear_EP);
        sp_make_name = findViewById(R.id.sp_make_name);
        sp_model_name = findViewById(R.id.sp_model_name);

        btn_Submit_db= findViewById(R.id.btn_Submit_db);


        checkBox=findViewById(R.id.checkBox);
        checkBoxBL=findViewById(R.id.checkBoxBL);
        checkBoxPC=findViewById(R.id.checkBoxPC);
        checkBoxSI=findViewById(R.id.checkBoxSI);
        checkBoxBSVF=findViewById(R.id.checkBoxBSVF);
        checkBoxKD=findViewById(R.id.checkBoxKD);
        checkBox7_12=findViewById(R.id.checkBox7_12);
        checkBoxForm=findViewById(R.id.checkBoxForm);
        checkBoxBankLoanStatement=findViewById(R.id.checkBoxBankLoanStatement);


        MobileNo = getIntent().getStringExtra("MobileNo");


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        progressDialog= new ProgressDialog(DocumentBoxSecondActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getViewCuProfileEdit(MobileNo)
                .enqueue(new Callback<ViewCustomerProfileEditModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileEditModel> call,
                                   @NotNull Response<ViewCustomerProfileEditModel> response) {

                assert response.body() != null;
                if (response.body().getCustomer_profile().size() == 0) {
                    progressDialog.dismiss();
                    Utils.showErrorToast(DocumentBoxSecondActivity.this, "No Data Available");
                } else {

                    progressDialog.dismiss();
                    edt_categoryName_db.setText(response.body().getCustomer_profile().get(0).getCat_name());
                    edt_FnameName_db.setText(response.body().getCustomer_profile().get(0).getFname());
                    edt_LastName_db.setText(response.body().getCustomer_profile().get(0).getLname());
                    edt_mobile_number_db.setText(response.body().getCustomer_profile().get(0).getMoblino());
                    edt_whatsapp_number_db.setText(response.body().getCustomer_profile().get(0).getWhatsappno());
                    edt_State_db.setText(response.body().getCustomer_profile().get(0).getState());
                    edt_City_db.setText(response.body().getCustomer_profile().get(0).getCity());
                    edt_District_db.setText(response.body().getCustomer_profile().get(0).getDistric());
                    edt_Taluko_db.setText(response.body().getCustomer_profile().get(0).getTehsil());
                    edt_Village_db.setText(response.body().getCustomer_profile().get(0).getVilage());

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

                        ArrayAdapter adapter2 = new ArrayAdapter(DocumentBoxSecondActivity.this,
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

                                                    ArrayAdapter adapter3 = new ArrayAdapter(DocumentBoxSecondActivity.this,
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




        btn_Submit_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder sb = new StringBuilder("");

                if(checkBox.isChecked()){
                 /*String s = checkBox.getText().toString();
                    sb.append(s+",");*/

                    CheckName.add(checkBox.getText().toString());

                }else{
                    /*String s = "";
                    sb.append(s);*/

                    CheckName.remove(checkBox.getText().toString());

                }


                if(checkBoxBL.isChecked()){
                   /* String  s1 = checkBoxBL.getText().toString();
                    sb.append(s1+",");*/

                    CheckName.add(checkBoxBL.getText().toString());
                }else{
                  /*  String s1 = "";
                    sb.append(s1);*/

                    CheckName.remove(checkBoxBL.getText().toString());
                }


                if(checkBoxSI.isChecked()){
                  /*  String  s2 = checkBoxSI.getText().toString();
                    sb.append(s2+",");*/

                    CheckName.add(checkBoxSI.getText().toString());

                }else{
                    /*String  s2 = "";
                    sb.append(s2);*/

                    CheckName.remove(checkBoxSI.getText().toString());

                }

                if(checkBoxBSVF.isChecked()){
                    /*String  s2 = checkBoxBSVF.getText().toString();
                    sb.append(s2+",");*/

                    CheckName.add(checkBoxBSVF.getText().toString());
                }else{
                    /*String  s2 = "";
                    sb.append(s2);*/


                    CheckName.remove(checkBoxBSVF.getText().toString());

                }

                if(checkBoxKD.isChecked()){
                    /*String  s2 = checkBoxKD.getText().toString();
                    sb.append(s2+",");*/

                    CheckName.add(checkBoxKD.getText().toString());
                }else{
                   /* String  s2 = "";
                    sb.append(s2);*/

                    CheckName.remove(checkBoxKD.getText().toString());
                }

                if(checkBox7_12.isChecked()){
                   /* String  s2 = checkBox7_12.getText().toString();
                    sb.append(s2+",");*/

                    CheckName.add(checkBox7_12.getText().toString());
                }else{
                   /* String  s2 = "";
                    sb.append(s2);*/

                    CheckName.remove(checkBox7_12.getText().toString());
                }


                if(checkBoxBankLoanStatement.isChecked()){
                    CheckName.add(checkBoxBankLoanStatement.getText().toString());
                }else{
                    CheckName.remove(checkBoxBankLoanStatement.getText().toString());
                }


                if(checkBoxForm.isChecked()){
                    CheckName.add(checkBoxForm.getText().toString());
                }else{
                    CheckName.remove(checkBoxForm.getText().toString());
                }



                String BorrowOneNameArrayItem = "";

                for(int i=0; i<=CheckName.size();i++)
                {
                    if(BorrowOneNameArrayItem.equals("")){
                        try {
                            BorrowOneNameArrayItem = CheckName.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            BorrowOneNameArrayItem = BorrowOneNameArrayItem+","+CheckName.get(i);
                           // Log.d("BBList_ll", "onCreate: "+BorrowOneNameArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

             //  Toast.makeText(DocumentBoxSecondActivity.this, ""+BorrowOneNameArrayItem, Toast.LENGTH_SHORT).show();
                if (isvalidate()) {

                    progressDialog = new ProgressDialog(DocumentBoxSecondActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                    if (makedata.equals("Select Make")){
                        makedata="";
                    }

                    WebService.getClient().getDocumentBoxAdd(emp,
                            cuid,
                            BorrowOneNameArrayItem,
                            makedata,
                            modellistdata,
                            edt_TractorRegisterNo.getText().toString(),
                            edt_MfgYear_EP.getText().toString(),
                            edt_Description_db.getText().toString()).enqueue(new Callback<DocumentBoxAddModel>() {
                        @Override
                        public void onResponse(@NotNull Call<DocumentBoxAddModel> call, @NotNull Response<DocumentBoxAddModel> response) {
                            progressDialog.dismiss();



                            String text = "  નમસ્કાર Kumar Group પરિવારમાં આપનું સ્વાગત છે! મને આનંદ છે કે તમે આ SMS વાંચી રહ્યા છો. હું તમને તમારા ખેતી વ્યવસાયમાં વૃદ્ધિ કરવામાં મદદ કરવા માટે આનંદ કરીશ. અમારી સાથે જોડાવા બદલ આભાર તરીકે, હું તમને એક ભેટ આપવા માંગું છું. હવે કેટલાક Free ખેતી ઉપયોગી સંસાધનોની લિંક મેળવો! https://youtu.be/fNM89dTlW_A, નવા સોનાલિકા ડિજિટલ શોરૂમ ની મુલકાત લેવા અહિયા ક્લિક કરો https://www.vvcard.in/a-s-world વધુ મહીતી સંપર્ક Mo-7500567770  ";
                                               /* String text = "** Kumar Group ** \n"
                                                        + "Inquiry Detail:\n" +
                                                        "FirstName: " + et_first_name.getText().toString() + "\n" +
                                                        "LastName: " + et_last_name.getText().toString() + "\n" +
                                                        "Category: " + categoryName + "\n" +
                                                        "Mobile: " + et_mobile_number.getText().toString() + "\n" +
                                                        "State: " + statedata + "\n" +
                                                        "City: " + citydata + "\n" +
                                                        "Village:" + villagedata + "\n"+
                                                        "District:" + districtdata + "\n" +
                                                        "Taluko:" + tehsildata + "\n" +
                                                        "Make: " + makedata +"\n" +
                                                        "Model: " + modellistdata +"\n" +
                                                        "Source of inquiry: " + sor_inqData +"\n" +
                                                        "Description: " + etMessage.getText().toString();// Replace with your message.
*/
                            String toNumber = edt_whatsapp_number_db.getText().toString();
//                                                String toNumber = "+917500567770";

                            // Replace with mobile phone number without +Sign or leading zeros, but with country code
                            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+toNumber + "&text=" + text));
                            startActivity(intent);



                            Toast.makeText(DocumentBoxSecondActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(DocumentBoxSecondActivity.this, FoDashbord.class);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(@NotNull Call<DocumentBoxAddModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                        }
                    });
                }

            }

        });

    }

    private boolean isvalidate() {

        if (edt_Description_db.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show();
            return false;
        }else if (makedata!=null && modellistdata.equals("Select Model")){
            Toast.makeText(this, "Select Model Name First", Toast.LENGTH_SHORT).show();
            return false;
        }else if (makedata!=null && !modellistdata.equals("Select Model")  && edt_MfgYear_EP.getText().toString().equals("")){
            Toast.makeText(this, "Enter Model Year", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}