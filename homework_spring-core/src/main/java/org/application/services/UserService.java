package org.application.services;

import lombok.RequiredArgsConstructor;
//import org.application.repositories.user.UserRepository;
import org.application.interfaces.UserInterface;
import org.application.models.Task;
import org.application.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TaskService taskService;
    private final UserInterface userInterface;

    public User createUser(User user) {
        return userInterface.saveUser(user);
    }

    public void deleteUser(int userId) {
        userInterface.deleteUser(userId);
    }

    public User getUserForCurrentTask(Integer taskId) {
        return userInterface.getUserForCurrentTask(taskId);
    }

    public User setTaskForUser(Integer taskId, Integer userId) {
        Task task = taskService.getTaskById(taskId);
        task.setUserId(userId);
        User user = userInterface.getUserById(userId);
        user.addTasks(List.of(task));
        taskService.updateTask(task);
        return user;
    }

    public List<User> getAllUsers() {
        return userInterface.getAllUsers();
    }

    public User getUserById(Integer id) {
        List<Task> tasks = taskService.getTasksByUserId(id);
        User user = userInterface.getUserById(id);
        user.addTasks(tasks);
        return user;
    }
}
