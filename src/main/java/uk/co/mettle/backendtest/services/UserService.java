package uk.co.mettle.backendtest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.mettle.backendtest.dao.FeatureRepository;
import uk.co.mettle.backendtest.dao.UserFeatureRepository;
import uk.co.mettle.backendtest.model.Feature;
import uk.co.mettle.backendtest.model.UserFeature;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    FeatureRepository featureRepository;

    @Autowired
    UserFeatureRepository userFeatureRepository;

    public Set<String> getAvailableFeaturesForUser(int userId) {

        // get all global enabled features
        List<Feature> allFeatures = (List<Feature>) featureRepository.findAll();

        Predicate<Feature> enabledAndGlobalFilter = feature -> feature.getEnabled().equals("Y") && feature.getGlobal().equals("Y");

        Set<String> availableFeatures = allFeatures.stream()
                .filter(enabledAndGlobalFilter)
                .map(Feature::getName)
                .collect(Collectors.toSet());

        // get all features for user
        List<UserFeature> userFeatures = userFeatureRepository.findByUserId(Long.valueOf(userId));

        for (UserFeature userFeature : userFeatures) {
            for (Feature feature : allFeatures) {
                if (feature.getId() == userFeature.getFeatureId() && feature.getEnabled().equals("Y")) {
                    availableFeatures.add(feature.getName());
                }
            }
        }

        return availableFeatures;
    }
}
