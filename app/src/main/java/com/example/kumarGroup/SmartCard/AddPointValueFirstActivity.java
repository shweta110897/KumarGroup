package com.example.kumarGroup.SmartCard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.FirstAddSmartCardModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPointValueFirstActivity extends AppCompatActivity {

    Spinner spn_SelctMob_smart;

    EditText edt_Mobile_no,edt_SmartCard_no;

    String SelectType;
    String[] Select_Type = {"Select Type", "Mobile No", "Smart Card"};

    Button btn_Submit_Form;

    ProgressDialog progressDialog;

    String smo_mno;


    SharedPreferences preferences;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point_value_first);

        getSupportActionBar().setTitle("Add Point Value");


        spn_SelctMob_smart=findViewById(R.id.spn_SelctMob_smart);
        edt_Mobile_no=findViewById(R.id.edt_Mobile_no);
        edt_SmartCard_no=findViewById(R.id.edt_SmartCard_no);
        btn_Submit_Form=findViewById(R.id.btn_Submit_Form);


        ArrayAdapter selectMobSmart = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Select_Type);
        spn_SelctMob_smart.setAdapter(selectMobSmart);

        spn_SelctMob_smart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectType = Select_Type[position];
                if (SelectType.equals("Mobile No")) {
                    edt_Mobile_no.setVisibility(View.VISIBLE);
                    edt_SmartCard_no.setVisibility(View.GONE);

                   // smo_mno = edt_Mobile_no.getText().toString();

                } else if(SelectType.equals("Smart Card")) {
                    edt_Mobile_no.setVisibility(View.GONE);

                    edt_SmartCard_no.setVisibility(View.VISIBLE);

                  //  smo_mno = edt_SmartCard_no.getText().toString();

                }else{
                    edt_Mobile_no.setVisibility(View.GONE);

                    edt_SmartCard_no.setVisibility(View.GONE);

                  //  smo_mno= "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_Submit_Form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(SelectType.equals("Mobile No")){
                    smo_mno = edt_Mobile_no.getText().toString();
                }

                if(SelectType.equals("Smart Card")){
                    smo_mno = edt_SmartCard_no.getText().toString();
                }


              //  Toast.makeText(AddPointValueFirstActivity.this, ""+SelectType+","+smo_mno+"vvvvv", Toast.LENGTH_SHORT).show();


                progressDialog= new ProgressDialog(AddPointValueFirstActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                WebService.getClient().getFirstSmartCard(SelectType,
                        smo_mno).enqueue(new Callback<FirstAddSmartCardModel>() {
                    @Override
                    public void onResponse(@NotNull Call<FirstAddSmartCardModel> call,
                                           @NotNull Response<FirstAddSmartCardModel> response) {
                        progressDialog.dismiss();

                        assert response.body() != null;

                       /* Toast.makeText(AddPointValueFirstActivity.this, ""+response.body().getMsg()+" "+
                                smo_mno, Toast.LENGTH_SHORT).show();*/

                        preferences = getSharedPreferences("ID", MODE_PRIVATE);
                        preferences.edit().putInt("id", response.body().getId()).apply();
                        preferences.edit().putString("cuid", response.body().getCuid()).apply();




                        if(response.body().getSuccess()==true){
                            Toast.makeText(AddPointValueFirstActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(AddPointValueFirstActivity.this,AddPointValueSecondActivity.class);
                            i.putExtra("cuid",response.body().getCuid());
                            i.putExtra("id",response.body().getId());
                            startActivity(i);
                        }
                        else{
                            Utils.showErrorToast(AddPointValueFirstActivity.this,
                                   ""+ response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<FirstAddSmartCardModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });

            }
        });
    }
}