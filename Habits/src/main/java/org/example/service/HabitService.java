package org.example.service;

import org.example.model.HabitModel;
import org.example.repository.HabitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HabitService implements HabitRepository {
    private final List<HabitModel> habits = new ArrayList<>();

    @Override
    public HabitModel createHabit(String name, String description, int interval) {
        HabitModel habit = new HabitModel(name, description, interval, new ArrayList<>());
        habits.add(habit);
        return habit;
    }

    @Override
    public HabitModel readHabit(String name) {
        return habits.stream()
                .filter(habit -> habit.getName().equals(name))
                .findFirst()
                .orElse(null); // Return null if not found
    }

    @Override
    public List<HabitModel> readAllHabits() {
        return new ArrayList<>(habits); // Return a copy of the list
    }

    @Override
    public void updateHabitName(String name, String newName) {
        updateHabit(name, habit -> habit.setName(newName));
    }

    @Override
    public void updateHabitDescription(String name, String newDescription) {
        updateHabit(name, habit -> habit.setDescription(newDescription));
    }

    @Override
    public void updateHabitInterval(String name, int newInterval) {
        updateHabit(name, habit -> habit.setInterval(newInterval));
    }

    private void updateHabit(String name, java.util.function.Consumer<HabitModel> updater) {
        habits.stream()
                .filter(habit -> habit.getName().equals(name))
                .findFirst()
                .ifPresent(updater);
    }

    @Override
    public void deleteHabit(String name) {
        habits.removeIf(habit -> habit.getName().equals(name));
    }

    @Override
    public void deleteAllHabits() {
        habits.clear();
    }
}
