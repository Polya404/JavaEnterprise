package org.application;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class HomeworkSpringCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkSpringCoreApplication.class, args);
    }

}
