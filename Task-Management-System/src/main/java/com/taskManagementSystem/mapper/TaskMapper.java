package com.taskManagementSystem.mapper;

import com.taskManagementSystem.dto.TaskDto;
import com.taskManagementSystem.entity.TaskModel;

public class TaskMapper {

    public static TaskDto mapToTaskDto(TaskModel taskModel){
        return new TaskDto(
                taskModel.getId(),
                taskModel.getTitle(),
                taskModel.getDescription(),
                taskModel.getDueDate(),
                taskModel.isCompleted()
        );
    }

    public static TaskModel mapToTaskModel(TaskDto taskDto){
        return new TaskModel(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getDueDate(),
                taskDto.isCompleted()
        );
    }
}
