package org.application.controllers;

import lombok.AllArgsConstructor;
import org.application.models.Student;
import org.application.services.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping(path = "/getStudents")
    public Map<Integer, Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "/getStudents/")
    public Student getStudentByIdRequest(@RequestParam int id){
        return studentService.getStudentById(id);
    }

    @GetMapping(path = "/getStudents/{id}")
    public Student getStudentByIdPath(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @GetMapping(path = "/getStudents/string")
    public String getString(){
        return "Test string";
    }


}
