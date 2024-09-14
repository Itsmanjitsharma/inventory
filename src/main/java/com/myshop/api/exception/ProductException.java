package com.myshop.api.exception;


public class ProductException extends RuntimeException{
    private final String errorCode;
    private final String errorMessage;

    public ProductException(String errorCode,String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getErrorCode() {
        return errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
