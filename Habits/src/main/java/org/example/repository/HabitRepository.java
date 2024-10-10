package org.example.repository;

import org.example.model.HabitModel;

import java.util.List;

public interface HabitRepository {
    HabitModel createHabit(String name, String description, int interval);
    HabitModel readHabit(String name);
    List<HabitModel> readAllHabits();
    void updateHabitName(String name, String newName);
    void updateHabitDescription(String name, String newDescription);
    void updateHabitInterval(String name, int newInterval);
    void deleteHabit(String name);
    void deleteAllHabits();
}
