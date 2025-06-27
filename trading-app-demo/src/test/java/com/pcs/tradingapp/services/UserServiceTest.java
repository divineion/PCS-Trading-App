package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pcs.tradingapp.domain.RoleName;
import com.pcs.tradingapp.exceptions.RoleNotFoundException;
import com.pcs.tradingapp.exceptions.UserNotFoundException;
import com.pcs.tradingapp.repositories.RoleRepository;
import com.pcs.tradingapp.repositories.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService service;
	
	@Test
	public void testFetchRole_shouldThrowException() throws Exception {
		//assert: repository don't find the role
		when(roleRepository.findByName(RoleName.USER)).thenReturn(null);
		
		//act and assert: service shoul throw exception
		assertThrows(RoleNotFoundException.class, ()-> service.fetchUserRole("USER"));
	}
	
	@Test
	public void testValidateUserExists_shouldThrowException() throws Exception {
		int userId = 999;
		when(userRepository.findById(userId)).thenReturn(null);
		
		assertThrows(UserNotFoundException.class, ()-> service.validateUserExists(userId));
	}
}
