package uk.co.mettle.backendtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.mettle.backendtest.model.UserFeature;
import uk.co.mettle.backendtest.services.UserFeatureService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("UserFeature")
public class UserFeatureController extends ExceptionController {

    @Autowired
    UserFeatureService userFeatureService;

    @PostMapping("/AddFeature")
    public ResponseEntity<UserFeature> addFeature(@RequestBody UserFeature userFeature, HttpServletRequest request) throws Exception {
        return new ResponseEntity<>(userFeatureService.addFeature(userFeature, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/RemoveFeature")
    public ResponseEntity<String> deleteFeature(@RequestBody UserFeature userFeature, HttpServletRequest request) throws Exception {
        return new ResponseEntity<>(userFeatureService.deleteFeature(userFeature, request), HttpStatus.OK);
    }

}
