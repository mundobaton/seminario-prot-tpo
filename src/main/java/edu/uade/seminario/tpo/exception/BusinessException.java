package edu.uade.seminario.tpo.exception;

public class BusinessException extends Exception {

    private int status;

    public BusinessException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BusinessException(String message) {
        this(message, 500);
    }

    public int getStatus() {
        return status;
    }
}
