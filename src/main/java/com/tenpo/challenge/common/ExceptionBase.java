package com.tenpo.challenge.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public class ExceptionBase extends RuntimeException {

    private HttpStatus status;

    private Boolean requiresLog;

    public ExceptionBase(String message,Exception ex,Boolean requiresLog ){
        super(message,ex);
        this.requiresLog = requiresLog;
    }
    public ExceptionBase(String message,Exception ex){
        super(message,ex);
        this.requiresLog = false;
    }
    public ExceptionBase(String message,Exception ex, HttpStatus status,Boolean requiresLog){
        super(message,ex);
        this.status = status;
        this.requiresLog = requiresLog;
    }

    public ExceptionBase(String message,Exception ex, HttpStatus status){
        super(message,ex);
        this.status = status;
    }
}
