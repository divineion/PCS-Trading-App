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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RatingControllerIT {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser
	public void testIndex_shouldReturnListView() throws Exception {
		mockMvc.perform(get("/rating/list"))
			.andExpect(view().name("rating/list"))
			.andExpect(model().attributeExists("ratings"))
			.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	@WithMockUser
    public void testShowAddForm_shouldReturnAddFormView() throws Exception {
    	mockMvc.perform(get("/rating/add"))
    		.andExpect(view().name("rating/add"))
    		.andExpect(model().attributeExists("rating"))
    		.andExpect(status().is2xxSuccessful());
    }
	
	@Test
	@WithMockUser
    public void testAddRating_withValidData_shouldRedirectToList() throws Exception {
        mockMvc.perform(post("/rating/add")
                        .param("moodysRating", "Aaa")
                        .param("sandPRating", "AAA")
                        .param("fitchRating", "BBB")
        				.param("order", "8")
        				.with(csrf())
        		)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }
	
	@Test
	@WithMockUser
    public void testAddRating_withExistingOrderNumber_shouldReturnAddView() throws Exception {
        mockMvc.perform(post("/rating/add")
                        .param("moodysRating", "Aaa")
                        .param("sandPRating", "AAA")
                        .param("fitchRating", "BBB")
        				.param("order", "1")
        				.with(csrf())
        		)
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    @WithMockUser
    public void testAddRating_withInvalidData_shouldReturnAddView() throws Exception {
        mockMvc.perform(post("/rating/add")
        		.param("moodysRating", "123")
                .param("sandPRating", "AAAAA")
                .param("fitchRating", "BABA")
				.param("order", "1")
				.with(csrf())
			)
            .andExpect(status().isOk())
            .andExpect(view().name("rating/add"));
    }

    @Test
    @WithMockUser
    public void testShowUpdateForm_withExistingId_shouldReturnUpdateView() throws Exception {
        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    @WithMockUser
    public void testShowUpdateForm_withUnknownId_shouldRedirectToListWithError() throws Exception {
        mockMvc.perform(get("/rating/update/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(flash().attributeExists("errorMsg"));
    }

    @Test
    @WithMockUser
    public void testUpdateRating_withValidData_shouldRedirectToList() throws Exception {
        mockMvc.perform(post("/rating/update/1")
		                .param("moodysRating", "Aaa")
		                .param("sandPRating", "AAA")
		                .param("fitchRating", "BBB")
						.param("order", "8")
						.with(csrf())		
        		)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    @WithMockUser
    public void testUpdateRating_withBlankOrder_shouldReturnUpdateView() throws Exception {
        mockMvc.perform(post("/rating/update/1")
	        		.param("moodysRating", "Aaa")
	                .param("sandPRating", "AAA")
	                .param("fitchRating", "BBB")
					.param("order", "")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .with(csrf())
        		)
            .andExpect(status().isOk())
            .andExpect(view().name("rating/update"));
    }

    @Test
    @WithMockUser
    public void testDeleteRating_shouldRedirectToList() throws Exception {
        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }
    
    @Test
    @WithMockUser
    public void testDeleteRating_shouldThrowException() throws Exception {
        mockMvc.perform(get("/rating/delete/777"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(flash().attributeExists("errorMsg"));
    }
}
