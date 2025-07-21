package com.pcs.tradingapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
	AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
	    return new CustomAuthenticationSuccessHandler();
	}
	
	/**
	 * Configures the security filter chain with:
	 * <ul>
	 *   <li>Session-based authentication using form login</li>
	 *   <li>Role-based authorization: public access to <code>/login</code>, 
	 *   <code>ADMIN</code> role required for <code>/admin/**</code>, 
	 *   authentication required for all other endpoints</li>
	 *   <li>Custom handling of authentication success via 
	 *   {@link CustomAuthenticationSuccessHandler}</li>
	 * </ul>
	 *
	 * @param http the {@link HttpSecurity} to customize
	 * @return the configured {@link SecurityFilterChain}
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers("/login").permitAll();
				auth.requestMatchers("/admin/**").hasRole("ADMIN");
				auth.anyRequest().authenticated();
			})
			.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/accessdenied"))
			.formLogin(form -> form
					.permitAll()
					.successHandler(customAuthenticationSuccessHandler())
				);
		
		return http.build();
	}
	
	/**
	 * Provides a {@link PasswordEncoder} bean using the BCrypt hashing algorithm.
	 * This encoder is used for securely hashing and verifying user passwords.
	 *
	 * @return a {@link BCryptPasswordEncoder} instance
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Configures and exposes the application {@link AuthenticationManager} bean.
	 * 
	 * Uses the custom {@link CustomUserDetailsService} and the provided {@link PasswordEncoder}
	 * for authentication management.
	 *
	 * @param http the {@link HttpSecurity} context
	 * @param passwordEncoder the {@link PasswordEncoder} to use for authentication
	 * @return the configured {@link AuthenticationManager}
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder);
		
		return authenticationManagerBuilder.build();
	}
}
