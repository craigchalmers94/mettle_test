package uk.co.mettle.backendtest.util;

public class FeatureNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public FeatureNotFoundException(Long featureId) {
        super("Unable to find existing feature with ID : " + featureId);
    }
}
