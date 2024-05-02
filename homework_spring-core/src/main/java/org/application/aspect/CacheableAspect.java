package org.application.aspect;

import org.application.annotation.Cacheable;
import org.application.services.RedisDataCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.logging.Logger;


@Component
@Aspect
public class CacheableAspect {

    Logger logger = Logger.getLogger("Cache");

    @Autowired
    RedisDataCache redisDataCache;

    @Pointcut("@annotation(org.application.annotation.Cacheable)")
    public void cacheMethodResult() {

    }

    @Around("cacheMethodResult()")
    public Object cacheMethodResult(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String key = extractCacheKey(proceedingJoinPoint);
        if (redisDataCache.ifKeyExists(key)) {
            return getFromCache(proceedingJoinPoint, key);
        } else {
            return addToCache(proceedingJoinPoint, key);
        }
    }

    private Object getFromCache(ProceedingJoinPoint proceedingJoinPoint, String key) {
        logger.info("Fetching data from Redis cache for key " + key);
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Class<?> returnType = signature.getMethod().getReturnType();
        return redisDataCache.fetchDataFromRedisCache(key, returnType);
    }

    private Object addToCache(ProceedingJoinPoint proceedingJoinPoint, String key) throws Throwable {
        Object response;
        if (proceedingJoinPoint.getArgs().length != 0) {
            Object argument = proceedingJoinPoint.getArgs()[0];
            response = proceedingJoinPoint.proceed(new Object[]{argument});
        } else {
            response = proceedingJoinPoint.proceed();
        }
        logger.info("Adding data to Redis cache for key " + key);
        redisDataCache.insertDataInRedisCache(key, response);
        return response;
    }


    private String extractCacheKey(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Cacheable cacheableAnnotation = method.getAnnotation(Cacheable.class);
        if (cacheableAnnotation != null) {
            return cacheableAnnotation.key();
        } else {
            return null;
        }
    }

}



