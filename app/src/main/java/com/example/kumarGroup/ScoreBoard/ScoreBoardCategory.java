package com.example.kumarGroup.ScoreBoard;

import static com.example.kumarGroup.FoDashbord.Score_Board_Check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kumarGroup.DealstageAK.DeliveryButtonActivity;
import com.example.kumarGroup.R;

public class ScoreBoardCategory extends AppCompatActivity {

    TextView ViewInqScoreBoard,ViewInqPerformance,ViewInqPerformanceMarketing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board_category);

        ViewInqScoreBoard=findViewById(R.id.ViewInqScoreBoard);
        ViewInqPerformance=findViewById(R.id.ViewInqPerformance);
        ViewInqPerformanceMarketing=findViewById(R.id.ViewInqPerformanceMarketing);

        ViewInqScoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScoreBoardCategory.this, DateFilterActivity.class);
                startActivity(i);
            }
        });

        ViewInqPerformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Score_Board_Check) {
                    DeliveryButtonActivity.perfomanceFlag_INQ = true;
                }else{
                    DeliveryButtonActivity.perfomanceFlag = true;
                }
                Intent i = new Intent(ScoreBoardCategory.this, DeliveryButtonActivity.class);
                startActivity(i);

            }
        });

        ViewInqPerformanceMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Score_Board_Check) {
                    DeliveryButtonActivity.perfomanceMarketFlag_INQ = true;
                }else{
                    DeliveryButtonActivity.perfomanceMarketFlag = true;
                }
                Intent i = new Intent(ScoreBoardCategory.this, DeliveryButtonActivity.class);
                startActivity(i);

            }
        });

    }
}