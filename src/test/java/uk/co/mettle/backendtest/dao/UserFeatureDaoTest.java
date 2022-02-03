package uk.co.mettle.backendtest.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.mettle.backendtest.model.UserFeature;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserFeatureDaoTest {

    @Autowired
    UserFeatureRepository userFeatureRepository;

    UserFeature userFeature;

    @BeforeEach
    public void setUp() {
        userFeature = new UserFeature();
        userFeature.setFeatureId(1);
        userFeature.setUserId(1);
    }

    @AfterEach
    public void tearDown() {
        userFeatureRepository.deleteAll();
        userFeature = null;
    }

    @Test
    public void should_save_user_feature_and_return_it() {
        userFeature = userFeatureRepository.save(userFeature);
        UserFeature foundFeature = userFeatureRepository.findById(userFeature.getId()).get();
        assertEquals(1, foundFeature.getUserId());
    }

    @Test
    public void should_delete_the_user_feature() {
        userFeature = userFeatureRepository.save(userFeature);
        userFeatureRepository.deleteById(userFeature.getId());
        Optional optional = userFeatureRepository.findById(userFeature.getId());
        assertEquals(Optional.empty(), optional);
    }

}
