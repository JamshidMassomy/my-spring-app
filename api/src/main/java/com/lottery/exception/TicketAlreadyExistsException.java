package com.lottery.exception;

public final class TicketAlreadyExistsException extends RuntimeException {

    public TicketAlreadyExistsException(String message) {
        super(message);
    }
}
