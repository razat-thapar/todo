package com.razat.todo.dtos;
import com.razat.todo.enums.TaskStatus;
import com.razat.todo.models.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class CreateTaskRequestDTO {
    private String name;
    private String description;
    private Date start;
    private Date end;

    public Task toTask(){
        Task task = Task
                .builder()
                .name(name)
                .description(description)
                .taskStatus(TaskStatus.NEW)
                .start(start)
                .end(end)
                .build();
        return task;
    }
}
