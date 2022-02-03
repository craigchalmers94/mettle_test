package uk.co.mettle.backendtest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.mettle.backendtest.model.Feature;
import uk.co.mettle.backendtest.services.FeatureService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FeatureControllerTest {

    @Mock
    FeatureService featureService;

    @InjectMocks
    FeatureController featureController;

    @Autowired
    MockMvc mockMvc;

    Feature feature;

    @BeforeEach
    public void setup(){
        feature = new Feature();
        feature.setEnabled("Y");
        feature.setGlobal("Y");
        feature.setName("Hello World");
        mockMvc = MockMvcBuilders.standaloneSetup(featureController).build();
    }

    @Test
    public void add_new_feature_successfully() throws Exception {
        when(featureService.addNewFeature(any(), any())).thenReturn(feature);
        mockMvc.perform(post("/Feature/NewFeature").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(feature))).
                andExpect(status().isCreated());
        verify(featureService).addNewFeature(any(),any());
    }

    @Test
    public void GetMappingOfAllProduct() throws Exception {
        List<Feature> featureList = new ArrayList<>();
        featureList.add(feature);

        when(featureService.getAllFeatures(any())).thenReturn(featureList);
        mockMvc.perform(MockMvcRequestBuilders.get("/Feature/FindAll").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(feature))).
                    andExpect(MockMvcResultMatchers.status().isOk()).
                    andDo(MockMvcResultHandlers.print());
        verify(featureService).getAllFeatures(any());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
