package org.example;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<Habit> usersHabits = new ArrayList<>();


    public void setUsersHabits(ArrayList<Habit> usersHabits) {
        this.usersHabits = usersHabits;
    }

    public ArrayList<Habit> getUsersHabits() {
        return usersHabits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String name, String email, String password, ArrayList<Habit> usersHabits) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.usersHabits = usersHabits;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}