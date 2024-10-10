package org.example.service;

import org.example.model.HabitExecutionModel;
import org.example.model.HabitModel;
import org.example.repository.HabitExecutionModelRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class HabitExecutionService implements HabitExecutionModelRepository {
    @Override
    public void markHabitAsCompleted(HabitModel habit, LocalDate date) {
        HabitExecutionModel execution = new HabitExecutionModel(date, true);
        habit.getExecutions().add(execution);
    }

    @Override
    public ArrayList<HabitExecutionModel> getExecutions(HabitModel habit) {
        return habit.getExecutions();
    }
}
