package com.example.kumarGroup.MyProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.R;
import com.example.kumarGroup.myProfileNew.GeneralVisitFormActivity;

public class ViewProfileDetailsActivity extends AppCompatActivity
{
    TextView tvd_First_name, tv_customor_do_it, tv_mobilenumber, tvd_district, tv_village, tvd_taluko, tvd_Last_name, tv_Whappnumber;
    TextView lin_whapp, lin_call, lin_email, lin_sms, lin_share;

    String Mobilecall, Whatapp, Customordoit, FirstName, LastName, Mobile, Village, district, Taluko;
    String   Id,cat_id,eid;;
    String sms = "";//The message you want to text to the phone


    LinearLayout linDataNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_details);

        getSupportActionBar().setTitle("View Profile Detail");

        allocateMemory();

        Mobilecall = getIntent().getStringExtra("Mobilecall");
        Whatapp = getIntent().getStringExtra("Whappnumbercall");
        Customordoit = getIntent().getStringExtra("Customordoit");
        FirstName = getIntent().getStringExtra("FirstName");
        LastName = getIntent().getStringExtra("LastName");
        Mobile = getIntent().getStringExtra("Mobile");
        Village = getIntent().getStringExtra("Village");
        district = getIntent().getStringExtra("district");
        Taluko = getIntent().getStringExtra("Taluko");

        tvd_First_name.setText(getIntent().getStringExtra("FirstName"));
        tvd_Last_name.setText(getIntent().getStringExtra("LastName"));
        tv_customor_do_it.setText(getIntent().getStringExtra("Customordoit"));
        tv_village.setText(getIntent().getStringExtra("Village"));
        tvd_district.setText(getIntent().getStringExtra("district"));
        tvd_taluko.setText(getIntent().getStringExtra("Taluko"));
        tv_mobilenumber.setText(getIntent().getStringExtra("Mobile"));
        tv_Whappnumber.setText(getIntent().getStringExtra("Whappnumber"));


        cat_id = getIntent().getStringExtra("cat_id");
        eid = getIntent().getStringExtra("eid");


        Id = getIntent().getStringExtra("Id");

        lin_whapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed = appInstallOrNot("com.whatsapp");
                if (installed) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91" + Whatapp));
                    startActivity(intent);
                } else {
                    Toast.makeText(ViewProfileDetailsActivity.this, "Whats app not installed on your device ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        lin_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + Mobilecall));
                Log.d("call", "onClick: " + Mobilecall);
                startActivity(intent);
            }
        });

        lin_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"some@email.address"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                intent.setPackage("com.google.android.gm");

                startActivity(Intent.createChooser(intent, ""));
            }
        });

        lin_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", Mobilecall, null));
                smsIntent.putExtra("sms_body", sms);
                startActivity(smsIntent);

            }
        });


        lin_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "** Kumar Group ** \n" + "Customer Detail:\n" + FirstName + "\n" + LastName + "\n" + Customordoit + "\n" + Mobile + "\n" + Village + "\n" + district + "\n" + Taluko);
                startActivity(Intent.createChooser(i, "Share via"));

            }
        });


        linDataNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewProfileDetailsActivity.this, GeneralVisitFormActivity.class);
                i.putExtra("CustomerName",FirstName);
                i.putExtra("CustomerName1",LastName);
                i.putExtra("Customordoit",Customordoit);
                i.putExtra("cat_id",cat_id);
                i.putExtra("Id",Id);
                i.putExtra("eid",eid);
                startActivity(i);
            }
        });
    }

    private boolean appInstallOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void allocateMemory() {

        tvd_First_name = findViewById(R.id.tvd_First_name);
        tv_customor_do_it = findViewById(R.id.tv_customor_do_it);
        tv_mobilenumber = findViewById(R.id.tv_mobilenumber);
        tvd_district = findViewById(R.id.tvd_district);
        tv_village = findViewById(R.id.tv_village);
        tvd_taluko = findViewById(R.id.tvd_taluko);
        tvd_Last_name = findViewById(R.id.tvd_Last_name);
        tv_Whappnumber = findViewById(R.id.tv_Whappnumber);
        lin_whapp = findViewById(R.id.lin_whapp);
        lin_call = findViewById(R.id.lin_call);
        lin_email = findViewById(R.id.lin_email);
        lin_sms = findViewById(R.id.lin_sms);
        lin_share = findViewById(R.id.lin_share);


        linDataNext=findViewById(R.id.linDataNext);

    }
}

