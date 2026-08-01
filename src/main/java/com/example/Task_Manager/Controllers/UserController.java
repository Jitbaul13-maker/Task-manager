package com.example.Task_Manager.Controllers;

import com.example.Task_Manager.DTOs.CreateUserDto;
import com.example.Task_Manager.DTOs.UpdateUserDto;
import com.example.Task_Manager.DTOs.UserResponseDto;
import com.example.Task_Manager.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUsers(@Valid @RequestBody CreateUserDto dto){
        UserResponseDto user = service.createUser(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
        UserResponseDto user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @Valid@RequestBody UpdateUserDto dto) {
        UserResponseDto user = service.updateUser(id, dto);
        return ResponseEntity.ok(user);
    }
}
