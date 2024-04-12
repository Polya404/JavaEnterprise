package org.application.dao.utils;

import org.application.entity.Course;
import org.application.entity.Student;

public class CourseUtils {

    public void addStudent(Course course, Student student) {
        course.getStudents().add(student);
    }



}
