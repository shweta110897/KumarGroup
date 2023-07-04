package com.example.kumarGroup.SSP.DataBank.AK;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.kumarGroup.DataCityModel;
import com.example.kumarGroup.DataDistrictModel;
import com.example.kumarGroup.DataSubmitModel;
import com.example.kumarGroup.DataTehsilModel;
import com.example.kumarGroup.DataVillageModel;
import com.example.kumarGroup.DatafonameModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.SSP.SSPDashboardActivity;
import com.example.kumarGroup.StateModel;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.ssp_databank_employ_model;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SSPGaneralInformationAK extends AppCompatActivity {

    List<String> state = new ArrayList<>();
    List<String> stateid = new ArrayList<>();
    List<String> City = new ArrayList<>();
    List<String>cityid=new ArrayList<>();
    List<String>District=new ArrayList<>();
    List<String>districtid=new ArrayList<>();
    List<String>Tehsil=new ArrayList<>();
    List<String>tehsilid=new ArrayList<>();
    List<String>Village=new ArrayList<>();
    List<String>villageid=new ArrayList<>();
    List<String>foname=new ArrayList<>();
    List<String>fonameak=new ArrayList<>();
    List<String>foid=new ArrayList<>();
    List<String>foidak=new ArrayList<>();
    Button btn_nextgv;


    String sid = "";
    String cid = "";
    String did = "";
    String tid = "";
    String vid = "";
    String eid = "";
    String eidak = "";
    String  statedata,citydata,districtdata,tehsildata,villagedata,employedata,employedataak;
    Spinner sp_state,sp_city,sp_distric,sp_Taluko,sp_Village,sp_Fild_officer,sp_emploay;

    EditText et_first_name,et_last_name,et_mobile_number,et_whatsapp_number,etMessage;
    ProgressDialog progressDialog;


    EditText etNextVisitDate;
    Spinner sp_TypeHotCold,sp_FollowUpType;

    String cat_id;

    SharedPreferences sp1;
    String emp;

    String Status;


    String Item;


    String HotColdType;
    String[] HotCold_Type = {"Select Type","Hot", "Cold"};


    String FollowUpType;
    String[] FollowUpType_Type = {"Select FollowUp Type","Call", "Visit","Visit With Dealer","Showroom Visit"};


    int day,month,year;
    Calendar mcurrentdate;
    String dayOfWeek;
    String dayCount, MobileNo,add_type,add_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sspganeral_information_ak);


        getSupportActionBar().setTitle("General Information");

        cat_id = getIntent().getStringExtra("categoryId");
        Status = getIntent().getStringExtra("Status");


        Item= getIntent().getStringExtra("Item");


        etMessage = findViewById(R.id.etMessage);


        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");
        //  etMessage.setVisibility(View.GONE);
        // editext();
        //  Toast.makeText(this, ""+cat_id, Toast.LENGTH_SHORT).show();
        // Log.d("iddd", "onCreate: "+cat_id);

        sp_state = findViewById(R.id.sp_state);
        btn_nextgv = findViewById(R.id.btn_nextgv);
        sp_city = findViewById(R.id.sp_city);
        sp_emploay = findViewById(R.id.sp_emploay);
        sp_distric = findViewById(R.id.sp_distric);
        sp_Taluko = findViewById(R.id.sp_Taluko);
        sp_Village = findViewById(R.id.sp_Village);
        sp_Fild_officer = findViewById(R.id.sp_Fild_officer);

        sp_TypeHotCold = findViewById(R.id.sp_TypeHotCold);
        sp_FollowUpType = findViewById(R.id.sp_FollowUpType);


        //Textview
        et_first_name = findViewById(R.id.et_first_name);
        et_last_name = findViewById(R.id.et_last_name);
        et_mobile_number = findViewById(R.id.et_mobile_number);
        et_whatsapp_number = findViewById(R.id.et_whatsapp_number);

        etNextVisitDate = findViewById(R.id.etNextVisitDate);


        // Toast.makeText(this, ""+Item, Toast.LENGTH_SHORT).show();


        if(Item.equals("Inquiry")){
            etNextVisitDate.setVisibility(View.VISIBLE);
            sp_TypeHotCold.setVisibility(View.VISIBLE);
            sp_FollowUpType.setVisibility(View.VISIBLE);
        }
        else{
            etNextVisitDate.setVisibility(View.GONE);
            sp_TypeHotCold.setVisibility(View.GONE);
            sp_FollowUpType.setVisibility(View.GONE);
        }



        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH );
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR );
        // month = month+1;

        WebService.getClient().get_ssp_databank_ak_web().enqueue(new Callback<ssp_databank_employ_model>() {
            @Override
            public void onResponse(Call<ssp_databank_employ_model> call, Response<ssp_databank_employ_model> responseak) {
                fonameak.clear();

                if(responseak.isSuccessful()){

                    if (responseak.body() !=null){
                        for (int i = 0; i < responseak.body().getData().size();i++){

                            fonameak.add(responseak.body().getData().get(i).getEname());
                            foidak.add(responseak.body().getData().get(i).getId());
                        }
                        ArrayAdapter adapter7 = new ArrayAdapter(SSPGaneralInformationAK.this, android.R.layout.simple_spinner_dropdown_item, fonameak);
                        sp_emploay.setAdapter(adapter7);

                        sp_emploay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                employedataak = fonameak.get(position);
                                eidak = foidak.get(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<ssp_databank_employ_model> call, Throwable t) {

            }
        });



        etNextVisitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SSPGaneralInformationAK.this, R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
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


        ArrayAdapter adapterType = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                HotCold_Type);
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
                progressDialog= new ProgressDialog(SSPGaneralInformationAK.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                if(et_first_name.getText().toString().equals("")){
                    Utils.showErrorToast(SSPGaneralInformationAK.this,"Please Enter First Name");

                }
                else if(et_last_name.getText().toString().equals("")){
                    Utils.showErrorToast(SSPGaneralInformationAK.this,"Please Enter Last Name");

                }
                else if (et_mobile_number.getText().toString().equals("")){
                    Utils.showErrorToast(SSPGaneralInformationAK.this,"Please Enter Mobile Number");

                }
                else if(et_whatsapp_number.getText().toString().equals("")){
                    Utils.showErrorToast(SSPGaneralInformationAK.this,"Please Enter Whapp Number");

                }

                if (!et_first_name.getText().toString().equals("")
                        && !et_last_name.getText().toString().equals("")
                        && !et_mobile_number.getText().toString().equals("")
                        && !et_whatsapp_number.getText().toString().equals(""))
                {

                    WebService.getClient().ssp_getdata(eidak,
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
                            HotColdType,
                            FollowUpType).enqueue(new Callback<DataSubmitModel>() {

                        @Override
                        public void onResponse(@NotNull Call<DataSubmitModel> call,
                                               @NotNull Response<DataSubmitModel> response)
                        {
                            progressDialog.dismiss();

                            DataSubmitModel DataSubmitModel = response.body();
                            Log.d("Response", response.body().toString());
                            Toast.makeText(SSPGaneralInformationAK.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();

                            Intent i=new Intent(SSPGaneralInformationAK.this, SSPDashboardActivity.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onFailure(@NotNull Call<DataSubmitModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();

                            Log.d("Response", t.getMessage().toString());
                            Toast.makeText(SSPGaneralInformationAK.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
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
                        ArrayAdapter adapter2 = new ArrayAdapter(SSPGaneralInformationAK.this,
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

                                                ArrayAdapter adapter3 = new ArrayAdapter(SSPGaneralInformationAK.this, android.R.layout.simple_spinner_dropdown_item, City);
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
                                                                        ArrayAdapter adapter4 = new ArrayAdapter(SSPGaneralInformationAK.this, android.R.layout.simple_spinner_dropdown_item, District);
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
                                                                                                ArrayAdapter adapter5 = new ArrayAdapter(SSPGaneralInformationAK.this, android.R.layout.simple_spinner_dropdown_item, Tehsil);
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
                                                                                                                    ArrayAdapter adapter6 = new ArrayAdapter(SSPGaneralInformationAK.this, android.R.layout.simple_spinner_dropdown_item, Village);
                                                                                                                    sp_Village.setAdapter(adapter6);

                                                                                                                    sp_Village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                        @Override
                                                                                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                                                                                            villagedata = Village.get(position);
                                                                                                                            vid=villageid.get(position);

                                                                                                                            WebService.getClient().getfoname(vid).enqueue(new Callback<DatafonameModel>() {
                                                                                                                                @Override
                                                                                                                                public void onResponse(Call<DatafonameModel> call, Response<DatafonameModel> response6) {
                                                                                                                                    foname.clear();

                                                                                                                                    if(response6.isSuccessful()){

                                                                                                                                        if (response6.body() !=null){
                                                                                                                                            for (int i = 0; i < response6.body().getName().size();i++){

                                                                                                                                                foname.add(response6.body().getName().get(i).getEname());
                                                                                                                                                foid.add(response6.body().getName().get(i).getEmp_id());
                                                                                                                                            }
                                                                                                                                            ArrayAdapter adapter7 = new ArrayAdapter(SSPGaneralInformationAK.this, android.R.layout.simple_spinner_dropdown_item, foname);
                                                                                                                                            sp_Fild_officer.setAdapter(adapter7);


                                                                                                                                            sp_Fild_officer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                @Override
                                                                                                                                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                                                                                                                    employedata = foname.get(position);
                                                                                                                                                    eid = foid.get(position);
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
                Toast.makeText(SSPGaneralInformationAK.this, "Error Rety", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
