package org.application.interfaces;

import org.application.models.Task;

import java.util.List;

public interface TaskInterface {
    Task saveTask(Task task);
    void deleteTaskById(Integer id);
    Task updateTask(Task task);
    void updateTaskStatus(Task task);
    Task getTaskById(int id);
    List<Task> getAllTasks();
    List<Task> getAllTasks(String sortField);
    List<Task> getTasksByUserId(Integer userId);

}
