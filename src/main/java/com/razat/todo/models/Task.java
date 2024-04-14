package com.razat.todo.models;

import com.razat.todo.enums.TaskStatus;
import com.razat.todo.exceptions.InvalidTaskException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseModel{
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private TaskStatus taskStatus;
    @Column(nullable = false)
    private Date start;
    @Column(nullable = false)
    private Date end ;
    @ManyToOne
    private User assignedTo;

    public static TaskBuilder builder(){
        return new TaskBuilder();
    }
    private Task(Task other){
        super(other);
        this.name = other.name;
        this.description= other.description;
        this.start = other.start;
        this.end = other.end;
        this.taskStatus = other.taskStatus;
        this.assignedTo = other.assignedTo;
    }
    @Override
    public Task clone(){
        return new Task(this);
    }

    public static class TaskBuilder{

        private Task task = new Task();
        public Task build(){
            //perform complex validations.
            validate(task);
            //deep copy.
            return task.clone();
        }

        private static void validate(Task task) {
            if(task.name == null ||  task.start == null || task.end == null)
                throw new InvalidTaskException("Task can't have name ,start and end date as null!");
        }

        public TaskBuilder name(String name){
            task.name = name;
            return this;
        }
        public TaskBuilder description(String description){
            task.description = description;
            return this;
        }
        public TaskBuilder start(Date start){
            task.start = start;
            return this;
        }
        public TaskBuilder end(Date end){
            task.end = end;
            return this;
        }
        public TaskBuilder assignedTo(User assignedTo){
            task.assignedTo = assignedTo;
            return this;
        }

        public TaskBuilder taskStatus(TaskStatus taskStatus){
            task.taskStatus = taskStatus;
            return this;
        }



    }

}
