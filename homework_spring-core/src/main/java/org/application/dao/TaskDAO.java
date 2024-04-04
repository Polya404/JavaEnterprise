package org.application.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.application.models.Task;
import org.application.util.DBConnect;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class TaskDAO {
    private final DBConnect dbConnect;

    @SneakyThrows
    public int saveTask(Task task) {
        if (task.getUserId() == null) {
            PreparedStatement statement = dbConnect.connect().prepareStatement(
                    "INSERT INTO tasks (id, name, status, description, deadline, priority) VALUES (?,?,?,?,?,?);"
            );
            return saveTaskWithoutUserId(task, statement);
        } else {
            PreparedStatement statement = dbConnect.connect().prepareStatement(
                    "INSERT INTO tasks (id, user_id, name, status, description, deadline, priority) VALUES (?,?,?,?,?,?,?);"
            );
            return saveTaskWithUserId(task, statement);
        }
    }

    private int saveTaskWithUserId(Task task, PreparedStatement statement) throws SQLException {
        try (statement) {
            statement.setInt(1, task.getId());
            statement.setInt(2, task.getUserId());
            statement.setString(3, task.getName());
            statement.setString(4, task.getStatus().name());
            statement.setString(5, task.getDescription());
            statement.setDate(6, Date.valueOf(task.getDeadline()));
            statement.setInt(7, task.getPriority());
            return statement.executeUpdate();
        }
    }

    private int saveTaskWithoutUserId(Task task, PreparedStatement statement) throws SQLException {
        try (statement) {
            statement.setInt(1, task.getId());
            statement.setString(2, task.getName());
            statement.setString(3, task.getStatus().name());
            statement.setString(4, task.getDescription());
            statement.setDate(5, Date.valueOf(task.getDeadline()));
            statement.setInt(6, task.getPriority());
            return statement.executeUpdate();
        }
    }

    @SneakyThrows
    public void deleteTask(int taskId) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "DELETE FROM tasks WHERE id = ?"
        )) {
            statement.setInt(1, taskId);
            statement.executeUpdate();
        }
    }

    @SneakyThrows
    public int updateTaskStatus(Task task) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "UPDATE tasks SET status = ? WHERE id = ?"
        )) {
            statement.setString(1, task.getStatus().name());
            statement.setInt(2, task.getId());
            return statement.executeUpdate();
        }
    }

    @SneakyThrows
    public Task getTaskById(int id) {
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(
                "SELECT * FROM tasks WHERE id = ?"
        )) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Utils.extractTaskFromResultSet(resultSet);
            }
            return null;
        }
    }

    @SneakyThrows
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Statement statement = dbConnect.connect().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");
            while (resultSet.next()) {
                Task task = Utils.extractTaskFromResultSet(resultSet);
                tasks.add(task);
            }
        }
        return tasks;
    }

    @SneakyThrows
    public List<Task> getAllTasks(String sortField) {
        String sql = "SELECT * FROM tasks ORDER BY " + sortField;
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = Utils.extractTaskFromResultSet(resultSet);
                tasks.add(task);
            }
        }
        return tasks;
    }

    @SneakyThrows
    public Task updateTask(Task task) {
        String sql = "UPDATE tasks SET user_id = ?, name = ?, status = ?, description = ?, deadline = ?, priority = ? WHERE id = ?";
        try (PreparedStatement statement = dbConnect.connect().prepareStatement(sql)) {
            statement.setInt(1, task.getUserId());
            statement.setString(2, task.getName());
            statement.setString(3, task.getStatus().name());
            statement.setString(4, task.getDescription());
            statement.setDate(5, Date.valueOf(task.getDeadline()));
            statement.setInt(6, task.getPriority());
            statement.setInt(7, task.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return task;
            }
        }
        return null;
    }
}
