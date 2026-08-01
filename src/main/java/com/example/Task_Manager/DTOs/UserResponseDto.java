package com.example.Task_Manager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private String name;
    private String email;
}
