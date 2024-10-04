package com.example.habittracker.model;

import java.util.ArrayList;
import java.util.List;

public class HabitCheckIn {
    private List<HabitObserver> observers = new ArrayList<>();

    public void addObserver(HabitObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(HabitObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (HabitObserver observer : observers) {
            observer.update();
        }
    }

    public void checkIn(Habit habit) {
        habit.checkIn();
        notifyObservers();
    }
}
