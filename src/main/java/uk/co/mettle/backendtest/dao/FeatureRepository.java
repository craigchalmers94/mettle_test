package uk.co.mettle.backendtest.dao;

import org.springframework.data.repository.CrudRepository;
import uk.co.mettle.backendtest.model.Feature;

public interface FeatureRepository extends CrudRepository<Feature, Long> {

}
