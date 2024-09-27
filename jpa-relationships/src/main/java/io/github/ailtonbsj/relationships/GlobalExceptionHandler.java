package io.github.ailtonbsj.relationships;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
