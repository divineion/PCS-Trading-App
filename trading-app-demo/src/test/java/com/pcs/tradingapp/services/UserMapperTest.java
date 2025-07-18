package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.RoleName;
import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.dto.request.user.CreateUserDto;
import com.pcs.tradingapp.services.user.UserMapper;

public class UserMapperTest {
	private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
	
    @Test
    public void testCreateUserDtoToUserShouldPass() {   	
        CreateUserDto dto = new CreateUserDto();
        dto.setUsername("User1");
        dto.setFullname("User1");
        dto.setRole("USER");
        dto.setPassword("P@ssword1");

        User user = mapper.createUserDtoToUser(dto);
        //suffit pour instance sans persist
        user.setRole(new Role(RoleName.valueOf(dto.getRole())));

        assertAll("User entity validation",
                () -> assertNotNull(user.getRole(), "The role cannot be null"),
                () -> assertEquals(RoleName.USER, user.getRole().getName(), "The role must be 'USER'"),
                () -> assertEquals("User1", user.getUsername()),
                () -> assertEquals("User1", user.getFullname()),
                () -> assertEquals("P@ssword1", user.getPassword())
            );
    }
}