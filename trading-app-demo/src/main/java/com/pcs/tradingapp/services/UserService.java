package com.pcs.tradingapp.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.RoleName;
import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.dto.request.CreateUserDto;
import com.pcs.tradingapp.dto.response.UserInfoDto;
import com.pcs.tradingapp.exceptions.RoleNotFoundException;
import com.pcs.tradingapp.exceptions.UsernameAlreadyExistsException;
import com.pcs.tradingapp.repositories.RoleRepository;
import com.pcs.tradingapp.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository repository;
	private final RoleRepository roleRepository;
	private final UserMapper mapper;
	
	public UserService(UserRepository repository, UserMapper mapper, RoleRepository roleRepository) {
		this.repository = repository;
		this.mapper = mapper;
		this.roleRepository = roleRepository;
	}
	
	public List<UserInfoDto> getAllUsers() {
		List<User> users = repository.findAll();
		
		return mapper.usersToUserInfoDtos(users);
	}

	public List<UserInfoDto> createNewUser(CreateUserDto userDto) throws RoleNotFoundException, UsernameAlreadyExistsException {
		if (repository.findByUsername(userDto.getUsername()).isPresent()) {
			throw new UsernameAlreadyExistsException(ApiMessages.USERNAME_ALREADY_EXISTS);
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = mapper.createUserDtoToUser(userDto);
        
        RoleName roleName = null;
        
        try {
            roleName = RoleName.valueOf(userDto.getRole());
        } catch (IllegalArgumentException ex) {
            throw new RoleNotFoundException(ApiMessages.ROLE_NOT_FOUND);
        }
        
        Role role = roleRepository.findByName(roleName);
        
        if (role == null) {
            throw new RoleNotFoundException(ApiMessages.ROLE_NOT_FOUND);
        }
        
        user.setRole(role);
        
        repository.save(user);
        
        return getAllUsers();
	}
}
