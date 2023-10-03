package com.tenpo.challenge.exception;

import com.tenpo.challenge.common.ExceptionBase;
import org.springframework.http.HttpStatus;

public class RateLimitException extends ExceptionBase {
    public RateLimitException(String message) {
        super("Rate limit ex > " + message,new Exception(message), HttpStatus.TOO_MANY_REQUESTS,false);
    }

}
