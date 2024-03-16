package org.application.springcore.config;

import org.application.springcore.service.OtherService;
import org.application.springcore.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

    @Value("${app.my.value}")
    private String value;

    public String getValue() {
        return value;
    }

    @Bean
    public StudentService studentService(){
        return new StudentService();
    }

    @Bean
    public OtherService otherService(StudentService studentService){
        return new OtherService(studentService);
    }
}
