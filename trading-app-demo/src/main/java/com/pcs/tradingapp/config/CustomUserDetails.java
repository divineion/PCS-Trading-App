package com.pcs.tradingapp.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.User;

/**
 * Custom implementation of {@link UserDetails} for Spring Security authentication.
 * <p>
 * Wraps a domain User object and exposes user credentials and authorities
 * to the Spring Security infrastructure.
 * </p>
 */
public class CustomUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final User user;

	/**
     * Constructs a {@code CustomUserDetails} instance from the given user entity.
     *
     * @param user the domain {@link User} to wrap
     */
	public CustomUserDetails(User user) {
		this.user = user;
	}

	/**
     * Returns the authorities granted to the user.
     * <p>
     * This implementation returns a single role mapped from the user's {@link Role}.
     * </p>
     *
     * @return a collection containing the user's granted authorities
     */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().name()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
	

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
