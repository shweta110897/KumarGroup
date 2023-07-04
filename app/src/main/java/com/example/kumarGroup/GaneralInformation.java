package com.example.kumarGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GaneralInformation extends AppCompatActivity {

    List<String> state = new ArrayList<>();
    List<String> stateid = new ArrayList<>();
    List<String> City = new ArrayList<>();
    List<String> cityid=new ArrayList<>();
    List<String> District=new ArrayList<>();
    List<String> districtid=new ArrayList<>();
    List<String> Tehsil=new ArrayList<>();
    List<String> tehsilid=new ArrayList<>();
    List<String> Village=new ArrayList<>();
    List<String> villageid=new ArrayList<>();
    List<String> foname=new ArrayList<>();
    List<String> whatno=new ArrayList<>();
    List<String> fotoken=new ArrayList<>();
    List<String> foid=new ArrayList<>();
    Button btn_nextgv;


    String sid = "";
    String text = "";
    String cid = "";
    String did = "";
    String tid = "";
    String vid = "";
    String eid = "";
    String token = "";
    String whats_no = "";
    String  statedata,citydata,districtdata,tehsildata,villagedata,employedata, makedata = "", modellistdata = "", sor_inqData = "";
    Spinner sp_state,sp_city,sp_distric,sp_Taluko,sp_Village,sp_Fild_officer, sp_make_name, sp_model_name, sp_sor_inq;

    String s_make_name = "1", s_model_name = "1", s_sor_inq = "1";
    String Modeal_year;
    Boolean flag_make_name = false;
    Boolean flag_model_name = false;
    Boolean flag_sor_inq = false;
    List<String> l_make_name = new ArrayList<>();
    List<String> l_model_list = new ArrayList<>();

    EditText et_first_name,et_last_name,et_mobile_number,et_whatsapp_number,etMessage,etModeal_year;
    ProgressDialog progressDialog;

    EditText etNextVisitDate;
    Spinner sp_TypeHotCold,sp_FollowUpType,sp_model_emp,sp_source_inquire;

    String cat_id;
    String Modeal_year1;

    SharedPreferences sp1;
    String emp;

    String Status;


    String Item,categoryName;


    List<String> modelname_list;

    String HotColdType;
    String[] HotCold_Type = {"Select Type","Hot", "Cold"};


    String FollowUpType;
    String[] FollowUpType_Type = {"Select FollowUp Type","Call", "Visit","Visit With Dealer","Showroom Visit"};

    String[] sourceof_inquire = {"Select source of inquire","Activity", "Showroom visit","Calling","Digital","Field work","Work shop","Repeat & Resale"};


    int day,month,year;
    Calendar mcurrentdate;
    String dayOfWeek;
    String dayCount, MobileNo,add_type,add_id,String_modelget,sourceofinquire_string;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganeral_information);

        getSupportActionBar().setTitle("General Information");

        cat_id = getIntent().getStringExtra("categoryId");
        Status = getIntent().getStringExtra("Status");
        categoryName = getIntent().getStringExtra("categoryName");
        Modeal_year1 = getIntent().getStringExtra("Modeal_year");

        Item= getIntent().getStringExtra("Item");


        etMessage = findViewById(R.id.etMessage);


        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        sp_state = findViewById(R.id.sp_state);
        btn_nextgv = findViewById(R.id.btn_nextgv);
        sp_city = findViewById(R.id.sp_city);
        sp_distric = findViewById(R.id.sp_distric);
        sp_Taluko = findViewById(R.id.sp_Taluko);
        sp_Village = findViewById(R.id.sp_Village);
        sp_Fild_officer = findViewById(R.id.sp_Fild_officer);

        sp_TypeHotCold = findViewById(R.id.sp_TypeHotCold);
        sp_FollowUpType = findViewById(R.id.sp_FollowUpType);
        sp_source_inquire = findViewById(R.id.sp_source_inquire);

        sp_make_name = findViewById(R.id.sp_make_name);
        sp_model_name = findViewById(R.id.sp_model_name);
        sp_sor_inq = findViewById(R.id.sp_sor_inq);

        etModeal_year = findViewById(R.id.etModeal_year);

        //Textview
        et_first_name = findViewById(R.id.et_first_name);
        et_last_name = findViewById(R.id.et_last_name);
        et_mobile_number = findViewById(R.id.et_mobile_number);
        et_whatsapp_number = findViewById(R.id.et_whatsapp_number);

        etNextVisitDate = findViewById(R.id.etNextVisitDate);
        sp_model_emp = findViewById(R.id.sp_model_emp);
        modelname_list = new ArrayList<>();

        spinner = findViewById(R.id.spinner1);


       // Toast.makeText(this, ""+Item, Toast.LENGTH_SHORT).show();


        if(Item.equals("Inquiry")){
//            etNextVisitDate.setVisibility(View.VISIBLE);
//            sp_TypeHotCold.setVisibility(View.VISIBLE);
            //sp_FollowUpType.setVisibility(View.VISIBLE);
        }
        else{
            etNextVisitDate.setVisibility(View.GONE);
            sp_TypeHotCold.setVisibility(View.GONE);
            sp_FollowUpType.setVisibility(View.GONE);
        }

        Log.d("TAG", "onCreate: Modeal_year"+Modeal_year1);
        if (Modeal_year1.equals("0")){
            etModeal_year.setVisibility(View.VISIBLE);
        }else {
            etModeal_year.setVisibility(View.GONE);
        }



        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        etNextVisitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(GaneralInformation.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth)
                    {
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Date date = new Date(year, monthofYear, dayOfMonth-1);
                        dayOfWeek = simpledateformat.format(date);
                        Log.d("dayOfWeek", "onDateSet: "+dayOfWeek);

                        monthofYear = monthofYear+1;

                        String mt,dy;   //local variable
                        if(monthofYear<10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        }
                        else{
                            mt=String.valueOf(monthofYear);
                        }

                        if(dayOfMonth<10) {
                            dy = "0" + dayOfMonth;
                        }
                        else{
                            dy = String.valueOf(dayOfMonth);
                        }

                        etNextVisitDate.setText(year+"-"+mt+"-"+dy);
                    }
                },year,month,day);
                datePickerDialog.show();
               // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });
        etNextVisitDate.setFocusable(false);

        WebService.getClient().getDataBankFields(cat_id).enqueue(new Callback<DataBankFieldDisplayModel>() {
            @Override
            public void onResponse(Call<DataBankFieldDisplayModel> call, Response<DataBankFieldDisplayModel> response) {


                if (0 != response.body().getData().size()) {
                    s_make_name = response.body().getData().get(0).getMakeName();
                    s_model_name = response.body().getData().get(0).getModelName();
                    s_sor_inq = response.body().getData().get(0).getSorInq();



                    if (s_make_name.equals("0") && s_model_name.equals("0")) {
                        sp_make_name.setVisibility(View.VISIBLE);
                        sp_model_name.setVisibility(View.VISIBLE);

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

                                        ArrayAdapter adapter2 = new ArrayAdapter(GaneralInformation.this,
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

                                                                    ArrayAdapter adapter3 = new ArrayAdapter(GaneralInformation.this,
                                                                            android.R.layout.simple_spinner_dropdown_item, l_model_list);
                                                                    sp_model_name.setAdapter(adapter3);

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
                    }

                    if (s_sor_inq.equals("0")) {
                        sp_sor_inq.setVisibility(View.VISIBLE);
                        flag_sor_inq = true;

                        ArrayAdapter adapterSourceofin = new ArrayAdapter(GaneralInformation.this,
                                android.R.layout.simple_spinner_dropdown_item, sourceof_inquire);
                        sp_sor_inq.setAdapter(adapterSourceofin);

                        sp_sor_inq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                sor_inqData = sourceof_inquire[i];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<DataBankFieldDisplayModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        WebService.getClient().emp_getmodel_name_web().enqueue(new Callback<emp_modelname_model>() {
            @Override
            public void onResponse(Call<emp_modelname_model> call, Response<emp_modelname_model> response) {

                modelname_list.clear();

                modelname_list.add("Select Model");

                if (0 != response.body().getData().size()){
                    for (int i = 0 ; i < response.body().getData().size() ; i ++){
                        modelname_list.add(response.body().getData().get(i).getModel_name());
                    }


                    ArrayAdapter adapterType = new ArrayAdapter(GaneralInformation.this, android.R.layout.simple_spinner_dropdown_item, modelname_list);
                    sp_model_emp.setAdapter(adapterType);

                    sp_model_emp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if ("Select Model".equals(modelname_list.get(i))){
                                String_modelget = "";
                            }
                            else {
                                String_modelget = modelname_list.get(i);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<emp_modelname_model> call, Throwable t) {
                Toast.makeText(GaneralInformation.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter adapterType = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, HotCold_Type);
        sp_TypeHotCold.setAdapter(adapterType);

        sp_TypeHotCold.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HotColdType = HotCold_Type[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterSourceofin = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                sourceof_inquire);
        sp_source_inquire.setAdapter(adapterSourceofin);

        sp_source_inquire.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ("Select source of inquire".equals(sourceof_inquire[position]))
                {
                    sourceofinquire_string ="";
                }
                else {
                    sourceofinquire_string = sourceof_inquire[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter adapterFollowUpType = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                FollowUpType_Type);
        sp_FollowUpType.setAdapter(adapterFollowUpType);


        sp_FollowUpType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FollowUpType = FollowUpType_Type[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    //   ;

        btn_nextgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("vid",vid);
                Log.d("vid",eid);
                progressDialog= new ProgressDialog(GaneralInformation.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                       if(et_first_name.getText().toString().equals("")){
                           Utils.showErrorToast(GaneralInformation.this,"Please Enter First Name");

                       }
                       else if(et_last_name.getText().toString().equals("")){
                           Utils.showErrorToast(GaneralInformation.this,"Please Enter Last Name");

                       }
                     else if (et_mobile_number.getText().toString().equals("")){
                           Utils.showErrorToast(GaneralInformation.this,"Please Enter Mobile Number");

                       }
                     else if(et_whatsapp_number.getText().toString().equals("")){
                           Utils.showErrorToast(GaneralInformation.this,"Please Enter Whapp Number");

                       } else if(makedata.equals("Select Make")) {
                           Utils.showErrorToast(GaneralInformation.this,"Please Select Make");
                       } else if(modellistdata.equals("Select Model")) {
                           Utils.showErrorToast(GaneralInformation.this,"Please Select Model");
                       } else if(sor_inqData.equals("Select source of inquire")) {
                           Utils.showErrorToast(GaneralInformation.this,"Please Select Source of inquiry");
                       }

                if (!et_first_name.getText().toString().equals("")
                        && !et_last_name.getText().toString().equals("")
                        && !et_mobile_number.getText().toString().equals("")
                        && !et_whatsapp_number.getText().toString().equals(""))
                {


                    if ("".equals(String_modelget)){
                        Utils.showErrorToast(GaneralInformation.this,"Please select Model");
                        return;
                    }
                    else if ("".equals(sourceofinquire_string)){
                        Utils.showErrorToast(GaneralInformation.this,"Please select Source of inquire");
                        return;
                    }

                    Date currentTime = Calendar.getInstance().getTime();
                    Log.d("currentTimeNotAttend", String.valueOf(currentTime));
//                    SimpleDateFormat spf=new SimpleDateFormat("MM dd, yyyy hh:mm:ss");
                    SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date_str = spf.format(currentTime);
                    Log.d("date_str",date_str);

                    WebService.getClient().emp_getdata(emp,
                            String.valueOf(cat_id),
                            et_first_name.getText().toString(),
                            et_last_name.getText().toString(),
                            sid,
                            cid,
                            did,
                            tid,
                            vid,
                            eid,
                            et_mobile_number.getText().toString(),
                            et_whatsapp_number.getText().toString(),
                            etMessage.getText().toString(),
                            Status,
                            etNextVisitDate.getText().toString(),
                            //HotColdType,
                            "",
                            //FollowUpType,
                            "",
                            modellistdata,
                            makedata,
                            //sourceofinquire_string
                            sor_inqData,etModeal_year.getText().toString(),
                            date_str
                    ).enqueue(new Callback<DataSubmitModel>() {

                                    @Override
                                    public void onResponse(@NotNull Call<DataSubmitModel> call,
                                                           @NotNull Response<DataSubmitModel> response)
                                    {
                                        progressDialog.dismiss();

                                        Log.d("Response", response.body().toString());
                                        Toast.makeText(GaneralInformation.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(GaneralInformation.this, FoDashbord.class);
                                        startActivity(i);
                                        finish();

                                        if ("Inquiry".equals(Item)) {

                                            JSONObject notification = new JSONObject();
                                            try {
                                                 text = "Inquiry Detail:\t" +
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


                                                notification.put("title", "Inquiry Added");
                                                notification.put("body", text);
                                                // Add any additional data you want to send with the notification

                                                String notification_title = "Inquiry Added";
                                                String notification_des = text;
                                                new FCMMessages().sendMessageSingle(token, notification_title, notification_des, null);


//                                                String toNumber = whats_no;
//                                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+toNumber + "&text=" + text));
//                                                startActivity(intent);


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Utils.showErrorToast(GaneralInformation.this, ""+e.getMessage());
                                            }

                                        }else{
                                            try {

                                                String text = "પ્રિય મિત્ર.\n" +
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

//                                                String text = "  નમસ્કાર Kumar Group પરિવારમાં આપનું સ્વાગત છે! મને આનંદ છે કે તમે આ SMS વાંચી રહ્યા છો. હું તમને તમારા ખેતી વ્યવસાયમાં વૃદ્ધિ કરવામાં મદદ કરવા માટે આનંદ કરીશ. અમારી સાથે જોડાવા બદલ આભાર તરીકે, હું તમને એક ભેટ આપવા માંગું છું. હવે કેટલાક Free ખેતી ઉપયોગી સંસાધનોની લિંક મેળવો! https://youtu.be/fNM89dTlW_A, નવા સોનાલિકા ડિજિટલ શોરૂમ ની મુલકાત લેવા અહિયા ક્લિક કરો https://www.vvcard.in/a-s-world વધુ મહીતી સંપર્ક Mo-7500567770  ";
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
                                                String toNumber = et_mobile_number.getText().toString();
//                                                String toNumber = "+917500567770";

                                                // Replace with mobile phone number without +Sign or leading zeros, but with country code
                                                //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

                                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+toNumber + "&text=" + text));
                                                startActivity(intent);


                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Utils.showErrorToast(GaneralInformation.this, ""+e.getMessage());
                                            }

                                        }

                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<DataSubmitModel> call, @NotNull Throwable t) {
                                        progressDialog.dismiss();

                                        Log.d("Response", t.getMessage().toString());
                                        Toast.makeText(GaneralInformation.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                            });
                    }

                 }
             });

                WebService.getClient().getstate().enqueue(new Callback<StateModel>() {
                    @Override
                    public void onResponse(@NotNull Call<StateModel> call, @NotNull Response<StateModel> response) {
//                Log.d("Banks", response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                state.clear();
                                stateid.clear();

                                state.add("Select State");
                                stateid.add("0");
                                Log.d("Banks", response.body().toString());
                                for (int i = 0; i < response.body().getData().size(); i++)
                                {
                                    state.add(response.body().getData().get(i).getState());
                                    stateid.add(response.body().getData().get(i).getId());
                                }
                                Log.d("Banks", state.toString());
                                ArrayAdapter adapter2 = new ArrayAdapter(GaneralInformation.this,
                                        android.R.layout.simple_spinner_dropdown_item, state);
                                sp_state.setAdapter(adapter2);

                                sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        statedata = state.get(position);
                                        sid = stateid.get(position);



                                        WebService.getClient().getCity(sid).enqueue(new Callback<DataCityModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<DataCityModel> call,
                                                                   @NotNull Response<DataCityModel> response1) {
                                                City.clear();
                                                cityid.clear();
                                                if (response1.isSuccessful()) {
                                                    if (response1.body() != null) {
                                                        City.add("Select City");
                                                        cityid.add("0");

//                                                Log.d("City", response1.body().getCity().get(0).getCity());
                                                        for (int i = 0; i < response1.body().getCity().size();i++)
                                                        {
                                                            City.add(response1.body().getCity().get(i).getCity());
                                                            cityid.add(response1.body().getCity().get(i).getId());
                                                        }
                                                        //       final List<String> cityarray = Arrays.asList(response1.body().getCity().toString().replace("city="," ").split(","));

                                                        ArrayAdapter adapter3 = new ArrayAdapter(GaneralInformation.this, android.R.layout.simple_spinner_dropdown_item, City);
                                                        sp_city.setAdapter(adapter3);

                                                        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                                citydata = City.get(position);
                                                                cid = cityid.get(position);

                                                                WebService.getClient().getDistrict(cid).enqueue(new Callback<DataDistrictModel>() {
                                                                    @Override
                                                                    public void onResponse(Call<DataDistrictModel> call, Response<DataDistrictModel> response3) {
                                                                        District.clear();
                                                                        districtid.clear();
                                                                        if (response3.isSuccessful()){
                                                                            if (response3.body() !=null){
                                                                                District.add("Select District");
                                                                                districtid.add("0");
//                                                                        Log.d("District",response3.body().getDistrict().get(0).getDistrict());
                                                                                for (int i = 0; i < response3.body().getDistrict().size();i++)
                                                                                {

                                                                                    District.add(response3.body().getDistrict().get(i).getDistrict());
                                                                                    districtid.add(response3.body().getDistrict().get(i).getId());
                                                                                }
                                                                                ArrayAdapter adapter4 = new ArrayAdapter(GaneralInformation.this, android.R.layout.simple_spinner_dropdown_item, District);
                                                                                sp_distric.setAdapter(adapter4);


                                                                                sp_distric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                    @Override
                                                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                                                        districtdata = District.get(position);
                                                                                        did = districtid.get(position);


                                                                                        WebService.getClient().getTeshill(did).enqueue(new Callback<DataTehsilModel>() {
                                                                                            @Override
                                                                                            public void onResponse(Call<DataTehsilModel> call, Response<DataTehsilModel> response4) {
                                                                                                Tehsil.clear();
                                                                                                tehsilid.clear();
                                                                                                if(response4.isSuccessful()){

                                                                                                    if(response4.body() != null){
                                                                                                        Tehsil.add("Select Taluko");
                                                                                                        tehsilid.add("0");
//                                                                                                Log.d("Tehsil",response4.body().getTehsil().get(0).getTehsil_name());

                                                                                                        for (int i = 0; i< response4.body().getTehsil().size();i++ )
                                                                                                        {
                                                                                                            Tehsil.add(response4.body().getTehsil().get(i).getTehsil_name());
                                                                                                            tehsilid.add(response4.body().getTehsil().get(i).getId());

                                                                                                        }
                                                                                                        ArrayAdapter adapter5 = new ArrayAdapter(GaneralInformation.this, android.R.layout.simple_spinner_dropdown_item, Tehsil);
                                                                                                        sp_Taluko.setAdapter(adapter5);

                                                                                                        sp_Taluko.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                            @Override
                                                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                                                                                                                tehsildata = Tehsil.get(position);
                                                                                                                tid= tehsilid.get(position);

                                                                                                                WebService.getClient().getVillage(tid).enqueue(new Callback<DataVillageModel>() {
                                                                                                                    @Override
                                                                                                                    public void onResponse(Call<DataVillageModel> call, Response<DataVillageModel> response5) {
                                                                                                                        Village.clear();
                                                                                                                        villageid.clear();
                                                                                                                        if(response5.isSuccessful()){

                                                                                                                            if (response5.body() !=null){
                                                                                                                                Village.add("Select Village");
                                                                                                                                villageid.add("0");

//                                                                                                                      Log.d("Village",response5.body().getVillage().get(0).getVillage_name());
                                                                                                                                for (int i = 0 ; i< response5.body().getVillage().size(); i++){
                                                                                                                                    Village.add(response5.body().getVillage().get(i).getVillage_name());
                                                                                                                                    villageid.add(response5.body().getVillage().get(i).getId());                                                                                                                      }
                                                                                                                            }


                                                                                                                            //ArrayAdapter adapter6 = new ArrayAdapter(GaneralInformation.this, android.R.layout.simple_spinner_dropdown_item, Village);
                                                                                                                            spinner.setAdapter(new ArrayAdapter<>(GaneralInformation.this, android.R.layout.simple_spinner_dropdown_item,Village));
                                                                                                                            //sp_Village.setAdapter(spinner);
                                                                                                                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                @Override
                                                                                                                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                                                                                                    villagedata = Village.get(position);
                                                                                                                                    vid=villageid.get(position);
                                                                                                                                    WebService.getClient().getEMPname(vid,cat_id,Status).enqueue(new Callback<DatafonameModel>() {
                                                                                                                                        @Override
                                                                                                                                        public void onResponse(Call<DatafonameModel> call, Response<DatafonameModel> response6) {
                                                                                                                                            foname.clear();
                                                                                                                                            foid.clear();
                                                                                                                                            fotoken.clear();
                                                                                                                                            whatno.clear();

                                                                                                                                            if(response6.isSuccessful()){

                                                                                                                                                if (response6.body() !=null){
                                                                                                                                                    for (int i = 0; i < response6.body().getName().size();i++){

                                                                                                                                                        foname.add(response6.body().getName().get(i).getEname());
                                                                                                                                                        foid.add(response6.body().getName().get(i).getEmp_id());
                                                                                                                                                        whatno.add(response6.body().getName().get(i).getEno());
                                                                                                                                                        fotoken.add(response6.body().getName().get(i).getToken());
                                                                                                                                                    }
                                                                                                                                                    ArrayAdapter adapter7 = new ArrayAdapter(GaneralInformation.this, android.R.layout.simple_spinner_dropdown_item, foname);
                                                                                                                                                    sp_Fild_officer.setAdapter(adapter7);


                                                                                                                                                    sp_Fild_officer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                                                                                                                            employedata = foname.get(position);
                                                                                                                                                            eid = foid.get(position);
                                                                                                                                                            whats_no = whatno.get(position);
                                                                                                                                                            token = fotoken.get(position);
                                                                                                                                                        }

                                                                                                                                                        @Override
                                                                                                                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                                                                        }
                                                                                                                                                    });

                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }

                                                                                                                                        @Override
                                                                                                                                        public void onFailure(Call<DatafonameModel> call, Throwable t) {

                                                                                                                                        }
                                                                                                                                    });

                                                                                                                                }

                                                                                                                                @Override
                                                                                                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                                                }
                                                                                                                            });

                                                                                                                        }
                                                                                                                    }

                                                                                                                    @Override
                                                                                                                    public void onFailure(Call<DataVillageModel> call, Throwable t) {

                                                                                                                    }
                                                                                                                });

                                                                                                            }

                                                                                                            @Override
                                                                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                            }
                                                                                                        });

                                                                                                    }
                                                                                                }

                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Call<DataTehsilModel> call, Throwable t) {

                                                                                            }
                                                                                        });




                                                                                    }

                                                                                    @Override
                                                                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                    }
                                                                                });

                                                                            }


                                                                        }

                                                                    }

                                                                    @Override
                                                                    public void onFailure(Call<DataDistrictModel> call, Throwable t) {

                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> parent) {

                                                            }
                                                        });
                                                    }
                                                }
                                            }


                                            @Override
                                            public void onFailure(Call<DataCityModel> call, Throwable t) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<StateModel> call, Throwable t) {
                        Log.d("error", "onFailure: " + t);
                        Toast.makeText(GaneralInformation.this, "Error Rety", Toast.LENGTH_SHORT).show();

                    }
                });

            }


}
