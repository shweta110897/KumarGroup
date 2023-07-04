package com.example.kumarGroup.WalletAccountLedger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;

import com.example.kumarGroup.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class WalletAcLadgerActivity extends AppCompatActivity {

    Button btn_view,btn_download;
    RecyclerView rvLadgerList;

    String emp;
    SharedPreferences sp;
    ProgressDialog progressDialog;
    ArrayList<WalletAccountLadgerView> mcatlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_ac_ladger);

        getSupportActionBar().setTitle("Account Ladger");

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp.getString("emp_id","");

        btn_view=findViewById(R.id.btn_view);
        btn_download=findViewById(R.id.btn_download);
        rvLadgerList=findViewById(R.id.rvLadgerList);

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvLadgerList.setVisibility(View.VISIBLE);
                Intent intent =new Intent(WalletAcLadgerActivity.this,WalletAccountLadgerView.class);
                startActivity(intent);
            }
        });

        rvLadgerList.setHasFixedSize(true);
        rvLadgerList.setLayoutManager(new LinearLayoutManager(this));

        jsonLoad();
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvLadgerList.setVisibility(View.VISIBLE);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        generatePDF(rvLadgerList);
                    }
                }, 3000);

            }
        });
    }


    /******************code for generate pdf *******************/
    public void generatePDF(RecyclerView view) {

        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            Log.e("sizeeee", String.valueOf(size));
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {

                    bitmaCache.put(String.valueOf(i), drawingCache);
                }

                height += holder.itemView.getMeasuredHeight();
            }

            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            bigCanvas.drawColor(Color.WHITE);

            Document document = new Document(PageSize.A4);

            File pdfDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "MyProject");
            if (!pdfDir.exists()){
                pdfDir.mkdir();
            }

            File pdfFile = new File(pdfDir, "myPdfFile_new.pdf");

            try {
                PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < size; i++) {

                try {
                    //Adding the content to the document
                    Bitmap bmp = bitmaCache.get(String.valueOf(i));
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    Image image = Image.getInstance(stream.toByteArray());
                    float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                            - document.rightMargin() - 0) / image.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
                    image.scalePercent(scaler);
                    image.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER | com.itextpdf.text.Image.ALIGN_TOP);
                    if (!document.isOpen()) {
                        document.open();
                    }
                    document.add(image);

                } catch (Exception ex) {
                    Log.e("TAG-ORDER PRINT ERROR", ex.getMessage());
                }
            }

            if (document.isOpen()) {
                document.close();
            }
            // Set on UI Thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    AlertDialog.Builder builder = new AlertDialog.Builder(WalletAcLadgerActivity.this);
                    builder.setTitle("Success")
                            .setMessage("PDF File Generated Successfully.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(pdfFile), "application/pdf");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);
                                }

                            }).show();
                }
            });

        }

    }




    private void jsonLoad() {

        progressDialog= new ProgressDialog(WalletAcLadgerActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

       /* WebService.getClient().WalletAccountLadgerView(emp).enqueue(new Callback<ArrayList<AccountLadgerData>>()
        {
            @Override
            public void onResponse(@NotNull Call<ArrayList<AccountLadgerData>> call, @NotNull Response<ArrayList<AccountLadgerData>> response)
            {

                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    mcatlist = response.body();
                    if (mcatlist.size() == 0) {
                        Utils.showErrorToast(WalletAcLadgerActivity.this,"No Data Found");

                    } else {
                        // Utils.showErrorToast(WalletAttendance.this,"data found");

                        WalletAccountLadgerAdapter walletAccountLadgerAdapter = new WalletAccountLadgerAdapter(WalletAcLadgerActivity.this,mcatlist );
                        // Log.d("data", "onResponse: "+cat);
                        rvLadgerList.setAdapter(walletAccountLadgerAdapter);
                    }
                }
                else {

                    Utils.showNormalToast(WalletAcLadgerActivity.this,"Server error please try again later");

                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<AccountLadgerData>> call, @NotNull Throwable t)
            {
                progressDialog.dismiss();
                Toast.makeText(WalletAcLadgerActivity.this, "Fail", Toast.LENGTH_SHORT).show();

                Log.d("OnFailureRL", "onFailure: "+t.getCause());
            }
        });*/
    }
}