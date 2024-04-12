package org.application.services;

import lombok.RequiredArgsConstructor;
import org.application.interfaces.TaskInterface;
import org.application.models.Status;
import org.application.models.Task;

import java.util.*;

@RequiredArgsConstructor
public class TaskService {

    private final TaskInterface taskInterface;

    public Task createNewTask(Task task) {
        return taskInterface.saveTask(task);
    }

    public void deleteTaskById(Integer id) {
        taskInterface.deleteTaskById(id);
    }

    public Status changeStatusOfTask(Integer taskId, Status status) {
        Task task = taskInterface.getTaskById(taskId);
        task.setStatus(status);
        taskInterface.updateTaskStatus(task);
        return task.getStatus();
    }

    public Task updateTask(Task task) {
        return taskInterface.updateTask(task);
    }

    public Task getTaskById(Integer idTask) {
        return taskInterface.getTaskById(idTask);
    }


    public List<Task> getOrderedTask(String fieldName) {
        return taskInterface.getAllTasks(fieldName);
    }

    public List<Task> getTasks() {
        return taskInterface.getAllTasks();
    }

    public List<Task> getTasksByUserId(Integer userId) {
        return taskInterface.getTasksByUserId(userId);
    }
}
