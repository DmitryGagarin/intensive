package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HabitModel {
    private UUID id;
    private String name;
    private String description;
    private int interval;
    private ArrayList<HabitExecutionModel> executions;

    public HabitModel(String name, String description, int interval, ArrayList<HabitExecutionModel> executions) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.interval = interval;
        this.executions = executions;
    }
}
