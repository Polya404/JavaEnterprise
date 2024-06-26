package org.application.end.controllers;

import lombok.RequiredArgsConstructor;
import org.application.end.services.RestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customNews")
@RequiredArgsConstructor
public class CustomNewsController {
    private final RestClient restClient;

    @GetMapping("/getCustomNews")
    public ResponseEntity<List<String>> getCustomHeaders() {
        return restClient.getCustomNews();
    }
}
