package org.example.model;


import lombok.*;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
// TODO: implement user role (user/admin) and status (free/banned); role & status can be changed by admin only
public class UserModel {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private ArrayList<HabitModel> usersHabitModels = new ArrayList<>();

    public UserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserModel(String name, String email, String password, ArrayList<HabitModel> usersHabitModels) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.usersHabitModels = usersHabitModels;
    }
}