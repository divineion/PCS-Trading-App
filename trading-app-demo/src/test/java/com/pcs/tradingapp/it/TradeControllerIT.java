package com.pcs.tradingapp.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

import com.pcs.tradingapp.config.DevSecurityConfiguration;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Import(DevSecurityConfiguration.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=test")
public class TradeControllerIT {
		@Autowired
		private MockMvc mockMvc;
		
		@Test
		public void testIndex_shouldReturnListView() throws Exception {
			mockMvc.perform(get("/trade/list"))
				.andExpect(view().name("trade/list"))
				.andExpect(model().attributeExists("trades"))
				.andExpect(status().is2xxSuccessful());
		}
		
		@Test
	    public void testShowAddForm_shouldReturnAddFormView() throws Exception {
	    	mockMvc.perform(get("/trade/add"))
	    		.andExpect(view().name("trade/add"))
	    		.andExpect(model().attributeExists("trade"))
	    		.andExpect(status().is2xxSuccessful());
	    }
		
		@Test
	    public void testAddTrade_withValidData_shouldRedirectToList() throws Exception {
	        mockMvc.perform(post("/trade/add")
	                        .param("account", "AnyACC")
	                        .param("type", "sell")
	        				.param("buyQuantity", "10"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"));
		}
		
	    @Test
	    public void testAddTrade_withBlankRequiredField_shouldReturnAddView() throws Exception {
	        mockMvc.perform(post("/trade/add")
	        		 .param("account", "")
                     .param("type", "Sell")
 					 .param("buyQuantity", "10")	
				)
	            .andExpect(status().isOk())
	            .andExpect(model().attributeHasFieldErrors("trade", "account"))
	            .andExpect(view().name("trade/add"));
	    }
	    
	    @Test
	    public void testAddTrade_withInvalidData_shouldReturnAddView() throws Exception {
	    	mockMvc.perform(post("/trade/add")
	    			.param("account", "anyACC")
	    			.param("type", "sell")
	    			.param("buyQuantity", "five thousand")	
	    			)
	    	.andDo(print())
	    	.andExpect(status().isOk())
	    	.andExpect(model().attributeHasFieldErrors("trade", "buyQuantity"))
	    	.andExpect(view().name("trade/add"));
	    }
	    
	    @Test
	    public void testShowUpdateForm_withExistingId_shouldReturnUpdateView() throws Exception {
	        mockMvc.perform(get("/trade/update/1"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("trade/update"))
	                .andExpect(model().attributeExists("trade"));
	    }

	    @Test
	    public void testShowUpdateForm_withUnknownId_shouldRedirectToListWithError() throws Exception {
	        mockMvc.perform(get("/trade/update/123"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"))
	                .andExpect(flash().attributeExists("errorMsg"));
	    }

	    @Test
	    public void testUpdateTrade_withValidData_shouldRedirectToList() throws Exception {
	        mockMvc.perform(post("/trade/update/1")
	        		.param("account", "anyACC")
	    			.param("type", "sell")
	    			.param("buyQuantity", "80"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"));
	    }

	    @Test
	    public void testUpdateTrade_withInValidData_shouldReturnUpdateViewWithError() throws Exception {
	    	mockMvc.perform(post("/trade/update/1")
	    			.param("account", "anyACC")
	    			.param("type", "sell")
	    			.param("buyQuantity", "10.4585658"))
	    	.andExpect(status().isOk())
	    	.andExpect(view().name("trade/update"))
	    	.andExpectAll(model().attributeHasFieldErrors("trade", "buyQuantity"));
	    }
	    
	    @Test
	    public void testDeleteTrade_shouldRedirectToList() throws Exception {
	        mockMvc.perform(get("/trade/delete/1"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"));
	    }
	    
	    @Test
	    public void testDeleteTrade_shouldThrowException() throws Exception {
	        mockMvc.perform(get("/trade/delete/321"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/trade/list"))
	                .andExpect(flash().attributeExists("errorMsg"));
	    }
   }