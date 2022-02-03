package uk.co.mettle.backendtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.mettle.backendtest.model.Feature;
import uk.co.mettle.backendtest.services.FeatureService;
import uk.co.mettle.backendtest.util.UnauthorisedActionException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("Feature")
public class FeatureController extends ExceptionController {

    @Autowired
    FeatureService featureService;

    @GetMapping("/FindAll")
    public ResponseEntity<List<Feature>> getAllFeatures(HttpServletRequest request) throws UnauthorisedActionException {
        return new ResponseEntity<>(featureService.getAllFeatures(request), HttpStatus.OK);
    }

    @GetMapping("/Find/{featureId}")
    public ResponseEntity<Feature> getFeature(@PathVariable int featureId, HttpServletRequest request) throws Exception {
        return new ResponseEntity<>(featureService.getFeature(featureId, request), HttpStatus.OK);
    }

    @PostMapping("NewFeature")
    public ResponseEntity<Feature> addNewFeature(@RequestBody Feature feature, HttpServletRequest request) throws Exception {
        return new ResponseEntity<>(featureService.addNewFeature(feature, request), HttpStatus.CREATED);
    }

    @PutMapping("UpdateFeature")
    public ResponseEntity<Feature> updateFeature(@RequestBody Feature feature, HttpServletRequest request) throws Exception {
        return new ResponseEntity<>(featureService.updateFeature(feature, request), HttpStatus.OK);
    }

    // TODO Delete API

}
