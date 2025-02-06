package com.l2code.contato_service.exception;

import com.l2code.contato_service.dto.ErrorDetailsDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDTO> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                           WebRequest webRequest) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(getTime(), HttpStatus.NOT_FOUND.value(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetailsDTO> handleBadRequestException(BadRequestException exception,
                                                                     WebRequest webRequest) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(getTime(), HttpStatus.BAD_REQUEST.value(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDetailsDTO> handleExceptionException(ConflictException exception, WebRequest webRequest) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(getTime(), HttpStatus.CONFLICT.value(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    private Long getTime() {
        return LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
