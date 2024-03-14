package org.application.springcore;

import org.application.springcore.models.Student;
import org.application.springcore.service.OtherService;
import org.application.springcore.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringCoreApplication.class, args);
        StudentService studentService = run.getBean(StudentService.class);

        studentService.addStudent(new Student(1, "test"));

        OtherService otherService = run.getBean(OtherService.class);

        otherService.printOfStudents();
    }

}
