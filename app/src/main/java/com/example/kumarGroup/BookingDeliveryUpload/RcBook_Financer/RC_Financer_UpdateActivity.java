package com.example.kumarGroup.BookingDeliveryUpload.RcBook_Financer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kumarGroup.R;
import com.example.kumarGroup.RcbookUpdate;
import com.example.kumarGroup.WebService;

import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RC_Financer_UpdateActivity extends AppCompatActivity {

    Spinner spn_ecbook_update_rto;
    String number_plate_fitment,rc_book_update,rc_update_customer;
    List<String> Y_N = Arrays.asList(new String[]{"Select Option","Yes", "No"});
    String number_plate1,rcbook_picture11,rcbook_picture21;
    Button btn_Passing_Submit;
    EditText edt_mobile;
    String id,register_no;
    ProgressDialog pro;
    LinearLayout ll_mobile;


    String emp_name,Mobile_No,Village,Financer_Name,Financer_From,WA_No,engineno,Chasisno;
    TextView tv_name,tv_village,tv_mobile,tv_Whapp_Number,tv_trac_reg_no,tv_financer_name,tv_financer_from,tv_engine,tv_chasis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_financer_update);

        getSupportActionBar().setTitle("RCBOOK Update(Financer)");

        pro = new ProgressDialog(this);
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");

        btn_Passing_Submit = findViewById(R.id.btn_Passing_Submit);
        spn_ecbook_update_rto = findViewById(R.id.spn_ecbook_update_rto);
        ll_mobile = findViewById(R.id.ll_mobile);
        edt_mobile = findViewById(R.id.edt_mobile);
        tv_name = findViewById(R.id.tv_name);
        tv_village = findViewById(R.id.tv_village);
        tv_mobile = findViewById(R.id.tv_mobile);
        tv_Whapp_Number = findViewById(R.id.tv_Whapp_Number);
        tv_trac_reg_no = findViewById(R.id.tv_trac_reg_no);
        tv_financer_name = findViewById(R.id.tv_financer_name);
        tv_financer_from = findViewById(R.id.tv_financer_from);
        tv_engine = findViewById(R.id.tv_engine);
        tv_chasis = findViewById(R.id.tv_chasis);


        id = getIntent().getStringExtra("idBookingUpload");
        register_no = getIntent().getStringExtra("register_no");
        emp_name = getIntent().getStringExtra("emp_name");
        Mobile_No = getIntent().getStringExtra("Mobile_No");
        Village = getIntent().getStringExtra("Village");
        Financer_Name = getIntent().getStringExtra("Financer_Name");
        Financer_From = getIntent().getStringExtra("Financer_From");
        WA_No = getIntent().getStringExtra("WA_No");
        Chasisno = getIntent().getStringExtra("Chasisno");
        engineno = getIntent().getStringExtra("engineno");


        tv_name.setText("Name: "+emp_name);
        tv_village.setText("Village: "+Village);
        tv_mobile.setText("Mobile Number: "+Mobile_No);
        tv_Whapp_Number.setText("Whatsapp Number: "+WA_No);
        tv_trac_reg_no.setText("Tractor Reg No.: "+register_no);
        tv_financer_name.setText("Financer Name: "+Financer_Name);
        tv_financer_from.setText("Finance From: "+Financer_From);
        tv_engine.setText("Engine No: "+engineno);
        tv_chasis.setText("Chasis No: "+Chasisno);


        ArrayAdapter adapterStage4 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Y_N);
        spn_ecbook_update_rto.setAdapter(adapterStage4);

        spn_ecbook_update_rto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rc_book_update = Y_N.get(i);
                if (rc_book_update.equals("Yes")){
                    ll_mobile.setVisibility(View.VISIBLE);
                }else {
                    ll_mobile.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ll_mobile.setVisibility(View.GONE);

            }
        });

        btn_Passing_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.show();
                WebService.getClient().FinalSubmit(RequestBody.create(MediaType.parse("text/plain"), id)
                ).enqueue(new Callback<RcbookUpdate>() {
                    @Override
                    public void onResponse(Call<RcbookUpdate> call, Response<RcbookUpdate> response) {
                        pro.dismiss();

                        if (response.body().getMsg().equals("Record Update Succesfully")){
                            Toast.makeText(RC_Financer_UpdateActivity.this, "Record Update Succesfully", Toast.LENGTH_SHORT).show();
                            showDialogEdit();
                        }else {
                            Toast.makeText(RC_Financer_UpdateActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<RcbookUpdate> call, Throwable t) {
                        pro.dismiss();
                    }
                });



               /* if (edt_mobile.getText().toString().equals("")){
                    Toast.makeText(RC_Financer_UpdateActivity.this, "Try Again after adding Mobile Number", Toast.LENGTH_SHORT).show();

                }else{
                    SendWhatsappMessage(edt_mobile.getText().toString());
                }*/
             /*   pro.show();

                MultipartBody.Part img_number_platem = null;
                MultipartBody.Part img_rcbook_picture1m  = null;
                MultipartBody.Part img_rcbook_picture2m  = null;

                if(number_plate1 != null){
                    File file1 = new File(number_plate1);
                    final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    img_number_platem = MultipartBody.Part.createFormData("image1",
                            file1.getName(), requestBody1);
                }
                if(rcbook_picture11 != null){
                    File file2 = new File(rcbook_picture11);
                    final RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    img_rcbook_picture1m = MultipartBody.Part.createFormData("image2",
                            file2.getName(), requestBody2);
                }
                if(rcbook_picture21 != null){
                    File file3 = new File(rcbook_picture21);
                    final RequestBody requestBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                    img_rcbook_picture2m = MultipartBody.Part.createFormData("image3",
                            file3.getName(), requestBody3);
                }

                WebService.getClient().RcbookUpdate(RequestBody.create(MediaType.parse("text/plain"), id),
                        RequestBody.create(MediaType.parse("text/plain"), number_plate_fitment),
                        RequestBody.create(MediaType.parse("text/plain"), rc_book_update),
                        RequestBody.create(MediaType.parse("text/plain"), rc_update_customer),
                        RequestBody.create(MediaType.parse("text/plain"), register_no),
                        img_number_platem,
                        img_rcbook_picture1m,
                        img_rcbook_picture2m
                ).enqueue(new Callback<RcbookUpdate>() {
                    @Override
                    public void onResponse(Call<RcbookUpdate> call, Response<RcbookUpdate> response) {
                        pro.dismiss();
                        if (response.body().getMsg().equals("Record Update Succesfully")){
                            Toast.makeText(RC_Financer_UpdateActivity.this, "Record Update Succesfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RC_Financer_UpdateActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<RcbookUpdate> call, Throwable t) {
                        pro.dismiss();
                    }
                });
*/
            }
        });

    }

    private void showDialogEdit() {

        Dialog dialog = new Dialog(RC_Financer_UpdateActivity.this);
        dialog.setContentView(R.layout.edit_detail);

        EditText customer_name = dialog.findViewById(R.id.customerf_name);
        EditText customerl_name = dialog.findViewById(R.id.customerl_name);
        EditText customer_mnmbr = dialog.findViewById(R.id.customer_mnmbr);
        EditText customer_other_mnmbr = dialog.findViewById(R.id.customer_other_mnmbr);
        TextView locationget = dialog.findViewById(R.id.locationget);
        Button getlocation1 = dialog.findViewById(R.id.getlocation);

        EditText note = dialog.findViewById(R.id.note);
        Button btn_edit = dialog.findViewById(R.id.btn_edit);

        Spinner sp_model_emp =dialog. findViewById(R.id.sp_model_emp);
        Spinner sp_paymenttype =dialog. findViewById(R.id.sp_paymenttype);
        Spinner sp_passingtype =dialog. findViewById(R.id.sp_passingtype);
        Spinner sp_make_name = dialog.findViewById(R.id.sp_make_name);
        Spinner sp_model_name = dialog.findViewById(R.id.sp_model_name);


        customer_name.setVisibility(View.GONE);
        customerl_name.setVisibility(View.GONE);
        customer_other_mnmbr.setVisibility(View.GONE);
        note.setVisibility(View.GONE);
        sp_model_emp.setVisibility(View.GONE);
        sp_paymenttype.setVisibility(View.GONE);
        sp_passingtype.setVisibility(View.GONE);
        sp_make_name.setVisibility(View.GONE);
        sp_model_name.setVisibility(View.GONE);



        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (customer_mnmbr.getText().toString().equals("")){
                    Toast.makeText(RC_Financer_UpdateActivity.this, "Try Again after adding Mobile Number", Toast.LENGTH_SHORT).show();

                }else{
                    SendWhatsappMessage(customer_mnmbr.getText().toString(),dialog);


                }
            }
        });



        dialog.show();


    }

    private void SendWhatsappMessage(String MobileNo, Dialog dialog) {
        boolean installed = appInstallOrNot("com.whatsapp");
        if (installed){

            dialog.dismiss();

          String  message = "** Kumar Group ** \n" +
                  ""+"Customer Detail:\n" +
                  "Name: "+emp_name+"\n" +
                  "Mobile: "+Mobile_No+"\n"+
                  "Village: "+Village+"\n"+
                  "WhatsApp Number: "+WA_No+"\n"+
                  "Tractor Reg No.: "+register_no+"\n" +
                  "Financer_Name: "+Financer_Name+"\n"+
                  "Financer_From: "+Financer_From+"\n"+
                  "Engine No: "+engineno+"\n"+
                  "Chasis No: "+Chasisno+"\n";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"+MobileNo + "&text=" + message));
            startActivity(intent);


/*
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+
                    MobileNo));
            intent.putExtra(Intent.EXTRA_TEXT,  "** Kumar Group ** \n" +
                    ""+"Customer Detail:\n" +
                    "Name: "+emp_name+"\n" +
                    "Mobile: "+Mobile_No+"\n"+
                    "Village: "+Village+"\n"+
                    "WhatsApp Number: "+WA_No+"\n"+
                    "Tractor Reg No.: "+register_no+"\n" +
                    "Financer_Name: "+Financer_Name+"\n"+
                    "Financer_From: "+Financer_From+"\n");
            startActivity(intent);*/
        }else {
            dialog.dismiss();
            Toast.makeText(RC_Financer_UpdateActivity.this, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean appInstallOrNot(String url)
    {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try{

            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed =false;
        }
        return app_installed;
    }

}