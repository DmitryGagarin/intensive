package org.example.service;

import org.example.model.HabitExecutionModel;
import org.example.model.HabitModel;

import java.time.LocalDate;

class AnalyticsService {

    public int calculateStreak(HabitModel habit) {
        int streak = 0;
        LocalDate today = LocalDate.now();

        for (HabitExecutionModel execution : habit.getExecutions()) {
            if (execution.isCompleted() && execution.getDate().isEqual(today.minusDays(streak))) {
                streak++;
            } else {
                break;
            }
        }
        return streak;
    }

    public double calculateSuccessRate(HabitModel habit, LocalDate startDate, LocalDate endDate) {
        long total = 0;
        long completed = 0;

        for (HabitExecutionModel execution : habit.getExecutions()) {
            if ((execution.getDate().isAfter(startDate) || execution.getDate().isEqual(startDate)) &&
                    (execution.getDate().isBefore(endDate) || execution.getDate().isEqual(endDate))) {
                total++;
                if (execution.isCompleted()) {
                    completed++;
                }
            }
        }

        return total > 0 ? (double) completed / total * 100 : 0;
    }
}
