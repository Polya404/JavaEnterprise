package org.application.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.application.dao.UserDAO;
import org.application.models.Task;
import org.application.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class UserService {
    private final TaskService taskService;
    private final UserDAO userDAO;

    public User createUser(User user) {
        int savedUser = 0;
        if (user.getTasksId() != null && !user.getTasksId().isEmpty()) {
            List<Integer> tasksId = user.getTasksId();
            savedUser = getSavedUser(user, tasksId, savedUser);
        } else {
            savedUser = userDAO.saveUser(user);
        }
        if (savedUser != 1) {
            throw new IllegalArgumentException("Failed to save user");
        }
        return user;
    }

    private int getSavedUser(User user, List<Integer> tasksId, int savedUser) {
        for (Integer id : tasksId) {
            Task task = taskService.getTaskById(id);
            if (task == null) {
                throw new IllegalArgumentException("Task doesn't exist. Please create task before setting it for the user");
            }
            task.setUserId(user.getId());
            savedUser = userDAO.saveUser(user);
            taskService.updateTask(task);
        }
        return savedUser;
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

    public User setTaskForUser(Integer taskId, Integer userId) {
        Task task = taskService.getTaskById(taskId);
        task.setUserId(userId);
        User user = userDAO.getUserById(userId);
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
