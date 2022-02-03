package uk.co.mettle.backendtest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.co.mettle.backendtest.dao.FeatureRepository;
import uk.co.mettle.backendtest.model.Feature;
import uk.co.mettle.backendtest.util.FeatureNotFoundException;
import uk.co.mettle.backendtest.util.InvalidInputException;
import uk.co.mettle.backendtest.util.UnauthorisedActionException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class FeatureService {

    @Autowired
    ValidationService validationService;

    @Autowired
    FeatureRepository featureRepository;

    public List<Feature> getAllFeatures(HttpServletRequest request) throws UnauthorisedActionException {
        validationService.actionPermitted(request, "ADMIN");

        return (List<Feature>) featureRepository.findAll();
    }

    public Feature getFeature(int featureId, HttpServletRequest request) throws Exception {
        validationService.actionPermitted(request, "ADMIN");

        return featureRepository.findById(Long.valueOf(featureId))
                .orElseThrow(() -> new FeatureNotFoundException(Long.valueOf(featureId)));
    }

    public Feature addNewFeature(Feature feature, HttpServletRequest request) throws Exception {
        validationService.actionPermitted(request, "ADMIN");

        if (!isValidNewFeature(feature)) {
            throw new InvalidInputException();
        }

        return featureRepository.save(feature);
    }

    public Feature updateFeature(Feature feature, HttpServletRequest request) throws Exception {
        validationService.actionPermitted(request, "ADMIN");

        if (!isValidUpdateFeature(feature)) {
            throw new InvalidInputException();
        }

        if (!featureRepository.existsById(feature.getId())) {
            throw new FeatureNotFoundException(feature.getId());
        }

        return featureRepository.save(feature);
    }

    private boolean isValidNewFeature(Feature feature) {

        if (feature.getId() != 0) {
            return false; // should be 0 for new ones
        }
        if (!StringUtils.hasText(feature.getName())) {
            return false;
        }
        if (!StringUtils.hasText(feature.getEnabled())) {
            feature.setEnabled("N"); // default to disabled if not specified
        }
        if (!validYNField(feature.getEnabled())) {
            return false;
        }
        if (!validYNField(feature.getGlobal())) {
            return false;
        }

        //TODO check for invalid characters, length of any fields
        //TODO prepare input like trim whitespace
        return true;
    }

    private boolean isValidUpdateFeature(Feature feature) {

        if (feature.getId() == 0) {
            return false;
        }
        if (!StringUtils.hasText(feature.getName())) {
            return false;
        }
        if (!validYNField(feature.getEnabled())) {
            return false;
        }
        if (!validYNField(feature.getGlobal())) {
            return false;
        }

        //TODO check for invalid characters, length of any fields
        //TODO prepare input like trim whitespace
        return true;
    }

    private boolean validYNField(String input) {
        if (StringUtils.hasText(input) && (input.equals("Y") || input.equals("N"))) {
            return true;
        } else {
            return false;
        }
    }
}
