package com.example.habittrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddHabitActivity extends AppCompatActivity {

    private EditText etHabitName, etHabitGoal;
    private Button btnSaveHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        etHabitName = findViewById(R.id.etHabitName);
        etHabitGoal = findViewById(R.id.etHabitGoal);
        btnSaveHabit = findViewById(R.id.btnSaveHabit);

        btnSaveHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etHabitName.getText().toString();
                String goal = etHabitGoal.getText().toString();

                if (name.isEmpty() || goal.isEmpty()) {
                    Toast.makeText(AddHabitActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("habit_name", name);
                    resultIntent.putExtra("habit_goal", goal);
                    setResult(RESULT_OK, resultIntent);
                    finish(); // ปิด Activity และกลับไปยัง MainActivity
                }
            }
        });
    }
}
