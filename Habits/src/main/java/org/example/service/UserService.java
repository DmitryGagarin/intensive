package org.example.service;

import org.example.model.UserModel;
import org.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: work on boiler-plate code, get rid of similar pieces
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
        for (var user : userModels) {
            if (user.getName().equals(name)) {
                return "User can't be registered, this name already exists";
            }
            else if (user.getEmail().equals(email)) {
                return "User can't be registered, this email already exists";
            }
        }
        UserModel userModel = new UserModel(name, email, password);
        userModels.add(userModel);
        return "User successfully registered";
    }

    @Override
    public boolean loginUser(String email, String password) {
        UserModel userModel = new UserModel(email, password);
        for (var userIterate : userModels) {
            if (userModel.getEmail().equals(userIterate.getEmail())) {
                if (userModel.getPassword().equals(userIterate.getPassword())) {
                    System.out.println("Successfully Authenticated");
                    return true;
                }
            }
        }
        System.out.println("Authentication Failed");
        return false;
    }

    @Override
    public void userChangeName(String email, String newName) {
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                userIterate.setName(newName);
            }
        }
    }

    @Override
    public void userChangeEmail(String email, String newEmail) {
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                userIterate.setEmail(newEmail);
            }
        }
    }

    @Override
    public void userChangePassword(String email, String oldPassword, String newPassword) {
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                if (userIterate.getPassword().equals(oldPassword)) {
                    userIterate.setPassword(newPassword);
                }
            }
        }
    }

    @Override
    public void deleteUser(String email, String password) {
        Iterator<UserModel> iterator = userModels.iterator();
        while (iterator.hasNext()) {
            UserModel userIterate = iterator.next();
            if (userIterate.getEmail().equals(email) && userIterate.getPassword().equals(password)) {
                iterator.remove(); // Use iterator to safely remove the item
            }
        }
    }

    @Override
    public void userAddHabit(String email, String habitName, String habitDescription, int interval) {
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                HabitService habit = new HabitService();
                userIterate.setUsersHabitModels(habit.createHabit(habitName, habitDescription, interval));
            }
        }
    }

    @Override
    public void userDeleteHabit(String email, String habitName) {
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                HabitService habit = new HabitService();
                userIterate.getUsersHabitModels().remove(habitName);
            }
        }
    }

    @Override
    public UserModel checkUserInformation(String email) {
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                return userIterate;
            }
        }
        return null;
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
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                sendPasswordToEmail(email);
            }
        }
    }

    @Override
    public String sendPasswordToEmail(String email) {
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                return userIterate.getPassword();
            }
        }
        return "Mail not found";
    }

    @Override
    public void adminDeleteUser(String email) {
        userModels.removeIf(user -> user.getEmail().equals(email));
    }

    @Override
    public void adminBanUser(String email) {
        for (var user : userModels) {
            if (user.getEmail().equals(email)) {
                System.out.println("blocked");
            }
        }
    }

    @Override
    public String adminGetCertainUser(String email) {
        for (var userIterate : userModels) {
            if (userIterate.getEmail().equals(email)) {
                return userIterate.toString();
            }
        }
        return "User Not Found";
    }
}