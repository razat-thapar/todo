package com.razat.todo.services;

import com.razat.todo.exceptions.InvalidTaskException;
import com.razat.todo.models.Task;
import com.razat.todo.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository ;
    private Logger logger = LoggerFactory.getLogger(TaskService.class);
    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        //validation.
        validateTask(task);
        //persist
        logger.info("Persisting the task!");
        Task savedTask = taskRepository.save(task);
        logger.info("Task Persisted Succesfully!");
        return savedTask;
    }

    private void validateTask(Task task) {
        if(task.getStart().compareTo(task.getEnd()) ==1 ){
            throw new InvalidTaskException("Start Date can't be greater than End date!");
        }
    }
}
