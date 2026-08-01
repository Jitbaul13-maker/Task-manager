package com.example.Task_Manager.Services;

import com.example.Task_Manager.DTOs.CreateTaskDto;
import com.example.Task_Manager.DTOs.TaskResponseDto;
import com.example.Task_Manager.DTOs.UpdateTaskDto;
import com.example.Task_Manager.DTOs.UserResponseDto;
import com.example.Task_Manager.ENUMs.TaskPriority;
import com.example.Task_Manager.ENUMs.TaskStatus;
import com.example.Task_Manager.Models.Task;
import com.example.Task_Manager.Models.User;
import com.example.Task_Manager.Repos.TaskRepo;
import com.example.Task_Manager.Repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepo repo;
    private final UserRepo userRepo;

    public TaskService(TaskRepo repo, UserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public TaskResponseDto responseDtoHelper(Task task){
        UserResponseDto assignedTo = null;

        if (task.getAssignedTo() != null) {
            assignedTo = new UserResponseDto(
                    task.getAssignedTo().getName(),
                    task.getAssignedTo().getEmail()
            );
        }

        return new TaskResponseDto(
                task.getTitle(),
                task.getDescription(),
                new UserResponseDto(task.getCreatedBy().getName(), task.getCreatedBy().getEmail()),
                assignedTo
        );
    }

    public TaskResponseDto createTask(CreateTaskDto dto, Long id) {

        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Not a valid user"));

        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setPriority(TaskPriority.MEDIUM);
        task.setStatus(TaskStatus.TODO);
        task.setCreatedBy(user);

        repo.save(task);

        return responseDtoHelper(task);
    }

    public TaskResponseDto getTask(Long tid) {
        Task task = repo.findById(tid).orElseThrow(() -> new RuntimeException("No task found"));

        return responseDtoHelper(task);
    }

    public TaskResponseDto updateTask(UpdateTaskDto dto, Long tid) {
        Task task = repo.findById(tid).orElseThrow(() -> new RuntimeException("No task found"));

        if (dto.getTitle() != null) {task.setTitle(dto.getTitle());}
        if (dto.getDescription() != null) {task.setDescription(dto.getDescription());}
        if (dto.getAssignedToUserId() != null)
        {
            User user =  userRepo.findById(dto.getAssignedToUserId())
                    .orElseThrow(() -> new RuntimeException("No such User exists"));
            task.setAssignedTo(user);
        }
        if (dto.getDueDate() != null) {task.setDueDate(dto.getDueDate());}
        if (dto.getPriority() != null) {task.setPriority(dto.getPriority());}
        if (dto.getStatus() != null) {task.setStatus(dto.getStatus());}

        repo.save(task);

        return responseDtoHelper(task);
    }
}
