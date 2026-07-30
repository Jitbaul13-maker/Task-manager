package com.example.Task_Manager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@AllArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private String name;
    private String email;
}
