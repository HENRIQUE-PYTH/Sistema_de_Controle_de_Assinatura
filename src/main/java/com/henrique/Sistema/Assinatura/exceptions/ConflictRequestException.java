package com.henrique.Sistema.Assinatura.exceptions;

public class ConflictRequestException extends RuntimeException {
    public ConflictRequestException(String msg){
        super(msg);
    }
}
