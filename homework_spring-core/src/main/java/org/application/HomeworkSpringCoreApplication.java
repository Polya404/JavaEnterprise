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
        SpringApplication.run(HomeworkSpringCoreApplication.class, args);
    }

}
