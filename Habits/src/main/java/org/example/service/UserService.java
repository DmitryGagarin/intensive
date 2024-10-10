package org.example.service;

import org.example.model.UserModel;
import org.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService implements UserRepository {

    public ArrayList<UserModel> userModels = new ArrayList<>();
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void createAdmin() {
        userModels.add(new UserModel("Admin", "Admin@habits.com", "root"));
    }

    @Override
    public String registerNewUser(String name, String email, String password) {
        if (userExists(name, email)) {
            return "User can't be registered, this name or email already exists";
        }
        userModels.add(new UserModel(name, email, password));
        return "User successfully registered";
    }

    private boolean userExists(String name, String email) {
        return userModels.stream()
                .anyMatch(user -> user.getName().equals(name) || user.getEmail().equals(email));
    }

    @Override
    public boolean loginUser(String email, String password) {
        return userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        System.out.println("Successfully Authenticated");
                        return true;
                    } else {
                        System.out.println("Authentication Failed");
                        return false;
                    }
                })
                .orElseGet(() -> {
                    System.out.println("Authentication Failed");
                    return false;
                });
    }

    @Override
    public void userChangeName(String email, String newName) {
        updateUser(email, user -> user.setName(newName));
    }

    @Override
    public void userChangeEmail(String email, String newEmail) {
        updateUser(email, user -> user.setEmail(newEmail));
    }

    @Override
    public void userChangePassword(String email, String oldPassword, String newPassword) {
        updateUser(email, user -> {
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
            }
        });
    }

    private void updateUser(String email, java.util.function.Consumer<UserModel> updater) {
        userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .ifPresent(updater);
    }

    @Override
    public void deleteUser(String email, String password) {
        userModels.removeIf(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    @Override
    public void userAddHabit(String email, String habitName, String habitDescription, int interval) {
        userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .ifPresent(user -> {
                    HabitService habitService = new HabitService();
                    user.getUsersHabitModels().add(habitService.createHabit(habitName, habitDescription, interval)); // Use getter
                });
    }

    @Override
    public void userDeleteHabit(String email, String habitName) {
        userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .ifPresent(user -> user.getUsersHabitModels().removeIf(habit -> habit.getName().equals(habitName))); // Use getter
    }

    @Override
    public UserModel checkUserInformation(String email) {
        return userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean passwordVerification(String password1, String password2) {
        return password1.equals(password2);
    }

    @Override
    public boolean emailVerification(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    @Override
    public void forgetPassword(String email) {
        userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .ifPresent(user -> sendPasswordToEmail(email));
    }

    @Override
    public String sendPasswordToEmail(String email) {
        return userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .map(UserModel::getPassword)
                .orElse("Mail not found");
    }

    @Override
    public void adminDeleteUser(String email) {
        userModels.removeIf(user -> user.getEmail().equals(email));
    }

    @Override
    public void adminBanUser(String email) {
        userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .ifPresent(user -> System.out.println("User " + email + " is blocked"));
    }

    @Override
    public String adminGetCertainUser(String email) {
        return userModels.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .map(UserModel::toString)
                .orElse("User Not Found");
    }
}
