package com.example.Task_Manager.Controllers;

import com.example.Task_Manager.DTOs.CreateUserDto;
import com.example.Task_Manager.DTOs.UpdateUserDto;
import com.example.Task_Manager.DTOs.UserResponseDto;
import com.example.Task_Manager.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUsers(@RequestBody CreateUserDto dto){
        UserResponseDto user = service.createUser(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam("id") Long id) {
        UserResponseDto user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestParam("id") Long id, @RequestBody UpdateUserDto dto) {
        UserResponseDto user = service.updateUser(id, dto);
        return ResponseEntity.ok(user);
    }
}
