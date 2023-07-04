package com.example.kumarGroup.ScoreBoard;

import static com.example.kumarGroup.Utils.isDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.PaymentPendingModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.perfomance_model;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfomanceMarketingActivity extends AppCompatActivity {

    String emp="",empname="",date1,date2,emp12="",emp_name="",emp_id="";
    TextView trdataentry,trachivment,datanote,
            trnewinq,newinqachivment,newinqnote,
            complaintraget,complainachivment,complainnote,
            datatr,dataachivment,datanote1,
            edittr,editachiv,editnote,
            droptr,dropachiv,dropnote,
            deliverytr,deliveryachiv,deliverynote,Activityachivment,tractivity,Activitynote,
            selllosttr,selllostachiv,selllostnote,tv_performance,tv_performance1,
            paymentColl,assign_count;

    Button btn_share;
    LinearLayout ll_main;

    RelativeLayout rv_main;

    float totalvallllll=0; float tot1=0;

    SharedPreferences sp_stateIdSB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfomance_marketing);

        getSupportActionBar().setTitle("Marketing Performance");

        sp_stateIdSB = getSharedPreferences("ScoreBoardId",MODE_PRIVATE);
        if (FoDashbord.Score_Board_Check){
            emp_id = sp_stateIdSB.getString("StateId","");
            emp_name = sp_stateIdSB.getString("StateIdName","");
        }else {
//            emp_id = emp12;
            SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
            emp_name=sp.getString("emp_name","");
            emp_id = sp.getString("emp_id","");
        }


        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");

        btn_share = findViewById(R.id.btn_share);
        ll_main = findViewById(R.id.ll_main);
        trdataentry = findViewById(R.id.trdataentry);
        trachivment = findViewById(R.id.trachivment);
        datanote = findViewById(R.id.datanote);
        rv_main = findViewById(R.id.rv_main);
        tv_performance = findViewById(R.id.tv_performance);
        tv_performance1 = findViewById(R.id.tv_performance1);
        paymentColl = findViewById(R.id.paymentColl);

        if (empname.equals("Select Employee")){
            tv_performance1.setText(emp_name+" "+date1+" to "+date2);
//            emp_id=emp12;
        }else{
            tv_performance1.setText(emp_name+" "+date1+" to "+date2);
//            emp_id=emp;
        }


        trnewinq = findViewById(R.id.trnewinq);
        newinqachivment = findViewById(R.id.newinqachivment);
        newinqnote = findViewById(R.id.newinqnote);

        Activityachivment = findViewById(R.id.Activityachivment);
        tractivity = findViewById(R.id.tractivity);
        Activitynote = findViewById(R.id.Activitynote);

        complaintraget = findViewById(R.id.complaintraget);
        complainachivment = findViewById(R.id.complainachivment);
        complainnote = findViewById(R.id.complainnote);

        assign_count = findViewById(R.id.assign_count);
        datatr = findViewById(R.id.datatr);
        dataachivment = findViewById(R.id.dataachiv);
        datanote1 = findViewById(R.id.datanote1);

        edittr = findViewById(R.id.edittr);
        editachiv = findViewById(R.id.editachivment);
        editnote = findViewById(R.id.editnote);

        droptr = findViewById(R.id.droptr);
        dropachiv = findViewById(R.id.dropachiv);
        dropnote = findViewById(R.id.dropnote);

        selllosttr = findViewById(R.id.selllosttr);
        selllostachiv = findViewById(R.id.selllostachiv);
        selllostnote = findViewById(R.id.selllostnote);

        deliverytr = findViewById(R.id.deliverytr);
        deliveryachiv = findViewById(R.id.deliveryachiv);
        deliverynote = findViewById(R.id.deliverynote);
        Log.e("apidata123","emp:-"+empname+"\n date1:-"+date1+"\n date2:-"+date2);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // Code for API level 29 and higher
                    Log.d("device==>>q",">q");
                    Toast.makeText(PerfomanceMarketingActivity.this, "iffff", Toast.LENGTH_SHORT).show();

                    // Convert LinearLayout to Bitmap
                    Bitmap bitmap = Bitmap.createBitmap(ll_main.getWidth(), ll_main.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    ll_main.draw(canvas);

                    // Save Bitmap to a file
                    File cachePath = new File(getExternalCacheDir(), "Performance" + date1 + "to" + date2 + "image.png");
                    try (  FileOutputStream fos = new FileOutputStream(cachePath)) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Share Bitmap via WhatsApp intent
                    Uri imageUri = FileProvider.getUriForFile(PerfomanceMarketingActivity.this, getPackageName() + ".provider", cachePath);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                    shareIntent.setPackage("com.whatsapp");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(Intent.createChooser(shareIntent, "Share Image"));

                } else {
                    Log.d("device==>>q","<q");
                    Toast.makeText(PerfomanceMarketingActivity.this, "elseee", Toast.LENGTH_SHORT).show();
                    // Code for API level 28 and lower
                    ll_main.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(ll_main.getDrawingCache());
                    ll_main.setDrawingCacheEnabled(false);

                    try {
                        File file = new File(getExternalCacheDir(), "Performance"+date1+"to"+date2+"image.png");
                        FileOutputStream fOut = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        fOut.flush();
                        fOut.close();
                        file.setReadable(true, false);

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                        intent.setType("image/png");
                        startActivity(Intent.createChooser(intent, "Share Performance image"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

//            }
            }
        });


        WebService.getClient().getMarketPaymentPending(emp_id).enqueue(new Callback<PaymentPendingModel>() {
            @Override
            public void onResponse(@NotNull Call<PaymentPendingModel> call, @NotNull Response<PaymentPendingModel> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData().size() == 0) {
                        Utils.showErrorToast(PerfomanceMarketingActivity.this,"No Data Found");
                        getSupportActionBar().setTitle("Total:-"+tot1);

                    } else {

                        for (int i=0;i<response.body().getData().size();i++){

                            String dataaa=response.body().getData().get(i).getFinal_amt();

                            if (!isDate(dataaa)){
                                totalvallllll= Float.parseFloat(response.body().getData().get(i).getFinal_amt());
                                Log.d("totalvallllll",String.valueOf(totalvallllll));

//                                if (totalvallllll>=0){
                                tot1=tot1+totalvallllll;
//                                }
                                Log.d("tot123",String.valueOf(tot1));
                            }else{
                                Log.d("totalvallllll",String.valueOf(response.body().getData().get(i).getFinal_amt()));
                            }
                        }

                        Log.d("tot1111",String.valueOf(tot1));
                        paymentColl.setText("Payment :- Rs."+String.valueOf(tot1));



                    }
                }
                else {
                    Utils.showNormalToast(PerfomanceMarketingActivity.this,"Data Not Found");
                }


            }

            @Override
            public void onFailure(@NotNull Call<PaymentPendingModel> call, @NotNull Throwable t) {
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(PerfomanceMarketingActivity.this,"No Data Available");
            }
        });


        WebService.getClient().getPerfomance(emp_id,date1,date2/*,VillageId*/).enqueue(new Callback<perfomance_model>() {
            @Override
            public void onResponse(Call<perfomance_model> call, Response<perfomance_model> response) {

                if ("good".equals(response.body().getNew_note())){
                    datanote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getNew_note())){
                    datanote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getNew_note())){
                    datanote.setBackgroundResource(R.drawable.redcolor);
                }

                assign_count.setText(response.body().getAssign_inq());

                trdataentry.setText(response.body().getNew_visit_target());
                trachivment.setText(response.body().getNew_visit_achiv());
                datanote.setText(response.body().getNew_note());

                Activityachivment.setText(response.body().getActivity_visit_achiv());
                tractivity.setText(response.body().getActivity_visit_target());
                Activitynote.setText(response.body().getActivity_note());

                if ("good".equals(response.body().getActivity_note())){
                    Activitynote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getActivity_note())){
                    Activitynote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getActivity_note())){
                    Activitynote.setBackgroundResource(R.drawable.redcolor);
                }

                trnewinq.setText(response.body().getNew_inq_target());
                newinqachivment.setText(response.body().getNew_inq_achiv());
                newinqnote.setText(response.body().getNew_inq_note());

                if ("good".equals(response.body().getNew_inq_note())){
                    newinqnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getNew_inq_note())){
                    newinqnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getNew_inq_note())){
                    newinqnote.setBackgroundResource(R.drawable.redcolor);
                }



                complaintraget.setText(response.body().getNewcomplain_target_market());
                complainachivment.setText(response.body().getNewcomplain_achiv_market());
                complainnote.setText(response.body().getNewcomplain_note_market());

                if ("good".equals(response.body().getNewcomplain_note_market())){
                    complainnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getNewcomplain_note_market())){
                    complainnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getNewcomplain_note_market())){
                    complainnote.setBackgroundResource(R.drawable.redcolor);
                }

                datatr.setText(response.body().getDatabank_target_market());
                dataachivment.setText(response.body().getDatabank_achiv_market());
                datanote1.setText(response.body().getDatabank_note_market());

                if ("good".equals(response.body().getDatabank_note_market())){
                    datanote1.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getDatabank_note_market())){
                    datanote1.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getDatabank_note_market())){
                    datanote1.setBackgroundResource(R.drawable.redcolor);
                }

                edittr.setText(response.body().getProfiie_target_market());
                editachiv.setText(response.body().getProfile_achiv_market());
                editnote.setText(response.body().getProfile_note_market());

                if ("good".equals(response.body().getProfile_note_market())){
                    editnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getProfile_note_market())){
                    editnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getProfile_note_market())){
                    editnote.setBackgroundResource(R.drawable.redcolor);
                }

                deliverytr.setText(response.body().getDelivery_target_market());
                deliveryachiv.setText(response.body().getDelivery_achiv_market());
                deliverynote.setText(response.body().getDelivery_note_market());


                if ("good".equals(response.body().getDelivery_note_market())){
                    deliverynote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getDelivery_note_market())){
                    deliverynote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getDelivery_note_market())){
                    deliverynote.setBackgroundResource(R.drawable.redcolor);
                }

                droptr.setText(response.body().getDrop_target_market());
                dropachiv.setText(response.body().getDrop_achiv_market());
                dropnote.setText(response.body().getDrop_note_market());


                if ("good".equals(response.body().getDrop_note_market())){
                    dropnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getDrop_note_market())){
                    dropnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getDrop_note_market())){
                    dropnote.setBackgroundResource(R.drawable.redcolor);
                }


                selllosttr.setText(response.body().getSelllost_target_market());
                selllostachiv.setText(response.body().getSelllost_achiv_market());
                selllostnote.setText(response.body().getSelllost_note_market());

                if ("good".equals(response.body().getSelllost_note_market())){
                    selllostnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getSelllost_note_market())){
                    selllostnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getSelllost_note_market())){
                    selllostnote.setBackgroundResource(R.drawable.redcolor);
                }

            }

            @Override
            public void onFailure(Call<perfomance_model> call, Throwable t) {

            }
        });
    }
}