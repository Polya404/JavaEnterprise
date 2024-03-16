package org.application.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.application.models.Task;
import org.application.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
@RequiredArgsConstructor
public class UserService {
    private final TaskService taskService;
    private final Map<Integer, User> users = new HashMap<>();

    public User createUser(User user) {
        return users.put(user.getId(), user);
    }

    public void deleteUser(int userId) {
        users.remove(userId);
    }

    public List<Task> getTasksByUserId(int userId) {
        User user = users.get(userId);
        List<Task> tasksOfCurrentUser = new ArrayList<>();
        Map<Integer, Task> tasks = taskService.getTasks();
        List<Integer> tasksId = user.getTasksId();
        for (Integer taskId : tasksId) {
            tasksOfCurrentUser.add(tasks.get(taskId));
        }
        return tasksOfCurrentUser;
    }

    public User getUserForCurrentTask(Integer taskId) {
        Task task = taskService.getTasks().get(taskId);
        return users.get(task.getUserId());
    }

    public User setTaskForUser(Task task, User user) {
        Map<Integer, Task> tasks = taskService.getTasks();
        Task currentTask = tasks.get(task.getId());
        currentTask.setUserId(user.getId());
        user.addTask(task.getId());
        updateUser(user);
        return user;
    }

    private void updateUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUserById(Integer id) {
        return users.get(id);
    }
}
