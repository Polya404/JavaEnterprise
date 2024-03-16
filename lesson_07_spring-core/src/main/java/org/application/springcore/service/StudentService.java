package org.application.springcore.service;

import org.application.springcore.models.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private final Map<Integer, Student> students = new HashMap<>();

    public Student getStudentById(int id) {
        return students.get(id);
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }
}
