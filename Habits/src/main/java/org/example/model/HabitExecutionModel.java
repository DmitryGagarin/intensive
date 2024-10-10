package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class HabitExecutionModel {
    private LocalDate date;
    private boolean completed;

    public HabitExecutionModel(LocalDate date, boolean completed) {
        this.date = date;
        this.completed = completed;
    }
}
