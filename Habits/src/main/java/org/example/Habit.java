package org.example;

import java.util.ArrayList;

public class Habit {
    private String name;
    private String description;
    private int interval;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Habit() {
    }

    public Habit(String name, String description, int interval) {
        this.name = name;
        this.description = description;
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", interval=" + interval +
                '}';
    }
}
