package org.application.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.application.dao.TaskDAO;
import org.application.models.Status;
import org.application.models.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Getter
@RequiredArgsConstructor
public class TaskService {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final TaskDAO taskDAO;

    public Task createNewTask(Task task) {
        if (taskDAO.saveTask(task) == 1) {
            return task;
        } else throw new IllegalArgumentException();
    }

    public void deleteTaskById(Integer id) {
        taskDAO.deleteTask(id);
    }

    public Status changeStatusOfTask(Integer taskId, Status status) {
        Task task = taskDAO.getTaskById(taskId);
        task.setStatus(status);
        taskDAO.updateTaskStatus(task);
        return task.getStatus();
    }

    public Task updateTask(Task task) {
        return taskDAO.updateTask(task);
    }

    public Task getTaskById(Integer idTask) {
        return taskDAO.getTaskById(idTask);
    }


    public List<Task> getOrderedTask(String fieldName) {
        return taskDAO.getAllTasks(fieldName);
    }

    public List<Task> getTasks() {
        return taskDAO.getAllTasks();
    }
}
