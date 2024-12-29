package com.bibliotecaVirtual.api.exception;

public class LivroJaCadastradoException extends RuntimeException {
    public LivroJaCadastradoException(String message){
        super(message);
    }
}
