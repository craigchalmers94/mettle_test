package uk.co.mettle.backendtest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.mettle.backendtest.dao.FeatureRepository;
import uk.co.mettle.backendtest.model.Feature;
import uk.co.mettle.backendtest.util.InvalidInputException;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeatureServiceTest {

    @Mock
    FeatureRepository featureRepository;

    @Mock
    ValidationService validationService;

    @InjectMocks
    FeatureService featureService;

    Feature feature;
    HttpServletRequest mockedRequest;

    @BeforeEach
    public void setup() {
        feature = new Feature();
        feature.setEnabled("Y");
        feature.setGlobal("Y");
        feature.setName("Hello World");

        mockedRequest = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    public void should_add_new_feature_successfully() throws Exception {
        when(featureRepository.save(any())).thenReturn(feature);
        featureService.addNewFeature(feature, mockedRequest);
        verify(featureRepository).save(feature);
    }

    @Test
    public void should_fail_to_add_new_feature() {

        feature.setId(5); // not allowed an id when adding new

        assertThrows(InvalidInputException.class,
                ()->{
                    featureService.addNewFeature(feature, mockedRequest);
                });
    }

    @Test
    public void update_feature_successfully() throws Exception {

        Feature updatedFeature = new Feature();
        updatedFeature.setId(1);
        updatedFeature.setEnabled("Y");
        updatedFeature.setGlobal("Y");
        updatedFeature.setName("Hello World Again");

        given(featureRepository.existsById(anyLong())).willReturn(true);
        featureService.updateFeature(updatedFeature, mockedRequest);

        verify(featureRepository).save(updatedFeature);
        verify(featureRepository).existsById(any());

    }

}