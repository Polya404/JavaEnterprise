package org.application.controller;

import lombok.RequiredArgsConstructor;
import org.application.dto.UserRequestResponse;
import org.application.model.Role;
import org.application.service.RoleService;
import org.application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRequestResponse getUserById(@PathVariable final Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<UserRequestResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable final Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/role/add/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void addRole(@PathVariable final Long userId, @RequestBody final Role role) {
        roleService.addRoleToUser(userId, role.getId());
    }

    @DeleteMapping("/role/delete/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable final Long roleId) {
        roleService.deleteRoleById(roleId);
    }

    @GetMapping("/role/get/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getUserRoles(@PathVariable final Long userId) {
        return roleService.getRoleByUserId(userId);
    }

    @GetMapping("/role/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getRoles() {
        return roleService.getAllExistRoles();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UserRequestResponse updateUser(@RequestBody final UserRequestResponse user) {
        return userService.updateUser(user);

    }
}
