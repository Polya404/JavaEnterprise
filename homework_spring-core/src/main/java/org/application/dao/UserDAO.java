package org.application.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.application.models.Task;
import org.application.models.User;
import org.application.interfaces.UserInterface;
import org.application.services.TaskService;
import org.application.util.DBConnect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "user.useRepository", havingValue = "false")
public class UserDAO implements UserInterface {
    private final DBConnect dbConnect;
    private final TaskService taskService;

    @SneakyThrows
    public User saveUser(User user) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "INSERT INTO users (id, full_name) VALUES (?, ?);"
        )) {
           statement.setInt(1, user.getId());
           statement.setString(2, user.getFullName());
            int savedUser = statement.executeUpdate();
            if (user.getTasksId() != null && !user.getTasksId().isEmpty()) {
                List<Integer> tasksId = user.getTasksId();
                saveUser(user, tasksId);
            }
            if (savedUser != 1) {
                throw new IllegalArgumentException("Failed to save user");
            }
            return user;
        }
    }

    private void saveUser(User user, List<Integer> tasksId) {
        for (Integer id : tasksId) {
            Task task = taskService.getTaskById(id);
            if (task == null) {
                throw new IllegalArgumentException("Task doesn't exist. Please create task before setting it for the user");
            }
            task.setUserId(user.getId());
            saveUser(user);
            taskService.updateTask(task);
        }
    }

    @SneakyThrows
    public void deleteUser(int userId) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "DELETE FROM users WHERE id = ?"
        )) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }

    @SneakyThrows
    public User getUserForCurrentTask(int taskId) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "SELECT * FROM tasks WHERE id = ?"
        )) {
            statement.setInt(1, taskId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserById(resultSet.getInt("user_id"));
            }
            return null;
        }
    }

    @SneakyThrows
    public List<User> getAllUsers() {
        Map<Integer, User> users = new HashMap<>();
        try (Statement statement = dbConnect.connect().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.put(user.getId(), user);
            }
        }
        List<User> userList = new ArrayList<>(users.values());
        userList.forEach(user -> {
            List<Task> tasks = taskService.getTasksByUserId(user.getId());
            if (tasks != null) {
                user.addTasks(tasks);
            }
        });
        return userList;
    }
    @SneakyThrows
    public User getUserById(int userId) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "SELECT * FROM users WHERE id = ?"
        )) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractUserFromResultSet(resultSet);
            }
            throw new IllegalArgumentException();
        }
    }

    @SneakyThrows
    private User extractUserFromResultSet(ResultSet resultSet) {
        int id = resultSet.getInt("id");
        String fullName = resultSet.getString("full_name");
        return new User(id, fullName);
    }
}
