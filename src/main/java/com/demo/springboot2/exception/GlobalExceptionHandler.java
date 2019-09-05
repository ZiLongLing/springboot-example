package com.demo.springboot2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ErrorResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        BindingResult result = null;
        if (e instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            result = ((BindException) e).getBindingResult();
        } else if (e instanceof ConstraintViolationException) {
//            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
//            StringBuilder errorMsg = new StringBuilder();
//            for (ConstraintViolation<?> violation : constraintViolations) {
//                String felid = violation.getPropertyPath().toString();
//                String substring = felid.substring(felid.indexOf(".") + 1, felid.length());
//                errorMsg.append(substring + "：" + violation.getMessage()).append(",");
//            }
//            errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
            return new ErrorResponseEntity(400, ((ConstraintViolationException) e).getConstraintViolations().iterator().next().getMessage());
        }
        return new ErrorResponseEntity(400, result.getFieldError().getField() + "：" + result.getFieldError().getDefaultMessage());
    }
}
