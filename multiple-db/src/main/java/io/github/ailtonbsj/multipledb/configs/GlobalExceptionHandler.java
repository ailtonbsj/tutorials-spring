package io.github.ailtonbsj.multipledb.configs;

import java.util.HashMap;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.ailtonbsj.multipledb.utils.IdentityConversionException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> BadRequestException(MethodArgumentNotValidException e) {
        ObjectError field = e.getBindingResult().getAllErrors().getFirst();
        HashMap<String, String> fieldError = new HashMap<>();
        fieldError.put("field", ((FieldError) field).getField());
        fieldError.put("message", field.getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> BadRequestException(DataIntegrityViolationException e) {
        HashMap<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IdentityConversionException.class)
    public ResponseEntity<Object> BadRequestException(IdentityConversionException e) {
        HashMap<String, String> error = new HashMap<>();
        error.put("message", "Id inválido. " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
