package com.example.kumarGroup.meetingRoomAK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.kumarGroup.R;
import com.example.kumarGroup.Utils;
import com.example.kumarGroup.WebService;
import com.example.kumarGroup.meeting_emp_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingRoomActivity extends AppCompatActivity {
    RecyclerView meetingRecyclerView;

    ProgressDialog pro;
    String id,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_room);

        meetingRecyclerView = findViewById(R.id.meetingRecyclerView);
        pro = new ProgressDialog(this);

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");

        pro.show();
        pro.setCancelable(false);
        pro.setMessage("Please wait ..");
        WebService.getClient().emp_meeting_room_web(type,id).enqueue(new Callback<meeting_emp_model>() {
            @Override
            public void onResponse(Call<meeting_emp_model> call, Response<meeting_emp_model> response) {

                Log.d("TAG", "typeforemploy "+type+" id "+id);

                if (response.body().getData().size() == 0){
                    pro.dismiss();
                    Utils.showErrorToast(MeetingRoomActivity.this,"No Data Found");
                }
                else {
                    MeetingAdapter meetingAdapter = new MeetingAdapter(MeetingRoomActivity.this,response.body().getData());
                    meetingRecyclerView.setAdapter(meetingAdapter);
                    pro.dismiss();
                }
            }

            @Override
            public void onFailure(Call<meeting_emp_model> call, Throwable t) {
                Utils.showErrorToast(MeetingRoomActivity.this,t.getMessage());
                pro.dismiss();
            }
        });
    }
}