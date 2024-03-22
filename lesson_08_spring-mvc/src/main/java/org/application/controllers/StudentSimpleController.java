package org.application.controllers;

import lombok.AllArgsConstructor;
import org.application.models.Student;
import org.application.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@AllArgsConstructor
public class StudentSimpleController {
    private StudentService studentService;

//    @GetMapping(path = "/getStudents")
//    public @ResponseBody Map<Integer, Student> getStudents(){
//        return studentService.getStudents();
//    }
}
