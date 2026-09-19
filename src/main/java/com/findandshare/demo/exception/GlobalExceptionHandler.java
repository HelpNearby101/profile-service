package com.findandshare.demo.exception;

import com.findandshare.demo.enumeration.ResponseStatus;
import com.findandshare.demo.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ApiResponse> handleProfileNotFound(
            ProfileNotFoundException exception) {

        ApiResponse response = new ApiResponse(
                exception.getMessage(),
                ResponseStatus.FAILURE,
                null,
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }
}
