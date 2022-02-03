package uk.co.mettle.backendtest.model;

public class ErrorWrapper {

    private String errorMessage;

    public ErrorWrapper(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
