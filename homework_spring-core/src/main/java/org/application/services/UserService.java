package org.application.services;

import lombok.RequiredArgsConstructor;
//import org.application.repositories.user.UserRepository;
import org.application.interfaces.UserInterface;
import org.application.models.Task;
import org.application.models.User;
import org.application.models.UserTasks;
import org.application.repositories.UserTasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TaskService taskService;
    private final UserInterface userInterface;
    private final UserTasksRepository userTasksRepository;

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
        User user = userInterface.getUserById(userId);
        Task task = taskService.getTaskById(taskId);
        task.setUser(user);
        userTasksRepository.save(UserTasks.builder()
                .task(task)
                .user(user)
                .build());
        List<Task> tasksByUserId = taskService.getTasksByUserId(userId);
        user.addTasks(tasksByUserId);
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
