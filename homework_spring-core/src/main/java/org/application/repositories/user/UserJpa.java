package org.application.repositories.user;

import lombok.RequiredArgsConstructor;
import org.application.models.User;
import org.application.interfaces.UserInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@ConditionalOnProperty(value = "user.useRepository", havingValue = "true")
public class UserJpa implements UserInterface {

    private final UserRepository repository;


    public User saveUser(User user) {
        return repository.save(user);
    }


    public void deleteUser(int userId) {
        repository.deleteById(userId);
    }


    public User getUserForCurrentTask(int taskId) {
        return repository.findUserByTaskId(taskId);
    }


    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(int userId) {
        return repository.findById(userId).orElseThrow();
    }
}
