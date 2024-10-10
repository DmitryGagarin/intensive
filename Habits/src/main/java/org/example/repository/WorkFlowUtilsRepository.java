package org.example.repository;

import java.io.BufferedReader;
import java.io.IOException;

public interface WorkFlowUtilsRepository {

    void createAdminStart();
    boolean fetchMainMenu(BufferedReader reader) throws IOException;
    void fetchUserMenu(BufferedReader reader) throws IOException;
    void fetchUserInformationChangeMenu(BufferedReader reader) throws IOException;
    void fetchHabitMenu(BufferedReader reader) throws IOException;

    void fetchHabitInformationUpdateMenu(BufferedReader reader) throws IOException;

    void fetchAdminMenu(BufferedReader reader) throws IOException;
}
