package com.example.E_Wallet.ExceptionHandle;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WrongDataErrorHandler {

    @ExceptionHandler(WrongDataException.class)
    public String handleWrongDataException(WrongDataException e){
        return e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String HandleIllegalArgumentException(IllegalArgumentException e){
        return e.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String HandleConstraintViolationException(ConstraintViolationException e){
        return e.getMessage();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String HandleDataIntegrityViolationException(DataIntegrityViolationException e){
        return "This Email already exist";
    }
}
