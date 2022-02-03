package uk.co.mettle.backendtest.util;

public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long userId) {
        super("Unable to find existing user with ID : " + userId);
    }
}
