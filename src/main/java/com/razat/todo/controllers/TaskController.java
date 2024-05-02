package com.razat.todo.controllers;

import com.razat.todo.dtos.CreateTaskRequestDTO;
import com.razat.todo.dtos.TaskResponseDTO;
import com.razat.todo.dtos.UpdateTaskRequestDTO;
import com.razat.todo.models.Task;
import com.razat.todo.services.TaskService;
import com.razat.todo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    public TaskController(TaskService taskService,UserService userService){
        this.taskService = taskService;
        this.userService = userService;
    }
    //create
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody CreateTaskRequestDTO createTaskRequestDTO){
        logger.info("Task Creation in progress!");
        TaskResponseDTO taskResponseDTO = TaskResponseDTO.toTaskResponseDTO(taskService.createTask(createTaskRequestDTO.toTask()));
        logger.info("Task Creation completed!");
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.CREATED);
    }
    //get all
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(){
        logger.info("Fetching all Tasks!!");
        List<TaskResponseDTO> taskResponseDTOList = TaskResponseDTO.toTaskResponseDTOList(taskService.getAllTasks());
        logger.info("Fetch All successfull!!");
        return new ResponseEntity<>(taskResponseDTOList,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable(name="id") Long id){
        logger.info("Fetching a task by id!");
        TaskResponseDTO taskResponseDTO = TaskResponseDTO.toTaskResponseDTO(taskService.getTaskById(id));
        logger.info("Found the task!!");
        return new ResponseEntity<>(taskResponseDTO,HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTaskById(@PathVariable Long id,@RequestBody UpdateTaskRequestDTO updateTaskRequestDTO){
        //convert to task.
        Task task = updateTaskRequestDTO.toTask();
        logger.info("checking if user is present in update request!");
        //get the user corresponding to assignedTo user id in DTO
        Long userId = updateTaskRequestDTO.getAssignedTo();
        if(userId!=null){
            logger.info("getting user with id:"+userId);
            task.setAssignedTo(userService.getUserById(userId));
            logger.info("user mapped to update request!!");
        }
        //update the non-null fields for an existing task.
        logger.info("Updating the task!");
        TaskResponseDTO taskResponseDTO= TaskResponseDTO.toTaskResponseDTO(taskService.updateTaskById(id,task));
        return new ResponseEntity<>(taskResponseDTO,HttpStatus.ACCEPTED);
    }

    //delete a task.
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> deleteTaskById(@PathVariable Long id){
        Task task = taskService.deleteTaskById(id);
        return new ResponseEntity<>(TaskResponseDTO.toTaskResponseDTO(task),HttpStatus.OK);
    }
}
