package com.example.kumarGroup.BookingDeliveryUpload.Loan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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

import com.bumptech.glide.Glide;
import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.FinanceFormModel;
import com.example.kumarGroup.LoanDataDisplayModel;
import com.example.kumarGroup.LoanSubmitBookingModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanFillFormActivity extends AppCompatActivity {

    //Argument Details
    EditText edt_ADBooking_BookingNo_loan,edt_ADBooking_BookingType_loan,edt_ADBooking_BookingDate_loan,
            edt_ADBooking_BookingLoginName_loan;

    TextView txtLoanFillBookingType_loan;
    TextView txtLoanPassBookBook_Loan;
    TextView txtLoanChequeBook_Loan;

    //Customer detail
    EditText edt_CD_Fname_loan,edt_CD_LastName_loan,edt_CD_Surname_loan,edt_CD_MobileNo_loan,
            edt_CD_WhatsappNo_loan,
            edt_CD_ReferenceName_loan,edt_CD_ReferenceNo_loan,edt_CD_State_loan,edt_CD_City_loan,
            edt_CD_District_loan,
            edt_CD_Taluko_loan,edt_CD_Village_loan,edt_CD_EmployeName_loan,edt_CD_PassBook_loan,
            edt_CD_ChequeBook_loan,edt_CD_PaymentOption_loan;


    ImageView img_CD_Booking_loan,img_CD_AdharCard_loan,img_CD_ElectionCard_loan,img_CD_PassportSize_loan;

    ImageView img_CD_AdharCard_loan2,img_CD_ElectionCard_loan2,img_CD_PassportSize_loan2;

    TextView txt_CD_UploadBookingPhoto_loan,txt_CD_AdharCard_loan,txt_CD_ElectionCard_loan,txt_CD_PassportSize_loan;

    TextView txt_CD_AdharCard_loan2,txt_CD_ElectionCard_loan2,txt_CD_PassportSize_loan2;


    //Product detail
    EditText edt_PD_PurchaseName_loan,edt_PD_ModelName_loan,edt_PD_Description_loan;

    //Price detail
    EditText edt_PriceD_price_loan,edt_PriceD_priceInWord_loan,edt_PriceD_GST_loan;

    //RTO Detail
    EditText edt_RTO_RtoMain_loan,edt_RTO_RtoTax_loan,edt_RTO_RtoPassing_loan,
            edt_RTO_Insurance_loan,edt_RTO_AgentFees_loan,edt_RTO_NumberPlat_loan,
            edt_RTO_LoanCharge_loan;

    TextView txtRTOTax,txtRTOPassing,txtInsurance,txtAgentFees,txtNumberPlat,txtLoanCharge;

    //Down Payment
    EditText  edt_DownP_BookingAmount_loan,edt_DownP_CashAmount_loan,
            edt_DownP_BankOption_loan,edt_DownP_ChequeDate_loan,edt_DownP_ChequeAmount_loan,
            edt_DownP_NEFT_RTGSAmount_loan,edt_DownP_NEFT_RTGS_date_loan,
            edt_DownP_SelectMake_loan,edt_DownP_ModelVehicle_loan,edt_DownP_ManufactureYear_loan,
            edt_DownP_oldAmount_loan,edt_DownP_PaperExchange_loan,edt_DownP_oldTractorAmount_loan,
            edt_DownP_NOC_loan;

    ImageView img_DownP_Cheque_loan,img_DownP_NEFT_RTGS_loan,img_DownP_NocPhoto_loan,
            img_DownP_OldVehicle_loan,img_DownP_RcBook_loan,img_DownP_ElectionPhoto_loan;

    ImageView img_DownP_RcBook_loan2,img_DownP_ElectionPhoto_loan2;

    TextView txt_DownP_UploadRCBook_loan2,txt_DownP_UploadElectionPhoto_loan2;

    TextView txt_DownP_UploadCheque_loan,txt_DownP_UploadNEFT_RTGS_loan,txt_DownP_UploadNocPhoto_loan,
            txt_DownP_UploadOldVehicle_loan,txt_DownP_UploadRCBook_loan,txt_DownP_UploadElectionPhoto_loan;

    //Down payment label
    TextView txtDPCashAmount_loan,txtDPBankOption_loan,txtDPChequeDate_loan,txtDPChequeAmount_loan,
            txtDPNEFT_RTGSdate_loan,
            txtDPNEFT_RTGSAmount_loan,txtDPMake_loan,txtDPManufectureYear_loan,
            txtDPOldAmount_loan,txtDPModelName_loan,
            txtDPPaperExpense_loan,txtDPOldTractorAmount_loan,txtDPNoc_loan;

    LinearLayout lin_dp_cheque_loan,lin_dp_NEFT_RTGS_loan,lin_dp_NocPhoto_loan,
            lin_dp_OldVehicle_loan,lin_dp_Rcbook_loan,
            lin_dp_Election_loan;

    LinearLayout lin_dp_Rcbook_loan2,lin_dp_Election_loan2;

    //Consumer Details
    EditText edt_CS_Hood_loan,edt_CS_TopLink_loan,edt_CS_DrawBar_loan,edt_CS_ToolKit_loan,edt_CS_Bumper_loan,
            edt_CS_Hitch_loan,edt_CS_Description_loan,
            edt_CS_ConsumerSkim_loan;

    TextView txt_cs_Hood,txt_cs_TopLink,txt_cs_Drawbar,txt_cs_ToolKit,txt_cs_Bumper,
            txt_cs_Hitch,txt_cs_Description;


    LinearLayout lin_consumerSkim_CS;

    //Loan Details
    EditText edt_loanDetail_REF,edt_loanDetail_LoanAmount,edt_loanDetail_LoanSancAmont,edt_loanDetail_LoanCharge,
            edt_loanDetail_LandDetail,edt_loanDetail_CibilScore,edt_loanDetail_FiDate,edt_loanDetail_SanctionDate;

    Spinner spn_loanDetail_FinanceForm,spn_loanDetail_Stage;

    ImageView img_LoanDetail_BankpassBook,img_LoanDetail_Cheque,img_LoanDetail_SarpanchID,
            img_LoanDetail_SignatureVerifi;

    ImageView img_LoanDetail_BankpassBook2;

    TextView txt_LoanDetail_BankPassBook2;

    TextView txt_LoanDetail_BankPassBook,txt_LoanDetail_Cheque,txt_LoanDetail_SarpanchId,
            txt_LoanDetail_SignatureVeri;

    String WayPath_LoanDetail_BankPassBook,WayPath_LoanDetail_Cheque,WayPath_LoanDetail_SarpanchId,
            WayPath_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook,Uri_LoanDetail_Cheque,Uri_LoanDetail_SarpanchId,
            Uri_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook2;

    String WayPath_LoanDetail_BankPassBook2;

    Button btn_LoanPhase_Submit;


    LinearLayout lin_LoanDetail_Loan,lin_cashLoan_loan;

    //Cash Loan
    EditText edt_CashDetail_Date,edt_CashDetail_Amount,edt_CashDetail_Description;

    String Stage;
    String[] Satge_data1 = {"Pending","Fi Done","Login Done","sanction","Reject"};

   List<String> Satge_data = Arrays.asList(new String[]{"Pending", "Fi Done", "Login Done", "sanction", "Reject"});



    List<String> FinanceName = new ArrayList<>();
    List<String> FInanceID = new ArrayList<>();

    String finance_name,finance_id;

    String idBookingUpload;

    String emp_id;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    Calendar mcurrentdate;
    int day, month, year;

    int day1, month1, year1;


    Uri uri_CD_UploadBookingPhoto_loan,uri_CD_AdharCard_loan,uri_CD_ElectionCard_loan,uri_CD_PassportSize_loan;

    Uri uri_CD_AdharCard_loan2,uri_CD_ElectionCard_loan2,uri_CD_PassportSize_loan2;

    String waypath_CD_UploadBookingPhoto_loan,waypath_CD_AdharCard_loan,waypath_CD_ElectionCard_loan,
            waypath_CD_PassportSize_loan;

   String   waypath_CD_AdharCard_loan2,waypath_CD_ElectionCard_loan2, waypath_CD_PassportSize_loan2;

    Uri uriUploadDPCheque_loan, uriUploadDPNEFT_RTGS_loan, uriUploadDPNocPhoto_loan,
            uriUploadDPOldVehicle_loan, uriUploadDPRCBook_loan,
            uriUploadDPElectionPhoto_loan;

    Uri uriUploadDPRCBook_loan2, uriUploadDPElectionPhoto_loan2;

    String waypathUploadDPCheque_loan, waypathUploadDPNEFT_RTGS_loan, waypathUploadDPNocPhoto_loan,
            waypathUploadDPOldVehicle_loan, waypathUploadDPRCBook_loan, waypathUploadDPElectionPhoto_loan;

    String waypathUploadDPRCBook_loan2, waypathUploadDPElectionPhoto_loan2;

    String id_item;

    int id_pos;

    TextView txt_loanDetail_FiDate,txt_loanDetail_SanctionDate;

   public  int x;
   public  int y;
    int sub;

    String mydata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_fill_form);

        getSupportActionBar().setTitle("Loan Form");

        /** ****************************************** Memory Allocation **************************************************  */

        //Agreement Details
        edt_ADBooking_BookingNo_loan = findViewById(R.id.edt_ADBooking_BookingNo_loan);
        edt_ADBooking_BookingType_loan = findViewById(R.id.edt_ADBooking_BookingType_loan);
        edt_ADBooking_BookingDate_loan = findViewById(R.id.edt_ADBooking_BookingDate_loan);
        edt_ADBooking_BookingLoginName_loan = findViewById(R.id.edt_ADBooking_BookingLoginName_loan);

        txtLoanFillBookingType_loan = findViewById(R.id.txtLoanFillBookingType_loan);
        txtLoanPassBookBook_Loan = findViewById(R.id.txtLoanPassBookBook_Loan);
        txtLoanChequeBook_Loan = findViewById(R.id.txtLoanChequeBook_Loan);

        //Customer Detail
        edt_CD_Fname_loan   = findViewById(R.id.edt_CD_Fname_loan);
        edt_CD_LastName_loan = findViewById(R.id.edt_CD_LastName_loan);
        edt_CD_Surname_loan = findViewById(R.id.edt_CD_Surname_loan);
        edt_CD_MobileNo_loan = findViewById(R.id.edt_CD_MobileNo_loan);
        edt_CD_WhatsappNo_loan = findViewById(R.id.edt_CD_WhatsappNo_loan);
        edt_CD_ReferenceName_loan = findViewById(R.id.edt_CD_ReferenceName_loan);
        edt_CD_ReferenceNo_loan = findViewById(R.id.edt_CD_ReferenceNo_loan);
        edt_CD_State_loan = findViewById(R.id.edt_CD_State_loan);
        edt_CD_City_loan = findViewById(R.id.edt_CD_City_loan);
        edt_CD_District_loan = findViewById(R.id.edt_CD_District_loan);
        edt_CD_Taluko_loan = findViewById(R.id.edt_CD_Taluko_loan);
        edt_CD_Village_loan = findViewById(R.id.edt_CD_Village_loan);
        edt_CD_EmployeName_loan = findViewById(R.id.edt_CD_EmployeName_loan);
        edt_CD_PassBook_loan = findViewById(R.id.edt_CD_PassBook_loan);
        edt_CD_ChequeBook_loan = findViewById(R.id.edt_CD_ChequeBook_loan);
        edt_CD_PaymentOption_loan = findViewById(R.id.edt_CD_PaymentOption_loan);

        txt_CD_UploadBookingPhoto_loan =findViewById(R.id.txt_CD_UploadBookingPhoto_loan);
        txt_CD_AdharCard_loan =findViewById(R.id.txt_CD_AdharCard_loan);
        txt_CD_ElectionCard_loan =findViewById(R.id.txt_CD_ElectionCard_loan);
      /*  txt_CD_BankPassBook =findViewById(R.id.txt_CD_BankPassBook);
        txt_CD_Cheque =findViewById(R.id.txt_CD_Cheque);*/
        txt_CD_PassportSize_loan =findViewById(R.id.txt_CD_PassportSize_loan);

        txt_CD_AdharCard_loan2 =findViewById(R.id.txt_CD_AdharCard_loan2);
        txt_CD_ElectionCard_loan2 =findViewById(R.id.txt_CD_ElectionCard_loan2);
        txt_CD_PassportSize_loan2 =findViewById(R.id.txt_CD_PassportSize_loan2);

        img_CD_Booking_loan =findViewById(R.id.img_CD_Booking_loan);
        img_CD_AdharCard_loan =findViewById(R.id.img_CD_AdharCard_loan);
        img_CD_ElectionCard_loan =findViewById(R.id.img_CD_ElectionCard_loan);
       /* img_CD_BankPassBook=findViewById(R.id.img_CD_BankPassBook);
        img_CD_Cheque=findViewById(R.id.img_CD_Cheque);*/
        img_CD_PassportSize_loan =findViewById(R.id.img_CD_PassportSize_loan);

        img_CD_AdharCard_loan2 =findViewById(R.id.img_CD_AdharCard_loan2);
        img_CD_ElectionCard_loan2 =findViewById(R.id.img_CD_ElectionCard_loan2);
        img_CD_PassportSize_loan2 =findViewById(R.id.img_CD_PassportSize_loan2);

        //Product Details
        edt_PD_PurchaseName_loan =findViewById(R.id.edt_PD_PurchaseName_loan);
        edt_PD_ModelName_loan =findViewById(R.id.edt_PD_ModelName_loan);
        edt_PD_Description_loan =findViewById(R.id.edt_PD_Description_loan);

        //Price Details
        edt_PriceD_price_loan =findViewById(R.id.edt_PriceD_price_loan);
        edt_PriceD_priceInWord_loan =findViewById(R.id.edt_PriceD_priceInWord_loan);
        edt_PriceD_GST_loan =findViewById(R.id.edt_PriceD_GST_loan);

        //RTO Details
        edt_RTO_RtoMain_loan =findViewById(R.id.edt_RTO_RtoMain_loan);
        edt_RTO_RtoTax_loan =findViewById(R.id.edt_RTO_RtoTax_loan);
        edt_RTO_RtoPassing_loan =findViewById(R.id.edt_RTO_RtoPassing_loan);
        edt_RTO_Insurance_loan =findViewById(R.id.edt_RTO_Insurance_loan);
        edt_RTO_AgentFees_loan =findViewById(R.id.edt_RTO_AgentFees_loan);
        edt_RTO_NumberPlat_loan =findViewById(R.id.edt_RTO_NumberPlat_loan);
        edt_RTO_LoanCharge_loan =findViewById(R.id.edt_RTO_LoanCharge_loan);


        txtRTOTax= findViewById(R.id.txtRTOTax);
        txtRTOPassing= findViewById(R.id.txtRTOPassing);
        txtInsurance= findViewById(R.id.txtInsurance);
        txtAgentFees= findViewById(R.id.txtAgentFees);
        txtNumberPlat= findViewById(R.id.txtNumberPlat);
        txtLoanCharge= findViewById(R.id.txtLoanCharge);

        //Down Payment
        edt_DownP_BookingAmount_loan =findViewById(R.id.edt_DownP_BookingAmount_loan);
        edt_DownP_CashAmount_loan =findViewById(R.id.edt_DownP_CashAmount_loan);
        edt_DownP_BankOption_loan =findViewById(R.id.edt_DownP_BankOption_loan);
        edt_DownP_ChequeDate_loan =findViewById(R.id.edt_DownP_ChequeDate_loan);
        edt_DownP_ChequeAmount_loan =findViewById(R.id.edt_DownP_ChequeAmount_loan);
        edt_DownP_NEFT_RTGS_date_loan =findViewById(R.id.edt_DownP_NEFT_RTGS_date_loan);
        edt_DownP_NEFT_RTGSAmount_loan =findViewById(R.id.edt_DownP_NEFT_RTGSAmount_loan);
        edt_DownP_SelectMake_loan =findViewById(R.id.edt_DownP_SelectMake_loan);
        edt_DownP_ModelVehicle_loan =findViewById(R.id.edt_DownP_ModelVehicle_loan);
        edt_DownP_oldAmount_loan =findViewById(R.id.edt_DownP_oldAmount_loan);
        edt_DownP_ManufactureYear_loan =findViewById(R.id.edt_DownP_ManufactureYear_loan);
        edt_DownP_PaperExchange_loan =findViewById(R.id.edt_DownP_PaperExchange_loan);
        edt_DownP_oldTractorAmount_loan =findViewById(R.id.edt_DownP_oldTractorAmount_loan);
        edt_DownP_NOC_loan =findViewById(R.id.edt_DownP_NOC_loan);


        img_DownP_Cheque_loan = findViewById(R.id.img_DownP_Cheque_loan);
        img_DownP_NEFT_RTGS_loan = findViewById(R.id.img_DownP_NEFT_RTGS_loan);
        img_DownP_NocPhoto_loan = findViewById(R.id.img_DownP_NocPhoto_loan);
        img_DownP_OldVehicle_loan = findViewById(R.id.img_DownP_OldVehicle_loan);
        img_DownP_RcBook_loan = findViewById(R.id.img_DownP_RcBook_loan);
        img_DownP_ElectionPhoto_loan = findViewById(R.id.img_DownP_ElectionPhoto_loan);

        img_DownP_RcBook_loan2 = findViewById(R.id.img_DownP_RcBook_loan2);
        img_DownP_ElectionPhoto_loan2 = findViewById(R.id.img_DownP_ElectionPhoto_loan2);

        txt_DownP_UploadCheque_loan =findViewById(R.id.txt_DownP_UploadCheque_loan);
        txt_DownP_UploadNEFT_RTGS_loan =findViewById(R.id.txt_DownP_UploadNEFT_RTGS_loan);
        txt_DownP_UploadNocPhoto_loan =findViewById(R.id.txt_DownP_UploadNocPhoto_loan);
        txt_DownP_UploadOldVehicle_loan =findViewById(R.id.txt_DownP_UploadOldVehicle_loan);
        txt_DownP_UploadRCBook_loan =findViewById(R.id.txt_DownP_UploadRCBook_loan);
        txt_DownP_UploadElectionPhoto_loan =findViewById(R.id.txt_DownP_UploadElectionPhoto_loan);

        txt_DownP_UploadRCBook_loan2 =findViewById(R.id.txt_DownP_UploadRCBook_loan2);
        txt_DownP_UploadElectionPhoto_loan2 =findViewById(R.id.txt_DownP_UploadElectionPhoto_loan2);

        //Label Textview
        txtDPCashAmount_loan =findViewById(R.id.txtDPCashAmount_loan);
        txtDPBankOption_loan =findViewById(R.id.txtDPBankOption_loan);
        txtDPChequeDate_loan =findViewById(R.id.txtDPChequeDate_loan);
        txtDPChequeAmount_loan =findViewById(R.id.txtDPChequeAmount_loan);
        txtDPNEFT_RTGSdate_loan =findViewById(R.id.txtDPNEFT_RTGSdate_loan);
        txtDPNEFT_RTGSAmount_loan =findViewById(R.id.txtDPNEFT_RTGSAmount_loan);
        txtDPMake_loan =findViewById(R.id.txtDPMake_loan);
        txtDPManufectureYear_loan=findViewById(R.id.txtDPManufectureYear_loan);
        txtDPOldAmount_loan =findViewById(R.id.txtDPOldAmount_loan);
        txtDPModelName_loan =findViewById(R.id.txtDPModelName_loan);
        txtDPPaperExpense_loan =findViewById(R.id.txtDPPaperExpense_loan);
        txtDPOldTractorAmount_loan =findViewById(R.id.txtDPOldTractorAmount_loan);
        txtDPNoc_loan =findViewById(R.id.txtDPNoc_loan);


        lin_dp_cheque_loan =findViewById(R.id.lin_dp_cheque_loan);
        lin_dp_NEFT_RTGS_loan =findViewById(R.id.lin_dp_NEFT_RTGS_loan);
        lin_dp_NocPhoto_loan =findViewById(R.id.lin_dp_NocPhoto_loan);
        lin_dp_OldVehicle_loan =findViewById(R.id.lin_dp_OldVehicle_loan);
        lin_dp_Rcbook_loan =findViewById(R.id.lin_dp_Rcbook_loan);
        lin_dp_Election_loan =findViewById(R.id.lin_dp_Election_loan);

        lin_dp_Rcbook_loan2 =findViewById(R.id.lin_dp_Rcbook_loan2);
        lin_dp_Election_loan2 =findViewById(R.id.lin_dp_Election_loan2);

        lin_consumerSkim_CS =findViewById(R.id.lin_consumerSkim_CS);


        //Consumer Skim
        edt_CS_Hood_loan =findViewById(R.id.edt_CS_Hood_loan);
        edt_CS_TopLink_loan =findViewById(R.id.edt_CS_TopLink_loan);
        edt_CS_DrawBar_loan=findViewById(R.id.edt_CS_DrawBar_loan);
        edt_CS_ToolKit_loan=findViewById(R.id.edt_CS_ToolKit_loan);
        edt_CS_Bumper_loan=findViewById(R.id.edt_CS_Bumper_loan);
        edt_CS_Hitch_loan=findViewById(R.id.edt_CS_Hitch_loan);
        edt_CS_Description_loan=findViewById(R.id.edt_CS_Description_loan);
        edt_CS_ConsumerSkim_loan=findViewById(R.id.edt_CS_ConsumerSkim_loan);

        txt_cs_Hood=findViewById(R.id.txt_cs_Hood);
        txt_cs_TopLink=findViewById(R.id.txt_cs_TopLink);
        txt_cs_Drawbar=findViewById(R.id.txt_cs_Drawbar);
        txt_cs_ToolKit=findViewById(R.id.txt_cs_ToolKit);
        txt_cs_Bumper=findViewById(R.id.txt_cs_Bumper);
        txt_cs_Hitch=findViewById(R.id.txt_cs_Hitch);
        txt_cs_Description=findViewById(R.id.txt_cs_Description);

        //Loan Detail Form

        edt_loanDetail_REF= findViewById(R.id.edt_loanDetail_REF);
        edt_loanDetail_LoanAmount= findViewById(R.id.edt_loanDetail_LoanAmount);
        edt_loanDetail_LoanSancAmont= findViewById(R.id.edt_loanDetail_LoanSancAmont);
        edt_loanDetail_LoanCharge= findViewById(R.id.edt_loanDetail_LoanCharge);
        edt_loanDetail_LandDetail= findViewById(R.id.edt_loanDetail_LandDetail);
        edt_loanDetail_CibilScore= findViewById(R.id.edt_loanDetail_CibilScore);
        edt_loanDetail_FiDate= findViewById(R.id.edt_loanDetail_FiDate);
        edt_loanDetail_SanctionDate= findViewById(R.id.edt_loanDetail_SanctionDate);
        txt_loanDetail_FiDate= findViewById(R.id.txt_loanDetail_FiDate);
        txt_loanDetail_SanctionDate= findViewById(R.id.txt_loanDetail_SanctionDate);


        spn_loanDetail_FinanceForm= findViewById(R.id.spn_loanDetail_FinanceForm);
        spn_loanDetail_Stage= findViewById(R.id.spn_loanDetail_Stage);


        img_LoanDetail_BankpassBook2 = findViewById(R.id.img_LoanDetail_BankpassBook2);

        img_LoanDetail_BankpassBook= findViewById(R.id.img_LoanDetail_BankpassBook);
        img_LoanDetail_Cheque= findViewById(R.id.img_LoanDetail_Cheque);
        img_LoanDetail_SarpanchID= findViewById(R.id.img_LoanDetail_SarpanchID);
        img_LoanDetail_SignatureVerifi= findViewById(R.id.img_LoanDetail_SignatureVerifi);


        txt_LoanDetail_BankPassBook2= findViewById(R.id.txt_LoanDetail_BankPassBook2);

        txt_LoanDetail_BankPassBook= findViewById(R.id.txt_LoanDetail_BankPassBook);
        txt_LoanDetail_Cheque= findViewById(R.id.txt_LoanDetail_Cheque);
        txt_LoanDetail_SarpanchId= findViewById(R.id.txt_LoanDetail_SarpanchId);
        txt_LoanDetail_SignatureVeri= findViewById(R.id.txt_LoanDetail_SignatureVeri);


        lin_LoanDetail_Loan= findViewById(R.id.lin_LoanDetail_Loan);
        lin_cashLoan_loan= findViewById(R.id.lin_cashLoan_loan);

        edt_CashDetail_Date= findViewById(R.id.edt_CashDetail_Date);
        edt_CashDetail_Amount= findViewById(R.id.edt_CashDetail_Amount);
        edt_CashDetail_Description= findViewById(R.id.edt_CashDetail_Description);

        btn_LoanPhase_Submit=findViewById(R.id.btn_LoanPhase_Submit);


        /** *************************************************************************************************************** */

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");


        edt_loanDetail_LandDetail.setVisibility(View.GONE);
        edt_loanDetail_CibilScore.setVisibility(View.GONE);

        idBookingUpload = getIntent().getStringExtra("idBookingUpload");

        id_item = getIntent().getStringExtra("position");

        id_pos= Integer.parseInt(id_item);

       //  Toast.makeText(LoanFillFormActivity.this, ""+id_pos+" "+idBookingUpload, Toast.LENGTH_SHORT).show();


        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);


        progressDialog= new ProgressDialog(LoanFillFormActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getLoanDataDisplay(emp).enqueue(new Callback<LoanDataDisplayModel>() {
            @Override
            public void onResponse(@NotNull Call<LoanDataDisplayModel> call,
                                   @NotNull Response<LoanDataDisplayModel> response) {
                progressDialog.dismiss();

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getB_photo())
                        .into(img_CD_Booking_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getAdhar_photo())
                        .into(img_CD_AdharCard_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getEcard_photo())
                        .into(img_CD_ElectionCard_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getP_photo())
                        .into(img_CD_PassportSize_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getCheck1_photo())
                        .into(img_DownP_Cheque_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getNeft_rtgs_photo())
                        .into(img_DownP_NEFT_RTGS_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getNoc_photo())
                        .into(img_DownP_NocPhoto_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getOld_vehicle_photo())
                        .into(img_DownP_OldVehicle_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getRecbook_photo())
                        .into(img_DownP_RcBook_loan);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getEc_photo())
                        .into(img_DownP_ElectionPhoto_loan);

                //---------------------------------------------------------------------------------

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getAdhar_back())
                        .into(img_CD_AdharCard_loan2);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getElection_back())
                        .into(img_CD_ElectionCard_loan2);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getP_photo_back())
                        .into(img_CD_PassportSize_loan2);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getRcbook_back())
                        .into(img_DownP_RcBook_loan2);

                Glide.with(LoanFillFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getElec_back())
                        .into(img_DownP_ElectionPhoto_loan2);


                //----------------------------------------------------------------


                emp_id = response.body().getData().get(id_pos).getEmp_id();

                if(response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                        response.body().getData().get(id_pos).getProduct_name().equals("Spar part"))
                {
                    edt_ADBooking_BookingType_loan.setVisibility(View.GONE);
                    edt_CD_PassBook_loan.setVisibility(View.GONE);
                    edt_CD_ChequeBook_loan.setVisibility(View.GONE);
                    txtLoanFillBookingType_loan.setVisibility(View.GONE);
                    txtLoanPassBookBook_Loan.setVisibility(View.GONE);
                    txtLoanChequeBook_Loan.setVisibility(View.GONE);


                    txt_CD_UploadBookingPhoto_loan.setVisibility(View.GONE);
                    txt_CD_AdharCard_loan.setVisibility(View.GONE);
                    txt_CD_ElectionCard_loan.setVisibility(View.GONE);
                    txt_CD_PassportSize_loan.setVisibility(View.GONE);
                    txt_CD_AdharCard_loan2.setVisibility(View.GONE);
                    txt_CD_ElectionCard_loan2.setVisibility(View.GONE);
                    txt_CD_PassportSize_loan2.setVisibility(View.GONE);

                    img_CD_Booking_loan.setVisibility(View.GONE);
                    img_CD_AdharCard_loan.setVisibility(View.GONE);
                    img_CD_ElectionCard_loan.setVisibility(View.GONE);
                    img_CD_PassportSize_loan.setVisibility(View.GONE);
                    img_CD_AdharCard_loan2.setVisibility(View.GONE);
                    img_CD_ElectionCard_loan2.setVisibility(View.GONE);
                    img_CD_PassportSize_loan2.setVisibility(View.GONE);

                }
                else{
                    edt_ADBooking_BookingType_loan.setVisibility(View.VISIBLE);
                    edt_CD_PassBook_loan.setVisibility(View.VISIBLE);
                    edt_CD_ChequeBook_loan.setVisibility(View.VISIBLE);
                    txtLoanFillBookingType_loan.setVisibility(View.VISIBLE);
                    txtLoanPassBookBook_Loan.setVisibility(View.VISIBLE);
                    txtLoanChequeBook_Loan.setVisibility(View.VISIBLE);


                    if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                        txt_CD_UploadBookingPhoto_loan.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_UploadBookingPhoto_loan.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                        txt_CD_AdharCard_loan.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_AdharCard_loan.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                        txt_CD_ElectionCard_loan.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_ElectionCard_loan.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                        txt_CD_PassportSize_loan.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize_loan.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                        txt_CD_AdharCard_loan2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_AdharCard_loan2.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getElection_back_check().equals("0")){
                        txt_CD_ElectionCard_loan2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_ElectionCard_loan2.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                        txt_CD_PassportSize_loan2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize_loan2.setVisibility(View.GONE);
                    }

                }



                if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                    txt_CD_PassportSize_loan.setVisibility(View.VISIBLE);
                    img_CD_PassportSize_loan.setVisibility(View.VISIBLE);

                    txt_CD_PassportSize_loan2.setVisibility(View.VISIBLE);
                    img_CD_PassportSize_loan2.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                        txt_CD_PassportSize_loan.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize_loan.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                        txt_CD_PassportSize_loan2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize_loan2.setVisibility(View.GONE);
                    }

                } else {
                    txt_CD_PassportSize_loan.setVisibility(View.GONE);
                    img_CD_PassportSize_loan.setVisibility(View.GONE);

                    txt_CD_PassportSize_loan2.setVisibility(View.GONE);
                    img_CD_PassportSize_loan2.setVisibility(View.GONE);
                }


                //------------------------------------------------------------------------------------------

                if(response.body().getData().get(id_pos).getBno_check().equals("0")){
                    edt_ADBooking_BookingNo_loan.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingNo_loan.setFocusable(false);
                    edt_ADBooking_BookingNo_loan.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getB_date_check().equals("0")){
                    edt_ADBooking_BookingDate_loan.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingDate_loan.setFocusable(false);
                    edt_ADBooking_BookingDate_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getB_type_check().equals("0")){
                    edt_ADBooking_BookingType_loan.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingType_loan.setFocusable(false);
                    edt_ADBooking_BookingType_loan.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getEmp_check().equals("0")){
                    edt_ADBooking_BookingLoginName_loan.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingLoginName_loan.setFocusable(false);
                    edt_ADBooking_BookingLoginName_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getFname_check().equals("0")){
                    edt_CD_Fname_loan.setFocusable(true);
                }
                else{
                    edt_CD_Fname_loan.setFocusable(false);
                    edt_CD_Fname_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLname_check().equals("0")){
                    edt_CD_LastName_loan.setFocusable(true);
                }
                else{
                    edt_CD_LastName_loan.setFocusable(false);
                    edt_CD_LastName_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getSname_check().equals("0")){
                    edt_CD_Surname_loan.setFocusable(true);
                }
                else{
                    edt_CD_Surname_loan.setFocusable(false);
                    edt_CD_Surname_loan.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getMobileno_check().equals("0")){
                    edt_CD_MobileNo_loan.setFocusable(true);
                }
                else{
                    edt_CD_MobileNo_loan.setFocusable(false);
                    edt_CD_MobileNo_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getWhno_check().equals("0")){
                    edt_CD_WhatsappNo_loan.setFocusable(true);
                }
                else {
                    edt_CD_WhatsappNo_loan.setFocusable(false);
                    edt_CD_WhatsappNo_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRef_name_check().equals("id_pos")){
                    edt_CD_ReferenceName_loan.setFocusable(true);
                }
                else{
                    edt_CD_ReferenceName_loan.setFocusable(false);
                    edt_CD_ReferenceName_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRef_no_check().equals("0")){
                    edt_CD_ReferenceNo_loan.setFocusable(true);
                }
                else{
                    edt_CD_ReferenceNo_loan.setFocusable(false);
                    edt_CD_ReferenceNo_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getState_check().equals("0")){
                    edt_CD_State_loan.setFocusable(true);
                }
                else{
                    edt_CD_State_loan.setFocusable(false);
                    edt_CD_State_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCity_check().equals("0")){
                    edt_CD_City_loan.setFocusable(true);
                }
                else{
                    edt_CD_City_loan.setFocusable(false);
                    edt_CD_City_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDistric_check().equals("0")){
                    edt_CD_District_loan.setFocusable(true);
                }
                else{
                    edt_CD_District_loan.setFocusable(false);
                    edt_CD_District_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getVillage_check().equals("0")){
                    edt_CD_Village_loan.setFocusable(true);
                }
                else{
                    edt_CD_Village_loan.setFocusable(false);
                    edt_CD_Village_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getEmp_check().equals("0")){
                    edt_CD_EmployeName_loan.setFocusable(true);
                }
                else{
                    edt_CD_EmployeName_loan.setFocusable(false);
                    edt_CD_EmployeName_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getTehsill_check().equals("0")){
                    edt_CD_Taluko_loan.setFocusable(true);
                }
                else{
                    edt_CD_Taluko_loan.setFocusable(false);
                    edt_CD_Taluko_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getProduct_name_check().equals("0")){
                    edt_PD_PurchaseName_loan.setFocusable(true);
                }
                else{
                    edt_PD_PurchaseName_loan.setFocusable(false);
                    edt_PD_PurchaseName_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getModel_name_check().equals("0")){
                    edt_PD_ModelName_loan.setFocusable(true);
                }
                else{
                    edt_PD_ModelName_loan.setFocusable(false);
                    edt_PD_ModelName_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getP_desc_check().equals("0")){
                    edt_PD_Description_loan.setFocusable(true);
                }
                else{
                    edt_PD_Description_loan.setFocusable(false);
                    edt_PD_Description_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDeal_price_check().equals("0")){
                    edt_PriceD_price_loan.setFocusable(true);
                }
                else{
                    edt_PriceD_price_loan.setFocusable(false);
                    edt_PriceD_price_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")){
                    edt_PriceD_priceInWord_loan.setFocusable(true);
                }
                else{
                    edt_PriceD_priceInWord_loan.setFocusable(false);
                    edt_PriceD_priceInWord_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getGst_check().equals("0")){
                    edt_PriceD_GST_loan.setFocusable(true);
                }
                else{
                    edt_PriceD_GST_loan.setFocusable(false);
                    edt_PriceD_GST_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRto_check().equals("0")){
                    edt_RTO_RtoMain_loan.setFocusable(true);
                }
                else{
                    edt_RTO_RtoMain_loan.setFocusable(false);
                    edt_RTO_RtoMain_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRto_tax_check().equals("0")){
                    edt_RTO_RtoTax_loan.setFocusable(true);
                }
                else{
                    edt_RTO_RtoTax_loan.setFocusable(false);
                    edt_RTO_RtoTax_loan.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getRto_passing_check().equals("0")){
                    edt_RTO_RtoPassing_loan.setFocusable(true);
                }
                else{
                    edt_RTO_RtoPassing_loan.setFocusable(false);
                    edt_RTO_RtoPassing_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getInsurance_check().equals("0")){
                    edt_RTO_Insurance_loan.setFocusable(true);
                }
                else{
                    edt_RTO_Insurance_loan.setFocusable(false);
                    edt_RTO_Insurance_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAgent_fee_check().equals("0")){
                    edt_RTO_AgentFees_loan.setFocusable(true);
                }
                else{
                    edt_RTO_AgentFees_loan.setFocusable(false);
                    edt_RTO_AgentFees_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNumber_plat_check().equals("0")){
                    edt_RTO_NumberPlat_loan.setFocusable(true);
                }
                else{
                    edt_RTO_NumberPlat_loan.setFocusable(false);
                    edt_RTO_NumberPlat_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLoan_charge_check().equals("0")){
                    edt_RTO_LoanCharge_loan.setFocusable(true);
                }
                else{
                    edt_RTO_LoanCharge_loan.setFocusable(false);
                    edt_RTO_LoanCharge_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getBooking_amt_check().equals("0")){
                    edt_DownP_BookingAmount_loan.setFocusable(true);
                }
                else{
                    edt_DownP_BookingAmount_loan.setFocusable(false);
                    edt_DownP_BookingAmount_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAmount_check().equals("0")){
                    edt_DownP_CashAmount_loan.setFocusable(true);
                }
                else{
                    edt_DownP_CashAmount_loan.setFocusable(false);
                    edt_DownP_CashAmount_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCheck_date_check().equals("0")){
                    edt_DownP_ChequeDate_loan.setFocusable(true);
                }
                else{
                    edt_DownP_ChequeDate_loan.setFocusable(false);
                    edt_DownP_ChequeDate_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCheck_amt_check().equals("0")){
                    edt_DownP_ChequeAmount_loan.setFocusable(true);
                }
                else{
                    edt_DownP_ChequeAmount_loan.setFocusable(false);
                    edt_DownP_ChequeAmount_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")){
                    edt_DownP_NEFT_RTGS_date_loan.setFocusable(true);
                }
                else{
                    edt_DownP_NEFT_RTGS_date_loan.setFocusable(false);
                    edt_DownP_NEFT_RTGS_date_loan.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")){
                    edt_DownP_NEFT_RTGSAmount_loan.setFocusable(true);
                }
                else{
                    edt_DownP_NEFT_RTGSAmount_loan.setFocusable(false);
                    edt_DownP_NEFT_RTGSAmount_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getMake_check().equals("0")){
                    edt_DownP_SelectMake_loan.setFocusable(true);
                }
                else{
                    edt_DownP_SelectMake_loan.setFocusable(false);
                    edt_DownP_SelectMake_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getModel_check().equals("0")){
                    edt_DownP_ModelVehicle_loan.setFocusable(true);
                }
                else{
                    edt_DownP_ModelVehicle_loan.setFocusable(false);
                    edt_DownP_ModelVehicle_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")){
                    edt_DownP_oldAmount_loan.setFocusable(true);
                }
                else{
                    edt_DownP_oldAmount_loan.setFocusable(false);
                    edt_DownP_oldAmount_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getM_year_check().equals("0")){
                    edt_DownP_ManufactureYear_loan.setFocusable(true);
                }
                else{
                    edt_DownP_ManufactureYear_loan.setFocusable(false);
                    edt_DownP_ManufactureYear_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getPaper_expence_check().equals("0")){
                    edt_DownP_PaperExchange_loan.setFocusable(true);
                }
                else{
                    edt_DownP_PaperExchange_loan.setFocusable(false);
                    edt_DownP_PaperExchange_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")){
                    edt_DownP_oldTractorAmount_loan.setFocusable(true);
                }
                else{
                    edt_DownP_oldTractorAmount_loan.setFocusable(false);
                    edt_DownP_oldTractorAmount_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNoc_check().equals("0")){
                    edt_DownP_NOC_loan.setFocusable(true);
                }
                else{
                    edt_DownP_NOC_loan.setFocusable(false);
                    edt_DownP_NOC_loan.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getHood_check().equals("0")){
                    edt_CS_Hood_loan.setFocusable(true);
                }
                else{
                    edt_CS_Hood_loan.setFocusable(false);
                    edt_CS_Hood_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getToplink_check().equals("0")){
                    edt_CS_TopLink_loan.setFocusable(true);
                }
                else{
                    edt_CS_TopLink_loan.setFocusable(false);
                    edt_CS_TopLink_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDrowbar_check().equals("0")){
                    edt_CS_DrawBar_loan.setFocusable(true);
                }
                else{
                    edt_CS_DrawBar_loan.setFocusable(false);
                    edt_CS_DrawBar_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getToolkit_check().equals("0")){
                    edt_CS_ToolKit_loan.setFocusable(true);
                }
                else{
                    edt_CS_ToolKit_loan.setFocusable(false);
                    edt_CS_ToolKit_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getBumper_check().equals("0")){
                    edt_CS_Bumper_loan.setFocusable(true);
                }
                else{
                    edt_CS_Bumper_loan.setFocusable(false);
                    edt_CS_Bumper_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getHitch_check().equals("0")){
                    edt_CS_Hitch_loan.setFocusable(true);
                }
                else{
                    edt_CS_Hitch_loan.setFocusable(false);
                    edt_CS_Hitch_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDescription_check().equals("0")){
                    edt_CS_Description_loan.setFocusable(true);
                }
                else{
                    edt_CS_Description_loan.setFocusable(false);
                    edt_CS_Description_loan.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getB_p_photo_check().equals("0")){
                    edt_CD_PassBook_loan.setFocusable(true);
                }
                else{
                    edt_CD_PassBook_loan.setFocusable(false);
                    edt_CD_PassBook_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCheck_neft_rtgs_check().equals("0")){
                    edt_DownP_BankOption_loan.setFocusable(true);
                }
                else {
                    edt_DownP_BankOption_loan.setFocusable(false);
                    edt_DownP_BankOption_loan.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getCheck_photo_check().equals("0")){
                    edt_CD_ChequeBook_loan .setFocusable(true);
                }
                else{
                    edt_CD_ChequeBook_loan.setFocusable(false);
                    edt_CD_ChequeBook_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getSkim_check().equals("0")){
                    edt_CS_ConsumerSkim_loan.setFocusable(true);
                }else{
                    edt_CS_ConsumerSkim_loan.setFocusable(false);
                    edt_CS_ConsumerSkim_loan.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAtype_check().equals("0")){
                    edt_CD_PaymentOption_loan.setFocusable(true);
                }else{
                    edt_CD_PaymentOption_loan.setFocusable(false);
                    edt_CD_PaymentOption_loan.setTextColor(Color.parseColor("#43a047"));
                }


                /** ************************************************************************************* */

                /*if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                    txt_CD_UploadBookingPhoto_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_UploadBookingPhoto_loan.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                    txt_CD_AdharCard_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_AdharCard_loan.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                    txt_CD_ElectionCard_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_ElectionCard_loan.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                    txt_CD_PassportSize_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_PassportSize_loan.setVisibility(View.GONE);
                }*/

                if(response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")){
                    txt_DownP_UploadCheque_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadCheque_loan.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")){
                    txt_DownP_UploadNEFT_RTGS_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadNEFT_RTGS_loan.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getNoc_photo_check().equals("0")){
                    txt_DownP_UploadNocPhoto_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadNocPhoto_loan.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")){
                    txt_DownP_UploadOldVehicle_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadOldVehicle_loan.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")){
                    txt_DownP_UploadRCBook_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadRCBook_loan.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getEc_photo_check().equals("0")){
                    txt_DownP_UploadElectionPhoto_loan.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadElectionPhoto_loan.setVisibility(View.GONE);
                }

                //**********************************************************
               /* if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                    txt_CD_AdharCard_loan2.setVisibility(View.VISIBLE);

                }else{
                    txt_CD_AdharCard_loan2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getElection_back_check ().equals("0")){
                    txt_CD_ElectionCard_loan2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_ElectionCard_loan2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                    txt_CD_PassportSize_loan2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_PassportSize_loan2.setVisibility(View.GONE);
                }*/


                if(response.body().getData().get(id_pos).getRcbook_back_check().equals("0")){
                    txt_DownP_UploadRCBook_loan2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadRCBook_loan2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getElec_back_check().equals("0")){
                    txt_DownP_UploadElectionPhoto_loan2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadElectionPhoto_loan2.setVisibility(View.GONE);
                }


                /** ********************************************************************************** */

                /*if(response.body().getData().get(id_pos).getAtype().equals("Loan")){
                    lin_cashLoan_loan.setVisibility(View.GONE);
                    lin_LoanDetail_Loan.setVisibility(View.VISIBLE);
                }
                else{
                    lin_cashLoan_loan.setVisibility(View.VISIBLE);
                    lin_LoanDetail_Loan.setVisibility(View.GONE);
                }*/

                if(response.body().getData().get(id_pos).getSkim().equals("No")){
                    lin_consumerSkim_CS.setVisibility(View.GONE);
                }
                else{
                    lin_consumerSkim_CS.setVisibility(View.VISIBLE);
                }


                /*********************************************************************************************/

                String data =  response.body().getData().get(id_pos).getBooking_amt();//"cash,bank"
                //  Toast.makeText(BookingPhaseOneActivity.this, ""+data, Toast.LENGTH_SHORT).show();
                String[] res = data.split(",");


                txtDPCashAmount_loan.setVisibility(View.GONE);
                edt_DownP_CashAmount_loan.setVisibility(View.GONE);

                edt_DownP_BankOption_loan.setVisibility(View.GONE);
                edt_DownP_ChequeDate_loan.setVisibility(View.GONE);
                edt_DownP_ChequeAmount_loan.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGS_date_loan.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGSAmount_loan.setVisibility(View.GONE);
                edt_DownP_SelectMake_loan.setVisibility(View.GONE);
                edt_DownP_ModelVehicle_loan.setVisibility(View.GONE);
                edt_DownP_oldAmount_loan.setVisibility(View.GONE);
                edt_DownP_ManufactureYear_loan.setVisibility(View.GONE);
                edt_DownP_PaperExchange_loan.setVisibility(View.GONE);
                edt_DownP_oldTractorAmount_loan.setVisibility(View.GONE);
                edt_DownP_NOC_loan.setVisibility(View.GONE);

                lin_dp_cheque_loan.setVisibility(View.GONE);
                lin_dp_NEFT_RTGS_loan.setVisibility(View.GONE);
                lin_dp_NocPhoto_loan.setVisibility(View.GONE);
                lin_dp_OldVehicle_loan.setVisibility(View.GONE);
                lin_dp_Rcbook_loan.setVisibility(View.GONE);
                lin_dp_Election_loan.setVisibility(View.GONE);


                lin_dp_Rcbook_loan2.setVisibility(View.GONE);
                lin_dp_Election_loan2.setVisibility(View.GONE);

                txtDPBankOption_loan.setVisibility(View.GONE);
                txtDPChequeDate_loan.setVisibility(View.GONE);
                txtDPChequeAmount_loan.setVisibility(View.GONE);
                txtDPNEFT_RTGSdate_loan.setVisibility(View.GONE);
                txtDPNEFT_RTGSAmount_loan.setVisibility(View.GONE);
                txtDPMake_loan.setVisibility(View.GONE);
                txtDPManufectureYear_loan.setVisibility(View.GONE);
                txtDPOldAmount_loan.setVisibility(View.GONE);
                txtDPModelName_loan.setVisibility(View.GONE);
                txtDPPaperExpense_loan.setVisibility(View.GONE);
                txtDPOldTractorAmount_loan.setVisibility(View.GONE);
                txtDPNoc_loan.setVisibility(View.GONE);


                for (int i = 0; i < res.length; i++) {
                    mydata = res[i];
                    // Toast.makeText(BookingPhaseOneActivity.this, "yes" + mydata, Toast.LENGTH_SHORT).show();

                    String uu = mydata.trim();
                    // Log.e("TAG", "onResponse: " + uu);
                    //   Log.d("TAG", "onResponse: "+mydata);

                    if (uu.equals("Cash")) {
                        txtDPCashAmount_loan.setVisibility(View.VISIBLE);
                        edt_DownP_CashAmount_loan.setVisibility(View.VISIBLE);

                        // Log.e("TAG", "onResponse:casting ");
                        // Toast.makeText(BookingPhaseOneActivity.this, "casting", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Old Vehicle")) {
                        // Log.e("TAG", "onResponse:casting45 ");
                        txtDPMake_loan.setVisibility(View.VISIBLE);
                        edt_DownP_SelectMake_loan.setVisibility(View.VISIBLE);
                        txtDPModelName_loan.setVisibility(View.VISIBLE);
                        edt_DownP_ModelVehicle_loan.setVisibility(View.VISIBLE);
                        txtDPManufectureYear_loan.setVisibility(View.VISIBLE);
                        edt_DownP_ManufactureYear_loan.setVisibility(View.VISIBLE);
                        txtDPOldAmount_loan.setVisibility(View.VISIBLE);
                        txtDPPaperExpense_loan.setVisibility(View.VISIBLE);
                        edt_DownP_PaperExchange_loan.setVisibility(View.VISIBLE);

                        if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                          //  edt_DownP_oldAmount_loan.setVisibility(View.GONE);
                            edt_DownP_oldTractorAmount_loan.setVisibility(View.GONE);
                        }
                        else {
                           // edt_DownP_oldAmount_loan.setVisibility(View.VISIBLE);
                            edt_DownP_oldTractorAmount_loan.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                            lin_dp_NocPhoto_loan.setVisibility(View.GONE);
                        } else {
//                            lin_dp_NocPhoto_loan.setVisibility(View.VISIBLE);
                        }

                        txtDPOldTractorAmount_loan.setVisibility(View.VISIBLE);
                        edt_DownP_oldAmount_loan.setVisibility(View.VISIBLE);
                        txtDPNoc_loan.setVisibility(View.VISIBLE);
                        edt_DownP_NOC_loan.setVisibility(View.VISIBLE);

                     //   lin_dp_NocPhoto_loan.setVisibility(View.VISIBLE);
//                        lin_dp_OldVehicle_loan.setVisibility(View.VISIBLE);
//                        lin_dp_Rcbook_loan.setVisibility(View.VISIBLE);
//                        lin_dp_Election_loan.setVisibility(View.VISIBLE);
//
//                        lin_dp_Rcbook_loan2.setVisibility(View.VISIBLE);
//                        lin_dp_Election_loan2.setVisibility(View.VISIBLE);
                        //  Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Bank")) {
                        // Log.e("TAG", "onResponse:casting450 ");
                        // Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();

                        txtDPBankOption_loan.setVisibility(View.VISIBLE);
                        edt_DownP_BankOption_loan.setVisibility(View.VISIBLE);

                        if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                            txtDPChequeDate_loan.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeDate_loan.setVisibility(View.VISIBLE);
                            txtDPChequeAmount_loan.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeAmount_loan.setVisibility(View.VISIBLE);
                            lin_dp_cheque_loan.setVisibility(View.VISIBLE);

                            txtDPNEFT_RTGSdate_loan.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date_loan.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount_loan.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_loan.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_loan.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                        {
                            txtDPNEFT_RTGSdate_loan.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGS_date_loan.setVisibility(View.VISIBLE);
                            txtDPNEFT_RTGSAmount_loan.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGSAmount_loan.setVisibility(View.VISIBLE);
                            lin_dp_NEFT_RTGS_loan.setVisibility(View.VISIBLE);

                            txtDPChequeDate_loan.setVisibility(View.GONE);
                            edt_DownP_ChequeDate_loan.setVisibility(View.GONE);
                            txtDPChequeAmount_loan.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_loan.setVisibility(View.GONE);
                            lin_dp_cheque_loan.setVisibility(View.GONE);
                        }

                    }
                }


                /*********************************************************************************************/




               /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Cash")){

                    txtDPCashAmount_loan.setVisibility(View.VISIBLE);
                    edt_DownP_CashAmount_loan.setVisibility(View.VISIBLE);

                    edt_DownP_BankOption_loan.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_loan.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_loan.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGS_date_loan.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_loan.setVisibility(View.GONE);
                    edt_DownP_SelectMake_loan.setVisibility(View.GONE);
                    edt_DownP_ModelVehicle_loan.setVisibility(View.GONE);
                    edt_DownP_oldAmount_loan.setVisibility(View.GONE);
                    edt_DownP_ManufactureYear_loan.setVisibility(View.GONE);
                    edt_DownP_PaperExchange_loan.setVisibility(View.GONE);
                    edt_DownP_oldTractorAmount_loan.setVisibility(View.GONE);
                    edt_DownP_NOC_loan.setVisibility(View.GONE);

                    lin_dp_cheque_loan.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_loan.setVisibility(View.GONE);
                    lin_dp_NocPhoto_loan.setVisibility(View.GONE);
                    lin_dp_OldVehicle_loan.setVisibility(View.GONE);
                    lin_dp_Rcbook_loan.setVisibility(View.GONE);
                    lin_dp_Election_loan.setVisibility(View.GONE);


                    lin_dp_Rcbook_loan2.setVisibility(View.GONE);
                    lin_dp_Election_loan2.setVisibility(View.GONE);

                    txtDPBankOption_loan.setVisibility(View.GONE);
                    txtDPChequeDate_loan.setVisibility(View.GONE);
                    txtDPChequeAmount_loan.setVisibility(View.GONE);
                    txtDPNEFT_RTGSdate_loan.setVisibility(View.GONE);
                    txtDPNEFT_RTGSAmount_loan.setVisibility(View.GONE);
                    txtDPMake_loan.setVisibility(View.GONE);
                    txtDPManufectureYear_loan.setVisibility(View.GONE);
                    txtDPOldAmount_loan.setVisibility(View.GONE);
                    txtDPModelName_loan.setVisibility(View.GONE);
                    txtDPPaperExpense_loan.setVisibility(View.GONE);
                    txtDPOldTractorAmount_loan.setVisibility(View.GONE);
                    txtDPNoc_loan.setVisibility(View.GONE);
                }*/


               /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Bank")){

                    txtDPCashAmount_loan.setVisibility(View.GONE);
                    edt_DownP_CashAmount_loan.setVisibility(View.GONE);

                    txtDPBankOption_loan.setVisibility(View.VISIBLE);
                    edt_DownP_BankOption_loan.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                        txtDPChequeDate_loan.setVisibility(View.VISIBLE);
                        edt_DownP_ChequeDate_loan.setVisibility(View.VISIBLE);
                        txtDPChequeAmount_loan.setVisibility(View.VISIBLE);
                        edt_DownP_ChequeAmount_loan.setVisibility(View.VISIBLE);
                        lin_dp_cheque_loan.setVisibility(View.VISIBLE);

                        txtDPNEFT_RTGSdate_loan.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGS_date_loan.setVisibility(View.GONE);
                        txtDPNEFT_RTGSAmount_loan.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGSAmount_loan.setVisibility(View.GONE);
                        lin_dp_NEFT_RTGS_loan.setVisibility(View.GONE);

                        txtDPMake_loan.setVisibility(View.GONE);
                        edt_DownP_SelectMake_loan.setVisibility(View.GONE);
                        txtDPModelName_loan.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_loan.setVisibility(View.GONE);
                        txtDPManufectureYear_loan.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_loan.setVisibility(View.GONE);
                        txtDPOldAmount_loan.setVisibility(View.GONE);
                        edt_DownP_oldAmount_loan.setVisibility(View.GONE);
                        txtDPPaperExpense_loan.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_loan.setVisibility(View.GONE);
                        txtDPOldTractorAmount_loan.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_loan.setVisibility(View.GONE);
                        txtDPNoc_loan.setVisibility(View.GONE);
                        edt_DownP_NOC_loan.setVisibility(View.GONE);

                        lin_dp_NocPhoto_loan.setVisibility(View.GONE);
                        lin_dp_OldVehicle_loan.setVisibility(View.GONE);
                        lin_dp_Rcbook_loan.setVisibility(View.GONE);
                        lin_dp_Election_loan.setVisibility(View.GONE);

                        lin_dp_Rcbook_loan2.setVisibility(View.GONE);
                        lin_dp_Election_loan2.setVisibility(View.GONE);


                    }

                    if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                    {
                        txtDPNEFT_RTGSdate_loan.setVisibility(View.VISIBLE);
                        edt_DownP_NEFT_RTGS_date_loan.setVisibility(View.VISIBLE);
                        txtDPNEFT_RTGSAmount_loan.setVisibility(View.VISIBLE);
                        edt_DownP_NEFT_RTGSAmount_loan.setVisibility(View.VISIBLE);
                        lin_dp_NEFT_RTGS_loan.setVisibility(View.VISIBLE);

                        txtDPChequeDate_loan.setVisibility(View.GONE);
                        edt_DownP_ChequeDate_loan.setVisibility(View.GONE);
                        txtDPChequeAmount_loan.setVisibility(View.GONE);
                        edt_DownP_ChequeAmount_loan.setVisibility(View.GONE);
                        lin_dp_cheque_loan.setVisibility(View.GONE);

                        txtDPMake_loan.setVisibility(View.GONE);
                        edt_DownP_SelectMake_loan.setVisibility(View.GONE);
                        txtDPModelName_loan.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_loan.setVisibility(View.GONE);
                        txtDPManufectureYear_loan.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_loan.setVisibility(View.GONE);
                        txtDPOldAmount_loan.setVisibility(View.GONE);
                        edt_DownP_oldAmount_loan.setVisibility(View.GONE);
                        txtDPPaperExpense_loan.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_loan.setVisibility(View.GONE);
                        txtDPOldTractorAmount_loan.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_loan.setVisibility(View.GONE);
                        txtDPNoc_loan.setVisibility(View.GONE);
                        edt_DownP_NOC_loan.setVisibility(View.GONE);

                        lin_dp_NocPhoto_loan.setVisibility(View.GONE);
                        lin_dp_OldVehicle_loan.setVisibility(View.GONE);
                        lin_dp_Rcbook_loan.setVisibility(View.GONE);
                        lin_dp_Election_loan.setVisibility(View.GONE);

                        lin_dp_Rcbook_loan2.setVisibility(View.GONE);
                        lin_dp_Election_loan2.setVisibility(View.GONE);
                    }

                }*/

                //Old Vehicle
                /*if(response.body().getData().get(id_pos).getBooking_amt().equals("Old Vehicle")){

                    txtDPMake_loan.setVisibility(View.VISIBLE);
                    edt_DownP_SelectMake_loan.setVisibility(View.VISIBLE);
                    txtDPModelName_loan.setVisibility(View.VISIBLE);
                    edt_DownP_ModelVehicle_loan.setVisibility(View.VISIBLE);
                    txtDPManufectureYear_loan.setVisibility(View.VISIBLE);
                    edt_DownP_ManufactureYear_loan.setVisibility(View.VISIBLE);
                    txtDPOldAmount_loan.setVisibility(View.VISIBLE);
                    txtDPPaperExpense_loan.setVisibility(View.VISIBLE);
                    edt_DownP_PaperExchange_loan.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                        edt_DownP_oldAmount_loan.setVisibility(View.GONE);
                    }
                    else {
                        edt_DownP_oldAmount_loan.setVisibility(View.VISIBLE);
                    }

                    txtDPOldTractorAmount_loan.setVisibility(View.VISIBLE);
                    edt_DownP_oldTractorAmount_loan.setVisibility(View.VISIBLE);
                    txtDPNoc_loan.setVisibility(View.VISIBLE);
                    edt_DownP_NOC_loan.setVisibility(View.VISIBLE);

                    lin_dp_NocPhoto_loan.setVisibility(View.VISIBLE);
                    lin_dp_OldVehicle_loan.setVisibility(View.VISIBLE);
                    lin_dp_Rcbook_loan.setVisibility(View.VISIBLE);
                    lin_dp_Election_loan.setVisibility(View.VISIBLE);

                    lin_dp_Rcbook_loan2.setVisibility(View.VISIBLE);
                    lin_dp_Election_loan2.setVisibility(View.VISIBLE);


                    txtDPCashAmount_loan.setVisibility(View.GONE);
                    edt_DownP_CashAmount_loan.setVisibility(View.GONE);
                    txtDPBankOption_loan.setVisibility(View.GONE);
                    edt_DownP_BankOption_loan.setVisibility(View.GONE);
                    txtDPChequeDate_loan.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_loan.setVisibility(View.GONE);
                    txtDPChequeAmount_loan.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_loan.setVisibility(View.GONE);
                    lin_dp_cheque_loan.setVisibility(View.GONE);
                    txtDPNEFT_RTGSdate_loan.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGS_date_loan.setVisibility(View.GONE);
                    txtDPNEFT_RTGSAmount_loan.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_loan.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_loan.setVisibility(View.GONE);
                }*/


                  if(response.body().getData().get(id_pos).getRto().equals("No")){
                    edt_RTO_RtoTax_loan.setVisibility(View.GONE);
                    edt_RTO_RtoPassing_loan.setVisibility(View.GONE);
                    edt_RTO_Insurance_loan.setVisibility(View.GONE);
                    edt_RTO_AgentFees_loan.setVisibility(View.GONE);
                    edt_RTO_NumberPlat_loan.setVisibility(View.GONE);
                    edt_RTO_LoanCharge_loan.setVisibility(View.GONE);


                      txtRTOTax.setVisibility(View.GONE);
                      txtRTOPassing.setVisibility(View.GONE);
                      txtInsurance.setVisibility(View.GONE);
                      txtAgentFees.setVisibility(View.GONE);
                      txtNumberPlat.setVisibility(View.GONE);
                      txtLoanCharge.setVisibility(View.GONE);
                }
                else{
                    edt_RTO_RtoTax_loan.setVisibility(View.VISIBLE);
                    edt_RTO_RtoPassing_loan.setVisibility(View.VISIBLE);
                    edt_RTO_Insurance_loan.setVisibility(View.VISIBLE);
                    edt_RTO_AgentFees_loan.setVisibility(View.VISIBLE);
                    edt_RTO_NumberPlat_loan.setVisibility(View.VISIBLE);
                    edt_RTO_LoanCharge_loan.setVisibility(View.VISIBLE);

                    txtRTOTax.setVisibility(View.VISIBLE);
                    txtRTOPassing.setVisibility(View.VISIBLE);
                    txtInsurance.setVisibility(View.VISIBLE);
                    txtAgentFees.setVisibility(View.VISIBLE);
                    txtNumberPlat.setVisibility(View.VISIBLE);
                    txtLoanCharge.setVisibility(View.VISIBLE);
                }

                if(response.body().getData().get(id_pos).getSkim().equals("No")){

                    edt_CS_Hood_loan.setVisibility(View.GONE);
                    edt_CS_TopLink_loan.setVisibility(View.GONE);
                    edt_CS_DrawBar_loan.setVisibility(View.GONE);
                    edt_CS_ToolKit_loan.setVisibility(View.GONE);
                    edt_CS_Bumper_loan.setVisibility(View.GONE);
                    edt_CS_Hitch_loan.setVisibility(View.GONE);
                //    edt_CS_Description_loan.setVisibility(View.GONE);

                    txt_cs_Hood.setVisibility(View.GONE);
                    txt_cs_TopLink.setVisibility(View.GONE);
                    txt_cs_Drawbar.setVisibility(View.GONE);
                    txt_cs_ToolKit.setVisibility(View.GONE);
                    txt_cs_Bumper.setVisibility(View.GONE);
                    txt_cs_Hitch.setVisibility(View.GONE);
                  //  txt_cs_Description.setVisibility(View.GONE);

                }
                else{
                    edt_CS_Hood_loan.setVisibility(View.VISIBLE);
                    edt_CS_TopLink_loan.setVisibility(View.VISIBLE);
                    edt_CS_DrawBar_loan.setVisibility(View.VISIBLE);
                    edt_CS_ToolKit_loan.setVisibility(View.VISIBLE);
                    edt_CS_Bumper_loan.setVisibility(View.VISIBLE);
                    edt_CS_Hitch_loan.setVisibility(View.VISIBLE);
                 //   edt_CS_Description_loan.setVisibility(View.VISIBLE);

                    txt_cs_Hood.setVisibility(View.VISIBLE);
                    txt_cs_TopLink.setVisibility(View.VISIBLE);
                    txt_cs_Drawbar.setVisibility(View.VISIBLE);
                    txt_cs_ToolKit.setVisibility(View.VISIBLE);
                    txt_cs_Bumper.setVisibility(View.VISIBLE);
                    txt_cs_Hitch.setVisibility(View.VISIBLE);
                 //   txt_cs_Description.setVisibility(View.VISIBLE);
                }


               // if(edt_CD_PaymentOption_loan.getText().equals("Loan")){
                if(response.body().getData().get(id_pos).getAtype().equals("Loan")){
                    lin_LoanDetail_Loan.setVisibility(View.VISIBLE);
                    lin_cashLoan_loan.setVisibility(View.GONE);
                }
                /*else {
                    lin_LoanDetail_Loan.setVisibility(View.GONE);
                }*/

                if(response.body().getData().get(id_pos).getAtype().equals("Cash")){
                    lin_LoanDetail_Loan.setVisibility(View.GONE);
                    lin_cashLoan_loan.setVisibility(View.VISIBLE);
                }

             //   Toast.makeText(LoanFillFormActivity.this, "" +response.body().getData().get(id_pos).getAtype(), Toast.LENGTH_LONG).show();

                if(response.body().getData().get(id_pos).getAtype().equals("Cash-Loan")){
                    lin_LoanDetail_Loan.setVisibility(View.VISIBLE);
                    lin_cashLoan_loan.setVisibility(View.VISIBLE);
                }
                /*else {
                    lin_LoanDetail_Loan.setVisibility(View.GONE);
                }*/


                edt_ADBooking_BookingNo_loan.setText(response.body().getData().get(id_pos).getBno());
                edt_ADBooking_BookingType_loan.setText(response.body().getData().get(id_pos).getB_type());
                edt_ADBooking_BookingDate_loan.setText(response.body().getData().get(id_pos).getB_date());
                edt_ADBooking_BookingLoginName_loan.setText(response.body().getData().get(id_pos).getEmp());

                edt_CD_Fname_loan.setText(response.body().getData().get(id_pos).getFname());
                edt_CD_LastName_loan.setText(response.body().getData().get(id_pos).getLname()+" ");
                edt_CD_Surname_loan.setText(response.body().getData().get(id_pos).getSname());
                edt_CD_MobileNo_loan.setText(response.body().getData().get(id_pos).getMobileno());
                edt_CD_WhatsappNo_loan.setText(response.body().getData().get(id_pos).getWhno());
                edt_CD_ReferenceName_loan.setText(response.body().getData().get(id_pos).getRef_name());
                edt_CD_ReferenceNo_loan.setText(response.body().getData().get(id_pos).getRef_no());
                edt_CD_State_loan.setText(response.body().getData().get(id_pos).getState());
                edt_CD_City_loan.setText(response.body().getData().get(id_pos).getCity());
                edt_CD_District_loan.setText(response.body().getData().get(id_pos).getDistric());
                edt_CD_Village_loan.setText(response.body().getData().get(id_pos).getVillage());
                edt_CD_EmployeName_loan.setText(response.body().getData().get(id_pos).getEmp());
                edt_CD_Taluko_loan.setText(response.body().getData().get(id_pos).getTehsill());
                edt_CD_PassBook_loan.setText(response.body().getData().get(id_pos).getB_p_photo());
                edt_CD_ChequeBook_loan.setText(response.body().getData().get(id_pos).getCheck_photo());
                edt_CD_PaymentOption_loan.setText(response.body().getData().get(id_pos).getAtype());

                edt_PD_PurchaseName_loan.setText(response.body().getData().get(id_pos).getProduct_name());
                edt_PD_ModelName_loan.setText(response.body().getData().get(id_pos).getModel_name());
                edt_PD_Description_loan.setText(response.body().getData().get(id_pos).getP_desc());


                edt_PriceD_price_loan.setText(response.body().getData().get(id_pos).getDeal_price());
                edt_PriceD_priceInWord_loan.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                edt_PriceD_GST_loan.setText(response.body().getData().get(id_pos).getGst());



                edt_RTO_RtoMain_loan.setText(response.body().getData().get(id_pos).getRto());
                edt_RTO_RtoTax_loan.setText(response.body().getData().get(id_pos).getRto_tax());
                edt_RTO_RtoPassing_loan.setText(response.body().getData().get(id_pos).getRto_passing());
                edt_RTO_Insurance_loan.setText(response.body().getData().get(id_pos).getInsurance());
                edt_RTO_AgentFees_loan.setText(response.body().getData().get(id_pos).getAgent_fee());
                edt_RTO_NumberPlat_loan.setText(response.body().getData().get(id_pos).getNumber_plat());
                edt_RTO_LoanCharge_loan.setText(response.body().getData().get(id_pos).getLoan_charge());

                edt_DownP_BookingAmount_loan.setText(response.body().getData().get(id_pos).getBooking_amt());
                edt_DownP_CashAmount_loan.setText(response.body().getData().get(id_pos).getAmount());
                edt_DownP_BankOption_loan.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                edt_DownP_ChequeDate_loan.setText(response.body().getData().get(id_pos).getCheck_date());
                edt_DownP_ChequeAmount_loan.setText(response.body().getData().get(id_pos).getCheck_amt());
                edt_DownP_NEFT_RTGS_date_loan.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                edt_DownP_NEFT_RTGSAmount_loan.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                edt_DownP_SelectMake_loan.setText(response.body().getData().get(id_pos).getMake());
                edt_DownP_ModelVehicle_loan.setText(response.body().getData().get(id_pos).getModel());
                edt_DownP_oldAmount_loan.setText(response.body().getData().get(id_pos).getOld_t_amount());
                edt_DownP_ManufactureYear_loan.setText(response.body().getData().get(id_pos).getM_year());
                edt_DownP_PaperExchange_loan.setText(response.body().getData().get(id_pos).getPaper_expence());
                edt_DownP_oldTractorAmount_loan.setText(response.body().getData().get(id_pos).getC_amount());
                edt_DownP_NOC_loan.setText(response.body().getData().get(id_pos).getNoc());


                edt_CS_ConsumerSkim_loan.setText(response.body().getData().get(id_pos).getSkim());
                edt_CS_Hood_loan.setText(response.body().getData().get(id_pos).getHood());
                edt_CS_TopLink_loan.setText(response.body().getData().get(id_pos).getToplink());
                edt_CS_DrawBar_loan.setText(response.body().getData().get(id_pos).getDrowbar());
                edt_CS_ToolKit_loan.setText(response.body().getData().get(id_pos).getToolkit());
                edt_CS_Bumper_loan.setText(response.body().getData().get(id_pos).getBumper());
                edt_CS_Hitch_loan.setText(response.body().getData().get(id_pos).getHitch());
                edt_CS_Description_loan.setText(response.body().getData().get(id_pos).getDescription());


                //Loan Detail
//                Log.d("TAG", "onResponse: CheckLoandetail "+response.body().getData().get(id_pos).getR_e_name());
//                Log.d("TAG", "onResponse: CheckLoandetail "+response.body().getData().get(id_pos).getR_e_name());
                if(response.body().getData().get(id_pos).getR_e_name()==null){
                    edt_loanDetail_REF.setText("");

                }else {
                    edt_loanDetail_REF.setText(response.body().getData().get(id_pos).getR_e_name());
                }
                Log.d("TAG", "onResponse: Checkresponce "+ response.body().getData().get(id_pos).getLoan_amount()+" id"+id_pos);

                if (response.body().getData().get(id_pos).getLoan_amount() == null || response.body().getData().get(id_pos).getLoan_amount().equals("")){
//                    Log.d("TAG", "onResponse: Checkresponce "+ response.body().getData().get(id_pos).getLoan_amount());
                }else {
                    edt_loanDetail_LoanAmount.setText(response.body().getData().get(id_pos).getLoan_amount());

                }
                if (response.body().getData().get(id_pos).getL_sec_amt()==null || response.body().getData().get(id_pos).getL_sec_amt().equals("")){

                }else {
                    edt_loanDetail_LoanSancAmont.setText(response.body().getData().get(id_pos).getL_sec_amt().toString());

                }
//                Log.d("TAG", "onResponse: Checkresponce "+ response.body().getData().get(id_pos).getLloan_charge()+" id "+id_pos );
                if (response.body().getData().get(id_pos).getLloan_charge()==null){

                }else {
                    edt_loanDetail_LoanCharge.setText(response.body().getData().get(id_pos).getLloan_charge().toString());
                }
                if (response.body().getData().get(id_pos).getLand_details()==null){

                }else {
                    edt_loanDetail_LandDetail.setText(response.body().getData().get(id_pos).getLand_details().toString());
                }
                if (response.body().getData().get(id_pos).getCibil_score()==null){
                }else {
                    edt_loanDetail_CibilScore.setText(response.body().getData().get(id_pos).getCibil_score().toString());

                }
                int j = Satge_data.indexOf(response.body().getData().get(id_pos).getStage());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int i = FinanceName.indexOf(response.body().getData().get(id_pos).getFinance_from());
                        spn_loanDetail_FinanceForm.setSelection(i);
                        Log.d("TAG", "onResponse: PositonCheck "+i+" "+response.body().getData().get(id_pos).getFinance_from()+" "+j);

                    }
                },1000);
                spn_loanDetail_Stage.setSelection(j);
                if (j==3 || Stage.equals("sanction")){
                    txt_loanDetail_FiDate.setVisibility(View.VISIBLE);
                    edt_loanDetail_FiDate.setVisibility(View.VISIBLE);

                    txt_loanDetail_SanctionDate.setVisibility(View.VISIBLE);
                    edt_loanDetail_SanctionDate.setVisibility(View.VISIBLE);
                }else {
                    txt_loanDetail_FiDate.setVisibility(View.GONE);
                    edt_loanDetail_FiDate.setVisibility(View.GONE);

                    txt_loanDetail_SanctionDate.setVisibility(View.GONE);
                    edt_loanDetail_SanctionDate.setVisibility(View.GONE);
                }
                if (response.body().getData().get(id_pos).getFi_date()==null){

                }else {
                    edt_loanDetail_FiDate.setText(response.body().getData().get(id_pos).getFi_date().toString());
                }
                if (response.body().getData().get(id_pos).getSectiondate()==null){

                }else {
                    edt_loanDetail_SanctionDate.setText(response.body().getData().get(id_pos).getSectiondate());

                }

               /* Toast.makeText(LoanFillFormActivity.this, ""+
                        response.body().getData().get(id_pos).getB_type()+
                        " "+response.body().getData().get(id_pos).getB_p_photo()+" "+
                response.body().getData().get(id_pos).getCheck_photo(), Toast.LENGTH_SHORT).show();*/

            }

            @Override
            public void onFailure(@NotNull Call<LoanDataDisplayModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("LoanFill", "onFailure: "+t.getMessage()+" "+t.getMessage());
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                //  Utils.showErrorToast(BookingPhaseOneActivity.this,"No Data Available");
            }
        });

        //Loan Subtraction
        edt_loanDetail_LoanAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_loanDetail_LoanAmount.getText().length()==0){

                }else {
                    x = Integer.parseInt(edt_loanDetail_LoanAmount.getText().toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edt_loanDetail_LoanAmount.getText().length()==0){

                }else {
                    x = Integer.parseInt(edt_loanDetail_LoanAmount.getText().toString());

                }
            }
        });

        edt_loanDetail_LoanSancAmont.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_loanDetail_LoanSancAmont.getText().length()==0){

                }else {
                    y = Integer.parseInt(edt_loanDetail_LoanSancAmont.getText().toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edt_loanDetail_LoanSancAmont.getText().length()==0){

                }else {
                    y = Integer.parseInt(edt_loanDetail_LoanSancAmont.getText().toString());

                    //  Toast.makeText(LoanFillFormActivity.this, ""+y+x, Toast.LENGTH_SHORT).show();

                    sub = x - y; //Perform Maths operation
                    edt_loanDetail_LoanCharge.setText(""+ sub);//print answer
                }

              //  Toast.makeText(LoanFillFormActivity.this, ""+x+y+sub, Toast.LENGTH_SHORT).show();

            }
        });

      /*  sub = x - y; //Perform Maths operation
        edt_loanDetail_LoanCharge.setText(" "+ sub);//print answer
        Toast.makeText(this, ""+x+y+sub, Toast.LENGTH_SHORT).show();*/


     /*   edt_loanDetail_LoanCharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
              //  x = Integer.parseInt(edt_loanDetail_LoanAmount.getText().toString());
              //  y = Integer.parseInt(edt_loanDetail_LoanSancAmont.getText().toString());
                sub = x - y; //Perform Maths operation
                edt_loanDetail_LoanCharge.setText(" "+ sub);//print answer
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               *//* x = Integer.parseInt(edt_loanDetail_LoanAmount.getText().toString());
                y = Integer.parseInt(edt_loanDetail_LoanSancAmont.getText().toString());
                sub = x - y; //Perform Maths operation
                edt_loanDetail_LoanCharge.setText(" "+ sub);//print answer*//*
            }
        });*/


        /* int x = new Integer(etv.getText().toString());
    int y = new Integer(etv2.getText().toString());
    int sub = x - y; //Perform Maths operation
    result.setText("The ANS of " + x + " - " + y + " = " + sub);//print answer*/


        txt_CD_UploadBookingPhoto_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        txt_CD_AdharCard_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        txt_CD_ElectionCard_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });

        txt_CD_PassportSize_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });


        txt_DownP_UploadCheque_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });

        txt_DownP_UploadNEFT_RTGS_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

        txt_DownP_UploadNocPhoto_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_DownP_UploadOldVehicle_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });

        txt_DownP_UploadRCBook_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });

        txt_DownP_UploadElectionPhoto_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 11);
            }
        });


        txt_LoanDetail_BankPassBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 12);
            }
        });

        txt_LoanDetail_Cheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 13);
            }
        });

        txt_LoanDetail_SarpanchId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 14);
            }
        });

        txt_LoanDetail_SignatureVeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 15);
            }
        });


        // ////////////////////////////////////

          txt_CD_AdharCard_loan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 16);
            }
        });

        txt_CD_ElectionCard_loan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 17);
            }
        });

        txt_CD_PassportSize_loan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 18);
            }
        });

         // ************************************************

         txt_DownP_UploadRCBook_loan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 19);
            }
        });

        txt_DownP_UploadElectionPhoto_loan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 20);
            }
        });

        //******************************************************
        txt_LoanDetail_BankPassBook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 21);
            }
        });


        ArrayAdapter adapterStage = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Satge_data);
        spn_loanDetail_Stage.setAdapter(adapterStage);

        spn_loanDetail_Stage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Stage = Satge_data.get(position);
                if (Stage.equals("sanction")){
                    txt_loanDetail_FiDate.setVisibility(View.VISIBLE);
                    edt_loanDetail_FiDate.setVisibility(View.VISIBLE);

                    txt_loanDetail_SanctionDate.setVisibility(View.VISIBLE);
                    edt_loanDetail_SanctionDate.setVisibility(View.VISIBLE);
                }else {
                    txt_loanDetail_FiDate.setVisibility(View.GONE);
                    edt_loanDetail_FiDate.setVisibility(View.GONE);

                    txt_loanDetail_SanctionDate.setVisibility(View.GONE);
                    edt_loanDetail_SanctionDate.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        WebService.getClient().getFinanceForm().enqueue(new Callback<FinanceFormModel>() {
            @Override
            public void onResponse(Call<FinanceFormModel> call, Response<FinanceFormModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        FinanceName.clear();
                        FInanceID.clear();
                        //    Log.d("product", response.body().toString());

                        for (int i = 0; i < response.body().getFinance().size(); i++) {
                            FinanceName.add(response.body().getFinance().get(i).getFcname());
                            FInanceID.add(response.body().getFinance().get(i).getId());
                        }

                        Log.d("ProductS", "onResponse: " + response.body().getFinance().size());

                        //   Log.d("MProduct", ModelName.toString());

                        ArrayAdapter adapter2 = new ArrayAdapter(LoanFillFormActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, FinanceName);
                        spn_loanDetail_FinanceForm.setAdapter(adapter2);


                        spn_loanDetail_FinanceForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                finance_name = FinanceName.get(position);
                                finance_id = FInanceID.get(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<FinanceFormModel> call, Throwable t) {

            }
        });


        edt_loanDetail_SanctionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(LoanFillFormActivity.this,
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

                        edt_loanDetail_SanctionDate.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_loanDetail_SanctionDate.setFocusable(false);


        edt_loanDetail_FiDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DatePickerDialog datePickerDialog = new DatePickerDialog(LoanFillFormActivity.this,
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
                        edt_loanDetail_FiDate.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_loanDetail_FiDate.setFocusable(false);


        edt_CashDetail_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(LoanFillFormActivity.this,
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
                        edt_CashDetail_Date.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_CashDetail_Date.setFocusable(false);


        btn_LoanPhase_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog= new ProgressDialog(LoanFillFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                MultipartBody.Part CD_UploadBookingPhoto_loan = null;
                MultipartBody.Part CD_AdharCard_loan  = null;
                MultipartBody.Part CD_ElectionCard_loan  = null;
                MultipartBody.Part CD_PassportSize_loan  = null;
                MultipartBody.Part UploadDPCheque_loan  = null;
                MultipartBody.Part UploadDPNEFT_RTGS_loan = null;
                MultipartBody.Part UploadDPNocPhoto_loan = null;
                MultipartBody.Part UploadDPOldVehicle_loan = null;
                MultipartBody.Part UploadDPRCBook_loan = null;
                MultipartBody.Part UploadDPElectionPhoto_loan = null;

                MultipartBody.Part LoanDetail_BankPassBook = null;
                MultipartBody.Part LoanDetail_Cheque = null;
                MultipartBody.Part LoanDetail_SarpanchId = null;
                MultipartBody.Part LoanDetail_SignatureVeri = null;

                //-----------------------------------------------------------

                MultipartBody.Part CD_AdharCard_loan2  = null;
                MultipartBody.Part CD_ElectionCard_loan2  = null;
                MultipartBody.Part CD_PassportSize_loan2  = null;

                MultipartBody.Part UploadDPRCBook_loan2 = null;
                MultipartBody.Part UploadDPElectionPhoto_loan2 = null;

                MultipartBody.Part LoanDetail_BankPassBook2 = null;



                if(waypath_CD_UploadBookingPhoto_loan != null){
                    File file1 = new File(waypath_CD_UploadBookingPhoto_loan);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    CD_UploadBookingPhoto_loan = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }

                if(waypath_CD_AdharCard_loan != null){
                    File file2 = new File(waypath_CD_AdharCard_loan);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    CD_AdharCard_loan = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }

                if(waypath_CD_ElectionCard_loan != null){
                    File file3 = new File(waypath_CD_ElectionCard_loan);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    CD_ElectionCard_loan = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                if(waypath_CD_PassportSize_loan != null){
                    File file4 = new File(waypath_CD_PassportSize_loan);
                    final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                    CD_PassportSize_loan = MultipartBody.Part.createFormData("image6",
                            file4.getName(), requestBody4);
                }


                if(waypathUploadDPCheque_loan != null){
                    File file5 = new File(waypathUploadDPCheque_loan);
                    final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                    UploadDPCheque_loan = MultipartBody.Part.createFormData("image7",
                            file5.getName(), requestBody5);
                }

                if(waypathUploadDPNEFT_RTGS_loan != null){
                    File file6 = new File(waypathUploadDPNEFT_RTGS_loan);
                    final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                    UploadDPNEFT_RTGS_loan = MultipartBody.Part.createFormData("image8",
                            file6.getName(), requestBody6);
                }


                if(waypathUploadDPOldVehicle_loan != null){
                    File file8 = new File(waypathUploadDPOldVehicle_loan);
                    final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                    UploadDPOldVehicle_loan = MultipartBody.Part.createFormData("image9",
                            file8.getName(), requestBody8);
                }

                if(waypathUploadDPRCBook_loan != null){
                    File file9 = new File(waypathUploadDPRCBook_loan);
                    final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file9);
                    UploadDPRCBook_loan = MultipartBody.Part.createFormData("image10",
                            file9.getName(), requestBody9);
                }

                if(waypathUploadDPElectionPhoto_loan != null){
                    File file10 = new File(waypathUploadDPElectionPhoto_loan);
                    final RequestBody requestBody10 = RequestBody.create(MediaType.parse("image/*"), file10);
                    UploadDPElectionPhoto_loan = MultipartBody.Part.createFormData("image11",
                            file10.getName(), requestBody10);
                }

                if(waypathUploadDPNocPhoto_loan != null){
                    File file7 = new File(waypathUploadDPNocPhoto_loan);
                    final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                    UploadDPNocPhoto_loan = MultipartBody.Part.createFormData("image12",
                            file7.getName(), requestBody7);
                }



                if(WayPath_LoanDetail_BankPassBook!= null){
                    File file11 = new File(WayPath_LoanDetail_BankPassBook);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file11);
                    LoanDetail_BankPassBook = MultipartBody.Part.createFormData("do_photo13",
                            file11.getName(), requestBody11);
                }

                if(WayPath_LoanDetail_Cheque!= null){
                    File file12 = new File(WayPath_LoanDetail_Cheque);
                    final RequestBody requestBody12 = RequestBody.create(MediaType.parse("image/*"), file12);
                    LoanDetail_Cheque = MultipartBody.Part.createFormData("do_photo14",
                            file12.getName(), requestBody12);
                }

                if(WayPath_LoanDetail_SarpanchId!= null){
                    File file13 = new File(WayPath_LoanDetail_SarpanchId);
                    final RequestBody requestBody13 = RequestBody.create(MediaType.parse("image/*"), file13);
                    LoanDetail_SarpanchId = MultipartBody.Part.createFormData("do_photo15",
                            file13.getName(), requestBody13);
                }

                if(WayPath_LoanDetail_SignatureVeri!= null){
                    File file14 = new File(WayPath_LoanDetail_SignatureVeri);
                    final RequestBody requestBody14 = RequestBody.create(MediaType.parse("image/*"), file14);
                    LoanDetail_SignatureVeri = MultipartBody.Part.createFormData("do_photo16",
                            file14.getName(), requestBody14);
                }


                //----------------------------------------------

                if(waypath_CD_AdharCard_loan2 != null){
                    File file15 = new File(waypath_CD_AdharCard_loan2);
                    final RequestBody requestBody15 = RequestBody.create(MediaType.parse("image/*"), file15);
                    CD_AdharCard_loan2 = MultipartBody.Part.createFormData("imgg1",
                            file15.getName(), requestBody15);
                }

                if(waypath_CD_ElectionCard_loan2 != null){
                    File file16 = new File(waypath_CD_ElectionCard_loan2);
                    final RequestBody requestBody16 = RequestBody.create(MediaType.parse("image/*"), file16);
                    CD_ElectionCard_loan2 = MultipartBody.Part.createFormData("imgg2",
                            file16.getName(), requestBody16);
                }

                if(waypath_CD_PassportSize_loan2 != null){
                    File file17 = new File(waypath_CD_PassportSize_loan2);
                    final RequestBody requestBody17 = RequestBody.create(MediaType.parse("image/*"), file17);
                    CD_PassportSize_loan2 = MultipartBody.Part.createFormData("imgg3",
                            file17.getName(), requestBody17);
                }

                if(waypathUploadDPRCBook_loan2 != null){
                    File file18 = new File(waypathUploadDPRCBook_loan2);
                    final RequestBody requestBody18 = RequestBody.create(MediaType.parse("image/*"), file18);
                    UploadDPRCBook_loan2 = MultipartBody.Part.createFormData("imgg4",
                            file18.getName(), requestBody18);
                }

                if(waypathUploadDPElectionPhoto_loan2 != null){
                    File file19 = new File(waypathUploadDPElectionPhoto_loan2);
                    final RequestBody requestBody19 = RequestBody.create(MediaType.parse("image/*"), file19);
                    UploadDPElectionPhoto_loan2 = MultipartBody.Part.createFormData("imgg5",
                            file19.getName(), requestBody19);
                }


                if(WayPath_LoanDetail_BankPassBook2!= null){
                    File file20 = new File(WayPath_LoanDetail_BankPassBook2);
                    final RequestBody requestBody20 = RequestBody.create(MediaType.parse("image/*"), file20);
                    LoanDetail_BankPassBook2 = MultipartBody.Part.createFormData("imgg6",
                            file20.getName(), requestBody20);
                }

                if(finance_name == null){
                    finance_name = " ";
                }


//                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingLoginName_loan.getText().toString()),

                WebService.getClient().getLoanSubmit(
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingNo_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingDate_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), emp),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Fname_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Surname_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_MobileNo_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_WhatsappNo_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceName_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceNo_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_State_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_City_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_District_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Taluko_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Village_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingLoginName_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingType_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PassBook_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ChequeBook_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_PurchaseName_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_ModelName_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_Description_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_price_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_priceInWord_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_GST_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoMain_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoTax_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoPassing_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_Insurance_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_AgentFees_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_NumberPlat_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_LoanCharge_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BookingAmount_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_CashAmount_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BankOption_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeDate_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeAmount_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGS_date_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGSAmount_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_SelectMake_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ModelVehicle_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ManufactureYear_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldAmount_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_PaperExchange_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldTractorAmount_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NOC_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hood_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_TopLink_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_DrawBar_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ToolKit_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Bumper_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hitch_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Description_loan.getText().toString()),
                        CD_UploadBookingPhoto_loan,
                        CD_AdharCard_loan,
                        CD_ElectionCard_loan ,
                        CD_PassportSize_loan ,
                        UploadDPCheque_loan  ,
                        UploadDPNEFT_RTGS_loan,
                        UploadDPOldVehicle_loan,
                        UploadDPRCBook_loan,
                        UploadDPElectionPhoto_loan,
                        UploadDPNocPhoto_loan ,
                        RequestBody.create(MediaType.parse("text/plain"), idBookingUpload),
                        RequestBody.create(MediaType.parse("text/plain"), edt_loanDetail_REF.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),finance_name),
                        RequestBody.create(MediaType.parse("text/plain"), edt_loanDetail_LoanAmount.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_loanDetail_LoanSancAmont.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_loanDetail_LoanCharge.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_loanDetail_LandDetail.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_loanDetail_CibilScore.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_loanDetail_FiDate.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_loanDetail_SanctionDate.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),Stage),
                        RequestBody.create(MediaType.parse("text/plain"),edt_CD_PaymentOption_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),edt_CS_ConsumerSkim_loan.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),edt_CashDetail_Date.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),edt_CashDetail_Amount.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),edt_CashDetail_Description.getText().toString()),
                        LoanDetail_BankPassBook,
                        /*LoanDetail_Cheque,
                        LoanDetail_SarpanchId,*/
                        LoanDetail_SignatureVeri,
                        CD_AdharCard_loan2,
                        CD_ElectionCard_loan2,
                        CD_PassportSize_loan2,
                        UploadDPRCBook_loan2,
                        UploadDPElectionPhoto_loan2,
                        LoanDetail_BankPassBook2
                        ).enqueue(new Callback<LoanSubmitBookingModel>()
                {
                    @Override
                    public void onResponse(@NotNull Call<LoanSubmitBookingModel> call, @NotNull Response<LoanSubmitBookingModel> response) {
                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(LoanFillFormActivity.this, ""
                                        +response.body().getMessage(),
                                Toast.LENGTH_LONG).show();

                        Intent i = new Intent(LoanFillFormActivity.this,
                                BookingDeliveryMainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<LoanSubmitBookingModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.d("Fail", "onFailure: "+t.getCause()+" "+t.getMessage());
                        Toast.makeText(LoanFillFormActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_UploadBookingPhoto_loan = data.getData();
                    Log.d("PanImageUri", uri_CD_UploadBookingPhoto_loan.toString());
                    waypath_CD_UploadBookingPhoto_loan = getFilePath(this, uri_CD_UploadBookingPhoto_loan);


                    Log.d("PanImage", waypath_CD_UploadBookingPhoto_loan);
                    String[] name = waypath_CD_UploadBookingPhoto_loan.split("/");
                    txt_CD_UploadBookingPhoto_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_Booking_loan.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_loan = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_loan.toString());
                    waypath_CD_AdharCard_loan = getFilePath(this, uri_CD_AdharCard_loan);

                    Log.d("PanRTGS", waypath_CD_AdharCard_loan);
                    String[] name = waypath_CD_AdharCard_loan.split("/");
                    txt_CD_AdharCard_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_loan.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_loan = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_loan.toString());
                    waypath_CD_ElectionCard_loan = getFilePath(this, uri_CD_ElectionCard_loan);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_loan);
                    String[] name = waypath_CD_ElectionCard_loan.split("/");
                    txt_CD_ElectionCard_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_loan.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_loan = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_loan.toString());
                    waypath_CD_PassportSize_loan = getFilePath(this, uri_CD_PassportSize_loan);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_loan);
                    String[] name = waypath_CD_PassportSize_loan.split("/");
                    txt_CD_PassportSize_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_loan.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPCheque_loan = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPCheque_loan.toString());
                    waypathUploadDPCheque_loan = getFilePath(this, uriUploadDPCheque_loan);
                    Log.d("Pan Image Uri", waypathUploadDPCheque_loan);
                    String[] name = waypathUploadDPCheque_loan.split("/");
                    txt_DownP_UploadCheque_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_Cheque_loan.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNEFT_RTGS_loan = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNEFT_RTGS_loan.toString());
                    waypathUploadDPNEFT_RTGS_loan = getFilePath(this, uriUploadDPNEFT_RTGS_loan);
                    Log.d("Pan Image Uri", waypathUploadDPNEFT_RTGS_loan);
                    String[] name = waypathUploadDPNEFT_RTGS_loan.split("/");
                    txt_DownP_UploadNEFT_RTGS_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NEFT_RTGS_loan.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNocPhoto_loan = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNocPhoto_loan.toString());
                    waypathUploadDPNocPhoto_loan = getFilePath(this, uriUploadDPNocPhoto_loan);
                    Log.d("Pan Image Uri", waypathUploadDPNocPhoto_loan);
                    String[] name = waypathUploadDPNocPhoto_loan.split("/");
                    txt_DownP_UploadNocPhoto_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NocPhoto_loan.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPOldVehicle_loan = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPOldVehicle_loan.toString());
                    waypathUploadDPOldVehicle_loan = getFilePath(this, uriUploadDPOldVehicle_loan);
                    Log.d("Pan Image Uri", waypathUploadDPOldVehicle_loan);
                    String[] name = waypathUploadDPOldVehicle_loan.split("/");
                    txt_DownP_UploadOldVehicle_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_OldVehicle_loan.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_loan = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_loan.toString());
                    waypathUploadDPRCBook_loan = getFilePath(this, uriUploadDPRCBook_loan);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_loan);
                    String[] name = waypathUploadDPRCBook_loan.split("/");
                    txt_DownP_UploadRCBook_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_loan.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_loan = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_loan.toString());
                    waypathUploadDPElectionPhoto_loan = getFilePath(this, uriUploadDPElectionPhoto_loan);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_loan);
                    String[] name = waypathUploadDPElectionPhoto_loan.split("/");
                    txt_DownP_UploadElectionPhoto_loan.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_loan.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_LoanDetail_BankPassBook = data.getData();
                    Log.d("Pan Image Uri", Uri_LoanDetail_BankPassBook.toString());
                    WayPath_LoanDetail_BankPassBook = getFilePath(this, Uri_LoanDetail_BankPassBook);
                    Log.d("Pan Image Uri", WayPath_LoanDetail_BankPassBook);
                    String[] name = WayPath_LoanDetail_BankPassBook.split("/");
                    txt_LoanDetail_BankPassBook.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 13) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_LoanDetail_Cheque = data.getData();
                    Log.d("Pan Image Uri", Uri_LoanDetail_Cheque.toString());
                    WayPath_LoanDetail_Cheque = getFilePath(this, Uri_LoanDetail_Cheque);
                    Log.d("Pan Image Uri", WayPath_LoanDetail_Cheque);
                    String[] name = WayPath_LoanDetail_Cheque.split("/");
                    txt_LoanDetail_Cheque.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_Cheque.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 14) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_LoanDetail_SarpanchId = data.getData();
                    Log.d("Pan Image Uri", Uri_LoanDetail_SarpanchId.toString());
                    WayPath_LoanDetail_SarpanchId = getFilePath(this, Uri_LoanDetail_SarpanchId);
                    Log.d("Pan Image Uri", WayPath_LoanDetail_SarpanchId);
                    String[] name = WayPath_LoanDetail_SarpanchId.split("/");
                    txt_LoanDetail_SarpanchId.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SarpanchID.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 15) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_LoanDetail_SignatureVeri = data.getData();
                    Log.d("Pan Image Uri", Uri_LoanDetail_SignatureVeri.toString());
                    WayPath_LoanDetail_SignatureVeri = getFilePath(this, Uri_LoanDetail_SignatureVeri);
                    Log.d("Pan Image Uri", WayPath_LoanDetail_SignatureVeri);
                    String[] name = WayPath_LoanDetail_SignatureVeri.split("/");
                    txt_LoanDetail_SignatureVeri.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SignatureVerifi.setImageURI(selectedImageUri);
                }
            }
        }

        // **************************************************************************

        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_loan2 = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_loan2.toString());
                    waypath_CD_AdharCard_loan2 = getFilePath(this, uri_CD_AdharCard_loan2);

                    Log.d("PanRTGS", waypath_CD_AdharCard_loan2);
                    String[] name = waypath_CD_AdharCard_loan2.split("/");
                    txt_CD_AdharCard_loan2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_loan2.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 17) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_loan2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_loan2.toString());
                    waypath_CD_ElectionCard_loan2 = getFilePath(this, uri_CD_ElectionCard_loan2);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_loan2);
                    String[] name = waypath_CD_ElectionCard_loan2.split("/");
                    txt_CD_ElectionCard_loan2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_loan2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 18) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_loan2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_loan2.toString());
                    waypath_CD_PassportSize_loan2 = getFilePath(this, uri_CD_PassportSize_loan2);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_loan2);
                    String[] name = waypath_CD_PassportSize_loan2.split("/");
                    txt_CD_PassportSize_loan2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_loan2.setImageURI(selectedImageUri);
                }
            }
        }

        // ------------------------------------------------------------

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_loan2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_loan2.toString());
                    waypathUploadDPRCBook_loan2 = getFilePath(this, uriUploadDPRCBook_loan2);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_loan2);
                    String[] name = waypathUploadDPRCBook_loan2.split("/");
                    txt_DownP_UploadRCBook_loan2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_loan2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_loan2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_loan2.toString());
                    waypathUploadDPElectionPhoto_loan2 = getFilePath(this, uriUploadDPElectionPhoto_loan2);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_loan2);
                    String[] name = waypathUploadDPElectionPhoto_loan2.split("/");
                    txt_DownP_UploadElectionPhoto_loan2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_loan2.setImageURI(selectedImageUri);
                }
            }
        }

        //----------------------------------------------------------------------
        if (requestCode == 21) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_LoanDetail_BankPassBook2 = data.getData();
                    Log.d("Pan Image Uri", Uri_LoanDetail_BankPassBook2.toString());
                    WayPath_LoanDetail_BankPassBook2 = getFilePath(this, Uri_LoanDetail_BankPassBook2);
                    Log.d("Pan Image Uri", WayPath_LoanDetail_BankPassBook2);
                    String[] name = WayPath_LoanDetail_BankPassBook2.split("/");
                    txt_LoanDetail_BankPassBook2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook2.setImageURI(selectedImageUri);
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