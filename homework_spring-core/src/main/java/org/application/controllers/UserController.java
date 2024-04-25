package org.application.controllers;

import lombok.RequiredArgsConstructor;
import org.application.annotation.Cacheable;
import org.application.models.User;
import org.application.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/getUserByTaskId/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(key = "userForCurrentTask")
    public User getUserForCurrentTask(@PathVariable Integer taskId) {
        return userService.getUserForCurrentTask(taskId);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(key = "getUserById")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(key = "allUsers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/setTask/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public User setTask(@PathVariable Integer taskId, @RequestBody Integer userId) {
        return userService.setTaskForUser(taskId, userId);
    }
}
