package com.taskManagementSystem.controller;

import com.taskManagementSystem.dto.TaskDto;
import com.taskManagementSystem.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskDto savedTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id")  Long taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> tasksList = taskService.getAllTasks();
        return ResponseEntity.ok(tasksList);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long taskId, @RequestBody TaskDto updatedTaskDetails){
        TaskDto updatedTask = taskService.updateTask(taskId, updatedTaskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TaskDto> completed(@PathVariable("id") Long taskId){
        TaskDto taskDto = taskService.completed(taskId);
        return ResponseEntity.ok(taskDto);
    }

    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TaskDto> inComplete(@PathVariable("id") Long taskId){
        TaskDto taskDto = taskService.inComplete(taskId);
        return ResponseEntity.ok(taskDto);
    }
}
