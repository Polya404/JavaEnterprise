package org.application.interfaces;

import org.application.models.User;

import java.util.List;

public interface UserInterface {
    User saveUser(User user);
    void deleteUser(int userId);
    User getUserForCurrentTask(int taskId);
    List<User> getAllUsers();
    User getUserById(int userId);
}
