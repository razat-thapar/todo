package com.razat.todo.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.razat.todo.enums.TaskStatus;
import com.razat.todo.models.Task;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Setter
@Getter
public class CreateTaskRequestDTO {
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
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
