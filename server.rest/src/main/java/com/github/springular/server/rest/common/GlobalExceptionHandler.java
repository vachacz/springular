package com.github.springular.server.rest.common;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.springular.server.exception.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(BusinessException e) {
        return new ErrorResponse(e.getMessages());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return processFieldErrors(result.getFieldErrors());
    }

    private ErrorResponse processFieldErrors(List<FieldError> fieldErrors) {
    	ErrorResponse response = new ErrorResponse();
        for (FieldError fieldError: fieldErrors) {
        	response.addMessage(fieldError.getDefaultMessage());
        }
        return response;
    }
    
}
