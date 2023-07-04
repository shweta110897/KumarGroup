package com.example.kumarGroup.ViewInquiryDealStage;

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
import com.example.kumarGroup.InsentiveWalletModel;
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

public class PerfomanceActivityINQ extends AppCompatActivity {

    String emp="",empname="",date1,date2,emp12="",emp_name="",emp_id="";
    TextView trdataentry,trachivment,datanote,
            trnewinq,newinqachivment,newinqnote,
            hottraget,hotachivment,hotnote,
            followtr,followachivment,follownote,
            deliverytr,deliveryachiv,deliverynote,
            droptr,dropachiv,dropnote,assign_count,
            warmtr,warmachiv,warmnote,Activityachivment,tractivity,Activitynote,
            selllosttr,selllostachiv,selllostnote,tv_performance,tv_performance1,
            Overduetr,Overdueachiv,Overduenote,Notattendtr,notattendachiv,Notattendnote,FirstMeettr,firstachiv,FirstMeetnote,paymentColl;

    Button btn_share;
    LinearLayout ll_main;

    RelativeLayout rv_main;

    float totalvallllll=0; float tot1=0;

    SharedPreferences sp_stateIdSB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfomance_inq);

        getSupportActionBar().setTitle("Performance View Inquiry");

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
        assign_count = findViewById(R.id.assign_count);

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

        hottraget = findViewById(R.id.hottraget);
        hotachivment = findViewById(R.id.hotachivment);
        hotnote = findViewById(R.id.hotnote);

        followtr = findViewById(R.id.followtr);
        followachivment = findViewById(R.id.followachivment);
        follownote = findViewById(R.id.follownote);

        deliverytr = findViewById(R.id.deliverytr);
        deliveryachiv = findViewById(R.id.deliveryachiv);
        deliverynote = findViewById(R.id.deliverynote);

        droptr = findViewById(R.id.droptr);
        dropachiv = findViewById(R.id.dropachiv);
        dropnote = findViewById(R.id.dropnote);

        warmtr = findViewById(R.id.warmtr);
        warmachiv = findViewById(R.id.warmachiv);
        warmnote = findViewById(R.id.warmnote);

        selllosttr = findViewById(R.id.selllosttr);
        selllostachiv = findViewById(R.id.selllostachiv);
        selllostnote = findViewById(R.id.selllostnote);


        Overduetr = findViewById(R.id.Overduetr);
        Overdueachiv = findViewById(R.id.Overdueachiv);
        Overduenote = findViewById(R.id.Overduenote);

        Notattendtr = findViewById(R.id.Notattendtr);
        notattendachiv = findViewById(R.id.notattendachiv);
        Notattendnote = findViewById(R.id.Notattendnote);

        FirstMeettr = findViewById(R.id.FirstMeettr);
        firstachiv = findViewById(R.id.firstachiv);
        FirstMeetnote = findViewById(R.id.FirstMeetnote);

        Log.e("apidata123","emp:-"+empname+"\n date1:-"+date1+"\n date2:-"+date2);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // Code for API level 29 and higher
                    Log.d("device==>>q",">q");
                    Toast.makeText(PerfomanceActivityINQ.this, "iffff", Toast.LENGTH_SHORT).show();

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
                    Uri imageUri = FileProvider.getUriForFile(PerfomanceActivityINQ.this, getPackageName() + ".provider", cachePath);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                    shareIntent.setPackage("com.whatsapp");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(Intent.createChooser(shareIntent, "Share Image"));

                } else {
                    Log.d("device==>>q","<q");
                    Toast.makeText(PerfomanceActivityINQ.this, "elseee", Toast.LENGTH_SHORT).show();
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

        WebService.getClient().getInsensitiveDeliveryData(emp_id).enqueue(new Callback<InsentiveWalletModel>() {
            @Override
            public void onResponse(@NotNull Call<InsentiveWalletModel> call, @NotNull Response<InsentiveWalletModel> response) {

                assert response.body() != null;

                if (response.body().getData().size() == 0) {
                    getSupportActionBar().setTitle("Total:-"+tot1);
                    Utils.showErrorToast(PerfomanceActivityINQ.this, "No Data Available");
                } else {
                    for (int i=0;i<response.body().getData().size();i++){

                        String dataaa=response.body().getData().get(i).getFinal_amount();

                        if (!isDate(dataaa)){
                            totalvallllll= Float.parseFloat(response.body().getData().get(i).getFinal_amount());
                            Log.d("totalvallllll",String.valueOf(totalvallllll));
                            tot1=tot1+totalvallllll;
                            Log.d("tot123",String.valueOf(tot1));
                        }else{
                            Log.d("totalvallllll",String.valueOf(response.body().getData().get(i).getFinal_amount()));
                        }
                    }

                    Log.d("tot1111",String.valueOf(tot1));
                    paymentColl.setText("Payment :- Rs."+tot1);


                }
            }

            @Override
            public void onFailure(@NotNull Call<InsentiveWalletModel> call, @NotNull Throwable t) {
                Utils.showErrorToast(PerfomanceActivityINQ.this,"No Data Available");

            }
        });


      /*  WebService.getClient().getWalletPaymentPending(emp_id).enqueue(new Callback<PaymentPendingModel>() {
            @Override
            public void onResponse(@NotNull Call<PaymentPendingModel> call, @NotNull Response<PaymentPendingModel> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData().size() == 0) {
                        Utils.showErrorToast(PerfomanceActivityINQ.this,"No Data Found");
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
                    Utils.showNormalToast(PerfomanceActivityINQ.this,"Data Not Found");
                }


            }

            @Override
            public void onFailure(@NotNull Call<PaymentPendingModel> call, @NotNull Throwable t) {
                // Toast.makeText(BookingUploadMainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Utils.showErrorToast(PerfomanceActivityINQ.this,"No Data Available");
            }
        });
*/

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



                hottraget.setText(response.body().getHot_target());
                hotachivment.setText(response.body().getHot_achiv());
                hotnote.setText(response.body().getHot_note());

                if ("good".equals(response.body().getHot_note())){
                    hotnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getHot_note())){
                    hotnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getHot_note())){
                    hotnote.setBackgroundResource(R.drawable.redcolor);
                }

                followtr.setText(response.body().getFollow_up_target());
                followachivment.setText(response.body().getFollow_up_achiv());
                follownote.setText(response.body().getFollow_up_note());

                if ("good".equals(response.body().getFollow_up_note())){
                    follownote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getFollow_up_note())){
                    follownote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getFollow_up_note())){
                    follownote.setBackgroundResource(R.drawable.redcolor);
                }

                deliverytr.setText(response.body().getDelivery_target());
                deliveryachiv.setText(response.body().getDelivery_achiv());
                deliverynote.setText(response.body().getDelivery_note());

                if ("good".equals(response.body().getDelivery_note())){
                    deliverynote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getDelivery_note())){
                    deliverynote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getDelivery_note())){
                    deliverynote.setBackgroundResource(R.drawable.redcolor);
                }

                droptr.setText(response.body().getDrop_target());
                dropachiv.setText(response.body().getDrop_achiv());
                dropnote.setText(response.body().getDrop_note());


                if ("good".equals(response.body().getDrop_note())){
                    dropnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getDrop_note())){
                    dropnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getDrop_note())){
                    dropnote.setBackgroundResource(R.drawable.redcolor);
                }


//                int targetWamCold=Integer.parseInt(response.body().getCold_target()+response.body().getWarm_target());
//                int achivWamCold=Integer.parseInt(response.body().getCold_achiv()+response.body().getWarm_achiv());
//                int notWamCold=Integer.parseInt(response.body().getCold_note()+response.body().getWarm_note());


                warmtr.setText(response.body().getWarm_target());
                warmachiv.setText(response.body().getWarm_achiv());
                warmnote.setText(response.body().getWarm_note());


                if ("good".equals(response.body().getWarm_note())){
                    warmnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getDrop_note())){
                    warmnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getDrop_note())){
                    warmnote.setBackgroundResource(R.drawable.redcolor);
                }


                selllosttr.setText(response.body().getSelllost_target());
                selllostachiv.setText(response.body().getSelllost_achiv());
                selllostnote.setText(response.body().getSelllost_note());

                if ("good".equals(response.body().getSelllost_note())){
                    selllostnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getSelllost_note())){
                    selllostnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getSelllost_note())){
                    selllostnote.setBackgroundResource(R.drawable.redcolor);
                }



//                Overduetr.setText(response.body().getOverdue_target());
                Overduetr.setText(response.body().getOverdue_achiv());
                Overdueachiv.setText(response.body().getOverdue_target());
                Overduenote.setText(response.body().getOverdue_note());

                if ("good".equals(response.body().getOverdue_note())){
                    Overduenote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getOverdue_note())){
                    Overduenote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getOverdue_note())){
                    Overduenote.setBackgroundResource(R.drawable.redcolor);
                }


                Notattendtr.setText(response.body().getNot_attand_target());
//                Notattendtr.setText(response.body().getNot_attand_achiv());
                notattendachiv.setText(response.body().getNot_attand_achiv());
                Notattendnote.setText(response.body().getNot_attand_note());

                if ("good".equals(response.body().getNot_attand_note())){
                    Notattendnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getNot_attand_note())){
                    Notattendnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getNot_attand_note())){
                    Notattendnote.setBackgroundResource(R.drawable.redcolor);
                }


                FirstMeettr.setText(response.body().getFirst_metting_target());
//                FirstMeettr.setText(response.body().getFirst_metting_achiv());
                firstachiv.setText(response.body().getFirst_metting_achiv());
                FirstMeetnote.setText(response.body().getFirst_metting_note());

                if ("good".equals(response.body().getFirst_metting_note())){
                    FirstMeetnote.setBackgroundResource(R.drawable.green);
                }
                else if("medium".equals(response.body().getFirst_metting_note())){
                    FirstMeetnote.setBackgroundResource(R.drawable.yellow);
                }
                else if ("Lower".equals(response.body().getFirst_metting_note())){
                    FirstMeetnote.setBackgroundResource(R.drawable.redcolor);
                }

            }

            @Override
            public void onFailure(Call<perfomance_model> call, Throwable t) {

            }
        });
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.download_file, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.action_download ){

//            public void shareRelativeLayout(RelativeLayout layout) {

                rv_main.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(rv_main.getDrawingCache());
                rv_main.setDrawingCacheEnabled(false);

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
                    startActivity(Intent.createChooser(intent, "Share layout image"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            }



            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}