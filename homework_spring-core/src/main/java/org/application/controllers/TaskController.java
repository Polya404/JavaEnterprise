package org.application.controllers;

import lombok.RequiredArgsConstructor;
import org.application.models.Status;
import org.application.models.Task;
import org.application.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }

    @DeleteMapping("/delete")
    public void deleteTask(Integer id){
        taskService.deleteTaskById(id);
    }

    @PutMapping("/changeStatus/{taskId}")
    public ResponseEntity<Status> changeStatus(@PathVariable Integer taskId, Status status) {
        return ResponseEntity.ok(taskService.changeStatusOfTask(taskId, status));
    }

    @GetMapping("/get")
    public ResponseEntity<Task> getTaskById(Integer id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/get/ordered")
    public ResponseEntity<Queue<Task>> getOrderedTasks(String orderBy) {
        return ResponseEntity.ok(taskService.getOrderedTask(orderBy));
    }
}
