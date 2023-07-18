package com.validate.HandlingExceptions.controller;

import com.validate.HandlingExceptions.dto.ErrorDTO;
import com.validate.HandlingExceptions.exceptions.BusinessException;
import com.validate.HandlingExceptions.exceptions.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex) {
    ErrorDTO error = ErrorDTO.builder()
        .code("P-500")
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = RequestException.class)
  public ResponseEntity<ErrorDTO> requestHandlerException(RequestException ex) {
    ErrorDTO error = ErrorDTO.builder()
        .code(ex.getCode())
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = BusinessException.class)
  public ResponseEntity<ErrorDTO> businessHandlerException(BusinessException ex) {
    ErrorDTO error = ErrorDTO.builder()
        .code(ex.getCode())
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(error, ex.getStatus());
  }

}
