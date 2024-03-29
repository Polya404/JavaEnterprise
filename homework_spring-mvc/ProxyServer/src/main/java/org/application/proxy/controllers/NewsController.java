package org.application.proxy.controllers;

import lombok.RequiredArgsConstructor;
import org.application.proxy.services.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/getHeaders")
    public ResponseEntity<List<String>> getTopArticle() {
        return ResponseEntity.ok(newsService.getTopHeaders());
    }
}
