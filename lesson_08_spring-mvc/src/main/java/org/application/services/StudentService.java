package org.application.services;

import org.application.models.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Integer, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Map<Integer, Student> getStudents() {
        students.put(1, new Student(1, "Student1"));
//        return students;
        throw new RuntimeException("Can't manage this request");
    }

    public Student getStudentById(int id) {
        students.put(1, new Student(1, "Student1"));
        return students.get(id);
    }
}
