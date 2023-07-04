package com.example.kumarGroup.BookingDeliveryUpload.Passing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import android.provider.DocumentsContract;
import android.provider.MediaStore;
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
import com.example.kumarGroup.DeliveryBookingModel;
import com.example.kumarGroup.PassingDataModel;
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

public class PassingFormActivity extends AppCompatActivity {

    //Argument Details
    EditText edt_ADBooking_BookingNo_Passing,edt_ADBooking_BookingType_Passing,edt_ADBooking_BookingDate_Passing,
            edt_ADBooking_BookingLoginName_Passing;

    TextView txtFillBookingType_Passing,txtPassBookBook_Passing,txtChequeBook_Passing;

    //Customer detail
    EditText edt_CD_Fname_Passing,edt_CD_LastName_Passing,edt_CD_Surname_Passing,edt_CD_MobileNo_Passing,
            edt_CD_WhatsappNo_Passing,
            edt_CD_ReferenceName_Passing,edt_CD_ReferenceNo_Passing,edt_CD_State_Passing,edt_CD_City_Passing,
            edt_CD_District_Passing,
            edt_CD_Taluko_Passing,edt_CD_Village_Passing,edt_CD_EmployeName_Passing,edt_CD_PassBook_Passing,
            edt_CD_ChequeBook_Passing,edt_CD_PaymentOption_Passing;


    ImageView img_CD_Booking_Passing,img_CD_AdharCard_Passing,img_CD_ElectionCard_Passing,img_CD_PassportSize_Passing;

    TextView txt_CD_UploadBookingPhoto_Passing,txt_CD_AdharCard_Passing,txt_CD_ElectionCard_Passing,txt_CD_PassportSize_Passing;

    TextView txt_CD_AdharCard_Passing2,txt_CD_ElectionCard_Passing2,txt_CD_PassportSize_Passing2;

    ImageView img_CD_AdharCard_Passing2,img_CD_ElectionCard_Passing2,img_CD_PassportSize_Passing2;

    //Product detail
    EditText edt_PD_PurchaseName_Passing,edt_PD_ModelName_Passing,edt_PD_Description_Passing;

    //Price detail
    EditText edt_PriceD_price_Passing,edt_PriceD_priceInWord_Passing,edt_PriceD_GST_Passing;

    //RTO Detail
    EditText edt_RTO_RtoMain_Passing,edt_RTO_RtoTax_Passing,edt_RTO_RtoPassing_Passing,
            edt_RTO_Insurance_Passing,edt_RTO_AgentFees_Passing,edt_RTO_NumberPlat_Passing,
            edt_RTO_LoanCharge_Passing;


    TextView txtRTOTax,txtRTOPassing,txtInsurance,txtAgentFees,txtNumberPlat,txtLoanCharge;

    //Down Payment
    EditText  edt_DownP_BookingAmount_Passing,edt_DownP_CashAmount_Passing,
            edt_DownP_BankOption_Passing,edt_DownP_ChequeDate_Passing,edt_DownP_ChequeAmount_Passing,
            edt_DownP_NEFT_RTGSAmount_Passing,edt_DownP_NEFT_RTGS_date_Passing,
            edt_DownP_SelectMake_Passing,edt_DownP_ModelVehicle_Passing,edt_DownP_ManufactureYear_Passing,
            edt_DownP_oldAmount_Passing,edt_DownP_PaperExchange_Passing,edt_DownP_oldTractorAmount_Passing,
            edt_DownP_NOC_Passing;

    ImageView img_DownP_Cheque_Passing,img_DownP_NEFT_RTGS_Passing,img_DownP_NocPhoto_Passing,
            img_DownP_OldVehicle_Passing,img_DownP_RcBook_Passing,img_DownP_ElectionPhoto_Passing;

    ImageView img_DownP_RcBook_Passing2,img_DownP_ElectionPhoto_Passing2;

    TextView txt_DownP_UploadRCBook_Passing2,txt_DownP_UploadElectionPhoto_Passing2;

    TextView txt_DownP_UploadCheque_Passing,txt_DownP_UploadNEFT_RTGS_Passing,txt_DownP_UploadNocPhoto_Passing,
            txt_DownP_UploadOldVehicle_Passing,txt_DownP_UploadRCBook_Passing,txt_DownP_UploadElectionPhoto_Passing;

    //Down payment label
    TextView txtDPCashAmount_Passing,txtDPBankOption_Passing,txtDPChequeDate_Passing,txtDPChequeAmount_Passing,
            txtDPNEFT_RTGSdate_Passing,
            txtDPNEFT_RTGSAmount_Passing,txtDPMake_Passing,txtDPManufectureYear_Passing,
            txtDPOldAmount_Passing,txtDPModelName_Passing,
            txtDPPaperExpense_Passing,txtDPOldTractorAmount_Passing,txtDPNoc_Passing;


    LinearLayout lin_dp_cheque_Passing,lin_dp_NEFT_RTGS_Passing,lin_dp_NocPhoto_Passing,
            lin_dp_OldVehicle_Passing,lin_dp_Rcbook_Passing,
            lin_dp_Election_Passing,lin_dp_Rcbook_Passing2,
            lin_dp_Election_Passing2;

    //Consumer Details
    EditText edt_CS_Hood_Passing,edt_CS_TopLink_Passing,edt_CS_DrawBar_Passing,edt_CS_ToolKit_Passing,edt_CS_Bumper_Passing,
            edt_CS_Hitch_Passing,edt_CS_Description_Passing,edt_CS_ConsumerSkim_Passing;

    //Loan Details
    EditText edt_PassingDetail_REF_Passing,
            edt_PassingDetail_FinanceForm_Passing,edt_PassingDetail_LoanAmount_Passing,
            edt_PassingDetail_LoanSancAmont_Passing,edt_PassingDetail_LoanCharge_Passing,
            edt_PassingDetail_LandDetail_Passing,edt_PassingDetail_CibilScore_Passing,
            edt_PassingDetail_FiDate_Passing,edt_PassingDetail_SanctionDate_Passing,
            edt_PassingDetail_Stage_Passing;

    //  Spinner spn_PassingDetail_FinanceForm_Passing,spn_PassingDetail_Stage_Passing;

    ImageView img_LoanDetail_BankpassBook_Passing,img_LoanDetail_Cheque_Passing,img_LoanDetail_SarpanchID_Passing,
            img_LoanDetail_SignatureVerifi_Passing;

    ImageView img_LoanDetail_BankpassBook_Passing2;

    TextView txt_LoanDetail_BankPassBook_Passing2;

    TextView txt_LoanDetail_BankPassBook_Passing,txt_LoanDetail_Cheque_Passing,txt_LoanDetail_SarpanchId_Passing,
            txt_LoanDetail_SignatureVeri_Passing;


    //Delivery Details
    EditText edt_modelName_Passing,edt_ChesisNumber_Passing,edt_EngineNumber_Passing,edt_Variente_Passing,
            edt_Type_Passing,edt_Bettry_Passing,edt_PassingDate_Passing;

    TextView txt_Tyre_Passing,txt_Battery_Passing;

    ImageView img_PassingPhoto_Passing,img_ChesisPrint_Passing;

    TextView txt_PassingPhoto_Passing,txt_ChesisPrint_Passing;


    //Cash Loan
    EditText edt_CashDetail_Date_Passing,edt_CashDetail_Amount_Passing,edt_CashDetail_Description_Passing;

    LinearLayout lin_cashLoan_Passing,lin_ConsumerSkim_passing,lin_Loan_Passing_form;


    //***************************************************************************************************

    String WayPath_LoanDetail_BankPassBook,WayPath_LoanDetail_Cheque,WayPath_LoanDetail_SarpanchId,
            WayPath_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook,Uri_LoanDetail_Cheque,Uri_LoanDetail_SarpanchId,
            Uri_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook2;
    String WayPath_LoanDetail_BankPassBook2;

    Uri uri_PassingPhoto_Passing,uri_ChesisPrint_Passing;

    String Waypath_PassingPhoto_Passing,Waypath_ChesisPrint_Passing;

    Button btn_Passing_Submit;


    String Stage;
    String[] Satge_data = {"Pending", "Fi Done","Login Pending","Login Done","CIBIL Check","sanction","Reject"};



    Spinner spn_PassingDetail_stageloan;

    String StageFinalVal;

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


    Uri uri_CD_UploadBookingPhoto_Passing,uri_CD_AdharCard_Passing,uri_CD_ElectionCard_Passing,uri_CD_PassportSize_Passing;

    Uri uri_CD_AdharCard_Passing2,uri_CD_ElectionCard_Passing2,uri_CD_PassportSize_Passing2;

    String waypath_CD_AdharCard_Passing2,waypath_CD_ElectionCard_Passing2, waypath_CD_PassportSize_Passing2;

    String waypath_CD_UploadBookingPhoto_Passing,waypath_CD_AdharCard_Passing,waypath_CD_ElectionCard_Passing,
            waypath_CD_PassportSize_Passing;

    Uri uriUploadDPCheque_Passing, uriUploadDPNEFT_RTGS_Passing, uriUploadDPNocPhoto_Passing,
            uriUploadDPOldVehicle_Passing, uriUploadDPRCBook_Passing,
            uriUploadDPElectionPhoto_Passing;

    Uri uriUploadDPRCBook_Passing2, uriUploadDPElectionPhoto_Passing2;

    String  waypathUploadDPRCBook_Passing2, waypathUploadDPElectionPhoto_Passing2;

    String waypathUploadDPCheque_Passing, waypathUploadDPNEFT_RTGS_Passing, waypathUploadDPNocPhoto_Passing,
            waypathUploadDPOldVehicle_Passing, waypathUploadDPRCBook_Passing, waypathUploadDPElectionPhoto_Passing;


    String id_item;

    int id_pos;

    String mydata;

    List<String> Y_N = Arrays.asList(new String[]{"Select Option","Yes", "No"});

    Spinner spn_number_plate_order,spn_number_plate_Recive,spn_RC_Book_Update;
    String number_plate_order,number_plate_Recive,RC_Book_Update;
    EditText edt_registration_number;


    SwipeRefreshLayout swipeRefreshLayoutPassing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_form);

        getSupportActionBar().setTitle("Passing Form");

        swipeRefreshLayoutPassing=findViewById(R.id.swipeRefreshLayoutPassing);


        //Agreement Details
        edt_ADBooking_BookingNo_Passing = findViewById(R.id.edt_ADBooking_BookingNo_Passing);
        edt_ADBooking_BookingType_Passing = findViewById(R.id.edt_ADBooking_BookingType_Passing);
        edt_ADBooking_BookingDate_Passing = findViewById(R.id.edt_ADBooking_BookingDate_Passing);
        edt_ADBooking_BookingLoginName_Passing = findViewById(R.id.edt_ADBooking_BookingLoginName_Passing);


        txtFillBookingType_Passing = findViewById(R.id.txtFillBookingType_Passing);
        txtPassBookBook_Passing = findViewById(R.id.txtPassBookBook_Passing);
        txtChequeBook_Passing = findViewById(R.id.txtChequeBook_Passing);

        //Customer Detail
        edt_CD_Fname_Passing   = findViewById(R.id.edt_CD_Fname_Passing);
        edt_CD_LastName_Passing = findViewById(R.id.edt_CD_LastName_Passing);
        edt_CD_Surname_Passing = findViewById(R.id.edt_CD_Surname_Passing);
        edt_CD_MobileNo_Passing = findViewById(R.id.edt_CD_MobileNo_Passing);
        edt_CD_WhatsappNo_Passing = findViewById(R.id.edt_CD_WhatsappNo_Passing);
        edt_CD_ReferenceName_Passing = findViewById(R.id.edt_CD_ReferenceName_Passing);
        edt_CD_ReferenceNo_Passing = findViewById(R.id.edt_CD_ReferenceNo_Passing);
        edt_CD_State_Passing = findViewById(R.id.edt_CD_State_Passing);
        edt_CD_City_Passing = findViewById(R.id.edt_CD_City_Passing);
        edt_CD_District_Passing = findViewById(R.id.edt_CD_District_Passing);
        edt_CD_Taluko_Passing = findViewById(R.id.edt_CD_Taluko_Passing);
        edt_CD_Village_Passing = findViewById(R.id.edt_CD_Village_Passing);
        edt_CD_EmployeName_Passing = findViewById(R.id.edt_CD_EmployeName_Passing);
        edt_CD_PassBook_Passing = findViewById(R.id.edt_CD_PassBook_Passing);
        edt_CD_ChequeBook_Passing = findViewById(R.id.edt_CD_ChequeBook_Passing);
        edt_CD_PaymentOption_Passing = findViewById(R.id.edt_CD_PaymentOption_Passing);

        txt_CD_UploadBookingPhoto_Passing =findViewById(R.id.txt_CD_UploadBookingPhoto_Passing);
        txt_CD_AdharCard_Passing =findViewById(R.id.txt_CD_AdharCard_Passing);
        txt_CD_ElectionCard_Passing =findViewById(R.id.txt_CD_ElectionCard_Passing);
      /*  txt_CD_BankPassBook =findViewById(R.id.txt_CD_BankPassBook);
        txt_CD_Cheque =findViewById(R.id.txt_CD_Cheque);*/
        txt_CD_PassportSize_Passing =findViewById(R.id.txt_CD_PassportSize_Passing);

        txt_CD_AdharCard_Passing2 =findViewById(R.id.txt_CD_AdharCard_Passing2);
        txt_CD_ElectionCard_Passing2 =findViewById(R.id.txt_CD_ElectionCard_Passing2);
        txt_CD_PassportSize_Passing2 =findViewById(R.id.txt_CD_PassportSize_Passing2);

        img_CD_Booking_Passing =findViewById(R.id.img_CD_Booking_Passing);
        img_CD_AdharCard_Passing =findViewById(R.id.img_CD_AdharCard_Passing);
        img_CD_ElectionCard_Passing =findViewById(R.id.img_CD_ElectionCard_Passing);
       /* img_CD_BankPassBook=findViewById(R.id.img_CD_BankPassBook);
        img_CD_Cheque=findViewById(R.id.img_CD_Cheque);*/
        img_CD_PassportSize_Passing =findViewById(R.id.img_CD_PassportSize_Passing);

        img_CD_AdharCard_Passing2 =findViewById(R.id.img_CD_AdharCard_Passing2);
        img_CD_ElectionCard_Passing2 =findViewById(R.id.img_CD_ElectionCard_Passing2);
        img_CD_PassportSize_Passing2 =findViewById(R.id.img_CD_PassportSize_Passing2);

        //Product Details
        edt_PD_PurchaseName_Passing =findViewById(R.id.edt_PD_PurchaseName_Passing);
        edt_PD_ModelName_Passing =findViewById(R.id.edt_PD_ModelName_Passing);
        edt_PD_Description_Passing =findViewById(R.id.edt_PD_Description_Passing);


        //Price Details
        edt_PriceD_price_Passing =findViewById(R.id.edt_PriceD_price_Passing);
        edt_PriceD_priceInWord_Passing =findViewById(R.id.edt_PriceD_priceInWord_Passing);
        edt_PriceD_GST_Passing =findViewById(R.id.edt_PriceD_GST_Passing);

        //RTO Details
        edt_RTO_RtoMain_Passing =findViewById(R.id.edt_RTO_RtoMain_Passing);
        edt_RTO_RtoTax_Passing =findViewById(R.id.edt_RTO_RtoTax_Passing);
        edt_RTO_RtoPassing_Passing =findViewById(R.id.edt_RTO_RtoPassing_Passing);
        edt_RTO_Insurance_Passing =findViewById(R.id.edt_RTO_Insurance_Passing);
        edt_RTO_AgentFees_Passing =findViewById(R.id.edt_RTO_AgentFees_Passing);
        edt_RTO_NumberPlat_Passing =findViewById(R.id.edt_RTO_NumberPlat_Passing);
        edt_RTO_LoanCharge_Passing =findViewById(R.id.edt_RTO_LoanCharge_Passing);


        txtRTOTax= findViewById(R.id.txtRTOTax);
        txtRTOPassing= findViewById(R.id.txtRTOPassing);
        txtInsurance= findViewById(R.id.txtInsurance);
        txtAgentFees= findViewById(R.id.txtAgentFees);
        txtNumberPlat= findViewById(R.id.txtNumberPlat);
        txtLoanCharge= findViewById(R.id.txtLoanCharge);


        //Down Payment
        edt_DownP_BookingAmount_Passing =findViewById(R.id.edt_DownP_BookingAmount_Passing);
        edt_DownP_CashAmount_Passing =findViewById(R.id.edt_DownP_CashAmount_Passing);
        edt_DownP_BankOption_Passing =findViewById(R.id.edt_DownP_BankOption_Passing);
        edt_DownP_ChequeDate_Passing =findViewById(R.id.edt_DownP_ChequeDate_Passing);
        edt_DownP_ChequeAmount_Passing =findViewById(R.id.edt_DownP_ChequeAmount_Passing);
        edt_DownP_NEFT_RTGS_date_Passing =findViewById(R.id.edt_DownP_NEFT_RTGS_date_Passing);
        edt_DownP_NEFT_RTGSAmount_Passing =findViewById(R.id.edt_DownP_NEFT_RTGSAmount_Passing);
        edt_DownP_SelectMake_Passing =findViewById(R.id.edt_DownP_SelectMake_Passing);
        edt_DownP_ModelVehicle_Passing =findViewById(R.id.edt_DownP_ModelVehicle_Passing);
        edt_DownP_oldAmount_Passing =findViewById(R.id.edt_DownP_oldAmount_Passing);
        edt_DownP_ManufactureYear_Passing =findViewById(R.id.edt_DownP_ManufactureYear_Passing);
        edt_DownP_PaperExchange_Passing =findViewById(R.id.edt_DownP_PaperExchange_Passing);
        edt_DownP_oldTractorAmount_Passing =findViewById(R.id.edt_DownP_oldTractorAmount_Passing);
        edt_DownP_NOC_Passing =findViewById(R.id.edt_DownP_NOC_Passing);


        img_DownP_Cheque_Passing = findViewById(R.id.img_DownP_Cheque_Passing);
        img_DownP_NEFT_RTGS_Passing = findViewById(R.id.img_DownP_NEFT_RTGS_Passing);
        img_DownP_NocPhoto_Passing = findViewById(R.id.img_DownP_NocPhoto_Passing);
        img_DownP_OldVehicle_Passing = findViewById(R.id.img_DownP_OldVehicle_Passing);
        img_DownP_RcBook_Passing = findViewById(R.id.img_DownP_RcBook_Passing);
        img_DownP_ElectionPhoto_Passing = findViewById(R.id.img_DownP_ElectionPhoto_Passing);

        img_DownP_RcBook_Passing2 = findViewById(R.id.img_DownP_RcBook_Passing2);
        img_DownP_ElectionPhoto_Passing2 = findViewById(R.id.img_DownP_ElectionPhoto_Passing2);

        txt_DownP_UploadRCBook_Passing2 =findViewById(R.id.txt_DownP_UploadRCBook_Passing2);
        txt_DownP_UploadElectionPhoto_Passing2 =findViewById(R.id.txt_DownP_UploadElectionPhoto_Passing2);

        txt_DownP_UploadCheque_Passing =findViewById(R.id.txt_DownP_UploadCheque_Passing);
        txt_DownP_UploadNEFT_RTGS_Passing =findViewById(R.id.txt_DownP_UploadNEFT_RTGS_Passing);
        txt_DownP_UploadNocPhoto_Passing =findViewById(R.id.txt_DownP_UploadNocPhoto_Passing);
        txt_DownP_UploadOldVehicle_Passing =findViewById(R.id.txt_DownP_UploadOldVehicle_Passing);
        txt_DownP_UploadRCBook_Passing =findViewById(R.id.txt_DownP_UploadRCBook_Passing);
        txt_DownP_UploadElectionPhoto_Passing =findViewById(R.id.txt_DownP_UploadElectionPhoto_Passing);

        //Label Textview
        txtDPCashAmount_Passing =findViewById(R.id.txtDPCashAmount_Passing);
        txtDPBankOption_Passing =findViewById(R.id.txtDPBankOption_Passing);
        txtDPChequeDate_Passing =findViewById(R.id.txtDPChequeDate_Passing);
        txtDPChequeAmount_Passing =findViewById(R.id.txtDPChequeAmount_Passing);
        txtDPNEFT_RTGSdate_Passing =findViewById(R.id.txtDPNEFT_RTGSdate_Passing);
        txtDPNEFT_RTGSAmount_Passing =findViewById(R.id.txtDPNEFT_RTGSAmount_Passing);
        txtDPMake_Passing =findViewById(R.id.txtDPMake_Passing);
        txtDPManufectureYear_Passing=findViewById(R.id.txtDPManufectureYear_Passing);
        txtDPOldAmount_Passing =findViewById(R.id.txtDPOldAmount_Passing);
        txtDPModelName_Passing =findViewById(R.id.txtDPModelName_Passing);
        txtDPPaperExpense_Passing =findViewById(R.id.txtDPPaperExpense_Passing);
        txtDPOldTractorAmount_Passing =findViewById(R.id.txtDPOldTractorAmount_Passing);
        txtDPNoc_Passing =findViewById(R.id.txtDPNoc_Passing);


        lin_dp_cheque_Passing =findViewById(R.id.lin_dp_cheque_Passing);
        lin_dp_NEFT_RTGS_Passing =findViewById(R.id.lin_dp_NEFT_RTGS_Passing);
        lin_dp_NocPhoto_Passing =findViewById(R.id.lin_dp_NocPhoto_Passing);
        lin_dp_OldVehicle_Passing =findViewById(R.id.lin_dp_OldVehicle_Passing);
        lin_dp_Rcbook_Passing =findViewById(R.id.lin_dp_Rcbook_Passing);
        lin_dp_Election_Passing =findViewById(R.id.lin_dp_Election_Passing);
        lin_dp_Rcbook_Passing2 =findViewById(R.id.lin_dp_Rcbook_Passing2);
        lin_dp_Election_Passing2 =findViewById(R.id.lin_dp_Election_Passing2);

        //Consumer Skim
        edt_CS_Hood_Passing =findViewById(R.id.edt_CS_Hood_Passing);
        edt_CS_TopLink_Passing =findViewById(R.id.edt_CS_TopLink_Passing);
        edt_CS_DrawBar_Passing=findViewById(R.id.edt_CS_DrawBar_Passing);
        edt_CS_ToolKit_Passing=findViewById(R.id.edt_CS_ToolKit_Passing);
        edt_CS_Bumper_Passing=findViewById(R.id.edt_CS_Bumper_Passing);
        edt_CS_Hitch_Passing=findViewById(R.id.edt_CS_Hitch_Passing);
        edt_CS_Description_Passing=findViewById(R.id.edt_CS_Description_Passing);
        edt_CS_ConsumerSkim_Passing=findViewById(R.id.edt_CS_ConsumerSkim_Passing);

        //Loan Detail Form

        edt_PassingDetail_REF_Passing= findViewById(R.id.edt_PassingDetail_REF_Passing);
        edt_PassingDetail_FinanceForm_Passing= findViewById(R.id.edt_PassingDetail_FinanceForm_Passing);
        edt_PassingDetail_LoanAmount_Passing= findViewById(R.id.edt_PassingDetail_LoanAmount_Passing);
        edt_PassingDetail_LoanSancAmont_Passing= findViewById(R.id.edt_PassingDetail_LoanSancAmont_Passing);
        edt_PassingDetail_LoanCharge_Passing= findViewById(R.id.edt_PassingDetail_LoanCharge_Passing);
        edt_PassingDetail_LandDetail_Passing= findViewById(R.id.edt_PassingDetail_LandDetail_Passing);
        edt_PassingDetail_CibilScore_Passing= findViewById(R.id.edt_PassingDetail_CibilScore_Passing);
        edt_PassingDetail_FiDate_Passing= findViewById(R.id.edt_PassingDetail_FiDate_Passing);
        edt_PassingDetail_SanctionDate_Passing = findViewById(R.id.edt_PassingDetail_SanctionDate_Passing);

        /*spn_PassingDetail_FinanceForm_Passing= findViewById(R.id.spn_PassingDetail_FinanceForm_Passing);
        spn_PassingDetail_Stage_Passing= findViewById(R.id.spn_PassingDetail_Stage_Passing);*/

        edt_PassingDetail_Stage_Passing = findViewById(R.id.edt_PassingDetail_Stage_Passing);
        spn_PassingDetail_stageloan= findViewById(R.id.spn_PassingDetail_stageloan);

        img_LoanDetail_BankpassBook_Passing2 = findViewById(R.id.img_LoanDetail_BankpassBook_Passing2);
        txt_LoanDetail_BankPassBook_Passing2 = findViewById(R.id.txt_LoanDetail_BankPassBook_Passing2);


        img_LoanDetail_BankpassBook_Passing= findViewById(R.id.img_LoanDetail_BankpassBook_Passing);
        img_LoanDetail_Cheque_Passing= findViewById(R.id.img_LoanDetail_Cheque_Passing);
        img_LoanDetail_SarpanchID_Passing= findViewById(R.id.img_LoanDetail_SarpanchID_Passing);
        img_LoanDetail_SignatureVerifi_Passing= findViewById(R.id.img_LoanDetail_SignatureVerifi_Passing);


        txt_LoanDetail_BankPassBook_Passing= findViewById(R.id.txt_LoanDetail_BankPassBook_Passing);
        txt_LoanDetail_Cheque_Passing= findViewById(R.id.txt_LoanDetail_Cheque_Passing);
        txt_LoanDetail_SarpanchId_Passing= findViewById(R.id.txt_LoanDetail_SarpanchId_Passing);
        txt_LoanDetail_SignatureVeri_Passing= findViewById(R.id.txt_LoanDetail_SignatureVeri_Passing);


        //Delivery Details
     /*   edt_modelName_Passing = findViewById(R.id.edt_modelName_Passing);
        edt_ChesisNumber_Passing = findViewById(R.id.edt_ChesisNumber_Passing);
        edt_EngineNumber_Passing = findViewById(R.id.edt_EngineNumber_Passing);
        edt_Variente_Passing = findViewById(R.id.edt_Variente_Passing);*/

        edt_Type_Passing = findViewById(R.id.edt_Type_Passing);
        txt_Tyre_Passing = findViewById(R.id.txt_Tyre_Passing);
        txt_Battery_Passing = findViewById(R.id.txt_Battery_Passing);
        edt_Bettry_Passing = findViewById(R.id.edt_Bettry_Passing);
        edt_PassingDate_Passing = findViewById(R.id.edt_PassingDate_Passing);

        img_PassingPhoto_Passing = findViewById(R.id.img_PassingPhoto_Passing);
        img_ChesisPrint_Passing = findViewById(R.id.img_ChesisPrint_Passing);

        txt_PassingPhoto_Passing = findViewById(R.id.txt_PassingPhoto_Passing);
        txt_ChesisPrint_Passing = findViewById(R.id.txt_ChesisPrint_Passing);


        edt_CashDetail_Date_Passing= findViewById(R.id.edt_CashDetail_Date_Passing);
        edt_CashDetail_Amount_Passing = findViewById(R.id.edt_CashDetail_Amount_Passing);
        edt_CashDetail_Description_Passing = findViewById(R.id.edt_CashDetail_Description_Passing);

        lin_cashLoan_Passing = findViewById(R.id.lin_cashLoan_Passing);
        lin_ConsumerSkim_passing = findViewById(R.id.lin_ConsumerSkim_passing);
        lin_Loan_Passing_form = findViewById(R.id.lin_Loan_Passing_form);

        btn_Passing_Submit=findViewById(R.id.btn_Passing_Submit);

        //Passing Detail
        edt_registration_number = findViewById(R.id.edt_registration_number);
        spn_number_plate_order = findViewById(R.id.spn_number_plate_order);
        spn_number_plate_Recive = findViewById(R.id.spn_number_plate_Recive);
        spn_RC_Book_Update = findViewById(R.id.spn_RC_Book_Update);


        /** ********************************************************************************************************** */

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");


        idBookingUpload = getIntent().getStringExtra("idBookingUpload");


        id_item = getIntent().getStringExtra("position");

        id_pos= Integer.parseInt(id_item);

        //  Toast.makeText(DeliveryFormActivity.this, ""+id_item+" "+idBookingUpload, Toast.LENGTH_SHORT).show();

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);


        progressDialog= new ProgressDialog(PassingFormActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getPassingData(emp).enqueue(new Callback<PassingDataModel>() {
            @Override
            public void onResponse(@NotNull Call<PassingDataModel> call, @NotNull Response<PassingDataModel> response) {
                progressDialog.dismiss();

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getB_photo())
                        .into(img_CD_Booking_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getAdhar_photo())
                        .into(img_CD_AdharCard_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getEcard_photo())
                        .into(img_CD_ElectionCard_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getP_photo())
                        .into(img_CD_PassportSize_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getCheck1_photo())
                        .into(img_DownP_Cheque_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getNeft_rtgs_photo())
                        .into(img_DownP_NEFT_RTGS_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getNoc_photo())
                        .into(img_DownP_NocPhoto_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getOld_vehicle_photo())
                        .into(img_DownP_OldVehicle_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getRecbook_photo())
                        .into(img_DownP_RcBook_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getEc_photo())
                        .into(img_DownP_ElectionPhoto_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getLb_pb_photo())
                        .into(img_LoanDetail_BankpassBook_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getL_c_photo())
                        .into(img_LoanDetail_Cheque_Passing);


                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getSar_id_photo())
                        .into(img_LoanDetail_SarpanchID_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getSign_veri())
                        .into(img_LoanDetail_SignatureVerifi_Passing);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getDelivry_photo())
                        .into(img_PassingPhoto_Passing);


                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getChasis_print())
                        .into(img_ChesisPrint_Passing);


                // -------------------------------------------------------------------
                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getAdhar_back())
                        .into(img_CD_AdharCard_Passing2);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getElection_back())
                        .into(img_CD_ElectionCard_Passing2);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getP_photo_back())
                        .into(img_CD_PassportSize_Passing2);


                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getRcbook_back())
                        .into(img_DownP_RcBook_Passing2);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getElec_back())
                        .into(img_DownP_ElectionPhoto_Passing2);

                Glide.with(PassingFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getB_pass_back())
                        .into(img_LoanDetail_BankpassBook_Passing2);

                //-----------------------------------------------------------




                //----------------------------------------------------------------------


                emp_id = response.body().getData().get(id_pos).getEmp_id();



                if(response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                        response.body().getData().get(id_pos).getProduct_name().equals("Spar part"))
                {
                    edt_ADBooking_BookingType_Passing.setVisibility(View.GONE);
                    edt_CD_PassBook_Passing.setVisibility(View.GONE);
                    edt_CD_ChequeBook_Passing.setVisibility(View.GONE);
                    txtFillBookingType_Passing.setVisibility(View.GONE);
                    txtPassBookBook_Passing.setVisibility(View.GONE);
                    txtChequeBook_Passing.setVisibility(View.GONE);


                    txt_CD_UploadBookingPhoto_Passing.setVisibility(View.GONE);
                    txt_CD_AdharCard_Passing.setVisibility(View.GONE);
                    txt_CD_ElectionCard_Passing.setVisibility(View.GONE);
                    txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                    txt_CD_AdharCard_Passing2.setVisibility(View.GONE);
                    txt_CD_ElectionCard_Passing2.setVisibility(View.GONE);
                    txt_CD_PassportSize_Passing2.setVisibility(View.GONE);

                    img_CD_Booking_Passing.setVisibility(View.GONE);
                    img_CD_AdharCard_Passing.setVisibility(View.GONE);
                    img_CD_ElectionCard_Passing.setVisibility(View.GONE);
                    img_CD_PassportSize_Passing.setVisibility(View.GONE);
                    img_CD_AdharCard_Passing2.setVisibility(View.GONE);
                    img_CD_ElectionCard_Passing2.setVisibility(View.GONE);
                    img_CD_PassportSize_Passing2.setVisibility(View.GONE);

                    txt_Tyre_Passing.setVisibility(View.GONE);
                    edt_Type_Passing.setVisibility(View.GONE);
                    edt_Bettry_Passing.setVisibility(View.GONE);
                    txt_Battery_Passing.setVisibility(View.GONE);

                }
                else{
                    edt_ADBooking_BookingType_Passing.setVisibility(View.VISIBLE);
                    edt_CD_PassBook_Passing.setVisibility(View.VISIBLE);
                    edt_CD_ChequeBook_Passing.setVisibility(View.VISIBLE);
                    txtFillBookingType_Passing.setVisibility(View.VISIBLE);
                    txtPassBookBook_Passing.setVisibility(View.VISIBLE);
                    txtChequeBook_Passing.setVisibility(View.VISIBLE);

                    txt_Tyre_Passing.setVisibility(View.VISIBLE);
                    edt_Type_Passing.setVisibility(View.VISIBLE);
                    edt_Bettry_Passing.setVisibility(View.VISIBLE);
                    txt_Battery_Passing.setVisibility(View.VISIBLE);

                    //------------------------------------------------------

                    if(response.body().getData().get(id_pos).getTyre_check().equals("0")){
                        edt_Type_Passing.setFocusable(true);
                    }else{
                        edt_Type_Passing.setFocusable(false);
                        edt_Type_Passing.setTextColor(Color.parseColor("#43a047"));
                    }

                    if(response.body().getData().get(id_pos).getBattery_check().equals("0")){
                        edt_Bettry_Passing.setFocusable(true);
                    }else{
                        edt_Bettry_Passing.setFocusable(false);
                        edt_Bettry_Passing.setTextColor(Color.parseColor("#43a047"));
                    }

                    //------------------------------------------------------


                    if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                        txt_CD_UploadBookingPhoto_Passing.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_UploadBookingPhoto_Passing.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                        txt_CD_AdharCard_Passing.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_AdharCard_Passing.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                        txt_CD_ElectionCard_Passing.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_ElectionCard_Passing.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                        txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                        txt_CD_AdharCard_Passing2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_AdharCard_Passing2.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getElection_back_check().equals("0")){
                        txt_CD_ElectionCard_Passing2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_ElectionCard_Passing2.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                        txt_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize_Passing2.setVisibility(View.GONE);
                    }

                }

                if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                    img_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);
                    txt_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);

                    img_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                    txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);

                    if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                    }

                } else {
                    txt_CD_PassportSize_Passing2.setVisibility(View.GONE);
                    img_CD_PassportSize_Passing2.setVisibility(View.GONE);

                    txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                    img_CD_PassportSize_Passing.setVisibility(View.GONE);

                   /* if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                    }*/
                }



                //------------------------------------------------------------------------

                if(response.body().getData().get(id_pos).getBno_check().equals("0")){
                    edt_ADBooking_BookingNo_Passing.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingNo_Passing.setFocusable(false);
                    edt_ADBooking_BookingNo_Passing.setTextColor(Color.parseColor("#43a047"));
                }



                if(response.body().getData().get(id_pos).getB_date_check().equals("0")){
                    edt_ADBooking_BookingDate_Passing.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingDate_Passing.setFocusable(false);
                    edt_ADBooking_BookingDate_Passing.setTextColor(Color.parseColor("#43a047"));
                }



                if(response.body().getData().get(id_pos).getB_type_check().equals("0")){
                    edt_ADBooking_BookingType_Passing.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingType_Passing.setFocusable(false);
                    edt_ADBooking_BookingType_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getEmp_check().equals("0")){
                    edt_ADBooking_BookingLoginName_Passing.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingLoginName_Passing.setFocusable(false);
                    edt_ADBooking_BookingLoginName_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getFname_check().equals("0")){
                    edt_CD_Fname_Passing.setFocusable(true);
                }
                else{
                    edt_CD_Fname_Passing.setFocusable(false);
                    edt_CD_Fname_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLname_check().equals("0")){
                    edt_CD_LastName_Passing.setFocusable(true);
                }
                else{
                    edt_CD_LastName_Passing.setFocusable(false);
                    edt_CD_LastName_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getSname_check().equals("0")){
                    edt_CD_Surname_Passing.setFocusable(true);
                }
                else{
                    edt_CD_Surname_Passing.setFocusable(false);
                    edt_CD_Surname_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getMobileno_check().equals("0")){
                    edt_CD_MobileNo_Passing.setFocusable(true);
                }
                else{
                    edt_CD_MobileNo_Passing.setFocusable(false);
                    edt_CD_MobileNo_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getWhno_check().equals("0")){
                    edt_CD_WhatsappNo_Passing.setFocusable(true);
                }else{
                    edt_CD_WhatsappNo_Passing.setFocusable(false);
                    edt_CD_WhatsappNo_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getRef_name_check().equals("0")){
                    edt_CD_ReferenceName_Passing.setFocusable(true);
                }
                else{
                    edt_CD_ReferenceName_Passing.setFocusable(false);
                    edt_CD_ReferenceName_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRef_no_check().equals("0")){
                    edt_CD_ReferenceNo_Passing.setFocusable(true);
                }
                else{
                    edt_CD_ReferenceNo_Passing.setFocusable(false);
                    edt_CD_ReferenceNo_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getState_check().equals("0")){
                    edt_CD_State_Passing.setFocusable(true);
                }
                else{
                    edt_CD_State_Passing.setFocusable(false);
                    edt_CD_State_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCity_check().equals("0")){
                    edt_CD_City_Passing.setFocusable(true);
                }
                else{
                    edt_CD_City_Passing.setFocusable(false);
                    edt_CD_City_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDistric_check().equals("0")){
                    edt_CD_District_Passing.setFocusable(true);
                }
                else{
                    edt_CD_District_Passing.setFocusable(false);
                    edt_CD_District_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getVillage_check().equals("0")){
                    edt_CD_Village_Passing.setFocusable(true);
                }
                else{
                    edt_CD_Village_Passing.setFocusable(false);
                    edt_CD_Village_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getEmp_check().equals("0")){
                    edt_CD_EmployeName_Passing.setFocusable(true);
                }
                else{
                    edt_CD_EmployeName_Passing.setFocusable(false);
                    edt_CD_EmployeName_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getTehsill_check().equals("0")){
                    edt_CD_Taluko_Passing.setFocusable(true);
                }
                else{
                    edt_CD_Taluko_Passing.setFocusable(false);
                    edt_CD_Taluko_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getProduct_name_check().equals("0")){
                    edt_PD_PurchaseName_Passing.setFocusable(true);
                }
                else{
                    edt_PD_PurchaseName_Passing.setFocusable(false);
                    edt_PD_PurchaseName_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getModel_name_check().equals("0")){
                    edt_PD_ModelName_Passing.setFocusable(true);
                }
                else{
                    edt_PD_ModelName_Passing.setFocusable(false);
                    edt_PD_ModelName_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getP_desc_check().equals("0")){
                    edt_PD_Description_Passing.setFocusable(true);
                }
                else{
                    edt_PD_Description_Passing.setFocusable(false);
                    edt_PD_Description_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDeal_price_check().equals("0")){
                    edt_PriceD_price_Passing.setFocusable(true);
                }
                else{
                    edt_PriceD_price_Passing.setFocusable(false);
                    edt_PriceD_price_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")){
                    edt_PriceD_priceInWord_Passing.setFocusable(true);
                }
                else{
                    edt_PriceD_priceInWord_Passing.setFocusable(false);
                    edt_PriceD_priceInWord_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getGst_check().equals("0")){
                    edt_PriceD_GST_Passing.setFocusable(true);
                }
                else{
                    edt_PriceD_GST_Passing.setFocusable(false);
                    edt_PriceD_GST_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRto_check().equals("0")){
                    edt_RTO_RtoMain_Passing.setFocusable(true);
                }
                else{
                    edt_RTO_RtoMain_Passing.setFocusable(false);
                    edt_RTO_RtoMain_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRto_tax_check().equals("0")){
                    edt_RTO_RtoTax_Passing.setFocusable(true);
                }
                else{
                    edt_RTO_RtoTax_Passing.setFocusable(false);
                    edt_RTO_RtoTax_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getRto_passing_check().equals("0")){
                    edt_RTO_RtoPassing_Passing.setFocusable(true);
                }
                else{
                    edt_RTO_RtoPassing_Passing.setFocusable(false);
                    edt_RTO_RtoPassing_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getInsurance_check().equals("0")){
                    edt_RTO_Insurance_Passing.setFocusable(true);
                }
                else{
                    edt_RTO_Insurance_Passing.setFocusable(false);
                    edt_RTO_Insurance_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAgent_fee_check().equals("0")){
                    edt_RTO_AgentFees_Passing.setFocusable(true);
                }
                else{
                    edt_RTO_AgentFees_Passing.setFocusable(false);
                    edt_RTO_AgentFees_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNumber_plat_check().equals("0")){
                    edt_RTO_NumberPlat_Passing.setFocusable(true);
                }
                else{
                    edt_RTO_NumberPlat_Passing.setFocusable(false);
                    edt_RTO_NumberPlat_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLoan_charge_check().equals("0")){
                    edt_RTO_LoanCharge_Passing.setFocusable(true);
                }
                else{
                    edt_RTO_LoanCharge_Passing.setFocusable(false);
                    edt_RTO_LoanCharge_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getBooking_amt_check().equals("0")){
                    edt_DownP_BookingAmount_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_BookingAmount_Passing.setFocusable(false);
                    edt_DownP_BookingAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAmount_check().equals("0")){
                    edt_DownP_CashAmount_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_CashAmount_Passing.setFocusable(false);
                    edt_DownP_CashAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCheck_neft_rtgs_check().equals("0")){
                    edt_DownP_BankOption_Passing.setFocusable(true);
                }
                else {
                   edt_DownP_BankOption_Passing.setFocusable(false);
                   edt_DownP_BankOption_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCheck_date_check().equals("0")){
                    edt_DownP_ChequeDate_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_ChequeDate_Passing.setFocusable(false);
                    edt_DownP_ChequeDate_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getC_amount_check().equals("0")){
                    edt_DownP_ChequeAmount_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_ChequeAmount_Passing.setFocusable(false);
                    edt_DownP_ChequeAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")){
                    edt_DownP_NEFT_RTGS_date_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_NEFT_RTGS_date_Passing.setFocusable(false);
                    edt_DownP_NEFT_RTGS_date_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")){
                    edt_DownP_NEFT_RTGSAmount_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_NEFT_RTGSAmount_Passing.setFocusable(false);
                    edt_DownP_NEFT_RTGSAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getMake_check().equals("0")){
                    edt_DownP_SelectMake_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_SelectMake_Passing.setFocusable(false);
                    edt_DownP_SelectMake_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getModel_check().equals("0")){
                    edt_DownP_ModelVehicle_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_ModelVehicle_Passing.setFocusable(false);
                    edt_DownP_ModelVehicle_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")){
                    edt_DownP_oldAmount_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_oldAmount_Passing.setFocusable(false);
                    edt_DownP_oldAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getM_year_check().equals("0")){
                    edt_DownP_ManufactureYear_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_ManufactureYear_Passing.setFocusable(false);
                    edt_DownP_ManufactureYear_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getPaper_expence_check().equals("0")){
                    edt_DownP_PaperExchange_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_PaperExchange_Passing.setFocusable(false);
                    edt_DownP_PaperExchange_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")){
                    edt_DownP_oldTractorAmount_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_oldTractorAmount_Passing.setFocusable(false);
                    edt_DownP_oldTractorAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNoc_check().equals("0")){
                    edt_DownP_NOC_Passing.setFocusable(true);
                }
                else{
                    edt_DownP_NOC_Passing.setFocusable(false);
                    edt_DownP_NOC_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getHood_check().equals("0")){
                    edt_CS_Hood_Passing.setFocusable(true);
                }
                else{
                    edt_CS_Hood_Passing.setFocusable(false);
                    edt_CS_Hood_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getToplink_check().equals("0")){
                    edt_CS_TopLink_Passing.setFocusable(true);
                }
                else{
                    edt_CS_TopLink_Passing.setFocusable(false);
                    edt_CS_TopLink_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDrowbar_check().equals("0")){
                    edt_CS_DrawBar_Passing.setFocusable(true);
                }
                else{
                    edt_CS_DrawBar_Passing.setFocusable(false);
                    edt_CS_DrawBar_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getToolkit_check().equals("0")){
                    edt_CS_ToolKit_Passing.setFocusable(true);
                }
                else{
                    edt_CS_ToolKit_Passing.setFocusable(false);
                    edt_CS_ToolKit_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getBumper_check().equals("0")){
                    edt_CS_Bumper_Passing.setFocusable(true);
                }
                else{
                    edt_CS_Bumper_Passing.setFocusable(false);
                    edt_CS_Bumper_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getHitch_check().equals("0")){
                    edt_CS_Hitch_Passing.setFocusable(true);
                }
                else{
                    edt_CS_Hitch_Passing.setFocusable(false);
                    edt_CS_Hitch_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDescription_check().equals("0")){
                    edt_CS_Description_Passing.setFocusable(true);
                }
                else{
                    edt_CS_Description_Passing.setFocusable(false);
                    edt_CS_Description_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getB_p_photo_check().equals("0")){
                    edt_CD_PassBook_Passing.setFocusable(true);
                }
                else{
                    edt_CD_PassBook_Passing.setFocusable(false);
                    edt_CD_PassBook_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCheck_photo_check().equals("0")){
                    edt_CD_ChequeBook_Passing .setFocusable(true);
                }
                else{
                    edt_CD_ChequeBook_Passing.setFocusable(false);
                    edt_CD_ChequeBook_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getR_e_name_check().equals("0")){
                    edt_PassingDetail_REF_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_REF_Passing.setFocusable(false);
                    edt_PassingDetail_REF_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getFinance_from_check().equals("0")){
                    edt_PassingDetail_FinanceForm_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_FinanceForm_Passing.setFocusable(false);
                    edt_PassingDetail_FinanceForm_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLoan_amount_check().equals("0")){
                    edt_PassingDetail_LoanAmount_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_LoanAmount_Passing.setFocusable(false);
                    edt_PassingDetail_LoanAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")){
                    edt_PassingDetail_LoanSancAmont_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_LoanSancAmont_Passing.setFocusable(false);
                    edt_PassingDetail_LoanSancAmont_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLloan_charge_check().equals("0")){
                    edt_PassingDetail_LoanCharge_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_LoanCharge_Passing.setFocusable(false);
                    edt_PassingDetail_LoanCharge_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLand_details_check().equals("0")){
                    edt_PassingDetail_LandDetail_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_LandDetail_Passing.setFocusable(false);
                    edt_PassingDetail_LandDetail_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCibil_score_check().equals("0")){
                    edt_PassingDetail_CibilScore_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_CibilScore_Passing.setFocusable(false);
                    edt_PassingDetail_CibilScore_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getFi_date_check().equals("0")){
                    edt_PassingDetail_FiDate_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_FiDate_Passing.setFocusable(false);
                    edt_PassingDetail_FiDate_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getSectiondate_check().equals("0")){
                    edt_PassingDetail_SanctionDate_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_SanctionDate_Passing.setFocusable(false);
                    edt_PassingDetail_SanctionDate_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getStage_check().equals("0")){
                    edt_PassingDetail_Stage_Passing .setFocusable(true);
                }
                else{
                    edt_PassingDetail_Stage_Passing.setFocusable(false);
                    edt_PassingDetail_Stage_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getSkim_check().equals("0")){
                    edt_CS_ConsumerSkim_Passing.setFocusable(true);
                }else{
                    edt_CS_ConsumerSkim_Passing.setFocusable(false);
                    edt_CS_ConsumerSkim_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAtype_check().equals("0")){
                    edt_CD_PaymentOption_Passing.setFocusable(true);
                }else{
                    edt_CD_PaymentOption_Passing.setFocusable(false);
                    edt_CD_PaymentOption_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getCash_date_check().equals("0")){
                    edt_CashDetail_Date_Passing.setFocusable(true);
                }else{
                    edt_CashDetail_Date_Passing.setFocusable(false);
                    edt_CashDetail_Date_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCash_amount_check().equals("0")){
                    edt_CashDetail_Amount_Passing.setFocusable(true);
                }else{
                    edt_CashDetail_Amount_Passing.setFocusable(false);
                    edt_CashDetail_Amount_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCash_description_check().equals("0")){
                    edt_CashDetail_Description_Passing.setFocusable(true);
                }else{
                    edt_CashDetail_Description_Passing.setFocusable(false);
                    edt_CashDetail_Description_Passing.setTextColor(Color.parseColor("#43a047"));
                }


               /* if(response.body().getData().get(id_pos).getTyre_check().equals("0")){
                    edt_Type_Passing.setFocusable(true);
                }else{
                    edt_Type_Passing.setFocusable(false);
                    edt_Type_Passing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getBattery_check().equals("0")){
                    edt_Bettry_Passing.setFocusable(true);
                }else{
                    edt_Bettry_Passing.setFocusable(false);
                    edt_Bettry_Passing.setTextColor(Color.parseColor("#43a047"));
                }*/

                if(response.body().getData().get(id_pos).getDelivery_date_check().equals("0")){
                    edt_PassingDate_Passing.setFocusable(true);
                }else{
                    edt_PassingDate_Passing.setFocusable(false);
                    edt_PassingDate_Passing.setTextColor(Color.parseColor("#43a047"));
                }


                /** ************************************************************************************* */

               /* if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                    txt_CD_UploadBookingPhoto_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_UploadBookingPhoto_Passing.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                    txt_CD_AdharCard_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_AdharCard_Passing.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                    txt_CD_ElectionCard_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_ElectionCard_Passing.setVisibility(View.GONE);
                }*/

               /* if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                    txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                }*/

                if(response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")){
                    txt_DownP_UploadCheque_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadCheque_Passing.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")){
                    txt_DownP_UploadNEFT_RTGS_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadNEFT_RTGS_Passing.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getNoc_photo_check().equals("0")){
                    txt_DownP_UploadNocPhoto_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadNocPhoto_Passing.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")){
                    txt_DownP_UploadOldVehicle_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadOldVehicle_Passing.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")){
                    txt_DownP_UploadRCBook_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadRCBook_Passing.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getEc_photo_check().equals("0")){
                    txt_DownP_UploadElectionPhoto_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadElectionPhoto_Passing.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                    txt_LoanDetail_BankPassBook_Passing.setVisibility(View.VISIBLE);
                } else {
                    txt_LoanDetail_BankPassBook_Passing.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getDelivry_photo_check().equals("0")){
                    txt_PassingPhoto_Passing.setVisibility(View.VISIBLE);
                }
                else{
                    txt_PassingPhoto_Passing.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getChasis_print_check().equals("0")) {
                    txt_ChesisPrint_Passing.setVisibility(View.VISIBLE);
                } else {
                    txt_ChesisPrint_Passing.setVisibility(View.GONE);
                }


                //----------------------------------------------------

                /*if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                    txt_CD_AdharCard_Passing2.setVisibility(View.VISIBLE);

                }else{
                    txt_CD_AdharCard_Passing2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getElection_back_check ().equals("0")){
                    txt_CD_ElectionCard_Passing2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_ElectionCard_Passing2.setVisibility(View.GONE);
                }*/

                /*if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                    txt_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_PassportSize_Passing2.setVisibility(View.GONE);
                }*/


                if(response.body().getData().get(id_pos).getRcbook_back_check().equals("0")){
                    txt_DownP_UploadRCBook_Passing2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadRCBook_Passing2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getElec_back_check().equals("0")){
                    txt_DownP_UploadElectionPhoto_Passing2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadElectionPhoto_Passing2.setVisibility(View.GONE);
                }

                //
                if(response.body().getData().get(id_pos).getB_pass_back_check().equals("0")){
                    txt_LoanDetail_BankPassBook_Passing2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_LoanDetail_BankPassBook_Passing2.setVisibility(View.GONE);
                }

                /** ********************************************************************************** */

                if (response.body().getData().get(id_pos).getStage().equals("Pending")) {
                    String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_PassingDetail_stageloan.setAdapter(adapterStage);

                    spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }


                if (response.body().getData().get(id_pos).getStage().equals("Fi Done")) {
                    String[] Satge_data = {"Fi Done", "Pending", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_PassingDetail_stageloan.setAdapter(adapterStage);

                    spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (response.body().getData().get(id_pos).getStage().equals("Login Pending")) {
                    String[] Satge_data = {"Login Pending", "Pending", "Fi Done", "Login Done", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_PassingDetail_stageloan.setAdapter(adapterStage);

                    spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (response.body().getData().get(id_pos).getStage().equals("Login Done")) {
                    String[] Satge_data = {"Login Done", "Pending", "Fi Done", "Login Pending", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_PassingDetail_stageloan.setAdapter(adapterStage);

                    spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (response.body().getData().get(id_pos).getStage().equals("CIBIL Check")) {
                    String[] Satge_data = {"CIBIL Check", "Pending", "Fi Done", "Login Pending", "Login Done", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_PassingDetail_stageloan.setAdapter(adapterStage);

                    spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (response.body().getData().get(id_pos).getStage().equals("sanction")) {
                    String[] Satge_data = {"sanction", "Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_PassingDetail_stageloan.setAdapter(adapterStage);

                    spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }


                if (response.body().getData().get(id_pos).getStage().equals("Reject")) {
                    String[] Satge_data = {"Reject", "Pending", "Fi Done", "Login Pending", "Login Done",
                            "CIBIL Check", "sanction"};
                    ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_PassingDetail_stageloan.setAdapter(adapterStage);

                    spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }



                /** ********************************************************************************** */

                String data =  response.body().getData().get(id_pos).getBooking_amt();//"cash,bank"
                //  Toast.makeText(BookingPhaseOneActivity.this, ""+data, Toast.LENGTH_SHORT).show();
                String[] res = data.split(",");

                txtDPCashAmount_Passing.setVisibility(View.GONE);
                edt_DownP_CashAmount_Passing.setVisibility(View.GONE);

                edt_DownP_BankOption_Passing.setVisibility(View.GONE);
                edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                edt_DownP_SelectMake_Passing.setVisibility(View.GONE);
                edt_DownP_ModelVehicle_Passing.setVisibility(View.GONE);
                edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                edt_DownP_ManufactureYear_Passing.setVisibility(View.GONE);
                edt_DownP_PaperExchange_Passing.setVisibility(View.GONE);
                edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                edt_DownP_NOC_Passing.setVisibility(View.GONE);

                lin_dp_cheque_Passing.setVisibility(View.GONE);
                lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);
                lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                lin_dp_OldVehicle_Passing.setVisibility(View.GONE);
                lin_dp_Rcbook_Passing.setVisibility(View.GONE);
                lin_dp_Election_Passing.setVisibility(View.GONE);

                lin_dp_Rcbook_Passing2.setVisibility(View.GONE);
                lin_dp_Election_Passing2.setVisibility(View.GONE);

                txtDPBankOption_Passing.setVisibility(View.GONE);
                txtDPChequeDate_Passing.setVisibility(View.GONE);
                txtDPChequeAmount_Passing.setVisibility(View.GONE);
                txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                txtDPMake_Passing.setVisibility(View.GONE);
                txtDPManufectureYear_Passing.setVisibility(View.GONE);
                txtDPOldAmount_Passing.setVisibility(View.GONE);
                txtDPModelName_Passing.setVisibility(View.GONE);
                txtDPPaperExpense_Passing.setVisibility(View.GONE);
                txtDPOldTractorAmount_Passing.setVisibility(View.GONE);
                txtDPNoc_Passing.setVisibility(View.GONE);


                for (int i = 0; i < res.length; i++) {
                    mydata = res[i];
                    // Toast.makeText(BookingPhaseOneActivity.this, "yes" + mydata, Toast.LENGTH_SHORT).show();

                    String uu = mydata.trim();
                    // Log.e("TAG", "onResponse: " + uu);
                    //   Log.d("TAG", "onResponse: "+mydata);

                    if (uu.equals("Cash")) {
                        txtDPCashAmount_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_CashAmount_Passing.setVisibility(View.VISIBLE);
                        // Log.e("TAG", "onResponse:casting ");
                        // Toast.makeText(BookingPhaseOneActivity.this, "casting", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Old Vehicle")) {
                        // Log.e("TAG", "onResponse:casting45 ");
                        txtDPMake_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_SelectMake_Passing.setVisibility(View.VISIBLE);
                        txtDPModelName_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_ModelVehicle_Passing.setVisibility(View.VISIBLE);
                        txtDPManufectureYear_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_ManufactureYear_Passing.setVisibility(View.VISIBLE);
                        txtDPOldAmount_Passing.setVisibility(View.VISIBLE);
                        txtDPPaperExpense_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_PaperExchange_Passing.setVisibility(View.VISIBLE);

                        if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                            //edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                        }
                        else {
                            //edt_DownP_oldAmount_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_oldTractorAmount_Passing.setVisibility(View.VISIBLE);
                        }


                        if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                            lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                        } else {
                            lin_dp_NocPhoto_Passing.setVisibility(View.VISIBLE);
                        }

                        txtDPOldTractorAmount_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_oldAmount_Passing.setVisibility(View.VISIBLE);
                       // edt_DownP_oldTractorAmount_Passing.setVisibility(View.VISIBLE);
                        txtDPNoc_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_NOC_Passing.setVisibility(View.VISIBLE);

                       // lin_dp_NocPhoto_Passing.setVisibility(View.VISIBLE);
                        lin_dp_OldVehicle_Passing.setVisibility(View.VISIBLE);
                        lin_dp_Rcbook_Passing.setVisibility(View.VISIBLE);
                        lin_dp_Election_Passing.setVisibility(View.VISIBLE);

                        lin_dp_Rcbook_Passing2.setVisibility(View.VISIBLE);
                        lin_dp_Election_Passing2.setVisibility(View.VISIBLE);

                        //  Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Bank")) {
                        // Log.e("TAG", "onResponse:casting450 ");
                        // Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();

                        txtDPBankOption_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_BankOption_Passing.setVisibility(View.VISIBLE);

                        if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                            txtDPChequeDate_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeDate_Passing.setVisibility(View.VISIBLE);
                            txtDPChequeAmount_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeAmount_Passing.setVisibility(View.VISIBLE);
                            lin_dp_cheque_Passing.setVisibility(View.VISIBLE);

                            txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                        {
                            txtDPNEFT_RTGSdate_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.VISIBLE);
                            txtDPNEFT_RTGSAmount_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.VISIBLE);
                            lin_dp_NEFT_RTGS_Passing.setVisibility(View.VISIBLE);

                            txtDPChequeDate_Passing.setVisibility(View.GONE);
                            edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                            txtDPChequeAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                            lin_dp_cheque_Passing.setVisibility(View.GONE);
                        }

                    }
                }


               /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Cash")){

                    txtDPCashAmount_Passing.setVisibility(View.VISIBLE);
                    edt_DownP_CashAmount_Passing.setVisibility(View.VISIBLE);

                    edt_DownP_BankOption_Passing.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                    edt_DownP_SelectMake_Passing.setVisibility(View.GONE);
                    edt_DownP_ModelVehicle_Passing.setVisibility(View.GONE);
                    edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                    edt_DownP_ManufactureYear_Passing.setVisibility(View.GONE);
                    edt_DownP_PaperExchange_Passing.setVisibility(View.GONE);
                    edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                    edt_DownP_NOC_Passing.setVisibility(View.GONE);

                    lin_dp_cheque_Passing.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);
                    lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                    lin_dp_OldVehicle_Passing.setVisibility(View.GONE);
                    lin_dp_Rcbook_Passing.setVisibility(View.GONE);
                    lin_dp_Election_Passing.setVisibility(View.GONE);

                    lin_dp_Rcbook_Passing2.setVisibility(View.GONE);
                    lin_dp_Election_Passing2.setVisibility(View.GONE);

                    txtDPBankOption_Passing.setVisibility(View.GONE);
                    txtDPChequeDate_Passing.setVisibility(View.GONE);
                    txtDPChequeAmount_Passing.setVisibility(View.GONE);
                    txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                    txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                    txtDPMake_Passing.setVisibility(View.GONE);
                    txtDPManufectureYear_Passing.setVisibility(View.GONE);
                    txtDPOldAmount_Passing.setVisibility(View.GONE);
                    txtDPModelName_Passing.setVisibility(View.GONE);
                    txtDPPaperExpense_Passing.setVisibility(View.GONE);
                    txtDPOldTractorAmount_Passing.setVisibility(View.GONE);
                    txtDPNoc_Passing.setVisibility(View.GONE);
                }*/


               /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Bank")){

                    txtDPCashAmount_Passing.setVisibility(View.GONE);
                    edt_DownP_CashAmount_Passing.setVisibility(View.GONE);

                    txtDPBankOption_Passing.setVisibility(View.VISIBLE);
                    edt_DownP_BankOption_Passing.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                        txtDPChequeDate_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_ChequeDate_Passing.setVisibility(View.VISIBLE);
                        txtDPChequeAmount_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_ChequeAmount_Passing.setVisibility(View.VISIBLE);
                        lin_dp_cheque_Passing.setVisibility(View.VISIBLE);

                        txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                        txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                        lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);

                        txtDPMake_Passing.setVisibility(View.GONE);
                        edt_DownP_SelectMake_Passing.setVisibility(View.GONE);
                        txtDPModelName_Passing.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_Passing.setVisibility(View.GONE);
                        txtDPManufectureYear_Passing.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_Passing.setVisibility(View.GONE);
                        txtDPOldAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                        txtDPPaperExpense_Passing.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_Passing.setVisibility(View.GONE);
                        txtDPOldTractorAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                        txtDPNoc_Passing.setVisibility(View.GONE);
                        edt_DownP_NOC_Passing.setVisibility(View.GONE);

                        lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                        lin_dp_OldVehicle_Passing.setVisibility(View.GONE);
                        lin_dp_Rcbook_Passing.setVisibility(View.GONE);
                        lin_dp_Election_Passing.setVisibility(View.GONE);
                        lin_dp_Rcbook_Passing2.setVisibility(View.GONE);
                        lin_dp_Election_Passing2.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                    {
                        txtDPNEFT_RTGSdate_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.VISIBLE);
                        txtDPNEFT_RTGSAmount_Passing.setVisibility(View.VISIBLE);
                        edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.VISIBLE);
                        lin_dp_NEFT_RTGS_Passing.setVisibility(View.VISIBLE);

                        txtDPChequeDate_Passing.setVisibility(View.GONE);
                        edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                        txtDPChequeAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                        lin_dp_cheque_Passing.setVisibility(View.GONE);

                        txtDPMake_Passing.setVisibility(View.GONE);
                        edt_DownP_SelectMake_Passing.setVisibility(View.GONE);
                        txtDPModelName_Passing.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_Passing.setVisibility(View.GONE);
                        txtDPManufectureYear_Passing.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_Passing.setVisibility(View.GONE);
                        txtDPOldAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                        txtDPPaperExpense_Passing.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_Passing.setVisibility(View.GONE);
                        txtDPOldTractorAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                        txtDPNoc_Passing.setVisibility(View.GONE);
                        edt_DownP_NOC_Passing.setVisibility(View.GONE);

                        lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                        lin_dp_OldVehicle_Passing.setVisibility(View.GONE);
                        lin_dp_Rcbook_Passing.setVisibility(View.GONE);
                        lin_dp_Election_Passing.setVisibility(View.GONE);
                        lin_dp_Rcbook_Passing2.setVisibility(View.GONE);
                        lin_dp_Election_Passing2.setVisibility(View.GONE);
                    }

                }*/

                //Old Vehicle
               /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Old Vehicle")){

                    txtDPMake_Passing.setVisibility(View.VISIBLE);
                    edt_DownP_SelectMake_Passing.setVisibility(View.VISIBLE);
                    txtDPModelName_Passing.setVisibility(View.VISIBLE);
                    edt_DownP_ModelVehicle_Passing.setVisibility(View.VISIBLE);
                    txtDPManufectureYear_Passing.setVisibility(View.VISIBLE);
                    edt_DownP_ManufactureYear_Passing.setVisibility(View.VISIBLE);
                    txtDPOldAmount_Passing.setVisibility(View.VISIBLE);
                    txtDPPaperExpense_Passing.setVisibility(View.VISIBLE);
                    edt_DownP_PaperExchange_Passing.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                        edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                    }
                    else {
                        edt_DownP_oldAmount_Passing.setVisibility(View.VISIBLE);
                    }

                    txtDPOldTractorAmount_Passing.setVisibility(View.VISIBLE);
                    edt_DownP_oldTractorAmount_Passing.setVisibility(View.VISIBLE);
                    txtDPNoc_Passing.setVisibility(View.VISIBLE);
                    edt_DownP_NOC_Passing.setVisibility(View.VISIBLE);

                    lin_dp_NocPhoto_Passing.setVisibility(View.VISIBLE);
                    lin_dp_OldVehicle_Passing.setVisibility(View.VISIBLE);
                    lin_dp_Rcbook_Passing.setVisibility(View.VISIBLE);
                    lin_dp_Election_Passing.setVisibility(View.VISIBLE);

                    lin_dp_Rcbook_Passing2.setVisibility(View.VISIBLE);
                    lin_dp_Election_Passing2.setVisibility(View.VISIBLE);


                    txtDPCashAmount_Passing.setVisibility(View.GONE);
                    edt_DownP_CashAmount_Passing.setVisibility(View.GONE);
                    txtDPBankOption_Passing.setVisibility(View.GONE);
                    edt_DownP_BankOption_Passing.setVisibility(View.GONE);
                    txtDPChequeDate_Passing.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                    txtDPChequeAmount_Passing.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                    lin_dp_cheque_Passing.setVisibility(View.GONE);
                    txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                    txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);
                }*/


                if(response.body().getData().get(id_pos).getRto().equals("No")){
                    edt_RTO_RtoTax_Passing.setVisibility(View.GONE);
                    edt_RTO_RtoPassing_Passing.setVisibility(View.GONE);
                    edt_RTO_Insurance_Passing.setVisibility(View.GONE);
                    edt_RTO_AgentFees_Passing.setVisibility(View.GONE);
                    edt_RTO_NumberPlat_Passing.setVisibility(View.GONE);
                    edt_RTO_LoanCharge_Passing.setVisibility(View.GONE);

                    txtRTOTax.setVisibility(View.GONE);
                    txtRTOPassing.setVisibility(View.GONE);
                    txtInsurance.setVisibility(View.GONE);
                    txtAgentFees.setVisibility(View.GONE);
                    txtNumberPlat.setVisibility(View.GONE);
                    txtLoanCharge.setVisibility(View.GONE);
                }

                else{
                    edt_RTO_RtoTax_Passing.setVisibility(View.VISIBLE);
                    edt_RTO_RtoPassing_Passing.setVisibility(View.VISIBLE);
                    edt_RTO_Insurance_Passing.setVisibility(View.VISIBLE);
                    edt_RTO_AgentFees_Passing.setVisibility(View.VISIBLE);
                    edt_RTO_NumberPlat_Passing.setVisibility(View.VISIBLE);
                    edt_RTO_LoanCharge_Passing.setVisibility(View.VISIBLE);

                    txtRTOTax.setVisibility(View.VISIBLE);
                    txtRTOPassing.setVisibility(View.VISIBLE);
                    txtInsurance.setVisibility(View.VISIBLE);
                    txtAgentFees.setVisibility(View.VISIBLE);
                    txtNumberPlat.setVisibility(View.VISIBLE);
                    txtLoanCharge.setVisibility(View.VISIBLE);
                }

                if(response.body().getData().get(id_pos).getSkim().equals("No")){
                    lin_ConsumerSkim_passing.setVisibility(View.GONE);
                }
                else{
                    lin_ConsumerSkim_passing.setVisibility(View.VISIBLE);
                }


               /* if(response.body().getData().get(id_pos).getAtype().equals("Loan")){
                    lin_cashLoan_Passing.setVisibility(View.GONE);
                    lin_Loan_Passing_form.setVisibility(View.VISIBLE);
                }
                else {
                    lin_cashLoan_Passing.setVisibility(View.VISIBLE);
                    lin_Loan_Passing_form.setVisibility(View.GONE);
                }*/

                if (response.body().getData().get(id_pos).getAtype().equals("Loan")) {
                    lin_Loan_Passing_form.setVisibility(View.VISIBLE);
                    lin_cashLoan_Passing.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {
                    lin_Loan_Passing_form.setVisibility(View.GONE);
                    lin_cashLoan_Passing.setVisibility(View.VISIBLE);
                }


                if (response.body().getData().get(id_pos).getAtype().equals("Cash-Loan")) {
                    lin_Loan_Passing_form.setVisibility(View.VISIBLE);
                    lin_cashLoan_Passing.setVisibility(View.VISIBLE);
                }


                //-------------------------------------
                  if (response.body().getData().get(id_pos).getStage_check().equals("0")) {

                    StageFinalVal = Stage;

                } else {

                    StageFinalVal = edt_PassingDetail_Stage_Passing.getText().toString();
                }

                if( StageFinalVal == null){
                    StageFinalVal=" ";
                }
                //-------------------------------------


                /*Toast.makeText(PassingFormActivity.this, ""
                        +response.body().getData().get(id_pos).getCheck_neft_rtgs()+" "+
                        response.body().getData().get(id_pos).getAtype(), Toast.LENGTH_LONG).show();*/


                edt_ADBooking_BookingNo_Passing.setText(response.body().getData().get(id_pos).getBno());
                edt_ADBooking_BookingType_Passing.setText(response.body().getData().get(id_pos).getB_type());
                edt_ADBooking_BookingDate_Passing.setText(response.body().getData().get(id_pos).getB_date());
                edt_ADBooking_BookingLoginName_Passing.setText(response.body().getData().get(id_pos).getEmp());

                // Toast.makeText(DeliveryFormActivity.this, ""+id_item, Toast.LENGTH_SHORT).show();

               /* edt_ADBooking_BookingNo_Passing.setText(response.body().getData().get(id_item).getBno());
                edt_ADBooking_BookingType_Passing.setText(response.body().getData().get(id_item).getB_type());
                edt_ADBooking_BookingDate_Passing.setText(response.body().getData().get(id_item).getB_date());
                edt_ADBooking_BookingLoginName_Passing.setText(response.body().getData().get(id_item).getEmp());*/

                edt_CD_Fname_Passing.setText(response.body().getData().get(id_pos).getFname());
                edt_CD_LastName_Passing.setText(response.body().getData().get(id_pos).getLname()+" ");
                edt_CD_Surname_Passing.setText(response.body().getData().get(id_pos).getSname());
                edt_CD_MobileNo_Passing.setText(response.body().getData().get(id_pos).getMobileno());
                edt_CD_WhatsappNo_Passing.setText(response.body().getData().get(id_pos).getWhno());
                edt_CD_ReferenceName_Passing.setText(response.body().getData().get(id_pos).getRef_name());
                edt_CD_ReferenceNo_Passing.setText(response.body().getData().get(id_pos).getRef_no());
                edt_CD_State_Passing.setText(response.body().getData().get(id_pos).getState());
                edt_CD_City_Passing.setText(response.body().getData().get(id_pos).getCity());
                edt_CD_District_Passing.setText(response.body().getData().get(id_pos).getDistric());
                edt_CD_Village_Passing.setText(response.body().getData().get(id_pos).getVillage());
                edt_CD_EmployeName_Passing.setText(response.body().getData().get(id_pos).getEmp());
                edt_CD_Taluko_Passing.setText(response.body().getData().get(id_pos).getTehsill());
                edt_CD_PassBook_Passing.setText(response.body().getData().get(id_pos).getB_p_photo());
                edt_CD_ChequeBook_Passing.setText(response.body().getData().get(id_pos).getCheck_photo());
                edt_CD_PaymentOption_Passing.setText(response.body().getData().get(id_pos).getAtype());


                edt_PD_PurchaseName_Passing.setText(response.body().getData().get(id_pos).getProduct_name());
                edt_PD_ModelName_Passing.setText(response.body().getData().get(id_pos).getModel_name());
                edt_PD_Description_Passing.setText(response.body().getData().get(id_pos).getP_desc());


                edt_PriceD_price_Passing.setText(response.body().getData().get(id_pos).getDeal_price());
                edt_PriceD_priceInWord_Passing.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                edt_PriceD_GST_Passing.setText(response.body().getData().get(id_pos).getGst());


                edt_RTO_RtoMain_Passing.setText(response.body().getData().get(id_pos).getRto());
                edt_RTO_RtoTax_Passing.setText(response.body().getData().get(id_pos).getRto_tax());
                edt_RTO_RtoPassing_Passing.setText(response.body().getData().get(id_pos).getRto_passing());
                edt_RTO_Insurance_Passing.setText(response.body().getData().get(id_pos).getInsurance());
                edt_RTO_AgentFees_Passing.setText(response.body().getData().get(id_pos).getAgent_fee());
                edt_RTO_NumberPlat_Passing.setText(response.body().getData().get(id_pos).getNumber_plat());
                edt_RTO_LoanCharge_Passing.setText(response.body().getData().get(id_pos).getLoan_charge());


                edt_DownP_BookingAmount_Passing.setText(response.body().getData().get(id_pos).getBooking_amt());
                edt_DownP_CashAmount_Passing.setText(response.body().getData().get(id_pos).getAmount());
                edt_DownP_BankOption_Passing.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                edt_DownP_ChequeDate_Passing.setText(response.body().getData().get(id_pos).getCheck_date());
                edt_DownP_ChequeAmount_Passing.setText(response.body().getData().get(id_pos).getCheck_amt());
                edt_DownP_NEFT_RTGS_date_Passing.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                edt_DownP_NEFT_RTGSAmount_Passing.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                edt_DownP_SelectMake_Passing.setText(response.body().getData().get(id_pos).getMake());
                edt_DownP_ModelVehicle_Passing.setText(response.body().getData().get(id_pos).getModel());
                edt_DownP_oldAmount_Passing.setText(response.body().getData().get(id_pos).getOld_t_amount());
                edt_DownP_ManufactureYear_Passing.setText(response.body().getData().get(id_pos).getM_year());
                edt_DownP_PaperExchange_Passing.setText(response.body().getData().get(id_pos).getPaper_expence());
                edt_DownP_oldTractorAmount_Passing.setText(response.body().getData().get(id_pos).getC_amount());
                edt_DownP_NOC_Passing.setText(response.body().getData().get(id_pos).getNoc());


                edt_CS_ConsumerSkim_Passing.setText(response.body().getData().get(id_pos).getSkim());
                edt_CS_Hood_Passing.setText(response.body().getData().get(id_pos).getHood());
                edt_CS_TopLink_Passing.setText(response.body().getData().get(id_pos).getToplink());
                edt_CS_DrawBar_Passing.setText(response.body().getData().get(id_pos).getDrowbar());
                edt_CS_ToolKit_Passing.setText(response.body().getData().get(id_pos).getToolkit());
                edt_CS_Bumper_Passing.setText(response.body().getData().get(id_pos).getBumper());
                edt_CS_Hitch_Passing.setText(response.body().getData().get(id_pos).getHitch());
                edt_CS_Description_Passing.setText(response.body().getData().get(id_pos).getDescription());


                edt_PassingDetail_REF_Passing.setText(response.body().getData().get(id_pos).getR_e_name());
                edt_PassingDetail_FinanceForm_Passing.setText(response.body().getData().get(id_pos).getFinance_from());
                edt_PassingDetail_LoanAmount_Passing.setText(response.body().getData().get(id_pos).getLoan_amount());
                edt_PassingDetail_LoanSancAmont_Passing.setText(response.body().getData().get(id_pos).getL_sec_amt());
                edt_PassingDetail_LoanCharge_Passing.setText(response.body().getData().get(id_pos).getLloan_charge());
                edt_PassingDetail_LandDetail_Passing.setText(response.body().getData().get(id_pos).getLand_details());
                edt_PassingDetail_CibilScore_Passing.setText(response.body().getData().get(id_pos).getCibil_score());
                edt_PassingDetail_FiDate_Passing.setText(response.body().getData().get(id_pos).getFi_date());
                edt_PassingDetail_SanctionDate_Passing.setText(response.body().getData().get(0).getSectiondate());
                edt_PassingDetail_Stage_Passing.setText(response.body().getData().get(id_pos).getStage());


              /*  edt_modelName_Passing.setText(response.body().getData().get(0).getDmodelname()+"");
                edt_ChesisNumber_Passing.setText(response.body().getData().get(0).getChasisno()+"");
                edt_EngineNumber_Passing.setText(response.body().getData().get(0).getEngineno()+"");
                edt_Variente_Passing.setText(response.body().getData().get(0).getVariants()+"");*/

                edt_Type_Passing.setText(response.body().getData().get(id_pos).getTyre()+"");
                edt_Bettry_Passing.setText(response.body().getData().get(id_pos).getBattery()+"");
                edt_PassingDate_Passing.setText(response.body().getData().get(id_pos).getDelivery_date()+"");

                //cash Details
                edt_CashDetail_Date_Passing.setText(response.body().getData().get(id_pos).getCash_date());
                edt_CashDetail_Amount_Passing.setText(response.body().getData().get(id_pos).getCash_amount());
                edt_CashDetail_Description_Passing.setText(response.body().getData().get(id_pos).getCash_description());

                //Passing Details
                if (response.body().getData().get(id_pos).getRegister_no()==null){

                }else {
                    edt_registration_number.setText(response.body().getData().get(id_pos).getRegister_no());
                }
                int i = Y_N.indexOf(response.body().getData().get(id_pos).getNum_plate_order());
                spn_number_plate_order.setSelection(i);
                int a = Y_N.indexOf(response.body().getData().get(id_pos).getNum_plate_recive());
                spn_number_plate_Recive.setSelection(a);
                int b = Y_N.indexOf(response.body().getData().get(id_pos).getRc_book_financial());
                spn_RC_Book_Update.setSelection(b);



            }

            @Override
            public void onFailure(@NotNull Call<PassingDataModel> call, @NotNull Throwable t) {
    
                progressDialog.dismiss();
                Log.d("Tag", "onFailure: "+t.getCause());
            }
        });


        swipeRefreshLayoutPassing.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getClient().getPassingData(emp).enqueue(new Callback<PassingDataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<PassingDataModel> call, @NotNull Response<PassingDataModel> response) {
                        progressDialog.dismiss();

                        swipeRefreshLayoutPassing.setRefreshing(false);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getB_photo())
                                .into(img_CD_Booking_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getAdhar_photo())
                                .into(img_CD_AdharCard_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getEcard_photo())
                                .into(img_CD_ElectionCard_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getP_photo())
                                .into(img_CD_PassportSize_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getCheck1_photo())
                                .into(img_DownP_Cheque_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getNeft_rtgs_photo())
                                .into(img_DownP_NEFT_RTGS_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getNoc_photo())
                                .into(img_DownP_NocPhoto_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getOld_vehicle_photo())
                                .into(img_DownP_OldVehicle_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getRecbook_photo())
                                .into(img_DownP_RcBook_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getEc_photo())
                                .into(img_DownP_ElectionPhoto_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getLb_pb_photo())
                                .into(img_LoanDetail_BankpassBook_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getL_c_photo())
                                .into(img_LoanDetail_Cheque_Passing);


                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getSar_id_photo())
                                .into(img_LoanDetail_SarpanchID_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getSign_veri())
                                .into(img_LoanDetail_SignatureVerifi_Passing);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getDelivry_photo())
                                .into(img_PassingPhoto_Passing);


                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getChasis_print())
                                .into(img_ChesisPrint_Passing);


                        // -------------------------------------------------------------------
                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getAdhar_back())
                                .into(img_CD_AdharCard_Passing2);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getElection_back())
                                .into(img_CD_ElectionCard_Passing2);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getP_photo_back())
                                .into(img_CD_PassportSize_Passing2);


                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getRcbook_back())
                                .into(img_DownP_RcBook_Passing2);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getElec_back())
                                .into(img_DownP_ElectionPhoto_Passing2);

                        Glide.with(PassingFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        +response.body().getData().get(id_pos).getB_pass_back())
                                .into(img_LoanDetail_BankpassBook_Passing2);

                        //-----------------------------------------------------------


                        //----------------------------------------------------------------------


                        emp_id = response.body().getData().get(id_pos).getEmp_id();

                        if(response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                                response.body().getData().get(id_pos).getProduct_name().equals("Spar part"))
                        {
                            edt_ADBooking_BookingType_Passing.setVisibility(View.GONE);
                            edt_CD_PassBook_Passing.setVisibility(View.GONE);
                            edt_CD_ChequeBook_Passing.setVisibility(View.GONE);
                            txtFillBookingType_Passing.setVisibility(View.GONE);
                            txtPassBookBook_Passing.setVisibility(View.GONE);
                            txtChequeBook_Passing.setVisibility(View.GONE);


                            txt_CD_UploadBookingPhoto_Passing.setVisibility(View.GONE);
                            txt_CD_AdharCard_Passing.setVisibility(View.GONE);
                            txt_CD_ElectionCard_Passing.setVisibility(View.GONE);
                            txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                            txt_CD_AdharCard_Passing2.setVisibility(View.GONE);
                            txt_CD_ElectionCard_Passing2.setVisibility(View.GONE);
                            txt_CD_PassportSize_Passing2.setVisibility(View.GONE);

                            img_CD_Booking_Passing.setVisibility(View.GONE);
                            img_CD_AdharCard_Passing.setVisibility(View.GONE);
                            img_CD_ElectionCard_Passing.setVisibility(View.GONE);
                            img_CD_PassportSize_Passing.setVisibility(View.GONE);
                            img_CD_AdharCard_Passing2.setVisibility(View.GONE);
                            img_CD_ElectionCard_Passing2.setVisibility(View.GONE);
                            img_CD_PassportSize_Passing2.setVisibility(View.GONE);

                            txt_Tyre_Passing.setVisibility(View.GONE);
                            edt_Type_Passing.setVisibility(View.GONE);
                            edt_Bettry_Passing.setVisibility(View.GONE);
                            txt_Battery_Passing.setVisibility(View.GONE);

                        }
                        else{
                            edt_ADBooking_BookingType_Passing.setVisibility(View.VISIBLE);
                            edt_CD_PassBook_Passing.setVisibility(View.VISIBLE);
                            edt_CD_ChequeBook_Passing.setVisibility(View.VISIBLE);
                            txtFillBookingType_Passing.setVisibility(View.VISIBLE);
                            txtPassBookBook_Passing.setVisibility(View.VISIBLE);
                            txtChequeBook_Passing.setVisibility(View.VISIBLE);

                            txt_Tyre_Passing.setVisibility(View.VISIBLE);
                            edt_Type_Passing.setVisibility(View.VISIBLE);
                            edt_Bettry_Passing.setVisibility(View.VISIBLE);
                            txt_Battery_Passing.setVisibility(View.VISIBLE);


                            //------------------------------------------------------

                            if(response.body().getData().get(id_pos).getTyre_check().equals("0")){
                                edt_Type_Passing.setFocusable(true);
                            }else{
                                edt_Type_Passing.setFocusable(false);
                                edt_Type_Passing.setTextColor(Color.parseColor("#43a047"));
                            }

                            if(response.body().getData().get(id_pos).getBattery_check().equals("0")){
                                edt_Bettry_Passing.setFocusable(true);
                            }else{
                                edt_Bettry_Passing.setFocusable(false);
                                edt_Bettry_Passing.setTextColor(Color.parseColor("#43a047"));
                            }

                            //------------------------------------------------------

                            if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                                txt_CD_UploadBookingPhoto_Passing.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_UploadBookingPhoto_Passing.setVisibility(View.GONE);
                            }

                            if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                                txt_CD_AdharCard_Passing.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_AdharCard_Passing.setVisibility(View.GONE);
                            }


                            if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                                txt_CD_ElectionCard_Passing.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_ElectionCard_Passing.setVisibility(View.GONE);
                            }

                            if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                                txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                            }


                            if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                                txt_CD_AdharCard_Passing2.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_AdharCard_Passing2.setVisibility(View.GONE);
                            }


                            if(response.body().getData().get(id_pos).getElection_back_check().equals("0")){
                                txt_CD_ElectionCard_Passing2.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_ElectionCard_Passing2.setVisibility(View.GONE);
                            }

                            if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                                txt_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_PassportSize_Passing2.setVisibility(View.GONE);
                            }
                        }


                        if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                            img_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);
                            txt_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);

                            img_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                            txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);

                            if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                                txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                            }

                            if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                                txt_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_PassportSize_Passing2.setVisibility(View.GONE);
                            }

                        } else {
                            txt_CD_PassportSize_Passing2.setVisibility(View.GONE);
                            img_CD_PassportSize_Passing2.setVisibility(View.GONE);

                            txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                            img_CD_PassportSize_Passing.setVisibility(View.GONE);

                   /* if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                    }*/
                        }


                        //-------------------------------------------------------------------

                        if(response.body().getData().get(id_pos).getBno_check().equals("0")){
                            edt_ADBooking_BookingNo_Passing.setFocusable(true);
                        }
                        else{
                            edt_ADBooking_BookingNo_Passing.setFocusable(false);
                            edt_ADBooking_BookingNo_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getB_date_check().equals("0")){
                            edt_ADBooking_BookingDate_Passing.setFocusable(true);
                        }
                        else{
                            edt_ADBooking_BookingDate_Passing.setFocusable(false);
                            edt_ADBooking_BookingDate_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getB_type_check().equals("0")){
                            edt_ADBooking_BookingType_Passing.setFocusable(true);
                        }
                        else{
                            edt_ADBooking_BookingType_Passing.setFocusable(false);
                            edt_ADBooking_BookingType_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getEmp_check().equals("0")){
                            edt_ADBooking_BookingLoginName_Passing.setFocusable(true);
                        }
                        else{
                            edt_ADBooking_BookingLoginName_Passing.setFocusable(false);
                            edt_ADBooking_BookingLoginName_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getFname_check().equals("0")){
                            edt_CD_Fname_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_Fname_Passing.setFocusable(false);
                            edt_CD_Fname_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getLname_check().equals("0")){
                            edt_CD_LastName_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_LastName_Passing.setFocusable(false);
                            edt_CD_LastName_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getSname_check().equals("0")){
                            edt_CD_Surname_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_Surname_Passing.setFocusable(false);
                            edt_CD_Surname_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getMobileno_check().equals("0")){
                            edt_CD_MobileNo_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_MobileNo_Passing.setFocusable(false);
                            edt_CD_MobileNo_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getWhno_check().equals("0")){
                            edt_CD_WhatsappNo_Passing.setFocusable(true);
                        }else{
                            edt_CD_WhatsappNo_Passing.setFocusable(false);
                            edt_CD_WhatsappNo_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getRef_name_check().equals("0")){
                            edt_CD_ReferenceName_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_ReferenceName_Passing.setFocusable(false);
                            edt_CD_ReferenceName_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getRef_no_check().equals("0")){
                            edt_CD_ReferenceNo_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_ReferenceNo_Passing.setFocusable(false);
                            edt_CD_ReferenceNo_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getState_check().equals("0")){
                            edt_CD_State_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_State_Passing.setFocusable(false);
                            edt_CD_State_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getCity_check().equals("0")){
                            edt_CD_City_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_City_Passing.setFocusable(false);
                            edt_CD_City_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getDistric_check().equals("0")){
                            edt_CD_District_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_District_Passing.setFocusable(false);
                            edt_CD_District_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getVillage_check().equals("0")){
                            edt_CD_Village_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_Village_Passing.setFocusable(false);
                            edt_CD_Village_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getEmp_check().equals("0")){
                            edt_CD_EmployeName_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_EmployeName_Passing.setFocusable(false);
                            edt_CD_EmployeName_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getTehsill_check().equals("0")){
                            edt_CD_Taluko_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_Taluko_Passing.setFocusable(false);
                            edt_CD_Taluko_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getProduct_name_check().equals("0")){
                            edt_PD_PurchaseName_Passing.setFocusable(true);
                        }
                        else{
                            edt_PD_PurchaseName_Passing.setFocusable(false);
                            edt_PD_PurchaseName_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getModel_name_check().equals("0")){
                            edt_PD_ModelName_Passing.setFocusable(true);
                        }
                        else{
                            edt_PD_ModelName_Passing.setFocusable(false);
                            edt_PD_ModelName_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getP_desc_check().equals("0")){
                            edt_PD_Description_Passing.setFocusable(true);
                        }
                        else{
                            edt_PD_Description_Passing.setFocusable(false);
                            edt_PD_Description_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getDeal_price_check().equals("0")){
                            edt_PriceD_price_Passing.setFocusable(true);
                        }
                        else{
                            edt_PriceD_price_Passing.setFocusable(false);
                            edt_PriceD_price_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")){
                            edt_PriceD_priceInWord_Passing.setFocusable(true);
                        }
                        else{
                            edt_PriceD_priceInWord_Passing.setFocusable(false);
                            edt_PriceD_priceInWord_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getGst_check().equals("0")){
                            edt_PriceD_GST_Passing.setFocusable(true);
                        }
                        else{
                            edt_PriceD_GST_Passing.setFocusable(false);
                            edt_PriceD_GST_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getRto_check().equals("0")){
                            edt_RTO_RtoMain_Passing.setFocusable(true);
                        }
                        else{
                            edt_RTO_RtoMain_Passing.setFocusable(false);
                            edt_RTO_RtoMain_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getRto_tax_check().equals("0")){
                            edt_RTO_RtoTax_Passing.setFocusable(true);
                        }
                        else{
                            edt_RTO_RtoTax_Passing.setFocusable(false);
                            edt_RTO_RtoTax_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getRto_passing_check().equals("0")){
                            edt_RTO_RtoPassing_Passing.setFocusable(true);
                        }
                        else{
                            edt_RTO_RtoPassing_Passing.setFocusable(false);
                            edt_RTO_RtoPassing_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getInsurance_check().equals("0")){
                            edt_RTO_Insurance_Passing.setFocusable(true);
                        }
                        else{
                            edt_RTO_Insurance_Passing.setFocusable(false);
                            edt_RTO_Insurance_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getAgent_fee_check().equals("0")){
                            edt_RTO_AgentFees_Passing.setFocusable(true);
                        }
                        else{
                            edt_RTO_AgentFees_Passing.setFocusable(false);
                            edt_RTO_AgentFees_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getNumber_plat_check().equals("0")){
                            edt_RTO_NumberPlat_Passing.setFocusable(true);
                        }
                        else{
                            edt_RTO_NumberPlat_Passing.setFocusable(false);
                            edt_RTO_NumberPlat_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getLoan_charge_check().equals("0")){
                            edt_RTO_LoanCharge_Passing.setFocusable(true);
                        }
                        else{
                            edt_RTO_LoanCharge_Passing.setFocusable(false);
                            edt_RTO_LoanCharge_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getBooking_amt_check().equals("0")){
                            edt_DownP_BookingAmount_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_BookingAmount_Passing.setFocusable(false);
                            edt_DownP_BookingAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getAmount_check().equals("0")){
                            edt_DownP_CashAmount_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_CashAmount_Passing.setFocusable(false);
                            edt_DownP_CashAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getCheck_neft_rtgs_check().equals("0")){
                            edt_DownP_BankOption_Passing.setFocusable(true);
                        }
                        else {
                            edt_DownP_BankOption_Passing.setFocusable(false);
                            edt_DownP_BankOption_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getCheck_date_check().equals("0")){
                            edt_DownP_ChequeDate_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_ChequeDate_Passing.setFocusable(false);
                            edt_DownP_ChequeDate_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getC_amount_check().equals("0")){
                            edt_DownP_ChequeAmount_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_ChequeAmount_Passing.setFocusable(false);
                            edt_DownP_ChequeAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")){
                            edt_DownP_NEFT_RTGS_date_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_NEFT_RTGS_date_Passing.setFocusable(false);
                            edt_DownP_NEFT_RTGS_date_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")){
                            edt_DownP_NEFT_RTGSAmount_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_NEFT_RTGSAmount_Passing.setFocusable(false);
                            edt_DownP_NEFT_RTGSAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getMake_check().equals("0")){
                            edt_DownP_SelectMake_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_SelectMake_Passing.setFocusable(false);
                            edt_DownP_SelectMake_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getModel_check().equals("0")){
                            edt_DownP_ModelVehicle_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_ModelVehicle_Passing.setFocusable(false);
                            edt_DownP_ModelVehicle_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")){
                            edt_DownP_oldAmount_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_oldAmount_Passing.setFocusable(false);
                            edt_DownP_oldAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getM_year_check().equals("0")){
                            edt_DownP_ManufactureYear_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_ManufactureYear_Passing.setFocusable(false);
                            edt_DownP_ManufactureYear_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getPaper_expence_check().equals("0")){
                            edt_DownP_PaperExchange_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_PaperExchange_Passing.setFocusable(false);
                            edt_DownP_PaperExchange_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")){
                            edt_DownP_oldTractorAmount_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_oldTractorAmount_Passing.setFocusable(false);
                            edt_DownP_oldTractorAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getNoc_check().equals("0")){
                            edt_DownP_NOC_Passing.setFocusable(true);
                        }
                        else{
                            edt_DownP_NOC_Passing.setFocusable(false);
                            edt_DownP_NOC_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getHood_check().equals("0")){
                            edt_CS_Hood_Passing.setFocusable(true);
                        }
                        else{
                            edt_CS_Hood_Passing.setFocusable(false);
                            edt_CS_Hood_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getToplink_check().equals("0")){
                            edt_CS_TopLink_Passing.setFocusable(true);
                        }
                        else{
                            edt_CS_TopLink_Passing.setFocusable(false);
                            edt_CS_TopLink_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getDrowbar_check().equals("0")){
                            edt_CS_DrawBar_Passing.setFocusable(true);
                        }
                        else{
                            edt_CS_DrawBar_Passing.setFocusable(false);
                            edt_CS_DrawBar_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getToolkit_check().equals("0")){
                            edt_CS_ToolKit_Passing.setFocusable(true);
                        }
                        else{
                            edt_CS_ToolKit_Passing.setFocusable(false);
                            edt_CS_ToolKit_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getBumper_check().equals("0")){
                            edt_CS_Bumper_Passing.setFocusable(true);
                        }
                        else{
                            edt_CS_Bumper_Passing.setFocusable(false);
                            edt_CS_Bumper_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getHitch_check().equals("0")){
                            edt_CS_Hitch_Passing.setFocusable(true);
                        }
                        else{
                            edt_CS_Hitch_Passing.setFocusable(false);
                            edt_CS_Hitch_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getDescription_check().equals("0")){
                            edt_CS_Description_Passing.setFocusable(true);
                        }
                        else{
                            edt_CS_Description_Passing.setFocusable(false);
                            edt_CS_Description_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getB_p_photo_check().equals("0")){
                            edt_CD_PassBook_Passing.setFocusable(true);
                        }
                        else{
                            edt_CD_PassBook_Passing.setFocusable(false);
                            edt_CD_PassBook_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getCheck_photo_check().equals("0")){
                            edt_CD_ChequeBook_Passing .setFocusable(true);
                        }
                        else{
                            edt_CD_ChequeBook_Passing.setFocusable(false);
                            edt_CD_ChequeBook_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getR_e_name_check().equals("0")){
                            edt_PassingDetail_REF_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_REF_Passing.setFocusable(false);
                            edt_PassingDetail_REF_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getFinance_from_check().equals("0")){
                            edt_PassingDetail_FinanceForm_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_FinanceForm_Passing.setFocusable(false);
                            edt_PassingDetail_FinanceForm_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getLoan_amount_check().equals("0")){
                            edt_PassingDetail_LoanAmount_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_LoanAmount_Passing.setFocusable(false);
                            edt_PassingDetail_LoanAmount_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")){
                            edt_PassingDetail_LoanSancAmont_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_LoanSancAmont_Passing.setFocusable(false);
                            edt_PassingDetail_LoanSancAmont_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getLloan_charge_check().equals("0")){
                            edt_PassingDetail_LoanCharge_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_LoanCharge_Passing.setFocusable(false);
                            edt_PassingDetail_LoanCharge_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getLand_details_check().equals("0")){
                            edt_PassingDetail_LandDetail_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_LandDetail_Passing.setFocusable(false);
                            edt_PassingDetail_LandDetail_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getCibil_score_check().equals("0")){
                            edt_PassingDetail_CibilScore_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_CibilScore_Passing.setFocusable(false);
                            edt_PassingDetail_CibilScore_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getFi_date_check().equals("0")){
                            edt_PassingDetail_FiDate_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_FiDate_Passing.setFocusable(false);
                            edt_PassingDetail_FiDate_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getSectiondate_check().equals("0")){
                            edt_PassingDetail_SanctionDate_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_SanctionDate_Passing.setFocusable(false);
                            edt_PassingDetail_SanctionDate_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getStage_check().equals("0")){
                            edt_PassingDetail_Stage_Passing .setFocusable(true);
                        }
                        else{
                            edt_PassingDetail_Stage_Passing.setFocusable(false);
                            edt_PassingDetail_Stage_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getSkim_check().equals("0")){
                            edt_CS_ConsumerSkim_Passing.setFocusable(true);
                        }else{
                            edt_CS_ConsumerSkim_Passing.setFocusable(false);
                            edt_CS_ConsumerSkim_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getAtype_check().equals("0")){
                            edt_CD_PaymentOption_Passing.setFocusable(true);
                        }else{
                            edt_CD_PaymentOption_Passing.setFocusable(false);
                            edt_CD_PaymentOption_Passing.setTextColor(Color.parseColor("#43a047"));
                        }


                        if(response.body().getData().get(id_pos).getCash_date_check().equals("0")){
                            edt_CashDetail_Date_Passing.setFocusable(true);
                        }else{
                            edt_CashDetail_Date_Passing.setFocusable(false);
                            edt_CashDetail_Date_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getCash_amount_check().equals("0")){
                            edt_CashDetail_Amount_Passing.setFocusable(true);
                        }else{
                            edt_CashDetail_Amount_Passing.setFocusable(false);
                            edt_CashDetail_Amount_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        if(response.body().getData().get(id_pos).getCash_description_check().equals("0")){
                            edt_CashDetail_Description_Passing.setFocusable(true);
                        }else{
                            edt_CashDetail_Description_Passing.setFocusable(false);
                            edt_CashDetail_Description_Passing.setTextColor(Color.parseColor("#43a047"));
                        }

                        /** ************************************************************************************* */

                       /* if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                            txt_CD_UploadBookingPhoto_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_UploadBookingPhoto_Passing.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                            txt_CD_AdharCard_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_AdharCard_Passing.setVisibility(View.GONE);
                        }


                        if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                            txt_CD_ElectionCard_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_ElectionCard_Passing.setVisibility(View.GONE);
                        }*/

                        /*if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                            txt_CD_PassportSize_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_PassportSize_Passing.setVisibility(View.GONE);
                        }*/

                        if(response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")){
                            txt_DownP_UploadCheque_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_DownP_UploadCheque_Passing.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")){
                            txt_DownP_UploadNEFT_RTGS_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_DownP_UploadNEFT_RTGS_Passing.setVisibility(View.GONE);
                        }


                        if(response.body().getData().get(id_pos).getNoc_photo_check().equals("0")){
                            txt_DownP_UploadNocPhoto_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_DownP_UploadNocPhoto_Passing.setVisibility(View.GONE);
                        }


                        if(response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")){
                            txt_DownP_UploadOldVehicle_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_DownP_UploadOldVehicle_Passing.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")){
                            txt_DownP_UploadRCBook_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_DownP_UploadRCBook_Passing.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getEc_photo_check().equals("0")){
                            txt_DownP_UploadElectionPhoto_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_DownP_UploadElectionPhoto_Passing.setVisibility(View.GONE);
                        }


                        //----------------------------------------------------

                       /* if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                            txt_CD_AdharCard_Passing2.setVisibility(View.VISIBLE);

                        }else{
                            txt_CD_AdharCard_Passing2.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getElection_back_check ().equals("0")){
                            txt_CD_ElectionCard_Passing2.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_ElectionCard_Passing2.setVisibility(View.GONE);
                        }*/

                        /*if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                            txt_CD_PassportSize_Passing2.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_PassportSize_Passing2.setVisibility(View.GONE);
                        }*/


                        if(response.body().getData().get(id_pos).getRcbook_back_check().equals("0")){
                            txt_DownP_UploadRCBook_Passing2.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_DownP_UploadRCBook_Passing2.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getElec_back_check().equals("0")){
                            txt_DownP_UploadElectionPhoto_Passing2.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_DownP_UploadElectionPhoto_Passing2.setVisibility(View.GONE);
                        }

                        //
                        if(response.body().getData().get(id_pos).getB_pass_back_check().equals("0")){
                            txt_LoanDetail_BankPassBook_Passing2.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_LoanDetail_BankPassBook_Passing2.setVisibility(View.GONE);
                        }


                        if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                            txt_LoanDetail_BankPassBook_Passing.setVisibility(View.VISIBLE);
                        } else {
                            txt_LoanDetail_BankPassBook_Passing.setVisibility(View.GONE);
                        }


                        if(response.body().getData().get(id_pos).getDelivry_photo_check().equals("0")){
                            txt_PassingPhoto_Passing.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_PassingPhoto_Passing.setVisibility(View.GONE);
                        }


                        if (response.body().getData().get(id_pos).getChasis_print_check().equals("0")) {
                            txt_ChesisPrint_Passing.setVisibility(View.VISIBLE);
                        } else {
                            txt_ChesisPrint_Passing.setVisibility(View.GONE);
                        }



                        /** ********************************************************************************** */

                        if (response.body().getData().get(id_pos).getStage().equals("Pending")) {
                            String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_PassingDetail_stageloan.setAdapter(adapterStage);

                            spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }


                        if (response.body().getData().get(id_pos).getStage().equals("Fi Done")) {
                            String[] Satge_data = {"Fi Done", "Pending", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_PassingDetail_stageloan.setAdapter(adapterStage);

                            spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        if (response.body().getData().get(id_pos).getStage().equals("Login Pending")) {
                            String[] Satge_data = {"Login Pending", "Pending", "Fi Done", "Login Done", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_PassingDetail_stageloan.setAdapter(adapterStage);

                            spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        if (response.body().getData().get(id_pos).getStage().equals("Login Done")) {
                            String[] Satge_data = {"Login Done", "Pending", "Fi Done", "Login Pending", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_PassingDetail_stageloan.setAdapter(adapterStage);

                            spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        if (response.body().getData().get(id_pos).getStage().equals("CIBIL Check")) {
                            String[] Satge_data = {"CIBIL Check", "Pending", "Fi Done", "Login Pending", "Login Done", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_PassingDetail_stageloan.setAdapter(adapterStage);

                            spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        if (response.body().getData().get(id_pos).getStage().equals("sanction")) {
                            String[] Satge_data = {"sanction", "Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_PassingDetail_stageloan.setAdapter(adapterStage);

                            spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }


                        if (response.body().getData().get(id_pos).getStage().equals("Reject")) {
                            String[] Satge_data = {"Reject", "Pending", "Fi Done", "Login Pending", "Login Done",
                                    "CIBIL Check", "sanction"};
                            ArrayAdapter adapterStage = new ArrayAdapter(PassingFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_PassingDetail_stageloan.setAdapter(adapterStage);

                            spn_PassingDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }


                        /** ********************************************************************************** */

                        String data =  response.body().getData().get(id_pos).getBooking_amt();//"cash,bank"
                        //  Toast.makeText(BookingPhaseOneActivity.this, ""+data, Toast.LENGTH_SHORT).show();
                        String[] res = data.split(",");

                        txtDPCashAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_CashAmount_Passing.setVisibility(View.GONE);

                        edt_DownP_BankOption_Passing.setVisibility(View.GONE);
                        edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                        edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_SelectMake_Passing.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_Passing.setVisibility(View.GONE);
                        edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_Passing.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_Passing.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                        edt_DownP_NOC_Passing.setVisibility(View.GONE);

                        lin_dp_cheque_Passing.setVisibility(View.GONE);
                        lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);
                        lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                        lin_dp_OldVehicle_Passing.setVisibility(View.GONE);
                        lin_dp_Rcbook_Passing.setVisibility(View.GONE);
                        lin_dp_Election_Passing.setVisibility(View.GONE);

                        lin_dp_Rcbook_Passing2.setVisibility(View.GONE);
                        lin_dp_Election_Passing2.setVisibility(View.GONE);

                        txtDPBankOption_Passing.setVisibility(View.GONE);
                        txtDPChequeDate_Passing.setVisibility(View.GONE);
                        txtDPChequeAmount_Passing.setVisibility(View.GONE);
                        txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                        txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                        txtDPMake_Passing.setVisibility(View.GONE);
                        txtDPManufectureYear_Passing.setVisibility(View.GONE);
                        txtDPOldAmount_Passing.setVisibility(View.GONE);
                        txtDPModelName_Passing.setVisibility(View.GONE);
                        txtDPPaperExpense_Passing.setVisibility(View.GONE);
                        txtDPOldTractorAmount_Passing.setVisibility(View.GONE);
                        txtDPNoc_Passing.setVisibility(View.GONE);


                        for (int i = 0; i < res.length; i++) {
                            mydata = res[i];
                            // Toast.makeText(BookingPhaseOneActivity.this, "yes" + mydata, Toast.LENGTH_SHORT).show();

                            String uu = mydata.trim();
                            // Log.e("TAG", "onResponse: " + uu);
                            //   Log.d("TAG", "onResponse: "+mydata);

                            if (uu.equals("Cash")) {
                                txtDPCashAmount_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_CashAmount_Passing.setVisibility(View.VISIBLE);
                                // Log.e("TAG", "onResponse:casting ");
                                // Toast.makeText(BookingPhaseOneActivity.this, "casting", Toast.LENGTH_SHORT).show();
                            }

                            if (uu.equals("Old Vehicle")) {
                                // Log.e("TAG", "onResponse:casting45 ");
                                txtDPMake_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_SelectMake_Passing.setVisibility(View.VISIBLE);
                                txtDPModelName_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_ModelVehicle_Passing.setVisibility(View.VISIBLE);
                                txtDPManufectureYear_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_ManufactureYear_Passing.setVisibility(View.VISIBLE);
                                txtDPOldAmount_Passing.setVisibility(View.VISIBLE);
                                txtDPPaperExpense_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_PaperExchange_Passing.setVisibility(View.VISIBLE);

                                if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                                   // edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                                    edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                                }
                                else {
                                   // edt_DownP_oldAmount_Passing.setVisibility(View.VISIBLE);
                                    edt_DownP_oldTractorAmount_Passing.setVisibility(View.VISIBLE);
                                }


                                if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                                    lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                                } else {
                                    lin_dp_NocPhoto_Passing.setVisibility(View.VISIBLE);
                                }

                                txtDPOldTractorAmount_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_oldAmount_Passing.setVisibility(View.VISIBLE);
                               // edt_DownP_oldTractorAmount_Passing.setVisibility(View.VISIBLE);
                                txtDPNoc_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_NOC_Passing.setVisibility(View.VISIBLE);

                                // lin_dp_NocPhoto_Passing.setVisibility(View.VISIBLE);
                                lin_dp_OldVehicle_Passing.setVisibility(View.VISIBLE);
                                lin_dp_Rcbook_Passing.setVisibility(View.VISIBLE);
                                lin_dp_Election_Passing.setVisibility(View.VISIBLE);

                                lin_dp_Rcbook_Passing2.setVisibility(View.VISIBLE);
                                lin_dp_Election_Passing2.setVisibility(View.VISIBLE);

                                //  Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();
                            }

                            if (uu.equals("Bank")) {
                                // Log.e("TAG", "onResponse:casting450 ");
                                // Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();

                                txtDPBankOption_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_BankOption_Passing.setVisibility(View.VISIBLE);

                                if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                                    txtDPChequeDate_Passing.setVisibility(View.VISIBLE);
                                    edt_DownP_ChequeDate_Passing.setVisibility(View.VISIBLE);
                                    txtDPChequeAmount_Passing.setVisibility(View.VISIBLE);
                                    edt_DownP_ChequeAmount_Passing.setVisibility(View.VISIBLE);
                                    lin_dp_cheque_Passing.setVisibility(View.VISIBLE);

                                    txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                                    edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                                    txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                                    edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                                    lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);
                                }

                                if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                                {
                                    txtDPNEFT_RTGSdate_Passing.setVisibility(View.VISIBLE);
                                    edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.VISIBLE);
                                    txtDPNEFT_RTGSAmount_Passing.setVisibility(View.VISIBLE);
                                    edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.VISIBLE);
                                    lin_dp_NEFT_RTGS_Passing.setVisibility(View.VISIBLE);

                                    txtDPChequeDate_Passing.setVisibility(View.GONE);
                                    edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                                    txtDPChequeAmount_Passing.setVisibility(View.GONE);
                                    edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                                    lin_dp_cheque_Passing.setVisibility(View.GONE);
                                }

                            }
                        }

                       /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Cash")){

                            txtDPCashAmount_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_CashAmount_Passing.setVisibility(View.VISIBLE);

                            edt_DownP_BankOption_Passing.setVisibility(View.GONE);
                            edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_SelectMake_Passing.setVisibility(View.GONE);
                            edt_DownP_ModelVehicle_Passing.setVisibility(View.GONE);
                            edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_ManufactureYear_Passing.setVisibility(View.GONE);
                            edt_DownP_PaperExchange_Passing.setVisibility(View.GONE);
                            edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_NOC_Passing.setVisibility(View.GONE);

                            lin_dp_cheque_Passing.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);
                            lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                            lin_dp_OldVehicle_Passing.setVisibility(View.GONE);
                            lin_dp_Rcbook_Passing.setVisibility(View.GONE);
                            lin_dp_Election_Passing.setVisibility(View.GONE);

                            lin_dp_Rcbook_Passing2.setVisibility(View.GONE);
                            lin_dp_Election_Passing2.setVisibility(View.GONE);

                            txtDPBankOption_Passing.setVisibility(View.GONE);
                            txtDPChequeDate_Passing.setVisibility(View.GONE);
                            txtDPChequeAmount_Passing.setVisibility(View.GONE);
                            txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                            txtDPMake_Passing.setVisibility(View.GONE);
                            txtDPManufectureYear_Passing.setVisibility(View.GONE);
                            txtDPOldAmount_Passing.setVisibility(View.GONE);
                            txtDPModelName_Passing.setVisibility(View.GONE);
                            txtDPPaperExpense_Passing.setVisibility(View.GONE);
                            txtDPOldTractorAmount_Passing.setVisibility(View.GONE);
                            txtDPNoc_Passing.setVisibility(View.GONE);
                        }*/


                        /*if(response.body().getData().get(id_pos).getBooking_amt().equals("Bank")){

                            txtDPCashAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_CashAmount_Passing.setVisibility(View.GONE);

                            txtDPBankOption_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_BankOption_Passing.setVisibility(View.VISIBLE);

                            if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                                txtDPChequeDate_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_ChequeDate_Passing.setVisibility(View.VISIBLE);
                                txtDPChequeAmount_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_ChequeAmount_Passing.setVisibility(View.VISIBLE);
                                lin_dp_cheque_Passing.setVisibility(View.VISIBLE);

                                txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                                edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                                txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                                edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                                lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);

                                txtDPMake_Passing.setVisibility(View.GONE);
                                edt_DownP_SelectMake_Passing.setVisibility(View.GONE);
                                txtDPModelName_Passing.setVisibility(View.GONE);
                                edt_DownP_ModelVehicle_Passing.setVisibility(View.GONE);
                                txtDPManufectureYear_Passing.setVisibility(View.GONE);
                                edt_DownP_ManufactureYear_Passing.setVisibility(View.GONE);
                                txtDPOldAmount_Passing.setVisibility(View.GONE);
                                edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                                txtDPPaperExpense_Passing.setVisibility(View.GONE);
                                edt_DownP_PaperExchange_Passing.setVisibility(View.GONE);
                                txtDPOldTractorAmount_Passing.setVisibility(View.GONE);
                                edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                                txtDPNoc_Passing.setVisibility(View.GONE);
                                edt_DownP_NOC_Passing.setVisibility(View.GONE);

                                lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                                lin_dp_OldVehicle_Passing.setVisibility(View.GONE);
                                lin_dp_Rcbook_Passing.setVisibility(View.GONE);
                                lin_dp_Election_Passing.setVisibility(View.GONE);
                                lin_dp_Rcbook_Passing2.setVisibility(View.GONE);
                                lin_dp_Election_Passing2.setVisibility(View.GONE);
                            }

                            if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                            {
                                txtDPNEFT_RTGSdate_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.VISIBLE);
                                txtDPNEFT_RTGSAmount_Passing.setVisibility(View.VISIBLE);
                                edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.VISIBLE);
                                lin_dp_NEFT_RTGS_Passing.setVisibility(View.VISIBLE);

                                txtDPChequeDate_Passing.setVisibility(View.GONE);
                                edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                                txtDPChequeAmount_Passing.setVisibility(View.GONE);
                                edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                                lin_dp_cheque_Passing.setVisibility(View.GONE);

                                txtDPMake_Passing.setVisibility(View.GONE);
                                edt_DownP_SelectMake_Passing.setVisibility(View.GONE);
                                txtDPModelName_Passing.setVisibility(View.GONE);
                                edt_DownP_ModelVehicle_Passing.setVisibility(View.GONE);
                                txtDPManufectureYear_Passing.setVisibility(View.GONE);
                                edt_DownP_ManufactureYear_Passing.setVisibility(View.GONE);
                                txtDPOldAmount_Passing.setVisibility(View.GONE);
                                edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                                txtDPPaperExpense_Passing.setVisibility(View.GONE);
                                edt_DownP_PaperExchange_Passing.setVisibility(View.GONE);
                                txtDPOldTractorAmount_Passing.setVisibility(View.GONE);
                                edt_DownP_oldTractorAmount_Passing.setVisibility(View.GONE);
                                txtDPNoc_Passing.setVisibility(View.GONE);
                                edt_DownP_NOC_Passing.setVisibility(View.GONE);

                                lin_dp_NocPhoto_Passing.setVisibility(View.GONE);
                                lin_dp_OldVehicle_Passing.setVisibility(View.GONE);
                                lin_dp_Rcbook_Passing.setVisibility(View.GONE);
                                lin_dp_Election_Passing.setVisibility(View.GONE);
                                lin_dp_Rcbook_Passing2.setVisibility(View.GONE);
                                lin_dp_Election_Passing2.setVisibility(View.GONE);
                            }

                        }*/

                        //Old Vehicle
                       /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Old Vehicle")){

                            txtDPMake_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_SelectMake_Passing.setVisibility(View.VISIBLE);
                            txtDPModelName_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_ModelVehicle_Passing.setVisibility(View.VISIBLE);
                            txtDPManufectureYear_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_ManufactureYear_Passing.setVisibility(View.VISIBLE);
                            txtDPOldAmount_Passing.setVisibility(View.VISIBLE);
                            txtDPPaperExpense_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_PaperExchange_Passing.setVisibility(View.VISIBLE);

                            if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                                edt_DownP_oldAmount_Passing.setVisibility(View.GONE);
                            }
                            else {
                                edt_DownP_oldAmount_Passing.setVisibility(View.VISIBLE);
                            }

                            txtDPOldTractorAmount_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_oldTractorAmount_Passing.setVisibility(View.VISIBLE);
                            txtDPNoc_Passing.setVisibility(View.VISIBLE);
                            edt_DownP_NOC_Passing.setVisibility(View.VISIBLE);

                            lin_dp_NocPhoto_Passing.setVisibility(View.VISIBLE);
                            lin_dp_OldVehicle_Passing.setVisibility(View.VISIBLE);
                            lin_dp_Rcbook_Passing.setVisibility(View.VISIBLE);
                            lin_dp_Election_Passing.setVisibility(View.VISIBLE);

                            lin_dp_Rcbook_Passing2.setVisibility(View.VISIBLE);
                            lin_dp_Election_Passing2.setVisibility(View.VISIBLE);


                            txtDPCashAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_CashAmount_Passing.setVisibility(View.GONE);
                            txtDPBankOption_Passing.setVisibility(View.GONE);
                            edt_DownP_BankOption_Passing.setVisibility(View.GONE);
                            txtDPChequeDate_Passing.setVisibility(View.GONE);
                            edt_DownP_ChequeDate_Passing.setVisibility(View.GONE);
                            txtDPChequeAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_Passing.setVisibility(View.GONE);
                            lin_dp_cheque_Passing.setVisibility(View.GONE);
                            txtDPNEFT_RTGSdate_Passing.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date_Passing.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_Passing.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_Passing.setVisibility(View.GONE);
                        }*/


                        if(response.body().getData().get(id_pos).getRto().equals("No")){
                            edt_RTO_RtoTax_Passing.setVisibility(View.GONE);
                            edt_RTO_RtoPassing_Passing.setVisibility(View.GONE);
                            edt_RTO_Insurance_Passing.setVisibility(View.GONE);
                            edt_RTO_AgentFees_Passing.setVisibility(View.GONE);
                            edt_RTO_NumberPlat_Passing.setVisibility(View.GONE);
                            edt_RTO_LoanCharge_Passing.setVisibility(View.GONE);


                            txtRTOTax.setVisibility(View.GONE);
                            txtRTOPassing.setVisibility(View.GONE);
                            txtInsurance.setVisibility(View.GONE);
                            txtAgentFees.setVisibility(View.GONE);
                            txtNumberPlat.setVisibility(View.GONE);
                            txtLoanCharge.setVisibility(View.GONE);
                        }

                        else{
                            edt_RTO_RtoTax_Passing.setVisibility(View.VISIBLE);
                            edt_RTO_RtoPassing_Passing.setVisibility(View.VISIBLE);
                            edt_RTO_Insurance_Passing.setVisibility(View.VISIBLE);
                            edt_RTO_AgentFees_Passing.setVisibility(View.VISIBLE);
                            edt_RTO_NumberPlat_Passing.setVisibility(View.VISIBLE);
                            edt_RTO_LoanCharge_Passing.setVisibility(View.VISIBLE);

                            txtRTOTax.setVisibility(View.VISIBLE);
                            txtRTOPassing.setVisibility(View.VISIBLE);
                            txtInsurance.setVisibility(View.VISIBLE);
                            txtAgentFees.setVisibility(View.VISIBLE);
                            txtNumberPlat.setVisibility(View.VISIBLE);
                            txtLoanCharge.setVisibility(View.VISIBLE);
                        }

                        if(response.body().getData().get(id_pos).getSkim().equals("No")){
                            lin_ConsumerSkim_passing.setVisibility(View.GONE);
                        }
                        else{
                            lin_ConsumerSkim_passing.setVisibility(View.VISIBLE);
                        }


                        /*if(response.body().getData().get(id_pos).getAtype().equals("Loan")){
                            lin_cashLoan_Passing.setVisibility(View.GONE);
                            lin_Loan_Passing_form.setVisibility(View.VISIBLE);
                        }
                        else {
                            lin_cashLoan_Passing.setVisibility(View.VISIBLE);
                            lin_Loan_Passing_form.setVisibility(View.GONE);
                        }*/

                        if (response.body().getData().get(id_pos).getAtype().equals("Loan")) {
                            lin_Loan_Passing_form.setVisibility(View.VISIBLE);
                            lin_cashLoan_Passing.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {
                            lin_Loan_Passing_form.setVisibility(View.GONE);
                            lin_cashLoan_Passing.setVisibility(View.VISIBLE);
                        }


                        if (response.body().getData().get(id_pos).getAtype().equals("Cash-Loan")) {
                            lin_Loan_Passing_form.setVisibility(View.VISIBLE);
                            lin_cashLoan_Passing.setVisibility(View.VISIBLE);
                        }


                        if (response.body().getData().get(id_pos).getStage_check().equals("0")) {

                            StageFinalVal = Stage;

                        } else {

                            StageFinalVal = edt_PassingDetail_Stage_Passing.getText().toString();
                        }

                        if( StageFinalVal == null){
                            StageFinalVal=" ";
                        }


                        edt_ADBooking_BookingNo_Passing.setText(response.body().getData().get(id_pos).getBno());
                        edt_ADBooking_BookingType_Passing.setText(response.body().getData().get(id_pos).getB_type());
                        edt_ADBooking_BookingDate_Passing.setText(response.body().getData().get(id_pos).getB_date());
                        edt_ADBooking_BookingLoginName_Passing.setText(response.body().getData().get(id_pos).getEmp());

                        // Toast.makeText(DeliveryFormActivity.this, ""+id_item, Toast.LENGTH_SHORT).show();

               /* edt_ADBooking_BookingNo_Passing.setText(response.body().getData().get(id_item).getBno());
                edt_ADBooking_BookingType_Passing.setText(response.body().getData().get(id_item).getB_type());
                edt_ADBooking_BookingDate_Passing.setText(response.body().getData().get(id_item).getB_date());
                edt_ADBooking_BookingLoginName_Passing.setText(response.body().getData().get(id_item).getEmp());*/


                        edt_CD_Fname_Passing.setText(response.body().getData().get(id_pos).getFname());
                        edt_CD_LastName_Passing.setText(response.body().getData().get(id_pos).getLname()+" ");
                        edt_CD_Surname_Passing.setText(response.body().getData().get(id_pos).getSname());
                        edt_CD_MobileNo_Passing.setText(response.body().getData().get(id_pos).getMobileno());
                        edt_CD_WhatsappNo_Passing.setText(response.body().getData().get(id_pos).getWhno());
                        edt_CD_ReferenceName_Passing.setText(response.body().getData().get(id_pos).getRef_name());
                        edt_CD_ReferenceNo_Passing.setText(response.body().getData().get(id_pos).getRef_no());
                        edt_CD_State_Passing.setText(response.body().getData().get(id_pos).getState());
                        edt_CD_City_Passing.setText(response.body().getData().get(id_pos).getCity());
                        edt_CD_District_Passing.setText(response.body().getData().get(id_pos).getDistric());
                        edt_CD_Village_Passing.setText(response.body().getData().get(id_pos).getVillage());
                        edt_CD_EmployeName_Passing.setText(response.body().getData().get(id_pos).getEmp());
                        edt_CD_Taluko_Passing.setText(response.body().getData().get(id_pos).getTehsill());
                        edt_CD_PassBook_Passing.setText(response.body().getData().get(id_pos).getB_p_photo());
                        edt_CD_ChequeBook_Passing.setText(response.body().getData().get(id_pos).getCheck_photo());
                        edt_CD_PaymentOption_Passing.setText(response.body().getData().get(id_pos).getAtype());


                        edt_PD_PurchaseName_Passing.setText(response.body().getData().get(id_pos).getProduct_name());
                        edt_PD_ModelName_Passing.setText(response.body().getData().get(id_pos).getModel_name());
                        edt_PD_Description_Passing.setText(response.body().getData().get(id_pos).getP_desc());


                        edt_PriceD_price_Passing.setText(response.body().getData().get(id_pos).getDeal_price());
                        edt_PriceD_priceInWord_Passing.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                        edt_PriceD_GST_Passing.setText(response.body().getData().get(id_pos).getGst());


                        edt_RTO_RtoMain_Passing.setText(response.body().getData().get(id_pos).getRto());
                        edt_RTO_RtoTax_Passing.setText(response.body().getData().get(id_pos).getRto_tax());
                        edt_RTO_RtoPassing_Passing.setText(response.body().getData().get(id_pos).getRto_passing());
                        edt_RTO_Insurance_Passing.setText(response.body().getData().get(id_pos).getInsurance());
                        edt_RTO_AgentFees_Passing.setText(response.body().getData().get(id_pos).getAgent_fee());
                        edt_RTO_NumberPlat_Passing.setText(response.body().getData().get(id_pos).getNumber_plat());
                        edt_RTO_LoanCharge_Passing.setText(response.body().getData().get(id_pos).getLoan_charge());

                        edt_DownP_BookingAmount_Passing.setText(response.body().getData().get(id_pos).getBooking_amt());
                        edt_DownP_CashAmount_Passing.setText(response.body().getData().get(id_pos).getAmount());
                        edt_DownP_BankOption_Passing.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                        edt_DownP_ChequeDate_Passing.setText(response.body().getData().get(id_pos).getCheck_date());
                        edt_DownP_ChequeAmount_Passing.setText(response.body().getData().get(id_pos).getCheck_amt());
                        edt_DownP_NEFT_RTGS_date_Passing.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                        edt_DownP_NEFT_RTGSAmount_Passing.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                        edt_DownP_SelectMake_Passing.setText(response.body().getData().get(id_pos).getMake());
                        edt_DownP_ModelVehicle_Passing.setText(response.body().getData().get(id_pos).getModel());
                        edt_DownP_oldAmount_Passing.setText(response.body().getData().get(id_pos).getOld_t_amount());
                        edt_DownP_ManufactureYear_Passing.setText(response.body().getData().get(id_pos).getM_year());
                        edt_DownP_PaperExchange_Passing.setText(response.body().getData().get(id_pos).getPaper_expence());
                        edt_DownP_oldTractorAmount_Passing.setText(response.body().getData().get(id_pos).getC_amount());
                        edt_DownP_NOC_Passing.setText(response.body().getData().get(id_pos).getNoc());


                        edt_CS_ConsumerSkim_Passing.setText(response.body().getData().get(id_pos).getSkim());
                        edt_CS_Hood_Passing.setText(response.body().getData().get(id_pos).getHood());
                        edt_CS_TopLink_Passing.setText(response.body().getData().get(id_pos).getToplink());
                        edt_CS_DrawBar_Passing.setText(response.body().getData().get(id_pos).getDrowbar());
                        edt_CS_ToolKit_Passing.setText(response.body().getData().get(id_pos).getToolkit());
                        edt_CS_Bumper_Passing.setText(response.body().getData().get(id_pos).getBumper());
                        edt_CS_Hitch_Passing.setText(response.body().getData().get(id_pos).getHitch());
                        edt_CS_Description_Passing.setText(response.body().getData().get(id_pos).getDescription());


                        edt_PassingDetail_REF_Passing.setText(response.body().getData().get(id_pos).getR_e_name());
                        edt_PassingDetail_FinanceForm_Passing.setText(response.body().getData().get(id_pos).getFinance_from());
                        edt_PassingDetail_LoanAmount_Passing.setText(response.body().getData().get(id_pos).getLoan_amount());
                        edt_PassingDetail_LoanSancAmont_Passing.setText(response.body().getData().get(id_pos).getL_sec_amt());
                        edt_PassingDetail_LoanCharge_Passing.setText(response.body().getData().get(id_pos).getLloan_charge());
                        edt_PassingDetail_LandDetail_Passing.setText(response.body().getData().get(id_pos).getLand_details());
                        edt_PassingDetail_CibilScore_Passing.setText(response.body().getData().get(id_pos).getCibil_score());
                        edt_PassingDetail_FiDate_Passing.setText(response.body().getData().get(id_pos).getFi_date());
                        edt_PassingDetail_SanctionDate_Passing.setText(response.body().getData().get(0).getSectiondate());
                        edt_PassingDetail_Stage_Passing.setText(response.body().getData().get(id_pos).getStage());


              /*  edt_modelName_Passing.setText(response.body().getData().get(0).getDmodelname()+"");
                edt_ChesisNumber_Passing.setText(response.body().getData().get(0).getChasisno()+"");
                edt_EngineNumber_Passing.setText(response.body().getData().get(0).getEngineno()+"");
                edt_Variente_Passing.setText(response.body().getData().get(0).getVariants()+"");*/

                        edt_Type_Passing.setText(response.body().getData().get(id_pos).getTyre()+"");
                        edt_Bettry_Passing.setText(response.body().getData().get(id_pos).getBattery()+"");
                        edt_PassingDate_Passing.setText(response.body().getData().get(id_pos).getDelivery_date()+"");

                        //cash Details
                        edt_CashDetail_Date_Passing.setText(response.body().getData().get(id_pos).getCash_date());
                        edt_CashDetail_Amount_Passing.setText(response.body().getData().get(id_pos).getCash_amount());
                        edt_CashDetail_Description_Passing.setText(response.body().getData().get(id_pos).getCash_description());

                    }

                    @Override
                    public void onFailure(@NotNull Call<PassingDataModel> call, @NotNull Throwable t) {

                        progressDialog.dismiss();
                        Log.d("Tag", "onFailure: "+t.getCause());
                    }
                });
            }
        });
        
        
          txt_CD_UploadBookingPhoto_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        txt_CD_AdharCard_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        txt_CD_ElectionCard_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });

        txt_CD_PassportSize_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });


        txt_DownP_UploadCheque_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });

        txt_DownP_UploadNEFT_RTGS_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

        txt_DownP_UploadNocPhoto_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_DownP_UploadOldVehicle_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });

        txt_DownP_UploadRCBook_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });

        txt_DownP_UploadElectionPhoto_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 11);
            }
        });


        txt_LoanDetail_BankPassBook_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 12);
            }
        });

        txt_LoanDetail_Cheque_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 13);
            }
        });

        txt_LoanDetail_SarpanchId_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 14);
            }
        });

        txt_LoanDetail_SignatureVeri_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 15);
            }
        });


        txt_PassingPhoto_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 16);
            }
        });

         txt_ChesisPrint_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 17);
            }
        });

         //--------------------------Passing Detail---------------------------------------

        ArrayAdapter adapterStage = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Y_N);
        spn_number_plate_order.setAdapter(adapterStage);

        spn_number_plate_order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                number_plate_order = Y_N.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter adapterStage1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Y_N);
        spn_number_plate_Recive.setAdapter(adapterStage1);

        spn_number_plate_Recive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                number_plate_Recive = Y_N.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapterStage2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Y_N);
        spn_RC_Book_Update.setAdapter(adapterStage2);

        spn_RC_Book_Update.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                RC_Book_Update = Y_N.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

         //-------------------------------------------------------------------------------

         txt_CD_AdharCard_Passing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 18);
            }
        });

        txt_CD_ElectionCard_Passing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 19);
            }
        });

        txt_CD_PassportSize_Passing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 20);
            }
        });


         txt_DownP_UploadRCBook_Passing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 21);
            }
        });

        txt_DownP_UploadElectionPhoto_Passing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 22);
            }
        });

        txt_LoanDetail_BankPassBook_Passing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 23);
            }
        });

        //-------------------------------------------------------------------------------------



        edt_PassingDate_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassingFormActivity.this,
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
                        edt_PassingDate_Passing.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_PassingDate_Passing.setFocusable(false);

        edt_PassingDetail_SanctionDate_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DatePickerDialog datePickerDialog = new DatePickerDialog(PassingFormActivity.this,
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
                        edt_PassingDetail_SanctionDate_Passing.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_PassingDetail_SanctionDate_Passing.setFocusable(false);

        edt_CashDetail_Date_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DatePickerDialog datePickerDialog = new DatePickerDialog(PassingFormActivity.this,
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
                        edt_CashDetail_Date_Passing.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_CashDetail_Date_Passing.setFocusable(false);



        btn_Passing_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                progressDialog = new ProgressDialog(PassingFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                
                
                MultipartBody.Part CD_UploadBookingPhoto_Passing = null;
                MultipartBody.Part CD_AdharCard_Passing  = null;
                MultipartBody.Part CD_ElectionCard_Passing  = null;
                MultipartBody.Part CD_PassportSize_Passing  = null;
                MultipartBody.Part UploadDPCheque_Passing  = null;
                MultipartBody.Part UploadDPNEFT_RTGS_Passing = null;
                MultipartBody.Part UploadDPNocPhoto_Passing = null;
                MultipartBody.Part UploadDPOldVehicle_Passing = null;
                MultipartBody.Part UploadDPRCBook_Passing = null;
                MultipartBody.Part UploadDPElectionPhoto_Passing = null;

                MultipartBody.Part LoanDetail_BankPassBook_Passing = null;
                MultipartBody.Part LoanDetail_Cheque_Passing = null;
                MultipartBody.Part LoanDetail_SarpanchId_Passing = null;
                MultipartBody.Part LoanDetail_SignatureVeri_Passing = null;

                MultipartBody.Part DeliveryPhoto_Passing_Passing = null;
                MultipartBody.Part ChesisPrint_Passing = null;


                MultipartBody.Part CD_AdharCard_Passing2  = null;
                MultipartBody.Part CD_ElectionCard_Passing2  = null;
                MultipartBody.Part CD_PassportSize_Passing2  = null;

                MultipartBody.Part UploadDPRCBook_Passing2 = null;
                MultipartBody.Part UploadDPElectionPhoto_Passing2 = null;
                MultipartBody.Part LoanDetail_BankPassBook_Passing2 = null;


                if(waypath_CD_UploadBookingPhoto_Passing != null){
                    File file1 = new File(waypath_CD_UploadBookingPhoto_Passing);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    CD_UploadBookingPhoto_Passing = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }

                if(waypath_CD_AdharCard_Passing != null){
                    File file2 = new File(waypath_CD_AdharCard_Passing);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    CD_AdharCard_Passing = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }

                if(waypath_CD_ElectionCard_Passing != null){
                    File file3 = new File(waypath_CD_ElectionCard_Passing);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    CD_ElectionCard_Passing = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                if(waypath_CD_PassportSize_Passing != null){
                    File file4 = new File(waypath_CD_PassportSize_Passing);
                    final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                    CD_PassportSize_Passing = MultipartBody.Part.createFormData("image6",
                            file4.getName(), requestBody4);
                }


                if(waypathUploadDPCheque_Passing != null){
                    File file5 = new File(waypathUploadDPCheque_Passing);
                    final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                    UploadDPCheque_Passing = MultipartBody.Part.createFormData("image7",
                            file5.getName(), requestBody5);
                }

                if(waypathUploadDPNEFT_RTGS_Passing != null){
                    File file6 = new File(waypathUploadDPNEFT_RTGS_Passing);
                    final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                    UploadDPNEFT_RTGS_Passing = MultipartBody.Part.createFormData("image8",
                            file6.getName(), requestBody6);
                }


                if(waypathUploadDPOldVehicle_Passing != null){
                    File file8 = new File(waypathUploadDPOldVehicle_Passing);
                    final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                    UploadDPOldVehicle_Passing = MultipartBody.Part.createFormData("image9",
                            file8.getName(), requestBody8);
                }

                if(waypathUploadDPRCBook_Passing != null){
                    File file9 = new File(waypathUploadDPRCBook_Passing);
                    final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file9);
                    UploadDPRCBook_Passing = MultipartBody.Part.createFormData("image10",
                            file9.getName(), requestBody9);
                }

                if(waypathUploadDPElectionPhoto_Passing != null){
                    File file10 = new File(waypathUploadDPElectionPhoto_Passing);
                    final RequestBody requestBody10 = RequestBody.create(MediaType.parse("image/*"), file10);
                    UploadDPElectionPhoto_Passing = MultipartBody.Part.createFormData("image11",
                            file10.getName(), requestBody10);
                }

                if(waypathUploadDPNocPhoto_Passing != null){
                    File file7 = new File(waypathUploadDPNocPhoto_Passing);
                    final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                    UploadDPNocPhoto_Passing = MultipartBody.Part.createFormData("image12",
                            file7.getName(), requestBody7);
                }


                if(WayPath_LoanDetail_BankPassBook!= null){
                    File file11 = new File(WayPath_LoanDetail_BankPassBook);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file11);
                    LoanDetail_BankPassBook_Passing = MultipartBody.Part.createFormData("do_photo13",
                            file11.getName(), requestBody11);
                }

                if(WayPath_LoanDetail_Cheque!= null){
                    File file12 = new File(WayPath_LoanDetail_Cheque);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file12);
                    LoanDetail_Cheque_Passing = MultipartBody.Part.createFormData("do_photo14",
                            file12.getName(), requestBody11);
                }

                if(WayPath_LoanDetail_SarpanchId!= null){
                    File file13 = new File(WayPath_LoanDetail_SarpanchId);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file13);
                    LoanDetail_SarpanchId_Passing = MultipartBody.Part.createFormData("do_photo15",
                            file13.getName(), requestBody11);
                }

                if(WayPath_LoanDetail_SignatureVeri!= null){
                    File file14 = new File(WayPath_LoanDetail_SignatureVeri);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file14);
                    LoanDetail_SignatureVeri_Passing = MultipartBody.Part.createFormData("do_photo16",
                            file14.getName(), requestBody11);
                }

                if(Waypath_PassingPhoto_Passing!= null){
                    File file16 = new File(Waypath_PassingPhoto_Passing);
                    final RequestBody requestBody16 = RequestBody.create(MediaType.parse("image/*"), file16);
                    DeliveryPhoto_Passing_Passing = MultipartBody.Part.createFormData("do_photo17",
                            file16.getName(), requestBody16);
                }


                if(Waypath_ChesisPrint_Passing!= null){
                    File file15 = new File(Waypath_ChesisPrint_Passing);
                    final RequestBody requestBody15 = RequestBody.create(MediaType.parse("image/*"), file15);
                    ChesisPrint_Passing = MultipartBody.Part.createFormData("do_photo18",
                            file15.getName(), requestBody15);
                }




                //-------------------------------------------------------
                if(waypath_CD_AdharCard_Passing2 != null){
                    File file17 = new File(waypath_CD_AdharCard_Passing2);
                    final RequestBody requestBody17 = RequestBody.create(MediaType.parse("image/*"), file17);
                    CD_AdharCard_Passing2 = MultipartBody.Part.createFormData("imgg1",
                            file17.getName(), requestBody17);
                }

                if(waypath_CD_ElectionCard_Passing2 != null){
                    File file18 = new File(waypath_CD_ElectionCard_Passing2);
                    final RequestBody requestBody18 = RequestBody.create(MediaType.parse("image/*"), file18);
                    CD_ElectionCard_Passing2 = MultipartBody.Part.createFormData("imgg2",
                            file18.getName(), requestBody18);
                }

                if(waypath_CD_PassportSize_Passing2 != null){
                    File file19 = new File(waypath_CD_PassportSize_Passing2);
                    final RequestBody requestBody19 = RequestBody.create(MediaType.parse("image/*"), file19);
                    CD_PassportSize_Passing2 = MultipartBody.Part.createFormData("imgg3",
                            file19.getName(), requestBody19);
                }

                if(waypathUploadDPRCBook_Passing2 != null){
                    File file20 = new File(waypathUploadDPRCBook_Passing2);
                    final RequestBody requestBody20 = RequestBody.create(MediaType.parse("image/*"), file20);
                    UploadDPRCBook_Passing2 = MultipartBody.Part.createFormData("imgg4",
                            file20.getName(), requestBody20);
                }

                if(waypathUploadDPElectionPhoto_Passing2 != null){
                    File file21 = new File(waypathUploadDPElectionPhoto_Passing2);
                    final RequestBody requestBody21 = RequestBody.create(MediaType.parse("image/*"), file21);
                    UploadDPElectionPhoto_Passing2 = MultipartBody.Part.createFormData("imgg5",
                            file21.getName(), requestBody21);
                }


                if(WayPath_LoanDetail_BankPassBook2!= null){
                    File file22 = new File(WayPath_LoanDetail_BankPassBook2);
                    final RequestBody requestBody22 = RequestBody.create(MediaType.parse("image/*"), file22);
                    LoanDetail_BankPassBook_Passing2 = MultipartBody.Part.createFormData("imgg6",
                            file22.getName(), requestBody22);
                }

                if (edt_registration_number.getText().toString().equals("")){
                    progressDialog.dismiss();
                    edt_registration_number.setError("Enter Registration Number");
                    Toast.makeText(PassingFormActivity.this, "Enter Registration Number", Toast.LENGTH_SHORT).show();
                    return;
                }else if(number_plate_order.equals("Select Option")){
                    progressDialog.dismiss();
                    TextView errorText = (TextView)spn_number_plate_order.getSelectedView();
                    errorText.setError("Select Number Plate Order");
                    Toast.makeText(PassingFormActivity.this, "Select Number Plate Order", Toast.LENGTH_SHORT).show();
                    return;
                }else if (number_plate_Recive.equals("Select Option")){
                    progressDialog.dismiss();
                    TextView errorText = (TextView)spn_number_plate_Recive.getSelectedView();
                    errorText.setError("Select Number Plate Recive");
                    Toast.makeText(PassingFormActivity.this, "Select Number Plate Recive", Toast.LENGTH_SHORT).show();
                    return;
                }else if (RC_Book_Update.equals("Select Option")){
                    progressDialog.dismiss();
                    TextView errorText = (TextView)spn_RC_Book_Update.getSelectedView();
                    errorText.setError("Select Rc Book Update");
                    Toast.makeText(PassingFormActivity.this, "Select Rc Book Update", Toast.LENGTH_SHORT).show();
                    return;
                }
                //----------------------------------------------

                WebService.getClient().getDeliverySubmit(
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingNo_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingDate_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), emp),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Fname_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Surname_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_MobileNo_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_WhatsappNo_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceName_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceNo_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_State_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_City_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_District_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Taluko_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Village_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingLoginName_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingType_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PassBook_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ChequeBook_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_PurchaseName_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_ModelName_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_Description_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_price_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_priceInWord_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_GST_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoMain_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoTax_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoPassing_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_Insurance_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_AgentFees_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_NumberPlat_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_LoanCharge_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BookingAmount_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_CashAmount_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BankOption_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeDate_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeAmount_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGS_date_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGSAmount_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_SelectMake_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ModelVehicle_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ManufactureYear_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldAmount_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_PaperExchange_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldTractorAmount_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NOC_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hood_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_TopLink_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_DrawBar_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ToolKit_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Bumper_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hitch_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Description_Passing.getText().toString()),
                        CD_UploadBookingPhoto_Passing,
                        CD_AdharCard_Passing,
                        CD_ElectionCard_Passing ,
                        CD_PassportSize_Passing ,
                        UploadDPCheque_Passing  ,
                        UploadDPNEFT_RTGS_Passing,
                        UploadDPOldVehicle_Passing,
                        UploadDPRCBook_Passing,
                        UploadDPElectionPhoto_Passing,
                        UploadDPNocPhoto_Passing ,
                        RequestBody.create(MediaType.parse("text/plain"), idBookingUpload),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDetail_REF_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"),edt_PassingDetail_FinanceForm_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDetail_LoanAmount_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDetail_LoanSancAmont_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDetail_LoanCharge_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDetail_LandDetail_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDetail_CibilScore_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDetail_FiDate_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDetail_SanctionDate_Passing.getText().toString()),
                        /*RequestBody.create(MediaType.parse("text/plain"),edt_PassingDetail_Stage_Passing.getText().toString()),*/
                        RequestBody.create(MediaType.parse("text/plain"),StageFinalVal),
                        LoanDetail_BankPassBook_Passing ,
                        /*LoanDetail_Cheque_Passing ,
                        LoanDetail_SarpanchId_Passing ,*/
                        LoanDetail_SignatureVeri_Passing,
                        DeliveryPhoto_Passing_Passing,
                        ChesisPrint_Passing,
                        RequestBody.create(MediaType.parse("text/plain"), edt_PassingDate_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Type_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Bettry_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PaymentOption_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ConsumerSkim_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Date_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Amount_Passing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Description_Passing.getText().toString()),
                        CD_AdharCard_Passing2,
                        CD_ElectionCard_Passing2,
                        CD_PassportSize_Passing2,
                        UploadDPRCBook_Passing2,
                        UploadDPElectionPhoto_Passing2,
                        LoanDetail_BankPassBook_Passing2,
                        RequestBody.create(MediaType.parse("text/plain")," "),
                        RequestBody.create(MediaType.parse("text/plain"),number_plate_order),
                        RequestBody.create(MediaType.parse("text/plain"),number_plate_Recive),
                        RequestBody.create(MediaType.parse("text/plain"),RC_Book_Update),
                        RequestBody.create(MediaType.parse("text/plain"),edt_registration_number.getText().toString())
                ).enqueue(new Callback<DeliveryBookingModel>() {
                    @Override
                    public void onResponse(@NotNull Call<DeliveryBookingModel> call, @NotNull Response<DeliveryBookingModel> response) {
                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(PassingFormActivity.this, ""
                                        +response.body().getMessage()+" ",
                                Toast.LENGTH_LONG).show();

                        Intent i = new Intent(PassingFormActivity.this,
                                BookingDeliveryMainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(@NotNull Call<DeliveryBookingModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Log.d("Fail_data", "onFailure: "+t.getCause()+" "+t.getMessage());
                        Toast.makeText(PassingFormActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
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
                    uri_CD_UploadBookingPhoto_Passing = data.getData();
                    Log.d("PanImageUri", uri_CD_UploadBookingPhoto_Passing.toString());
                    waypath_CD_UploadBookingPhoto_Passing = getFilePath(this, uri_CD_UploadBookingPhoto_Passing);


                    Log.d("PanImage", waypath_CD_UploadBookingPhoto_Passing);
                    String[] name = waypath_CD_UploadBookingPhoto_Passing.split("/");
                    txt_CD_UploadBookingPhoto_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_Booking_Passing.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_Passing = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_Passing.toString());
                    waypath_CD_AdharCard_Passing = getFilePath(this, uri_CD_AdharCard_Passing);

                    Log.d("PanRTGS", waypath_CD_AdharCard_Passing);
                    String[] name = waypath_CD_AdharCard_Passing.split("/");
                    txt_CD_AdharCard_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_Passing.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_Passing = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_Passing.toString());
                    waypath_CD_ElectionCard_Passing = getFilePath(this, uri_CD_ElectionCard_Passing);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_Passing);
                    String[] name = waypath_CD_ElectionCard_Passing.split("/");
                    txt_CD_ElectionCard_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_Passing.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_Passing = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_Passing.toString());
                    waypath_CD_PassportSize_Passing = getFilePath(this, uri_CD_PassportSize_Passing);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_Passing);
                    String[] name = waypath_CD_PassportSize_Passing.split("/");
                    txt_CD_PassportSize_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_Passing.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPCheque_Passing = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPCheque_Passing.toString());
                    waypathUploadDPCheque_Passing = getFilePath(this, uriUploadDPCheque_Passing);
                    Log.d("Pan Image Uri", waypathUploadDPCheque_Passing);
                    String[] name = waypathUploadDPCheque_Passing.split("/");
                    txt_DownP_UploadCheque_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_Cheque_Passing.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNEFT_RTGS_Passing = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNEFT_RTGS_Passing.toString());
                    waypathUploadDPNEFT_RTGS_Passing = getFilePath(this, uriUploadDPNEFT_RTGS_Passing);
                    Log.d("Pan Image Uri", waypathUploadDPNEFT_RTGS_Passing);
                    String[] name = waypathUploadDPNEFT_RTGS_Passing.split("/");
                    txt_DownP_UploadNEFT_RTGS_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NEFT_RTGS_Passing.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNocPhoto_Passing = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNocPhoto_Passing.toString());
                    waypathUploadDPNocPhoto_Passing = getFilePath(this, uriUploadDPNocPhoto_Passing);
                    Log.d("Pan Image Uri", waypathUploadDPNocPhoto_Passing);
                    String[] name = waypathUploadDPNocPhoto_Passing.split("/");
                    txt_DownP_UploadNocPhoto_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NocPhoto_Passing.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPOldVehicle_Passing = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPOldVehicle_Passing.toString());
                    waypathUploadDPOldVehicle_Passing = getFilePath(this, uriUploadDPOldVehicle_Passing);
                    Log.d("Pan Image Uri", waypathUploadDPOldVehicle_Passing);
                    String[] name = waypathUploadDPOldVehicle_Passing.split("/");
                    txt_DownP_UploadOldVehicle_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_OldVehicle_Passing.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_Passing = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_Passing.toString());
                    waypathUploadDPRCBook_Passing = getFilePath(this, uriUploadDPRCBook_Passing);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_Passing);
                    String[] name = waypathUploadDPRCBook_Passing.split("/");
                    txt_DownP_UploadRCBook_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_Passing.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_Passing = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_Passing.toString());
                    waypathUploadDPElectionPhoto_Passing = getFilePath(this, uriUploadDPElectionPhoto_Passing);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_Passing);
                    String[] name = waypathUploadDPElectionPhoto_Passing.split("/");
                    txt_DownP_UploadElectionPhoto_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_Passing.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_BankPassBook_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook_Passing.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_Cheque_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_Cheque_Passing.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_SarpanchId_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SarpanchID_Passing.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_SignatureVeri_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SignatureVerifi_Passing.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_PassingPhoto_Passing = data.getData();
                    Log.d("Pan Image Uri", uri_PassingPhoto_Passing.toString());
                    Waypath_PassingPhoto_Passing = getFilePath(this, uri_PassingPhoto_Passing);
                    Log.d("Pan Image Uri", Waypath_PassingPhoto_Passing);
                    String[] name = Waypath_PassingPhoto_Passing.split("/");
                    txt_PassingPhoto_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_PassingPhoto_Passing.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 17) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_ChesisPrint_Passing = data.getData();
                    Log.d("Pan Image Uri", uri_ChesisPrint_Passing.toString());
                    Waypath_ChesisPrint_Passing = getFilePath(this, uri_ChesisPrint_Passing);
                    Log.d("Pan Image Uri", Waypath_ChesisPrint_Passing);
                    String[] name = Waypath_ChesisPrint_Passing.split("/");
                    txt_ChesisPrint_Passing.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_ChesisPrint_Passing.setImageURI(selectedImageUri);
                }
            }
        }


        //------------------------------------------------------------------------------------

        if (requestCode == 18) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_Passing2 = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_Passing2.toString());
                    waypath_CD_AdharCard_Passing2 = getFilePath(this, uri_CD_AdharCard_Passing2);

                    Log.d("PanRTGS", waypath_CD_AdharCard_Passing2);
                    String[] name = waypath_CD_AdharCard_Passing2.split("/");
                    txt_CD_AdharCard_Passing2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_Passing2.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_Passing2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_Passing2.toString());
                    waypath_CD_ElectionCard_Passing2 = getFilePath(this, uri_CD_ElectionCard_Passing2);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_Passing2);
                    String[] name = waypath_CD_ElectionCard_Passing2.split("/");
                    txt_CD_ElectionCard_Passing2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_Passing2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_Passing2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_Passing2.toString());
                    waypath_CD_PassportSize_Passing2 = getFilePath(this, uri_CD_PassportSize_Passing2);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_Passing2);
                    String[] name = waypath_CD_PassportSize_Passing2.split("/");
                    txt_CD_PassportSize_Passing2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_Passing2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 21) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_Passing2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_Passing2.toString());
                    waypathUploadDPRCBook_Passing2 = getFilePath(this, uriUploadDPRCBook_Passing2);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_Passing2);
                    String[] name = waypathUploadDPRCBook_Passing2.split("/");
                    txt_DownP_UploadRCBook_Passing2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_Passing2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 22) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_Passing2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_Passing2.toString());
                    waypathUploadDPElectionPhoto_Passing2 = getFilePath(this, uriUploadDPElectionPhoto_Passing2);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_Passing2);
                    String[] name = waypathUploadDPElectionPhoto_Passing2.split("/");
                    txt_DownP_UploadElectionPhoto_Passing2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_Passing2.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 23) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri_LoanDetail_BankPassBook2 = data.getData();
                    Log.d("Pan Image Uri", Uri_LoanDetail_BankPassBook2.toString());
                    WayPath_LoanDetail_BankPassBook2 = getFilePath(this, Uri_LoanDetail_BankPassBook2);
                    Log.d("Pan Image Uri", WayPath_LoanDetail_BankPassBook2);
                    String[] name = WayPath_LoanDetail_BankPassBook2.split("/");
                    txt_LoanDetail_BankPassBook_Passing2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook_Passing2.setImageURI(selectedImageUri);
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