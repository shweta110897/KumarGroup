package com.example.kumarGroup.ViewInquiryDealStage.Add;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kumarGroup.DownPaymentNextModel;
import com.example.kumarGroup.MakeDownPaymentModel;
import com.example.kumarGroup.ModelDownPaymentModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownPaymentDealstageActivity extends AppCompatActivity {

   Spinner spn_DownPayment_BAmount,spn_DownPayment_BankOpt,spn_DownPayment_SelectMake,
           spn_DownPayment_ModelVehicle,spn_DownPayment_PaperEnhance,spn_DownPayment_NOC;

   EditText edt_DownPayment_CashAmount,edt_DownPayment_ChequeDate,edt_DownPayment_ChequeAmount,
           edt_DownPayment_NEFT_RTGS_date,edt_DownPayment_NEFT_RTGSAmount,edt_DownPayment_ManufactureYear,edt_DownPayment_Registeration_Number,
           edt_DownPayment_oldTractorAmount,edt_DownPayment_oldAmount;

   LinearLayout lin_dp_cheque,lin_dp_NEFT_RTGS,lin_dp_NocPhoto,lin_dp_OldVehicle,
           lin_dp_Rcbook,lin_dp_Election;

   LinearLayout lin_dp_Rcbook2,lin_dp_Election2;

   ImageView img_DownPayment_Cheque,img_DownPayment_NEFT_RTGS,img_DownPayment_NocPhoto,
           img_DownPayment_OldVehicle,img_DownPayment_RcBook,img_DownPayment_ElectionPhoto;

   ImageView img_DownPayment_RcBook2,img_DownPayment_ElectionPhoto2;


   TextView txt_DownPayment_UploadCheque,txt_DownPayment_UploadNEFT_RTGS,txt_DownPayment_UploadNocPhoto,
           txt_DownPayment_UploadOldVehicle,txt_DownPayment_UploadRCBook,txt_DownPayment_UploadElectionPhoto;

   TextView txt_DownPayment_UploadRCBook2,txt_DownPayment_UploadElectionPhoto2;

   //----------------------------------------

   Button btn_DownPayment_Next;

    //----------------------------------------


    String dp_bookingAmount;
  //  String[] DpBookingAmount = {"Select Booking Amount","Cash","Bank","Old Vehicle"};
    String[] DpBookingAmount = {"Select Booking Amount(Cash)","Cash"};

    String dp_Bank;
    String[] DpBank = {"Select Bank","Cheque","NEFT/RTGS"};

    List<String>  MakeDP_ID = new ArrayList<>();
    List<String>  makeDp_Name = new ArrayList<>();

    String MakeID, MakeName, modelName;

    List<String>  ModelDP_ID = new ArrayList<>();
    List<String>  modelDp_Name = new ArrayList<>();

    String PaperEnhance_DP;
    String[] PaperEnhance = {"Paper Expense","Dealer","Customer"};

    String Noc_DP;
    String[] NOC= {"NOC","Yes","No"};

    Calendar mcurrentdate;
    int day, month, year;

    Uri uriUploadCheque, uriUploadNEFT_RTGS, uriUploadNocPhoto, uriUploadOldVehicle, uriUploadRCBook,
            uriUploadElectionPhoto;

    Uri uriUploadRCBook2,uriUploadElectionPhoto2;

    String  waypathUploadRCBook2, waypathUploadElectionPhoto2;

    String waypathUploadCheque, waypathUploadNEFT_RTGS, waypathUploadNocPhoto,
            waypathUploadOldVehicle, waypathUploadRCBook, waypathUploadElectionPhoto;

    //---------------------------------------------------------

    SharedPreferences sharedPreferences;
    String NextIDD;

    ProgressDialog progressDialog;

    int number, number_cashAmount;
    int number_2,number_neftRtgs;
    int number_3;
    int number_4;
    int number_oldamount;

    int numberCash,NumberCheque,NumberNeftRtgs, NumberOldVehical;

    String CashAmount, ChequeAmount, NeftRtgsAmount, OldVehicleAmount;

    String AddData;

    String CashAmount_2, ChequeAmount_2, NeftRtgsAmount_2, OldVehicleAmount_2;

    String CashAmount_3, ChequeAmount_3, NeftRtgsAmount_3, OldVehicleAmount_3,oldTAmt_3;

    String CashAmount_4, ChequeAmount_4, NeftRtgsAmount_4, OldVehicleAmount_4;


    public  int x;
    public  int y;
    int addition;

    String additionThree;


    //------------------------------------------------
    ImageView imgDownPayment_AddOne,imgDownPayment_AddTwo_2,imgDownPayment_AddTwo_3;

    LinearLayout Lin_Downp_OneData, Lin_Downp_twoData,Lin_Downp_thirdData,Lin_Downp_FourthData;

    int flag = 0,flag1=0, flag2=0;


    //--------------------------------------Second data-----------------------------------------------------------------

    Spinner spn_DownPayment_BAmount_2,spn_DownPayment_BankOpt_2,spn_DownPayment_SelectMake_2,
            spn_DownPayment_ModelVehicle_2,spn_DownPayment_PaperEnhance_2,spn_DownPayment_NOC_2;

    EditText edt_DownPayment_CashAmount_2,edt_DownPayment_ChequeDate_2,edt_DownPayment_ChequeAmount_2,
            edt_DownPayment_NEFT_RTGS_date_2,edt_DownPayment_NEFT_RTGSAmount_2,edt_DownPayment_ManufactureYear_2,
            edt_DownPayment_oldTractorAmount_2,edt_DownPayment_oldAmount_2;

    LinearLayout lin_dp_cheque_2,lin_dp_NEFT_RTGS_2,lin_dp_NocPhoto_2,lin_dp_OldVehicle_2,
            lin_dp_Rcbook_2,lin_dp_Election_2;

    LinearLayout lin_dp_Rcbook2_2,lin_dp_Election2_2;


    ImageView img_DownPayment_Cheque_2,img_DownPayment_NEFT_RTGS_2,img_DownPayment_NocPhoto_2,
            img_DownPayment_OldVehicle_2,img_DownPayment_RcBook_2,img_DownPayment_ElectionPhoto_2;

    ImageView img_DownPayment_RcBook2_2,img_DownPayment_ElectionPhoto2_2;


    TextView txt_DownPayment_UploadCheque_2,txt_DownPayment_UploadNEFT_RTGS_2,txt_DownPayment_UploadNocPhoto_2,
            txt_DownPayment_UploadOldVehicle_2,txt_DownPayment_UploadRCBook_2,
            txt_DownPayment_UploadElectionPhoto_2;

    TextView txt_DownPayment_UploadRCBook2_2,txt_DownPayment_UploadElectionPhoto2_2;


    String dp_bookingAmount_2;
   // String[] DpBookingAmount_2 = {"Select Booking Amount","Cash","Bank","Old Vehicle"};
    String[] DpBookingAmount_2 = {"Select Booking Amount(Bank)","Bank"};

    String dp_Bank_2;
    String[] DpBank_2 = {"Select Bank","Cheque","NEFT/RTGS"};

    List<String>  MakeDP_ID_2 = new ArrayList<>();
    List<String>  makeDp_Name_2 = new ArrayList<>();

    String MakeID_2, MakeName_2, modelName_2;

    List<String>  ModelDP_ID_2 = new ArrayList<>();
    List<String>  modelDp_Name_2 = new ArrayList<>();

    String PaperEnhance_DP_2;
    String[] PaperEnhance_2 = {"Paper Expense","Dealer","Customer"};

    String Noc_DP_2;
    String[] NOC_2= {"NOC","Yes","No"};


    Uri uriUploadCheque_2, uriUploadNEFT_RTGS_2, uriUploadNocPhoto_2, uriUploadOldVehicle_2,
            uriUploadRCBook_2, uriUploadElectionPhoto_2;

    Uri uriUploadRCBook2_2,uriUploadElectionPhoto2_2;

    String  waypathUploadRCBook2_2, waypathUploadElectionPhoto2_2;

    String waypathUploadCheque_2, waypathUploadNEFT_RTGS_2, waypathUploadNocPhoto_2,
            waypathUploadOldVehicle_2, waypathUploadRCBook_2, waypathUploadElectionPhoto_2;


    //----------------------------------------------Third data-------------------------------------------------------------

    Spinner spn_DownPayment_BAmount_3,spn_DownPayment_BankOpt_3,spn_DownPayment_SelectMake_3,
            spn_DownPayment_ModelVehicle_3,spn_DownPayment_PaperEnhance_3,spn_DownPayment_RCBOOK,
            spn_DownPayment_OwnerKYC,spn_DownPayment_Loan4Close,spn_DownPayment_NOC_3;

    EditText edt_DownPayment_CashAmount_3,edt_DownPayment_ChequeDate_3,edt_DownPayment_ChequeAmount_3,
            edt_DownPayment_NEFT_RTGS_date_3,edt_DownPayment_NEFT_RTGSAmount_3,
            edt_DownPayment_ManufactureYear_3,edt_DownPayment_Registeration_Number_3,
            edt_DownPayment_oldTractorAmount_3,edt_DownPayment_oldAmount_3;

    LinearLayout lin_dp_cheque_3,lin_dp_NEFT_RTGS_3,lin_dp_NocPhoto_3,lin_dp_OldVehicle_3,
            lin_dp_Rcbook_3,lin_dp_Election_3;

    LinearLayout lin_dp_Rcbook2_3,lin_dp_Election2_3;


    ImageView img_DownPayment_Cheque_3,img_DownPayment_NEFT_RTGS_3,img_DownPayment_NocPhoto_3,
            img_DownPayment_OldVehicle_3,img_DownPayment_RcBook_3,img_DownPayment_ElectionPhoto_3;

    ImageView img_DownPayment_RcBook2_3,img_DownPayment_ElectionPhoto2_3;


    TextView txt_DownPayment_UploadCheque_3,txt_DownPayment_UploadNEFT_RTGS_3,txt_DownPayment_UploadNocPhoto_3,
            txt_DownPayment_UploadOldVehicle_3,txt_DownPayment_UploadRCBook_3,
            txt_DownPayment_UploadElectionPhoto_3;

    TextView txt_DownPayment_UploadRCBook2_3,txt_DownPayment_UploadElectionPhoto2_3;

    String dp_bookingAmount_3;
   // String[] DpBookingAmount_3 = {"Select Booking Amount","Cash","Bank","Old Vehicle"};
    String[] DpBookingAmount_3 = {"Select Booking Amount (Old Vehicle)","Old Vehicle"};

    String dp_Bank_3;
    String[] DpBank_3 = {"Select Bank","Cheque","NEFT/RTGS"};

    List<String>  MakeDP_ID_3 = new ArrayList<>();
    List<String>  makeDp_Name_3 = new ArrayList<>();

    String MakeID_3, MakeName_3, modelName_3;

    List<String>  ModelDP_ID_3 = new ArrayList<>();
    List<String>  modelDp_Name_3 = new ArrayList<>();

    String PaperEnhance_DP_3;
    String[] PaperEnhance_3 = {"Paper Expense","Dealer","Customer"};

    String RCBOOK;
    String[] RCBOOK_3= {" Old Vehicle RC BOOK","Yes","No"};

    String OwnerKyc;
    String[] OwnerKyc_3= {" Old Vehicle Owner KYC","Yes","No"};

    String Loan4Close;
    String[] Loan4Close_3= {" Old Vehicle Loan For Close","Yes","No"};

    String Noc_DP_3;
    String[] NOC_3= {" Old Vehicle Bank NOC","Yes","No"};


    Uri uriUploadCheque_3, uriUploadNEFT_RTGS_3, uriUploadNocPhoto_3, uriUploadOldVehicle_3,
            uriUploadRCBook_3, uriUploadElectionPhoto_3;

    Uri uriUploadRCBook2_3,uriUploadElectionPhoto2_3;

    String  waypathUploadRCBook2_3, waypathUploadElectionPhoto2_3;

    String waypathUploadCheque_3, waypathUploadNEFT_RTGS_3, waypathUploadNocPhoto_3,
            waypathUploadOldVehicle_3, waypathUploadRCBook_3, waypathUploadElectionPhoto_3;

    //----------------------------------------------Fourth data-------------------------------------------------------------

    Spinner spn_DownPayment_BAmount_4,spn_DownPayment_BankOpt_4,spn_DownPayment_SelectMake_4,
            spn_DownPayment_ModelVehicle_4,spn_DownPayment_PaperEnhance_4,spn_DownPayment_NOC_4;

    EditText edt_DownPayment_CashAmount_4,edt_DownPayment_ChequeDate_4,edt_DownPayment_ChequeAmount_4,
            edt_DownPayment_NEFT_RTGS_date_4,edt_DownPayment_NEFT_RTGSAmount_4,
            edt_DownPayment_ManufactureYear_4,
            edt_DownPayment_oldTractorAmount_4,edt_DownPayment_oldAmount_4;

    LinearLayout lin_dp_cheque_4,lin_dp_NEFT_RTGS_4,lin_dp_NocPhoto_4,lin_dp_OldVehicle_4,
            lin_dp_Rcbook_4,lin_dp_Election_4;

    LinearLayout lin_dp_Rcbook2_4,lin_dp_Election2_4;

    ImageView img_DownPayment_Cheque_4,img_DownPayment_NEFT_RTGS_4,img_DownPayment_NocPhoto_4,
            img_DownPayment_OldVehicle_4,img_DownPayment_RcBook_4,img_DownPayment_ElectionPhoto_4;

    ImageView img_DownPayment_RcBook2_4,img_DownPayment_ElectionPhoto2_4;

    TextView txt_DownPayment_UploadCheque_4,txt_DownPayment_UploadNEFT_RTGS_4,txt_DownPayment_UploadNocPhoto_4,
            txt_DownPayment_UploadOldVehicle_4,txt_DownPayment_UploadRCBook_4,
            txt_DownPayment_UploadElectionPhoto_4;

    TextView txt_DownPayment_UploadRCBook2_4,txt_DownPayment_UploadElectionPhoto2_4;

    String dp_bookingAmount_4;
    String[] DpBookingAmount_4 = {"Select Booking Amount","Cash","Bank","Old Vehicle"};

    String dp_Bank_4;
    String[] DpBank_4 = {"Select Bank","Cheque","NEFT/RTGS"};

    List<String>  MakeDP_ID_4 = new ArrayList<>();
    List<String>  makeDp_Name_4 = new ArrayList<>();

    String MakeID_4, MakeName_4, modelName_4;

    List<String>  ModelDP_ID_4 = new ArrayList<>();
    List<String>  modelDp_Name_4 = new ArrayList<>();

    String PaperEnhance_DP_4;
    String[] PaperEnhance_4 = {"Paper Expense","Dealer","Customer"};

    String Noc_DP_4;
    String[] NOC_4= {"NOC","Yes","No"};


    Uri uriUploadCheque_4, uriUploadNEFT_RTGS_4, uriUploadNocPhoto_4, uriUploadOldVehicle_4,
            uriUploadRCBook_4, uriUploadElectionPhoto_4;

    Uri uriUploadRCBook2_4,uriUploadElectionPhoto2_4;

    String  waypathUploadRCBook2_4, waypathUploadElectionPhoto2_4;

    String waypathUploadCheque_4, waypathUploadNEFT_RTGS_4, waypathUploadNocPhoto_4,
            waypathUploadOldVehicle_4, waypathUploadRCBook_4, waypathUploadElectionPhoto_4;


    //--------------------------------------------------------------------------------------------------------------
      ArrayList<String> dp_bookingAmountArray = new ArrayList<>();
      ArrayList<String> cashAmountAll = new ArrayList<>();

      ArrayList<String> dp_BankArray = new ArrayList<>();
      ArrayList<String> edt_ChequeDateArray = new ArrayList<>();
      ArrayList<String> edt_ChequeAmountArray = new ArrayList<>();
      ArrayList<String> edt_NEFT_RTGS_dateArray = new ArrayList<>();
      ArrayList<String> edtNEFT_RTGSAmountArray = new ArrayList<>();
      ArrayList<String> MakeNameArray = new ArrayList<>();
      ArrayList<String> modelNameArray = new ArrayList<>();
      ArrayList<String> edt_ManufactureYearArray = new ArrayList<>();
      ArrayList<String> edt_oldAmountArray = new ArrayList<>();
      ArrayList<String> PaperEnhance_DPArray = new ArrayList<>();
      ArrayList<String> edt_oldTractorAmountArray = new ArrayList<>();
      ArrayList<String> Noc_DPArray = new ArrayList<>();
      ArrayList<String> CashAmountArray = new ArrayList<>();
      ArrayList<String> ChequeAmountArray = new ArrayList<>();
      ArrayList<String> NeftRtgsAmountArray = new ArrayList<>();
      ArrayList<String> OldVehicleAmountArray = new ArrayList<>();

    //--------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_payment);
        getSupportActionBar().setTitle("Down Payment");

        /** *********************************** Memory Allocation ****************************************** */

        spn_DownPayment_BAmount=findViewById(R.id.spn_DownPayment_BAmount);
        spn_DownPayment_BankOpt=findViewById(R.id.spn_DownPayment_BankOpt);
        spn_DownPayment_SelectMake=findViewById(R.id.spn_DownPayment_SelectMake);
        spn_DownPayment_ModelVehicle=findViewById(R.id.spn_DownPayment_ModelVehicle);
        spn_DownPayment_PaperEnhance=findViewById(R.id.spn_DownPayment_PaperEnhance);
        spn_DownPayment_NOC=findViewById(R.id.spn_DownPayment_NOC);

        edt_DownPayment_CashAmount=findViewById(R.id.edt_DownPayment_CashAmount);
        edt_DownPayment_ChequeDate=findViewById(R.id.edt_DownPayment_ChequeDate);
        edt_DownPayment_ChequeAmount=findViewById(R.id.edt_DownPayment_ChequeAmount);
        edt_DownPayment_NEFT_RTGS_date=findViewById(R.id.edt_DownPayment_NEFT_RTGS_date);
        edt_DownPayment_NEFT_RTGSAmount=findViewById(R.id.edt_DownPayment_NEFT_RTGSAmount);
        edt_DownPayment_ManufactureYear=findViewById(R.id.edt_DownPayment_ManufactureYear);
        edt_DownPayment_oldTractorAmount=findViewById(R.id.edt_DownPayment_oldTractorAmount);
        edt_DownPayment_oldAmount=findViewById(R.id.edt_DownPayment_oldAmount);

        lin_dp_cheque=findViewById(R.id.lin_dp_cheque);
        lin_dp_NEFT_RTGS=findViewById(R.id.lin_dp_NEFT_RTGS);
        lin_dp_NocPhoto=findViewById(R.id.lin_dp_NocPhoto);
        lin_dp_OldVehicle=findViewById(R.id.lin_dp_OldVehicle);
        lin_dp_Rcbook=findViewById(R.id.lin_dp_Rcbook);
        lin_dp_Election=findViewById(R.id.lin_dp_Election);

        lin_dp_Rcbook2=findViewById(R.id.lin_dp_Rcbook2);
        lin_dp_Election2=findViewById(R.id.lin_dp_Election2);

        img_DownPayment_Cheque=findViewById(R.id.img_DownPayment_Cheque);
        img_DownPayment_NEFT_RTGS=findViewById(R.id.img_DownPayment_NEFT_RTGS);
        img_DownPayment_NocPhoto=findViewById(R.id.img_DownPayment_NocPhoto);
        img_DownPayment_OldVehicle=findViewById(R.id.img_DownPayment_OldVehicle);
        img_DownPayment_RcBook=findViewById(R.id.img_DownPayment_RcBook);
        img_DownPayment_ElectionPhoto=findViewById(R.id.img_DownPayment_ElectionPhoto);

        img_DownPayment_RcBook2=findViewById(R.id.img_DownPayment_RcBook2);
        img_DownPayment_ElectionPhoto2=findViewById(R.id.img_DownPayment_ElectionPhoto2);

        txt_DownPayment_UploadCheque=findViewById(R.id.txt_DownPayment_UploadCheque);
        txt_DownPayment_UploadNEFT_RTGS=findViewById(R.id.txt_DownPayment_UploadNEFT_RTGS);
        txt_DownPayment_UploadNocPhoto=findViewById(R.id.txt_DownPayment_UploadNocPhoto);
        txt_DownPayment_UploadOldVehicle=findViewById(R.id.txt_DownPayment_UploadOldVehicle);
        txt_DownPayment_UploadRCBook=findViewById(R.id.txt_DownPayment_UploadRCBook);
        txt_DownPayment_UploadElectionPhoto=findViewById(R.id.txt_DownPayment_UploadElectionPhoto);

        txt_DownPayment_UploadRCBook2=findViewById(R.id.txt_DownPayment_UploadRCBook2);
        txt_DownPayment_UploadElectionPhoto2=findViewById(R.id.txt_DownPayment_UploadElectionPhoto2);

        /********************************************************************************/

        btn_DownPayment_Next=findViewById(R.id.btn_DownPayment_Next);

        /*************************************Second data*******************************************/

        spn_DownPayment_BAmount_2=findViewById(R.id.spn_DownPayment_BAmount_2);
        spn_DownPayment_BankOpt_2=findViewById(R.id.spn_DownPayment_BankOpt_2);
        spn_DownPayment_SelectMake_2=findViewById(R.id.spn_DownPayment_SelectMake_2);
        spn_DownPayment_ModelVehicle_2=findViewById(R.id.spn_DownPayment_ModelVehicle_2);
        spn_DownPayment_PaperEnhance_2=findViewById(R.id.spn_DownPayment_PaperEnhance_2);
        spn_DownPayment_NOC_2=findViewById(R.id.spn_DownPayment_NOC_2);

        edt_DownPayment_CashAmount_2=findViewById(R.id.edt_DownPayment_CashAmount_2);
        edt_DownPayment_ChequeDate_2=findViewById(R.id.edt_DownPayment_ChequeDate_2);
        edt_DownPayment_ChequeAmount_2=findViewById(R.id.edt_DownPayment_ChequeAmount_2);
        edt_DownPayment_NEFT_RTGS_date_2=findViewById(R.id.edt_DownPayment_NEFT_RTGS_date_2);
        edt_DownPayment_NEFT_RTGSAmount_2=findViewById(R.id.edt_DownPayment_NEFT_RTGSAmount_2);
        edt_DownPayment_ManufactureYear_2=findViewById(R.id.edt_DownPayment_ManufactureYear_2);
        edt_DownPayment_oldTractorAmount_2=findViewById(R.id.edt_DownPayment_oldTractorAmount_2);
        edt_DownPayment_oldAmount_2=findViewById(R.id.edt_DownPayment_oldAmount_2);

        lin_dp_cheque_2=findViewById(R.id.lin_dp_cheque_2);
        lin_dp_NEFT_RTGS_2=findViewById(R.id.lin_dp_NEFT_RTGS_2);
        lin_dp_NocPhoto_2=findViewById(R.id.lin_dp_NocPhoto_2);
        lin_dp_OldVehicle_2=findViewById(R.id.lin_dp_OldVehicle_2);
        lin_dp_Rcbook_2=findViewById(R.id.lin_dp_Rcbook_2);
        lin_dp_Election_2=findViewById(R.id.lin_dp_Election_2);

        lin_dp_Rcbook2_2=findViewById(R.id.lin_dp_Rcbook2_2);
        lin_dp_Election2_2=findViewById(R.id.lin_dp_Election2_2);

        img_DownPayment_Cheque_2=findViewById(R.id.img_DownPayment_Cheque_2);
        img_DownPayment_NEFT_RTGS_2=findViewById(R.id.img_DownPayment_NEFT_RTGS_2);
        img_DownPayment_NocPhoto_2=findViewById(R.id.img_DownPayment_NocPhoto_2);
        img_DownPayment_OldVehicle_2=findViewById(R.id.img_DownPayment_OldVehicle_2);
        img_DownPayment_RcBook_2=findViewById(R.id.img_DownPayment_RcBook_2);
        img_DownPayment_ElectionPhoto_2=findViewById(R.id.img_DownPayment_ElectionPhoto_2);

        img_DownPayment_RcBook2_2=findViewById(R.id.img_DownPayment_RcBook2_2);
        img_DownPayment_ElectionPhoto2_2=findViewById(R.id.img_DownPayment_ElectionPhoto2_2);

        txt_DownPayment_UploadCheque_2=findViewById(R.id.txt_DownPayment_UploadCheque_2);
        txt_DownPayment_UploadNEFT_RTGS_2=findViewById(R.id.txt_DownPayment_UploadNEFT_RTGS_2);
        txt_DownPayment_UploadNocPhoto_2=findViewById(R.id.txt_DownPayment_UploadNocPhoto_2);
        txt_DownPayment_UploadOldVehicle_2=findViewById(R.id.txt_DownPayment_UploadOldVehicle_2);
        txt_DownPayment_UploadRCBook_2=findViewById(R.id.txt_DownPayment_UploadRCBook_2);
        txt_DownPayment_UploadElectionPhoto_2=findViewById(R.id.txt_DownPayment_UploadElectionPhoto_2);

        txt_DownPayment_UploadRCBook2_2=findViewById(R.id.txt_DownPayment_UploadRCBook2_2);
        txt_DownPayment_UploadElectionPhoto2_2=findViewById(R.id.txt_DownPayment_UploadElectionPhoto2_2);

        /*************************************Third data*******************************************/

        spn_DownPayment_BAmount_3=findViewById(R.id.spn_DownPayment_BAmount_3);
        spn_DownPayment_BankOpt_3=findViewById(R.id.spn_DownPayment_BankOpt_3);
        spn_DownPayment_SelectMake_3=findViewById(R.id.spn_DownPayment_SelectMake_3);
        spn_DownPayment_ModelVehicle_3=findViewById(R.id.spn_DownPayment_ModelVehicle_3);
        spn_DownPayment_PaperEnhance_3=findViewById(R.id.spn_DownPayment_PaperEnhance_3);
        spn_DownPayment_RCBOOK=findViewById(R.id.spn_DownPayment_RCBOOK);
        spn_DownPayment_OwnerKYC=findViewById(R.id.spn_DownPayment_OwnerKYC);
        spn_DownPayment_Loan4Close=findViewById(R.id.spn_DownPayment_Loan4Close);
        spn_DownPayment_NOC_3=findViewById(R.id.spn_DownPayment_NOC_3);

        edt_DownPayment_CashAmount_3=findViewById(R.id.edt_DownPayment_CashAmount_3);
        edt_DownPayment_ChequeDate_3=findViewById(R.id.edt_DownPayment_ChequeDate_3);
        edt_DownPayment_ChequeAmount_3=findViewById(R.id.edt_DownPayment_ChequeAmount_3);
        edt_DownPayment_NEFT_RTGS_date_3=findViewById(R.id.edt_DownPayment_NEFT_RTGS_date_3);
        edt_DownPayment_NEFT_RTGSAmount_3=findViewById(R.id.edt_DownPayment_NEFT_RTGSAmount_3);
        edt_DownPayment_ManufactureYear_3=findViewById(R.id.edt_DownPayment_ManufactureYear_3);
        edt_DownPayment_Registeration_Number_3=findViewById(R.id.edt_DownPayment_Registeration_Number_3);
        edt_DownPayment_oldTractorAmount_3=findViewById(R.id.edt_DownPayment_oldTractorAmount_3);
        edt_DownPayment_oldAmount_3=findViewById(R.id.edt_DownPayment_oldAmount_3);

        lin_dp_cheque_3=findViewById(R.id.lin_dp_cheque_3);
        lin_dp_NEFT_RTGS_3=findViewById(R.id.lin_dp_NEFT_RTGS_3);
        lin_dp_NocPhoto_3=findViewById(R.id.lin_dp_NocPhoto_3);
        lin_dp_OldVehicle_3=findViewById(R.id.lin_dp_OldVehicle_3);
        lin_dp_Rcbook_3=findViewById(R.id.lin_dp_Rcbook_3);
        lin_dp_Election_3=findViewById(R.id.lin_dp_Election_3);

        lin_dp_Rcbook2_3=findViewById(R.id.lin_dp_Rcbook2_3);
        lin_dp_Election2_3=findViewById(R.id.lin_dp_Election2_3);

        img_DownPayment_Cheque_3=findViewById(R.id.img_DownPayment_Cheque_3);
        img_DownPayment_NEFT_RTGS_3=findViewById(R.id.img_DownPayment_NEFT_RTGS_3);
        img_DownPayment_NocPhoto_3=findViewById(R.id.img_DownPayment_NocPhoto_3);
        img_DownPayment_OldVehicle_3=findViewById(R.id.img_DownPayment_OldVehicle_3);
        img_DownPayment_RcBook_3=findViewById(R.id.img_DownPayment_RcBook_3);
        img_DownPayment_ElectionPhoto_3=findViewById(R.id.img_DownPayment_ElectionPhoto_3);

        img_DownPayment_RcBook2_3=findViewById(R.id.img_DownPayment_RcBook2_3);
        img_DownPayment_ElectionPhoto2_3=findViewById(R.id.img_DownPayment_ElectionPhoto2_3);

        txt_DownPayment_UploadCheque_3=findViewById(R.id.txt_DownPayment_UploadCheque_3);
        txt_DownPayment_UploadNEFT_RTGS_3=findViewById(R.id.txt_DownPayment_UploadNEFT_RTGS_3);
        txt_DownPayment_UploadNocPhoto_3=findViewById(R.id.txt_DownPayment_UploadNocPhoto_3);
        txt_DownPayment_UploadOldVehicle_3=findViewById(R.id.txt_DownPayment_UploadOldVehicle_3);
        txt_DownPayment_UploadRCBook_3=findViewById(R.id.txt_DownPayment_UploadRCBook_3);
        txt_DownPayment_UploadElectionPhoto_3=findViewById(R.id.txt_DownPayment_UploadElectionPhoto_3);

        txt_DownPayment_UploadRCBook2_3=findViewById(R.id.txt_DownPayment_UploadRCBook2_3);
        txt_DownPayment_UploadElectionPhoto2_3=findViewById(R.id.txt_DownPayment_UploadElectionPhoto2_3);

        /*************************************** Fourth Data *******************************************************************/

        spn_DownPayment_BAmount_4=findViewById(R.id.spn_DownPayment_BAmount_4);
        spn_DownPayment_BankOpt_4=findViewById(R.id.spn_DownPayment_BankOpt_4);
        spn_DownPayment_SelectMake_4=findViewById(R.id.spn_DownPayment_SelectMake_4);
        spn_DownPayment_ModelVehicle_4=findViewById(R.id.spn_DownPayment_ModelVehicle_4);
        spn_DownPayment_PaperEnhance_4=findViewById(R.id.spn_DownPayment_PaperEnhance_4);
        spn_DownPayment_NOC_4=findViewById(R.id.spn_DownPayment_NOC_4);

        edt_DownPayment_CashAmount_4=findViewById(R.id.edt_DownPayment_CashAmount_4);
        edt_DownPayment_ChequeDate_4=findViewById(R.id.edt_DownPayment_ChequeDate_4);
        edt_DownPayment_ChequeAmount_4=findViewById(R.id.edt_DownPayment_ChequeAmount_4);
        edt_DownPayment_NEFT_RTGS_date_4=findViewById(R.id.edt_DownPayment_NEFT_RTGS_date_4);
        edt_DownPayment_NEFT_RTGSAmount_4=findViewById(R.id.edt_DownPayment_NEFT_RTGSAmount_4);
        edt_DownPayment_ManufactureYear_4=findViewById(R.id.edt_DownPayment_ManufactureYear_4);
        edt_DownPayment_oldTractorAmount_4=findViewById(R.id.edt_DownPayment_oldTractorAmount_4);
        edt_DownPayment_oldAmount_4=findViewById(R.id.edt_DownPayment_oldAmount_4);

        lin_dp_cheque_4=findViewById(R.id.lin_dp_cheque_4);
        lin_dp_NEFT_RTGS_4=findViewById(R.id.lin_dp_NEFT_RTGS_4);
        lin_dp_NocPhoto_4=findViewById(R.id.lin_dp_NocPhoto_4);
        lin_dp_OldVehicle_4=findViewById(R.id.lin_dp_OldVehicle_4);
        lin_dp_Rcbook_4=findViewById(R.id.lin_dp_Rcbook_4);
        lin_dp_Election_4=findViewById(R.id.lin_dp_Election_4);

        lin_dp_Rcbook2_4=findViewById(R.id.lin_dp_Rcbook2_4);
        lin_dp_Election2_4=findViewById(R.id.lin_dp_Election2_4);

        img_DownPayment_Cheque_4=findViewById(R.id.img_DownPayment_Cheque_4);
        img_DownPayment_NEFT_RTGS_4=findViewById(R.id.img_DownPayment_NEFT_RTGS_4);
        img_DownPayment_NocPhoto_4=findViewById(R.id.img_DownPayment_NocPhoto_4);
        img_DownPayment_OldVehicle_4=findViewById(R.id.img_DownPayment_OldVehicle_4);
        img_DownPayment_RcBook_4=findViewById(R.id.img_DownPayment_RcBook_4);
        img_DownPayment_ElectionPhoto_4=findViewById(R.id.img_DownPayment_ElectionPhoto_4);

        img_DownPayment_RcBook2_4=findViewById(R.id.img_DownPayment_RcBook2_4);
        img_DownPayment_ElectionPhoto2_4=findViewById(R.id.img_DownPayment_ElectionPhoto2_4);

        txt_DownPayment_UploadCheque_4=findViewById(R.id.txt_DownPayment_UploadCheque_4);
        txt_DownPayment_UploadNEFT_RTGS_4=findViewById(R.id.txt_DownPayment_UploadNEFT_RTGS_4);
        txt_DownPayment_UploadNocPhoto_4=findViewById(R.id.txt_DownPayment_UploadNocPhoto_4);
        txt_DownPayment_UploadOldVehicle_4=findViewById(R.id.txt_DownPayment_UploadOldVehicle_4);
        txt_DownPayment_UploadRCBook_4=findViewById(R.id.txt_DownPayment_UploadRCBook_4);
        txt_DownPayment_UploadElectionPhoto_4=findViewById(R.id.txt_DownPayment_UploadElectionPhoto_4);

        txt_DownPayment_UploadRCBook2_4=findViewById(R.id.txt_DownPayment_UploadRCBook2_4);
        txt_DownPayment_UploadElectionPhoto2_4=findViewById(R.id.txt_DownPayment_UploadElectionPhoto2_4);


        /** ******************************************************************************************** */

        imgDownPayment_AddOne = findViewById(R.id.imgDownPayment_AddOne);
        imgDownPayment_AddTwo_2 = findViewById(R.id.imgDownPayment_AddTwo_2);
        imgDownPayment_AddTwo_3 = findViewById(R.id.imgDownPayment_AddTwo_3);
        Lin_Downp_OneData = findViewById(R.id.Lin_Downp_OneData);
        Lin_Downp_twoData = findViewById(R.id.Lin_Downp_twoData);
        Lin_Downp_thirdData = findViewById(R.id.Lin_Downp_thirdData);
        Lin_Downp_FourthData = findViewById(R.id.Lin_Downp_FourthData);

        /** ******************************************************************************************** */

        sharedPreferences = getSharedPreferences("NextId1", MODE_PRIVATE);
        NextIDD = sharedPreferences.getString("NextId1","");

        Lin_Downp_twoData.setVisibility(View.GONE);
        Lin_Downp_thirdData.setVisibility(View.GONE);
        Lin_Downp_FourthData.setVisibility(View.GONE);

        //---------------------------------------------------------------------------------------------
        imgDownPayment_AddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {

                   /* imgDownPayment_AddOne.setImageResource(R.drawable.ic_baseline_add_24);
                    Lin_Downp_twoData.setVisibility(View.VISIBLE);*/

                   imgDownPayment_AddOne.setImageResource(R.drawable.ic_baseline_remove_24);
                   Lin_Downp_twoData.setVisibility(View.GONE);
                    flag = 1;

                } else if (flag == 1) {

                   imgDownPayment_AddOne.setImageResource(R.drawable.ic_baseline_add_24);
                   Lin_Downp_twoData.setVisibility(View.VISIBLE);
                   /* imgDownPayment_AddOne.setImageResource(R.drawable.ic_baseline_remove_24);
                    Lin_Downp_twoData.setVisibility(View.GONE);*/
                    flag = 0;
                }
            }
        });

        imgDownPayment_AddTwo_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1 == 0) {

                    imgDownPayment_AddTwo_2.setImageResource(R.drawable.ic_baseline_remove_24);
                    Lin_Downp_thirdData.setVisibility(View.GONE);
                    flag1 = 1;

                } else if (flag1 == 1) {

                    imgDownPayment_AddTwo_2.setImageResource(R.drawable.ic_baseline_add_24);
                    Lin_Downp_thirdData.setVisibility(View.VISIBLE);

                    flag1 = 0;
                }
            }
        });

       /* imgDownPayment_AddTwo_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2 == 0) {

                    imgDownPayment_AddTwo_3.setImageResource(R.drawable.ic_baseline_remove_24);
                    Lin_Downp_FourthData.setVisibility(View.GONE);
                    flag2 = 1;

                } else if (flag2 == 1) {

                    imgDownPayment_AddTwo_3.setImageResource(R.drawable.ic_baseline_add_24);
                    Lin_Downp_FourthData.setVisibility(View.VISIBLE);
                    flag2 = 0;
                }
            }
        });*/

        //---------------------------------------------------------------------------------------------


        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);
        // month = month+1;

       /* List<String> anotherList = Arrays.asList(edt_DownPayment_CashAmount.getText().toString(),
                edt_DownPayment_CashAmount_2.getText().toString(),
                edt_DownPayment_CashAmount_3.getText().toString(),
                edt_DownPayment_CashAmount_4.getText().toString());
        .addAll(anotherList);*/


       // Toast.makeText(this, ""+CheckCategoryFilterItem, Toast.LENGTH_SHORT).show();


       /**************************************First data***********************************************/
        edt_DownPayment_NEFT_RTGS_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DownPaymentDealstageActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                       /* Date date = new Date(year, monthofYear,dayOfMonth);
                       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String cdate = formatter.format(date);*/

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
                        edt_DownPayment_NEFT_RTGS_date.setText(mt + "/" + dy + "/" + year);
                        // EdtWalletSalarySlipDateOne.setText(year+"/"+mt+"/"+dy);
                        //edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DownPayment_ChequeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DownPaymentDealstageActivity.this,
                        R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;

                       /* Date date = new Date(year, monthofYear,dayOfMonth);
                       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String cdate = formatter.format(date);*/

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
                        edt_DownPayment_ChequeDate.setText(mt + "/" + dy + "/" + year);
                        // EdtWalletSalarySlipDateOne.setText(year+"/"+mt+"/"+dy);
                        //edtSearchBBListReqDate.setText(cdate);
                        //edtSearchBBListReqDate.setText(year +"-" + monthofYear + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DownPayment_ChequeDate.setFocusable(false);
        edt_DownPayment_NEFT_RTGS_date.setFocusable(false);

     //   CashAmount, ChequeAmount, NeftRtgsAmount, OldVehicleAmount;

        edt_DownPayment_CashAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_cashAmount = Integer.parseInt(edt_DownPayment_CashAmount.getText().toString());
                String numToWord = NumToWordDealstage.GFG.convertToWords(number_cashAmount);

                CashAmount = numToWord;


                numberCash = Integer.parseInt(edt_DownPayment_CashAmount.getText().toString());

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_cashAmount = Integer.parseInt(edt_DownPayment_CashAmount.getText().toString());
                String numToWord = NumToWordDealstage.GFG.convertToWords(number_cashAmount);

                CashAmount = numToWord;

                numberCash = Integer.parseInt(edt_DownPayment_CashAmount.getText().toString());

                Log.d("CashAmount", "afterTextChanged: "+CashAmount);


              //  Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }
        });

       /* edt_DownPayment_ChequeAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number = Integer.parseInt(edt_DownPayment_ChequeAmount.getText().toString());
                String numToWord1 = NumToWord.GFG.convertToWords(number);

                ChequeAmount= numToWord1;
               // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number = Integer.parseInt(edt_DownPayment_ChequeAmount.getText().toString());
                String numToWord1 = NumToWord.GFG.convertToWords(number);

                ChequeAmount= numToWord1;
                Log.d("ChequeAmount", "afterTextChanged: "+ChequeAmount);

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }
        });*/

       /* edt_DownPayment_NEFT_RTGSAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount.getText().toString());
                String numToWord2 = NumToWord.GFG.convertToWords(number);

                NeftRtgsAmount = numToWord2;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount);


                // Toast.makeText(DownPaymentActivity.this, ""+numToWord2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount.getText().toString());
                String numToWord2 = NumToWord.GFG.convertToWords(number);

                NeftRtgsAmount = numToWord2;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount);

                //Toast.makeText(DownPaymentActivity.this, ""+numToWord2, Toast.LENGTH_SHORT).show();
            }
        });*/

       /* edt_DownPayment_oldAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number = Integer.parseInt(edt_DownPayment_oldAmount.getText().toString());
                String numToWord3 = NumToWord.GFG.convertToWords(number);

                OldVehicleAmount = numToWord3;
              //  Toast.makeText(DownPaymentActivity.this, ""+numToWord3, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number = Integer.parseInt(edt_DownPayment_oldAmount.getText().toString());
                String numToWord3 = NumToWord.GFG.convertToWords(number);

                OldVehicleAmount =numToWord3;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount);

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }
        });*/


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DpBookingAmount);
        spn_DownPayment_BAmount.setAdapter(adapter);

        spn_DownPayment_BAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dp_bookingAmount = DpBookingAmount[position];

                edt_DownPayment_CashAmount.setVisibility(View.GONE);

                if(dp_bookingAmount.equals("Select Booking Amount(Cash)")){
                    edt_DownPayment_CashAmount.setVisibility(View.GONE);

                    spn_DownPayment_BankOpt.setVisibility(View.GONE);
                    spn_DownPayment_SelectMake.setVisibility(View.GONE);
                    spn_DownPayment_ModelVehicle.setVisibility(View.GONE);
                    spn_DownPayment_PaperEnhance.setVisibility(View.GONE);
                    spn_DownPayment_NOC.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGSAmount.setVisibility(View.GONE);
                    edt_DownPayment_ManufactureYear.setVisibility(View.GONE);
                    edt_DownPayment_oldTractorAmount.setVisibility(View.GONE);
                    edt_DownPayment_oldAmount.setVisibility(View.GONE);

                    lin_dp_cheque.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS.setVisibility(View.GONE);
                    lin_dp_NocPhoto.setVisibility(View.GONE);
                    lin_dp_OldVehicle.setVisibility(View.GONE);
                    lin_dp_Rcbook.setVisibility(View.GONE);
                    lin_dp_Election.setVisibility(View.GONE);

                    lin_dp_Rcbook2.setVisibility(View.GONE);
                    lin_dp_Election2.setVisibility(View.GONE);
                }



               // if(DpBookingAmount[position].equals("Cash")){
                if(dp_bookingAmount.equals("Cash")){
                    edt_DownPayment_CashAmount.setVisibility(View.VISIBLE);

                    spn_DownPayment_BankOpt.setVisibility(View.GONE);
                    spn_DownPayment_SelectMake.setVisibility(View.GONE);
                    spn_DownPayment_ModelVehicle.setVisibility(View.GONE);
                    spn_DownPayment_PaperEnhance.setVisibility(View.GONE);
                    spn_DownPayment_NOC.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGSAmount.setVisibility(View.GONE);
                    edt_DownPayment_ManufactureYear.setVisibility(View.GONE);
                    edt_DownPayment_oldTractorAmount.setVisibility(View.GONE);
                    edt_DownPayment_oldAmount.setVisibility(View.GONE);

                    lin_dp_cheque.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS.setVisibility(View.GONE);
                    lin_dp_NocPhoto.setVisibility(View.GONE);
                    lin_dp_OldVehicle.setVisibility(View.GONE);
                    lin_dp_Rcbook.setVisibility(View.GONE);
                    lin_dp_Election.setVisibility(View.GONE);

                    lin_dp_Rcbook2.setVisibility(View.GONE);
                    lin_dp_Election2.setVisibility(View.GONE);
                }


                if(DpBookingAmount[position].equals("Bank"))
                {
                    spn_DownPayment_BankOpt.setVisibility(View.VISIBLE);

                    ArrayAdapter adapter = new ArrayAdapter(DownPaymentDealstageActivity.this, android.R.layout.simple_spinner_dropdown_item, DpBank);
                    spn_DownPayment_BankOpt.setAdapter(adapter);

                    spn_DownPayment_BankOpt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            dp_Bank = DpBank[position];

                            if(DpBank[position].equals("Cheque")){
                                edt_DownPayment_ChequeDate.setVisibility(View.VISIBLE);
                                edt_DownPayment_ChequeAmount.setVisibility(View.VISIBLE);
//                                lin_dp_cheque.setVisibility(View.VISIBLE);

                                edt_DownPayment_CashAmount.setVisibility(View.GONE);

                                edt_DownPayment_NEFT_RTGS_date.setVisibility(View.GONE);
                                edt_DownPayment_NEFT_RTGSAmount.setVisibility(View.GONE);
                                edt_DownPayment_ManufactureYear.setVisibility(View.GONE);
                                edt_DownPayment_oldTractorAmount.setVisibility(View.GONE);
                                edt_DownPayment_oldAmount.setVisibility(View.GONE);

                                lin_dp_NEFT_RTGS.setVisibility(View.GONE);
                                lin_dp_NocPhoto.setVisibility(View.GONE);
                                lin_dp_OldVehicle.setVisibility(View.GONE);
                                lin_dp_Rcbook.setVisibility(View.GONE);
                                lin_dp_Election.setVisibility(View.GONE);

                                lin_dp_Rcbook2.setVisibility(View.GONE);
                                lin_dp_Election2.setVisibility(View.GONE);


                                spn_DownPayment_SelectMake.setVisibility(View.GONE);
                                spn_DownPayment_ModelVehicle.setVisibility(View.GONE);
                                spn_DownPayment_PaperEnhance.setVisibility(View.GONE);
                                spn_DownPayment_NOC.setVisibility(View.GONE);
                            }


                            if(DpBank[position].equals("NEFT/RTGS")){
                                edt_DownPayment_NEFT_RTGS_date.setVisibility(View.VISIBLE);
                                edt_DownPayment_NEFT_RTGSAmount.setVisibility(View.VISIBLE);
//                                lin_dp_NEFT_RTGS.setVisibility(View.VISIBLE);

                                edt_DownPayment_ChequeDate.setVisibility(View.GONE);
                                edt_DownPayment_ChequeAmount.setVisibility(View.GONE);
                                lin_dp_cheque.setVisibility(View.GONE);

                                edt_DownPayment_CashAmount.setVisibility(View.GONE);
                                edt_DownPayment_ManufactureYear.setVisibility(View.GONE);
                                edt_DownPayment_oldTractorAmount.setVisibility(View.GONE);
                                edt_DownPayment_oldAmount.setVisibility(View.GONE);

                                lin_dp_NocPhoto.setVisibility(View.GONE);
                                lin_dp_OldVehicle.setVisibility(View.GONE);
                                lin_dp_Rcbook.setVisibility(View.GONE);
                                lin_dp_Election.setVisibility(View.GONE);

                                lin_dp_Rcbook2.setVisibility(View.GONE);
                                lin_dp_Election2.setVisibility(View.GONE);

                                spn_DownPayment_SelectMake.setVisibility(View.GONE);
                                spn_DownPayment_ModelVehicle.setVisibility(View.GONE);
                                spn_DownPayment_PaperEnhance.setVisibility(View.GONE);
                                spn_DownPayment_NOC.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if(DpBookingAmount[position].equals("Old Vehicle"))
                {

                    spn_DownPayment_SelectMake.setVisibility(View.VISIBLE);
                    spn_DownPayment_ModelVehicle.setVisibility(View.VISIBLE);

                    edt_DownPayment_ManufactureYear.setVisibility(View.VISIBLE);
                    edt_DownPayment_oldAmount.setVisibility(View.VISIBLE);

                    spn_DownPayment_PaperEnhance.setVisibility(View.VISIBLE);
/*
                    lin_dp_OldVehicle.setVisibility(View.VISIBLE);
                    lin_dp_Rcbook.setVisibility(View.VISIBLE);
                    lin_dp_Election.setVisibility(View.VISIBLE);

                    lin_dp_Rcbook2.setVisibility(View.VISIBLE);
                    lin_dp_Election2.setVisibility(View.VISIBLE);*/

                    spn_DownPayment_NOC.setVisibility(View.VISIBLE);

                    edt_DownPayment_oldTractorAmount.setVisibility(View.GONE);

                    edt_DownPayment_CashAmount.setVisibility(View.GONE);

                    lin_dp_NocPhoto.setVisibility(View.GONE);
                    lin_dp_cheque.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount.setVisibility(View.GONE);

                    edt_DownPayment_NEFT_RTGSAmount.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date.setVisibility(View.GONE);


                    WebService.getClient().getMakeDownPayment().enqueue(new Callback<MakeDownPaymentModel>() {
                        @Override
                        public void onResponse(Call<MakeDownPaymentModel> call, Response<MakeDownPaymentModel> response) {
                            if(response.isSuccessful()){
                                if(response.body()!=null){
                                    makeDp_Name.clear();
                                    MakeDP_ID.clear();

                                    makeDp_Name.add("Select Make");
                                    MakeDP_ID.add("0");

                                    for (int i = 0; i < response.body().getState().size(); i++)
                                    {
                                        makeDp_Name.add(response.body().getState().get(i).getMake());
                                        MakeDP_ID.add(response.body().getState().get(i).getId());
                                    }

                                    ArrayAdapter adapterMake = new ArrayAdapter(DownPaymentDealstageActivity.this, android.R.layout.simple_spinner_dropdown_item, makeDp_Name);
                                    spn_DownPayment_SelectMake.setAdapter(adapterMake);

                                    spn_DownPayment_SelectMake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            MakeName =   makeDp_Name.get(position);
                                            MakeID = MakeDP_ID.get(position);

                                            WebService.getClient().getModelDownPayment(MakeID).enqueue(new Callback<ModelDownPaymentModel>() {
                                                @Override
                                                public void onResponse(Call<ModelDownPaymentModel> call, Response<ModelDownPaymentModel> response) {
                                                    if(response.isSuccessful()){
                                                        if(response.body()!= null){
                                                            ModelDP_ID.clear();
                                                            modelDp_Name.clear();

                                                            modelDp_Name.add("Select Model");
                                                            ModelDP_ID.add("0");

                                                            for(int i =0; i < response.body().getModel().size();i++){
                                                                modelDp_Name.add(response.body().getModel().get(i).getModel_name());
                                                            }

                                                            ArrayAdapter adapterModel = new ArrayAdapter(DownPaymentDealstageActivity.this, android.R.layout.simple_spinner_dropdown_item, modelDp_Name);
                                                            spn_DownPayment_ModelVehicle.setAdapter(adapterModel);

                                                            spn_DownPayment_ModelVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    modelName = modelDp_Name.get(position);
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ModelDownPaymentModel> call, Throwable t) {

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
                        public void onFailure(Call<MakeDownPaymentModel> call, Throwable t) {

                        }
                    });


                    ArrayAdapter adapterPaperE = new ArrayAdapter(DownPaymentDealstageActivity.this, android.R.layout.simple_spinner_dropdown_item, PaperEnhance);
                    spn_DownPayment_PaperEnhance.setAdapter(adapterPaperE);

                    spn_DownPayment_PaperEnhance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            PaperEnhance_DP = PaperEnhance[position];

                            if (PaperEnhance_DP.equals("Customer")){
                                edt_DownPayment_oldTractorAmount.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter adapterNoc = new ArrayAdapter(DownPaymentDealstageActivity.this, android.R.layout.simple_spinner_dropdown_item, NOC);
                    spn_DownPayment_NOC.setAdapter(adapterNoc);

                    spn_DownPayment_NOC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Noc_DP = NOC[position];

                            if(Noc_DP.equals("Yes")){
//                                lin_dp_NocPhoto.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        txt_DownPayment_UploadCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });


        txt_DownPayment_UploadNEFT_RTGS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        txt_DownPayment_UploadNocPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });

        txt_DownPayment_UploadOldVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });

        txt_DownPayment_UploadRCBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });

        txt_DownPayment_UploadElectionPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

         txt_DownPayment_UploadRCBook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_DownPayment_UploadElectionPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });


        /**************************************Second data***********************************************/


           edt_DownPayment_NEFT_RTGS_date_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DownPaymentDealstageActivity.this,
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

                        edt_DownPayment_NEFT_RTGS_date_2.setText(mt + "/" + dy + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DownPayment_ChequeDate_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DownPaymentDealstageActivity.this,
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

                        edt_DownPayment_ChequeDate_2.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DownPayment_ChequeDate_2.setFocusable(false);
        edt_DownPayment_NEFT_RTGS_date_2.setFocusable(false);

     //   CashAmount, ChequeAmount, NeftRtgsAmount, OldVehicleAmount;

        /*edt_DownPayment_CashAmount_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_2 = Integer.parseInt(edt_DownPayment_CashAmount_2.getText().toString());
                String numToWord = NumToWord.GFG.convertToWords(number_2);

                CashAmount_2 = numToWord;
               // Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_2 = Integer.parseInt(edt_DownPayment_CashAmount_2.getText().toString());
                String numToWord = NumToWord.GFG.convertToWords(number_2);

                CashAmount_2 = numToWord;
                Log.d("CashAmount", "afterTextChanged: "+CashAmount_2);
              //  Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }
        });*/

        edt_DownPayment_ChequeAmount_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_2 = Integer.parseInt(edt_DownPayment_ChequeAmount_2.getText().toString());
                String numToWord1 = NumToWordDealstage.GFG.convertToWords(number_2);

                ChequeAmount_2= numToWord1;

                NumberCheque = Integer.parseInt(edt_DownPayment_ChequeAmount_2.getText().toString());

               // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_2 = Integer.parseInt(edt_DownPayment_ChequeAmount_2.getText().toString());
                String numToWord1 = NumToWordDealstage.GFG.convertToWords(number_2);

                ChequeAmount_2 = numToWord1;
                Log.d("ChequeAmount", "afterTextChanged: "+ChequeAmount);

                NumberCheque = Integer.parseInt(edt_DownPayment_ChequeAmount_2.getText().toString());

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }
        });

        edt_DownPayment_NEFT_RTGSAmount_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_neftRtgs = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount_2.getText().toString());
                String numToWord2 = NumToWordDealstage.GFG.convertToWords(number_neftRtgs);

                NeftRtgsAmount_2 = numToWord2;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_2);

                NumberNeftRtgs = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount_2.getText().toString());

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_neftRtgs = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount_2.getText().toString());
                String numToWord2 = NumToWordDealstage.GFG.convertToWords(number_neftRtgs);

                NeftRtgsAmount_2 = numToWord2;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_2);

                NumberNeftRtgs = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount_2.getText().toString());

                //Toast.makeText(DownPaymentActivity.this, ""+numToWord2, Toast.LENGTH_SHORT).show();
            }
        });

       /* edt_DownPayment_oldAmount_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_2 = Integer.parseInt(edt_DownPayment_oldAmount_2.getText().toString());
                String numToWord3 = NumToWord.GFG.convertToWords(number_2);

                OldVehicleAmount_2 = numToWord3;
              //  Toast.makeText(DownPaymentActivity.this, ""+numToWord3, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_2 = Integer.parseInt(edt_DownPayment_oldAmount_2.getText().toString());
                String numToWord3 = NumToWord.GFG.convertToWords(number_2);

                OldVehicleAmount_2 =numToWord3;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_2);

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }
        });*/


        ArrayAdapter adapter_2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                DpBookingAmount_2);
        spn_DownPayment_BAmount_2.setAdapter(adapter_2);

        spn_DownPayment_BAmount_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dp_bookingAmount_2 = DpBookingAmount_2[position];

                edt_DownPayment_CashAmount_2.setVisibility(View.GONE);

                if(dp_bookingAmount_2.equals("Select Booking Amount(Bank)")){
                    edt_DownPayment_CashAmount_2.setVisibility(View.GONE);

                    spn_DownPayment_BankOpt_2.setVisibility(View.GONE);
                    spn_DownPayment_SelectMake_2.setVisibility(View.GONE);
                    spn_DownPayment_ModelVehicle_2.setVisibility(View.GONE);
                    spn_DownPayment_PaperEnhance_2.setVisibility(View.GONE);
                    spn_DownPayment_NOC_2.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_2.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_2.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_2.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGSAmount_2.setVisibility(View.GONE);
                    edt_DownPayment_ManufactureYear_2.setVisibility(View.GONE);
                    edt_DownPayment_oldTractorAmount_2.setVisibility(View.GONE);
                    edt_DownPayment_oldAmount_2.setVisibility(View.GONE);

                    lin_dp_cheque_2.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_2.setVisibility(View.GONE);
                    lin_dp_NocPhoto_2.setVisibility(View.GONE);
                    lin_dp_OldVehicle_2.setVisibility(View.GONE);
                    lin_dp_Rcbook_2.setVisibility(View.GONE);
                    lin_dp_Election_2.setVisibility(View.GONE);

                    lin_dp_Rcbook2_2.setVisibility(View.GONE);
                    lin_dp_Election2_2.setVisibility(View.GONE);
                }

               // if(DpBookingAmount[position].equals("Cash")){
                if(dp_bookingAmount_2.equals("Cash")){
                    edt_DownPayment_CashAmount_2.setVisibility(View.VISIBLE);

                    spn_DownPayment_BankOpt_2.setVisibility(View.GONE);
                    spn_DownPayment_SelectMake_2.setVisibility(View.GONE);
                    spn_DownPayment_ModelVehicle_2.setVisibility(View.GONE);
                    spn_DownPayment_PaperEnhance_2.setVisibility(View.GONE);
                    spn_DownPayment_NOC_2.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_2.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_2.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_2.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGSAmount_2.setVisibility(View.GONE);
                    edt_DownPayment_ManufactureYear_2.setVisibility(View.GONE);
                    edt_DownPayment_oldTractorAmount_2.setVisibility(View.GONE);
                    edt_DownPayment_oldAmount_2.setVisibility(View.GONE);

                    lin_dp_cheque_2.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_2.setVisibility(View.GONE);
                    lin_dp_NocPhoto_2.setVisibility(View.GONE);
                    lin_dp_OldVehicle_2.setVisibility(View.GONE);
                    lin_dp_Rcbook_2.setVisibility(View.GONE);
                    lin_dp_Election_2.setVisibility(View.GONE);

                    lin_dp_Rcbook2_2.setVisibility(View.GONE);
                    lin_dp_Election2_2.setVisibility(View.GONE);
                }


                if(DpBookingAmount_2[position].equals("Bank"))
                {
                    spn_DownPayment_BankOpt_2.setVisibility(View.VISIBLE);

                    ArrayAdapter adapter = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, DpBank_2);
                    spn_DownPayment_BankOpt_2.setAdapter(adapter);

                    spn_DownPayment_BankOpt_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            dp_Bank_2 = DpBank_2[position];

                            if(DpBank_2[position].equals("Cheque")){
                                edt_DownPayment_ChequeDate_2.setVisibility(View.VISIBLE);
                                edt_DownPayment_ChequeAmount_2.setVisibility(View.VISIBLE);
//                                lin_dp_cheque_2.setVisibility(View.VISIBLE);

                                edt_DownPayment_CashAmount_2.setVisibility(View.GONE);

                                edt_DownPayment_NEFT_RTGS_date_2.setVisibility(View.GONE);
                                edt_DownPayment_NEFT_RTGSAmount_2.setVisibility(View.GONE);
                                edt_DownPayment_ManufactureYear_2.setVisibility(View.GONE);
                                edt_DownPayment_oldTractorAmount_2.setVisibility(View.GONE);
                                edt_DownPayment_oldAmount_2.setVisibility(View.GONE);

                                lin_dp_NEFT_RTGS_2.setVisibility(View.GONE);
                                lin_dp_NocPhoto_2.setVisibility(View.GONE);
                                lin_dp_OldVehicle_2.setVisibility(View.GONE);
                                lin_dp_Rcbook_2.setVisibility(View.GONE);
                                lin_dp_Election_2.setVisibility(View.GONE);

                                lin_dp_Rcbook2_2.setVisibility(View.GONE);
                                lin_dp_Election2_2.setVisibility(View.GONE);


                                spn_DownPayment_SelectMake_2.setVisibility(View.GONE);
                                spn_DownPayment_ModelVehicle_2.setVisibility(View.GONE);
                                spn_DownPayment_PaperEnhance_2.setVisibility(View.GONE);
                                spn_DownPayment_NOC_2.setVisibility(View.GONE);
                            }


                            if(DpBank_2[position].equals("NEFT/RTGS")){
                                edt_DownPayment_NEFT_RTGS_date_2.setVisibility(View.VISIBLE);
                                edt_DownPayment_NEFT_RTGSAmount_2.setVisibility(View.VISIBLE);
//                                lin_dp_NEFT_RTGS_2.setVisibility(View.VISIBLE);

                                edt_DownPayment_ChequeDate_2.setVisibility(View.GONE);
                                edt_DownPayment_ChequeAmount_2.setVisibility(View.GONE);
                                lin_dp_cheque_2.setVisibility(View.GONE);

                                edt_DownPayment_CashAmount_2.setVisibility(View.GONE);
                                edt_DownPayment_ManufactureYear_2.setVisibility(View.GONE);
                                edt_DownPayment_oldTractorAmount_2.setVisibility(View.GONE);
                                edt_DownPayment_oldAmount_2.setVisibility(View.GONE);

                                lin_dp_NocPhoto_2.setVisibility(View.GONE);
                                lin_dp_OldVehicle_2.setVisibility(View.GONE);
                                lin_dp_Rcbook_2.setVisibility(View.GONE);
                                lin_dp_Election_2.setVisibility(View.GONE);

                                lin_dp_Rcbook2_2.setVisibility(View.GONE);
                                lin_dp_Election2_2.setVisibility(View.GONE);

                                spn_DownPayment_SelectMake_2.setVisibility(View.GONE);
                                spn_DownPayment_ModelVehicle_2.setVisibility(View.GONE);
                                spn_DownPayment_PaperEnhance_2.setVisibility(View.GONE);
                                spn_DownPayment_NOC_2.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if(DpBookingAmount_2[position].equals("Old Vehicle"))
                {

                    spn_DownPayment_SelectMake_2.setVisibility(View.VISIBLE);
                    spn_DownPayment_ModelVehicle_2.setVisibility(View.VISIBLE);

                    edt_DownPayment_ManufactureYear_2.setVisibility(View.VISIBLE);
                    edt_DownPayment_oldAmount_2.setVisibility(View.VISIBLE);

                    spn_DownPayment_PaperEnhance_2.setVisibility(View.VISIBLE);

               /*     lin_dp_OldVehicle_2.setVisibility(View.VISIBLE);
                    lin_dp_Rcbook_2.setVisibility(View.VISIBLE);
                    lin_dp_Election_2.setVisibility(View.VISIBLE);

                    lin_dp_Rcbook2_2.setVisibility(View.VISIBLE);
                    lin_dp_Election2_2.setVisibility(View.VISIBLE);*/

                    spn_DownPayment_NOC_2.setVisibility(View.VISIBLE);

                    edt_DownPayment_oldTractorAmount_2.setVisibility(View.GONE);

                    edt_DownPayment_CashAmount_2.setVisibility(View.GONE);

                    lin_dp_NocPhoto_2.setVisibility(View.GONE);
                    lin_dp_cheque_2.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_2.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_2.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_2.setVisibility(View.GONE);

                    edt_DownPayment_NEFT_RTGSAmount_2.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_2.setVisibility(View.GONE);


                    WebService.getClient().getMakeDownPayment().enqueue(new Callback<MakeDownPaymentModel>() {
                        @Override
                        public void onResponse(@NotNull Call<MakeDownPaymentModel> call,
                                               @NotNull Response<MakeDownPaymentModel> response) {
                            if(response.isSuccessful()){
                                if(response.body()!=null){
                                    makeDp_Name_2.clear();
                                    MakeDP_ID_2.clear();

                                    makeDp_Name_2.add("Select Make");
                                    MakeDP_ID_2.add("0");

                                    for (int i = 0; i < response.body().getState().size(); i++)
                                    {
                                        makeDp_Name_2.add(response.body().getState().get(i).getMake());
                                        MakeDP_ID_2.add(response.body().getState().get(i).getId());
                                    }

                                    ArrayAdapter adapterMake = new ArrayAdapter(DownPaymentDealstageActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item, makeDp_Name_2);
                                    spn_DownPayment_SelectMake_2.setAdapter(adapterMake);

                                    spn_DownPayment_SelectMake_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            MakeName_2 =   makeDp_Name_2.get(position);
                                            MakeID_2 = MakeDP_ID_2.get(position);

                                            WebService.getClient().getModelDownPayment(MakeID_2).
                                                    enqueue(new Callback<ModelDownPaymentModel>() {
                                                @Override
                                                public void onResponse(@NotNull Call<ModelDownPaymentModel> call,
                                                                       @NotNull Response<ModelDownPaymentModel> response) {
                                                    if(response.isSuccessful()){
                                                        if(response.body()!= null){
                                                            ModelDP_ID_2.clear();
                                                            modelDp_Name_2.clear();

                                                            modelDp_Name_2.add("Select Model");
                                                            ModelDP_ID_2.add("0");

                                                            for(int i =0; i < response.body().getModel().size();i++){
                                                                modelDp_Name_2.add(response.body().getModel().get(i).getModel_name());
                                                            }

                                                            ArrayAdapter adapterModel = new ArrayAdapter(DownPaymentDealstageActivity.this,
                                                                    android.R.layout.simple_spinner_dropdown_item, modelDp_Name_2);
                                                            spn_DownPayment_ModelVehicle_2.setAdapter(adapterModel);

                                                            spn_DownPayment_ModelVehicle_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    modelName_2 = modelDp_Name_2.get(position);
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ModelDownPaymentModel> call, Throwable t) {

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
                        public void onFailure(Call<MakeDownPaymentModel> call, Throwable t) {

                        }
                    });


                    ArrayAdapter adapterPaperE = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, PaperEnhance_2);
                    spn_DownPayment_PaperEnhance_2.setAdapter(adapterPaperE);

                    spn_DownPayment_PaperEnhance_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            PaperEnhance_DP_2 = PaperEnhance_2[position];

                            if (PaperEnhance_DP_2.equals("Customer")){
                                edt_DownPayment_oldTractorAmount_2.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter adapterNoc = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, NOC_2);
                    spn_DownPayment_NOC_2.setAdapter(adapterNoc);

                    spn_DownPayment_NOC_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Noc_DP_2 = NOC_2[position];

                            if(Noc_DP_2.equals("Yes")){
//                                lin_dp_NocPhoto_2.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        txt_DownPayment_UploadCheque_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });


        txt_DownPayment_UploadNEFT_RTGS_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 11);
            }
        });

        txt_DownPayment_UploadNocPhoto_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 12);
            }
        });

        txt_DownPayment_UploadOldVehicle_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 13);
            }
        });

        txt_DownPayment_UploadRCBook_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 14);
            }
        });

        txt_DownPayment_UploadElectionPhoto_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 15);
            }
        });

         txt_DownPayment_UploadRCBook2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 16);
            }
        });

        txt_DownPayment_UploadElectionPhoto2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 17);
            }
        });


        /****************************************** Third data **************************************************************************/

            edt_DownPayment_NEFT_RTGS_date_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DownPaymentDealstageActivity.this,
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

                        edt_DownPayment_NEFT_RTGS_date_3.setText(mt + "/" + dy + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DownPayment_ChequeDate_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DownPaymentDealstageActivity.this,
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

                        edt_DownPayment_ChequeDate_3.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DownPayment_ChequeDate_3.setFocusable(false);
        edt_DownPayment_NEFT_RTGS_date_3.setFocusable(false);

     //   CashAmount, ChequeAmount, NeftRtgsAmount, OldVehicleAmount;

       /* edt_DownPayment_CashAmount_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_3 = Integer.parseInt(edt_DownPayment_CashAmount_3.getText().toString());
                String numToWord = NumToWord.GFG.convertToWords(number_3);

                CashAmount_3 = numToWord;
               // Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_3 = Integer.parseInt(edt_DownPayment_CashAmount_3.getText().toString());
                String numToWord = NumToWord.GFG.convertToWords(number_3);

                CashAmount_3 = numToWord;
                Log.d("CashAmount", "afterTextChanged: "+CashAmount_3);
              //  Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }
        });*/

       /* edt_DownPayment_ChequeAmount_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_3 = Integer.parseInt(edt_DownPayment_ChequeAmount_3.getText().toString());
                String numToWord1 = NumToWord.GFG.convertToWords(number_3);

                ChequeAmount_3= numToWord1;
               // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_3 = Integer.parseInt(edt_DownPayment_ChequeAmount_3.getText().toString());
                String numToWord1 = NumToWord.GFG.convertToWords(number_3);

                ChequeAmount_3 = numToWord1;
                Log.d("ChequeAmount", "afterTextChanged: "+ChequeAmount_3);

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }
        });*/

       /* edt_DownPayment_NEFT_RTGSAmount_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_3 = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount_3.getText().toString());
                String numToWord2 = NumToWord.GFG.convertToWords(number_3);

                NeftRtgsAmount_3 = numToWord2;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_3);


                // Toast.makeText(DownPaymentActivity.this, ""+numToWord2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_3 = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount_3.getText().toString());
                String numToWord2 = NumToWord.GFG.convertToWords(number_3);

                NeftRtgsAmount_3 = numToWord2;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_2);

                //Toast.makeText(DownPaymentActivity.this, ""+numToWord2, Toast.LENGTH_SHORT).show();
            }
        });*/

        edt_DownPayment_oldAmount_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_oldamount = Integer.parseInt(edt_DownPayment_oldAmount_3.getText().toString());
                String numToWord3 = NumToWordDealstage.GFG.convertToWords(number_oldamount);

                OldVehicleAmount_3 = numToWord3;
              //  Toast.makeText(DownPaymentActivity.this, ""+numToWord3, Toast.LENGTH_SHORT).show();

                NumberOldVehical = Integer.parseInt(edt_DownPayment_oldAmount_3.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_oldamount = Integer.parseInt(edt_DownPayment_oldAmount_3.getText().toString());
                String numToWord3 = NumToWordDealstage.GFG.convertToWords(number_oldamount);

                OldVehicleAmount_3 = numToWord3;
               // Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_3);

                NumberOldVehical = Integer.parseInt(edt_DownPayment_oldAmount_3.getText().toString());

                // Toast.makeText(DownPaymentActivity.this, ""+OldVehicleAmount_3, Toast.LENGTH_SHORT).show();
            }
        });

        //Toast.makeText(this, ""+OldVehicleAmount_3, Toast.LENGTH_LONG).show();

        edt_DownPayment_oldTractorAmount_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_3 = Integer.parseInt(edt_DownPayment_oldTractorAmount_3.getText().toString());
                String numToWord3 = NumToWordDealstage.GFG.convertToWords(number_3);

                oldTAmt_3 = numToWord3;
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_3 = Integer.parseInt(edt_DownPayment_oldTractorAmount_3.getText().toString());
                String numToWord3 = NumToWordDealstage.GFG.convertToWords(number_3);

                oldTAmt_3 = numToWord3;
            }
        });


        ArrayAdapter adapter_3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                DpBookingAmount_3);
        spn_DownPayment_BAmount_3.setAdapter(adapter_3);

        spn_DownPayment_BAmount_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dp_bookingAmount_3 = DpBookingAmount_3[position];

                edt_DownPayment_CashAmount_3.setVisibility(View.GONE);

                if(dp_bookingAmount_3.equals("Select Booking Amount (Old Vehicle)")){
                    edt_DownPayment_CashAmount_3.setVisibility(View.GONE);

                    spn_DownPayment_BankOpt_3.setVisibility(View.GONE);
                    spn_DownPayment_SelectMake_3.setVisibility(View.GONE);
                    spn_DownPayment_ModelVehicle_3.setVisibility(View.GONE);
                    spn_DownPayment_PaperEnhance_3.setVisibility(View.GONE);
                    spn_DownPayment_NOC_3.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_3.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_3.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_3.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGSAmount_3.setVisibility(View.GONE);
                    edt_DownPayment_ManufactureYear_3.setVisibility(View.GONE);
                    edt_DownPayment_Registeration_Number_3.setVisibility(View.GONE);
                    edt_DownPayment_oldTractorAmount_3.setVisibility(View.GONE);
                    edt_DownPayment_oldAmount_3.setVisibility(View.GONE);

                    lin_dp_cheque_3.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_3.setVisibility(View.GONE);
                    lin_dp_NocPhoto_3.setVisibility(View.GONE);
                    lin_dp_OldVehicle_3.setVisibility(View.GONE);
                    lin_dp_Rcbook_3.setVisibility(View.GONE);
                    lin_dp_Election_3.setVisibility(View.GONE);

                    lin_dp_Rcbook2_3.setVisibility(View.GONE);
                    lin_dp_Election2_3.setVisibility(View.GONE);
                }



               // if(DpBookingAmount[position].equals("Cash")){
                if(dp_bookingAmount_3.equals("Cash")){
                    edt_DownPayment_CashAmount_3.setVisibility(View.VISIBLE);

                    spn_DownPayment_BankOpt_3.setVisibility(View.GONE);
                    spn_DownPayment_SelectMake_3.setVisibility(View.GONE);
                    spn_DownPayment_ModelVehicle_3.setVisibility(View.GONE);
                    spn_DownPayment_PaperEnhance_3.setVisibility(View.GONE);
                    spn_DownPayment_NOC_3.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_3.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_3.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_3.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGSAmount_3.setVisibility(View.GONE);
                    edt_DownPayment_ManufactureYear_3.setVisibility(View.GONE);
                    edt_DownPayment_Registeration_Number_3.setVisibility(View.GONE);
                    edt_DownPayment_oldTractorAmount_3.setVisibility(View.GONE);
                    edt_DownPayment_oldAmount_3.setVisibility(View.GONE);

                    lin_dp_cheque_3.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_3.setVisibility(View.GONE);
                    lin_dp_NocPhoto_3.setVisibility(View.GONE);
                    lin_dp_OldVehicle_3.setVisibility(View.GONE);
                    lin_dp_Rcbook_3.setVisibility(View.GONE);
                    lin_dp_Election_3.setVisibility(View.GONE);

                    lin_dp_Rcbook2_3.setVisibility(View.GONE);
                    lin_dp_Election2_3.setVisibility(View.GONE);
                }


                if(DpBookingAmount_3[position].equals("Bank"))
                {
                    spn_DownPayment_BankOpt_3.setVisibility(View.VISIBLE);

                    ArrayAdapter adapter = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, DpBank_3);
                    spn_DownPayment_BankOpt_3.setAdapter(adapter);

                    spn_DownPayment_BankOpt_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            dp_Bank_3 = DpBank_3[position];

                            if(DpBank_3[position].equals("Cheque")){
                                edt_DownPayment_ChequeDate_3.setVisibility(View.VISIBLE);
                                edt_DownPayment_ChequeAmount_3.setVisibility(View.VISIBLE);
//                                lin_dp_cheque_3.setVisibility(View.VISIBLE);

                                edt_DownPayment_CashAmount_3.setVisibility(View.GONE);

                                edt_DownPayment_NEFT_RTGS_date_3.setVisibility(View.GONE);
                                edt_DownPayment_NEFT_RTGSAmount_3.setVisibility(View.GONE);
                                edt_DownPayment_ManufactureYear_3.setVisibility(View.GONE);
                                edt_DownPayment_Registeration_Number_3.setVisibility(View.GONE);
                                edt_DownPayment_oldTractorAmount_3.setVisibility(View.GONE);
                                edt_DownPayment_oldAmount_3.setVisibility(View.GONE);

                                lin_dp_NEFT_RTGS_3.setVisibility(View.GONE);
                                lin_dp_NocPhoto_3.setVisibility(View.GONE);
                                lin_dp_OldVehicle_3.setVisibility(View.GONE);
                                lin_dp_Rcbook_3.setVisibility(View.GONE);
                                lin_dp_Election_3.setVisibility(View.GONE);

                                lin_dp_Rcbook2_3.setVisibility(View.GONE);
                                lin_dp_Election2_3.setVisibility(View.GONE);


                                spn_DownPayment_SelectMake_3.setVisibility(View.GONE);
                                spn_DownPayment_ModelVehicle_3.setVisibility(View.GONE);
                                spn_DownPayment_PaperEnhance_3.setVisibility(View.GONE);
                                spn_DownPayment_NOC_3.setVisibility(View.GONE);
                            }


                            if(DpBank_3[position].equals("NEFT/RTGS")){
                                edt_DownPayment_NEFT_RTGS_date_3.setVisibility(View.VISIBLE);
                                edt_DownPayment_NEFT_RTGSAmount_3.setVisibility(View.VISIBLE);
//                                lin_dp_NEFT_RTGS_3.setVisibility(View.VISIBLE);

                                edt_DownPayment_ChequeDate_3.setVisibility(View.GONE);
                                edt_DownPayment_ChequeAmount_3.setVisibility(View.GONE);
                                lin_dp_cheque_3.setVisibility(View.GONE);

                                edt_DownPayment_CashAmount_3.setVisibility(View.GONE);
                                edt_DownPayment_ManufactureYear_3.setVisibility(View.GONE);
                                edt_DownPayment_Registeration_Number_3.setVisibility(View.GONE);
                                edt_DownPayment_oldTractorAmount_3.setVisibility(View.GONE);
                                edt_DownPayment_oldAmount_3.setVisibility(View.GONE);

                                lin_dp_NocPhoto_3.setVisibility(View.GONE);
                                lin_dp_OldVehicle_3.setVisibility(View.GONE);
                                lin_dp_Rcbook_3.setVisibility(View.GONE);
                                lin_dp_Election_3.setVisibility(View.GONE);

                                lin_dp_Rcbook2_3.setVisibility(View.GONE);
                                lin_dp_Election2_3.setVisibility(View.GONE);

                                spn_DownPayment_SelectMake_3.setVisibility(View.GONE);
                                spn_DownPayment_ModelVehicle_3.setVisibility(View.GONE);
                                spn_DownPayment_PaperEnhance_3.setVisibility(View.GONE);
                                spn_DownPayment_NOC_3.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if(DpBookingAmount_3[position].equals("Old Vehicle"))
                {
                    spn_DownPayment_SelectMake_3.setVisibility(View.VISIBLE);
                    spn_DownPayment_ModelVehicle_3.setVisibility(View.VISIBLE);

                    edt_DownPayment_ManufactureYear_3.setVisibility(View.VISIBLE);
                    edt_DownPayment_Registeration_Number_3.setVisibility(View.VISIBLE);
                    edt_DownPayment_oldAmount_3.setVisibility(View.VISIBLE);

                    spn_DownPayment_PaperEnhance_3.setVisibility(View.VISIBLE);

                   /* lin_dp_OldVehicle_3.setVisibility(View.VISIBLE);
                    lin_dp_Rcbook_3.setVisibility(View.VISIBLE);
                    lin_dp_Election_3.setVisibility(View.VISIBLE);

                    lin_dp_Rcbook2_3.setVisibility(View.VISIBLE);
                    lin_dp_Election2_3.setVisibility(View.VISIBLE);*/

                    spn_DownPayment_NOC_3.setVisibility(View.VISIBLE);

                    edt_DownPayment_oldTractorAmount_3.setVisibility(View.GONE);

                    edt_DownPayment_CashAmount_3.setVisibility(View.GONE);

                    lin_dp_NocPhoto_3.setVisibility(View.GONE);
                    lin_dp_cheque_3.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_3.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_3.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_3.setVisibility(View.GONE);

                    edt_DownPayment_NEFT_RTGSAmount_3.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_3.setVisibility(View.GONE);

                    WebService.getClient().getMakeDownPayment().enqueue(new Callback<MakeDownPaymentModel>() {
                        @Override
                        public void onResponse(@NotNull Call<MakeDownPaymentModel> call,
                                               @NotNull Response<MakeDownPaymentModel> response) {
                            if(response.isSuccessful()){
                                if(response.body()!=null){
                                    makeDp_Name_3.clear();
                                    MakeDP_ID_3.clear();

                                    makeDp_Name_3.add("Select Make");
                                    MakeDP_ID_3.add("0");

                                    for (int i = 0; i < response.body().getState().size(); i++)
                                    {
                                        makeDp_Name_3.add(response.body().getState().get(i).getMake());
                                        MakeDP_ID_3.add(response.body().getState().get(i).getId());
                                    }

                                    ArrayAdapter adapterMake = new ArrayAdapter(DownPaymentDealstageActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item, makeDp_Name_3);
                                    spn_DownPayment_SelectMake_3.setAdapter(adapterMake);

                                    spn_DownPayment_SelectMake_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            MakeName_3 =   makeDp_Name_3.get(position);
                                            MakeID_3 = MakeDP_ID_3.get(position);

                                            WebService.getClient().getModelDownPayment(MakeID_3).
                                                    enqueue(new Callback<ModelDownPaymentModel>() {
                                                @Override
                                                public void onResponse(@NotNull Call<ModelDownPaymentModel> call,
                                                                       @NotNull Response<ModelDownPaymentModel> response) {
                                                    if(response.isSuccessful()){
                                                        if(response.body()!= null){
                                                            ModelDP_ID_3.clear();
                                                            modelDp_Name_3.clear();

                                                            modelDp_Name_3.add("Select Model");
                                                            ModelDP_ID_3.add("0");

                                                            for(int i =0; i < response.body().getModel().size();i++){
                                                                modelDp_Name_3.add(response.body().getModel().get(i).getModel_name());
                                                            }

                                                            ArrayAdapter adapterModel = new ArrayAdapter(DownPaymentDealstageActivity.this,
                                                                    android.R.layout.simple_spinner_dropdown_item, modelDp_Name_3);
                                                            spn_DownPayment_ModelVehicle_3.setAdapter(adapterModel);

                                                            spn_DownPayment_ModelVehicle_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    modelName_3 = modelDp_Name_3.get(position);
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ModelDownPaymentModel> call, Throwable t) {

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
                        public void onFailure(Call<MakeDownPaymentModel> call, Throwable t) {

                        }
                    });


                    ArrayAdapter adapterPaperE = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, PaperEnhance_3);
                    spn_DownPayment_PaperEnhance_3.setAdapter(adapterPaperE);

                    spn_DownPayment_PaperEnhance_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            PaperEnhance_DP_3 = PaperEnhance_3[position];

                            if (PaperEnhance_DP_3.equals("Customer")){
                                edt_DownPayment_oldTractorAmount_3.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter adapterRcbook = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, RCBOOK_3);
                    spn_DownPayment_RCBOOK.setAdapter(adapterRcbook);

                    spn_DownPayment_RCBOOK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            RCBOOK = RCBOOK_3[position];

                            if(RCBOOK.equals("Yes")){
//                                lin_dp_NocPhoto_3.setVisibility(View.VISIBLE);
                            }
                            else{
//                                lin_dp_NocPhoto_3.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter adapterKYC = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, OwnerKyc_3);
                    spn_DownPayment_OwnerKYC.setAdapter(adapterKYC);

                    spn_DownPayment_OwnerKYC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            OwnerKyc = OwnerKyc_3[position];

                            if(OwnerKyc.equals("Yes")){
//                                lin_dp_NocPhoto_3.setVisibility(View.VISIBLE);
                            }
                            else{
//                                lin_dp_NocPhoto_3.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter adapterLoan = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Loan4Close_3);
                    spn_DownPayment_Loan4Close.setAdapter(adapterLoan);

                    spn_DownPayment_Loan4Close.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Loan4Close = Loan4Close_3[position];

                            if(Loan4Close.equals("Yes")){
//                                lin_dp_NocPhoto_3.setVisibility(View.VISIBLE);
                            }
                            else{
//                                lin_dp_NocPhoto_3.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter adapterNoc = new ArrayAdapter(DownPaymentDealstageActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, NOC_3);
                    spn_DownPayment_NOC_3.setAdapter(adapterNoc);

                    spn_DownPayment_NOC_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Noc_DP_3 = NOC_3[position];

                            if(Noc_DP_3.equals("Yes")){
//                                lin_dp_NocPhoto_3.setVisibility(View.VISIBLE);
                            }
                            else{
                                lin_dp_NocPhoto_3.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        txt_DownPayment_UploadCheque_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 18);
            }
        });


        txt_DownPayment_UploadNEFT_RTGS_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 19);
            }
        });

        txt_DownPayment_UploadNocPhoto_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 20);
            }
        });

        txt_DownPayment_UploadOldVehicle_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 21);
            }
        });

        txt_DownPayment_UploadRCBook_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 22);
            }
        });

        txt_DownPayment_UploadElectionPhoto_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 23);
            }
        });

         txt_DownPayment_UploadRCBook2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 24);
            }
        });

        txt_DownPayment_UploadElectionPhoto2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 25);
            }
        });

        /******************************************* Fourth Data *****************************************************************/

        /* edt_DownPayment_NEFT_RTGS_date_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DownPaymentActivity.this,
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

                        edt_DownPayment_NEFT_RTGS_date_4.setText(mt + "/" + dy + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });*/

       /* edt_DownPayment_ChequeDate_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DownPaymentActivity.this,
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
                        edt_DownPayment_ChequeDate_4.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });*/

       // edt_DownPayment_ChequeDate_4.setFocusable(false);
       // edt_DownPayment_NEFT_RTGS_date_4.setFocusable(false);


      /*  edt_DownPayment_CashAmount_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_4 = Integer.parseInt(edt_DownPayment_CashAmount_4.getText().toString());
                String numToWord = NumToWord.GFG.convertToWords(number_4);

                CashAmount_4 = numToWord;
               // Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_4 = Integer.parseInt(edt_DownPayment_CashAmount_4.getText().toString());
                String numToWord = NumToWord.GFG.convertToWords(number_4);

                CashAmount_4 = numToWord;
                Log.d("CashAmount", "afterTextChanged: "+CashAmount_4);
              //  Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }
        });*/

      /*  edt_DownPayment_ChequeAmount_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_4 = Integer.parseInt(edt_DownPayment_ChequeAmount_4.getText().toString());
                String numToWord1 = NumToWord.GFG.convertToWords(number_4);

                ChequeAmount_3= numToWord1;
               // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_4 = Integer.parseInt(edt_DownPayment_ChequeAmount_4.getText().toString());
                String numToWord1 = NumToWord.GFG.convertToWords(number_4);

                ChequeAmount_4 = numToWord1;
                Log.d("ChequeAmount", "afterTextChanged: "+ChequeAmount_4);

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }
        });*/

       /* edt_DownPayment_NEFT_RTGSAmount_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_4 = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount_4.getText().toString());
                String numToWord2 = NumToWord.GFG.convertToWords(number_4);

                NeftRtgsAmount_4 = numToWord2;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_4);


                // Toast.makeText(DownPaymentActivity.this, ""+numToWord2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_4 = Integer.parseInt(edt_DownPayment_NEFT_RTGSAmount_4.getText().toString());
                String numToWord2 = NumToWord.GFG.convertToWords(number_4);

                NeftRtgsAmount_4 = numToWord2;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_4);

                //Toast.makeText(DownPaymentActivity.this, ""+numToWord2, Toast.LENGTH_SHORT).show();
            }
        });*/

       /* edt_DownPayment_oldAmount_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number_4 = Integer.parseInt(edt_DownPayment_oldAmount_4.getText().toString());
                String numToWord3 = NumToWord.GFG.convertToWords(number_4);

                OldVehicleAmount_4 = numToWord3;
              //  Toast.makeText(DownPaymentActivity.this, ""+numToWord3, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                number_4 = Integer.parseInt(edt_DownPayment_oldAmount_4.getText().toString());
                String numToWord3 = NumToWord.GFG.convertToWords(number_4);

                OldVehicleAmount_4 =numToWord3;
                Log.d("NeftRtgsAmount", "afterTextChanged: "+NeftRtgsAmount_4);

                // Toast.makeText(DownPaymentActivity.this, ""+numToWord1, Toast.LENGTH_SHORT).show();
            }
        });*/


      /*  ArrayAdapter adapter_4 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                DpBookingAmount_4);
        spn_DownPayment_BAmount_4.setAdapter(adapter_4);

        spn_DownPayment_BAmount_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dp_bookingAmount_4 = DpBookingAmount_4[position];

                edt_DownPayment_CashAmount_4.setVisibility(View.GONE);

                if(dp_bookingAmount_4.equals("Select Booking Amount")){
                    edt_DownPayment_CashAmount_4.setVisibility(View.GONE);

                    spn_DownPayment_BankOpt_4.setVisibility(View.GONE);
                    spn_DownPayment_SelectMake_4.setVisibility(View.GONE);
                    spn_DownPayment_ModelVehicle_4.setVisibility(View.GONE);
                    spn_DownPayment_PaperEnhance_4.setVisibility(View.GONE);
                    spn_DownPayment_NOC_4.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_4.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_4.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_4.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGSAmount_4.setVisibility(View.GONE);
                    edt_DownPayment_ManufactureYear_4.setVisibility(View.GONE);
                    edt_DownPayment_oldTractorAmount_4.setVisibility(View.GONE);
                    edt_DownPayment_oldAmount_4.setVisibility(View.GONE);

                    lin_dp_cheque_4.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_4.setVisibility(View.GONE);
                    lin_dp_NocPhoto_4.setVisibility(View.GONE);
                    lin_dp_OldVehicle_4.setVisibility(View.GONE);
                    lin_dp_Rcbook_4.setVisibility(View.GONE);
                    lin_dp_Election_4.setVisibility(View.GONE);

                    lin_dp_Rcbook2_4.setVisibility(View.GONE);
                    lin_dp_Election2_4.setVisibility(View.GONE);
                }



               // if(DpBookingAmount[position].equals("Cash")){
                if(dp_bookingAmount_4.equals("Cash")){
                    edt_DownPayment_CashAmount_4.setVisibility(View.VISIBLE);

                    spn_DownPayment_BankOpt_4.setVisibility(View.GONE);
                    spn_DownPayment_SelectMake_4.setVisibility(View.GONE);
                    spn_DownPayment_ModelVehicle_4.setVisibility(View.GONE);
                    spn_DownPayment_PaperEnhance_4.setVisibility(View.GONE);
                    spn_DownPayment_NOC_4.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_4.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_4.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_4.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGSAmount_4.setVisibility(View.GONE);
                    edt_DownPayment_ManufactureYear_4.setVisibility(View.GONE);
                    edt_DownPayment_oldTractorAmount_4.setVisibility(View.GONE);
                    edt_DownPayment_oldAmount_4.setVisibility(View.GONE);

                    lin_dp_cheque_4.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_4.setVisibility(View.GONE);
                    lin_dp_NocPhoto_4.setVisibility(View.GONE);
                    lin_dp_OldVehicle_4.setVisibility(View.GONE);
                    lin_dp_Rcbook_4.setVisibility(View.GONE);
                    lin_dp_Election_4.setVisibility(View.GONE);

                    lin_dp_Rcbook2_4.setVisibility(View.GONE);
                    lin_dp_Election2_4.setVisibility(View.GONE);
                }


                if(DpBookingAmount_4[position].equals("Bank"))
                {
                    spn_DownPayment_BankOpt_4.setVisibility(View.VISIBLE);

                    ArrayAdapter adapter = new ArrayAdapter(DownPaymentActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, DpBank_4);
                    spn_DownPayment_BankOpt_4.setAdapter(adapter);

                    spn_DownPayment_BankOpt_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            dp_Bank_4 = DpBank_4[position];

                            if(DpBank_4[position].equals("Cheque")){
                                edt_DownPayment_ChequeDate_4.setVisibility(View.VISIBLE);
                                edt_DownPayment_ChequeAmount_4.setVisibility(View.VISIBLE);
                                lin_dp_cheque_4.setVisibility(View.VISIBLE);

                                edt_DownPayment_CashAmount_4.setVisibility(View.GONE);

                                edt_DownPayment_NEFT_RTGS_date_4.setVisibility(View.GONE);
                                edt_DownPayment_NEFT_RTGSAmount_4.setVisibility(View.GONE);
                                edt_DownPayment_ManufactureYear_4.setVisibility(View.GONE);
                                edt_DownPayment_oldTractorAmount_4.setVisibility(View.GONE);
                                edt_DownPayment_oldAmount_4.setVisibility(View.GONE);

                                lin_dp_NEFT_RTGS_4.setVisibility(View.GONE);
                                lin_dp_NocPhoto_4.setVisibility(View.GONE);
                                lin_dp_OldVehicle_4.setVisibility(View.GONE);
                                lin_dp_Rcbook_4.setVisibility(View.GONE);
                                lin_dp_Election_4.setVisibility(View.GONE);

                                lin_dp_Rcbook2_4.setVisibility(View.GONE);
                                lin_dp_Election2_4.setVisibility(View.GONE);


                                spn_DownPayment_SelectMake_4.setVisibility(View.GONE);
                                spn_DownPayment_ModelVehicle_4.setVisibility(View.GONE);
                                spn_DownPayment_PaperEnhance_4.setVisibility(View.GONE);
                                spn_DownPayment_NOC_4.setVisibility(View.GONE);
                            }


                            if(DpBank_4[position].equals("NEFT/RTGS")){
                                edt_DownPayment_NEFT_RTGS_date_4.setVisibility(View.VISIBLE);
                                edt_DownPayment_NEFT_RTGSAmount_4.setVisibility(View.VISIBLE);
                                lin_dp_NEFT_RTGS_4.setVisibility(View.VISIBLE);

                                edt_DownPayment_ChequeDate_4.setVisibility(View.GONE);
                                edt_DownPayment_ChequeAmount_4.setVisibility(View.GONE);
                                lin_dp_cheque_4.setVisibility(View.GONE);

                                edt_DownPayment_CashAmount_4.setVisibility(View.GONE);
                                edt_DownPayment_ManufactureYear_4.setVisibility(View.GONE);
                                edt_DownPayment_oldTractorAmount_4.setVisibility(View.GONE);
                                edt_DownPayment_oldAmount_4.setVisibility(View.GONE);

                                lin_dp_NocPhoto_4.setVisibility(View.GONE);
                                lin_dp_OldVehicle_4.setVisibility(View.GONE);
                                lin_dp_Rcbook_4.setVisibility(View.GONE);
                                lin_dp_Election_4.setVisibility(View.GONE);

                                lin_dp_Rcbook2_4.setVisibility(View.GONE);
                                lin_dp_Election2_4.setVisibility(View.GONE);

                                spn_DownPayment_SelectMake_4.setVisibility(View.GONE);
                                spn_DownPayment_ModelVehicle_4.setVisibility(View.GONE);
                                spn_DownPayment_PaperEnhance_4.setVisibility(View.GONE);
                                spn_DownPayment_NOC_4.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if(DpBookingAmount_4[position].equals("Old Vehicle"))
                {
                    spn_DownPayment_SelectMake_4.setVisibility(View.VISIBLE);
                    spn_DownPayment_ModelVehicle_4.setVisibility(View.VISIBLE);

                    edt_DownPayment_ManufactureYear_4.setVisibility(View.VISIBLE);
                    edt_DownPayment_oldAmount_4.setVisibility(View.VISIBLE);

                    spn_DownPayment_PaperEnhance_4.setVisibility(View.VISIBLE);

                    lin_dp_OldVehicle_4.setVisibility(View.VISIBLE);
                    lin_dp_Rcbook_4.setVisibility(View.VISIBLE);
                    lin_dp_Election_4.setVisibility(View.VISIBLE);

                    lin_dp_Rcbook2_4.setVisibility(View.VISIBLE);
                    lin_dp_Election2_4.setVisibility(View.VISIBLE);

                    spn_DownPayment_NOC_4.setVisibility(View.VISIBLE);

                    edt_DownPayment_oldTractorAmount_4.setVisibility(View.GONE);

                    edt_DownPayment_CashAmount_4.setVisibility(View.GONE);

                    lin_dp_NocPhoto_4.setVisibility(View.GONE);
                    lin_dp_cheque_4.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_4.setVisibility(View.GONE);

                    edt_DownPayment_ChequeDate_4.setVisibility(View.GONE);
                    edt_DownPayment_ChequeAmount_4.setVisibility(View.GONE);

                    edt_DownPayment_NEFT_RTGSAmount_4.setVisibility(View.GONE);
                    edt_DownPayment_NEFT_RTGS_date_4.setVisibility(View.GONE);


                    WebService.getClient().getMakeDownPayment().enqueue(new Callback<MakeDownPaymentModel>() {
                        @Override
                        public void onResponse(@NotNull Call<MakeDownPaymentModel> call,
                                               @NotNull Response<MakeDownPaymentModel> response) {
                            if(response.isSuccessful()){
                                if(response.body()!=null){
                                    makeDp_Name_4.clear();
                                    MakeDP_ID_4.clear();

                                    makeDp_Name_4.add("Select Make");
                                    MakeDP_ID_4.add("0");

                                    for (int i = 0; i < response.body().getState().size(); i++)
                                    {
                                        makeDp_Name_4.add(response.body().getState().get(i).getMake());
                                        MakeDP_ID_4.add(response.body().getState().get(i).getId());
                                    }

                                    ArrayAdapter adapterMake = new ArrayAdapter(DownPaymentActivity.this,
                                            android.R.layout.simple_spinner_dropdown_item, makeDp_Name_4);
                                    spn_DownPayment_SelectMake_4.setAdapter(adapterMake);

                                    spn_DownPayment_SelectMake_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            MakeName_4 =   makeDp_Name_4.get(position);
                                            MakeID_4 = MakeDP_ID_4.get(position);

                                            WebService.getClient().getModelDownPayment(MakeID_4).
                                                    enqueue(new Callback<ModelDownPaymentModel>() {
                                                @Override
                                                public void onResponse(@NotNull Call<ModelDownPaymentModel> call,
                                                                       @NotNull Response<ModelDownPaymentModel> response) {
                                                    if(response.isSuccessful()){
                                                        if(response.body()!= null){
                                                            ModelDP_ID_4.clear();
                                                            modelDp_Name_4.clear();

                                                            modelDp_Name_4.add("Select Model");
                                                            ModelDP_ID_4.add("0");

                                                            for(int i =0; i < response.body().getModel().size();i++){
                                                                modelDp_Name_4.add(response.body().getModel().get(i).getModel_name());
                                                            }

                                                            ArrayAdapter adapterModel = new ArrayAdapter(DownPaymentActivity.this,
                                                                    android.R.layout.simple_spinner_dropdown_item,
                                                                    modelDp_Name_4);
                                                            spn_DownPayment_ModelVehicle_4.setAdapter(adapterModel);

                                                            spn_DownPayment_ModelVehicle_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    modelName_4 = modelDp_Name_4.get(position);
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ModelDownPaymentModel> call, Throwable t) {

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
                        public void onFailure(Call<MakeDownPaymentModel> call, Throwable t) {

                        }
                    });


                    ArrayAdapter adapterPaperE = new ArrayAdapter(DownPaymentActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, PaperEnhance_4);
                    spn_DownPayment_PaperEnhance_4.setAdapter(adapterPaperE);

                    spn_DownPayment_PaperEnhance_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            PaperEnhance_DP_4 = PaperEnhance_4[position];

                            if (PaperEnhance_DP_4.equals("Customer")){
                                edt_DownPayment_oldTractorAmount_4.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter adapterNoc = new ArrayAdapter(DownPaymentActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, NOC_4);
                    spn_DownPayment_NOC_4.setAdapter(adapterNoc);

                    spn_DownPayment_NOC_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Noc_DP_4 = NOC_4[position];

                            if(Noc_DP_4.equals("Yes")){
                                lin_dp_NocPhoto_4.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


       /* txt_DownPayment_UploadCheque_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 26);
            }
        });


        txt_DownPayment_UploadNEFT_RTGS_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 27);
            }
        });

        txt_DownPayment_UploadNocPhoto_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 28);
            }
        });

        txt_DownPayment_UploadOldVehicle_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 29);
            }
        });

        txt_DownPayment_UploadRCBook_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 30);
            }
        });

        txt_DownPayment_UploadElectionPhoto_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 31);
            }
        });

         txt_DownPayment_UploadRCBook2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 32);
            }
        });

        txt_DownPayment_UploadElectionPhoto2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 33);
            }
        });*/


        /********************************************** Addition of three value *************************************************************/
      /*  edt_DownPayment_CashAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                x = Integer.parseInt(edt_DownPayment_CashAmount.getText().toString());
                // Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                x = Integer.parseInt(edt_DownPayment_CashAmount.getText().toString());
                Log.d("CashAmount", "afterTextChanged: "+CashAmount);
                //  Toast.makeText(DownPaymentActivity.this, ""+numToWord, Toast.LENGTH_SHORT).show();
            }
        });*/




          /*edt_loanDetail_LoanAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                x = Integer.parseInt(edt_loanDetail_LoanAmount.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                x = Integer.parseInt(edt_loanDetail_LoanAmount.getText().toString());
            }
        });

        edt_loanDetail_LoanSancAmont.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                y = Integer.parseInt(edt_loanDetail_LoanSancAmont.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                y = Integer.parseInt(edt_loanDetail_LoanSancAmont.getText().toString());

              //  Toast.makeText(LoanFillFormActivity.this, ""+y+x, Toast.LENGTH_SHORT).show();

                sub = x - y; //Perform Maths operation
                edt_loanDetail_LoanCharge.setText(""+ sub);//print answer
              //  Toast.makeText(LoanFillFormActivity.this, ""+x+y+sub, Toast.LENGTH_SHORT).show();

            }
        });*/





       /* if(edt_DownPayment_CashAmount.getText().toString() == ""){
            numberCash = 0;
        }
       *//* else{
            int m= Integer.parseInt(edt_DownPayment_CashAmount.getText().toString());
            Toast.makeText(this, ""+m, Toast.LENGTH_SHORT).show();
            numberCash = m;
        }*//*


        if(edt_DownPayment_ChequeAmount_2.getText().toString()== null){
            NumberCheque = 0;
        }
       *//* else{
            NumberCheque = Integer.parseInt(edt_DownPayment_ChequeAmount_2.getText().toString());
        }*//*


        if(edt_DownPayment_NEFT_RTGS_date_2.getText().toString()== null){
            NumberNeftRtgs = 0;
        }
        *//*else{
            NumberNeftRtgs = Integer.parseInt(edt_DownPayment_NEFT_RTGS_date_2.getText().toString());
        }*//*


        if(edt_DownPayment_oldAmount_3.getText().toString() == null){
            NumberOldVehical = 0;
        }
        *//*else{
            NumberOldVehical = Integer.parseInt(edt_DownPayment_oldAmount_3.getText().toString());
        }*//*


        addition = number_cashAmount + number_2+ number_neftRtgs + number_oldamount; //Perform Maths operation
        additionThree = addition+"";

        Toast.makeText(this, ""+additionThree, Toast.LENGTH_SHORT).show();
        Log.d("addition", "onCreate: "+additionThree);*/


        /*************************************Next Button Final API ***********************************************/


        btn_DownPayment_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (spn_DownPayment_BAmount.getSelectedItem().toString().trim().equals("Select Booking Amount")) {
                    Utils.showErrorToast(DownPaymentDealstageActivity.this, "Select Booking Amount");

                }else if(edt_DownPayment_CashAmount.getText().toString().equals("")){
                    Utils.showErrorToast(DownPaymentDealstageActivity.this, "Please Enter Amount");

                }else {

                    progressDialog = new ProgressDialog(DownPaymentDealstageActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    //--------------------------------------------------------------------------
               /* if(edt_DownPayment_CashAmount.getText().toString().equals(""))
                {
                    cashAmountAll.remove(edt_DownPayment_CashAmount.getText().toString());
                }
                else{
                    cashAmountAll.add(edt_DownPayment_CashAmount.getText().toString());
                }

                if(edt_DownPayment_CashAmount_2.getText().toString().equals(""))
                {
                    cashAmountAll.remove(edt_DownPayment_CashAmount_2.getText().toString());
                }
                else{
                    cashAmountAll.add(edt_DownPayment_CashAmount_2.getText().toString());
                }

                if(edt_DownPayment_CashAmount_3.getText().toString().equals(""))
                {
                    cashAmountAll.remove(edt_DownPayment_CashAmount_3.getText().toString());
                }
                else{
                    cashAmountAll.add(edt_DownPayment_CashAmount_3.getText().toString());
                }

                if(edt_DownPayment_CashAmount_4.getText().toString().equals(""))
                {
                    cashAmountAll.remove(edt_DownPayment_CashAmount_4.getText().toString());
                }
                else{
                    cashAmountAll.add(edt_DownPayment_CashAmount_4.getText().toString());
                }

                String CheckCategoryFilterItem = "";

                for(int i=0; i<=cashAmountAll.size();i++)
                {
                    if(CheckCategoryFilterItem.equals("")){
                        try {
                            CheckCategoryFilterItem = cashAmountAll.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            CheckCategoryFilterItem = CheckCategoryFilterItem+","+cashAmountAll.get(i);
                            Log.d("BBList", "onCreate: "+CheckCategoryFilterItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }*/

                    //-----------------------------------------------------------------------

                    if (edt_DownPayment_CashAmount.getText().toString() == "") {
                        numberCash = 0;
                    }

                    if (edt_DownPayment_ChequeAmount_2.getText().toString() == null) {
                        NumberCheque = 0;
                    }


                    if (edt_DownPayment_NEFT_RTGS_date_2.getText().toString() == null) {
                        NumberNeftRtgs = 0;
                    }


                    if (edt_DownPayment_oldAmount_3.getText().toString() == null) {
                        NumberOldVehical = 0;
                    }


                    addition = number_cashAmount + number_2 + number_neftRtgs + number_oldamount; //Perform Maths operation
                    additionThree = addition + "";

                    String wordAddition = NumToWordDealstage.GFG.convertToWords(addition);

                    AddData = wordAddition;

                    // Toast.makeText(DownPaymentActivity.this, "" + additionThree+AddData, Toast.LENGTH_SHORT).show();
                    Log.d("addition", "onCreate: " + additionThree + AddData);


                    //--------------------------------------------------------------------------
                    if (dp_bookingAmount == null || dp_bookingAmount.equals("Select Booking Amount")) {
                        dp_bookingAmountArray.remove(dp_bookingAmount);
                    } else {
                        dp_bookingAmountArray.add(dp_bookingAmount);
                    }

                    if (dp_bookingAmount_2 == null || dp_bookingAmount_2.equals("Select Booking Amount")) {
                        dp_bookingAmountArray.remove(dp_bookingAmount_2);
                    } else {
                        dp_bookingAmountArray.add(dp_bookingAmount_2);
                    }

                    if (dp_bookingAmount_3 == null || dp_bookingAmount_3.equals("Select Booking Amount")) {
                        dp_bookingAmountArray.remove(dp_bookingAmount_3);
                    } else {
                        dp_bookingAmountArray.add(dp_bookingAmount_3);
                    }


                    String dp_bookingAmountArrayItem = "";

                    for (int i = 0; i <= dp_bookingAmountArray.size(); i++) {
                        if (dp_bookingAmountArrayItem.equals("")) {
                            try {
                                dp_bookingAmountArrayItem = dp_bookingAmountArray.get(i);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                dp_bookingAmountArrayItem = dp_bookingAmountArrayItem + "," + dp_bookingAmountArray.get(i);
                                Log.d("BBList_ll", "onCreate: " + dp_bookingAmountArrayItem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    //---------------------------------------------------------------------------

              /*  if(dp_Bank.equals(""))
                {
                    dp_BankArray.remove(dp_Bank);
                }
                else{
                    dp_BankArray.add(dp_Bank);
                }

                if(dp_Bank_2.equals(""))
                {
                    dp_BankArray.remove(dp_Bank_2);
                }
                else{
                    dp_BankArray.add(dp_Bank_2);
                }

                if(dp_bookingAmount_3.equals(""))
                {
                    dp_BankArray.remove(dp_bookingAmount_3);
                }
                else{
                    dp_BankArray.add(dp_bookingAmount_3);
                }

                if(dp_Bank_3.equals(""))
                {
                    dp_BankArray.remove(dp_Bank_3);
                }
                else{
                    dp_BankArray.add(dp_Bank_3);
                }

                String dp_BankArrayItem = "";

                for(int i=0; i<=dp_BankArray.size();i++)
                {
                    if(dp_BankArrayItem.equals("")){
                        try {
                            dp_BankArrayItem = dp_BankArray.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            dp_BankArrayItem = dp_BankArrayItem+","+dp_BankArray.get(i);
                            Log.d("BBList", "onCreate: "+dp_bookingAmountArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
*/
                    //--------------------------------------------------------------------------

                    //---------------------------------------------------------------------------

               /* if(edt_DownPayment_ChequeDate.getText().toString().equals(""))
                {
                    edt_ChequeDateArray.remove(edt_DownPayment_ChequeDate.getText().toString());
                }
                else{
                    edt_ChequeDateArray.add(edt_DownPayment_ChequeDate.getText().toString());
                }

                if(edt_DownPayment_ChequeDate_2.getText().toString().equals(""))
                {
                    edt_ChequeDateArray.remove(edt_DownPayment_ChequeDate_2.getText().toString());
                }
                else{
                    edt_ChequeDateArray.add(edt_DownPayment_ChequeDate_2.getText().toString());
                }

                if(edt_DownPayment_ChequeDate_3.getText().toString().equals(""))
                {
                    edt_ChequeDateArray.remove(edt_DownPayment_ChequeDate_3.getText().toString());
                }
                else{
                    edt_ChequeDateArray.add(edt_DownPayment_ChequeDate_3.getText().toString());
                }

                if(edt_DownPayment_ChequeDate_4.getText().toString().equals(""))
                {
                    edt_ChequeDateArray.remove(edt_DownPayment_ChequeDate_4.getText().toString());
                }
                else{
                    edt_ChequeDateArray.add(edt_DownPayment_ChequeDate_4.getText().toString());
                }

                String edt_ChequeDateArrayItem = "";

                for(int i=0; i<=edt_ChequeDateArray.size();i++)
                {
                    if(edt_ChequeDateArrayItem.equals("")){
                        try {
                            edt_ChequeDateArrayItem = edt_ChequeDateArray.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            edt_ChequeDateArrayItem = edt_ChequeDateArrayItem+","+edt_ChequeDateArray.get(i);
                            Log.d("BBList", "onCreate: "+edt_ChequeDateArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }*/

                    //--------------------------------------------------------------------------

                    //---------------------------------------------------------------------------

               /* if(edt_DownPayment_ChequeAmount.getText().toString().equals(""))
                {
                    edt_ChequeAmountArray.remove(edt_DownPayment_ChequeAmount.getText().toString());
                }
                else{
                    edt_ChequeAmountArray.add(edt_DownPayment_ChequeAmount.getText().toString());
                }

                if(edt_DownPayment_ChequeAmount_2.getText().toString().equals(""))
                {
                    edt_ChequeAmountArray.remove(edt_DownPayment_ChequeAmount_2.getText().toString());
                }
                else{
                    edt_ChequeAmountArray.add(edt_DownPayment_ChequeAmount_2.getText().toString());
                }

                if(edt_DownPayment_ChequeDate_3.getText().toString().equals(""))
                {
                    edt_ChequeAmountArray.remove(edt_DownPayment_ChequeDate_3.getText().toString());
                }
                else{
                    edt_ChequeAmountArray.add(edt_DownPayment_ChequeDate_3.getText().toString());
                }*/

              /*  if(edt_DownPayment_ChequeDate_4.getText().toString().equals(""))
                {
                    edt_ChequeAmountArray.remove(edt_DownPayment_ChequeDate_4.getText().toString());
                }
                else{
                    edt_ChequeAmountArray.add(edt_DownPayment_ChequeDate_4.getText().toString());
                }*/

               /* String edt_ChequeAmountArrayItem = "";

                for(int i=0; i<=edt_ChequeDateArray.size();i++)
                {
                    if(edt_ChequeAmountArrayItem.equals("")){
                        try {
                            edt_ChequeAmountArrayItem = edt_ChequeDateArray.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            edt_ChequeAmountArrayItem = edt_ChequeAmountArrayItem+","+edt_ChequeDateArray.get(i);
                            Log.d("BBList", "onCreate: "+edt_ChequeDateArrayItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }*/

                    //--------------------------------------------------------------------------


                    // List<MultipartBody.Part> imagepart_cheque = new ArrayList();


                    MultipartBody.Part imagePartCheque = null;
                    MultipartBody.Part imagePartCheque_2 = null;
                    MultipartBody.Part imagePartCheque_3 = null;
                    MultipartBody.Part imagePartCheque_4 = null;

                    MultipartBody.Part imagePartNEFT_RTGS = null;
                    MultipartBody.Part imagePartNEFT_RTGS_2 = null;
                    MultipartBody.Part imagePartNoc = null;
                    MultipartBody.Part imagePartNoc_3 = null;
                    MultipartBody.Part imagePassOLDVehicle = null;
                    MultipartBody.Part imagePassOLDVehicle_3 = null;
                    MultipartBody.Part imagePartRCBook = null;
                    MultipartBody.Part imagePartRCBook_3 = null;
                    MultipartBody.Part imagePartElectionPhoto = null;
                    MultipartBody.Part imagePartElectionPhoto_3 = null;

                    MultipartBody.Part imagePartRCBook2 = null;
                    MultipartBody.Part imagePartRCBook2_3 = null;
                    MultipartBody.Part imagePartElectionPhoto2 = null;
                    MultipartBody.Part imagePartElectionPhoto2_3 = null;


                    ArrayList<MultipartBody.Part> imagepart_cheque = new ArrayList();

                    //   List<MultipartBody.Part> imagepart_cheque = new ArrayList<>();
                    // MultipartBody.Part[] imagepart_cheque = new MultipartBody.Part[surveyModel.getPicturesList().size()];
                    // MultipartBody.Part[] imagepart_cheque = new MultipartBody.Part;


                    if (waypathUploadCheque != null) {

                        File file1 = new File(waypathUploadCheque);
                        final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        imagePartCheque = MultipartBody.Part.createFormData("image1",
                                file1.getName(), requestBody1);
                    }

                    //   imagepart_cheque.add(imagePartCheque);
                /*  }
                  else{
                      imagepart_cheque.remove(imagePartCheque);
                  }*/

               /* if (waypathUploadCheque_2 != null) {

                    File file1 = new File(waypathUploadCheque_2);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    imagePartCheque_2 = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);*/

                    //  imagepart_cheque.add(imagePartCheque_2);
               /* }
                else{
                    imagepart_cheque.remove(imagePartCheque_2);
                }*/

               /* if (waypathUploadCheque_3 != null) {

                    File file1 = new File(waypathUploadCheque_3);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    imagePartCheque_3 = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);

                  //  imagepart_cheque.add(imagePartCheque_3);
                }*/
                /*else{
                    imagepart_cheque.remove(imagePartCheque_3);
                }*/

                /*if (waypathUploadCheque_4 != null) {

                    File file1 = new File(waypathUploadCheque_4);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    imagePartCheque_4 = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);

                    //imagepart_cheque.add(imagePartCheque_4);
                }*/
               /* else{
                    imagepart_cheque.remove(imagePartCheque_4);
                }*/

               /* MultipartBody.Part imageCheque = null;

                for(int i=0; i<=imagepart_cheque.size();i++)
                {
                    if(imageCheque == null){
                        try {
                            imageCheque = imagepart_cheque.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                           // imageCheque = imageCheque+","+imagepart_cheque.get(i);
                            imageCheque =  imagepart_cheque.get(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }*/


                    //------------------------------------------------------------------------------------------

                    if (waypathUploadNEFT_RTGS != null) {
                        File file2 = new File(waypathUploadNEFT_RTGS);
                        final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        imagePartNEFT_RTGS = MultipartBody.Part.createFormData("image2",
                                file2.getName(), requestBody2);
                    }


                    if (waypathUploadNEFT_RTGS_2 != null) {
                        File file2 = new File(waypathUploadNEFT_RTGS_2);
                        final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        imagePartNEFT_RTGS_2 = MultipartBody.Part.createFormData("image2",
                                file2.getName(), requestBody2);
                    }
                    //------------------------------------------------------------------------------------------

                    if (waypathUploadOldVehicle != null) {
                        File file4 = new File(waypathUploadOldVehicle);
                        final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        imagePassOLDVehicle = MultipartBody.Part.createFormData("image3",
                                file4.getName(), requestBody4);
                    }

                    if (waypathUploadOldVehicle_3 != null) {
                        File file4 = new File(waypathUploadOldVehicle_3);
                        final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        imagePassOLDVehicle_3 = MultipartBody.Part.createFormData("image3",
                                file4.getName(), requestBody4);
                    }

                    //------------------------------------------------------------------------------------------

                    if (waypathUploadRCBook != null) {
                        File file5 = new File(waypathUploadRCBook);
                        final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                        imagePartRCBook = MultipartBody.Part.createFormData("image4",
                                file5.getName(), requestBody5);
                    }

                    if (waypathUploadRCBook_3 != null) {
                        File file5 = new File(waypathUploadRCBook_3);
                        final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                        imagePartRCBook_3 = MultipartBody.Part.createFormData("image4",
                                file5.getName(), requestBody5);
                    }

                    //------------------------------------------------------------------------------------------

                    if (waypathUploadElectionPhoto != null) {
                        File file6 = new File(waypathUploadElectionPhoto);
                        final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                        imagePartElectionPhoto = MultipartBody.Part.createFormData("image5",
                                file6.getName(), requestBody6);
                    }

                    if (waypathUploadElectionPhoto_3 != null) {
                        File file6 = new File(waypathUploadElectionPhoto_3);
                        final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                        imagePartElectionPhoto_3 = MultipartBody.Part.createFormData("image5",
                                file6.getName(), requestBody6);
                    }

                    //------------------------------------------------------------------------------------------

                    if (waypathUploadNocPhoto != null) {
                        File file3 = new File(waypathUploadNocPhoto);
                        final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        imagePartNoc = MultipartBody.Part.createFormData("image6",
                                file3.getName(), requestBody3);
                    }

                    if (waypathUploadNocPhoto_3 != null) {
                        File file3 = new File(waypathUploadNocPhoto_3);
                        final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        imagePartNoc_3 = MultipartBody.Part.createFormData("image6",
                                file3.getName(), requestBody3);
                    }

                    //------------------------------------------------------------------------------------------

                    if (waypathUploadRCBook2 != null) {
                        File file7 = new File(waypathUploadRCBook2);
                        final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                        imagePartRCBook2 = MultipartBody.Part.createFormData("imgg6",
                                file7.getName(), requestBody7);
                    }

                    if (waypathUploadRCBook2_3 != null) {
                        File file7 = new File(waypathUploadRCBook2_3);
                        final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                        imagePartRCBook2_3 = MultipartBody.Part.createFormData("imgg6",
                                file7.getName(), requestBody7);
                    }

                    //------------------------------------------------------------------------------------------


                    if (waypathUploadElectionPhoto2 != null) {
                        File file8 = new File(waypathUploadElectionPhoto2);
                        final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                        imagePartElectionPhoto2 = MultipartBody.Part.createFormData("imgg7",
                                file8.getName(), requestBody8);
                    }

                    if (waypathUploadElectionPhoto2_3 != null) {
                        File file8 = new File(waypathUploadElectionPhoto2_3);
                        final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                        imagePartElectionPhoto2_3 = MultipartBody.Part.createFormData("imgg7",
                                file8.getName(), requestBody8);
                    }

                    if (dp_Bank_2 == null) {
                        dp_Bank_2 = " ";
                    }

                    if (MakeName_3 == null) {
                        MakeName_3 = " ";
                    }

                    if (modelName_3 == null) {
                        modelName_3 = " ";
                    }

                    if (PaperEnhance_DP_3 == null) {
                        PaperEnhance_DP_3 = " ";
                    }

                    if (Noc_DP_3 == null) {
                        Noc_DP_3 = " ";
                    }

                    if (CashAmount == null) {
                        CashAmount = " ";
                    }

                    if (ChequeAmount_2 == null) {
                        ChequeAmount_2 = " ";
                    }

                    if (NeftRtgsAmount_2 == null) {
                        NeftRtgsAmount_2 = " ";

                    }

                    if (OldVehicleAmount_3 == null) {
                        OldVehicleAmount_3 = " ";
                    }


                    if (oldTAmt_3 == null) {
                        oldTAmt_3 = " ";
                    }

                    //  Toast.makeText(DownPaymentActivity.this, ""+dp_bookingAmountArrayItem, Toast.LENGTH_LONG).show();


                    WebService.getClient().getDownPaymentNext(
                            RequestBody.create(MediaType.parse("text/plain"), dp_bookingAmountArrayItem),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_CashAmount.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), dp_Bank_2),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_ChequeDate_2.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_ChequeAmount_2.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_NEFT_RTGS_date_2.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_NEFT_RTGSAmount_2.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), MakeName_3),
                            RequestBody.create(MediaType.parse("text/plain"), modelName_3),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_ManufactureYear_3.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_Registeration_Number_3.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_oldAmount_3.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), PaperEnhance_DP_3),
                            RequestBody.create(MediaType.parse("text/plain"), edt_DownPayment_oldTractorAmount_3.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), Noc_DP_3),
                            RequestBody.create(MediaType.parse("text/plain"), "4"),
                            RequestBody.create(MediaType.parse("text/plain"), NextIDD),
                            RequestBody.create(MediaType.parse("text/plain"), CashAmount),
                            RequestBody.create(MediaType.parse("text/plain"), ChequeAmount_2),
                            RequestBody.create(MediaType.parse("text/plain"), NeftRtgsAmount_2),
                            RequestBody.create(MediaType.parse("text/plain"), OldVehicleAmount_3),
                            RequestBody.create(MediaType.parse("text/plain"), AddData),
                            /*RequestBody.create(MediaType.parse("text/plain"),oldTAmt_3),*/
                            imagePartCheque_2,
                            /* imagepart_cheque,*/
                            imagePartNEFT_RTGS_2,
                            imagePassOLDVehicle_3,
                            imagePartRCBook_3,
                            imagePartElectionPhoto_3,
                            imagePartNoc_3,
                            imagePartRCBook2_3,
                            imagePartElectionPhoto2_3

                    ).enqueue(new Callback<DownPaymentNextModel>() {
                        @Override
                        public void onResponse(@NotNull Call<DownPaymentNextModel> call, @NotNull Response<DownPaymentNextModel> response) {
                            progressDialog.dismiss();


                            assert response.body() != null;
                            Toast.makeText(DownPaymentDealstageActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            // Intent i = new Intent(DownPaymentActivity.this,CustomerSkimActivity.class);
                            Intent i = new Intent(DownPaymentDealstageActivity.this, RtoDetailsDealstageActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(@NotNull Call<DownPaymentNextModel> call, @NotNull Throwable t) {
                            progressDialog.dismiss();
                            Log.d("Imagearray", "onFailure: " + imagepart_cheque + t.getMessage());
                        }
                    });

                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /********************************** First data Image *********************************************************/

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadCheque = data.getData();
                    Log.d("PanImageUri", uriUploadCheque.toString());
                    waypathUploadCheque = getFilePath(this, uriUploadCheque);

                  //  Toast.makeText(DownPaymentActivity.this, ""+waypathUploadCheque, Toast.LENGTH_SHORT).show();

                    Log.d("PanImage", waypathUploadCheque);
                    String[] name = waypathUploadCheque.split("/");
                    txt_DownPayment_UploadCheque.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_Cheque.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadNEFT_RTGS = data.getData();
                    Log.d("PanImageUri", uriUploadNEFT_RTGS.toString());
                    waypathUploadNEFT_RTGS = getFilePath(this, uriUploadNEFT_RTGS);

                    Log.d("PanRTGS", waypathUploadNEFT_RTGS);
                    String[] name = waypathUploadNEFT_RTGS.split("/");
                    txt_DownPayment_UploadNEFT_RTGS.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_NEFT_RTGS.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadNocPhoto = data.getData();
                    Log.d("Pan Image Uri", uriUploadNocPhoto.toString());
                    waypathUploadNocPhoto = getFilePath(this, uriUploadNocPhoto);
                    Log.d("Pan Image Uri", waypathUploadNocPhoto);
                    String[] name = waypathUploadNocPhoto.split("/");
                    txt_DownPayment_UploadNocPhoto.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_NocPhoto.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadOldVehicle = data.getData();
                    Log.d("Pan Image Uri", uriUploadOldVehicle.toString());
                    waypathUploadOldVehicle = getFilePath(this, uriUploadOldVehicle);
                    Log.d("Pan Image Uri", waypathUploadOldVehicle);
                    String[] name = waypathUploadOldVehicle.split("/");
                    txt_DownPayment_UploadOldVehicle.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_OldVehicle.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadRCBook = data.getData();
                    Log.d("Pan Image Uri", uriUploadRCBook.toString());
                    waypathUploadRCBook = getFilePath(this, uriUploadRCBook);
                    Log.d("Pan Image Uri", waypathUploadRCBook);
                    String[] name = waypathUploadRCBook.split("/");
                    txt_DownPayment_UploadRCBook.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_RcBook.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadElectionPhoto = data.getData();
                    Log.d("Pan Image Uri", uriUploadElectionPhoto.toString());
                    waypathUploadElectionPhoto = getFilePath(this, uriUploadElectionPhoto);
                    Log.d("Pan Image Uri", waypathUploadElectionPhoto);
                    String[] name = waypathUploadElectionPhoto.split("/");
                    txt_DownPayment_UploadElectionPhoto.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_ElectionPhoto.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadRCBook2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadRCBook2.toString());
                    waypathUploadRCBook2 = getFilePath(this, uriUploadRCBook2);
                    Log.d("Pan Image Uri", waypathUploadRCBook2);
                    String[] name = waypathUploadRCBook2.split("/");
                    txt_DownPayment_UploadRCBook2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_RcBook2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadElectionPhoto2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadElectionPhoto2.toString());
                    waypathUploadElectionPhoto2 = getFilePath(this, uriUploadElectionPhoto2);
                    Log.d("Pan Image Uri", waypathUploadElectionPhoto2);
                    String[] name = waypathUploadElectionPhoto2.split("/");
                    txt_DownPayment_UploadElectionPhoto2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_ElectionPhoto2.setImageURI(selectedImageUri);
                }
            }
        }


        /********************************** Second data Image *********************************************************/

        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadCheque_2 = data.getData();
                    Log.d("PanImageUri", uriUploadCheque_2.toString());
                    waypathUploadCheque_2 = getFilePath(this, uriUploadCheque_2);

                    //  Toast.makeText(DownPaymentActivity.this, ""+waypathUploadCheque, Toast.LENGTH_SHORT).show();

                    Log.d("PanImage", waypathUploadCheque_2);
                    String[] name = waypathUploadCheque_2.split("/");
                    txt_DownPayment_UploadCheque_2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_Cheque_2.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadNEFT_RTGS_2 = data.getData();
                    Log.d("PanImageUri", uriUploadNEFT_RTGS_2.toString());
                    waypathUploadNEFT_RTGS_2 = getFilePath(this, uriUploadNEFT_RTGS_2);

                    Log.d("PanRTGS", waypathUploadNEFT_RTGS_2);
                    String[] name = waypathUploadNEFT_RTGS_2.split("/");
                    txt_DownPayment_UploadNEFT_RTGS_2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_NEFT_RTGS_2.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadNocPhoto_2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadNocPhoto_2.toString());
                    waypathUploadNocPhoto_2 = getFilePath(this, uriUploadNocPhoto_2);
                    Log.d("Pan Image Uri", waypathUploadNocPhoto_2);
                    String[] name = waypathUploadNocPhoto_2.split("/");
                    txt_DownPayment_UploadNocPhoto_2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_NocPhoto_2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 13) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadOldVehicle_2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadOldVehicle_2.toString());
                    waypathUploadOldVehicle_2 = getFilePath(this, uriUploadOldVehicle_2);
                    Log.d("Pan Image Uri", waypathUploadOldVehicle_2);
                    String[] name = waypathUploadOldVehicle_2.split("/");
                    txt_DownPayment_UploadOldVehicle_2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_OldVehicle_2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 14) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadRCBook_2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadRCBook_2.toString());
                    waypathUploadRCBook_2 = getFilePath(this, uriUploadRCBook_2);
                    Log.d("Pan Image Uri", waypathUploadRCBook_2);
                    String[] name = waypathUploadRCBook_2.split("/");
                    txt_DownPayment_UploadRCBook_2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_RcBook_2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 15) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadElectionPhoto_2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadElectionPhoto_2.toString());
                    waypathUploadElectionPhoto_2 = getFilePath(this, uriUploadElectionPhoto_2);
                    Log.d("Pan Image Uri", waypathUploadElectionPhoto_2);
                    String[] name = waypathUploadElectionPhoto_2.split("/");
                    txt_DownPayment_UploadElectionPhoto_2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_ElectionPhoto_2.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadRCBook2_2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadRCBook2_2.toString());
                    waypathUploadRCBook2_2 = getFilePath(this, uriUploadRCBook2_2);
                    Log.d("Pan Image Uri", waypathUploadRCBook2_2);
                    String[] name = waypathUploadRCBook2_2.split("/");
                    txt_DownPayment_UploadRCBook2_2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_RcBook2_2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 17) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadElectionPhoto2_2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadElectionPhoto2_2.toString());
                    waypathUploadElectionPhoto2_2 = getFilePath(this, uriUploadElectionPhoto2_2);
                    Log.d("Pan Image Uri", waypathUploadElectionPhoto2_2);
                    String[] name = waypathUploadElectionPhoto2_2.split("/");
                    txt_DownPayment_UploadElectionPhoto2_2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_ElectionPhoto2_2.setImageURI(selectedImageUri);
                }
            }
        }

        /********************************** Third data Image *********************************************************/

        if (requestCode == 18) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadCheque_3 = data.getData();
                    Log.d("PanImageUri", uriUploadCheque_3.toString());
                    waypathUploadCheque_3 = getFilePath(this, uriUploadCheque_3);

                    //  Toast.makeText(DownPaymentActivity.this, ""+waypathUploadCheque, Toast.LENGTH_SHORT).show();

                    Log.d("PanImage", waypathUploadCheque_3);
                    String[] name = waypathUploadCheque_3.split("/");
                    txt_DownPayment_UploadCheque_3.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_Cheque_3.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadNEFT_RTGS_3 = data.getData();
                    Log.d("PanImageUri", uriUploadNEFT_RTGS_3.toString());
                    waypathUploadNEFT_RTGS_3 = getFilePath(this, uriUploadNEFT_RTGS_3);

                    Log.d("PanRTGS", waypathUploadNEFT_RTGS_3);
                    String[] name = waypathUploadNEFT_RTGS_3.split("/");
                    txt_DownPayment_UploadNEFT_RTGS_3.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_NEFT_RTGS_3.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadNocPhoto_3 = data.getData();
                    Log.d("Pan Image Uri", uriUploadNocPhoto_3.toString());
                    waypathUploadNocPhoto_3 = getFilePath(this, uriUploadNocPhoto_3);
                    Log.d("Pan Image Uri", waypathUploadNocPhoto_3);
                    String[] name = waypathUploadNocPhoto_3.split("/");
                    txt_DownPayment_UploadNocPhoto_3.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_NocPhoto_3.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 21) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadOldVehicle_3 = data.getData();
                    Log.d("Pan Image Uri", uriUploadOldVehicle_3.toString());
                    waypathUploadOldVehicle_3 = getFilePath(this, uriUploadOldVehicle_3);
                    Log.d("Pan Image Uri", waypathUploadOldVehicle_3);
                    String[] name = waypathUploadOldVehicle_3.split("/");
                    txt_DownPayment_UploadOldVehicle_3.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_OldVehicle_3.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 22) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadRCBook_3 = data.getData();
                    Log.d("Pan Image Uri", uriUploadRCBook_3.toString());
                    waypathUploadRCBook_3 = getFilePath(this, uriUploadRCBook_3);
                    Log.d("Pan Image Uri", waypathUploadRCBook_3);
                    String[] name = waypathUploadRCBook_3.split("/");
                    txt_DownPayment_UploadRCBook_3.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_RcBook_3.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode ==23) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadElectionPhoto_3 = data.getData();
                    Log.d("Pan Image Uri", uriUploadElectionPhoto_3.toString());
                    waypathUploadElectionPhoto_3 = getFilePath(this, uriUploadElectionPhoto_3);
                    Log.d("Pan Image Uri", waypathUploadElectionPhoto_3);
                    String[] name = waypathUploadElectionPhoto_3.split("/");
                    txt_DownPayment_UploadElectionPhoto_3.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_ElectionPhoto_3.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 24) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadRCBook2_3 = data.getData();
                    Log.d("Pan Image Uri", uriUploadRCBook2_3.toString());
                    waypathUploadRCBook2_3 = getFilePath(this, uriUploadRCBook2_3);
                    Log.d("Pan Image Uri", waypathUploadRCBook2_3);
                    String[] name = waypathUploadRCBook2_3.split("/");
                    txt_DownPayment_UploadRCBook2_3.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_RcBook2_3.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 25) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadElectionPhoto2_3 = data.getData();
                    Log.d("Pan Image Uri", uriUploadElectionPhoto2_3.toString());
                    waypathUploadElectionPhoto2_3 = getFilePath(this, uriUploadElectionPhoto2_3);
                    Log.d("Pan Image Uri", waypathUploadElectionPhoto2_3);
                    String[] name = waypathUploadElectionPhoto2_3.split("/");
                    txt_DownPayment_UploadElectionPhoto2_3.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_ElectionPhoto2_3.setImageURI(selectedImageUri);
                }
            }
        }

        /******************************************Fourth Data***************************************************************************/

        if (requestCode == 26) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadCheque_4 = data.getData();
                    Log.d("PanImageUri", uriUploadCheque_4.toString());
                    waypathUploadCheque_4 = getFilePath(this, uriUploadCheque_4);

                    //  Toast.makeText(DownPaymentActivity.this, ""+waypathUploadCheque, Toast.LENGTH_SHORT).show();

                    Log.d("PanImage", waypathUploadCheque_4);
                    String[] name = waypathUploadCheque_4.split("/");
                    txt_DownPayment_UploadCheque_4.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_Cheque_4.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 27) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadNEFT_RTGS_4 = data.getData();
                    Log.d("PanImageUri", uriUploadNEFT_RTGS_4.toString());
                    waypathUploadNEFT_RTGS_4 = getFilePath(this, uriUploadNEFT_RTGS_4);

                    Log.d("PanRTGS", waypathUploadNEFT_RTGS_4);
                    String[] name = waypathUploadNEFT_RTGS_4.split("/");
                    txt_DownPayment_UploadNEFT_RTGS_4.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_NEFT_RTGS_4.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 28) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadNocPhoto_4 = data.getData();
                    Log.d("Pan Image Uri", uriUploadNocPhoto_4.toString());
                    waypathUploadNocPhoto_4 = getFilePath(this, uriUploadNocPhoto_4);
                    Log.d("Pan Image Uri", waypathUploadNocPhoto_4);
                    String[] name = waypathUploadNocPhoto_4.split("/");
                    txt_DownPayment_UploadNocPhoto_4.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_NocPhoto_4.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 29) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadOldVehicle_4 = data.getData();
                    Log.d("Pan Image Uri", uriUploadOldVehicle_4.toString());
                    waypathUploadOldVehicle_4 = getFilePath(this, uriUploadOldVehicle_4);
                    Log.d("Pan Image Uri", waypathUploadOldVehicle_4);
                    String[] name = waypathUploadOldVehicle_4.split("/");
                    txt_DownPayment_UploadOldVehicle_4.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_OldVehicle_4.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 30) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadRCBook_4 = data.getData();
                    Log.d("Pan Image Uri", uriUploadRCBook_4.toString());
                    waypathUploadRCBook_4 = getFilePath(this, uriUploadRCBook_4);
                    Log.d("Pan Image Uri", waypathUploadRCBook_4);
                    String[] name = waypathUploadRCBook_4.split("/");
                    txt_DownPayment_UploadRCBook_4.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_RcBook_4.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode ==31) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadElectionPhoto_4 = data.getData();
                    Log.d("Pan Image Uri", uriUploadElectionPhoto_4.toString());
                    waypathUploadElectionPhoto_4 = getFilePath(this, uriUploadElectionPhoto_4);
                    Log.d("Pan Image Uri", waypathUploadElectionPhoto_4);
                    String[] name = waypathUploadElectionPhoto_4.split("/");
                    txt_DownPayment_UploadElectionPhoto_4.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_ElectionPhoto_4.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 32) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadRCBook2_4 = data.getData();
                    Log.d("Pan Image Uri", uriUploadRCBook2_4.toString());
                    waypathUploadRCBook2_4 = getFilePath(this, uriUploadRCBook2_4);
                    Log.d("Pan Image Uri", waypathUploadRCBook2_4);
                    String[] name = waypathUploadRCBook2_4.split("/");
                    txt_DownPayment_UploadRCBook2_4.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_RcBook2_4.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 33) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadElectionPhoto2_4 = data.getData();
                    Log.d("Pan Image Uri", uriUploadElectionPhoto2_4.toString());
                    waypathUploadElectionPhoto2_4 = getFilePath(this, uriUploadElectionPhoto2_4);
                    Log.d("Pan Image Uri", waypathUploadElectionPhoto2_4);
                    String[] name = waypathUploadElectionPhoto2_4.split("/");
                    txt_DownPayment_UploadElectionPhoto2_4.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownPayment_ElectionPhoto2_4.setImageURI(selectedImageUri);
                }
            }
        }

    }


    public String getFilePath(Context context, Uri uri) {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/all_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}