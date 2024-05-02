package com.razat.todo.services;

import com.razat.todo.dtos.TaskResponseDTO;
import com.razat.todo.exceptions.InvalidTaskException;
import com.razat.todo.exceptions.TaskNotFoundException;
import com.razat.todo.models.Task;
import com.razat.todo.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(task.getName() == null ||  task.getStart() == null || task.getEnd() == null)
            throw new InvalidTaskException("Task can't have name ,start and end date as null!");
        if(task.getStart().compareTo(task.getEnd()) ==1 ){
            throw new InvalidTaskException("Start Date can't be greater than End date!");
        }
    }
    public List<Task> getAllTasks() {
        //fetch from database.
        logger.info("Querying the Persistence Store !!!");
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        //fetch task from persistence store.
        logger.info("Fetching task from persistence store!!");
        return taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found with id"+id));
    }

    public Task updateTaskById(Long id, Task task) {
        logger.info("fetching the task from persistence store using id+"+id);
        Task savedTask = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found with id:"+id));
        logger.info("setting the latest info of task!!");
        if(task.getName()!=null){
            savedTask.setName(task.getName());
        }
        if(task.getDescription()!=null){
            savedTask.setDescription(task.getDescription());
        }
        if(task.getTaskStatus()!=null){
            savedTask.setTaskStatus(task.getTaskStatus());
        }
        if(task.getAssignedTo()!=null){
            savedTask.setAssignedTo(task.getAssignedTo());
        }
        logger.info("Persisting the task with updated info!!");
        savedTask = taskRepository.save(savedTask);
        logger.info("Task persisted successfully!");
        return savedTask;
    }

    public Task deleteTaskById(Long id) {
        //check if task exist , then delete and return the deleted task.
        logger.info("checking if task exist ? ");
        Task task = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task Not found with id"+id));
        taskRepository.deleteById(id);
        logger.info("deleted the task with id {} successfully!!",id);
        return task;
    }
}
