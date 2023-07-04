package com.example.kumarGroup.Workshop.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.FourthWSModel;
import com.example.kumarGroup.MobileNumDetailModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Workshop.WorkshopMainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FourthWsAddMainActivity extends AppCompatActivity {

    Spinner spn_FourthWs_Payment;

    //  EditText edt_FourthWs_AmountCash,edt_FourthWs_AmountCashDebit;

    EditText edt_FourthWs_PickDate, edt_FourthWs_MobileNo, edt_FourthWs_Fname, edt_FourthWs_LastName,
            edt_FourthWs_WhatsappNo, edt_FourthWs_State, edt_FourthWs_City, edt_FourthWs_District,
            edt_FourthWs_Taluko, edt_FourthWs_Village, edt_FourthWs_Description;

    Button Btn_FourthWs_Next, Btn_Ws_ForthWS_Cancel;

    String PaymentOpt;
    String[] Payment_Opt = {"Select Payment Type", "Cash", "Bank", "Debit"};

    String[] Payment_Opt1 = {"Select Payment Type", "Company Claim", "Dealer Claim"};

    String[] Payment_Opt_Bank = {"Select Payment Type", "Cheque", "NEFT/RTGS"};
    String Bank_opt;

    ProgressDialog progressDialog;

    Calendar mcurrentdate;
    int day, month, year;

    String emp_id_ws, nextId;
    String MobileNo_WS;
    String EMPName_Ws;

    SharedPreferences sharedPreferences;
    String NextIDD_WS;
    String Sale_list;

    String nextId_Ws;
    Integer DealPrice;


    LinearLayout lin_Cash, lin_BankCheque, lin_BankRTGS_NEFT;

    EditText edt_FourthWs_CashDate, edt_FourthWs_AmountCash, edt_FourthWs_CashDescription;

    EditText edt_FourthWs_ChequeDate, edt_FourthWs_ChequeCash, edt_FourthWs_ChequeDescription;

    EditText edt_FourthWs_NEFT_RTGSDate, edt_FourthWs_NEFT_RTGSCash, edt_FourthWs_NEFT_RTGSDescription;


    Spinner spn_FourthWs_Bank;

    Integer CashMinus, ChequeMinus, NeftRtgsMinus;

    String left_amt, left_status;

    int cashVal, chequeVal, neftRtgsVal;

    //Warranty Sale

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_ws_add_main);

        getSupportActionBar().setTitle("Workshop Add");

        sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
        NextIDD_WS = sharedPreferences.getString("NextId_WS", "");

      //  Toast.makeText(this, ""+NextIDD_WS, Toast.LENGTH_SHORT).show();


        Sale_list = sharedPreferences.getString("Sale_list", "");


        sharedPreferences = getSharedPreferences("DealPrice", MODE_PRIVATE);
        DealPrice = sharedPreferences.getInt("DealPrice", 0);

        //   Toast.makeText(this, ""+DealPrice, Toast.LENGTH_SHORT).show();

        spn_FourthWs_Payment = findViewById(R.id.spn_FourthWs_Payment);

        edt_FourthWs_AmountCash = findViewById(R.id.edt_FourthWs_AmountCash);
        // edt_FourthWs_AmountCashDebit=findViewById(R.id.edt_FourthWs_AmountCashDebit);


        edt_FourthWs_PickDate = findViewById(R.id.edt_FourthWs_PickDate);
        edt_FourthWs_MobileNo = findViewById(R.id.edt_FourthWs_MobileNo);
        edt_FourthWs_Fname = findViewById(R.id.edt_FourthWs_Fname);
        edt_FourthWs_LastName = findViewById(R.id.edt_FourthWs_LastName);
        edt_FourthWs_WhatsappNo = findViewById(R.id.edt_FourthWs_WhatsappNo);
        edt_FourthWs_State = findViewById(R.id.edt_FourthWs_State);
        edt_FourthWs_City = findViewById(R.id.edt_FourthWs_City);
        edt_FourthWs_District = findViewById(R.id.edt_FourthWs_District);
        edt_FourthWs_Taluko = findViewById(R.id.edt_FourthWs_Taluko);
        edt_FourthWs_Village = findViewById(R.id.edt_FourthWs_Village);
        edt_FourthWs_Description = findViewById(R.id.edt_FourthWs_Description);

        lin_Cash = findViewById(R.id.lin_Cash);
        lin_BankCheque = findViewById(R.id.lin_BankCheque);
        lin_BankRTGS_NEFT = findViewById(R.id.lin_BankRTGS_NEFT);
        spn_FourthWs_Bank = findViewById(R.id.spn_FourthWs_Bank);

        edt_FourthWs_CashDate = findViewById(R.id.edt_FourthWs_CashDate);
        edt_FourthWs_CashDescription = findViewById(R.id.edt_FourthWs_CashDescription);

        edt_FourthWs_ChequeDate = findViewById(R.id.edt_FourthWs_ChequeDate);
        edt_FourthWs_ChequeCash = findViewById(R.id.edt_FourthWs_ChequeCash);
        edt_FourthWs_ChequeDescription = findViewById(R.id.edt_FourthWs_ChequeDescription);

        edt_FourthWs_NEFT_RTGSDate = findViewById(R.id.edt_FourthWs_NEFT_RTGSDate);
        edt_FourthWs_NEFT_RTGSCash = findViewById(R.id.edt_FourthWs_NEFT_RTGSCash);
        edt_FourthWs_NEFT_RTGSDescription = findViewById(R.id.edt_FourthWs_NEFT_RTGSDescription);

        Btn_FourthWs_Next = findViewById(R.id.Btn_FourthWs_Next);
        Btn_Ws_ForthWS_Cancel = findViewById(R.id.Btn_Ws_ForthWS_Cancel);


        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);
        // month = month+1;


        edt_FourthWs_PickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FourthWsAddMainActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                        String mt, dy;   //local variable
                        if (monthofYear < 10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        } else {
                            mt = String.valueOf(monthofYear);
                        }

                        if (dayOfMonth < 10) {
                            dy = "0" + dayOfMonth;
                        } else {
                            dy = String.valueOf(dayOfMonth);
                        }
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        // edt_FourthWs_PickDate.setText(mt + "/" + dy + "/" + year);2021-04-01

                        edt_FourthWs_PickDate.setText(year + "-" + mt + "-" + dy);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_FourthWs_PickDate.setFocusable(false);

        //----------------------------------------------------------------------------
        edt_FourthWs_ChequeDate.setFocusable(false);

        edt_FourthWs_ChequeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FourthWsAddMainActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                        String mt, dy;   //local variable
                        if (monthofYear < 10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        } else {
                            mt = String.valueOf(monthofYear);
                        }

                        if (dayOfMonth < 10) {
                            dy = "0" + dayOfMonth;
                        } else {
                            dy = String.valueOf(dayOfMonth);
                        }
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        edt_FourthWs_ChequeDate.setText(mt + "/" + dy + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_FourthWs_NEFT_RTGSDate.setFocusable(false);

        edt_FourthWs_NEFT_RTGSDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FourthWsAddMainActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                        String mt, dy;   //local variable
                        if (monthofYear < 10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        } else {
                            mt = String.valueOf(monthofYear);
                        }

                        if (dayOfMonth < 10) {
                            dy = "0" + dayOfMonth;
                        } else {
                            dy = String.valueOf(dayOfMonth);
                        }
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        edt_FourthWs_NEFT_RTGSDate.setText(mt + "/" + dy + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        edt_FourthWs_CashDate.setFocusable(false);

        edt_FourthWs_CashDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FourthWsAddMainActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                        String mt, dy;   //local variable
                        if (monthofYear < 10) {
                            mt = "0" + monthofYear; //if month less than 10 then ad 0 before month
                        } else {
                            mt = String.valueOf(monthofYear);
                        }

                        if (dayOfMonth < 10) {
                            dy = "0" + dayOfMonth;
                        } else {
                            dy = String.valueOf(dayOfMonth);
                        }
                        // EdtWalletSalarySlipDateOne.setText(dy+"/"+mt+"/"+year);
                        edt_FourthWs_CashDate.setText(mt + "/" + dy + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        //---------------------------------------------------------------------------------------------

        edt_FourthWs_MobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(edt_FourthWs_MobileNo.getText().toString().length()==10){

                    WebService.getClient().getDetailsOnMobileNo(edt_FourthWs_MobileNo.getText().toString()).enqueue(new Callback<MobileNumDetailModel>() {
                        @Override
                        public void onResponse(@NotNull Call<MobileNumDetailModel> call, @NotNull Response<MobileNumDetailModel> response) {
                            // progressDialog.dismiss();
                            assert response.body() != null;

                            try {
                                Log.d("1212", "onResponse: " + response.body().getCat().size() + MobileNo_WS);


                                edt_FourthWs_Fname.setText(response.body().getCat().get(0).getFname());
                                edt_FourthWs_LastName.setText(response.body().getCat().get(0).getLname());
                                /*  edt_AddCD_MobileNo.setText(response.body().getCat().get(0).getMoblino());*/
                                edt_FourthWs_WhatsappNo.setText(response.body().getCat().get(0).getWhatsappno());
                                edt_FourthWs_State.setText(response.body().getCat().get(0).getState());
                                edt_FourthWs_City.setText(response.body().getCat().get(0).getCity());
                                edt_FourthWs_District.setText(response.body().getCat().get(0).getDistric());
                                edt_FourthWs_Taluko.setText(response.body().getCat().get(0).getTehsil());
                                edt_FourthWs_Village.setText(response.body().getCat().get(0).getVilage());
                                edt_FourthWs_Description.setText(response.body().getCat().get(0).getNote());

                                emp_id_ws = response.body().getCat().get(0).getEmployee_name();

                                EMPName_Ws = response.body().getCat().get(0).getEid();
                            } catch (Exception e) {
                                e.printStackTrace();

                                //  Toast.makeText(FourthWsAddMainActivity.this, "catch", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<MobileNumDetailModel> call, @NotNull Throwable t) {
                            Toast.makeText(FourthWsAddMainActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                }


               /* WebService.getClient().getDetailsOnMobileNo(edt_FourthWs_MobileNo.getText().toString()).enqueue(new Callback<MobileNumDetailModel>() {
                    @Override
                    public void onResponse(@NotNull Call<MobileNumDetailModel> call, @NotNull Response<MobileNumDetailModel> response) {
                        // progressDialog.dismiss();
                        assert response.body() != null;

                        try {
                            Log.d("1212", "onResponse: " + response.body().getCat().size() + MobileNo_WS);


                            edt_FourthWs_Fname.setText(response.body().getCat().get(0).getFname());
                            edt_FourthWs_LastName.setText(response.body().getCat().get(0).getLname());
                            *//*  edt_AddCD_MobileNo.setText(response.body().getCat().get(0).getMoblino());*//*
                            edt_FourthWs_WhatsappNo.setText(response.body().getCat().get(0).getWhatsappno());
                            edt_FourthWs_State.setText(response.body().getCat().get(0).getState());
                            edt_FourthWs_City.setText(response.body().getCat().get(0).getCity());
                            edt_FourthWs_District.setText(response.body().getCat().get(0).getDistric());
                            edt_FourthWs_Taluko.setText(response.body().getCat().get(0).getTehsil());
                            edt_FourthWs_Village.setText(response.body().getCat().get(0).getVilage());
                            edt_FourthWs_Description.setText(response.body().getCat().get(0).getNote());

                            emp_id_ws = response.body().getCat().get(0).getEmployee_name();

                            EMPName_Ws = response.body().getCat().get(0).getEid();
                        } catch (Exception e) {
                            e.printStackTrace();

                          //  Toast.makeText(FourthWsAddMainActivity.this, "catch", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<MobileNumDetailModel> call, @NotNull Throwable t) {
                        Toast.makeText(FourthWsAddMainActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
                    }
                });*/

            }
        });


        if (Sale_list.equals("Warranty Sale")) {

            lin_Cash.setVisibility(View.GONE);
            lin_BankCheque.setVisibility(View.GONE);
            lin_BankRTGS_NEFT.setVisibility(View.GONE);

            spn_FourthWs_Bank.setVisibility(View.GONE);


            ArrayAdapter adapterBankPassBook = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, Payment_Opt1);
            spn_FourthWs_Payment.setAdapter(adapterBankPassBook);

            spn_FourthWs_Payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    PaymentOpt = Payment_Opt1[position];

                    if (Payment_Opt.equals("Select Payment Type")) {
                        PaymentOpt = "";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            ArrayAdapter adapterBankPassBook = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Payment_Opt);
            spn_FourthWs_Payment.setAdapter(adapterBankPassBook);

            spn_FourthWs_Payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    PaymentOpt = Payment_Opt[position];


                    //CashMinus, ChequeMinus, NeftRtgsMinus;

                    if (PaymentOpt.equals("Select Payment Type")) {
                        lin_Cash.setVisibility(View.GONE);
                        lin_BankCheque.setVisibility(View.GONE);
                        lin_BankRTGS_NEFT.setVisibility(View.GONE);
                        spn_FourthWs_Bank.setVisibility(View.GONE);


                    }

                    if (PaymentOpt.equals("Cash")) {
                        lin_Cash.setVisibility(View.VISIBLE);
                        lin_BankCheque.setVisibility(View.GONE);
                        lin_BankRTGS_NEFT.setVisibility(View.GONE);

                        spn_FourthWs_Bank.setVisibility(View.GONE);

                    }

                    if (PaymentOpt.equals("Bank")) {
                        lin_Cash.setVisibility(View.GONE);
                       /* lin_BankCheque.setVisibility(View.GONE);
                        lin_BankRTGS_NEFT.setVisibility(View.GONE);*/
                        spn_FourthWs_Bank.setVisibility(View.VISIBLE);


                        ArrayAdapter adapterBankPassBook = new ArrayAdapter(FourthWsAddMainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, Payment_Opt_Bank);
                        spn_FourthWs_Bank.setAdapter(adapterBankPassBook);

                        spn_FourthWs_Bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                Bank_opt = Payment_Opt_Bank[position];


                                if (Bank_opt.equals("Select Payment Type")) {
                                    lin_Cash.setVisibility(View.GONE);
                                    lin_BankCheque.setVisibility(View.GONE);
                                    lin_BankRTGS_NEFT.setVisibility(View.GONE);
                                }

                                if (Bank_opt.equals("Cheque")) {
                                    lin_Cash.setVisibility(View.GONE);
                                    lin_BankCheque.setVisibility(View.VISIBLE);
                                    lin_BankRTGS_NEFT.setVisibility(View.GONE);
                                }

                                if (Bank_opt.equals("NEFT/RTGS")) {
                                    lin_Cash.setVisibility(View.GONE);
                                    lin_BankCheque.setVisibility(View.GONE);
                                    lin_BankRTGS_NEFT.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }


                    if (PaymentOpt.equals("Debit")) {
                        left_status = "1";
                        left_amt = String.valueOf(DealPrice);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


        edt_FourthWs_AmountCash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // cashVal = Integer.parseInt(edt_FourthWs_AmountCash.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cashVal = Integer.parseInt(edt_FourthWs_AmountCash.getText().toString());

                CashMinus = DealPrice - cashVal;

                //  Toast.makeText(FourthWsAddMainActivity.this, ""+CashMinus, Toast.LENGTH_SHORT).show();

              /*  if(CashMinus>0){
                    left_amt = String.valueOf(CashMinus);
                    left_status = "1";

                    Toast.makeText(FourthWsAddMainActivity.this, "Minus Value"+left_amt, Toast.LENGTH_SHORT).show();

                }

                if(CashMinus< 0|| CashMinus==0) {
                    left_amt = String.valueOf(CashMinus);
                    left_status = "0";

                    Toast.makeText(FourthWsAddMainActivity.this, "Value"+left_amt, Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                cashVal = Integer.parseInt(edt_FourthWs_AmountCash.getText().toString());

                CashMinus = DealPrice - cashVal;

                left_amt = String.valueOf(CashMinus);

                //  Toast.makeText(FourthWsAddMainActivity.this, ""+CashMinus, Toast.LENGTH_SHORT).show();

                if (CashMinus > 0) {

                    left_status = "1";
                }

                if (CashMinus < 0 || CashMinus == 0) {
                    //  left_amt = String.valueOf(CashMinus);
                    left_status = "0";

                    //  Toast.makeText(FourthWsAddMainActivity.this, "Value"+left_amt, Toast.LENGTH_SHORT).show();

                }
            }
        });

        //****************************************************************
        edt_FourthWs_ChequeCash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // cashVal = Integer.parseInt(edt_FourthWs_AmountCash.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                chequeVal = Integer.parseInt(edt_FourthWs_ChequeCash.getText().toString());

                ChequeMinus = DealPrice - chequeVal;

            }

            @Override
            public void afterTextChanged(Editable s) {
                chequeVal = Integer.parseInt(edt_FourthWs_ChequeCash.getText().toString());

                ChequeMinus = DealPrice - chequeVal;

                left_amt = String.valueOf(ChequeMinus);


                if (ChequeMinus > 0) {

                    left_status = "1";
                }

                if (ChequeMinus < 0 || ChequeMinus == 0) {
                    //  left_amt = String.valueOf(CashMinus);
                    left_status = "0";

                    //  Toast.makeText(FourthWsAddMainActivity.this, "Value"+left_amt, Toast.LENGTH_SHORT).show();

                }
            }
        });


        edt_FourthWs_NEFT_RTGSCash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                neftRtgsVal = Integer.parseInt(edt_FourthWs_NEFT_RTGSCash.getText().toString());

                NeftRtgsMinus = DealPrice - neftRtgsVal;

                // Toast.makeText(FourthWsAddMainActivity.this, ""+CashMinus, Toast.LENGTH_SHORT).show();

              /*  if(ChequeMinus>0){
                    left_amt = String.valueOf(ChequeMinus);
                    left_status = "1";

                    Toast.makeText(FourthWsAddMainActivity.this, "Minus Value"+left_amt, Toast.LENGTH_SHORT).show();

                }

                if(ChequeMinus< 0|| ChequeMinus==0) {
                    left_amt = String.valueOf(ChequeMinus);
                    left_status = "0";

                    Toast.makeText(FourthWsAddMainActivity.this, "Value"+left_amt, Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                chequeVal = Integer.parseInt(edt_FourthWs_NEFT_RTGSCash.getText().toString());

                NeftRtgsMinus = DealPrice - neftRtgsVal;

                left_amt = String.valueOf(NeftRtgsMinus);

                //  Toast.makeText(FourthWsAddMainActivity.this, ""+CashMinus, Toast.LENGTH_SHORT).show();

                if (NeftRtgsMinus > 0) {
                    left_status = "1";
                    //  Toast.makeText(FourthWsAddMainActivity.this, "Minus Value"+left_status, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("ChequeValBig", "afterTextChanged: " + NeftRtgsMinus);
                }

                if (NeftRtgsMinus < 0 || NeftRtgsMinus == 0) {
                    //   left_amt = String.valueOf(NeftRtgsMinus);
                    left_status = "0";
                    //  Toast.makeText(FourthWsAddMainActivity.this, "Value"+left_amt, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("ChequeValSmall", "afterTextChanged: " + NeftRtgsMinus);
                }
            }
        });


        Btn_FourthWs_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Bank_opt == null) {
                    Bank_opt = " ";
                }


                progressDialog = new ProgressDialog(FourthWsAddMainActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                if (edt_FourthWs_PickDate.getText().toString().equals("")) {
                    edt_FourthWs_PickDate.setError("Enter Date");
                }

                if (edt_FourthWs_MobileNo.getText().toString().equals("")) {
                    edt_FourthWs_MobileNo.setError("Enter Mobile");
                }

                if (edt_FourthWs_Fname.getText().toString().equals("")) {
                    edt_FourthWs_Fname.setError("Enter First Name");
                }

                if (edt_FourthWs_LastName.getText().toString().equals("")) {
                    edt_FourthWs_LastName.setError("Enter Last Name");
                }

                if (edt_FourthWs_WhatsappNo.getText().toString().equals("")) {
                    edt_FourthWs_WhatsappNo.setError("Enter WHats App No");
                }

                if (edt_FourthWs_State.getText().toString().equals("")) {
                    edt_FourthWs_State.setError("Enter State");
                }

                if (edt_FourthWs_City.getText().toString().equals("")) {
                    edt_FourthWs_City.setError("Enter City");
                }

                if (edt_FourthWs_District.getText().toString().equals("")) {
                    edt_FourthWs_District.setError("Enter Date");
                }

                if (edt_FourthWs_City.getText().toString().equals("")) {
                    edt_FourthWs_City.setError("Enter Date");
                }

                if (edt_FourthWs_Taluko.getText().toString().equals("")) {
                    edt_FourthWs_Taluko.setError("Enter Taluko");
                }

                if (edt_FourthWs_Village.getText().toString().equals("")) {
                    edt_FourthWs_Village.setError("Enter Village");
                }


                if (PaymentOpt.equals("Select Payment Type")) {
                    Utils.showErrorToast(FourthWsAddMainActivity.this, "Please select Type");
                }


                if (PaymentOpt.equals("Cash")) {
                    if (edt_FourthWs_CashDate.getText().toString().equals("")) {
                        edt_FourthWs_CashDate.setError("Enter Date");
                    }

                    if (edt_FourthWs_AmountCash.getText().toString().equals("")) {
                        edt_FourthWs_AmountCash.setError("Enter Cash Amount");
                    }

                    if (edt_FourthWs_CashDescription.getText().toString().equals("")) {
                        edt_FourthWs_CashDescription.setError("Enter Cash Description");
                    }

                }

                if (PaymentOpt.equals("Bank")) {
                    if (Bank_opt.equals("Cheque")) {


                        if (edt_FourthWs_ChequeDate.getText().toString().equals("")) {
                            edt_FourthWs_ChequeDate.setError("Enter Cheque Date");
                        }

                        if (edt_FourthWs_ChequeCash.getText().toString().equals("")) {
                            edt_FourthWs_ChequeCash.setError("Enter Cheque Amount");
                        }

                        if (edt_FourthWs_ChequeDescription.getText().toString().equals("")) {
                            edt_FourthWs_ChequeDescription.setError("Enter Cheque Description");
                        }
                    }

                    if (Bank_opt.equals("NEFT/RTGS")) {
                        if (edt_FourthWs_ChequeDate.getText().toString().equals("")) {
                            edt_FourthWs_ChequeDate.setError("Enter NEFT/RTGS Date");
                        }

                        if (edt_FourthWs_ChequeCash.getText().toString().equals("")) {
                            edt_FourthWs_ChequeCash.setError("Enter NEFT/RTGS Amount");
                        }

                        if (edt_FourthWs_ChequeDescription.getText().toString().equals("")) {
                            edt_FourthWs_ChequeDescription.setError("Enter NEFT/RTGS Description");
                        }
                    }

                }


                if (!edt_FourthWs_PickDate.getText().toString().equals("") &&
                        !edt_FourthWs_MobileNo.getText().toString().equals("") &&
                        !PaymentOpt.equals("Select Payment Type")) {


                    WebService.getClient().getFourthPhaseWs(NextIDD_WS,
                            edt_FourthWs_PickDate.getText().toString(),
                            edt_FourthWs_Fname.getText().toString() + " " + edt_FourthWs_LastName.getText().toString(),
                            edt_FourthWs_MobileNo.getText().toString(),
                            edt_FourthWs_WhatsappNo.getText().toString(),
                            edt_FourthWs_State.getText().toString(),
                            edt_FourthWs_City.getText().toString(),
                            edt_FourthWs_District.getText().toString(),
                            edt_FourthWs_Taluko.getText().toString(),
                            edt_FourthWs_Village.getText().toString(),
                            emp_id_ws,
                            PaymentOpt,
                            edt_FourthWs_CashDate.getText().toString(),
                            edt_FourthWs_AmountCash.getText().toString(),
                            edt_FourthWs_CashDescription.getText().toString(),
                            Bank_opt,
                            edt_FourthWs_ChequeDate.getText().toString(),
                            edt_FourthWs_ChequeCash.getText().toString(),
                            edt_FourthWs_ChequeDescription.getText().toString(),
                            edt_FourthWs_NEFT_RTGSDate.getText().toString(),
                            edt_FourthWs_NEFT_RTGSCash.getText().toString(),
                            edt_FourthWs_NEFT_RTGSDescription.getText().toString(),
                            left_amt,
                            left_status,
                            EMPName_Ws,
                            "4"
                    ).enqueue(new Callback<FourthWSModel>() {
                        @Override
                        public void onResponse(@NotNull Call<FourthWSModel> call, @NotNull Response<FourthWSModel> response) {
                            progressDialog.dismiss();

                            Toast.makeText(FourthWsAddMainActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                          //  nextId_Ws = String.valueOf(response.body().getId());

                            /*sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                            sharedPreferences.edit().putString("NextId_WS", String.valueOf(response.body().getId())).apply();*/


                            Intent i = new Intent(FourthWsAddMainActivity.this, WorkshopMainActivity.class);
                           // Intent i = new Intent(FourthWsAddMainActivity.this, AddFirstActivity.class);

                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<FourthWSModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(FourthWsAddMainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        Btn_Ws_ForthWS_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FourthWsAddMainActivity.this, FoDashbord.class);
                sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                sharedPreferences.edit().putString("NextId_WS", "").apply();
                startActivity(i);
            }
        });

    }
}