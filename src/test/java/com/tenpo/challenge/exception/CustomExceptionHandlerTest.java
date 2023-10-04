package com.tenpo.challenge.exception;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class CustomExceptionHandlerTest {

    private CustomExceptionHandler advice;
    @BeforeEach
    void setUp() {
        advice = new CustomExceptionHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handlerRepositoryEx() {
        final String message = "Testing ex handled";
        Exception e=new RepositoryException(message);
        var resp = advice.generalHandler(e);

        var responseEx =resp.getBody();

        assert(responseEx.getMessage().contains(message));
        assertEquals(responseEx.getError(),HttpStatus.BAD_REQUEST.getReasonPhrase());
        assertEquals(responseEx.getStatus(), HttpStatus.BAD_REQUEST.value());
    }
    @Test
    void handlerRateLimitEx() {
        final String message = "Testing rate limit";
        Exception e=new RateLimitException(message);
        var resp = advice.generalHandler(e);

        var responseEx =resp.getBody();

        assert(responseEx.getMessage().contains(message));
        assertEquals(responseEx.getError(),HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
        assertEquals(responseEx.getStatus(), HttpStatus.TOO_MANY_REQUESTS.value());
    }

    @Test
    void handlerCustomEx() {
        final String message = "Testing custom ex limit";
        Exception e=new Exception(message);
        var resp = advice.generalHandler(e);

        var responseEx =resp.getBody();

        assertEquals(responseEx.getError(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        assert(!responseEx.getMessage().contains(message));
        assertEquals(responseEx.getStatus(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}