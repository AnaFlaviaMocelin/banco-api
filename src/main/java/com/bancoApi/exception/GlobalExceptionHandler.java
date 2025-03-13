package com.bancoApi.exception;

import com.bancoApi.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler (ContaJaExisteException.class)
  public ResponseEntity<ErrorResponse> handleContaJaExisteException (ContaJaExisteException ex) {
    ErrorResponse errorResponse = new ErrorResponse (ex.getMessage ());
    return new ResponseEntity<> (errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
  public ResponseEntity<java.util.Map<String, String>> handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
    org.springframework.validation.BindingResult bindingResult = ex.getBindingResult();
    java.util.Map<String, String> errorMap = new java.util.HashMap<> ();

    for (org.springframework.validation.FieldError fieldError : bindingResult.getFieldErrors()) {
      errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
  }
}