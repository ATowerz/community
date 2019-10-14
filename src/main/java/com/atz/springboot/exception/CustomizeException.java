package com.atz.springboot.exception;

/**
 * Created by codedrinker on 2019/5/28.
 */
public class CustomizeException extends RuntimeException {

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    public CustomizeException(CustomizeErrorCode customizeErrorCode) {
        super(customizeErrorCode.getMessage());
    }
}
