package com.megarepuestos.megarepuestos.exception;


import com.megarepuestos.megarepuestos.exception.custom.BadRequestException;
import com.megarepuestos.megarepuestos.exception.custom.UnauthorizeException;
import com.megarepuestos.megarepuestos.exception.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex){
        ApiError apiError = new ApiError(ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> internalServerError(Exception exception) {
        ApiError apiError = new ApiError(exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> unauthorized(UnauthorizeException exception) {
        ApiError apiError = new ApiError(exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

}
