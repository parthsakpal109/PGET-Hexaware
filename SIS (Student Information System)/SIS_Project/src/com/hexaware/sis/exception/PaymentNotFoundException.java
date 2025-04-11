package com.hexaware.sis.exception;

public class PaymentNotFoundException extends RuntimeException 
{
    public PaymentNotFoundException(String message) 
    {
        super(message);
    }
}
