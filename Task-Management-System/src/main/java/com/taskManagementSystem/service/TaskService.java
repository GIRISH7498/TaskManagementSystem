package com.taskManagementSystem.service;

import com.taskManagementSystem.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(Long taskId);

    List<TaskDto> getAllTasks();

    TaskDto updateTask(Long taskId, TaskDto updatedTaskDetails);

    void deleteTask(Long taskId);

    TaskDto completed(Long taskId);

    TaskDto inComplete(Long taskId);
}
