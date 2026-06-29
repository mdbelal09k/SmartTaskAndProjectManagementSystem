package com.SmartTaskAndProjectManagementSystem.exception;

//exception/GlobalExceptionHandler.java


import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.SmartTaskAndProjectManagementSystem.Util.ApiResonse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(ResourceNotFoundException.class)
 public ResponseEntity<ApiResonse<Void>> handleNotFound(ResourceNotFoundException ex) {
     return ResponseEntity.status(HttpStatus.NOT_FOUND)
             .body(ApiResonse.error(ex.getMessage()));
 }

 @ExceptionHandler(DuplicateResourceException.class)
 public ResponseEntity<ApiResonse<Void>> handleDuplicate(DuplicateResourceException ex) {
     return ResponseEntity.status(HttpStatus.CONFLICT)
             .body(ApiResonse.error(ex.getMessage()));
 }

 @ExceptionHandler(BadRequestException.class)
 public ResponseEntity<ApiResonse<Void>> handleBadRequest(BadRequestException ex) {
     return ResponseEntity.status(HttpStatus.BAD_REQUEST)
             .body(ApiResonse.error(ex.getMessage()));
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<ApiResonse<Map<String, String>>> handleValidation(
         MethodArgumentNotValidException ex) {
     Map<String, String> errors = new HashMap<>();
     ex.getBindingResult().getFieldErrors()
             .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
     return ResponseEntity.status(HttpStatus.BAD_REQUEST)
             .body(ApiResonse.<Map<String, String>>builder()
                     .success(false).message("Validation failed").data(errors).build());
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<ApiResonse<Void>> handleGeneric(Exception ex) {
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
             .body(ApiResonse.error("Something went wrong: " + ex.getMessage()));
 }
}