package com.example.habittrackerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HabitAdapter.OnHabitClickListener {

    private Button btnAddHabit;
    private RecyclerView recyclerView;
    private HabitAdapter habitAdapter;
    private ArrayList<String> habitList;
    private int editPosition = -1; // เก็บตำแหน่งที่ต้องการแก้ไข

    private final ActivityResultLauncher<Intent> addHabitLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String habitName = result.getData().getStringExtra("habit_name");
                    String habitGoal = result.getData().getStringExtra("habit_goal");

                    if (editPosition == -1) {
                        // เพิ่มนิสัยใหม่
                        habitList.add("Habit: " + habitName + " | Goal: " + habitGoal + " days");
                    } else {
                        // แก้ไขนิสัยเดิม
                        habitList.set(editPosition, "Habit: " + habitName + " | Goal: " + habitGoal + " days");
                        editPosition = -1;
                    }

                    habitAdapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddHabit = findViewById(R.id.btnAddHabit);
        recyclerView = findViewById(R.id.recyclerView);

        habitList = new ArrayList<>();
        habitAdapter = new HabitAdapter(habitList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(habitAdapter);

        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
                addHabitLauncher.launch(intent);
            }
        });
    }

    @Override
    public void onDeleteClick(int position) {
        // ลบนิสัยเมื่อผู้ใช้กดปุ่มลบ
        new AlertDialog.Builder(this)
                .setTitle("Delete Habit")
                .setMessage("Are you sure you want to delete this habit?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    habitList.remove(position);
                    habitAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onEditClick(int position) {
        // แก้ไขนิสัยเมื่อผู้ใช้กดปุ่มแก้ไข
        editPosition = position;
        Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
        String[] parts = habitList.get(position).split("\\|");
        String habitName = parts[0].split(":")[1].trim();
        String habitGoal = parts[1].split(":")[1].trim().replace(" days", "");
        intent.putExtra("habit_name", habitName);
        intent.putExtra("habit_goal", habitGoal);
        addHabitLauncher.launch(intent);
    }
}