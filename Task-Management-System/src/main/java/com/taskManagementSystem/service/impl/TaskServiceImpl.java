package com.taskManagementSystem.service.impl;

import com.taskManagementSystem.dto.TaskDto;
import com.taskManagementSystem.entity.TaskModel;
import com.taskManagementSystem.exception.ResourceNotFoundException;
import com.taskManagementSystem.mapper.TaskMapper;
import com.taskManagementSystem.repository.TaskRepository;
import com.taskManagementSystem.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Override
    public TaskDto createTask(TaskDto taskDto) {

        TaskModel taskModel = TaskMapper.mapToTaskModel(taskDto);
        TaskModel savedTask = taskRepository.save(taskModel);
        return TaskMapper.mapToTaskDto(savedTask);
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        TaskModel taskModel = taskRepository.findById(taskId).
                orElseThrow(() -> new ResourceNotFoundException("Task is not exists with given id: " + taskId));
        return TaskMapper.mapToTaskDto(taskModel);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskModel> taskModelList = taskRepository.findAll();
        return taskModelList.stream().map(TaskMapper::mapToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto updatedTaskDetails) {
        TaskModel taskModel = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task is not present with the given id: " + taskId)
        );

        taskModel.setTitle(updatedTaskDetails.getTitle());
        taskModel.setDescription(updatedTaskDetails.getDescription());
        taskModel.setDueDate(updatedTaskDetails.getDueDate());
        taskModel.setCompleted(updatedTaskDetails.isCompleted());

        TaskModel updatedTaskObj = taskRepository.save(taskModel);
        return TaskMapper.mapToTaskDto(updatedTaskObj);
    }

    @Override
    public void deleteTask(Long taskId) {
        TaskModel taskModel = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task is not present with the given id: " + taskId)
        );

        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDto completed(Long taskId) {
        TaskModel taskModel = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task is not present with the given id: " + taskId)
        );

        taskModel.setCompleted(Boolean.TRUE);
        TaskModel isCompleted = taskRepository.save(taskModel);

        return TaskMapper.mapToTaskDto(isCompleted);
    }

    @Override
    public TaskDto inComplete(Long taskId) {
        TaskModel taskModel = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task is not present with the given id: " + taskId)
        );

        taskModel.setCompleted(Boolean.FALSE);
        TaskModel inCompleted = taskRepository.save(taskModel);

        return TaskMapper.mapToTaskDto(inCompleted);
    }
}
