package com.example.Task_Manager.Services;

import com.example.Task_Manager.DTOs.CreateUserDto;
import com.example.Task_Manager.DTOs.UpdateUserDto;
import com.example.Task_Manager.DTOs.UserResponseDto;
import com.example.Task_Manager.ENUMs.Role;
import com.example.Task_Manager.Models.User;
import com.example.Task_Manager.Repos.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public UserResponseDto createUser(CreateUserDto dto) {
        Optional<User> existingUser = repo.findByEmail(dto.getEmail());

        if (existingUser.isPresent()) throw new RuntimeException("User with same email already exists");

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER);

        repo.save(user);

        return new UserResponseDto(
                user.getName(),
                user.getEmail()
        );
    }

    public UserResponseDto getUserById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found!"));

        return new UserResponseDto(
                user.getName(),
                user.getEmail()
        );
    }

    public UserResponseDto updateUser(Long id, UpdateUserDto dto) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found!"));

        if (dto.getName() != null){ user.setName(dto.getName());}
        if (dto.getEmail() != null){ user.setEmail(dto.getEmail());}
        if (dto.getPassword() != null){ user.setPassword(encoder.encode(dto.getPassword()));}

        repo.save(user);

        return new UserResponseDto(
                user.getName(),
                user.getEmail()
        );
    }
}
