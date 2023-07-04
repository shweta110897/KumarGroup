package com.example.kumarGroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LanchBreak extends AppCompatActivity {

    TextView emergencycall1,emergencycall2;
    ImageView mImageViewlanchbreak,startlunchbreak;
    Integer REQUEST_CAMERA=4,SELECT_FILE=0;
    Button btnlanchbreck,btn_exit,btn_continue;
    Uri urilanch;
    String waypathClient,waypathlanch;
    String emp,id;
    LinearLayout layout;
    SharedPreferences sp;
    ProgressDialog progressDialog;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanch_break);
        getSupportActionBar().setTitle( ( "Emergency Call"));

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp.getString("emp_id","");
        id=sp.getString("id","");
        Log.d("idget", "onCreate: "+id);
        layout = findViewById(R.id.layout);
      //  mImageViewlanchbreak = findViewById(R.id.mImageViewlanchbreak);
        startlunchbreak = findViewById(R.id.startlunchbreak);
        btn_exit = findViewById(R.id.btn_exit);
        btn_continue = findViewById(R.id.btn_continue);
        emergencycall1 = findViewById(R.id.emergencycall1);
        emergencycall2 = findViewById(R.id.emergencycall2);

   //     endlaunchbreck=findViewById(R.id.endlaunchbreck);
        // mImageView=findViewById(R.id.mImageView);
       // btnlanchbreck=findViewById(R.id.btnnextsatape);
        btnlanchbreck=findViewById(R.id.btnlanchbreck);
   //     startlunchbreak=findViewById(R.id.startlunchbreak);
        layout.setVisibility(View.GONE);
        startlunchbreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startlunchbreak.setVisibility(View.INVISIBLE);
                btnlanchbreck.setVisibility(View.INVISIBLE);
                btn_exit.setVisibility(View.VISIBLE);
                btn_continue.setVisibility(View.VISIBLE);

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("hh:mm a");

                 time =  format.format(calendar.getTime()).toLowerCase();
                Log.d("time", "onClick: "+time);

                if(layout.getVisibility() == View.VISIBLE){
                    layout.setVisibility(View.GONE);
                } else {
                    layout.setVisibility(View.VISIBLE);
                }
            }
    });
        btnlanchbreck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LanchBreak.this,ChackOut.class);
                startActivity(i);


            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog= new ProgressDialog(LanchBreak.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                WebService.getClient().getlanchbrack(
                        RequestBody.create(MediaType.parse("text/plain"), emp),
                        RequestBody.create(MediaType.parse("text/plain"), time),
                        RequestBody.create(MediaType.parse("text/plain"), id)

                        ).enqueue(new Callback<LanchBreckModel>() {
                    @Override
                    public void onResponse(Call<LanchBreckModel> call, Response<LanchBreckModel> response) {
                        progressDialog.dismiss();

                        Log.d("Response", response.body().toString());
                        Toast.makeText(LanchBreak.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LanchBreak.this,FoDashbord.class);
                        startActivity(i);
                        finish();

                    }
                    @Override
                    public void onFailure(Call<LanchBreckModel> call, Throwable t) {
                        progressDialog.dismiss();

                        Log.d("Response2", t.getMessage());
                        Toast.makeText(LanchBreak.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog= new ProgressDialog(LanchBreak.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                WebService.getClient().getlanchbrack2(
                        RequestBody.create(MediaType.parse("text/plain"), emp),
                        RequestBody.create(MediaType.parse("text/plain"), time),
                        RequestBody.create(MediaType.parse("text/plain"), id)

                ).enqueue(new Callback<LanchBreckModel2>() {
                    @Override
                    public void onResponse(Call<LanchBreckModel2> call, Response<LanchBreckModel2> response) {
                        progressDialog.dismiss();

                        Log.d("Response", response.body().toString());
                        Toast.makeText(LanchBreak.this, "" + response.body().getMsg(), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LanchBreak.this,ChackOut.class);
                        startActivity(i);
                        finish();

                    }
                    @Override
                    public void onFailure(Call<LanchBreckModel2> call, Throwable t) {
                        progressDialog.dismiss();

                        Log.d("Response2", t.getMessage());
                        Toast.makeText(LanchBreak.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



            }
        });
        emergencycall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"9898656575"));

                startActivity(intent);
            }
    });
        emergencycall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"7505786792"));
                startActivity(intent);
            }
        });
    }


    /*@Override
    public void onResume(){
        super.onResume();

        WebService.getClient().getphase(id).enqueue(new Callback<ChackphaseModel>() {
            @Override
            public void onResponse(@NotNull Call<ChackphaseModel> call, @NotNull Response<ChackphaseModel> response) {
                assert response.body() != null;
                Log.d("Phase", "onResponse: " + response.body().getPhase());

                sp.edit().putString("CheckInPhase", response.body().getPhase()).apply();

                if (response.body().getPhase().equals("13")) {
                    Intent i = new Intent(LanchBreak.this, ChackOut.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ChackphaseModel> call, @NotNull Throwable t) {

                Toast.makeText(LanchBreak.this, "null", Toast.LENGTH_SHORT).show();
            }
        });

    }*/

}


