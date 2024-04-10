package org.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Lesson13SpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lesson13SpringDataApplication.class, args);
    }

}
