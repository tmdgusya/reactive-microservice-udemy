package com.roach.webfluxdemo.exception;

public class InputValidationException extends RuntimeException {

    private static final String MSG = "allowed rang is 10 - 20";
    private static final int errorCdeo = 100;
    private final int input;

    public InputValidationException(int input) {
        super(MSG);
        this.input = input;
    }

    public int getErrorCdeo() {
        return errorCdeo;
    }

    public int getInput() {
        return input;
    }
}
