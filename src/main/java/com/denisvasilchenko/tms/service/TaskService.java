package com.denisvasilchenko.tms.service;

import com.denisvasilchenko.tms.dto.EntityMapper;
import com.denisvasilchenko.tms.dto.task.TaskRequest;
import com.denisvasilchenko.tms.dto.task.TaskResponse;
import com.denisvasilchenko.tms.exceprions.TaskNotFoundException;
import com.denisvasilchenko.tms.model.Task;
import com.denisvasilchenko.tms.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private TaskRepository taskRepository;

    public Task save(TaskRequest task) {
        return taskRepository.save(entityMapper.requestToEntity(task));
    }

    public Task create (TaskRequest taskRequest) {
        return save(taskRequest);
    }
    public Task update (TaskRequest taskRequest) {
        return save(taskRequest);
    }

    public Page<TaskResponse> findAll (Specification<Task> specification, Pageable pageable){
        Page<Task> tasks = taskRepository.findAll(specification, pageable);
        List<TaskResponse> taskResponses = tasks.getContent().stream()
                .map(task -> entityMapper.entityToResponse(task)).collect(Collectors.toList());
        return new PageImpl<>(taskResponses, pageable, tasks.getTotalElements());
    }

    public TaskResponse findById(Long id) {
        return entityMapper.entityToResponse(taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id)));
    }

    public void deleteById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
        }
        else {throw new TaskNotFoundException(id);}
    }
}
