package com.example.kumarGroup.BookingDeliveryUpload.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.ConsumerSkimSubmitModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerSkimActivity extends AppCompatActivity {

    Spinner spn_CustomerSkim_Hood,spn_CustomerSkim_Toplink,spn_CustomerSkim_DrawBar,
            spn_CustomerSkim_ToolKit,spn_CustomerSkim_Bumper,spn_CustomerSkim_Hitch,
            spn_CustomerSkim_ConsumerSkim,spn_CustomerSkim_ConsumerSkim1,spn_referral_Skim;

    EditText edt_CustomerSkim_Description,edt_CustomerSkim_Amount,edt_referral_referral_name,
            edt_referral_mobileno,edt_referral_Description,edt_referral_Amount;

    Button btn_AddRto_Next;

    String consumer_Skim;
    String[] ConsumerSkim = {"Accessories","Yes","No"};

    String consumer_Skim1;
    String[] ConsumerSkim1 = {"Consumer Skim","Yes","No"};

    String referral_Skim1;
    String[] referral_Skim = {"Referral Skim","Yes","No"};


    String cs_Hood;
    String[] CSHood = {"Hood(canopy)","Yes","No"};

    String cs_Toplink;
    String[] CSTopLink = {"TopLink","Yes","No"};

    String cs_DrawBar;
    String[] CSDrawBar = {"DrawBar","Yes","No"};

    String cs_ToolKit;
    String[] CSToolKit = {"ToolKit","Yes","No"};

    String cs_Bumper;
    String[] CSBumper = {"Bumper","Yes","No"};

    String cs_Hitch;
    String[] CSHitch = {"Hitch","Yes","No"};

    SharedPreferences sharedPreferences;
    String NextIDD;

    ProgressDialog pro;


    public static boolean check1 = false;
    public static boolean check2 = false;
    public static boolean check3 = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_skim);
        getSupportActionBar().setTitle("Consumer Skim");

        /** ************************** Memory Allocation **************************** */
        pro = new ProgressDialog(this);

        check1 = false;
        check2 = false;
        check3 = false;


        spn_CustomerSkim_Hood=findViewById(R.id.spn_CustomerSkim_Hood);
        spn_CustomerSkim_Toplink=findViewById(R.id.spn_CustomerSkim_Toplink);
        spn_CustomerSkim_DrawBar=findViewById(R.id.spn_CustomerSkim_DrawBar);
        spn_CustomerSkim_ToolKit=findViewById(R.id.spn_CustomerSkim_ToolKit);
        spn_CustomerSkim_Bumper=findViewById(R.id.spn_CustomerSkim_Bumper);
        spn_CustomerSkim_Hitch=findViewById(R.id.spn_CustomerSkim_Hitch);
        spn_CustomerSkim_ConsumerSkim=findViewById(R.id.spn_CustomerSkim_ConsumerSkim);
        spn_CustomerSkim_ConsumerSkim1=findViewById(R.id.spn_CustomerSkim_ConsumerSkim1);
        spn_referral_Skim=findViewById(R.id.spn_referral_Skim);
        edt_referral_referral_name=findViewById(R.id.edt_referral_referral_name);
        edt_referral_mobileno=findViewById(R.id.edt_referral_mobileno);

        edt_CustomerSkim_Description=findViewById(R.id.edt_CustomerSkim_Description);
        edt_CustomerSkim_Amount=findViewById(R.id.edt_CustomerSkim_Amount);
        edt_referral_Description=findViewById(R.id.edt_referral_Description);
        edt_referral_Amount=findViewById(R.id.edt_referral_Amount);

        btn_AddRto_Next=findViewById(R.id.btn_AddRto_Next);
        edt_CustomerSkim_Description.setVisibility(View.GONE);

        /** *********************************************************************** */

        spn_CustomerSkim_ConsumerSkim1.setBackgroundColor(getResources().getColor(R.color.Green));
        spn_CustomerSkim_ConsumerSkim.setBackgroundColor(getResources().getColor(R.color.snack_red));
        spn_referral_Skim.setBackgroundColor(getResources().getColor(R.color.Yellow));

        sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
        NextIDD = sharedPreferences.getString("NextId","");

        ArrayAdapter consumerSkim1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,ConsumerSkim1);
        spn_CustomerSkim_ConsumerSkim1.setAdapter(consumerSkim1);
        spn_CustomerSkim_ConsumerSkim1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                consumer_Skim1 = ConsumerSkim1[i];
                if (consumer_Skim1.equals("Yes")){
                    edt_CustomerSkim_Description.setVisibility(View.VISIBLE);
                    edt_CustomerSkim_Amount.setVisibility(View.VISIBLE);
                }else {
                    edt_CustomerSkim_Description.setVisibility(View.GONE);
                    edt_CustomerSkim_Amount.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter consumerSkim = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,ConsumerSkim);
        spn_CustomerSkim_ConsumerSkim.setAdapter(consumerSkim);

        spn_CustomerSkim_ConsumerSkim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                consumer_Skim = ConsumerSkim[position];

                if(ConsumerSkim[position].equals("Yes")){
                    spn_CustomerSkim_Hood.setVisibility(View.VISIBLE);
                    spn_CustomerSkim_Toplink.setVisibility(View.VISIBLE);
                    spn_CustomerSkim_DrawBar.setVisibility(View.VISIBLE);
                    spn_CustomerSkim_ToolKit.setVisibility(View.VISIBLE);
                    spn_CustomerSkim_Bumper.setVisibility(View.VISIBLE);
                    spn_CustomerSkim_Hitch.setVisibility(View.VISIBLE);
                  //  edt_CustomerSkim_Description.setVisibility(View.VISIBLE);
                }
                else{
                    spn_CustomerSkim_Hood.setVisibility(View.GONE);
                    spn_CustomerSkim_Toplink.setVisibility(View.GONE);
                    spn_CustomerSkim_DrawBar.setVisibility(View.GONE);
                    spn_CustomerSkim_ToolKit.setVisibility(View.GONE);
                    spn_CustomerSkim_Bumper.setVisibility(View.GONE);
                    spn_CustomerSkim_Hitch.setVisibility(View.GONE);
                 //   edt_CustomerSkim_Description.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter Referal = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,referral_Skim);
        spn_referral_Skim.setAdapter(Referal);

        spn_referral_Skim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                referral_Skim1 = referral_Skim[i];
                if (referral_Skim[i].equals("Yes")){
                    edt_referral_referral_name.setVisibility(View.VISIBLE);
                    edt_referral_mobileno.setVisibility(View.VISIBLE);
                    edt_referral_Description.setVisibility(View.VISIBLE);
                    edt_referral_Amount.setVisibility(View.VISIBLE);
                }else {
                    edt_referral_referral_name.setVisibility(View.GONE);
                    edt_referral_mobileno.setVisibility(View.GONE);
                    edt_referral_Description.setVisibility(View.GONE);
                    edt_referral_Amount.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapterHood = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,CSHood);
        spn_CustomerSkim_Hood.setAdapter(adapterHood);

        spn_CustomerSkim_Hood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cs_Hood = CSHood[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterToplink = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,CSTopLink);
        spn_CustomerSkim_Toplink.setAdapter(adapterToplink);

        spn_CustomerSkim_Toplink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cs_Toplink = CSTopLink[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterDrawBar = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,CSDrawBar);
        spn_CustomerSkim_DrawBar.setAdapter(adapterDrawBar);

        spn_CustomerSkim_DrawBar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cs_DrawBar = CSDrawBar[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterToolKit = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,CSToolKit);
        spn_CustomerSkim_ToolKit.setAdapter(adapterToolKit);

        spn_CustomerSkim_ToolKit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cs_ToolKit = CSToolKit[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterBumper = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,CSBumper);
        spn_CustomerSkim_Bumper.setAdapter(adapterBumper);

        spn_CustomerSkim_Bumper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cs_Bumper = CSBumper[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterHitch = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,CSHitch);
        spn_CustomerSkim_Hitch.setAdapter(adapterHitch);

        spn_CustomerSkim_Hitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cs_Hitch = CSHitch[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_AddRto_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro.setCancelable(false);
                pro.setMessage("Please wait ...");


                if (consumer_Skim1.equals("Yes")) {
                    if (edt_CustomerSkim_Description.getText().length() == 0) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        edt_CustomerSkim_Description.setError("Enter Data");
                    } else if (edt_CustomerSkim_Amount.getText().length() == 0) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        edt_CustomerSkim_Amount.setError("Enter Data");
                    } else {
                        check1 = true;
                    }
                } else {
                    check1 = true;
                }
                if (consumer_Skim.equals("Yes")) {
                    if (cs_Hood.equals("Hood(canopy)")) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        TextView errorText = (TextView) spn_CustomerSkim_Hood.getSelectedView();
                        errorText.setError("Enter Data");
                        errorText.setTextColor(Color.RED);
                        errorText.setText("Enter Data");
                    } else if (cs_Toplink.equals("TopLink")) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        TextView errorText = (TextView) spn_CustomerSkim_Toplink.getSelectedView();
                        errorText.setError("Enter Data");
                        errorText.setTextColor(Color.RED);
                        errorText.setText("Enter Data");
                    } else if (cs_DrawBar.equals("DrawBar")) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        TextView errorText = (TextView) spn_CustomerSkim_DrawBar.getSelectedView();
                        errorText.setError("Enter Data");
                        errorText.setTextColor(Color.RED);
                        errorText.setText("Enter Data");
                    } else if (cs_ToolKit.equals("ToolKit")) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        TextView errorText = (TextView) spn_CustomerSkim_ToolKit.getSelectedView();
                        errorText.setError("Enter Data");
                        errorText.setTextColor(Color.RED);
                        errorText.setText("Enter Data");
                    } else if (cs_Bumper.equals("Bumper")) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        TextView errorText = (TextView) spn_CustomerSkim_Bumper.getSelectedView();
                        errorText.setError("Enter Data");
                        errorText.setTextColor(Color.RED);
                        errorText.setText("Enter Data");
                    } else if (cs_Hitch.equals("Hitch")) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        TextView errorText = (TextView) spn_CustomerSkim_Hitch.getSelectedView();
                        errorText.setError("Enter Data");
                        errorText.setTextColor(Color.RED);
                        errorText.setText("Enter Data");
                    } else {
                        check2 = true;
                    }

                } else if (check1){
                    check2 = true;
                }
                if (referral_Skim1.equals("Yes") && check1 && check2) {
                    check3 = false;
                    if (edt_referral_referral_name.getText().length() == 0) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        edt_referral_referral_name.setError("Enter Data");
                    } else if (edt_referral_mobileno.getText().length() == 0) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        edt_referral_mobileno.setError("Enter Data");
                    } else if (edt_referral_Description.getText().length() == 0) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        edt_referral_Description.setError("Enter Data");
                    } else if (edt_referral_Amount.getText().length() == 0) {
                        Toast.makeText(CustomerSkimActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                        edt_referral_Amount.setError("Enter Data");
                    } else {
                        check3 = true;
                    }
                } else if (check1 && check2){
                    check3 = true;
                }

                if (check1 && check2 && check3) {
                    pro.show();
                    check1 = false;
                    check2 = false;
                    check3 = false;

                    WebService.getClient().getConsumerSkimSubmit(
                            consumer_Skim1,
                            consumer_Skim,
                            cs_Hood,
                            cs_Toplink,
                            cs_DrawBar,
                            cs_ToolKit,
                            cs_Bumper,
                            cs_Hitch,
                            edt_CustomerSkim_Description.getText().toString(),
                            "6",
                            NextIDD,
                            edt_CustomerSkim_Amount.getText().toString(),
                            referral_Skim1,
                            edt_referral_referral_name.getText().toString(),
                            edt_referral_mobileno.getText().toString(),
                            edt_referral_Description.getText().toString(),
                            edt_referral_Amount.getText().toString()
                    ).enqueue(new Callback<ConsumerSkimSubmitModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ConsumerSkimSubmitModel> call, Response<ConsumerSkimSubmitModel> response) {

                            pro.dismiss();


                            sharedPreferences = getSharedPreferences("NextId1", MODE_PRIVATE);
                            sharedPreferences.edit().putString("phase", "6").apply();

                            Toast.makeText(CustomerSkimActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "onFailure: checkdata fail " + response.body().getMsg());

                            Intent i = new Intent(CustomerSkimActivity.this, BookingDeliveryMainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(Call<ConsumerSkimSubmitModel> call, Throwable t) {
                            pro.dismiss();
                            Log.d("TAG", "onFailure: checkdata fail");
                        }
                    });
                } else {
                    Toast.makeText(CustomerSkimActivity.this, "Check Your Data Again", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}