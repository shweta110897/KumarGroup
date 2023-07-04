package com.example.kumarGroup.BookingDeliveryUpload.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.PriceDetailNextModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.ViewInquiryDealStage.Add.EnglishNumberToWordsDealstage;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceDetailsAddActivity extends AppCompatActivity {


    EditText edt_AddPriceD_Price, edt_AddPriceD_PriceInWord;

    Spinner spn_AddPriceD_GST;

    Button btn_AddPriceDetails_Next;

    String gstSpn;
    String[] Gst_Spn = {"With GST","Without GST"};

    int number;

    ProgressDialog progressDialog;

    SharedPreferences sp1;
    String emp;

    String NextId;

    SharedPreferences sharedPreferences;
    String NextIDD;

    String Product_Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_details_add);
        getSupportActionBar().setTitle("Price Details");

        edt_AddPriceD_Price=findViewById(R.id.edt_AddPriceD_Price);
        edt_AddPriceD_PriceInWord=findViewById(R.id.edt_AddPriceD_PriceInWord);

        spn_AddPriceD_GST=findViewById(R.id.spn_AddPriceD_GST);

        btn_AddPriceDetails_Next=findViewById(R.id.btn_AddPriceDetails_Next);


        NextId = getIntent().getStringExtra("NextId");

        Product_Name = getIntent().getStringExtra("Product_Name");

        sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
        NextIDD = sharedPreferences.getString("NextId","");

        edt_AddPriceD_Price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (String.valueOf(s).equals("")){
                    number =0;
                    edt_AddPriceD_PriceInWord.setText("ZERO");
                }else if(edt_AddPriceD_Price.getText().toString().equals("")){
                    number =0;
                    edt_AddPriceD_PriceInWord.setText("ZERO");
                }else{
                    number = Integer.parseInt(edt_AddPriceD_Price.getText().toString());

                    String return_val_in_english =   EnglishNumberToWords.convert(number);

                    String numToWord = NumToWord.GFG.convertToWords(number);

                    // edt_AddPriceD_PriceInWord.setText(return_val_in_english);
                    edt_AddPriceD_PriceInWord.setText(numToWord);
                }
               /* number = Integer.parseInt(edt_AddPriceD_Price.getText().toString());

                String return_val_in_english =   EnglishNumberToWords.convert(number);

                String numToWord = NumToWord.GFG.convertToWords(number);

                // edt_AddPriceD_PriceInWord.setText(return_val_in_english);
                edt_AddPriceD_PriceInWord.setText(numToWord);*/
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (String.valueOf(s).equals("")){
                    number =0;
                    edt_AddPriceD_PriceInWord.setText("ZERO");
                }else if(edt_AddPriceD_Price.getText().toString().equals("")){
                    number =0;
                    edt_AddPriceD_PriceInWord.setText("ZERO");
                }else{
                    number = Integer.parseInt(edt_AddPriceD_Price.getText().toString());

                    String return_val_in_english =   EnglishNumberToWordsDealstage.convert(number);

                    String numToWord = NumToWord.GFG.convertToWords(number);

                    // edt_AddPriceD_PriceInWord.setText(return_val_in_english);
                    edt_AddPriceD_PriceInWord.setText(numToWord);
                }
              /*  number = Integer.parseInt(edt_AddPriceD_Price.getText().toString());

                String return_val_in_english =   EnglishNumberToWords.convert(number);

                String numToWord = NumToWord.GFG.convertToWords(number);

               // edt_AddPriceD_PriceInWord.setText(return_val_in_english);
                edt_AddPriceD_PriceInWord.setText(numToWord);*/
            }
        });

        /* number = Integer.parseInt(edt_AddPriceD_Price.getText().toString());

        String return_val_in_english =   EnglishNumberToWords.convert(number);

        edt_AddPriceD_PriceInWord.setText(return_val_in_english);*/

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Gst_Spn);
        spn_AddPriceD_GST.setAdapter(adapter);

        spn_AddPriceD_GST.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gstSpn = Gst_Spn[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_AddPriceDetails_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isvalidate1()) {

                    progressDialog = new ProgressDialog(PriceDetailsAddActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    WebService.getClient().getPriceDetailNext(edt_AddPriceD_Price.getText().toString(),
                            edt_AddPriceD_PriceInWord.getText().toString(),
                            gstSpn,
                            "2",
                            NextIDD
                    ).enqueue(new Callback<PriceDetailNextModel>() {
                        @Override
                        public void onResponse(Call<PriceDetailNextModel> call, Response<PriceDetailNextModel> response) {
                            progressDialog.dismiss();

                            Toast.makeText(PriceDetailsAddActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            // Intent i = new Intent(PriceDetailsAddActivity.this, RtoDetailsActivity.class);
                            Intent i = new Intent(PriceDetailsAddActivity.this, AddCustomerDetailsActivity.class);
                            //  i.putExtra("Product_Name",Product_Name);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<PriceDetailNextModel> call, Throwable t) {
                            progressDialog.dismiss();
                        }
                    });

                }

            }
        });

    }

    private boolean isvalidate1() {
        if (edt_AddPriceD_Price.getText().toString().equals("")){
            Utils.showErrorToast(PriceDetailsAddActivity.this, "Please enter price");
            return false;
        }else if(edt_AddPriceD_PriceInWord.getText().toString().equals("")){
            Utils.showErrorToast(PriceDetailsAddActivity.this, "Please enter price");
            return false;
        }else{
            return true;
        }
    }
}