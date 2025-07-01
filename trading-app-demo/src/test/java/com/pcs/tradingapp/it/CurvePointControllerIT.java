package com.pcs.tradingapp.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.pcs.tradingapp.config.DevSecurityConfiguration;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
@Import(DevSecurityConfiguration.class)
public class CurvePointControllerIT {
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndex_shouldReturnCurvePointView() throws Exception {
    	mockMvc.perform(get("/curvepoint/list"))
    	.andExpect(status().is2xxSuccessful())
    	.andExpect(model().attributeExists("curvePoints"))
    	.andExpect(view().name("curvePoint/list"));
    }
}
