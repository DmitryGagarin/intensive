package org.example.utils;

import org.example.Main;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserServiceUtils {

    public static void userRegProcess(BufferedReader reader) throws IOException {
        System.out.println("Insert your name");
        String name = reader.readLine();

        System.out.println("Insert your email");
        String email = reader.readLine();

        System.out.println("Insert your password");
        String password = reader.readLine();

        System.out.println("Verify your password");
        String passwordVerification = reader.readLine();

        UserService tempUser = new UserService();
        var passwordVer = tempUser.passwordVerification(password, passwordVerification);
        var emailVer = tempUser.emailVerification(email);
        if (!passwordVer) {
            System.out.println("Your passwords are different, try one more time");
        } else if (!emailVer) {
            System.out.println("Your email is incorrect");
        } else {
            var newUser = tempUser.registerNewUser(name, email, password);
            System.out.println(newUser);
        }
    }

    public static void userLoginProcess(BufferedReader reader) throws IOException {
        System.out.println("Insert your email");
        String email = reader.readLine();

        System.out.println("Insert your password");
        String password = reader.readLine();

        if (email.equals("Admin@habits.com") && password.equals("root")) {
            WorkFlowUtils workFlowUtils = new WorkFlowUtils();
            workFlowUtils.fetchAdminMenu(reader);
        } else {
            UserService userService = new UserService();
            boolean loginSuccess = userService.loginUser(email, password);
            if (loginSuccess) {
                Main.currentAuthenticatedUserModel = userService.userModels.stream()
                        .filter(user -> user.getEmail().equals(email))
                        .findFirst()
                        .orElse(null);

                if (Main.currentAuthenticatedUserModel != null) {
                    WorkFlowUtils workflow = new WorkFlowUtils();
                    while (true) {
                        workflow.fetchUserMenu(reader);
                    }
                } else {
                    System.out.println("User not found after login.");
                }
            } else {
                System.out.println("Login failed. Please try again.");
            }
        }
    }

    private static void fillCurrentAuthenticatedUser(String name, String email, String password) {
        if (Main.currentAuthenticatedUserModel != null) {
            Main.currentAuthenticatedUserModel.setName(name);
            Main.currentAuthenticatedUserModel.setEmail(email);
            Main.currentAuthenticatedUserModel.setPassword(password);
        }
    }
}
