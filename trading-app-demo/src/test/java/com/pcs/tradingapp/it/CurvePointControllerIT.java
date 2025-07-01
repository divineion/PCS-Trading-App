package com.pcs.tradingapp.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
    
    @Test
    public void testShowAddForm_shouldReturnAddFormView() throws Exception {
    	mockMvc.perform(get("/curvepoint/add"))
    		.andExpect(view().name("curvePoint/add"))
    		.andExpect(model().attributeExists("curvePoint"))
    		.andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void testAddCurvePoint_withValidData_ShouldRedirectToListView() throws Exception {
    	mockMvc.perform(post("/curvepoint/add")
    			.param("curveId", "1")
    			.param("term", "12.2")
    			.param("value", "1.4")
			)
    		
    		.andExpect(status().is3xxRedirection())
    		.andExpect(redirectedUrl("/curvepoint/list"));
    }
    
    @Test
    public void testAddCurvePoint_withInvalidValues_ShouldReturnError() throws Exception {
    	mockMvc.perform(post("/curvepoint/add")
    			.param("curveId", "1.2")
    			.param("term", "12.2")
    			.param("value", "1.4")
    			)

    	.andExpect(status().isOk())
    	.andExpect(model().attributeHasFieldErrors("curvePoint", "curveId"))
    	.andExpect(view().name("curvePoint/add"));
    }
    
    @Test
    public void testShowUpdateForm_withExistingId_shouldReturnUpdateView() throws Exception {
        mockMvc.perform(get("/curvepoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    public void testShowUpdateForm_withUnknownId_shouldRedirectToListWithError() throws Exception {
        mockMvc.perform(get("/curvepoint/update/777"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvepoint/list"))
                .andExpect(flash().attributeExists("errorMsg"));
    }

}
