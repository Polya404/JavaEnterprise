package org.application;

import org.application.models.Status;
import org.application.models.Task;
import org.application.models.User;
import org.application.services.TaskService;
import org.application.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class HomeworkSpringCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HomeworkSpringCoreApplication.class, args);

        TaskService taskService = context.getBean(TaskService.class);
        UserService userService = context.getBean(UserService.class);

        Task task = Task.builder()
                .id(1)
                .name("Task")
                .status(Status.TO_DO)
                .description("Test")
                .deadline(LocalDate.now().plusDays(7))
                .priority(2)
                .build();

        Task task1 = Task.builder()
                .id(2)
                .name("Task1")
                .status(Status.BLOCKED)
                .description("Test")
                .deadline(LocalDate.now().plusDays(6))
                .priority(1)
                .build();

        Task task2 = Task.builder()
                .id(3)
                .name("Task2")
                .status(Status.DONE)
                .description("Test")
                .deadline(LocalDate.now().plusDays(1))
                .priority(5)
                .build();

        User user = new User(1);
        user.setFullName("User1");

        System.out.println(userService.createUser(user));
        System.out.println(userService.getUserById(user.getId()));
        System.out.println(taskService.createNewTask(task));
        System.out.println(userService.setTaskForUser(task, user));
        System.out.println(userService.getTasksByUserId(user.getId()));
        taskService.changeStatusOfTask(task.getId(), Status.IN_PROGRESS);
        System.out.println(taskService.getTaskById(task.getId()));
        System.out.println(userService.getUserForCurrentTask(task.getId()));
        taskService.createNewTask(task1);
        taskService.createNewTask(task2);
        System.out.println(taskService.getOrderedTask("priority"));
        System.out.println(taskService.getOrderedTask("status"));
        System.out.println(taskService.getOrderedTask("deadline"));

    }

}
