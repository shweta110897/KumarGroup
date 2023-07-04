package com.example.kumarGroup.Workshop.Invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kumarGroup.InvoiceViewModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewInvoiceNewActivity extends AppCompatActivity {

    RecyclerView RclvNewInvoiceDesign;

    ProgressDialog progressDialog;

    String id;

    FloatingActionButton floatingBtnPDF;

    //  LinearLayout scroll;
    LinearLayout scroll;

    Bitmap bitmap;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invoice_new);


        RclvNewInvoiceDesign=findViewById(R.id.RclvNewInvoiceDesign);
        RclvNewInvoiceDesign.setHasFixedSize(true);
        RclvNewInvoiceDesign.setLayoutManager(new LinearLayoutManager(this));


        scroll=findViewById(R.id.scroll);
        floatingBtnPDF=findViewById(R.id.floatingBtnPDF);

        id = getIntent().getStringExtra("id");


        progressDialog= new ProgressDialog(ViewInvoiceNewActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getInvoiceView(id).enqueue(new Callback<InvoiceViewModel>() {
            @Override
            public void onResponse(@NotNull Call<InvoiceViewModel> call, @NotNull Response<InvoiceViewModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(ViewInvoiceNewActivity.this,"No Data Available");
                }
                else {

                    ViewInvoiceNewAdapter adapter = new ViewInvoiceNewAdapter(ViewInvoiceNewActivity.this,
                            response.body().getData());
                    RclvNewInvoiceDesign.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(@NotNull Call<InvoiceViewModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });


        floatingBtnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("size", " " + scroll.getWidth() + "  " + scroll.getWidth());
                bitmap = loadBitmapFromView(scroll, scroll.getWidth(), scroll.getHeight());
                createPdf();
            }
        });

    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf()
    {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        // this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        // String targetPdf = "/sdcard/SalaryReport.pdf";
        String targetPdf = "/storage/emulated/0/Invoice.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();

        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

        // openGeneratedPDF();
        sharePDF();
        // openGeneratedPDF();
    }

    private void sharePDF() {
        // File outputFile = new File("/storage/emulated/0/SalaryReport.pdf");
        File outputFile = new File("/storage/emulated/0/Invoice.pdf");
        // Uri uri = Uri.fromFile(outputFile);
        Uri uri = FileProvider.getUriForFile(context,
                ViewInvoiceNewActivity.this.getPackageName() + ".provider", outputFile);
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        //  share.setPackage("com.whatsapp");
        startActivity(share);
    }

    private void openGeneratedPDF(){
        //   File file = new File("/sdcard/pdffromlayout.pdf");
        File file = new File("/storage/emulated/0/Invoice.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            //  Uri uri = Uri.fromFile(file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri uri = FileProvider.getUriForFile(context, ViewInvoiceNewActivity.this.getPackageName() + ".provider", file);
            intent.setDataAndType(uri, "application/pdf");
            //   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            Intent i = Intent.createChooser(intent, "Open File");
            try
            {
                // startActivity(intent);
                startActivity(i);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(ViewInvoiceNewActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }


            /*
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ filename);
Intent target = new Intent(Intent.ACTION_VIEW);
target.setDataAndType(Uri.fromFile(file),"application/pdf");
target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

Intent intent = Intent.createChooser(target, "Open File");
try {
    startActivity(intent);
} catch (ActivityNotFoundException e) {
    // Instruct the user to install a PDF reader here, or something
} */
        }
    }


}