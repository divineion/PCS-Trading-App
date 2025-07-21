package com.pcs.tradingapp.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.repositories.UserRepository;

/**
 * Service for retrieving application user details for Spring Security authentication.
 * <p>
 * Implements {@link UserDetailsService} to load a user's data based on a username.
 * Returns a {@link CustomUserDetails} instance that wraps the corresponding domain user entity.
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository repository;
	
	public CustomUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}
	
	/**
     * Looks up a user in the repository using the provided username. 
     * Returns a {@link CustomUserDetails} object if found. 
     * Throw {@link UsernameNotFoundException} otherwise.   
     * </p>
     *
     * @param username the username identifying the user whose data is required
     * @return a {@link CustomUserDetails} object populated with user data
     * @throws UsernameNotFoundException if the user could not be found
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
				
		return new CustomUserDetails(user);
	}
}
