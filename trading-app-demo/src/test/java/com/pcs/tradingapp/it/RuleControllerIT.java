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
import org.springframework.test.web.servlet.MockMvc;

import com.pcs.tradingapp.config.DevSecurityConfiguration;

@Import(DevSecurityConfiguration.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RuleControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndex_shouldReturnListView() throws Exception {
        mockMvc.perform(get("/rule/list"))
            .andExpect(view().name("rule/list"))
            .andExpect(model().attributeExists("rules"))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testShowAddForm_shouldReturnAddFormView() throws Exception {
        mockMvc.perform(get("/rule/add"))
            .andExpect(view().name("rule/add"))
            .andExpect(model().attributeExists("rule"))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testAddRule_withValidData_shouldRedirectToList() throws Exception {
        mockMvc.perform(post("/rule/add")
                .param("name", "Any New Rule Name")
                .param("description", "Description.")
                .param("json", "{}")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/rule/list"));
    }

    @Test
    public void testAddRule_withExistingName_shouldReturnAddView() throws Exception {
        mockMvc.perform(post("/rule/add")
                .param("name", "Stop Loss Rule")
                .param("description", "description")
                .param("json", "{}")
        )
            .andExpect(status().isOk())
            .andExpect(view().name("rule/add"))
            .andExpect(model().attributeHasFieldErrors("rule", "name"));
    }

    @Test
    public void testAddRule_withBlankName_shouldReturnAddView() throws Exception {
        mockMvc.perform(post("/rule/add")
                .param("name", "")
                .param("description", "desc")
                .param("json", "{}")
                .param("template", "template")
        )
            .andExpect(status().isOk())
            .andExpect(view().name("rule/add"))
            .andExpect(model().attributeHasFieldErrors("rule", "name"));
    }

    @Test
    public void testShowUpdateForm_withExistingId_shouldReturnUpdateView() throws Exception {
        mockMvc.perform(get("/rule/update/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("rule/update"))
            .andExpect(model().attributeExists("rule"));
    }

    @Test
    public void testShowUpdateForm_withUnknownId_shouldRedirectToListWithError() throws Exception {
        mockMvc.perform(get("/rule/update/999"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/rule/list"))
            .andExpect(flash().attributeExists("errorMsg"));
    }

    @Test
    public void testUpdateRule_withValidData_shouldRedirectToList() throws Exception {
        mockMvc.perform(post("/rule/update/1")
                .param("id", "1")
                .param("name", "Updated Rule")
                .param("description", "desc updated")
                .param("json", "{\"updated\": true}")
                .param("template", "template updated")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/rule/list"));
    }

    @Test
    public void testUpdateRule_withBlankName_shouldReturnUpdateView() throws Exception {
        mockMvc.perform(post("/rule/update/1")
                .param("id", "1")
                .param("name", "")
                .param("description", "desc")
                .param("json", "{}")
        )
            .andExpect(status().isOk())
            .andExpect(view().name("rule/update"));
    }
    
    @Test
    public void testUpdateRule_withUnknownId_shouldRedirectWithErrorMessage() throws Exception {
    	mockMvc.perform(post("/rule/update/222")
    	 .param("id", "11111")
         .param("name", "AnyName")
         .param("description", "desc")
         .param("json", "{}"))
    	.andExpect(status().is3xxRedirection())
    	.andExpect(flash().attributeExists("errorMsg"))
    	.andExpect(redirectedUrl("/rule/list"))
    	.andDo(print());
    }
    
    @Test
    public void testUpdateRule_withExistingName_shouldRedirectWithErrorMessage() throws Exception {
    	mockMvc.perform(post("/rule/update/1")
    			.param("id", "1")
    			.param("name", "Volume Spike Rule")
    			.param("description", "desc")
    			.param("json", "{}"))
    	.andExpect(status().isOk())
    	.andExpect(view().name("rule/update"))
    	.andExpect(model().attributeHasFieldErrors("rule", "name"));
    }

    @Test
    public void testDeleteRule_shouldRedirectToList() throws Exception {
        mockMvc.perform(get("/rule/delete/1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/rule/list"));
    }

    @Test
    public void testDeleteRule_withUnknownId_shouldRedirectWithErrorMessage() throws Exception {
        mockMvc.perform(get("/rule/delete/999"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/rule/list"))
            .andExpect(flash().attributeExists("errorMsg"));
    }
}
