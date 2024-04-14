package com.razat.todo.controllers;

import com.razat.todo.dtos.CreateTaskRequestDTO;
import com.razat.todo.dtos.TaskResponseDTO;
import com.razat.todo.models.Task;
import com.razat.todo.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;
    private Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    //create
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody CreateTaskRequestDTO createTaskRequestDTO){
        logger.info("Task Creation in progress!");
        TaskResponseDTO taskResponseDTO = TaskResponseDTO.toTaskResponseDTO(taskService.createTask(createTaskRequestDTO.toTask()));
        logger.info("Task Creation completed!");
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.CREATED);
    }
}
