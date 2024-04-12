package org.application;

import org.application.config.HibernateSession;
import org.application.dao.CourseDAO;
import org.application.dao.StudentDAO;
import org.application.dao.utils.CourseUtils;
import org.application.entity.Course;
import org.application.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Lesson12HibernateApplication {

    public static void main(String[] args) {


        try (SessionFactory sessionFactory = HibernateSession.sessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            StudentDAO studentDAO = new StudentDAO(session);

            CourseDAO courseDAO = new CourseDAO(session);

            CourseUtils courseUtils = new CourseUtils();

            courseDAO.add(new Course("Java Enterprise"));

            List<Student> students = studentDAO.findAll();

            Course currentCourse = courseDAO.findById(1);

            courseUtils.addStudent(currentCourse, new Student("new", "new"));

            courseDAO.add(currentCourse);

            session.getTransaction().commit();


        }

    }
}
