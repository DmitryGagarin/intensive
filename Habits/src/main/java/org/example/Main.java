package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static User currentAuthenticatedUser;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws Exception {
        createAdminStart();
        while (true) {
            ConsoleMenu.mainMenu();
            fetchMainMenu(reader);
        }
    }

    protected static void createAdminStart() {
        UserUtils user = new UserUtils();
        user.createAdmin();
    }

    private static void userRegProcess(BufferedReader reader) throws IOException {
        System.out.println("Insert your name");
        String name = reader.readLine();

        System.out.println("Insert your email");
        String email = reader.readLine();

        System.out.println("Insert your password");
        String password = reader.readLine();

        System.out.println("Verify your password");
        String passwordVerification = reader.readLine();

        var passwordVer = UserUtils.passwordVerification(password, passwordVerification);
        if (!passwordVer) {
            System.out.println("Your passwords are different, try one more time");
        } else {
            var newUser = UserUtils.registerNewUser(name, email, password);
            System.out.println(newUser);
        }
    }

    private static void userLoginProcess(BufferedReader reader) throws IOException {

        System.out.println("Insert your email");
        String email = reader.readLine();

        System.out.println("Insert your password");
        String password = reader.readLine();

        if (email.equals("Admin@habits.com") && password.equals("root")) {
            adminFunctionality(reader);
        } else {
            UserUtils.loginUser(email, password);

            String name = "";
            for (var user : UserUtils.users) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    name = user.getName();
                }
            }
            fillCurrentAuthenticatedUser(name, email, password);

            ConsoleMenu.userMenu();
            fetchLoginMenu(reader);
        }
    }

    private static void fillCurrentAuthenticatedUser(String name, String email, String password) {
        currentAuthenticatedUser.setName(name);
        currentAuthenticatedUser.setEmail(email);
        currentAuthenticatedUser.setPassword(password);
    }

    private static void fetchMainMenu(BufferedReader reader) throws IOException {

        System.out.println("Insert your choice");
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1:
                userRegProcess(reader);
                break;
            case 2:
                userLoginProcess(reader);
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    private static void fetchLoginMenu(BufferedReader reader) throws IOException {
        System.out.println("Insert your choice");
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1:
//                userProcess();
                break;
            case 2:
//                loginProcess();
                break;
            case 3:
                fetchUserInformationChangeMenu(reader);
                break;
            case 4:
                System.exit(0);
                break;
        }
    }

    private static void fetchUserInformationChangeMenu(BufferedReader reader) throws IOException {
        ConsoleMenu.userInformationUpdateMenu();
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1:
                String newName = reader.readLine();
                UserUtils.userChangeName(currentAuthenticatedUser.getName(), newName);
                break;
            case 2:
                String newEmail = reader.readLine();
                UserUtils.userChangeEmail(currentAuthenticatedUser.getEmail(), newEmail);
                break;
            case 3:
                String newPassword = reader.readLine();
                UserUtils.userChangePassword(currentAuthenticatedUser.getEmail(), currentAuthenticatedUser.getPassword(), newPassword);
                break;
            case 4:
                String emailToDelete = reader.readLine();
                String passwordToDelete = reader.readLine();
                UserUtils.deleteUser(emailToDelete, passwordToDelete);
            case 5:
                System.exit(0);
        }
    }

    private static void adminFunctionality(BufferedReader reader) throws IOException {
        ConsoleMenu.adminMenu();
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1 -> System.out.println(UserUtils.users);
        }
    }
}