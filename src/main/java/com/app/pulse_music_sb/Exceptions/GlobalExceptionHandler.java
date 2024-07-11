package com.app.pulse_music_sb.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value =  RuntimeException.class)
    String handlingRuntimeException(RuntimeException e) {
        System.out.println(e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingValidation(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getDetailMessageCode());
    }
}
