package com.pcs.tradingapp.it;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=test")
public class AuthenticationIT {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testLoginWithBadPassword_shouldRedirectToError() throws Exception {
		mockMvc.perform(formLogin("/login").user("user").password("AnyBadP@ssw0rd"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/login?error"));
	}
	
	@Test
	public void testLoginWithValidCredentials_shouldRedirectToHome() throws Exception {
		mockMvc.perform(formLogin("/login").user("user").password("user_P@ssw0rd"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void testAccessProtectedUrlWithoutAuthentication_shouldRedirectToLogin() throws Exception {
		mockMvc.perform(get("/user/list"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrlPattern("**/login"));
	}
}
