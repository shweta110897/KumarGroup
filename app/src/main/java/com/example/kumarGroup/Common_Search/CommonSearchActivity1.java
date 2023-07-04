package com.example.kumarGroup.Common_Search;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kumarGroup.CommonSearch;
import com.example.kumarGroup.FoDashbord;
import com.example.kumarGroup.R;
import com.example.kumarGroup.WebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonSearchActivity1 extends AppCompatActivity {


    String emp_id,cat_id,Paymenttype;
    private SearchView searchView;
    RecyclerView recyclerView;
    List<CommonSearch.commonSearch> commonSearch;
    CommonSearchAdapter commonSearchAdapter;
    SharedPreferences sp1;
    ProgressDialog pro;
    EditText editSerch;
    TextView deal_filtercommon_search;
    List<String> modelname_list;
    String Day;


    String[] PaymentType_list={"Select PaymentType","Loan", "Cash"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_search);

        pro = new ProgressDialog(this);

        Day=getIntent().getStringExtra("Day");

        deal_filtercommon_search = findViewById(R.id.deal_filtercommon_search);
        editSerch = findViewById(R.id.edtSearch);
        editSerch.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerView_common_search);


        if (FoDashbord.Common_Search_Check ){
            sp1 = getSharedPreferences("Login", MODE_PRIVATE);
            emp_id = sp1.getString("emp_id", "");
        }else {
            SharedPreferences sharedPreferencesS = getSharedPreferences("SelectedEMP_id",MODE_PRIVATE);
            emp_id = sharedPreferencesS.getString("Slected_EMPID","");
        }
        
        SharedPreferences sharedPreferences = getSharedPreferences("dealstage3cateid", Context.MODE_PRIVATE);
        cat_id = sharedPreferences.getString("catId_eid","");
//        Toast.makeText(CommonSearchActivity.this, "OUt side api calllll", Toast.LENGTH_SHORT).show();

        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ...");
//        Log.d("TAG", "onClick: Else "+emp_id+" Cat id "+cat_id+" Number "+editText_common_search.getText());
        WebService.getClient().CommonSearch(emp_id,cat_id,"","").enqueue(new Callback<CommonSearch>() {
            @Override
            public void onResponse(Call<CommonSearch> call, Response<CommonSearch> response) {
                    pro.dismiss();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    commonSearchAdapter = new CommonSearchAdapter(CommonSearchActivity1.this,response.body().getCat());
                    recyclerView.setAdapter(commonSearchAdapter);
                    commonSearch = response.body().getCat();
                    if (Day!=null){
                        commonSearchAdapter.getFilter1().filter(Day);
                    }


                }

            @Override
            public void onFailure(Call<CommonSearch> call, Throwable t) {
                Log.d("TAG", "onFailure: processfail "+t.getMessage());
            }
        });


        deal_filtercommon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogPayment();
                }
        });
    }

    private void showDialogPayment() {
        Dialog dialog = new Dialog(CommonSearchActivity1.this);
        dialog.setContentView(R.layout.filter_payment);/*filter_payment*/

        Spinner sp_paymenttype =dialog. findViewById(R.id.sp_paymenttype);
        Spinner sp_passingtype =dialog. findViewById(R.id.sp_passingtype);
        Button btn_submit =dialog. findViewById(R.id.btn_submit);
        modelname_list = new ArrayList<>();

        ArrayAdapter adapterPayment = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PaymentType_list);
        sp_paymenttype.setAdapter(adapterPayment);

        sp_paymenttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if( PaymentType_list[position]=="Select FollowUp"){
                    Paymenttype = "";
                }
                else{
                    Paymenttype = PaymentType_list[position];
                    Log.e("Paymenttype123",Paymenttype);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                commonSearchAdapter.getFilter().filter(Paymenttype);
            }
        });

        dialog.show();

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

                //Log.d("TAG", "onQueryTextChange: asgdjhasgd " + feedbackCallAdapter);


                //filter recycler view when text is changed
                if (commonSearch == null) {
                            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

                }else if (commonSearch.size() == 0){
                             Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                }else{
                    commonSearchAdapter.getFilter().filter(query);
                    Log.d("ssss", "onQueryTextChange: "+ query);
                }
                return true;
            }
        });
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.action_search ){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}