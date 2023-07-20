package com.lottery.exception;


import org.springframework.stereotype.Component;


public class TicketLimitException extends RuntimeException {

    public TicketLimitException(String message) {
        super(message);
    }

    public TicketLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
