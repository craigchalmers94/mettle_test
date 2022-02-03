package uk.co.mettle.backendtest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.mettle.backendtest.model.ErrorWrapper;
import uk.co.mettle.backendtest.util.*;

public class ExceptionController {

    @ExceptionHandler({ FeatureNotFoundException.class, InvalidInputException.class,
            UserFeatureNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorWrapper> inputException(Exception e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( UnauthorisedActionException.class)
    public ResponseEntity<ErrorWrapper> permissionsException(UnauthorisedActionException e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler( Exception.class)
    public ResponseEntity<ErrorWrapper> generalException(Exception e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
