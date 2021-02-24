package com.carona.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_LABEL = "ERROR -> {} - {}";

    @Autowired
    ObjectMapper objectMapper;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());

        log.error(ERROR_LABEL, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}