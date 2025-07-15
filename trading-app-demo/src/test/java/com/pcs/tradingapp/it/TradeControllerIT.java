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
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=test")
public class TradeControllerIT {
		@Autowired
		private MockMvc mockMvc;
		
		@Test
		@WithMockUser
		public void testIndex_shouldReturnListView() throws Exception {
			mockMvc.perform(get("/trade/list"))
				.andExpect(view().name("trade/list"))
				.andExpect(model().attributeExists("trades"))
				.andExpect(status().is2xxSuccessful());
		}
		
		@Test
		@WithMockUser
	    public void testShowAddForm_shouldReturnAddFormView() throws Exception {
	    	mockMvc.perform(get("/trade/add"))
	    		.andExpect(view().name("trade/add"))
	    		.andExpect(model().attributeExists("trade"))
	    		.andExpect(status().is2xxSuccessful());
	    }
		
		@Test
		@WithMockUser
	    public void testAddTrade_withValidData_shouldRedirectToList() throws Exception {
	        mockMvc.perform(post("/trade/add")
	                        .param("account", "AnyACC")
	                        .param("type", "sell")
	        				.param("buyQuantity", "10")
	        				.with(csrf())		
	        			)
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"));
		}
		
	    @Test
	    @WithMockUser
	    public void testAddTrade_withBlankRequiredField_shouldReturnAddView() throws Exception {
	        mockMvc.perform(post("/trade/add")
	        		 .param("account", "")
                     .param("type", "Sell")
 					 .param("buyQuantity", "10")	
 					.with(csrf())
				)
	            .andExpect(status().isOk())
	            .andExpect(model().attributeHasFieldErrors("trade", "account"))
	            .andExpect(view().name("trade/add"));
	    }
	    
	    @Test
	    @WithMockUser
	    public void testAddTrade_withInvalidData_shouldReturnAddView() throws Exception {
	    	mockMvc.perform(post("/trade/add")
	    			.param("account", "anyACC")
	    			.param("type", "sell")
	    			.param("buyQuantity", "five thousand")	
	    			.with(csrf())	
    			)
	    	.andExpect(status().isOk())
	    	.andExpect(model().attributeHasFieldErrors("trade", "buyQuantity"))
	    	.andExpect(view().name("trade/add"));
	    }
	    
	    @Test
	    @WithMockUser
	    public void testShowUpdateForm_withExistingId_shouldReturnUpdateView() throws Exception {
	        mockMvc.perform(get("/trade/update/1"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("trade/update"))
	                .andExpect(model().attributeExists("trade"));
	    }

	    @Test
	    @WithMockUser
	    public void testShowUpdateForm_withUnknownId_shouldRedirectToListWithError() throws Exception {
	        mockMvc.perform(get("/trade/update/123"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"))
	                .andExpect(flash().attributeExists("errorMsg"));
	    }

	    @Test
	    @WithMockUser
	    public void testUpdateTrade_withValidData_shouldRedirectToList() throws Exception {
	        mockMvc.perform(post("/trade/update/1")
	        		.param("account", "anyACC")
	    			.param("type", "sell")
	    			.param("buyQuantity", "80")
	    			.with(csrf())
        		)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
	    }

	    @Test
	    @WithMockUser
	    public void testUpdateTrade_withUnknownId_shouldRedirectToList() throws Exception {
	    	mockMvc.perform(post("/trade/update/178")
	    			.param("account", "anyACC")
	    			.param("type", "sell")
	    			.param("buyQuantity", "80")
	    			.with(csrf())
    			)
	    	.andExpect(status().is3xxRedirection())
	    	.andExpect(flash().attributeExists("errorMsg"))
	    	.andExpect(redirectedUrl("/trade/list"));
	    }

	    @Test
	    @WithMockUser
	    public void testUpdateTrade_withInValidData_shouldReturnUpdateViewWithError() throws Exception {
	    	mockMvc.perform(post("/trade/update/1")
	    			.param("account", "anyACC")
	    			.param("type", "sell")
	    			.param("buyQuantity", "10.4585658")
	    			.with(csrf())
			)
	    	.andExpect(status().isOk())
	    	.andExpect(view().name("trade/update"))
	    	.andExpectAll(model().attributeHasFieldErrors("trade", "buyQuantity"));
	    }
	    
	    @Test
	    @WithMockUser
	    public void testDeleteTrade_shouldRedirectToList() throws Exception {
	        mockMvc.perform(get("/trade/delete/1"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"));
	    }
	    
	    @Test
	    @WithMockUser
	    public void testDeleteTrade_shouldThrowException() throws Exception {
	        mockMvc.perform(get("/trade/delete/321"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"))
	                .andExpect(flash().attributeExists("errorMsg"));
	    }
   }