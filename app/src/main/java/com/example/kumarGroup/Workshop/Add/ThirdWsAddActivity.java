package com.example.kumarGroup.Workshop.Add;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ThirdWSModel;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdWsAddActivity extends AppCompatActivity {

    Button Btn_Ws_Add_Next,Btn_Ws_ThirdWS_Cancel;
    EditText edt_ThirdWs_DealPrice
            ,edt_ThirdWs_DealPriWord ;
    Spinner spn_ThirdWs_GST;

    String gst;
    String[] Gst_list = {"With GST","Without GST"};

    int number;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    String NextIDD_WS;

    Integer sumOfTotalPrice;

    Integer costPetrol, costLabor;

    Integer dealPrice;

    Integer PricePetrol;
    Integer PriceLabor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_ws_add);


        getSupportActionBar().setTitle("Workshop Add");

        sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
        NextIDD_WS = sharedPreferences.getString("NextId_WS","");


        sumOfTotalPrice = sharedPreferences.getInt("sumOfTotalPrice",0);

        sharedPreferences = getSharedPreferences("Price", MODE_PRIVATE);
        PricePetrol = sharedPreferences.getInt("PricePetrol",0);
        PriceLabor = sharedPreferences.getInt("PriceLabor",0);


       /* costPetrol = Integer.parseInt(PricePetrol);
        costLabor = Integer.parseInt(PriceLabor);*/


        edt_ThirdWs_DealPrice=findViewById(R.id.edt_ThirdWs_DealPrice);

        edt_ThirdWs_DealPriWord=findViewById(R.id.edt_ThirdWs_DealPriWord);

        spn_ThirdWs_GST=findViewById(R.id.spn_ThirdWs_GST);

        Btn_Ws_Add_Next=findViewById(R.id.Btn_Ws_Add_Next);
        Btn_Ws_ThirdWS_Cancel=findViewById(R.id.Btn_Ws_ThirdWS_Cancel);


        Log.d("TAG", "onCreate: sumOfTotalPrice"+sumOfTotalPrice);
        Log.d("TAG", "onCreate: sumOfTotalPrice"+"PricePetrol"+PricePetrol);
        Log.d("TAG", "onCreate: sumOfTotalPrice"+"PriceLabor"+PriceLabor);
        dealPrice = sumOfTotalPrice + PricePetrol + PriceLabor;
        edt_ThirdWs_DealPrice.setText(String.valueOf(dealPrice));


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Gst_list);
        spn_ThirdWs_GST.setAdapter(adapter);

        spn_ThirdWs_GST.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gst = Gst_list[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        number = Integer.parseInt(edt_ThirdWs_DealPrice.getText().toString());

        String numToWord = NumToWordWs.GFG.convertToWords(number);

        edt_ThirdWs_DealPriWord.setText(numToWord);


        edt_ThirdWs_DealPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                number = Integer.parseInt(edt_ThirdWs_DealPrice.getText().toString());

                String numToWord = NumToWordWs.GFG.convertToWords(number);

                edt_ThirdWs_DealPriWord.setText(numToWord);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number = Integer.parseInt(edt_ThirdWs_DealPrice.getText().toString());

                String numToWord = NumToWordWs.GFG.convertToWords(number);

                edt_ThirdWs_DealPriWord.setText(numToWord);
            }

            @Override
            public void afterTextChanged(Editable s) {
                number = Integer.parseInt(edt_ThirdWs_DealPrice.getText().toString());

                String numToWord = NumToWordWs.GFG.convertToWords(number);

                edt_ThirdWs_DealPriWord.setText(numToWord);
            }
        });



        Btn_Ws_Add_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_ThirdWs_DealPrice.getText().toString().equals("")){
                    edt_ThirdWs_DealPrice.setError("Please Enter Deal Price");
                }

                if(edt_ThirdWs_DealPriWord.getText().toString().equals("")){
                    edt_ThirdWs_DealPriWord.setError("Please Enter Deal PriceWord");
                }


                progressDialog = new ProgressDialog(ThirdWsAddActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                if (!edt_ThirdWs_DealPrice.getText().toString().equals("") &&
                !edt_ThirdWs_DealPriWord.getText().toString().equals("")) {

                    WebService.getClient().getThirdWs(NextIDD_WS,
                            edt_ThirdWs_DealPrice.getText().toString(),
                            edt_ThirdWs_DealPriWord.getText().toString(),
                            gst,
                            "3"
                    ).enqueue(new Callback<ThirdWSModel>() {
                        @Override
                        public void onResponse(@NotNull Call<ThirdWSModel> call, @NotNull Response<ThirdWSModel> response) {

                            progressDialog.dismiss();

                            sharedPreferences = getSharedPreferences("DealPrice", MODE_PRIVATE);
                            sharedPreferences.edit().putInt("DealPrice", Integer.parseInt(edt_ThirdWs_DealPrice.getText().toString())).apply();


                            sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                            sharedPreferences.edit().putInt("sumOfTotalPrice", 0).apply();

                            Intent i = new Intent(ThirdWsAddActivity.this, FourthWsAddMainActivity.class);
                            //Intent i = new Intent(ThirdWsAddActivity.this, FoDashbord.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<ThirdWSModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });


        Btn_Ws_ThirdWS_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                sharedPreferences.edit().putInt("sumOfTotalPrice", 0).apply();
                Intent i = new Intent(ThirdWsAddActivity.this, FoDashbord.class);
                sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                sharedPreferences.edit().putString("NextId_WS", "").apply();
                startActivity(i);
            }
        });
    }
}