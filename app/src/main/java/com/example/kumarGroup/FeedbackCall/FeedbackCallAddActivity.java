package com.example.kumarGroup.FeedbackCall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.FeedbackCall_add;
import com.example.kumarGroup.R;
import com.example.kumarGroup.ViewInquiryDealStage.DealViewMainActivity;
import com.example.kumarGroup.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackCallAddActivity extends AppCompatActivity {

    Spinner Employee_visit, Customer_satisfaction, Lastest_offer, Intrested;
    EditText Remark;
    Button btnSubmitIG;
    String Autoid, Employee_visits, Customer_satisfactions, Lastest_offers, Intresteds, Remarks, Whatsappnumber, message;
    String[] List = {"Employee Visit", "Yes", "No"};
    String[] List2 = {"Customer satisfaction", "Yes", "No"};
    String[] List3 = {"Latest Offer", "Yes", "No"};
    String[] List4 = {"Intrested", "Yes", "No"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_call_add);

        Employee_visit = findViewById(R.id.Employee_visit);
        Customer_satisfaction = findViewById(R.id.Customer_satisfaction);
        Lastest_offer = findViewById(R.id.Lastest_offer);
        Intrested = findViewById(R.id.Intrested);
        Remark = findViewById(R.id.Remark);
        btnSubmitIG = findViewById(R.id.btnSubmitIG);

        Autoid = getIntent().getStringExtra("autoid");
        Whatsappnumber = getIntent().getStringExtra("number");


        ArrayAdapter adapterFollowUp1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, List);
        Employee_visit.setAdapter(adapterFollowUp1);

        Employee_visit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Employee_visits = List[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapterFollowUp2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, List2);
        Customer_satisfaction.setAdapter(adapterFollowUp2);
        Customer_satisfaction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Customer_satisfactions = List2[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter adapterFollowUp3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, List3);
        Lastest_offer.setAdapter(adapterFollowUp3);
        Lastest_offer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Lastest_offers = List3[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter adapterFollowUp4 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, List4);
        Intrested.setAdapter(adapterFollowUp4);
        Intrested.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intresteds = List4[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSubmitIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Employee_visits == "Employee Visit") {
                    TextView errorText = (TextView) Employee_visit.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Select opction");//changes the selected item text to this
                } else if (Customer_satisfactions == "Customer satisfaction") {
                    TextView errorText = (TextView) Customer_satisfaction.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Select opction");//changes the selected item text to this
                } else if (Lastest_offers == "Latest Offer") {
                    TextView errorText = (TextView) Lastest_offer.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Select opction");//changes the selected item text to this

                } else if (Intresteds == "Intrested") {
                    TextView errorText = (TextView) Intrested.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Select opction");//changes the selected item text to this
                } else if (Remark.getText().length() == 0) {
                    Remark.setError("Enter Remark");
                } else {
                    btnSubmitIG.setVisibility(View.INVISIBLE);
                    Remarks = Remark.getText().toString();
                    Log.d("TAG", "onClick: Whatsapp_message");
                    WebService.getClient().FeedBackCall_add(Autoid, Employee_visits, Customer_satisfactions, Lastest_offers, Intresteds, Remarks).enqueue(new Callback<FeedbackCall_add>() {
                        @Override
                        public void onResponse(Call<FeedbackCall_add> call, Response<FeedbackCall_add> response) {
                            if (response.body().getMsg().equals("Record added Succesfully")) {
                                Toast.makeText(getApplicationContext(), "Record added Succesfully", Toast.LENGTH_LONG).show();

                                message = "प्रिय किसान मित्र।\n" +
                                        "\n" +
                                        "     आपके बहुमूल्य समय के लिए बहुत-बहुत धन्यवाद, हम भविष्य में आपको संतोषजनक सेवाएं प्रदान करेंगे, और आपके बहुमूल्य सुझावों की प्रतीक्षा रहेगी।\n" +
                                        "\n" +
                                        "नमस्ते...\n" +
                                        "कुमार ऑटोमोबाइल्स (सोनालिका ट्रैक्टर शोरूम)\n" +
                                        "\n" +
                                        "अधिक जानकारी के लिए संपर्क करें।\n" +
                                        "सेल्स MO:- 7500567770\n" +
                                        "सर्विस MO:-7505786792";                                Log.d("TAG", "onResponse: Whatsapp_message" + message);


                                startActivity(
                                        new Intent(Intent.ACTION_VIEW,
                                                Uri.parse(
                                                        String.format("https://api.whatsapp.com/send?phone=%s&text=%s", "+91" + Whatsappnumber, message)
                                                )
                                        )
                                );
                                finish();
                            }

                        }

                        @Override
                        public void onFailure(Call<FeedbackCall_add> call, Throwable t) {

                        }
                    });
                }

            }

        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(FeedbackCallAddActivity.this, DealViewMainActivity.class);
        startActivity(intent);
        finish();
    }
}