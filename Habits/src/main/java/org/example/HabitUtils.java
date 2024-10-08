package org.example;

import java.util.ArrayList;

public class HabitUtils {
    ArrayList<Habit> habits = new ArrayList<>();

    // create
    public ArrayList<Habit> createHabit(String name, String description, int interval) {
        habits.add(new Habit(name, description, interval));
        return habits;
    }


    // read
    protected String readHabit(String name) {
        for (var habit : habits) {
            if (habit.getName().equals(name)) {
                return habit.getName() + " " + habit.getDescription() + " " + habit.getInterval();
            }
        }
        return "Habit not found";
    }

    protected String readAllHabits() {
        for (var habit : habits) {
            return habit.getName() + " " + habit.getDescription() + " " + habit.getInterval();
        }
        return "Error while reading all habits";
    }


    // update
    protected void updateCertainHabitName(String name) {
        for (var habit : habits) {
            if (habit.getName().equals(name)) {
                habit.setName(name);
            }
        }
    }

    protected void updateCertainHabitDescription(String name, String description) {
        for (var habit : habits) {
            if (habit.getName().equals(name)) {
                habit.setDescription(description);
            }
        }
    }

    protected void updateCertainHabitInterval(String name, int interval) {
        for (var habit : habits) {
            if (habit.getName().equals(name)) {
                habit.setInterval(interval);
            }
        }
    }

    // delete
    protected void deleteCertainHabit(String name) {
        habits.removeIf(habit -> habit.getName().equals(name));
    }

    protected void deleteAllHabits() {
        habits.clear();
    }
}
