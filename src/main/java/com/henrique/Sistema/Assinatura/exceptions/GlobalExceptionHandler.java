package com.henrique.Sistema.Assinatura.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    //    ============================ HTTP NOT_FOUND { -404 } ========================================

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundHandle(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage()));
    }

    //    ============================ HTTP BAD_REQUEST { -400 } ========================================

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestHandle (BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, ex.getMessage()));
    }

    //    ============================ HTTP CONFLICT { -409 } ========================================

    @ExceptionHandler(ConflictRequestException.class)
    public ResponseEntity<ErrorResponse> conflictRequestHandle(ConflictRequestException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, ex.getMessage()));
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ErrorResponse> handleNoContent (NoContentException ex){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ErrorResponse(204, ex.getMessage()));
    }
}
