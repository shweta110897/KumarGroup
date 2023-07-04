package com.example.kumarGroup.CustomerProfileData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.ComplainBoxSubModel;
import com.example.kumarGroup.DatabankMakeModel;
import com.example.kumarGroup.DatabankModelListModel;
import com.example.kumarGroup.InquiryDataBankModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.TractorDoIt;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewCustomerProfileEditModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitSearchActivity extends AppCompatActivity {

    String MobileNo;

    EditText edt_categoryName,edt_FnameName,edt_LastName,edt_State,
            edt_City,edt_District,edt_Taluko,edt_Village,edt_mobile_number,
            edt_whatsapp_number,edt_Description,edt_TractorRegisterNo,edt_MfgYear_EP;

    Button btn_Submit;

    String  cuid;

    ProgressDialog progressDialog;

    SharedPreferences sp;
    String emp;

    Button btn_add;
    LinearLayout ll_main;

    Spinner sp_make_name ,sp_model_name;
    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();

    String  makedata = "", modellistdata = "", strModel = "";


    List<String> category = new ArrayList<>();
    List<String> categoryId = new ArrayList<>();
    List<String> categoryStatus = new ArrayList<>();

    List<String> Inquiry = new ArrayList<>();
    List<String> InquiryId = new ArrayList<>();
    List<String> InquiryStatus = new ArrayList<>();

    boolean[] selectedLanguage;
    ArrayList<Integer> langList = new ArrayList<>();
    ArrayList<Integer> langListId = new ArrayList<>();
    String[] langArray ;
    String[] catIdArray ;

    TextView tv_inquiry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_search);

        MobileNo = getIntent().getStringExtra("MobileNo");

        tv_inquiry= findViewById(R.id.tv_inquiry);
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

        btn_add= findViewById(R.id.btn_add);
        ll_main= findViewById(R.id.ll_main);

        btn_Submit= findViewById(R.id.btn_Submit);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");


        progressDialog = new ProgressDialog(VisitSearchActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getViewCuProfileEdit(MobileNo).enqueue(new Callback<ViewCustomerProfileEditModel>() {
            @Override
            public void onResponse(@NotNull Call<ViewCustomerProfileEditModel> call,
                                   @NotNull Response<ViewCustomerProfileEditModel> response) {

                progressDialog.dismiss();

                assert response.body() != null;
                if (response.body().getCustomer_profile().size() == 0) {
                    Utils.showErrorToast(VisitSearchActivity.this, "No Data Available");
                    ll_main.setVisibility(View.GONE);
                    btn_add.setVisibility(View.VISIBLE);
                } else {

                    ll_main.setVisibility(View.VISIBLE);
                    btn_add.setVisibility(View.GONE);

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
                progressDialog.dismiss();
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

                        ArrayAdapter adapter2 = new ArrayAdapter(VisitSearchActivity.this,
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

                                                    ArrayAdapter adapter3 = new ArrayAdapter(VisitSearchActivity.this,
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

        WebService.getClient().getInquiryData().enqueue(new Callback<InquiryDataBankModel>() {
            @Override
            public void onResponse(@NotNull Call<InquiryDataBankModel> call,
                                   @NotNull Response<InquiryDataBankModel> response) {


                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Inquiry.clear();
                        InquiryId.clear();
                        InquiryStatus.clear();

                        Inquiry.add("Select Category");
                        InquiryId.add("0");
                        InquiryStatus.add("0");

                        Log.d("Banks", response.body().toString());

                        for (int i = 0; i < response.body().getData().size(); i++)
                        {
                            Inquiry.add(response.body().getData().get(i).getCat_list());
                            InquiryId.add(response.body().getData().get(i).getCat_id());
                            InquiryStatus.add(response.body().getData().get(i).getStatus());

                        }

                        langArray= Inquiry.toArray(new String[0]);
                        catIdArray= InquiryId.toArray(new String[0]);
                        selectedLanguage = new boolean[langArray.length];

                        tv_inquiry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Initialize alert dialog
                                AlertDialog.Builder builder = new AlertDialog.Builder(VisitSearchActivity.this);

                                // set title
                                builder.setTitle("Select Category");

                                // set dialog non cancelable
                                builder.setCancelable(false);

                                builder.setMultiChoiceItems(langArray, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                        // check condition
                                        if (b) {
                                            // when checkbox selected
                                            // Add position  in lang list
                                            langList.add(i);
                                            langListId.add(Integer.valueOf(catIdArray[i]));
                                            // Sort array list
                                            Collections.sort(langList);

                                            Log.d("langListId", String.valueOf(langListId));
                                        } else {
                                            // when checkbox unselected
                                            // Remove position from langList
                                            langList.remove(Integer.valueOf(i));
                                            langListId.remove(Integer.valueOf(catIdArray[i]));
                                        }

                                        Log.d("langListId", String.valueOf(langListId));
                                    }
                                });

                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // Initialize string builder
                                        StringBuilder stringBuilder = new StringBuilder();
                                        StringBuilder stringBuilder1 = new StringBuilder();
                                        // use for loop
                                        for (int j = 0; j < langList.size(); j++) {
                                            // concat array value
                                            stringBuilder.append(langArray[langList.get(j)]);
                                            // check condition
                                            if (j != langList.size() - 1) {
                                                // When j value  not equal
                                                // to lang list size - 1
                                                // add comma
                                                stringBuilder.append(", ");

                                            }
                                        }



                                        // set text on textView
                                        tv_inquiry.setText(stringBuilder.toString());


                                        Log.d("langListId_onOK", String.valueOf(langListId));

                                        String csv = langListId.toString().replace("[", "").replace("]", "")
                                                .replace(", ", ",");

                                        Log.d("langListId_onOKcsv", String.valueOf(csv));

                                        StringBuilder sb = new StringBuilder();
                                        for (Integer n : langListId) {
                                            if (sb.length() > 0) sb.append(',');
                                            sb.append("'").append(n).append("'");
                                        }
                                        Log.d("langListId1",sb.toString());

                                    }
                                });

                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // dismiss dialog
                                        dialogInterface.dismiss();
                                    }
                                });
                                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // use for loop
                                        for (int j = 0; j < selectedLanguage.length; j++) {
                                            // remove all selection
                                            selectedLanguage[j] = false;
                                            // clear language list
                                            langList.clear();
                                            langListId.clear();
                                            // clear text view value
                                            tv_inquiry.setText("");
                                        }
                                    }
                                });
                                // show dialog
                                builder.show();

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<InquiryDataBankModel> call, @NotNull Throwable t) {

            }
        });


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VisitSearchActivity.this, TractorDoIt.class);
                startActivity(i);
            }
        });


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isvalidate()) {

                    progressDialog = new ProgressDialog(VisitSearchActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String curDate = df.format(c);

                    if (makedata.equals("Select Make")){
                        makedata="";
                    }

                    WebService.getClient().getActivityEntryList(
                            emp,
                            edt_Description.getText().toString(),
                            curDate,
                            makedata,
                            modellistdata,
                            edt_TractorRegisterNo.getText().toString(),
                            edt_MfgYear_EP.getText().toString(),
                            langListId.toString().replace("[", "").replace("]", "")
                                    .replace(", ", ",")
                            , cuid).enqueue(new Callback<ComplainBoxSubModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ComplainBoxSubModel> call,
                                               @NotNull Response<ComplainBoxSubModel> response) {

                            progressDialog.dismiss();


                            String text  = "પ્રિય મિત્ર.\n" +
                                    "\n" +
                                    "નમસ્કાર......\n" +
                                    "આપનો કિમતી સમય આપવા બદલ આપનો ખૂબ-ખૂબ આભાર, અમો આવનરા સમય મા આપને સંતોષકારક સેવાઓ આપિશુ,અને આપના બહુમુલ્ય સુચનો ની આશા રાખીશુ.\n" +
                                    "\n" +
                                    "એ.એસ.વર્લ્ડ (સોનાલીકા ટ્રેક્ટર)\n" +
                                    "ભાવનગર\n" +
                                    "\n" +
                                    "\uD83D\uDC49 નવા સોનાલિકા ડિજિટલ શોરૂમ ની મુલકાત લેવા અહિયા ક્લિક કરો https://asworld.catalog.to\n" +
                                    "\n" +
                                    "વધુ મહીતી સંપર્ક Mo-9979848436\n" +
                                    "Mo-7500567770 \n" +
                                    "\n" +
                                    "\uD83D\uDC47 અહિયા ક્લિક કરો. \uD83D\uDC47\n" +
                                    "https://www.sonalikashowroom.com/\n" +
                                    "\n" ;

                            String toNumber = edt_whatsapp_number.getText().toString();
//                                                String toNumber = "+917500567770";

                            // Replace with mobile phone number without +Sign or leading zeros, but with country code
                            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


                            assert response.body() != null;

                            if (response.body().getMsg().equals("already Reason Update")){
                                Toast.makeText(VisitSearchActivity.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }else{
                                finish();

                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+toNumber + "&text=" + text));
                                startActivity(intent);
                            }




                       /* Intent i = new Intent(ComplainBoxSecondActivity.this, FoDashbord.class);
                        startActivity(i);*/


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

    public Menu option_Menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
     /*   option_Menu = menu;
        menu.findItem(R.id.action_add).setVisible(false);*/
        return true;
    }

    public void setMenuVisible(boolean visible, int id) {
        if (option_Menu != null) {
            option_Menu.findItem(id).setVisible(visible);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_search ){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isvalidate() {
        if (edt_Description.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show();
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