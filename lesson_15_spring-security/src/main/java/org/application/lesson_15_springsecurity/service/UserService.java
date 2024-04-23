package org.application.lesson_15_springsecurity.service;

import org.application.lesson_15_springsecurity.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User findUserByEmail(String email);

    User findUserById(Long id);

    void deleteUserById(Long id);

    List<User> getAllUsers();
}
