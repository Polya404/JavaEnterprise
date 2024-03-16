package org.application.services;

import org.application.models.Status;
import org.application.models.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    UserService userService;

    @InjectMocks
    TaskService taskService;

    @BeforeEach
    void setUp() {
        Map<Integer, Task> tasks = taskService.getTasks();
        tasks.clear();
    }

    @Test
    void createNewTask() {
        taskService.createNewTask(new Task(1));
        int size = taskService.getTasks().size();
        Assertions.assertEquals(1, size);
    }

    @Test
    void changeStatusOfTask() {
        Task task = newTask(1);
        taskService.changeStatusOfTask(task.getId(), Status.IN_PROGRESS);
        Assertions.assertEquals(Status.IN_PROGRESS, task.getStatus());
    }

    @Test
    void getTaskById() {
        Task task = newTask(1);
        Task taskById = taskService.getTaskById(task.getId());
        Assertions.assertEquals("Test", taskById.getName());
    }

    @Test
    void getOrderedTask() {
        Task task1 = newTask(1);
        task1.setPriority(3);
        Task task2 = newTask(2);
        task2.setPriority(1);
        Task task3 = newTask(3);
        task3.setPriority(2);

        Queue<Task> orderedTasks = taskService.getOrderedTask("priority");

        assertEquals(task2, orderedTasks.poll());
        assertEquals(task3, orderedTasks.poll());
        assertEquals(task1, orderedTasks.poll());
        assertTrue(orderedTasks.isEmpty());
    }

    private Task newTask(int id) {
        Task task = Task.builder()
                .id(id)
                .name("Test")
                .status(Status.TO_DO)
                .deadline(LocalDate.now().plusDays(7))
                .priority(new Random().nextInt(5))
                .build();
        taskService.getTasks().put(task.getId(), task);
        return task;
    }
}