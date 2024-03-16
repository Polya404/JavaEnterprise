package org.application.springcore.service;


import org.application.springcore.component.MyComponent;
import org.application.springcore.config.ConfigApp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OtherService {
    @Autowired
    private ConfigApp configApp;

    @Autowired
    private List<MyComponent> myComponentList;
    private final StudentService studentService;

    public OtherService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void printOfStudents(){
        System.out.println(myComponentList.size());
        System.out.println(configApp.getValue());
        System.out.println(studentService.getStudents());
    }
}
