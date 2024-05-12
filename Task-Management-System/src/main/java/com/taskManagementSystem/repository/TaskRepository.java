package com.taskManagementSystem.repository;

import com.taskManagementSystem.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel, Long>{

}
