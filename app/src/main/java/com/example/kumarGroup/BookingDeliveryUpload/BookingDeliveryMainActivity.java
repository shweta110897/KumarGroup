package com.example.kumarGroup.BookingDeliveryUpload;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.BookingCountModel;
import com.example.kumarGroup.BookingDeliveryUpload.Add.AddCustomerDetailsActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Add.CustomerSkimActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Add.DownPaymentActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Add.PriceDetailsAddActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Add.ProductDetailsActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Add.RtoDetailsActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Booking.BookingUploadMainActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Delivery.DeliveryMainActivity;
import com.example.kumarGroup.BookingDeliveryUpload.DocumentPrint.DocumentPrintMainActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Loan.LoanDataMainActivity;
import com.example.kumarGroup.BookingDeliveryUpload.Passing.PassingDataMainActivity;
import com.example.kumarGroup.BookingDeliveryUpload.PaymentPending.PaymentPendingMainActivity;
import com.example.kumarGroup.BookingDeliveryUpload.NumberPlate.NumberPlate_Detail_Activity;

import com.example.kumarGroup.BookingDeliveryUpload.RcBook_Financer.RC_Financer_Detail_Activity;
import com.example.kumarGroup.BookingDeliveryUpload.Rcbook_Update.RC_Customer_Detail_Activity;
import com.example.kumarGroup.PhaseAddBookingModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDeliveryMainActivity extends AppCompatActivity {


    TextView tv_BookingDelivery_add,tv_BookingDelivery_Booking,
            tv_BookingDelivery_Delivery,tv_BookingDelivery_Loan,
            tv_BookingDelivery_Passing,tv_BookingDelivery_Payment_pending,
            tv_BookingDelivery_DocPrint,tv_BookingDelivery_Number_Plate,
            tv_BookingDelivery_RCBook_Update_Customer,tv_BookingDelivery_RCBook_Update_Financer;

    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;

    TextView txt_count_Booking,txt_count_Loan,txt_count_Delivery,txt_count_numberplate,
            txt_count_DocPrint,txt_count_Passing,txt_count_payPen,txt_count_rc_financer,txt_count_rc_customer;

    SharedPreferences sharedPreferences;
    String NextIDD,Booking_Add;

    SharedPreferences sp;
    String emp;

    ProgressDialog progressDialog;

    public static boolean Next_Button_Check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_delivery_main);

        getSupportActionBar().setTitle("Booking/Delivery Upload");


        // getSupportActionBar().setTitle("Booking");
        
        tv_BookingDelivery_add=findViewById(R.id.tv_BookingDelivery_add);
        tv_BookingDelivery_Booking=findViewById(R.id.tv_BookingDelivery_Booking);
        tv_BookingDelivery_Delivery=findViewById(R.id.tv_BookingDelivery_Delivery);
        tv_BookingDelivery_Loan=findViewById(R.id.tv_BookingDelivery_Loan);
        tv_BookingDelivery_Passing=findViewById(R.id.tv_BookingDelivery_Passing);
        tv_BookingDelivery_Payment_pending=findViewById(R.id.tv_BookingDelivery_Payment_pending);
        tv_BookingDelivery_DocPrint=findViewById(R.id.tv_BookingDelivery_DocPrint);
        tv_BookingDelivery_Number_Plate=findViewById(R.id.tv_BookingDelivery_Number_Plate);
        tv_BookingDelivery_RCBook_Update_Customer=findViewById(R.id.tv_BookingDelivery_RCBook_Update_Customer);
        tv_BookingDelivery_RCBook_Update_Financer=findViewById(R.id.tv_BookingDelivery_RCBook_Update_Financer);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);
        txt7=findViewById(R.id.txt7);
        txt8=findViewById(R.id.txt8);
        txt9=findViewById(R.id.txt9);
        txt10=findViewById(R.id.txt10);


        txt_count_Booking=findViewById(R.id.txt_count_Booking);
        txt_count_Loan=findViewById(R.id.txt_count_Loan);
        txt_count_Delivery=findViewById(R.id.txt_count_Delivery);
        txt_count_DocPrint=findViewById(R.id.txt_count_DocPrint);
        txt_count_Passing=findViewById(R.id.txt_count_Passing);
        txt_count_payPen=findViewById(R.id.txt_count_payPen);
        txt_count_numberplate=findViewById(R.id.txt_count_numberplate);
        txt_count_rc_customer=findViewById(R.id.txt_count_rc_customer);
        txt_count_rc_financer=findViewById(R.id.txt_count_rc_financer);


        SharedPreferences preferences = getSharedPreferences("NextId", MODE_PRIVATE);
        preferences.edit().remove("NextId").apply();
        preferences.edit().remove("phase").apply();

        sharedPreferences = getSharedPreferences("NextId", MODE_PRIVATE);
        NextIDD = sharedPreferences.getString("NextId","");

        Booking_Add=getIntent().getStringExtra("module_name");


        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp = sp.getString("emp_id","");




        tv_BookingDelivery_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                txt1.setBackgroundResource(R.drawable.enable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);



                if(NextIDD.equals(""))
               // if(NextIDD == null)
                {
                  //  Toast.makeText(BookingDeliveryMainActivity.this, "If Condition Call", Toast.LENGTH_SHORT).show();
                  //  Intent i = new Intent(BookingDeliveryMainActivity.this, AddCustomerDetailsActivity.class);
                  //  startActivity(i);

                    Intent i = new Intent(BookingDeliveryMainActivity.this, ProductDetailsActivity.class);
                    startActivity(i);
                }
                else {
                   // Toast.makeText(BookingDeliveryMainActivity.this, "Else Condition Call"+NextIDD, Toast.LENGTH_SHORT).show();

                    WebService.getClient().getPhaseAddBooking(NextIDD).enqueue(new Callback<PhaseAddBookingModel>() {
                        @Override
                        public void onResponse(@NotNull Call<PhaseAddBookingModel> call,
                                               @NotNull Response<PhaseAddBookingModel> response) {

                            if (response.body().getPhase() == null) {
                          //  if (response.body().getPhase().equals("0")) {
                                //Intent i = new Intent(BookingDeliveryMainActivity.this, AddCustomerDetailsActivity.class);
                                Intent i = new Intent(BookingDeliveryMainActivity.this, ProductDetailsActivity.class);
                                startActivity(i);
                            } else if (response.body().getPhase().equals("1")) {
                                //Intent i = new Intent(BookingDeliveryMainActivity.this, ProductDetailsActivity.class);
                                Intent i = new Intent(BookingDeliveryMainActivity.this, PriceDetailsAddActivity.class);
                                startActivity(i);
                            } else if (response.body().getPhase().equals("2")) {
                                //Intent i = new Intent(BookingDeliveryMainActivity.this, PriceDetailsAddActivity.class);
                                Intent i = new Intent(BookingDeliveryMainActivity.this, AddCustomerDetailsActivity.class);
                                startActivity(i);
                            } else if (response.body().getPhase().equals("3")) {
                               // Intent i = new Intent(BookingDeliveryMainActivity.this, RtoDetailsActivity.class);
                                Intent i = new Intent(BookingDeliveryMainActivity.this, DownPaymentActivity.class);
                                startActivity(i);
                            } else if (response.body().getPhase().equals("4")) {
                               // Intent i = new Intent(BookingDeliveryMainActivity.this, DownPaymentActivity.class);
                                Intent i = new Intent(BookingDeliveryMainActivity.this, RtoDetailsActivity.class);
                                startActivity(i);
                            } else if (response.body().getPhase().equals("5")) {
                                Intent i = new Intent(BookingDeliveryMainActivity.this, CustomerSkimActivity.class);
                                startActivity(i);
                            }
                            else  if(response.body().getPhase().equals("6")){
                               // Intent i = new Intent(BookingDeliveryMainActivity.this, AddCustomerDetailsActivity.class);
                                Intent i = new Intent(BookingDeliveryMainActivity.this, ProductDetailsActivity.class);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<PhaseAddBookingModel> call, @NotNull Throwable t) {

                        }
                    });
                }

               /* Intent i = new Intent(BookingDeliveryMainActivity.this, AddCustomerDetailsActivity.class);
                startActivity(i);*/
            }
        });


        progressDialog= new ProgressDialog(BookingDeliveryMainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        WebService.getClient().getBookingCount(emp).enqueue(new Callback<BookingCountModel>() {
            @Override
            public void onResponse(@NotNull Call<BookingCountModel> call, @NotNull Response<BookingCountModel> response) {
                progressDialog.dismiss();


                txt_count_Booking.setText(response.body().getCat().get(0).getBooking());

                txt_count_Loan.setText(response.body().getCat().get(1).getLoan());

                txt_count_Delivery.setText(response.body().getCat().get(2).getDelivery());

                txt_count_DocPrint.setText(response.body().getCat().get(3).getDocprint());

                txt_count_Passing.setText(response.body().getCat().get(4).getPassing());

                txt_count_payPen.setText(response.body().getCat().get(5).getPaymentpen());
                txt_count_numberplate.setText(response.body().getCat().get(6).getFitment_count());
                txt_count_rc_customer.setText(response.body().getCat().get(7).getRc_fitment_customer());
                txt_count_rc_financer.setText(response.body().getCat().get(8).getRc_fitment_final());

            }

            @Override
            public void onFailure(@NotNull Call<BookingCountModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });


        tv_BookingDelivery_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Jemin123: BookingUploadMainActivity");
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.enable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);
                Next_Button_Check = true;
                Intent i = new Intent(BookingDeliveryMainActivity.this, BookingUploadMainActivity.class);
                startActivity(i);

            }
        });

        tv_BookingDelivery_Loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.enable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);
                Next_Button_Check = true;

                Intent i = new Intent(BookingDeliveryMainActivity.this, LoanDataMainActivity.class);
                startActivity(i);

            }
        });


        tv_BookingDelivery_Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.enable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(BookingDeliveryMainActivity.this, DeliveryMainActivity.class);
                startActivity(i);
            }
        });


        tv_BookingDelivery_DocPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.enable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(BookingDeliveryMainActivity.this, DocumentPrintMainActivity.class);
                startActivity(i);
            }
        });


        tv_BookingDelivery_Passing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.enable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);


                Intent i = new Intent(BookingDeliveryMainActivity.this, PassingDataMainActivity.class);
                startActivity(i);
            }
        });

        tv_BookingDelivery_Payment_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.enable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);



                Intent i = new Intent(BookingDeliveryMainActivity.this, PaymentPendingMainActivity.class);
                startActivity(i);
            }
        });

        tv_BookingDelivery_Number_Plate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.enable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                Intent intent = new Intent(BookingDeliveryMainActivity.this, NumberPlate_Detail_Activity.class);
                startActivity(intent);
            }
        });

        tv_BookingDelivery_RCBook_Update_Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.enable_page);
                txt10.setBackgroundResource(R.drawable.disable_page);

                Intent intent = new Intent(BookingDeliveryMainActivity.this, RC_Customer_Detail_Activity.class);
                startActivity(intent);
            }
        });

        tv_BookingDelivery_RCBook_Update_Financer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt1.setBackgroundResource(R.drawable.disable_page);
                txt2.setBackgroundResource(R.drawable.disable_page);
                txt3.setBackgroundResource(R.drawable.disable_page);
                txt4.setBackgroundResource(R.drawable.disable_page);
                txt5.setBackgroundResource(R.drawable.disable_page);
                txt6.setBackgroundResource(R.drawable.disable_page);
                txt7.setBackgroundResource(R.drawable.disable_page);
                txt8.setBackgroundResource(R.drawable.disable_page);
                txt9.setBackgroundResource(R.drawable.disable_page);
                txt10.setBackgroundResource(R.drawable.enable_page);

                Intent intent = new Intent(BookingDeliveryMainActivity.this, RC_Financer_Detail_Activity.class);
                startActivity(intent);
            }
        });

    }
}