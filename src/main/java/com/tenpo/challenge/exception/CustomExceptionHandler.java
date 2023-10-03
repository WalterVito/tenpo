package com.tenpo.challenge.exception;

import com.tenpo.challenge.common.ExceptionBase;
import com.tenpo.challenge.common.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(CustomExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> generalHandler(Exception exception) {
        if (exception instanceof ExceptionBase exBase)
        {
            HttpStatus status = exBase.getStatus();
            if (exBase.getRequiresLog()){
                LOGGER.error("Managed error log >" , exception);
            }
            ErrorResponse error = new ErrorResponse(
                    status.value(),
                    status.getReasonPhrase(),
                    exception.getMessage());

            return new ResponseEntity<>(error, status);
        }
        LOGGER.error("Unexpected error in application not handled >", exception);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "we have experimented technical issues , retry in a few moments");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
