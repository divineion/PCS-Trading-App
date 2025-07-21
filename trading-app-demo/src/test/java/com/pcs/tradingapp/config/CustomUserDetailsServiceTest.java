package com.pcs.tradingapp.config;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pcs.tradingapp.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {
	@InjectMocks
	CustomUserDetailsService customUserDetailsService;
	
	@Mock
	UserRepository repository;
	
	@Test
	public void testLoadUserByUsername_shouldThrowException() {
		String username = "anyUsername";
		when(repository.findByUsername(username)).thenReturn(Optional.empty());
		
		assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(username));
	}
}
