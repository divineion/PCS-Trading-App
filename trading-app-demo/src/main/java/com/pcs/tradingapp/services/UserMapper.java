package com.pcs.tradingapp.services;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.dto.request.CreateUserDto;
import com.pcs.tradingapp.dto.request.UpdateUserDto;
import com.pcs.tradingapp.dto.response.UserInfoDto;

// génère un bean spring pr le mapper (évite de devoir instancier manuellement avec Mappers.getMapper() )
@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "role", expression = "java(user.getRole().getName().name())")
	public UserInfoDto userToUserInfoDto(User user);
	
	public List<UserInfoDto> usersToUserInfoDtos(List<User> users);
	
	@Mapping(target = "role", expression = "java(user.getRole().getName().name())")
	public UpdateUserDto userToUpdateUserDto(User user);
	
	@Mapping(target="role", ignore=true)
	public User updateUserDtoToUser (UpdateUserDto userDto);
	
	@Mapping(target="id", ignore=true)
	@Mapping(target="role", ignore=true)
	public User createUserDtoToUser(CreateUserDto userDto);
}
