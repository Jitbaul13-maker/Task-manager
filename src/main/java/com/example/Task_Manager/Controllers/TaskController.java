package com.example.Task_Manager.Controllers;

import com.example.Task_Manager.DTOs.CreateTaskDto;
import com.example.Task_Manager.DTOs.TaskResponseDto;
import com.example.Task_Manager.DTOs.UpdateTaskDto;
import com.example.Task_Manager.Services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/tasks/")
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody CreateTaskDto dto,
                                                      @PathVariable("id") Long id){
        TaskResponseDto task = service.createTask(dto, id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/tasks/{tid}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable("tid") Long tid){
        TaskResponseDto task = service.getTask(tid);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/tasks/{tid}")
    public ResponseEntity<TaskResponseDto> updateTask(@Valid @RequestBody UpdateTaskDto dto, @PathVariable("tid") Long tid){
        TaskResponseDto task = service.updateTask(dto, tid);
        return ResponseEntity.ok(task);
    }
}
