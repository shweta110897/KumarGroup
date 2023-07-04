package com.example.kumarGroup.BookingDeliveryUpload.DocumentPrint;

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
import com.example.kumarGroup.DocumentPrintDataModel;
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

public class DocumentPrintFormActivity extends AppCompatActivity {


    //Argument Details
    EditText edt_ADBooking_BookingNo_DocPrint, edt_ADBooking_BookingType_DocPrint, edt_ADBooking_BookingDate_DocPrint,
            edt_ADBooking_BookingLoginName_DocPrint;

    TextView txtFillBookingType_DocPrint,txtPassBookBook_DocPrint,txtChequeBook_DocPrint;

    //Customer detail
    EditText edt_CD_Fname_DocPrint, edt_CD_LastName_DocPrint, edt_CD_Surname_DocPrint, edt_CD_MobileNo_DocPrint,
            edt_CD_WhatsappNo_DocPrint,
            edt_CD_ReferenceName_DocPrint, edt_CD_ReferenceNo_DocPrint, edt_CD_State_DocPrint, edt_CD_City_DocPrint,
            edt_CD_District_DocPrint,
            edt_CD_Taluko_DocPrint, edt_CD_Village_DocPrint, edt_CD_EmployeName_DocPrint, edt_CD_PassBook_DocPrint,
            edt_CD_ChequeBook_DocPrint, edt_CD_PaymentOption_DocPrint;


    ImageView img_CD_Booking_DocPrint, img_CD_AdharCard_DocPrint, img_CD_ElectionCard_DocPrint, img_CD_PassportSize_DocPrint;

    TextView txt_CD_UploadBookingPhoto_DocPrint, txt_CD_AdharCard_DocPrint, txt_CD_ElectionCard_DocPrint, txt_CD_PassportSize_DocPrint;


    ImageView img_CD_AdharCard_DocPrint2, img_CD_ElectionCard_DocPrint2, img_CD_PassportSize_DocPrint2;

    TextView txt_CD_AdharCard_DocPrint2, txt_CD_ElectionCard_DocPrint2, txt_CD_PassportSize_DocPrint2;

    //Product detail
    EditText edt_PD_PurchaseName_DocPrint, edt_PD_ModelName_DocPrint, edt_PD_Description_DocPrint;


    //Price detail
    EditText edt_PriceD_price_DocPrint, edt_PriceD_priceInWord_DocPrint, edt_PriceD_GST_DocPrint;

    //RTO Detail
    EditText edt_RTO_RtoMain_DocPrint, edt_RTO_RtoTax_DocPrint, edt_RTO_RtoPassing_DocPrint,
            edt_RTO_Insurance_DocPrint, edt_RTO_AgentFees_DocPrint, edt_RTO_NumberPlat_DocPrint,
            edt_RTO_LoanCharge_DocPrint;


    TextView txtRTOTax, txtRTOPassing, txtInsurance, txtAgentFees, txtNumberPlat, txtLoanCharge;

    //Down Payment
    EditText edt_DownP_BookingAmount_DocPrint, edt_DownP_CashAmount_DocPrint,
            edt_DownP_BankOption_DocPrint, edt_DownP_ChequeDate_DocPrint, edt_DownP_ChequeAmount_DocPrint,
            edt_DownP_NEFT_RTGSAmount_DocPrint, edt_DownP_NEFT_RTGS_date_DocPrint,
            edt_DownP_SelectMake_DocPrint, edt_DownP_ModelVehicle_DocPrint, edt_DownP_ManufactureYear_DocPrint,
            edt_DownP_oldAmount_DocPrint, edt_DownP_PaperExchange_DocPrint, edt_DownP_oldTractorAmount_DocPrint,
            edt_DownP_NOC_DocPrint;

    ImageView img_DownP_Cheque_DocPrint, img_DownP_NEFT_RTGS_DocPrint, img_DownP_NocPhoto_DocPrint,
            img_DownP_OldVehicle_DocPrint, img_DownP_RcBook_DocPrint, img_DownP_ElectionPhoto_DocPrint;

    ImageView img_DownP_RcBook_DocPrint2, img_DownP_ElectionPhoto_DocPrint2;

    TextView txt_DownP_UploadRCBook_DocPrint2, txt_DownP_UploadElectionPhoto_DocPrint2;

    TextView txt_DownP_UploadCheque_DocPrint, txt_DownP_UploadNEFT_RTGS_DocPrint, txt_DownP_UploadNocPhoto_DocPrint,
            txt_DownP_UploadOldVehicle_DocPrint, txt_DownP_UploadRCBook_DocPrint, txt_DownP_UploadElectionPhoto_DocPrint;

    //Down payment label
    TextView txtDPCashAmount_DocPrint, txtDPBankOption_DocPrint, txtDPChequeDate_DocPrint, txtDPChequeAmount_DocPrint,
            txtDPNEFT_RTGSdate_DocPrint,
            txtDPNEFT_RTGSAmount_DocPrint, txtDPMake_DocPrint, txtDPManufectureYear_DocPrint,
            txtDPOldAmount_DocPrint, txtDPModelName_DocPrint,
            txtDPPaperExpense_DocPrint, txtDPOldTractorAmount_DocPrint, txtDPNoc_DocPrint;


    LinearLayout lin_dp_cheque_DocPrint, lin_dp_NEFT_RTGS_DocPrint, lin_dp_NocPhoto_DocPrint,
            lin_dp_OldVehicle_DocPrint, lin_dp_Rcbook_DocPrint,
            lin_dp_Election_DocPrint, lin_dp_Rcbook_DocPrint2,
            lin_dp_Election_DocPrint2;

    //Consumer Details
    EditText edt_CS_Hood_DocPrint, edt_CS_TopLink_DocPrint, edt_CS_DrawBar_DocPrint, edt_CS_ToolKit_DocPrint, edt_CS_Bumper_DocPrint,
            edt_CS_Hitch_DocPrint, edt_CS_Description_DocPrint, edt_CS_ConsumerSkim_DocPrint;


    TextView txt_cs_Hood, txt_cs_TopLink, txt_cs_Drawbar, txt_cs_ToolKit, txt_cs_Bumper,
            txt_cs_Hitch, txt_cs_Description;

    //Loan Details
    EditText edt_DocPrintDetail_REF_DocPrint,
            edt_DocPrintDetail_FinanceForm_DocPrint, edt_DocPrintDetail_LoanAmount_DocPrint,
            edt_DocPrintDetail_LoanSancAmont_DocPrint, edt_DocPrintDetail_LoanCharge_DocPrint,
            edt_DocPrintDetail_LandDetail_DocPrint, edt_DocPrintDetail_CibilScore_DocPrint,
            edt_DocPrintDetail_FiDate_DocPrint, edt_DocPrintDetail_SanctionDate_DocPrint,
            edt_DocPrintDetail_Stage_DocPrint;

    //  Spinner spn_DocPrintDetail_FinanceForm_DocPrint,spn_DocPrintDetail_Stage_DocPrint;

    ImageView img_LoanDetail_BankpassBook_DocPrint, img_LoanDetail_Cheque_DocPrint, img_LoanDetail_SarpanchID_DocPrint,
            img_LoanDetail_SignatureVerifi_DocPrint;

    TextView txt_LoanDetail_BankPassBook_DocPrint, txt_LoanDetail_Cheque_DocPrint, txt_LoanDetail_SarpanchId_DocPrint,
            txt_LoanDetail_SignatureVeri_DocPrint;

    ImageView img_LoanDetail_BankpassBook_DocPrint2;

    TextView txt_LoanDetail_BankPassBook_DocPrint2;

    //Delivery Details
    EditText edt_modelName_DocPrint, edt_ChesisNumber_DocPrint, edt_EngineNumber_DocPrint, edt_Variente_DocPrint,
            edt_Type_DocPrint, edt_Bettry_DocPrint, edt_DocPrintDate_DocPrint;

    TextView txt_Tyre_DocPrint,txt_Battery_DocPrint;

    ImageView img_DocPrintPhoto_DocPrint, img_ChesisPrint_DocPrint;

    TextView txt_DocPrintPhoto_DocPrint, txt_ChesisPrint_DocPrint;


    LinearLayout lin_LoanDetail_DocPrint, lin_cashLoan_DocPrint;

    //Cash Loan
    EditText edt_CashDetail_Date_DocPrint, edt_CashDetail_Amount_DocPrint, edt_CashDetail_Description_DocPrint;

    LinearLayout lin_ConsumerSkim_passing;

    //***************************************************************************************************

    String WayPath_LoanDetail_BankPassBook, WayPath_LoanDetail_Cheque, WayPath_LoanDetail_SarpanchId,
            WayPath_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook, Uri_LoanDetail_Cheque, Uri_LoanDetail_SarpanchId,
            Uri_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook2;
    String WayPath_LoanDetail_BankPassBook2;

    Uri uri_DocPrintPhoto_DocPrint, uri_ChesisPrint_DocPrint;

    String Waypath_DocPrintPhoto_DocPrint, Waypath_ChesisPrint_DocPrint;

    Button btn_DocPrint_Submit;


    String Stage;
    String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};

    Spinner spn_DocPrintDetail_stageloan;

    String StageFinalVal;

    List<String> FinanceName = new ArrayList<>();
    List<String> FInanceID = new ArrayList<>();

    String finance_name, finance_id;

    String idBookingUpload;

    String emp_id;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    Calendar mcurrentdate;
    int day, month, year;


    Uri uri_CD_UploadBookingPhoto_DocPrint, uri_CD_AdharCard_DocPrint, uri_CD_ElectionCard_DocPrint, uri_CD_PassportSize_DocPrint;

    Uri uri_CD_AdharCard_DocPrint2, uri_CD_ElectionCard_DocPrint2, uri_CD_PassportSize_DocPrint2;

    String waypath_CD_AdharCard_DocPrint2, waypath_CD_ElectionCard_DocPrint2, waypath_CD_PassportSize_DocPrint2;

    String waypath_CD_UploadBookingPhoto_DocPrint, waypath_CD_AdharCard_DocPrint, waypath_CD_ElectionCard_DocPrint,
            waypath_CD_PassportSize_DocPrint;

    Uri uriUploadDPRCBook_DocPrint2, uriUploadDPElectionPhoto_DocPrint2;

    String waypathUploadDPRCBook_DocPrint2, waypathUploadDPElectionPhoto_DocPrint2;

    Uri uriUploadDPCheque_DocPrint, uriUploadDPNEFT_RTGS_DocPrint, uriUploadDPNocPhoto_DocPrint,
            uriUploadDPOldVehicle_DocPrint, uriUploadDPRCBook_DocPrint,
            uriUploadDPElectionPhoto_DocPrint;

    String waypathUploadDPCheque_DocPrint, waypathUploadDPNEFT_RTGS_DocPrint, waypathUploadDPNocPhoto_DocPrint,
            waypathUploadDPOldVehicle_DocPrint, waypathUploadDPRCBook_DocPrint, waypathUploadDPElectionPhoto_DocPrint;


    String id_item;

    int id_pos;

    String mydata;

    SwipeRefreshLayout swipeRefreshLayoutDocPrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_print_form);


        getSupportActionBar().setTitle("Document Print Form");

        swipeRefreshLayoutDocPrint = findViewById(R.id.swipeRefreshLayoutDocPrint);

        //Agreement Details
        edt_ADBooking_BookingNo_DocPrint = findViewById(R.id.edt_ADBooking_BookingNo_DocPrint);
        edt_ADBooking_BookingType_DocPrint = findViewById(R.id.edt_ADBooking_BookingType_DocPrint);
        edt_ADBooking_BookingDate_DocPrint = findViewById(R.id.edt_ADBooking_BookingDate_DocPrint);
        edt_ADBooking_BookingLoginName_DocPrint = findViewById(R.id.edt_ADBooking_BookingLoginName_DocPrint);

        txtFillBookingType_DocPrint = findViewById(R.id.txtFillBookingType_DocPrint);
        txtPassBookBook_DocPrint = findViewById(R.id.txtPassBookBook_DocPrint);
        txtChequeBook_DocPrint = findViewById(R.id.txtChequeBook_DocPrint);

        //Customer Detail

        edt_CD_Fname_DocPrint = findViewById(R.id.edt_CD_Fname_DocPrint);
        edt_CD_LastName_DocPrint = findViewById(R.id.edt_CD_LastName_DocPrint);
        edt_CD_Surname_DocPrint = findViewById(R.id.edt_CD_Surname_DocPrint);
        edt_CD_MobileNo_DocPrint = findViewById(R.id.edt_CD_MobileNo_DocPrint);
        edt_CD_WhatsappNo_DocPrint = findViewById(R.id.edt_CD_WhatsappNo_DocPrint);
        edt_CD_ReferenceName_DocPrint = findViewById(R.id.edt_CD_ReferenceName_DocPrint);
        edt_CD_ReferenceNo_DocPrint = findViewById(R.id.edt_CD_ReferenceNo_DocPrint);
        edt_CD_State_DocPrint = findViewById(R.id.edt_CD_State_DocPrint);
        edt_CD_City_DocPrint = findViewById(R.id.edt_CD_City_DocPrint);
        edt_CD_District_DocPrint = findViewById(R.id.edt_CD_District_DocPrint);
        edt_CD_Taluko_DocPrint = findViewById(R.id.edt_CD_Taluko_DocPrint);
        edt_CD_Village_DocPrint = findViewById(R.id.edt_CD_Village_DocPrint);
        edt_CD_EmployeName_DocPrint = findViewById(R.id.edt_CD_EmployeName_DocPrint);
        edt_CD_PassBook_DocPrint = findViewById(R.id.edt_CD_PassBook_DocPrint);
        edt_CD_ChequeBook_DocPrint = findViewById(R.id.edt_CD_ChequeBook_DocPrint);
        edt_CD_PaymentOption_DocPrint = findViewById(R.id.edt_CD_PaymentOption_DocPrint);

        txt_CD_UploadBookingPhoto_DocPrint = findViewById(R.id.txt_CD_UploadBookingPhoto_DocPrint);
        txt_CD_AdharCard_DocPrint = findViewById(R.id.txt_CD_AdharCard_DocPrint);
        txt_CD_ElectionCard_DocPrint = findViewById(R.id.txt_CD_ElectionCard_DocPrint);
      /*  txt_CD_BankPassBook =findViewById(R.id.txt_CD_BankPassBook);
        txt_CD_Cheque =findViewById(R.id.txt_CD_Cheque);*/
        txt_CD_PassportSize_DocPrint = findViewById(R.id.txt_CD_PassportSize_DocPrint);

        txt_CD_AdharCard_DocPrint2 = findViewById(R.id.txt_CD_AdharCard_DocPrint2);
        txt_CD_ElectionCard_DocPrint2 = findViewById(R.id.txt_CD_ElectionCard_DocPrint2);
        txt_CD_PassportSize_DocPrint2 = findViewById(R.id.txt_CD_PassportSize_DocPrint2);

        img_CD_Booking_DocPrint = findViewById(R.id.img_CD_Booking_DocPrint);
        img_CD_AdharCard_DocPrint = findViewById(R.id.img_CD_AdharCard_DocPrint);
        img_CD_ElectionCard_DocPrint = findViewById(R.id.img_CD_ElectionCard_DocPrint);
       /* img_CD_BankPassBook=findViewById(R.id.img_CD_BankPassBook);
        img_CD_Cheque=findViewById(R.id.img_CD_Cheque);*/
        img_CD_PassportSize_DocPrint = findViewById(R.id.img_CD_PassportSize_DocPrint);

        img_CD_AdharCard_DocPrint2 = findViewById(R.id.img_CD_AdharCard_DocPrint2);
        img_CD_ElectionCard_DocPrint2 = findViewById(R.id.img_CD_ElectionCard_DocPrint2);
        img_CD_PassportSize_DocPrint2 = findViewById(R.id.img_CD_PassportSize_DocPrint2);

        //Product Details
        edt_PD_PurchaseName_DocPrint = findViewById(R.id.edt_PD_PurchaseName_DocPrint);
        edt_PD_ModelName_DocPrint = findViewById(R.id.edt_PD_ModelName_DocPrint);
        edt_PD_Description_DocPrint = findViewById(R.id.edt_PD_Description_DocPrint);


        //Price Details
        edt_PriceD_price_DocPrint = findViewById(R.id.edt_PriceD_price_DocPrint);
        edt_PriceD_priceInWord_DocPrint = findViewById(R.id.edt_PriceD_priceInWord_DocPrint);
        edt_PriceD_GST_DocPrint = findViewById(R.id.edt_PriceD_GST_DocPrint);

        //RTO Details
        edt_RTO_RtoMain_DocPrint = findViewById(R.id.edt_RTO_RtoMain_DocPrint);
        edt_RTO_RtoTax_DocPrint = findViewById(R.id.edt_RTO_RtoTax_DocPrint);
        edt_RTO_RtoPassing_DocPrint = findViewById(R.id.edt_RTO_RtoPassing_DocPrint);
        edt_RTO_Insurance_DocPrint = findViewById(R.id.edt_RTO_Insurance_DocPrint);
        edt_RTO_AgentFees_DocPrint = findViewById(R.id.edt_RTO_AgentFees_DocPrint);
        edt_RTO_NumberPlat_DocPrint = findViewById(R.id.edt_RTO_NumberPlat_DocPrint);
        edt_RTO_LoanCharge_DocPrint = findViewById(R.id.edt_RTO_LoanCharge_DocPrint);


        txtRTOTax = findViewById(R.id.txtRTOTax);
        txtRTOPassing = findViewById(R.id.txtRTOPassing);
        txtInsurance = findViewById(R.id.txtInsurance);
        txtAgentFees = findViewById(R.id.txtAgentFees);
        txtNumberPlat = findViewById(R.id.txtNumberPlat);
        txtLoanCharge = findViewById(R.id.txtLoanCharge);


        //Down Payment
        edt_DownP_BookingAmount_DocPrint = findViewById(R.id.edt_DownP_BookingAmount_DocPrint);
        edt_DownP_CashAmount_DocPrint = findViewById(R.id.edt_DownP_CashAmount_DocPrint);
        edt_DownP_BankOption_DocPrint = findViewById(R.id.edt_DownP_BankOption_DocPrint);
        edt_DownP_ChequeDate_DocPrint = findViewById(R.id.edt_DownP_ChequeDate_DocPrint);
        edt_DownP_ChequeAmount_DocPrint = findViewById(R.id.edt_DownP_ChequeAmount_DocPrint);
        edt_DownP_NEFT_RTGS_date_DocPrint = findViewById(R.id.edt_DownP_NEFT_RTGS_date_DocPrint);
        edt_DownP_NEFT_RTGSAmount_DocPrint = findViewById(R.id.edt_DownP_NEFT_RTGSAmount_DocPrint);
        edt_DownP_SelectMake_DocPrint = findViewById(R.id.edt_DownP_SelectMake_DocPrint);
        edt_DownP_ModelVehicle_DocPrint = findViewById(R.id.edt_DownP_ModelVehicle_DocPrint);
        edt_DownP_oldAmount_DocPrint = findViewById(R.id.edt_DownP_oldAmount_DocPrint);
        edt_DownP_ManufactureYear_DocPrint = findViewById(R.id.edt_DownP_ManufactureYear_DocPrint);
        edt_DownP_PaperExchange_DocPrint = findViewById(R.id.edt_DownP_PaperExchange_DocPrint);
        edt_DownP_oldTractorAmount_DocPrint = findViewById(R.id.edt_DownP_oldTractorAmount_DocPrint);
        edt_DownP_NOC_DocPrint = findViewById(R.id.edt_DownP_NOC_DocPrint);


        img_DownP_Cheque_DocPrint = findViewById(R.id.img_DownP_Cheque_DocPrint);
        img_DownP_NEFT_RTGS_DocPrint = findViewById(R.id.img_DownP_NEFT_RTGS_DocPrint);
        img_DownP_NocPhoto_DocPrint = findViewById(R.id.img_DownP_NocPhoto_DocPrint);
        img_DownP_OldVehicle_DocPrint = findViewById(R.id.img_DownP_OldVehicle_DocPrint);
        img_DownP_RcBook_DocPrint = findViewById(R.id.img_DownP_RcBook_DocPrint);
        img_DownP_ElectionPhoto_DocPrint = findViewById(R.id.img_DownP_ElectionPhoto_DocPrint);

        img_DownP_RcBook_DocPrint2 = findViewById(R.id.img_DownP_RcBook_DocPrint2);
        img_DownP_ElectionPhoto_DocPrint2 = findViewById(R.id.img_DownP_ElectionPhoto_DocPrint2);

        txt_DownP_UploadCheque_DocPrint = findViewById(R.id.txt_DownP_UploadCheque_DocPrint);
        txt_DownP_UploadNEFT_RTGS_DocPrint = findViewById(R.id.txt_DownP_UploadNEFT_RTGS_DocPrint);
        txt_DownP_UploadNocPhoto_DocPrint = findViewById(R.id.txt_DownP_UploadNocPhoto_DocPrint);
        txt_DownP_UploadOldVehicle_DocPrint = findViewById(R.id.txt_DownP_UploadOldVehicle_DocPrint);
        txt_DownP_UploadRCBook_DocPrint = findViewById(R.id.txt_DownP_UploadRCBook_DocPrint);
        txt_DownP_UploadElectionPhoto_DocPrint = findViewById(R.id.txt_DownP_UploadElectionPhoto_DocPrint);

        txt_DownP_UploadRCBook_DocPrint2 = findViewById(R.id.txt_DownP_UploadRCBook_DocPrint2);
        txt_DownP_UploadElectionPhoto_DocPrint2 = findViewById(R.id.txt_DownP_UploadElectionPhoto_DocPrint2);

        //Label Textview
        txtDPCashAmount_DocPrint = findViewById(R.id.txtDPCashAmount_DocPrint);
        txtDPBankOption_DocPrint = findViewById(R.id.txtDPBankOption_DocPrint);
        txtDPChequeDate_DocPrint = findViewById(R.id.txtDPChequeDate_DocPrint);
        txtDPChequeAmount_DocPrint = findViewById(R.id.txtDPChequeAmount_DocPrint);
        txtDPNEFT_RTGSdate_DocPrint = findViewById(R.id.txtDPNEFT_RTGSdate_DocPrint);
        txtDPNEFT_RTGSAmount_DocPrint = findViewById(R.id.txtDPNEFT_RTGSAmount_DocPrint);
        txtDPMake_DocPrint = findViewById(R.id.txtDPMake_DocPrint);
        txtDPManufectureYear_DocPrint = findViewById(R.id.txtDPManufectureYear_DocPrint);
        txtDPOldAmount_DocPrint = findViewById(R.id.txtDPOldAmount_DocPrint);
        txtDPModelName_DocPrint = findViewById(R.id.txtDPModelName_DocPrint);
        txtDPPaperExpense_DocPrint = findViewById(R.id.txtDPPaperExpense_DocPrint);
        txtDPOldTractorAmount_DocPrint = findViewById(R.id.txtDPOldTractorAmount_DocPrint);
        txtDPNoc_DocPrint = findViewById(R.id.txtDPNoc_DocPrint);


        lin_dp_cheque_DocPrint = findViewById(R.id.lin_dp_cheque_DocPrint);
        lin_dp_NEFT_RTGS_DocPrint = findViewById(R.id.lin_dp_NEFT_RTGS_DocPrint);
        lin_dp_NocPhoto_DocPrint = findViewById(R.id.lin_dp_NocPhoto_DocPrint);
        lin_dp_OldVehicle_DocPrint = findViewById(R.id.lin_dp_OldVehicle_DocPrint);
        lin_dp_Rcbook_DocPrint = findViewById(R.id.lin_dp_Rcbook_DocPrint);
        lin_dp_Election_DocPrint = findViewById(R.id.lin_dp_Election_DocPrint);

        lin_dp_Rcbook_DocPrint2 = findViewById(R.id.lin_dp_Rcbook_DocPrint2);
        lin_dp_Election_DocPrint2 = findViewById(R.id.lin_dp_Election_DocPrint2);

        //Consumer Skim
        edt_CS_Hood_DocPrint = findViewById(R.id.edt_CS_Hood_DocPrint);
        edt_CS_TopLink_DocPrint = findViewById(R.id.edt_CS_TopLink_DocPrint);
        edt_CS_DrawBar_DocPrint = findViewById(R.id.edt_CS_DrawBar_DocPrint);
        edt_CS_ToolKit_DocPrint = findViewById(R.id.edt_CS_ToolKit_DocPrint);
        edt_CS_Bumper_DocPrint = findViewById(R.id.edt_CS_Bumper_DocPrint);
        edt_CS_Hitch_DocPrint = findViewById(R.id.edt_CS_Hitch_DocPrint);
        edt_CS_Description_DocPrint = findViewById(R.id.edt_CS_Description_DocPrint);
        edt_CS_ConsumerSkim_DocPrint = findViewById(R.id.edt_CS_ConsumerSkim_DocPrint);


       /* txt_cs_Hood=findViewById(R.id.txt_cs_Hood);
        txt_cs_TopLink=findViewById(R.id.txt_cs_TopLink);
        txt_cs_Drawbar=findViewById(R.id.txt_cs_Drawbar);
        txt_cs_ToolKit=findViewById(R.id.txt_cs_ToolKit);
        txt_cs_Bumper=findViewById(R.id.txt_cs_Bumper);
        txt_cs_Hitch=findViewById(R.id.txt_cs_Hitch);
        txt_cs_Description=findViewById(R.id.txt_cs_Description);*/

        //Loan Detail Form

        edt_DocPrintDetail_REF_DocPrint = findViewById(R.id.edt_DocPrintDetail_REF_DocPrint);
        edt_DocPrintDetail_FinanceForm_DocPrint = findViewById(R.id.edt_DocPrintDetail_FinanceForm_DocPrint);
        edt_DocPrintDetail_LoanAmount_DocPrint = findViewById(R.id.edt_DocPrintDetail_LoanAmount_DocPrint);
        edt_DocPrintDetail_LoanSancAmont_DocPrint = findViewById(R.id.edt_DocPrintDetail_LoanSancAmont_DocPrint);
        edt_DocPrintDetail_LoanCharge_DocPrint = findViewById(R.id.edt_DocPrintDetail_LoanCharge_DocPrint);
        edt_DocPrintDetail_LandDetail_DocPrint = findViewById(R.id.edt_DocPrintDetail_LandDetail_DocPrint);
        edt_DocPrintDetail_CibilScore_DocPrint = findViewById(R.id.edt_DocPrintDetail_CibilScore_DocPrint);
        edt_DocPrintDetail_FiDate_DocPrint = findViewById(R.id.edt_DocPrintDetail_FiDate_DocPrint);
        edt_DocPrintDetail_SanctionDate_DocPrint = findViewById(R.id.edt_DocPrintDetail_SanctionDate_DocPrint);

        /*spn_DocPrintDetail_FinanceForm_DocPrint= findViewById(R.id.spn_DocPrintDetail_FinanceForm_DocPrint);
        spn_DocPrintDetail_Stage_DocPrint= findViewById(R.id.spn_DocPrintDetail_Stage_DocPrint);*/

        edt_DocPrintDetail_Stage_DocPrint = findViewById(R.id.edt_DocPrintDetail_Stage_DocPrint);
        spn_DocPrintDetail_stageloan = findViewById(R.id.spn_DocPrintDetail_stageloan);

        img_LoanDetail_BankpassBook_DocPrint2 = findViewById(R.id.img_LoanDetail_BankpassBook_DocPrint2);
        txt_LoanDetail_BankPassBook_DocPrint2 = findViewById(R.id.txt_LoanDetail_BankPassBook_DocPrint2);


        img_LoanDetail_BankpassBook_DocPrint = findViewById(R.id.img_LoanDetail_BankpassBook_DocPrint);
        img_LoanDetail_Cheque_DocPrint = findViewById(R.id.img_LoanDetail_Cheque_DocPrint);
        img_LoanDetail_SarpanchID_DocPrint = findViewById(R.id.img_LoanDetail_SarpanchID_DocPrint);
        img_LoanDetail_SignatureVerifi_DocPrint = findViewById(R.id.img_LoanDetail_SignatureVerifi_DocPrint);


        txt_LoanDetail_BankPassBook_DocPrint = findViewById(R.id.txt_LoanDetail_BankPassBook_DocPrint);
        txt_LoanDetail_Cheque_DocPrint = findViewById(R.id.txt_LoanDetail_Cheque_DocPrint);
        txt_LoanDetail_SarpanchId_DocPrint = findViewById(R.id.txt_LoanDetail_SarpanchId_DocPrint);
        txt_LoanDetail_SignatureVeri_DocPrint = findViewById(R.id.txt_LoanDetail_SignatureVeri_DocPrint);


        //Delivery Details
     /*   edt_modelName_DocPrint = findViewById(R.id.edt_modelName_DocPrint);
        edt_ChesisNumber_DocPrint = findViewById(R.id.edt_ChesisNumber_DocPrint);
        edt_EngineNumber_DocPrint = findViewById(R.id.edt_EngineNumber_DocPrint);
        edt_Variente_DocPrint = findViewById(R.id.edt_Variente_DocPrint);*/
        txt_Tyre_DocPrint = findViewById(R.id.txt_Tyre_DocPrint);
        edt_Type_DocPrint = findViewById(R.id.edt_Type_DocPrint);
        txt_Battery_DocPrint = findViewById(R.id.txt_Battery_DocPrint);
        edt_Bettry_DocPrint = findViewById(R.id.edt_Bettry_DocPrint);
        edt_DocPrintDate_DocPrint = findViewById(R.id.edt_DocPrintDate_DocPrint);

        img_DocPrintPhoto_DocPrint = findViewById(R.id.img_DocPrintPhoto_DocPrint);
        img_ChesisPrint_DocPrint = findViewById(R.id.img_ChesisPrint_DocPrint);

        txt_DocPrintPhoto_DocPrint = findViewById(R.id.txt_DocPrintPhoto_DocPrint);
        txt_ChesisPrint_DocPrint = findViewById(R.id.txt_ChesisPrint_DocPrint);


        lin_LoanDetail_DocPrint = findViewById(R.id.lin_LoanDetail_DocPrint);
        lin_cashLoan_DocPrint = findViewById(R.id.lin_cashLoan_DocPrint);

        edt_CashDetail_Date_DocPrint = findViewById(R.id.edt_CashDetail_Date_DocPrint);
        edt_CashDetail_Amount_DocPrint = findViewById(R.id.edt_CashDetail_Amount_DocPrint);
        edt_CashDetail_Description_DocPrint = findViewById(R.id.edt_CashDetail_Description_DocPrint);

        lin_ConsumerSkim_passing = findViewById(R.id.lin_ConsumerSkim_passing);


        btn_DocPrint_Submit = findViewById(R.id.btn_DocPrint_Submit);

        /** ********************************************************************************************************** */

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        emp = sp.getString("emp_id", "");


        idBookingUpload = getIntent().getStringExtra("idBookingUpload");


        id_item = getIntent().getStringExtra("position");

        id_pos = Integer.parseInt(id_item);

        //  Toast.makeText(DeliveryFormActivity.this, ""+id_item+" "+idBookingUpload, Toast.LENGTH_SHORT).show();

        //DatePicker for Requirement Date
        mcurrentdate = Calendar.getInstance();
        day = mcurrentdate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentdate.get(Calendar.MONTH);
        year = mcurrentdate.get(Calendar.YEAR);

        progressDialog = new ProgressDialog(DocumentPrintFormActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebService.getClient().getDocPrint(emp).enqueue(new Callback<DocumentPrintDataModel>() {
            @Override
            public void onResponse(@NotNull Call<DocumentPrintDataModel> call, @NotNull Response<DocumentPrintDataModel> response) {
                progressDialog.dismiss();

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getB_photo())
                        .into(img_CD_Booking_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getAdhar_photo())
                        .into(img_CD_AdharCard_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getEcard_photo())
                        .into(img_CD_ElectionCard_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getP_photo())
                        .into(img_CD_PassportSize_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getCheck1_photo())
                        .into(img_DownP_Cheque_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getNeft_rtgs_photo())
                        .into(img_DownP_NEFT_RTGS_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getNoc_photo())
                        .into(img_DownP_NocPhoto_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getOld_vehicle_photo())
                        .into(img_DownP_OldVehicle_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getRecbook_photo())
                        .into(img_DownP_RcBook_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getEc_photo())
                        .into(img_DownP_ElectionPhoto_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getLb_pb_photo())
                        .into(img_LoanDetail_BankpassBook_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getL_c_photo())
                        .into(img_LoanDetail_Cheque_DocPrint);


                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getSar_id_photo())
                        .into(img_LoanDetail_SarpanchID_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getSign_veri())
                        .into(img_LoanDetail_SignatureVerifi_DocPrint);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getDelivry_photo())
                        .into(img_DocPrintPhoto_DocPrint);


                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getChasis_print())
                        .into(img_ChesisPrint_DocPrint);


                //-----------------------------------------------------------------------------
                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getAdhar_back())
                        .into(img_CD_AdharCard_DocPrint2);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getElection_back())
                        .into(img_CD_ElectionCard_DocPrint2);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getP_photo_back())
                        .into(img_CD_PassportSize_DocPrint2);


                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getRcbook_back())
                        .into(img_DownP_RcBook_DocPrint2);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getElec_back())
                        .into(img_DownP_ElectionPhoto_DocPrint2);

                Glide.with(DocumentPrintFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getB_pass_back())
                        .into(img_LoanDetail_BankpassBook_DocPrint2);

                //-----------------------------------------------------------

                //delivry_photo


                emp_id = response.body().getData().get(id_pos).getEmp_id();


                if(response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                        response.body().getData().get(id_pos).getProduct_name().equals("Spar part"))
                {
                    edt_ADBooking_BookingType_DocPrint.setVisibility(View.GONE);
                    edt_CD_PassBook_DocPrint.setVisibility(View.GONE);
                    edt_CD_ChequeBook_DocPrint.setVisibility(View.GONE);
                    txtFillBookingType_DocPrint.setVisibility(View.GONE);
                    txtPassBookBook_DocPrint.setVisibility(View.GONE);
                    txtChequeBook_DocPrint.setVisibility(View.GONE);


                    txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.GONE);
                    txt_CD_AdharCard_DocPrint.setVisibility(View.GONE);
                    txt_CD_ElectionCard_DocPrint.setVisibility(View.GONE);
                    txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                    txt_CD_AdharCard_DocPrint2.setVisibility(View.GONE);
                    txt_CD_ElectionCard_DocPrint2.setVisibility(View.GONE);
                    txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);

                    img_CD_Booking_DocPrint.setVisibility(View.GONE);
                    img_CD_AdharCard_DocPrint.setVisibility(View.GONE);
                    img_CD_ElectionCard_DocPrint.setVisibility(View.GONE);
                    img_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                    img_CD_AdharCard_DocPrint2.setVisibility(View.GONE);
                    img_CD_ElectionCard_DocPrint2.setVisibility(View.GONE);
                    img_CD_PassportSize_DocPrint2.setVisibility(View.GONE);


                    txt_Tyre_DocPrint.setVisibility(View.GONE);
                    edt_Type_DocPrint.setVisibility(View.GONE);
                    edt_Bettry_DocPrint.setVisibility(View.GONE);
                    txt_Battery_DocPrint.setVisibility(View.GONE);

                }
                else{
                    edt_ADBooking_BookingType_DocPrint.setVisibility(View.VISIBLE);
                    edt_CD_PassBook_DocPrint.setVisibility(View.VISIBLE);
                    edt_CD_ChequeBook_DocPrint.setVisibility(View.VISIBLE);
                    txtFillBookingType_DocPrint.setVisibility(View.VISIBLE);
                    txtPassBookBook_DocPrint.setVisibility(View.VISIBLE);
                    txtChequeBook_DocPrint.setVisibility(View.VISIBLE);


                    txt_Tyre_DocPrint.setVisibility(View.VISIBLE);
                    edt_Type_DocPrint.setVisibility(View.VISIBLE);
                    edt_Bettry_DocPrint.setVisibility(View.VISIBLE);
                    txt_Battery_DocPrint.setVisibility(View.VISIBLE);

                    //-----------------------------------------------------------
                    if (response.body().getData().get(id_pos).getTyre_check().equals("0")) {
                        edt_Type_DocPrint.setFocusable(true);
                    } else {
                        edt_Type_DocPrint.setFocusable(false);
                        edt_Type_DocPrint.setTextColor(Color.parseColor("#43a047"));
                    }

                    if (response.body().getData().get(id_pos).getBattery_check().equals("0")) {
                        edt_Bettry_DocPrint.setFocusable(true);
                    } else {
                        edt_Bettry_DocPrint.setFocusable(false);
                        edt_Bettry_DocPrint.setTextColor(Color.parseColor("#43a047"));
                    }
                    //-----------------------------------------------------------


                    if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                        txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                        txt_CD_AdharCard_DocPrint.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_AdharCard_DocPrint.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                        txt_CD_ElectionCard_DocPrint.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_ElectionCard_DocPrint.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                        txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                        txt_CD_AdharCard_DocPrint2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_AdharCard_DocPrint2.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getElection_back_check().equals("0")){
                        txt_CD_ElectionCard_DocPrint2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_ElectionCard_DocPrint2.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                        txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                    }

                }



                if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                    img_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                    txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);

                    img_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                    txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);

                    if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                    }

                } else {
                    txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                    img_CD_PassportSize_DocPrint2.setVisibility(View.GONE);

                    txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                    img_CD_PassportSize_DocPrint.setVisibility(View.GONE);

                }



                //-----------------------------------------------------------

                if (response.body().getData().get(id_pos).getBno_check().equals("0")) {
                    edt_ADBooking_BookingNo_DocPrint.setFocusable(true);
                } else {
                    edt_ADBooking_BookingNo_DocPrint.setFocusable(false);
                    edt_ADBooking_BookingNo_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getB_date_check().equals("0")) {
                    edt_ADBooking_BookingDate_DocPrint.setFocusable(true);
                } else {
                    edt_ADBooking_BookingDate_DocPrint.setFocusable(false);
                    edt_ADBooking_BookingDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }




                if (response.body().getData().get(id_pos).getB_type_check().equals("0")) {
                    edt_ADBooking_BookingType_DocPrint.setFocusable(true);
                } else {
                    edt_ADBooking_BookingType_DocPrint.setFocusable(false);
                    edt_ADBooking_BookingType_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                    edt_ADBooking_BookingLoginName_DocPrint.setFocusable(true);
                } else {
                    edt_ADBooking_BookingLoginName_DocPrint.setFocusable(false);
                    edt_ADBooking_BookingLoginName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getFname_check().equals("0")) {
                    edt_CD_Fname_DocPrint.setFocusable(true);
                } else {
                    edt_CD_Fname_DocPrint.setFocusable(false);
                    edt_CD_Fname_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getLname_check().equals("0")) {
                    edt_CD_LastName_DocPrint.setFocusable(true);
                } else {
                    edt_CD_LastName_DocPrint.setFocusable(false);
                    edt_CD_LastName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getSname_check().equals("0")) {
                    edt_CD_Surname_DocPrint.setFocusable(true);
                } else {
                    edt_CD_Surname_DocPrint.setFocusable(false);
                    edt_CD_Surname_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getMobileno_check().equals("0")) {
                    edt_CD_MobileNo_DocPrint.setFocusable(true);
                } else {
                    edt_CD_MobileNo_DocPrint.setFocusable(false);
                    edt_CD_MobileNo_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getWhno_check().equals("0")) {
                    edt_CD_WhatsappNo_DocPrint.setFocusable(true);
                } else {
                    edt_CD_WhatsappNo_DocPrint.setFocusable(false);
                    edt_CD_WhatsappNo_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getRef_name_check().equals("0")) {
                    edt_CD_ReferenceName_DocPrint.setFocusable(true);
                } else {
                    edt_CD_ReferenceName_DocPrint.setFocusable(false);
                    edt_CD_ReferenceName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getRef_no_check().equals("0")) {
                    edt_CD_ReferenceNo_DocPrint.setFocusable(true);
                } else {
                    edt_CD_ReferenceNo_DocPrint.setFocusable(false);
                    edt_CD_ReferenceNo_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getState_check().equals("0")) {
                    edt_CD_State_DocPrint.setFocusable(true);
                } else {
                    edt_CD_State_DocPrint.setFocusable(false);
                    edt_CD_State_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCity_check().equals("0")) {
                    edt_CD_City_DocPrint.setFocusable(true);
                } else {
                    edt_CD_City_DocPrint.setFocusable(false);
                    edt_CD_City_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDistric_check().equals("0")) {
                    edt_CD_District_DocPrint.setFocusable(true);
                } else {
                    edt_CD_District_DocPrint.setFocusable(false);
                    edt_CD_District_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getVillage_check().equals("0")) {
                    edt_CD_Village_DocPrint.setFocusable(true);
                } else {
                    edt_CD_Village_DocPrint.setFocusable(false);
                    edt_CD_Village_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                    edt_CD_EmployeName_DocPrint.setFocusable(true);
                } else {
                    edt_CD_EmployeName_DocPrint.setFocusable(false);
                    edt_CD_EmployeName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getTehsill_check().equals("0")) {
                    edt_CD_Taluko_DocPrint.setFocusable(true);
                } else {
                    edt_CD_Taluko_DocPrint.setFocusable(false);
                    edt_CD_Taluko_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getProduct_name_check().equals("0")) {
                    edt_PD_PurchaseName_DocPrint.setFocusable(true);
                } else {
                    edt_PD_PurchaseName_DocPrint.setFocusable(false);
                    edt_PD_PurchaseName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getModel_name_check().equals("0")) {
                    edt_PD_ModelName_DocPrint.setFocusable(true);
                } else {
                    edt_PD_ModelName_DocPrint.setFocusable(false);
                    edt_PD_ModelName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getP_desc_check().equals("0")) {
                    edt_PD_Description_DocPrint.setFocusable(true);
                } else {
                    edt_PD_Description_DocPrint.setFocusable(false);
                    edt_PD_Description_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDeal_price_check().equals("0")) {
                    edt_PriceD_price_DocPrint.setFocusable(true);
                } else {
                    edt_PriceD_price_DocPrint.setFocusable(false);
                    edt_PriceD_price_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")) {
                    edt_PriceD_priceInWord_DocPrint.setFocusable(true);
                } else {
                    edt_PriceD_priceInWord_DocPrint.setFocusable(false);
                    edt_PriceD_priceInWord_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getGst_check().equals("0")) {
                    edt_PriceD_GST_DocPrint.setFocusable(true);
                } else {
                    edt_PriceD_GST_DocPrint.setFocusable(false);
                    edt_PriceD_GST_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getRto_check().equals("0")) {
                    edt_RTO_RtoMain_DocPrint.setFocusable(true);
                } else {
                    edt_RTO_RtoMain_DocPrint.setFocusable(false);
                    edt_RTO_RtoMain_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getRto_tax_check().equals("0")) {
                    edt_RTO_RtoTax_DocPrint.setFocusable(true);
                } else {
                    edt_RTO_RtoTax_DocPrint.setFocusable(false);
                    edt_RTO_RtoTax_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getRto_passing_check().equals("0")) {
                    edt_RTO_RtoPassing_DocPrint.setFocusable(true);
                } else {
                    edt_RTO_RtoPassing_DocPrint.setFocusable(false);
                    edt_RTO_RtoPassing_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getInsurance_check().equals("0")) {
                    edt_RTO_Insurance_DocPrint.setFocusable(true);
                } else {
                    edt_RTO_Insurance_DocPrint.setFocusable(false);
                    edt_RTO_Insurance_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getAgent_fee_check().equals("0")) {
                    edt_RTO_AgentFees_DocPrint.setFocusable(true);
                } else {
                    edt_RTO_AgentFees_DocPrint.setFocusable(false);
                    edt_RTO_AgentFees_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getNumber_plat_check().equals("0")) {
                    edt_RTO_NumberPlat_DocPrint.setFocusable(true);
                } else {
                    edt_RTO_NumberPlat_DocPrint.setFocusable(false);
                    edt_RTO_NumberPlat_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getLoan_charge_check().equals("0")) {
                    edt_RTO_LoanCharge_DocPrint.setFocusable(true);
                } else {
                    edt_RTO_LoanCharge_DocPrint.setFocusable(false);
                    edt_RTO_LoanCharge_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getBooking_amt_check().equals("0")) {
                    edt_DownP_BookingAmount_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_BookingAmount_DocPrint.setFocusable(false);
                    edt_DownP_BookingAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getAmount_check().equals("0")) {
                    edt_DownP_CashAmount_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_CashAmount_DocPrint.setFocusable(false);
                    edt_DownP_CashAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCheck_neft_rtgs_check().equals("0")) {
                    edt_DownP_BankOption_DocPrint.setFocusable(false);
                } else {
                    edt_DownP_BankOption_DocPrint.setFocusable(false);
                    edt_DownP_BankOption_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCheck_date_check().equals("0")) {
                    edt_DownP_ChequeDate_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_ChequeDate_DocPrint.setFocusable(false);
                    edt_DownP_ChequeDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getC_amount_check().equals("0")) {
                    edt_DownP_ChequeAmount_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_ChequeAmount_DocPrint.setFocusable(false);
                    edt_DownP_ChequeAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")) {
                    edt_DownP_NEFT_RTGS_date_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_NEFT_RTGS_date_DocPrint.setFocusable(false);
                    edt_DownP_NEFT_RTGS_date_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")) {
                    edt_DownP_NEFT_RTGSAmount_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_NEFT_RTGSAmount_DocPrint.setFocusable(false);
                    edt_DownP_NEFT_RTGSAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getMake_check().equals("0")) {
                    edt_DownP_SelectMake_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_SelectMake_DocPrint.setFocusable(false);
                    edt_DownP_SelectMake_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getModel_check().equals("0")) {
                    edt_DownP_ModelVehicle_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_ModelVehicle_DocPrint.setFocusable(false);
                    edt_DownP_ModelVehicle_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                    edt_DownP_oldAmount_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_oldAmount_DocPrint.setFocusable(false);
                    edt_DownP_oldAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getM_year_check().equals("0")) {
                    edt_DownP_ManufactureYear_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_ManufactureYear_DocPrint.setFocusable(false);
                    edt_DownP_ManufactureYear_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getPaper_expence_check().equals("0")) {
                    edt_DownP_PaperExchange_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_PaperExchange_DocPrint.setFocusable(false);
                    edt_DownP_PaperExchange_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                    edt_DownP_oldTractorAmount_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_oldTractorAmount_DocPrint.setFocusable(false);
                    edt_DownP_oldTractorAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getNoc_check().equals("0")) {
                    edt_DownP_NOC_DocPrint.setFocusable(true);
                } else {
                    edt_DownP_NOC_DocPrint.setFocusable(false);
                    edt_DownP_NOC_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getHood_check().equals("0")) {
                    edt_CS_Hood_DocPrint.setFocusable(true);
                } else {
                    edt_CS_Hood_DocPrint.setFocusable(false);
                    edt_CS_Hood_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getToplink_check().equals("0")) {
                    edt_CS_TopLink_DocPrint.setFocusable(true);
                } else {
                    edt_CS_TopLink_DocPrint.setFocusable(false);
                    edt_CS_TopLink_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDrowbar_check().equals("0")) {
                    edt_CS_DrawBar_DocPrint.setFocusable(true);
                } else {
                    edt_CS_DrawBar_DocPrint.setFocusable(false);
                    edt_CS_DrawBar_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getToolkit_check().equals("0")) {
                    edt_CS_ToolKit_DocPrint.setFocusable(true);
                } else {
                    edt_CS_ToolKit_DocPrint.setFocusable(false);
                    edt_CS_ToolKit_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getBumper_check().equals("0")) {
                    edt_CS_Bumper_DocPrint.setFocusable(true);
                } else {
                    edt_CS_Bumper_DocPrint.setFocusable(false);
                    edt_CS_Bumper_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getHitch_check().equals("0")) {
                    edt_CS_Hitch_DocPrint.setFocusable(true);
                } else {
                    edt_CS_Hitch_DocPrint.setFocusable(false);
                    edt_CS_Hitch_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDescription_check().equals("0")) {
                    edt_CS_Description_DocPrint.setFocusable(true);
                } else {
                    edt_CS_Description_DocPrint.setFocusable(false);
                    edt_CS_Description_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getB_p_photo_check().equals("0")) {
                    edt_CD_PassBook_DocPrint.setFocusable(true);
                } else {
                    edt_CD_PassBook_DocPrint.setFocusable(false);
                    edt_CD_PassBook_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCheck_photo_check().equals("0")) {
                    edt_CD_ChequeBook_DocPrint.setFocusable(true);
                } else {
                    edt_CD_ChequeBook_DocPrint.setFocusable(false);
                    edt_CD_ChequeBook_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getR_e_name_check().equals("0")) {
                    edt_DocPrintDetail_REF_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_REF_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_REF_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getFinance_from_check().equals("0")) {
                    edt_DocPrintDetail_FinanceForm_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_FinanceForm_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_FinanceForm_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getLoan_amount_check().equals("0")) {
                    edt_DocPrintDetail_LoanAmount_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_LoanAmount_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_LoanAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")) {
                    edt_DocPrintDetail_LoanSancAmont_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_LoanSancAmont_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_LoanSancAmont_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getLloan_charge_check().equals("0")) {
                    edt_DocPrintDetail_LoanCharge_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_LoanCharge_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_LoanCharge_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getLand_details_check().equals("0")) {
                    edt_DocPrintDetail_LandDetail_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_LandDetail_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_LandDetail_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCibil_score_check().equals("0")) {
                    edt_DocPrintDetail_CibilScore_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_CibilScore_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_CibilScore_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getFi_date_check().equals("0")) {
                    edt_DocPrintDetail_FiDate_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_FiDate_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_FiDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getSectiondate_check().equals("0")) {
                    edt_DocPrintDetail_SanctionDate_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDetail_SanctionDate_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_SanctionDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getStage_check().equals("0")) {
                    edt_DocPrintDetail_Stage_DocPrint.setFocusable(true);
                    spn_DocPrintDetail_stageloan.setVisibility(View.VISIBLE);
                    edt_DocPrintDetail_Stage_DocPrint.setVisibility(View.GONE);

                } else {
                    spn_DocPrintDetail_stageloan.setVisibility(View.GONE);
                    edt_DocPrintDetail_Stage_DocPrint.setVisibility(View.VISIBLE);
                    edt_DocPrintDetail_Stage_DocPrint.setFocusable(false);
                    edt_DocPrintDetail_Stage_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getSkim_check().equals("0")) {
                    edt_CS_ConsumerSkim_DocPrint.setFocusable(true);
                } else {
                    edt_CS_ConsumerSkim_DocPrint.setFocusable(false);
                    edt_CS_ConsumerSkim_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getAtype_check().equals("0")) {
                    edt_CD_PaymentOption_DocPrint.setFocusable(true);
                } else {
                    edt_CD_PaymentOption_DocPrint.setFocusable(false);
                    edt_CD_PaymentOption_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getCash_date_check().equals("0")) {
                    edt_CashDetail_Date_DocPrint.setFocusable(true);
                } else {
                    edt_CashDetail_Date_DocPrint.setFocusable(false);
                    edt_CashDetail_Date_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getCash_amount_check().equals("0")) {
                    edt_CashDetail_Amount_DocPrint.setFocusable(true);
                } else {
                    edt_CashDetail_Amount_DocPrint.setFocusable(false);
                    edt_CashDetail_Amount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCash_description_check().equals("0")) {
                    edt_CashDetail_Description_DocPrint.setFocusable(true);
                } else {
                    edt_CashDetail_Description_DocPrint.setFocusable(false);
                    edt_CashDetail_Description_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


               /* if (response.body().getData().get(id_pos).getTyre_check().equals("0")) {
                    edt_Type_DocPrint.setFocusable(true);
                } else {
                    edt_Type_DocPrint.setFocusable(false);
                    edt_Type_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getBattery_check().equals("0")) {
                    edt_Bettry_DocPrint.setFocusable(true);
                } else {
                    edt_Bettry_DocPrint.setFocusable(false);
                    edt_Bettry_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }*/


                if (response.body().getData().get(id_pos).getDelivery_date_check().equals("0")) {
                    edt_DocPrintDate_DocPrint.setFocusable(true);
                } else {
                    edt_DocPrintDate_DocPrint.setFocusable(false);
                    edt_DocPrintDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                }


                /** ************************************************************************************* */

               /* if (response.body().getData().get(id_pos).getB_photo_check().equals("0")) {
                    txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")) {
                    txt_CD_AdharCard_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_CD_AdharCard_DocPrint.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getEcard_photo_check().equals("0")) {
                    txt_CD_ElectionCard_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_CD_ElectionCard_DocPrint.setVisibility(View.GONE);
                }*/

                /*if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                    txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                }*/

                if (response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")) {
                    txt_DownP_UploadCheque_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadCheque_DocPrint.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")) {
                    txt_DownP_UploadNEFT_RTGS_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadNEFT_RTGS_DocPrint.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getNoc_photo_check().equals("0")) {
                    txt_DownP_UploadNocPhoto_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadNocPhoto_DocPrint.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")) {
                    txt_DownP_UploadOldVehicle_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadOldVehicle_DocPrint.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")) {
                    txt_DownP_UploadRCBook_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadRCBook_DocPrint.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getEc_photo_check().equals("0")) {
                    txt_DownP_UploadElectionPhoto_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadElectionPhoto_DocPrint.setVisibility(View.GONE);
                }


                //----------------------------------------------------------------------------

                /*if (response.body().getData().get(id_pos).getAdhar_back_check().equals("0")) {
                    txt_CD_AdharCard_DocPrint2.setVisibility(View.VISIBLE);

                } else {
                    txt_CD_AdharCard_DocPrint2.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getElection_back_check().equals("0")) {
                    txt_CD_ElectionCard_DocPrint2.setVisibility(View.VISIBLE);
                } else {
                    txt_CD_ElectionCard_DocPrint2.setVisibility(View.GONE);
                }*/

               /* if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                    txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                } else {
                    txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                }*/


                if (response.body().getData().get(id_pos).getRcbook_back_check().equals("0")) {
                    txt_DownP_UploadRCBook_DocPrint2.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadRCBook_DocPrint2.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getElec_back_check().equals("0")) {
                    txt_DownP_UploadElectionPhoto_DocPrint2.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadElectionPhoto_DocPrint2.setVisibility(View.GONE);
                }

                //
                if (response.body().getData().get(id_pos).getB_pass_back_check().equals("0")) {
                    txt_LoanDetail_BankPassBook_DocPrint2.setVisibility(View.VISIBLE);
                } else {
                    txt_LoanDetail_BankPassBook_DocPrint2.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                    txt_LoanDetail_BankPassBook_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_LoanDetail_BankPassBook_DocPrint.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getDelivry_photo_check().equals("0")){
                    txt_DocPrintPhoto_DocPrint.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DocPrintPhoto_DocPrint.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getChasis_print_check().equals("0")) {
                    txt_ChesisPrint_DocPrint.setVisibility(View.VISIBLE);
                } else {
                    txt_ChesisPrint_DocPrint.setVisibility(View.GONE);
                }


                /** ********************************************************************************** */

                if (response.body().getData().get(id_pos).getStage().equals("Pending")) {
                    String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                    spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                    spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                    spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                    spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                    spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                    spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                    spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (response.body().getData().get(id_pos).getStage_check().equals("0")) {

                    StageFinalVal = Stage;

                } else {

                    StageFinalVal = edt_DocPrintDetail_Stage_DocPrint.getText().toString();
                }

                if( StageFinalVal == null){
                    StageFinalVal=" ";
                }



                /** ********************************************************************************** */

                String data = response.body().getData().get(id_pos).getBooking_amt();//"cash,bank"
                //  Toast.makeText(BookingPhaseOneActivity.this, ""+data, Toast.LENGTH_SHORT).show();
                String[] res = data.split(",");


                txtDPCashAmount_DocPrint.setVisibility(View.GONE);
                edt_DownP_CashAmount_DocPrint.setVisibility(View.GONE);

                edt_DownP_BankOption_DocPrint.setVisibility(View.GONE);
                edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                edt_DownP_SelectMake_DocPrint.setVisibility(View.GONE);
                edt_DownP_ModelVehicle_DocPrint.setVisibility(View.GONE);
                edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                edt_DownP_ManufactureYear_DocPrint.setVisibility(View.GONE);
                edt_DownP_PaperExchange_DocPrint.setVisibility(View.GONE);
                edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                edt_DownP_NOC_DocPrint.setVisibility(View.GONE);

                lin_dp_cheque_DocPrint.setVisibility(View.GONE);
                lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);
                lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                lin_dp_OldVehicle_DocPrint.setVisibility(View.GONE);
                lin_dp_Rcbook_DocPrint.setVisibility(View.GONE);
                lin_dp_Election_DocPrint.setVisibility(View.GONE);

                lin_dp_Rcbook_DocPrint2.setVisibility(View.GONE);
                lin_dp_Election_DocPrint2.setVisibility(View.GONE);

                txtDPBankOption_DocPrint.setVisibility(View.GONE);
                txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                txtDPMake_DocPrint.setVisibility(View.GONE);
                txtDPManufectureYear_DocPrint.setVisibility(View.GONE);
                txtDPOldAmount_DocPrint.setVisibility(View.GONE);
                txtDPModelName_DocPrint.setVisibility(View.GONE);
                txtDPPaperExpense_DocPrint.setVisibility(View.GONE);
                txtDPOldTractorAmount_DocPrint.setVisibility(View.GONE);
                txtDPNoc_DocPrint.setVisibility(View.GONE);


                for (int i = 0; i < res.length; i++) {
                    mydata = res[i];
                    // Toast.makeText(BookingPhaseOneActivity.this, "yes" + mydata, Toast.LENGTH_SHORT).show();

                    String uu = mydata.trim();
                    // Log.e("TAG", "onResponse: " + uu);
                    //   Log.d("TAG", "onResponse: "+mydata);

                    if (uu.equals("Cash")) {
                        txtDPCashAmount_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_CashAmount_DocPrint.setVisibility(View.VISIBLE);
                        // Log.e("TAG", "onResponse:casting ");
                        // Toast.makeText(BookingPhaseOneActivity.this, "casting", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Old Vehicle")) {
                        // Log.e("TAG", "onResponse:casting45 ");
                        txtDPMake_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_SelectMake_DocPrint.setVisibility(View.VISIBLE);
                        txtDPModelName_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_ModelVehicle_DocPrint.setVisibility(View.VISIBLE);
                        txtDPManufectureYear_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_ManufactureYear_DocPrint.setVisibility(View.VISIBLE);
                        txtDPOldAmount_DocPrint.setVisibility(View.VISIBLE);
                        txtDPPaperExpense_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_PaperExchange_DocPrint.setVisibility(View.VISIBLE);

                        if (response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")) {
                           // edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                        } else {
                            //edt_DownP_oldAmount_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                        }


                        if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                            lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                        } else {
//                            lin_dp_NocPhoto_DocPrint.setVisibility(View.VISIBLE);
                        }

                        txtDPOldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_oldAmount_DocPrint.setVisibility(View.VISIBLE);
                        //edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                        txtDPNoc_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_NOC_DocPrint.setVisibility(View.VISIBLE);

                        //  lin_dp_NocPhoto_DocPrint.setVisibility(View.VISIBLE);
//                        lin_dp_OldVehicle_DocPrint.setVisibility(View.VISIBLE);
                     /*   lin_dp_Rcbook_DocPrint.setVisibility(View.VISIBLE);
                        lin_dp_Election_DocPrint.setVisibility(View.VISIBLE);

                        lin_dp_Rcbook_DocPrint2.setVisibility(View.VISIBLE);
                        lin_dp_Election_DocPrint2.setVisibility(View.VISIBLE);*/

                        //  Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Bank")) {
                        // Log.e("TAG", "onResponse:casting450 ");
                        // Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();

                        txtDPBankOption_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_BankOption_DocPrint.setVisibility(View.VISIBLE);

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")) {
                            txtDPChequeDate_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeDate_DocPrint.setVisibility(View.VISIBLE);
                            txtDPChequeAmount_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeAmount_DocPrint.setVisibility(View.VISIBLE);
//                            lin_dp_cheque_DocPrint.setVisibility(View.VISIBLE);

                            txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS")) {
                            txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.VISIBLE);
                            txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.VISIBLE);
//                            lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.VISIBLE);

                            txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                            edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                            txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                            lin_dp_cheque_DocPrint.setVisibility(View.GONE);
                        }

                    }
                }





              /*  if(response.body().getData().get(id_pos).getBooking_amt().equals("Cash")){

                    txtDPCashAmount_DocPrint.setVisibility(View.VISIBLE);
                    edt_DownP_CashAmount_DocPrint.setVisibility(View.VISIBLE);

                    edt_DownP_BankOption_DocPrint.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                    edt_DownP_SelectMake_DocPrint.setVisibility(View.GONE);
                    edt_DownP_ModelVehicle_DocPrint.setVisibility(View.GONE);
                    edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                    edt_DownP_ManufactureYear_DocPrint.setVisibility(View.GONE);
                    edt_DownP_PaperExchange_DocPrint.setVisibility(View.GONE);
                    edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                    edt_DownP_NOC_DocPrint.setVisibility(View.GONE);

                    lin_dp_cheque_DocPrint.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);
                    lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                    lin_dp_OldVehicle_DocPrint.setVisibility(View.GONE);
                    lin_dp_Rcbook_DocPrint.setVisibility(View.GONE);
                    lin_dp_Election_DocPrint.setVisibility(View.GONE);

                    lin_dp_Rcbook_DocPrint2.setVisibility(View.GONE);
                    lin_dp_Election_DocPrint2.setVisibility(View.GONE);

                    txtDPBankOption_DocPrint.setVisibility(View.GONE);
                    txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                    txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                    txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                    txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                    txtDPMake_DocPrint.setVisibility(View.GONE);
                    txtDPManufectureYear_DocPrint.setVisibility(View.GONE);
                    txtDPOldAmount_DocPrint.setVisibility(View.GONE);
                    txtDPModelName_DocPrint.setVisibility(View.GONE);
                    txtDPPaperExpense_DocPrint.setVisibility(View.GONE);
                    txtDPOldTractorAmount_DocPrint.setVisibility(View.GONE);
                    txtDPNoc_DocPrint.setVisibility(View.GONE);
                }*/


                /*if(response.body().getData().get(id_pos).getBooking_amt().equals("Bank")){

                    txtDPCashAmount_DocPrint.setVisibility(View.GONE);
                    edt_DownP_CashAmount_DocPrint.setVisibility(View.GONE);

                    txtDPBankOption_DocPrint.setVisibility(View.VISIBLE);
                    edt_DownP_BankOption_DocPrint.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                        txtDPChequeDate_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_ChequeDate_DocPrint.setVisibility(View.VISIBLE);
                        txtDPChequeAmount_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_ChequeAmount_DocPrint.setVisibility(View.VISIBLE);
                        lin_dp_cheque_DocPrint.setVisibility(View.VISIBLE);

                        txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                        txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                        lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);

                        txtDPMake_DocPrint.setVisibility(View.GONE);
                        edt_DownP_SelectMake_DocPrint.setVisibility(View.GONE);
                        txtDPModelName_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_DocPrint.setVisibility(View.GONE);
                        txtDPManufectureYear_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_DocPrint.setVisibility(View.GONE);
                        txtDPOldAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                        txtDPPaperExpense_DocPrint.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_DocPrint.setVisibility(View.GONE);
                        txtDPOldTractorAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                        txtDPNoc_DocPrint.setVisibility(View.GONE);
                        edt_DownP_NOC_DocPrint.setVisibility(View.GONE);

                        lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                        lin_dp_OldVehicle_DocPrint.setVisibility(View.GONE);
                        lin_dp_Rcbook_DocPrint.setVisibility(View.GONE);
                        lin_dp_Election_DocPrint.setVisibility(View.GONE);
                        lin_dp_Rcbook_DocPrint2.setVisibility(View.GONE);
                        lin_dp_Election_DocPrint2.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                    {
                        txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.VISIBLE);
                        txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.VISIBLE);
                        edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.VISIBLE);
                        lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.VISIBLE);

                        txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                        txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                        lin_dp_cheque_DocPrint.setVisibility(View.GONE);

                        txtDPMake_DocPrint.setVisibility(View.GONE);
                        edt_DownP_SelectMake_DocPrint.setVisibility(View.GONE);
                        txtDPModelName_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_DocPrint.setVisibility(View.GONE);
                        txtDPManufectureYear_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_DocPrint.setVisibility(View.GONE);
                        txtDPOldAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                        txtDPPaperExpense_DocPrint.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_DocPrint.setVisibility(View.GONE);
                        txtDPOldTractorAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                        txtDPNoc_DocPrint.setVisibility(View.GONE);
                        edt_DownP_NOC_DocPrint.setVisibility(View.GONE);

                        lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                        lin_dp_OldVehicle_DocPrint.setVisibility(View.GONE);
                        lin_dp_Rcbook_DocPrint.setVisibility(View.GONE);
                        lin_dp_Election_DocPrint.setVisibility(View.GONE);
                        lin_dp_Rcbook_DocPrint2.setVisibility(View.GONE);
                        lin_dp_Election_DocPrint2.setVisibility(View.GONE);
                    }

                }*/

                //Old Vehicle
               /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Old Vehicle")){

                    txtDPMake_DocPrint.setVisibility(View.VISIBLE);
                    edt_DownP_SelectMake_DocPrint.setVisibility(View.VISIBLE);
                    txtDPModelName_DocPrint.setVisibility(View.VISIBLE);
                    edt_DownP_ModelVehicle_DocPrint.setVisibility(View.VISIBLE);
                    txtDPManufectureYear_DocPrint.setVisibility(View.VISIBLE);
                    edt_DownP_ManufactureYear_DocPrint.setVisibility(View.VISIBLE);
                    txtDPOldAmount_DocPrint.setVisibility(View.VISIBLE);
                    txtDPPaperExpense_DocPrint.setVisibility(View.VISIBLE);
                    edt_DownP_PaperExchange_DocPrint.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                        edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                    }
                    else {
                        edt_DownP_oldAmount_DocPrint.setVisibility(View.VISIBLE);
                    }

                    txtDPOldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                    edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                    txtDPNoc_DocPrint.setVisibility(View.VISIBLE);
                    edt_DownP_NOC_DocPrint.setVisibility(View.VISIBLE);

                    lin_dp_NocPhoto_DocPrint.setVisibility(View.VISIBLE);
                    lin_dp_OldVehicle_DocPrint.setVisibility(View.VISIBLE);
                    lin_dp_Rcbook_DocPrint.setVisibility(View.VISIBLE);
                    lin_dp_Election_DocPrint.setVisibility(View.VISIBLE);

                    lin_dp_Rcbook_DocPrint2.setVisibility(View.VISIBLE);
                    lin_dp_Election_DocPrint2.setVisibility(View.VISIBLE);


                    txtDPCashAmount_DocPrint.setVisibility(View.GONE);
                    edt_DownP_CashAmount_DocPrint.setVisibility(View.GONE);
                    txtDPBankOption_DocPrint.setVisibility(View.GONE);
                    edt_DownP_BankOption_DocPrint.setVisibility(View.GONE);
                    txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                    txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                    lin_dp_cheque_DocPrint.setVisibility(View.GONE);
                    txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                    txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);
                }*/


                if (response.body().getData().get(id_pos).getRto().equals("No")) {
                    edt_RTO_RtoTax_DocPrint.setVisibility(View.GONE);
                    edt_RTO_RtoPassing_DocPrint.setVisibility(View.GONE);
                    edt_RTO_Insurance_DocPrint.setVisibility(View.GONE);
                    edt_RTO_AgentFees_DocPrint.setVisibility(View.GONE);
                    edt_RTO_NumberPlat_DocPrint.setVisibility(View.GONE);
                    edt_RTO_LoanCharge_DocPrint.setVisibility(View.GONE);


                    txtRTOTax.setVisibility(View.GONE);
                    txtRTOPassing.setVisibility(View.GONE);
                    txtInsurance.setVisibility(View.GONE);
                    txtAgentFees.setVisibility(View.GONE);
                    txtNumberPlat.setVisibility(View.GONE);
                    txtLoanCharge.setVisibility(View.GONE);
                } else {
                    edt_RTO_RtoTax_DocPrint.setVisibility(View.VISIBLE);
                    edt_RTO_RtoPassing_DocPrint.setVisibility(View.VISIBLE);
                    edt_RTO_Insurance_DocPrint.setVisibility(View.VISIBLE);
                    edt_RTO_AgentFees_DocPrint.setVisibility(View.VISIBLE);
                    edt_RTO_NumberPlat_DocPrint.setVisibility(View.VISIBLE);
                    edt_RTO_LoanCharge_DocPrint.setVisibility(View.VISIBLE);

                    txtRTOTax.setVisibility(View.VISIBLE);
                    txtRTOPassing.setVisibility(View.VISIBLE);
                    txtInsurance.setVisibility(View.VISIBLE);
                    txtAgentFees.setVisibility(View.VISIBLE);
                    txtNumberPlat.setVisibility(View.VISIBLE);
                    txtLoanCharge.setVisibility(View.VISIBLE);
                }

                if (response.body().getData().get(id_pos).getSkim().equals("No")) {

                    lin_ConsumerSkim_passing.setVisibility(View.GONE);

                   /* edt_CS_Hood_DocPrint.setVisibility(View.GONE);
                    edt_CS_TopLink_DocPrint.setVisibility(View.GONE);
                    edt_CS_DrawBar_DocPrint.setVisibility(View.GONE);
                    edt_CS_ToolKit_DocPrint.setVisibility(View.GONE);
                    edt_CS_Bumper_DocPrint.setVisibility(View.GONE);
                    edt_CS_Hitch_DocPrint.setVisibility(View.GONE);
                    edt_CS_Description_DocPrint.setVisibility(View.GONE);

                    txt_cs_Hood.setVisibility(View.GONE);
                    txt_cs_TopLink.setVisibility(View.GONE);
                    txt_cs_Drawbar.setVisibility(View.GONE);
                    txt_cs_ToolKit.setVisibility(View.GONE);
                    txt_cs_Bumper.setVisibility(View.GONE);
                    txt_cs_Hitch.setVisibility(View.GONE);
                    txt_cs_Description.setVisibility(View.GONE);*/

                } else {

                    lin_ConsumerSkim_passing.setVisibility(View.VISIBLE);

                   /* edt_CS_Hood_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_TopLink_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_DrawBar_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_ToolKit_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_Bumper_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_Hitch_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_Description_DocPrint.setVisibility(View.VISIBLE);

                    txt_cs_Hood.setVisibility(View.VISIBLE);
                    txt_cs_TopLink.setVisibility(View.VISIBLE);
                    txt_cs_Drawbar.setVisibility(View.VISIBLE);
                    txt_cs_ToolKit.setVisibility(View.VISIBLE);
                    txt_cs_Bumper.setVisibility(View.VISIBLE);
                    txt_cs_Hitch.setVisibility(View.VISIBLE);
                    txt_cs_Description.setVisibility(View.VISIBLE);*/
                }


               /* if(response.body().getData().get(id_pos).getAtype().equals("Loan")){
                    lin_LoanDetail_DocPrint.setVisibility(View.VISIBLE);
                    lin_cashLoan_DocPrint.setVisibility(View.GONE);
                }
                else {
                    lin_LoanDetail_DocPrint.setVisibility(View.GONE);
                    lin_cashLoan_DocPrint.setVisibility(View.VISIBLE);
                }*/

                if (response.body().getData().get(id_pos).getAtype().equals("Loan")) {
                    lin_LoanDetail_DocPrint.setVisibility(View.VISIBLE);
                    lin_cashLoan_DocPrint.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {
                    lin_LoanDetail_DocPrint.setVisibility(View.GONE);
                    lin_cashLoan_DocPrint.setVisibility(View.VISIBLE);
                }


                if (response.body().getData().get(id_pos).getAtype().equals("Cash-Loan")) {
                    lin_LoanDetail_DocPrint.setVisibility(View.VISIBLE);
                    lin_cashLoan_DocPrint.setVisibility(View.VISIBLE);
                }



                edt_ADBooking_BookingNo_DocPrint.setText(response.body().getData().get(id_pos).getBno());
                edt_ADBooking_BookingType_DocPrint.setText(response.body().getData().get(id_pos).getB_type());
                edt_ADBooking_BookingDate_DocPrint.setText(response.body().getData().get(id_pos).getB_date());
                edt_ADBooking_BookingLoginName_DocPrint.setText(response.body().getData().get(id_pos).getEmp());

                // Toast.makeText(DeliveryFormActivity.this, ""+id_item, Toast.LENGTH_SHORT).show();

               /* edt_ADBooking_BookingNo_DocPrint.setText(response.body().getData().get(id_item).getBno());
                edt_ADBooking_BookingType_DocPrint.setText(response.body().getData().get(id_item).getB_type());
                edt_ADBooking_BookingDate_DocPrint.setText(response.body().getData().get(id_item).getB_date());
                edt_ADBooking_BookingLoginName_DocPrint.setText(response.body().getData().get(id_item).getEmp());*/


                edt_CD_Fname_DocPrint.setText(response.body().getData().get(id_pos).getFname());
                edt_CD_LastName_DocPrint.setText(response.body().getData().get(id_pos).getLname() + " ");
                edt_CD_Surname_DocPrint.setText(response.body().getData().get(id_pos).getSname());
                edt_CD_MobileNo_DocPrint.setText(response.body().getData().get(id_pos).getMobileno());
                edt_CD_WhatsappNo_DocPrint.setText(response.body().getData().get(id_pos).getWhno());
                edt_CD_ReferenceName_DocPrint.setText(response.body().getData().get(id_pos).getRef_name());
                edt_CD_ReferenceNo_DocPrint.setText(response.body().getData().get(id_pos).getRef_no());
                edt_CD_State_DocPrint.setText(response.body().getData().get(id_pos).getState());
                edt_CD_City_DocPrint.setText(response.body().getData().get(id_pos).getCity());
                edt_CD_District_DocPrint.setText(response.body().getData().get(id_pos).getDistric());
                edt_CD_Village_DocPrint.setText(response.body().getData().get(id_pos).getVillage());
                edt_CD_EmployeName_DocPrint.setText(response.body().getData().get(id_pos).getEmp());
                edt_CD_Taluko_DocPrint.setText(response.body().getData().get(id_pos).getTehsill());
                edt_CD_PassBook_DocPrint.setText(response.body().getData().get(id_pos).getB_p_photo());
                edt_CD_ChequeBook_DocPrint.setText(response.body().getData().get(id_pos).getCheck_photo());
                edt_CD_PaymentOption_DocPrint.setText(response.body().getData().get(id_pos).getAtype());


                edt_PD_PurchaseName_DocPrint.setText(response.body().getData().get(id_pos).getProduct_name());
                edt_PD_ModelName_DocPrint.setText(response.body().getData().get(id_pos).getModel_name());
                edt_PD_Description_DocPrint.setText(response.body().getData().get(id_pos).getP_desc());


                edt_PriceD_price_DocPrint.setText(response.body().getData().get(id_pos).getDeal_price());
                edt_PriceD_priceInWord_DocPrint.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                edt_PriceD_GST_DocPrint.setText(response.body().getData().get(id_pos).getGst());


                edt_RTO_RtoMain_DocPrint.setText(response.body().getData().get(id_pos).getRto());
                edt_RTO_RtoTax_DocPrint.setText(response.body().getData().get(id_pos).getRto_tax());
                edt_RTO_RtoPassing_DocPrint.setText(response.body().getData().get(id_pos).getRto_passing());
                edt_RTO_Insurance_DocPrint.setText(response.body().getData().get(id_pos).getInsurance());
                edt_RTO_AgentFees_DocPrint.setText(response.body().getData().get(id_pos).getAgent_fee());
                edt_RTO_NumberPlat_DocPrint.setText(response.body().getData().get(id_pos).getNumber_plat());
                edt_RTO_LoanCharge_DocPrint.setText(response.body().getData().get(id_pos).getLoan_charge());

                edt_DownP_BookingAmount_DocPrint.setText(response.body().getData().get(id_pos).getBooking_amt());
                edt_DownP_CashAmount_DocPrint.setText(response.body().getData().get(id_pos).getAmount());
                edt_DownP_BankOption_DocPrint.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                edt_DownP_ChequeDate_DocPrint.setText(response.body().getData().get(id_pos).getCheck_date());
                edt_DownP_ChequeAmount_DocPrint.setText(response.body().getData().get(id_pos).getCheck_amt());
                edt_DownP_NEFT_RTGS_date_DocPrint.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                edt_DownP_NEFT_RTGSAmount_DocPrint.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                edt_DownP_SelectMake_DocPrint.setText(response.body().getData().get(id_pos).getMake());
                edt_DownP_ModelVehicle_DocPrint.setText(response.body().getData().get(id_pos).getModel());
                edt_DownP_oldAmount_DocPrint.setText(response.body().getData().get(id_pos).getOld_t_amount());
                edt_DownP_ManufactureYear_DocPrint.setText(response.body().getData().get(id_pos).getM_year());
                edt_DownP_PaperExchange_DocPrint.setText(response.body().getData().get(id_pos).getPaper_expence());
                edt_DownP_oldTractorAmount_DocPrint.setText(response.body().getData().get(id_pos).getC_amount());
                edt_DownP_NOC_DocPrint.setText(response.body().getData().get(id_pos).getNoc());


                edt_CS_ConsumerSkim_DocPrint.setText(response.body().getData().get(id_pos).getSkim());
                edt_CS_Hood_DocPrint.setText(response.body().getData().get(id_pos).getHood());
                edt_CS_TopLink_DocPrint.setText(response.body().getData().get(id_pos).getToplink());
                edt_CS_DrawBar_DocPrint.setText(response.body().getData().get(id_pos).getDrowbar());
                edt_CS_ToolKit_DocPrint.setText(response.body().getData().get(id_pos).getToolkit());
                edt_CS_Bumper_DocPrint.setText(response.body().getData().get(id_pos).getBumper());
                edt_CS_Hitch_DocPrint.setText(response.body().getData().get(id_pos).getHitch());
                edt_CS_Description_DocPrint.setText(response.body().getData().get(id_pos).getDescription());


                //Loan detail
                edt_DocPrintDetail_REF_DocPrint.setText(response.body().getData().get(id_pos).getR_e_name());
                edt_DocPrintDetail_FinanceForm_DocPrint.setText(response.body().getData().get(id_pos).getFinance_from());
                edt_DocPrintDetail_LoanAmount_DocPrint.setText(response.body().getData().get(id_pos).getLoan_amount());
                edt_DocPrintDetail_LoanSancAmont_DocPrint.setText(response.body().getData().get(id_pos).getL_sec_amt());
                edt_DocPrintDetail_LoanCharge_DocPrint.setText(response.body().getData().get(id_pos).getLloan_charge());
                edt_DocPrintDetail_LandDetail_DocPrint.setText(response.body().getData().get(id_pos).getLand_details());
                edt_DocPrintDetail_CibilScore_DocPrint.setText(response.body().getData().get(id_pos).getCibil_score());
                edt_DocPrintDetail_FiDate_DocPrint.setText(response.body().getData().get(id_pos).getFi_date());
                edt_DocPrintDetail_SanctionDate_DocPrint.setText(response.body().getData().get(0).getSectiondate());
                edt_DocPrintDetail_Stage_DocPrint.setText(response.body().getData().get(id_pos).getStage());


              /*  edt_modelName_DocPrint.setText(response.body().getData().get(0).getDmodelname()+"");
                edt_ChesisNumber_DocPrint.setText(response.body().getData().get(0).getChasisno()+"");
                edt_EngineNumber_DocPrint.setText(response.body().getData().get(0).getEngineno()+"");
                edt_Variente_DocPrint.setText(response.body().getData().get(0).getVariants()+"");*/

                edt_Type_DocPrint.setText(response.body().getData().get(id_pos).getTyre() + "");
                edt_Bettry_DocPrint.setText(response.body().getData().get(id_pos).getBattery() + "");
                edt_DocPrintDate_DocPrint.setText(response.body().getData().get(id_pos).getDelivery_date() + "");

                /*Toast.makeText(DocumentPrintFormActivity.this, "Finance Form: "
                        + response.body().getData().get(id_pos).getFinance_from() + "Cash Desc: " +
                        response.body().getData().get(id_pos).getCash_description(), Toast.LENGTH_SHORT).show();*/

                //Cash details
                edt_CashDetail_Date_DocPrint.setText(response.body().getData().get(id_pos).getCash_date());
                edt_CashDetail_Amount_DocPrint.setText(response.body().getData().get(id_pos).getCash_amount());
                edt_CashDetail_Description_DocPrint.setText(response.body().getData().get(id_pos).getCash_description());

            }

            @Override
            public void onFailure(@NotNull Call<DocumentPrintDataModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });

        swipeRefreshLayoutDocPrint.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getClient().getDocPrint(emp).enqueue(new Callback<DocumentPrintDataModel>() {
                    @Override
                    public void onResponse(Call<DocumentPrintDataModel> call, Response<DocumentPrintDataModel> response) {
                        progressDialog.dismiss();

                        swipeRefreshLayoutDocPrint.setRefreshing(false);


                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getB_photo())
                                .into(img_CD_Booking_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getAdhar_photo())
                                .into(img_CD_AdharCard_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getEcard_photo())
                                .into(img_CD_ElectionCard_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getP_photo())
                                .into(img_CD_PassportSize_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getCheck1_photo())
                                .into(img_DownP_Cheque_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getNeft_rtgs_photo())
                                .into(img_DownP_NEFT_RTGS_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getNoc_photo())
                                .into(img_DownP_NocPhoto_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getOld_vehicle_photo())
                                .into(img_DownP_OldVehicle_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getRecbook_photo())
                                .into(img_DownP_RcBook_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getEc_photo())
                                .into(img_DownP_ElectionPhoto_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getLb_pb_photo())
                                .into(img_LoanDetail_BankpassBook_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getL_c_photo())
                                .into(img_LoanDetail_Cheque_DocPrint);


                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getSar_id_photo())
                                .into(img_LoanDetail_SarpanchID_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getSign_veri())
                                .into(img_LoanDetail_SignatureVerifi_DocPrint);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getDelivry_photo())
                                .into(img_DocPrintPhoto_DocPrint);


                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getChasis_print())
                                .into(img_ChesisPrint_DocPrint);


                        //-----------------------------------------------------------------------------
                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getAdhar_back())
                                .into(img_CD_AdharCard_DocPrint2);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getElection_back())
                                .into(img_CD_ElectionCard_DocPrint2);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getP_photo_back())
                                .into(img_CD_PassportSize_DocPrint2);


                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getRcbook_back())
                                .into(img_DownP_RcBook_DocPrint2);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getElec_back())
                                .into(img_DownP_ElectionPhoto_DocPrint2);

                        Glide.with(DocumentPrintFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getB_pass_back())
                                .into(img_LoanDetail_BankpassBook_DocPrint2);

                        //-----------------------------------------------------------

                        //delivry_photo


                        emp_id = response.body().getData().get(id_pos).getEmp_id();


                        if(response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                                response.body().getData().get(id_pos).getProduct_name().equals("Spar part"))
                        {
                            edt_ADBooking_BookingType_DocPrint.setVisibility(View.GONE);
                            edt_CD_PassBook_DocPrint.setVisibility(View.GONE);
                            edt_CD_ChequeBook_DocPrint.setVisibility(View.GONE);
                            txtFillBookingType_DocPrint.setVisibility(View.GONE);
                            txtPassBookBook_DocPrint.setVisibility(View.GONE);
                            txtChequeBook_DocPrint.setVisibility(View.GONE);


                            txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.GONE);
                            txt_CD_AdharCard_DocPrint.setVisibility(View.GONE);
                            txt_CD_ElectionCard_DocPrint.setVisibility(View.GONE);
                            txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                            txt_CD_AdharCard_DocPrint2.setVisibility(View.GONE);
                            txt_CD_ElectionCard_DocPrint2.setVisibility(View.GONE);
                            txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);

                            img_CD_Booking_DocPrint.setVisibility(View.GONE);
                            img_CD_AdharCard_DocPrint.setVisibility(View.GONE);
                            img_CD_ElectionCard_DocPrint.setVisibility(View.GONE);
                            img_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                            img_CD_AdharCard_DocPrint2.setVisibility(View.GONE);
                            img_CD_ElectionCard_DocPrint2.setVisibility(View.GONE);
                            img_CD_PassportSize_DocPrint2.setVisibility(View.GONE);

                            txt_Tyre_DocPrint.setVisibility(View.VISIBLE);
                            edt_Type_DocPrint.setVisibility(View.VISIBLE);
                            edt_Bettry_DocPrint.setVisibility(View.VISIBLE);
                            txt_Battery_DocPrint.setVisibility(View.VISIBLE);

                        }
                        else{
                            edt_ADBooking_BookingType_DocPrint.setVisibility(View.VISIBLE);
                            edt_CD_PassBook_DocPrint.setVisibility(View.VISIBLE);
                            edt_CD_ChequeBook_DocPrint.setVisibility(View.VISIBLE);
                            txtFillBookingType_DocPrint.setVisibility(View.VISIBLE);
                            txtPassBookBook_DocPrint.setVisibility(View.VISIBLE);
                            txtChequeBook_DocPrint.setVisibility(View.VISIBLE);


                            txt_Tyre_DocPrint.setVisibility(View.VISIBLE);
                            edt_Type_DocPrint.setVisibility(View.VISIBLE);
                            edt_Bettry_DocPrint.setVisibility(View.VISIBLE);
                            txt_Battery_DocPrint.setVisibility(View.VISIBLE);

                            //-----------------------------------------------------------
                            if (response.body().getData().get(id_pos).getTyre_check().equals("0")) {
                                edt_Type_DocPrint.setFocusable(true);
                            } else {
                                edt_Type_DocPrint.setFocusable(false);
                                edt_Type_DocPrint.setTextColor(Color.parseColor("#43a047"));
                            }

                            if (response.body().getData().get(id_pos).getBattery_check().equals("0")) {
                                edt_Bettry_DocPrint.setFocusable(true);
                            } else {
                                edt_Bettry_DocPrint.setFocusable(false);
                                edt_Bettry_DocPrint.setTextColor(Color.parseColor("#43a047"));
                            }
                            //-----------------------------------------------------------


                            if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                                txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.GONE);
                            }

                            if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                                txt_CD_AdharCard_DocPrint.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_AdharCard_DocPrint.setVisibility(View.GONE);
                            }


                            if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                                txt_CD_ElectionCard_DocPrint.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_ElectionCard_DocPrint.setVisibility(View.GONE);
                            }

                            if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                                txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                            }


                            if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                                txt_CD_AdharCard_DocPrint2.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_AdharCard_DocPrint2.setVisibility(View.GONE);
                            }


                            if(response.body().getData().get(id_pos).getElection_back_check().equals("0")){
                                txt_CD_ElectionCard_DocPrint2.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_ElectionCard_DocPrint2.setVisibility(View.GONE);
                            }

                            if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                                txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                            }

                        }


                        if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                            img_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                            txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);

                            img_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                            txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);

                            if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                                txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                            }

                            if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                                txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                            }

                        } else {
                            txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                            img_CD_PassportSize_DocPrint2.setVisibility(View.GONE);

                            txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                            img_CD_PassportSize_DocPrint.setVisibility(View.GONE);

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

                        //------------------------------------------------------

                        if (response.body().getData().get(id_pos).getBno_check().equals("0")) {
                            edt_ADBooking_BookingNo_DocPrint.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingNo_DocPrint.setFocusable(false);
                            edt_ADBooking_BookingNo_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getB_date_check().equals("0")) {
                            edt_ADBooking_BookingDate_DocPrint.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingDate_DocPrint.setFocusable(false);
                            edt_ADBooking_BookingDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }




                       /* if (response.body().getData().get(id_pos).getB_date_check().equals("0")) {
                            edt_ADBooking_BookingDate_DocPrint.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingDate_DocPrint.setFocusable(false);
                            edt_ADBooking_BookingDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }*/

                        if (response.body().getData().get(id_pos).getB_type_check().equals("0")) {
                            edt_ADBooking_BookingType_DocPrint.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingType_DocPrint.setFocusable(false);
                            edt_ADBooking_BookingType_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                            edt_ADBooking_BookingLoginName_DocPrint.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingLoginName_DocPrint.setFocusable(false);
                            edt_ADBooking_BookingLoginName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getFname_check().equals("0")) {
                            edt_CD_Fname_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_Fname_DocPrint.setFocusable(false);
                            edt_CD_Fname_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getLname_check().equals("0")) {
                            edt_CD_LastName_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_LastName_DocPrint.setFocusable(false);
                            edt_CD_LastName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getSname_check().equals("0")) {
                            edt_CD_Surname_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_Surname_DocPrint.setFocusable(false);
                            edt_CD_Surname_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getMobileno_check().equals("0")) {
                            edt_CD_MobileNo_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_MobileNo_DocPrint.setFocusable(false);
                            edt_CD_MobileNo_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getWhno_check().equals("0")) {
                            edt_CD_WhatsappNo_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_WhatsappNo_DocPrint.setFocusable(false);
                            edt_CD_WhatsappNo_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getRef_name_check().equals("0")) {
                            edt_CD_ReferenceName_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_ReferenceName_DocPrint.setFocusable(false);
                            edt_CD_ReferenceName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getRef_no_check().equals("0")) {
                            edt_CD_ReferenceNo_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_ReferenceNo_DocPrint.setFocusable(false);
                            edt_CD_ReferenceNo_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getState_check().equals("0")) {
                            edt_CD_State_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_State_DocPrint.setFocusable(false);
                            edt_CD_State_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCity_check().equals("0")) {
                            edt_CD_City_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_City_DocPrint.setFocusable(false);
                            edt_CD_City_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDistric_check().equals("0")) {
                            edt_CD_District_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_District_DocPrint.setFocusable(false);
                            edt_CD_District_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getVillage_check().equals("0")) {
                            edt_CD_Village_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_Village_DocPrint.setFocusable(false);
                            edt_CD_Village_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                            edt_CD_EmployeName_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_EmployeName_DocPrint.setFocusable(false);
                            edt_CD_EmployeName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getTehsill_check().equals("0")) {
                            edt_CD_Taluko_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_Taluko_DocPrint.setFocusable(false);
                            edt_CD_Taluko_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getProduct_name_check().equals("0")) {
                            edt_PD_PurchaseName_DocPrint.setFocusable(true);
                        } else {
                            edt_PD_PurchaseName_DocPrint.setFocusable(false);
                            edt_PD_PurchaseName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getModel_name_check().equals("0")) {
                            edt_PD_ModelName_DocPrint.setFocusable(true);
                        } else {
                            edt_PD_ModelName_DocPrint.setFocusable(false);
                            edt_PD_ModelName_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getP_desc_check().equals("0")) {
                            edt_PD_Description_DocPrint.setFocusable(true);
                        } else {
                            edt_PD_Description_DocPrint.setFocusable(false);
                            edt_PD_Description_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDeal_price_check().equals("0")) {
                            edt_PriceD_price_DocPrint.setFocusable(true);
                        } else {
                            edt_PriceD_price_DocPrint.setFocusable(false);
                            edt_PriceD_price_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")) {
                            edt_PriceD_priceInWord_DocPrint.setFocusable(true);
                        } else {
                            edt_PriceD_priceInWord_DocPrint.setFocusable(false);
                            edt_PriceD_priceInWord_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getGst_check().equals("0")) {
                            edt_PriceD_GST_DocPrint.setFocusable(true);
                        } else {
                            edt_PriceD_GST_DocPrint.setFocusable(false);
                            edt_PriceD_GST_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getRto_check().equals("0")) {
                            edt_RTO_RtoMain_DocPrint.setFocusable(true);
                        } else {
                            edt_RTO_RtoMain_DocPrint.setFocusable(false);
                            edt_RTO_RtoMain_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getRto_tax_check().equals("0")) {
                            edt_RTO_RtoTax_DocPrint.setFocusable(true);
                        } else {
                            edt_RTO_RtoTax_DocPrint.setFocusable(false);
                            edt_RTO_RtoTax_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getRto_passing_check().equals("0")) {
                            edt_RTO_RtoPassing_DocPrint.setFocusable(true);
                        } else {
                            edt_RTO_RtoPassing_DocPrint.setFocusable(false);
                            edt_RTO_RtoPassing_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getInsurance_check().equals("0")) {
                            edt_RTO_Insurance_DocPrint.setFocusable(true);
                        } else {
                            edt_RTO_Insurance_DocPrint.setFocusable(false);
                            edt_RTO_Insurance_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getAgent_fee_check().equals("0")) {
                            edt_RTO_AgentFees_DocPrint.setFocusable(true);
                        } else {
                            edt_RTO_AgentFees_DocPrint.setFocusable(false);
                            edt_RTO_AgentFees_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getNumber_plat_check().equals("0")) {
                            edt_RTO_NumberPlat_DocPrint.setFocusable(true);
                        } else {
                            edt_RTO_NumberPlat_DocPrint.setFocusable(false);
                            edt_RTO_NumberPlat_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getLoan_charge_check().equals("0")) {
                            edt_RTO_LoanCharge_DocPrint.setFocusable(true);
                        } else {
                            edt_RTO_LoanCharge_DocPrint.setFocusable(false);
                            edt_RTO_LoanCharge_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getBooking_amt_check().equals("0")) {
                            edt_DownP_BookingAmount_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_BookingAmount_DocPrint.setFocusable(false);
                            edt_DownP_BookingAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getAmount_check().equals("0")) {
                            edt_DownP_CashAmount_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_CashAmount_DocPrint.setFocusable(false);
                            edt_DownP_CashAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs_check().equals("0")) {
                            edt_DownP_BankOption_DocPrint.setFocusable(false);
                        } else {
                            edt_DownP_BankOption_DocPrint.setFocusable(false);
                            edt_DownP_BankOption_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCheck_date_check().equals("0")) {
                            edt_DownP_ChequeDate_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_ChequeDate_DocPrint.setFocusable(false);
                            edt_DownP_ChequeDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getC_amount_check().equals("0")) {
                            edt_DownP_ChequeAmount_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_ChequeAmount_DocPrint.setFocusable(false);
                            edt_DownP_ChequeAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")) {
                            edt_DownP_NEFT_RTGS_date_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_NEFT_RTGS_date_DocPrint.setFocusable(false);
                            edt_DownP_NEFT_RTGS_date_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")) {
                            edt_DownP_NEFT_RTGSAmount_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_NEFT_RTGSAmount_DocPrint.setFocusable(false);
                            edt_DownP_NEFT_RTGSAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getMake_check().equals("0")) {
                            edt_DownP_SelectMake_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_SelectMake_DocPrint.setFocusable(false);
                            edt_DownP_SelectMake_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getModel_check().equals("0")) {
                            edt_DownP_ModelVehicle_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_ModelVehicle_DocPrint.setFocusable(false);
                            edt_DownP_ModelVehicle_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                            edt_DownP_oldAmount_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_oldAmount_DocPrint.setFocusable(false);
                            edt_DownP_oldAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getM_year_check().equals("0")) {
                            edt_DownP_ManufactureYear_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_ManufactureYear_DocPrint.setFocusable(false);
                            edt_DownP_ManufactureYear_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getPaper_expence_check().equals("0")) {
                            edt_DownP_PaperExchange_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_PaperExchange_DocPrint.setFocusable(false);
                            edt_DownP_PaperExchange_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                            edt_DownP_oldTractorAmount_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_oldTractorAmount_DocPrint.setFocusable(false);
                            edt_DownP_oldTractorAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getNoc_check().equals("0")) {
                            edt_DownP_NOC_DocPrint.setFocusable(true);
                        } else {
                            edt_DownP_NOC_DocPrint.setFocusable(false);
                            edt_DownP_NOC_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getHood_check().equals("0")) {
                            edt_CS_Hood_DocPrint.setFocusable(true);
                        } else {
                            edt_CS_Hood_DocPrint.setFocusable(false);
                            edt_CS_Hood_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getToplink_check().equals("0")) {
                            edt_CS_TopLink_DocPrint.setFocusable(true);
                        } else {
                            edt_CS_TopLink_DocPrint.setFocusable(false);
                            edt_CS_TopLink_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDrowbar_check().equals("0")) {
                            edt_CS_DrawBar_DocPrint.setFocusable(true);
                        } else {
                            edt_CS_DrawBar_DocPrint.setFocusable(false);
                            edt_CS_DrawBar_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getToolkit_check().equals("0")) {
                            edt_CS_ToolKit_DocPrint.setFocusable(true);
                        } else {
                            edt_CS_ToolKit_DocPrint.setFocusable(false);
                            edt_CS_ToolKit_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getBumper_check().equals("0")) {
                            edt_CS_Bumper_DocPrint.setFocusable(true);
                        } else {
                            edt_CS_Bumper_DocPrint.setFocusable(false);
                            edt_CS_Bumper_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getHitch_check().equals("0")) {
                            edt_CS_Hitch_DocPrint.setFocusable(true);
                        } else {
                            edt_CS_Hitch_DocPrint.setFocusable(false);
                            edt_CS_Hitch_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDescription_check().equals("0")) {
                            edt_CS_Description_DocPrint.setFocusable(true);
                        } else {
                            edt_CS_Description_DocPrint.setFocusable(false);
                            edt_CS_Description_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getB_p_photo_check().equals("0")) {
                            edt_CD_PassBook_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_PassBook_DocPrint.setFocusable(false);
                            edt_CD_PassBook_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCheck_photo_check().equals("0")) {
                            edt_CD_ChequeBook_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_ChequeBook_DocPrint.setFocusable(false);
                            edt_CD_ChequeBook_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getR_e_name_check().equals("0")) {
                            edt_DocPrintDetail_REF_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_REF_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_REF_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getFinance_from_check().equals("0")) {
                            edt_DocPrintDetail_FinanceForm_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_FinanceForm_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_FinanceForm_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getLoan_amount_check().equals("0")) {
                            edt_DocPrintDetail_LoanAmount_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_LoanAmount_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_LoanAmount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")) {
                            edt_DocPrintDetail_LoanSancAmont_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_LoanSancAmont_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_LoanSancAmont_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getLloan_charge_check().equals("0")) {
                            edt_DocPrintDetail_LoanCharge_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_LoanCharge_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_LoanCharge_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getLand_details_check().equals("0")) {
                            edt_DocPrintDetail_LandDetail_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_LandDetail_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_LandDetail_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCibil_score_check().equals("0")) {
                            edt_DocPrintDetail_CibilScore_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_CibilScore_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_CibilScore_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getFi_date_check().equals("0")) {
                            edt_DocPrintDetail_FiDate_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_FiDate_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_FiDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getSectiondate_check().equals("0")) {
                            edt_DocPrintDetail_SanctionDate_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDetail_SanctionDate_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_SanctionDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getStage_check().equals("0")) {
                            edt_DocPrintDetail_Stage_DocPrint.setFocusable(true);
                            spn_DocPrintDetail_stageloan.setVisibility(View.VISIBLE);
                            edt_DocPrintDetail_Stage_DocPrint.setVisibility(View.GONE);
                        } else {
                            spn_DocPrintDetail_stageloan.setVisibility(View.GONE);
                            edt_DocPrintDetail_Stage_DocPrint.setVisibility(View.VISIBLE);
                            edt_DocPrintDetail_Stage_DocPrint.setFocusable(false);
                            edt_DocPrintDetail_Stage_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getSkim_check().equals("0")) {
                            edt_CS_ConsumerSkim_DocPrint.setFocusable(true);
                        } else {
                            edt_CS_ConsumerSkim_DocPrint.setFocusable(false);
                            edt_CS_ConsumerSkim_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getAtype_check().equals("0")) {
                            edt_CD_PaymentOption_DocPrint.setFocusable(true);
                        } else {
                            edt_CD_PaymentOption_DocPrint.setFocusable(false);
                            edt_CD_PaymentOption_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getCash_date_check().equals("0")) {
                            edt_CashDetail_Date_DocPrint.setFocusable(true);
                        } else {
                            edt_CashDetail_Date_DocPrint.setFocusable(false);
                            edt_CashDetail_Date_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getCash_amount_check().equals("0")) {
                            edt_CashDetail_Amount_DocPrint.setFocusable(true);
                        } else {
                            edt_CashDetail_Amount_DocPrint.setFocusable(false);
                            edt_CashDetail_Amount_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCash_description_check().equals("0")) {
                            edt_CashDetail_Description_DocPrint.setFocusable(true);
                        } else {
                            edt_CashDetail_Description_DocPrint.setFocusable(false);
                            edt_CashDetail_Description_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                       /* if (response.body().getData().get(id_pos).getTyre_check().equals("0")) {
                            edt_Type_DocPrint.setFocusable(true);
                        } else {
                            edt_Type_DocPrint.setFocusable(false);
                            edt_Type_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getBattery_check().equals("0")) {
                            edt_Bettry_DocPrint.setFocusable(true);
                        } else {
                            edt_Bettry_DocPrint.setFocusable(false);
                            edt_Bettry_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }*/


                        if (response.body().getData().get(id_pos).getDelivery_date_check().equals("0")) {
                            edt_DocPrintDate_DocPrint.setFocusable(true);
                        } else {
                            edt_DocPrintDate_DocPrint.setFocusable(false);
                            edt_DocPrintDate_DocPrint.setTextColor(Color.parseColor("#43a047"));
                        }


                        /** ************************************************************************************* */

                       /* if (response.body().getData().get(id_pos).getB_photo_check().equals("0")) {
                            txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_CD_UploadBookingPhoto_DocPrint.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")) {
                            txt_CD_AdharCard_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_CD_AdharCard_DocPrint.setVisibility(View.GONE);
                        }


                        if (response.body().getData().get(id_pos).getEcard_photo_check().equals("0")) {
                            txt_CD_ElectionCard_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_CD_ElectionCard_DocPrint.setVisibility(View.GONE);
                        }*/

                        /*if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                            txt_CD_PassportSize_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_CD_PassportSize_DocPrint.setVisibility(View.GONE);
                        }*/

                        if (response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")) {
                            txt_DownP_UploadCheque_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadCheque_DocPrint.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")) {
                            txt_DownP_UploadNEFT_RTGS_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadNEFT_RTGS_DocPrint.setVisibility(View.GONE);
                        }


                        if (response.body().getData().get(id_pos).getNoc_photo_check().equals("0")) {
                            txt_DownP_UploadNocPhoto_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadNocPhoto_DocPrint.setVisibility(View.GONE);
                        }


                        if (response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")) {
                            txt_DownP_UploadOldVehicle_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadOldVehicle_DocPrint.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")) {
                            txt_DownP_UploadRCBook_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadRCBook_DocPrint.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getEc_photo_check().equals("0")) {
                            txt_DownP_UploadElectionPhoto_DocPrint.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadElectionPhoto_DocPrint.setVisibility(View.GONE);
                        }


                        //----------------------------------------------------------------------------

                        /*if (response.body().getData().get(id_pos).getAdhar_back_check().equals("0")) {
                            txt_CD_AdharCard_DocPrint2.setVisibility(View.VISIBLE);

                        } else {
                            txt_CD_AdharCard_DocPrint2.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getElection_back_check().equals("0")) {
                            txt_CD_ElectionCard_DocPrint2.setVisibility(View.VISIBLE);
                        } else {
                            txt_CD_ElectionCard_DocPrint2.setVisibility(View.GONE);
                        }*/

                       /* if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                            txt_CD_PassportSize_DocPrint2.setVisibility(View.VISIBLE);
                        } else {
                            txt_CD_PassportSize_DocPrint2.setVisibility(View.GONE);
                        }*/


                        if (response.body().getData().get(id_pos).getRcbook_back_check().equals("0")) {
                            txt_DownP_UploadRCBook_DocPrint2.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadRCBook_DocPrint2.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getElec_back_check().equals("0")) {
                            txt_DownP_UploadElectionPhoto_DocPrint2.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadElectionPhoto_DocPrint2.setVisibility(View.GONE);
                        }

                        //
                        if (response.body().getData().get(id_pos).getB_pass_back_check().equals("0")) {
                            txt_LoanDetail_BankPassBook_DocPrint2.setVisibility(View.VISIBLE);
                        } else {
                            txt_LoanDetail_BankPassBook_DocPrint2.setVisibility(View.GONE);
                        }

                        /** ********************************************************************************** */

                        if (response.body().getData().get(id_pos).getStage().equals("Pending")) {
                            String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                            spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                            spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                            spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                            spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                            spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                            spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            ArrayAdapter adapterStage = new ArrayAdapter(DocumentPrintFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_DocPrintDetail_stageloan.setAdapter(adapterStage);

                            spn_DocPrintDetail_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                        String data = response.body().getData().get(id_pos).getBooking_amt();//"cash,bank"
                        //  Toast.makeText(BookingPhaseOneActivity.this, ""+data, Toast.LENGTH_SHORT).show();
                        String[] res = data.split(",");


                        txtDPCashAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_CashAmount_DocPrint.setVisibility(View.GONE);

                        edt_DownP_BankOption_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_SelectMake_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_DocPrint.setVisibility(View.GONE);
                        edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_DocPrint.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_DocPrint.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                        edt_DownP_NOC_DocPrint.setVisibility(View.GONE);

                        lin_dp_cheque_DocPrint.setVisibility(View.GONE);
                        lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);
                        lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                        lin_dp_OldVehicle_DocPrint.setVisibility(View.GONE);
                        lin_dp_Rcbook_DocPrint.setVisibility(View.GONE);
                        lin_dp_Election_DocPrint.setVisibility(View.GONE);

                        lin_dp_Rcbook_DocPrint2.setVisibility(View.GONE);
                        lin_dp_Election_DocPrint2.setVisibility(View.GONE);

                        txtDPBankOption_DocPrint.setVisibility(View.GONE);
                        txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                        txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                        txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                        txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                        txtDPMake_DocPrint.setVisibility(View.GONE);
                        txtDPManufectureYear_DocPrint.setVisibility(View.GONE);
                        txtDPOldAmount_DocPrint.setVisibility(View.GONE);
                        txtDPModelName_DocPrint.setVisibility(View.GONE);
                        txtDPPaperExpense_DocPrint.setVisibility(View.GONE);
                        txtDPOldTractorAmount_DocPrint.setVisibility(View.GONE);
                        txtDPNoc_DocPrint.setVisibility(View.GONE);


                        for (int i = 0; i < res.length; i++) {
                            mydata = res[i];
                            // Toast.makeText(BookingPhaseOneActivity.this, "yes" + mydata, Toast.LENGTH_SHORT).show();

                            String uu = mydata.trim();
                            // Log.e("TAG", "onResponse: " + uu);
                            //   Log.d("TAG", "onResponse: "+mydata);

                            if (uu.equals("Cash")) {
                                txtDPCashAmount_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_CashAmount_DocPrint.setVisibility(View.VISIBLE);
                                // Log.e("TAG", "onResponse:casting ");
                                // Toast.makeText(BookingPhaseOneActivity.this, "casting", Toast.LENGTH_SHORT).show();
                            }

                            if (uu.equals("Old Vehicle")) {
                                // Log.e("TAG", "onResponse:casting45 ");
                                txtDPMake_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_SelectMake_DocPrint.setVisibility(View.VISIBLE);
                                txtDPModelName_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_ModelVehicle_DocPrint.setVisibility(View.VISIBLE);
                                txtDPManufectureYear_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_ManufactureYear_DocPrint.setVisibility(View.VISIBLE);
                                txtDPOldAmount_DocPrint.setVisibility(View.VISIBLE);
                                txtDPPaperExpense_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_PaperExchange_DocPrint.setVisibility(View.VISIBLE);

                                if (response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")) {
                                   // edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                                    edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                                } else {
                                   // edt_DownP_oldAmount_DocPrint.setVisibility(View.VISIBLE);
                                    edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                                }


                                if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                                    lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                                } else {
//                                    lin_dp_NocPhoto_DocPrint.setVisibility(View.VISIBLE);
                                }

                                txtDPOldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_oldAmount_DocPrint.setVisibility(View.VISIBLE);
                                //edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                                txtDPNoc_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_NOC_DocPrint.setVisibility(View.VISIBLE);

                                //  lin_dp_NocPhoto_DocPrint.setVisibility(View.VISIBLE);
                               /* lin_dp_OldVehicle_DocPrint.setVisibility(View.VISIBLE);
                                lin_dp_Rcbook_DocPrint.setVisibility(View.VISIBLE);
                                lin_dp_Election_DocPrint.setVisibility(View.VISIBLE);

                                lin_dp_Rcbook_DocPrint2.setVisibility(View.VISIBLE);
                                lin_dp_Election_DocPrint2.setVisibility(View.VISIBLE);*/

                                //  Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();
                            }

                            if (uu.equals("Bank")) {
                                // Log.e("TAG", "onResponse:casting450 ");
                                // Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();

                                txtDPBankOption_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_BankOption_DocPrint.setVisibility(View.VISIBLE);

                                if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")) {
                                    txtDPChequeDate_DocPrint.setVisibility(View.VISIBLE);
                                    edt_DownP_ChequeDate_DocPrint.setVisibility(View.VISIBLE);
                                    txtDPChequeAmount_DocPrint.setVisibility(View.VISIBLE);
                                    edt_DownP_ChequeAmount_DocPrint.setVisibility(View.VISIBLE);
//                                    lin_dp_cheque_DocPrint.setVisibility(View.VISIBLE);

                                    txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                                    edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                                    txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                                    edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                                    lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);
                                }

                                if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS")) {
                                    txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.VISIBLE);
                                    edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.VISIBLE);
                                    txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.VISIBLE);
                                    edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.VISIBLE);
//                                    lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.VISIBLE);

                                    txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                                    edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                                    txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                                    edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                                    lin_dp_cheque_DocPrint.setVisibility(View.GONE);
                                }

                            }
                        }


                        /*if(response.body().getData().get(id_pos).getBooking_amt().equals("Cash")){

                            txtDPCashAmount_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_CashAmount_DocPrint.setVisibility(View.VISIBLE);

                            edt_DownP_BankOption_DocPrint.setVisibility(View.GONE);
                            edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_SelectMake_DocPrint.setVisibility(View.GONE);
                            edt_DownP_ModelVehicle_DocPrint.setVisibility(View.GONE);
                            edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_ManufactureYear_DocPrint.setVisibility(View.GONE);
                            edt_DownP_PaperExchange_DocPrint.setVisibility(View.GONE);
                            edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_NOC_DocPrint.setVisibility(View.GONE);

                            lin_dp_cheque_DocPrint.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);
                            lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                            lin_dp_OldVehicle_DocPrint.setVisibility(View.GONE);
                            lin_dp_Rcbook_DocPrint.setVisibility(View.GONE);
                            lin_dp_Election_DocPrint.setVisibility(View.GONE);

                            lin_dp_Rcbook_DocPrint2.setVisibility(View.GONE);
                            lin_dp_Election_DocPrint2.setVisibility(View.GONE);

                            txtDPBankOption_DocPrint.setVisibility(View.GONE);
                            txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                            txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                            txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                            txtDPMake_DocPrint.setVisibility(View.GONE);
                            txtDPManufectureYear_DocPrint.setVisibility(View.GONE);
                            txtDPOldAmount_DocPrint.setVisibility(View.GONE);
                            txtDPModelName_DocPrint.setVisibility(View.GONE);
                            txtDPPaperExpense_DocPrint.setVisibility(View.GONE);
                            txtDPOldTractorAmount_DocPrint.setVisibility(View.GONE);
                            txtDPNoc_DocPrint.setVisibility(View.GONE);
                        }*/


                       /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Bank")){

                            txtDPCashAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_CashAmount_DocPrint.setVisibility(View.GONE);

                            txtDPBankOption_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_BankOption_DocPrint.setVisibility(View.VISIBLE);

                            if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                                txtDPChequeDate_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_ChequeDate_DocPrint.setVisibility(View.VISIBLE);
                                txtDPChequeAmount_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_ChequeAmount_DocPrint.setVisibility(View.VISIBLE);
                                lin_dp_cheque_DocPrint.setVisibility(View.VISIBLE);

                                txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                                edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                                txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                                edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                                lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);

                                txtDPMake_DocPrint.setVisibility(View.GONE);
                                edt_DownP_SelectMake_DocPrint.setVisibility(View.GONE);
                                txtDPModelName_DocPrint.setVisibility(View.GONE);
                                edt_DownP_ModelVehicle_DocPrint.setVisibility(View.GONE);
                                txtDPManufectureYear_DocPrint.setVisibility(View.GONE);
                                edt_DownP_ManufactureYear_DocPrint.setVisibility(View.GONE);
                                txtDPOldAmount_DocPrint.setVisibility(View.GONE);
                                edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                                txtDPPaperExpense_DocPrint.setVisibility(View.GONE);
                                edt_DownP_PaperExchange_DocPrint.setVisibility(View.GONE);
                                txtDPOldTractorAmount_DocPrint.setVisibility(View.GONE);
                                edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                                txtDPNoc_DocPrint.setVisibility(View.GONE);
                                edt_DownP_NOC_DocPrint.setVisibility(View.GONE);

                                lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                                lin_dp_OldVehicle_DocPrint.setVisibility(View.GONE);
                                lin_dp_Rcbook_DocPrint.setVisibility(View.GONE);
                                lin_dp_Election_DocPrint.setVisibility(View.GONE);
                                lin_dp_Rcbook_DocPrint2.setVisibility(View.GONE);
                                lin_dp_Election_DocPrint2.setVisibility(View.GONE);
                            }

                            if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                            {
                                txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.VISIBLE);
                                txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.VISIBLE);
                                edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.VISIBLE);
                                lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.VISIBLE);

                                txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                                edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                                txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                                edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                                lin_dp_cheque_DocPrint.setVisibility(View.GONE);

                                txtDPMake_DocPrint.setVisibility(View.GONE);
                                edt_DownP_SelectMake_DocPrint.setVisibility(View.GONE);
                                txtDPModelName_DocPrint.setVisibility(View.GONE);
                                edt_DownP_ModelVehicle_DocPrint.setVisibility(View.GONE);
                                txtDPManufectureYear_DocPrint.setVisibility(View.GONE);
                                edt_DownP_ManufactureYear_DocPrint.setVisibility(View.GONE);
                                txtDPOldAmount_DocPrint.setVisibility(View.GONE);
                                edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                                txtDPPaperExpense_DocPrint.setVisibility(View.GONE);
                                edt_DownP_PaperExchange_DocPrint.setVisibility(View.GONE);
                                txtDPOldTractorAmount_DocPrint.setVisibility(View.GONE);
                                edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.GONE);
                                txtDPNoc_DocPrint.setVisibility(View.GONE);
                                edt_DownP_NOC_DocPrint.setVisibility(View.GONE);

                                lin_dp_NocPhoto_DocPrint.setVisibility(View.GONE);
                                lin_dp_OldVehicle_DocPrint.setVisibility(View.GONE);
                                lin_dp_Rcbook_DocPrint.setVisibility(View.GONE);
                                lin_dp_Election_DocPrint.setVisibility(View.GONE);
                                lin_dp_Rcbook_DocPrint2.setVisibility(View.GONE);
                                lin_dp_Election_DocPrint2.setVisibility(View.GONE);
                            }

                        }*/

                        //Old Vehicle
                        /*if(response.body().getData().get(id_pos).getBooking_amt().equals("Old Vehicle")){

                            txtDPMake_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_SelectMake_DocPrint.setVisibility(View.VISIBLE);
                            txtDPModelName_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_ModelVehicle_DocPrint.setVisibility(View.VISIBLE);
                            txtDPManufectureYear_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_ManufactureYear_DocPrint.setVisibility(View.VISIBLE);
                            txtDPOldAmount_DocPrint.setVisibility(View.VISIBLE);
                            txtDPPaperExpense_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_PaperExchange_DocPrint.setVisibility(View.VISIBLE);

                            if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                                edt_DownP_oldAmount_DocPrint.setVisibility(View.GONE);
                            }
                            else {
                                edt_DownP_oldAmount_DocPrint.setVisibility(View.VISIBLE);
                            }

                            txtDPOldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_oldTractorAmount_DocPrint.setVisibility(View.VISIBLE);
                            txtDPNoc_DocPrint.setVisibility(View.VISIBLE);
                            edt_DownP_NOC_DocPrint.setVisibility(View.VISIBLE);

                            lin_dp_NocPhoto_DocPrint.setVisibility(View.VISIBLE);
                            lin_dp_OldVehicle_DocPrint.setVisibility(View.VISIBLE);
                            lin_dp_Rcbook_DocPrint.setVisibility(View.VISIBLE);
                            lin_dp_Election_DocPrint.setVisibility(View.VISIBLE);

                            lin_dp_Rcbook_DocPrint2.setVisibility(View.VISIBLE);
                            lin_dp_Election_DocPrint2.setVisibility(View.VISIBLE);


                            txtDPCashAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_CashAmount_DocPrint.setVisibility(View.GONE);
                            txtDPBankOption_DocPrint.setVisibility(View.GONE);
                            edt_DownP_BankOption_DocPrint.setVisibility(View.GONE);
                            txtDPChequeDate_DocPrint.setVisibility(View.GONE);
                            edt_DownP_ChequeDate_DocPrint.setVisibility(View.GONE);
                            txtDPChequeAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_DocPrint.setVisibility(View.GONE);
                            lin_dp_cheque_DocPrint.setVisibility(View.GONE);
                            txtDPNEFT_RTGSdate_DocPrint.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date_DocPrint.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_DocPrint.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_DocPrint.setVisibility(View.GONE);
                        }*/


                        if (response.body().getData().get(id_pos).getRto().equals("No")) {
                            edt_RTO_RtoTax_DocPrint.setVisibility(View.GONE);
                            edt_RTO_RtoPassing_DocPrint.setVisibility(View.GONE);
                            edt_RTO_Insurance_DocPrint.setVisibility(View.GONE);
                            edt_RTO_AgentFees_DocPrint.setVisibility(View.GONE);
                            edt_RTO_NumberPlat_DocPrint.setVisibility(View.GONE);
                            edt_RTO_LoanCharge_DocPrint.setVisibility(View.GONE);


                            txtRTOTax.setVisibility(View.GONE);
                            txtRTOPassing.setVisibility(View.GONE);
                            txtInsurance.setVisibility(View.GONE);
                            txtAgentFees.setVisibility(View.GONE);
                            txtNumberPlat.setVisibility(View.GONE);
                            txtLoanCharge.setVisibility(View.GONE);
                        } else {
                            edt_RTO_RtoTax_DocPrint.setVisibility(View.VISIBLE);
                            edt_RTO_RtoPassing_DocPrint.setVisibility(View.VISIBLE);
                            edt_RTO_Insurance_DocPrint.setVisibility(View.VISIBLE);
                            edt_RTO_AgentFees_DocPrint.setVisibility(View.VISIBLE);
                            edt_RTO_NumberPlat_DocPrint.setVisibility(View.VISIBLE);
                            edt_RTO_LoanCharge_DocPrint.setVisibility(View.VISIBLE);

                            txtRTOTax.setVisibility(View.VISIBLE);
                            txtRTOPassing.setVisibility(View.VISIBLE);
                            txtInsurance.setVisibility(View.VISIBLE);
                            txtAgentFees.setVisibility(View.VISIBLE);
                            txtNumberPlat.setVisibility(View.VISIBLE);
                            txtLoanCharge.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get(id_pos).getSkim().equals("No")) {

                            lin_ConsumerSkim_passing.setVisibility(View.GONE);

                   /* edt_CS_Hood_DocPrint.setVisibility(View.GONE);
                    edt_CS_TopLink_DocPrint.setVisibility(View.GONE);
                    edt_CS_DrawBar_DocPrint.setVisibility(View.GONE);
                    edt_CS_ToolKit_DocPrint.setVisibility(View.GONE);
                    edt_CS_Bumper_DocPrint.setVisibility(View.GONE);
                    edt_CS_Hitch_DocPrint.setVisibility(View.GONE);
                    edt_CS_Description_DocPrint.setVisibility(View.GONE);

                    txt_cs_Hood.setVisibility(View.GONE);
                    txt_cs_TopLink.setVisibility(View.GONE);
                    txt_cs_Drawbar.setVisibility(View.GONE);
                    txt_cs_ToolKit.setVisibility(View.GONE);
                    txt_cs_Bumper.setVisibility(View.GONE);
                    txt_cs_Hitch.setVisibility(View.GONE);
                    txt_cs_Description.setVisibility(View.GONE);*/

                        } else {

                            lin_ConsumerSkim_passing.setVisibility(View.VISIBLE);

                   /* edt_CS_Hood_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_TopLink_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_DrawBar_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_ToolKit_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_Bumper_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_Hitch_DocPrint.setVisibility(View.VISIBLE);
                    edt_CS_Description_DocPrint.setVisibility(View.VISIBLE);

                    txt_cs_Hood.setVisibility(View.VISIBLE);
                    txt_cs_TopLink.setVisibility(View.VISIBLE);
                    txt_cs_Drawbar.setVisibility(View.VISIBLE);
                    txt_cs_ToolKit.setVisibility(View.VISIBLE);
                    txt_cs_Bumper.setVisibility(View.VISIBLE);
                    txt_cs_Hitch.setVisibility(View.VISIBLE);
                    txt_cs_Description.setVisibility(View.VISIBLE);*/
                        }


                        if (response.body().getData().get(id_pos).getAtype().equals("Loan")) {
                            lin_LoanDetail_DocPrint.setVisibility(View.VISIBLE);
                            lin_cashLoan_DocPrint.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {
                            lin_LoanDetail_DocPrint.setVisibility(View.GONE);
                            lin_cashLoan_DocPrint.setVisibility(View.VISIBLE);
                        }


                        if (response.body().getData().get(id_pos).getAtype().equals("Cash-Loan")) {
                            lin_LoanDetail_DocPrint.setVisibility(View.VISIBLE);
                            lin_cashLoan_DocPrint.setVisibility(View.VISIBLE);
                        }


                        //******************************************

                        if (response.body().getData().get(id_pos).getStage_check().equals("0")) {

                            StageFinalVal = Stage;

                        } else {

                            StageFinalVal = edt_DocPrintDetail_Stage_DocPrint.getText().toString();
                        }

                        if( StageFinalVal == null){
                            StageFinalVal=" ";
                        }



                        //******************************************



                        edt_ADBooking_BookingNo_DocPrint.setText(response.body().getData().get(id_pos).getBno());
                        edt_ADBooking_BookingType_DocPrint.setText(response.body().getData().get(id_pos).getB_type());
                        edt_ADBooking_BookingDate_DocPrint.setText(response.body().getData().get(id_pos).getB_date());
                        edt_ADBooking_BookingLoginName_DocPrint.setText(response.body().getData().get(id_pos).getEmp());

                        // Toast.makeText(DeliveryFormActivity.this, ""+id_item, Toast.LENGTH_SHORT).show();

               /* edt_ADBooking_BookingNo_DocPrint.setText(response.body().getData().get(id_item).getBno());
                edt_ADBooking_BookingType_DocPrint.setText(response.body().getData().get(id_item).getB_type());
                edt_ADBooking_BookingDate_DocPrint.setText(response.body().getData().get(id_item).getB_date());
                edt_ADBooking_BookingLoginName_DocPrint.setText(response.body().getData().get(id_item).getEmp());*/


                        edt_CD_Fname_DocPrint.setText(response.body().getData().get(id_pos).getFname());
                        edt_CD_LastName_DocPrint.setText(response.body().getData().get(id_pos).getLname() + " ");
                        edt_CD_Surname_DocPrint.setText(response.body().getData().get(id_pos).getSname());
                        edt_CD_MobileNo_DocPrint.setText(response.body().getData().get(id_pos).getMobileno());
                        edt_CD_WhatsappNo_DocPrint.setText(response.body().getData().get(id_pos).getWhno());
                        edt_CD_ReferenceName_DocPrint.setText(response.body().getData().get(id_pos).getRef_name());
                        edt_CD_ReferenceNo_DocPrint.setText(response.body().getData().get(id_pos).getRef_no());
                        edt_CD_State_DocPrint.setText(response.body().getData().get(id_pos).getState());
                        edt_CD_City_DocPrint.setText(response.body().getData().get(id_pos).getCity());
                        edt_CD_District_DocPrint.setText(response.body().getData().get(id_pos).getDistric());
                        edt_CD_Village_DocPrint.setText(response.body().getData().get(id_pos).getVillage());
                        edt_CD_EmployeName_DocPrint.setText(response.body().getData().get(id_pos).getEmp());
                        edt_CD_Taluko_DocPrint.setText(response.body().getData().get(id_pos).getTehsill());
                        edt_CD_PassBook_DocPrint.setText(response.body().getData().get(id_pos).getB_p_photo());
                        edt_CD_ChequeBook_DocPrint.setText(response.body().getData().get(id_pos).getCheck_photo());
                        edt_CD_PaymentOption_DocPrint.setText(response.body().getData().get(id_pos).getAtype());


                        edt_PD_PurchaseName_DocPrint.setText(response.body().getData().get(id_pos).getProduct_name());
                        edt_PD_ModelName_DocPrint.setText(response.body().getData().get(id_pos).getModel_name());
                        edt_PD_Description_DocPrint.setText(response.body().getData().get(id_pos).getP_desc());


                        edt_PriceD_price_DocPrint.setText(response.body().getData().get(id_pos).getDeal_price());
                        edt_PriceD_priceInWord_DocPrint.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                        edt_PriceD_GST_DocPrint.setText(response.body().getData().get(id_pos).getGst());


                        edt_RTO_RtoMain_DocPrint.setText(response.body().getData().get(id_pos).getRto());
                        edt_RTO_RtoTax_DocPrint.setText(response.body().getData().get(id_pos).getRto_tax());
                        edt_RTO_RtoPassing_DocPrint.setText(response.body().getData().get(id_pos).getRto_passing());
                        edt_RTO_Insurance_DocPrint.setText(response.body().getData().get(id_pos).getInsurance());
                        edt_RTO_AgentFees_DocPrint.setText(response.body().getData().get(id_pos).getAgent_fee());
                        edt_RTO_NumberPlat_DocPrint.setText(response.body().getData().get(id_pos).getNumber_plat());
                        edt_RTO_LoanCharge_DocPrint.setText(response.body().getData().get(id_pos).getLoan_charge());

                        edt_DownP_BookingAmount_DocPrint.setText(response.body().getData().get(id_pos).getBooking_amt());
                        edt_DownP_CashAmount_DocPrint.setText(response.body().getData().get(id_pos).getAmount());
                        edt_DownP_BankOption_DocPrint.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                        edt_DownP_ChequeDate_DocPrint.setText(response.body().getData().get(id_pos).getCheck_date());
                        edt_DownP_ChequeAmount_DocPrint.setText(response.body().getData().get(id_pos).getCheck_amt());
                        edt_DownP_NEFT_RTGS_date_DocPrint.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                        edt_DownP_NEFT_RTGSAmount_DocPrint.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                        edt_DownP_SelectMake_DocPrint.setText(response.body().getData().get(id_pos).getMake());
                        edt_DownP_ModelVehicle_DocPrint.setText(response.body().getData().get(id_pos).getModel());
                        edt_DownP_oldAmount_DocPrint.setText(response.body().getData().get(id_pos).getOld_t_amount());
                        edt_DownP_ManufactureYear_DocPrint.setText(response.body().getData().get(id_pos).getM_year());
                        edt_DownP_PaperExchange_DocPrint.setText(response.body().getData().get(id_pos).getPaper_expence());
                        edt_DownP_oldTractorAmount_DocPrint.setText(response.body().getData().get(id_pos).getC_amount());
                        edt_DownP_NOC_DocPrint.setText(response.body().getData().get(id_pos).getNoc());


                        edt_CS_ConsumerSkim_DocPrint.setText(response.body().getData().get(id_pos).getSkim());
                        edt_CS_Hood_DocPrint.setText(response.body().getData().get(id_pos).getHood());
                        edt_CS_TopLink_DocPrint.setText(response.body().getData().get(id_pos).getToplink());
                        edt_CS_DrawBar_DocPrint.setText(response.body().getData().get(id_pos).getDrowbar());
                        edt_CS_ToolKit_DocPrint.setText(response.body().getData().get(id_pos).getToolkit());
                        edt_CS_Bumper_DocPrint.setText(response.body().getData().get(id_pos).getBumper());
                        edt_CS_Hitch_DocPrint.setText(response.body().getData().get(id_pos).getHitch());
                        edt_CS_Description_DocPrint.setText(response.body().getData().get(id_pos).getDescription());

                        //Loan detail
                        edt_DocPrintDetail_REF_DocPrint.setText(response.body().getData().get(id_pos).getR_e_name());
                        edt_DocPrintDetail_FinanceForm_DocPrint.setText(response.body().getData().get(id_pos).getFinance_from());
                        edt_DocPrintDetail_LoanAmount_DocPrint.setText(response.body().getData().get(id_pos).getLoan_amount());
                        edt_DocPrintDetail_LoanSancAmont_DocPrint.setText(response.body().getData().get(id_pos).getL_sec_amt());
                        edt_DocPrintDetail_LoanCharge_DocPrint.setText(response.body().getData().get(id_pos).getLloan_charge());
                        edt_DocPrintDetail_LandDetail_DocPrint.setText(response.body().getData().get(id_pos).getLand_details());
                        edt_DocPrintDetail_CibilScore_DocPrint.setText(response.body().getData().get(id_pos).getCibil_score());
                        edt_DocPrintDetail_FiDate_DocPrint.setText(response.body().getData().get(id_pos).getFi_date());
                        edt_DocPrintDetail_SanctionDate_DocPrint.setText(response.body().getData().get(0).getSectiondate());
                        edt_DocPrintDetail_Stage_DocPrint.setText(response.body().getData().get(id_pos).getStage());


              /*  edt_modelName_DocPrint.setText(response.body().getData().get(0).getDmodelname()+"");
                edt_ChesisNumber_DocPrint.setText(response.body().getData().get(0).getChasisno()+"");
                edt_EngineNumber_DocPrint.setText(response.body().getData().get(0).getEngineno()+"");
                edt_Variente_DocPrint.setText(response.body().getData().get(0).getVariants()+"");*/

                        edt_Type_DocPrint.setText(response.body().getData().get(id_pos).getTyre() + "");
                        edt_Bettry_DocPrint.setText(response.body().getData().get(id_pos).getBattery() + "");
                        edt_DocPrintDate_DocPrint.setText(response.body().getData().get(id_pos).getDelivery_date() + "");


                        //Cash details
                        edt_CashDetail_Date_DocPrint.setText(response.body().getData().get(id_pos).getCash_date());
                        edt_CashDetail_Amount_DocPrint.setText(response.body().getData().get(id_pos).getCash_amount());
                        edt_CashDetail_Description_DocPrint.setText(response.body().getData().get(id_pos).getCash_description());

                    }

                    @Override
                    public void onFailure(Call<DocumentPrintDataModel> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });

            }
        });


        txt_CD_UploadBookingPhoto_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        txt_CD_AdharCard_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        txt_CD_ElectionCard_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });

        txt_CD_PassportSize_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });


        txt_DownP_UploadCheque_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });

        txt_DownP_UploadNEFT_RTGS_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

        txt_DownP_UploadNocPhoto_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_DownP_UploadOldVehicle_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });

        txt_DownP_UploadRCBook_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });

        txt_DownP_UploadElectionPhoto_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 11);
            }
        });


        txt_LoanDetail_BankPassBook_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 12);
            }
        });

        txt_LoanDetail_Cheque_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 13);
            }
        });

        txt_LoanDetail_SarpanchId_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 14);
            }
        });

        txt_LoanDetail_SignatureVeri_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 15);
            }
        });


        txt_DocPrintPhoto_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 16);
            }
        });

        txt_ChesisPrint_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 17);
            }
        });

        // ----------------------------------------------------------------------------------------

        //------------------------------------------------------------------------
        txt_CD_AdharCard_DocPrint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 18);
            }
        });

        txt_CD_ElectionCard_DocPrint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 19);
            }
        });

        txt_CD_PassportSize_DocPrint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 20);
            }
        });

        txt_DownP_UploadRCBook_DocPrint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 21);
            }
        });

        txt_DownP_UploadElectionPhoto_DocPrint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 22);
            }
        });

        txt_LoanDetail_BankPassBook_DocPrint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 23);
            }
        });

        //--------------------------------------------------------------------------


        edt_DocPrintDate_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DocumentPrintFormActivity.this,
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
                        edt_DocPrintDate_DocPrint.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DocPrintDate_DocPrint.setFocusable(false);

        edt_DocPrintDetail_SanctionDate_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DocumentPrintFormActivity.this,
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
                        edt_DocPrintDetail_SanctionDate_DocPrint.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //   edt_DocPrintDetail_SanctionDate_DocPrint.setFocusable(false);

        edt_CashDetail_Date_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DocumentPrintFormActivity.this,
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
                        edt_CashDetail_Date_DocPrint.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        btn_DocPrint_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(DocumentPrintFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                MultipartBody.Part CD_UploadBookingPhoto_DocPrint = null;
                MultipartBody.Part CD_AdharCard_DocPrint = null;
                MultipartBody.Part CD_ElectionCard_DocPrint = null;
                MultipartBody.Part CD_PassportSize_DocPrint = null;
                MultipartBody.Part UploadDPCheque_DocPrint = null;
                MultipartBody.Part UploadDPNEFT_RTGS_DocPrint = null;
                MultipartBody.Part UploadDPNocPhoto_DocPrint = null;
                MultipartBody.Part UploadDPOldVehicle_DocPrint = null;
                MultipartBody.Part UploadDPRCBook_DocPrint = null;
                MultipartBody.Part UploadDPElectionPhoto_DocPrint = null;

                MultipartBody.Part LoanDetail_BankPassBook_DocPrint = null;
                MultipartBody.Part LoanDetail_Cheque_DocPrint = null;
                MultipartBody.Part LoanDetail_SarpanchId_DocPrint = null;
                MultipartBody.Part LoanDetail_SignatureVeri_DocPrint = null;

                MultipartBody.Part DeliveryPhoto_DocPrint_DocPrint = null;
                MultipartBody.Part ChesisPrint_DocPrint = null;

                MultipartBody.Part CD_AdharCard_Delivery2 = null;
                MultipartBody.Part CD_ElectionCard_Delivery2 = null;
                MultipartBody.Part CD_PassportSize_Delivery2 = null;

                MultipartBody.Part UploadDPRCBook_Delivery2 = null;
                MultipartBody.Part UploadDPElectionPhoto_Delivery2 = null;
                MultipartBody.Part LoanDetail_BankPassBook_Delivery2 = null;


                if (waypath_CD_UploadBookingPhoto_DocPrint != null) {
                    File file1 = new File(waypath_CD_UploadBookingPhoto_DocPrint);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    CD_UploadBookingPhoto_DocPrint = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }

                if (waypath_CD_AdharCard_DocPrint != null) {
                    File file2 = new File(waypath_CD_AdharCard_DocPrint);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    CD_AdharCard_DocPrint = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }

                if (waypath_CD_ElectionCard_DocPrint != null) {
                    File file3 = new File(waypath_CD_ElectionCard_DocPrint);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    CD_ElectionCard_DocPrint = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                if (waypath_CD_PassportSize_DocPrint != null) {
                    File file4 = new File(waypath_CD_PassportSize_DocPrint);
                    final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                    CD_PassportSize_DocPrint = MultipartBody.Part.createFormData("image6",
                            file4.getName(), requestBody4);
                }


                if (waypathUploadDPCheque_DocPrint != null) {
                    File file5 = new File(waypathUploadDPCheque_DocPrint);
                    final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                    UploadDPCheque_DocPrint = MultipartBody.Part.createFormData("image7",
                            file5.getName(), requestBody5);
                }

                if (waypathUploadDPNEFT_RTGS_DocPrint != null) {
                    File file6 = new File(waypathUploadDPNEFT_RTGS_DocPrint);
                    final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                    UploadDPNEFT_RTGS_DocPrint = MultipartBody.Part.createFormData("image8",
                            file6.getName(), requestBody6);
                }


                if (waypathUploadDPOldVehicle_DocPrint != null) {
                    File file8 = new File(waypathUploadDPOldVehicle_DocPrint);
                    final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                    UploadDPOldVehicle_DocPrint = MultipartBody.Part.createFormData("image9",
                            file8.getName(), requestBody8);
                }

                if (waypathUploadDPRCBook_DocPrint != null) {
                    File file9 = new File(waypathUploadDPRCBook_DocPrint);
                    final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file9);
                    UploadDPRCBook_DocPrint = MultipartBody.Part.createFormData("image10",
                            file9.getName(), requestBody9);
                }

                if (waypathUploadDPElectionPhoto_DocPrint != null) {
                    File file10 = new File(waypathUploadDPElectionPhoto_DocPrint);
                    final RequestBody requestBody10 = RequestBody.create(MediaType.parse("image/*"), file10);
                    UploadDPElectionPhoto_DocPrint = MultipartBody.Part.createFormData("image11",
                            file10.getName(), requestBody10);
                }

                if (waypathUploadDPNocPhoto_DocPrint != null) {
                    File file7 = new File(waypathUploadDPNocPhoto_DocPrint);
                    final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                    UploadDPNocPhoto_DocPrint = MultipartBody.Part.createFormData("image12",
                            file7.getName(), requestBody7);
                }


                if (WayPath_LoanDetail_BankPassBook != null) {
                    File file11 = new File(WayPath_LoanDetail_BankPassBook);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file11);
                    LoanDetail_BankPassBook_DocPrint = MultipartBody.Part.createFormData("do_photo13",
                            file11.getName(), requestBody11);
                }

                if (WayPath_LoanDetail_Cheque != null) {
                    File file12 = new File(WayPath_LoanDetail_Cheque);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file12);
                    LoanDetail_Cheque_DocPrint = MultipartBody.Part.createFormData("do_photo14",
                            file12.getName(), requestBody11);
                }

                if (WayPath_LoanDetail_SarpanchId != null) {
                    File file13 = new File(WayPath_LoanDetail_SarpanchId);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file13);
                    LoanDetail_SarpanchId_DocPrint = MultipartBody.Part.createFormData("do_photo15",
                            file13.getName(), requestBody11);
                }

                if (WayPath_LoanDetail_SignatureVeri != null) {
                    File file14 = new File(WayPath_LoanDetail_SignatureVeri);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file14);
                    LoanDetail_SignatureVeri_DocPrint = MultipartBody.Part.createFormData("do_photo16",
                            file14.getName(), requestBody11);
                }

                if (Waypath_DocPrintPhoto_DocPrint != null) {
                    File file15 = new File(Waypath_DocPrintPhoto_DocPrint);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file15);
                    DeliveryPhoto_DocPrint_DocPrint = MultipartBody.Part.createFormData("do_photo17",
                            file15.getName(), requestBody11);
                }


                if (Waypath_ChesisPrint_DocPrint != null) {
                    File file16 = new File(Waypath_ChesisPrint_DocPrint);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file16);
                    ChesisPrint_DocPrint = MultipartBody.Part.createFormData("do_photo18",
                            file16.getName(), requestBody11);
                }




                //--------------------------------------------------------------------------
                //-------------------------------------------------------
                if (waypath_CD_AdharCard_DocPrint2 != null) {
                    File file17 = new File(waypath_CD_AdharCard_DocPrint2);
                    final RequestBody requestBody17 = RequestBody.create(MediaType.parse("image/*"), file17);
                    CD_AdharCard_Delivery2 = MultipartBody.Part.createFormData("imgg1",
                            file17.getName(), requestBody17);
                }

                if (waypath_CD_ElectionCard_DocPrint2 != null) {
                    File file18 = new File(waypath_CD_ElectionCard_DocPrint2);
                    final RequestBody requestBody18 = RequestBody.create(MediaType.parse("image/*"), file18);
                    CD_ElectionCard_Delivery2 = MultipartBody.Part.createFormData("imgg2",
                            file18.getName(), requestBody18);
                }

                if (waypath_CD_PassportSize_DocPrint2 != null) {
                    File file19 = new File(waypath_CD_PassportSize_DocPrint2);
                    final RequestBody requestBody19 = RequestBody.create(MediaType.parse("image/*"), file19);
                    CD_PassportSize_Delivery2 = MultipartBody.Part.createFormData("imgg3",
                            file19.getName(), requestBody19);
                }

                if (waypathUploadDPRCBook_DocPrint2 != null) {
                    File file20 = new File(waypathUploadDPRCBook_DocPrint2);
                    final RequestBody requestBody20 = RequestBody.create(MediaType.parse("image/*"), file20);
                    UploadDPRCBook_Delivery2 = MultipartBody.Part.createFormData("imgg4",
                            file20.getName(), requestBody20);
                }

                if (waypathUploadDPElectionPhoto_DocPrint2 != null) {
                    File file21 = new File(waypathUploadDPElectionPhoto_DocPrint2);
                    final RequestBody requestBody21 = RequestBody.create(MediaType.parse("image/*"), file21);
                    UploadDPElectionPhoto_Delivery2 = MultipartBody.Part.createFormData("imgg5",
                            file21.getName(), requestBody21);
                }


                if (WayPath_LoanDetail_BankPassBook2 != null) {
                    File file22 = new File(WayPath_LoanDetail_BankPassBook2);
                    final RequestBody requestBody22 = RequestBody.create(MediaType.parse("image/*"), file22);
                    LoanDetail_BankPassBook_Delivery2 = MultipartBody.Part.createFormData("imgg6",
                            file22.getName(), requestBody22);
                }


                WebService.getClient().getDeliverySubmit(
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingNo_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingDate_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), emp),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Fname_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Surname_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_MobileNo_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_WhatsappNo_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceName_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceNo_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_State_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_City_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_District_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Taluko_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Village_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingLoginName_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingType_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PassBook_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ChequeBook_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_PurchaseName_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_ModelName_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_Description_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_price_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_priceInWord_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_GST_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoMain_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoTax_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoPassing_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_Insurance_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_AgentFees_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_NumberPlat_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_LoanCharge_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BookingAmount_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_CashAmount_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BankOption_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeDate_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeAmount_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGS_date_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGSAmount_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_SelectMake_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ModelVehicle_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ManufactureYear_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldAmount_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_PaperExchange_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldTractorAmount_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NOC_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hood_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_TopLink_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_DrawBar_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ToolKit_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Bumper_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hitch_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Description_DocPrint.getText().toString()),
                        CD_UploadBookingPhoto_DocPrint,
                        CD_AdharCard_DocPrint,
                        CD_ElectionCard_DocPrint,
                        CD_PassportSize_DocPrint,
                        UploadDPCheque_DocPrint,
                        UploadDPNEFT_RTGS_DocPrint,
                        UploadDPOldVehicle_DocPrint,
                        UploadDPRCBook_DocPrint,
                        UploadDPElectionPhoto_DocPrint,
                        UploadDPNocPhoto_DocPrint,
                        RequestBody.create(MediaType.parse("text/plain"), idBookingUpload),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_REF_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_FinanceForm_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_LoanAmount_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_LoanSancAmont_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_LoanCharge_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_LandDetail_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_CibilScore_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_FiDate_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_SanctionDate_DocPrint.getText().toString()),
                       /* RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDetail_Stage_DocPrint.getText().toString()),*/
                        RequestBody.create(MediaType.parse("text/plain"),StageFinalVal),
                        LoanDetail_BankPassBook_DocPrint,
                        /*LoanDetail_Cheque_DocPrint,
                        LoanDetail_SarpanchId_DocPrint,*/
                        LoanDetail_SignatureVeri_DocPrint,
                        DeliveryPhoto_DocPrint_DocPrint,
                        ChesisPrint_DocPrint,
                        RequestBody.create(MediaType.parse("text/plain"), edt_DocPrintDate_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Type_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Bettry_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PaymentOption_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ConsumerSkim_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Date_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Amount_DocPrint.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Description_DocPrint.getText().toString()),
                        CD_AdharCard_Delivery2,
                        CD_ElectionCard_Delivery2,
                        CD_PassportSize_Delivery2,
                        UploadDPRCBook_Delivery2,
                        UploadDPElectionPhoto_Delivery2,
                        LoanDetail_BankPassBook_Delivery2,
                        RequestBody.create(MediaType.parse("text/plain"),""),
                        RequestBody.create(MediaType.parse("text/plain"),""),
                        RequestBody.create(MediaType.parse("text/plain"),""),
                        RequestBody.create(MediaType.parse("text/plain"),""),
                        RequestBody.create(MediaType.parse("text/plain"),"")
                ).enqueue(new Callback<DeliveryBookingModel>() {
                    @Override
                    public void onResponse(@NotNull Call<DeliveryBookingModel> call, @NotNull Response<DeliveryBookingModel> response) {
                        progressDialog.dismiss();
                        assert response.body() != null;
                        Toast.makeText(DocumentPrintFormActivity.this, ""
                                        + response.body().getMessage() + " ",
                                Toast.LENGTH_LONG).show();
                        //  Toast.makeText(DocumentPrintFormActivity.this, "Added", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(DocumentPrintFormActivity.this,
                                BookingDeliveryMainActivity.class);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(@NotNull Call<DeliveryBookingModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Log.d("Fail_data", "onFailure: " + t.getCause() + " " + t.getMessage());
                        // Toast.makeText(DocumentPrintFormActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
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
                    uri_CD_UploadBookingPhoto_DocPrint = data.getData();
                    Log.d("PanImageUri", uri_CD_UploadBookingPhoto_DocPrint.toString());
                    waypath_CD_UploadBookingPhoto_DocPrint = getFilePath(this, uri_CD_UploadBookingPhoto_DocPrint);


                    Log.d("PanImage", waypath_CD_UploadBookingPhoto_DocPrint);
                    String[] name = waypath_CD_UploadBookingPhoto_DocPrint.split("/");
                    txt_CD_UploadBookingPhoto_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_Booking_DocPrint.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_DocPrint = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_DocPrint.toString());
                    waypath_CD_AdharCard_DocPrint = getFilePath(this, uri_CD_AdharCard_DocPrint);

                    Log.d("PanRTGS", waypath_CD_AdharCard_DocPrint);
                    String[] name = waypath_CD_AdharCard_DocPrint.split("/");
                    txt_CD_AdharCard_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_DocPrint.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_DocPrint.toString());
                    waypath_CD_ElectionCard_DocPrint = getFilePath(this, uri_CD_ElectionCard_DocPrint);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_DocPrint);
                    String[] name = waypath_CD_ElectionCard_DocPrint.split("/");
                    txt_CD_ElectionCard_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_DocPrint.toString());
                    waypath_CD_PassportSize_DocPrint = getFilePath(this, uri_CD_PassportSize_DocPrint);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_DocPrint);
                    String[] name = waypath_CD_PassportSize_DocPrint.split("/");
                    txt_CD_PassportSize_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPCheque_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPCheque_DocPrint.toString());
                    waypathUploadDPCheque_DocPrint = getFilePath(this, uriUploadDPCheque_DocPrint);
                    Log.d("Pan Image Uri", waypathUploadDPCheque_DocPrint);
                    String[] name = waypathUploadDPCheque_DocPrint.split("/");
                    txt_DownP_UploadCheque_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_Cheque_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNEFT_RTGS_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNEFT_RTGS_DocPrint.toString());
                    waypathUploadDPNEFT_RTGS_DocPrint = getFilePath(this, uriUploadDPNEFT_RTGS_DocPrint);
                    Log.d("Pan Image Uri", waypathUploadDPNEFT_RTGS_DocPrint);
                    String[] name = waypathUploadDPNEFT_RTGS_DocPrint.split("/");
                    txt_DownP_UploadNEFT_RTGS_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NEFT_RTGS_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNocPhoto_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNocPhoto_DocPrint.toString());
                    waypathUploadDPNocPhoto_DocPrint = getFilePath(this, uriUploadDPNocPhoto_DocPrint);
                    Log.d("Pan Image Uri", waypathUploadDPNocPhoto_DocPrint);
                    String[] name = waypathUploadDPNocPhoto_DocPrint.split("/");
                    txt_DownP_UploadNocPhoto_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NocPhoto_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPOldVehicle_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPOldVehicle_DocPrint.toString());
                    waypathUploadDPOldVehicle_DocPrint = getFilePath(this, uriUploadDPOldVehicle_DocPrint);
                    Log.d("Pan Image Uri", waypathUploadDPOldVehicle_DocPrint);
                    String[] name = waypathUploadDPOldVehicle_DocPrint.split("/");
                    txt_DownP_UploadOldVehicle_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_OldVehicle_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_DocPrint.toString());
                    waypathUploadDPRCBook_DocPrint = getFilePath(this, uriUploadDPRCBook_DocPrint);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_DocPrint);
                    String[] name = waypathUploadDPRCBook_DocPrint.split("/");
                    txt_DownP_UploadRCBook_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_DocPrint.toString());
                    waypathUploadDPElectionPhoto_DocPrint = getFilePath(this, uriUploadDPElectionPhoto_DocPrint);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_DocPrint);
                    String[] name = waypathUploadDPElectionPhoto_DocPrint.split("/");
                    txt_DownP_UploadElectionPhoto_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_DocPrint.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_BankPassBook_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook_DocPrint.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_Cheque_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_Cheque_DocPrint.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_SarpanchId_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SarpanchID_DocPrint.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_SignatureVeri_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SignatureVerifi_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_DocPrintPhoto_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uri_DocPrintPhoto_DocPrint.toString());
                    Waypath_DocPrintPhoto_DocPrint = getFilePath(this, uri_DocPrintPhoto_DocPrint);
                    Log.d("Pan Image Uri", Waypath_DocPrintPhoto_DocPrint);
                    String[] name = Waypath_DocPrintPhoto_DocPrint.split("/");
                    txt_DocPrintPhoto_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DocPrintPhoto_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 17) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_ChesisPrint_DocPrint = data.getData();
                    Log.d("Pan Image Uri", uri_ChesisPrint_DocPrint.toString());
                    Waypath_ChesisPrint_DocPrint = getFilePath(this, uri_ChesisPrint_DocPrint);
                    Log.d("Pan Image Uri", Waypath_ChesisPrint_DocPrint);
                    String[] name = Waypath_ChesisPrint_DocPrint.split("/");
                    txt_ChesisPrint_DocPrint.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_ChesisPrint_DocPrint.setImageURI(selectedImageUri);
                }
            }
        }

        //----------------------------------------------------------------------

        if (requestCode == 18) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_DocPrint2 = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_DocPrint2.toString());
                    waypath_CD_AdharCard_DocPrint2 = getFilePath(this, uri_CD_AdharCard_DocPrint2);

                    Log.d("PanRTGS", waypath_CD_AdharCard_DocPrint2);
                    String[] name = waypath_CD_AdharCard_DocPrint2.split("/");
                    txt_CD_AdharCard_DocPrint2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_DocPrint2.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_DocPrint2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_DocPrint2.toString());
                    waypath_CD_ElectionCard_DocPrint2 = getFilePath(this, uri_CD_ElectionCard_DocPrint2);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_DocPrint2);
                    String[] name = waypath_CD_ElectionCard_DocPrint2.split("/");
                    txt_CD_ElectionCard_DocPrint2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_DocPrint2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_DocPrint2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_DocPrint2.toString());
                    waypath_CD_PassportSize_DocPrint2 = getFilePath(this, uri_CD_PassportSize_DocPrint2);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_DocPrint2);
                    String[] name = waypath_CD_PassportSize_DocPrint2.split("/");
                    txt_CD_PassportSize_DocPrint2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_DocPrint2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 21) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_DocPrint2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_DocPrint2.toString());
                    waypathUploadDPRCBook_DocPrint2 = getFilePath(this, uriUploadDPRCBook_DocPrint2);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_DocPrint2);
                    String[] name = waypathUploadDPRCBook_DocPrint2.split("/");
                    txt_DownP_UploadRCBook_DocPrint2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_DocPrint2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 22) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_DocPrint2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_DocPrint2.toString());
                    waypathUploadDPElectionPhoto_DocPrint2 = getFilePath(this, uriUploadDPElectionPhoto_DocPrint2);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_DocPrint2);
                    String[] name = waypathUploadDPElectionPhoto_DocPrint2.split("/");
                    txt_DownP_UploadElectionPhoto_DocPrint2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_DocPrint2.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_BankPassBook_DocPrint2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook_DocPrint2.setImageURI(selectedImageUri);
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
