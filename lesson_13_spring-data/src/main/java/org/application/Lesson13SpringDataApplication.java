package org.application;

import org.application.dto.Student;
import org.application.repository.StudentDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Lesson13SpringDataApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Lesson13SpringDataApplication.class, args);

        StudentDAO studentDAO = applicationContext.getBean(StudentDAO.class);

        for (Student student : studentDAO.findAll()) {

            System.out.println(student);

        }


        System.out.println("------------------------");

        Student studentsByNameAndEmail = studentDAO.getHilelelGoodStudentsByNameAndEmail("Timur", "email");
        System.out.println(studentsByNameAndEmail);
    }


}
