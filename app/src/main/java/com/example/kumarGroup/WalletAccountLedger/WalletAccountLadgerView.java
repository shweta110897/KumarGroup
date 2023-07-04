package com.example.kumarGroup.WalletAccountLedger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kumarGroup.Dataentry1;
import com.example.kumarGroup.ItemDateComparator1;
import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import com.example.kumarGroup.cashbook_getDate_model;
import com.example.kumarGroup.ladger_getdata_model;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletAccountLadgerView extends AppCompatActivity {

    RecyclerView dataentryRecyclerView,dataFilterRecyclerView;
    WalletAccountLadgerAdapter1 walletAccountLadgerAdapter1;
    Button btn_download;

    Spinner ladger_start_date,ladger_end_date;


    TextView das_bank,das_inhand,das_fulltotal,tvname;
    List<String> datelist_start = new ArrayList<>();
    List<String> datelist_end = new ArrayList<>();
    String start_date,end_date;

    String emp,emp_name;
    SharedPreferences sp;

    List<Dataentry1> mdataentrylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_account_ladger_view);


        dataentryRecyclerView = findViewById(R.id.dataentryRecyclerView);
        dataFilterRecyclerView = findViewById(R.id.dataFilterRecyclerView);

        btn_download = findViewById(R.id.btn_download);
        das_bank = findViewById(R.id.das_bank);
        das_inhand = findViewById(R.id.das_inhand);
        das_fulltotal = findViewById(R.id.das_fulltotal);
        tvname = findViewById(R.id.tvname);

        mdataentrylist=new ArrayList<>();


        //date filter

        //AllocateMemory
        ladger_start_date = findViewById(R.id.start_date);
        ladger_end_date = findViewById(R.id.end_date);


        ladger_start_date.setFocusable(false);
        ladger_end_date.setFocusable(false);

        sp = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp.getString("emp_id","");
        emp_name=sp.getString("emp_name","");

        getentryData();

        WebService.getClient().cashbook_getDate().enqueue(new Callback<cashbook_getDate_model>() {
            @Override
            public void onResponse(Call<cashbook_getDate_model> call, Response<cashbook_getDate_model> response) {
                if (0 != response.body().getCat().size()){
                    datelist_start.clear();

                    datelist_start.add("Start Date");
                    datelist_end.add("End Date");

                    for (int i = 0; i < response.body().getCat().size(); i++) {
                        datelist_start.add(response.body().getCat().get(i).getCudate());
                        datelist_end.add(response.body().getCat().get(i).getCudate());

                    }

                    ArrayAdapter adapter1 = new ArrayAdapter(WalletAccountLadgerView.this, android.R.layout.simple_spinner_dropdown_item, datelist_start);
                    ladger_start_date.setAdapter(adapter1);

                    ArrayAdapter adapter2 = new ArrayAdapter(WalletAccountLadgerView.this, android.R.layout.simple_spinner_dropdown_item, datelist_end);
                    ladger_end_date.setAdapter(adapter2);


                    ladger_start_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // state = category.get(position);
                            // stateId = categoryId.get(position);

                            if(datelist_start.get(position).equals("Start Date")){
                                //Utils.showErrorToast(WalletAccountLadgerView.this,"Please select Start Date");
                                start_date = "";
                            }
                            else{
                                start_date = datelist_start.get(position);

                                if (!end_date.equals("") && !"End Date".equals(end_date)) {
                                    callapi_filter_date();
                                }
                            }

                            Log.d("TAG", "startdate "+start_date);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ladger_end_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(datelist_end.get(position).equals("End Date")){
                                end_date = "";
                            }
                            else{
                                end_date = datelist_end.get(position);

                                if (!start_date.equals("") && !"Start Date".equals(start_date)) {
                                    callapi_filter_date();
                                }
                                else {
                                    Utils.showErrorToast(WalletAccountLadgerView.this,"Please Select Start Date");
                                }
                            }

                            Log.d("TAG", "enddate "+start_date);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<cashbook_getDate_model> call, Throwable t) {
                Log.d("TAG", "onFailure: check123 message2 "+t.getMessage());

                Utils.showErrorToast(WalletAccountLadgerView.this,t.getMessage());
            }
        });



        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  // Create a new `PdfDocument` object
                PdfDocument document = new PdfDocument();

// Set the page size and margin for the document
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
                PdfDocument.Page page = document.startPage(pageInfo);

// Create a `Canvas` object to draw the content onto the page
                Canvas canvas = page.getCanvas();

// Draw the header item on a separate page
                PdfDocument.PageInfo headerInfo = new PdfDocument.PageInfo.Builder(595, 30, 1).create();
                PdfDocument.Page headerPage = document.startPage(headerInfo);
                Canvas headerCanvas = headerPage.getCanvas();
// draw your header content on headerCanvas
                // Set the color and size of the text
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setTextSize(24);

                // Measure the width of the text
                float textWidth = paint.measureText(emp_name);

// Calculate the x and y coordinates to center the text on the canvas
                float x = (canvas.getWidth() - textWidth) / 2;
                float y = (canvas.getHeight() - paint.getTextSize()) / 2;


// Draw the text onto the canvas
                canvas.drawText(emp_name, x, y, paint);
                document.finishPage(headerPage);

// Draw the footer item on a separate page
                PdfDocument.PageInfo footerInfo = new PdfDocument.PageInfo.Builder(595, 50, 1).create();
                PdfDocument.Page footerPage = document.startPage(footerInfo);
                Canvas footerCanvas = footerPage.getCanvas();
// draw your footer content on footerCanvas
                document.finishPage(footerPage);

// Iterate through the items in the `RecyclerView` and draw each one onto a separate page

                for (int i = 0; i < dataentryRecyclerView.getAdapter().getItemCount(); i++) {
                    // Create a new page for each item
                    PdfDocument.PageInfo itemInfo = new PdfDocument.PageInfo.Builder(595, 842, i + 3).create();
                    PdfDocument.Page itemPage = document.startPage(itemInfo);
                    Canvas itemCanvas = itemPage.getCanvas();

                    // Get the `ViewHolder` for the current item and bind the data to it
                    RecyclerView.ViewHolder viewHolder = dataentryRecyclerView.findViewHolderForAdapterPosition(i);
                    dataentryRecyclerView.getAdapter().onBindViewHolder(viewHolder, i);

                    // Measure and layout the item view
                    View itemView = viewHolder.itemView;
                    int itemWidth = itemView.getWidth();
                    int itemHeight = itemView.getHeight();
                    itemView.measure(View.MeasureSpec.makeMeasureSpec(itemWidth, View.MeasureSpec.EXACTLY),
                            View.MeasureSpec.makeMeasureSpec(itemHeight, View.MeasureSpec.EXACTLY));
                    itemView.layout(0, 0, itemWidth, itemHeight);

                    // Draw the item view onto the page
                    itemView.draw(itemCanvas);

                    // Finish the page
                    document.finishPage(itemPage);
                }

                // Save the PDF to a file
                File file = new File(getExternalFilesDir( Environment.DIRECTORY_DOCUMENTS), "example.pdf");
                try {
                    document.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

               /* File pdfDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS), "MyProject");
                if (!pdfDir.exists()){
                    pdfDir.mkdir();
                }

                File pdfFile = new File(pdfDir, "myPdfFile_new.pdf");
                PdfWriter writer =null;
                try {
                    writer= PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
*/

// Close the document
//                document.close();



//                tvname.setText(emp_name);
//                tvname.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 2 seconds

                        try {
                            generatePDF(dataentryRecyclerView);
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                    }
                }, 2000);


//                generatePdf();
//                startActivity(new Intent(WalletAccountLadgerView.this, OutButtionActivity.class));
            }
        });

    }

    private void generatePDF(RecyclerView view) throws DocumentException {
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
            PdfWriter writer =null;
            try {
                writer= PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            document.open();

           /* Paragraph p1 = new Paragraph("ASWorld");
            Font paraFont= new Font(Font.FontFamily.COURIER);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setFont(paraFont);

            //add paragraph to document
            try {
                document.add(p1);
            } catch (DocumentException e) {
                e.printStackTrace();
            }*/

//            Font mOrderDetailsTitleFont1 = new Font( Font.FontFamily.COURIER,36.0f, Font.NORMAL, BaseColor.BLACK);
//// Creating Chunk
//            Chunk mOrderDetailsTitleChunk1 = new Chunk("Ladger Details of "+emp_name, mOrderDetailsTitleFont1);
//// Creating Paragraph to add...
//            Paragraph mOrderDetailsTitleParagraph1 = new Paragraph(mOrderDetailsTitleChunk1);
//// Setting Alignment for Heading
//            mOrderDetailsTitleParagraph1.setAlignment(Element.ALIGN_CENTER);
//// Finally Adding that Chunk
//            try {
//                document.add(mOrderDetailsTitleParagraph1);
//            } catch (DocumentException e) {
//                e.printStackTrace();
//            }
            Image image = null;
            for (int i = 0; i < size; i++) {

                try {
                    //Adding the content to the document
                    Bitmap bmp = bitmaCache.get(String.valueOf(i));
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    image = Image.getInstance(stream.toByteArray());
                    float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                            - document.rightMargin() - 0) / image.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
                    image.scalePercent(scaler);
                    image.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER | com.itextpdf.text.Image.ALIGN_TOP);

                } catch (Exception ex) {
                    Log.e("TAG-ORDER PRINT ERROR", ex.getMessage());
                }
                if (!document.isOpen()) {
                    document.open();
                }
                document.add(image);

            }
            Font mOrderDetailsTitleFont = new Font( Font.FontFamily.COURIER,26.0f, Font.NORMAL, BaseColor.GREEN);
// Creating Chunk
            Chunk mOrderDetailsTitleChunk = new Chunk("Total Balance :- Rs."+das_fulltotal.getText().toString(), mOrderDetailsTitleFont);
// Creating Paragraph to add...
            Paragraph mOrderDetailsTitleParagraph = new Paragraph(mOrderDetailsTitleChunk);
// Setting Alignment for Heading
            mOrderDetailsTitleParagraph.setAlignment(Element.ALIGN_CENTER);
// Finally Adding that Chunk
            try {
                document.add(mOrderDetailsTitleParagraph);
            } catch (DocumentException e) {
                e.printStackTrace();
            }


            if (document.isOpen()) {
                document.close();
            }
            // Set on UI Thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    AlertDialog.Builder builder = new AlertDialog.Builder(WalletAccountLadgerView.this);
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


    private void callapi_filter_date() {

        if ("".equals(start_date) && "".equals(end_date)){
            Utils.showErrorToast(WalletAccountLadgerView.this,"Please select date");
        }
        else {


            WebService.getClient().emp_ledger_datefilter(start_date, end_date,emp).enqueue(new Callback<ladger_getdata_model>() {
                @Override
                public void onResponse(Call<ladger_getdata_model> call, Response<ladger_getdata_model> response) {
                    if (0 != response.body().getData().size()) {
                        dataentryRecyclerView.setVisibility(View.GONE);
                        dataFilterRecyclerView.setVisibility(View.VISIBLE);

                        Collections.sort(response.body().getData(), new ItemDateComparator1());

//                        das_trans.setText(response.body().getData().get(0).getToday_transfer());
//                        Collections.reverse(response.body().getData());
                        walletAccountLadgerAdapter1 = new WalletAccountLadgerAdapter1(WalletAccountLadgerView.this, response.body().getData()/*,response.body().getFulltotal()*/);
                        dataFilterRecyclerView.setAdapter(walletAccountLadgerAdapter1);
                    }
                    else {
                        Utils.showErrorToast(WalletAccountLadgerView.this,"No Data Found This Date");
                    }

                }

                @Override
                public void onFailure(Call<ladger_getdata_model> call, Throwable t) {
                    Utils.showErrorToast(WalletAccountLadgerView.this, t.getMessage());
                }
            });
        }
    }

    private void getentryData() {
        WebService.getClient().AccountLadgerData(emp).enqueue(new Callback<ladger_getdata_model>() {
            @Override
            public void onResponse(Call<ladger_getdata_model> call, Response<ladger_getdata_model> response) {
                if (0 != response.body().getData().size()){

                    mdataentrylist=response.body().getData();
                    Collections.sort(mdataentrylist, new ItemDateComparator1());
                    Collections.reverse(mdataentrylist);
                    walletAccountLadgerAdapter1 = new WalletAccountLadgerAdapter1(WalletAccountLadgerView.this,response.body().getData()/*,response.body().getFulltotal()*/);
                    dataentryRecyclerView.setAdapter(walletAccountLadgerAdapter1);
                }

                das_bank.setText(response.body().getBanktotal());
                Log.d("TAG", "onResponse: check123 money2 "+String.valueOf(response.body().getBanktotal()));

                das_inhand.setText(response.body().getCashtotal());
                Log.d("TAG", "onResponse: check123 money3 "+String.valueOf(response.body().getCashtotal()));

                das_fulltotal.setText(response.body().getFulltotal());
                Log.d("TAG", "onResponse: check123 money4 "+String.valueOf(response.body().getFulltotal()));


            }

            @Override
            public void onFailure(Call<ladger_getdata_model> call, Throwable t) {
                Log.d("TAG", "onFailure: check123 message1 "+t.getMessage());
                Utils.showErrorToast(WalletAccountLadgerView.this,t.getMessage());
            }
        });
    }
}