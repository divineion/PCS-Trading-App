package com.pcs.tradingapp.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.pcs.tradingapp.config.DevSecurityConfiguration;
import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.RoleName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

 // https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-dirtiescontext.html
// clean up database before each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    public void testAddUser_withUnavailableUsername_ShouldReturnAddUserViewWithError() throws Exception {  	
    	mockMvc.perform(post("/user/add")
    			.param("fullname", "Fullname")
    			.param("username", "user") 
    			.param("password", "AnyP@ssw0rd") // RoleNotFoundException si password invalide et username already exists
    			.param("role", "USER")
    			)
    	
    	.andExpect(status().is2xxSuccessful())
    	.andExpect(view().name("user/add"))
    	//model attribute then fields
    	.andExpect(model().attributeHasFieldErrors("user", "username"));
    }

    @Test
    public void  testShowUpdateForm_ShouldReturnUpdateView() throws Exception {
    	mockMvc.perform(get("/user/update/1"))
    		.andExpect(status().is2xxSuccessful())
    		.andExpect(view().name("user/update"))
    		.andExpect(model().attributeExists("user"));
    }
    
    @Test
    public void  testShowUpdateForm_WithUnknownUserParameter_shouldRedirectToListViewWithErrorMessage() throws Exception {
    	mockMvc.perform(get("/user/update/999"))
    		.andExpect(status().is3xxRedirection())
    		.andExpect(flash().attributeExists("errorMsg"))
    		.andExpect(redirectedUrl("/user/list"));
    }
    
    @Test
    public void testUpdateUser_shouldRedirectToUpdatedUsersList() throws Exception {
    	mockMvc.perform(post("/user/update/1")
    				.param("fullname", "Main Administrator")
    				.param("username", "MainAdmin")
    				.param("role", "ADMIN")
    				.param("password", "mainAdminNewP@ssw0rd")
    			)
    	    	
    		.andExpect(status().is3xxRedirection())
    		.andExpect(redirectedUrl("/user/list"));
    }
    
    @Test
    public void testUpdateUser_withInvalidPassword_ShouldReturnUpdateUserViewWithError() throws Exception {  	
    	mockMvc.perform(post("/user/update/1")
    			.param("fullname", "Fullname")
    			.param("username", "availableusername") 
    			.param("password", "invalid!password")
    			.param("role", "USER")
    			)
    	
    	.andDo(print())
    	.andExpect(status().is2xxSuccessful())
    	.andExpect(view().name("user/update"))
    	.andExpect(model().attributeHasFieldErrors("user", "password"));
    }  
    
    @Test
    public void testUpdateUser_withUnavailableUsername_shouldReturnUpdateViewWithErrors() throws Exception {
    	mockMvc.perform(post("/user/update/2")
    			.param("fullname", "Administrator")
    			.param("username", "admin")
    			.param("role", "ADMIN")
    			.param("password", "AnyP@ssw0rd")
    			)
    	    	
    	.andExpect(status().is2xxSuccessful())
    	.andExpect(view().name("user/update"))
    	.andExpect(model().attributeExists("user"));
    }
    
    @Test
    public void testUpdateUser_withUnknownUser_shouldRedirectToListViewWithErrorMessage() throws Exception {
    	mockMvc.perform(post("/user/update/19")
    				.param("fullname", "Main Administrator")
    				.param("username", "MainAdmin")
    				.param("role", "ADMIN")
    				.param("password", "mainAdminNewP@ssw0rd")
    			)
    	
    		.andExpect(status().is3xxRedirection())
    		.andExpect(flash().attributeExists("errorMsg"))
    		.andExpect(redirectedUrl("/user/list"));
    }
    
    @Test
    public void testDeleteUser_shouldReturnUsersListView() throws Exception {
    	mockMvc.perform(get("/user/delete/2"))
    	
    	.andExpect(status().is3xxRedirection())
    	.andExpect(redirectedUrl("/user/list"));
    }
    
    @Test
    public void testDeleteUser_shouldRedirectToListViewWithErrorMessage() throws Exception {
    	mockMvc.perform(get("/user/delete/999"))
    	
    	.andExpect(status().is3xxRedirection())
    	.andExpect(flash().attributeExists("errorMsg"))
    	.andExpect(redirectedUrl("/user/list"));
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
    public void testAddUser_WithUnknownRole_ShouldReturnUpdateFormWithError() throws Exception {
        mockMvc.perform(post("/user/add")
        		.param("fullname", "Valid Fullname")
                .param("username", "SomeUsername") 
                .param("password", "V@lidP@ssw0rd")
                .param("role", "admin")
    		)
        
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("user", "role"))
                .andExpect(view().name("user/add"));
    }
}