package com.example.kumarGroup.TrueValue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.kumarGroup.ActivityData;
import com.example.kumarGroup.ActivityReviewData;
import com.example.kumarGroup.Catnotattend;
import com.example.kumarGroup.DataDisplay;
import com.example.kumarGroup.DataFeedBack;
import com.example.kumarGroup.DeliveryDataDisplayModel;
import com.example.kumarGroup.FeedbackCallWSModel;
import com.example.kumarGroup.R;
import com.example.kumarGroup.RcUpdate;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.Workshop.FeedBackCall.FeedbackMainAdapter;
import com.example.kumarGroup.Deal_notattend_model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewList extends AppCompatActivity {

    RecyclerView rv_Review;
    SharedPreferences sp;
    String emp_id;

    ProgressDialog pro;

    List<Catnotattend> catShowRCGVS;
    ArrayList<ActivityData>  catActReviewData;
    AdapterReviewList adapterShowButtonData;
    AdapterActivityReview adapterActivityReview;

    SearchView searchView;
    List<RcUpdate.rcUpdate> data;
    List<DataFeedBack> dataFeedBacks;
    List<DataDisplay>  dataDisplays;

    NumbrPlateFittingReviewAdapter rc_customer_update_detail_adapter;


    public static boolean delivery_review_flag = false;
    public static boolean serviceandcomplain_review_flag = false;
    public static boolean walking_review_flag = false;
    public static boolean numbrplate_review_flag = false;
    public static boolean activity_review_flag = false;
    public static boolean firstmeeting_review_flag = false;
    public static boolean warminq_review_flag = false;
    public static boolean hotinq_review_flag = false;
    public static boolean coldinq_review_flag = false;
    public static boolean selllost_review_flag = false;
    public static boolean drop_review_flag = false;
    public static boolean new_visit_falg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        rv_Review=findViewById(R.id.rv_Review);

        sp = getSharedPreferences("Login", MODE_PRIVATE);

        emp_id = sp.getString("emp_id", "");
        pro = new ProgressDialog(this);
        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");


        if(firstmeeting_review_flag){
            firstmeeting_review_flag=false;

            WebService.getClient().getFirstMeetingReviewHistory(emp_id).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterShowButtonData = new AdapterReviewList(ReviewList.this, ReviewList.this, response.body().getCat());
                        rv_Review.setAdapter(adapterShowButtonData);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }

        if(new_visit_falg){
            new_visit_falg=false;

            WebService.getClient().getNewVisitEntry(emp_id).enqueue(new Callback<ActivityReviewData>() {
                @Override
                public void onResponse(Call<ActivityReviewData> call, Response<ActivityReviewData> response) {

                    if (0 == response.body().getData().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catActReviewData = response.body().getData();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterActivityReview = new AdapterActivityReview(ReviewList.this, ReviewList.this, catActReviewData);
                        rv_Review.setAdapter(adapterActivityReview);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ActivityReviewData> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }

        if(hotinq_review_flag){
            hotinq_review_flag=false;

            WebService.getClient().getInqTypeReviewHistory(emp_id,"HOT").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterShowButtonData = new AdapterReviewList(ReviewList.this, ReviewList.this, response.body().getCat());
                        rv_Review.setAdapter(adapterShowButtonData);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }

        if(warminq_review_flag){
            warminq_review_flag=false;

            WebService.getClient().getInqTypeReviewHistory(emp_id,"WARM").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterShowButtonData = new AdapterReviewList(ReviewList.this, ReviewList.this, response.body().getCat());
                        rv_Review.setAdapter(adapterShowButtonData);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }

        if(coldinq_review_flag){
            coldinq_review_flag=false;

            WebService.getClient().getInqTypeReviewHistory(emp_id,"COLD").enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterShowButtonData = new AdapterReviewList(ReviewList.this, ReviewList.this, response.body().getCat());
                        rv_Review.setAdapter(adapterShowButtonData);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }
        if(selllost_review_flag){
            selllost_review_flag=false;

            WebService.getClient().getSellLostReviewHistory(emp_id).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterShowButtonData = new AdapterReviewList(ReviewList.this, ReviewList.this, response.body().getCat());
                        rv_Review.setAdapter(adapterShowButtonData);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }

        if(drop_review_flag){
            drop_review_flag=false;

            WebService.getClient().getDropReviewHistory(emp_id).enqueue(new Callback<Deal_notattend_model>() {
                @Override
                public void onResponse(Call<Deal_notattend_model> call, Response<Deal_notattend_model> response) {

                    if (0 == response.body().getCat().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catShowRCGVS = response.body().getCat();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterShowButtonData = new AdapterReviewList(ReviewList.this, ReviewList.this, response.body().getCat());
                        rv_Review.setAdapter(adapterShowButtonData);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Deal_notattend_model> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }


        if(numbrplate_review_flag){
            numbrplate_review_flag=false;

            WebService.getClient().getbookingfitmentReview(emp_id).enqueue(new Callback<RcUpdate>() {
                @Override
                public void onResponse(Call<RcUpdate> call, Response<RcUpdate> response) {

                    if (response.body().getData().size()==0 ){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {

                        data = response.body().getData();
                        rv_Review.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rc_customer_update_detail_adapter = new NumbrPlateFittingReviewAdapter(ReviewList.this, ReviewList.this, data);
                        rv_Review.setAdapter(rc_customer_update_detail_adapter);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<RcUpdate> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }


        if(serviceandcomplain_review_flag){
            serviceandcomplain_review_flag=false;

            WebService.getClient().getservicecompalinReview(emp_id).enqueue(new Callback<FeedbackCallWSModel>() {
                @Override
                public void onResponse(Call<FeedbackCallWSModel> call, Response<FeedbackCallWSModel> response) {

                    if (0 == response.body().getData().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        dataFeedBacks = response.body().getData();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        FeedbackMainAdapter adapter = new FeedbackMainAdapter(ReviewList.this,
                                dataFeedBacks);
                        rv_Review.setAdapter(adapter);;
                      /*  adapterShowButtonData = new AdapterReviewList(ReviewList.this, ReviewList.this, response.body().getCat());
                        rv_Review.setAdapter(adapterShowButtonData);*/

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<FeedbackCallWSModel> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }


        if(delivery_review_flag){
            delivery_review_flag=false;

            WebService.getClient().getDeliveryHistory(emp_id).enqueue(new Callback<DeliveryDataDisplayModel>() {
                @Override
                public void onResponse(Call<DeliveryDataDisplayModel> call, Response<DeliveryDataDisplayModel> response) {

                    if (0 == response.body().getData().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        dataDisplays = response.body().getData();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                       /* adapterShowButtonData = new AdapterReviewList(ReviewList.this, ReviewList.this, response.body().getCat());
                        rv_Review.setAdapter(adapterShowButtonData);*/
                        DeliveryReviewAdapter adapter = new DeliveryReviewAdapter(ReviewList.this,
                                dataDisplays);
                        rv_Review.setAdapter(adapter);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<DeliveryDataDisplayModel> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }

        /*getActivityReview*/
        if(activity_review_flag){
            activity_review_flag=false;

            WebService.getClient().getActivityReview(emp_id).enqueue(new Callback<ActivityReviewData>() {
                @Override
                public void onResponse(Call<ActivityReviewData> call, Response<ActivityReviewData> response) {

                    if (0 == response.body().getData().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catActReviewData = response.body().getData();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterActivityReview = new AdapterActivityReview(ReviewList.this, ReviewList.this, catActReviewData);
                        rv_Review.setAdapter(adapterActivityReview);

                        pro.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<ActivityReviewData> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }

        if(walking_review_flag){
            walking_review_flag=false;

            WebService.getClient().getWalkingActivityReview(emp_id).enqueue(new Callback<ActivityReviewData>() {
                @Override
                public void onResponse(Call<ActivityReviewData> call, Response<ActivityReviewData> response) {

                    if (0 == response.body().getData().size()){
                        Utils.showErrorToast(ReviewList.this,"No data found");
                        pro.dismiss();
                    }
                    else {
                        catActReviewData = response.body().getData();
                        Log.d("shweta", "onCreateViewHolder: DealstageRecyclerActivityViewINQ-2");
                        adapterActivityReview = new AdapterActivityReview(ReviewList.this, ReviewList.this, catActReviewData);
                        rv_Review.setAdapter(adapterActivityReview);

                        pro.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ActivityReviewData> call, Throwable t) {
                    pro.dismiss();
                    Utils.showErrorToast(ReviewList.this,t.getMessage());
                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // return true;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {


                // filter recycler view when text is changed
                if (catShowRCGVS == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (catShowRCGVS.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    adapterShowButtonData.getFilter().filter(query);
                    // showDetailGVAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ adapterShowButtonData);
                    //           Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.action_search ){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {


        delivery_review_flag = false;
        serviceandcomplain_review_flag = false;
        walking_review_flag = false;
        numbrplate_review_flag = false;
        activity_review_flag = false;
        firstmeeting_review_flag = false;
        warminq_review_flag = false;
        hotinq_review_flag = false;
        coldinq_review_flag = false;
        selllost_review_flag = false;
        drop_review_flag = false;

        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}