package com.pcs.tradingapp.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository repository;
	
	public CustomUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
				
		return new CustomUserDetails(user);
	}
}
