package com.sheep.sh.myblog.handler;

import com.sheep.sh.myblog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public ResponseDto<String> handleArgumentException(Exception e) {
        return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
