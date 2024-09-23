package org.callboard.controllers.exceptionHandlers;

import org.callboard.dto.ErrorResponseDto;
import org.callboard.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e) {
        return buildResponse(e.getMessage(), "NotFoundException", HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorResponseDto> buildResponse(String message, String errorType, HttpStatus status) {
       ErrorResponseDto response = ErrorResponseDto.builder()
                .errorMessage(message)
                .errorType(errorType)
                .build();
        return new ResponseEntity<>(response, status);
    }

}
