package org.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RandomException.class)
    public ErrorBody handleCustomException(Exception e){
        System.out.println("We have a problem" + e.getMessage());
        e.printStackTrace();
        return new ErrorBody("Custom error", "Custom details");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorBody> handleGlobalException(Exception e){
        System.out.println("We have a problem " + e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorBody("Custom error", "Custom details"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    record ErrorBody(String errorName, String errorDetails) {

    }
}
