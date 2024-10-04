package com.example.habittracker.model;

public class Habit {
    private String name;
    private int goal; // จำนวนวันที่ตั้งเป้า
    private int progress; // ความคืบหน้าของนิสัยนี้

    public Habit(String name, int goal) {
        this.name = name;
        this.goal = goal;
        this.progress = 0;
    }

    // Getter และ Setter
    public String getName() { return name; }
    public int getGoal() { return goal; }
    public int getProgress() { return progress; }

    public void checkIn() {
        if (progress < goal) {
            progress++;
        }
    }
}
