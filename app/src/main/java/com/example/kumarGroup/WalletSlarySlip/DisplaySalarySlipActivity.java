package com.example.kumarGroup.WalletSlarySlip;

import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.kumarGroup.R;
import com.example.kumarGroup.SalarySlipModel;
import com.example.kumarGroup.WebService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplaySalarySlipActivity extends AppCompatActivity {

    String date1,date2;
    SharedPreferences sp1;
    String emp;
    RecyclerView RclvDisplaySalarySlip;
    ProgressDialog progressDialog;
    FloatingActionButton floatingBtnPDF;


    File pdfFile;

    View title;
    ConstraintLayout constraintLayout;
    LinearLayout scroll;
    Bitmap bitmap;
    String filename;

    File mFolder;
    File mFile;
    Context context;

    //Assuming your rootView is called mRootView like so
    private View mRootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_salary_slip);

        getSupportActionBar().setTitle("Salary Report");

        sp1 = getSharedPreferences("Login",MODE_PRIVATE);
        emp=sp1.getString("emp_id","");

        date1 = getIntent().getStringExtra("StartingDateOne");
        date2 = getIntent().getStringExtra("EndingDateTwo");

        floatingBtnPDF= findViewById(R.id.floatingBtnPDF);
        RclvDisplaySalarySlip= findViewById(R.id.RclvDisplaySalarySlip);
        RclvDisplaySalarySlip.setHasFixedSize(true);
        RclvDisplaySalarySlip.setLayoutManager(new LinearLayoutManager(this));

        progressDialog= new ProgressDialog(DisplaySalarySlipActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        scroll = findViewById(R.id.scroll);

        WebService.getClient().getSalarySlip(emp,date1,date2).enqueue(new Callback<SalarySlipModel>() {
            @Override
            public void onResponse(@NotNull Call<SalarySlipModel> call, @NotNull Response<SalarySlipModel> response) {
                progressDialog.dismiss();

                    SalarySlipAdapter adapter = new SalarySlipAdapter(DisplaySalarySlipActivity.this,response.body().getSalarysleep());
                    RclvDisplaySalarySlip.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<SalarySlipModel> call, @NotNull Throwable t) {
                progressDialog.dismiss();
            }
        });


        floatingBtnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("size", " " + scroll.getWidth() + "  " + scroll.getWidth());
                bitmap = loadBitmapFromView(scroll, scroll.getWidth(), scroll.getHeight());
                createPdf();

              /*  ViewTreeObserver viewTreeObserver = title.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            title.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                           viewWidth = title.getWidth();

                           viewHeight = title.getHeight();
                            Log.d("height",""+viewHeight);
                        }
                    });
                }*/

              /*  mFile = new File(mFolder, filename + ".pdf");
                if (!mFile.exists()) {
                    //title.getMeasuredHeight();
                   // int height = title.getHeight() + bitmap.getHeight();
                    int height = title.getMeasuredHeight()+ bitmap.getHeight();
                    Log.d("height_",""+height);
                    PdfDocument document = new PdfDocument();
                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), height, 1).create();
                    PdfDocument.Page page = document.startPage(pageInfo);
                    Canvas canvas = page.getCanvas();
                    title.draw(canvas);

                    canvas.drawBitmap(bitmap, null, new Rect(0, title.getHeight(), bitmap.getWidth(),bitmap.getHeight()), null);

                    document.finishPage(page);


                    try {
                        mFile.createNewFile();
                        OutputStream out = new FileOutputStream(mFile);
                        document.writeTo(out);
                        document.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/


            /*    Bitmap recycler_view_bm =     getScreenshotFromRecyclerView(RclvDisplaySalarySlip);

                try {

                    pdfFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(pdfFile);

                    PdfDocument document = new PdfDocument();
                    PdfDocument.PageInfo pageInfo = new
                            PdfDocument.PageInfo.Builder(recycler_view_bm.getWidth(), recycler_view_bm.getHeight(), 1).create();
                    PdfDocument.Page page = document.startPage(pageInfo);
                    recycler_view_bm.prepareToDraw();
                    Canvas c;
                    c = page.getCanvas();
                    c.drawBitmap(recycler_view_bm,0,0,null);
                    document.finishPage(page);
                    document.writeTo(fOut);
                    document.close();
                    Snackbar snackbar = Snackbar
                            .make(constraintLayout, "PDF generated successfully.", Snackbar.LENGTH_LONG)
                            .setAction("Open", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                  //  openPDFRecord(pdfFile);
                                }
                            });

                    snackbar.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public Bitmap getScreenshotFromRecyclerView(RecyclerView view) {
                RecyclerView.Adapter adapter = view.getAdapter();
                Bitmap bigBitmap = null;
                if (adapter != null) {
                    int size = adapter.getItemCount();
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

                    for (int i = 0; i < size; i++) {
                        Bitmap bitmap = bitmaCache.get(String.valueOf(i));
                        bigCanvas.drawBitmap(bitmap, 0f, iHeight, paint);
                        iHeight += bitmap.getHeight();
                        bitmap.recycle();
                    }

                }
                return bigBitmap;

            }*/
            }

        });

    }


    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
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
        String targetPdf = "/storage/emulated/0/SalaryReport.pdf";
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
        File outputFile = new File("/storage/emulated/0/SalaryReport.pdf");
       // Uri uri = Uri.fromFile(outputFile);
        Uri uri = FileProvider.getUriForFile(context,
                DisplaySalarySlipActivity.this.getPackageName() + ".provider", outputFile);
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, uri);
      //  share.setPackage("com.whatsapp");
        startActivity(share);
    }

    private void openGeneratedPDF(){
     //   File file = new File("/sdcard/pdffromlayout.pdf");
        File file = new File("/storage/emulated/0/SalaryReport.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
          //  Uri uri = Uri.fromFile(file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri uri = FileProvider.getUriForFile(context, DisplaySalarySlipActivity.this.getPackageName() + ".provider", file);
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
                Toast.makeText(DisplaySalarySlipActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
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