package com.example.kumarGroup.Workshop.Invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
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
import android.widget.ScrollView;
import android.widget.TextView;
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

public class ViewInvoiceActivity extends AppCompatActivity {

    TextView tv_Cname_ViewInvoice,tv_mobile_no_ViewInvoice,tv_Village_ViewInvoice,tv_Taluko_ViewInvoice,
            tv_District_ViewInvoice,tv_MechanicName_ViewInvoice,
            tv_EngineNo_ViewInvoice,tv_ChasisNo_ViewInvoice,tv_Date_ViewInvoice,
            tv_WorkType_ViewInvoice;

    //Row1
    TextView tv_partNo_IV,tv_Part_Detail_IV,tv_Part_Qty_VI,tv_Part_Rate_VI,tv_Part_Total;

    //ROw2
    TextView tv_PetrolNo_IV,tv_Petrol_Detail_IV,tv_Petrol_Qty_VI,tv_Petrol_Rate_VI,tv_Petrol_Total;

    //Row3
    TextView tv_LaberNo_IV,tv_Laber_Detail_IV,tv_Laber_Qty_VI,tv_Laber_Rate_VI,tv_Laber_Total;

    TextView tv_DealPrice_ViewInvoice,tv_DealPriceWord_ViewInvoice;


    ProgressDialog progressDialog;

    String id;

    RecyclerView rclvViewInvoice;


    FloatingActionButton floatingBtnPDFWS;

  //  LinearLayout scroll;
    ScrollView scroll;

    Bitmap bitmap;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invoice);


        id = getIntent().getStringExtra("id");


       /* rclvViewInvoice=findViewById(R.id.rclvViewInvoice);
        rclvViewInvoice.setHasFixedSize(true);
        rclvViewInvoice.setLayoutManager(new LinearLayoutManager(this));
        rclvViewInvoice.setNestedScrollingEnabled(true);*/


        tv_Cname_ViewInvoice=findViewById(R.id.tv_Cname_ViewInvoice);
        tv_mobile_no_ViewInvoice=findViewById(R.id.tv_mobile_no_ViewInvoice);
        tv_Village_ViewInvoice=findViewById(R.id.tv_Village_ViewInvoice);
        tv_Taluko_ViewInvoice=findViewById(R.id.tv_Taluko_ViewInvoice);
        tv_District_ViewInvoice=findViewById(R.id.tv_District_ViewInvoice);
        tv_MechanicName_ViewInvoice=findViewById(R.id.tv_MechanicName_ViewInvoice);
        tv_EngineNo_ViewInvoice=findViewById(R.id.tv_EngineNo_ViewInvoice);
        tv_ChasisNo_ViewInvoice=findViewById(R.id.tv_ChasisNo_ViewInvoice);
        tv_Date_ViewInvoice=findViewById(R.id.tv_Date_ViewInvoice);
        tv_WorkType_ViewInvoice=findViewById(R.id.tv_WorkType_ViewInvoice);
        
        
        tv_partNo_IV=findViewById(R.id.tv_partNo_IV);
        tv_Part_Detail_IV=findViewById(R.id.tv_Part_Detail_IV);
        tv_Part_Qty_VI=findViewById(R.id.tv_Part_Qty_VI);
        tv_Part_Rate_VI=findViewById(R.id.tv_Part_Rate_VI);
        tv_Part_Total=findViewById(R.id.tv_Part_Total);
        
        tv_PetrolNo_IV=findViewById(R.id.tv_PetrolNo_IV);
        tv_Petrol_Detail_IV=findViewById(R.id.tv_Petrol_Detail_IV);
        tv_Petrol_Qty_VI=findViewById(R.id.tv_Petrol_Qty_VI);
        tv_Petrol_Rate_VI=findViewById(R.id.tv_Petrol_Rate_VI);
        tv_Petrol_Total=findViewById(R.id.tv_Petrol_Total);
        
        tv_LaberNo_IV=findViewById(R.id.tv_LaberNo_IV);
        tv_Laber_Detail_IV=findViewById(R.id.tv_Laber_Detail_IV);
        tv_Laber_Qty_VI=findViewById(R.id.tv_Laber_Qty_VI);
        tv_Laber_Rate_VI=findViewById(R.id.tv_Laber_Rate_VI);
        tv_Laber_Total=findViewById(R.id.tv_Laber_Total);
        
        tv_DealPrice_ViewInvoice=findViewById(R.id.tv_DealPrice_ViewInvoice);
        tv_DealPriceWord_ViewInvoice=findViewById(R.id.tv_DealPriceWord_ViewInvoice);


        floatingBtnPDFWS=findViewById(R.id.floatingBtnPDFWS);

        scroll=findViewById(R.id.scroll);


        progressDialog= new ProgressDialog(ViewInvoiceActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        WebService.getClient().getInvoiceView(id).enqueue(new Callback<InvoiceViewModel>() {
            @Override
            public void onResponse(@NotNull Call<InvoiceViewModel> call, @NotNull Response<InvoiceViewModel> response) {
                progressDialog.dismiss();

                assert response.body() != null;

                if(response.body().getData().size() == 0){
                    Utils.showErrorToast(ViewInvoiceActivity.this,"No Data Available");
                }
                else {

                   /* ViewInvoiceAdapter adapter = new ViewInvoiceAdapter(ViewInvoiceActivity.this,
                            response.body().getData());
                    rclvViewInvoice.setAdapter(adapter);*/



                    tv_Cname_ViewInvoice.setText("Customer Name: "+response.body().getData().get(0).getCname());
                    tv_mobile_no_ViewInvoice.setText("Mobile No: "+response.body().getData().get(0).getMobileno());
                    tv_Village_ViewInvoice.setText("Village: "+response.body().getData().get(0).getVillage());
                    tv_Taluko_ViewInvoice.setText("Taluko: "+response.body().getData().get(0).getTehsil());
                    tv_District_ViewInvoice.setText("District: "+response.body().getData().get(0).getDistric());
                    tv_MechanicName_ViewInvoice.setText("Mechanic Name: "+response.body().getData().get(0).getMacanic_name());
                    tv_EngineNo_ViewInvoice.setText("Engine No: "+response.body().getData().get(0).getEngine_no());
                    tv_ChasisNo_ViewInvoice.setText("Chasis No: "+response.body().getData().get(0).getChasis_no());
                    tv_Date_ViewInvoice.setText("Date: "+response.body().getData().get(0).getEntry_date());
                    tv_WorkType_ViewInvoice.setText("Work Type: "+response.body().getData().get(0).getWorks_ser());


                    String Rate = response.body().getData().get(0).getRate();
                    String Rate1 =  Rate.replaceAll(",", "\n");

                    String PartNo = response.body().getData().get(0).getMno();
                    String PartNo1 = PartNo.replaceAll(",","\n");

                    String PartDetail = response.body().getData().get(0).getModel_name();
                    String PartDetail1 = PartDetail.replaceAll(",","\n");

                    String PartQty = response.body().getData().get(0).getQty();
                    String PartQty1 = PartQty.replaceAll(",","\n");

                    String PartTot = response.body().getData().get(0).getQty();
                    String PartTot1 = PartTot.replaceAll(",","\n");


                    tv_partNo_IV.setText(""+PartNo1);
                    tv_Part_Detail_IV.setText(""+PartDetail1);
                    tv_Part_Qty_VI.setText(""+PartQty1);
                    tv_Part_Rate_VI.setText(""+Rate1);
                    tv_Part_Total.setText(""+PartTot1);


                    tv_Petrol_Detail_IV.setText("Petrol Charge");
                    tv_Petrol_Total.setText(response.body().getData().get(0).getPetrol_charge());

                    tv_Laber_Detail_IV.setText("Labor Charge");
                    tv_Laber_Total.setText(response.body().getData().get(0).getPetrol_charge());

                    tv_DealPrice_ViewInvoice.setText("Total: "+response.body().getData().get(0).getDeal_price());
                    tv_DealPriceWord_ViewInvoice.setText("Total Price In Words: "
                            +response.body().getData().get(0).getDeal_price_word());

                }
            }

            @Override
            public void onFailure(@NotNull Call<InvoiceViewModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });




        floatingBtnPDFWS.setOnClickListener(new View.OnClickListener() {
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
                ViewInvoiceActivity.this.getPackageName() + ".provider", outputFile);
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
            Uri uri = FileProvider.getUriForFile(context, ViewInvoiceActivity.this.getPackageName() + ".provider", file);
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
                Toast.makeText(ViewInvoiceActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
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