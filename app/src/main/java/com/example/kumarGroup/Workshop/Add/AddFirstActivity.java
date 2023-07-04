package com.example.kumarGroup.Workshop.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.PartDataModel;
import com.example.kumarGroup.PhaseOneWsAdd;
import com.example.kumarGroup.QtyAvailableWSModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.WorkShopAddProductModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFirstActivity extends AppCompatActivity {

    Spinner spn_Ws_Add_SparPart, spn_Ws_Add_SubSparPart, spn_Ws_Sale,spn_SecondWS_selectData;
    Spinner spn_Ws_Add_SparPart2, spn_Ws_Add_SubSparPart2, spn_Ws_Sale2;
    Spinner spn_Ws_Add_SparPart3, spn_Ws_Add_SubSparPart3, spn_Ws_Sale3;
    Spinner spn_Ws_Add_SparPart4, spn_Ws_Add_SubSparPart4, spn_Ws_Sale4;
    Spinner spn_Ws_Add_SparPart5, spn_Ws_Add_SubSparPart5, spn_Ws_Sale5;

    Spinner spn_Ws_Add_SparPart6, spn_Ws_Add_SubSparPart6, spn_Ws_Sale6;
    Spinner spn_Ws_Add_SparPart7, spn_Ws_Add_SubSparPart7, spn_Ws_Sale7;
    Spinner spn_Ws_Add_SparPart8, spn_Ws_Add_SubSparPart8, spn_Ws_Sale8;
    Spinner spn_Ws_Add_SparPart9, spn_Ws_Add_SubSparPart9, spn_Ws_Sale9;
    Spinner spn_Ws_Add_SparPart10, spn_Ws_Add_SubSparPart10, spn_Ws_Sale10;


    Spinner spn_smartCard,spn_ScardYes;

    Button Btn_Ws_Add_Next,Btn_Ws_Add_Cancel,Btn_Ws_Add_Next_alpesh;

    LinearLayout firstmain1,firstmain2;

    EditText edt_Ws_Add_complainNumber;

    /****************************************/

    EditText edt_Ws_Add_SubPart,edt_Ws_Add_SubPart2,edt_Ws_Add_SubPart3,edt_Ws_Add_SubPart4
            ,edt_Ws_Add_SubPart5,
            edt_Ws_Add_SubPart6,edt_Ws_Add_SubPart7,edt_Ws_Add_SubPart8,edt_Ws_Add_SubPart9,
            edt_Ws_Add_SubPart10;


    /****************************************/


    EditText edt_Ws_Add_PartNumber, edt_Ws_Add_Quantity, edt_Ws_Add_RateDisplay, edt_Ws_Add_QMultiR;
    EditText edt_Ws_Add_PartNumber2, edt_Ws_Add_Quantity2, edt_Ws_Add_RateDisplay2, edt_Ws_Add_QMultiR2;
    EditText edt_Ws_Add_PartNumber3, edt_Ws_Add_Quantity3, edt_Ws_Add_RateDisplay3, edt_Ws_Add_QMultiR3;
    EditText edt_Ws_Add_PartNumber4, edt_Ws_Add_Quantity4, edt_Ws_Add_RateDisplay4, edt_Ws_Add_QMultiR4;
    EditText edt_Ws_Add_PartNumber5, edt_Ws_Add_Quantity5, edt_Ws_Add_RateDisplay5, edt_Ws_Add_QMultiR5;

    EditText edt_Ws_Add_PartNumber6, edt_Ws_Add_Quantity6, edt_Ws_Add_RateDisplay6, edt_Ws_Add_QMultiR6;
    EditText edt_Ws_Add_PartNumber7, edt_Ws_Add_Quantity7, edt_Ws_Add_RateDisplay7, edt_Ws_Add_QMultiR7;
    EditText edt_Ws_Add_PartNumber8, edt_Ws_Add_Quantity8, edt_Ws_Add_RateDisplay8, edt_Ws_Add_QMultiR8;
    EditText edt_Ws_Add_PartNumber9, edt_Ws_Add_Quantity9, edt_Ws_Add_RateDisplay9, edt_Ws_Add_QMultiR9;
    EditText edt_Ws_Add_PartNumber10, edt_Ws_Add_Quantity10, edt_Ws_Add_RateDisplay10, edt_Ws_Add_QMultiR10;

    LinearLayout linNoOne, linNoTwo, linNoThree, linNoFour, linNoFive;

    LinearLayout linNoSix, linNoSeven, linNoEight, linNoNine, linNoTen;

    ImageView img_Ws_AddOne, img_Ws_AddOne2, img_Ws_AddOne3, img_Ws_AddOne4, img_Ws_AddOne5;

    ImageView img_Ws_AddOne6, img_Ws_AddOne7, img_Ws_AddOne8, img_Ws_AddOne9, img_Ws_AddOne10;

    int flag = 0, flag1 = 0, flag2 = 0, flag3 = 0;
    int flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0, flag8 = 0, flag9 = 0;


    String part_dataId, part_dataId2,part_dataId3,part_dataId4,part_dataId5,
            part_dataId6,part_dataId7,part_dataId8,part_dataId9,part_dataId10;

    SharedPreferences sp1;
    String emp;

    String Product_Name;
    String[] Product_Name_List = {"Select Product", "Spar Part","General Checkup"};


    String SmartCard;
    String[] Smart_Card = {"Select Smart Card Option", "Yes","No"};


    String SmartCardYes;
    String[] Smart_CardYes = {"Select Type", "Referral","Repeat"};


    String Sale;
    String[] Sale_list = {"Select Sale Type", "Warranty Sale", "Customer Sale"};

    List<String> SparPartName = new ArrayList<>();
    List<String> SparPartID = new ArrayList<>();

    List<String> SparPartName2 = new ArrayList<>();
    List<String> SparPartID2 = new ArrayList<>();

    List<String> SparPartName3 = new ArrayList<>();
    List<String> SparPartID3 = new ArrayList<>();

    List<String> SparPartName4 = new ArrayList<>();
    List<String> SparPartID4 = new ArrayList<>();

    List<String> SparPartName5 = new ArrayList<>();
    List<String> SparPartID5 = new ArrayList<>();


    List<String> SparPartName6 = new ArrayList<>();
    List<String> SparPartID6 = new ArrayList<>();

    List<String> SparPartName7 = new ArrayList<>();
    List<String> SparPartID7 = new ArrayList<>();

    List<String> SparPartName8 = new ArrayList<>();
    List<String> SparPartID8 = new ArrayList<>();

    List<String> SparPartName9 = new ArrayList<>();
    List<String> SparPartID9 = new ArrayList<>();

    List<String> SparPartName10 = new ArrayList<>();
    List<String> SparPartID10 = new ArrayList<>();

    String Sparpart_Name, Sparpart_ID;

    String Sparpart_Name2, Sparpart_ID2;

    String Sparpart_Name3, Sparpart_ID3;

    String Sparpart_Name4, Sparpart_ID4;

    String Sparpart_Name5, Sparpart_ID5;

    String Sparpart_Name6, Sparpart_ID6;
    String Sparpart_Name7, Sparpart_ID7;
    String Sparpart_Name8, Sparpart_ID8;
    String Sparpart_Name9, Sparpart_ID9;
    String Sparpart_Name10, Sparpart_ID10;

    int x;
    Double y,mul;
    int x2;
    Double y2, mul2;
    int x3;
    Double y3, mul3;
    int x4;
    Double y4, mul4;
    int x5;
    Double y5, mul5;

    int x6;
    Double y6, mul6;
    int x7;
    Double y7, mul7;
    int x8;
    Double y8, mul8;
    int x9;
    Double y9, mul9;
    int x10;
    Double y10, mul10;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;


    String nextId_Ws;

    ArrayList<String> WS_ModelId = new ArrayList<>();
    ArrayList<String> WS_PartName = new ArrayList<>();

    ArrayList<String> WS_PartName_newAdd = new ArrayList<>();
    ArrayList<String> WS_PartID_newAdd = new ArrayList<>();

    ArrayList<String> WS_PartNumber = new ArrayList<>();
    ArrayList<String> WS_Quantity = new ArrayList<>();
    ArrayList<String> WS_Rate = new ArrayList<>();
    ArrayList<String> WS_TotalPrice = new ArrayList<>();


  //  int sumOfTotalPrice;
    Double sumOfTotalPrice;

    String NextIDD_WS;


    String SubPartNo;

    Button btnpo1,btnpo2,btnpo3,btnpo4,btnpo5,btnpo6,btnpo7,btnpo8,btnpo9,btnpo10;

    String select_data;
    String[] select_data_list = {"Select Work Type", "Service","Complaint","Health/Check Up","Installation"
            ,"Name and Number Plate","After Delivery Discuss","Counter Sale"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_first);
        getSupportActionBar().setTitle("Workshop Add");


        sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp1.getString("emp_id", "");


        sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
        NextIDD_WS = sharedPreferences.getString("NextId_WS", "");

        /** ******************************** First ***************************************************** */

        Btn_Ws_Add_Next_alpesh = findViewById(R.id.Btn_Ws_Add_Next_alpesh);
        spn_SecondWS_selectData = findViewById(R.id.spn_SecondWS_selectData);
        firstmain1 = findViewById(R.id.firstmain1);
        firstmain2 = findViewById(R.id.firstmain2);

        Btn_Ws_Add_Next = findViewById(R.id.Btn_Ws_Add_Next);
        Btn_Ws_Add_Cancel = findViewById(R.id.Btn_Ws_Add_Cancel);

        spn_Ws_Add_SparPart = findViewById(R.id.spn_Ws_Add_SparPart);
        spn_Ws_Add_SubSparPart = findViewById(R.id.spn_Ws_Add_SubSparPart);
        spn_Ws_Sale = findViewById(R.id.spn_Ws_Sale);

        edt_Ws_Add_PartNumber = findViewById(R.id.edt_Ws_Add_PartNumber);
        edt_Ws_Add_Quantity = findViewById(R.id.edt_Ws_Add_Quantity);
        edt_Ws_Add_RateDisplay = findViewById(R.id.edt_Ws_Add_RateDisplay);
        edt_Ws_Add_QMultiR = findViewById(R.id.edt_Ws_Add_QMultiR);


        edt_Ws_Add_SubPart=findViewById(R.id.edt_Ws_Add_SubPart);
        btnpo1=findViewById(R.id.btnpo1);

        edt_Ws_Add_complainNumber=findViewById(R.id.edt_Ws_Add_complainNumber);


        spn_smartCard= findViewById(R.id.spn_smartCard);
        spn_ScardYes= findViewById(R.id.spn_ScardYes);

        /** ******************************** Second ***************************************************** */

        //  spn_Ws_Add_SparPart2 = findViewById(R.id.spn_Ws_Add_SparPart2);
        //  spn_Ws_Sale2 = findViewById(R.id.spn_Ws_Sale2);
        spn_Ws_Add_SubSparPart2 = findViewById(R.id.spn_Ws_Add_SubSparPart2);


        edt_Ws_Add_PartNumber2 = findViewById(R.id.edt_Ws_Add_PartNumber2);
        edt_Ws_Add_Quantity2 = findViewById(R.id.edt_Ws_Add_Quantity2);
        edt_Ws_Add_RateDisplay2 = findViewById(R.id.edt_Ws_Add_RateDisplay2);
        edt_Ws_Add_QMultiR2 = findViewById(R.id.edt_Ws_Add_QMultiR2);


        edt_Ws_Add_SubPart2 = findViewById(R.id.edt_Ws_Add_SubPart2);
        btnpo2=findViewById(R.id.btnpo2);


        /** ******************************** Third ***************************************************** */
        // spn_Ws_Add_SparPart3 = findViewById(R.id.spn_Ws_Add_SparPart3);
        spn_Ws_Add_SubSparPart3 = findViewById(R.id.spn_Ws_Add_SubSparPart3);
        //  spn_Ws_Sale3 = findViewById(R.id.spn_Ws_Sale3);


        edt_Ws_Add_PartNumber3 = findViewById(R.id.edt_Ws_Add_PartNumber3);
        edt_Ws_Add_Quantity3 = findViewById(R.id.edt_Ws_Add_Quantity3);
        edt_Ws_Add_RateDisplay3 = findViewById(R.id.edt_Ws_Add_RateDisplay3);
        edt_Ws_Add_QMultiR3 = findViewById(R.id.edt_Ws_Add_QMultiR3);

        edt_Ws_Add_SubPart3=findViewById(R.id.edt_Ws_Add_SubPart3);
        btnpo3=findViewById(R.id.btnpo3);


        /** ******************************** Fourth ***************************************************** */

        //  spn_Ws_Add_SparPart4 = findViewById(R.id.spn_Ws_Add_SparPart4);
        spn_Ws_Add_SubSparPart4 = findViewById(R.id.spn_Ws_Add_SubSparPart4);
        //  spn_Ws_Sale4 = findViewById(R.id.spn_Ws_Sale4);

        edt_Ws_Add_PartNumber4 = findViewById(R.id.edt_Ws_Add_PartNumber4);
        edt_Ws_Add_Quantity4 = findViewById(R.id.edt_Ws_Add_Quantity4);
        edt_Ws_Add_RateDisplay4 = findViewById(R.id.edt_Ws_Add_RateDisplay4);
        edt_Ws_Add_QMultiR4 = findViewById(R.id.edt_Ws_Add_QMultiR4);

        edt_Ws_Add_SubPart4 = findViewById(R.id.edt_Ws_Add_SubPart4);
        btnpo4=findViewById(R.id.btnpo4);


        /** ******************************** Fifth ***************************************************** */

        //  spn_Ws_Add_SparPart5 = findViewById(R.id.spn_Ws_Add_SparPart5);
        spn_Ws_Add_SubSparPart5 = findViewById(R.id.spn_Ws_Add_SubSparPart5);
        //  spn_Ws_Sale5 = findViewById(R.id.spn_Ws_Sale5);

        edt_Ws_Add_PartNumber5 = findViewById(R.id.edt_Ws_Add_PartNumber5);
        edt_Ws_Add_Quantity5 = findViewById(R.id.edt_Ws_Add_Quantity5);
        edt_Ws_Add_RateDisplay5 = findViewById(R.id.edt_Ws_Add_RateDisplay5);
        edt_Ws_Add_QMultiR5 = findViewById(R.id.edt_Ws_Add_QMultiR5);

        edt_Ws_Add_SubPart5 = findViewById(R.id.edt_Ws_Add_SubPart5);
        btnpo5=findViewById(R.id.btnpo5);


        /** ******************************** Six ***************************************************** */
        //  spn_Ws_Add_SparPart5 = findViewById(R.id.spn_Ws_Add_SparPart5);
        //  spn_Ws_Sale5 = findViewById(R.id.spn_Ws_Sale5);
        spn_Ws_Add_SubSparPart6 = findViewById(R.id.spn_Ws_Add_SubSparPart6);

        edt_Ws_Add_PartNumber6 = findViewById(R.id.edt_Ws_Add_PartNumber6);
        edt_Ws_Add_Quantity6 = findViewById(R.id.edt_Ws_Add_Quantity6);
        edt_Ws_Add_RateDisplay6 = findViewById(R.id.edt_Ws_Add_RateDisplay6);
        edt_Ws_Add_QMultiR6 = findViewById(R.id.edt_Ws_Add_QMultiR6);

        edt_Ws_Add_SubPart6 = findViewById(R.id.edt_Ws_Add_SubPart6);
        btnpo6=findViewById(R.id.btnpo6);



        /** ******************************** Seven ***************************************************** */
        //  spn_Ws_Add_SparPart5 = findViewById(R.id.spn_Ws_Add_SparPart5);
        //  spn_Ws_Sale5 = findViewById(R.id.spn_Ws_Sale5);
        spn_Ws_Add_SubSparPart7 = findViewById(R.id.spn_Ws_Add_SubSparPart7);

        edt_Ws_Add_PartNumber7 = findViewById(R.id.edt_Ws_Add_PartNumber7);
        edt_Ws_Add_Quantity7 = findViewById(R.id.edt_Ws_Add_Quantity7);
        edt_Ws_Add_RateDisplay7 = findViewById(R.id.edt_Ws_Add_RateDisplay7);
        edt_Ws_Add_QMultiR7 = findViewById(R.id.edt_Ws_Add_QMultiR7);

        edt_Ws_Add_SubPart7 = findViewById(R.id.edt_Ws_Add_SubPart7);
        btnpo7=findViewById(R.id.btnpo7);

        /** ******************************** Eight ***************************************************** */
        //  spn_Ws_Add_SparPart5 = findViewById(R.id.spn_Ws_Add_SparPart5);
        //  spn_Ws_Sale5 = findViewById(R.id.spn_Ws_Sale5);
        spn_Ws_Add_SubSparPart8 = findViewById(R.id.spn_Ws_Add_SubSparPart8);

        edt_Ws_Add_PartNumber8 = findViewById(R.id.edt_Ws_Add_PartNumber8);
        edt_Ws_Add_Quantity8 = findViewById(R.id.edt_Ws_Add_Quantity8);
        edt_Ws_Add_RateDisplay8 = findViewById(R.id.edt_Ws_Add_RateDisplay8);
        edt_Ws_Add_QMultiR8 = findViewById(R.id.edt_Ws_Add_QMultiR8);

        edt_Ws_Add_SubPart8 = findViewById(R.id.edt_Ws_Add_SubPart8);
        btnpo8=findViewById(R.id.btnpo8);

        /** ******************************** Nine ***************************************************** */
        //  spn_Ws_Add_SparPart5 = findViewById(R.id.spn_Ws_Add_SparPart5);
        //  spn_Ws_Sale5 = findViewById(R.id.spn_Ws_Sale5);
        spn_Ws_Add_SubSparPart9 = findViewById(R.id.spn_Ws_Add_SubSparPart9);

        edt_Ws_Add_PartNumber9 = findViewById(R.id.edt_Ws_Add_PartNumber9);
        edt_Ws_Add_Quantity9 = findViewById(R.id.edt_Ws_Add_Quantity9);
        edt_Ws_Add_RateDisplay9 = findViewById(R.id.edt_Ws_Add_RateDisplay9);
        edt_Ws_Add_QMultiR9 = findViewById(R.id.edt_Ws_Add_QMultiR9);

        edt_Ws_Add_SubPart9 = findViewById(R.id.edt_Ws_Add_SubPart9);
        btnpo9=findViewById(R.id.btnpo9);

        /** ******************************** Ten ***************************************************** */

        //  spn_Ws_Add_SparPart5 = findViewById(R.id.spn_Ws_Add_SparPart5);
        //  spn_Ws_Sale5 = findViewById(R.id.spn_Ws_Sale5);
        spn_Ws_Add_SubSparPart10 = findViewById(R.id.spn_Ws_Add_SubSparPart10);

        edt_Ws_Add_PartNumber10 = findViewById(R.id.edt_Ws_Add_PartNumber10);
        edt_Ws_Add_Quantity10 = findViewById(R.id.edt_Ws_Add_Quantity10);
        edt_Ws_Add_RateDisplay10 = findViewById(R.id.edt_Ws_Add_RateDisplay10);
        edt_Ws_Add_QMultiR10 = findViewById(R.id.edt_Ws_Add_QMultiR10);

        edt_Ws_Add_SubPart10 = findViewById(R.id.edt_Ws_Add_SubPart10);
        btnpo10=findViewById(R.id.btnpo10);

        //**************************************************************************

        linNoOne = findViewById(R.id.linNoOne);
        linNoTwo = findViewById(R.id.linNoTwo);
        linNoThree = findViewById(R.id.linNoThree);
        linNoFour = findViewById(R.id.linNoFour);
        linNoFive = findViewById(R.id.linNoFive);

        linNoSix = findViewById(R.id.linNoSix);
        linNoSeven = findViewById(R.id.linNoSeven);
        linNoEight = findViewById(R.id.linNoEight);
        linNoNine = findViewById(R.id.linNoNine);
        linNoTen = findViewById(R.id.linNoTen);


        img_Ws_AddOne = findViewById(R.id.img_Ws_AddOne);
        img_Ws_AddOne2 = findViewById(R.id.img_Ws_AddOne2);
        img_Ws_AddOne3 = findViewById(R.id.img_Ws_AddOne3);
        img_Ws_AddOne4 = findViewById(R.id.img_Ws_AddOne4);
        img_Ws_AddOne5 = findViewById(R.id.img_Ws_AddOne5);

        img_Ws_AddOne6 = findViewById(R.id.img_Ws_AddOne6);
        img_Ws_AddOne7 = findViewById(R.id.img_Ws_AddOne7);
        img_Ws_AddOne8 = findViewById(R.id.img_Ws_AddOne8);
        img_Ws_AddOne9 = findViewById(R.id.img_Ws_AddOne9);
        img_Ws_AddOne10 = findViewById(R.id.img_Ws_AddOne10);

        //**************************************************************************

        linNoTwo.setVisibility(View.GONE);
        linNoThree.setVisibility(View.GONE);
        linNoFour.setVisibility(View.GONE);
        linNoFive.setVisibility(View.GONE);

        linNoSix.setVisibility(View.GONE);
        linNoSeven.setVisibility(View.GONE);
        linNoEight.setVisibility(View.GONE);
        linNoNine.setVisibility(View.GONE);
        linNoTen.setVisibility(View.GONE);


        /** ********************************************************************************* */


        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,select_data_list);
        spn_SecondWS_selectData.setAdapter(adapter1);

        spn_SecondWS_selectData.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_data = select_data_list[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


     //   spn_ScardYes.setVisibility(View.GONE);

        ArrayAdapter consumerSkim = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Smart_Card);
        spn_smartCard.setAdapter(consumerSkim);

        spn_smartCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SmartCard = Smart_Card[position];
                if(Smart_Card[position].equals("Yes")){
                    spn_ScardYes.setVisibility(View.VISIBLE);
                }
                else{
                    spn_ScardYes.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter ScardYes = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Smart_CardYes);
        spn_ScardYes.setAdapter(ScardYes);

        spn_ScardYes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SmartCardYes = Smart_CardYes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        /** ********************************************************************************* */


        img_Ws_AddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {

                    img_Ws_AddOne.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoTwo.setVisibility(View.GONE);
                    flag = 1;

                } else if (flag == 1) {

                    img_Ws_AddOne.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoTwo.setVisibility(View.VISIBLE);

                    flag = 0;
                }
            }
        });

        img_Ws_AddOne2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1 == 0) {

                    img_Ws_AddOne2.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoThree.setVisibility(View.GONE);
                    flag1 = 1;

                } else if (flag1 == 1) {

                    img_Ws_AddOne2.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoThree.setVisibility(View.VISIBLE);

                    flag1 = 0;
                }
                //  linNoThree.setVisibility(View.VISIBLE);
            }
        });

        img_Ws_AddOne3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2 == 0) {

                    img_Ws_AddOne3.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoFour.setVisibility(View.GONE);
                    flag2 = 1;

                } else if (flag2 == 1) {

                    img_Ws_AddOne3.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoFour.setVisibility(View.VISIBLE);

                    flag2 = 0;
                }
                // linNoFour.setVisibility(View.VISIBLE);
            }
        });

        img_Ws_AddOne4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linNoFive.setVisibility(View.VISIBLE);
                if (flag3 == 0) {

                    img_Ws_AddOne4.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoFive.setVisibility(View.GONE);
                    flag3 = 1;

                } else if (flag3 == 1) {

                    img_Ws_AddOne4.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoFive.setVisibility(View.VISIBLE);

                    flag3 = 0;
                }
            }
        });

        img_Ws_AddOne5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linNoSix.setVisibility(View.VISIBLE);
                if (flag4 == 0) {

                    img_Ws_AddOne5.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoSix.setVisibility(View.GONE);
                    flag4 = 1;

                } else if (flag4 == 1) {

                    img_Ws_AddOne5.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoSix.setVisibility(View.VISIBLE);

                    flag4 = 0;
                }
            }
        });

        img_Ws_AddOne6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linNoSeven.setVisibility(View.VISIBLE);
                if (flag5 == 0) {

                    img_Ws_AddOne6.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoSeven.setVisibility(View.GONE);
                    flag5 = 1;

                } else if (flag5 == 1) {

                    img_Ws_AddOne6.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoSeven.setVisibility(View.VISIBLE);

                    flag5 = 0;
                }
            }
        });

        img_Ws_AddOne7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linNoEight.setVisibility(View.VISIBLE);
                if (flag6 == 0) {

                    img_Ws_AddOne7.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoEight.setVisibility(View.GONE);
                    flag6 = 1;

                } else if (flag6 == 1) {

                    img_Ws_AddOne7.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoEight.setVisibility(View.VISIBLE);

                    flag6 = 0;
                }
            }
        });


        img_Ws_AddOne8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linNoNine.setVisibility(View.VISIBLE);
                if (flag7 == 0) {

                    img_Ws_AddOne8.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoNine.setVisibility(View.GONE);
                    flag7 = 1;

                } else if (flag7 == 1) {

                    img_Ws_AddOne8.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoNine.setVisibility(View.VISIBLE);

                    flag7 = 0;
                }
            }
        });

        img_Ws_AddOne9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linNoTen.setVisibility(View.VISIBLE);
                if (flag8 == 0) {

                    img_Ws_AddOne9.setImageResource(R.drawable.ic_baseline_remove_24);
                    linNoTen.setVisibility(View.GONE);
                    flag8 = 1;

                } else if (flag8 == 1) {

                    img_Ws_AddOne9.setImageResource(R.drawable.ic_baseline_add_24);
                    linNoTen.setVisibility(View.VISIBLE);

                    flag8 = 0;
                }
            }
        });


        /** ********************************************************************************************************** */




        edt_Ws_Add_PartNumber.setFocusable(false);
        edt_Ws_Add_RateDisplay.setFocusable(false);
        edt_Ws_Add_QMultiR.setFocusable(false);


        ArrayAdapter adapter_sale = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                Sale_list);
        spn_Ws_Sale.setAdapter(adapter_sale);

        spn_Ws_Sale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sale = Sale_list[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Product_Name_List);
        spn_Ws_Add_SparPart.setAdapter(adapter);

        spn_Ws_Add_SparPart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Product_Name = Product_Name_List[position];
                if ("Select Product".equals(Product_Name)){
                    Btn_Ws_Add_Next_alpesh.setVisibility(View.VISIBLE);
                    firstmain1.setVisibility(View.GONE);
                    firstmain2.setVisibility(View.GONE);
                    Btn_Ws_Add_Next.setVisibility(View.GONE);
                }

                else if ("General Checkup".equals(Product_Name)){
                    Btn_Ws_Add_Next_alpesh.setVisibility(View.VISIBLE);
                    firstmain1.setVisibility(View.GONE);
                    firstmain2.setVisibility(View.GONE);
                    Btn_Ws_Add_Next.setVisibility(View.GONE);
                }
                else if ("Spar Part".equals(Product_Name)){
                    Btn_Ws_Add_Next_alpesh.setVisibility(View.GONE);
                    firstmain1.setVisibility(View.VISIBLE);
                    firstmain2.setVisibility(View.VISIBLE);
                    Btn_Ws_Add_Next.setVisibility(View.VISIBLE);

                    WebService.getClient().getWorkshopAddProd(Product_Name).enqueue(new Callback<WorkShopAddProductModel>() {
                        @Override
                        public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                               @NotNull Response<WorkShopAddProductModel> response) {

                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    SparPartName.clear();
                                    SparPartID.clear();

                                    SparPartName.add("Select Model");
                                    SparPartID.add("0");

                                    //    Log.d("product", response.body().toString());

                                    for (int i = 0; i < response.body().getDetail().size(); i++) {
                                        String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                                        //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                                        SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                                        //SparPartName.add(result);
                                        SparPartID.add(response.body().getDetail().get(i).getId());
                                    }

                                    Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                                    //   Log.d("MProduct", ModelName.toString());

                                    ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item, SparPartName);
                                    spn_Ws_Add_SubSparPart.setAdapter(adapter2);

                                    spn_Ws_Add_SubSparPart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            Sparpart_Name = SparPartName.get(position);
                                            Sparpart_ID = SparPartID.get(position);

                                       /* WebService.getClient().getPartData(Sparpart_ID).
                                                enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                if (response.body().getDetail().size() == 0) {
                                                    Log.d("Nodata", "onResponse: No Date Available ");
                                                } else {
                                                    edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno() + "");
                                                    edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate() + "");
                                                    part_dataId = response.body().getDetail().get(0).getId();
                                                }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                                            }
                                        });*/

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });




                              /*  edt_Ws_Add_SubPart.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                        WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                try {

                                                    if (response.body().getDetail().size() == 0) {
                                                        Log.d("Nodata", "onResponse: No Date Available ");
                                                    } else {

                                                        edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno() + "");
                                                        edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate() + "");
                                                        part_dataId = response.body().getDetail().get(0).getId();

                                                    }
                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                  //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                                                }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        String SubPartNo = edt_Ws_Add_SubPart.getText().toString();
                                        WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                try {

                                                    if (response.body().getDetail().size() == 0) {
                                                        Log.d("Nodata", "onResponse: No Date Available ");
                                                    } else {

                                                        edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno() + "");
                                                        edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate() + "");
                                                        part_dataId = response.body().getDetail().get(0).getId();


                                                    }
                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                  //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                                                }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                                            }
                                        });

                                    }
                                });*/

                                }
                            }

                        }

                        @Override
                        public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnpo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_Ws_Add_SubPart.getText().toString().equals("")){
                    edt_Ws_Add_SubPart.setError("Please Enter Part code");
                }
                else {

                    WebService.getClient().getPartData(edt_Ws_Add_SubPart.getText().toString()).enqueue(new Callback<PartDataModel>() {
                        @Override
                        public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                            assert response.body() != null;
                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {

                                edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId = response.body().getDetail().get(0).getId();

                            }

                        /*try {

                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {

                                edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId = response.body().getDetail().get(0).getId();

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }*/

                        }

                        @Override
                        public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                        }
                    });
                }
            }
        });



     /*   edt_Ws_Add_SubPart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {

                                edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId = response.body().getDetail().get(0).getId();

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {

                                edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId = response.body().getDetail().get(0).getId();

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
              //  String SubPartNo ;

               *//* if (edt_Ws_Add_SubPart.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    SubPartNo = s.toString();
                }*//*


                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {

                                edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId = response.body().getDetail().get(0).getId();


                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });

            }
        });*/


        edt_Ws_Add_RateDisplay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //y = Integer.parseInt(edt_Ws_Add_RateDisplay.getText().toString());
                y = Double.parseDouble(edt_Ws_Add_RateDisplay.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y = Double.parseDouble(edt_Ws_Add_RateDisplay.getText().toString());
            }
        });


        edt_Ws_Add_Quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
                }


                mul = x * y; //Perform Maths operation
                edt_Ws_Add_QMultiR.setText("" + mul);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

               // WebService.getClient().getQtyAvailable(Sparpart_ID, edt_Ws_Add_Quantity.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart.getText().toString(),
                        edt_Ws_Add_Quantity.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                       /* if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, ""
                                        + response.body().getMsg());
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, "" + response.body().getMsg());
                        }*/

                        if (response.body().getSuccess()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, "" + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this,
                                    "" + response.body().getMsg());

                            Utils.showErrorToast(AddFirstActivity.this,"Please check Quantity");


                           /* assert response.body() != null;
                            if(!response.body().getSuccess()){
                                Btn_Ws_Add_Next.setClickable(false);
                                Utils.showErrorToast(AddFirstActivity.this,"Please check Quantity");
                            }
                            else{
                                assert response.body() != null;
                                Utils.showErrorToast(AddFirstActivity.this, "" + response.body().getMsg());

                            }*/

                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul = x * y; //Perform Maths operation
                edt_Ws_Add_QMultiR.setText("" + mul);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });


        /** ************************************Second******************************************************* */


        WebService.getClient().getWorkshopAddProd("Spar Part").enqueue(new Callback<WorkShopAddProductModel>() {
            @Override
            public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                   @NotNull Response<WorkShopAddProductModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        SparPartName2.clear();
                        SparPartID2.clear();

                        SparPartName2.add("Select Model");
                        SparPartID2.add("0");

                        //    Log.d("product", response.body().toString());

                        for (int i = 0; i < response.body().getDetail().size(); i++) {
                            String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                            //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                            SparPartName2.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                            //SparPartName.add(result);
                            SparPartID2.add(response.body().getDetail().get(i).getId());
                        }

                        Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                        //   Log.d("MProduct", ModelName.toString());

                        ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, SparPartName2);
                        spn_Ws_Add_SubSparPart2.setAdapter(adapter2);

                        spn_Ws_Add_SubSparPart2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Sparpart_Name2 = SparPartName2.get(position);
                                Sparpart_ID2 = SparPartID2.get(position);

                                WebService.getClient().getPartData(Sparpart_ID2).enqueue(new Callback<PartDataModel>() {
                                    @Override
                                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                        assert response.body() != null;
                                        try {
                                            if (response.body().getDetail().size() == 0) {
                                                Log.d("Nodata", "onResponse: No Date Available ");
                                            } else {
                                                edt_Ws_Add_PartNumber2.setText(response.body().getDetail().get(0).getPno() + "");
                                                edt_Ws_Add_RateDisplay2.setText(response.body().getDetail().get(0).getRate() + "");
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }
                                        /*if (response.body().getDetail().size() == 0) {
                                            Log.d("Nodata", "onResponse: No Date Available ");
                                        } else {
                                            edt_Ws_Add_PartNumber2.setText(response.body().getDetail().get(0).getPno() + "");
                                            edt_Ws_Add_RateDisplay2.setText(response.body().getDetail().get(0).getRate() + "");
                                        }*/
                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
            public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

            }
        });


        //**********************************************

        btnpo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(edt_Ws_Add_SubPart2.getText().toString().equals("")){
                    edt_Ws_Add_SubPart2.setError("Please Enter Part code");
                }
                else {
                     WebService.getClient().getPartData(edt_Ws_Add_SubPart2.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber2.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay2.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId2 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });

     /*   edt_Ws_Add_SubPart2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {
                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {
                                edt_Ws_Add_PartNumber2.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay2.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId2 = response.body().getDetail().get(0).getId();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {
                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {
                                edt_Ws_Add_PartNumber2.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay2.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId2 = response.body().getDetail().get(0).getId();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/



        edt_Ws_Add_RateDisplay2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y2 = Double.parseDouble(edt_Ws_Add_RateDisplay2.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y2 = Double.parseDouble(edt_Ws_Add_RateDisplay2.getText().toString());
            }
        });


        edt_Ws_Add_Quantity2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity2.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x2 = Integer.parseInt(edt_Ws_Add_Quantity2.getText().toString());
                }


                mul2 = x2 * y2; //Perform Maths operation
                edt_Ws_Add_QMultiR2.setText("" + mul2);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity2.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x2 = Integer.parseInt(edt_Ws_Add_Quantity2.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart2.getText().toString(),
                        edt_Ws_Add_Quantity2.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, "" + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, "" + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul2 = x2 * y2; //Perform Maths operation
                edt_Ws_Add_QMultiR2.setText("" + mul2);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });


        /** ****************************************** Third ************************************************* */

        WebService.getClient().getWorkshopAddProd("Spar Part").enqueue(new Callback<WorkShopAddProductModel>() {
            @Override
            public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                   @NotNull Response<WorkShopAddProductModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        SparPartName3.clear();
                        SparPartID3.clear();

                        SparPartName3.add("Select Model");
                        SparPartID3.add("0");

                        //    Log.d("product", response.body().toString());

                        for (int i = 0; i < response.body().getDetail().size(); i++) {
                            String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                            //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                            SparPartName3.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                            //SparPartName.add(result);
                            SparPartID3.add(response.body().getDetail().get(i).getId());
                        }

                        Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                        //   Log.d("MProduct", ModelName.toString());

                        ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, SparPartName3);
                        spn_Ws_Add_SubSparPart3.setAdapter(adapter2);

                        spn_Ws_Add_SubSparPart3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Sparpart_Name3 = SparPartName3.get(position);
                                Sparpart_ID3 = SparPartID3.get(position);

                                WebService.getClient().getPartData(Sparpart_ID3).enqueue(new Callback<PartDataModel>() {
                                    @Override
                                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                        assert response.body() != null;
                                        if (response.body().getDetail().size() == 0) {
                                            Log.d("Nodata", "onResponse: No Date Available ");
                                        } else {
                                            edt_Ws_Add_PartNumber3.setText(response.body().getDetail().get(0).getPno() + "");
                                            edt_Ws_Add_RateDisplay3.setText(response.body().getDetail().get(0).getRate() + "");
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
            public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

            }
        });


        btnpo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(edt_Ws_Add_SubPart3.getText().toString().equals("")){
                    edt_Ws_Add_SubPart3.setError("Please Enter Part code");
                }
                else {
                     WebService.getClient().getPartData(edt_Ws_Add_SubPart3.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber3.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay3.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId3 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });

       /* edt_Ws_Add_SubPart3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {

                                edt_Ws_Add_PartNumber3.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay3.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId = response.body().getDetail().get(0).getId();


                            }
                        }catch (Exception e){
                            e.printStackTrace();

                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {


                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber3.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay3.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId3 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }
                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/



        edt_Ws_Add_RateDisplay3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y3 = Double.parseDouble(edt_Ws_Add_RateDisplay3.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y3 = Double.parseDouble(edt_Ws_Add_RateDisplay3.getText().toString());
            }
        });


        edt_Ws_Add_Quantity3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity3.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x3 = Integer.parseInt(edt_Ws_Add_Quantity3.getText().toString());
                }


                mul3 = x3 * y3; //Perform Maths operation
                edt_Ws_Add_QMultiR3.setText("" + mul3);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity3.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x3 = Integer.parseInt(edt_Ws_Add_Quantity3.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart3.getText().toString(),
                        edt_Ws_Add_Quantity3.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, "" + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, ""
                                    + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul3 = x3 * y3; //Perform Maths operation
                edt_Ws_Add_QMultiR3.setText("" + mul3);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });

        /** ****************************************** Fourth ************************************************* */

        WebService.getClient().getWorkshopAddProd("Spar Part")
                .enqueue(new Callback<WorkShopAddProductModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                           @NotNull Response<WorkShopAddProductModel> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                SparPartName4.clear();
                                SparPartID4.clear();

                                SparPartName4.add("Select Model");
                                SparPartID4.add("0");

                                //    Log.d("product", response.body().toString());

                                for (int i = 0; i < response.body().getDetail().size(); i++) {
                                    String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                                    //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                                    SparPartName4.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                                    //SparPartName.add(result);
                                    SparPartID4.add(response.body().getDetail().get(i).getId());
                                }

                                Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                                //   Log.d("MProduct", ModelName.toString());

                                ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item, SparPartName4);
                                spn_Ws_Add_SubSparPart4.setAdapter(adapter2);

                                spn_Ws_Add_SubSparPart4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Sparpart_Name4 = SparPartName4.get(position);
                                        Sparpart_ID4 = SparPartID4.get(position);

                                        WebService.getClient().getPartData(Sparpart_ID4).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                if (response.body().getDetail().size() == 0) {
                                                    Log.d("Nodata", "onResponse: No Date Available ");
                                                } else {
                                                    edt_Ws_Add_PartNumber4.setText(response.body().getDetail().get(0).getPno() + "");
                                                    edt_Ws_Add_RateDisplay4.setText(response.body().getDetail().get(0).getRate() + "");
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
                    public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

                    }
                });


        btnpo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(edt_Ws_Add_SubPart4.getText().toString().equals("")){
                    edt_Ws_Add_SubPart4.setError("Please Enter Part code");
                }
                else {
                     WebService.getClient().getPartData(edt_Ws_Add_SubPart4.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber4.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay4.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId4 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });


      /*  edt_Ws_Add_SubPart4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {


                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber4.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay4.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId4 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }
                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {


                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber4.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay4.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId4 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }
                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/



        edt_Ws_Add_RateDisplay4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y4 = Double.parseDouble(edt_Ws_Add_RateDisplay4.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y4 = Double.parseDouble(edt_Ws_Add_RateDisplay4.getText().toString());
            }
        });


        edt_Ws_Add_Quantity4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity4.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x4 = Integer.parseInt(edt_Ws_Add_Quantity4.getText().toString());
                }


                mul4 = x4 * y4; //Perform Maths operation
                edt_Ws_Add_QMultiR4.setText("" + mul4);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity4.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x4 = Integer.parseInt(edt_Ws_Add_Quantity4.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart4.getText().toString(),
                        edt_Ws_Add_Quantity4.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, "" + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, ""
                                    + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul4 = x4 * y4; //Perform Maths operation
                edt_Ws_Add_QMultiR4.setText("" + mul4);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });

        /** ****************************************** Fifth ************************************************* */
        WebService.getClient().getWorkshopAddProd("Spar Part")
                .enqueue(new Callback<WorkShopAddProductModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                           @NotNull Response<WorkShopAddProductModel> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                SparPartName5.clear();
                                SparPartID5.clear();

                                SparPartName5.add("Select Model");
                                SparPartID5.add("0");

                                //    Log.d("product", response.body().toString());

                                for (int i = 0; i < response.body().getDetail().size(); i++) {
                                    String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                                    //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                                    SparPartName5.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                                    //SparPartName.add(result);
                                    SparPartID5.add(response.body().getDetail().get(i).getId());
                                }

                                Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                                //   Log.d("MProduct", ModelName.toString());

                                ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item, SparPartName5);
                                spn_Ws_Add_SubSparPart5.setAdapter(adapter2);

                                spn_Ws_Add_SubSparPart5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Sparpart_Name5 = SparPartName5.get(position);
                                        Sparpart_ID5 = SparPartID5.get(position);

                                        WebService.getClient().getPartData(Sparpart_ID5).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                if (response.body().getDetail().size() == 0) {
                                                    Log.d("Nodata", "onResponse: No Date Available ");
                                                } else {
                                                    edt_Ws_Add_PartNumber5.setText(response.body().getDetail().get(0).getPno() + "");
                                                    edt_Ws_Add_RateDisplay5.setText(response.body().getDetail().get(0).getRate() + "");
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
                    public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

                    }
                });


        btnpo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(edt_Ws_Add_SubPart5.getText().toString().equals("")){
                    edt_Ws_Add_SubPart5.setError("Please Enter Part code");
                }
                else {
                     WebService.getClient().getPartData(edt_Ws_Add_SubPart5.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber5.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay5.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId5 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });




       /* edt_Ws_Add_SubPart5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {
                                edt_Ws_Add_PartNumber5.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay5.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId5 = response.body().getDetail().get(0).getId();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {
                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {
                                edt_Ws_Add_PartNumber5.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay5.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId5 = response.body().getDetail().get(0).getId();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/




        edt_Ws_Add_RateDisplay5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y5 = Double.parseDouble(edt_Ws_Add_RateDisplay5.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y5 = Double.parseDouble(edt_Ws_Add_RateDisplay5.getText().toString());
            }
        });


        edt_Ws_Add_Quantity5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity5.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x5 = Integer.parseInt(edt_Ws_Add_Quantity5.getText().toString());
                }


                mul5 = x5 * y5; //Perform Maths operation
                edt_Ws_Add_QMultiR5.setText("" + mul5);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity5.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x5 = Integer.parseInt(edt_Ws_Add_Quantity5.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart5.getText().toString(),
                        edt_Ws_Add_Quantity5.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, "" + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, ""
                                    + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul5 = x5 * y5; //Perform Maths operation
                edt_Ws_Add_QMultiR5.setText("" + mul5);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });


        /** **************************************** Six *************************************************** */

          WebService.getClient().getWorkshopAddProd("Spar Part")
                .enqueue(new Callback<WorkShopAddProductModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                           @NotNull Response<WorkShopAddProductModel> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                SparPartName6.clear();
                                SparPartID6.clear();

                                SparPartName6.add("Select Model");
                                SparPartID6.add("0");

                                //    Log.d("product", response.body().toString());

                                for (int i = 0; i < response.body().getDetail().size(); i++) {
                                    String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                                    //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                                    SparPartName6.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                                    //SparPartName.add(result);
                                    SparPartID6.add(response.body().getDetail().get(i).getId());
                                }

                                Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                                //   Log.d("MProduct", ModelName.toString());

                                ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item, SparPartName6);
                                spn_Ws_Add_SubSparPart6.setAdapter(adapter2);

                                spn_Ws_Add_SubSparPart6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Sparpart_Name6 = SparPartName6.get(position);
                                        Sparpart_ID6 = SparPartID6.get(position);

                                        WebService.getClient().getPartData(Sparpart_ID6).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                if (response.body().getDetail().size() == 0) {
                                                    Log.d("Nodata", "onResponse: No Date Available ");
                                                } else {
                                                    edt_Ws_Add_PartNumber6.setText(response.body().getDetail().get(0).getPno() + "");
                                                    edt_Ws_Add_RateDisplay6.setText(response.body().getDetail().get(0).getRate() + "");
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
                    public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

                    }
                });


        btnpo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(edt_Ws_Add_SubPart6.getText().toString().equals("")){
                    edt_Ws_Add_SubPart6.setError("Please Enter Part code");
                }
                else {
                     WebService.getClient().getPartData(edt_Ws_Add_SubPart6.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber6.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay6.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId6 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });


      /*  edt_Ws_Add_SubPart6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {


                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber6.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay6.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId6 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }
                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber6.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay6.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId6 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }
                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/




        edt_Ws_Add_RateDisplay6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y6 = Double.parseDouble(edt_Ws_Add_RateDisplay6.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y6 = Double.parseDouble(edt_Ws_Add_RateDisplay6.getText().toString());
            }
        });


        edt_Ws_Add_Quantity6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity6.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x6 = Integer.parseInt(edt_Ws_Add_Quantity6.getText().toString());
                }


                mul6 = x6 * y6; //Perform Maths operation
                edt_Ws_Add_QMultiR6.setText("" + mul6);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity6.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x6 = Integer.parseInt(edt_Ws_Add_Quantity6.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart6.getText().toString(),
                        edt_Ws_Add_Quantity6.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, ""
                                        + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, ""
                                    + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul6 = x6 * y6; //Perform Maths operation
                edt_Ws_Add_QMultiR6.setText("" + mul6);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });



        /** *********************************************** Seven ******************************************** */

        WebService.getClient().getWorkshopAddProd("Spar Part")
                .enqueue(new Callback<WorkShopAddProductModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                           @NotNull Response<WorkShopAddProductModel> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                SparPartName7.clear();
                                SparPartID7.clear();

                                SparPartName7.add("Select Model");
                                SparPartID7.add("0");

                                //    Log.d("product", response.body().toString());

                                for (int i = 0; i < response.body().getDetail().size(); i++) {
                                    String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                                    //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                                    SparPartName7.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                                    //SparPartName.add(result);
                                    SparPartID7.add(response.body().getDetail().get(i).getId());
                                }

                                Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                                //   Log.d("MProduct", ModelName.toString());

                                ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item, SparPartName7);
                                spn_Ws_Add_SubSparPart7.setAdapter(adapter2);

                                spn_Ws_Add_SubSparPart7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Sparpart_Name7 = SparPartName7.get(position);
                                        Sparpart_ID7 = SparPartID7.get(position);

                                        WebService.getClient().getPartData(Sparpart_ID7).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                if (response.body().getDetail().size() == 0) {
                                                    Log.d("Nodata", "onResponse: No Date Available ");
                                                } else {
                                                    edt_Ws_Add_PartNumber7.setText(response.body().getDetail().get(0).getPno() + "");
                                                    edt_Ws_Add_RateDisplay7.setText(response.body().getDetail().get(0).getRate() + "");
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
                    public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

                    }
                });

        btnpo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(edt_Ws_Add_SubPart7.getText().toString().equals("")){
                    edt_Ws_Add_SubPart7.setError("Please Enter Part code");
                }
                else {
                     WebService.getClient().getPartData(edt_Ws_Add_SubPart7.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber7.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay7.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId7 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });


       /* edt_Ws_Add_SubPart7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber7.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay7.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId7 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {


                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber7.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay7.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId7 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }
                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                WebService.getClient().getPartData(edt_Ws_Add_SubPart7.getText().toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber7.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay7.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId7 = response.body().getDetail().get(0).getId();

                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/




        edt_Ws_Add_RateDisplay7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y7 = Double.parseDouble(edt_Ws_Add_RateDisplay7.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y7 = Double.parseDouble(edt_Ws_Add_RateDisplay7.getText().toString());
            }
        });


        edt_Ws_Add_Quantity7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity7.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x7 = Integer.parseInt(edt_Ws_Add_Quantity7.getText().toString());
                }


                mul7 = x7 * y7; //Perform Maths operation
                edt_Ws_Add_QMultiR7.setText("" + mul7);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity7.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x7 = Integer.parseInt(edt_Ws_Add_Quantity7.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart7.getText().toString(),
                        edt_Ws_Add_Quantity7.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, ""
                                        + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, ""
                                    + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul7 = x7 * y7; //Perform Maths operation
                edt_Ws_Add_QMultiR7.setText("" + mul7);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });

        /** **************************************** Eight *************************************************** */



         WebService.getClient().getWorkshopAddProd("Spar Part")
                .enqueue(new Callback<WorkShopAddProductModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                           @NotNull Response<WorkShopAddProductModel> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                SparPartName8.clear();
                                SparPartID8.clear();

                                SparPartName8.add("Select Model");
                                SparPartID8.add("0");

                                //    Log.d("product", response.body().toString());

                                for (int i = 0; i < response.body().getDetail().size(); i++) {
                                    String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                                    //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                                    SparPartName8.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                                    //SparPartName.add(result);
                                    SparPartID8.add(response.body().getDetail().get(i).getId());
                                }

                                Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                                //   Log.d("MProduct", ModelName.toString());

                                ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item, SparPartName8);
                                spn_Ws_Add_SubSparPart8.setAdapter(adapter2);

                                spn_Ws_Add_SubSparPart8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Sparpart_Name8 = SparPartName8.get(position);
                                        Sparpart_ID8 = SparPartID8.get(position);

                                        WebService.getClient().getPartData(Sparpart_ID8).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                if (response.body().getDetail().size() == 0) {
                                                    Log.d("Nodata", "onResponse: No Date Available ");
                                                } else {
                                                    edt_Ws_Add_PartNumber8.setText(response.body().getDetail().get(0).getPno() + "");
                                                    edt_Ws_Add_RateDisplay8.setText(response.body().getDetail().get(0).getRate() + "");
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
                    public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

                    }
                });


        btnpo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(edt_Ws_Add_SubPart8.getText().toString().equals("")){
                    edt_Ws_Add_SubPart8.setError("Please Enter Part code");
                }
                else {
                     WebService.getClient().getPartData(edt_Ws_Add_SubPart8.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber8.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay8.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId8 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });

       /* edt_Ws_Add_SubPart8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber8.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay8.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId8 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }
                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {


                            if (response.body().getDetail().size() == 0) {
                                Log.d("Nodata", "onResponse: No Date Available ");
                            } else {
                                edt_Ws_Add_PartNumber8.setText(response.body().getDetail().get(0).getPno() + "");
                                edt_Ws_Add_RateDisplay8.setText(response.body().getDetail().get(0).getRate() + "");
                                part_dataId8 = response.body().getDetail().get(0).getId();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/




        edt_Ws_Add_RateDisplay8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y8 = Double.parseDouble(edt_Ws_Add_RateDisplay8.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y8 = Double.parseDouble(edt_Ws_Add_RateDisplay8.getText().toString());
            }
        });


        edt_Ws_Add_Quantity8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity8.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x8 = Integer.parseInt(edt_Ws_Add_Quantity8.getText().toString());
                }


                mul8 = x8 * y8; //Perform Maths operation
                edt_Ws_Add_QMultiR8.setText("" + mul8);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity8.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x8 = Integer.parseInt(edt_Ws_Add_Quantity8.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart8.getText().toString(),
                        edt_Ws_Add_Quantity8.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, ""
                                        + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, ""
                                    + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul8 = x8 * y8; //Perform Maths operation
                edt_Ws_Add_QMultiR8.setText("" + mul8);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });



        /** *********************************************** Nine ******************************************** */

        WebService.getClient().getWorkshopAddProd("Spar Part")
                .enqueue(new Callback<WorkShopAddProductModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                           @NotNull Response<WorkShopAddProductModel> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                SparPartName9.clear();
                                SparPartID9.clear();

                                SparPartName9.add("Select Model");
                                SparPartID9.add("0");

                                //    Log.d("product", response.body().toString());

                                for (int i = 0; i < response.body().getDetail().size(); i++) {
                                    String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                                    //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                                    SparPartName9.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                                    //SparPartName.add(result);
                                    SparPartID9.add(response.body().getDetail().get(i).getId());
                                }

                                Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                                //   Log.d("MProduct", ModelName.toString());

                                ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item, SparPartName9);
                                spn_Ws_Add_SubSparPart9.setAdapter(adapter2);

                                spn_Ws_Add_SubSparPart9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Sparpart_Name9 = SparPartName9.get(position);
                                        Sparpart_ID9 = SparPartID9.get(position);

                                        WebService.getClient().getPartData(Sparpart_ID9).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                if (response.body().getDetail().size() == 0) {
                                                    Log.d("Nodata", "onResponse: No Date Available ");
                                                } else {
                                                    edt_Ws_Add_PartNumber9.setText(response.body().getDetail().get(0).getPno() + "");
                                                    edt_Ws_Add_RateDisplay9.setText(response.body().getDetail().get(0).getRate() + "");
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
                    public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

                    }
                });



        btnpo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(edt_Ws_Add_SubPart.getText().toString().equals("")){
                    edt_Ws_Add_SubPart.setError("Please Enter Part code");
                }
                else {


                     WebService.getClient().getPartData(edt_Ws_Add_SubPart9.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber9.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay9.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId9 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });


      /*  edt_Ws_Add_SubPart9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {


                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber9.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay9.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId9 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try {

                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber9.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay9.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId9 = response.body().getDetail().get(0).getId();

                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/



        edt_Ws_Add_RateDisplay9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y9 = Double.parseDouble(edt_Ws_Add_RateDisplay9.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y9 = Double.parseDouble(edt_Ws_Add_RateDisplay9.getText().toString());
            }
        });


        edt_Ws_Add_Quantity9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity9.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x9 = Integer.parseInt(edt_Ws_Add_Quantity9.getText().toString());
                }


                mul9 = x9 * y9; //Perform Maths operation
                edt_Ws_Add_QMultiR9.setText("" + mul9);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity9.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x9 = Integer.parseInt(edt_Ws_Add_Quantity9.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart9.getText().toString(),
                        edt_Ws_Add_Quantity9.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, ""
                                        + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, ""
                                    + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul9 = x9 * y9; //Perform Maths operation
                edt_Ws_Add_QMultiR9.setText("" + mul9);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });


        /** ********************************************* Ten ********************************************** */


         WebService.getClient().getWorkshopAddProd("Spar Part")
                .enqueue(new Callback<WorkShopAddProductModel>() {
                    @Override
                    public void onResponse(@NotNull Call<WorkShopAddProductModel> call,
                                           @NotNull Response<WorkShopAddProductModel> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                SparPartName10.clear();
                                SparPartID10.clear();

                                SparPartName10.add("Select Model");
                                SparPartID10.add("0");

                                //    Log.d("product", response.body().toString());

                                for (int i = 0; i < response.body().getDetail().size(); i++) {
                                    String result = response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", "");
                                    //SparPartName.add(response.body().getDetail().get(i).getPartname().replaceAll("[\\-\\+\\.\\%\\:\\,]",""));
                                    SparPartName10.add(response.body().getDetail().get(i).getPartname().replaceAll("[-+.^:,%]", " "));
                                    //SparPartName.add(result);
                                    SparPartID10.add(response.body().getDetail().get(i).getId());
                                }

                                Log.d("ProductS", "onResponse: " + response.body().getDetail().size());

                                //   Log.d("MProduct", ModelName.toString());

                                ArrayAdapter adapter2 = new ArrayAdapter(AddFirstActivity.this,
                                        android.R.layout.simple_spinner_dropdown_item, SparPartName10);
                                spn_Ws_Add_SubSparPart10.setAdapter(adapter2);

                                spn_Ws_Add_SubSparPart10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Sparpart_Name10 = SparPartName10.get(position);
                                        Sparpart_ID10 = SparPartID10.get(position);

                                        WebService.getClient().getPartData(Sparpart_ID10).enqueue(new Callback<PartDataModel>() {
                                            @Override
                                            public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                                                assert response.body() != null;
                                                if (response.body().getDetail().size() == 0) {
                                                    Log.d("Nodata", "onResponse: No Date Available ");
                                                } else {
                                                    edt_Ws_Add_PartNumber10.setText(response.body().getDetail().get(0).getPno() + "");
                                                    edt_Ws_Add_RateDisplay10.setText(response.body().getDetail().get(0).getRate() + "");
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

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
                    public void onFailure(@NotNull Call<WorkShopAddProductModel> call, @NotNull Throwable t) {

                    }
                });


        btnpo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(edt_Ws_Add_SubPart.getText().toString().equals("")){
                    edt_Ws_Add_SubPart.setError("Please Enter Part code");
                }
                else {
                     WebService.getClient().getPartData(edt_Ws_Add_SubPart10.getText().toString()).enqueue(new Callback<PartDataModel>() {
                         @Override
                         public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                             assert response.body() != null;
                             try {

                                 if (response.body().getDetail().size() == 0) {
                                     Log.d("Nodata", "onResponse: No Date Available ");
                                 } else {

                                     edt_Ws_Add_PartNumber10.setText(response.body().getDetail().get(0).getPno() + "");
                                     edt_Ws_Add_RateDisplay10.setText(response.body().getDetail().get(0).getRate() + "");
                                     part_dataId10 = response.body().getDetail().get(0).getId();

                                 }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                 //  Toast.makeText(AddFirstActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                         }

                         @Override
                         public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                         }
                     });
                 }
            }
        });

    /*    edt_Ws_Add_SubPart10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        try{

                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber10.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay10.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId10 = response.body().getDetail().get(0).getId();

                        }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                WebService.getClient().getPartData(s.toString()).enqueue(new Callback<PartDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PartDataModel> call, @NotNull Response<PartDataModel> response) {
                        assert response.body() != null;
                        if (response.body().getDetail().size() == 0) {
                            Log.d("Nodata", "onResponse: No Date Available ");
                        } else {
                            edt_Ws_Add_PartNumber10.setText(response.body().getDetail().get(0).getPno() + "");
                            edt_Ws_Add_RateDisplay10.setText(response.body().getDetail().get(0).getRate() + "");
                            part_dataId10 = response.body().getDetail().get(0).getId();

                        }

                                              *//* edt_Ws_Add_PartNumber.setText(response.body().getDetail().get(0).getPno()+"");
                                               edt_Ws_Add_RateDisplay.setText(response.body().getDetail().get(0).getRate()+"");*//*
                    }

                    @Override
                    public void onFailure(@NotNull Call<PartDataModel> call, @NotNull Throwable t) {

                    }
                });
            }
        });*/





        edt_Ws_Add_RateDisplay10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y10 = Double.parseDouble(edt_Ws_Add_RateDisplay10.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                y10 = Double.parseDouble(edt_Ws_Add_RateDisplay10.getText().toString());
            }
        });


        edt_Ws_Add_Quantity10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // x2 = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_Ws_Add_Quantity10.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x10 = Integer.parseInt(edt_Ws_Add_Quantity10.getText().toString());
                }


                mul10 = x10 * y10; //Perform Maths operation
                edt_Ws_Add_QMultiR10.setText("" + mul10);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edt_Ws_Add_Quantity10.getText().toString().equals("")) {
                    Log.d("ValueNull", "onTextChanged: null");
                } else {
                    x10 = Integer.parseInt(edt_Ws_Add_Quantity10.getText().toString());
                }

                // x = Integer.parseInt(edt_Ws_Add_Quantity.getText().toString());

                WebService.getClient().getQtyAvailable(edt_Ws_Add_SubPart10.getText().toString(),
                        edt_Ws_Add_Quantity10.getText().toString()).enqueue(new Callback<QtyAvailableWSModel>() {
                    @Override
                    public void onResponse(@NotNull Call<QtyAvailableWSModel> call,
                                           @NotNull Response<QtyAvailableWSModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Utils.showNormalToast(AddFirstActivity.this, ""
                                        + response.body().getMsg());
                            }
                        } else {
                            assert response.body() != null;
                            Utils.showErrorToast(AddFirstActivity.this, ""
                                    + response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QtyAvailableWSModel> call, @NotNull Throwable t) {
                        Toast.makeText(AddFirstActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                mul10 = x10 * y10; //Perform Maths operation
                edt_Ws_Add_QMultiR10.setText("" + mul10);//print answer
                // Toast.makeText(AddFirstActivity.this, ""+x+y+mul, Toast.LENGTH_SHORT).show();

            }
        });


        /** ******************************************************************************************* */

        Btn_Ws_Add_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddFirstActivity.this, FoDashbord.class);
                sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                sharedPreferences.edit().putString("NextId_WS", "").apply();
                startActivity(i);
            }
        });



        Btn_Ws_Add_Next_alpesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(AddFirstActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



                if (TextUtils.isEmpty(Sale)){
                    Utils.showErrorToast(AddFirstActivity.this,"Select Sale Type");
                    return;
                }

                if (TextUtils.isEmpty(Product_Name)){
                    Utils.showErrorToast(AddFirstActivity.this,"Select  Product");
                    return;
                }

                if (TextUtils.isEmpty(select_data)){
                    Utils.showErrorToast(AddFirstActivity.this,"Select  Work Type");
                    return;
                }

                Log.d("TAG", "onClick: complain_no "+edt_Ws_Add_complainNumber.getText().toString()+
                        " sale "+Sale+
                        " producname "+Product_Name+" selectdata "+select_data);

                WebService.getClient().getPhaseOneWS(
                        edt_Ws_Add_complainNumber.getText().toString(),
                        Sale,
                        Product_Name,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "1",
                        select_data
                ).enqueue(new Callback<PhaseOneWsAdd>() {
                    @Override
                    public void onResponse(@NotNull Call<PhaseOneWsAdd> call, @NotNull Response<PhaseOneWsAdd> response) {

                        assert response.body() != null;
                        nextId_Ws = String.valueOf(response.body().getId());

                        // double data = sumOfTotalPrice;
                       // value = (int)Math.round(sumOfTotalPrice);

                        //int sumOfTP = Integer.parseInt(String.valueOf(sumOfTotalPrice));

                        sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                        sharedPreferences.edit().putString("NextId_WS", String.valueOf(response.body().getId())).apply();
                        //sharedPreferences.edit().putInt("sumOfTotalPrice", value).apply();
                        sharedPreferences.edit().putString("Sale_list", Sale).apply();
                        //  sharedPreferences.edit().putString("Product_Name", ProductName).apply();

                        Toast.makeText(AddFirstActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                        Intent i = new Intent(AddFirstActivity.this, WsAddSecondActivity.class);
                        // Intent i = new Intent(AddFirstActivity.this, WsAddSecondActivity.class);

                        startActivity(i);
                    }

                    @Override
                    public void onFailure(@NotNull Call<PhaseOneWsAdd> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                    }
                });

            }
        });

        Btn_Ws_Add_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(AddFirstActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                //**********************************************************************************************

                if(Sparpart_ID == null )
                {
                    WS_ModelId.remove(Sparpart_ID);
                }
                else{
                    WS_ModelId.add(Sparpart_ID);
                }

                if(Sparpart_ID2 == null )
                {
                    WS_ModelId.remove(Sparpart_ID2);
                }
                else{
                    WS_ModelId.add(Sparpart_ID2);
                }

                if(Sparpart_ID3 == null )
                {
                    WS_ModelId.remove(Sparpart_ID3);
                }
                else{
                    WS_ModelId.add(Sparpart_ID3);
                }

                if(Sparpart_ID4 == null )
                {
                    WS_ModelId.remove(Sparpart_ID4);
                }
                else{
                    WS_ModelId.add(Sparpart_ID4);
                }

                if(Sparpart_ID5 == null )
                {
                    WS_ModelId.remove(Sparpart_ID5);
                }
                else{
                    WS_ModelId.add(Sparpart_ID5);
                }

                if(Sparpart_ID6 == null )
                {
                    WS_ModelId.remove(Sparpart_ID6);
                }
                else{
                    WS_ModelId.add(Sparpart_ID6);
                }

                if(Sparpart_ID7 == null )
                {
                    WS_ModelId.remove(Sparpart_ID7);
                }
                else{
                    WS_ModelId.add(Sparpart_ID7);
                }

                if(Sparpart_ID8 == null )
                {
                    WS_ModelId.remove(Sparpart_ID8);
                }
                else{
                    WS_ModelId.add(Sparpart_ID8);
                }

                if(Sparpart_ID9 == null )
                {
                    WS_ModelId.remove(Sparpart_ID9);
                }
                else{
                    WS_ModelId.add(Sparpart_ID9);
                }

                if(Sparpart_ID10 == null )
                {
                    WS_ModelId.remove(Sparpart_ID10);
                }
                else{
                    WS_ModelId.add(Sparpart_ID10);
                }

                String Ws_ModelIdArrayItem = "";

                for(int i=0; i<=WS_ModelId.size();i++)
                {
                    if(Ws_ModelIdArrayItem.equals("")){
                        try {
                            Ws_ModelIdArrayItem = WS_ModelId.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            Ws_ModelIdArrayItem = Ws_ModelIdArrayItem+","+WS_ModelId.get(i);
                            Log.d("BBList_ll", "onCreate: "+Ws_ModelIdArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

              //  Toast.makeText(AddFirstActivity.this, ""+Ws_ModelIdArrayItem, Toast.LENGTH_SHORT).show();

                //*********************************************************************************************

                 if(Sparpart_Name == null || Sparpart_Name.equals("Select Model"))
                {
                    WS_PartName.remove(Sparpart_Name);
                }
                else{
                     WS_PartName.add(Sparpart_Name);
                }

                if(Sparpart_Name2 == null || Sparpart_Name2.equals("Select Model"))
                {
                    WS_PartName.remove(Sparpart_Name2);
                }
                else{
                    WS_PartName.add(Sparpart_Name2);
                }

                if(Sparpart_Name3 == null || Sparpart_Name3.equals("Select Model"))
                {
                    WS_PartName.remove(Sparpart_Name3);
                }
                else{
                    WS_PartName.add(Sparpart_Name3);
                }

                if(Sparpart_Name4 == null || Sparpart_Name4.equals("Select Model"))
                {
                    WS_PartName.remove(Sparpart_Name4);
                }
                else{
                    WS_PartName.add(Sparpart_Name4);
                }

                if(Sparpart_Name5 == null || Sparpart_Name5.equals("Select Model") )
                {
                    WS_PartName.remove(Sparpart_Name5);
                }
                else{
                    WS_PartName.add(Sparpart_Name5);
                }

                if(Sparpart_Name6 == null || Sparpart_Name6.equals("Select Model"))
                {
                    WS_PartName.remove(Sparpart_Name6);
                }
                else{
                    WS_PartName.add(Sparpart_Name6);
                }

                if(Sparpart_Name7 == null || Sparpart_Name7.equals("Select Type"))
                {
                    WS_PartName.remove(Sparpart_Name7);
                }
                else{
                    WS_PartName.add(Sparpart_Name7);
                }

                if(Sparpart_Name8 == null || Sparpart_Name8.equals("Select Model"))
                {
                    WS_PartName.remove(Sparpart_Name8);
                }
                else{
                    WS_PartName.add(Sparpart_Name8);
                }

                if(Sparpart_Name9 == null || Sparpart_Name9.equals("Select Model"))
                {
                    WS_PartName.remove(Sparpart_Name9);
                }
                else{
                    WS_PartName.add(Sparpart_Name9);
                }

                if(Sparpart_Name10 == null || Sparpart_Name10.equals("Select Model"))
                {
                    WS_PartName.remove(Sparpart_Name10);
                }
                else{
                    WS_PartName.add(Sparpart_Name10);
                }

                String Ws_Sparpart_NameArrayItem = "";

                for(int i=0; i<=WS_PartName.size();i++)
                {
                    if(Ws_Sparpart_NameArrayItem.equals("")){
                        try {
                            Ws_Sparpart_NameArrayItem = WS_PartName.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            Ws_Sparpart_NameArrayItem = Ws_Sparpart_NameArrayItem+","+WS_PartName.get(i);
                            Log.d("BBList_ll", "onCreate: "+Ws_Sparpart_NameArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                //**********************************************************************************************

                if(part_dataId == null )
                {
                    WS_PartID_newAdd.remove(part_dataId);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId);
                }

                if(part_dataId2 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId2);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId2);
                }


                if(part_dataId3 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId3);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId3);
                }


                if(part_dataId4 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId4);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId4);
                }

                if(part_dataId5 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId5);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId5);
                }

                if(part_dataId6 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId6);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId6);
                }

                if(part_dataId7 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId7);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId7);
                }

                if(part_dataId8 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId8);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId8);
                }


                if(part_dataId9 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId9);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId9);
                }


                if(part_dataId10 == null )
                {
                    WS_PartID_newAdd.remove(part_dataId10);
                }
                else{
                    WS_PartID_newAdd.add(part_dataId10);
                }


                String Ws_PartId_ArrayList = "";

                for(int i=0; i<=WS_PartID_newAdd.size();i++)
                {
                    if(Ws_PartId_ArrayList.equals("")){
                        try {
                            Ws_PartId_ArrayList = WS_PartID_newAdd.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            Ws_PartId_ArrayList = Ws_PartId_ArrayList+","+WS_PartID_newAdd.get(i);
                            Log.d("BBList_ll", "onCreate: "+Ws_PartId_ArrayList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


                //**********************************************************************************************

                String PartName_new = edt_Ws_Add_SubPart.getText().toString();
                if(PartName_new.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new);
                }

                String PartName_new2 = edt_Ws_Add_SubPart2.getText().toString();
                if(PartName_new2.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new2);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new2);
                }


                String PartName_new3 = edt_Ws_Add_SubPart3.getText().toString();
                if(PartName_new3.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new3);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new3);
                }

                String PartName_new4 = edt_Ws_Add_SubPart4.getText().toString();
                if(PartName_new4.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new4);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new4);
                }


                String PartName_new5 = edt_Ws_Add_SubPart5.getText().toString();
                if(PartName_new5.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new5);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new5);
                }


                String PartName_new6 = edt_Ws_Add_SubPart6.getText().toString();
                if(PartName_new6.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new6);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new6);
                }


                String PartName_new7 = edt_Ws_Add_SubPart7.getText().toString();
                if(PartName_new7.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new7);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new7);
                }


                String PartName_new8 = edt_Ws_Add_SubPart8.getText().toString();
                if(PartName_new8.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new8);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new8);
                }

                String PartName_new9 = edt_Ws_Add_SubPart9.getText().toString();
                if(PartName_new9.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new9);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new9);
                }


                String PartName_new10 = edt_Ws_Add_SubPart10.getText().toString();
                if(PartName_new10.length() == 0)
                {
                    WS_PartName_newAdd.remove(PartName_new10);
                }
                else{
                    WS_PartName_newAdd.add(PartName_new10);
                }


                String PartName_new_ArrayItem = "";

                for(int i=0; i<=WS_PartName_newAdd.size();i++)
                {
                    if(PartName_new_ArrayItem.equals("")){
                        try {
                            PartName_new_ArrayItem = WS_PartName_newAdd.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            PartName_new_ArrayItem = PartName_new_ArrayItem+","+WS_PartName_newAdd.get(i);
                            Log.d("BBList_ll", "onCreate: "+PartName_new_ArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


                //************************************************************************************************

                String PartNum = edt_Ws_Add_PartNumber.getText().toString().trim();

                 if(PartNum.length() == 0)
                {
                    WS_PartNumber.remove(PartNum);
                }
                else{
                     WS_PartNumber.add(PartNum);
                }

                String PartNum2 = edt_Ws_Add_PartNumber2.getText().toString().trim();

                if(PartNum2.length() == 0)
                {
                    WS_PartNumber.remove(PartNum2);
                }
                else{
                    WS_PartNumber.add(PartNum2);
                }

                String PartNum3 = edt_Ws_Add_PartNumber3.getText().toString().trim();

                if(PartNum3.length() == 0)
                {
                    WS_PartNumber.remove(PartNum3);
                }
                else{
                    WS_PartNumber.add(PartNum3);
                }


                String PartNum4 = edt_Ws_Add_PartNumber4.getText().toString().trim();

                if(PartNum4.length() == 0)
                {
                    WS_PartNumber.remove(PartNum4);
                }
                else{
                    WS_PartNumber.add(PartNum4);
                }

                String PartNum5 = edt_Ws_Add_PartNumber5.getText().toString().trim();

                if(PartNum5.length() == 0)
                {
                    WS_PartNumber.remove(PartNum5);
                }
                else{
                    WS_PartNumber.add(PartNum5);
                }

                String PartNum6 = edt_Ws_Add_PartNumber6.getText().toString().trim();

                if(PartNum6.length() == 0)
                {
                    WS_PartNumber.remove(PartNum6);
                }
                else{
                    WS_PartNumber.add(PartNum6);
                }


                String PartNum7 = edt_Ws_Add_PartNumber7.getText().toString().trim();

                if(PartNum7.length() == 0)
                {
                    WS_PartNumber.remove(PartNum7);
                }
                else{
                    WS_PartNumber.add(PartNum7);
                }

                String PartNum8 = edt_Ws_Add_PartNumber8.getText().toString();

                if(PartNum8.length() == 0)
                {
                    WS_PartNumber.remove(PartNum8);
                }
                else{
                    WS_PartNumber.add(PartNum8);
                }

                String PartNum9 = edt_Ws_Add_PartNumber9.getText().toString().trim();

                if(PartNum9.length() == 0)
                {
                    WS_PartNumber.remove(PartNum9);
                }
                else{
                    WS_PartNumber.add(PartNum9);
                }

                String PartNum10 = edt_Ws_Add_PartNumber10.getText().toString().trim();

                if(PartNum10.length() == 0 )
                {
                    WS_PartNumber.remove(PartNum10);
                }
                else{
                    WS_PartNumber.add(PartNum10);
                }

                String Ws_PartNumber_ArrayItem = "";

                for(int i=0; i<=WS_PartNumber.size();i++)
                {
                    if(Ws_PartNumber_ArrayItem.equals("")){
                        try {
                            Ws_PartNumber_ArrayItem = WS_PartNumber.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            Ws_PartNumber_ArrayItem = Ws_PartNumber_ArrayItem+","+WS_PartNumber.get(i);
                            Log.d("BBList_ll", "onCreate: "+Ws_PartNumber_ArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }





                //************************************************************************************************

                String Quantity = edt_Ws_Add_Quantity.getText().toString().trim();


                if(Quantity.length()==0)
                {
                    WS_Quantity.remove(Quantity);
                }
                else{
                    WS_Quantity.add(Quantity);
                }

                String Quantity2 = edt_Ws_Add_Quantity2.getText().toString().trim();

                if(Quantity2.length()==0)
                {
                    WS_Quantity.remove(Quantity2);
                }
                else{
                    WS_Quantity.add(Quantity2);
                }


                String Quantity3 = edt_Ws_Add_Quantity3.getText().toString().trim();


                if(Quantity3.length()==0)
                {
                    WS_Quantity.remove(Quantity3);
                }
                else{
                    WS_Quantity.add(Quantity3);
                }

                String Quantity4 = edt_Ws_Add_Quantity4.getText().toString().trim();


                if(Quantity4.length()==0)
                {
                    WS_Quantity.remove(Quantity4);
                }
                else{
                    WS_Quantity.add(Quantity4);
                }

                String Quantity5 = edt_Ws_Add_Quantity5.getText().toString().trim();


                if(Quantity5.length()==0 )
                {
                    WS_Quantity.remove(Quantity5);
                }
                else{
                    WS_Quantity.add(Quantity5);
                }

                String Quantity6 = edt_Ws_Add_Quantity6.getText().toString().trim();


                if(Quantity6.length()==0)
                {
                    WS_Quantity.remove(Quantity6);
                }
                else{
                    WS_Quantity.add(Quantity6);
                }

                String Quantity7 = edt_Ws_Add_Quantity7.getText().toString().trim();


                if(Quantity7.length()==0)
                {
                    WS_Quantity.remove(Quantity7);
                }
                else{
                    WS_Quantity.add(Quantity7);
                }

                String Quantity8 = edt_Ws_Add_Quantity8.getText().toString().trim();


                if(Quantity8.length()==0)
                {
                    WS_Quantity.remove(Quantity8);
                }
                else{
                    WS_Quantity.add(Quantity8);
                }

                String Quantity9 = edt_Ws_Add_Quantity9.getText().toString().trim();


                if(Quantity9.length()==0 )
                {
                    WS_Quantity.remove(Quantity9);
                }
                else{
                    WS_Quantity.add(Quantity9);
                }

                String Quantity10 = edt_Ws_Add_Quantity10.getText().toString().trim();


                if(Quantity10.length()==0)
                {
                    WS_Quantity.remove(Quantity10);
                }
                else{
                    WS_Quantity.add(Quantity10);
                }

                String WS_QuantityArrayItem = "";

                for(int i=0; i<=WS_Quantity.size();i++)
                {
                    if(WS_QuantityArrayItem.equals("")){
                        try {
                            WS_QuantityArrayItem = WS_Quantity.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            WS_QuantityArrayItem = WS_QuantityArrayItem+","+WS_Quantity.get(i);
                            Log.d("BBList_ll", "onCreate: "+Ws_PartNumber_ArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                //*********************************************************************************************

                String Rate = edt_Ws_Add_RateDisplay.getText().toString().trim();


                if(Rate.length()==0)
                {
                    WS_Rate.remove(Rate);
                }
                else{
                     WS_Rate.add(Rate);
                }

                String Rate2 = edt_Ws_Add_RateDisplay2.getText().toString().trim();


                if(Rate2.length()==0)
                {
                    WS_Rate.remove(Rate2);
                }
                else{
                    WS_Rate.add(Rate2);
                }

                String Rate3 = edt_Ws_Add_RateDisplay3.getText().toString().trim();


                if(Rate3.length()==0)
                {
                    WS_Rate.remove(Rate3);
                }
                else{
                    WS_Rate.add(Rate3);
                }

                String Rate4 = edt_Ws_Add_RateDisplay4.getText().toString().trim();


                if(Rate4.length()==0)
                {
                    WS_Rate.remove(Rate4);
                }
                else{
                    WS_Rate.add(Rate4);
                }

                String Rate5 = edt_Ws_Add_RateDisplay5.getText().toString().trim();


                if(Rate5.length()==0 )
                {
                    WS_Rate.remove(Rate5);
                }
                else{
                    WS_Rate.add(Rate5);
                }

                String Rate6 = edt_Ws_Add_RateDisplay6.getText().toString().trim();


                if(Rate6.length()==0)
                {
                    WS_Rate.remove(Rate6);
                }
                else{
                    WS_Rate.add(Rate6);
                }

                String Rate7 = edt_Ws_Add_RateDisplay7.getText().toString().trim();


                if(Rate7.length()==0)
                {
                    WS_Rate.remove(Rate7);
                }
                else{
                    WS_Rate.add(Rate7);
                }

                String Rate8 = edt_Ws_Add_RateDisplay8.getText().toString().trim();


                if(Rate8.length()==0)
                {
                    WS_Rate.remove(Rate8);
                }
                else{
                    WS_Rate.add(Rate8);
                }

                String Rate9 = edt_Ws_Add_RateDisplay9.getText().toString().trim();


                if(Rate9.length()==0)
                {
                    WS_Rate.remove(Rate9);
                }
                else{
                    WS_Rate.add(Rate9);
                }

                String Rate10 = edt_Ws_Add_RateDisplay10.getText().toString().trim();


                if(Rate10.length()==0 )
                {
                    WS_Rate.remove(Rate10);
                }
                else{
                    WS_Rate.add(Rate10);
                }

                String WS_RateArrayItem = "";

                for(int i=0; i<=WS_Rate.size();i++)
                {
                    if(WS_RateArrayItem.equals("")){
                        try {
                            WS_RateArrayItem = WS_Rate.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            WS_RateArrayItem = WS_RateArrayItem+","+WS_Rate.get(i);
                            Log.d("BBList_ll", "onCreate: "+WS_RateArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


                //*********************************************************************************************

                String TotalPrice = edt_Ws_Add_QMultiR.getText().toString().trim();


                if(TotalPrice.length()==0 )
                {
                    WS_TotalPrice.remove(TotalPrice);

                }
                else{
                     WS_TotalPrice.add(TotalPrice);

                    //sumOfTotalPrice = Integer.parseInt(TotalPrice);
                    sumOfTotalPrice = Double.parseDouble(TotalPrice);
                }

                String TotalPrice2 = edt_Ws_Add_QMultiR2.getText().toString().trim();


                if(TotalPrice2.length()==0 )
                {
                    WS_TotalPrice.remove(TotalPrice2);
                }
                else{
                    WS_TotalPrice.add(TotalPrice2);

                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice2);
                }

                String TotalPrice3 = edt_Ws_Add_QMultiR3.getText().toString().trim();


                if(TotalPrice3.length()==0 )
                {
                    WS_TotalPrice.remove(TotalPrice3);
                }
                else{
                    WS_TotalPrice.add(TotalPrice3);

                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice3);
                }

                String TotalPrice4 = edt_Ws_Add_QMultiR4.getText().toString().trim();


                if(TotalPrice4.length()==0)
                {
                    WS_TotalPrice.remove(TotalPrice4);
                }
                else{
                    WS_TotalPrice.add(TotalPrice4);

                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice4);
                }

                String TotalPrice5 = edt_Ws_Add_QMultiR5.getText().toString().trim();


                if(TotalPrice5.length()==0)
                {
                    WS_TotalPrice.remove(TotalPrice5);
                }
                else{
                    WS_TotalPrice.add(TotalPrice5);
                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice5);
                }

                String TotalPrice6 = edt_Ws_Add_QMultiR6.getText().toString().trim();


                if(TotalPrice6.length()==0 )
                {
                    WS_TotalPrice.remove(TotalPrice6);
                }
                else{
                    WS_TotalPrice.add(TotalPrice6);
                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice6);
                }

                String TotalPrice7 = edt_Ws_Add_QMultiR7.getText().toString().trim();


                if(TotalPrice7.length()==0)
                {
                    WS_TotalPrice.remove(TotalPrice7);
                }
                else{
                    WS_TotalPrice.add(TotalPrice7);
                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice7);
                }

                String TotalPrice8 = edt_Ws_Add_QMultiR8.getText().toString().trim();


                if(TotalPrice8.length()==0 )
                {
                    WS_TotalPrice.remove(TotalPrice8);
                }
                else{
                    WS_TotalPrice.add(TotalPrice8);
                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice8);
                }

                String TotalPrice9 = edt_Ws_Add_QMultiR9.getText().toString().trim();


                if(TotalPrice9.length()==0)
                {
                    WS_TotalPrice.remove(TotalPrice9);
                }
                else{
                    WS_TotalPrice.add(TotalPrice9);
                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice9);
                }

                String TotalPrice10 = edt_Ws_Add_QMultiR10.getText().toString().trim();


                if(TotalPrice10.length()==0)
                {
                    WS_TotalPrice.remove(TotalPrice10);
                }
                else{
                    WS_TotalPrice.add(TotalPrice10);
                    sumOfTotalPrice = sumOfTotalPrice+Double.parseDouble(TotalPrice10);
                }

                String WS_RateMulQuatityArrayItem = "";

                for(int i=0; i<=WS_TotalPrice.size();i++)
                {
                    if(WS_RateMulQuatityArrayItem.equals("")){
                        try {
                            WS_RateMulQuatityArrayItem = WS_TotalPrice.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            WS_RateMulQuatityArrayItem = WS_RateMulQuatityArrayItem+","+WS_TotalPrice.get(i);
                            Log.d("BBList_ll", "onCreate: "+WS_RateArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

              //  Toast.makeText(AddFirstActivity.this, ""+sumOfTotalPrice, Toast.LENGTH_SHORT).show();

                
                if(edt_Ws_Add_SubPart.getText().toString().equals("")){
                    edt_Ws_Add_SubPart.setError("Please enter Part");
                }

                if(edt_Ws_Add_PartNumber.getText().toString().equals("")){
                    edt_Ws_Add_PartNumber.setError("Please enter Part Number");
                }

                if(edt_Ws_Add_Quantity.getText().toString().equals("")){
                    edt_Ws_Add_Quantity.setError("Please enter Quality");
                }

                if(edt_Ws_Add_RateDisplay.getText().toString().equals("")){
                    edt_Ws_Add_RateDisplay.setError("Please enter Rate");
                }

                if(edt_Ws_Add_QMultiR.getText().toString().equals("")){
                    edt_Ws_Add_QMultiR.setError("Please enter value");
                }

                 if(Sale.equals("Select Type")){
                     Utils.showErrorToast(AddFirstActivity.this,"Please select Type");
                 }

                if(Product_Name.equals("Select Product")){
                    Utils.showErrorToast(AddFirstActivity.this,"Please Select Product");
                }


                if(linNoTwo.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart2.getText().toString().equals("")){
                        edt_Ws_Add_SubPart2.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber2.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber2.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity2.getText().toString().equals("")){
                        edt_Ws_Add_Quantity2.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay2.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay2.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR2.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR2.setError("Please enter value");
                    }
                }


                if(linNoThree.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart3.getText().toString().equals("")){
                        edt_Ws_Add_SubPart3.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber3.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber3.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity3.getText().toString().equals("")){
                        edt_Ws_Add_Quantity3.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay3.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay3.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR2.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR2.setError("Please enter value");
                    }
                }


                if(linNoFour.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart4.getText().toString().equals("")){
                        edt_Ws_Add_SubPart4.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber4.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber4.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity4.getText().toString().equals("")){
                        edt_Ws_Add_Quantity4.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay4.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay4.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR4.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR4.setError("Please enter value");
                    }
                }


                if(linNoFive.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart5.getText().toString().equals("")){
                        edt_Ws_Add_SubPart5.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber5.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber5.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity5.getText().toString().equals("")){
                        edt_Ws_Add_Quantity5.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay5.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay5.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR5.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR5.setError("Please enter value");
                    }
                }

                if(linNoSix.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart6.getText().toString().equals("")){
                        edt_Ws_Add_SubPart6.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber6.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber6.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity6.getText().toString().equals("")){
                        edt_Ws_Add_Quantity6.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay6.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay6.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR6.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR6.setError("Please enter value");
                    }
                }

                if(linNoSeven.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart7.getText().toString().equals("")){
                        edt_Ws_Add_SubPart7.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber7.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber7.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity7.getText().toString().equals("")){
                        edt_Ws_Add_Quantity7.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay7.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay7.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR7.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR7.setError("Please enter value");
                    }
                }


                if(linNoEight.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart8.getText().toString().equals("")){
                        edt_Ws_Add_SubPart8.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber8.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber8.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity8.getText().toString().equals("")){
                        edt_Ws_Add_Quantity8.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay8.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay8.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR8.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR8.setError("Please enter value");
                    }
                }


                if(linNoNine.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart9.getText().toString().equals("")){
                        edt_Ws_Add_SubPart9.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber9.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber9.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity9.getText().toString().equals("")){
                        edt_Ws_Add_Quantity9.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay9.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay9.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR9.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR9.setError("Please enter value");
                    }
                }


                if(linNoTen.getVisibility()==View.VISIBLE){
                    if(edt_Ws_Add_SubPart10.getText().toString().equals("")){
                        edt_Ws_Add_SubPart10.setError("Please enter Part");
                    }

                    if(edt_Ws_Add_PartNumber10.getText().toString().equals("")){
                        edt_Ws_Add_PartNumber10.setError("Please enter Part Number");
                    }

                    if(edt_Ws_Add_Quantity10.getText().toString().equals("")){
                        edt_Ws_Add_Quantity10.setError("Please enter Quality");
                    }

                    if(edt_Ws_Add_RateDisplay10.getText().toString().equals("")){
                        edt_Ws_Add_RateDisplay10.setError("Please enter Rate");
                    }

                    if(edt_Ws_Add_QMultiR10.getText().toString().equals("")){
                        edt_Ws_Add_QMultiR10.setError("Please enter value");
                    }
                }

                //*********************************************************************************************
                /* Ws_ModelIdArrayItem,*/

                if (!edt_Ws_Add_SubPart.getText().toString().equals("") &&
                        !Sale.equals("Select Option") &&
                        !Product_Name.equals("Select Option") &&
                        !edt_Ws_Add_PartNumber.getText().toString().equals("")&&
                        !edt_Ws_Add_RateDisplay.getText().toString().equals("")&&
                        !edt_Ws_Add_QMultiR.getText().toString().equals("")&&
                        !edt_Ws_Add_Quantity.getText().toString().equals("")) {


                    WebService.getClient().getPhaseOneWS(
                            edt_Ws_Add_complainNumber.getText().toString(),
                            Sale,
                            Product_Name,
                            Ws_PartId_ArrayList,
                            Ws_PartNumber_ArrayItem,
                            PartName_new_ArrayItem,
                            WS_QuantityArrayItem,
                            WS_RateArrayItem,
                            WS_RateMulQuatityArrayItem,
                            "1",
                            select_data
                    ).enqueue(new Callback<PhaseOneWsAdd>() {
                        @Override
                        public void onResponse(@NotNull Call<PhaseOneWsAdd> call, @NotNull Response<PhaseOneWsAdd> response) {

                            assert response.body() != null;
                            nextId_Ws = String.valueOf(response.body().getId());

                           // double data = sumOfTotalPrice;
                            int value = (int)Math.round(sumOfTotalPrice);

                            //int sumOfTP = Integer.parseInt(String.valueOf(sumOfTotalPrice));

                            sharedPreferences = getSharedPreferences("NextId_WS", MODE_PRIVATE);
                            sharedPreferences.edit().putString("NextId_WS", String.valueOf(response.body().getId())).apply();
                            sharedPreferences.edit().putInt("sumOfTotalPrice", value).apply();
                            sharedPreferences.edit().putString("Sale_list", Sale).apply();
                            //  sharedPreferences.edit().putString("Product_Name", ProductName).apply();

                            Toast.makeText(AddFirstActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            progressDialog.dismiss();

                            Intent i = new Intent(AddFirstActivity.this, WsAddSecondActivity.class);
                           // Intent i = new Intent(AddFirstActivity.this, WsAddSecondActivity.class);

                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<PhaseOneWsAdd> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

    }
}