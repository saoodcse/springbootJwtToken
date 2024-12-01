package com.saood.exception;

import com.saood.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    // Handle specific exceptions
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(ApplicationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                null,
                ex.getMessage(),
                ex.getCode()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    // Handle generic exceptions


    // Example: Return custom response body
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                null,
                ex.getMessage(),
                "1000"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException  ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                null,
                ex.getParameterName(),
                "2001"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                null,
                ex.getMessage(),
                "2002"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                null,
                "Uncaught Exception",
                "9000"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);    }

}


