package com.example.kumarGroup.CustomerProfileData;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kumarGroup.CatPerDetailModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayCatPermissionActivity extends AppCompatActivity {

    TextView txtCatName_CP,txtFname_CP,txtLname_CP,txtState_CP,
            txtCity_Cp,txtDistrict_CP,txtTehsil_CP,txtVillage_CP,
            txtMobileNo_CP,txtWhatsAppNo_CP,txtEmployeeName_CP,txtDescription_CP,
            txtTractor_CP,txtModelTName_CP,txtMfgy_CP,txtEngineNo_CP,
            txtChasisNo_CP,txtRotavetor_CP,txtSPeedDrill_CP,
            txtPelough_CP;

    ImageView ImgImageOne_CP,ImgImageTwo_CP;


    TextView lin_call,lin_sms,lin_whapp,lin_share,lin_email;

    String Id;

    String sms = "";//The message you want to text to the phone

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cat_permission);


        Id = getIntent().getStringExtra("id");

        txtCatName_CP= findViewById(R.id.txtCatName_CP);
        txtFname_CP= findViewById(R.id.txtFname_CP);
        txtLname_CP= findViewById(R.id.txtLname_CP);
        txtState_CP= findViewById(R.id.txtState_CP);
        txtCity_Cp= findViewById(R.id.txtCity_Cp);
        txtDistrict_CP= findViewById(R.id.txtDistrict_CP);
        txtTehsil_CP= findViewById(R.id.txtTehsil_CP);

        txtVillage_CP= findViewById(R.id.txtVillage_CP);
        txtMobileNo_CP= findViewById(R.id.txtMobileNo_CP);
        txtWhatsAppNo_CP= findViewById(R.id.txtWhatsAppNo_CP);
        txtEmployeeName_CP= findViewById(R.id.txtEmployeeName_CP);
        txtDescription_CP= findViewById(R.id.txtDescription_CP);

        txtTractor_CP= findViewById(R.id.txtTractor_CP);
        txtModelTName_CP= findViewById(R.id.txtModelTName_CP);
        txtMfgy_CP= findViewById(R.id.txtMfgy_CP);
        txtEngineNo_CP= findViewById(R.id.txtEngineNo_CP);
        txtChasisNo_CP= findViewById(R.id.txtChasisNo_CP);
        txtRotavetor_CP= findViewById(R.id.txtRotavetor_CP);
        txtSPeedDrill_CP= findViewById(R.id.txtSPeedDrill_CP);
        txtPelough_CP= findViewById(R.id.txtPelough_CP);

        ImgImageOne_CP= findViewById(R.id.ImgImageOne_CP);
        ImgImageTwo_CP= findViewById(R.id.ImgImageTwo_CP);

        lin_call= findViewById(R.id.lin_call);
        lin_sms= findViewById(R.id.lin_sms);
        lin_whapp= findViewById(R.id.lin_whapp);
        lin_share= findViewById(R.id.lin_share);
        lin_email= findViewById(R.id.lin_email);



        progressDialog = new ProgressDialog(DisplayCatPermissionActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getCatPerDetails(Id).enqueue(new Callback<CatPerDetailModel>() {
            @Override
            public void onResponse(@NotNull Call<CatPerDetailModel> call, @NotNull Response<CatPerDetailModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;


                if(response.body().getCat().size()==0){
                    Utils.showErrorToast(DisplayCatPermissionActivity.this,"Data not Available");
                }
                else {

                    txtCatName_CP.setText("Category Name: " + response.body().getCat().get(0).getCat_name());
                    txtFname_CP.setText("First Name: " + response.body().getCat().get(0).getFname());
                    txtLname_CP.setText("Last Name: " + response.body().getCat().get(0).getLname());
                    txtState_CP.setText("State: " + response.body().getCat().get(0).getState());
                    txtCity_Cp.setText("City: " + response.body().getCat().get(0).getCity());
                    txtDistrict_CP.setText("District: " + response.body().getCat().get(0).getDistric());
                    txtTehsil_CP.setText("Tehsil: " + response.body().getCat().get(0).getTehsil());
                    txtVillage_CP.setText("Village: " + response.body().getCat().get(0).getVilage());
                    txtMobileNo_CP.setText("Mobile No: " + response.body().getCat().get(0).getMoblino());
                    txtWhatsAppNo_CP.setText("WhatsApp No: " + response.body().getCat().get(0).getWhatsappno());
                    txtEmployeeName_CP.setText("Employee Name: " + response.body().getCat().get(0).getEmployee_name());
                    txtDescription_CP.setText("Description: " + response.body().getCat().get(0).getDesc());


                    txtTractor_CP.setText("Tractor: " + response.body().getCat().get(0).getTractor());
                    txtModelTName_CP.setText("Model Name: " + response.body().getCat().get(0).getModel_t_name());
                    txtMfgy_CP.setText("MFG Year: " + response.body().getCat().get(0).getMfgy());
                    txtEngineNo_CP.setText("Engine No: " + response.body().getCat().get(0).getEngineno());
                    txtChasisNo_CP.setText("CHasis No: " + response.body().getCat().get(0).getChasisno());
                    txtRotavetor_CP.setText("Rotavetor: " + response.body().getCat().get(0).getRotavator());
                    txtSPeedDrill_CP.setText("Speed Drill: " + response.body().getCat().get(0).getSpeeddrel());
                    txtPelough_CP.setText("Pelough: " + response.body().getCat().get(0).getPelough());


                    Glide.with(DisplayCatPermissionActivity.this)
                            .load(response.body().getCat().get(0).getPath() +
                                    response.body().getCat().get(0).getImage1())
                            .into(ImgImageOne_CP);

                    Glide.with(DisplayCatPermissionActivity.this)
                            .load(response.body().getCat().get(0).getPath() +
                                    response.body().getCat().get(0).getImage2())
                            .into(ImgImageTwo_CP);



                    lin_whapp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean installed = appInstallOrNot("com.whatsapp");
                            if (installed) {

                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91"
                                        + response.body().getCat().get(0).getWhatsappno()));
                                startActivity(intent);
                            } else {
                                Toast.makeText(DisplayCatPermissionActivity.this, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                    lin_call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + response.body().getCat().get(0).getMoblino()));
                            Log.d("call", "onClick: " + response.body().getCat().get(0).getMoblino());
                            startActivity(intent);
                        }
                    });

                    lin_email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("plain/text");
                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"some@email.address"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                            intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                            intent.setPackage("com.google.android.gm");

                            startActivity(Intent.createChooser(intent, ""));
                        }
                    });


                    lin_sms.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
                                    response.body().getCat().get(0).getMoblino(), null));
                            smsIntent.putExtra("sms_body", sms);
                            startActivity(smsIntent);

                        }
                    });


                    lin_share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(android.content.Intent.ACTION_SEND);
                            i.setType("text/plain");
                            i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject test");
                            i.putExtra(android.content.Intent.EXTRA_TEXT, "** Kumar Group **" +
                                    " \n" + "Customer Detail:\n"
                                    + response.body().getCat().get(0).getFname() + "\n" +
                                    response.body().getCat().get(0).getLname() + "\n" +
                                    response.body().getCat().get(0).getCat_name() + "\n" +
                                    response.body().getCat().get(0).getMoblino() + "\n" +
                                    response.body().getCat().get(0).getVilage() + "\n" +
                                    response.body().getCat().get(0).getDistric() + "\n" +
                                    response.body().getCat().get(0).getTehsil());
                            startActivity(Intent.createChooser(i, "Share via"));

                        }
                    });


                }

            }

            @Override
            public void onFailure(@NotNull Call<CatPerDetailModel> call, @NotNull Throwable t) {

            }
        });

    }
    private boolean appInstallOrNot(String url){
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