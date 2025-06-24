package com.pcs.tradingapp.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.RoleName;
import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.dto.request.CreateUserDto;
import com.pcs.tradingapp.dto.response.UserInfoDto;
import com.pcs.tradingapp.repositories.RoleRepository;
import com.pcs.tradingapp.repositories.UserRepository;

import jakarta.validation.Valid;

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

	public List<UserInfoDto> validateNewUser(CreateUserDto userDto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = mapper.createUserDtoToUser(userDto);
        
        Role role = roleRepository.findByName(RoleName.valueOf(userDto.getRole()));
        user.setRole(role);
        
        repository.save(user);
        
        return getAllUsers();
	}
}
