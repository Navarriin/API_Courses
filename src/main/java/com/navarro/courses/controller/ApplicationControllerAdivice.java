package com.navarro.courses.controller;

import com.navarro.courses.exceptions.RecordNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApplicationControllerAdivice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException err){
        return err.getMessage();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException err){
        return err.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + "" + error.getDefaultMessage())
                .reduce("",(acc, error) -> acc + error +"\n");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleConstraintViolationException(ConstraintViolationException err){
        return err.getConstraintViolations().stream()
                .map(error -> error.getPropertyPath() + "" + error.getMessage())
                .reduce("",(acc, error) -> acc + error +"\n");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException err) {
        if (err != null && err.getRequiredType() != null) {
            String type = err.getRequiredType().getName();
            String[] typeParts = type.split("\\.");
            String typeName = typeParts[typeParts.length - 1];
            return err.getName() + " should be of type " + typeName;
        }
        return "Argument type not valid";
    }
}
