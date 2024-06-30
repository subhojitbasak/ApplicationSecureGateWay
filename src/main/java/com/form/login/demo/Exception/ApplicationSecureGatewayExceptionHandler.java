package com.form.login.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationSecureGatewayExceptionHandler extends Exception{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        Map<String , List<String>> errors = new HashMap<>();

        List<String> list = new ArrayList<>();
        for (FieldError err : exception.getBindingResult().getFieldErrors()) {
            String defaultMessage = err.getDefaultMessage();
            list.add(defaultMessage);
        }
        errors.put("error", list);
//        errors.put("error",exception.getBindingResult().getFieldError().getDefaultMessage());
        return errors;
    }
}
