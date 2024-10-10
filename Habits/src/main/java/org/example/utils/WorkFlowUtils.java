package org.example.utils;

import org.example.out.ConsoleMenus;
import org.example.Main;
import org.example.repository.WorkFlowUtilsRepository;
import org.example.service.HabitService;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class WorkFlowUtils implements WorkFlowUtilsRepository {
    @Override
    public void createAdminStart() {
        UserService user = new UserService();
        user.createAdmin();
    }

    @Override
    public boolean fetchMainMenu(BufferedReader reader) throws IOException {
        System.out.println("Insert your choice");
        int choice = Integer.parseInt(reader.readLine());
        return switch (choice) {
            case 1 -> {
                UserServiceUtils.userRegProcess(reader);
                yield true;
            }
            case 2 -> {
                UserServiceUtils.userLoginProcess(reader);
                yield true;
            }
            case 3 -> {
                System.out.println("Exiting...");
                yield false;
            }
            default -> {
                System.out.println("Invalid choice, please try again.");
                yield true;
            }
        };
    }

    @Override
    public void fetchUserMenu(BufferedReader reader) throws IOException {
        while (true) {
            ConsoleMenus.userMenu();
            System.out.println("Insert your choice");
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    fetchHabitMenu(reader);
                    break;
                case 2:
                    UserService tempUser = new UserService();
                    System.out.println(tempUser.checkUserInformation(Main.currentAuthenticatedUserModel.getEmail()));
                    break;
                case 3:
                    fetchUserInformationChangeMenu(reader);
                    break;
                case 4:
                    fetchMainMenu(reader);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    @Override
    public void fetchUserInformationChangeMenu(BufferedReader reader) throws IOException {
        while (true) {
            ConsoleMenus.userInformationUpdateMenu();
            int choice = Integer.parseInt(reader.readLine());
            UserService tempUser = new UserService();
            switch (choice) {
                case 1:
                    String newName = reader.readLine();
                    tempUser.userChangeName(Main.currentAuthenticatedUserModel.getName(), newName);
                    fetchUserMenu(reader);
                    break;
                case 2:
                    String newEmail = reader.readLine();
                    tempUser.userChangeEmail(Main.currentAuthenticatedUserModel.getEmail(), newEmail);
                    fetchUserMenu(reader);
                    break;
                case 3:
                    String newPassword = reader.readLine();
                    tempUser.userChangePassword(Main.currentAuthenticatedUserModel.getEmail(), Main.currentAuthenticatedUserModel.getPassword(), newPassword);
                    fetchUserMenu(reader);
                    break;
                case 4:
                    String emailToDelete = reader.readLine();
                    String passwordToDelete = reader.readLine();
                    tempUser.deleteUser(emailToDelete, passwordToDelete);
                    fetchUserMenu(reader);
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }

    @Override
    public void fetchHabitMenu(BufferedReader reader) throws IOException {
        while (true) {
            ConsoleMenus.userHabitsMenu();
            UserService tempUser = new UserService();
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    System.out.println("Insert Habit Name");
                    String habitName = reader.readLine();
                    System.out.println("Insert Habit Description");
                    String habitDescription = reader.readLine();
                    System.out.println("Insert Habit Interval");
                    int interval = Integer.parseInt(reader.readLine());
                    tempUser.userAddHabit(Main.currentAuthenticatedUserModel.getEmail(), habitName, habitDescription, interval);
                    break;
                case 2:
                    System.out.println("Insert Name Of Habit To Delete");
                    String habitToDelete = reader.readLine();
                    tempUser.userDeleteHabit(Main.currentAuthenticatedUserModel.getEmail(), habitToDelete);
                    break;
                case 3:
                    fetchHabitInformationUpdateMenu(reader);
                case 4:
                    fetchUserMenu(reader);
            }
        }
    }

    @Override
    public void fetchHabitInformationUpdateMenu(BufferedReader reader) throws IOException {
        while (true) {
            ConsoleMenus.habitInformationUpdateMenu();
            int choice = Integer.parseInt(reader.readLine());
            HabitService tempHabit = new HabitService();
            tempHabit.readAllHabits();
            switch (choice) {
                case 1:
                    System.out.println("Insert habit name to change");
                    String nameOfHabitChangeName = reader.readLine();
                    System.out.println("Insert new name of this habit");
                    String newName = reader.readLine();
                    tempHabit.updateHabitName(nameOfHabitChangeName, newName);
                    fetchUserMenu(reader);
                    break;
                case 2:
                    System.out.println("Insert habit name to change");
                    String nameOfHabitChangeDesc = reader.readLine();
                    System.out.println("Insert new description of this habit");
                    String newDescription = reader.readLine();
                    tempHabit.updateHabitDescription(nameOfHabitChangeDesc, newDescription);
                    fetchUserMenu(reader);
                    break;
                case 3:
                    System.out.println("Insert habit name to change");
                    String nameOfHabitChangeInterval = reader.readLine();
                    System.out.println("Insert new interval of this habit");
                    int newInterval = Integer.parseInt(reader.readLine());
                    tempHabit.updateHabitInterval(nameOfHabitChangeInterval, newInterval);
                    fetchUserMenu(reader);
                    break;
                case 4:
                    fetchUserMenu(reader);
                    break;
            }
        }
    }

    @Override
    public void fetchAdminMenu(BufferedReader reader) throws IOException {
        while (true) {
            ConsoleMenus.adminMenu();
            int choice = Integer.parseInt(reader.readLine());
            UserService user = new UserService();
            switch (choice) {
                case 1:
                    System.out.println(UserService.userModels);
                    break;
                case 2:
                    System.out.println("Insert email of user");
                    String emailToGet = reader.readLine();
                    user.adminGetCertainUser(emailToGet);
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Insert email of user");
                    String emailToDelete = reader.readLine();
                    user.adminDeleteUser(emailToDelete);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
            }
        }
    }
}
