package org.example;

import java.util.ArrayList;

public class UserUtils {

    static ArrayList<User> users = new ArrayList<>();

    public void createAdmin() {
        users.add(new User("Admin", "Admin@habits.com", "root"));
    }

    public static String registerNewUser(String name, String email, String password) {
        for (var user : users) {
            if (user.getName().equals(name) || user.getEmail().equals(email)) {
                return "User can't be registered, this name already exists";
            }
        }
        User user = new User(name, email, password);
        users.add(user);
        return "User successfully registered";
    }

    protected static boolean loginUser(String email, String password) {
        User user = new User(email, password);
        for (var userIterate : users) {
            if (user.getEmail().equals(userIterate.getEmail())) {
                if (user.getPassword().equals(userIterate.getPassword())) {
                    System.out.println("Successfully Authenticated");
                    return true;
                }
            }
        }
        System.out.println("Authentication Failed");
        return false;
    }

    protected static void userChangeName(String name, String newName) {
        for (var userIterate : users) {
            if (userIterate.getName().equals(name)) {
                userIterate.setName(newName);
            }
        }
    }

    protected static void userChangeEmail(String email, String newEmail) {
        for (var userIterate : users) {
            if (userIterate.getEmail().equals(email)) {
                userIterate.setEmail(newEmail);
            }
        }
    }

    protected static void userChangePassword(String email, String oldPassword, String newPassword) {
        for (var userIterate : users) {
            if (userIterate.getEmail().equals(email)) {
                if (userIterate.getPassword().equals(oldPassword)) {
                    userIterate.setPassword(newPassword);
                }
            }
        }
    }

    public static void deleteUser(String email, String password) {
        for (var userIterate : users) {
            if (userIterate.getEmail().equals(email)) {
                if (userIterate.getPassword().equals(password)) {
                    users.remove(userIterate);
                }
            }
        }
    }

    protected void userAddHabit(String email, String habitName, String habitDescription, int interval) {
        for (var userIterate : users) {
            if (userIterate.getEmail().equals(email)) {
                HabitUtils habit = new HabitUtils();
                userIterate.setUsersHabits(habit.createHabit(habitName, habitDescription, interval));
            }
        }
    }

    public static boolean passwordVerification(String password1, String password2) {
        return password1.equals(password2);
    }

    public static void forgetPassword() {

    }

    public static void adminDeleteUser() {

    }

    public static void adminBlockUser() {

    }

}