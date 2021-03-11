package br.com.LoginSpringBootThymeleaf.exceptions;

public class BadCredentialsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadCredentialsException(String msg) {
        super(msg);
    }
}
