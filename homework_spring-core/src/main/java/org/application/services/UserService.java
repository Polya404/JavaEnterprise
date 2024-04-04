package org.application.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.application.dao.UserDAO;
import org.application.models.Task;
import org.application.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class UserService {
    private final TaskService taskService;
    private final UserDAO userDAO;

    public User createUser(User user) {
        if (userDAO.saveUser(user) == 1) {
            return user;
        } else throw new IllegalArgumentException();
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public List<Task> getTasksByUserId(int userId) {
        return userDAO.getTasksByUserId(userId);
    }

    public User getUserForCurrentTask(Integer taskId) {
        return userDAO.getUserForCurrentTask(taskId);
    }

    public User setTaskForUser(Task task, User user) {
        task.setUserId(user.getId());
        user.addTasks(List.of(task));
        taskService.updateTask(task);
        return user;
    }

    public List<User> getAllUsers() {
        return userDAO.getUsers().values().stream()
                .peek(user -> {
                    List<Task> tasks = getTasksByUserId(user.getId());
                    user.addTasks(tasks);
                })
                .collect(Collectors.toList());
    }

    public User getUserById(Integer id) {
        List<Task> tasks = getTasksByUserId(id);
        User user = userDAO.getUserById(id);
        user.addTasks(tasks);
        return user;
    }
}
