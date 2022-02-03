package uk.co.mettle.backendtest.util;

public class UnauthorisedActionException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnauthorisedActionException(String message) {
        super("Unable to complete action, this requires the role of : " + message);
    }
}
