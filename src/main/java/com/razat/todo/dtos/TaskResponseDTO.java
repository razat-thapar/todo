package com.razat.todo.dtos;

import com.razat.todo.enums.TaskStatus;
import com.razat.todo.models.Task;
import com.razat.todo.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Builder
@Getter
public class TaskResponseDTO {
    private Long id;
    private String name;
    private String description;
    private TaskStatus taskStatus;
    private Date start;

    private Date end ;
    private User assignedTo;
    public static TaskResponseDTO toTaskResponseDTO(Task task){
        TaskResponseDTO taskResponseDTO = TaskResponseDTO
                .builder()
                .id(task.getId())
                .name(task.getName())
                .taskStatus(task.getTaskStatus())
                .description(task.getDescription())
                .start(task.getStart())
                .end(task.getEnd())
                .assignedTo(task.getAssignedTo())
                .build();
        return taskResponseDTO;
    }
}
