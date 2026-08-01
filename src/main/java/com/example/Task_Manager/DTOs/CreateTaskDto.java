package com.example.Task_Manager.DTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTaskDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    @FutureOrPresent
    private LocalDate dueDate;

}
