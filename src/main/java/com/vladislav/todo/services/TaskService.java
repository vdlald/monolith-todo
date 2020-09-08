package com.vladislav.todo.services;

import com.vladislav.todo.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllUserTasks(Long userId);

    Task saveTask(Task task);
}
