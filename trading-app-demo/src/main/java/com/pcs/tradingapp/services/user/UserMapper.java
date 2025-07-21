package com.pcs.tradingapp.services.user;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.dto.request.user.CreateUserDto;
import com.pcs.tradingapp.dto.request.user.UpdateUserDto;
import com.pcs.tradingapp.dto.response.UserInfoDto;

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
