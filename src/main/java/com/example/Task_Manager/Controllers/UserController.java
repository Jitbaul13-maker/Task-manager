package com.example.Task_Manager.Controllers;

import com.example.Task_Manager.DTOs.CreateUserDto;
import com.example.Task_Manager.DTOs.UserResponseDto;
import com.example.Task_Manager.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> getAllUsers(@RequestBody CreateUserDto dto){
        UserResponseDto user = service.createUser(dto);
        return ResponseEntity.ok(user);
    }
}
