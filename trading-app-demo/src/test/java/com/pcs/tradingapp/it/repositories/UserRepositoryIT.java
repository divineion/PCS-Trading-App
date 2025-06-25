package com.pcs.tradingapp.it.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.RoleName;
import com.pcs.tradingapp.domain.User;
import com.pcs.tradingapp.dto.request.CreateUserDto;
import com.pcs.tradingapp.exceptions.UserNotFoundException;
import com.pcs.tradingapp.repositories.RoleRepository;
import com.pcs.tradingapp.repositories.UserRepository;
import com.pcs.tradingapp.services.UserMapper;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    /**
     * Integration test to verify that a User entity is correctly created and persisted 
     * in the database from a CreateUserDto using the UserMapper.
     * 
     * The test checks that:
     * - The User entity is properly mapped from the DTO.
     * - The Role is correctly fetched and associated with the User.
     * - The User is successfully saved in the database.
     * - The saved User's fields (username, fullname, role, password) match the expected values.
     * 
     * This ensures the mapping and persistence logic work as expected.
     */
    @Test
    public void testSaveUserFromDto() throws UserNotFoundException {
        // GIVEN
        CreateUserDto dto = new CreateUserDto();
        dto.setFullname("Second User");
        dto.setUsername("user2");
        dto.setPassword("P@ssw0rd2");
        dto.setRole("USER");

        // WHEN
        User user = userMapper.createUserDtoToUser(dto);
        
        Role userRole = roleRepository.findByName(RoleName.valueOf(dto.getRole()));
        user.setRole(userRole);
        
        userRepository.save(user);
      
        User userFromDb = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(ApiMessages.USER_NOT_FOUND));

        // THEN
        assertEquals("user2", userFromDb.getUsername());
        assertEquals("Second User", userFromDb.getFullname());
        assertEquals(RoleName.USER, userFromDb.getRole().getName());
        assertNotNull(userFromDb.getPassword());
    }
}
