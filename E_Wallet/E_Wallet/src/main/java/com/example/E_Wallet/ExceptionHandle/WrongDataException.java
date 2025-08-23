package com.example.E_Wallet.ExceptionHandle;

public class WrongDataException extends RuntimeException{
    public WrongDataException(String message){
        super(message);
    }
}
