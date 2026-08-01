package com.example.Task_Manager.Services;

import com.example.Task_Manager.DTOs.CreateTaskDto;
import com.example.Task_Manager.DTOs.TaskResponseDto;
import com.example.Task_Manager.Models.Task;
import com.example.Task_Manager.Models.User;
import com.example.Task_Manager.Repos.TaskRepo;
import com.example.Task_Manager.Repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private TaskService service;

    @Test
    void shouldCreateTaskSuccessfully(){

        CreateTaskDto task = new CreateTaskDto();
        task.setTitle("xyz");
        task.setDescription("task_description");
        task.setDueDate(LocalDate.now().plusDays(2));

        User user = new User();
        user.setId(1L);
        user.setName("abc");

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        TaskResponseDto dto = service.createTask(task, 1L);

        assertEquals(user.getName(), dto.getCreatedBy().getName());
        assertEquals(task.getTitle(), dto.getTitle());
        assertEquals(task.getDescription(), dto.getDescription());

        verify(taskRepo).save(any(Task.class));
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskForNonExistingUser(){

        CreateTaskDto task = new CreateTaskDto();
        task.setTitle("xyz");
        task.setDescription("task_description");
        task.setDueDate(LocalDate.now().plusDays(2));

        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.createTask(task, 1L));

        verify(taskRepo, never()).save(any());
    }
}
