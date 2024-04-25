package org.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisDataCache {
    private final RedisTemplate<String, Object> jsonRedisTemplate;
    private final ObjectMapper objectMapper;

    public boolean ifKeyExists(String key) {
        return Boolean.TRUE.equals(jsonRedisTemplate.hasKey(key));
    }

    @SneakyThrows
    public void insertDataInRedisCache(String key, Object data) {
        String jsonData = objectMapper.writeValueAsString(data);
        jsonRedisTemplate.opsForValue().set(key, jsonData, Duration.ofMinutes(5));
    }

    @SneakyThrows
    public Object fetchDataFromRedisCache(String key, Class<?> objectType) {
        Object cachedObject = jsonRedisTemplate.opsForValue().get(key);
        return objectMapper.readValue((String) cachedObject, objectType);
    }
}
