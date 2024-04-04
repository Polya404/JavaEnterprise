package org.application.controllers;

import lombok.RequiredArgsConstructor;
import org.application.models.Status;
import org.application.models.Task;
import org.application.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }

    @GetMapping("/getAll")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTaskById(id);
    }

    @PutMapping("/changeStatus/{taskId}/{status}")
    public ResponseEntity<Status> changeStatus(@PathVariable Integer taskId, @PathVariable Status status) {
        return ResponseEntity.ok(taskService.changeStatusOfTask(taskId, status));
    }

    @GetMapping("/get")
    public ResponseEntity<Task> getTaskById(@RequestBody Integer id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/get/ordered/{orderBy}")
    public ResponseEntity<List<Task>> getOrderedTasks(@PathVariable String orderBy) {
        return ResponseEntity.ok(taskService.getOrderedTask(orderBy));
    }
}
