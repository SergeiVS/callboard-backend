package org.callboard.controllers.exceptionHandlers;

import com.amazonaws.SdkClientException;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.errorDto.ErrorResponseDto;
import org.callboard.exceptions.AlreadyExistException;
import org.callboard.exceptions.IllegalRequestParamException;
import org.callboard.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e) {
        return buildResponse(e.getMessage(), "NotFoundException", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleAlreadyExistException(AlreadyExistException e) {
        return buildResponse(e.getMessage(), "AlreadyExistException", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalRequestParamException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalRequestParamException(IllegalRequestParamException e) {
        return buildResponse(e.getMessage(), "IllegalRequestParamException", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException e) {

        return buildResponse(e.getMessage(), "BadCredentialsException", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException e) {

        StringBuilder message = new StringBuilder();
        e.getConstraintViolations().forEach(constraintViolation -> {
            message.append(constraintViolation.getMessage()).append("\n");
        });
        return buildResponse(message.toString(), "ConstraintViolationException", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        return buildResponse(e.getMessage(), "IllegalArgumentException", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthException(AuthException e) {
        return buildResponse(e.getMessage(), "AuthException", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException e) {
        log.error(Arrays.toString(e.getStackTrace()), e);
        return buildResponse(e.getMessage(), "RuntimeException", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponseDto> handleIOException(IOException e) {
        return buildResponse(e.getMessage(), "IOException", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SdkClientException.class)
    public ResponseEntity<ErrorResponseDto> handleSdkClientException(SdkClientException e) {
        log.error(Arrays.toString(e.getStackTrace()), e);
        return buildResponse(e.getMessage(), "SdkClientException", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<ErrorResponseDto> buildResponse(String message, String errorType, HttpStatus status) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .errorMessage(message)
                .errorType(errorType)
                .build();
        return new ResponseEntity<>(response, status);
    }

}

