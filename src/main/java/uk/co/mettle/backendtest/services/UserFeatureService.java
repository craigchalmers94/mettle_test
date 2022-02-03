package uk.co.mettle.backendtest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.mettle.backendtest.dao.FeatureRepository;
import uk.co.mettle.backendtest.dao.UserFeatureRepository;
import uk.co.mettle.backendtest.dao.UserRepository;
import uk.co.mettle.backendtest.model.UserFeature;
import uk.co.mettle.backendtest.util.*;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserFeatureService {

    @Autowired
    ValidationService validationService;

    @Autowired
    FeatureRepository featureRepository;

    @Autowired
    UserFeatureRepository userFeatureRepository;

    @Autowired
    UserRepository userRepository;

    public UserFeature addFeature(UserFeature userFeature, HttpServletRequest request) throws Exception {
        validationService.actionPermitted(request, "ADMIN");

        if (!userRepository.existsById(userFeature.getUserId())) {
            throw new UserNotFoundException(userFeature.getUserId());
        }
        if (!featureRepository.existsById(userFeature.getFeatureId())) {
            throw new FeatureNotFoundException(userFeature.getFeatureId());
        }

        // TODO check if feature and user already exists in user_feature table

        return userFeatureRepository.save(userFeature);
    }

    public String deleteFeature(UserFeature userFeature, HttpServletRequest request) throws Exception {
        validationService.actionPermitted(request, "ADMIN");

        if (!userFeatureRepository.existsById(userFeature.getId())) {
            throw new UserFeatureNotFoundException(userFeature.getId());
        }

        userFeatureRepository.deleteById(userFeature.getId());
        return "Feature successfully removed from user";
    }
}
