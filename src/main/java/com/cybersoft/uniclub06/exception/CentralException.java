package com.cybersoft.uniclub06.exception;

import com.cybersoft.uniclub06.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralException {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(500);
        baseResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @ExceptionHandler({AuthenException.class})
    public ResponseEntity<?> handleException1(Exception e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
