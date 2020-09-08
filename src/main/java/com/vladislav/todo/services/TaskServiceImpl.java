package com.vladislav.todo.services;

import com.vladislav.todo.entities.Task;
import com.vladislav.todo.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllUserTasks(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
}
