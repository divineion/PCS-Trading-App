package com.pcs.tradingapp.it;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class CurvePointControllerIT {
	@Autowired
    private MockMvc mockMvc;

    @Test 
    @WithMockUser
    public void testIndex_shouldReturnCurvePointView() throws Exception {
    	mockMvc.perform(get("/curvepoint/list"))
    	.andExpect(status().is2xxSuccessful())
    	.andExpect(model().attributeExists("curvePoints"))
    	.andExpect(view().name("curvePoint/list"));
    }
    
    @Test 
    @WithMockUser
    public void testShowAddForm_shouldReturnAddFormView() throws Exception {
    	mockMvc.perform(get("/curvepoint/add"))
    		.andExpect(view().name("curvePoint/add"))
    		.andExpect(model().attributeExists("curvePoint"))
    		.andExpect(status().is2xxSuccessful());
    }
    
    @Test 
    @WithMockUser
    public void testAddCurvePoint_withValidData_ShouldRedirectToListView() throws Exception {
    	mockMvc.perform(post("/curvepoint/add")
    			.param("curveId", "1")
    			.param("term", "12.2")
    			.param("value", "1.4")
    			.with(csrf())
			)
    		
    		.andExpect(status().is3xxRedirection())
    		.andExpect(redirectedUrl("/curvepoint/list"));
    }
    
    @Test 
    @WithMockUser
    public void testAddCurvePoint_withInvalidValues_ShouldReturnError() throws Exception {
    	mockMvc.perform(post("/curvepoint/add")
    			.param("curveId", "1.2")
    			.param("term", "12.2")
    			.param("value", "1.4")
    			.with(csrf())
    			)

    	.andExpect(status().isOk())
    	.andExpect(model().attributeHasFieldErrors("curvePoint", "curveId"))
    	.andExpect(view().name("curvePoint/add"));
    }
    
    @Test 
    @WithMockUser
    public void testShowUpdateForm_withExistingId_shouldReturnUpdateView() throws Exception {
        mockMvc.perform(get("/curvepoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test 
    @WithMockUser
    public void testShowUpdateForm_withUnknownId_shouldRedirectToListWithError() throws Exception {
        mockMvc.perform(get("/curvepoint/update/777"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvepoint/list"))
                .andExpect(flash().attributeExists("errorMsg"));
    }
    
    @Test 
    @WithMockUser
    public void testUpdateCurvePoint_withValidData_ShouldRedirectToListView() throws Exception {
    	mockMvc.perform(post("/curvepoint/update/1")
    			.param("curveId", "54")
    			.param("term", "15")
    			.param("value", "1")
    			.with(csrf())
			)
    		
    		.andExpect(status().is3xxRedirection())
    		.andExpect(redirectedUrl("/curvepoint/list"));
    }
    
    @Test 
    @WithMockUser
    public void testUpdateCurvePoint_withInvalidValues_ShouldReturnError() throws Exception {
    	mockMvc.perform(post("/curvepoint/update/1")
    			.param("curveId", "1.2")
    			.param("term", "12.2")
    			.param("value", "1.4")
    			.with(csrf())
    			)

    	.andExpect(status().isOk())
    	.andExpect(model().attributeHasFieldErrors("curvePoint", "curveId"))
    	.andExpect(view().name("/curvePoint/update"));
    }
    
    @Test 
    @WithMockUser
    public void testDeleteCurvePoint_shouldRedirectToList() throws Exception {
        mockMvc.perform(get("/curvepoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvepoint/list"));
    }
    
    @Test 
    @WithMockUser
    public void testDeleteCurvePoint_shouldReturnError() throws Exception {
        mockMvc.perform(get("/curvepoint/delete/78568"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvepoint/list"))
                .andExpect(flash().attributeExists("errorMsg"));
    }
}
