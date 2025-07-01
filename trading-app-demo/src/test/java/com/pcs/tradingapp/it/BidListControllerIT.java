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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.pcs.tradingapp.config.DevSecurityConfiguration;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
@Import(DevSecurityConfiguration.class)
public class BidListControllerIT {
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    public void testListBidList_shouldReturnListView() throws Exception {
        mockMvc.perform(get("/bidlist/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidlist/list"))
                .andExpect(model().attributeExists("bidLists"));
    }

    @Test
    public void testAddBidListForm_shouldReturnAddView() throws Exception {
        mockMvc.perform(get("/bidlist/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidlist/add"))
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    public void testAddBidList_withValidData_shouldRedirectToList() throws Exception {
        mockMvc.perform(post("/bidlist/add")
                        .param("account", "Account1")
                        .param("type", "Type1")
                        .param("bidQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidlist/list"));
    }

    @Test
    public void testAddBidList_withInvalidData_shouldReturnAddView() throws Exception {
        mockMvc.perform(post("/bidlist/add")
                        .param("account", "") 
                        .param("type", "Type1")
                        .param("bidQuantity", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    public void testShowUpdateForm_withExistingId_shouldReturnUpdateView() throws Exception {
        mockMvc.perform(get("/bidlist/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    public void testShowUpdateForm_withUnknownId_shouldRedirectToListWithError() throws Exception {
        mockMvc.perform(get("/bidlist/update/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidlist/list"))
                .andExpect(flash().attributeExists("errorMsg"));
    }

    @Test
    public void testUpdateBid_withValidData_shouldRedirectToList() throws Exception {
        mockMvc.perform(post("/bidlist/update/1")
                        .param("id", "1")
                        .param("account", "UpdatedAccount")
                        .param("type", "UpdatedType")
                        .param("bidQuantity", "20")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidlist/list"));
    }

    @Test
    public void testUpdateBid_withBlankAccount_shouldReturnUpdateView() throws Exception {
        mockMvc.perform(post("/bidlist/update/1")
                        .param("id", "1")
                        .param("account", "") 
                        .param("type", "Type1")
                        .param("bidQuantity", "20")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    public void testDeleteBidList_shouldRedirectToList() throws Exception {
        mockMvc.perform(get("/bidlist/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidlist/list"));
    }
    
    @Test
    public void testDeleteBidList_shouldThrowException() throws Exception {
        mockMvc.perform(get("/bidlist/delete/455"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidlist/list"))
                .andExpect(flash().attributeExists("errorMsg"));
    }
}
