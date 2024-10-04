package com.example.habittrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddHabitActivity extends AppCompatActivity {

    private EditText etHabitName, etHabitGoal, etHabitReward;
    private Button btnSaveHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        etHabitName = findViewById(R.id.etHabitName);
        etHabitGoal = findViewById(R.id.etHabitGoal);
        etHabitReward = findViewById(R.id.etHabitReward); // ช่องกรอกข้อมูลรางวัล

        // รับข้อมูลจาก Intent หากมีการแก้ไข
        Intent intent = getIntent();
        if (intent != null) {
            String habitName = intent.getStringExtra("habit_name");
            String habitGoal = intent.getStringExtra("habit_goal");
            String habitReward = intent.getStringExtra("habit_reward");


            etHabitName.setText(habitName);
            etHabitGoal.setText(habitGoal);
            etHabitReward.setText(habitReward);
        }

        btnSaveHabit = findViewById(R.id.btnSaveHabit);
        btnSaveHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etHabitName.getText().toString();
                String goal = etHabitGoal.getText().toString();
                String reward = etHabitReward.getText().toString();

                if (name.isEmpty() || goal.isEmpty() || reward.isEmpty()) {
                    Toast.makeText(AddHabitActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("habit_name", name);
                    resultIntent.putExtra("habit_goal", goal);
                    resultIntent.putExtra("habit_reward", reward);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
