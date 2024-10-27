package com.apiexamples.exception;

import com.apiexamples.payload.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ExceptionDetails> GlobalResourceNotFoundExceptionHandler(ResourceNotFound ex){
    ExceptionDetails err=new ExceptionDetails(ex.getMessage(),new Date());
    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> ExceptionHandler(Exception ex){
        ExceptionDetails err=new ExceptionDetails(ex.getMessage(),new Date());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
