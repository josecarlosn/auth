package br.com.josecarlosn.auth.exception;


public class UserException extends RuntimeException {
    public UserException(){super("UserException.");}
    public UserException(String message) {super(message);}
}
