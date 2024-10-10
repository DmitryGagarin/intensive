package org.example;

import org.example.model.UserModel;
import org.example.out.ConsoleMenus;
import org.example.utils.WorkFlowUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// TODO: tests
// TODO: workflow
public class Main {

    public static UserModel currentAuthenticatedUserModel;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        WorkFlowUtils workflow = new WorkFlowUtils();
        workflow.createAdminStart();
        boolean running = true;
        while (running) {
            ConsoleMenus.mainMenu();
            running = workflow.fetchMainMenu(reader);
        }
    }
}