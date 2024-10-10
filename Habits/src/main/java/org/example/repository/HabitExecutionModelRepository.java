package org.example.repository;

import org.example.model.HabitExecutionModel;
import org.example.model.HabitModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface HabitExecutionModelRepository {
    void markHabitAsCompleted(HabitModel habit, LocalDate date);

    ArrayList<HabitExecutionModel> getExecutions(HabitModel habit);
}
