package uk.co.mettle.backendtest.util;

public class InvalidInputException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidInputException() {
        super("Error, invalid input supplied ");
    }
}
