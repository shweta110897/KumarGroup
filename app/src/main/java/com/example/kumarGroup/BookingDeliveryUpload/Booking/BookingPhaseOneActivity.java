package com.example.kumarGroup.BookingDeliveryUpload.Booking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kumarGroup.BookingDeliveryUpload.BookingDeliveryMainActivity;
import com.example.kumarGroup.BookingSubmitModel;
import com.example.kumarGroup.BookingUploadModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingPhaseOneActivity extends AppCompatActivity {

    //Agreement Details
    EditText edt_ADBooking_BookingNo,edt_ADBooking_BookingType,
            edt_ADBooking_BookingDate,edt_ADBooking_BookingLoginName;

    TextView txtBPType;

    //Customer Detail
    EditText edt_CD_Fname,edt_CD_LastName,edt_CD_Surname,edt_CD_MobileNo,edt_CD_WhatsappNo,
            edt_CD_ReferenceName,edt_CD_ReferenceNo,edt_CD_State,edt_CD_City,edt_CD_District,
            edt_CD_Taluko,edt_CD_Village,edt_CD_EmployeName,edt_CD_PassBook,edt_CD_ChequeBook,
            edt_CD_PaymentOption;

    TextView txtBPPassBook,txtBPChequeBook;

    TextView  txt_CD_UploadBookingPhoto,txt_CD_AdharCard,
            txt_CD_ElectionCard,txt_CD_BankPassBook,txt_CD_Cheque,txt_CD_PassportSize;

    ImageView img_CD_Booking,img_CD_AdharCard,img_CD_ElectionCard,
            img_CD_BankPassBook,img_CD_Cheque,img_CD_PassportSize;

    ImageView img_CD_AdharCard2,img_CD_ElectionCard2,img_CD_PassportSize2;

    TextView  txt_CD_AdharCard2, txt_CD_ElectionCard2,txt_CD_PassportSize2;

    //Product Details
    EditText edt_PD_PurchaseName,edt_PD_ModelName,edt_PD_Description;

    //Price Details
    EditText edt_PriceD_price,edt_PriceD_priceInWord,edt_PriceD_GST;

    //RTO Details
    EditText edt_RTO_RtoMain,edt_RTO_RtoTax,edt_RTO_RtoPassing,
            edt_RTO_Insurance,edt_RTO_AgentFees,edt_RTO_NumberPlat,edt_RTO_LoanCharge;

    TextView txtRTOTax,txtRTOPassing,txtInsurance,txtAgentFees,txtNumberPlat,txtLoanCharge;


    //Down Payment
    EditText edt_DownP_BookingAmount,edt_DownP_CashAmount,edt_DownP_BankOption,edt_DownP_ChequeDate,
            edt_DownP_ChequeAmount,edt_DownP_NEFT_RTGS_date,edt_DownP_NEFT_RTGSAmount,edt_DownP_SelectMake,
            edt_DownP_ModelVehicle,edt_DownP_ManufactureYear,edt_DownP_oldAmount,edt_DownP_PaperExchange,
            edt_DownP_oldTractorAmount,edt_DownP_NOC;

    ImageView img_DownP_Cheque,img_DownP_NEFT_RTGS,img_DownP_NocPhoto,img_DownP_OldVehicle,
            img_DownP_RcBook,img_DownP_ElectionPhoto;

    ImageView  img_DownP_RcBook2,img_DownP_ElectionPhoto2;

    TextView txt_DownP_UploadRCBook2,txt_DownP_UploadElectionPhoto2;

    TextView txt_DownP_UploadCheque,txt_DownP_UploadNEFT_RTGS,txt_DownP_UploadNocPhoto,
            txt_DownP_UploadOldVehicle,txt_DownP_UploadRCBook,txt_DownP_UploadElectionPhoto;

    TextView txtDPCashAmount,txtDPBankOption,txtDPChequeDate,txtDPChequeAmount,txtDPNEFT_RTGSdate,
            txtDPNEFT_RTGSAmount,txtDPMake,txtDPManufectureYear,txtDPOldAmount,txtDPModelName,
            txtDPPaperExpense,txtDPOldTractorAmount,txtDPNoc;

    LinearLayout lin_dp_cheque,lin_dp_NEFT_RTGS,lin_dp_NocPhoto,lin_dp_OldVehicle,lin_dp_Rcbook,
            lin_dp_Election;

    LinearLayout lin_dp_Election2,lin_dp_Rcbook2;

    //Consumer Skim
    EditText   edt_CS_Hood,edt_CS_TopLink,edt_CS_DrawBar,edt_CS_ToolKit,
            edt_CS_Bumper,edt_CS_Hitch,edt_CS_Description,edt_CS_ConsumerSkim;

    TextView txt_cs_Hood,txt_cs_TopLink,txt_cs_Drawbar,txt_cs_ToolKit,txt_cs_Bumper,
            txt_cs_Hitch,txt_cs_Description;

    Button btn_BookingPhase_Submit;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    Uri uri_CD_UploadBookingPhoto,uri_CD_AdharCard,uri_CD_ElectionCard,uri_CD_PassportSize;

    Uri uri_CD_AdharCard2,uri_CD_ElectionCard2,uri_CD_PassportSize2;

    String waypath_CD_UploadBookingPhoto,waypath_CD_AdharCard,waypath_CD_ElectionCard,waypath_CD_PassportSize;

    String waypath_CD_AdharCard2,waypath_CD_ElectionCard2,waypath_CD_PassportSize2;

    Uri uriUploadDPCheque, uriUploadDPNEFT_RTGS, uriUploadDPNocPhoto, uriUploadDPOldVehicle,
            uriUploadDPRCBook, uriUploadDPElectionPhoto;

    String waypathUploadDPCheque, waypathUploadDPNEFT_RTGS, waypathUploadDPNocPhoto,
            waypathUploadDPOldVehicle, waypathUploadDPRCBook, waypathUploadDPElectionPhoto;

    String  waypathUploadDPRCBook2, waypathUploadDPElectionPhoto2;

    Uri  uriUploadDPRCBook2, uriUploadDPElectionPhoto2;


    String idBookingUpload;

    String emp_id;

    String id_item;

    int id_pos;

    int number;


    ArrayList<String> Bamt = new ArrayList<>();

    //ArrayList<String> Bamt = new ArrayList<>();
    ArrayList<String> bookingAmountArray = new ArrayList<>();


    String Cash,bank,OldVehicle;

    String mydata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_phase_one);

        getSupportActionBar().setTitle("Booking");

        /** ------------------------------------ Memory Allocation ---------------------------------------------*/

        //Agreement Details
        edt_ADBooking_BookingNo = findViewById(R.id.edt_ADBooking_BookingNo);
        edt_ADBooking_BookingType = findViewById(R.id.edt_ADBooking_BookingType);
        txtBPType = findViewById(R.id.txtBPType);
        edt_ADBooking_BookingDate = findViewById(R.id.edt_ADBooking_BookingDate);
        edt_ADBooking_BookingLoginName = findViewById(R.id.edt_ADBooking_BookingLoginName);

        //Customer Detail
        edt_CD_Fname = findViewById(R.id.edt_CD_Fname);
        edt_CD_LastName = findViewById(R.id.edt_CD_LastName);
        edt_CD_Surname = findViewById(R.id.edt_CD_Surname);
        edt_CD_MobileNo = findViewById(R.id.edt_CD_MobileNo);
        edt_CD_WhatsappNo = findViewById(R.id.edt_CD_WhatsappNo);
        edt_CD_ReferenceName = findViewById(R.id.edt_CD_ReferenceName);
        edt_CD_ReferenceNo = findViewById(R.id.edt_CD_ReferenceNo);
        edt_CD_State = findViewById(R.id.edt_CD_State);
        edt_CD_City = findViewById(R.id.edt_CD_City);
        edt_CD_District = findViewById(R.id.edt_CD_District);
        edt_CD_Taluko = findViewById(R.id.edt_CD_Taluko);
        edt_CD_Village = findViewById(R.id.edt_CD_Village);
        edt_CD_EmployeName = findViewById(R.id.edt_CD_EmployeName);
        edt_CD_PassBook = findViewById(R.id.edt_CD_PassBook);
        txtBPPassBook = findViewById(R.id.txtBPPassBook);
        edt_CD_ChequeBook = findViewById(R.id.edt_CD_ChequeBook);
        txtBPChequeBook = findViewById(R.id.txtBPChequeBook);
        edt_CD_PaymentOption = findViewById(R.id.edt_CD_PaymentOption);


        txt_CD_UploadBookingPhoto =findViewById(R.id.txt_CD_UploadBookingPhoto);
        txt_CD_AdharCard =findViewById(R.id.txt_CD_AdharCard);
        txt_CD_ElectionCard =findViewById(R.id.txt_CD_ElectionCard);
      /*  txt_CD_BankPassBook =findViewById(R.id.txt_CD_BankPassBook);
        txt_CD_Cheque =findViewById(R.id.txt_CD_Cheque);*/
        txt_CD_PassportSize =findViewById(R.id.txt_CD_PassportSize);


        img_CD_Booking=findViewById(R.id.img_CD_Booking);
        img_CD_AdharCard=findViewById(R.id.img_CD_AdharCard);
        img_CD_ElectionCard=findViewById(R.id.img_CD_ElectionCard);
       /* img_CD_BankPassBook=findViewById(R.id.img_CD_BankPassBook);
        img_CD_Cheque=findViewById(R.id.img_CD_Cheque);*/
        img_CD_PassportSize=findViewById(R.id.img_CD_PassportSize);

      //  img_CD_AdharCard2,img_CD_ElectionCard2,img_CD_PassportSize2;

     //   txt_CD_AdharCard2, txt_CD_ElectionCard2,txt_CD_PassportSize2;

        img_CD_AdharCard2=findViewById(R.id.img_CD_AdharCard2);
        img_CD_ElectionCard2=findViewById(R.id.img_CD_ElectionCard2);
        img_CD_PassportSize2=findViewById(R.id.img_CD_PassportSize2);

        txt_CD_AdharCard2=findViewById(R.id.txt_CD_AdharCard2);
        txt_CD_ElectionCard2=findViewById(R.id.txt_CD_ElectionCard2);
        txt_CD_PassportSize2=findViewById(R.id.txt_CD_PassportSize2);


        //Product Details
        edt_PD_PurchaseName=findViewById(R.id.edt_PD_PurchaseName);
        edt_PD_ModelName=findViewById(R.id.edt_PD_ModelName);
        edt_PD_Description=findViewById(R.id.edt_PD_Description);


        //Price Details
        edt_PriceD_price=findViewById(R.id.edt_PriceD_price);
        edt_PriceD_priceInWord=findViewById(R.id.edt_PriceD_priceInWord);
        edt_PriceD_GST=findViewById(R.id.edt_PriceD_GST);


        //RTO Details
        edt_RTO_RtoMain=findViewById(R.id.edt_RTO_RtoMain);
        edt_RTO_RtoTax=findViewById(R.id.edt_RTO_RtoTax);
        edt_RTO_RtoPassing=findViewById(R.id.edt_RTO_RtoPassing);
        edt_RTO_Insurance=findViewById(R.id.edt_RTO_Insurance);
        edt_RTO_AgentFees=findViewById(R.id.edt_RTO_AgentFees);
        edt_RTO_NumberPlat=findViewById(R.id.edt_RTO_NumberPlat);
        edt_RTO_LoanCharge=findViewById(R.id.edt_RTO_LoanCharge);

        txtRTOTax= findViewById(R.id.txtRTOTax);
        txtRTOPassing= findViewById(R.id.txtRTOPassing);
        txtInsurance= findViewById(R.id.txtInsurance);
        txtAgentFees= findViewById(R.id.txtAgentFees);
        txtNumberPlat= findViewById(R.id.txtNumberPlat);
        txtLoanCharge= findViewById(R.id.txtLoanCharge);

        //Down Payment
        edt_DownP_BookingAmount=findViewById(R.id.edt_DownP_BookingAmount);
        edt_DownP_CashAmount=findViewById(R.id.edt_DownP_CashAmount);
        edt_DownP_BankOption=findViewById(R.id.edt_DownP_BankOption);
        edt_DownP_ChequeDate=findViewById(R.id.edt_DownP_ChequeDate);
        edt_DownP_ChequeAmount=findViewById(R.id.edt_DownP_ChequeAmount);
        edt_DownP_NEFT_RTGS_date=findViewById(R.id.edt_DownP_NEFT_RTGS_date);
        edt_DownP_NEFT_RTGSAmount=findViewById(R.id.edt_DownP_NEFT_RTGSAmount);
        edt_DownP_SelectMake=findViewById(R.id.edt_DownP_SelectMake);
        edt_DownP_ModelVehicle=findViewById(R.id.edt_DownP_ModelVehicle);
        edt_DownP_oldAmount=findViewById(R.id.edt_DownP_oldAmount);
        edt_DownP_ManufactureYear=findViewById(R.id.edt_DownP_ManufactureYear);
        edt_DownP_PaperExchange=findViewById(R.id.edt_DownP_PaperExchange);
        edt_DownP_oldTractorAmount=findViewById(R.id.edt_DownP_oldTractorAmount);
        edt_DownP_NOC=findViewById(R.id.edt_DownP_NOC);


        img_DownP_Cheque= findViewById(R.id.img_DownP_Cheque);
        img_DownP_NEFT_RTGS= findViewById(R.id.img_DownP_NEFT_RTGS);
        img_DownP_NocPhoto= findViewById(R.id.img_DownP_NocPhoto);
        img_DownP_OldVehicle= findViewById(R.id.img_DownP_OldVehicle);
        img_DownP_RcBook= findViewById(R.id.img_DownP_RcBook);
        img_DownP_ElectionPhoto= findViewById(R.id.img_DownP_ElectionPhoto);

        img_DownP_RcBook2= findViewById(R.id.img_DownP_RcBook2);
        img_DownP_ElectionPhoto2= findViewById(R.id.img_DownP_ElectionPhoto2);

        txt_DownP_UploadCheque=findViewById(R.id.txt_DownP_UploadCheque);
        txt_DownP_UploadNEFT_RTGS=findViewById(R.id.txt_DownP_UploadNEFT_RTGS);
        txt_DownP_UploadNocPhoto=findViewById(R.id.txt_DownP_UploadNocPhoto);
        txt_DownP_UploadOldVehicle=findViewById(R.id.txt_DownP_UploadOldVehicle);
        txt_DownP_UploadRCBook=findViewById(R.id.txt_DownP_UploadRCBook);
        txt_DownP_UploadElectionPhoto=findViewById(R.id.txt_DownP_UploadElectionPhoto);

        txt_DownP_UploadRCBook2=findViewById(R.id.txt_DownP_UploadRCBook2);
        txt_DownP_UploadElectionPhoto2=findViewById(R.id.txt_DownP_UploadElectionPhoto2);

         //Label Textview
        txtDPCashAmount=findViewById(R.id.txtDPCashAmount);
        txtDPBankOption=findViewById(R.id.txtDPBankOption);
        txtDPChequeDate=findViewById(R.id.txtDPChequeDate);
        txtDPChequeAmount=findViewById(R.id.txtDPChequeAmount);
        txtDPNEFT_RTGSdate=findViewById(R.id.txtDPNEFT_RTGSdate);
        txtDPNEFT_RTGSAmount=findViewById(R.id.txtDPNEFT_RTGSAmount);
        txtDPMake=findViewById(R.id.txtDPMake);
        txtDPManufectureYear=findViewById(R.id.txtDPManufectureYear);
        txtDPOldAmount=findViewById(R.id.txtDPOldAmount);
        txtDPModelName=findViewById(R.id.txtDPModelName);
        txtDPPaperExpense=findViewById(R.id.txtDPPaperExpense);
        txtDPOldTractorAmount=findViewById(R.id.txtDPOldTractorAmount);
        txtDPNoc=findViewById(R.id.txtDPNoc);


        lin_dp_cheque=findViewById(R.id.lin_dp_cheque);
        lin_dp_NEFT_RTGS=findViewById(R.id.lin_dp_NEFT_RTGS);
        lin_dp_NocPhoto=findViewById(R.id.lin_dp_NocPhoto);
        lin_dp_OldVehicle=findViewById(R.id.lin_dp_OldVehicle);
        lin_dp_Rcbook=findViewById(R.id.lin_dp_Rcbook);
        lin_dp_Election=findViewById(R.id.lin_dp_Election);

        lin_dp_Rcbook2=findViewById(R.id.lin_dp_Rcbook2);
        lin_dp_Election2=findViewById(R.id.lin_dp_Election2);

        //Consumer Skim
        edt_CS_Hood=findViewById(R.id.edt_CS_Hood);
        edt_CS_TopLink=findViewById(R.id.edt_CS_TopLink);
        edt_CS_DrawBar=findViewById(R.id.edt_CS_DrawBar);
        edt_CS_ToolKit=findViewById(R.id.edt_CS_ToolKit);
        edt_CS_Bumper=findViewById(R.id.edt_CS_Bumper);
        edt_CS_Hitch=findViewById(R.id.edt_CS_Hitch);
        edt_CS_Description=findViewById(R.id.edt_CS_Description);
        edt_CS_ConsumerSkim=findViewById(R.id.edt_CS_ConsumerSkim);


        txt_cs_Hood=findViewById(R.id.txt_cs_Hood);
        txt_cs_TopLink=findViewById(R.id.txt_cs_TopLink);
        txt_cs_Drawbar=findViewById(R.id.txt_cs_Drawbar);
        txt_cs_ToolKit=findViewById(R.id.txt_cs_ToolKit);
        txt_cs_Bumper=findViewById(R.id.txt_cs_Bumper);
        txt_cs_Hitch=findViewById(R.id.txt_cs_Hitch);
        txt_cs_Description=findViewById(R.id.txt_cs_Description);


        btn_BookingPhase_Submit=findViewById(R.id.btn_BookingPhase_Submit);

        /** -------------------------------------------------------------------------------------------------- */

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");

        idBookingUpload = getIntent().getStringExtra("idBookingUpload");

        id_item = getIntent().getStringExtra("position");

        id_pos= Integer.parseInt(id_item);

        progressDialog= new ProgressDialog(BookingPhaseOneActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getBookingUploadModule(emp).enqueue(new Callback<BookingUploadModel>() {
            @Override
            public void onResponse(@NotNull Call<BookingUploadModel> call,
                                   @NotNull Response<BookingUploadModel> response) {

                progressDialog.dismiss();

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getB_photo())
                        .into(img_CD_Booking);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getAdhar_photo())
                        .into(img_CD_AdharCard);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getEcard_photo())
                        .into(img_CD_ElectionCard);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getP_photo())
                        .into(img_CD_PassportSize);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getCheck1_photo())
                        .into(img_DownP_Cheque);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getNeft_rtgs_photo())
                        .into(img_DownP_NEFT_RTGS);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getNoc_photo())
                        .into(img_DownP_NocPhoto);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getOld_vehicle_photo())
                        .into(img_DownP_OldVehicle);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getRecbook_photo())
                        .into(img_DownP_RcBook);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getEc_photo())
                        .into(img_DownP_ElectionPhoto);

                // /////////////////////////////////////////////////

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getAdhar_back())
                        .into(img_CD_AdharCard2);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getElection_back())
                        .into(img_CD_ElectionCard2);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getP_photo_back())
                        .into(img_CD_PassportSize2);

                // ///////////////////////////////////////////////


                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getRcbook_back())
                        .into(img_DownP_RcBook2);

                Glide.with(BookingPhaseOneActivity.this)
                        .load("http://asworldtech.com/sonalika/booking/"+response.body().getData().get(id_pos).getElec_back())
                        .into(img_DownP_ElectionPhoto2);

                emp_id = response.body().getData().get(id_pos).getEmp_id();



                if(response.body().getData().get(id_pos).getProduct_name().equals("Implement") ||
                        response.body().getData().get(id_pos).getProduct_name().equals("Spar part"))
                {
                     edt_ADBooking_BookingType.setVisibility(View.GONE);
                     edt_CD_PassBook.setVisibility(View.GONE);
                     edt_CD_ChequeBook.setVisibility(View.GONE);
                    txtBPType.setVisibility(View.GONE);
                    txtBPPassBook.setVisibility(View.GONE);
                    txtBPChequeBook.setVisibility(View.GONE);


                    txt_CD_UploadBookingPhoto.setVisibility(View.GONE);
                    txt_CD_AdharCard.setVisibility(View.GONE);
                    txt_CD_ElectionCard.setVisibility(View.GONE);
                    txt_CD_PassportSize.setVisibility(View.GONE);
                    txt_CD_AdharCard2.setVisibility(View.GONE);
                    txt_CD_ElectionCard2.setVisibility(View.GONE);
                    txt_CD_PassportSize2.setVisibility(View.GONE);

                    img_CD_Booking.setVisibility(View.GONE);
                    img_CD_AdharCard.setVisibility(View.GONE);
                    img_CD_ElectionCard.setVisibility(View.GONE);
                    img_CD_PassportSize.setVisibility(View.GONE);
                    img_CD_AdharCard2.setVisibility(View.GONE);
                    img_CD_ElectionCard2.setVisibility(View.GONE);
                    img_CD_PassportSize2.setVisibility(View.GONE);

                }
                else{
                    edt_ADBooking_BookingType.setVisibility(View.VISIBLE);
                    edt_CD_PassBook.setVisibility(View.VISIBLE);
                    edt_CD_ChequeBook.setVisibility(View.VISIBLE);
                    txtBPType.setVisibility(View.VISIBLE);
                    txtBPPassBook.setVisibility(View.VISIBLE);
                    txtBPChequeBook.setVisibility(View.VISIBLE);


                    if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                        txt_CD_UploadBookingPhoto.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_UploadBookingPhoto.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                        txt_CD_AdharCard.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_AdharCard.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                        txt_CD_ElectionCard.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_ElectionCard.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                        txt_CD_PassportSize.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                        txt_CD_AdharCard2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_AdharCard2.setVisibility(View.GONE);
                    }


                    if(response.body().getData().get(id_pos).getElection_back_check().equals("0")){
                        txt_CD_ElectionCard2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_ElectionCard2.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                        txt_CD_PassportSize2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize2.setVisibility(View.GONE);
                    }


                    /*txt_CD_UploadBookingPhoto.setVisibility(View.VISIBLE);
                    txt_CD_AdharCard.setVisibility(View.VISIBLE);
                    txt_CD_ElectionCard.setVisibility(View.VISIBLE);
                    txt_CD_PassportSize.setVisibility(View.VISIBLE);
                    txt_CD_AdharCard2.setVisibility(View.VISIBLE);
                    txt_CD_ElectionCard2.setVisibility(View.VISIBLE);
                    txt_CD_PassportSize2.setVisibility(View.VISIBLE);

                    img_CD_Booking.setVisibility(View.VISIBLE);
                    img_CD_AdharCard.setVisibility(View.VISIBLE);
                    img_CD_ElectionCard.setVisibility(View.VISIBLE);
                    img_CD_PassportSize.setVisibility(View.VISIBLE);
                    img_CD_AdharCard2.setVisibility(View.VISIBLE);
                    img_CD_ElectionCard2.setVisibility(View.VISIBLE);
                    img_CD_PassportSize2.setVisibility(View.GONE);*/
                }



                if (response.body().getData().get(id_pos).getB_type().equals("agricultural")) {
                    txt_CD_PassportSize.setVisibility(View.VISIBLE);
                    img_CD_PassportSize.setVisibility(View.VISIBLE);

                    txt_CD_PassportSize2.setVisibility(View.VISIBLE);
                    img_CD_PassportSize2.setVisibility(View.VISIBLE);

                    if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                        txt_CD_PassportSize.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize.setVisibility(View.GONE);
                    }

                    if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                        txt_CD_PassportSize2.setVisibility(View.VISIBLE);
                    }
                    else{
                        txt_CD_PassportSize2.setVisibility(View.GONE);
                    }

                } else {
                    txt_CD_PassportSize.setVisibility(View.GONE);
                    img_CD_PassportSize.setVisibility(View.GONE);

                    txt_CD_PassportSize2.setVisibility(View.GONE);
                    img_CD_PassportSize2.setVisibility(View.GONE);
                }


                //--------------------------------------------------------------------------------------

                if(response.body().getData().get(id_pos).getBno_check().equals("0")){
                    edt_ADBooking_BookingNo.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingNo.setFocusable(false);
                    edt_ADBooking_BookingNo.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getB_date_check().equals("0")){
                    edt_ADBooking_BookingDate.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingDate.setFocusable(false);
                    edt_ADBooking_BookingDate.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getB_type_check().equals("0")){
                    edt_ADBooking_BookingType.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingType.setFocusable(false);
                    edt_ADBooking_BookingType.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getEmp_check().equals("0")){
                    edt_ADBooking_BookingLoginName.setFocusable(true);
                }
                else{
                    edt_ADBooking_BookingLoginName.setFocusable(false);
                    edt_ADBooking_BookingLoginName.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getFname_check().equals("0")){
                    edt_CD_Fname.setFocusable(true);
                }
                else{
                    edt_CD_Fname.setFocusable(false);
                    edt_CD_Fname.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLname_check().equals("0")){
                    edt_CD_LastName.setFocusable(true);
                }
                else{
                    edt_CD_LastName.setFocusable(false);
                    edt_CD_LastName.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getSname_check().equals("0")){
                    edt_CD_Surname.setFocusable(true);
                }
                else{
                    edt_CD_Surname.setFocusable(false);
                    edt_CD_Surname.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getMobileno_check().equals("0")){
                    edt_CD_MobileNo.setFocusable(true);
                }
                else{
                    edt_CD_MobileNo.setFocusable(false);
                    edt_CD_MobileNo.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getWhno_check().equals("0")){
                    edt_CD_WhatsappNo.setFocusable(true);
                }
                else{
                    edt_CD_WhatsappNo.setFocusable(false);
                    edt_CD_WhatsappNo.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRef_name_check().equals("0")){
                    edt_CD_ReferenceName.setFocusable(true);
                }
                else{
                    edt_CD_ReferenceName.setFocusable(false);
                    edt_CD_ReferenceName.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRef_no_check().equals("0")){
                    edt_CD_ReferenceNo.setFocusable(true);
                }
                else{
                    edt_CD_ReferenceNo.setFocusable(false);
                    edt_CD_ReferenceNo.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getState_check().equals("0")){
                    edt_CD_State.setFocusable(true);
                }
                else{
                    edt_CD_State.setFocusable(false);
                    edt_CD_State.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCity_check().equals("0")){
                    edt_CD_City.setFocusable(true);
                }
                else{
                    edt_CD_City.setFocusable(false);
                    edt_CD_City.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDistric_check().equals("0")){
                    edt_CD_District.setFocusable(true);
                }
                else{
                    edt_CD_District.setFocusable(false);
                    edt_CD_District.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getVillage_check().equals("0")){
                    edt_CD_Village.setFocusable(true);
                }
                else{
                    edt_CD_Village.setFocusable(false);
                    edt_CD_Village.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getEmp_check().equals("0")){
                    edt_CD_EmployeName.setFocusable(true);
                }
                else{
                    edt_CD_EmployeName.setFocusable(false);
                    edt_CD_EmployeName.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(0).getTehsill_check().equals("0")){
                    edt_CD_Taluko.setFocusable(true);
                }
                else{
                    edt_CD_Taluko.setFocusable(false);
                    edt_CD_Taluko.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getProduct_name_check().equals("0")){
                    edt_PD_PurchaseName.setFocusable(true);
                }
                else{
                    edt_PD_PurchaseName.setFocusable(false);
                    edt_PD_PurchaseName.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getModel_name_check().equals("0")){
                    edt_PD_ModelName.setFocusable(true);
                }
                else{
                    edt_PD_ModelName.setFocusable(false);
                    edt_PD_ModelName.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getP_desc_check().equals("0")){
                    edt_PD_Description.setFocusable(true);
                }
                else{
                    edt_PD_Description.setFocusable(false);
                    edt_PD_Description.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDeal_price_check().equals("0")){
                    edt_PriceD_price.setFocusable(true);
                }
                else{
                    edt_PriceD_price.setFocusable(false);
                    edt_PriceD_price.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDeal_price_in_word_check().equals("0")){
                    edt_PriceD_priceInWord.setFocusable(true);
                }
                else{
                    edt_PriceD_priceInWord.setFocusable(false);
                    edt_PriceD_priceInWord.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getGst_check().equals("0")){
                    edt_PriceD_GST.setFocusable(true);
                }
                else{
                    edt_PriceD_GST.setFocusable(false);
                    edt_PriceD_GST.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRto_check().equals("0")){
                    edt_RTO_RtoMain.setFocusable(true);
                }
                else{
                    edt_RTO_RtoMain.setFocusable(false);
                    edt_RTO_RtoMain.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getRto_tax_check().equals("0")){
                    edt_RTO_RtoTax.setFocusable(true);
                }
                else{
                    edt_RTO_RtoTax.setFocusable(false);
                    edt_RTO_RtoTax.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getRto_passing_check().equals("0")){
                    edt_RTO_RtoPassing.setFocusable(true);
                }
                else{
                    edt_RTO_RtoPassing.setFocusable(false);
                    edt_RTO_RtoPassing.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getInsurance_check().equals("0")){
                    edt_RTO_Insurance.setFocusable(true);
                }
                else{
                    edt_RTO_Insurance.setFocusable(false);
                    edt_RTO_Insurance.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAgent_fee_check().equals("0")){
                    edt_RTO_AgentFees.setFocusable(true);
                }
                else{
                    edt_RTO_AgentFees.setFocusable(false);
                    edt_RTO_AgentFees.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNumber_plat_check().equals("0")){
                    edt_RTO_NumberPlat.setFocusable(true);
                }
                else{
                    edt_RTO_NumberPlat.setFocusable(false);
                    edt_RTO_NumberPlat.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getLoan_charge_check().equals("0")){
                    edt_RTO_LoanCharge.setFocusable(true);
                }
                else{
                    edt_RTO_LoanCharge.setFocusable(false);
                    edt_RTO_LoanCharge.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getBooking_amt_check().equals("0")){
                    edt_DownP_BookingAmount.setFocusable(true);
                }
                else{
                    edt_DownP_BookingAmount.setFocusable(false);
                    edt_DownP_BookingAmount.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAmount_check().equals("0")){
                    edt_DownP_CashAmount.setFocusable(true);
                }
                else{
                    edt_DownP_CashAmount.setFocusable(false);
                    edt_DownP_CashAmount.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCheck_date_check().equals("0")){
                    edt_DownP_ChequeDate.setFocusable(true);
                }
                else{
                    edt_DownP_ChequeDate.setFocusable(false);
                    edt_DownP_ChequeDate.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getC_amount_check().equals("0")){
                    edt_DownP_ChequeAmount.setFocusable(true);
                }
                else{
                    edt_DownP_ChequeAmount.setFocusable(false);
                    edt_DownP_ChequeAmount.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNeft_rtgs_date_check().equals("0")){
                    edt_DownP_NEFT_RTGS_date.setFocusable(true);
                }
                else{
                    edt_DownP_NEFT_RTGS_date.setFocusable(false);
                    edt_DownP_NEFT_RTGS_date.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getNeft_rtgs_amt_check().equals("0")){
                    edt_DownP_NEFT_RTGSAmount.setFocusable(true);
                }
                else{
                    edt_DownP_NEFT_RTGSAmount.setFocusable(false);
                    edt_DownP_NEFT_RTGSAmount.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getMake_check().equals("0")){
                    edt_DownP_SelectMake.setFocusable(true);
                }
                else{
                    edt_DownP_SelectMake.setFocusable(false);
                    edt_DownP_SelectMake.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getModel_check().equals("0")){
                    edt_DownP_ModelVehicle.setFocusable(true);
                }
                else{
                    edt_DownP_ModelVehicle.setFocusable(false);
                    edt_DownP_ModelVehicle.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")){
                    edt_DownP_oldAmount.setFocusable(true);
                }
                else{
                    edt_DownP_oldAmount.setFocusable(false);
                    edt_DownP_oldAmount.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getM_year_check().equals("0")){
                    edt_DownP_ManufactureYear.setFocusable(true);
                }
                else{
                    edt_DownP_ManufactureYear.setFocusable(false);
                    edt_DownP_ManufactureYear.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getPaper_expence_check().equals("0")){
                    edt_DownP_PaperExchange.setFocusable(true);
                }
                else{
                    edt_DownP_PaperExchange.setFocusable(false);
                    edt_DownP_PaperExchange.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getOld_t_amount_check().equals("0")){
                    edt_DownP_oldTractorAmount.setFocusable(true);
                }
                else{
                    edt_DownP_oldTractorAmount.setFocusable(false);
                    edt_DownP_oldTractorAmount.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getNoc_check().equals("0")){
                    edt_DownP_NOC.setFocusable(true);
                }
                else{
                    edt_DownP_NOC.setFocusable(false);
                    edt_DownP_NOC.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getHood_check().equals("0")){
                    edt_CS_Hood.setFocusable(true);
                }
                else{
                    edt_CS_Hood.setFocusable(false);
                    edt_CS_Hood.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getToplink_check().equals("0")){
                    edt_CS_TopLink.setFocusable(true);
                }
                else{
                    edt_CS_TopLink.setFocusable(false);
                    edt_CS_TopLink.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDrowbar_check().equals("0")){
                    edt_CS_DrawBar.setFocusable(true);
                }
                else{
                    edt_CS_DrawBar.setFocusable(false);
                    edt_CS_DrawBar.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getToolkit_check().equals("0")){
                    edt_CS_ToolKit.setFocusable(true);
                }
                else{
                    edt_CS_ToolKit.setFocusable(false);
                    edt_CS_ToolKit.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getBumper_check().equals("0")){
                    edt_CS_Bumper.setFocusable(true);
                }
                else{
                    edt_CS_Bumper.setFocusable(false);
                    edt_CS_Bumper.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getHitch_check().equals("0")){
                    edt_CS_Hitch.setFocusable(true);
                }
                else{
                    edt_CS_Hitch.setFocusable(false);
                    edt_CS_Hitch.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getDescription_check().equals("0")){
                    edt_CS_Description.setFocusable(true);
                }
                else{
                    edt_CS_Description.setFocusable(false);
                    edt_CS_Description.setTextColor(Color.parseColor("#43a047"));
                }


                if(response.body().getData().get(id_pos).getB_p_photo_check().equals("0")){
                    edt_CD_PassBook.setFocusable(true);
                }
                else{
                    edt_CD_PassBook.setFocusable(false);
                    edt_CD_PassBook.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getCheck_photo_check().equals("0")){
                    edt_CD_ChequeBook .setFocusable(true);
                }
                else{
                    edt_CD_ChequeBook.setFocusable(false);
                    edt_CD_ChequeBook.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getSkim_check().equals("0")){
                    edt_CS_ConsumerSkim.setFocusable(true);
                }else{
                    edt_CS_ConsumerSkim.setFocusable(false);
                    edt_CS_ConsumerSkim.setTextColor(Color.parseColor("#43a047"));
                }

                if(response.body().getData().get(id_pos).getAtype_check().equals("0")){
                    edt_CD_PaymentOption.setFocusable(true);
                }else{
                    edt_CD_PaymentOption.setFocusable(false);
                    edt_CD_PaymentOption.setTextColor(Color.parseColor("#43a047"));
                }


                /** ************************************************************************************* */



               /* if(response.body().getData().get(id_pos).getB_photo_check().equals("0")){
                    txt_CD_UploadBookingPhoto.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_UploadBookingPhoto.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getAdhar_photo_check().equals("0")){
                    txt_CD_AdharCard.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_AdharCard.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getEcard_photo_check().equals("0")){
                    txt_CD_ElectionCard.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_ElectionCard.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getP_photo_check().equals("0")){
                    txt_CD_PassportSize.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_PassportSize.setVisibility(View.GONE);
                }*/

                if(response.body().getData().get(id_pos).getCheck1_photo_check().equals("0")){
                    txt_DownP_UploadCheque.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadCheque.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getNeft_rtgs_photo_check().equals("0")){
                    txt_DownP_UploadNEFT_RTGS.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadNEFT_RTGS.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getNoc_photo_check().equals("0")){
                    txt_DownP_UploadNocPhoto.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadNocPhoto.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getOld_vehicle_photo_check().equals("0")){
                    txt_DownP_UploadOldVehicle.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadOldVehicle.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getRecbook_photo_check().equals("0")){
                    txt_DownP_UploadRCBook.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadRCBook.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getEc_photo_check().equals("0")){
                    txt_DownP_UploadElectionPhoto.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadElectionPhoto.setVisibility(View.GONE);
                }

                // //////////////////////////////////////////

               /* if(response.body().getData().get(id_pos).getAdhar_back_check().equals("0")){
                    txt_CD_AdharCard2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_AdharCard2.setVisibility(View.GONE);
                }


                if(response.body().getData().get(id_pos).getElection_back_check().equals("0")){
                    txt_CD_ElectionCard2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_ElectionCard2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getP_photo_back_check().equals("0")){
                    txt_CD_PassportSize2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_CD_PassportSize2.setVisibility(View.GONE);
                }*/


                if(response.body().getData().get(id_pos).getRcbook_back_check().equals("0")){
                    txt_DownP_UploadRCBook2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadRCBook2.setVisibility(View.GONE);
                }

                if(response.body().getData().get(id_pos).getElection_back_check().equals("0")){
                    txt_DownP_UploadElectionPhoto2.setVisibility(View.VISIBLE);
                }
                else{
                    txt_DownP_UploadElectionPhoto2.setVisibility(View.GONE);
                }


                /** ********************************************************************************** */


                String data =  response.body().getData().get(id_pos).getBooking_amt();//"cash,bank"
              //  Toast.makeText(BookingPhaseOneActivity.this, ""+data, Toast.LENGTH_SHORT).show();
                String[] res = data.split(",");


                txtDPCashAmount.setVisibility(View.GONE);
                edt_DownP_CashAmount.setVisibility(View.GONE);

                edt_DownP_BankOption.setVisibility(View.GONE);
                edt_DownP_ChequeDate.setVisibility(View.GONE);
                edt_DownP_ChequeAmount.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGS_date.setVisibility(View.GONE);
                edt_DownP_NEFT_RTGSAmount.setVisibility(View.GONE);
                edt_DownP_SelectMake.setVisibility(View.GONE);
                edt_DownP_ModelVehicle.setVisibility(View.GONE);
                edt_DownP_oldAmount.setVisibility(View.GONE);
                edt_DownP_ManufactureYear.setVisibility(View.GONE);
                edt_DownP_PaperExchange.setVisibility(View.GONE);
                edt_DownP_oldTractorAmount.setVisibility(View.GONE);
                edt_DownP_NOC.setVisibility(View.GONE);

                lin_dp_cheque.setVisibility(View.GONE);
                lin_dp_NEFT_RTGS.setVisibility(View.GONE);
                lin_dp_NocPhoto.setVisibility(View.GONE);
                lin_dp_OldVehicle.setVisibility(View.GONE);
                lin_dp_Rcbook.setVisibility(View.GONE);
                lin_dp_Election.setVisibility(View.GONE);

                lin_dp_Rcbook2.setVisibility(View.GONE);
                lin_dp_Election2.setVisibility(View.GONE);

                txtDPBankOption.setVisibility(View.GONE);
                txtDPChequeDate.setVisibility(View.GONE);
                txtDPChequeAmount.setVisibility(View.GONE);
                txtDPNEFT_RTGSdate.setVisibility(View.GONE);
                txtDPNEFT_RTGSAmount.setVisibility(View.GONE);
                txtDPMake.setVisibility(View.GONE);
                txtDPManufectureYear.setVisibility(View.GONE);
                txtDPOldAmount.setVisibility(View.GONE);
                txtDPModelName.setVisibility(View.GONE);
                txtDPPaperExpense.setVisibility(View.GONE);
                txtDPOldTractorAmount.setVisibility(View.GONE);
                txtDPNoc.setVisibility(View.GONE);


                for (int i = 0; i < res.length; i++) {
                    mydata = res[i];
                   // Toast.makeText(BookingPhaseOneActivity.this, "yes" + mydata, Toast.LENGTH_SHORT).show();

                    String uu = mydata.trim();
                   // Log.e("TAG", "onResponse: " + uu);
                    //   Log.d("TAG", "onResponse: "+mydata);

                    if (uu.equals("Cash")) {
                        txtDPCashAmount.setVisibility(View.VISIBLE);
                        edt_DownP_CashAmount.setVisibility(View.VISIBLE);

                       // Log.e("TAG", "onResponse:casting ");
                       // Toast.makeText(BookingPhaseOneActivity.this, "casting", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Old Vehicle")) {
                       // Log.e("TAG", "onResponse:casting45 ");
                        txtDPMake.setVisibility(View.VISIBLE);
                        edt_DownP_SelectMake.setVisibility(View.VISIBLE);
                        txtDPModelName.setVisibility(View.VISIBLE);
                        edt_DownP_ModelVehicle.setVisibility(View.VISIBLE);
                        txtDPManufectureYear.setVisibility(View.VISIBLE);
                        edt_DownP_ManufactureYear.setVisibility(View.VISIBLE);
                        txtDPOldAmount.setVisibility(View.VISIBLE);
                        txtDPPaperExpense.setVisibility(View.VISIBLE);
                        edt_DownP_PaperExchange.setVisibility(View.VISIBLE);

                        if (response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")) {
                            edt_DownP_oldAmount.setVisibility(View.GONE);
                            txtDPOldAmount.setVisibility(View.GONE);
                          //  edt_DownP_oldTractorAmount.setVisibility(View.GONE);
                        } else {
                            edt_DownP_oldAmount.setVisibility(View.VISIBLE);
                            txtDPOldAmount.setVisibility(View.VISIBLE);
                           // edt_DownP_oldTractorAmount.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getData().get(id_pos).getNoc().equals("No")) {
                            lin_dp_NocPhoto.setVisibility(View.GONE);
                        } else {
//                            lin_dp_NocPhoto.setVisibility(View.VISIBLE);
                        }

                        txtDPOldTractorAmount.setVisibility(View.VISIBLE);
                       // edt_DownP_oldAmount.setVisibility(View.VISIBLE);
                        edt_DownP_oldTractorAmount.setVisibility(View.VISIBLE);
                        txtDPNoc.setVisibility(View.VISIBLE);
                        edt_DownP_NOC.setVisibility(View.VISIBLE);

                       // lin_dp_NocPhoto.setVisibility(View.VISIBLE);
//                        lin_dp_OldVehicle.setVisibility(View.VISIBLE);
//                        lin_dp_Rcbook.setVisibility(View.VISIBLE);
//                        lin_dp_Election.setVisibility(View.VISIBLE);

//                        lin_dp_Rcbook2.setVisibility(View.VISIBLE);
//                        lin_dp_Election2.setVisibility(View.VISIBLE);
                      //  Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();
                    }

                    if (uu.equals("Bank")) {
                       // Log.e("TAG", "onResponse:casting450 ");
                       // Toast.makeText(BookingPhaseOneActivity.this, "casting5", Toast.LENGTH_SHORT).show();

                        txtDPBankOption.setVisibility(View.VISIBLE);
                        edt_DownP_BankOption.setVisibility(View.VISIBLE);

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")) {
                            txtDPChequeDate.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeDate.setVisibility(View.VISIBLE);
                            txtDPChequeAmount.setVisibility(View.VISIBLE);
                            edt_DownP_ChequeAmount.setVisibility(View.VISIBLE);
//                            lin_dp_cheque.setVisibility(View.VISIBLE);

                            txtDPNEFT_RTGSdate.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGS_date.setVisibility(View.GONE);
                            txtDPNEFT_RTGSAmount.setVisibility(View.GONE);
                            edt_DownP_NEFT_RTGSAmount.setVisibility(View.GONE);
                            lin_dp_NEFT_RTGS.setVisibility(View.GONE);
                        }

                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS")) {
                            txtDPNEFT_RTGSdate.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGS_date.setVisibility(View.VISIBLE);
                            txtDPNEFT_RTGSAmount.setVisibility(View.VISIBLE);
                            edt_DownP_NEFT_RTGSAmount.setVisibility(View.VISIBLE);
//                            lin_dp_NEFT_RTGS.setVisibility(View.VISIBLE);

                            txtDPChequeDate.setVisibility(View.GONE);
                            edt_DownP_ChequeDate.setVisibility(View.GONE);
                            txtDPChequeAmount.setVisibility(View.GONE);
                            edt_DownP_ChequeAmount.setVisibility(View.GONE);
                            lin_dp_cheque.setVisibility(View.GONE);
                        }
                    }
                }

             //   Toast.makeText(BookingPhaseOneActivity.this, "hii" + mydata, Toast.LENGTH_SHORT).show();

                //if(response.body().getData().get(id_pos).getBooking_amt().equals("Cash")){
//
//
//                        txtDPCashAmount.setVisibility(View.VISIBLE);
//                        edt_DownP_CashAmount.setVisibility(View.VISIBLE);
//
//                        edt_DownP_BankOption.setVisibility(View.GONE);
//                        edt_DownP_ChequeDate.setVisibility(View.GONE);
//                        edt_DownP_ChequeAmount.setVisibility(View.GONE);
//                        edt_DownP_NEFT_RTGS_date.setVisibility(View.GONE);
//                        edt_DownP_NEFT_RTGSAmount.setVisibility(View.GONE);
//                        edt_DownP_SelectMake.setVisibility(View.GONE);
//                        edt_DownP_ModelVehicle.setVisibility(View.GONE);
//                        edt_DownP_oldAmount.setVisibility(View.GONE);
//                        edt_DownP_ManufactureYear.setVisibility(View.GONE);
//                        edt_DownP_PaperExchange.setVisibility(View.GONE);
//                        edt_DownP_oldTractorAmount.setVisibility(View.GONE);
//                        edt_DownP_NOC.setVisibility(View.GONE);
//
//                        lin_dp_cheque.setVisibility(View.GONE);
//                        lin_dp_NEFT_RTGS.setVisibility(View.GONE);
//                        lin_dp_NocPhoto.setVisibility(View.GONE);
//                        lin_dp_OldVehicle.setVisibility(View.GONE);
//                        lin_dp_Rcbook.setVisibility(View.GONE);
//                        lin_dp_Election.setVisibility(View.GONE);
//
//                        lin_dp_Rcbook2.setVisibility(View.GONE);
//                        lin_dp_Election2.setVisibility(View.GONE);
//
//                        txtDPBankOption.setVisibility(View.GONE);
//                        txtDPChequeDate.setVisibility(View.GONE);
//                        txtDPChequeAmount.setVisibility(View.GONE);
//                        txtDPNEFT_RTGSdate.setVisibility(View.GONE);
//                        txtDPNEFT_RTGSAmount.setVisibility(View.GONE);
//                        txtDPMake.setVisibility(View.GONE);
//                        txtDPManufectureYear.setVisibility(View.GONE);
//                        txtDPOldAmount.setVisibility(View.GONE);
//                        txtDPModelName.setVisibility(View.GONE);
//                        txtDPPaperExpense.setVisibility(View.GONE);
//                        txtDPOldTractorAmount.setVisibility(View.GONE);
//                        txtDPNoc.setVisibility(View.GONE);
//                    }
//
//
//                    if(response.body().getData().get(id_pos).getBooking_amt().equals("Bank")){
//
//
//                        txtDPCashAmount.setVisibility(View.GONE);
//                        edt_DownP_CashAmount.setVisibility(View.GONE);
//
//                        txtDPBankOption.setVisibility(View.VISIBLE);
//                        edt_DownP_BankOption.setVisibility(View.VISIBLE);
//
//                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("Cheque")) {
//                            txtDPChequeDate.setVisibility(View.VISIBLE);
//                            edt_DownP_ChequeDate.setVisibility(View.VISIBLE);
//                            txtDPChequeAmount.setVisibility(View.VISIBLE);
//                            edt_DownP_ChequeAmount.setVisibility(View.VISIBLE);
//                            lin_dp_cheque.setVisibility(View.VISIBLE);
//
//                            txtDPNEFT_RTGSdate.setVisibility(View.GONE);
//                            edt_DownP_NEFT_RTGS_date.setVisibility(View.GONE);
//                            txtDPNEFT_RTGSAmount.setVisibility(View.GONE);
//                            edt_DownP_NEFT_RTGSAmount.setVisibility(View.GONE);
//                            lin_dp_NEFT_RTGS.setVisibility(View.GONE);
//
//                            txtDPMake.setVisibility(View.GONE);
//                            edt_DownP_SelectMake.setVisibility(View.GONE);
//                            txtDPModelName.setVisibility(View.GONE);
//                            edt_DownP_ModelVehicle.setVisibility(View.GONE);
//                            txtDPManufectureYear.setVisibility(View.GONE);
//                            edt_DownP_ManufactureYear.setVisibility(View.GONE);
//                            txtDPOldAmount.setVisibility(View.GONE);
//                            edt_DownP_oldAmount.setVisibility(View.GONE);
//                            txtDPPaperExpense.setVisibility(View.GONE);
//                            edt_DownP_PaperExchange.setVisibility(View.GONE);
//                            txtDPOldTractorAmount.setVisibility(View.GONE);
//                            edt_DownP_oldTractorAmount.setVisibility(View.GONE);
//                            txtDPNoc.setVisibility(View.GONE);
//                            edt_DownP_NOC.setVisibility(View.GONE);
//
//                            lin_dp_NocPhoto.setVisibility(View.GONE);
//                            lin_dp_OldVehicle.setVisibility(View.GONE);
//                            lin_dp_Rcbook.setVisibility(View.GONE);
//                            lin_dp_Election.setVisibility(View.GONE);
//
//                            lin_dp_Rcbook2.setVisibility(View.GONE);
//                            lin_dp_Election2.setVisibility(View.GONE);
//                        }
//
//                        if (response.body().getData().get(id_pos).getCheck_neft_rtgs().equals("NEFT/RTGS")) {
//                            txtDPNEFT_RTGSdate.setVisibility(View.VISIBLE);
//                            edt_DownP_NEFT_RTGS_date.setVisibility(View.VISIBLE);
//                            txtDPNEFT_RTGSAmount.setVisibility(View.VISIBLE);
//                            edt_DownP_NEFT_RTGSAmount.setVisibility(View.VISIBLE);
//                            lin_dp_NEFT_RTGS.setVisibility(View.VISIBLE);
//
//                            txtDPChequeDate.setVisibility(View.GONE);
//                            edt_DownP_ChequeDate.setVisibility(View.GONE);
//                            txtDPChequeAmount.setVisibility(View.GONE);
//                            edt_DownP_ChequeAmount.setVisibility(View.GONE);
//                            lin_dp_cheque.setVisibility(View.GONE);
//
//                            txtDPMake.setVisibility(View.GONE);
//                            edt_DownP_SelectMake.setVisibility(View.GONE);
//                            txtDPModelName.setVisibility(View.GONE);
//                            edt_DownP_ModelVehicle.setVisibility(View.GONE);
//                            txtDPManufectureYear.setVisibility(View.GONE);
//                            edt_DownP_ManufactureYear.setVisibility(View.GONE);
//                            txtDPOldAmount.setVisibility(View.GONE);
//                            edt_DownP_oldAmount.setVisibility(View.GONE);
//                            txtDPPaperExpense.setVisibility(View.GONE);
//                            edt_DownP_PaperExchange.setVisibility(View.GONE);
//                            txtDPOldTractorAmount.setVisibility(View.GONE);
//                            edt_DownP_oldTractorAmount.setVisibility(View.GONE);
//                            txtDPNoc.setVisibility(View.GONE);
//                            edt_DownP_NOC.setVisibility(View.GONE);
//
//                            lin_dp_NocPhoto.setVisibility(View.GONE);
//                            lin_dp_OldVehicle.setVisibility(View.GONE);
//                            lin_dp_Rcbook.setVisibility(View.GONE);
//                            lin_dp_Election.setVisibility(View.GONE);
//
//                            lin_dp_Rcbook2.setVisibility(View.GONE);
//                            lin_dp_Election2.setVisibility(View.GONE);
//                        }
//
//                    }
//
//                    //Old Vehicle
//                    if(response.body().getData().get(id_pos).getBooking_amt().equals("Old Vehicle")){
//
//                        txtDPMake.setVisibility(View.VISIBLE);
//                        edt_DownP_SelectMake.setVisibility(View.VISIBLE);
//                        txtDPModelName.setVisibility(View.VISIBLE);
//                        edt_DownP_ModelVehicle.setVisibility(View.VISIBLE);
//                        txtDPManufectureYear.setVisibility(View.VISIBLE);
//                        edt_DownP_ManufactureYear.setVisibility(View.VISIBLE);
//                        txtDPOldAmount.setVisibility(View.VISIBLE);
//                        txtDPPaperExpense.setVisibility(View.VISIBLE);
//                        edt_DownP_PaperExchange.setVisibility(View.VISIBLE);
//
//                        if (response.body().getData().get(id_pos).getPaper_expence().equals("Dealer")) {
//                            edt_DownP_oldAmount.setVisibility(View.GONE);
//                        } else {
//                            edt_DownP_oldAmount.setVisibility(View.VISIBLE);
//                        }
//
//                        if (response.body().getData().get(id_pos).getNoc().equals("Yes")) {
//                            lin_dp_NocPhoto.setVisibility(View.VISIBLE);
//                        } else {
//                            lin_dp_NocPhoto.setVisibility(View.GONE);
//                        }
//
//                        txtDPOldTractorAmount.setVisibility(View.VISIBLE);
//                        edt_DownP_oldTractorAmount.setVisibility(View.VISIBLE);
//                        txtDPNoc.setVisibility(View.VISIBLE);
//                        edt_DownP_NOC.setVisibility(View.VISIBLE);
//
//                        lin_dp_NocPhoto.setVisibility(View.VISIBLE);
//                        lin_dp_OldVehicle.setVisibility(View.VISIBLE);
//                        lin_dp_Rcbook.setVisibility(View.VISIBLE);
//                        lin_dp_Election.setVisibility(View.VISIBLE);
//
//                        lin_dp_Rcbook2.setVisibility(View.VISIBLE);
//                        lin_dp_Election2.setVisibility(View.VISIBLE);
//
//                        txtDPCashAmount.setVisibility(View.GONE);
//                        edt_DownP_CashAmount.setVisibility(View.GONE);
//                        txtDPBankOption.setVisibility(View.GONE);
//                        edt_DownP_BankOption.setVisibility(View.GONE);
//                        txtDPChequeDate.setVisibility(View.GONE);
//                        edt_DownP_ChequeDate.setVisibility(View.GONE);
//                        txtDPChequeAmount.setVisibility(View.GONE);
//                        edt_DownP_ChequeAmount.setVisibility(View.GONE);
//                        lin_dp_cheque.setVisibility(View.GONE);
//                        txtDPNEFT_RTGSdate.setVisibility(View.GONE);
//                        edt_DownP_NEFT_RTGS_date.setVisibility(View.GONE);
//                        txtDPNEFT_RTGSAmount.setVisibility(View.GONE);
//                        edt_DownP_NEFT_RTGSAmount.setVisibility(View.GONE);
//                        lin_dp_NEFT_RTGS.setVisibility(View.GONE);
//                    }

                    edt_RTO_RtoMain.setVisibility(View.VISIBLE);

                    if (response.body().getData().get(id_pos).getRto().equals("No")) {
                        edt_RTO_RtoTax.setVisibility(View.GONE);
                        edt_RTO_RtoPassing.setVisibility(View.GONE);
                        edt_RTO_Insurance.setVisibility(View.GONE);
                        edt_RTO_AgentFees.setVisibility(View.GONE);
                        edt_RTO_NumberPlat.setVisibility(View.GONE);
                        edt_RTO_LoanCharge.setVisibility(View.GONE);

                        txtRTOTax.setVisibility(View.GONE);
                        txtRTOPassing.setVisibility(View.GONE);
                        txtInsurance.setVisibility(View.GONE);
                        txtAgentFees.setVisibility(View.GONE);
                        txtNumberPlat.setVisibility(View.GONE);
                        txtLoanCharge.setVisibility(View.GONE);
                    } else {
                        edt_RTO_RtoTax.setVisibility(View.VISIBLE);
                        edt_RTO_RtoPassing.setVisibility(View.VISIBLE);
                        edt_RTO_Insurance.setVisibility(View.VISIBLE);
                        edt_RTO_AgentFees.setVisibility(View.VISIBLE);
                        edt_RTO_NumberPlat.setVisibility(View.VISIBLE);
                        edt_RTO_LoanCharge.setVisibility(View.VISIBLE);

                        txtRTOTax.setVisibility(View.VISIBLE);
                        txtRTOPassing.setVisibility(View.VISIBLE);
                        txtInsurance.setVisibility(View.VISIBLE);
                        txtAgentFees.setVisibility(View.VISIBLE);
                        txtNumberPlat.setVisibility(View.VISIBLE);
                        txtLoanCharge.setVisibility(View.VISIBLE);
                    }


                    if (response.body().getData().get(id_pos).getSkim().equals("No")) {

                        edt_CS_Hood.setVisibility(View.GONE);
                        edt_CS_TopLink.setVisibility(View.GONE);
                        edt_CS_DrawBar.setVisibility(View.GONE);
                        edt_CS_ToolKit.setVisibility(View.GONE);
                        edt_CS_Bumper.setVisibility(View.GONE);
                        edt_CS_Hitch.setVisibility(View.GONE);
                        //   edt_CS_Description.setVisibility(View.GONE);

                        txt_cs_Hood.setVisibility(View.GONE);
                        txt_cs_TopLink.setVisibility(View.GONE);
                        txt_cs_Drawbar.setVisibility(View.GONE);
                        txt_cs_ToolKit.setVisibility(View.GONE);
                        txt_cs_Bumper.setVisibility(View.GONE);
                        txt_cs_Hitch.setVisibility(View.GONE);
                        // txt_cs_Description.setVisibility(View.GONE);

                    } else {
                        edt_CS_Hood.setVisibility(View.VISIBLE);
                        edt_CS_TopLink.setVisibility(View.VISIBLE);
                        edt_CS_DrawBar.setVisibility(View.VISIBLE);
                        edt_CS_ToolKit.setVisibility(View.VISIBLE);
                        edt_CS_Bumper.setVisibility(View.VISIBLE);
                        edt_CS_Hitch.setVisibility(View.VISIBLE);
                        //   edt_CS_Description.setVisibility(View.VISIBLE);

                        txt_cs_Hood.setVisibility(View.VISIBLE);
                        txt_cs_TopLink.setVisibility(View.VISIBLE);
                        txt_cs_Drawbar.setVisibility(View.VISIBLE);
                        txt_cs_ToolKit.setVisibility(View.VISIBLE);
                        txt_cs_Bumper.setVisibility(View.VISIBLE);
                        txt_cs_Hitch.setVisibility(View.VISIBLE);
                        //  txt_cs_Description.setVisibility(View.VISIBLE);
                    }

               // }
                edt_ADBooking_BookingNo.setText(response.body().getData().get(id_pos).getBno());
                edt_ADBooking_BookingType.setText(response.body().getData().get(id_pos).getB_type());
                edt_ADBooking_BookingDate.setText(response.body().getData().get(id_pos).getB_date());
                edt_ADBooking_BookingLoginName.setText(response.body().getData().get(id_pos).getEmp());


                edt_CD_Fname.setText(response.body().getData().get(id_pos).getFname());
                edt_CD_LastName.setText(response.body().getData().get(id_pos).getLname()+" ");
                edt_CD_Surname.setText(response.body().getData().get(id_pos).getSname());
                edt_CD_MobileNo.setText(response.body().getData().get(id_pos).getMobileno());
                edt_CD_WhatsappNo.setText(response.body().getData().get(id_pos).getWhno());
                edt_CD_ReferenceName.setText(response.body().getData().get(id_pos).getRef_name());
                edt_CD_ReferenceNo.setText(response.body().getData().get(id_pos).getRef_no());
                edt_CD_State.setText(response.body().getData().get(id_pos).getState());
                edt_CD_City.setText(response.body().getData().get(id_pos).getCity());
                edt_CD_District.setText(response.body().getData().get(id_pos).getDistric());
                edt_CD_Village.setText(response.body().getData().get(id_pos).getVillage());
                edt_CD_EmployeName.setText(response.body().getData().get(id_pos).getEmp());
                edt_CD_Taluko.setText(response.body().getData().get(id_pos).getTehsill());
                edt_CD_PassBook.setText(response.body().getData().get(id_pos).getB_p_photo());
                edt_CD_ChequeBook.setText(response.body().getData().get(id_pos).getCheck_photo());
                edt_CD_PaymentOption.setText(response.body().getData().get(id_pos).getAtype());


                edt_PD_PurchaseName.setText(response.body().getData().get(id_pos).getProduct_name());
                edt_PD_ModelName.setText(response.body().getData().get(id_pos).getModel_name());
                edt_PD_Description.setText(response.body().getData().get(id_pos).getP_desc());


                edt_PriceD_price.setText(response.body().getData().get(id_pos).getDeal_price());
                edt_PriceD_priceInWord.setText(response.body().getData().get(id_pos).getDeal_price_in_word());
                edt_PriceD_GST.setText(response.body().getData().get(id_pos).getGst());

                edt_RTO_RtoMain.setText(response.body().getData().get(id_pos).getRto());
                edt_RTO_RtoTax.setText(response.body().getData().get(id_pos).getRto_tax());
                edt_RTO_RtoPassing.setText(response.body().getData().get(id_pos).getRto_passing());
                edt_RTO_Insurance.setText(response.body().getData().get(id_pos).getInsurance());
                edt_RTO_AgentFees.setText(response.body().getData().get(id_pos).getAgent_fee());
                edt_RTO_NumberPlat.setText(response.body().getData().get(id_pos).getNumber_plat());
                edt_RTO_LoanCharge.setText(response.body().getData().get(id_pos).getLoan_charge());

                edt_DownP_BookingAmount.setText(response.body().getData().get(id_pos).getBooking_amt());
                edt_DownP_CashAmount.setText(response.body().getData().get(id_pos).getAmount());
                edt_DownP_BankOption.setText(response.body().getData().get(id_pos).getCheck_neft_rtgs());
                edt_DownP_ChequeDate.setText(response.body().getData().get(id_pos).getCheck_date());
                edt_DownP_ChequeAmount.setText(response.body().getData().get(id_pos).getCheck_amt());
                edt_DownP_NEFT_RTGS_date.setText(response.body().getData().get(id_pos).getNeft_rtgs_date());
                edt_DownP_NEFT_RTGSAmount.setText(response.body().getData().get(id_pos).getNeft_rtgs_amt());
                edt_DownP_SelectMake.setText(response.body().getData().get(id_pos).getMake());
                edt_DownP_ModelVehicle.setText(response.body().getData().get(id_pos).getModel());
                edt_DownP_oldAmount.setText(response.body().getData().get(id_pos).getC_amount());
                edt_DownP_ManufactureYear.setText(response.body().getData().get(id_pos).getM_year());
                edt_DownP_PaperExchange.setText(response.body().getData().get(id_pos).getPaper_expence());
              //  edt_DownP_oldTractorAmount.setText(response.body().getData().get(id_pos).getC_amount());
                edt_DownP_oldTractorAmount.setText(response.body().getData().get(id_pos).getOld_t_amount());
                edt_DownP_NOC.setText(response.body().getData().get(id_pos).getNoc());


                edt_CS_ConsumerSkim.setText(response.body().getData().get(id_pos).getSkim());

                edt_CS_Hood.setText(response.body().getData().get(id_pos).getHood());
                edt_CS_TopLink.setText(response.body().getData().get(id_pos).getToplink());
                edt_CS_DrawBar.setText(response.body().getData().get(id_pos).getDrowbar());
                edt_CS_ToolKit.setText(response.body().getData().get(id_pos).getToolkit());
                edt_CS_Bumper.setText(response.body().getData().get(id_pos).getBumper());
                edt_CS_Hitch.setText(response.body().getData().get(id_pos).getHitch());
                edt_CS_Description.setText(response.body().getData().get(id_pos).getDescription());
            }

            @Override
            public void onFailure(@NotNull Call<BookingUploadModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.d("bookingOne", "onFailure: "+t.getCause()+" "+t.getMessage());
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
              //  Utils.showErrorToast(BookingPhaseOneActivity.this,"No Data Available");
            }
        });

         /* edt_PriceD_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number = Integer.parseInt(edt_PriceD_priceInWord.getText().toString());

                String return_val_in_english =   EnglishNumberToWords.convert(number);

                String numToWord = NumToWord.GFG.convertToWords(number);

                // edt_AddPriceD_PriceInWord.setText(return_val_in_english);
                edt_PriceD_priceInWord.setText(numToWord);
            }

            @Override
            public void afterTextChanged(Editable s) {
                number = Integer.parseInt(edt_PriceD_price.getText().toString());

                String return_val_in_english =   EnglishNumberToWords.convert(number);

                String numToWord = NumToWord.GFG.convertToWords(number);

               // edt_AddPriceD_PriceInWord.setText(return_val_in_english);
                edt_PriceD_priceInWord.setText(numToWord);
            }
        });*/



        txt_CD_UploadBookingPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 2);
            }
        });

        txt_CD_AdharCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 3);
            }
        });

        txt_CD_ElectionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 4);
            }
        });

        txt_CD_PassportSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 5);
            }
        });


        txt_DownP_UploadCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 6);
            }
        });

        txt_DownP_UploadNEFT_RTGS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 7);
            }
        });

        txt_DownP_UploadNocPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 8);
            }
        });

        txt_DownP_UploadOldVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 9);
            }
        });

        txt_DownP_UploadRCBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 10);
            }
        });

        txt_DownP_UploadElectionPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 11);
            }
        });



        txt_CD_AdharCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 12);
            }
        });

        txt_CD_ElectionCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 13);
            }
        });

        txt_CD_PassportSize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 14);
            }
        });


         txt_DownP_UploadRCBook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 15);
            }
        });

        txt_DownP_UploadElectionPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal = new Intent(Intent.ACTION_PICK);
                gal.setType("image/*");
                startActivityForResult(gal, 16);
            }
        });

        btn_BookingPhase_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= new ProgressDialog(BookingPhaseOneActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                //waypath_CD_UploadBookingPhoto
                //waypath_CD_AdharCard
              //  waypath_CD_ElectionCard
                //waypath_CD_PassportSize
                //waypathUploadDPCheque
                //waypathUploadDPNEFT_RTGS
                //waypathUploadDPNocPhoto
                //waypathUploadDPOldVehicle
               // waypathUploadDPElectionPhoto
                //waypathUploadDPRCBook

                MultipartBody.Part CD_UploadBookingPhoto = null;
                MultipartBody.Part CD_AdharCard  = null;
                MultipartBody.Part CD_ElectionCard  = null;
                MultipartBody.Part CD_PassportSize  = null;
                MultipartBody.Part UploadDPCheque  = null;
                MultipartBody.Part UploadDPNEFT_RTGS = null;
                MultipartBody.Part UploadDPNocPhoto = null;
                MultipartBody.Part UploadDPOldVehicle = null;
                MultipartBody.Part UploadDPRCBook = null;
                MultipartBody.Part UploadDPElectionPhoto = null;

                MultipartBody.Part CD_AdharCard2  = null;
                MultipartBody.Part CD_ElectionCard2  = null;
                MultipartBody.Part CD_PassportSize2 = null;

                MultipartBody.Part UploadDPRCBook2 = null;
                MultipartBody.Part UploadDPElectionPhoto2 = null;



                if(waypath_CD_UploadBookingPhoto != null){
                    File file1 = new File(waypath_CD_UploadBookingPhoto);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    CD_UploadBookingPhoto = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }

                if(waypath_CD_AdharCard != null){
                    File file2 = new File(waypath_CD_AdharCard);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    CD_AdharCard = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }

                if(waypath_CD_ElectionCard != null){
                    File file3 = new File(waypath_CD_ElectionCard);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    CD_ElectionCard = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                if(waypath_CD_PassportSize != null){
                    File file4 = new File(waypath_CD_PassportSize);
                    final RequestBody requestBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                    CD_PassportSize = MultipartBody.Part.createFormData("image6",
                            file4.getName(), requestBody4);
                }


                if(waypathUploadDPCheque != null){
                    File file5 = new File(waypathUploadDPCheque);
                    final RequestBody requestBody5 = RequestBody.create(MediaType.parse("image/*"), file5);
                    UploadDPCheque = MultipartBody.Part.createFormData("image7",
                            file5.getName(), requestBody5);
                }

                if(waypathUploadDPNEFT_RTGS != null){
                    File file6 = new File(waypathUploadDPNEFT_RTGS);
                    final RequestBody requestBody6 = RequestBody.create(MediaType.parse("image/*"), file6);
                    UploadDPNEFT_RTGS = MultipartBody.Part.createFormData("image8",
                            file6.getName(), requestBody6);
                }


                if(waypathUploadDPOldVehicle != null){
                    File file8 = new File(waypathUploadDPOldVehicle);
                    final RequestBody requestBody8 = RequestBody.create(MediaType.parse("image/*"), file8);
                    UploadDPOldVehicle = MultipartBody.Part.createFormData("image9",
                            file8.getName(), requestBody8);
                }

                if(waypathUploadDPRCBook != null){
                    File file9 = new File(waypathUploadDPRCBook);
                    final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file9);
                    UploadDPRCBook = MultipartBody.Part.createFormData("image10",
                            file9.getName(), requestBody9);
                }

                if(waypathUploadDPElectionPhoto != null){
                    File file10 = new File(waypathUploadDPElectionPhoto);
                    final RequestBody requestBody9 = RequestBody.create(MediaType.parse("image/*"), file10);
                    UploadDPElectionPhoto = MultipartBody.Part.createFormData("image11",
                            file10.getName(), requestBody9);
                }

                if(waypathUploadDPNocPhoto != null){
                    File file7 = new File(waypathUploadDPNocPhoto);
                    final RequestBody requestBody7 = RequestBody.create(MediaType.parse("image/*"), file7);
                    UploadDPNocPhoto = MultipartBody.Part.createFormData("image12",
                            file7.getName(), requestBody7);
                }

                //imgg1
                //imgg2
                //imgg3
                //imgg4
                //imgg5

                if(waypath_CD_AdharCard2 != null){
                    File file11 = new File(waypath_CD_AdharCard2);
                    final RequestBody requestBody11 = RequestBody.create(MediaType.parse("image/*"), file11);
                    CD_AdharCard2 = MultipartBody.Part.createFormData("imgg1",
                            file11.getName(), requestBody11);
                }

                if(waypath_CD_ElectionCard2 != null){
                    File file12 = new File(waypath_CD_ElectionCard2);
                    final RequestBody requestBody12 = RequestBody.create(MediaType.parse("image/*"), file12);
                    CD_ElectionCard2 = MultipartBody.Part.createFormData("imgg2",
                            file12.getName(), requestBody12);
                }

                if(waypath_CD_PassportSize2 != null){
                    File file13 = new File(waypath_CD_PassportSize2);
                    final RequestBody requestBody12 = RequestBody.create(MediaType.parse("image/*"), file13);
                    CD_PassportSize2 = MultipartBody.Part.createFormData("imgg3",
                            file13.getName(), requestBody12);
                }

                if(waypathUploadDPRCBook2 != null){
                    File file14 = new File(waypathUploadDPRCBook2);
                    final RequestBody requestBody14 = RequestBody.create(MediaType.parse("image/*"), file14);
                    UploadDPRCBook2 = MultipartBody.Part.createFormData("image10",
                            file14.getName(), requestBody14);
                }

                if(waypathUploadDPElectionPhoto2 != null){
                    File file15 = new File(waypathUploadDPElectionPhoto2);
                    final RequestBody requestBody15 = RequestBody.create(MediaType.parse("image/*"), file15);
                    UploadDPElectionPhoto2 = MultipartBody.Part.createFormData("image11",
                            file15.getName(), requestBody15);
                }

              //  Toast.makeText(BookingPhaseOneActivity.this, ""+edt_CD_ReferenceNo.getText().toString(), Toast.LENGTH_SHORT).show();

                WebService.getClient().getSubmitBooking(
                        RequestBody.create(MediaType.parse("text/plain"), idBookingUpload),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingNo.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingDate.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), emp),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Fname.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Surname.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_MobileNo.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_WhatsappNo.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceName.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ReferenceNo.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_State.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_City.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_District.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Taluko.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_Village.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingLoginName.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_ADBooking_BookingType.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PassBook.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_ChequeBook.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_PurchaseName.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_ModelName.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PD_Description.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_price.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_priceInWord.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_PriceD_GST.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoMain.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoTax.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_RtoPassing.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_Insurance.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_AgentFees.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_NumberPlat.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_RTO_LoanCharge.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BookingAmount.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_CashAmount.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_BankOption.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeDate.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ChequeAmount.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGS_date.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NEFT_RTGSAmount.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_SelectMake.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ModelVehicle.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_ManufactureYear.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldAmount.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_PaperExchange.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_oldTractorAmount.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_DownP_NOC.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hood.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_TopLink.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_DrawBar.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ToolKit.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Bumper.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Hitch.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_Description.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CD_PaymentOption.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edt_CS_ConsumerSkim.getText().toString()),
                        CD_UploadBookingPhoto,
                         CD_AdharCard,
                         CD_ElectionCard ,
                         CD_PassportSize ,
                         UploadDPCheque  ,
                         UploadDPNEFT_RTGS,
                         UploadDPNocPhoto ,
                         UploadDPOldVehicle,
                         UploadDPRCBook,
                         UploadDPElectionPhoto,
                         CD_AdharCard2,
                         CD_ElectionCard2,
                         CD_PassportSize2,
                         UploadDPRCBook2,
                         UploadDPElectionPhoto2
                         ).enqueue(new Callback<BookingSubmitModel>() {
                    @Override
                    public void onResponse(@NotNull Call<BookingSubmitModel> call, @NotNull Response<BookingSubmitModel> response) {
                        progressDialog.dismiss();
                        Toast.makeText(BookingPhaseOneActivity.this, ""+response.body().getMessage(),
                                Toast.LENGTH_LONG).show();

                        Intent i = new Intent(BookingPhaseOneActivity.this,
                                BookingDeliveryMainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(@NotNull Call<BookingSubmitModel> call, @NotNull Throwable t) {
                        progressDialog.dismiss();
                        Log.d("Fail", "onFailure: "+t.getCause()+" "+t.getMessage());
                      //  Toast.makeText(BookingPhaseOneActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
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
                    uri_CD_UploadBookingPhoto = data.getData();
                    Log.d("PanImageUri", uri_CD_UploadBookingPhoto.toString());
                    waypath_CD_UploadBookingPhoto = getFilePath(this, uri_CD_UploadBookingPhoto);


                    Log.d("PanImage", waypath_CD_UploadBookingPhoto);
                    String[] name = waypath_CD_UploadBookingPhoto.split("/");
                    txt_CD_UploadBookingPhoto.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_Booking.setImageURI(selectedImageUri);
                }

            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard.toString());
                    waypath_CD_AdharCard = getFilePath(this, uri_CD_AdharCard);

                    Log.d("PanRTGS", waypath_CD_AdharCard);
                    String[] name = waypath_CD_AdharCard.split("/");
                    txt_CD_AdharCard.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard.setImageURI(selectedImageUri);
                }


            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard.toString());
                    waypath_CD_ElectionCard = getFilePath(this, uri_CD_ElectionCard);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard);
                    String[] name = waypath_CD_ElectionCard.split("/");
                    txt_CD_ElectionCard.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize.toString());
                    waypath_CD_PassportSize = getFilePath(this, uri_CD_PassportSize);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize);
                    String[] name = waypath_CD_PassportSize.split("/");
                    txt_CD_PassportSize.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPCheque = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPCheque.toString());
                    waypathUploadDPCheque = getFilePath(this, uriUploadDPCheque);
                    Log.d("Pan Image Uri", waypathUploadDPCheque);
                    String[] name = waypathUploadDPCheque.split("/");
                    txt_DownP_UploadCheque.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_Cheque.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNEFT_RTGS = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNEFT_RTGS.toString());
                    waypathUploadDPNEFT_RTGS = getFilePath(this, uriUploadDPNEFT_RTGS);
                    Log.d("Pan Image Uri", waypathUploadDPNEFT_RTGS);
                    String[] name = waypathUploadDPNEFT_RTGS.split("/");
                    txt_DownP_UploadNEFT_RTGS.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NEFT_RTGS.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPNocPhoto = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPNocPhoto.toString());
                    waypathUploadDPNocPhoto = getFilePath(this, uriUploadDPNocPhoto);
                    Log.d("Pan Image Uri", waypathUploadDPNocPhoto);
                    String[] name = waypathUploadDPNocPhoto.split("/");
                    txt_DownP_UploadNocPhoto.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_NocPhoto.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPOldVehicle = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPOldVehicle.toString());
                    waypathUploadDPOldVehicle = getFilePath(this, uriUploadDPOldVehicle);
                    Log.d("Pan Image Uri", waypathUploadDPOldVehicle);
                    String[] name = waypathUploadDPOldVehicle.split("/");
                    txt_DownP_UploadOldVehicle.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_OldVehicle.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook.toString());
                    waypathUploadDPRCBook = getFilePath(this, uriUploadDPRCBook);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook);
                    String[] name = waypathUploadDPRCBook.split("/");
                    txt_DownP_UploadRCBook.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto.toString());
                    waypathUploadDPElectionPhoto = getFilePath(this, uriUploadDPElectionPhoto);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto);
                    String[] name = waypathUploadDPElectionPhoto.split("/");
                    txt_DownP_UploadElectionPhoto.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_AdharCard2 = data.getData();
                    Log.d("PanImagUri", uri_CD_AdharCard2.toString());
                    waypath_CD_AdharCard2 = getFilePath(this, uri_CD_AdharCard2);

                    Log.d("PanRTGS", waypath_CD_AdharCard2);
                    String[] name = waypath_CD_AdharCard2.split("/");
                    txt_CD_AdharCard2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_AdharCard2.setImageURI(selectedImageUri);
                }


            }
        }

        if (requestCode == 13) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_ElectionCard2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_ElectionCard2.toString());
                    waypath_CD_ElectionCard2 = getFilePath(this, uri_CD_ElectionCard2);
                    Log.d("Pan Image Uri", waypath_CD_ElectionCard2);
                    String[] name = waypath_CD_ElectionCard2.split("/");
                    txt_CD_ElectionCard2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_ElectionCard2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 14) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uri_CD_PassportSize2 = data.getData();
                    Log.d("Pan Image Uri", uri_CD_PassportSize2.toString());
                    waypath_CD_PassportSize2 = getFilePath(this, uri_CD_PassportSize2);
                    Log.d("Pan Image Uri", waypath_CD_PassportSize2);
                    String[] name = waypath_CD_PassportSize2.split("/");
                    txt_CD_PassportSize2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_CD_PassportSize2.setImageURI(selectedImageUri);
                }
            }
        }

        if (requestCode == 15) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPRCBook2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPRCBook2.toString());
                    waypathUploadDPRCBook2 = getFilePath(this, uriUploadDPRCBook2);
                    Log.d("Pan Image Uri", waypathUploadDPRCBook2);
                    String[] name = waypathUploadDPRCBook2.split("/");
                    txt_DownP_UploadRCBook2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_RcBook2.setImageURI(selectedImageUri);
                }
            }
        }


        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    uriUploadDPElectionPhoto2 = data.getData();
                    Log.d("Pan Image Uri", uriUploadDPElectionPhoto2.toString());
                    waypathUploadDPElectionPhoto2 = getFilePath(this, uriUploadDPElectionPhoto2);
                    Log.d("Pan Image Uri", waypathUploadDPElectionPhoto2);
                    String[] name = waypathUploadDPElectionPhoto2.split("/");
                    txt_DownP_UploadElectionPhoto2.setText(name[name.length - 1]);

                    Uri selectedImageUri = data.getData();
                    img_DownP_ElectionPhoto2.setImageURI(selectedImageUri);
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