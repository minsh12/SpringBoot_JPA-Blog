package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice   // 모든 Exception에 대해 발생하면 해당 클래스로 들어오게 함
@RestController
public class GlobalExceptionHandler {

//    // IllegalArgumentException이 처리되었을 때 실행되는 함수
//    @ExceptionHandler(value = IllegalArgumentException.class)   // IllegalArgumentException이 발생하면 그에대한 에러는 해당 함수에 전달
//    public String handleArgumentException(IllegalArgumentException e) {
//        return "<h1>" + e.getMessage() + "<h1>";
//    }

    // 모든 Exception이 처리되었을 때 실행되는 함수
    @ExceptionHandler(value = Exception.class)
    public String handleArgumentException(Exception e) {
        return "<h1>" + e.getMessage() + "<h1>";
    }
}
