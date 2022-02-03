package uk.co.mettle.backendtest.dao;

import org.springframework.data.repository.CrudRepository;
import uk.co.mettle.backendtest.model.Feature;
import uk.co.mettle.backendtest.model.UserFeature;

import java.util.List;

public interface UserFeatureRepository extends CrudRepository<UserFeature, Long> {

    List<UserFeature> findByUserId(Long userId);
}
