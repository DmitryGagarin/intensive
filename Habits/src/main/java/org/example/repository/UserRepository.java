package org.example.repository;

import org.example.model.UserModel;

public interface UserRepository {

    void createAdmin();

    String registerNewUser(String name, String email, String password);

    boolean loginUser(String email, String password);

    void userChangeName(String name, String newName);

    void userChangeEmail(String email, String newEmail);

    void userChangePassword(String email, String oldPassword, String newPassword);

    void deleteUser(String email, String password);

    void userAddHabit(String email, String habitName, String habitDescription, int interval);

    void userDeleteHabit(String email, String habitName);

    UserModel checkUserInformation(String email);

    boolean passwordVerification(String password1, String password2);

    boolean emailVerification(String email);

    void forgetPassword(String email);

    String sendPasswordToEmail(String email);

    void adminBanUser(String email);

    String adminGetCertainUser(String email);
    void adminDeleteUser(String email);

}
