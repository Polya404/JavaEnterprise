package org.application.service;

import lombok.RequiredArgsConstructor;
import org.application.models.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class RestClient {
    private final RestTemplate restTemplate;

    public void callMethod(){
        final ResponseEntity<Student> forEntity = restTemplate.getForEntity("http://localhost:8080/getStudents/1", Student.class);
        System.out.println(forEntity);
    }
}
