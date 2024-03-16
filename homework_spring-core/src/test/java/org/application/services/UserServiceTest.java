package org.application.services;

import org.application.models.Task;
import org.application.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    TaskService taskService;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        Map<Integer, User> users = userService.getUsers();
        users.clear();
    }

    @Test
    void createUser() {
        userService.createUser(new User(1));
        int size = userService.getUsers().size();
        Assertions.assertEquals(1, size);
    }

    @Test
    void deleteUser() {
        newUser();
        userService.deleteUser(1);
        int size = userService.getUsers().size();
        Assertions.assertEquals(0, size);
    }

    @Test
    void getTasksByUserId() {
        User user = newUser();
        taskService.getTasks().put(1, new Task(1));
        taskService.getTasks().put(2, new Task(2));
        user.addTask(1);
        user.addTask(2);
        userService.getTasksByUserId(1);
        Assertions.assertEquals(2, user.getTasksId().size());
    }

    @Test
    void getUserById() {
        User user = newUser();
        User userById = userService.getUserById(user.getId());
        Assertions.assertEquals("Test1", userById.getFullName());
    }

    private User newUser() {
        User user = new User(1);
        user.setFullName("Test1");
        userService.getUsers().put(user.getId(), user);
        return user;
    }
}