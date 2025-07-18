package com.pcs.tradingapp.services.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.RoleName;
import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.dto.request.user.CreateUserDto;
import com.pcs.tradingapp.dto.request.user.UpdateUserDto;
import com.pcs.tradingapp.dto.response.UserInfoDto;
import com.pcs.tradingapp.exceptions.RoleNotFoundException;
import com.pcs.tradingapp.exceptions.UserNotFoundException;
import com.pcs.tradingapp.exceptions.UsernameAlreadyExistsException;
import com.pcs.tradingapp.repositories.RoleRepository;
import com.pcs.tradingapp.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository repository;
	private final RoleRepository roleRepository;
	private final UserMapper mapper;
	private final PasswordEncoder encoder;
	
	public UserService(UserRepository repository, UserMapper mapper, RoleRepository roleRepository,
			PasswordEncoder encoder) {
		this.repository = repository;
		this.mapper = mapper;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
	}
	
	public Role fetchUserRole(String roleString) throws RoleNotFoundException {		
		RoleName roleName = null;
		
		try {
            roleName = RoleName.valueOf(roleString);
        } catch (IllegalArgumentException ex) {
            throw new RoleNotFoundException(ApiMessages.ROLE_NOT_FOUND);
        }
        
        Role role = roleRepository.findByName(roleName);
        
        if (role == null) {
        	throw new RoleNotFoundException(ApiMessages.ROLE_NOT_FOUND);
        }
        
        return role;
	}
	
	public boolean validateUsernameIsAvailable(String username) throws UsernameAlreadyExistsException {
		if (repository.findByUsername(username).isPresent()) {
			throw new UsernameAlreadyExistsException(ApiMessages.USERNAME_ALREADY_EXISTS);
		}
		
		return true;
	}
	
	public boolean validateUserExists(int id) throws UserNotFoundException {
		if (repository.findById(id) == null) {
        	throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND);
        }
		
		return true;
	}
	
	public List<UserInfoDto> getAllUsers() {
		List<User> users = repository.findAll();
		
		return mapper.usersToUserInfoDtos(users);
	}

	public void createNewUser(CreateUserDto userDto) throws RoleNotFoundException, UsernameAlreadyExistsException {
		validateUsernameIsAvailable(userDto.getUsername());

        userDto.setPassword(encoder.encode(userDto.getPassword()));
        
        User user = mapper.createUserDtoToUser(userDto);
        
        Role role = fetchUserRole(userDto.getRole());
        user.setRole(role);
        
        repository.save(user);
	}
	
	// TODO rename method
	public UpdateUserDto fetchUpdateUserDto(Integer id) throws UserNotFoundException {
		User dbUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException(ApiMessages.USER_NOT_FOUND));
        return  mapper.userToUpdateUserDto(dbUser);
	}
	
	public void updateUser(UpdateUserDto userDto) throws RoleNotFoundException, UserNotFoundException, UsernameAlreadyExistsException {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
                
        User user = repository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException(ApiMessages.USER_NOT_FOUND));
        
		if (!userDto.getUsername().equals(user.getUsername())) {
			validateUsernameIsAvailable(userDto.getUsername());
		}
        
        User userToUpdate = mapper.updateUserDtoToUser(userDto);
        
        Role role = fetchUserRole(userDto.getRole());        
        userToUpdate.setRole(role);
        
        repository.save(userToUpdate);
	}

	public void deleteUser(Integer id) throws UserNotFoundException {
		User user = repository.findById(id).orElseThrow( () -> new UserNotFoundException(ApiMessages.USER_NOT_FOUND));
		
        repository.delete(user);
	}
}
