package com.denisvasilchenko.tms.exceprions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long id){
        super("Task with ID "+id+" not found");
    }
}
