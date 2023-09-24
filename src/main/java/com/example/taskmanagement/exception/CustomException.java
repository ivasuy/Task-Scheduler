package com.example.taskmanagement.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{

    private String errorCode;

    public CustomException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
