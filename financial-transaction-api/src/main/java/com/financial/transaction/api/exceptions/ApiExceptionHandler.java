package com.financial.transaction.api.exceptions;

import com.financial.transaction.api.enums.Status;
import com.financial.transaction.api.utils.Result;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * Exception Handler
 */
@RestControllerAdvice
@ResponseBody
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result<Object> exceptionHandler(ServiceException e, HandlerMethod hm) {
        log.error("{} Meet a ServiceException: {}", hm.getShortLogMessage(), e.getMessage());
        return new Result<>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Result<Object> exceptionHandler(Throwable e, HandlerMethod hm) {
        ApiException ce = hm.getMethodAnnotation(ApiException.class);
        log.error("Meet en unknown exception: ", e);
        if (ce == null) {
            return Result.errorWithArgs(Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        Status st = ce.value();
        return Result.error(st);
    }

}
