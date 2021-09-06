package com.demo.shortUrl.exception;

import com.demo.shortUrl.model.ResponseMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) throws Exception {
        log.error("Generic ERROR: " + ex.getMessage());
        return new ResponseEntity<>((ResponseMetaData.builder().code(500l).message("Something went wrong")).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
