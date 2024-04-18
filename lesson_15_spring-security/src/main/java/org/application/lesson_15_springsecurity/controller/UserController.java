package org.application.lesson_15_springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.application.lesson_15_springsecurity.model.Role;
import org.application.lesson_15_springsecurity.model.User;
import org.application.lesson_15_springsecurity.service.RoleService;
import org.application.lesson_15_springsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable final Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/addRole/{userId}")
    public ResponseEntity<Void> addRole(@PathVariable final Long userId, @RequestBody final Role role) {
        roleService.addRoleToUser(userId, role.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
