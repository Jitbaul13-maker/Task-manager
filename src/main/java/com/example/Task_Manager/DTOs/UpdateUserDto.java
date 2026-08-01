package com.example.Task_Manager.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private String name;
    private String email;
    private String password;
}
