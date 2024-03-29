package org.application.end.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.end.utils.UrlClass;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestClient {

    private final RestTemplate restTemplate;

    public ResponseEntity<List<String>> getCustomNews() {
        ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<>() {
        };
        log.info("Getting custom news");
        return restTemplate.exchange(UrlClass.GET_TOP_HEADERS, HttpMethod.GET, null, responseType);
    }
}
