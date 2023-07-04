package com.example.kumarGroup.ReportCollection.GeneralVisit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.kumarGroup.BorrowOneModel;
import com.example.kumarGroup.BorrowTwoModel;
import com.example.kumarGroup.DataBorrowOne;
import com.example.kumarGroup.DataBorrowTwo;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ReportCollection.BookingUploadDataDisplayAdapter;
import com.example.kumarGroup.ReportCollection.WorkshopDataDisplayAdapter;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowGeneralVisitDataActivity extends AppCompatActivity {

    RecyclerView rclv_BookingDeliveryData;
    ProgressDialog progressDialog,progressDialog1,progressDialog2;


    ArrayList<String> BorrowOneName = new ArrayList<>();
    ArrayList<String> state = new ArrayList<>();
    ArrayList<String> city = new ArrayList<>();
    ArrayList<String> distric = new ArrayList<>();
    ArrayList<String> tehsil = new ArrayList<>();
    ArrayList<String> vilage = new ArrayList<>();
    ArrayList<String> mobile = new ArrayList<>();
    ArrayList<String> whatsappno = new ArrayList<>();
    ArrayList<String> empid = new ArrayList<>();

    List<DataBorrowOne> dataPaymentPendings_one1;

    List<DataBorrowTwo> dataWsPayPens_one1;

    BookingUploadDataDisplayAdapter adapter;
    WorkshopDataDisplayAdapter adapter1;

    private SearchView searchView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_general_visit_data);

        rclv_BookingDeliveryData = findViewById(R.id.rclv_BookingDeliveryData);

        progressDialog= new ProgressDialog(ShowGeneralVisitDataActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



        if (General_Visit_TypeActivity.Type_General_Check){
            WebService.getClient().getBorrowOne().enqueue(new Callback<BorrowOneModel>() {
                @Override
                public void onResponse(@NotNull Call<BorrowOneModel> call, @NotNull Response<BorrowOneModel> response) {
                    progressDialog.dismiss();
//                Log.d("TAG", "onResponse: check_pro 1");
                    assert response.body() != null;


                    if(response.isSuccessful()){
                        dataPaymentPendings_one1 =  response.body().getData();
                        if(dataPaymentPendings_one1.size()==0){
                            Toast.makeText(ShowGeneralVisitDataActivity.this, "Data Loading",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
//                        Log.d("TAG", "jemin123: GenVisitPaymentCollectionActivity");
                            //  Toast.makeText(GenVisitPaymentCollectionActivity.this, "On response", Toast.LENGTH_SHORT).show();
                            rclv_BookingDeliveryData.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter= new BookingUploadDataDisplayAdapter(ShowGeneralVisitDataActivity.this,
                                    dataPaymentPendings_one1);
                            rclv_BookingDeliveryData.setAdapter(adapter);
                            // adapter.notifyDataSetChanged();

                            //  BorrowOneName.clear();

//                            for (int i = 0; i < dataPaymentPendings_one1.size(); i++) {
//                                String result = response.body().getData().get(i).getCustomer_name();
//
//                                BorrowOneName.add(dataPaymentPendings_one1.get(i).getFname());
//                                state.add(dataPaymentPendings_one1.get(i).getState());
//                                city.add(dataPaymentPendings_one1.get(i).getCity());
//                                distric.add(dataPaymentPendings_one1.get(i).getDistric());
//                                tehsil.add(dataPaymentPendings_one1.get(i).getTehsill());
//                                vilage.add(dataPaymentPendings_one1.get(i).getVillage());
//                                mobile.add(dataPaymentPendings_one1.get(i).getMobileno());
//                                whatsappno.add(dataPaymentPendings_one1.get(i).getWhno());
//                                empid.add(dataPaymentPendings_one1.get(i).getEmpid());
//
//                            }

                            //  Toast.makeText(GenVisitPaymentCollectionActivity.this, ""+BorrowOneName, Toast.LENGTH_SHORT).show();

                       /* String[] mStringArray = new String[BorrowOneName.size()];
                        mStringArray = BorrowOneName.toArray(mStringArray);

                        String BorrowOneNameArrayItem = "";
                        for(int i = 0; i < mStringArray.length ; i++){
                            Log.d("string is",(String)mStringArray[i]);
                            BorrowOneNameArrayItem = (String)mStringArray[i];
                        }

                        Toast.makeText(GenVisitPaymentCollectionActivity.this,
                                ""+BorrowOneNameArrayItem, Toast.LENGTH_SHORT).show();*/

//                            String BorrowOneNameArrayItem = "";
//
//                            for(int i=0; i<=BorrowOneName.size();i++)
//                            {
//                                if(BorrowOneNameArrayItem.equals("")){
//                                    try {
//                                        BorrowOneNameArrayItem = BorrowOneName.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        BorrowOneNameArrayItem = BorrowOneNameArrayItem+","+BorrowOneName.get(i);
//                                        Log.d("BBList_ll", "onCreate: "+BorrowOneNameArrayItem);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                            String stateArrayItem = "";
//
//                            for(int i=0; i<=state.size();i++)
//                            {
//                                if(stateArrayItem.equals("")){
//                                    try {
//                                        stateArrayItem = state.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        stateArrayItem = stateArrayItem+","+state.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                            String cityArrayItem = "";
//
//                            for(int i=0; i<=city.size();i++)
//                            {
//                                if(cityArrayItem.equals("")){
//                                    try {
//                                        cityArrayItem = city.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        cityArrayItem = cityArrayItem+","+city.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            String districArrayItem = "";
//
//                            for(int i=0; i<=distric.size();i++)
//                            {
//                                if(districArrayItem.equals("")){
//                                    try {
//                                        districArrayItem = distric.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        districArrayItem = districArrayItem+","+distric.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            String tehsilArrayItem = "";
//
//                            for(int i=0; i<=tehsil.size();i++)
//                            {
//                                if(tehsilArrayItem.equals("")){
//                                    try {
//                                        tehsilArrayItem = tehsil.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        tehsilArrayItem = tehsilArrayItem+","+tehsil.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            String vilageArrayItem = "";
//
//                            for(int i=0; i<=vilage.size();i++)
//                            {
//                                if(vilageArrayItem.equals("")){
//                                    try {
//                                        vilageArrayItem = vilage.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        vilageArrayItem = vilageArrayItem+","+vilage.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                            String mobileArrayItem = "";
//
//                            for(int i=0; i<=mobile.size();i++)
//                            {
//                                if(mobileArrayItem.equals("")){
//                                    try {
//                                        mobileArrayItem = mobile.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        mobileArrayItem = mobileArrayItem+","+mobile.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//
//                            String whatsappnoArrayItem = "";
//
//                            for(int i=0; i<=whatsappno.size();i++)
//                            {
//                                if(whatsappnoArrayItem.equals("")){
//                                    try {
//                                        whatsappnoArrayItem = whatsappno.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        whatsappnoArrayItem = whatsappnoArrayItem+","+whatsappno.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                            String empidArrayItem = "";
//
//                            for(int i=0; i<=empid.size();i++)
//                            {
//                                if(empidArrayItem.equals("")){
//                                    try {
//                                        empidArrayItem = empid.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        empidArrayItem = empidArrayItem+","+empid.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                            WebService.getClient().getInsertPaymentCollection(BorrowOneNameArrayItem,
//                                    stateArrayItem,
//                                    cityArrayItem,
//                                    districArrayItem,
//                                    tehsilArrayItem,
//                                    vilageArrayItem,
//                                    mobileArrayItem,
//                                    whatsappnoArrayItem,
//                                    empidArrayItem).enqueue(new Callback<insertPaymentCollectionModel>() {
//                                @Override
//                                public void onResponse(@NotNull Call<insertPaymentCollectionModel> call,
//                                                       @NotNull Response<insertPaymentCollectionModel> response) {
//                                    assert response.body() != null;
//                                    //Toast.makeText(GenVisitPaymentCollectionActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onFailure(@NotNull Call<insertPaymentCollectionModel> call, @NotNull Throwable t) {
//                                    // Toast.makeText(GenVisitPaymentCollectionActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
//
//                                }
//                            });

                        }
                    }


               /* if(response.body().getData().size() == 0){
                 //   Utils.showErrorToast(GenVisitPaymentCollectionActivity.this,"No Data Available");
                    Toast.makeText(GenVisitPaymentCollectionActivity.this, "Data Loading",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    BookingUploadDataDisplayAdapter adapter = new BookingUploadDataDisplayAdapter(
                            GenVisitPaymentCollectionActivity.this,
                            response.body().getData());
                    we.setAdapter(adapter);
                }*/
                }

                @Override
                public void onFailure(@NotNull Call<BorrowOneModel> call, @NotNull Throwable t) {
                    Toast.makeText(ShowGeneralVisitDataActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });
        }else {
            WebService.getClient().getBorrowTwo().enqueue(new Callback<BorrowTwoModel>() {
                @Override
                public void onResponse(@NotNull Call<BorrowTwoModel> call, @NotNull Response<BorrowTwoModel> response) {
//                Log.d("TAG", "onResponse: check_pro 2");

                    progressDialog.dismiss();

                    assert response.body() != null;

                    if (response.isSuccessful()) {
                        dataWsPayPens_one1 = response.body().getData();
                        if (dataWsPayPens_one1.size() == 0) {
                            Utils.showErrorToast(ShowGeneralVisitDataActivity.this,"No Data Found");

                        } else {
//                        Log.d("TAG", "jemin123: GenVisitPaymentCollectionActivity-1");
                            rclv_BookingDeliveryData.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter1 = new WorkshopDataDisplayAdapter(ShowGeneralVisitDataActivity.this,
                                    dataWsPayPens_one1);
                            rclv_BookingDeliveryData.setAdapter(adapter1);



//                            for (int i = 0; i < dataWsPayPens_one1.size(); i++) {
//                                //  String result = response.body().getData().get(i).getCustomer_name();
//
//                                BorrowOneName2.add(dataWsPayPens_one1.get(i).getCname());
//                                state2.add(dataWsPayPens_one1.get(i).getState());
//                                city2.add(dataWsPayPens_one1.get(i).getCity());
//                                distric2.add(dataWsPayPens_one1.get(i).getDistric());
//                                tehsil2.add(dataWsPayPens_one1.get(i).getTehsil());
//                                vilage2.add(dataWsPayPens_one1.get(i).getVillage());
//                                mobile2.add(dataWsPayPens_one1.get(i).getMobileno());
//                                whatsappno2.add(dataWsPayPens_one1.get(i).getWhno());
//                                empid2.add(dataWsPayPens_one1.get(i).getVilage_wise_emp());
//
//                            }
                            //  Toast.makeText(GenVisitPaymentCollectionActivity.this, ""+BorrowOneName, Toast.LENGTH_SHORT).show();

                       /* String[] mStringArray = new String[BorrowOneName.size()];
                        mStringArray = BorrowOneName.toArray(mStringArray);

                        String BorrowOneNameArrayItem = "";
                        for(int i = 0; i < mStringArray.length ; i++){
                            Log.d("string is",(String)mStringArray[i]);
                            BorrowOneNameArrayItem = (String)mStringArray[i];
                        }

                        Toast.makeText(GenVisitPaymentCollectionActivity.this,
                                ""+BorrowOneNameArrayItem, Toast.LENGTH_SHORT).show();*/

//                            String BorrowOneNameArrayItem2 = "";
//
//                            for(int i=0; i<=BorrowOneName2.size();i++)
//                            {
//                                if(BorrowOneNameArrayItem2.equals("")){
//                                    try {
//                                        BorrowOneNameArrayItem2 = BorrowOneName2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        BorrowOneNameArrayItem2 = BorrowOneNameArrayItem2+","+BorrowOneName2.get(i);
////                                    Log.d("BBList_ll", "onCreate: "+BorrowOneNameArrayItem2);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                            String stateArrayItem2 = "";
//
//                            for(int i=0; i<=state2.size();i++)
//                            {
//                                if(stateArrayItem2.equals("")){
//                                    try {
//                                        stateArrayItem2 = state2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        stateArrayItem2 = stateArrayItem2+","+state2.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                            String cityArrayItem2 = "";
//
//                            for(int i=0; i<=city2.size();i++)
//                            {
//                                if(cityArrayItem2.equals("")){
//                                    try {
//                                        cityArrayItem2 = city2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        cityArrayItem2 = cityArrayItem2+","+city2.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            String districArrayItem2 = "";
//
//                            for(int i=0; i<=distric2.size();i++)
//                            {
//                                if(districArrayItem2.equals("")){
//                                    try {
//                                        districArrayItem2 = distric2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        districArrayItem2 = districArrayItem2+","+distric2.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            String tehsilArrayItem2 = "";
//
//                            for(int i=0; i<=tehsil2.size();i++)
//                            {
//                                if(tehsilArrayItem2.equals("")){
//                                    try {
//                                        tehsilArrayItem2 = tehsil2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        tehsilArrayItem2 = tehsilArrayItem2+","+tehsil2.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            String vilageArrayItem2 = "";
//
//                            for(int i=0; i<=vilage2.size();i++)
//                            {
//                                if(vilageArrayItem2.equals("")){
//                                    try {
//                                        vilageArrayItem2 = vilage2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        vilageArrayItem2 = vilageArrayItem2+","+vilage2.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//
//                            String mobileArrayItem2 = "";
//
//                            for(int i=0; i<=mobile2.size();i++)
//                            {
//                                if(mobileArrayItem2.equals("")){
//                                    try {
//                                        mobileArrayItem2 = mobile2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        mobileArrayItem2 = mobileArrayItem2+","+mobile2.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            String whatsappnoArrayItem2 = "";
//
//                            for(int i=0; i<=whatsappno2.size();i++)
//                            {
//                                if(whatsappnoArrayItem2.equals("")){
//                                    try {
//                                        whatsappnoArrayItem2 = whatsappno2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        whatsappnoArrayItem2 = whatsappnoArrayItem2+","+whatsappno2.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            String empidArrayItem2 = "";
//
//                            for(int i=0; i<=empid2.size();i++)
//                            {
//                                if(empidArrayItem2.equals("")){
//                                    try {
//                                        empidArrayItem2 = empid2.get(i);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                else
//                                {
//                                    try{
//                                        empidArrayItem2 = empidArrayItem2+","+empid2.get(i);
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }


//                            WebService.getClient().getInsertPaymentCollection(BorrowOneNameArrayItem2,
//                                    stateArrayItem2,
//                                    cityArrayItem2,
//                                    districArrayItem2,
//                                    tehsilArrayItem2,
//                                    vilageArrayItem2,
//                                    mobileArrayItem2,
//                                    whatsappnoArrayItem2,
//                                    empidArrayItem2).enqueue(new Callback<insertPaymentCollectionModel>() {
//                                @Override
//                                public void onResponse(@NotNull Call<insertPaymentCollectionModel> call,
//                                                       @NotNull Response<insertPaymentCollectionModel> response) {
//                                    assert response.body() != null;
//                                    //  Toast.makeText(GenVisitPaymentCollectionActivity.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onFailure(@NotNull Call<insertPaymentCollectionModel> call, @NotNull Throwable t) {
//                                    // Toast.makeText(GenVisitPaymentCollectionActivity.this, ""+t.getCause(), Toast.LENGTH_SHORT).show();
//
//                                }
//                            });

                        }
                    }
                    else {
                        Utils.showNormalToast(ShowGeneralVisitDataActivity.this,"Server error please try again later");
                    }

                /*if(response.body().getData().size() == 0){
                  //  Utils.showErrorToast(GenVisitPaymentCollectionActivity.this,"No Data Available");
                    Toast.makeText(GenVisitPaymentCollectionActivity.this, "Data", Toast.LENGTH_SHORT).show();
                }
                else {
                    WorkshopDataDisplayAdapter adapter = new WorkshopDataDisplayAdapter(GenVisitPaymentCollectionActivity.this,
                            response.body().getData());
                    rclv_WorkshopData.setAdapter(adapter);
                   // adapter.notifyDataSetChanged();
                }*/
                }

                @Override
                public void onFailure(@NotNull Call<BorrowTwoModel> call, @NotNull Throwable t) {
                    progressDialog.dismiss();
//                Log.d("borrowTwo", "onFailure: "+t.getMessage());
                    // Toast.makeText(GenVisitPaymentCollectionActivity.this, "borrowTwo"+t.getMessage(), Toast.LENGTH_LONG).show();
                    //  Utils.showErrorToast(GenVisitPaymentCollectionActivity.this,"No Data Available");
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                if (General_Visit_TypeActivity.Type_General_Check){
                    Log.d("TAG", "onQueryTextChange: Check_search " + query);
                    // filter recycler view when text is changed
                    if (dataPaymentPendings_one1 == null) {
                        //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                    }else if (dataPaymentPendings_one1.size() == 0){
                        //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                    }else{
                        adapter.getFilter().filter(query);
//                    Log.d("ssss", "onQueryTextChange: "+ adapter);
                        //Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.d("TAG", "onQueryTextChange: Check_search spr" + query);

                    if (dataWsPayPens_one1 == null) {
                        //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                    }else if (dataWsPayPens_one1.size() == 0){
                        //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                    }else{
                        adapter1.getFilter().filter(query);
//                    Log.d("ssss", "onQueryTextChange: "+ adapter);
                        //Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                    }
                }

               /* if (dataSupBorrowListList == null) {
                    //        Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();

                }else if (dataSupBorrowListList.size() == 0){
                    //         Toast.makeText(FoVisit.this, "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    adapter2.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ adapter);
                    //Toast.makeText(FoVisit.this, "Data", Toast.LENGTH_SHORT).show();
                }*/

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
}