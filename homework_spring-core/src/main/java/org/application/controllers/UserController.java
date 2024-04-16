package org.application.controllers;

import lombok.RequiredArgsConstructor;
import org.application.models.User;
import org.application.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/getUserByTaskId/{taskId}")
    public ResponseEntity<User> getUserForCurrentTask(@PathVariable Integer taskId) {
        return ResponseEntity.ok(userService.getUserForCurrentTask(taskId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.of(Optional.ofNullable(userService.getAllUsers()));
    }

    @PostMapping("/setTask/{taskId}")
    public ResponseEntity<User> setTask(@PathVariable Integer taskId, @RequestBody Integer userId) {
        return ResponseEntity.ok(userService.setTaskForUser(taskId, userId));
    }
}
