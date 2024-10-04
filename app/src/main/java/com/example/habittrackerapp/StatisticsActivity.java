package com.example.habittrackerapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {

    private TextView tvHabitDetail, tvReward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        tvHabitDetail = findViewById(R.id.tvHabitDetail);
        tvReward = findViewById(R.id.tvReward);

        // รับข้อมูลนิสัยจาก Intent
        String habitDetail = getIntent().getStringExtra("habit_detail");

        // รับข้อมูลรางวัลจาก Intent
        String habitReward = getIntent().getStringExtra("habit_reward");

        // แสดงข้อมูลนิสัย
        tvHabitDetail.setText(habitDetail);


        if (habitReward != null && !habitReward.isEmpty()) {
            tvReward.setText("Reward: " + habitReward);
        } else {
            calculateAndDisplayReward(habitDetail);
        }
    }

    private void calculateAndDisplayReward(String habitDetail) {
        int goalDays = Integer.parseInt(habitDetail.split("Goal: ")[1].replace(" days", "").trim());

        if (goalDays >= 100) {
            tvReward.append("\nReward: คุณได้รับเหรียญทอง!");
        } else if (goalDays >= 30) {
            tvReward.append("\nReward: คุณได้รับเหรียญเงิน!");
        } else {
            tvReward.append("\nReward: ทำต่อไป คุณจะได้รับรางวัล!");
        }
    }


}

