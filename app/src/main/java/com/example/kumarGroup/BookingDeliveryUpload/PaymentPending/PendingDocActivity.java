package com.example.kumarGroup.BookingDeliveryUpload.PaymentPending;

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
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.DeliveryBookingModel;
import com.example.kumarGroup.PaymentPendingModel;
import com.example.kumarGroup.R;
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

public class PendingDocActivity extends AppCompatActivity {

    //Argument Details
    EditText edt_ADBooking_BookingNo_PenDoc, edt_ADBooking_BookingType_PenDoc, edt_ADBooking_BookingDate_PenDoc,
            edt_ADBooking_BookingLoginName_PenDoc;

    //Customer detail
    EditText edt_CD_Fname_PenDoc, edt_CD_LastName_PenDoc, edt_CD_Surname_PenDoc, edt_CD_MobileNo_PenDoc,
            edt_CD_WhatsappNo_PenDoc,
            edt_CD_ReferenceName_PenDoc, edt_CD_ReferenceNo_PenDoc, edt_CD_State_PenDoc, edt_CD_City_PenDoc,
            edt_CD_District_PenDoc,
            edt_CD_Taluko_PenDoc, edt_CD_Village_PenDoc, edt_CD_EmployeName_PenDoc, edt_CD_PassBook_PenDoc,
            edt_CD_ChequeBook_PenDoc, edt_CD_PaymentOption_PenDoc;


    ImageView img_CD_Booking_PenDoc, img_CD_AdharCard_PenDoc, img_CD_ElectionCard_PenDoc, img_CD_PassportSize_PenDoc;

    TextView txt_CD_UploadBookingPhoto_PenDoc, txt_CD_AdharCard_PenDoc, txt_CD_ElectionCard_PenDoc, txt_CD_PassportSize_PenDoc;

    TextView txt_CD_AdharCard_PenDoc2, txt_CD_ElectionCard_PenDoc2, txt_CD_PassportSize_PenDoc2;

    ImageView img_CD_AdharCard_PenDoc2, img_CD_ElectionCard_PenDoc2, img_CD_PassportSize_PenDoc2;

    //Product detail
    EditText edt_PD_PurchaseName_PenDoc, edt_PD_ModelName_PenDoc, edt_PD_Description_PenDoc;

    //Price detail
    EditText edt_PriceD_price_PenDoc, edt_PriceD_priceInWord_PenDoc, edt_PriceD_GST_PenDoc;

    //RTO Detail
    EditText edt_RTO_RtoMain_PenDoc, edt_RTO_RtoTax_PenDoc, edt_RTO_RtoPassing_PenDoc,
            edt_RTO_Insurance_PenDoc, edt_RTO_AgentFees_PenDoc, edt_RTO_NumberPlat_PenDoc,
            edt_RTO_LoanCharge_PenDoc;

    TextView txtRTOTax, txtRTOPassing, txtInsurance, txtAgentFees, txtNumberPlat, txtLoanCharge;

    //Down Payment
    EditText edt_DownP_BookingAmount_PenDoc, edt_DownP_CashAmount_PenDoc,
            edt_DownP_BankOption_PenDoc, edt_DownP_ChequeDate_PenDoc, edt_DownP_ChequeAmount_PenDoc,
            edt_DownP_NEFT_RTGSAmount_PenDoc, edt_DownP_NEFT_RTGS_date_PenDoc,
            edt_DownP_SelectMake_PenDoc, edt_DownP_ModelVehicle_PenDoc, edt_DownP_ManufactureYear_PenDoc,
            edt_DownP_oldAmount_PenDoc, edt_DownP_PaperExchange_PenDoc, edt_DownP_oldTractorAmount_PenDoc,
            edt_DownP_NOC_PenDoc;

    ImageView img_DownP_Cheque_PenDoc, img_DownP_NEFT_RTGS_PenDoc, img_DownP_NocPhoto_PenDoc,
            img_DownP_OldVehicle_PenDoc, img_DownP_RcBook_PenDoc, img_DownP_ElectionPhoto_PenDoc;

    ImageView img_DownP_RcBook_PenDoc2, img_DownP_ElectionPhoto_PenDoc2;

    TextView txt_DownP_UploadRCBook_PenDoc2, txt_DownP_UploadElectionPhoto_PenDoc2;

    TextView txt_DownP_UploadCheque_PenDoc, txt_DownP_UploadNEFT_RTGS_PenDoc, txt_DownP_UploadNocPhoto_PenDoc,
            txt_DownP_UploadOldVehicle_PenDoc, txt_DownP_UploadRCBook_PenDoc, txt_DownP_UploadElectionPhoto_PenDoc;

    //Down payment label
   /* TextView txtDPCashAmount_PenDoc,txtDPBankOption_PenDoc,txtDPChequeDate_PenDoc,txtDPChequeAmount_PenDoc,
            txtDPNEFT_RTGSdate_PenDoc,
            txtDPNEFT_RTGSAmount_PenDoc,txtDPMake_PenDoc,txtDPManufectureYear_PenDoc,
            txtDPOldAmount_PenDoc,txtDPModelName_PenDoc,
            txtDPPaperExpense_PenDoc,txtDPOldTractorAmount_PenDoc,txtDPNoc_PenDoc;*/

    LinearLayout lin_dp_cheque_PenDoc, lin_dp_NEFT_RTGS_PenDoc, lin_dp_NocPhoto_PenDoc,
            lin_dp_OldVehicle_PenDoc, lin_dp_Rcbook_PenDoc,
            lin_dp_Election_PenDoc, lin_dp_Rcbook_PenDoc2,
            lin_dp_Election_PenDoc2;

    //Consumer Details
    EditText edt_CS_Hood_PenDoc, edt_CS_TopLink_PenDoc, edt_CS_DrawBar_PenDoc, edt_CS_ToolKit_PenDoc, edt_CS_Bumper_PenDoc,
            edt_CS_Hitch_PenDoc, edt_CS_Description_PenDoc, edt_CS_ConsumerSkim_PenDoc;

    //Loan Details
    EditText edt_PenDocDetail_REF_PenDoc,
            edt_PenDocDetail_FinanceForm_PenDoc, edt_PenDocDetail_LoanAmount_PenDoc,
            edt_PenDocDetail_LoanSancAmont_PenDoc, edt_PenDocDetail_LoanCharge_PenDoc,
            edt_PenDocDetail_LandDetail_PenDoc, edt_PenDocDetail_CibilScore_PenDoc,
            edt_PenDocDetail_FiDate_PenDoc, edt_PenDocDetail_SanctionDate_PenDoc,
            edt_PenDocDetail_Stage_PenDoc;

    //  Spinner spn_PenDocDetail_FinanceForm_PenDoc,spn_PenDocDetail_Stage_PenDoc;

    ImageView img_LoanDetail_BankpassBook_PenDoc, img_LoanDetail_Cheque_PenDoc, img_LoanDetail_SarpanchID_PenDoc,
            img_LoanDetail_SignatureVerifi_PenDoc;

    ImageView img_LoanDetail_BankpassBook_PenDoc2;

    TextView txt_LoanDetail_BankPassBook_PenDoc2;


    TextView txt_LoanDetail_BankPassBook_PenDoc, txt_LoanDetail_Cheque_PenDoc, txt_LoanDetail_SarpanchId_PenDoc,
            txt_LoanDetail_SignatureVeri_PenDoc;


    //Delivery Details
    EditText edt_modelName_PenDoc, edt_ChesisNumber_PenDoc, edt_EngineNumber_PenDoc, edt_Variente_PenDoc,
            edt_Type_PenDoc, edt_Bettry_PenDoc, edt_PenDocDate_PenDoc;

    //   TextView txt_Tyre_PenDoc,txt_Battery_PenDoc;

    ImageView img_PenDocPhoto_PenDoc, img_ChesisPrint_PenDoc;

    TextView txt_PenDocPhoto_PenDoc, txt_ChesisPrint_PenDoc;


    //Cash Loan
    EditText edt_CashDetail_Date_PenDoc, edt_CashDetail_Amount_PenDoc, edt_CashDetail_Description_PenDoc;

    LinearLayout lin_cashLoan_PenDoc, lin_ConsumerSkim_PenDoc, lin_Loan_PenDoc_form;


    //***************************************************************************************************

    String WayPath_LoanDetail_BankPassBook, WayPath_LoanDetail_Cheque, WayPath_LoanDetail_SarpanchId,
            WayPath_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook, Uri_LoanDetail_Cheque, Uri_LoanDetail_SarpanchId,
            Uri_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook2;
    String WayPath_LoanDetail_BankPassBook2;

    Uri uri_PenDocPhoto_PenDoc, uri_ChesisPrint_PenDoc;

    String Waypath_PenDocPhoto_PenDoc, Waypath_ChesisPrint_PenDoc;

    Button btn_PenDoc_Submit;


    String Stage;
    String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};

    Spinner spn_PenDoc_stageloan;

    String StageFinalVal;

    List<String> FinanceName = new ArrayList<>();
    List<String> FInanceID = new ArrayList<>();

    String finance_name, finance_id;

    String idBookingUpload;

    String emp_id;

    SharedPreferences sp;
    String emp, empName;

    ProgressDialog progressDialog;

    Calendar mcurrentdate;
    int day, month, year;


    Uri uri_CD_UploadBookingPhoto_PenDoc, uri_CD_AdharCard_PenDoc, uri_CD_ElectionCard_PenDoc, uri_CD_PassportSize_PenDoc;

    Uri uri_CD_AdharCard_PenDoc2, uri_CD_ElectionCard_PenDoc2, uri_CD_PassportSize_PenDoc2;

    String waypath_CD_AdharCard_PenDoc2, waypath_CD_ElectionCard_PenDoc2, waypath_CD_PassportSize_PenDoc2;

    String waypath_CD_UploadBookingPhoto_PenDoc, waypath_CD_AdharCard_PenDoc, waypath_CD_ElectionCard_PenDoc,
            waypath_CD_PassportSize_PenDoc;

    Uri uriUploadDPCheque_PenDoc, uriUploadDPNEFT_RTGS_PenDoc, uriUploadDPNocPhoto_PenDoc,
            uriUploadDPOldVehicle_PenDoc, uriUploadDPRCBook_PenDoc,
            uriUploadDPElectionPhoto_PenDoc;

    Uri uriUploadDPRCBook_PenDoc2, uriUploadDPElectionPhoto_PenDoc2;

    String waypathUploadDPRCBook_PenDoc2, waypathUploadDPElectionPhoto_PenDoc2;

    String waypathUploadDPCheque_PenDoc, waypathUploadDPNEFT_RTGS_PenDoc, waypathUploadDPNocPhoto_PenDoc,
            waypathUploadDPOldVehicle_PenDoc, waypathUploadDPRCBook_PenDoc, waypathUploadDPElectionPhoto_PenDoc;

    String id_item;

    int id_pos;

    String mydata;

    int penVal = 0;

    String clearVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_doc);

        getSupportActionBar().setTitle("Pending Doc");


        //Agreement Details
        edt_ADBooking_BookingNo_PenDoc = findViewById(R.id.edt_ADBooking_BookingNo_PenDoc);
        edt_ADBooking_BookingType_PenDoc = findViewById(R.id.edt_ADBooking_BookingType_PenDoc);
        edt_ADBooking_BookingDate_PenDoc = findViewById(R.id.edt_ADBooking_BookingDate_PenDoc);
        edt_ADBooking_BookingLoginName_PenDoc = findViewById(R.id.edt_ADBooking_BookingLoginName_PenDoc);

        //Customer Detail
        edt_CD_Fname_PenDoc = findViewById(R.id.edt_CD_Fname_PenDoc);
        edt_CD_LastName_PenDoc = findViewById(R.id.edt_CD_LastName_PenDoc);
        edt_CD_Surname_PenDoc = findViewById(R.id.edt_CD_Surname_PenDoc);
        edt_CD_MobileNo_PenDoc = findViewById(R.id.edt_CD_MobileNo_PenDoc);
        edt_CD_WhatsappNo_PenDoc = findViewById(R.id.edt_CD_WhatsappNo_PenDoc);
        edt_CD_ReferenceName_PenDoc = findViewById(R.id.edt_CD_ReferenceName_PenDoc);
        edt_CD_ReferenceNo_PenDoc = findViewById(R.id.edt_CD_ReferenceNo_PenDoc);
        edt_CD_State_PenDoc = findViewById(R.id.edt_CD_State_PenDoc);
        edt_CD_City_PenDoc = findViewById(R.id.edt_CD_City_PenDoc);
        edt_CD_District_PenDoc = findViewById(R.id.edt_CD_District_PenDoc);
        edt_CD_Taluko_PenDoc = findViewById(R.id.edt_CD_Taluko_PenDoc);
        edt_CD_Village_PenDoc = findViewById(R.id.edt_CD_Village_PenDoc);
        edt_CD_EmployeName_PenDoc = findViewById(R.id.edt_CD_EmployeName_PenDoc);
        edt_CD_PassBook_PenDoc = findViewById(R.id.edt_CD_PassBook_PenDoc);
        edt_CD_ChequeBook_PenDoc = findViewById(R.id.edt_CD_ChequeBook_PenDoc);
        edt_CD_PaymentOption_PenDoc = findViewById(R.id.edt_CD_PaymentOption_PenDoc);

        txt_CD_UploadBookingPhoto_PenDoc = findViewById(R.id.txt_CD_UploadBookingPhoto_PenDoc);
        txt_CD_AdharCard_PenDoc = findViewById(R.id.txt_CD_AdharCard_PenDoc);
        txt_CD_ElectionCard_PenDoc = findViewById(R.id.txt_CD_ElectionCard_PenDoc);
      /*  txt_CD_BankPassBook =findViewById(R.id.txt_CD_BankPassBook);
        txt_CD_Cheque =findViewById(R.id.txt_CD_Cheque);*/
        txt_CD_PassportSize_PenDoc = findViewById(R.id.txt_CD_PassportSize_PenDoc);

        txt_CD_AdharCard_PenDoc2 = findViewById(R.id.txt_CD_AdharCard_PenDoc2);
        txt_CD_ElectionCard_PenDoc2 = findViewById(R.id.txt_CD_ElectionCard_PenDoc2);
        txt_CD_PassportSize_PenDoc2 = findViewById(R.id.txt_CD_PassportSize_PenDoc2);

        img_CD_Booking_PenDoc = findViewById(R.id.img_CD_Booking_PenDoc);
        img_CD_AdharCard_PenDoc = findViewById(R.id.img_CD_AdharCard_PenDoc);
        img_CD_ElectionCard_PenDoc = findViewById(R.id.img_CD_ElectionCard_PenDoc);
       /* img_CD_BankPassBook=findViewById(R.id.img_CD_BankPassBook);
        img_CD_Cheque=findViewById(R.id.img_CD_Cheque);*/
        img_CD_PassportSize_PenDoc = findViewById(R.id.img_CD_PassportSize_PenDoc);

        img_CD_AdharCard_PenDoc2 = findViewById(R.id.img_CD_AdharCard_PenDoc2);
        img_CD_ElectionCard_PenDoc2 = findViewById(R.id.img_CD_ElectionCard_PenDoc2);
        img_CD_PassportSize_PenDoc2 = findViewById(R.id.img_CD_PassportSize_PenDoc2);

        //Product Details
        edt_PD_PurchaseName_PenDoc = findViewById(R.id.edt_PD_PurchaseName_PenDoc);
        edt_PD_ModelName_PenDoc = findViewById(R.id.edt_PD_ModelName_PenDoc);
        edt_PD_Description_PenDoc = findViewById(R.id.edt_PD_Description_PenDoc);


        //Price Details
        edt_PriceD_price_PenDoc = findViewById(R.id.edt_PriceD_price_PenDoc);
        edt_PriceD_priceInWord_PenDoc = findViewById(R.id.edt_PriceD_priceInWord_PenDoc);
        edt_PriceD_GST_PenDoc = findViewById(R.id.edt_PriceD_GST_PenDoc);

        //RTO Details
        edt_RTO_RtoMain_PenDoc = findViewById(R.id.edt_RTO_RtoMain_PenDoc);
        edt_RTO_RtoTax_PenDoc = findViewById(R.id.edt_RTO_RtoTax_PenDoc);
        edt_RTO_RtoPassing_PenDoc = findViewById(R.id.edt_RTO_RtoPassing_PenDoc);
        edt_RTO_Insurance_PenDoc = findViewById(R.id.edt_RTO_Insurance_PenDoc);
        edt_RTO_AgentFees_PenDoc = findViewById(R.id.edt_RTO_AgentFees_PenDoc);
        edt_RTO_NumberPlat_PenDoc = findViewById(R.id.edt_RTO_NumberPlat_PenDoc);
        edt_RTO_LoanCharge_PenDoc = findViewById(R.id.edt_RTO_LoanCharge_PenDoc);


        txtRTOTax = findViewById(R.id.txtRTOTax);
        txtRTOPassing = findViewById(R.id.txtRTOPassing);
        txtInsurance = findViewById(R.id.txtInsurance);
        txtAgentFees = findViewById(R.id.txtAgentFees);
        txtNumberPlat = findViewById(R.id.txtNumberPlat);
        txtLoanCharge = findViewById(R.id.txtLoanCharge);


        //Down Payment
        edt_DownP_BookingAmount_PenDoc = findViewById(R.id.edt_DownP_BookingAmount_PenDoc);
        edt_DownP_CashAmount_PenDoc = findViewById(R.id.edt_DownP_CashAmount_PenDoc);
        edt_DownP_BankOption_PenDoc = findViewById(R.id.edt_DownP_BankOption_PenDoc);
        edt_DownP_ChequeDate_PenDoc = findViewById(R.id.edt_DownP_ChequeDate_PenDoc);
        edt_DownP_ChequeAmount_PenDoc = findViewById(R.id.edt_DownP_ChequeAmount_PenDoc);
        edt_DownP_NEFT_RTGS_date_PenDoc = findViewById(R.id.edt_DownP_NEFT_RTGS_date_PenDoc);
        edt_DownP_NEFT_RTGSAmount_PenDoc = findViewById(R.id.edt_DownP_NEFT_RTGSAmount_PenDoc);
        edt_DownP_SelectMake_PenDoc = findViewById(R.id.edt_DownP_SelectMake_PenDoc);
        edt_DownP_ModelVehicle_PenDoc = findViewById(R.id.edt_DownP_ModelVehicle_PenDoc);
        edt_DownP_oldAmount_PenDoc = findViewById(R.id.edt_DownP_oldAmount_PenDoc);
        edt_DownP_ManufactureYear_PenDoc = findViewById(R.id.edt_DownP_ManufactureYear_PenDoc);
        edt_DownP_PaperExchange_PenDoc = findViewById(R.id.edt_DownP_PaperExchange_PenDoc);
        edt_DownP_oldTractorAmount_PenDoc = findViewById(R.id.edt_DownP_oldTractorAmount_PenDoc);
        edt_DownP_NOC_PenDoc = findViewById(R.id.edt_DownP_NOC_PenDoc);


        img_DownP_Cheque_PenDoc = findViewById(R.id.img_DownP_Cheque_PenDoc);
        img_DownP_NEFT_RTGS_PenDoc = findViewById(R.id.img_DownP_NEFT_RTGS_PenDoc);
        img_DownP_NocPhoto_PenDoc = findViewById(R.id.img_DownP_NocPhoto_PenDoc);
        img_DownP_OldVehicle_PenDoc = findViewById(R.id.img_DownP_OldVehicle_PenDoc);
        img_DownP_RcBook_PenDoc = findViewById(R.id.img_DownP_RcBook_PenDoc);
        img_DownP_ElectionPhoto_PenDoc = findViewById(R.id.img_DownP_ElectionPhoto_PenDoc);

        img_DownP_RcBook_PenDoc2 = findViewById(R.id.img_DownP_RcBook_PenDoc2);
        img_DownP_ElectionPhoto_PenDoc2 = findViewById(R.id.img_DownP_ElectionPhoto_PenDoc2);

        txt_DownP_UploadRCBook_PenDoc2 = findViewById(R.id.txt_DownP_UploadRCBook_PenDoc2);
        txt_DownP_UploadElectionPhoto_PenDoc2 = findViewById(R.id.txt_DownP_UploadElectionPhoto_PenDoc2);

        txt_DownP_UploadCheque_PenDoc = findViewById(R.id.txt_DownP_UploadCheque_PenDoc);
        txt_DownP_UploadNEFT_RTGS_PenDoc = findViewById(R.id.txt_DownP_UploadNEFT_RTGS_PenDoc);
        txt_DownP_UploadNocPhoto_PenDoc = findViewById(R.id.txt_DownP_UploadNocPhoto_PenDoc);
        txt_DownP_UploadOldVehicle_PenDoc = findViewById(R.id.txt_DownP_UploadOldVehicle_PenDoc);
        txt_DownP_UploadRCBook_PenDoc = findViewById(R.id.txt_DownP_UploadRCBook_PenDoc);
        txt_DownP_UploadElectionPhoto_PenDoc = findViewById(R.id.txt_DownP_UploadElectionPhoto_PenDoc);


        lin_dp_cheque_PenDoc = findViewById(R.id.lin_dp_cheque_PenDoc);
        lin_dp_NEFT_RTGS_PenDoc = findViewById(R.id.lin_dp_NEFT_RTGS_PenDoc);
        lin_dp_NocPhoto_PenDoc = findViewById(R.id.lin_dp_NocPhoto_PenDoc);
        lin_dp_OldVehicle_PenDoc = findViewById(R.id.lin_dp_OldVehicle_PenDoc);
        lin_dp_Rcbook_PenDoc = findViewById(R.id.lin_dp_Rcbook_PenDoc);
        lin_dp_Election_PenDoc = findViewById(R.id.lin_dp_Election_PenDoc);
        lin_dp_Rcbook_PenDoc2 = findViewById(R.id.lin_dp_Rcbook_PenDoc2);
        lin_dp_Election_PenDoc2 = findViewById(R.id.lin_dp_Election_PenDoc2);

        //Consumer Skim
        edt_CS_Hood_PenDoc = findViewById(R.id.edt_CS_Hood_PenDoc);
        edt_CS_TopLink_PenDoc = findViewById(R.id.edt_CS_TopLink_PenDoc);
        edt_CS_DrawBar_PenDoc = findViewById(R.id.edt_CS_DrawBar_PenDoc);
        edt_CS_ToolKit_PenDoc = findViewById(R.id.edt_CS_ToolKit_PenDoc);
        edt_CS_Bumper_PenDoc = findViewById(R.id.edt_CS_Bumper_PenDoc);
        edt_CS_Hitch_PenDoc = findViewById(R.id.edt_CS_Hitch_PenDoc);
        edt_CS_Description_PenDoc = findViewById(R.id.edt_CS_Description_PenDoc);
        edt_CS_ConsumerSkim_PenDoc = findViewById(R.id.edt_CS_ConsumerSkim_PenDoc);

        //Loan Detail Form
        edt_PenDocDetail_REF_PenDoc = findViewById(R.id.edt_PenDocDetail_REF_PenDoc);
        edt_PenDocDetail_FinanceForm_PenDoc = findViewById(R.id.edt_PenDocDetail_FinanceForm_PenDoc);
        edt_PenDocDetail_LoanAmount_PenDoc = findViewById(R.id.edt_PenDocDetail_LoanAmount_PenDoc);
        edt_PenDocDetail_LoanSancAmont_PenDoc = findViewById(R.id.edt_PenDocDetail_LoanSancAmont_PenDoc);
        edt_PenDocDetail_LoanCharge_PenDoc = findViewById(R.id.edt_PenDocDetail_LoanCharge_PenDoc);
        edt_PenDocDetail_LandDetail_PenDoc = findViewById(R.id.edt_PenDocDetail_LandDetail_PenDoc);
        edt_PenDocDetail_CibilScore_PenDoc = findViewById(R.id.edt_PenDocDetail_CibilScore_PenDoc);
        edt_PenDocDetail_FiDate_PenDoc = findViewById(R.id.edt_PenDocDetail_FiDate_PenDoc);
        edt_PenDocDetail_SanctionDate_PenDoc = findViewById(R.id.edt_PenDocDetail_SanctionDate_PenDoc);

        /*spn_PenDocDetail_FinanceForm_PenDoc= findViewById(R.id.spn_PenDocDetail_FinanceForm_PenDoc);
        spn_PenDocDetail_Stage_PenDoc= findViewById(R.id.spn_PenDocDetail_Stage_PenDoc);*/

        edt_PenDocDetail_Stage_PenDoc = findViewById(R.id.edt_PenDocDetail_Stage_PenDoc);


        img_LoanDetail_BankpassBook_PenDoc2 = findViewById(R.id.img_LoanDetail_BankpassBook_PenDoc2);
        txt_LoanDetail_BankPassBook_PenDoc2 = findViewById(R.id.txt_LoanDetail_BankPassBook_PenDoc2);


        img_LoanDetail_BankpassBook_PenDoc = findViewById(R.id.img_LoanDetail_BankpassBook_PenDoc);
        img_LoanDetail_Cheque_PenDoc = findViewById(R.id.img_LoanDetail_Cheque_PenDoc);
        img_LoanDetail_SarpanchID_PenDoc = findViewById(R.id.img_LoanDetail_SarpanchID_PenDoc);
        img_LoanDetail_SignatureVerifi_PenDoc = findViewById(R.id.img_LoanDetail_SignatureVerifi_PenDoc);


        txt_LoanDetail_BankPassBook_PenDoc = findViewById(R.id.txt_LoanDetail_BankPassBook_PenDoc);
        txt_LoanDetail_Cheque_PenDoc = findViewById(R.id.txt_LoanDetail_Cheque_PenDoc);
        txt_LoanDetail_SarpanchId_PenDoc = findViewById(R.id.txt_LoanDetail_SarpanchId_PenDoc);
        txt_LoanDetail_SignatureVeri_PenDoc = findViewById(R.id.txt_LoanDetail_SignatureVeri_PenDoc);


        //Delivery Details
     /*   edt_modelName_PenDoc = findViewById(R.id.edt_modelName_PenDoc);
        edt_ChesisNumber_PenDoc = findViewById(R.id.edt_ChesisNumber_PenDoc);
        edt_EngineNumber_PenDoc = findViewById(R.id.edt_EngineNumber_PenDoc);
        edt_Variente_PenDoc = findViewById(R.id.edt_Variente_PenDoc);*/
        edt_Type_PenDoc = findViewById(R.id.edt_Type_PenDoc);
        edt_Bettry_PenDoc = findViewById(R.id.edt_Bettry_PenDoc);


       /* txt_Tyre_PenDoc = findViewById(R.id.txt_Tyre_PenDoc);
        txt_Battery_PenDoc = findViewById(R.id.txt_Battery_PenDoc);*/

        edt_PenDocDate_PenDoc = findViewById(R.id.edt_PenDocDate_PenDoc);

        img_PenDocPhoto_PenDoc = findViewById(R.id.img_PenDocPhoto_PenDoc);
        img_ChesisPrint_PenDoc = findViewById(R.id.img_ChesisPrint_PenDoc);

        txt_PenDocPhoto_PenDoc = findViewById(R.id.txt_PenDocPhoto_PenDoc);
        txt_ChesisPrint_PenDoc = findViewById(R.id.txt_ChesisPrint_PenDoc);


        edt_CashDetail_Date_PenDoc = findViewById(R.id.edt_CashDetail_Date_PenDoc);
        edt_CashDetail_Amount_PenDoc = findViewById(R.id.edt_CashDetail_Amount_PenDoc);
        edt_CashDetail_Description_PenDoc = findViewById(R.id.edt_CashDetail_Description_PenDoc);

        lin_cashLoan_PenDoc = findViewById(R.id.lin_cashLoan_PenDoc);
        lin_ConsumerSkim_PenDoc = findViewById(R.id.lin_ConsumerSkim_PenDoc);
        lin_Loan_PenDoc_form = findViewById(R.id.lin_Loan_PenDoc_form);

        btn_PenDoc_Submit = findViewById(R.id.btn_PenDoc_Submit);

        /** ********************************************************************************************************** */

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");

        empName = sp.getString("emp_name", "");

        idBookingUpload = getIntent().getStringExtra("idBookingUpload");


        id_item = getIntent().getStringExtra("idBookingUpload");

        id_pos = Integer.parseInt(id_item);


        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);


        progressDialog = new ProgressDialog(PendingDocActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getPaymentPending(emp).enqueue(new Callback<PaymentPendingModel>() {
            @Override
            public void onResponse(Call<PaymentPendingModel> call, Response<PaymentPendingModel> response) {
                progressDialog.dismiss();

             /*  Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getB_photo())
                        .into(img_CD_Booking_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getAdhar_photo())
                        .into(img_CD_AdharCard_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getEcard_photo())
                        .into(img_CD_ElectionCard_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getP_photo())
                        .into(img_CD_PassportSize_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getCheck1_photo())
                        .into(img_DownP_Cheque_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getNeft_rtgs_photo())
                        .into(img_DownP_NEFT_RTGS_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getNoc_photo())
                        .into(img_DownP_NocPhoto_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getOld_vehicle_photo())
                        .into(img_DownP_OldVehicle_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getRecbook_photo())
                        .into(img_DownP_RcBook_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getEc_photo())
                        .into(img_DownP_ElectionPhoto_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getLb_pb_photo())
                        .into(img_LoanDetail_BankpassBook_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getL_c_photo())
                        .into(img_LoanDetail_Cheque_PenDoc);


                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getSar_id_photo())
                        .into(img_LoanDetail_SarpanchID_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getSign_veri())
                        .into(img_LoanDetail_SignatureVerifi_PenDoc);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getDelivry_photo())
                        .into(img_PenDocPhoto_PenDoc);


                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getChasis_print())
                        .into(img_ChesisPrint_PenDoc);


                // -------------------------------------------------------------------
                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getAdhar_back())
                        .into(img_CD_AdharCard_PenDoc2);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getElection_back())
                        .into(img_CD_ElectionCard_PenDoc2);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getP_photo_back())
                        .into(img_CD_PassportSize_PenDoc2);


                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getRcbook_back())
                        .into(img_DownP_RcBook_PenDoc2);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getElec_back())
                        .into(img_DownP_ElectionPhoto_PenDoc2);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getB_pass_back())
                        .into(img_LoanDetail_BankpassBook_PenDoc2);

                Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getDelivry_photo())
                        .into(img_PenDocPhoto_PenDoc);


             Glide.with(PendingDocActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getChasis_print())
                        .into(img_ChesisPrint_PenDoc);*/
                //-----------------------------------------------------------


                //----------------------------------------------------------------------


                assert response.body() != null;
                emp_id = response.body().getData().get(id_pos).getEmp_id();

                Log.d("TAG", "onResponse: check_click 2 "+response.body().getData().get(id_pos).getProduct_name());

                if (response.body().getData().get(id_pos).getProduct_name().equals("New Tractor")){
                    Log.d("TAG", "onResponse: check_click 1");
                    edt_ADBooking_BookingNo_PenDoc.setVisibility(View.GONE);
                    edt_ADBooking_BookingType_PenDoc.setVisibility(View.GONE);
                    edt_ADBooking_BookingDate_PenDoc.setVisibility(View.GONE);
                    edt_ADBooking_BookingLoginName_PenDoc.setVisibility(View.GONE);
                    edt_CD_Fname_PenDoc.setVisibility(View.GONE);
                    edt_CD_LastName_PenDoc.setVisibility(View.GONE);
                    edt_CD_Surname_PenDoc.setVisibility(View.GONE);
                    edt_CD_MobileNo_PenDoc.setVisibility(View.GONE);
                    edt_CD_WhatsappNo_PenDoc.setVisibility(View.GONE);
                    edt_CD_State_PenDoc.setVisibility(View.GONE);
                    edt_CD_City_PenDoc.setVisibility(View.GONE);
                    edt_CD_District_PenDoc.setVisibility(View.GONE);
                    edt_CD_Taluko_PenDoc.setVisibility(View.GONE);
                    edt_CD_Village_PenDoc.setVisibility(View.GONE);
                    edt_CD_EmployeName_PenDoc.setVisibility(View.GONE);
                    edt_CD_PassBook_PenDoc.setVisibility(View.GONE);
                    edt_CD_ChequeBook_PenDoc.setVisibility(View.GONE);
                    edt_CD_PaymentOption_PenDoc.setVisibility(View.GONE);

                    if (response.body().getData().get(id_pos).getB_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_UploadBookingPhoto_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_Booking_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_UploadBookingPhoto_PenDoc.setVisibility(View.GONE);
                        img_CD_Booking_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_AdharCard_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_AdharCard_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_AdharCard_PenDoc.setVisibility(View.GONE);
                        img_CD_AdharCard_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getAdhar_back_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_AdharCard_PenDoc2.setVisibility(View.VISIBLE);
                        img_CD_AdharCard_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_AdharCard_PenDoc2.setVisibility(View.GONE);
                        img_CD_AdharCard_PenDoc2.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getEcard_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_ElectionCard_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_ElectionCard_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_ElectionCard_PenDoc.setVisibility(View.GONE);
                        img_CD_ElectionCard_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getElection_back_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_ElectionCard_PenDoc2.setVisibility(View.VISIBLE);
                        img_CD_ElectionCard_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_ElectionCard_PenDoc2.setVisibility(View.GONE);
                        img_CD_ElectionCard_PenDoc2.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        //  penVal = penVal + 1;
                        txt_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                        img_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        //  penVal = penVal + 1;
                        txt_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                        img_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                        img_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                    }

                    edt_PD_PurchaseName_PenDoc.setVisibility(View.GONE);
                    edt_PD_ModelName_PenDoc.setVisibility(View.GONE);
                    edt_PD_Description_PenDoc.setVisibility(View.GONE);
                    edt_PriceD_price_PenDoc.setVisibility(View.GONE);
                    edt_PriceD_priceInWord_PenDoc.setVisibility(View.GONE);
                    edt_PriceD_GST_PenDoc.setVisibility(View.GONE);
                    edt_RTO_RtoMain_PenDoc.setVisibility(View.GONE);
                    edt_RTO_RtoTax_PenDoc.setVisibility(View.GONE);
                    edt_RTO_RtoPassing_PenDoc.setVisibility(View.GONE);
                    edt_RTO_Insurance_PenDoc.setVisibility(View.GONE);
                    edt_RTO_AgentFees_PenDoc.setVisibility(View.GONE);
                    edt_RTO_NumberPlat_PenDoc.setVisibility(View.GONE);
                    edt_RTO_LoanCharge_PenDoc.setVisibility(View.GONE);
                    edt_DownP_BookingAmount_PenDoc.setVisibility(View.GONE);
                    edt_DownP_CashAmount_PenDoc.setVisibility(View.GONE);
                    edt_DownP_BankOption_PenDoc.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_PenDoc.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_PenDoc.setVisibility(View.GONE);

                    if (response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")) {
                        penVal = penVal + 1;
//                        lin_dp_cheque_PenDoc.setVisibility(View.VISIBLE);
                        txt_DownP_UploadCheque_PenDoc.setVisibility(View.VISIBLE);
                        img_DownP_Cheque_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        lin_dp_cheque_PenDoc.setVisibility(View.GONE);
                        txt_DownP_UploadCheque_PenDoc.setVisibility(View.GONE);
                        img_DownP_Cheque_PenDoc.setVisibility(View.GONE);
                    }

                    edt_DownP_NEFT_RTGS_date_PenDoc.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_PenDoc.setVisibility(View.GONE);


                    if (response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")) {
                        penVal = penVal + 1;
//                        lin_dp_NEFT_RTGS_PenDoc.setVisibility(View.VISIBLE);
                        txt_DownP_UploadNEFT_RTGS_PenDoc.setVisibility(View.VISIBLE);
                        img_DownP_NEFT_RTGS_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        lin_dp_NEFT_RTGS_PenDoc.setVisibility(View.GONE);
                        txt_DownP_UploadNEFT_RTGS_PenDoc.setVisibility(View.GONE);
                        img_DownP_NEFT_RTGS_PenDoc.setVisibility(View.GONE);
                    }

                    edt_DownP_SelectMake_PenDoc.setVisibility(View.GONE);
                    edt_DownP_ModelVehicle_PenDoc.setVisibility(View.GONE);
                    edt_DownP_ManufactureYear_PenDoc.setVisibility(View.GONE);
                    edt_DownP_oldAmount_PenDoc.setVisibility(View.GONE);
                    edt_DownP_PaperExchange_PenDoc.setVisibility(View.GONE);
                    edt_DownP_oldTractorAmount_PenDoc.setVisibility(View.GONE);
                    edt_DownP_NOC_PenDoc.setVisibility(View.GONE);

                    if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                        lin_dp_NocPhoto_PenDoc.setVisibility(View.GONE);
                        txt_DownP_UploadNocPhoto_PenDoc.setVisibility(View.GONE);
                        img_DownP_NocPhoto_PenDoc.setVisibility(View.GONE);

                    } else {
                        if (response.body().getData().get(id_pos).getNoc_photo_check().equals("0")) {
                            penVal = penVal + 1;
//                            lin_dp_NocPhoto_PenDoc.setVisibility(View.VISIBLE);
                            txt_DownP_UploadNocPhoto_PenDoc.setVisibility(View.VISIBLE);
                            img_DownP_NocPhoto_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            lin_dp_NocPhoto_PenDoc.setVisibility(View.GONE);
                            txt_DownP_UploadNocPhoto_PenDoc.setVisibility(View.GONE);
                            img_DownP_NocPhoto_PenDoc.setVisibility(View.GONE);
                        }
                    }

                    if (response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")) {
                        penVal = penVal + 1;
//                        lin_dp_OldVehicle_PenDoc.setVisibility(View.VISIBLE);
                        txt_DownP_UploadOldVehicle_PenDoc.setVisibility(View.VISIBLE);
                        img_DownP_OldVehicle_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        lin_dp_OldVehicle_PenDoc.setVisibility(View.GONE);
                        txt_DownP_UploadOldVehicle_PenDoc.setVisibility(View.GONE);
                        img_DownP_OldVehicle_PenDoc.setVisibility(View.GONE);
                    }
                    if (response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")) {
                        penVal = penVal + 1;
//                        lin_dp_Rcbook_PenDoc.setVisibility(View.VISIBLE);
                        txt_DownP_UploadRCBook_PenDoc.setVisibility(View.VISIBLE);
                        img_DownP_RcBook_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        lin_dp_Rcbook_PenDoc.setVisibility(View.GONE);
                        txt_DownP_UploadRCBook_PenDoc.setVisibility(View.GONE);
                        img_DownP_RcBook_PenDoc.setVisibility(View.GONE);
                    }
                    if (response.body().getData().get(id_pos).getRcbook_back_check().equals("0")) {
                        penVal = penVal + 1;
//                        lin_dp_Rcbook_PenDoc2.setVisibility(View.VISIBLE);
                        txt_DownP_UploadRCBook_PenDoc2.setVisibility(View.VISIBLE);
                        img_DownP_RcBook_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_DownP_UploadRCBook_PenDoc2.setVisibility(View.GONE);
                        img_DownP_RcBook_PenDoc2.setVisibility(View.GONE);
                    }
                    if (response.body().getData().get(id_pos).getEc_photo_check().equals("0")) {
                        penVal = penVal + 1;
//                        lin_dp_Election_PenDoc.setVisibility(View.VISIBLE);
                        txt_DownP_UploadElectionPhoto_PenDoc.setVisibility(View.VISIBLE);
                        img_DownP_ElectionPhoto_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        lin_dp_Election_PenDoc.setVisibility(View.GONE);
                        txt_DownP_UploadElectionPhoto_PenDoc.setVisibility(View.GONE);
                        img_DownP_ElectionPhoto_PenDoc.setVisibility(View.GONE);
                    }
                    if (response.body().getData().get(id_pos).getElec_back_check().equals("0")) {
                        penVal = penVal + 1;
//                        lin_dp_Election_PenDoc2.setVisibility(View.VISIBLE);
                        txt_DownP_UploadElectionPhoto_PenDoc2.setVisibility(View.VISIBLE);
                        img_DownP_ElectionPhoto_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        lin_dp_Election_PenDoc2.setVisibility(View.GONE);
                        txt_DownP_UploadElectionPhoto_PenDoc2.setVisibility(View.GONE);
                        img_DownP_ElectionPhoto_PenDoc2.setVisibility(View.GONE);
                    }

                    edt_CS_ConsumerSkim_PenDoc.setVisibility(View.GONE);
                    edt_CS_Hood_PenDoc.setVisibility(View.GONE);
                    edt_CS_TopLink_PenDoc.setVisibility(View.GONE);
                    edt_CS_DrawBar_PenDoc.setVisibility(View.GONE);
                    edt_CS_ToolKit_PenDoc.setVisibility(View.GONE);
                    edt_CS_Bumper_PenDoc.setVisibility(View.GONE);
                    edt_CS_Hitch_PenDoc.setVisibility(View.GONE);
                    edt_CS_Description_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_REF_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_FinanceForm_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_LoanAmount_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_LoanSancAmont_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_LoanCharge_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_LandDetail_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_CibilScore_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_FiDate_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_SanctionDate_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDetail_Stage_PenDoc.setVisibility(View.GONE);

                    if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        img_LoanDetail_BankpassBook_PenDoc.setVisibility(View.VISIBLE);
                        txt_LoanDetail_BankPassBook_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        img_LoanDetail_BankpassBook_PenDoc.setVisibility(View.GONE);
                        txt_LoanDetail_BankPassBook_PenDoc.setVisibility(View.GONE);
                    }
                    if (response.body().getData().get(id_pos).getB_pass_back_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_LoanDetail_BankPassBook_PenDoc2.setVisibility(View.VISIBLE);
                        img_LoanDetail_BankpassBook_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_LoanDetail_BankPassBook_PenDoc2.setVisibility(View.GONE);
                        img_LoanDetail_BankpassBook_PenDoc2.setVisibility(View.GONE);
                    }
                    edt_CashDetail_Date_PenDoc.setVisibility(View.GONE);
                    edt_CashDetail_Amount_PenDoc.setVisibility(View.GONE);
                    edt_CashDetail_Description_PenDoc.setVisibility(View.GONE);
                    edt_Type_PenDoc.setVisibility(View.GONE);
                    edt_Bettry_PenDoc.setVisibility(View.GONE);
                    edt_PenDocDate_PenDoc.setVisibility(View.GONE);

                    if (response.body().getData().get(id_pos).getDelivry_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_PenDocPhoto_PenDoc.setVisibility(View.VISIBLE);
                        img_PenDocPhoto_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_PenDocPhoto_PenDoc.setVisibility(View.GONE);
                        img_PenDocPhoto_PenDoc.setVisibility(View.GONE);
                    }
                    if (response.body().getData().get(id_pos).getChasis_print_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_ChesisPrint_PenDoc.setVisibility(View.VISIBLE);
                        img_ChesisPrint_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_ChesisPrint_PenDoc.setVisibility(View.GONE);
                        img_ChesisPrint_PenDoc.setVisibility(View.GONE);
                    }
                }
                else if (response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                        response.body().getData().get(id_pos).getProduct_name().equals("Spar part")) {

                    edt_ADBooking_BookingType_PenDoc.setVisibility(View.GONE);
                    edt_CD_PassBook_PenDoc.setVisibility(View.GONE);
                    edt_CD_ChequeBook_PenDoc.setVisibility(View.GONE);

                    edt_Type_PenDoc.setVisibility(View.GONE);
                    edt_Bettry_PenDoc.setVisibility(View.GONE);

                    txt_CD_UploadBookingPhoto_PenDoc.setVisibility(View.GONE);
                    txt_CD_AdharCard_PenDoc.setVisibility(View.GONE);
                    txt_CD_ElectionCard_PenDoc.setVisibility(View.GONE);
                    txt_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                    txt_CD_AdharCard_PenDoc2.setVisibility(View.GONE);
                    txt_CD_ElectionCard_PenDoc2.setVisibility(View.GONE);
                    txt_CD_PassportSize_PenDoc2.setVisibility(View.GONE);

                    img_CD_Booking_PenDoc.setVisibility(View.GONE);
                    img_CD_AdharCard_PenDoc.setVisibility(View.GONE);
                    img_CD_ElectionCard_PenDoc.setVisibility(View.GONE);
                    img_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                    img_CD_AdharCard_PenDoc2.setVisibility(View.GONE);
                    img_CD_ElectionCard_PenDoc2.setVisibility(View.GONE);
                    img_CD_PassportSize_PenDoc2.setVisibility(View.GONE);

                    edt_Type_PenDoc.setVisibility(View.GONE);
                    edt_Bettry_PenDoc.setVisibility(View.GONE);

                } else {
                    edt_ADBooking_BookingType_PenDoc.setVisibility(View.VISIBLE);
                    edt_CD_PassBook_PenDoc.setVisibility(View.VISIBLE);
                    edt_CD_ChequeBook_PenDoc.setVisibility(View.VISIBLE);

                    edt_Type_PenDoc.setVisibility(View.VISIBLE);
                    edt_Bettry_PenDoc.setVisibility(View.VISIBLE);


                    if (response.body().getData().get(id_pos).getB_type_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_ADBooking_BookingType_PenDoc.setFocusable(true);
                        edt_ADBooking_BookingType_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_ADBooking_BookingType_PenDoc.setFocusable(false);
                        edt_ADBooking_BookingType_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_ADBooking_BookingType_PenDoc.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getB_p_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CD_PassBook_PenDoc.setFocusable(true);
                        edt_CD_PassBook_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CD_PassBook_PenDoc.setFocusable(false);
                        edt_CD_PassBook_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CD_PassBook_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getCheck_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CD_ChequeBook_PenDoc.setFocusable(true);
                        edt_CD_ChequeBook_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CD_ChequeBook_PenDoc.setFocusable(false);
                        edt_CD_ChequeBook_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CD_ChequeBook_PenDoc.setVisibility(View.GONE);
                    }


                    //-----------------------------------------------------------

                    if (response.body().getData().get(id_pos).getTyre_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_Type_PenDoc.setFocusable(true);
                        edt_Type_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_Type_PenDoc.setFocusable(false);
                        edt_Type_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_Type_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getBattery_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_Bettry_PenDoc.setFocusable(true);
                        edt_Bettry_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_Bettry_PenDoc.setFocusable(false);
                        edt_Bettry_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_Bettry_PenDoc.setVisibility(View.GONE);
                    }

                    //------------------------------------------------------------


                    if (response.body().getData().get(id_pos).getB_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_UploadBookingPhoto_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_Booking_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_UploadBookingPhoto_PenDoc.setVisibility(View.GONE);
                        img_CD_Booking_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_AdharCard_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_AdharCard_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_AdharCard_PenDoc.setVisibility(View.GONE);
                        img_CD_AdharCard_PenDoc.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getEcard_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_ElectionCard_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_ElectionCard_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_ElectionCard_PenDoc.setVisibility(View.GONE);
                        img_CD_ElectionCard_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        //  penVal = penVal + 1;
                        txt_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                        img_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getAdhar_back_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_AdharCard_PenDoc2.setVisibility(View.VISIBLE);
                        img_CD_AdharCard_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_AdharCard_PenDoc2.setVisibility(View.GONE);
                        img_CD_AdharCard_PenDoc2.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getElection_back_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_ElectionCard_PenDoc2.setVisibility(View.VISIBLE);
                        img_CD_ElectionCard_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_ElectionCard_PenDoc2.setVisibility(View.GONE);
                        img_CD_ElectionCard_PenDoc2.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        //  penVal = penVal + 1;
                        txt_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                        img_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                        img_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                        img_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                        txt_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);

                        img_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                        txt_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);

                        if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                            penVal = penVal + 1;
                            txt_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                            img_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            txt_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                            img_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                            penVal = penVal + 1;
                            txt_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                            img_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                        } else {
                            txt_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                            img_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                        }

                    } else {
                        // penVal = penVal - 1;
                        txt_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                        img_CD_PassportSize_PenDoc2.setVisibility(View.GONE);

                        // penVal = penVal - 1;
                        txt_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                        img_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                    }
                }


                //-----------------------------------------------------------------------------------------------

                /* if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                 *//*img_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                    txt_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);

                    img_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                    txt_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);*//*

                    if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                        img_CD_PassportSize_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                        img_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                        img_CD_PassportSize_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                        img_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                    }

                } else {
                   // penVal = penVal - 1;
                    txt_CD_PassportSize_PenDoc2.setVisibility(View.GONE);
                    img_CD_PassportSize_PenDoc2.setVisibility(View.GONE);

                   // penVal = penVal - 1;
                    txt_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                    img_CD_PassportSize_PenDoc.setVisibility(View.GONE);
                }*/


                //---------------------------------------------------------------------------

                if (response.body().getData().get(id_pos).getBno_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_ADBooking_BookingNo_PenDoc.setFocusable(true);
                    edt_ADBooking_BookingNo_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_ADBooking_BookingNo_PenDoc.setFocusable(false);
                    edt_ADBooking_BookingNo_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_ADBooking_BookingNo_PenDoc.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getB_date_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_ADBooking_BookingDate_PenDoc.setFocusable(true);
                    edt_ADBooking_BookingDate_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_ADBooking_BookingDate_PenDoc.setFocusable(false);
                    edt_ADBooking_BookingDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_ADBooking_BookingDate_PenDoc.setVisibility(View.GONE);
                }


               /* if (response.body().getData().get(id_pos).getB_type_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_ADBooking_BookingType_PenDoc.setFocusable(true);
                    edt_ADBooking_BookingType_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_ADBooking_BookingType_PenDoc.setFocusable(false);
                    edt_ADBooking_BookingType_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_ADBooking_BookingType_PenDoc.setVisibility(View.GONE);
                }*/


                if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_ADBooking_BookingLoginName_PenDoc.setFocusable(true);
                    edt_ADBooking_BookingLoginName_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_ADBooking_BookingLoginName_PenDoc.setFocusable(false);
                    edt_ADBooking_BookingLoginName_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_ADBooking_BookingLoginName_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getFname_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_Fname_PenDoc.setFocusable(true);
                    edt_CD_Fname_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_Fname_PenDoc.setFocusable(false);
                    edt_CD_Fname_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_Fname_PenDoc.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getMobileno_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_MobileNo_PenDoc.setFocusable(true);
                    edt_CD_MobileNo_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_MobileNo_PenDoc.setFocusable(false);
                    edt_CD_MobileNo_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_MobileNo_PenDoc.setVisibility(View.GONE);

                }

                if (response.body().getData().get(id_pos).getWhno_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_WhatsappNo_PenDoc.setFocusable(true);
                    edt_CD_WhatsappNo_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_WhatsappNo_PenDoc.setFocusable(false);
                    edt_CD_WhatsappNo_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_WhatsappNo_PenDoc.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getState_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_State_PenDoc.setFocusable(true);
                    edt_CD_State_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_State_PenDoc.setFocusable(false);
                    edt_CD_State_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_State_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getCity_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_City_PenDoc.setFocusable(true);
                    edt_CD_City_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_City_PenDoc.setFocusable(false);
                    edt_CD_City_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_City_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getDistric_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_District_PenDoc.setFocusable(true);
                    edt_CD_District_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_District_PenDoc.setFocusable(false);
                    edt_CD_District_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_District_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getVillage_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_Village_PenDoc.setFocusable(true);
                    edt_CD_Village_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_Village_PenDoc.setFocusable(false);
                    edt_CD_Village_PenDoc.setVisibility(View.GONE);
                    edt_CD_Village_PenDoc.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_EmployeName_PenDoc.setFocusable(true);
                    edt_CD_EmployeName_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_EmployeName_PenDoc.setFocusable(false);
                    edt_CD_EmployeName_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_EmployeName_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getTehsill_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_Taluko_PenDoc.setFocusable(true);
                    edt_CD_Taluko_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CD_Taluko_PenDoc.setFocusable(false);
                    edt_CD_Taluko_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_Taluko_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getProduct_name_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PD_PurchaseName_PenDoc.setFocusable(true);
                    edt_PD_PurchaseName_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PD_PurchaseName_PenDoc.setFocusable(false);
                    edt_PD_PurchaseName_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PD_PurchaseName_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getModel_name_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PD_ModelName_PenDoc.setFocusable(true);
                    edt_PD_ModelName_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PD_ModelName_PenDoc.setFocusable(false);
                    edt_PD_ModelName_PenDoc.setVisibility(View.GONE);
                    edt_PD_ModelName_PenDoc.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getP_desc_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PD_Description_PenDoc.setFocusable(true);
                    edt_PD_Description_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PD_Description_PenDoc.setFocusable(false);
                    edt_PD_Description_PenDoc.setVisibility(View.GONE);
                    edt_PD_Description_PenDoc.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDeal_price_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PriceD_price_PenDoc.setFocusable(true);
                    edt_PriceD_price_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PriceD_price_PenDoc.setFocusable(false);
                    edt_PriceD_price_PenDoc.setVisibility(View.GONE);
                    edt_PriceD_price_PenDoc.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PriceD_priceInWord_PenDoc.setFocusable(true);
                    edt_PriceD_priceInWord_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PriceD_priceInWord_PenDoc.setFocusable(false);
                    edt_PriceD_priceInWord_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PriceD_priceInWord_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getGst_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PriceD_GST_PenDoc.setFocusable(true);
                    edt_PriceD_GST_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PriceD_GST_PenDoc.setFocusable(false);
                    edt_PriceD_GST_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PriceD_GST_PenDoc.setVisibility(View.GONE);
                }

                //------------------------------------------------------------------------------------

                if (response.body().getData().get(id_pos).getRto_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_RTO_RtoMain_PenDoc.setFocusable(true);
                    edt_RTO_RtoMain_PenDoc.setVisibility(View.VISIBLE);
                /*} else {
                    edt_RTO_RtoMain_PenDoc.setFocusable(false);
                    edt_RTO_RtoMain_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_RTO_RtoMain_PenDoc.setVisibility(View.GONE);
                }*/

                    if (response.body().getData().get(id_pos).getRto_tax_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_RTO_RtoTax_PenDoc.setFocusable(true);
                        edt_RTO_RtoTax_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_RTO_RtoTax_PenDoc.setFocusable(false);
                        edt_RTO_RtoTax_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_RTO_RtoTax_PenDoc.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getRto_passing_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_RTO_RtoPassing_PenDoc.setFocusable(true);
                        edt_RTO_RtoPassing_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_RTO_RtoPassing_PenDoc.setFocusable(false);
                        edt_RTO_RtoPassing_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_RTO_RtoPassing_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getInsurance_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_RTO_Insurance_PenDoc.setFocusable(true);
                        edt_RTO_Insurance_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_RTO_Insurance_PenDoc.setFocusable(false);
                        edt_RTO_Insurance_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_RTO_Insurance_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getAgent_fee_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_RTO_AgentFees_PenDoc.setFocusable(true);
                        edt_RTO_AgentFees_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_RTO_AgentFees_PenDoc.setFocusable(false);
                        edt_RTO_AgentFees_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_RTO_AgentFees_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getNumber_plat_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_RTO_NumberPlat_PenDoc.setFocusable(true);
                        edt_RTO_NumberPlat_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_RTO_NumberPlat_PenDoc.setFocusable(false);
                        edt_RTO_NumberPlat_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_RTO_NumberPlat_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLoan_charge_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_RTO_LoanCharge_PenDoc.setFocusable(true);
                        edt_RTO_LoanCharge_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_RTO_LoanCharge_PenDoc.setFocusable(false);
                        edt_RTO_LoanCharge_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_RTO_LoanCharge_PenDoc.setVisibility(View.GONE);
                    }

                } else {
                    edt_RTO_RtoMain_PenDoc.setFocusable(false);
                    edt_RTO_RtoMain_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_RTO_RtoMain_PenDoc.setVisibility(View.GONE);

                    edt_RTO_RtoTax_PenDoc.setVisibility(View.GONE);
                    edt_RTO_RtoPassing_PenDoc.setVisibility(View.GONE);
                    edt_RTO_Insurance_PenDoc.setVisibility(View.GONE);
                    edt_RTO_AgentFees_PenDoc.setVisibility(View.GONE);
                    edt_RTO_NumberPlat_PenDoc.setVisibility(View.GONE);
                    edt_RTO_LoanCharge_PenDoc.setVisibility(View.GONE);
                }

                //----------------------------------------------------------------------------------------------

                if (response.body().getData().get(id_pos).getBooking_amt_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_DownP_BookingAmount_PenDoc.setFocusable(true);
                    edt_DownP_BookingAmount_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_DownP_BookingAmount_PenDoc.setFocusable(false);
                    edt_DownP_BookingAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_DownP_BookingAmount_PenDoc.setVisibility(View.GONE);
                }


                //----------------------------------------------************************************

                if (response.body().getData().get(id_pos).getSkim_check().equals("0")) {

                    penVal = penVal + 1;
                    edt_CS_ConsumerSkim_PenDoc.setFocusable(true);
                    edt_CS_ConsumerSkim_PenDoc.setVisibility(View.VISIBLE);

                    /*if (response.body().getData().get(id_pos).getSkim_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CS_ConsumerSkim_PenDoc.setFocusable(true);
                        edt_CS_ConsumerSkim_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CS_ConsumerSkim_PenDoc.setFocusable(false);
                        edt_CS_ConsumerSkim_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CS_ConsumerSkim_PenDoc.setVisibility(View.GONE);
                    }*/

               /* } else {
                    //lin_ConsumerSkim_PenDoc.setVisibility(View.VISIBLE);
                    lin_ConsumerSkim_PenDoc.setVisibility(View.GONE);
                }*/

                    if (response.body().getData().get(id_pos).getHood_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CS_Hood_PenDoc.setFocusable(true);
                        edt_CS_Hood_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CS_Hood_PenDoc.setFocusable(false);
                        edt_CS_Hood_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CS_Hood_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getToplink_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CS_TopLink_PenDoc.setFocusable(true);
                        edt_CS_TopLink_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CS_TopLink_PenDoc.setFocusable(false);
                        edt_CS_TopLink_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CS_TopLink_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getDrowbar_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CS_DrawBar_PenDoc.setFocusable(true);
                        edt_CS_DrawBar_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CS_DrawBar_PenDoc.setFocusable(false);
                        edt_CS_DrawBar_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CS_DrawBar_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getToolkit_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CS_ToolKit_PenDoc.setFocusable(true);
                        edt_CS_ToolKit_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CS_ToolKit_PenDoc.setFocusable(false);
                        edt_CS_ToolKit_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CS_ToolKit_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getBumper_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CS_Bumper_PenDoc.setFocusable(true);
                        edt_CS_Bumper_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CS_Bumper_PenDoc.setFocusable(false);
                        edt_CS_Bumper_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CS_Bumper_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getHitch_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CS_Hitch_PenDoc.setFocusable(true);
                        edt_CS_Hitch_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CS_Hitch_PenDoc.setFocusable(false);
                        edt_CS_Hitch_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CS_Hitch_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getDescription_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CS_Description_PenDoc.setFocusable(true);
                        edt_CS_Description_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CS_Description_PenDoc.setFocusable(false);
                        edt_CS_Description_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CS_Description_PenDoc.setVisibility(View.GONE);
                    }

                } else {
                    // lin_ConsumerSkim_PenDoc.setVisibility(View.GONE);
                    edt_CS_ConsumerSkim_PenDoc.setFocusable(false);
                    edt_CS_ConsumerSkim_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CS_ConsumerSkim_PenDoc.setVisibility(View.GONE);

                    edt_CS_Hood_PenDoc.setVisibility(View.GONE);
                    edt_CS_TopLink_PenDoc.setVisibility(View.GONE);
                    edt_CS_DrawBar_PenDoc.setVisibility(View.GONE);
                    edt_CS_ToolKit_PenDoc.setVisibility(View.GONE);
                    edt_CS_Bumper_PenDoc.setVisibility(View.GONE);
                    edt_CS_Hitch_PenDoc.setVisibility(View.GONE);

                    edt_CS_Description_PenDoc.setVisibility(View.GONE);
                }


                //--------------------------------------------------------------------------------

                if (response.body().getData().get(id_pos).getAtype().equals("Loan")) {
                    lin_Loan_PenDoc_form.setVisibility(View.VISIBLE);
                    lin_cashLoan_PenDoc.setVisibility(View.GONE);

                    if (response.body().getData().get(id_pos).getR_e_name_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_REF_PenDoc.setFocusable(true);
                        edt_PenDocDetail_REF_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_REF_PenDoc.setFocusable(false);
                        edt_PenDocDetail_REF_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_REF_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getFinance_from_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_FinanceForm_PenDoc.setFocusable(true);
                        edt_PenDocDetail_FinanceForm_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_FinanceForm_PenDoc.setFocusable(false);
                        edt_PenDocDetail_FinanceForm_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_FinanceForm_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLoan_amount_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_LoanAmount_PenDoc.setFocusable(true);
                        edt_PenDocDetail_LoanAmount_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_LoanAmount_PenDoc.setFocusable(false);
                        edt_PenDocDetail_LoanAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_LoanAmount_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setFocusable(true);
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setFocusable(false);
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLloan_charge_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_LoanCharge_PenDoc.setFocusable(true);
                        edt_PenDocDetail_LoanCharge_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_LoanCharge_PenDoc.setFocusable(false);
                        edt_PenDocDetail_LoanCharge_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_LoanCharge_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLand_details_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_LandDetail_PenDoc.setFocusable(true);
                        edt_PenDocDetail_LandDetail_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_LandDetail_PenDoc.setFocusable(false);
                        edt_PenDocDetail_LandDetail_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_LandDetail_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getCibil_score_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_CibilScore_PenDoc.setFocusable(true);
                        edt_PenDocDetail_CibilScore_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_CibilScore_PenDoc.setFocusable(false);
                        edt_PenDocDetail_CibilScore_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_CibilScore_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getFi_date_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_FiDate_PenDoc.setFocusable(true);
                        edt_PenDocDetail_FiDate_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_FiDate_PenDoc.setFocusable(false);
                        edt_PenDocDetail_FiDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_FiDate_PenDoc.setVisibility(View.GONE);

                    }

                    if (response.body().getData().get(id_pos).getSectiondate_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_SanctionDate_PenDoc.setFocusable(false);
                        edt_PenDocDetail_SanctionDate_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_SanctionDate_PenDoc.setFocusable(false);
                        edt_PenDocDetail_SanctionDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_SanctionDate_PenDoc.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getStage_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_Stage_PenDoc.setFocusable(true);
                        edt_PenDocDetail_Stage_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_Stage_PenDoc.setFocusable(false);
                        edt_PenDocDetail_Stage_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_Stage_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        img_LoanDetail_BankpassBook_PenDoc.setVisibility(View.VISIBLE);
                        txt_LoanDetail_BankPassBook_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        img_LoanDetail_BankpassBook_PenDoc.setVisibility(View.GONE);
                        txt_LoanDetail_BankPassBook_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getB_pass_back_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_LoanDetail_BankPassBook_PenDoc2.setVisibility(View.VISIBLE);
                        img_LoanDetail_BankpassBook_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_LoanDetail_BankPassBook_PenDoc2.setVisibility(View.GONE);
                        img_LoanDetail_BankpassBook_PenDoc2.setVisibility(View.GONE);
                    }

                }


                if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {
                    lin_Loan_PenDoc_form.setVisibility(View.GONE);
                    lin_cashLoan_PenDoc.setVisibility(View.VISIBLE);

                    if (response.body().getData().get(id_pos).getCash_date_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CashDetail_Date_PenDoc.setFocusable(true);
                        edt_CashDetail_Date_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CashDetail_Date_PenDoc.setFocusable(false);
                        edt_CashDetail_Date_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CashDetail_Date_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getCash_amount_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CashDetail_Amount_PenDoc.setFocusable(true);
                        edt_CashDetail_Amount_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CashDetail_Amount_PenDoc.setFocusable(false);
                        edt_CashDetail_Amount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CashDetail_Amount_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getCash_description_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CashDetail_Description_PenDoc.setFocusable(true);
                        edt_CashDetail_Description_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CashDetail_Description_PenDoc.setFocusable(false);
                        edt_CashDetail_Description_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CashDetail_Description_PenDoc.setVisibility(View.GONE);
                    }

                }


                if (response.body().getData().get(id_pos).getAtype().equals("Cash-Loan")) {
                    lin_Loan_PenDoc_form.setVisibility(View.VISIBLE);
                    lin_cashLoan_PenDoc.setVisibility(View.VISIBLE);

                    if (response.body().getData().get(id_pos).getR_e_name_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_REF_PenDoc.setFocusable(true);
                        edt_PenDocDetail_REF_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_REF_PenDoc.setFocusable(false);
                        edt_PenDocDetail_REF_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_REF_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getFinance_from_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_FinanceForm_PenDoc.setFocusable(true);
                        edt_PenDocDetail_FinanceForm_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_FinanceForm_PenDoc.setFocusable(false);
                        edt_PenDocDetail_FinanceForm_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_FinanceForm_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLoan_amount_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_LoanAmount_PenDoc.setFocusable(true);
                        edt_PenDocDetail_LoanAmount_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_LoanAmount_PenDoc.setFocusable(false);
                        edt_PenDocDetail_LoanAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_LoanAmount_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setFocusable(true);
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setFocusable(false);
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_LoanSancAmont_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLloan_charge_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_LoanCharge_PenDoc.setFocusable(true);
                        edt_PenDocDetail_LoanCharge_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_LoanCharge_PenDoc.setFocusable(false);
                        edt_PenDocDetail_LoanCharge_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_LoanCharge_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLand_details_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_LandDetail_PenDoc.setFocusable(true);
                        edt_PenDocDetail_LandDetail_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_LandDetail_PenDoc.setFocusable(false);
                        edt_PenDocDetail_LandDetail_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_LandDetail_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getCibil_score_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_CibilScore_PenDoc.setFocusable(true);
                        edt_PenDocDetail_CibilScore_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_CibilScore_PenDoc.setFocusable(false);
                        edt_PenDocDetail_CibilScore_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_CibilScore_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getFi_date_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_FiDate_PenDoc.setFocusable(true);
                        edt_PenDocDetail_FiDate_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_FiDate_PenDoc.setFocusable(false);
                        edt_PenDocDetail_FiDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_FiDate_PenDoc.setVisibility(View.GONE);

                    }

                    if (response.body().getData().get(id_pos).getSectiondate_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_SanctionDate_PenDoc.setFocusable(false);
                        edt_PenDocDetail_SanctionDate_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_SanctionDate_PenDoc.setFocusable(false);
                        edt_PenDocDetail_SanctionDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_SanctionDate_PenDoc.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getStage_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_PenDocDetail_Stage_PenDoc.setFocusable(true);
                        edt_PenDocDetail_Stage_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_PenDocDetail_Stage_PenDoc.setFocusable(false);
                        edt_PenDocDetail_Stage_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_PenDocDetail_Stage_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                        penVal = penVal + 1;
                        img_LoanDetail_BankpassBook_PenDoc.setVisibility(View.VISIBLE);
                        txt_LoanDetail_BankPassBook_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        img_LoanDetail_BankpassBook_PenDoc.setVisibility(View.GONE);
                        txt_LoanDetail_BankPassBook_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getB_pass_back_check().equals("0")) {
                        penVal = penVal + 1;
                        txt_LoanDetail_BankPassBook_PenDoc2.setVisibility(View.VISIBLE);
                        img_LoanDetail_BankpassBook_PenDoc2.setVisibility(View.VISIBLE);
                    } else {
                        txt_LoanDetail_BankPassBook_PenDoc2.setVisibility(View.GONE);
                        img_LoanDetail_BankpassBook_PenDoc2.setVisibility(View.GONE);
                    }

                    //****************************************

                    if (response.body().getData().get(id_pos).getCash_date_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CashDetail_Date_PenDoc.setFocusable(true);
                        edt_CashDetail_Date_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CashDetail_Date_PenDoc.setFocusable(false);
                        edt_CashDetail_Date_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CashDetail_Date_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getCash_amount_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CashDetail_Amount_PenDoc.setFocusable(true);
                        edt_CashDetail_Amount_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CashDetail_Amount_PenDoc.setFocusable(false);
                        edt_CashDetail_Amount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CashDetail_Amount_PenDoc.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getCash_description_check().equals("0")) {
                        penVal = penVal + 1;
                        edt_CashDetail_Description_PenDoc.setFocusable(true);
                        edt_CashDetail_Description_PenDoc.setVisibility(View.VISIBLE);
                    } else {
                        edt_CashDetail_Description_PenDoc.setFocusable(false);
                        edt_CashDetail_Description_PenDoc.setTextColor(Color.parseColor("#43a047"));
                        edt_CashDetail_Description_PenDoc.setVisibility(View.GONE);
                    }


                }


                if (response.body().getData().get(id_pos).getAtype_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CD_PaymentOption_PenDoc.setFocusable(true);
                    edt_CD_PaymentOption_PenDoc.setVisibility(View.VISIBLE);

                } else {
                    edt_CD_PaymentOption_PenDoc.setFocusable(false);
                    edt_CD_PaymentOption_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CD_PaymentOption_PenDoc.setVisibility(View.GONE);
                }

                //--------------------------------------------------------------------

               /* if (response.body().getData().get(id_pos).getR_e_name_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_REF_PenDoc.setFocusable(true);
                    edt_PenDocDetail_REF_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_REF_PenDoc.setFocusable(false);
                    edt_PenDocDetail_REF_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_REF_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getFinance_from_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_FinanceForm_PenDoc.setFocusable(true);
                    edt_PenDocDetail_FinanceForm_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_FinanceForm_PenDoc.setFocusable(false);
                    edt_PenDocDetail_FinanceForm_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_FinanceForm_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getLoan_amount_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_LoanAmount_PenDoc.setFocusable(true);
                    edt_PenDocDetail_LoanAmount_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_LoanAmount_PenDoc.setFocusable(false);
                    edt_PenDocDetail_LoanAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_LoanAmount_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_LoanSancAmont_PenDoc.setFocusable(true);
                    edt_PenDocDetail_LoanSancAmont_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_LoanSancAmont_PenDoc.setFocusable(false);
                    edt_PenDocDetail_LoanSancAmont_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_LoanSancAmont_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getLloan_charge_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_LoanCharge_PenDoc.setFocusable(true);
                    edt_PenDocDetail_LoanCharge_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_LoanCharge_PenDoc.setFocusable(false);
                    edt_PenDocDetail_LoanCharge_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_LoanCharge_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getLand_details_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_LandDetail_PenDoc.setFocusable(true);
                    edt_PenDocDetail_LandDetail_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_LandDetail_PenDoc.setFocusable(false);
                    edt_PenDocDetail_LandDetail_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_LandDetail_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getCibil_score_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_CibilScore_PenDoc.setFocusable(true);
                    edt_PenDocDetail_CibilScore_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_CibilScore_PenDoc.setFocusable(false);
                    edt_PenDocDetail_CibilScore_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_CibilScore_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getFi_date_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_FiDate_PenDoc.setFocusable(true);
                    edt_PenDocDetail_FiDate_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_FiDate_PenDoc.setFocusable(false);
                    edt_PenDocDetail_FiDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_FiDate_PenDoc.setVisibility(View.GONE);

                }

                if (response.body().getData().get(id_pos).getSectiondate_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_SanctionDate_PenDoc.setFocusable(false);
                    edt_PenDocDetail_SanctionDate_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_SanctionDate_PenDoc.setFocusable(false);
                    edt_PenDocDetail_SanctionDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_SanctionDate_PenDoc.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getStage_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDetail_Stage_PenDoc.setFocusable(true);
                    edt_PenDocDetail_Stage_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDetail_Stage_PenDoc.setFocusable(false);
                    edt_PenDocDetail_Stage_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDetail_Stage_PenDoc.setVisibility(View.GONE);
                }*/


                //----------------------------------

                /*if (response.body().getData().get(id_pos).getTyre_check().equals("0")) {
                    edt_Type_PenDoc.setFocusable(true);
                    edt_Type_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_Type_PenDoc.setFocusable(false);
                    edt_Type_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_Type_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getBattery_check().equals("0")) {
                    edt_Bettry_PenDoc.setFocusable(true);
                    edt_Bettry_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_Bettry_PenDoc.setFocusable(false);
                    edt_Bettry_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_Bettry_PenDoc.setVisibility(View.GONE);
                }*/


                if (response.body().getData().get(id_pos).getDelivery_date_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_PenDocDate_PenDoc.setFocusable(true);
                    edt_PenDocDate_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_PenDocDate_PenDoc.setFocusable(false);
                    edt_PenDocDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_PenDocDate_PenDoc.setVisibility(View.GONE);
                }

               /* if (response.body().getData().get(id_pos).getCash_date_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CashDetail_Date_PenDoc.setFocusable(true);
                    edt_CashDetail_Date_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CashDetail_Date_PenDoc.setFocusable(false);
                    edt_CashDetail_Date_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CashDetail_Date_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getCash_amount_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CashDetail_Amount_PenDoc.setFocusable(true);
                    edt_CashDetail_Amount_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CashDetail_Amount_PenDoc.setFocusable(false);
                    edt_CashDetail_Amount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CashDetail_Amount_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getCash_description_check().equals("0")) {
                    penVal = penVal + 1;
                    edt_CashDetail_Description_PenDoc.setFocusable(true);
                    edt_CashDetail_Description_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_CashDetail_Description_PenDoc.setFocusable(false);
                    edt_CashDetail_Description_PenDoc.setTextColor(Color.parseColor("#43a047"));
                    edt_CashDetail_Description_PenDoc.setVisibility(View.GONE);
                }
*/

                /** ************************************************************************************* */


                if (response.body().getData().get(id_pos).getDelivry_photo_check().equals("0")) {
                    penVal = penVal + 1;
                    txt_PenDocPhoto_PenDoc.setVisibility(View.VISIBLE);
                    img_PenDocPhoto_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    txt_PenDocPhoto_PenDoc.setVisibility(View.GONE);
                    img_PenDocPhoto_PenDoc.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getChasis_print_check().equals("0")) {
                    penVal = penVal + 1;
                    txt_ChesisPrint_PenDoc.setVisibility(View.VISIBLE);
                    img_ChesisPrint_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    txt_ChesisPrint_PenDoc.setVisibility(View.GONE);
                    img_ChesisPrint_PenDoc.setVisibility(View.GONE);
                }

                //----------------------------------------------------------

               /* if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                    penVal = penVal + 1;
                    img_LoanDetail_BankpassBook_PenDoc.setVisibility(View.VISIBLE);
                    txt_LoanDetail_BankPassBook_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    img_LoanDetail_BankpassBook_PenDoc.setVisibility(View.GONE);
                    txt_LoanDetail_BankPassBook_PenDoc.setVisibility(View.GONE);
                }*/

               /* if (response.body().getData().get(id_pos).getL_c_photo_check().equals("0")) {
                    penVal = penVal + 1;
                    img_LoanDetail_Cheque_PenDoc.setVisibility(View.VISIBLE);
                    txt_LoanDetail_Cheque_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    img_LoanDetail_Cheque_PenDoc.setVisibility(View.GONE);
                    txt_LoanDetail_Cheque_PenDoc.setVisibility(View.GONE);
                }*/


                //----------------------------------------------------

               /* if (response.body().getData().get(id_pos).getB_pass_back_check().equals("0")) {
                    penVal = penVal + 1;
                    txt_LoanDetail_BankPassBook_PenDoc2.setVisibility(View.VISIBLE);
                    img_LoanDetail_BankpassBook_PenDoc2.setVisibility(View.VISIBLE);
                } else {
                    txt_LoanDetail_BankPassBook_PenDoc2.setVisibility(View.GONE);
                    img_LoanDetail_BankpassBook_PenDoc2.setVisibility(View.GONE);
                }*/


                /** ********************************************************************************** */


                /** ********************************************************************************** */

                String data;
                if (response.body().getData().get(id_pos).getBooking_amt()==null){
                    data = "";

                }else {
                    data = response.body().getData().get(id_pos).getBooking_amt();//"cash,bank"

                }

                //  Toast.makeText(BookingPhaseOneActivity.this, ""+data, Toast.LENGTH_SHORT).show();
                String[] res = data.split(",");

                edt_DownP_CashAmount_PenDoc.setVisibility(View.GONE);

                edt_DownP_BankOption_PenDoc.setVisibility(View.GONE);
                edt_DownP_ChequeDate_PenDoc.setVisibility(View.GONE);
                edt_DownP_ChequeAmount_PenDoc.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGS_date_PenDoc.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGSAmount_PenDoc.setVisibility(View.GONE);
                edt_DownP_SelectMake_PenDoc.setVisibility(View.GONE);
                edt_DownP_ModelVehicle_PenDoc.setVisibility(View.GONE);
                edt_DownP_oldAmount_PenDoc.setVisibility(View.GONE);
                edt_DownP_ManufactureYear_PenDoc.setVisibility(View.GONE);
                edt_DownP_PaperExchange_PenDoc.setVisibility(View.GONE);
                edt_DownP_oldTractorAmount_PenDoc.setVisibility(View.GONE);
                edt_DownP_NOC_PenDoc.setVisibility(View.GONE);

                lin_dp_cheque_PenDoc.setVisibility(View.GONE);
                lin_dp_NEFT_RTGS_PenDoc.setVisibility(View.GONE);

                lin_dp_NocPhoto_PenDoc.setVisibility(View.GONE);
                lin_dp_OldVehicle_PenDoc.setVisibility(View.GONE);
                lin_dp_Rcbook_PenDoc.setVisibility(View.GONE);
                lin_dp_Election_PenDoc.setVisibility(View.GONE);
                lin_dp_Rcbook_PenDoc2.setVisibility(View.GONE);
                lin_dp_Election_PenDoc2.setVisibility(View.GONE);


                for (int i = 0; i < res.length; i++) {
                    mydata = res[i];

                    String uu = mydata.trim();

                    if (uu.equals("Cash")) {

                        if (response.body().getData().get(id_pos).getAmount_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_CashAmount_PenDoc.setFocusable(true);
                            edt_DownP_CashAmount_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_CashAmount_PenDoc.setFocusable(false);
                            edt_DownP_CashAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_CashAmount_PenDoc.setVisibility(View.GONE);
                        }
                    }

                    if (uu.equals("Old Vehicle")) {

                        if (response.body().getData().get(id_pos).getMake_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_SelectMake_PenDoc.setFocusable(true);
                            edt_DownP_SelectMake_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_SelectMake_PenDoc.setFocusable(false);
                            edt_DownP_SelectMake_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_SelectMake_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getModel_name_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_ModelVehicle_PenDoc.setFocusable(true);
                            edt_DownP_ModelVehicle_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_ModelVehicle_PenDoc.setFocusable(false);
                            edt_DownP_ModelVehicle_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_ModelVehicle_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_oldAmount_PenDoc.setFocusable(true);
                            edt_DownP_oldAmount_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_oldAmount_PenDoc.setFocusable(false);
                            edt_DownP_oldAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_oldAmount_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getM_year_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_ManufactureYear_PenDoc.setFocusable(true);
                            edt_DownP_ManufactureYear_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_ManufactureYear_PenDoc.setFocusable(false);
                            edt_DownP_ManufactureYear_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_ManufactureYear_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getPaper_expence_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_PaperExchange_PenDoc.setFocusable(true);
                            edt_DownP_PaperExchange_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_PaperExchange_PenDoc.setFocusable(false);
                            edt_DownP_PaperExchange_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_PaperExchange_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_oldTractorAmount_PenDoc.setFocusable(true);
                            edt_DownP_oldTractorAmount_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_oldTractorAmount_PenDoc.setFocusable(false);
                            edt_DownP_oldTractorAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_oldTractorAmount_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getNoc_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_NOC_PenDoc.setFocusable(true);
                            edt_DownP_NOC_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_NOC_PenDoc.setFocusable(false);
                            edt_DownP_NOC_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_NOC_PenDoc.setVisibility(View.GONE);
                        }

                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                        if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                            lin_dp_NocPhoto_PenDoc.setVisibility(View.GONE);
                            txt_DownP_UploadNocPhoto_PenDoc.setVisibility(View.GONE);
                            img_DownP_NocPhoto_PenDoc.setVisibility(View.GONE);

                        } else {
                            if (response.body().getData().get(id_pos).getNoc_photo_check().equals("0")) {
                                penVal = penVal + 1;
//                                lin_dp_NocPhoto_PenDoc.setVisibility(View.VISIBLE);
                                txt_DownP_UploadNocPhoto_PenDoc.setVisibility(View.VISIBLE);
                                img_DownP_NocPhoto_PenDoc.setVisibility(View.VISIBLE);
                            } else {
                                lin_dp_NocPhoto_PenDoc.setVisibility(View.GONE);
                                txt_DownP_UploadNocPhoto_PenDoc.setVisibility(View.GONE);
                                img_DownP_NocPhoto_PenDoc.setVisibility(View.GONE);
                            }
                        }


                        if (response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")) {
                            penVal = penVal + 1;
//                            lin_dp_OldVehicle_PenDoc.setVisibility(View.VISIBLE);
                            txt_DownP_UploadOldVehicle_PenDoc.setVisibility(View.VISIBLE);
                            img_DownP_OldVehicle_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            lin_dp_OldVehicle_PenDoc.setVisibility(View.GONE);
                            txt_DownP_UploadOldVehicle_PenDoc.setVisibility(View.GONE);
                            img_DownP_OldVehicle_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")) {
                            penVal = penVal + 1;
//                            lin_dp_Rcbook_PenDoc.setVisibility(View.VISIBLE);
                            txt_DownP_UploadRCBook_PenDoc.setVisibility(View.VISIBLE);
                            img_DownP_RcBook_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            lin_dp_Rcbook_PenDoc.setVisibility(View.GONE);
                            txt_DownP_UploadRCBook_PenDoc.setVisibility(View.GONE);
                            img_DownP_RcBook_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getEc_photo_check().equals("0")) {
                            penVal = penVal + 1;
//                            lin_dp_Election_PenDoc.setVisibility(View.VISIBLE);
                            txt_DownP_UploadElectionPhoto_PenDoc.setVisibility(View.VISIBLE);
                            img_DownP_ElectionPhoto_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            lin_dp_Election_PenDoc.setVisibility(View.GONE);
                            txt_DownP_UploadElectionPhoto_PenDoc.setVisibility(View.GONE);
                            img_DownP_ElectionPhoto_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getRcbook_back_check().equals("0")) {
                            penVal = penVal + 1;
//                            lin_dp_Rcbook_PenDoc2.setVisibility(View.VISIBLE);
                            txt_DownP_UploadRCBook_PenDoc2.setVisibility(View.VISIBLE);
                            img_DownP_RcBook_PenDoc2.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadRCBook_PenDoc2.setVisibility(View.GONE);
                            img_DownP_RcBook_PenDoc2.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getElec_back_check().equals("0")) {
                            penVal = penVal + 1;
//                            lin_dp_Election_PenDoc2.setVisibility(View.VISIBLE);
                            txt_DownP_UploadElectionPhoto_PenDoc2.setVisibility(View.VISIBLE);
                            img_DownP_ElectionPhoto_PenDoc2.setVisibility(View.VISIBLE);
                        } else {
                            lin_dp_Election_PenDoc2.setVisibility(View.GONE);
                            txt_DownP_UploadElectionPhoto_PenDoc2.setVisibility(View.GONE);
                            img_DownP_ElectionPhoto_PenDoc2.setVisibility(View.GONE);
                        }

                    }

                    if (uu.equals("Bank")) {
                        //  edt_DownP_CashAmount_PenDoc.setVisibility(View.GONE);
                        // edt_DownP_BankOption_PenDoc.setVisibility(View.GONE);

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs_check().equals("0")) {
                            penVal = penVal + 1;
                            edt_DownP_BankOption_PenDoc.setFocusable(true);
                            edt_DownP_BankOption_PenDoc.setVisibility(View.VISIBLE);
                        } else {
                            edt_DownP_BankOption_PenDoc.setFocusable(false);
                            edt_DownP_BankOption_PenDoc.setTextColor(Color.parseColor("#43a047"));
                            edt_DownP_BankOption_PenDoc.setVisibility(View.GONE);
                        }


                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")) {

                            if (response.body().getData().get(id_pos).getCheck_date_check().equals("0")) {
                                penVal = penVal + 1;
                                edt_DownP_ChequeDate_PenDoc.setFocusable(true);
                                edt_DownP_ChequeDate_PenDoc.setVisibility(View.VISIBLE);
                            } else {
                                edt_DownP_ChequeDate_PenDoc.setFocusable(false);
                                edt_DownP_ChequeDate_PenDoc.setTextColor(Color.parseColor("#43a047"));
                                edt_DownP_ChequeDate_PenDoc.setVisibility(View.GONE);
                            }


                            if (response.body().getData().get(id_pos).getC_amount_check().equals("0")) {
                                penVal = penVal + 1;
                                edt_DownP_ChequeAmount_PenDoc.setFocusable(true);
                                edt_DownP_ChequeAmount_PenDoc.setVisibility(View.VISIBLE);
                            } else {
                                edt_DownP_ChequeAmount_PenDoc.setFocusable(false);
                                edt_DownP_ChequeAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                                edt_DownP_ChequeAmount_PenDoc.setVisibility(View.GONE);
                            }


                            if (response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")) {
                                penVal = penVal + 1;
//                                lin_dp_cheque_PenDoc.setVisibility(View.VISIBLE);
                                txt_DownP_UploadCheque_PenDoc.setVisibility(View.VISIBLE);
                                img_DownP_Cheque_PenDoc.setVisibility(View.VISIBLE);
                            } else {
                                lin_dp_cheque_PenDoc.setVisibility(View.GONE);
                                txt_DownP_UploadCheque_PenDoc.setVisibility(View.GONE);
                                img_DownP_Cheque_PenDoc.setVisibility(View.GONE);
                            }

                            edt_DownP_NEFT_RTGS_date_PenDoc.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_PenDoc.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_PenDoc.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS")) {
                            //-------------------------------------------

                            if (response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")) {
                                penVal = penVal + 1;
                                edt_DownP_NEFT_RTGS_date_PenDoc.setFocusable(true);
                                edt_DownP_NEFT_RTGS_date_PenDoc.setVisibility(View.VISIBLE);
                            } else {
                                edt_DownP_NEFT_RTGS_date_PenDoc.setFocusable(false);
                                edt_DownP_NEFT_RTGS_date_PenDoc.setTextColor(Color.parseColor("#43a047"));
                                edt_DownP_NEFT_RTGS_date_PenDoc.setVisibility(View.GONE);
                            }


                            if (response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")) {
                                penVal = penVal + 1;
                                edt_DownP_NEFT_RTGSAmount_PenDoc.setFocusable(true);
                                edt_DownP_NEFT_RTGSAmount_PenDoc.setVisibility(View.VISIBLE);
                            } else {
                                edt_DownP_NEFT_RTGSAmount_PenDoc.setFocusable(false);
                                edt_DownP_NEFT_RTGSAmount_PenDoc.setTextColor(Color.parseColor("#43a047"));
                                edt_DownP_NEFT_RTGSAmount_PenDoc.setVisibility(View.GONE);
                            }

                            if (response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")) {
                                penVal = penVal + 1;
//                                lin_dp_NEFT_RTGS_PenDoc.setVisibility(View.VISIBLE);
                                txt_DownP_UploadNEFT_RTGS_PenDoc.setVisibility(View.VISIBLE);
                                img_DownP_NEFT_RTGS_PenDoc.setVisibility(View.VISIBLE);
                            } else {
                                lin_dp_NEFT_RTGS_PenDoc.setVisibility(View.GONE);
                                txt_DownP_UploadNEFT_RTGS_PenDoc.setVisibility(View.GONE);
                                img_DownP_NEFT_RTGS_PenDoc.setVisibility(View.GONE);
                            }

                            //-------------------------------------------

                            edt_DownP_ChequeDate_PenDoc.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_PenDoc.setVisibility(View.GONE);
                            lin_dp_cheque_PenDoc.setVisibility(View.GONE);
                        }
                    }
                }


                if (response.body().getData().get(id_pos).getRto_check().equals("0")) {
                    // penVal = penVal+1;
                    edt_RTO_RtoTax_PenDoc.setVisibility(View.VISIBLE);
                    edt_RTO_RtoPassing_PenDoc.setVisibility(View.VISIBLE);
                    edt_RTO_Insurance_PenDoc.setVisibility(View.VISIBLE);
                    edt_RTO_AgentFees_PenDoc.setVisibility(View.VISIBLE);
                    edt_RTO_NumberPlat_PenDoc.setVisibility(View.VISIBLE);
                    edt_RTO_LoanCharge_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    edt_RTO_RtoTax_PenDoc.setVisibility(View.GONE);
                    edt_RTO_RtoPassing_PenDoc.setVisibility(View.GONE);
                    edt_RTO_Insurance_PenDoc.setVisibility(View.GONE);
                    edt_RTO_AgentFees_PenDoc.setVisibility(View.GONE);
                    edt_RTO_NumberPlat_PenDoc.setVisibility(View.GONE);
                    edt_RTO_LoanCharge_PenDoc.setVisibility(View.GONE);
                }

                // if(response.body().getData().get(id_pos).getSkim().equals("No")){
               /* if (response.body().getData().get(id_pos).getSkim().equals("0")) {
                    lin_ConsumerSkim_PenDoc.setVisibility(View.VISIBLE);
                } else {
                    //lin_ConsumerSkim_PenDoc.setVisibility(View.VISIBLE);
                    lin_ConsumerSkim_PenDoc.setVisibility(View.GONE);
                }*/

                // Toast.makeText(PendingDocActivity.this, "" + penVal, Toast.LENGTH_SHORT).show();
                // clearVar;

                if (penVal == 0) {
                    clearVar = "1";
                } else {
                    clearVar = " ";
                }

                Log.d("PenClearVal", "onResponse: " + clearVar + " " + penVal);

                //      Toast.makeText(PendingDocActivity.this, ""+penVal + clearVar, Toast.LENGTH_SHORT).show();

                //gone
                edt_Type_PenDoc.setVisibility(View.GONE);
                edt_Bettry_PenDoc.setVisibility(View.GONE);


                edt_ADBooking_BookingNo_PenDoc.setText(response.body().getData().get(id_pos).getBno());
                edt_ADBooking_BookingType_PenDoc.setText(response.body().getData().get(id_pos).getB_type());
                edt_ADBooking_BookingDate_PenDoc.setText(response.body().getData().get(id_pos).getB_date());
                edt_ADBooking_BookingLoginName_PenDoc.setText(response.body().getData().get(id_pos).getEmp());


                edt_CD_Fname_PenDoc.setText(response.body().getData().get(id_pos).getFname());
                edt_CD_Surname_PenDoc.setText(response.body().getData().get(id_pos).getSname());
                edt_CD_MobileNo_PenDoc.setText(response.body().getData().get(id_pos).getMobileno());
                edt_CD_WhatsappNo_PenDoc.setText(response.body().getData().get(id_pos).getWhno());
                edt_CD_ReferenceName_PenDoc.setText(response.body().getData().get(id_pos).getRef_name());
                edt_CD_ReferenceNo_PenDoc.setText(response.body().getData().get(id_pos).getRef_no());
                edt_CD_State_PenDoc.setText(response.body().getData().get(id_pos).getState());
                edt_CD_City_PenDoc.setText(response.body().getData().get(id_pos).getCity());
                edt_CD_District_PenDoc.setText(response.body().getData().get(id_pos).getDistric());
                edt_CD_Village_PenDoc.setText(response.body().getData().get(id_pos).getVillage());
                edt_CD_EmployeName_PenDoc.setText(response.body().getData().get(id_pos).getEmp());
                edt_CD_Taluko_PenDoc.setText(response.body().getData().get(id_pos).getTehsill());
                edt_CD_PassBook_PenDoc.setText(response.body().getData().get(id_pos).getB_p_photo());
                edt_CD_ChequeBook_PenDoc.setText(response.body().getData().get(id_pos).getCheck_photo());
                edt_CD_PaymentOption_PenDoc.setText(response.body().getData().get(id_pos).getAtype());


                edt_PD_PurchaseName_PenDoc.setText(response.body().getData().get(id_pos).getProduct_name());
                edt_PD_ModelName_PenDoc.setText(response.body().getData().get(id_pos).getModel_name());
                edt_PD_Description_PenDoc.setText(response.body().getData().get(id_pos).getP_desc());


                edt_PriceD_price_PenDoc.setText(response.body().getData().get(id_pos).getDeal_price());
                edt_PriceD_priceInWord_PenDoc.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                edt_PriceD_GST_PenDoc.setText(response.body().getData().get(id_pos).getGst());


                edt_RTO_RtoMain_PenDoc.setText(response.body().getData().get(id_pos).getRto());
                edt_RTO_RtoTax_PenDoc.setText(response.body().getData().get(id_pos).getRto_tax());
                edt_RTO_RtoPassing_PenDoc.setText(response.body().getData().get(id_pos).getRto_passing());
                edt_RTO_Insurance_PenDoc.setText(response.body().getData().get(id_pos).getInsurance());
                edt_RTO_AgentFees_PenDoc.setText(response.body().getData().get(id_pos).getAgent_fee());
                edt_RTO_NumberPlat_PenDoc.setText(response.body().getData().get(id_pos).getNumber_plat());
                edt_RTO_LoanCharge_PenDoc.setText(response.body().getData().get(id_pos).getLoan_charge());

                edt_DownP_BookingAmount_PenDoc.setText(response.body().getData().get(id_pos).getBooking_amt());
                edt_DownP_CashAmount_PenDoc.setText(response.body().getData().get(id_pos).getAmount());
                edt_DownP_BankOption_PenDoc.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                edt_DownP_ChequeDate_PenDoc.setText(response.body().getData().get(id_pos).getCheck_date());
                edt_DownP_ChequeAmount_PenDoc.setText(response.body().getData().get(id_pos).getCheck_amt());
                edt_DownP_NEFT_RTGS_date_PenDoc.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                edt_DownP_NEFT_RTGSAmount_PenDoc.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                edt_DownP_SelectMake_PenDoc.setText(response.body().getData().get(id_pos).getMake());
                edt_DownP_ModelVehicle_PenDoc.setText(response.body().getData().get(id_pos).getModel_name());
                edt_DownP_oldAmount_PenDoc.setText(response.body().getData().get(id_pos).getOld_t_amount());
                edt_DownP_ManufactureYear_PenDoc.setText(response.body().getData().get(id_pos).getM_year());
                edt_DownP_PaperExchange_PenDoc.setText(response.body().getData().get(id_pos).getPaper_expence());
                edt_DownP_oldTractorAmount_PenDoc.setText(response.body().getData().get(id_pos).getC_amount());
                edt_DownP_NOC_PenDoc.setText(response.body().getData().get(id_pos).getNoc());


                edt_CS_ConsumerSkim_PenDoc.setText(response.body().getData().get(id_pos).getSkim());
                edt_CS_Hood_PenDoc.setText(response.body().getData().get(id_pos).getHood());
                edt_CS_TopLink_PenDoc.setText(response.body().getData().get(id_pos).getToplink());
                edt_CS_DrawBar_PenDoc.setText(response.body().getData().get(id_pos).getDrowbar());
                edt_CS_ToolKit_PenDoc.setText(response.body().getData().get(id_pos).getToolkit());
                edt_CS_Bumper_PenDoc.setText(response.body().getData().get(id_pos).getBumper());
                edt_CS_Hitch_PenDoc.setText(response.body().getData().get(id_pos).getHitch());
                edt_CS_Description_PenDoc.setText(response.body().getData().get(id_pos).getDescription());


                edt_PenDocDetail_REF_PenDoc.setText(response.body().getData().get(id_pos).getR_e_name());
                edt_PenDocDetail_FinanceForm_PenDoc.setText(response.body().getData().get(id_pos).getFinance_from());
                edt_PenDocDetail_LoanAmount_PenDoc.setText(response.body().getData().get(id_pos).getLoan_amount());
                edt_PenDocDetail_LoanSancAmont_PenDoc.setText(response.body().getData().get(id_pos).getL_sec_amt());
                edt_PenDocDetail_LoanCharge_PenDoc.setText(response.body().getData().get(id_pos).getLoan_charge());
                edt_PenDocDetail_LandDetail_PenDoc.setText(response.body().getData().get(id_pos).getLand_details());
                edt_PenDocDetail_CibilScore_PenDoc.setText(response.body().getData().get(id_pos).getCibil_score());
                edt_PenDocDetail_FiDate_PenDoc.setText(response.body().getData().get(id_pos).getFi_date());
                edt_PenDocDetail_SanctionDate_PenDoc.setText(response.body().getData().get(0).getSectiondate());
                edt_PenDocDetail_Stage_PenDoc.setText(response.body().getData().get(id_pos).getStage());

                edt_Type_PenDoc.setText(response.body().getData().get(0).getTyre() + "");
                edt_Bettry_PenDoc.setText(response.body().getData().get(0).getBattery() + "");
                edt_PenDocDate_PenDoc.setText(response.body().getData().get(0).getDelivery_date() + "");

                //cash Details
                edt_CashDetail_Date_PenDoc.setText(response.body().getData().get(id_pos).getCash_date());
                edt_CashDetail_Amount_PenDoc.setText(response.body().getData().get(id_pos).getCash_amount());
                edt_CashDetail_Description_PenDoc.setText(response.body().getData().get(id_pos).getCash_description());

            }

            @Override
            public void onFailure(@NotNull Call<PaymentPendingModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("Tag", "onFailure: " + t.getCause());
            }
        });


        txt_CD_UploadBookingPhoto_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        txt_CD_AdharCard_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        txt_CD_ElectionCard_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });

        txt_CD_PassportSize_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });


        txt_DownP_UploadCheque_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });

        txt_DownP_UploadNEFT_RTGS_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

        txt_DownP_UploadNocPhoto_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_DownP_UploadOldVehicle_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });

        txt_DownP_UploadRCBook_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });


        txt_DownP_UploadElectionPhoto_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 11);
            }
        });


        txt_LoanDetail_BankPassBook_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 12);
            }
        });

        txt_LoanDetail_Cheque_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 13);
            }
        });

        txt_LoanDetail_SarpanchId_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 14);
            }
        });

        txt_LoanDetail_SignatureVeri_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 15);
            }
        });


        txt_PenDocPhoto_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 16);
            }
        });

        txt_ChesisPrint_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 17);
            }
        });


        //-------------------------------------------------------------------------------

        txt_CD_AdharCard_PenDoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 18);
            }
        });

        txt_CD_ElectionCard_PenDoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 19);
            }
        });

        txt_CD_PassportSize_PenDoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 20);
            }
        });


        txt_DownP_UploadRCBook_PenDoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 21);
            }
        });

        txt_DownP_UploadElectionPhoto_PenDoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 22);
            }
        });

        txt_LoanDetail_BankPassBook_PenDoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 23);
            }
        });

        //-------------------------------------------------------------------------------------


        edt_PenDocDate_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PendingDocActivity.this,
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
                        edt_PenDocDate_PenDoc.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_PenDocDate_PenDoc.setFocusable(false);

        edt_PenDocDetail_SanctionDate_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PendingDocActivity.this,
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
                        edt_PenDocDetail_SanctionDate_PenDoc.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_PenDocDetail_SanctionDate_PenDoc.setFocusable(false);

        edt_CashDetail_Date_PenDoc.setFocusable(false);

        edt_CashDetail_Date_PenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PendingDocActivity.this,
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
                        edt_CashDetail_Date_PenDoc.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btn_PenDoc_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(PendingDocActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                MultipartBody.Part CD_UploadBookingPhoto_PenDoc = null;
                MultipartBody.Part CD_AdharCard_PenDoc = null;
                MultipartBody.Part CD_ElectionCard_PenDoc = null;
                MultipartBody.Part CD_PassportSize_PenDoc = null;
                MultipartBody.Part UploadDPCheque_PenDoc = null;
                MultipartBody.Part UploadDPNEFT_RTGS_PenDoc = null;
                MultipartBody.Part UploadDPNocPhoto_PenDoc = null;
                MultipartBody.Part UploadDPOldVehicle_PenDoc = null;
                MultipartBody.Part UploadDPRCBook_PenDoc = null;
                MultipartBody.Part UploadDPElectionPhoto_PenDoc = null;

                MultipartBody.Part LoanDetail_BankPassBook_PenDoc = null;
                MultipartBody.Part LoanDetail_Cheque_PenDoc = null;
                MultipartBody.Part LoanDetail_SarpanchId_PenDoc = null;
                MultipartBody.Part LoanDetail_SignatureVeri_PenDoc = null;

                MultipartBody.Part DeliveryPhoto_Passing_PenDoc = null;
                MultipartBody.Part ChesisPrint_PenDoc = null;


                MultipartBody.Part CD_AdharCard_PenDoc2 = null;
                MultipartBody.Part CD_ElectionCard_PenDoc2 = null;
                MultipartBody.Part CD_PassportSize_PenDoc2 = null;

                MultipartBody.Part UploadDPRCBook_PenDoc2 = null;
                MultipartBody.Part UploadDPElectionPhoto_PenDoc2 = null;
                MultipartBody.Part LoanDetail_BankPassBook_PenDoc2 = null;


                if (waypath_CD_UploadBookingPhoto_PenDoc != null) {
                    File file1 = new File(waypath_CD_UploadBookingPhoto_PenDoc);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    CD_UploadBookingPhoto_PenDoc = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }

                if (waypath_CD_AdharCard_PenDoc != null) {
                    File file2 = new File(waypath_CD_AdharCard_PenDoc);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    CD_AdharCard_PenDoc = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }

                if (waypath_CD_ElectionCard_PenDoc != null) {
                    File file3 = new File(waypath_CD_ElectionCard_PenDoc);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    CD_ElectionCard_PenDoc = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                if (waypath_CD_PassportSize_PenDoc != null) {
                    File file4 = new File(waypath_CD_PassportSize_PenDoc);
                    final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                    CD_PassportSize_PenDoc = MultipartBody.Part.createFormData("image6",
                            file4.getName(), requestBody4);
                }


                if (waypathUploadDPCheque_PenDoc != null) {
                    File file5 = new File(waypathUploadDPCheque_PenDoc);
                    final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                    UploadDPCheque_PenDoc = MultipartBody.Part.createFormData("image7",
                            file5.getName(), requestBody5);
                }

                if (waypathUploadDPNEFT_RTGS_PenDoc != null) {
                    File file6 = new File(waypathUploadDPNEFT_RTGS_PenDoc);
                    final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                    UploadDPNEFT_RTGS_PenDoc = MultipartBody.Part.createFormData("image8",
                            file6.getName(), requestBody6);
                }


                if (waypathUploadDPOldVehicle_PenDoc != null) {
                    File file8 = new File(waypathUploadDPOldVehicle_PenDoc);
                    final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                    UploadDPOldVehicle_PenDoc = MultipartBody.Part.createFormData("image9",
                            file8.getName(), requestBody8);
                }

                if (waypathUploadDPRCBook_PenDoc != null) {
                    File file9 = new File(waypathUploadDPRCBook_PenDoc);
                    final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file9);
                    UploadDPRCBook_PenDoc = MultipartBody.Part.createFormData("image10",
                            file9.getName(), requestBody9);
                }

                if (waypathUploadDPElectionPhoto_PenDoc != null) {
                    File file10 = new File(waypathUploadDPElectionPhoto_PenDoc);
                    final RequestBody requestBody10 = RequestBody.create(MediaType.parse("image/*"), file10);
                    UploadDPElectionPhoto_PenDoc = MultipartBody.Part.createFormData("image11",
                            file10.getName(), requestBody10);
                }

                if (waypathUploadDPNocPhoto_PenDoc != null) {
                    File file7 = new File(waypathUploadDPNocPhoto_PenDoc);
                    final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                    UploadDPNocPhoto_PenDoc = MultipartBody.Part.createFormData("image12",
                            file7.getName(), requestBody7);
                }


                if (WayPath_LoanDetail_BankPassBook != null) {
                    File file11 = new File(WayPath_LoanDetail_BankPassBook);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file11);
                    LoanDetail_BankPassBook_PenDoc = MultipartBody.Part.createFormData("do_photo13",
                            file11.getName(), requestBody11);
                }

                if (WayPath_LoanDetail_Cheque != null) {
                    File file12 = new File(WayPath_LoanDetail_Cheque);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file12);
                    LoanDetail_Cheque_PenDoc = MultipartBody.Part.createFormData("do_photo14",
                            file12.getName(), requestBody11);
                }

                if (WayPath_LoanDetail_SarpanchId != null) {
                    File file13 = new File(WayPath_LoanDetail_SarpanchId);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file13);
                    LoanDetail_SarpanchId_PenDoc = MultipartBody.Part.createFormData("do_photo15",
                            file13.getName(), requestBody11);
                }

                if (WayPath_LoanDetail_SignatureVeri != null) {
                    File file14 = new File(WayPath_LoanDetail_SignatureVeri);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file14);
                    LoanDetail_SignatureVeri_PenDoc = MultipartBody.Part.createFormData("do_photo16",
                            file14.getName(), requestBody11);
                }

                if (Waypath_PenDocPhoto_PenDoc != null) {
                    File file15 = new File(Waypath_PenDocPhoto_PenDoc);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file15);
                    DeliveryPhoto_Passing_PenDoc = MultipartBody.Part.createFormData("do_photo17",
                            file15.getName(), requestBody11);
                }

                if (Waypath_ChesisPrint_PenDoc != null) {
                    File file16 = new File(Waypath_ChesisPrint_PenDoc);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file16);
                    ChesisPrint_PenDoc = MultipartBody.Part.createFormData("do_photo18",
                            file16.getName(), requestBody11);
                }


                //-------------------------------------------------------
                if (waypath_CD_AdharCard_PenDoc2 != null) {
                    File file17 = new File(waypath_CD_AdharCard_PenDoc2);
                    final RequestBody requestBody17 = RequestBody.create(MediaType.parse("image/*"), file17);
                    CD_AdharCard_PenDoc2 = MultipartBody.Part.createFormData("imgg1",
                            file17.getName(), requestBody17);
                }

                if (waypath_CD_ElectionCard_PenDoc2 != null) {
                    File file18 = new File(waypath_CD_ElectionCard_PenDoc2);
                    final RequestBody requestBody18 = RequestBody.create(MediaType.parse("image/*"), file18);
                    CD_ElectionCard_PenDoc2 = MultipartBody.Part.createFormData("imgg2",
                            file18.getName(), requestBody18);
                }

                if (waypath_CD_PassportSize_PenDoc2 != null) {
                    File file19 = new File(waypath_CD_PassportSize_PenDoc2);
                    final RequestBody requestBody19 = RequestBody.create(MediaType.parse("image/*"), file19);
                    CD_PassportSize_PenDoc2 = MultipartBody.Part.createFormData("imgg3",
                            file19.getName(), requestBody19);
                }

                if (waypathUploadDPRCBook_PenDoc2 != null) {
                    File file20 = new File(waypathUploadDPRCBook_PenDoc2);
                    final RequestBody requestBody20 = RequestBody.create(MediaType.parse("image/*"), file20);
                    UploadDPRCBook_PenDoc2 = MultipartBody.Part.createFormData("imgg4",
                            file20.getName(), requestBody20);
                }

                if (waypathUploadDPElectionPhoto_PenDoc2 != null) {
                    File file21 = new File(waypathUploadDPElectionPhoto_PenDoc2);
                    final RequestBody requestBody21 = RequestBody.create(MediaType.parse("image/*"), file21);
                    UploadDPElectionPhoto_PenDoc2 = MultipartBody.Part.createFormData("imgg5",
                            file21.getName(), requestBody21);
                }


                if (WayPath_LoanDetail_BankPassBook2 != null) {
                    File file22 = new File(WayPath_LoanDetail_BankPassBook2);
                    final RequestBody requestBody22 = RequestBody.create(MediaType.parse("image/*"), file22);
                    LoanDetail_BankPassBook_PenDoc2 = MultipartBody.Part.createFormData("imgg6",
                            file22.getName(), requestBody22);
                }

                //----------------------------------------------

                WebService.getClient().getDeliverySubmit(
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingNo_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingDate_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), emp),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Fname_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Surname_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_MobileNo_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_WhatsappNo_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceName_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceNo_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_State_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_City_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_District_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Taluko_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Village_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingLoginName_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingType_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PassBook_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ChequeBook_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_PurchaseName_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_ModelName_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_Description_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_price_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_priceInWord_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_GST_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoMain_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoTax_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoPassing_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_Insurance_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_AgentFees_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_NumberPlat_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_LoanCharge_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BookingAmount_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_CashAmount_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BankOption_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeDate_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeAmount_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGS_date_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGSAmount_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_SelectMake_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ModelVehicle_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ManufactureYear_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldAmount_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_PaperExchange_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldTractorAmount_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NOC_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hood_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_TopLink_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_DrawBar_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ToolKit_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Bumper_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hitch_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Description_PenDoc.getText().toString()),
                        CD_UploadBookingPhoto_PenDoc,
                        CD_AdharCard_PenDoc,
                        CD_ElectionCard_PenDoc,
                        CD_PassportSize_PenDoc,
                        UploadDPCheque_PenDoc,
                        UploadDPNEFT_RTGS_PenDoc,
                        UploadDPOldVehicle_PenDoc,
                        UploadDPRCBook_PenDoc,
                        UploadDPElectionPhoto_PenDoc,
                        UploadDPNocPhoto_PenDoc,
                        RequestBody.create(MediaType.parse("text/plain"), idBookingUpload),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_REF_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_FinanceForm_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_LoanAmount_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_LoanSancAmont_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_LoanCharge_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_LandDetail_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_CibilScore_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_FiDate_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_SanctionDate_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDetail_Stage_PenDoc.getText().toString()),
                        LoanDetail_BankPassBook_PenDoc,
                       /* LoanDetail_Cheque_PenDoc ,
                        LoanDetail_SarpanchId_PenDoc ,*/
                        LoanDetail_SignatureVeri_PenDoc,
                        DeliveryPhoto_Passing_PenDoc,
                        ChesisPrint_PenDoc,
                        RequestBody.create(MediaType.parse("text/plain"), edt_PenDocDate_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Type_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Bettry_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PaymentOption_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ConsumerSkim_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Date_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Amount_PenDoc.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Description_PenDoc.getText().toString()),
                        CD_AdharCard_PenDoc2,
                        CD_ElectionCard_PenDoc2,
                        CD_PassportSize_PenDoc2,
                        UploadDPRCBook_PenDoc2,
                        UploadDPElectionPhoto_PenDoc2,
                        LoanDetail_BankPassBook_PenDoc2,
                        RequestBody.create(MediaType.parse("text/plain"), clearVar),
                        RequestBody.create(MediaType.parse("text/plain"), ""),
                        RequestBody.create(MediaType.parse("text/plain"), ""),
                        RequestBody.create(MediaType.parse("text/plain"), ""),
                        RequestBody.create(MediaType.parse("text/plain"), "")
                ).enqueue(new Callback<DeliveryBookingModel>() {
                    @Override
                    public void onResponse(@NotNull Call<DeliveryBookingModel> call,
                                           @NotNull Response<DeliveryBookingModel> response) {
                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(PendingDocActivity.this, ""
                                        + response.body().getMessage() + " ",
                                Toast.LENGTH_LONG).show();

                        Intent i = new Intent(PendingDocActivity.this,
                                BookingDeliveryMainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(@NotNull Call<DeliveryBookingModel> call,
                                          @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Log.d("Fail_data", "onFailure: " + t.getCause() + " " + t.getMessage());
                        Toast.makeText(PendingDocActivity.this, "" + t.getCause(), Toast.LENGTH_SHORT).show();
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
                    uri_CD_UploadBookingPhoto_PenDoc = data.getData();
                    Log.d("PanImageUri", uri_CD_UploadBookingPhoto_PenDoc.toString());
                    waypath_CD_UploadBookingPhoto_PenDoc = getFilePath(this, uri_CD_UploadBookingPhoto_PenDoc);


                    Log.d("PanImage", waypath_CD_UploadBookingPhoto_PenDoc);
                    String[] name = waypath_CD_UploadBookingPhoto_PenDoc.split("/");
                    txt_CD_UploadBookingPhoto_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_Booking_PenDoc.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_PenDoc = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_PenDoc.toString());
                    waypath_CD_AdharCard_PenDoc = getFilePath(this, uri_CD_AdharCard_PenDoc);

                    Log.d("PanRTGS", waypath_CD_AdharCard_PenDoc);
                    String[] name = waypath_CD_AdharCard_PenDoc.split("/");
                    txt_CD_AdharCard_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_PenDoc.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_PenDoc.toString());
                    waypath_CD_ElectionCard_PenDoc = getFilePath(this, uri_CD_ElectionCard_PenDoc);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_PenDoc);
                    String[] name = waypath_CD_ElectionCard_PenDoc.split("/");
                    txt_CD_ElectionCard_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_PenDoc.toString());
                    waypath_CD_PassportSize_PenDoc = getFilePath(this, uri_CD_PassportSize_PenDoc);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_PenDoc);
                    String[] name = waypath_CD_PassportSize_PenDoc.split("/");
                    txt_CD_PassportSize_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPCheque_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPCheque_PenDoc.toString());
                    waypathUploadDPCheque_PenDoc = getFilePath(this, uriUploadDPCheque_PenDoc);
                    Log.d("Pan Image Uri", waypathUploadDPCheque_PenDoc);
                    String[] name = waypathUploadDPCheque_PenDoc.split("/");
                    txt_DownP_UploadCheque_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_Cheque_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNEFT_RTGS_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNEFT_RTGS_PenDoc.toString());
                    waypathUploadDPNEFT_RTGS_PenDoc = getFilePath(this, uriUploadDPNEFT_RTGS_PenDoc);
                    Log.d("Pan Image Uri", waypathUploadDPNEFT_RTGS_PenDoc);
                    String[] name = waypathUploadDPNEFT_RTGS_PenDoc.split("/");
                    txt_DownP_UploadNEFT_RTGS_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NEFT_RTGS_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNocPhoto_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNocPhoto_PenDoc.toString());
                    waypathUploadDPNocPhoto_PenDoc = getFilePath(this, uriUploadDPNocPhoto_PenDoc);
                    Log.d("Pan Image Uri", waypathUploadDPNocPhoto_PenDoc);
                    String[] name = waypathUploadDPNocPhoto_PenDoc.split("/");
                    txt_DownP_UploadNocPhoto_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NocPhoto_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPOldVehicle_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPOldVehicle_PenDoc.toString());
                    waypathUploadDPOldVehicle_PenDoc = getFilePath(this, uriUploadDPOldVehicle_PenDoc);
                    Log.d("Pan Image Uri", waypathUploadDPOldVehicle_PenDoc);
                    String[] name = waypathUploadDPOldVehicle_PenDoc.split("/");
                    txt_DownP_UploadOldVehicle_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_OldVehicle_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_PenDoc.toString());
                    waypathUploadDPRCBook_PenDoc = getFilePath(this, uriUploadDPRCBook_PenDoc);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_PenDoc);
                    String[] name = waypathUploadDPRCBook_PenDoc.split("/");
                    txt_DownP_UploadRCBook_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_PenDoc.toString());
                    waypathUploadDPElectionPhoto_PenDoc = getFilePath(this, uriUploadDPElectionPhoto_PenDoc);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_PenDoc);
                    String[] name = waypathUploadDPElectionPhoto_PenDoc.split("/");
                    txt_DownP_UploadElectionPhoto_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_PenDoc.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_BankPassBook_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook_PenDoc.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_Cheque_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_Cheque_PenDoc.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_SarpanchId_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SarpanchID_PenDoc.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_SignatureVeri_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SignatureVerifi_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_PenDocPhoto_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uri_PenDocPhoto_PenDoc.toString());
                    Waypath_PenDocPhoto_PenDoc = getFilePath(this, uri_PenDocPhoto_PenDoc);
                    Log.d("Pan Image Uri", Waypath_PenDocPhoto_PenDoc);
                    String[] name = Waypath_PenDocPhoto_PenDoc.split("/");
                    txt_PenDocPhoto_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_PenDocPhoto_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 17) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_ChesisPrint_PenDoc = data.getData();
                    Log.d("Pan Image Uri", uri_ChesisPrint_PenDoc.toString());
                    Waypath_ChesisPrint_PenDoc = getFilePath(this, uri_ChesisPrint_PenDoc);
                    Log.d("Pan Image Uri", Waypath_ChesisPrint_PenDoc);
                    String[] name = Waypath_ChesisPrint_PenDoc.split("/");
                    txt_ChesisPrint_PenDoc.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_ChesisPrint_PenDoc.setImageURI(selectedImageUri);
                }
            }
        }


        //------------------------------------------------------------------------------------

        if (requestCode == 18) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_PenDoc2 = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_PenDoc2.toString());
                    waypath_CD_AdharCard_PenDoc2 = getFilePath(this, uri_CD_AdharCard_PenDoc2);

                    Log.d("PanRTGS", waypath_CD_AdharCard_PenDoc2);
                    String[] name = waypath_CD_AdharCard_PenDoc2.split("/");
                    txt_CD_AdharCard_PenDoc2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_PenDoc2.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_PenDoc2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_PenDoc2.toString());
                    waypath_CD_ElectionCard_PenDoc2 = getFilePath(this, uri_CD_ElectionCard_PenDoc2);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_PenDoc2);
                    String[] name = waypath_CD_ElectionCard_PenDoc2.split("/");
                    txt_CD_ElectionCard_PenDoc2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_PenDoc2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_PenDoc2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_PenDoc2.toString());
                    waypath_CD_PassportSize_PenDoc2 = getFilePath(this, uri_CD_PassportSize_PenDoc2);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_PenDoc2);
                    String[] name = waypath_CD_PassportSize_PenDoc2.split("/");
                    txt_CD_PassportSize_PenDoc2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_PenDoc2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 21) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_PenDoc2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_PenDoc2.toString());
                    waypathUploadDPRCBook_PenDoc2 = getFilePath(this, uriUploadDPRCBook_PenDoc2);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_PenDoc2);
                    String[] name = waypathUploadDPRCBook_PenDoc2.split("/");
                    txt_DownP_UploadRCBook_PenDoc2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_PenDoc2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 22) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_PenDoc2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_PenDoc2.toString());
                    waypathUploadDPElectionPhoto_PenDoc2 = getFilePath(this, uriUploadDPElectionPhoto_PenDoc2);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_PenDoc2);
                    String[] name = waypathUploadDPElectionPhoto_PenDoc2.split("/");
                    txt_DownP_UploadElectionPhoto_PenDoc2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_PenDoc2.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_BankPassBook_PenDoc2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook_PenDoc2.setImageURI(selectedImageUri);
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