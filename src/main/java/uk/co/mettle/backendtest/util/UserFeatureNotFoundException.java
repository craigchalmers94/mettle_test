package uk.co.mettle.backendtest.util;

public class UserFeatureNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserFeatureNotFoundException(Long userFeatureId) {
        super("Unable to find existing user feature with ID : " + userFeatureId);
    }
}
