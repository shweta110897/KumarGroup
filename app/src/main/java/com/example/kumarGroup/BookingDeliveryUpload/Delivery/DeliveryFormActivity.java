package com.example.kumarGroup.BookingDeliveryUpload.Delivery;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.DeliveryBookingModel;
import com.example.kumarGroup.DeliveryDataDisplayModel;
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

public class DeliveryFormActivity extends AppCompatActivity {

    //Argument Details
    EditText edt_ADBooking_BookingNo_Delivery, edt_ADBooking_BookingType_Delivery, edt_ADBooking_BookingDate_Delivery,
            edt_ADBooking_BookingLoginName_Delivery;

    TextView txtFillBookingType_Delivery, txtPassBookBook_Delivery, txtChequeBook_Delivery;


    //Customer detail
    EditText edt_CD_Fname_Delivery, edt_CD_LastName_Delivery, edt_CD_Surname_Delivery, edt_CD_MobileNo_Delivery,
            edt_CD_WhatsappNo_Delivery,
            edt_CD_ReferenceName_Delivery, edt_CD_ReferenceNo_Delivery, edt_CD_State_Delivery, edt_CD_City_Delivery,
            edt_CD_District_Delivery,
            edt_CD_Taluko_Delivery, edt_CD_Village_Delivery, edt_CD_EmployeName_Delivery, edt_CD_PassBook_Delivery,
            edt_CD_ChequeBook_Delivery, edt_CD_PaymentOption_Delivery;


    ImageView img_CD_Booking_Delivery, img_CD_AdharCard_Delivery, img_CD_ElectionCard_Delivery, img_CD_PassportSize_Delivery;

    TextView txt_CD_UploadBookingPhoto_Delivery, txt_CD_AdharCard_Delivery, txt_CD_ElectionCard_Delivery, txt_CD_PassportSize_Delivery;


    ImageView img_CD_AdharCard_Delivery2, img_CD_ElectionCard_Delivery2, img_CD_PassportSize_Delivery2;

    TextView txt_CD_AdharCard_Delivery2, txt_CD_ElectionCard_Delivery2, txt_CD_PassportSize_Delivery2;

    //Product detail
    EditText edt_PD_PurchaseName_Delivery, edt_PD_ModelName_Delivery, edt_PD_Description_Delivery;

    //Price detail
    EditText edt_PriceD_price_Delivery, edt_PriceD_priceInWord_Delivery, edt_PriceD_GST_Delivery;

    //RTO Detail
    EditText edt_RTO_RtoMain_Delivery, edt_RTO_RtoTax_Delivery, edt_RTO_RtoPassing_Delivery,
            edt_RTO_Insurance_Delivery, edt_RTO_AgentFees_Delivery, edt_RTO_NumberPlat_Delivery,
            edt_RTO_LoanCharge_Delivery;

    TextView txtRTOTax, txtRTOPassing, txtInsurance, txtAgentFees, txtNumberPlat, txtLoanCharge;

    //Down Payment
    EditText edt_DownP_BookingAmount_Delivery, edt_DownP_CashAmount_Delivery,
            edt_DownP_BankOption_Delivery, edt_DownP_ChequeDate_Delivery, edt_DownP_ChequeAmount_Delivery,
            edt_DownP_NEFT_RTGSAmount_Delivery, edt_DownP_NEFT_RTGS_date_Delivery,
            edt_DownP_SelectMake_Delivery, edt_DownP_ModelVehicle_Delivery, edt_DownP_ManufactureYear_Delivery,
            edt_DownP_oldAmount_Delivery, edt_DownP_PaperExchange_Delivery, edt_DownP_oldTractorAmount_Delivery,
            edt_DownP_NOC_Delivery;

    ImageView img_DownP_Cheque_Delivery, img_DownP_NEFT_RTGS_Delivery, img_DownP_NocPhoto_Delivery,
            img_DownP_OldVehicle_Delivery, img_DownP_RcBook_Delivery, img_DownP_ElectionPhoto_Delivery;

    ImageView img_DownP_RcBook_Delivery2, img_DownP_ElectionPhoto_Delivery2;

    TextView txt_DownP_UploadRCBook_Delivery2, txt_DownP_UploadElectionPhoto_Delivery2;

    LinearLayout lin_dp_Rcbook_Delivery2, lin_dp_Election_Delivery2;


    TextView txt_DownP_UploadCheque_Delivery, txt_DownP_UploadNEFT_RTGS_Delivery, txt_DownP_UploadNocPhoto_Delivery,
            txt_DownP_UploadOldVehicle_Delivery, txt_DownP_UploadRCBook_Delivery, txt_DownP_UploadElectionPhoto_Delivery;

    //Down payment label
    TextView txtDPCashAmount_Delivery, txtDPBankOption_Delivery, txtDPChequeDate_Delivery, txtDPChequeAmount_Delivery,
            txtDPNEFT_RTGSdate_Delivery,
            txtDPNEFT_RTGSAmount_Delivery, txtDPMake_Delivery, txtDPManufectureYear_Delivery,
            txtDPOldAmount_Delivery, txtDPModelName_Delivery,
            txtDPPaperExpense_Delivery, txtDPOldTractorAmount_Delivery, txtDPNoc_Delivery;


    LinearLayout lin_dp_cheque_Delivery, lin_dp_NEFT_RTGS_Delivery, lin_dp_NocPhoto_Delivery,
            lin_dp_OldVehicle_Delivery, lin_dp_Rcbook_Delivery,
            lin_dp_Election_Delivery;

    //Consumer Details
    EditText edt_CS_Hood_Delivery, edt_CS_TopLink_Delivery, edt_CS_DrawBar_Delivery, edt_CS_ToolKit_Delivery, edt_CS_Bumper_Delivery,
            edt_CS_Hitch_Delivery, edt_CS_Description_Delivery, edt_CS_ConsumerSkim_Delivery;

    TextView txt_cs_Hood, txt_cs_TopLink, txt_cs_Drawbar, txt_cs_ToolKit, txt_cs_Bumper,
            txt_cs_Hitch, txt_cs_Description;

    //Loan Details
    EditText edt_DeliveryDetail_REF_Delivery,
            edt_DeliveryDetail_FinanceForm_Delivery, edt_DeliveryDetail_LoanAmount_Delivery,
            edt_DeliveryDetail_LoanSancAmont_Delivery, edt_DeliveryDetail_LoanCharge_Delivery,
            edt_DeliveryDetail_LandDetail_Delivery, edt_DeliveryDetail_CibilScore_Delivery,
            edt_DeliveryDetail_FiDate_Delivery, edt_DeliveryDetail_SanctionDate_Delivery,
            edt_DeliveryDetail_Stage_Delivery;

    //  Spinner spn_DeliveryDetail_FinanceForm_Delivery,spn_DeliveryDetail_Stage_Delivery;

    ImageView img_LoanDetail_BankpassBook_Delivery, img_LoanDetail_Cheque_Delivery, img_LoanDetail_SarpanchID_Delivery,
            img_LoanDetail_SignatureVerifi_Delivery;

    TextView txt_LoanDetail_BankPassBook_Delivery, txt_LoanDetail_Cheque_Delivery, txt_LoanDetail_SarpanchId_Delivery,
            txt_LoanDetail_SignatureVeri_Delivery;

    ImageView img_LoanDetail_BankpassBook_Delivery2;//ib_pb_photo

    TextView txt_LoanDetail_BankPassBook_Delivery2;

    //Delivery Details
    EditText edt_modelName_Delivery, edt_ChesisNumber_Delivery, edt_EngineNumber_Delivery, edt_Variente_Delivery,
            edt_Type_Delivery, edt_Bettry_Delivery, edt_DeliveryDate_Delivery;

    ImageView img_DeliveryPhoto_Delivery, img_ChesisPrint_Delivery;

    TextView txt_DeliveryPhoto_Delivery, txt_ChesisPrint_Delivery;

    TextView deliveryTyre, deliveryBttery;

    //Cash Loan
    EditText edt_CashDetail_Date_Delivery, edt_CashDetail_Amount_Delivery, edt_CashDetail_Description_Delivery;

    LinearLayout lin_LoanDetail_Delivery, lin_cashLoan_Delivery;

    //***************************************************************************************************

    String WayPath_LoanDetail_BankPassBook, WayPath_LoanDetail_Cheque, WayPath_LoanDetail_SarpanchId,
            WayPath_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook, Uri_LoanDetail_Cheque, Uri_LoanDetail_SarpanchId,
            Uri_LoanDetail_SignatureVeri;

    Uri Uri_LoanDetail_BankPassBook2;
    String WayPath_LoanDetail_BankPassBook2;

    Uri uri_DeliveryPhoto_Delivery_one, uri_ChesisPrint_Delivery;

    String Waypath_DeliveryPhoto_Delivery_one, Waypath_ChesisPrint_Delivery;

    Button btn_Delivery_Submit;


    String Stage;
    String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};

    Spinner spn_delivery_stageloan;

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


    Uri uri_CD_AdharCard_Delivery2, uri_CD_ElectionCard_Delivery2, uri_CD_PassportSize_Delivery2;

    String waypath_CD_AdharCard_Delivery2, waypath_CD_ElectionCard_Delivery2,
            waypath_CD_PassportSize_Delivery2;

    Uri uri_CD_UploadBookingPhoto_Delivery, uri_CD_AdharCard_Delivery, uri_CD_ElectionCard_Delivery, uri_CD_PassportSize_Delivery;

    String waypath_CD_UploadBookingPhoto_Delivery, waypath_CD_AdharCard_Delivery, waypath_CD_ElectionCard_Delivery,
            waypath_CD_PassportSize_Delivery;

    Uri uriUploadDPCheque_Delivery, uriUploadDPNEFT_RTGS_Delivery, uriUploadDPNocPhoto_Delivery,
            uriUploadDPOldVehicle_Delivery, uriUploadDPRCBook_Delivery,
            uriUploadDPElectionPhoto_Delivery;

    String waypathUploadDPCheque_Delivery, waypathUploadDPNEFT_RTGS_Delivery, waypathUploadDPNocPhoto_Delivery,
            waypathUploadDPOldVehicle_Delivery, waypathUploadDPRCBook_Delivery, waypathUploadDPElectionPhoto_Delivery;

    Uri uriUploadDPRCBook_Delivery2, uriUploadDPElectionPhoto_Delivery2;

    String waypathUploadDPRCBook_Delivery2, waypathUploadDPElectionPhoto_Delivery2;

    String id_item;

    int id_pos;

    String StageCheck;

    String mydata;

    SwipeRefreshLayout swipeRefreshLayoutDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_form);
        Log.d("TAG", "onCreate: Check_Activity");

        getSupportActionBar().setTitle("Delivery Form");

        /** ********************************** Memory Allocation ******************************************************* */

        swipeRefreshLayoutDelivery = findViewById(R.id.swipeRefreshLayoutDelivery);


        //Agreement Details
        edt_ADBooking_BookingNo_Delivery = findViewById(R.id.edt_ADBooking_BookingNo_Delivery);
        edt_ADBooking_BookingType_Delivery = findViewById(R.id.edt_ADBooking_BookingType_Delivery);
        edt_ADBooking_BookingDate_Delivery = findViewById(R.id.edt_ADBooking_BookingDate_Delivery);
        edt_ADBooking_BookingLoginName_Delivery = findViewById(R.id.edt_ADBooking_BookingLoginName_Delivery);


        txtFillBookingType_Delivery = findViewById(R.id.txtFillBookingType_Delivery);
        txtPassBookBook_Delivery = findViewById(R.id.txtPassBookBook_Delivery);
        txtChequeBook_Delivery = findViewById(R.id.txtChequeBook_Delivery);

        //Customer Detail
        edt_CD_Fname_Delivery = findViewById(R.id.edt_CD_Fname_Delivery);
        edt_CD_LastName_Delivery = findViewById(R.id.edt_CD_LastName_Delivery);
        edt_CD_Surname_Delivery = findViewById(R.id.edt_CD_Surname_Delivery);
        edt_CD_MobileNo_Delivery = findViewById(R.id.edt_CD_MobileNo_Delivery);
        edt_CD_WhatsappNo_Delivery = findViewById(R.id.edt_CD_WhatsappNo_Delivery);
        edt_CD_ReferenceName_Delivery = findViewById(R.id.edt_CD_ReferenceName_Delivery);
        edt_CD_ReferenceNo_Delivery = findViewById(R.id.edt_CD_ReferenceNo_Delivery);
        edt_CD_State_Delivery = findViewById(R.id.edt_CD_State_Delivery);
        edt_CD_City_Delivery = findViewById(R.id.edt_CD_City_Delivery);
        edt_CD_District_Delivery = findViewById(R.id.edt_CD_District_Delivery);
        edt_CD_Taluko_Delivery = findViewById(R.id.edt_CD_Taluko_Delivery);
        edt_CD_Village_Delivery = findViewById(R.id.edt_CD_Village_Delivery);
        edt_CD_EmployeName_Delivery = findViewById(R.id.edt_CD_EmployeName_Delivery);
        edt_CD_PassBook_Delivery = findViewById(R.id.edt_CD_PassBook_Delivery);
        edt_CD_ChequeBook_Delivery = findViewById(R.id.edt_CD_ChequeBook_Delivery);
        edt_CD_PaymentOption_Delivery = findViewById(R.id.edt_CD_PaymentOption_Delivery);

        txt_CD_UploadBookingPhoto_Delivery = findViewById(R.id.txt_CD_UploadBookingPhoto_Delivery);
        txt_CD_AdharCard_Delivery = findViewById(R.id.txt_CD_AdharCard_Delivery);
        txt_CD_ElectionCard_Delivery = findViewById(R.id.txt_CD_ElectionCard_Delivery);
      /*  txt_CD_BankPassBook =findViewById(R.id.txt_CD_BankPassBook);
        txt_CD_Cheque =findViewById(R.id.txt_CD_Cheque);*/
        txt_CD_PassportSize_Delivery = findViewById(R.id.txt_CD_PassportSize_Delivery);

        txt_CD_AdharCard_Delivery2 = findViewById(R.id.txt_CD_AdharCard_Delivery2);
        txt_CD_ElectionCard_Delivery2 = findViewById(R.id.txt_CD_ElectionCard_Delivery2);
        txt_CD_PassportSize_Delivery2 = findViewById(R.id.txt_CD_PassportSize_Delivery2);

        img_CD_AdharCard_Delivery2 = findViewById(R.id.img_CD_AdharCard_Delivery2);
        img_CD_ElectionCard_Delivery2 = findViewById(R.id.img_CD_ElectionCard_Delivery2);
        img_CD_PassportSize_Delivery2 = findViewById(R.id.img_CD_PassportSize_Delivery2);


        img_CD_Booking_Delivery = findViewById(R.id.img_CD_Booking_Delivery);
        img_CD_AdharCard_Delivery = findViewById(R.id.img_CD_AdharCard_Delivery);
        img_CD_ElectionCard_Delivery = findViewById(R.id.img_CD_ElectionCard_Delivery);
       /* img_CD_BankPassBook=findViewById(R.id.img_CD_BankPassBook);
        img_CD_Cheque=findViewById(R.id.img_CD_Cheque);*/
        img_CD_PassportSize_Delivery = findViewById(R.id.img_CD_PassportSize_Delivery);

        //Product Details
        edt_PD_PurchaseName_Delivery = findViewById(R.id.edt_PD_PurchaseName_Delivery);
        edt_PD_ModelName_Delivery = findViewById(R.id.edt_PD_ModelName_Delivery);
        edt_PD_Description_Delivery = findViewById(R.id.edt_PD_Description_Delivery);


        //Price Details
        edt_PriceD_price_Delivery = findViewById(R.id.edt_PriceD_price_Delivery);
        edt_PriceD_priceInWord_Delivery = findViewById(R.id.edt_PriceD_priceInWord_Delivery);
        edt_PriceD_GST_Delivery = findViewById(R.id.edt_PriceD_GST_Delivery);

        //RTO Details
        edt_RTO_RtoMain_Delivery = findViewById(R.id.edt_RTO_RtoMain_Delivery);
        edt_RTO_RtoTax_Delivery = findViewById(R.id.edt_RTO_RtoTax_Delivery);
        edt_RTO_RtoPassing_Delivery = findViewById(R.id.edt_RTO_RtoPassing_Delivery);
        edt_RTO_Insurance_Delivery = findViewById(R.id.edt_RTO_Insurance_Delivery);
        edt_RTO_AgentFees_Delivery = findViewById(R.id.edt_RTO_AgentFees_Delivery);
        edt_RTO_NumberPlat_Delivery = findViewById(R.id.edt_RTO_NumberPlat_Delivery);
        edt_RTO_LoanCharge_Delivery = findViewById(R.id.edt_RTO_LoanCharge_Delivery);


        txtRTOTax = findViewById(R.id.txtRTOTax);
        txtRTOPassing = findViewById(R.id.txtRTOPassing);
        txtInsurance = findViewById(R.id.txtInsurance);
        txtAgentFees = findViewById(R.id.txtAgentFees);
        txtNumberPlat = findViewById(R.id.txtNumberPlat);
        txtLoanCharge = findViewById(R.id.txtLoanCharge);

        //Down Payment
        edt_DownP_BookingAmount_Delivery = findViewById(R.id.edt_DownP_BookingAmount_Delivery);
        edt_DownP_CashAmount_Delivery = findViewById(R.id.edt_DownP_CashAmount_Delivery);
        edt_DownP_BankOption_Delivery = findViewById(R.id.edt_DownP_BankOption_Delivery);
        edt_DownP_ChequeDate_Delivery = findViewById(R.id.edt_DownP_ChequeDate_Delivery);
        edt_DownP_ChequeAmount_Delivery = findViewById(R.id.edt_DownP_ChequeAmount_Delivery);
        edt_DownP_NEFT_RTGS_date_Delivery = findViewById(R.id.edt_DownP_NEFT_RTGS_date_Delivery);
        edt_DownP_NEFT_RTGSAmount_Delivery = findViewById(R.id.edt_DownP_NEFT_RTGSAmount_Delivery);
        edt_DownP_SelectMake_Delivery = findViewById(R.id.edt_DownP_SelectMake_Delivery);
        edt_DownP_ModelVehicle_Delivery = findViewById(R.id.edt_DownP_ModelVehicle_Delivery);
        edt_DownP_oldAmount_Delivery = findViewById(R.id.edt_DownP_oldAmount_Delivery);
        edt_DownP_ManufactureYear_Delivery = findViewById(R.id.edt_DownP_ManufactureYear_Delivery);
        edt_DownP_PaperExchange_Delivery = findViewById(R.id.edt_DownP_PaperExchange_Delivery);
        edt_DownP_oldTractorAmount_Delivery = findViewById(R.id.edt_DownP_oldTractorAmount_Delivery);
        edt_DownP_NOC_Delivery = findViewById(R.id.edt_DownP_NOC_Delivery);


        img_DownP_Cheque_Delivery = findViewById(R.id.img_DownP_Cheque_Delivery);
        img_DownP_NEFT_RTGS_Delivery = findViewById(R.id.img_DownP_NEFT_RTGS_Delivery);
        img_DownP_NocPhoto_Delivery = findViewById(R.id.img_DownP_NocPhoto_Delivery);
        img_DownP_OldVehicle_Delivery = findViewById(R.id.img_DownP_OldVehicle_Delivery);
        img_DownP_RcBook_Delivery = findViewById(R.id.img_DownP_RcBook_Delivery);
        img_DownP_ElectionPhoto_Delivery = findViewById(R.id.img_DownP_ElectionPhoto_Delivery);

        img_DownP_RcBook_Delivery2 = findViewById(R.id.img_DownP_RcBook_Delivery2);
        img_DownP_ElectionPhoto_Delivery2 = findViewById(R.id.img_DownP_ElectionPhoto_Delivery2);

        txt_DownP_UploadRCBook_Delivery2 = findViewById(R.id.txt_DownP_UploadRCBook_Delivery2);
        txt_DownP_UploadElectionPhoto_Delivery2 = findViewById(R.id.txt_DownP_UploadElectionPhoto_Delivery2);


        lin_dp_Rcbook_Delivery2 = findViewById(R.id.lin_dp_Rcbook_Delivery2);
        lin_dp_Election_Delivery2 = findViewById(R.id.lin_dp_Election_Delivery2);

        txt_DownP_UploadCheque_Delivery = findViewById(R.id.txt_DownP_UploadCheque_Delivery);
        txt_DownP_UploadNEFT_RTGS_Delivery = findViewById(R.id.txt_DownP_UploadNEFT_RTGS_Delivery);
        txt_DownP_UploadNocPhoto_Delivery = findViewById(R.id.txt_DownP_UploadNocPhoto_Delivery);
        txt_DownP_UploadOldVehicle_Delivery = findViewById(R.id.txt_DownP_UploadOldVehicle_Delivery);
        txt_DownP_UploadRCBook_Delivery = findViewById(R.id.txt_DownP_UploadRCBook_Delivery);
        txt_DownP_UploadElectionPhoto_Delivery = findViewById(R.id.txt_DownP_UploadElectionPhoto_Delivery);

        //Label Textview
        txtDPCashAmount_Delivery = findViewById(R.id.txtDPCashAmount_Delivery);
        txtDPBankOption_Delivery = findViewById(R.id.txtDPBankOption_Delivery);
        txtDPChequeDate_Delivery = findViewById(R.id.txtDPChequeDate_Delivery);
        txtDPChequeAmount_Delivery = findViewById(R.id.txtDPChequeAmount_Delivery);
        txtDPNEFT_RTGSdate_Delivery = findViewById(R.id.txtDPNEFT_RTGSdate_Delivery);
        txtDPNEFT_RTGSAmount_Delivery = findViewById(R.id.txtDPNEFT_RTGSAmount_Delivery);
        txtDPMake_Delivery = findViewById(R.id.txtDPMake_Delivery);
        txtDPManufectureYear_Delivery = findViewById(R.id.txtDPManufectureYear_Delivery);
        txtDPOldAmount_Delivery = findViewById(R.id.txtDPOldAmount_Delivery);
        txtDPModelName_Delivery = findViewById(R.id.txtDPModelName_Delivery);
        txtDPPaperExpense_Delivery = findViewById(R.id.txtDPPaperExpense_Delivery);
        txtDPOldTractorAmount_Delivery = findViewById(R.id.txtDPOldTractorAmount_Delivery);
        txtDPNoc_Delivery = findViewById(R.id.txtDPNoc_Delivery);


        lin_dp_cheque_Delivery = findViewById(R.id.lin_dp_cheque_Delivery);
        lin_dp_NEFT_RTGS_Delivery = findViewById(R.id.lin_dp_NEFT_RTGS_Delivery);
        lin_dp_NocPhoto_Delivery = findViewById(R.id.lin_dp_NocPhoto_Delivery);
        lin_dp_OldVehicle_Delivery = findViewById(R.id.lin_dp_OldVehicle_Delivery);
        lin_dp_Rcbook_Delivery = findViewById(R.id.lin_dp_Rcbook_Delivery);
        lin_dp_Election_Delivery = findViewById(R.id.lin_dp_Election_Delivery);

        lin_dp_Rcbook_Delivery2 = findViewById(R.id.lin_dp_Rcbook_Delivery2);
        lin_dp_Election_Delivery2 = findViewById(R.id.lin_dp_Election_Delivery2);

        //Consumer Skim
        edt_CS_Hood_Delivery = findViewById(R.id.edt_CS_Hood_Delivery);
        edt_CS_TopLink_Delivery = findViewById(R.id.edt_CS_TopLink_Delivery);
        edt_CS_DrawBar_Delivery = findViewById(R.id.edt_CS_DrawBar_Delivery);
        edt_CS_ToolKit_Delivery = findViewById(R.id.edt_CS_ToolKit_Delivery);
        edt_CS_Bumper_Delivery = findViewById(R.id.edt_CS_Bumper_Delivery);
        edt_CS_Hitch_Delivery = findViewById(R.id.edt_CS_Hitch_Delivery);
        edt_CS_Description_Delivery = findViewById(R.id.edt_CS_Description_Delivery);
        edt_CS_ConsumerSkim_Delivery = findViewById(R.id.edt_CS_ConsumerSkim_Delivery);


        txt_cs_Hood = findViewById(R.id.txt_cs_Hood);
        txt_cs_TopLink = findViewById(R.id.txt_cs_TopLink);
        txt_cs_Drawbar = findViewById(R.id.txt_cs_Drawbar);
        txt_cs_ToolKit = findViewById(R.id.txt_cs_ToolKit);
        txt_cs_Bumper = findViewById(R.id.txt_cs_Bumper);
        txt_cs_Hitch = findViewById(R.id.txt_cs_Hitch);
        txt_cs_Description = findViewById(R.id.txt_cs_Description);

        //Loan Detail Form
        edt_DeliveryDetail_REF_Delivery = findViewById(R.id.edt_DeliveryDetail_REF_Delivery);
        edt_DeliveryDetail_FinanceForm_Delivery = findViewById(R.id.edt_DeliveryDetail_FinanceForm_Delivery);
        edt_DeliveryDetail_LoanAmount_Delivery = findViewById(R.id.edt_DeliveryDetail_LoanAmount_Delivery);
        edt_DeliveryDetail_LoanSancAmont_Delivery = findViewById(R.id.edt_DeliveryDetail_LoanSancAmont_Delivery);
        edt_DeliveryDetail_LoanCharge_Delivery = findViewById(R.id.edt_DeliveryDetail_LoanCharge_Delivery);
        edt_DeliveryDetail_LandDetail_Delivery = findViewById(R.id.edt_DeliveryDetail_LandDetail_Delivery);
        edt_DeliveryDetail_CibilScore_Delivery = findViewById(R.id.edt_DeliveryDetail_CibilScore_Delivery);
        edt_DeliveryDetail_FiDate_Delivery = findViewById(R.id.edt_DeliveryDetail_FiDate_Delivery);
        edt_DeliveryDetail_SanctionDate_Delivery = findViewById(R.id.edt_DeliveryDetail_SanctionDate_Delivery);

        /*spn_DeliveryDetail_FinanceForm_Delivery= findViewById(R.id.spn_DeliveryDetail_FinanceForm_Delivery);
        spn_DeliveryDetail_Stage_Delivery= findViewById(R.id.spn_DeliveryDetail_Stage_Delivery);*/

        edt_DeliveryDetail_Stage_Delivery = findViewById(R.id.edt_DeliveryDetail_Stage_Delivery);


        spn_delivery_stageloan = findViewById(R.id.spn_delivery_stageloan);

        img_LoanDetail_BankpassBook_Delivery = findViewById(R.id.img_LoanDetail_BankpassBook_Delivery);
        img_LoanDetail_Cheque_Delivery = findViewById(R.id.img_LoanDetail_Cheque_Delivery);
        img_LoanDetail_SarpanchID_Delivery = findViewById(R.id.img_LoanDetail_SarpanchID_Delivery);
        img_LoanDetail_SignatureVerifi_Delivery = findViewById(R.id.img_LoanDetail_SignatureVerifi_Delivery);

        img_LoanDetail_BankpassBook_Delivery2 = findViewById(R.id.img_LoanDetail_BankpassBook_Delivery2);
        txt_LoanDetail_BankPassBook_Delivery2 = findViewById(R.id.txt_LoanDetail_BankPassBook_Delivery2);

        txt_LoanDetail_BankPassBook_Delivery = findViewById(R.id.txt_LoanDetail_BankPassBook_Delivery);
        txt_LoanDetail_Cheque_Delivery = findViewById(R.id.txt_LoanDetail_Cheque_Delivery);
        txt_LoanDetail_SarpanchId_Delivery = findViewById(R.id.txt_LoanDetail_SarpanchId_Delivery);
        txt_LoanDetail_SignatureVeri_Delivery = findViewById(R.id.txt_LoanDetail_SignatureVeri_Delivery);


        //Delivery Details
     /*   edt_modelName_Delivery = findViewById(R.id.edt_modelName_Delivery);
        edt_ChesisNumber_Delivery = findViewById(R.id.edt_ChesisNumber_Delivery);
        edt_EngineNumber_Delivery = findViewById(R.id.edt_EngineNumber_Delivery);
        edt_Variente_Delivery = findViewById(R.id.edt_Variente_Delivery);*/

        edt_Type_Delivery = findViewById(R.id.edt_Type_Delivery);
        edt_Bettry_Delivery = findViewById(R.id.edt_Bettry_Delivery);
        edt_DeliveryDate_Delivery = findViewById(R.id.edt_DeliveryDate_Delivery);

        deliveryTyre = findViewById(R.id.deliveryTyre);
        deliveryBttery = findViewById(R.id.deliveryBttery);


        img_DeliveryPhoto_Delivery = findViewById(R.id.img_DeliveryPhoto_Delivery);
        img_ChesisPrint_Delivery = findViewById(R.id.img_ChesisPrint_Delivery);

        txt_DeliveryPhoto_Delivery = findViewById(R.id.txt_DeliveryPhoto_Delivery);
        txt_ChesisPrint_Delivery = findViewById(R.id.txt_ChesisPrint_Delivery);

        lin_LoanDetail_Delivery = findViewById(R.id.lin_LoanDetail_Delivery);
        lin_cashLoan_Delivery = findViewById(R.id.lin_cashLoan_Delivery);

        edt_CashDetail_Date_Delivery = findViewById(R.id.edt_CashDetail_Date_Delivery);
        edt_CashDetail_Amount_Delivery = findViewById(R.id.edt_CashDetail_Amount_Delivery);
        edt_CashDetail_Description_Delivery = findViewById(R.id.edt_CashDetail_Description_Delivery);


        btn_Delivery_Submit = findViewById(R.id.btn_Delivery_Submit);

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


        progressDialog = new ProgressDialog(DeliveryFormActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getDeliveryDataDisplay(emp).enqueue(new Callback<DeliveryDataDisplayModel>() {
            @Override
            public void onResponse(Call<DeliveryDataDisplayModel> call, Response<DeliveryDataDisplayModel> response) {

                progressDialog.dismiss();

                if (response.body().getData().get(id_pos).getStage()==null){
                    StageCheck = "";
                }else {
                    StageCheck = response.body().getData().get(id_pos).getStage();
                }

               /* Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getB_photo()).
                        into(img_CD_Booking_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getAdhar_photo())
                        .into(img_CD_AdharCard_Delivery);


                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getEcard_photo())
                        .into(img_CD_ElectionCard_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getP_photo())
                        .into(img_CD_PassportSize_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getCheck1_photo())
                        .into(img_DownP_Cheque_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getNeft_rtgs_photo())
                        .into(img_DownP_NEFT_RTGS_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getNoc_photo())
                        .into(img_DownP_NocPhoto_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getOld_vehicle_photo())
                        .into(img_DownP_OldVehicle_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getRecbook_photo())
                        .into(img_DownP_RcBook_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getEc_photo())
                        .into(img_DownP_ElectionPhoto_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getLb_pb_photo())
                        .into(img_LoanDetail_BankpassBook_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getL_c_photo())
                        .into(img_LoanDetail_Cheque_Delivery);


                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getSar_id_photo())
                        .into(img_LoanDetail_SarpanchID_Delivery);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getSign_veri())
                        .into(img_LoanDetail_SignatureVerifi_Delivery);

                //--------------------------------------------------------------------

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getAdhar_back())
                        .into(img_CD_AdharCard_Delivery2);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getElection_back())
                        .into(img_CD_ElectionCard_Delivery2);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getP_photo_back())
                        .into(img_CD_PassportSize_Delivery2);


                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getRcbook_back())
                        .into(img_DownP_RcBook_Delivery2);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getElec_back())
                        .into(img_DownP_ElectionPhoto_Delivery2);

                Picasso.get()
                        .load("http://asworldtech.com/sonalika/booking/"
                                +response.body().getData().get(id_pos).getB_pass_back())
                        .into(img_LoanDetail_BankpassBook_Delivery2);*/


                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getB_photo())
                        .into(img_CD_Booking_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getAdhar_photo())
                        .into(img_CD_AdharCard_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getEcard_photo())
                        .into(img_CD_ElectionCard_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getP_photo())
                        .into(img_CD_PassportSize_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getCheck1_photo())
                        .into(img_DownP_Cheque_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getNeft_rtgs_photo())
                        .into(img_DownP_NEFT_RTGS_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getNoc_photo())
                        .into(img_DownP_NocPhoto_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getOld_vehicle_photo())
                        .into(img_DownP_OldVehicle_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getRecbook_photo())
                        .into(img_DownP_RcBook_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getEc_photo())
                        .into(img_DownP_ElectionPhoto_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getLb_pb_photo())
                        .into(img_LoanDetail_BankpassBook_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getL_c_photo())
                        .into(img_LoanDetail_Cheque_Delivery);


                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getSar_id_photo())
                        .into(img_LoanDetail_SarpanchID_Delivery);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getSign_veri())
                        .into(img_LoanDetail_SignatureVerifi_Delivery);

                //-----------------------------------------------------------------------
                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getAdhar_back())
                        .into(img_CD_AdharCard_Delivery2);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getElection_back())
                        .into(img_CD_ElectionCard_Delivery2);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getP_photo_back())
                        .into(img_CD_PassportSize_Delivery2);


                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getRcbook_back())
                        .into(img_DownP_RcBook_Delivery2);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getElec_back())
                        .into(img_DownP_ElectionPhoto_Delivery2);

                Glide.with(DeliveryFormActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"
                                + response.body().getData().get(id_pos).getB_pass_back())
                        .into(img_LoanDetail_BankpassBook_Delivery2);

                //-----------------------------------------------------------

                emp_id = response.body().getData().get(id_pos).getEmp_id();


                if (response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                        response.body().getData().get(id_pos).getProduct_name().equals("Spar part")) {
                    edt_ADBooking_BookingType_Delivery.setVisibility(View.GONE);
                    edt_CD_PassBook_Delivery.setVisibility(View.GONE);
                    edt_CD_ChequeBook_Delivery.setVisibility(View.GONE);
                    txtFillBookingType_Delivery.setVisibility(View.GONE);
                    txtPassBookBook_Delivery.setVisibility(View.GONE);
                    txtChequeBook_Delivery.setVisibility(View.GONE);


                    txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.GONE);
                    txt_CD_AdharCard_Delivery.setVisibility(View.GONE);
                    txt_CD_ElectionCard_Delivery.setVisibility(View.GONE);
                    txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                    txt_CD_AdharCard_Delivery2.setVisibility(View.GONE);
                    txt_CD_ElectionCard_Delivery2.setVisibility(View.GONE);
                    txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);

                    img_CD_Booking_Delivery.setVisibility(View.GONE);
                    img_CD_AdharCard_Delivery.setVisibility(View.GONE);
                    img_CD_ElectionCard_Delivery.setVisibility(View.GONE);
                    img_CD_PassportSize_Delivery.setVisibility(View.GONE);
                    img_CD_AdharCard_Delivery2.setVisibility(View.GONE);
                    img_CD_ElectionCard_Delivery2.setVisibility(View.GONE);
                    img_CD_PassportSize_Delivery2.setVisibility(View.GONE);

                    deliveryTyre.setVisibility(View.GONE);
                    edt_Type_Delivery.setVisibility(View.GONE);
                    deliveryBttery.setVisibility(View.GONE);
                    edt_Bettry_Delivery.setVisibility(View.GONE);

                } else {
                    edt_ADBooking_BookingType_Delivery.setVisibility(View.VISIBLE);
                    edt_CD_PassBook_Delivery.setVisibility(View.VISIBLE);
                    edt_CD_ChequeBook_Delivery.setVisibility(View.VISIBLE);
                    txtFillBookingType_Delivery.setVisibility(View.VISIBLE);
                    txtPassBookBook_Delivery.setVisibility(View.VISIBLE);
                    txtChequeBook_Delivery.setVisibility(View.VISIBLE);

                    deliveryTyre.setVisibility(View.VISIBLE);
                    edt_Type_Delivery.setVisibility(View.VISIBLE);
                    deliveryBttery.setVisibility(View.VISIBLE);
                    edt_Bettry_Delivery.setVisibility(View.VISIBLE);

                    deliveryTyre.setVisibility(View.GONE);
                    edt_Type_Delivery.setVisibility(View.GONE);
                    deliveryBttery.setVisibility(View.GONE);
                    edt_Bettry_Delivery.setVisibility(View.GONE);
                    Log.d("TAG", "onResponse: CheckBattery ");


                    if (response.body().getData().get(id_pos).getB_photo_check().equals("0")) {
                        txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")) {
                        txt_CD_AdharCard_Delivery.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_AdharCard_Delivery.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getEcard_photo_check().equals("0")) {
                        txt_CD_ElectionCard_Delivery.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_ElectionCard_Delivery.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        txt_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getAdhar_back_check().equals("0")) {
                        txt_CD_AdharCard_Delivery2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_AdharCard_Delivery2.setVisibility(View.GONE);
                    }


                    if (response.body().getData().get(id_pos).getElection_back_check().equals("0")) {
                        txt_CD_ElectionCard_Delivery2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_ElectionCard_Delivery2.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        txt_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                    }

                }


                if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                    txt_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);
                    img_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);

                    txt_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);
                    img_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);

                    if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                        txt_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                    }

                    if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                        txt_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);
                    } else {
                        txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                    }

                } else {
                    txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                    img_CD_PassportSize_Delivery.setVisibility(View.GONE);

                    txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                    img_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                }


                //---------------------------------------------------------------------------------------
                if (response.body().getData().get(id_pos).getBno_check().equals("0")) {
                    edt_ADBooking_BookingNo_Delivery.setFocusable(true);
                } else {
                    edt_ADBooking_BookingNo_Delivery.setFocusable(false);
                    edt_ADBooking_BookingNo_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getB_date_check().equals("0")) {
                    edt_ADBooking_BookingDate_Delivery.setFocusable(true);
                } else {
                    edt_ADBooking_BookingDate_Delivery.setFocusable(false);
                    edt_ADBooking_BookingDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getB_date_check().equals("0")) {
                    edt_ADBooking_BookingDate_Delivery.setFocusable(true);
                } else {
                    edt_ADBooking_BookingDate_Delivery.setFocusable(false);
                    edt_ADBooking_BookingDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getB_type_check().equals("0")) {
                    edt_ADBooking_BookingType_Delivery.setFocusable(true);
                } else {
                    edt_ADBooking_BookingType_Delivery.setFocusable(false);
                    edt_ADBooking_BookingType_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                    edt_ADBooking_BookingLoginName_Delivery.setFocusable(true);
                } else {
                    edt_ADBooking_BookingLoginName_Delivery.setFocusable(false);
                    edt_ADBooking_BookingLoginName_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getFname_check().equals("0")) {
                    edt_CD_Fname_Delivery.setFocusable(true);
                } else {
                    edt_CD_Fname_Delivery.setFocusable(false);
                    edt_CD_Fname_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getLname_check().equals("0")) {
                    edt_CD_LastName_Delivery.setFocusable(true);
                } else {
                    edt_CD_LastName_Delivery.setFocusable(false);
                    edt_CD_LastName_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getSname_check().equals("0")) {
                    edt_CD_Surname_Delivery.setFocusable(true);
                } else {
                    edt_CD_Surname_Delivery.setFocusable(false);
                    edt_CD_Surname_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getMobileno_check().equals("0")) {
                    edt_CD_MobileNo_Delivery.setFocusable(true);
                } else {
                    edt_CD_MobileNo_Delivery.setFocusable(false);
                    edt_CD_MobileNo_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getWhno_check().equals("0")) {
                    edt_CD_WhatsappNo_Delivery.setFocusable(true);
                } else {
                    edt_CD_WhatsappNo_Delivery.setFocusable(false);
                    edt_CD_WhatsappNo_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getRef_name_check().equals("0")) {
                    edt_CD_ReferenceName_Delivery.setFocusable(true);
                } else {
                    edt_CD_ReferenceName_Delivery.setFocusable(false);
                    edt_CD_ReferenceName_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getRef_no_check().equals("0")) {
                    edt_CD_ReferenceNo_Delivery.setFocusable(true);
                } else {
                    edt_CD_ReferenceNo_Delivery.setFocusable(false);
                    edt_CD_ReferenceNo_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getState_check().equals("0")) {
                    edt_CD_State_Delivery.setFocusable(true);
                } else {
                    edt_CD_State_Delivery.setFocusable(false);
                    edt_CD_State_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCity_check().equals("0")) {
                    edt_CD_City_Delivery.setFocusable(true);
                } else {
                    edt_CD_City_Delivery.setFocusable(false);
                    edt_CD_City_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDistric_check().equals("0")) {
                    edt_CD_District_Delivery.setFocusable(true);
                } else {
                    edt_CD_District_Delivery.setFocusable(false);
                    edt_CD_District_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getVillage_check().equals("0")) {
                    edt_CD_Village_Delivery.setFocusable(true);
                } else {
                    edt_CD_Village_Delivery.setFocusable(false);
                    edt_CD_Village_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                    edt_CD_EmployeName_Delivery.setFocusable(true);
                } else {
                    edt_CD_EmployeName_Delivery.setFocusable(false);
                    edt_CD_EmployeName_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getTehsill_check().equals("0")) {
                    edt_CD_Taluko_Delivery.setFocusable(true);
                } else {
                    edt_CD_Taluko_Delivery.setFocusable(false);
                    edt_CD_Taluko_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getProduct_name_check().equals("0")) {
                    edt_PD_PurchaseName_Delivery.setFocusable(true);
                } else {
                    edt_PD_PurchaseName_Delivery.setFocusable(false);
                    edt_PD_PurchaseName_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getModel_name_check().equals("0")) {
                    edt_PD_ModelName_Delivery.setFocusable(true);
                } else {
                    edt_PD_ModelName_Delivery.setFocusable(false);
                    edt_PD_ModelName_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getP_desc_check().equals("0")) {
                    edt_PD_Description_Delivery.setFocusable(true);
                } else {
                    edt_PD_Description_Delivery.setFocusable(false);
                    edt_PD_Description_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDeal_price_check().equals("0")) {
                    edt_PriceD_price_Delivery.setFocusable(true);
                } else {
                    edt_PriceD_price_Delivery.setFocusable(false);
                    edt_PriceD_price_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")) {
                    edt_PriceD_priceInWord_Delivery.setFocusable(true);
                } else {
                    edt_PriceD_priceInWord_Delivery.setFocusable(false);
                    edt_PriceD_priceInWord_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getGst_check().equals("0")) {
                    edt_PriceD_GST_Delivery.setFocusable(true);
                } else {
                    edt_PriceD_GST_Delivery.setFocusable(false);
                    edt_PriceD_GST_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getRto_check().equals("0")) {
                    edt_RTO_RtoMain_Delivery.setFocusable(true);
                } else {
                    edt_RTO_RtoMain_Delivery.setFocusable(false);
                    edt_RTO_RtoMain_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getRto_tax_check().equals("0")) {
                    edt_RTO_RtoTax_Delivery.setFocusable(true);
                } else {
                    edt_RTO_RtoTax_Delivery.setFocusable(false);
                    edt_RTO_RtoTax_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getRto_passing_check().equals("0")) {
                    edt_RTO_RtoPassing_Delivery.setFocusable(true);
                } else {
                    edt_RTO_RtoPassing_Delivery.setFocusable(false);
                    edt_RTO_RtoPassing_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getInsurance_check().equals("0")) {
                    edt_RTO_Insurance_Delivery.setFocusable(true);
                } else {
                    edt_RTO_Insurance_Delivery.setFocusable(false);
                    edt_RTO_Insurance_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getAgent_fee_check().equals("0")) {
                    edt_RTO_AgentFees_Delivery.setFocusable(true);
                } else {
                    edt_RTO_AgentFees_Delivery.setFocusable(false);
                    edt_RTO_AgentFees_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getNumber_plat_check().equals("0")) {
                    edt_RTO_NumberPlat_Delivery.setFocusable(true);
                } else {
                    edt_RTO_NumberPlat_Delivery.setFocusable(false);
                    edt_RTO_NumberPlat_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getLoan_charge_check().equals("0")) {
                    edt_RTO_LoanCharge_Delivery.setFocusable(true);
                } else {
                    edt_RTO_LoanCharge_Delivery.setFocusable(false);
                    edt_RTO_LoanCharge_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getBooking_amt_check().equals("0")) {
                    edt_DownP_BookingAmount_Delivery.setFocusable(true);
                } else {
                    edt_DownP_BookingAmount_Delivery.setFocusable(false);
                    edt_DownP_BookingAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getAmount_check().equals("0")) {
                    edt_DownP_CashAmount_Delivery.setFocusable(true);
                } else {
                    edt_DownP_CashAmount_Delivery.setFocusable(false);
                    edt_DownP_CashAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCheck_neft_rtgs_check().equals("0")) {
                    edt_DownP_BankOption_Delivery.setFocusable(false);
                } else {
                    edt_DownP_BankOption_Delivery.setFocusable(false);
                    edt_DownP_BankOption_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCheck_date_check().equals("0")) {
                    edt_DownP_ChequeDate_Delivery.setFocusable(true);
                } else {
                    edt_DownP_ChequeDate_Delivery.setFocusable(false);
                    edt_DownP_ChequeDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                    ;
                }

                if (response.body().getData().get(id_pos).getC_amount_check().equals("0")) {
                    edt_DownP_ChequeAmount_Delivery.setFocusable(true);
                } else {
                    edt_DownP_ChequeAmount_Delivery.setFocusable(false);
                    edt_DownP_ChequeAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")) {
                    edt_DownP_NEFT_RTGS_date_Delivery.setFocusable(true);
                } else {
                    edt_DownP_NEFT_RTGS_date_Delivery.setFocusable(false);
                    edt_DownP_NEFT_RTGS_date_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")) {
                    edt_DownP_NEFT_RTGSAmount_Delivery.setFocusable(true);
                } else {
                    edt_DownP_NEFT_RTGSAmount_Delivery.setFocusable(false);
                    edt_DownP_NEFT_RTGSAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getMake_check().equals("0")) {
                    edt_DownP_SelectMake_Delivery.setFocusable(true);
                } else {
                    edt_DownP_SelectMake_Delivery.setFocusable(false);
                    edt_DownP_SelectMake_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getModel_check().equals("0")) {
                    edt_DownP_ModelVehicle_Delivery.setFocusable(true);
                } else {
                    edt_DownP_ModelVehicle_Delivery.setFocusable(false);
                    edt_DownP_ModelVehicle_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                    edt_DownP_oldAmount_Delivery.setFocusable(true);
                } else {
                    edt_DownP_oldAmount_Delivery.setFocusable(false);
                    edt_DownP_oldAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getM_year_check().equals("0")) {
                    edt_DownP_ManufactureYear_Delivery.setFocusable(true);
                } else {
                    edt_DownP_ManufactureYear_Delivery.setFocusable(false);
                    edt_DownP_ManufactureYear_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getPaper_expence_check().equals("0")) {
                    edt_DownP_PaperExchange_Delivery.setFocusable(true);
                } else {
                    edt_DownP_PaperExchange_Delivery.setFocusable(false);
                    edt_DownP_PaperExchange_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                    edt_DownP_oldTractorAmount_Delivery.setFocusable(true);
                } else {
                    edt_DownP_oldTractorAmount_Delivery.setFocusable(false);
                    edt_DownP_oldTractorAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getNoc_check().equals("0")) {
                    edt_DownP_NOC_Delivery.setFocusable(true);
                } else {
                    edt_DownP_NOC_Delivery.setFocusable(false);
                    edt_DownP_NOC_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getHood_check().equals("0")) {
                    edt_CS_Hood_Delivery.setFocusable(true);
                } else {
                    edt_CS_Hood_Delivery.setFocusable(false);
                    edt_CS_Hood_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getToplink_check().equals("0")) {
                    edt_CS_TopLink_Delivery.setFocusable(true);
                } else {
                    edt_CS_TopLink_Delivery.setFocusable(false);
                    edt_CS_TopLink_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDrowbar_check().equals("0")) {
                    edt_CS_DrawBar_Delivery.setFocusable(true);
                } else {
                    edt_CS_DrawBar_Delivery.setFocusable(false);
                    edt_CS_DrawBar_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getToolkit_check().equals("0")) {
                    edt_CS_ToolKit_Delivery.setFocusable(true);
                } else {
                    edt_CS_ToolKit_Delivery.setFocusable(false);
                    edt_CS_ToolKit_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getBumper_check().equals("0")) {
                    edt_CS_Bumper_Delivery.setFocusable(true);
                } else {
                    edt_CS_Bumper_Delivery.setFocusable(false);
                    edt_CS_Bumper_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getHitch_check().equals("0")) {
                    edt_CS_Hitch_Delivery.setFocusable(true);
                } else {
                    edt_CS_Hitch_Delivery.setFocusable(false);
                    edt_CS_Hitch_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getDescription_check().equals("0")) {
                    edt_CS_Description_Delivery.setFocusable(true);
                } else {
                    edt_CS_Description_Delivery.setFocusable(false);
                    edt_CS_Description_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getB_p_photo_check().equals("0")) {
                    edt_CD_PassBook_Delivery.setFocusable(true);
                } else {
                    edt_CD_PassBook_Delivery.setFocusable(false);
                    edt_CD_PassBook_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCheck_photo_check().equals("0")) {
                    edt_CD_ChequeBook_Delivery.setFocusable(true);
                } else {
                    edt_CD_ChequeBook_Delivery.setFocusable(false);
                    edt_CD_ChequeBook_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getR_e_name_check().equals("0")) {
                    edt_DeliveryDetail_REF_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_REF_Delivery.setFocusable(false);
                    edt_DeliveryDetail_REF_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getFinance_from_check().equals("0")) {
                    edt_DeliveryDetail_FinanceForm_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_FinanceForm_Delivery.setFocusable(false);
                    edt_DeliveryDetail_FinanceForm_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getLoan_amount_check().equals("0")) {
                    edt_DeliveryDetail_LoanAmount_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_LoanAmount_Delivery.setFocusable(false);
                    edt_DeliveryDetail_LoanAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")) {
                    edt_DeliveryDetail_LoanSancAmont_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_LoanSancAmont_Delivery.setFocusable(false);
                    edt_DeliveryDetail_LoanSancAmont_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getLloan_charge_check().equals("0")) {
                    edt_DeliveryDetail_LoanCharge_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_LoanCharge_Delivery.setFocusable(false);
                    edt_DeliveryDetail_LoanCharge_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getLand_details_check().equals("0")) {
                    edt_DeliveryDetail_LandDetail_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_LandDetail_Delivery.setFocusable(false);
                    edt_DeliveryDetail_LandDetail_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCibil_score_check().equals("0")) {
                    edt_DeliveryDetail_CibilScore_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_CibilScore_Delivery.setFocusable(false);
                    edt_DeliveryDetail_CibilScore_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getFi_date_check().equals("0")) {
                    edt_DeliveryDetail_FiDate_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_FiDate_Delivery.setFocusable(false);
                    edt_DeliveryDetail_FiDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getSectiondate_check().equals("0")) {
                    edt_DeliveryDetail_SanctionDate_Delivery.setFocusable(true);
                } else {
                    edt_DeliveryDetail_SanctionDate_Delivery.setFocusable(false);
                    edt_DeliveryDetail_SanctionDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getStage_check().equals("0")) {
                    edt_DeliveryDetail_Stage_Delivery.setFocusable(true);
                    spn_delivery_stageloan.setVisibility(View.VISIBLE);
                    edt_DeliveryDetail_Stage_Delivery.setVisibility(View.GONE);
                } else {
                    spn_delivery_stageloan.setVisibility(View.GONE);
                    edt_DeliveryDetail_Stage_Delivery.setVisibility(View.VISIBLE);
                    edt_DeliveryDetail_Stage_Delivery.setFocusable(false);
                    edt_DeliveryDetail_Stage_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getSkim_check().equals("0")) {
                    edt_CS_ConsumerSkim_Delivery.setFocusable(true);
                } else {
                    edt_CS_ConsumerSkim_Delivery.setFocusable(false);
                    edt_CS_ConsumerSkim_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getAtype_check().equals("0")) {
                    edt_CD_PaymentOption_Delivery.setFocusable(true);
                } else {
                    edt_CD_PaymentOption_Delivery.setFocusable(false);
                    edt_CD_PaymentOption_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                if (response.body().getData().get(id_pos).getCash_date_check().equals("0")) {
                    edt_CashDetail_Date_Delivery.setFocusable(true);
                } else {
                    edt_CashDetail_Date_Delivery.setFocusable(false);
                    edt_CashDetail_Date_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCash_amount_check().equals("0")) {
                    edt_CashDetail_Amount_Delivery.setFocusable(true);
                } else {
                    edt_CashDetail_Amount_Delivery.setFocusable(false);
                    edt_CashDetail_Amount_Delivery.setTextColor(Color.parseColor("#43a047"));
                }

                if (response.body().getData().get(id_pos).getCash_description_check().equals("0")) {
                    edt_CashDetail_Description_Delivery.setFocusable(true);
                } else {
                    edt_CashDetail_Description_Delivery.setFocusable(false);
                    edt_CashDetail_Description_Delivery.setTextColor(Color.parseColor("#43a047"));
                }


                /** ************************************************************************************* */

                /*if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                    txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                    txt_CD_AdharCard_Delivery.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_AdharCard_Delivery.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                    txt_CD_ElectionCard_Delivery.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_ElectionCard_Delivery.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                    txt_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                }*/

                if (response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")) {
                    txt_DownP_UploadCheque_Delivery.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadCheque_Delivery.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")) {
                    txt_DownP_UploadNEFT_RTGS_Delivery.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadNEFT_RTGS_Delivery.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getNoc_photo_check().equals("0")) {
                    txt_DownP_UploadNocPhoto_Delivery.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadNocPhoto_Delivery.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")) {
                    txt_DownP_UploadOldVehicle_Delivery.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadOldVehicle_Delivery.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")) {
                    txt_DownP_UploadRCBook_Delivery.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadRCBook_Delivery.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getEc_photo_check().equals("0")) {
                    txt_DownP_UploadElectionPhoto_Delivery.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadElectionPhoto_Delivery.setVisibility(View.GONE);
                }

                //------------------------------------------------------------------------------


                /*if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                    txt_CD_AdharCard_Delivery2.setVisibility(View.VISIBLE);

                }else{
                    txt_CD_AdharCard_Delivery2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getElection_back_check ().equals("0")){
                    txt_CD_ElectionCard_Delivery2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_ElectionCard_Delivery2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                    txt_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                }*/


                if (response.body().getData().get(id_pos).getRcbook_back_check().equals("0")) {
                    txt_DownP_UploadRCBook_Delivery2.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadRCBook_Delivery2.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getElec_back_check().equals("0")) {
                    txt_DownP_UploadElectionPhoto_Delivery2.setVisibility(View.VISIBLE);
                } else {
                    txt_DownP_UploadElectionPhoto_Delivery2.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                    txt_LoanDetail_BankPassBook_Delivery.setVisibility(View.VISIBLE);
                } else {
                    txt_LoanDetail_BankPassBook_Delivery.setVisibility(View.GONE);
                }

                if (response.body().getData().get(id_pos).getB_pass_back_check().equals("0")) {
                    txt_LoanDetail_BankPassBook_Delivery2.setVisibility(View.VISIBLE);
                } else {
                    txt_LoanDetail_BankPassBook_Delivery2.setVisibility(View.GONE);
                }

                /** ****************************************************************************** */

                // edt_DeliveryDetail_Stage_Delivery.setText(response.body().getData().get(id_pos).getStage());
                //     String[] Satge_data = {"Pending", "Fi Done","Login Pending","Login Done","CIBIL Check","sanction","Reject"};

                if (StageCheck.equals("Pending")) {
                    String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_delivery_stageloan.setAdapter(adapterStage);

                    spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }


                if (StageCheck.equals("Fi Done")) {
                    String[] Satge_data = {"Fi Done", "Pending", "Login Pending",
                            "Login Done", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_delivery_stageloan.setAdapter(adapterStage);

                    spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (StageCheck.equals("Login Pending")) {
                    String[] Satge_data = {"Login Pending", "Pending", "Fi Done", "Login Done", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_delivery_stageloan.setAdapter(adapterStage);

                    spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (StageCheck.equals("Login Done")) {
                    String[] Satge_data = {"Login Done", "Pending", "Fi Done", "Login Pending", "CIBIL Check", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_delivery_stageloan.setAdapter(adapterStage);

                    spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (StageCheck.equals("CIBIL Check")) {
                    String[] Satge_data = {"CIBIL Check", "Pending", "Fi Done", "Login Pending", "Login Done", "sanction", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_delivery_stageloan.setAdapter(adapterStage);

                    spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (StageCheck.equals("sanction")) {
                    String[] Satge_data = {"sanction", "Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "Reject"};
                    ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_delivery_stageloan.setAdapter(adapterStage);

                    spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }


                if (StageCheck.equals("Reject")) {
                    String[] Satge_data = {"Reject", "Pending", "Fi Done", "Login Pending", "Login Done",
                            "CIBIL Check", "sanction"};
                    ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, Satge_data);
                    spn_delivery_stageloan.setAdapter(adapterStage);

                    spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Stage = Satge_data[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }


               /* ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, Satge_data);
                spn_delivery_stageloan.setAdapter(adapterStage);

                spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Stage = Satge_data[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*/

                /** ********************************************************************************** */
                String data = response.body().getData().get(id_pos).getBooking_amt();//"cash,bank"
                //  Toast.makeText(BookingPhaseOneActivity.this, ""+data, Toast.LENGTH_SHORT).show();
                String[] res = data.split(",");

                txtDPCashAmount_Delivery.setVisibility(View.GONE);
                edt_DownP_CashAmount_Delivery.setVisibility(View.GONE);

                edt_DownP_BankOption_Delivery.setVisibility(View.GONE);
                edt_DownP_ChequeDate_Delivery.setVisibility(View.GONE);
                edt_DownP_ChequeAmount_Delivery.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                edt_DownP_SelectMake_Delivery.setVisibility(View.GONE);
                edt_DownP_ModelVehicle_Delivery.setVisibility(View.GONE);
                edt_DownP_oldAmount_Delivery.setVisibility(View.GONE);
                edt_DownP_ManufactureYear_Delivery.setVisibility(View.GONE);
                edt_DownP_PaperExchange_Delivery.setVisibility(View.GONE);
                edt_DownP_oldTractorAmount_Delivery.setVisibility(View.GONE);
                edt_DownP_NOC_Delivery.setVisibility(View.GONE);

                lin_dp_cheque_Delivery.setVisibility(View.GONE);
                lin_dp_NEFT_RTGS_Delivery.setVisibility(View.GONE);
                lin_dp_NocPhoto_Delivery.setVisibility(View.GONE);
                lin_dp_OldVehicle_Delivery.setVisibility(View.GONE);
                lin_dp_Rcbook_Delivery.setVisibility(View.GONE);
                lin_dp_Election_Delivery.setVisibility(View.GONE);

                lin_dp_Rcbook_Delivery2.setVisibility(View.GONE);
                lin_dp_Election_Delivery2.setVisibility(View.GONE);


                txtDPBankOption_Delivery.setVisibility(View.GONE);
                txtDPChequeDate_Delivery.setVisibility(View.GONE);
                txtDPChequeAmount_Delivery.setVisibility(View.GONE);
                txtDPNEFT_RTGSdate_Delivery.setVisibility(View.GONE);
                txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                txtDPMake_Delivery.setVisibility(View.GONE);
                txtDPManufectureYear_Delivery.setVisibility(View.GONE);
                txtDPOldAmount_Delivery.setVisibility(View.GONE);
                txtDPModelName_Delivery.setVisibility(View.GONE);
                txtDPPaperExpense_Delivery.setVisibility(View.GONE);
                txtDPOldTractorAmount_Delivery.setVisibility(View.GONE);
                txtDPNoc_Delivery.setVisibility(View.GONE);


                for (int i = 0; i < res.length; i++) {
                    mydata = res[i];
                    // Toast.makeText(BookingPhaseOneActivity.this, "yes" + mydata, Toast.LENGTH_SHORT).show();

                    String uu = mydata.trim();
                    // Log.e("TAG", "onResponse: " + uu);
                    //   Log.d("TAG", "onResponse: "+mydata);

                    if (uu.equals("Cash")) {
                        txtDPCashAmount_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_CashAmount_Delivery.setVisibility(View.VISIBLE);

                        // Log.e("TAG", "onResponse:casting ");
                        // Toast.makeText(BookingPhaseOneActivity.this, "casting", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Old Vehicle")) {
                        // Log.e("TAG", "onResponse:casting45 ");
                        txtDPMake_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_SelectMake_Delivery.setVisibility(View.VISIBLE);
                        txtDPModelName_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_ModelVehicle_Delivery.setVisibility(View.VISIBLE);
                        txtDPManufectureYear_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_ManufactureYear_Delivery.setVisibility(View.VISIBLE);
                        txtDPOldAmount_Delivery.setVisibility(View.VISIBLE);
                        txtDPPaperExpense_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_PaperExchange_Delivery.setVisibility(View.VISIBLE);

                        if (response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")) {
                            // edt_DownP_oldAmount_Delivery.setVisibility(View.GONE);
                            edt_DownP_oldTractorAmount_Delivery.setVisibility(View.GONE);
                        } else {
                            // edt_DownP_oldAmount_Delivery.setVisibility(View.VISIBLE);
                            edt_DownP_oldTractorAmount_Delivery.setVisibility(View.VISIBLE);
                        }


                        if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                            lin_dp_NocPhoto_Delivery.setVisibility(View.GONE);
                        } else {
                            lin_dp_NocPhoto_Delivery.setVisibility(View.VISIBLE);
                        }

                        txtDPOldTractorAmount_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_oldAmount_Delivery.setVisibility(View.VISIBLE);
                        //  edt_DownP_oldTractorAmount_Delivery.setVisibility(View.VISIBLE);
                        txtDPNoc_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_NOC_Delivery.setVisibility(View.VISIBLE);

                        // lin_dp_NocPhoto_Delivery.setVisibility(View.VISIBLE);
                        lin_dp_OldVehicle_Delivery.setVisibility(View.VISIBLE);
                        lin_dp_Rcbook_Delivery.setVisibility(View.VISIBLE);
                        lin_dp_Election_Delivery.setVisibility(View.VISIBLE);

                        lin_dp_Rcbook_Delivery2.setVisibility(View.VISIBLE);
                        lin_dp_Election_Delivery2.setVisibility(View.VISIBLE);
                        //  Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Bank")) {
                        // Log.e("TAG", "onResponse:casting450 ");
                        // Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();

                        txtDPBankOption_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_BankOption_Delivery.setVisibility(View.VISIBLE);

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")) {
                            txtDPChequeDate_Delivery.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeDate_Delivery.setVisibility(View.VISIBLE);
                            txtDPChequeAmount_Delivery.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeAmount_Delivery.setVisibility(View.VISIBLE);
                            lin_dp_cheque_Delivery.setVisibility(View.VISIBLE);

                            txtDPNEFT_RTGSdate_Delivery.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS_Delivery.setVisibility(View.GONE);

                        }

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS")) {
                            txtDPNEFT_RTGSdate_Delivery.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.VISIBLE);
                            txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.VISIBLE);
                            lin_dp_NEFT_RTGS_Delivery.setVisibility(View.VISIBLE);

                            txtDPChequeDate_Delivery.setVisibility(View.GONE);
                            edt_DownP_ChequeDate_Delivery.setVisibility(View.GONE);
                            txtDPChequeAmount_Delivery.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount_Delivery.setVisibility(View.GONE);
                            lin_dp_cheque_Delivery.setVisibility(View.GONE);
                        }

                    }
                }



               /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Cash")){

                    txtDPCashAmount_Delivery.setVisibility(View.VISIBLE);
                    edt_DownP_CashAmount_Delivery.setVisibility(View.VISIBLE);

                    edt_DownP_BankOption_Delivery.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_Delivery.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_Delivery.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                    edt_DownP_SelectMake_Delivery.setVisibility(View.GONE);
                    edt_DownP_ModelVehicle_Delivery.setVisibility(View.GONE);
                    edt_DownP_oldAmount_Delivery.setVisibility(View.GONE);
                    edt_DownP_ManufactureYear_Delivery.setVisibility(View.GONE);
                    edt_DownP_PaperExchange_Delivery.setVisibility(View.GONE);
                    edt_DownP_oldTractorAmount_Delivery.setVisibility(View.GONE);
                    edt_DownP_NOC_Delivery.setVisibility(View.GONE);

                    lin_dp_cheque_Delivery.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_Delivery.setVisibility(View.GONE);
                    lin_dp_NocPhoto_Delivery.setVisibility(View.GONE);
                    lin_dp_OldVehicle_Delivery.setVisibility(View.GONE);
                    lin_dp_Rcbook_Delivery.setVisibility(View.GONE);
                    lin_dp_Election_Delivery.setVisibility(View.GONE);

                    lin_dp_Rcbook_Delivery2.setVisibility(View.GONE);
                    lin_dp_Election_Delivery2.setVisibility(View.GONE);


                    txtDPBankOption_Delivery.setVisibility(View.GONE);
                    txtDPChequeDate_Delivery.setVisibility(View.GONE);
                    txtDPChequeAmount_Delivery.setVisibility(View.GONE);
                    txtDPNEFT_RTGSdate_Delivery.setVisibility(View.GONE);
                    txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                    txtDPMake_Delivery.setVisibility(View.GONE);
                    txtDPManufectureYear_Delivery.setVisibility(View.GONE);
                    txtDPOldAmount_Delivery.setVisibility(View.GONE);
                    txtDPModelName_Delivery.setVisibility(View.GONE);
                    txtDPPaperExpense_Delivery.setVisibility(View.GONE);
                    txtDPOldTractorAmount_Delivery.setVisibility(View.GONE);
                    txtDPNoc_Delivery.setVisibility(View.GONE);
                }*/


               /* if(response.body().getData().get(id_pos).getBooking_amt().equals("Bank")){

                    txtDPCashAmount_Delivery.setVisibility(View.GONE);
                    edt_DownP_CashAmount_Delivery.setVisibility(View.GONE);

                    txtDPBankOption_Delivery.setVisibility(View.VISIBLE);
                    edt_DownP_BankOption_Delivery.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")){
                        txtDPChequeDate_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_ChequeDate_Delivery.setVisibility(View.VISIBLE);
                        txtDPChequeAmount_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_ChequeAmount_Delivery.setVisibility(View.VISIBLE);
                        lin_dp_cheque_Delivery.setVisibility(View.VISIBLE);

                        txtDPNEFT_RTGSdate_Delivery.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.GONE);
                        txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                        lin_dp_NEFT_RTGS_Delivery.setVisibility(View.GONE);

                        txtDPMake_Delivery.setVisibility(View.GONE);
                        edt_DownP_SelectMake_Delivery.setVisibility(View.GONE);
                        txtDPModelName_Delivery.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_Delivery.setVisibility(View.GONE);
                        txtDPManufectureYear_Delivery.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_Delivery.setVisibility(View.GONE);
                        txtDPOldAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_oldAmount_Delivery.setVisibility(View.GONE);
                        txtDPPaperExpense_Delivery.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_Delivery.setVisibility(View.GONE);
                        txtDPOldTractorAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_Delivery.setVisibility(View.GONE);
                        txtDPNoc_Delivery.setVisibility(View.GONE);
                        edt_DownP_NOC_Delivery.setVisibility(View.GONE);

                        lin_dp_NocPhoto_Delivery.setVisibility(View.GONE);
                        lin_dp_OldVehicle_Delivery.setVisibility(View.GONE);
                        lin_dp_Rcbook_Delivery.setVisibility(View.GONE);
                        lin_dp_Election_Delivery.setVisibility(View.GONE);
                        lin_dp_Rcbook_Delivery2.setVisibility(View.GONE);
                        lin_dp_Election_Delivery2.setVisibility(View.GONE);

                    }

                    if(response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS"))
                    {
                        txtDPNEFT_RTGSdate_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.VISIBLE);
                        txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.VISIBLE);
                        edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.VISIBLE);
                        lin_dp_NEFT_RTGS_Delivery.setVisibility(View.VISIBLE);

                        txtDPChequeDate_Delivery.setVisibility(View.GONE);
                        edt_DownP_ChequeDate_Delivery.setVisibility(View.GONE);
                        txtDPChequeAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_ChequeAmount_Delivery.setVisibility(View.GONE);
                        lin_dp_cheque_Delivery.setVisibility(View.GONE);

                        txtDPMake_Delivery.setVisibility(View.GONE);
                        edt_DownP_SelectMake_Delivery.setVisibility(View.GONE);
                        txtDPModelName_Delivery.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_Delivery.setVisibility(View.GONE);
                        txtDPManufectureYear_Delivery.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_Delivery.setVisibility(View.GONE);
                        txtDPOldAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_oldAmount_Delivery.setVisibility(View.GONE);
                        txtDPPaperExpense_Delivery.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_Delivery.setVisibility(View.GONE);
                        txtDPOldTractorAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_Delivery.setVisibility(View.GONE);
                        txtDPNoc_Delivery.setVisibility(View.GONE);
                        edt_DownP_NOC_Delivery.setVisibility(View.GONE);

                        lin_dp_NocPhoto_Delivery.setVisibility(View.GONE);
                        lin_dp_OldVehicle_Delivery.setVisibility(View.GONE);
                        lin_dp_Rcbook_Delivery.setVisibility(View.GONE);
                        lin_dp_Election_Delivery.setVisibility(View.GONE);
                        lin_dp_Rcbook_Delivery2.setVisibility(View.GONE);
                        lin_dp_Election_Delivery2.setVisibility(View.GONE);

                    }

                }*/

                //Old Vehicle
                /*if(response.body().getData().get(id_pos).getBooking_amt().equals("Old Vehicle")){

                    txtDPMake_Delivery.setVisibility(View.VISIBLE);
                    edt_DownP_SelectMake_Delivery.setVisibility(View.VISIBLE);
                    txtDPModelName_Delivery.setVisibility(View.VISIBLE);
                    edt_DownP_ModelVehicle_Delivery.setVisibility(View.VISIBLE);
                    txtDPManufectureYear_Delivery.setVisibility(View.VISIBLE);
                    edt_DownP_ManufactureYear_Delivery.setVisibility(View.VISIBLE);
                    txtDPOldAmount_Delivery.setVisibility(View.VISIBLE);
                    txtDPPaperExpense_Delivery.setVisibility(View.VISIBLE);
                    edt_DownP_PaperExchange_Delivery.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")){
                        edt_DownP_oldAmount_Delivery.setVisibility(View.GONE);
                    }
                    else {
                        edt_DownP_oldAmount_Delivery.setVisibility(View.VISIBLE);
                    }

                    txtDPOldTractorAmount_Delivery.setVisibility(View.VISIBLE);
                    edt_DownP_oldTractorAmount_Delivery.setVisibility(View.VISIBLE);
                    txtDPNoc_Delivery.setVisibility(View.VISIBLE);
                    edt_DownP_NOC_Delivery.setVisibility(View.VISIBLE);

                    lin_dp_NocPhoto_Delivery.setVisibility(View.VISIBLE);
                    lin_dp_OldVehicle_Delivery.setVisibility(View.VISIBLE);
                    lin_dp_Rcbook_Delivery.setVisibility(View.VISIBLE);
                    lin_dp_Election_Delivery.setVisibility(View.VISIBLE);

                    lin_dp_Rcbook_Delivery2.setVisibility(View.VISIBLE);
                    lin_dp_Election_Delivery2.setVisibility(View.VISIBLE);


                    txtDPCashAmount_Delivery.setVisibility(View.GONE);
                    edt_DownP_CashAmount_Delivery.setVisibility(View.GONE);
                    txtDPBankOption_Delivery.setVisibility(View.GONE);
                    edt_DownP_BankOption_Delivery.setVisibility(View.GONE);
                    txtDPChequeDate_Delivery.setVisibility(View.GONE);
                    edt_DownP_ChequeDate_Delivery.setVisibility(View.GONE);
                    txtDPChequeAmount_Delivery.setVisibility(View.GONE);
                    edt_DownP_ChequeAmount_Delivery.setVisibility(View.GONE);
                    lin_dp_cheque_Delivery.setVisibility(View.GONE);
                    txtDPNEFT_RTGSdate_Delivery.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.GONE);
                    txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                    edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                    lin_dp_NEFT_RTGS_Delivery.setVisibility(View.GONE);
                }*/


                if (response.body().getData().get(id_pos).getRto().equals("No")) {
                    edt_RTO_RtoTax_Delivery.setVisibility(View.GONE);
                    edt_RTO_RtoPassing_Delivery.setVisibility(View.GONE);
                    edt_RTO_Insurance_Delivery.setVisibility(View.GONE);
                    edt_RTO_AgentFees_Delivery.setVisibility(View.GONE);
                    edt_RTO_NumberPlat_Delivery.setVisibility(View.GONE);
                    edt_RTO_LoanCharge_Delivery.setVisibility(View.GONE);


                    txtRTOTax.setVisibility(View.GONE);
                    txtRTOPassing.setVisibility(View.GONE);
                    txtInsurance.setVisibility(View.GONE);
                    txtAgentFees.setVisibility(View.GONE);
                    txtNumberPlat.setVisibility(View.GONE);
                    txtLoanCharge.setVisibility(View.GONE);
                } else {
                    edt_RTO_RtoTax_Delivery.setVisibility(View.VISIBLE);
                    edt_RTO_RtoPassing_Delivery.setVisibility(View.VISIBLE);
                    edt_RTO_Insurance_Delivery.setVisibility(View.VISIBLE);
                    edt_RTO_AgentFees_Delivery.setVisibility(View.VISIBLE);
                    edt_RTO_NumberPlat_Delivery.setVisibility(View.VISIBLE);
                    edt_RTO_LoanCharge_Delivery.setVisibility(View.VISIBLE);

                    txtRTOTax.setVisibility(View.VISIBLE);
                    txtRTOPassing.setVisibility(View.VISIBLE);
                    txtInsurance.setVisibility(View.VISIBLE);
                    txtAgentFees.setVisibility(View.VISIBLE);
                    txtNumberPlat.setVisibility(View.VISIBLE);
                    txtLoanCharge.setVisibility(View.VISIBLE);
                }

                if (response.body().getData().get(id_pos).getSkim().equals("No")) {

                    edt_CS_Hood_Delivery.setVisibility(View.GONE);
                    edt_CS_TopLink_Delivery.setVisibility(View.GONE);
                    edt_CS_DrawBar_Delivery.setVisibility(View.GONE);
                    edt_CS_ToolKit_Delivery.setVisibility(View.GONE);
                    edt_CS_Bumper_Delivery.setVisibility(View.GONE);
                    edt_CS_Hitch_Delivery.setVisibility(View.GONE);
                    //  edt_CS_Description_Delivery.setVisibility(View.GONE);

                    txt_cs_Hood.setVisibility(View.GONE);
                    txt_cs_TopLink.setVisibility(View.GONE);
                    txt_cs_Drawbar.setVisibility(View.GONE);
                    txt_cs_ToolKit.setVisibility(View.GONE);
                    txt_cs_Bumper.setVisibility(View.GONE);
                    txt_cs_Hitch.setVisibility(View.GONE);
                    //  txt_cs_Description.setVisibility(View.GONE);

                } else {
                    edt_CS_Hood_Delivery.setVisibility(View.VISIBLE);
                    edt_CS_TopLink_Delivery.setVisibility(View.VISIBLE);
                    edt_CS_DrawBar_Delivery.setVisibility(View.VISIBLE);
                    edt_CS_ToolKit_Delivery.setVisibility(View.VISIBLE);
                    edt_CS_Bumper_Delivery.setVisibility(View.VISIBLE);
                    edt_CS_Hitch_Delivery.setVisibility(View.VISIBLE);
                    //  edt_CS_Description_Delivery.setVisibility(View.VISIBLE);

                    txt_cs_Hood.setVisibility(View.VISIBLE);
                    txt_cs_TopLink.setVisibility(View.VISIBLE);
                    txt_cs_Drawbar.setVisibility(View.VISIBLE);
                    txt_cs_ToolKit.setVisibility(View.VISIBLE);
                    txt_cs_Bumper.setVisibility(View.VISIBLE);
                    txt_cs_Hitch.setVisibility(View.VISIBLE);
                    //  txt_cs_Description.setVisibility(View.VISIBLE);
                }

               /* if(response.body().getData().get(id_pos).getAtype().equals("Loan")){
                    lin_LoanDetail_Delivery.setVisibility(View.VISIBLE);
                    lin_cashLoan_Delivery.setVisibility(View.GONE);
                }
                else {
                    lin_LoanDetail_Delivery.setVisibility(View.GONE);
                    lin_cashLoan_Delivery.setVisibility(View.VISIBLE);
                }*/

                if (response.body().getData().get(id_pos).getAtype().equals("Loan")) {
                    lin_LoanDetail_Delivery.setVisibility(View.VISIBLE);
                    lin_cashLoan_Delivery.setVisibility(View.GONE);
                }


                if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {
                    lin_LoanDetail_Delivery.setVisibility(View.GONE);
                    lin_cashLoan_Delivery.setVisibility(View.VISIBLE);

                   // StageFinalVal = "  ";
                }

                if (response.body().getData().get(id_pos).getAtype().equals("Cash-Loan")) {
                    lin_LoanDetail_Delivery.setVisibility(View.VISIBLE);
                    lin_cashLoan_Delivery.setVisibility(View.VISIBLE);
                }


                /* ********************************************************************* */

                if (response.body().getData().get(id_pos).getStage_check().equals("0")) {

                    StageFinalVal = Stage;


                } else {

                    StageFinalVal = edt_DeliveryDetail_Stage_Delivery.getText().toString();

                }

                if( StageFinalVal == null){
                    StageFinalVal=" ";
                }

               /* if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {

                    StageFinalVal = "  ";
                }*/

                /* ********************************************************************* */


                edt_ADBooking_BookingNo_Delivery.setText(response.body().getData().get(id_pos).getBno());
                edt_ADBooking_BookingType_Delivery.setText(response.body().getData().get(id_pos).getB_type());
                edt_ADBooking_BookingDate_Delivery.setText(response.body().getData().get(id_pos).getB_date());
                edt_ADBooking_BookingLoginName_Delivery.setText(response.body().getData().get(id_pos).getEmp());

                // Toast.makeText(DeliveryFormActivity.this, ""+id_item, Toast.LENGTH_SHORT).show();

               /* edt_ADBooking_BookingNo_Delivery.setText(response.body().getData().get(id_item).getBno());
                edt_ADBooking_BookingType_Delivery.setText(response.body().getData().get(id_item).getB_type());
                edt_ADBooking_BookingDate_Delivery.setText(response.body().getData().get(id_item).getB_date());
                edt_ADBooking_BookingLoginName_Delivery.setText(response.body().getData().get(id_item).getEmp());*/

                edt_CD_Fname_Delivery.setText(response.body().getData().get(id_pos).getFname());
                edt_CD_LastName_Delivery.setText(response.body().getData().get(id_pos).getLname() + " ");
                edt_CD_Surname_Delivery.setText(response.body().getData().get(id_pos).getSname());
                edt_CD_MobileNo_Delivery.setText(response.body().getData().get(id_pos).getMobileno());
                edt_CD_WhatsappNo_Delivery.setText(response.body().getData().get(id_pos).getWhno());
                edt_CD_ReferenceName_Delivery.setText(response.body().getData().get(id_pos).getRef_name());
                edt_CD_ReferenceNo_Delivery.setText(response.body().getData().get(id_pos).getRef_no());
                edt_CD_State_Delivery.setText(response.body().getData().get(id_pos).getState());
                edt_CD_City_Delivery.setText(response.body().getData().get(id_pos).getCity());
                edt_CD_District_Delivery.setText(response.body().getData().get(id_pos).getDistric());
                edt_CD_Village_Delivery.setText(response.body().getData().get(id_pos).getVillage());
                edt_CD_EmployeName_Delivery.setText(response.body().getData().get(id_pos).getEmp());
                edt_CD_Taluko_Delivery.setText(response.body().getData().get(id_pos).getTehsill());
                edt_CD_PassBook_Delivery.setText(response.body().getData().get(id_pos).getB_p_photo());
                edt_CD_ChequeBook_Delivery.setText(response.body().getData().get(id_pos).getCheck_photo());
                edt_CD_PaymentOption_Delivery.setText(response.body().getData().get(id_pos).getAtype());

                edt_PD_PurchaseName_Delivery.setText(response.body().getData().get(id_pos).getProduct_name());
                edt_PD_ModelName_Delivery.setText(response.body().getData().get(id_pos).getModel_name());
                edt_PD_Description_Delivery.setText(response.body().getData().get(id_pos).getP_desc());

                edt_PriceD_price_Delivery.setText(response.body().getData().get(id_pos).getDeal_price());
                edt_PriceD_priceInWord_Delivery.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                edt_PriceD_GST_Delivery.setText(response.body().getData().get(id_pos).getGst());

                edt_RTO_RtoMain_Delivery.setText(response.body().getData().get(id_pos).getRto());
                edt_RTO_RtoTax_Delivery.setText(response.body().getData().get(id_pos).getRto_tax());
                edt_RTO_RtoPassing_Delivery.setText(response.body().getData().get(id_pos).getRto_passing());
                edt_RTO_Insurance_Delivery.setText(response.body().getData().get(id_pos).getInsurance());
                edt_RTO_AgentFees_Delivery.setText(response.body().getData().get(id_pos).getAgent_fee());
                edt_RTO_NumberPlat_Delivery.setText(response.body().getData().get(id_pos).getNumber_plat());
                edt_RTO_LoanCharge_Delivery.setText(response.body().getData().get(id_pos).getLoan_charge());

                edt_DownP_BookingAmount_Delivery.setText(response.body().getData().get(id_pos).getBooking_amt());
                edt_DownP_CashAmount_Delivery.setText(response.body().getData().get(id_pos).getAmount());
                edt_DownP_BankOption_Delivery.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                edt_DownP_ChequeDate_Delivery.setText(response.body().getData().get(id_pos).getCheck_date());
                edt_DownP_ChequeAmount_Delivery.setText(response.body().getData().get(id_pos).getCheck_amt());
                edt_DownP_NEFT_RTGS_date_Delivery.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                edt_DownP_NEFT_RTGSAmount_Delivery.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                edt_DownP_SelectMake_Delivery.setText(response.body().getData().get(id_pos).getMake());
                edt_DownP_ModelVehicle_Delivery.setText(response.body().getData().get(id_pos).getModel());
                edt_DownP_oldAmount_Delivery.setText(response.body().getData().get(id_pos).getOld_t_amount());
                edt_DownP_ManufactureYear_Delivery.setText(response.body().getData().get(id_pos).getM_year());
                edt_DownP_PaperExchange_Delivery.setText(response.body().getData().get(id_pos).getPaper_expence());
                edt_DownP_oldTractorAmount_Delivery.setText(response.body().getData().get(id_pos).getC_amount());
                edt_DownP_NOC_Delivery.setText(response.body().getData().get(id_pos).getNoc());

                edt_CS_ConsumerSkim_Delivery.setText(response.body().getData().get(id_pos).getSkim());
                edt_CS_Hood_Delivery.setText(response.body().getData().get(id_pos).getHood());
                edt_CS_TopLink_Delivery.setText(response.body().getData().get(id_pos).getToplink());
                edt_CS_DrawBar_Delivery.setText(response.body().getData().get(id_pos).getDrowbar());
                edt_CS_ToolKit_Delivery.setText(response.body().getData().get(id_pos).getToolkit());
                edt_CS_Bumper_Delivery.setText(response.body().getData().get(id_pos).getBumper());
                edt_CS_Hitch_Delivery.setText(response.body().getData().get(id_pos).getHitch());
                edt_CS_Description_Delivery.setText(response.body().getData().get(id_pos).getDescription());


                //Loan Detail
                edt_DeliveryDetail_REF_Delivery.setText(response.body().getData().get(id_pos).getR_e_name());
                edt_DeliveryDetail_FinanceForm_Delivery.setText(response.body().getData().get(id_pos).getFinance_from());
                edt_DeliveryDetail_LoanAmount_Delivery.setText(response.body().getData().get(id_pos).getLoan_amount());
                edt_DeliveryDetail_LoanSancAmont_Delivery.setText(response.body().getData().get(id_pos).getL_sec_amt());
                edt_DeliveryDetail_LoanCharge_Delivery.setText(response.body().getData().get(id_pos).getLloan_charge());
                edt_DeliveryDetail_LandDetail_Delivery.setText(response.body().getData().get(id_pos).getLand_details());
                edt_DeliveryDetail_CibilScore_Delivery.setText(response.body().getData().get(id_pos).getCibil_score());
                edt_DeliveryDetail_FiDate_Delivery.setText(response.body().getData().get(id_pos).getFi_date());
                edt_DeliveryDetail_SanctionDate_Delivery.setText(response.body().getData().get(id_pos).getSectiondate() + "");
                edt_DeliveryDetail_Stage_Delivery.setText(StageCheck);


                //cash Details
                edt_CashDetail_Date_Delivery.setText(response.body().getData().get(id_pos).getCash_date());
                edt_CashDetail_Amount_Delivery.setText(response.body().getData().get(id_pos).getCash_amount());
                edt_CashDetail_Description_Delivery.setText(response.body().getData().get(id_pos).getCash_description());

              /*  edt_modelName_Delivery.setText(response.body().getData().get(0).getDmodelname()+"");
                edt_ChesisNumber_Delivery.setText(response.body().getData().get(0).getChasisno()+"");
                edt_EngineNumber_Delivery.setText(response.body().getData().get(0).getEngineno()+"");
                edt_Variente_Delivery.setText(response.body().getData().get(0).getVariants()+"");*/

                /*edt_Type_Delivery.setText(response.body().getData().get(0).getTyre()+"");
                edt_Bettry_Delivery.setText(response.body().getData().get(0).getBattery()+"");
                edt_DeliveryDate_Delivery.setText(response.body().getData().get(0).getDelivery_date()+"");*/
            }

            @Override
            public void onFailure(Call<DeliveryDataDisplayModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


        swipeRefreshLayoutDelivery.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebService.getClient().getDeliveryDataDisplay(emp).enqueue(new Callback<DeliveryDataDisplayModel>() {
                    @Override
                    public void onResponse(Call<DeliveryDataDisplayModel> call, Response<DeliveryDataDisplayModel> response) {
                        progressDialog.dismiss();

                        swipeRefreshLayoutDelivery.setRefreshing(false);

                        assert response.body() != null;

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getB_photo())
                                .into(img_CD_Booking_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getAdhar_photo())
                                .into(img_CD_AdharCard_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getEcard_photo())
                                .into(img_CD_ElectionCard_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getP_photo())
                                .into(img_CD_PassportSize_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getCheck1_photo())
                                .into(img_DownP_Cheque_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getNeft_rtgs_photo())
                                .into(img_DownP_NEFT_RTGS_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getNoc_photo())
                                .into(img_DownP_NocPhoto_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getOld_vehicle_photo())
                                .into(img_DownP_OldVehicle_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getRecbook_photo())
                                .into(img_DownP_RcBook_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getEc_photo())
                                .override(200, 200)
                                .into(img_DownP_ElectionPhoto_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getLb_pb_photo())
                                .into(img_LoanDetail_BankpassBook_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getL_c_photo())
                                .into(img_LoanDetail_Cheque_Delivery);


                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getSar_id_photo())
                                .into(img_LoanDetail_SarpanchID_Delivery);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getSign_veri())
                                .into(img_LoanDetail_SignatureVerifi_Delivery);

                        //-----------------------------------------------------------------------
                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getAdhar_back())
                                .into(img_CD_AdharCard_Delivery2);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getElection_back())
                                .into(img_CD_ElectionCard_Delivery2);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getP_photo_back())
                                .into(img_CD_PassportSize_Delivery2);


                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getRcbook_back())
                                .into(img_DownP_RcBook_Delivery2);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getElec_back())
                                .into(img_DownP_ElectionPhoto_Delivery2);

                        Glide.with(DeliveryFormActivity.this)
                                .load("http://asworldtech.com/sonalika/booking/"
                                        + response.body().getData().get(id_pos).getB_pass_back())
                                .apply(new RequestOptions().override(200, 200))
                                .disallowHardwareConfig()
                                .into(img_LoanDetail_BankpassBook_Delivery2);

                        //-----------------------------------------------------------

                        emp_id = response.body().getData().get(id_pos).getEmp_id();


                        if (response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                                response.body().getData().get(id_pos).getProduct_name().equals("Spar part")) {
                            edt_ADBooking_BookingType_Delivery.setVisibility(View.GONE);
                            edt_CD_PassBook_Delivery.setVisibility(View.GONE);
                            edt_CD_ChequeBook_Delivery.setVisibility(View.GONE);
                            txtFillBookingType_Delivery.setVisibility(View.GONE);
                            txtPassBookBook_Delivery.setVisibility(View.GONE);
                            txtChequeBook_Delivery.setVisibility(View.GONE);


                            txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.GONE);
                            txt_CD_AdharCard_Delivery.setVisibility(View.GONE);
                            txt_CD_ElectionCard_Delivery.setVisibility(View.GONE);
                            txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                            txt_CD_AdharCard_Delivery2.setVisibility(View.GONE);
                            txt_CD_ElectionCard_Delivery2.setVisibility(View.GONE);
                            txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);

                            img_CD_Booking_Delivery.setVisibility(View.GONE);
                            img_CD_AdharCard_Delivery.setVisibility(View.GONE);
                            img_CD_ElectionCard_Delivery.setVisibility(View.GONE);
                            img_CD_PassportSize_Delivery.setVisibility(View.GONE);
                            img_CD_AdharCard_Delivery2.setVisibility(View.GONE);
                            img_CD_ElectionCard_Delivery2.setVisibility(View.GONE);
                            img_CD_PassportSize_Delivery2.setVisibility(View.GONE);

                            deliveryTyre.setVisibility(View.GONE);
                            edt_Type_Delivery.setVisibility(View.GONE);
                            deliveryBttery.setVisibility(View.GONE);
                            edt_Bettry_Delivery.setVisibility(View.GONE);

                        } else {
                            edt_ADBooking_BookingType_Delivery.setVisibility(View.VISIBLE);
                            edt_CD_PassBook_Delivery.setVisibility(View.VISIBLE);
                            edt_CD_ChequeBook_Delivery.setVisibility(View.VISIBLE);
                            txtFillBookingType_Delivery.setVisibility(View.VISIBLE);
                            txtPassBookBook_Delivery.setVisibility(View.VISIBLE);
                            txtChequeBook_Delivery.setVisibility(View.VISIBLE);

                            deliveryTyre.setVisibility(View.VISIBLE);
                            edt_Type_Delivery.setVisibility(View.VISIBLE);
                            deliveryBttery.setVisibility(View.VISIBLE);
                            edt_Bettry_Delivery.setVisibility(View.VISIBLE);

                            deliveryTyre.setVisibility(View.GONE);
                            edt_Type_Delivery.setVisibility(View.GONE);
                            deliveryBttery.setVisibility(View.GONE);
                            edt_Bettry_Delivery.setVisibility(View.GONE);
                            Log.d("TAG", "onResponse: CheckBattery ");


                            if (response.body().getData().get(id_pos).getB_photo_check().equals("0")) {
                                txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.GONE);
                            }

                            if (response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")) {
                                txt_CD_AdharCard_Delivery.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_AdharCard_Delivery.setVisibility(View.GONE);
                            }


                            if (response.body().getData().get(id_pos).getEcard_photo_check().equals("0")) {
                                txt_CD_ElectionCard_Delivery.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_ElectionCard_Delivery.setVisibility(View.GONE);
                            }

                            if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                                txt_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                            }


                            if (response.body().getData().get(id_pos).getAdhar_back_check().equals("0")) {
                                txt_CD_AdharCard_Delivery2.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_AdharCard_Delivery2.setVisibility(View.GONE);
                            }


                            if (response.body().getData().get(id_pos).getElection_back_check().equals("0")) {
                                txt_CD_ElectionCard_Delivery2.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_ElectionCard_Delivery2.setVisibility(View.GONE);
                            }

                            if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                                txt_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                            }

                        }



                        if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                            txt_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);
                            img_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);

                            txt_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);
                            img_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);

                            if (response.body().getData().get(id_pos).getP_photo_check().equals("0")) {
                                txt_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                            }

                            if (response.body().getData().get(id_pos).getP_photo_back_check().equals("0")) {
                                txt_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);
                            } else {
                                txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                            }

                        } else {
                            txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                            img_CD_PassportSize_Delivery.setVisibility(View.GONE);

                            txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                            img_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                        }


                        //--------------------------------------------------------------

                        if (response.body().getData().get(id_pos).getBno_check().equals("0")) {
                            edt_ADBooking_BookingNo_Delivery.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingNo_Delivery.setFocusable(false);
                            edt_ADBooking_BookingNo_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getB_date_check().equals("0")) {
                            edt_ADBooking_BookingDate_Delivery.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingDate_Delivery.setFocusable(false);
                            edt_ADBooking_BookingDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getB_type_check().equals("0")) {
                            edt_ADBooking_BookingType_Delivery.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingType_Delivery.setFocusable(false);
                            edt_ADBooking_BookingType_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                            edt_ADBooking_BookingLoginName_Delivery.setFocusable(true);
                        } else {
                            edt_ADBooking_BookingLoginName_Delivery.setFocusable(false);
                            edt_ADBooking_BookingLoginName_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getFname_check().equals("0")) {
                            edt_CD_Fname_Delivery.setFocusable(true);
                        } else {
                            edt_CD_Fname_Delivery.setFocusable(false);
                            edt_CD_Fname_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getLname_check().equals("0")) {
                            edt_CD_LastName_Delivery.setFocusable(true);
                        } else {
                            edt_CD_LastName_Delivery.setFocusable(false);
                            edt_CD_LastName_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getSname_check().equals("0")) {
                            edt_CD_Surname_Delivery.setFocusable(true);
                        } else {
                            edt_CD_Surname_Delivery.setFocusable(false);
                            edt_CD_Surname_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getMobileno_check().equals("0")) {
                            edt_CD_MobileNo_Delivery.setFocusable(true);
                        } else {
                            edt_CD_MobileNo_Delivery.setFocusable(false);
                            edt_CD_MobileNo_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getWhno_check().equals("0")) {
                            edt_CD_WhatsappNo_Delivery.setFocusable(true);
                        } else {
                            edt_CD_WhatsappNo_Delivery.setFocusable(false);
                            edt_CD_WhatsappNo_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getRef_name_check().equals("0")) {
                            edt_CD_ReferenceName_Delivery.setFocusable(true);
                        } else {
                            edt_CD_ReferenceName_Delivery.setFocusable(false);
                            edt_CD_ReferenceName_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getRef_no_check().equals("0")) {
                            edt_CD_ReferenceNo_Delivery.setFocusable(true);
                        } else {
                            edt_CD_ReferenceNo_Delivery.setFocusable(false);
                            edt_CD_ReferenceNo_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getState_check().equals("0")) {
                            edt_CD_State_Delivery.setFocusable(true);
                        } else {
                            edt_CD_State_Delivery.setFocusable(false);
                            edt_CD_State_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCity_check().equals("0")) {
                            edt_CD_City_Delivery.setFocusable(true);
                        } else {
                            edt_CD_City_Delivery.setFocusable(false);
                            edt_CD_City_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDistric_check().equals("0")) {
                            edt_CD_District_Delivery.setFocusable(true);
                        } else {
                            edt_CD_District_Delivery.setFocusable(false);
                            edt_CD_District_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getVillage_check().equals("0")) {
                            edt_CD_Village_Delivery.setFocusable(true);
                        } else {
                            edt_CD_Village_Delivery.setFocusable(false);
                            edt_CD_Village_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getEmp_check().equals("0")) {
                            edt_CD_EmployeName_Delivery.setFocusable(true);
                        } else {
                            edt_CD_EmployeName_Delivery.setFocusable(false);
                            edt_CD_EmployeName_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getTehsill_check().equals("0")) {
                            edt_CD_Taluko_Delivery.setFocusable(true);
                        } else {
                            edt_CD_Taluko_Delivery.setFocusable(false);
                            edt_CD_Taluko_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getProduct_name_check().equals("0")) {
                            edt_PD_PurchaseName_Delivery.setFocusable(true);
                        } else {
                            edt_PD_PurchaseName_Delivery.setFocusable(false);
                            edt_PD_PurchaseName_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getModel_name_check().equals("0")) {
                            edt_PD_ModelName_Delivery.setFocusable(true);
                        } else {
                            edt_PD_ModelName_Delivery.setFocusable(false);
                            edt_PD_ModelName_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getP_desc_check().equals("0")) {
                            edt_PD_Description_Delivery.setFocusable(true);
                        } else {
                            edt_PD_Description_Delivery.setFocusable(false);
                            edt_PD_Description_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDeal_price_check().equals("0")) {
                            edt_PriceD_price_Delivery.setFocusable(true);
                        } else {
                            edt_PriceD_price_Delivery.setFocusable(false);
                            edt_PriceD_price_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")) {
                            edt_PriceD_priceInWord_Delivery.setFocusable(true);
                        } else {
                            edt_PriceD_priceInWord_Delivery.setFocusable(false);
                            edt_PriceD_priceInWord_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getGst_check().equals("0")) {
                            edt_PriceD_GST_Delivery.setFocusable(true);
                        } else {
                            edt_PriceD_GST_Delivery.setFocusable(false);
                            edt_PriceD_GST_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getRto_check().equals("0")) {
                            edt_RTO_RtoMain_Delivery.setFocusable(true);
                        } else {
                            edt_RTO_RtoMain_Delivery.setFocusable(false);
                            edt_RTO_RtoMain_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getRto_tax_check().equals("0")) {
                            edt_RTO_RtoTax_Delivery.setFocusable(true);
                        } else {
                            edt_RTO_RtoTax_Delivery.setFocusable(false);
                            edt_RTO_RtoTax_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getRto_passing_check().equals("0")) {
                            edt_RTO_RtoPassing_Delivery.setFocusable(true);
                        } else {
                            edt_RTO_RtoPassing_Delivery.setFocusable(false);
                            edt_RTO_RtoPassing_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getInsurance_check().equals("0")) {
                            edt_RTO_Insurance_Delivery.setFocusable(true);
                        } else {
                            edt_RTO_Insurance_Delivery.setFocusable(false);
                            edt_RTO_Insurance_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getAgent_fee_check().equals("0")) {
                            edt_RTO_AgentFees_Delivery.setFocusable(true);
                        } else {
                            edt_RTO_AgentFees_Delivery.setFocusable(false);
                            edt_RTO_AgentFees_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getNumber_plat_check().equals("0")) {
                            edt_RTO_NumberPlat_Delivery.setFocusable(true);
                        } else {
                            edt_RTO_NumberPlat_Delivery.setFocusable(false);
                            edt_RTO_NumberPlat_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getLoan_charge_check().equals("0")) {
                            edt_RTO_LoanCharge_Delivery.setFocusable(true);
                        } else {
                            edt_RTO_LoanCharge_Delivery.setFocusable(false);
                            edt_RTO_LoanCharge_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getBooking_amt_check().equals("0")) {
                            edt_DownP_BookingAmount_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_BookingAmount_Delivery.setFocusable(false);
                            edt_DownP_BookingAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getAmount_check().equals("0")) {
                            edt_DownP_CashAmount_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_CashAmount_Delivery.setFocusable(false);
                            edt_DownP_CashAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs_check().equals("0")) {
                            edt_DownP_BankOption_Delivery.setFocusable(false);
                        } else {
                            edt_DownP_BankOption_Delivery.setFocusable(false);
                            edt_DownP_BankOption_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCheck_date_check().equals("0")) {
                            edt_DownP_ChequeDate_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_ChequeDate_Delivery.setFocusable(false);
                            edt_DownP_ChequeDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                            ;
                        }

                        if (response.body().getData().get(id_pos).getC_amount_check().equals("0")) {
                            edt_DownP_ChequeAmount_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_ChequeAmount_Delivery.setFocusable(false);
                            edt_DownP_ChequeAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")) {
                            edt_DownP_NEFT_RTGS_date_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_NEFT_RTGS_date_Delivery.setFocusable(false);
                            edt_DownP_NEFT_RTGS_date_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")) {
                            edt_DownP_NEFT_RTGSAmount_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_NEFT_RTGSAmount_Delivery.setFocusable(false);
                            edt_DownP_NEFT_RTGSAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getMake_check().equals("0")) {
                            edt_DownP_SelectMake_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_SelectMake_Delivery.setFocusable(false);
                            edt_DownP_SelectMake_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getModel_check().equals("0")) {
                            edt_DownP_ModelVehicle_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_ModelVehicle_Delivery.setFocusable(false);
                            edt_DownP_ModelVehicle_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                            edt_DownP_oldAmount_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_oldAmount_Delivery.setFocusable(false);
                            edt_DownP_oldAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getM_year_check().equals("0")) {
                            edt_DownP_ManufactureYear_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_ManufactureYear_Delivery.setFocusable(false);
                            edt_DownP_ManufactureYear_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getPaper_expence_check().equals("0")) {
                            edt_DownP_PaperExchange_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_PaperExchange_Delivery.setFocusable(false);
                            edt_DownP_PaperExchange_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")) {
                            edt_DownP_oldTractorAmount_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_oldTractorAmount_Delivery.setFocusable(false);
                            edt_DownP_oldTractorAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getNoc_check().equals("0")) {
                            edt_DownP_NOC_Delivery.setFocusable(true);
                        } else {
                            edt_DownP_NOC_Delivery.setFocusable(false);
                            edt_DownP_NOC_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getHood_check().equals("0")) {
                            edt_CS_Hood_Delivery.setFocusable(true);
                        } else {
                            edt_CS_Hood_Delivery.setFocusable(false);
                            edt_CS_Hood_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getToplink_check().equals("0")) {
                            edt_CS_TopLink_Delivery.setFocusable(true);
                        } else {
                            edt_CS_TopLink_Delivery.setFocusable(false);
                            edt_CS_TopLink_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDrowbar_check().equals("0")) {
                            edt_CS_DrawBar_Delivery.setFocusable(true);
                        } else {
                            edt_CS_DrawBar_Delivery.setFocusable(false);
                            edt_CS_DrawBar_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getToolkit_check().equals("0")) {
                            edt_CS_ToolKit_Delivery.setFocusable(true);
                        } else {
                            edt_CS_ToolKit_Delivery.setFocusable(false);
                            edt_CS_ToolKit_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getBumper_check().equals("0")) {
                            edt_CS_Bumper_Delivery.setFocusable(true);
                        } else {
                            edt_CS_Bumper_Delivery.setFocusable(false);
                            edt_CS_Bumper_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getHitch_check().equals("0")) {
                            edt_CS_Hitch_Delivery.setFocusable(true);
                        } else {
                            edt_CS_Hitch_Delivery.setFocusable(false);
                            edt_CS_Hitch_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getDescription_check().equals("0")) {
                            edt_CS_Description_Delivery.setFocusable(true);
                        } else {
                            edt_CS_Description_Delivery.setFocusable(false);
                            edt_CS_Description_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getB_p_photo_check().equals("0")) {
                            edt_CD_PassBook_Delivery.setFocusable(true);
                        } else {
                            edt_CD_PassBook_Delivery.setFocusable(false);
                            edt_CD_PassBook_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCheck_photo_check().equals("0")) {
                            edt_CD_ChequeBook_Delivery.setFocusable(true);
                        } else {
                            edt_CD_ChequeBook_Delivery.setFocusable(false);
                            edt_CD_ChequeBook_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getR_e_name_check().equals("0")) {
                            edt_DeliveryDetail_REF_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_REF_Delivery.setFocusable(false);
                            edt_DeliveryDetail_REF_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getFinance_from_check().equals("0")) {
                            edt_DeliveryDetail_FinanceForm_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_FinanceForm_Delivery.setFocusable(false);
                            edt_DeliveryDetail_FinanceForm_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getLoan_amount_check().equals("0")) {
                            edt_DeliveryDetail_LoanAmount_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_LoanAmount_Delivery.setFocusable(false);
                            edt_DeliveryDetail_LoanAmount_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getL_sec_amt_check().equals("0")) {
                            edt_DeliveryDetail_LoanSancAmont_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_LoanSancAmont_Delivery.setFocusable(false);
                            edt_DeliveryDetail_LoanSancAmont_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getLloan_charge_check().equals("0")) {
                            edt_DeliveryDetail_LoanCharge_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_LoanCharge_Delivery.setFocusable(false);
                            edt_DeliveryDetail_LoanCharge_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getLand_details_check().equals("0")) {
                            edt_DeliveryDetail_LandDetail_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_LandDetail_Delivery.setFocusable(false);
                            edt_DeliveryDetail_LandDetail_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCibil_score_check().equals("0")) {
                            edt_DeliveryDetail_CibilScore_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_CibilScore_Delivery.setFocusable(false);
                            edt_DeliveryDetail_CibilScore_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getFi_date_check().equals("0")) {
                            edt_DeliveryDetail_FiDate_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_FiDate_Delivery.setFocusable(false);
                            edt_DeliveryDetail_FiDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getSectiondate_check().equals("0")) {
                            edt_DeliveryDetail_SanctionDate_Delivery.setFocusable(true);
                        } else {
                            edt_DeliveryDetail_SanctionDate_Delivery.setFocusable(false);
                            edt_DeliveryDetail_SanctionDate_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getStage_check().equals("0")) {
                            edt_DeliveryDetail_Stage_Delivery.setFocusable(true);
                            spn_delivery_stageloan.setVisibility(View.VISIBLE);
                            edt_DeliveryDetail_Stage_Delivery.setVisibility(View.GONE);
                        } else {
                            spn_delivery_stageloan.setVisibility(View.GONE);
                            edt_DeliveryDetail_Stage_Delivery.setVisibility(View.VISIBLE);
                            edt_DeliveryDetail_Stage_Delivery.setFocusable(false);
                            edt_DeliveryDetail_Stage_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getSkim_check().equals("0")) {
                            edt_CS_ConsumerSkim_Delivery.setFocusable(true);
                        } else {
                            edt_CS_ConsumerSkim_Delivery.setFocusable(false);
                            edt_CS_ConsumerSkim_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getAtype_check().equals("0")) {
                            edt_CD_PaymentOption_Delivery.setFocusable(true);
                        } else {
                            edt_CD_PaymentOption_Delivery.setFocusable(false);
                            edt_CD_PaymentOption_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        if (response.body().getData().get(id_pos).getCash_date_check().equals("0")) {
                            edt_CashDetail_Date_Delivery.setFocusable(true);
                        } else {
                            edt_CashDetail_Date_Delivery.setFocusable(false);
                            edt_CashDetail_Date_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCash_amount_check().equals("0")) {
                            edt_CashDetail_Amount_Delivery.setFocusable(true);
                        } else {
                            edt_CashDetail_Amount_Delivery.setFocusable(false);
                            edt_CashDetail_Amount_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }

                        if (response.body().getData().get(id_pos).getCash_description_check().equals("0")) {
                            edt_CashDetail_Description_Delivery.setFocusable(true);
                        } else {
                            edt_CashDetail_Description_Delivery.setFocusable(false);
                            edt_CashDetail_Description_Delivery.setTextColor(Color.parseColor("#43a047"));
                        }


                        /** ************************************************************************************* */

                        /*if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                            txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_UploadBookingPhoto_Delivery.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                            txt_CD_AdharCard_Delivery.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_AdharCard_Delivery.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                            txt_CD_ElectionCard_Delivery.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_ElectionCard_Delivery.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                            txt_CD_PassportSize_Delivery.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_PassportSize_Delivery.setVisibility(View.GONE);
                        }*/

                        if (response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")) {
                            txt_DownP_UploadCheque_Delivery.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadCheque_Delivery.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")) {
                            txt_DownP_UploadNEFT_RTGS_Delivery.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadNEFT_RTGS_Delivery.setVisibility(View.GONE);
                        }


                        if (response.body().getData().get(id_pos).getNoc_photo_check().equals("0")) {
                            txt_DownP_UploadNocPhoto_Delivery.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadNocPhoto_Delivery.setVisibility(View.GONE);
                        }


                        if (response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")) {
                            txt_DownP_UploadOldVehicle_Delivery.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadOldVehicle_Delivery.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")) {
                            txt_DownP_UploadRCBook_Delivery.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadRCBook_Delivery.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getEc_photo_check().equals("0")) {
                            txt_DownP_UploadElectionPhoto_Delivery.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadElectionPhoto_Delivery.setVisibility(View.GONE);
                        }

                        //------------------------------------------------------------------------------


                       /* if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                            txt_CD_AdharCard_Delivery2.setVisibility(View.VISIBLE);

                        }else{
                            txt_CD_AdharCard_Delivery2.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getElection_back_check ().equals("0")){
                            txt_CD_ElectionCard_Delivery2.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_ElectionCard_Delivery2.setVisibility(View.GONE);
                        }

                        if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                            txt_CD_PassportSize_Delivery2.setVisibility(View.VISIBLE);
                        }
                        else{
                            txt_CD_PassportSize_Delivery2.setVisibility(View.GONE);
                        }*/


                        if (response.body().getData().get(id_pos).getRcbook_back_check().equals("0")) {
                            txt_DownP_UploadRCBook_Delivery2.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadRCBook_Delivery2.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getElec_back_check().equals("0")) {
                            txt_DownP_UploadElectionPhoto_Delivery2.setVisibility(View.VISIBLE);
                        } else {
                            txt_DownP_UploadElectionPhoto_Delivery2.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getLb_pb_photo_check().equals("0")) {
                            txt_LoanDetail_BankPassBook_Delivery.setVisibility(View.VISIBLE);
                        } else {
                            txt_LoanDetail_BankPassBook_Delivery.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getB_pass_back_check().equals("0")) {
                            txt_LoanDetail_BankPassBook_Delivery2.setVisibility(View.VISIBLE);
                        } else {
                            txt_LoanDetail_BankPassBook_Delivery2.setVisibility(View.GONE);
                        }



                        /** ******************************************************************************** */

                        if (StageCheck.equals("Pending")) {
                            String[] Satge_data = {"Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_delivery_stageloan.setAdapter(adapterStage);

                            spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }


                        if (StageCheck.equals("Fi Done")) {
                            String[] Satge_data = {"Fi Done", "Pending", "Login Pending", "Login Done", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_delivery_stageloan.setAdapter(adapterStage);

                            spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        if (StageCheck.equals("Login Pending")) {
                            String[] Satge_data = {"Login Pending", "Pending", "Fi Done", "Login Done", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_delivery_stageloan.setAdapter(adapterStage);

                            spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        if (StageCheck.equals("Login Done")) {
                            String[] Satge_data = {"Login Done", "Pending", "Fi Done", "Login Pending", "CIBIL Check", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_delivery_stageloan.setAdapter(adapterStage);

                            spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        if (StageCheck.equals("CIBIL Check")) {
                            String[] Satge_data = {"CIBIL Check", "Pending", "Fi Done", "Login Pending", "Login Done", "sanction", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_delivery_stageloan.setAdapter(adapterStage);

                            spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                        if (StageCheck.equals("sanction")) {
                            String[] Satge_data = {"sanction", "Pending", "Fi Done", "Login Pending", "Login Done", "CIBIL Check", "Reject"};
                            ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_delivery_stageloan.setAdapter(adapterStage);

                            spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Stage = Satge_data[position];
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }


                        if (StageCheck.equals("Reject")) {
                            String[] Satge_data = {"Reject", "Pending", "Fi Done", "Login Pending", "Login Done",
                                    "CIBIL Check", "sanction"};
                            ArrayAdapter adapterStage = new ArrayAdapter(DeliveryFormActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, Satge_data);
                            spn_delivery_stageloan.setAdapter(adapterStage);

                            spn_delivery_stageloan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


                        txtDPCashAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_CashAmount_Delivery.setVisibility(View.GONE);

                        edt_DownP_BankOption_Delivery.setVisibility(View.GONE);
                        edt_DownP_ChequeDate_Delivery.setVisibility(View.GONE);
                        edt_DownP_ChequeAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.GONE);
                        edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_SelectMake_Delivery.setVisibility(View.GONE);
                        edt_DownP_ModelVehicle_Delivery.setVisibility(View.GONE);
                        edt_DownP_oldAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_ManufactureYear_Delivery.setVisibility(View.GONE);
                        edt_DownP_PaperExchange_Delivery.setVisibility(View.GONE);
                        edt_DownP_oldTractorAmount_Delivery.setVisibility(View.GONE);
                        edt_DownP_NOC_Delivery.setVisibility(View.GONE);

                        lin_dp_cheque_Delivery.setVisibility(View.GONE);
                        lin_dp_NEFT_RTGS_Delivery.setVisibility(View.GONE);
                        lin_dp_NocPhoto_Delivery.setVisibility(View.GONE);
                        lin_dp_OldVehicle_Delivery.setVisibility(View.GONE);
                        lin_dp_Rcbook_Delivery.setVisibility(View.GONE);
                        lin_dp_Election_Delivery.setVisibility(View.GONE);

                        lin_dp_Rcbook_Delivery2.setVisibility(View.GONE);
                        lin_dp_Election_Delivery2.setVisibility(View.GONE);


                        txtDPBankOption_Delivery.setVisibility(View.GONE);
                        txtDPChequeDate_Delivery.setVisibility(View.GONE);
                        txtDPChequeAmount_Delivery.setVisibility(View.GONE);
                        txtDPNEFT_RTGSdate_Delivery.setVisibility(View.GONE);
                        txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                        txtDPMake_Delivery.setVisibility(View.GONE);
                        txtDPManufectureYear_Delivery.setVisibility(View.GONE);
                        txtDPOldAmount_Delivery.setVisibility(View.GONE);
                        txtDPModelName_Delivery.setVisibility(View.GONE);
                        txtDPPaperExpense_Delivery.setVisibility(View.GONE);
                        txtDPOldTractorAmount_Delivery.setVisibility(View.GONE);
                        txtDPNoc_Delivery.setVisibility(View.GONE);


                        for (int i = 0; i < res.length; i++) {
                            mydata = res[i];
                            // Toast.makeText(BookingPhaseOneActivity.this, "yes" + mydata, Toast.LENGTH_SHORT).show();

                            String uu = mydata.trim();
                            // Log.e("TAG", "onResponse: " + uu);
                            //   Log.d("TAG", "onResponse: "+mydata);

                            if (uu.equals("Cash")) {
                                txtDPCashAmount_Delivery.setVisibility(View.VISIBLE);
                                edt_DownP_CashAmount_Delivery.setVisibility(View.VISIBLE);

                                // Log.e("TAG", "onResponse:casting ");
                                // Toast.makeText(BookingPhaseOneActivity.this, "casting", Toast.LENGTH_SHORT).show();
                            }

                            if (uu.equals("Old Vehicle")) {
                                // Log.e("TAG", "onResponse:casting45 ");
                                txtDPMake_Delivery.setVisibility(View.VISIBLE);
                                edt_DownP_SelectMake_Delivery.setVisibility(View.VISIBLE);
                                txtDPModelName_Delivery.setVisibility(View.VISIBLE);
                                edt_DownP_ModelVehicle_Delivery.setVisibility(View.VISIBLE);
                                txtDPManufectureYear_Delivery.setVisibility(View.VISIBLE);
                                edt_DownP_ManufactureYear_Delivery.setVisibility(View.VISIBLE);
                                txtDPOldAmount_Delivery.setVisibility(View.VISIBLE);
                                txtDPPaperExpense_Delivery.setVisibility(View.VISIBLE);
                                edt_DownP_PaperExchange_Delivery.setVisibility(View.VISIBLE);

                                if (response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")) {
                                    // edt_DownP_oldAmount_Delivery.setVisibility(View.GONE);
                                    edt_DownP_oldTractorAmount_Delivery.setVisibility(View.GONE);
                                } else {
                                    // edt_DownP_oldAmount_Delivery.setVisibility(View.VISIBLE);
                                    edt_DownP_oldTractorAmount_Delivery.setVisibility(View.VISIBLE);
                                }


                                if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                                    lin_dp_NocPhoto_Delivery.setVisibility(View.GONE);
                                } else {
                                    lin_dp_NocPhoto_Delivery.setVisibility(View.VISIBLE);
                                }

                                txtDPOldTractorAmount_Delivery.setVisibility(View.VISIBLE);
                                edt_DownP_oldAmount_Delivery.setVisibility(View.VISIBLE);
                                // edt_DownP_oldTractorAmount_Delivery.setVisibility(View.VISIBLE);
                                txtDPNoc_Delivery.setVisibility(View.VISIBLE);
                                edt_DownP_NOC_Delivery.setVisibility(View.VISIBLE);

                                // lin_dp_NocPhoto_Delivery.setVisibility(View.VISIBLE);
                                lin_dp_OldVehicle_Delivery.setVisibility(View.VISIBLE);
                                lin_dp_Rcbook_Delivery.setVisibility(View.VISIBLE);
                                lin_dp_Election_Delivery.setVisibility(View.VISIBLE);

                                lin_dp_Rcbook_Delivery2.setVisibility(View.VISIBLE);
                                lin_dp_Election_Delivery2.setVisibility(View.VISIBLE);
                                //  Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();
                            }

                            if (uu.equals("Bank")) {
                                // Log.e("TAG", "onResponse:casting450 ");
                                // Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();

                                txtDPBankOption_Delivery.setVisibility(View.VISIBLE);
                                edt_DownP_BankOption_Delivery.setVisibility(View.VISIBLE);

                                if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")) {
                                    txtDPChequeDate_Delivery.setVisibility(View.VISIBLE);
                                    edt_DownP_ChequeDate_Delivery.setVisibility(View.VISIBLE);
                                    txtDPChequeAmount_Delivery.setVisibility(View.VISIBLE);
                                    edt_DownP_ChequeAmount_Delivery.setVisibility(View.VISIBLE);
                                    lin_dp_cheque_Delivery.setVisibility(View.VISIBLE);

                                    txtDPNEFT_RTGSdate_Delivery.setVisibility(View.GONE);
                                    edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.GONE);
                                    txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                                    edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.GONE);
                                    lin_dp_NEFT_RTGS_Delivery.setVisibility(View.GONE);

                                }

                                if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS")) {
                                    txtDPNEFT_RTGSdate_Delivery.setVisibility(View.VISIBLE);
                                    edt_DownP_NEFT_RTGS_date_Delivery.setVisibility(View.VISIBLE);
                                    txtDPNEFT_RTGSAmount_Delivery.setVisibility(View.VISIBLE);
                                    edt_DownP_NEFT_RTGSAmount_Delivery.setVisibility(View.VISIBLE);
                                    lin_dp_NEFT_RTGS_Delivery.setVisibility(View.VISIBLE);

                                    txtDPChequeDate_Delivery.setVisibility(View.GONE);
                                    edt_DownP_ChequeDate_Delivery.setVisibility(View.GONE);
                                    txtDPChequeAmount_Delivery.setVisibility(View.GONE);
                                    edt_DownP_ChequeAmount_Delivery.setVisibility(View.GONE);
                                    lin_dp_cheque_Delivery.setVisibility(View.GONE);
                                }

                            }
                        }


                        if (response.body().getData().get(id_pos).getRto().equals("No")) {
                            edt_RTO_RtoTax_Delivery.setVisibility(View.GONE);
                            edt_RTO_RtoPassing_Delivery.setVisibility(View.GONE);
                            edt_RTO_Insurance_Delivery.setVisibility(View.GONE);
                            edt_RTO_AgentFees_Delivery.setVisibility(View.GONE);
                            edt_RTO_NumberPlat_Delivery.setVisibility(View.GONE);
                            edt_RTO_LoanCharge_Delivery.setVisibility(View.GONE);


                            txtRTOTax.setVisibility(View.GONE);
                            txtRTOPassing.setVisibility(View.GONE);
                            txtInsurance.setVisibility(View.GONE);
                            txtAgentFees.setVisibility(View.GONE);
                            txtNumberPlat.setVisibility(View.GONE);
                            txtLoanCharge.setVisibility(View.GONE);
                        } else {
                            edt_RTO_RtoTax_Delivery.setVisibility(View.VISIBLE);
                            edt_RTO_RtoPassing_Delivery.setVisibility(View.VISIBLE);
                            edt_RTO_Insurance_Delivery.setVisibility(View.VISIBLE);
                            edt_RTO_AgentFees_Delivery.setVisibility(View.VISIBLE);
                            edt_RTO_NumberPlat_Delivery.setVisibility(View.VISIBLE);
                            edt_RTO_LoanCharge_Delivery.setVisibility(View.VISIBLE);

                            txtRTOTax.setVisibility(View.VISIBLE);
                            txtRTOPassing.setVisibility(View.VISIBLE);
                            txtInsurance.setVisibility(View.VISIBLE);
                            txtAgentFees.setVisibility(View.VISIBLE);
                            txtNumberPlat.setVisibility(View.VISIBLE);
                            txtLoanCharge.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get(id_pos).getSkim().equals("No")) {

                            edt_CS_Hood_Delivery.setVisibility(View.GONE);
                            edt_CS_TopLink_Delivery.setVisibility(View.GONE);
                            edt_CS_DrawBar_Delivery.setVisibility(View.GONE);
                            edt_CS_ToolKit_Delivery.setVisibility(View.GONE);
                            edt_CS_Bumper_Delivery.setVisibility(View.GONE);
                            edt_CS_Hitch_Delivery.setVisibility(View.GONE);
                            //  edt_CS_Description_Delivery.setVisibility(View.GONE);

                            txt_cs_Hood.setVisibility(View.GONE);
                            txt_cs_TopLink.setVisibility(View.GONE);
                            txt_cs_Drawbar.setVisibility(View.GONE);
                            txt_cs_ToolKit.setVisibility(View.GONE);
                            txt_cs_Bumper.setVisibility(View.GONE);
                            txt_cs_Hitch.setVisibility(View.GONE);
                            //  txt_cs_Description.setVisibility(View.GONE);

                        } else {
                            edt_CS_Hood_Delivery.setVisibility(View.VISIBLE);
                            edt_CS_TopLink_Delivery.setVisibility(View.VISIBLE);
                            edt_CS_DrawBar_Delivery.setVisibility(View.VISIBLE);
                            edt_CS_ToolKit_Delivery.setVisibility(View.VISIBLE);
                            edt_CS_Bumper_Delivery.setVisibility(View.VISIBLE);
                            edt_CS_Hitch_Delivery.setVisibility(View.VISIBLE);
                            //  edt_CS_Description_Delivery.setVisibility(View.VISIBLE);

                            txt_cs_Hood.setVisibility(View.VISIBLE);
                            txt_cs_TopLink.setVisibility(View.VISIBLE);
                            txt_cs_Drawbar.setVisibility(View.VISIBLE);
                            txt_cs_ToolKit.setVisibility(View.VISIBLE);
                            txt_cs_Bumper.setVisibility(View.VISIBLE);
                            txt_cs_Hitch.setVisibility(View.VISIBLE);
                            //  txt_cs_Description.setVisibility(View.VISIBLE);
                        }

                       /* if(response.body().getData().get(id_pos).getAtype().equals("Loan")){
                            lin_LoanDetail_Delivery.setVisibility(View.VISIBLE);
                            lin_cashLoan_Delivery.setVisibility(View.GONE);
                        }
                        else {
                            lin_LoanDetail_Delivery.setVisibility(View.GONE);
                            lin_cashLoan_Delivery.setVisibility(View.VISIBLE);
                        }*/

                        if (response.body().getData().get(id_pos).getAtype().equals("Loan")) {
                            lin_LoanDetail_Delivery.setVisibility(View.VISIBLE);
                            lin_cashLoan_Delivery.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {
                            lin_LoanDetail_Delivery.setVisibility(View.GONE);
                            lin_cashLoan_Delivery.setVisibility(View.VISIBLE);

                           // StageFinalVal = "  ";
                        }


                        if (response.body().getData().get(id_pos).getAtype().equals("Cash-Loan")) {
                            lin_LoanDetail_Delivery.setVisibility(View.VISIBLE);
                            lin_cashLoan_Delivery.setVisibility(View.VISIBLE);
                        }


                        if(response.body().getData().get(id_pos).getStage_check().equals("0")){

                            StageFinalVal = Stage;
                        }
                        else{

                            StageFinalVal =  edt_DeliveryDetail_Stage_Delivery.getText().toString();
                        }

                        if( StageFinalVal == null){
                            StageFinalVal=" ";
                        }

                       /* if (response.body().getData().get(id_pos).getAtype().equals("Cash")) {

                            StageFinalVal = "  ";
                        }*/


                        edt_ADBooking_BookingNo_Delivery.setText(response.body().getData().get(id_pos).getBno());
                        edt_ADBooking_BookingType_Delivery.setText(response.body().getData().get(id_pos).getB_type());
                        edt_ADBooking_BookingDate_Delivery.setText(response.body().getData().get(id_pos).getB_date());
                        edt_ADBooking_BookingLoginName_Delivery.setText(response.body().getData().get(id_pos).getEmp());

                        // Toast.makeText(DeliveryFormActivity.this, ""+id_item, Toast.LENGTH_SHORT).show();

               /* edt_ADBooking_BookingNo_Delivery.setText(response.body().getData().get(id_item).getBno());
                edt_ADBooking_BookingType_Delivery.setText(response.body().getData().get(id_item).getB_type());
                edt_ADBooking_BookingDate_Delivery.setText(response.body().getData().get(id_item).getB_date());
                edt_ADBooking_BookingLoginName_Delivery.setText(response.body().getData().get(id_item).getEmp());*/

                        edt_CD_Fname_Delivery.setText(response.body().getData().get(id_pos).getFname());
                        edt_CD_LastName_Delivery.setText(response.body().getData().get(id_pos).getLname() + " ");
                        edt_CD_Surname_Delivery.setText(response.body().getData().get(id_pos).getSname());
                        edt_CD_MobileNo_Delivery.setText(response.body().getData().get(id_pos).getMobileno());
                        edt_CD_WhatsappNo_Delivery.setText(response.body().getData().get(id_pos).getWhno());
                        edt_CD_ReferenceName_Delivery.setText(response.body().getData().get(id_pos).getRef_name());
                        edt_CD_ReferenceNo_Delivery.setText(response.body().getData().get(id_pos).getRef_no());
                        edt_CD_State_Delivery.setText(response.body().getData().get(id_pos).getState());
                        edt_CD_City_Delivery.setText(response.body().getData().get(id_pos).getCity());
                        edt_CD_District_Delivery.setText(response.body().getData().get(id_pos).getDistric());
                        edt_CD_Village_Delivery.setText(response.body().getData().get(id_pos).getVillage());
                        edt_CD_EmployeName_Delivery.setText(response.body().getData().get(id_pos).getEmp());
                        edt_CD_Taluko_Delivery.setText(response.body().getData().get(id_pos).getTehsill());
                        edt_CD_PassBook_Delivery.setText(response.body().getData().get(id_pos).getB_p_photo());
                        edt_CD_ChequeBook_Delivery.setText(response.body().getData().get(id_pos).getCheck_photo());
                        edt_CD_PaymentOption_Delivery.setText(response.body().getData().get(id_pos).getAtype());

                        edt_PD_PurchaseName_Delivery.setText(response.body().getData().get(id_pos).getProduct_name());
                        edt_PD_ModelName_Delivery.setText(response.body().getData().get(id_pos).getModel_name());
                        edt_PD_Description_Delivery.setText(response.body().getData().get(id_pos).getP_desc());

                        edt_PriceD_price_Delivery.setText(response.body().getData().get(id_pos).getDeal_price());
                        edt_PriceD_priceInWord_Delivery.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                        edt_PriceD_GST_Delivery.setText(response.body().getData().get(id_pos).getGst());

                        edt_RTO_RtoMain_Delivery.setText(response.body().getData().get(id_pos).getRto());
                        edt_RTO_RtoTax_Delivery.setText(response.body().getData().get(id_pos).getRto_tax());
                        edt_RTO_RtoPassing_Delivery.setText(response.body().getData().get(id_pos).getRto_passing());
                        edt_RTO_Insurance_Delivery.setText(response.body().getData().get(id_pos).getInsurance());
                        edt_RTO_AgentFees_Delivery.setText(response.body().getData().get(id_pos).getAgent_fee());
                        edt_RTO_NumberPlat_Delivery.setText(response.body().getData().get(id_pos).getNumber_plat());
                        edt_RTO_LoanCharge_Delivery.setText(response.body().getData().get(id_pos).getLoan_charge());

                        edt_DownP_BookingAmount_Delivery.setText(response.body().getData().get(id_pos).getBooking_amt());
                        edt_DownP_CashAmount_Delivery.setText(response.body().getData().get(id_pos).getAmount());
                        edt_DownP_BankOption_Delivery.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                        edt_DownP_ChequeDate_Delivery.setText(response.body().getData().get(id_pos).getCheck_date());
                        edt_DownP_ChequeAmount_Delivery.setText(response.body().getData().get(id_pos).getCheck_amt());
                        edt_DownP_NEFT_RTGS_date_Delivery.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                        edt_DownP_NEFT_RTGSAmount_Delivery.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                        edt_DownP_SelectMake_Delivery.setText(response.body().getData().get(id_pos).getMake());
                        edt_DownP_ModelVehicle_Delivery.setText(response.body().getData().get(id_pos).getModel());
                        edt_DownP_oldAmount_Delivery.setText(response.body().getData().get(id_pos).getOld_t_amount());
                        edt_DownP_ManufactureYear_Delivery.setText(response.body().getData().get(id_pos).getM_year());
                        edt_DownP_PaperExchange_Delivery.setText(response.body().getData().get(id_pos).getPaper_expence());
                        edt_DownP_oldTractorAmount_Delivery.setText(response.body().getData().get(id_pos).getC_amount());
                        edt_DownP_NOC_Delivery.setText(response.body().getData().get(id_pos).getNoc());

                        edt_CS_ConsumerSkim_Delivery.setText(response.body().getData().get(id_pos).getSkim());
                        edt_CS_Hood_Delivery.setText(response.body().getData().get(id_pos).getHood());
                        edt_CS_TopLink_Delivery.setText(response.body().getData().get(id_pos).getToplink());
                        edt_CS_DrawBar_Delivery.setText(response.body().getData().get(id_pos).getDrowbar());
                        edt_CS_ToolKit_Delivery.setText(response.body().getData().get(id_pos).getToolkit());
                        edt_CS_Bumper_Delivery.setText(response.body().getData().get(id_pos).getBumper());
                        edt_CS_Hitch_Delivery.setText(response.body().getData().get(id_pos).getHitch());
                        edt_CS_Description_Delivery.setText(response.body().getData().get(id_pos).getDescription());


                        //Loan Detail
                        edt_DeliveryDetail_REF_Delivery.setText(response.body().getData().get(id_pos).getR_e_name());
                        edt_DeliveryDetail_FinanceForm_Delivery.setText(response.body().getData().get(id_pos).getFinance_from());
                        edt_DeliveryDetail_LoanAmount_Delivery.setText(response.body().getData().get(id_pos).getLoan_amount());
                        edt_DeliveryDetail_LoanSancAmont_Delivery.setText(response.body().getData().get(id_pos).getL_sec_amt());
                        edt_DeliveryDetail_LoanCharge_Delivery.setText(response.body().getData().get(id_pos).getLloan_charge());
                        edt_DeliveryDetail_LandDetail_Delivery.setText(response.body().getData().get(id_pos).getLand_details());
                        edt_DeliveryDetail_CibilScore_Delivery.setText(response.body().getData().get(id_pos).getCibil_score());
                        edt_DeliveryDetail_FiDate_Delivery.setText(response.body().getData().get(id_pos).getFi_date());
                        edt_DeliveryDetail_SanctionDate_Delivery.setText(response.body().getData().get(id_pos).getSectiondate() + "");
                        edt_DeliveryDetail_Stage_Delivery.setText(StageCheck);


                        //cash Details
                        edt_CashDetail_Date_Delivery.setText(response.body().getData().get(id_pos).getCash_date());
                        edt_CashDetail_Amount_Delivery.setText(response.body().getData().get(id_pos).getCash_amount());
                        edt_CashDetail_Description_Delivery.setText(response.body().getData().get(id_pos).getCash_description());

              /*  edt_modelName_Delivery.setText(response.body().getData().get(0).getDmodelname()+"");
                edt_ChesisNumber_Delivery.setText(response.body().getData().get(0).getChasisno()+"");
                edt_EngineNumber_Delivery.setText(response.body().getData().get(0).getEngineno()+"");
                edt_Variente_Delivery.setText(response.body().getData().get(0).getVariants()+"");*/

                /*edt_Type_Delivery.setText(response.body().getData().get(0).getTyre()+"");
                edt_Bettry_Delivery.setText(response.body().getData().get(0).getBattery()+"");
                edt_DeliveryDate_Delivery.setText(response.body().getData().get(0).getDelivery_date()+"");*/


                    }

                    @Override
                    public void onFailure(Call<DeliveryDataDisplayModel> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });




        txt_CD_UploadBookingPhoto_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        txt_CD_AdharCard_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        txt_CD_ElectionCard_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });

        txt_CD_PassportSize_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });


        txt_DownP_UploadCheque_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });

        txt_DownP_UploadNEFT_RTGS_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

        txt_DownP_UploadNocPhoto_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_DownP_UploadOldVehicle_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });

        txt_DownP_UploadRCBook_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });

        txt_DownP_UploadElectionPhoto_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 11);
            }
        });


        txt_LoanDetail_BankPassBook_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 12);
            }
        });

        txt_LoanDetail_Cheque_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 13);
            }
        });

        txt_LoanDetail_SarpanchId_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 14);
            }
        });

        txt_LoanDetail_SignatureVeri_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 15);
            }
        });

        //------------------------------------------------------------------


        txt_DeliveryPhoto_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                gal.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(gal, 16);
            }
        });

        txt_ChesisPrint_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                gal.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

                startActivityForResult(gal, 17);


         /*       Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setType("image/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 17);*/
            }
        });


        //------------------------------------------------------------------------
        txt_CD_AdharCard_Delivery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 18);
            }
        });

        txt_CD_ElectionCard_Delivery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 19);
            }
        });

        txt_CD_PassportSize_Delivery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 20);
            }
        });

        txt_DownP_UploadRCBook_Delivery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 21);
            }
        });

        txt_DownP_UploadElectionPhoto_Delivery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 22);
            }
        });

        txt_LoanDetail_BankPassBook_Delivery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 23);
            }
        });


        //-------------------------------------------------------

        edt_DeliveryDate_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DeliveryFormActivity.this,
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
                        edt_DeliveryDate_Delivery.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DeliveryDate_Delivery.setFocusable(false);

        edt_DeliveryDetail_SanctionDate_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DeliveryFormActivity.this,
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
                        edt_DeliveryDetail_SanctionDate_Delivery.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_DeliveryDetail_SanctionDate_Delivery.setFocusable(false);

        edt_CashDetail_Date_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DeliveryFormActivity.this,
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
                        edt_CashDetail_Date_Delivery.setText(mt + "/" + dy + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        edt_CashDetail_Date_Delivery.setFocusable(false);


      /*  if(StageFinalVal == null ){
            StageFinalVal = " ";
        }*/

        deliveryTyre.setVisibility(View.GONE);
        edt_Type_Delivery.setVisibility(View.GONE);
        deliveryBttery.setVisibility(View.GONE);
        edt_Bettry_Delivery.setVisibility(View.GONE);
        Log.d("TAG", "onResponse: CheckBattery ");

        btn_Delivery_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(DeliveryFormActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                MultipartBody.Part CD_UploadBookingPhoto_Delivery = null;
                MultipartBody.Part CD_AdharCard_Delivery = null;
                MultipartBody.Part CD_ElectionCard_Delivery = null;
                MultipartBody.Part CD_PassportSize_Delivery = null;
                MultipartBody.Part UploadDPCheque_Delivery = null;
                MultipartBody.Part UploadDPNEFT_RTGS_Delivery = null;
                MultipartBody.Part UploadDPNocPhoto_Delivery = null;
                MultipartBody.Part UploadDPOldVehicle_Delivery = null;
                MultipartBody.Part UploadDPRCBook_Delivery = null;
                MultipartBody.Part UploadDPElectionPhoto_Delivery = null;

                MultipartBody.Part LoanDetail_BankPassBook_Delivery = null;
                MultipartBody.Part LoanDetail_Cheque_Delivery = null;
                MultipartBody.Part LoanDetail_SarpanchId_Delivery = null;
                MultipartBody.Part LoanDetail_SignatureVeri_Delivery = null;

                /*--------------------------------------------------*/

                MultipartBody.Part DeliveryPhoto_Delivery = null;
                MultipartBody.Part ChesisPrint_Delivery = null;

                /*--------------------------------------------------*/


                MultipartBody.Part CD_AdharCard_Delivery2 = null;
                MultipartBody.Part CD_ElectionCard_Delivery2 = null;
                MultipartBody.Part CD_PassportSize_Delivery2 = null;

                MultipartBody.Part UploadDPRCBook_Delivery2 = null;
                MultipartBody.Part UploadDPElectionPhoto_Delivery2 = null;
                MultipartBody.Part LoanDetail_BankPassBook_Delivery2 = null;


                if (waypath_CD_UploadBookingPhoto_Delivery != null) {
                    File file1 = new File(waypath_CD_UploadBookingPhoto_Delivery);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    CD_UploadBookingPhoto_Delivery = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }

                if (waypath_CD_AdharCard_Delivery != null) {
                    File file2 = new File(waypath_CD_AdharCard_Delivery);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    CD_AdharCard_Delivery = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }

                if (waypath_CD_ElectionCard_Delivery != null) {
                    File file3 = new File(waypath_CD_ElectionCard_Delivery);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    CD_ElectionCard_Delivery = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                if (waypath_CD_PassportSize_Delivery != null) {
                    File file4 = new File(waypath_CD_PassportSize_Delivery);
                    final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                    CD_PassportSize_Delivery = MultipartBody.Part.createFormData("image6",
                            file4.getName(), requestBody4);
                }


                if (waypathUploadDPCheque_Delivery != null) {
                    File file5 = new File(waypathUploadDPCheque_Delivery);
                    final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                    UploadDPCheque_Delivery = MultipartBody.Part.createFormData("image7",
                            file5.getName(), requestBody5);
                }

                if (waypathUploadDPNEFT_RTGS_Delivery != null) {
                    File file6 = new File(waypathUploadDPNEFT_RTGS_Delivery);
                    final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                    UploadDPNEFT_RTGS_Delivery = MultipartBody.Part.createFormData("image8",
                            file6.getName(), requestBody6);
                }

                if (waypathUploadDPOldVehicle_Delivery != null) {
                    File file8 = new File(waypathUploadDPOldVehicle_Delivery);
                    final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                    UploadDPOldVehicle_Delivery = MultipartBody.Part.createFormData("image9",
                            file8.getName(), requestBody8);
                }

                if (waypathUploadDPRCBook_Delivery != null) {
                    File file9 = new File(waypathUploadDPRCBook_Delivery);
                    final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file9);
                    UploadDPRCBook_Delivery = MultipartBody.Part.createFormData("image10",
                            file9.getName(), requestBody9);
                }

                if (waypathUploadDPElectionPhoto_Delivery != null) {
                    File file10 = new File(waypathUploadDPElectionPhoto_Delivery);
                    final RequestBody requestBody10 = RequestBody.create(MediaType.parse("image/*"), file10);
                    UploadDPElectionPhoto_Delivery = MultipartBody.Part.createFormData("image11",
                            file10.getName(), requestBody10);
                }

                if (waypathUploadDPNocPhoto_Delivery != null) {
                    File file7 = new File(waypathUploadDPNocPhoto_Delivery);
                    final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                    UploadDPNocPhoto_Delivery = MultipartBody.Part.createFormData("image12",
                            file7.getName(), requestBody7);
                }


                if (WayPath_LoanDetail_BankPassBook != null) {
                    File file11 = new File(WayPath_LoanDetail_BankPassBook);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file11);
                    LoanDetail_BankPassBook_Delivery = MultipartBody.Part.createFormData("do_photo13",
                            file11.getName(), requestBody11);
                }

                if (WayPath_LoanDetail_Cheque != null) {
                    File file12 = new File(WayPath_LoanDetail_Cheque);
                    final RequestBody requestBody12 = RequestBody.create(MediaType.parse("image/*"), file12);
                    LoanDetail_Cheque_Delivery = MultipartBody.Part.createFormData("do_photo14",
                            file12.getName(), requestBody12);
                }

                if (WayPath_LoanDetail_SarpanchId != null) {
                    File file13 = new File(WayPath_LoanDetail_SarpanchId);
                    final RequestBody requestBody13 = RequestBody.create(MediaType.parse("image/*"), file13);
                    LoanDetail_SarpanchId_Delivery = MultipartBody.Part.createFormData("do_photo15",
                            file13.getName(), requestBody13);
                }

                if (WayPath_LoanDetail_SignatureVeri != null) {
                    File file14 = new File(WayPath_LoanDetail_SignatureVeri);
                    final RequestBody requestBody14 = RequestBody.create(MediaType.parse("image/*"), file14);
                    LoanDetail_SignatureVeri_Delivery = MultipartBody.Part.createFormData("do_photo16",
                            file14.getName(), requestBody14);
                }
                //-------------------------------------------------------------

                if (Waypath_DeliveryPhoto_Delivery_one != null) {
                    File file16 = new File(Waypath_DeliveryPhoto_Delivery_one);
                    final RequestBody requestBody16 = RequestBody.create(MediaType.parse("image/*"), file16);
                    DeliveryPhoto_Delivery = MultipartBody.Part.createFormData("do_photo17",
                            file16.getName(), requestBody16);
                }

                if (Waypath_ChesisPrint_Delivery != null) {
                    File file15 = new File(Waypath_ChesisPrint_Delivery);
                    final RequestBody requestBody15 = RequestBody.create(MediaType.parse("image/*"), file15);
                    ChesisPrint_Delivery = MultipartBody.Part.createFormData("do_photo18",
                            file15.getName(), requestBody15);
                }


                //-------------------------------------------------------

                if (waypath_CD_AdharCard_Delivery2 != null) {
                    File file17 = new File(waypath_CD_AdharCard_Delivery2);
                    final RequestBody requestBody17 = RequestBody.create(MediaType.parse("image/*"), file17);
                    CD_AdharCard_Delivery2 = MultipartBody.Part.createFormData("imgg1",
                            file17.getName(), requestBody17);
                }

                if (waypath_CD_ElectionCard_Delivery2 != null) {
                    File file18 = new File(waypath_CD_ElectionCard_Delivery2);
                    final RequestBody requestBody18 = RequestBody.create(MediaType.parse("image/*"), file18);
                    CD_ElectionCard_Delivery2 = MultipartBody.Part.createFormData("imgg2",
                            file18.getName(), requestBody18);
                }

                if (waypath_CD_PassportSize_Delivery2 != null) {
                    File file19 = new File(waypath_CD_PassportSize_Delivery2);
                    final RequestBody requestBody19 = RequestBody.create(MediaType.parse("image/*"), file19);
                    CD_PassportSize_Delivery2 = MultipartBody.Part.createFormData("imgg3",
                            file19.getName(), requestBody19);
                }

                if (waypathUploadDPRCBook_Delivery2 != null) {
                    File file20 = new File(waypathUploadDPRCBook_Delivery2);
                    final RequestBody requestBody20 = RequestBody.create(MediaType.parse("image/*"), file20);
                    UploadDPRCBook_Delivery2 = MultipartBody.Part.createFormData("imgg4",
                            file20.getName(), requestBody20);
                }

                if (waypathUploadDPElectionPhoto_Delivery2 != null) {
                    File file21 = new File(waypathUploadDPElectionPhoto_Delivery2);
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

                Log.d("TAG", "onCreate: Check_Activity-1");


                WebService.getClient().getDeliverySubmit(
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingNo_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingDate_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), emp),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Fname_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Surname_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_MobileNo_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_WhatsappNo_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceName_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceNo_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_State_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_City_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_District_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Taluko_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Village_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingLoginName_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingType_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PassBook_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ChequeBook_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_PurchaseName_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_ModelName_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_Description_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_price_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_priceInWord_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_GST_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoMain_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoTax_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoPassing_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_Insurance_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_AgentFees_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_NumberPlat_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_LoanCharge_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BookingAmount_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_CashAmount_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BankOption_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeDate_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeAmount_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGS_date_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGSAmount_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_SelectMake_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ModelVehicle_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ManufactureYear_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldAmount_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_PaperExchange_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldTractorAmount_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NOC_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hood_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_TopLink_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_DrawBar_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ToolKit_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Bumper_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hitch_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Description_Delivery.getText().toString()),
                        CD_UploadBookingPhoto_Delivery,
                        CD_AdharCard_Delivery,
                        CD_ElectionCard_Delivery,
                        CD_PassportSize_Delivery,
                        UploadDPCheque_Delivery,
                        UploadDPNEFT_RTGS_Delivery,
                        UploadDPOldVehicle_Delivery,
                        UploadDPRCBook_Delivery,
                        UploadDPElectionPhoto_Delivery,
                        UploadDPNocPhoto_Delivery,
                        RequestBody.create(MediaType.parse("text/plain"), idBookingUpload),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_REF_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_FinanceForm_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_LoanAmount_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_LoanSancAmont_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_LoanCharge_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_LandDetail_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_CibilScore_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_FiDate_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_SanctionDate_Delivery.getText().toString()),
                        /*RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDetail_Stage_Delivery.getText().toString()),*/
                        RequestBody.create(MediaType.parse("text/plain"), StageFinalVal),
                        LoanDetail_BankPassBook_Delivery,
                        /*LoanDetail_Cheque_Delivery ,
                        LoanDetail_SarpanchId_Delivery ,*/
                        LoanDetail_SignatureVeri_Delivery,
                        DeliveryPhoto_Delivery,
                        ChesisPrint_Delivery,
                        RequestBody.create(MediaType.parse("text/plain"), edt_DeliveryDate_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Type_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_Bettry_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PaymentOption_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ConsumerSkim_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Date_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Amount_Delivery.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CashDetail_Description_Delivery.getText().toString()),
                        CD_AdharCard_Delivery2,
                        CD_ElectionCard_Delivery2,
                        CD_PassportSize_Delivery2,
                        UploadDPRCBook_Delivery2,
                        UploadDPElectionPhoto_Delivery2,
                        LoanDetail_BankPassBook_Delivery2,
                        RequestBody.create(MediaType.parse("text/plain"), ""),
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

                        Toast.makeText(DeliveryFormActivity.this, ""
                                        + response.body().getMessage(),
                                Toast.LENGTH_LONG).show();

                        Intent i = new Intent(DeliveryFormActivity.this,
                                BookingDeliveryMainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(@NotNull Call<DeliveryBookingModel> call, @NotNull Throwable t) {

                        progressDialog.dismiss();

                        Log.d("Fail_delivery", "onFailure: " + t.getCause() + " " + t.getMessage());

                        //  Toast.makeText(DeliveryFormActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
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
                    uri_CD_UploadBookingPhoto_Delivery = data.getData();
                    Log.d("PanImageUri", uri_CD_UploadBookingPhoto_Delivery.toString());
                    waypath_CD_UploadBookingPhoto_Delivery = getFilePath(this, uri_CD_UploadBookingPhoto_Delivery);

                    Log.d("PanImage", waypath_CD_UploadBookingPhoto_Delivery);
                    String[] name = waypath_CD_UploadBookingPhoto_Delivery.split("/");
                    txt_CD_UploadBookingPhoto_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_Booking_Delivery.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_Delivery = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_Delivery.toString());
                    waypath_CD_AdharCard_Delivery = getFilePath(this, uri_CD_AdharCard_Delivery);

                    Log.d("PanRTGS", waypath_CD_AdharCard_Delivery);
                    String[] name = waypath_CD_AdharCard_Delivery.split("/");
                    txt_CD_AdharCard_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_Delivery.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_Delivery = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_Delivery.toString());
                    waypath_CD_ElectionCard_Delivery = getFilePath(this, uri_CD_ElectionCard_Delivery);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_Delivery);
                    String[] name = waypath_CD_ElectionCard_Delivery.split("/");
                    txt_CD_ElectionCard_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_Delivery.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_Delivery = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_Delivery.toString());
                    waypath_CD_PassportSize_Delivery = getFilePath(this, uri_CD_PassportSize_Delivery);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_Delivery);
                    String[] name = waypath_CD_PassportSize_Delivery.split("/");
                    txt_CD_PassportSize_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_Delivery.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPCheque_Delivery = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPCheque_Delivery.toString());
                    waypathUploadDPCheque_Delivery = getFilePath(this, uriUploadDPCheque_Delivery);
                    Log.d("Pan Image Uri", waypathUploadDPCheque_Delivery);
                    String[] name = waypathUploadDPCheque_Delivery.split("/");
                    txt_DownP_UploadCheque_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_Cheque_Delivery.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNEFT_RTGS_Delivery = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNEFT_RTGS_Delivery.toString());
                    waypathUploadDPNEFT_RTGS_Delivery = getFilePath(this, uriUploadDPNEFT_RTGS_Delivery);
                    Log.d("Pan Image Uri", waypathUploadDPNEFT_RTGS_Delivery);
                    String[] name = waypathUploadDPNEFT_RTGS_Delivery.split("/");
                    txt_DownP_UploadNEFT_RTGS_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NEFT_RTGS_Delivery.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNocPhoto_Delivery = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNocPhoto_Delivery.toString());
                    waypathUploadDPNocPhoto_Delivery = getFilePath(this, uriUploadDPNocPhoto_Delivery);
                    Log.d("Pan Image Uri", waypathUploadDPNocPhoto_Delivery);
                    String[] name = waypathUploadDPNocPhoto_Delivery.split("/");
                    txt_DownP_UploadNocPhoto_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NocPhoto_Delivery.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPOldVehicle_Delivery = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPOldVehicle_Delivery.toString());
                    waypathUploadDPOldVehicle_Delivery = getFilePath(this, uriUploadDPOldVehicle_Delivery);
                    Log.d("Pan Image Uri", waypathUploadDPOldVehicle_Delivery);
                    String[] name = waypathUploadDPOldVehicle_Delivery.split("/");
                    txt_DownP_UploadOldVehicle_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_OldVehicle_Delivery.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_Delivery = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_Delivery.toString());
                    waypathUploadDPRCBook_Delivery = getFilePath(this, uriUploadDPRCBook_Delivery);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_Delivery);
                    String[] name = waypathUploadDPRCBook_Delivery.split("/");
                    txt_DownP_UploadRCBook_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_Delivery.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_Delivery = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_Delivery.toString());
                    waypathUploadDPElectionPhoto_Delivery = getFilePath(this, uriUploadDPElectionPhoto_Delivery);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_Delivery);
                    String[] name = waypathUploadDPElectionPhoto_Delivery.split("/");
                    txt_DownP_UploadElectionPhoto_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_Delivery.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_BankPassBook_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook_Delivery.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_Cheque_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_Cheque_Delivery.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_SarpanchId_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SarpanchID_Delivery.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_SignatureVeri_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_SignatureVerifi_Delivery.setImageURI(selectedImageUri);
                }
            }
        }

        //----------------------------------------------------


        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_DeliveryPhoto_Delivery_one = data.getData();
                    Log.d("DeliveryURI", uri_DeliveryPhoto_Delivery_one.toString());
                    Waypath_DeliveryPhoto_Delivery_one = getFilePath(this, uri_DeliveryPhoto_Delivery_one);
                    Log.d("DeliveryWayPath", Waypath_DeliveryPhoto_Delivery_one);
                    String[] name = Waypath_DeliveryPhoto_Delivery_one.split("/");
                    txt_DeliveryPhoto_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DeliveryPhoto_Delivery.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 17) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_ChesisPrint_Delivery = data.getData();
                    Log.d("DeliveryCURI", uri_ChesisPrint_Delivery.toString());
                    Waypath_ChesisPrint_Delivery = getFilePath(this, uri_ChesisPrint_Delivery);
                    Log.d("DeliveryWayPath", Waypath_ChesisPrint_Delivery);
                    String[] name = Waypath_ChesisPrint_Delivery.split("/");
                    txt_ChesisPrint_Delivery.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_ChesisPrint_Delivery.setImageURI(selectedImageUri);
                }
            }
        }

        //----------------------------------------------------------------------

        if (requestCode == 18) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard_Delivery2 = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard_Delivery2.toString());
                    waypath_CD_AdharCard_Delivery2 = getFilePath(this, uri_CD_AdharCard_Delivery2);

                    Log.d("PanRTGS", waypath_CD_AdharCard_Delivery2);
                    String[] name = waypath_CD_AdharCard_Delivery2.split("/");
                    txt_CD_AdharCard_Delivery2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard_Delivery2.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard_Delivery2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard_Delivery2.toString());
                    waypath_CD_ElectionCard_Delivery2 = getFilePath(this, uri_CD_ElectionCard_Delivery2);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard_Delivery2);
                    String[] name = waypath_CD_ElectionCard_Delivery2.split("/");
                    txt_CD_ElectionCard_Delivery2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard_Delivery2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize_Delivery2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize_Delivery2.toString());
                    waypath_CD_PassportSize_Delivery2 = getFilePath(this, uri_CD_PassportSize_Delivery2);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize_Delivery2);
                    String[] name = waypath_CD_PassportSize_Delivery2.split("/");
                    txt_CD_PassportSize_Delivery2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize_Delivery2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 21) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook_Delivery2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook_Delivery2.toString());
                    waypathUploadDPRCBook_Delivery2 = getFilePath(this, uriUploadDPRCBook_Delivery2);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook_Delivery2);
                    String[] name = waypathUploadDPRCBook_Delivery2.split("/");
                    txt_DownP_UploadRCBook_Delivery2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook_Delivery2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 22) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto_Delivery2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto_Delivery2.toString());
                    waypathUploadDPElectionPhoto_Delivery2 = getFilePath(this, uriUploadDPElectionPhoto_Delivery2);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto_Delivery2);
                    String[] name = waypathUploadDPElectionPhoto_Delivery2.split("/");
                    txt_DownP_UploadElectionPhoto_Delivery2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto_Delivery2.setImageURI(selectedImageUri);
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
                    txt_LoanDetail_BankPassBook_Delivery2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_LoanDetail_BankpassBook_Delivery2.setImageURI(selectedImageUri);
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