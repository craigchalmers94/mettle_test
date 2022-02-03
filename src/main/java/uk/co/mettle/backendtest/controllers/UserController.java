package uk.co.mettle.backendtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.mettle.backendtest.services.UserService;

import java.util.Set;

@RestController
@RequestMapping("User")
public class UserController extends ExceptionController {

    @Autowired
    UserService userService;

    // TODO user management API's omitted for time, using data.sql file insted

    // normally would get user ID from request but no security configured yet
    @GetMapping("/Features/{userId}")
    public ResponseEntity<Set<String>> getAvailableFeatures(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getAvailableFeaturesForUser(userId), HttpStatus.OK);
    }

}
