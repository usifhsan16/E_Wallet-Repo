package com.example.E_Wallet.ExceptionHandle;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WrongDataErrorHandler {

    @ExceptionHandler(WrongDataException.class)
    public ResponseEntity<String> handleWrongDataException(WrongDataException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> HandleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String HandleConstraintViolationException(ConstraintViolationException e){
        return e.getMessage();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> HandleDataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String>HandleRuntimeException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
