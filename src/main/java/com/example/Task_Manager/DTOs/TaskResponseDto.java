package com.example.Task_Manager.DTOs;

import com.example.Task_Manager.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskResponseDto {
    private String title;
    private String description;
    private UserResponseDto createdBy;
    private UserResponseDto assignedTo;
}
