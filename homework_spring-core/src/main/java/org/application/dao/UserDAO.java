package org.application.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.application.models.Task;
import org.application.models.User;
import org.application.util.DBConnect;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@RequiredArgsConstructor
@Component
public class UserDAO {
    private final DBConnect dbConnect;

    @SneakyThrows
    public int saveUser(User user) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "INSERT INTO users (id, full_name) VALUES (?, ?);"
        )) {
           statement.setInt(1, user.getId());
           statement.setString(2, user.getFullName());
           return statement.executeUpdate();
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
    public List<Task> getTasksByUserId(int userId) {
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "SELECT * FROM tasks WHERE user_id = ?"
        )) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = Utils.extractTaskFromResultSet(resultSet);
                tasks.add(task);
            }
        }
        return tasks;
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
    public User updateUser(User user) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "UPDATE users SET full_name = ? WHERE id = ?"
        )) {
            statement.setString(1, user.getFullName());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            return user;
        }
    }

    @SneakyThrows
    public Map<Integer, User> getUsers() {
        Map<Integer, User> users = new HashMap<>();
        try (Statement statement = dbConnect.connect().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.put(user.getId(), user);
            }
        }
        return users;
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
            return null;
        }
    }

    @SneakyThrows
    private User extractUserFromResultSet(ResultSet resultSet) {
        int id = resultSet.getInt("id");
        String fullName = resultSet.getString("full_name");
        User user = new User(id);
        user.setFullName(fullName);
        return user;
    }
}
