package org.application.config;

import lombok.RequiredArgsConstructor;
import org.application.interfaces.TaskInterface;
import org.application.services.TaskService;
import org.application.interfaces.UserInterface;
import org.application.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public TaskService taskService(@Value("${task.useRepository}") boolean useRepository,
                                   @Qualifier("taskJpa") TaskInterface jpaTaskRepository,
                                   @Qualifier("taskDAO") TaskInterface taskDAO) {
        return new TaskService(useRepository ? jpaTaskRepository : taskDAO);
    }

    @Bean
    public UserService userService(@Value("${user.useRepository}") boolean useRepository,
                                   @Qualifier("userJpa") UserInterface jpaUserRepository,
                                   @Qualifier("userDAO") UserInterface userDAO,
                                   TaskService taskService) {
        return new UserService(useRepository ? jpaUserRepository : userDAO, taskService);
    }

}

