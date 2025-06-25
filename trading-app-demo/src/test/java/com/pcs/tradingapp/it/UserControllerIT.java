package com.pcs.tradingapp.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.pcs.tradingapp.config.DevSecurityConfiguration;
import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.RoleName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
@Import(DevSecurityConfiguration.class)
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListUsers_ShouldReturnListView() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void testShowAddUserForm_ShouldReturnAddUserView() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testAddUser_withValidData_ShouldRedirect() throws Exception {
        mockMvc.perform(post("/user/add")
        		.param("fullname", "test UserFullname")
                .param("username", "userDeTest")
                .param("password", "TestPassword123!")
                .param("role", "USER")
            )
        
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    public void testAddUser_withInvalidData_ShouldReturnAddUserViewWithErrors() throws Exception {
        mockMvc.perform(post("/user/add")
        		.param("fullname", "Fullname With Digits 45")
                .param("username", "") 
                .param("password", "short")
                .param("role", "")
    		)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/add"))
                //model attribute then fields
                .andExpect(model().attributeHasFieldErrors("user", "fullname", "username", "password", "role"));
    }
    
    /**
     * This test ensures robustness against potential inconsistencies. 
     * 
     * The test case should never happen in normal conditions, as available roles are
     * defined and persisted in the database. 
     * 
     * @see Role
     * @see RoleName
     */
    @Test
    public void testAddUser_WithUnknownRole_ShouldReturnInternalServerError() throws Exception {
        mockMvc.perform(post("/user/add")
        		.param("fullname", "Valid Fullname")
                .param("username", "SomeUsername") 
                .param("password", "V@lidP@ssw0rd")
                .param("role", "UNKNOWN")
    		)
        		.andDo(print())
                .andExpect(status().isInternalServerError());
    }
}