package com.henrique.Sistema.Assinatura.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
