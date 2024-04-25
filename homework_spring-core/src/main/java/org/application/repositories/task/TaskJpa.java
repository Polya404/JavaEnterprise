package org.application.repositories.task;

import lombok.RequiredArgsConstructor;
import org.application.annotation.Cacheable;
import org.application.models.Task;
import org.application.interfaces.TaskInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@ConditionalOnProperty(value = "task.useRepository", havingValue = "true")
public class TaskJpa implements TaskInterface {
    private final TaskRepository repository;

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public void deleteTaskById(Integer id) {
        repository.deleteById(id);
    }

    public Task updateTask(Task task) {
        return repository.save(task);
    }

    public void updateTaskStatus(Task task) {
        repository.save(task);
    }

    public Task getTaskById(int id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public List<Task> getAllTasks(String sortField) {
        return repository.getAllTaskSortedByFieldName(sortField);
    }

    public List<Task> getTasksByUserId(Integer userId) {
        return repository.getTasksByUserId(userId);
    }
}
