package com.example.Task_Manager.Services;

import com.example.Task_Manager.DTOs.CreateUserDto;
import com.example.Task_Manager.DTOs.UserResponseDto;
import com.example.Task_Manager.Models.User;
import com.example.Task_Manager.Repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo repo;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService service;

    @Test
    void shouldCreateUserSuccessfully() {

        CreateUserDto dto = new CreateUserDto();
        dto.setName("John");
        dto.setEmail("john@gmail.com");
        dto.setPassword("password123");

        when(repo.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        when(encoder.encode(dto.getPassword()))
                .thenReturn("encodedPassword");

        UserResponseDto response = service.createUser(dto);

        assertEquals(dto.getName(), response.getName());
        assertEquals(dto.getEmail(), response.getEmail());

        verify(repo).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        CreateUserDto dto = new CreateUserDto();
        dto.setName("John");
        dto.setEmail("john@gmail.com");
        dto.setPassword("password123");

        User existingUser = new User();

        when(repo.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(existingUser));

        assertThrows(RuntimeException.class,
                () -> service.createUser(dto));

        verify(repo, never()).save(any(User.class));
    }
}