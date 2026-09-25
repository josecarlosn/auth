package br.com.josecarlosn.auth.config.exception;

import br.com.josecarlosn.auth.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<RestExceptionMessage> departmentHandle(UserException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestExceptionMessage(HttpStatus.CONFLICT, exception.getMessage()));
    }

}