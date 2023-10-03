package com.tenpo.challenge.exception;

import com.tenpo.challenge.common.ExceptionBase;
import org.springframework.http.HttpStatus;

public class RepositoryException extends ExceptionBase {

    public RepositoryException(String message,Exception ex) {
        super("Repository error > " + message,ex, HttpStatus.BAD_REQUEST,true);
    }

    public RepositoryException(String message) {
        super("Repository error > " + message,new Exception(message), HttpStatus.BAD_REQUEST,true);
    }
}
