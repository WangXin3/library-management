package com.wxx.library.management.config;

import com.wxx.library.management.util.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LMException.class)
    public RespBean exceptionHandler(HttpServletRequest req, LMException e) {
        return RespBean.error(e.getMsg());
    }
}