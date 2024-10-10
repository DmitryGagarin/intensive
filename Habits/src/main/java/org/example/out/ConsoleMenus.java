package org.example.out;

public class ConsoleMenus {

    public static void mainMenu() {
        System.out.println("1. Create User");
        System.out.println("2. Authenticate");
        System.out.println("3. Exit");
    }

    public static void userMenu() {
        System.out.println("1. Check Habits");
        System.out.println("2. Check User Information");
        System.out.println("3. Update User Information");
        System.out.println("4. Exit");
    }

    public static void userInformationUpdateMenu() {
        System.out.println("1, Update Name");
        System.out.println("2. Update Email");
        System.out.println("3. Update Password");
        System.out.println("4. Delete Account");
        System.out.println("5. Exit");
    }

    public static void userHabitsMenu() {
        System.out.println("1. Add Habit");
        System.out.println("2. Delete Habit");
        System.out.println("3. Update Habit Information");
        System.out.println("4. Exit");
    }

    public static void habitInformationUpdateMenu() {
        System.out.println("1. Update Name");
        System.out.println("2. Update Description");
        System.out.println("3. Update Interval");
        System.out.println("4. Exit");
    }

    public static void adminMenu() {
        System.out.println("1. Show All Users");
        System.out.println("2. Show Certain User");
        System.out.println("3. Ban User");
        System.out.println("4. Delete User");
        System.out.println("5. Exit");
    }
}
