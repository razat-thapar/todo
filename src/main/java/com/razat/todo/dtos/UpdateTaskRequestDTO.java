package com.razat.todo.dtos;

import com.razat.todo.enums.TaskStatus;
import com.razat.todo.models.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateTaskRequestDTO {
    private String name;
    private String description;
    private TaskStatus taskStatus;
    private Long assignedTo;

    public Task toTask(){
        Task task = Task
                .builder()
                .name(name)
                .description(description)
                .taskStatus(taskStatus)
                .build();
        return task;
    }
}
