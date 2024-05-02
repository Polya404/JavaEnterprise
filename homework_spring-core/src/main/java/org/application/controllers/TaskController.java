package org.application.controllers;

import lombok.RequiredArgsConstructor;
import org.application.annotation.Cacheable;
import org.application.models.Status;
import org.application.models.Task;
import org.application.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Task createTask(@RequestBody Task task) {
        return taskService.createNewTask(task);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(key = "allTask")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTaskById(id);
    }

    @PutMapping("/changeStatus/{taskId}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public Status changeStatus(@PathVariable Integer taskId, @PathVariable Status status) {
        return taskService.changeStatusOfTask(taskId, status);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(key = "getTaskById")
    public Task getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/getTasks/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(key = "getTasksByUserId")
    public List<Task> getTasksByUserId(@PathVariable Integer userId) {
        return taskService.getTasksByUserId(userId);
    }

    @GetMapping("/get/ordered/{orderBy}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(key = "orderedTasks")
    public List<Task> getOrderedTasks(@PathVariable String orderBy) {
        return taskService.getOrderedTask(orderBy);
    }
}
