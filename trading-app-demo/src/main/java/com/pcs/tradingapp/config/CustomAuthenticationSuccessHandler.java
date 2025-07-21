package com.pcs.tradingapp.config;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles successful authentication events by user's username logging
 * and redirecting to the homepage. 
 *
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	Logger logger = LogManager.getLogger(CustomAuthenticationSuccessHandler.class);

    /**
     * Called when a user has been successfully authenticated.
     * Logs the authenticated user's username and redirects to the homepage.
     *
     * @param request       the {@link HttpServletRequest}
     * @param response      the {@link HttpServletResponse}
     * @param authentication the {@link Authentication} object containing the authenticated user details
     * @throws IOException
     * @throws ServletException
     */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String username = authentication.getName();
		
		logger.info("user {} has logged in", username);
		
		response.sendRedirect(request.getContextPath() + "/");
	}
}
