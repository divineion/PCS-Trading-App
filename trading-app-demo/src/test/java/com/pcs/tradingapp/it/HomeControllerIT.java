package com.pcs.tradingapp.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class HomeControllerIT {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test 
    @WithMockUser(roles = "ADMIN")
    public void testAdminAccess_withAdminRole_shouldRedirectToView() throws Exception {
    	mockMvc.perform(get("/admin/home"))
    	.andExpect(status().is3xxRedirection())
    	.andExpect(redirectedUrl("/bidlist/list"));
    }	
	
	@Test 
    @WithMockUser
    public void testAdminAccess_withUserRole_shouldReturnClientError() throws Exception {
    	mockMvc.perform(get("/admin/home"))
    	.andExpect(status().is4xxClientError());
    }

	@Test 
	@WithMockUser
	public void testHome_shouldReturnHomeView() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("home"));
	}
}
