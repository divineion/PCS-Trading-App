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
	
	// security filter chain 
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers("/login").permitAll();
				auth.requestMatchers("/admin/**").hasRole("ADMIN");
				auth.anyRequest().authenticated();
			})
			.formLogin(form -> form
					.permitAll()
					.successHandler(customAuthenticationSuccessHandler())
				);
		
		return http.build();
	}
	
	// password encoder
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// authentication manager
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder);
		
		return authenticationManagerBuilder.build();
	}
}
