package com.example.Task_Manager.DTOs;

import com.example.Task_Manager.ENUMs.TaskPriority;
import com.example.Task_Manager.ENUMs.TaskStatus;
import com.example.Task_Manager.Models.User;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateTaskDto {
    private String title;
    private String description;
    @FutureOrPresent
    private LocalDate dueDate;
    private Long assignedToUserId;
    private TaskPriority priority;
    private TaskStatus status;
}
