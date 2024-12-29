package com.bibliotecaVirtual.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(LivroJaCadastradoException.class)
    public ResponseEntity<String> handleLivroJaCadastradoException(LivroJaCadastradoException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(LivroNotFoundException.class)
    public ResponseEntity<String> handleLivroNotFoundException(LivroNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
